package com.anthropic.core.http

import okio.BufferedSource

interface HttpResponse : AutoCloseable {
    fun statusCode(): Int
    fun headers(): Headers
    fun requestId(): String? = headers().values("request-id").firstOrNull()
    fun body(): BufferedSource
    override fun close()

    interface Handler<T> {
        fun handle(response: HttpResponse): T
    }
}
