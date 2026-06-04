// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import java.util.concurrent.CompletableFuture

/**
 * A functional interface that wraps an [HttpClient] to read and/or modify its requests and/or
 * responses.
 */
fun interface Interceptor {

    companion object {

        /**
         * Returns an [Interceptor] that intercepts synchronous requests using [httpClientExecute]
         * and throws for asynchronous requests.
         */
        @JvmStatic
        fun syncOnly(httpClientExecute: HttpClientExecute) = Interceptor { client ->
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse = httpClientExecute.execute(client, request, requestOptions)

                override fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> =
                    throw UnsupportedOperationException("Sync only client does not support async")

                override fun close() = client.close()
            }
        }

        /**
         * Returns an [Interceptor] that intercepts asynchronous requests using
         * [httpClientExecuteAsync] and throws for synchronous requests.
         */
        @JvmStatic
        fun asyncOnly(httpClientExecuteAsync: HttpClientExecuteAsync) = Interceptor { client ->
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse =
                    throw UnsupportedOperationException("Async only client does not support sync")

                override fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> =
                    httpClientExecuteAsync.executeAsync(client, request, requestOptions)

                override fun close() = client.close()
            }
        }
    }

    fun intercept(httpClient: HttpClient): HttpClient
}

/**
 * Intercepts the given [httpClient] by applying the [Interceptor] list from right to left, such
 * that each interceptor in the list delegates to the next interceptor in the list.
 */
@JvmSynthetic
internal fun List<Interceptor>.intercept(httpClient: HttpClient) =
    foldRight(httpClient) { interceptor, wrappedHttpClient ->
        interceptor.intercept(wrappedHttpClient)
    }
