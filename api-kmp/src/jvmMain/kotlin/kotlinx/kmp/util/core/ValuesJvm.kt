@file:JvmName("ValuesJvm")

package kotlinx.kmp.util.core

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider

private val JSON_MAPPER by lazy { jsonMapper() }

fun <R : Any> JsonValue.convert(type: TypeReference<R>): R? = JSON_MAPPER.convertValue(this, type)
fun <R : Any> JsonValue.convert(type: Class<R>): R? = JSON_MAPPER.convertValue(this, type)

// --- Jackson bridge: JsonValue.fromJsonNode ---

fun JsonValue.Companion.fromJsonNode(node: JsonNode): JsonValue =
    when (node.nodeType) {
        com.fasterxml.jackson.databind.node.JsonNodeType.MISSING -> JsonMissing.of()
        com.fasterxml.jackson.databind.node.JsonNodeType.NULL -> JsonNull.of()
        com.fasterxml.jackson.databind.node.JsonNodeType.BOOLEAN -> JsonBoolean.of(node.booleanValue())
        com.fasterxml.jackson.databind.node.JsonNodeType.NUMBER -> JsonNumber.of(node.numberValue())
        com.fasterxml.jackson.databind.node.JsonNodeType.STRING -> JsonString.of(node.textValue())
        com.fasterxml.jackson.databind.node.JsonNodeType.ARRAY -> JsonArray.of(node.elements().asSequence().map { fromJsonNode(it) }.toList())
        com.fasterxml.jackson.databind.node.JsonNodeType.OBJECT -> JsonObject.of(node.fields().asSequence().map { it.key to fromJsonNode(it.value) }.toMap())
        com.fasterxml.jackson.databind.node.JsonNodeType.BINARY, com.fasterxml.jackson.databind.node.JsonNodeType.POJO, null -> throw IllegalStateException("Unexpected JsonNode type: ${node.nodeType}")
    }

// --- Jackson Deserializers ---

class JsonFieldDeserializer(private val type: JavaType? = null) :
    BaseDeserializer<JsonField<*>>(JsonField::class) {
    override fun createContextual(ctx: DeserializationContext, prop: BeanProperty?): JsonDeserializer<JsonField<*>> =
        JsonFieldDeserializer(ctx.contextualType?.containedType(0))
    override fun ObjectCodec.deserialize(node: JsonNode): JsonField<*> =
        type?.let { tryDeserialize<Any>(node, type) }?.let { JsonField.of(it) } ?: JsonValue.fromJsonNode(node)
    override fun getNullValue(ctx: DeserializationContext): JsonField<*> = JsonNull.of()
}

class JsonValueDeserializer : BaseDeserializer<JsonValue>(JsonValue::class) {
    override fun ObjectCodec.deserialize(node: JsonNode): JsonValue = JsonValue.fromJsonNode(node)
    override fun getNullValue(ctx: DeserializationContext?): JsonValue = JsonNull.of()
}

// --- Jackson Serializers ---

class JsonMissingSerializer : BaseSerializer<JsonMissing>(JsonMissing::class) {
    override fun serialize(value: JsonMissing, gen: JsonGenerator, prov: SerializerProvider) {
        throw IllegalStateException("JsonMissing cannot be serialized")
    }
}

class KnownValueSerializer : BaseSerializer<KnownValue<*>>(KnownValue::class) {
    override fun serialize(value: KnownValue<*>, gen: JsonGenerator, prov: SerializerProvider) {
        val inner = value.value
        // Use registered custom serializer for JsonValue subtypes
        val serializer = prov.findValueSerializer(inner::class.java)
        serializer.serialize(inner, gen, prov)
    }
}

class JsonBooleanSerializer : BaseSerializer<JsonBoolean>(JsonBoolean::class) {
    override fun serialize(value: JsonBoolean, gen: JsonGenerator, prov: SerializerProvider) = gen.writeBoolean(value.value)
}

class JsonNumberSerializer : BaseSerializer<JsonNumber>(JsonNumber::class) {
    override fun serialize(value: JsonNumber, gen: JsonGenerator, prov: SerializerProvider) = gen.writeNumber(value.value.toString())
}

class JsonStringSerializer : BaseSerializer<JsonString>(JsonString::class) {
    override fun serialize(value: JsonString, gen: JsonGenerator, prov: SerializerProvider) = gen.writeString(value.value)
}

class JsonArraySerializer : BaseSerializer<JsonArray>(JsonArray::class) {
    override fun serialize(value: JsonArray, gen: JsonGenerator, prov: SerializerProvider) {
        gen.writeStartArray(); value.values.forEach { prov.defaultSerializeValue(it, gen) }; gen.writeEndArray()
    }
}

class JsonObjectSerializer : BaseSerializer<JsonObject>(JsonObject::class) {
    override fun serialize(value: JsonObject, gen: JsonGenerator, prov: SerializerProvider) {
        gen.writeStartObject(); value.values.forEach { (k, v) -> gen.writeFieldName(k); prov.defaultSerializeValue(v, gen) }; gen.writeEndObject()
    }
}

class JsonNullSerializer : BaseSerializer<JsonNull>(JsonNull::class) {
    override fun serialize(value: JsonNull, gen: JsonGenerator, prov: SerializerProvider) = gen.writeNull()
}

// --- JVM Check functions ---

actual fun checkJsonVersionCompatibility() {
    val bad = RUNTIME_JACKSON_VERSIONS.mapNotNull {
        val r = BAD_JACKSON_VERSIONS[it.toString()]
        when {
            it.majorVersion != MIN_JACKSON.majorVersion -> it to "incompatible major"
            it.minorVersion < MIN_JACKSON.minorVersion -> it to "minor too low"
            it.minorVersion == MIN_JACKSON.minorVersion && it.patchLevel < MIN_JACKSON.patchLevel -> it to "patch too low"
            r != null -> it to r; else -> null
        }
    }
    check(bad.isEmpty()) { "Incompatible Jackson: ${bad.joinToString { "${it.first}(${it.second})" }}" }
}

private val MIN_JACKSON = com.fasterxml.jackson.core.util.VersionUtil.parseVersion("2.13.4", null, null)
private val BAD_JACKSON_VERSIONS = mapOf("2.18.1" to "https://github.com/FasterXML/jackson-databind/issues/4639")
private val RUNTIME_JACKSON_VERSIONS = listOf(
    com.fasterxml.jackson.core.json.PackageVersion.VERSION,
    com.fasterxml.jackson.databind.cfg.PackageVersion.VERSION,
    com.fasterxml.jackson.datatype.jdk8.PackageVersion.VERSION,
    com.fasterxml.jackson.datatype.jsr310.PackageVersion.VERSION,
    com.fasterxml.jackson.module.kotlin.PackageVersion.VERSION,
)

// --- JVM Utils ---

fun <K, V> java.util.SortedMap<K, V>.toImmutable(): java.util.SortedMap<K, V> =
    if (isEmpty()) java.util.Collections.emptySortedMap()
    else java.util.Collections.unmodifiableSortedMap(toSortedMap(comparator()))

fun <T> java.util.concurrent.locks.Lock.withLockAsync(
    action: () -> java.util.concurrent.CompletableFuture<T>
): java.util.concurrent.CompletableFuture<T> {
    lock()
    val future = try { action() } catch (e: Throwable) { unlock(); throw e }
    future.whenComplete { _, _ -> unlock() }
    return future
}
