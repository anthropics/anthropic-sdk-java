package kotlinx.kmp.util.core.errors

open class ApiException(message: String? = null, cause: Throwable? = null) :
    RuntimeException(message, cause)
