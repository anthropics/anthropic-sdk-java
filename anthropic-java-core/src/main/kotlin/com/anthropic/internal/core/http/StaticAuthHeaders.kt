@file:JvmName("StaticAuthHeaders")

package com.anthropic.internal.core.http

import com.anthropic.core.http.HttpRequest

/**
 * Returns the request with the static first-party credential headers attached, or unchanged if it
 * already carries either auth header, so credentials set at the client or request level win.
 *
 * This is the single implementation of static credential application; both the client-level auth
 * layer ([HeaderAuthHttpClient]) and the backend-level fallback must apply credentials identically.
 */
@JvmSynthetic
internal fun HttpRequest.withStaticAuthHeaders(apiKey: String?, authToken: String?): HttpRequest {
    val hasApiKey = headers.names().contains("X-Api-Key")
    val hasAuthorization = headers.names().contains("Authorization")
    if (hasApiKey || hasAuthorization) {
        return this
    }

    return toBuilder()
        .apply {
            apiKey?.let { putHeader("X-Api-Key", it) }
            authToken?.let { putHeader("Authorization", "Bearer $it") }
        }
        .build()
}
