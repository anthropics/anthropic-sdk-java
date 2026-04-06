package kotlinx.kmp.util.core.json

import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.KnownValue
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonNull
import kotlinx.kmp.util.core.JsonBoolean
import kotlinx.kmp.util.core.JsonNumber
import kotlinx.kmp.util.core.JsonString
import kotlinx.kmp.util.core.JsonArray
import kotlinx.kmp.util.core.JsonObject
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

/**
 * kotlinx.serialization [KSerializer] for [JsonValue] — the SDK's generic
 * JSON tree type.
 *
 * Maps between [JsonValue] and kotlinx.serialization's [JsonElement]:
 *   JsonNull    <-> JsonNull
 *   JsonMissing <-> (absent key — handled by enclosing object serializer)
 *   JsonBoolean <-> JsonPrimitive(boolean)
 *   JsonNumber  <-> JsonPrimitive(number)
 *   JsonString  <-> JsonPrimitive(string)
 *   JsonArray   <-> JsonArray
 *   JsonObject  <-> JsonObject
 */
object JsonValueSerializer : KSerializer<JsonValue> {

    override val descriptor: SerialDescriptor =
        JsonElement.serializer().descriptor

    override fun serialize(encoder: Encoder, value: JsonValue) {
        val element = toJsonElement(value)
        encoder.encodeSerializableValue(JsonElement.serializer(), element)
    }

    override fun deserialize(decoder: Decoder): JsonValue {
        val element = decoder.decodeSerializableValue(JsonElement.serializer())
        return fromJsonElement(element)
    }
}

/**
 * kotlinx.serialization [KSerializer] for [JsonField] — wraps a known
 * value or a raw [JsonValue].
 */
class JsonFieldSerializer<T : Any>(
    private val innerSerializer: KSerializer<T>,
) : KSerializer<JsonField<T>> {

    override val descriptor: SerialDescriptor = innerSerializer.descriptor

    override fun serialize(encoder: Encoder, value: JsonField<T>) {
        when (value) {
            is KnownValue -> encoder.encodeSerializableValue(innerSerializer, value.value)
            is JsonValue -> encoder.encodeSerializableValue(JsonValueSerializer, value)
        }
    }

    override fun deserialize(decoder: Decoder): JsonField<T> {
        return try {
            val v = decoder.decodeSerializableValue(innerSerializer)
            JsonField.of(v)
        } catch (_: Exception) {
            val element = decoder.decodeSerializableValue(JsonElement.serializer())
            fromJsonElement(element)
        }
    }
}

// --- JsonValue <-> JsonElement conversion utilities ---

/** Convert SDK [JsonValue] to kotlinx [JsonElement]. */
fun toJsonElement(value: JsonValue): JsonElement = when (value) {
    is JsonMissing -> kotlinx.serialization.json.JsonNull
    is JsonNull -> kotlinx.serialization.json.JsonNull
    is JsonBoolean -> JsonPrimitive(value.value)
    is JsonNumber -> JsonPrimitive(value.value)
    is JsonString -> JsonPrimitive(value.value)
    is JsonArray -> kotlinx.serialization.json.JsonArray(value.values.map { toJsonElement(it) })
    is JsonObject -> kotlinx.serialization.json.JsonObject(value.values.mapValues { toJsonElement(it.value) })
}

/** Convert kotlinx [JsonElement] to SDK [JsonValue]. */
fun fromJsonElement(element: JsonElement): JsonValue = when (element) {
    is kotlinx.serialization.json.JsonNull -> JsonNull.of()
    is JsonPrimitive -> when {
        element.isString -> JsonString.of(element.content)
        element.content == "true" || element.content == "false" -> JsonBoolean.of(element.content.toBoolean())
        else -> element.content.toLongOrNull()?.let { JsonNumber.of(it) }
            ?: element.content.toDoubleOrNull()?.let { JsonNumber.of(it) }
            ?: JsonString.of(element.content)
    }
    is kotlinx.serialization.json.JsonArray -> JsonArray.of(element.map { fromJsonElement(it) })
    is kotlinx.serialization.json.JsonObject -> JsonObject.of(element.mapValues { fromJsonElement(it.value) })
}
