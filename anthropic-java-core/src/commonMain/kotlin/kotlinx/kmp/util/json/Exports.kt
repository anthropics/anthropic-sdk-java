/**
 * kotlinx.kmp.util.json — generic KMP JSON infrastructure.
 *
 * Re-exports from com.anthropic.core for JSON serialization types.
 * Any API-key-based service can use these without Anthropic SDK coupling.
 */
package kotlinx.kmp.util.json

// --- JSON value types ---
typealias JsonValue = com.anthropic.core.JsonValue
typealias JsonField<T> = com.anthropic.core.JsonField<T>
typealias JsonMissing = com.anthropic.core.JsonMissing
typealias JsonNull = com.anthropic.core.JsonNull
typealias KnownValue<T> = com.anthropic.core.KnownValue<T>
typealias JsonObject = com.anthropic.core.JsonObject
typealias JsonString = com.anthropic.core.JsonString
typealias JsonNumber = com.anthropic.core.JsonNumber
typealias JsonBoolean = com.anthropic.core.JsonBoolean

// --- Serialization ---
typealias BaseDeserializer<T> = com.anthropic.core.BaseDeserializer<T>
typealias BaseSerializer<T> = com.anthropic.core.BaseSerializer<T>
