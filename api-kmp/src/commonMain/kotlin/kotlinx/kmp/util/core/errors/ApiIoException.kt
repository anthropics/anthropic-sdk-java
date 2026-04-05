package kotlinx.kmp.util.core.errors

import kotlinx.kmp.util.core.http.Retryable

class ApiIoException(message: String? = null, cause: Throwable? = null) :
    ApiException(message, cause), Retryable
