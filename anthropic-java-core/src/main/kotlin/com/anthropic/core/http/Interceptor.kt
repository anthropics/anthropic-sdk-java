// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import java.util.concurrent.CompletableFuture

fun interface Interceptor {

    companion object {

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

@JvmSynthetic
internal fun Interceptor?.intercept(httpClient: HttpClient) =
    this?.intercept(httpClient) ?: httpClient
