package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

/**
 * A delegating wrapper around an `HttpClient` that completes its futures in [completionExecutor].
 *
 * It closes a response that [completionExecutor] rejects.
 */
internal class CompletionExecutorHttpClient(
    private val httpClient: HttpClient,
    private val completionExecutor: Executor,
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
        httpClient.execute(request, requestOptions)

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> {
        val response = httpClient.executeAsync(request, requestOptions)
        return response.whenCompleteAsync({ _, _ -> }, completionExecutor).whenComplete { _, e ->
            if (e != null) {
                response.thenAccept { it.close() }
            }
        }
    }

    override fun close() = httpClient.close()
}
