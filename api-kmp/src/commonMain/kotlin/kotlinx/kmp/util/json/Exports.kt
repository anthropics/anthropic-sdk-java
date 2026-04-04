/**
 * kotlinx.kmp.util.json — Wire-style field/value containers.
 *
 * Kmp* names are the canonical KMP types (format-agnostic, not JSON-specific).
 * Json* typealiases provided for backward compat.
 */
package kotlinx.kmp.util.json

// Canonical Kmp* names — format-agnostic (JSON, MsgPack, Protobuf, CBOR)
typealias KmpField<T> = com.anthropic.core.JsonField<T>
typealias KmpValue = com.anthropic.core.JsonValue
typealias KmpKnownValue<T> = com.anthropic.core.KnownValue<T>
typealias KmpMissing = com.anthropic.core.JsonMissing
typealias KmpNull = com.anthropic.core.JsonNull
typealias KmpBoolean = com.anthropic.core.JsonBoolean
typealias KmpNumber = com.anthropic.core.JsonNumber
typealias KmpString = com.anthropic.core.JsonString
typealias KmpArray = com.anthropic.core.JsonArray
typealias KmpObject = com.anthropic.core.JsonObject

// Backward compat Json* names
typealias JsonField<T> = com.anthropic.core.JsonField<T>
typealias JsonValue = com.anthropic.core.JsonValue
typealias KnownValue<T> = com.anthropic.core.KnownValue<T>
typealias JsonMissing = com.anthropic.core.JsonMissing
typealias JsonNull = com.anthropic.core.JsonNull
typealias JsonBoolean = com.anthropic.core.JsonBoolean
typealias JsonNumber = com.anthropic.core.JsonNumber
typealias JsonString = com.anthropic.core.JsonString
typealias JsonArray = com.anthropic.core.JsonArray
typealias JsonObject = com.anthropic.core.JsonObject
typealias BaseDeserializer<T> = com.anthropic.core.BaseDeserializer<T>
typealias BaseSerializer<T> = com.anthropic.core.BaseSerializer<T>
