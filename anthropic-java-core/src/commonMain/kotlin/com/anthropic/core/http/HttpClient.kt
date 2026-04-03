package com.anthropic.core.http

import java.util.concurrent.CompletableFuture
import com.anthropic.core.RequestOptions

interface HttpClient : AutoCloseable {

    fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse

    fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): java.util.concurrent.CompletableFuture<HttpResponse>

    override fun close()
}
