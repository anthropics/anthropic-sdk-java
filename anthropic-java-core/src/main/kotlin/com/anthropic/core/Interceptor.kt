package com.anthropic.core

import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import java.util.concurrent.CompletableFuture

interface Interceptor {

    interface Chain {

        fun request(): HttpRequest

        fun proceed(request: HttpRequest): HttpResponse
    }

    interface AsyncChain {

        fun request(): HttpRequest

        fun proceed(request: HttpRequest): CompletableFuture<HttpResponse>
    }

    fun intercept(chain: Chain): HttpResponse

    fun interceptAsync(chain: AsyncChain): CompletableFuture<HttpResponse>
}
