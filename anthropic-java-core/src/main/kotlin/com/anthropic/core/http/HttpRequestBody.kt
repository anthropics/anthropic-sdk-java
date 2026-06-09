package com.anthropic.core.http

import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.lang.AutoCloseable

interface HttpRequestBody : AutoCloseable {

    companion object {

        /**
         * Returns a new [HttpRequestBody] containing the given value serialized to a JSON string.
         *
         * The content type will be set to `application/json` and will use UTF-8 encoding.
         */
        @JvmStatic fun ofJson(value: Any): HttpRequestBody = json(jsonMapper(), value)
    }

    fun writeTo(outputStream: OutputStream)

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

    /**
     * Returns the body parsed as JSON into an instance of the given class.
     *
     * This method writes the body ([writeTo]). If the body is not [repeatable], then call
     * [buffered] first and call this method on the returned body.
     *
     * @throws AnthropicInvalidDataException if the body cannot be parsed into the given class.
     */
    fun <T> json(clazz: Class<T>): T =
        try {
            val outputStream = ByteArrayOutputStream()
            writeTo(outputStream)
            jsonMapper().readValue(outputStream.toByteArray(), clazz)
        } catch (e: Exception) {
            throw AnthropicInvalidDataException("Error reading request body", e)
        }

    /**
     * Returns a body equivalent to this body, but which is fully written into memory, so it's
     * [repeatable].
     *
     * Returns this body unchanged if it's already [repeatable]. Otherwise, the returned body's
     * first [writeTo] call (or a call that implies it, like [json] or an unknown [contentLength])
     * fully writes this body into memory, so this body should no longer be used directly. Closing
     * the returned body closes this body.
     */
    fun buffered(): HttpRequestBody {
        if (repeatable()) {
            return this
        }

        return object : HttpRequestBody {
            private val bufferedBody: ByteArray by lazy {
                val outputStream = ByteArrayOutputStream()
                this@HttpRequestBody.writeTo(outputStream)
                outputStream.toByteArray()
            }

            override fun writeTo(outputStream: OutputStream) = outputStream.write(bufferedBody)

            override fun contentType(): String? = this@HttpRequestBody.contentType()

            override fun contentLength(): Long =
                this@HttpRequestBody.contentLength().takeIf { it != -1L }
                    ?: bufferedBody.size.toLong()

            override fun repeatable(): Boolean = true

            override fun close() = this@HttpRequestBody.close()
        }
    }

    /** Overridden from [AutoCloseable] to not have a checked exception in its signature. */
    override fun close()
}
