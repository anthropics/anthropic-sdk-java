package com.anthropic.core.http

import com.anthropic.core.PlatformCloseable
import com.anthropic.core.PlatformFuture
import com.anthropic.core.RequestOptions

interface HttpClient : PlatformCloseable {

    fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse

    fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): PlatformFuture<HttpResponse>

    override fun close()
}
