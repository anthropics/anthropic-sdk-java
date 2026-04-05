package kotlinx.kmp.util.core.errors

import kotlinx.kmp.util.core.http.Retryable

class AnthropicIoException(message: String? = null, cause: Throwable? = null) :
    AnthropicException(message, cause), Retryable
