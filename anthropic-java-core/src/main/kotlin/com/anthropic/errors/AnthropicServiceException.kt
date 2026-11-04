package com.anthropic.errors

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.core.valueAt
import com.anthropic.models.ErrorDetails
import com.anthropic.models.ErrorType
import java.util.Optional

abstract class AnthropicServiceException
protected constructor(message: String, cause: Throwable? = null) :
    AnthropicException(message, cause) {

    abstract fun statusCode(): Int

    abstract fun headers(): Headers

    /**
     * Returns the value of the `request-id` header, or an empty [Optional] if there's no such
     * header in the response.
     */
    fun requestId(): Optional<String> =
        Optional.ofNullable(headers().values("request-id").firstOrNull())

    /**
     * Returns the value of the `anthropic-workspace-id` header, or an empty [Optional] if there's
     * no such header in the response.
     */
    fun workspaceId(): Optional<String> =
        Optional.ofNullable(headers().values("anthropic-workspace-id").firstOrNull())

    abstract fun body(): JsonValue

    /** `error.message` of [body], or empty if it can't be read as this type. */
    fun apiMessage(): Optional<String> = body().valueAt("error", "message")

    /** `error.type` of [body], or empty if it can't be read as this type. */
    fun type(): Optional<ErrorType> = body().valueAt("error", "type")

    /** `error.details.error_code` of [body], or empty if it can't be read as this type. */
    fun errorCode(): Optional<String> = body().valueAt("error", "details", "error_code")

    /** `error.details` of [body], or empty if it can't be read as this type. */
    fun errorDetails(): Optional<ErrorDetails> = body().valueAt("error", "details")

    /**
     * The error type from the API response body (e.g., [ErrorType.RATE_LIMIT_ERROR],
     * [ErrorType.OVERLOADED_ERROR]).
     *
     * Extracted from `body.error.type`. Returns [Optional.empty] if the body doesn't contain a
     * structured error with a type field.
     */
    @Deprecated("Use type() instead.", ReplaceWith("type()"))
    fun errorType(): Optional<ErrorType> = type()
}
