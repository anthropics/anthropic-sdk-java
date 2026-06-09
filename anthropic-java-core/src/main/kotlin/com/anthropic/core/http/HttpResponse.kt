// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core.http

import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.Optional

interface HttpResponse : AutoCloseable {

    fun statusCode(): Int

    fun headers(): Headers

    /**
     * Returns the value of the `request-id` header, or an empty [Optional] if there's no such
     * header in the response.
     */
    fun requestId(): Optional<String> =
        Optional.ofNullable(headers().values("request-id").firstOrNull())

    fun body(): InputStream

    /**
     * Returns the response [body] parsed as JSON into an instance of the given class.
     *
     * This method consumes the [body] stream. To also read the body in other ways, call [buffered]
     * first and call this method on the returned response.
     *
     * @throws AnthropicInvalidDataException if the body cannot be parsed into the given class.
     */
    fun <T> json(clazz: Class<T>): T =
        try {
            jsonMapper().readValue(body(), clazz)
        } catch (e: Exception) {
            throw AnthropicInvalidDataException("Error reading response", e)
        }

    /**
     * Returns a response equivalent to this response, but whose [body] returns a new [InputStream]
     * on each call, so it can be read repeatedly.
     *
     * The first call to the returned response's [body] fully reads this response's [body] into
     * memory, so this response should no longer be used directly. Closing the returned response
     * closes this response. Calling [buffered] on the returned response returns it unchanged.
     */
    fun buffered(): HttpResponse =
        object : HttpResponse {
            private val bufferedBody: ByteArray by lazy {
                this@HttpResponse.body().use { it.readBytes() }
            }

            override fun statusCode(): Int = this@HttpResponse.statusCode()

            override fun headers(): Headers = this@HttpResponse.headers()

            override fun body(): InputStream = ByteArrayInputStream(bufferedBody)

            override fun buffered(): HttpResponse = this

            override fun close() = this@HttpResponse.close()
        }

    /** Overridden from [AutoCloseable] to not have a checked exception in its signature. */
    override fun close()

    interface Handler<T> {

        fun handle(response: HttpResponse): T
    }
}
