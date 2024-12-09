package com.anthropic.errors

class AnthropicIoException
@JvmOverloads
constructor(message: String? = null, cause: Throwable? = null) : AnthropicException(message, cause)
