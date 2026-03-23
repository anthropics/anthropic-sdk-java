// File generated from our OpenAPI spec by Stainless.

package com.anthropic.errors

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.ErrorType
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

abstract class AnthropicServiceException
protected constructor(message: String, cause: Throwable? = null) :
    AnthropicException(message, cause) {

    abstract fun statusCode(): Int

    abstract fun headers(): Headers

    abstract fun body(): JsonValue

    /**
     * The error type from the API response body (e.g., [ErrorType.RATE_LIMIT_ERROR],
     * [ErrorType.OVERLOADED_ERROR]).
     *
     * Extracted from `body.error.type`. Returns [Optional.empty] if the body doesn't contain a
     * structured error with a type field.
     */
    fun errorType(): Optional<ErrorType> {
        val error = body().asObject().getOrNull()?.get("error") ?: return Optional.empty()
        val type = error.asObject().getOrNull()?.get("type") ?: return Optional.empty()
        return type.asString().map { ErrorType.of(it) }
    }
}
