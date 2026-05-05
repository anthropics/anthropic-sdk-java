package com.anthropic.errors

class AnthropicWebhookException
@JvmOverloads
constructor(message: String? = null, cause: Throwable? = null) : AnthropicException(message, cause)
