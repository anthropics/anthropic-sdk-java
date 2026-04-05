package kotlinx.kmp.util.core.handlers

import kotlinx.kmp.util.core.apiJson
import kotlinx.kmp.util.core.http.HttpResponse
import kotlinx.kmp.util.core.http.HttpResponse.Handler
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonElement

/**
 * JSON handler using kotlinx.serialization directly.
 * This is the KMP-native alternative to the Jackson-based jsonHandler.
 * Use this for new code that doesn't need Jackson compatibility.
 */
fun kotlinxJsonObjectHandler(): Handler<JsonObject> =
    object : Handler<JsonObject> {
        override fun handle(response: HttpResponse): JsonObject {
            val body = response.body().readUtf8()
            return apiJson.parseToJsonElement(body) as JsonObject
        }
    }

fun kotlinxJsonElementHandler(): Handler<JsonElement> =
    object : Handler<JsonElement> {
        override fun handle(response: HttpResponse): JsonElement {
            val body = response.body().readUtf8()
            return apiJson.parseToJsonElement(body)
        }
    }

/**
 * Decode JSON response to a specific type using kotlinx.serialization.
 * Requires the type to be @Serializable.
 */
inline fun <reified T> kotlinxJsonHandler(): Handler<T> =
    object : Handler<T> {
        override fun handle(response: HttpResponse): T {
            val body = response.body().readUtf8()
            return apiJson.decodeFromString<T>(body)
        }
    }
