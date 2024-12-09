package com.anthropic.errors

class AnthropicInvalidDataException
@JvmOverloads
constructor(message: String? = null, cause: Throwable? = null) : AnthropicException(message, cause)
