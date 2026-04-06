package kotlinx.kmp.util.core.json

// --- Jackson runtime type aliases (JVM only) ---
// These give model (de)serializer inner classes a stable import path.

typealias ObjectCodec = com.fasterxml.jackson.core.ObjectCodec
typealias JsonNode = com.fasterxml.jackson.databind.JsonNode
typealias JsonGenerator = com.fasterxml.jackson.core.JsonGenerator
typealias SerializerProvider = com.fasterxml.jackson.databind.SerializerProvider
typealias ObjectNode = com.fasterxml.jackson.databind.node.ObjectNode
typealias ObjectMapper = com.fasterxml.jackson.databind.ObjectMapper
typealias JsonNodeType = com.fasterxml.jackson.databind.node.JsonNodeType

inline fun <reified T> jacksonTypeRef(): com.fasterxml.jackson.core.type.TypeReference<T> =
    object : com.fasterxml.jackson.core.type.TypeReference<T>() {}
