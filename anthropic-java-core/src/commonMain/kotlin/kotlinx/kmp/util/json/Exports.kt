/**
 * kotlinx.kmp.util.json — Wire-style generic field/value containers.
 *
 * These are format-agnostic value types implementing Wire's field presence
 * semantics (present/absent/null). Despite the "Json" prefix (historical),
 * they work across all serialization formats: JSON, MsgPack, Protobuf, CBOR.
 *
 * | Type          | Wire Equivalent      | Meaning                |
 * |---------------|----------------------|------------------------|
 * | KnownValue<T> | field is set         | Typed value present    |
 * | JsonMissing   | field not in message | Absent / default       |
 * | JsonNull      | field set to null    | Explicit null          |
 * | JsonValue     | Any / raw bytes      | Untyped value          |
 *
 * Re-exports from com.anthropic.core. Any API-key-based service can
 * use these without Anthropic SDK coupling.
 */
package kotlinx.kmp.util.json

// --- Wire-style field containers (format-agnostic, "Json" prefix is historical) ---

/** Field<T> with presence semantics: KnownValue | Missing | Null | untyped Value */
typealias JsonField<T> = com.anthropic.core.JsonField<T>

/** Untyped value: Boolean | Number | String | Array | Object | Missing | Null */
typealias JsonValue = com.anthropic.core.JsonValue

/** Field present with typed value T */
typealias KnownValue<T> = com.anthropic.core.KnownValue<T>

/** Field absent from payload (Wire: field not set / default) */
typealias JsonMissing = com.anthropic.core.JsonMissing

/** Field explicitly null (Wire: field set to null) */
typealias JsonNull = com.anthropic.core.JsonNull

// --- Primitive value types ---
typealias JsonBoolean = com.anthropic.core.JsonBoolean
typealias JsonNumber = com.anthropic.core.JsonNumber
typealias JsonString = com.anthropic.core.JsonString

// --- Composite value types ---
typealias JsonArray = com.anthropic.core.JsonArray
typealias JsonObject = com.anthropic.core.JsonObject

// --- Serialization (JVM Jackson bridge) ---
typealias BaseDeserializer<T> = com.anthropic.core.BaseDeserializer<T>
typealias BaseSerializer<T> = com.anthropic.core.BaseSerializer<T>
