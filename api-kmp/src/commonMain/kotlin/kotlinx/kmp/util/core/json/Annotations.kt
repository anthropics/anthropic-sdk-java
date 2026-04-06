@file:Suppress("unused")

package kotlinx.kmp.util.core.json

/**
 * KMP-compatible typealiases for Jackson annotations.
 *
 * jackson-annotations is a pure metadata JAR (no JVM-only bytecode) already
 * in commonMain deps. These typealiases give model code a single stable import
 * path: `kotlinx.kmp.util.core.json.JsonProperty` instead of directly
 * `com.fasterxml.jackson.annotation.JsonProperty`.
 *
 * If the serialization backend changes in the future (e.g., to
 * kotlinx.serialization or Moshi), only these typealiases need updating —
 * zero model file changes.
 *
 * Usage: in model files, replace
 *   import com.fasterxml.jackson.annotation.JsonProperty
 * with
 *   import kotlinx.kmp.util.core.json.JsonProperty
 */

// --- com.fasterxml.jackson.annotation ---

typealias JsonProperty = com.fasterxml.jackson.annotation.JsonProperty
typealias JsonCreator = com.fasterxml.jackson.annotation.JsonCreator
typealias JsonCreatorMode = com.fasterxml.jackson.annotation.JsonCreator.Mode
typealias JsonAnyGetter = com.fasterxml.jackson.annotation.JsonAnyGetter
typealias JsonAnySetter = com.fasterxml.jackson.annotation.JsonAnySetter
typealias JsonIgnore = com.fasterxml.jackson.annotation.JsonIgnore
typealias JsonInclude = com.fasterxml.jackson.annotation.JsonInclude
typealias JsonClassDescription = com.fasterxml.jackson.annotation.JsonClassDescription
typealias JsonPropertyDescription = com.fasterxml.jackson.annotation.JsonPropertyDescription
typealias JsonTypeName = com.fasterxml.jackson.annotation.JsonTypeName
typealias JsonValue = com.fasterxml.jackson.annotation.JsonValue

// --- com.fasterxml.jackson.databind.annotation ---

typealias JsonDeserialize = com.fasterxml.jackson.databind.annotation.JsonDeserialize
typealias JsonSerialize = com.fasterxml.jackson.databind.annotation.JsonSerialize

// --- com.fasterxml.jackson.module.kotlin ---

// Convenience: use inline reified function instead of typealias for TypeReference
inline fun <reified T> jacksonTypeRef(): com.fasterxml.jackson.core.type.TypeReference<T> =
    object : com.fasterxml.jackson.core.type.TypeReference<T>() {}

// --- Jackson runtime types used in model (de)serializer inner classes ---
// These are JVM-only at runtime. For future JS/Native targets, these would
// need to be replaced with kotlinx.serialization equivalents or stubs.

typealias ObjectCodec = com.fasterxml.jackson.core.ObjectCodec
typealias JsonNode = com.fasterxml.jackson.databind.JsonNode
typealias JsonGenerator = com.fasterxml.jackson.core.JsonGenerator
typealias SerializerProvider = com.fasterxml.jackson.databind.SerializerProvider
typealias ObjectNode = com.fasterxml.jackson.databind.node.ObjectNode
typealias ObjectMapper = com.fasterxml.jackson.databind.ObjectMapper
typealias JsonNodeType = com.fasterxml.jackson.databind.node.JsonNodeType
