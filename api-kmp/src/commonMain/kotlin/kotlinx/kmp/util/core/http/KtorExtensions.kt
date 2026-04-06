@file:JvmName("KtorExtensions")
package kotlinx.kmp.util.core.http

import kotlin.jvm.JvmName
import kotlinx.kmp.util.core.JsonArray
import kotlinx.kmp.util.core.JsonBoolean
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonNull
import kotlinx.kmp.util.core.JsonNumber
import kotlinx.kmp.util.core.JsonObject
import kotlinx.kmp.util.core.JsonString
import kotlinx.kmp.util.core.JsonValue

/**
 * Extensions on ktor Headers/Parameters/HttpClient for SDK integration.
 *
 * These add JsonValue support and convenience methods that ktor doesn't
 * provide natively. They bridge the SDK's Wire-style value types with
 * ktor's HTTP infrastructure.
 */

// ============================================================================
// HeadersBuilder extensions — add JsonValue serialization + replace semantics
// ============================================================================

/** Put a JsonValue as a header — handles all value types. */
fun io.ktor.http.HeadersBuilder.put(name: String, value: JsonValue) {
    when (value) {
        is JsonMissing, is JsonNull -> {}
        is JsonBoolean -> append(name, value.value.toString())
        is JsonNumber -> append(name, value.value.toString())
        is JsonString -> append(name, value.value)
        is JsonArray -> value.values.forEach { put(name, it) }
        is JsonObject -> value.values.forEach { (nestedName, v) -> put("$name.$nestedName", v) }
    }
}

/** Replace a header value (remove existing + append new). */
fun io.ktor.http.HeadersBuilder.replace(name: String, value: String) {
    remove(name)
    append(name, value)
}

/** Replace a header with multiple values. */
fun io.ktor.http.HeadersBuilder.replace(name: String, values: Iterable<String>) {
    remove(name)
    values.forEach { append(name, it) }
}

/** Replace all headers from another Headers instance. */
fun io.ktor.http.HeadersBuilder.replaceAll(headers: io.ktor.http.Headers) {
    headers.names().forEach { name ->
        remove(name)
        headers.getAll(name)?.forEach { append(name, it) }
    }
}

/** Replace all headers from a map. */
fun io.ktor.http.HeadersBuilder.replaceAll(headers: Map<String, Iterable<String>>) {
    headers.forEach { (name, values) -> replace(name, values) }
}

/** Put all headers from another Headers instance. */
fun io.ktor.http.HeadersBuilder.putAll(headers: io.ktor.http.Headers) {
    headers.forEach { name, values -> values.forEach { append(name, it) } }
}

/** Put all headers from a map. */
fun io.ktor.http.HeadersBuilder.putAll(headers: Map<String, Iterable<String>>) {
    headers.forEach { (name, values) -> values.forEach { append(name, it) } }
}

/** Remove all headers by name set. */
fun io.ktor.http.HeadersBuilder.removeAll(names: Set<String>) {
    names.forEach { remove(it) }
}

// ============================================================================
// ParametersBuilder extensions — add JsonValue serialization + replace semantics
// ============================================================================

/** Put a JsonValue as a query parameter. */
fun io.ktor.http.ParametersBuilder.put(key: String, value: JsonValue) {
    when (value) {
        is JsonMissing, is JsonNull -> {}
        is JsonBoolean -> append(key, value.value.toString())
        is JsonNumber -> append(key, value.value.toString())
        is JsonString -> append(key, value.value)
        is JsonArray -> value.values.forEach { put(key, it) }
        is JsonObject -> value.values.forEach { (nestedKey, v) -> put("$key[$nestedKey]", v) }
    }
}

/** Replace a query parameter (remove + append). */
fun io.ktor.http.ParametersBuilder.replace(key: String, value: String) {
    remove(key)
    append(key, value)
}

/** Replace a query parameter with multiple values. */
fun io.ktor.http.ParametersBuilder.replace(key: String, values: Iterable<String>) {
    remove(key)
    values.forEach { append(key, it) }
}

/** Replace all from another Parameters instance. */
fun io.ktor.http.ParametersBuilder.replaceAll(params: io.ktor.http.Parameters) {
    params.names().forEach { key ->
        remove(key)
        params.getAll(key)?.forEach { append(key, it) }
    }
}

/** Replace all from a map. */
fun io.ktor.http.ParametersBuilder.replaceAll(params: Map<String, Iterable<String>>) {
    params.forEach { (key, values) -> replace(key, values) }
}

/** Put all from another Parameters instance. */
fun io.ktor.http.ParametersBuilder.putAll(params: io.ktor.http.Parameters) {
    params.forEach { key, values -> values.forEach { append(key, it) } }
}

/** Put all from a map. */
fun io.ktor.http.ParametersBuilder.putAll(params: Map<String, Iterable<String>>) {
    params.forEach { (key, values) -> values.forEach { append(key, it) } }
}

/** Remove all by key set. */
fun io.ktor.http.ParametersBuilder.removeAll(keys: Set<String>) {
    keys.forEach { remove(it) }
}
