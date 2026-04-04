package com.anthropic.core.http

import java.util.concurrent.CompletableFuture
import com.anthropic.core.RequestOptions

interface HttpClient : AutoCloseable {

    /** Blocking execution. */
    fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse

    /** Async execution returning CompletableFuture (JVM standard). */
    fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): java.util.concurrent.CompletableFuture<HttpResponse>

    /** Suspend-native async execution — use from coroutines. */
    suspend fun executeSuspend(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse

    override fun close()
}
