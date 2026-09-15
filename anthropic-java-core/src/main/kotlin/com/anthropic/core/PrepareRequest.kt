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
        // `Accept` is only a default. It is added here, not in `ClientOptions`, so that a value set
        // on a derived builder replaces it.
        .also {
            if (
                !headers.names().contains("Accept") &&
                    !clientOptions.headers.names().contains("Accept") &&
                    !params._headers().names().contains("Accept")
            )
                it.putHeader("Accept", "application/json")
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
