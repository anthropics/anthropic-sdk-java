@file:JvmName("KtorBridge")
package com.anthropic.core

import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.QueryParams
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject as KJsonObject
import kotlinx.serialization.json.JsonArray as KJsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonNull as KJsonNull

/**
 * Bridge between SDK custom types and Ktor/kotlinx.serialization types.
 * 
 * This enables code to work with either the SDK's types (for backward compat)
 * or Ktor/kotlinx types directly (for new KMP-native code).
 */

// --- HttpMethod ---
fun HttpMethod.toKtor(): io.ktor.http.HttpMethod = when (this) {
    HttpMethod.GET -> io.ktor.http.HttpMethod.Get
    HttpMethod.POST -> io.ktor.http.HttpMethod.Post
    HttpMethod.PUT -> io.ktor.http.HttpMethod.Put
    HttpMethod.DELETE -> io.ktor.http.HttpMethod.Delete
    HttpMethod.PATCH -> io.ktor.http.HttpMethod.Patch
    HttpMethod.HEAD -> io.ktor.http.HttpMethod.Head
    HttpMethod.OPTIONS -> io.ktor.http.HttpMethod.Options
    HttpMethod.CONNECT -> io.ktor.http.HttpMethod("CONNECT")
    HttpMethod.TRACE -> io.ktor.http.HttpMethod("TRACE")
}

fun io.ktor.http.HttpMethod.toSdk(): HttpMethod = when (this) {
    io.ktor.http.HttpMethod.Get -> HttpMethod.GET
    io.ktor.http.HttpMethod.Post -> HttpMethod.POST
    io.ktor.http.HttpMethod.Put -> HttpMethod.PUT
    io.ktor.http.HttpMethod.Delete -> HttpMethod.DELETE
    io.ktor.http.HttpMethod.Patch -> HttpMethod.PATCH
    io.ktor.http.HttpMethod.Head -> HttpMethod.HEAD
    io.ktor.http.HttpMethod.Options -> HttpMethod.OPTIONS
    else -> HttpMethod.valueOf(value.uppercase())
}

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

// --- JsonValue ↔ kotlinx.serialization.json.JsonElement ---
// (Already defined in JsonConfiguration.kt as toJsonElement() / fromJsonElement())
