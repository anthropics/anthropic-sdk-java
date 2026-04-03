package com.anthropic.errors

class AnthropicIoException(message: String? = null, cause: Throwable? = null) :
    AnthropicException(message, cause)
