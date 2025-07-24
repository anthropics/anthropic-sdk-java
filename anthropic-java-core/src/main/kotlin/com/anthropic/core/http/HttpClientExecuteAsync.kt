// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import java.util.concurrent.CompletableFuture

fun interface HttpClientExecuteAsync {

    fun executeAsync(
        httpClient: HttpClient,
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse>
}
