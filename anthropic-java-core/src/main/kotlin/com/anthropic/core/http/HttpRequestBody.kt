package com.anthropic.core.http

import java.io.IOException
import java.io.OutputStream
import java.lang.AutoCloseable

interface HttpRequestBody : AutoCloseable {

    @Throws(IOException::class) fun writeTo(outputStream: OutputStream)

    fun contentType(): String?

    fun contentLength(): Long

    /**
     * Determines if a request can be repeated in a meaningful way, for example before doing a
     * retry.
     *
     * The most typical case when a request can't be retried is if the request body is being
     * streamed. In this case the body data isn't available on subsequent attempts.
     */
    fun repeatable(): Boolean

    override fun close()
}
