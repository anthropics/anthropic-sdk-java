package com.anthropic.core

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonArray as KJsonArray
import kotlinx.serialization.json.JsonObject as KJsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlinx.serialization.json.longOrNull

/**
 * KMP-native JSON configuration using kotlinx.serialization.
 * 
 * This provides a multiplatform JSON engine that can be used alongside
 * or as a replacement for Jackson. On JVM, Jackson is still used for 
 * model serialization (via annotations); this is for new KMP-native code.
 */
val anthropicJson: Json = Json {
    ignoreUnknownKeys = true
    isLenient = false
    encodeDefaults = false
    explicitNulls = false
    classDiscriminator = "type"
    prettyPrint = false
    coerceInputValues = false
}

// --- Bridge: JsonValue <-> kotlinx.serialization.json.JsonElement ---

/** Convert SDK JsonValue to kotlinx.serialization JsonElement */
fun JsonValue.toJsonElement(): JsonElement = when (this) {
    is com.anthropic.core.JsonNull -> kotlinx.serialization.json.JsonNull
    is JsonMissing -> kotlinx.serialization.json.JsonNull
    is JsonBoolean -> JsonPrimitive(value)
    is JsonNumber -> JsonPrimitive(value)
    is JsonString -> JsonPrimitive(value)
    is JsonArray -> KJsonArray(values.map { it.toJsonElement() })
    is JsonObject -> KJsonObject(values.mapValues { (_, v) -> v.toJsonElement() })
}

/** Convert kotlinx.serialization JsonElement to SDK JsonValue */
fun JsonValue.Companion.fromJsonElement(element: JsonElement): JsonValue = when (element) {
    is kotlinx.serialization.json.JsonNull -> com.anthropic.core.JsonNull.of()
    is JsonPrimitive -> when {
        element.booleanOrNull != null -> JsonBoolean.of(element.boolean)
        element.intOrNull != null -> JsonNumber.of(element.int)
        element.longOrNull != null -> JsonNumber.of(element.long)
        element.doubleOrNull != null -> JsonNumber.of(element.double)
        element.contentOrNull != null -> JsonString.of(element.content)
        else -> JsonString.of(element.content)
    }
    is KJsonArray -> JsonArray.of(element.map { fromJsonElement(it) })
    is KJsonObject -> JsonObject.of(element.mapValues { (_, v) -> fromJsonElement(v) })
}

/** Encode SDK JsonValue to JSON string using kotlinx.serialization */
fun JsonValue.toJsonString(): String = anthropicJson.encodeToString(
    kotlinx.serialization.json.JsonElement.serializer(), 
    toJsonElement()
)

/** Decode JSON string to SDK JsonValue using kotlinx.serialization */
fun JsonValue.Companion.fromJsonString(json: String): JsonValue =
    fromJsonElement(anthropicJson.parseToJsonElement(json))

// --- JsonField<T> ↔ kotlinx.serialization bridge ---
// JsonField<T> is conceptually equivalent to a nullable JsonElement:
// - KnownValue<T> → the actual JsonElement (with typed value)
// - JsonMissing → null (absent from JSON)
// - JsonNull → kotlinx.serialization.json.JsonNull
// - JsonValue subtypes → JsonElement

/**
 * Convert a JsonField to a nullable kotlinx.serialization JsonElement.
 * Returns null for JsonMissing (absent fields).
 */
fun <T : Any> JsonField<T>.toJsonElement(): JsonElement? = when (this) {
    is JsonMissing -> null
    is JsonValue -> (this as JsonValue).toJsonElement()
    is KnownValue -> {
        // Convert known value to JsonElement via JsonValue.from()
        val jv = JsonValue.from(value)
        jv.toJsonElement()
    }
}

/**
 * Create a JsonField from a kotlinx.serialization JsonElement.
 * A null element becomes JsonMissing.
 */
fun JsonField.Companion.fromJsonElement(element: JsonElement?): JsonField<*> =
    if (element == null) JsonMissing.of()
    else JsonValue.fromJsonElement(element)
