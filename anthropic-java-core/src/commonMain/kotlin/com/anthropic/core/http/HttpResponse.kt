package com.anthropic.core.http

import java.io.InputStream

interface HttpResponse : AutoCloseable {
    fun statusCode(): Int
    fun headers(): Headers
    fun requestId(): String? = headers().values("request-id").firstOrNull()
    fun body(): java.io.InputStream
    override fun close()

    interface Handler<T> {
        fun handle(response: HttpResponse): T
    }
}
