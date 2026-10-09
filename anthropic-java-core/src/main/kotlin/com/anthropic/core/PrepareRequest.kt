@file:JvmName("PrepareRequest")

package com.anthropic.core

import com.anthropic.core.http.HttpRequest
import java.util.concurrent.CompletableFuture

@JvmSynthetic
internal fun HttpRequest.prepare(clientOptions: ClientOptions, params: Params): HttpRequest =
    toBuilder()
        .putAllQueryParams(clientOptions.queryParams)
        .replaceAllQueryParams(params._queryParams())
        .putAllHeaders(clientOptions.headers)
        .replaceAllHeaders(params._headers())
        .also { builder ->
            val sources = listOf(headers, clientOptions.headers, params._headers())
            for ((name, value) in DEFAULT_HEADERS) {
                if (sources.none { it.names().contains(name) }) {
                    builder.putHeader(name, value)
                }
            }
        }
        .build()

@JvmSynthetic
internal fun HttpRequest.prepareAsync(
    clientOptions: ClientOptions,
    params: Params,
): CompletableFuture<HttpRequest> =
    // This async version exists to make it easier to add async specific preparation logic in the
    // future.
    CompletableFuture.completedFuture(prepare(clientOptions, params))

/**
 * Headers that are only defaults. `prepare` adds them, not `ClientOptions`, so that a value set on
 * a derived builder replaces them.
 */
private val DEFAULT_HEADERS: Map<String, String> = mapOf("Accept" to "application/json")
