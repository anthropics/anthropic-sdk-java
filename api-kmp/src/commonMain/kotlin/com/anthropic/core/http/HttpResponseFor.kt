package com.anthropic.core.http

import okio.BufferedSource

interface HttpResponseFor<T> : HttpResponse {
    fun parse(): T
}

fun <T> HttpResponse.parseable(parse: () -> T): HttpResponseFor<T> =
    object : HttpResponseFor<T> {
        private val parsed: T by lazy { parse() }
        override fun parse(): T = parsed
        override fun statusCode(): Int = this@parseable.statusCode()
        override fun headers(): Headers = this@parseable.headers()
        override fun body(): BufferedSource = this@parseable.body()
        override fun close() = this@parseable.close()
    }
