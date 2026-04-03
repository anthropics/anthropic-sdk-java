// File generated from our OpenAPI spec by Stainless.

package com.anthropic.errors

import com.anthropic.models.ErrorType

/**
 * The error type from the API response body (e.g., [ErrorType.RATE_LIMIT_ERROR],
 * [ErrorType.OVERLOADED_ERROR]).
 *
 * Extracted from `body.error.type`. Returns `null` if the body doesn't contain a structured error
 * with a type field.
 */
fun AnthropicServiceException.errorType(): ErrorType? {
    val error = body().asObject()?.get("error") ?: return null
    val type = error.asObject()?.get("type") ?: return null
    return type.asString()?.let { ErrorType.of(it) }
}
