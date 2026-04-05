@file:JvmName("KtorBridge")
package kotlinx.kmp.util.core

import kotlinx.kmp.util.core.http.Headers
import kotlinx.kmp.util.core.http.HttpMethod
import kotlinx.kmp.util.core.http.QueryParams

/**
 * Bridge between SDK types and Ktor types.
 * Thin conversions — SDK types are minimal value wrappers, not ktor duplicates.
 */

// --- HttpMethod ---
fun HttpMethod.toKtor(): io.ktor.http.HttpMethod = io.ktor.http.HttpMethod(value)

// --- Headers ---
fun Headers.toKtor(): io.ktor.http.Headers = io.ktor.http.Headers.build {
    this@toKtor.names().forEach { name ->
        this@toKtor.values(name).forEach { value ->
            append(name, value)
        }
    }
}

fun io.ktor.http.Headers.toSdk(): Headers {
    val builder = Headers.builder()
    forEach { name, values -> values.forEach { builder.put(name, it) } }
    return builder.build()
}

// --- QueryParams ---
fun QueryParams.toKtor(): io.ktor.http.Parameters = io.ktor.http.Parameters.build {
    this@toKtor.keys().forEach { key ->
        this@toKtor.values(key).forEach { value ->
            append(key, value)
        }
    }
}
