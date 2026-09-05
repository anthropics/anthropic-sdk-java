package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import java.util.concurrent.CompletableFuture

/** A functional interface that asynchronously executes a request using a client. */
fun interface HttpClientExecuteAsync {

    fun executeAsync(
        nextClient: HttpClient,
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse>
}
