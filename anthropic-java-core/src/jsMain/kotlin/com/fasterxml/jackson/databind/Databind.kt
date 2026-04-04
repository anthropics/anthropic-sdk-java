// JS compile-only stubs for com.fasterxml.jackson.databind
package com.fasterxml.jackson.databind

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.node.JsonNodeType
import kotlin.reflect.KClass

abstract class JsonNode : Iterable<JsonNode> {
    abstract val nodeType: JsonNodeType
    open val isObject: Boolean get() = false
    open val isArray: Boolean get() = false
    open val isTextual: Boolean get() = false
    open val isEmpty: Boolean get() = true

    open fun get(fieldName: String): JsonNode? = null
    open fun get(index: Int): JsonNode? = null
    open fun path(fieldName: String): JsonNode = MissingNode.instance
    open fun booleanValue(): Boolean = false
    open fun numberValue(): Number = 0
    open fun textValue(): String? = null
    open fun asText(): String = ""
    open fun asBoolean(): Boolean = false
    open fun binaryValue(): ByteArray? = null
    open fun size(): Int = 0

    open fun elements(): Iterator<JsonNode> = emptyList<JsonNode>().iterator()
    open fun fields(): Iterator<Map.Entry<String, JsonNode>> = emptyList<Map.Entry<String, JsonNode>>().iterator()
    open fun fieldNames(): Iterator<String> = emptyList<String>().iterator()

    override fun iterator(): Iterator<JsonNode> = elements()

    fun forEachIndexed(action: (Int, JsonNode) -> Unit) {
        var index = 0
        for (element in this) {
            action(index++, element)
        }
    }

    fun asSequence(): Sequence<JsonNode> = iterator().asSequence()
}

class MissingNode private constructor() : JsonNode() {
    override val nodeType: JsonNodeType = JsonNodeType.MISSING
    companion object {
        val instance: MissingNode = MissingNode()
    }
}

abstract class JsonDeserializer<T> {
    abstract fun deserialize(parser: JsonParser, context: DeserializationContext): T
    open fun getNullValue(context: DeserializationContext?): T? = null
}

abstract class JsonSerializer<T> {
    abstract fun serialize(value: T, gen: JsonGenerator, serializers: SerializerProvider)
}

class DeserializationContext {
    val contextualType: JavaType? = null
}

class SerializerProvider {
    fun defaultSerializeValue(value: Any?, gen: JsonGenerator) { TODO("JS stub") }
    fun findValueSerializer(type: KClass<*>): JsonSerializer<Any> = TODO("JS stub")
    fun findValueSerializer(type: Any): JsonSerializer<Any> = TODO("JS stub")
}

class JavaType {
    fun containedType(index: Int): JavaType? = null
}

class BeanProperty

open class ObjectMapper : ObjectCodec() {
    open fun <T> readValue(src: String, valueType: KClass<T>): T = TODO("JS stub")
    open fun <T> readValue(src: ByteArray, valueType: KClass<T>): T = TODO("JS stub")
    open fun <T> readValue(src: String, type: TypeReference<T>): T = TODO("JS stub")
    open fun writeValueAsBytes(value: Any?): ByteArray = TODO("JS stub")
    open fun writeValueAsString(value: Any?): String = TODO("JS stub")
    open fun readTree(content: String): JsonNode = TODO("JS stub")
    open fun <T> convertValue(fromValue: Any?, toValueType: TypeReference<T>): T = TODO("JS stub")
    open fun <T> convertValue(fromValue: Any?, toValueType: KClass<T>): T = TODO("JS stub")
    open fun valueToTree(fromValue: Any?): JsonNode = TODO("JS stub")
    open fun <T : JsonNode> valueToTree(fromValue: Any?, nodeType: KClass<T>): T = TODO("JS stub")
    fun readerFor(type: TypeReference<*>): ObjectMapper = this

    // ObjectCodec abstract implementations
    override fun <T> readValue(parser: JsonParser, type: TypeReference<T>): T = TODO("JS stub")
    override fun <T> readValue(parser: JsonParser, type: JavaType): T = TODO("JS stub")
    override fun treeAsTokens(node: JsonNode): JsonParser = TODO("JS stub")
}

enum class DeserializationFeature {
    ADJUST_DATES_TO_CONTEXT_TIME_ZONE,
    FAIL_ON_UNKNOWN_PROPERTIES,
}

enum class SerializationFeature {
    FLUSH_AFTER_WRITE_VALUE,
    WRITE_DATES_AS_TIMESTAMPS,
    WRITE_DURATIONS_AS_TIMESTAMPS,
}

enum class MapperFeature {
    ALLOW_COERCION_OF_SCALARS,
    AUTO_DETECT_CREATORS,
    AUTO_DETECT_FIELDS,
    AUTO_DETECT_GETTERS,
    AUTO_DETECT_IS_GETTERS,
    AUTO_DETECT_SETTERS,
}
