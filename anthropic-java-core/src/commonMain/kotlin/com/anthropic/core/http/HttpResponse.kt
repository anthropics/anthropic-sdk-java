package com.anthropic.core.http

import com.anthropic.core.PlatformCloseable
import com.anthropic.core.PlatformInputStream

interface HttpResponse : PlatformCloseable {
    fun statusCode(): Int
    fun headers(): Headers
    fun requestId(): String? = headers().values("request-id").firstOrNull()
    fun body(): PlatformInputStream
    override fun close()

    interface Handler<T> {
        fun handle(response: HttpResponse): T
    }
}
