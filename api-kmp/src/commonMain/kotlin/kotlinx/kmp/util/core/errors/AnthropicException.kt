package kotlinx.kmp.util.core.errors

open class AnthropicException(message: String? = null, cause: Throwable? = null) :
    RuntimeException(message, cause)
