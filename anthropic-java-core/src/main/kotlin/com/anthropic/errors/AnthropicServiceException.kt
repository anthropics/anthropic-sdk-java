package com.anthropic.errors

import com.anthropic.core.http.Headers

abstract class AnthropicServiceException
@JvmOverloads
constructor(
    private val statusCode: Int,
    private val headers: Headers,
    private val body: String,
    private val error: AnthropicError,
    message: String = "$statusCode: $error",
    cause: Throwable? = null,
) : AnthropicException(message, cause) {

    fun statusCode(): Int = statusCode

    fun headers(): Headers = headers

    fun body(): String = body

    fun error(): AnthropicError = error
}
