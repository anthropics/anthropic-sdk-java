// File generated from our OpenAPI spec by Stainless.

package com.anthropic.errors

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers

abstract class AnthropicServiceException
protected constructor(message: String, cause: Throwable? = null) :
    AnthropicException(message, cause) {

    abstract fun statusCode(): Int

    abstract fun headers(): Headers

    abstract fun body(): JsonValue
}
