// File generated from our OpenAPI spec by Stainless.

package kotlinx.kmp.util.core.errors

import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.http.Headers

abstract class AnthropicServiceException
protected constructor(message: String, cause: Throwable? = null) :
    AnthropicException(message, cause) {

    abstract fun statusCode(): Int

    abstract fun headers(): Headers

    abstract fun body(): JsonValue
}
