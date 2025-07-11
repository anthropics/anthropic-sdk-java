package com.anthropic.core

import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import java.util.concurrent.CompletableFuture

class InterceptingHttpClient
private constructor(
    private val httpClient: HttpClient,
    private val interceptors: List<Interceptor>,
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
        InterceptorsChain(request, interceptors(requestOptions)).proceed(request)

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        InterceptorsAsyncChain(request, interceptors(requestOptions)).proceed(request)

    private fun interceptors(requestOptions: RequestOptions): List<Interceptor> {
        val lastInterceptor =
            object : Interceptor {

                override fun intercept(chain: Interceptor.Chain): HttpResponse =
                    httpClient.execute(chain.request(), requestOptions)

                override fun interceptAsync(
                    chain: Interceptor.AsyncChain
                ): CompletableFuture<HttpResponse> =
                    httpClient.executeAsync(chain.request(), requestOptions)
            }
        return interceptors + listOf(lastInterceptor)
    }

    override fun close() = httpClient.close()

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var httpClient: HttpClient? = null
        private var interceptors: List<Interceptor> = emptyList()

        fun httpClient(httpClient: HttpClient) = apply { this.httpClient = httpClient }

        fun interceptors(interceptors: List<Interceptor>) = apply {
            this.interceptors = interceptors.toImmutable()
        }

        fun build(): HttpClient =
            InterceptingHttpClient(checkRequired("httpClient", httpClient), interceptors)
    }
}

private data class InterceptorsChain(
    private val request: HttpRequest,
    private val interceptors: List<Interceptor>,
) : Interceptor.Chain {

    override fun request(): HttpRequest = request

    override fun proceed(request: HttpRequest): HttpResponse {
        val nextChain =
            copy(request = request, interceptors = interceptors.subList(1, interceptors.size))
        return interceptors.first().intercept(nextChain)
    }
}

private data class InterceptorsAsyncChain(
    private val request: HttpRequest,
    private val interceptors: List<Interceptor>,
) : Interceptor.AsyncChain {

    override fun request(): HttpRequest = request

    override fun proceed(request: HttpRequest): CompletableFuture<HttpResponse> {
        val nextChain =
            copy(request = request, interceptors = interceptors.subList(1, interceptors.size))
        return interceptors.first().interceptAsync(nextChain)
    }
}
