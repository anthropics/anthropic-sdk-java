package com.anthropic.errors

class AnthropicInvalidDataException(message: String? = null, cause: Throwable? = null) :
    AnthropicException(message, cause)
