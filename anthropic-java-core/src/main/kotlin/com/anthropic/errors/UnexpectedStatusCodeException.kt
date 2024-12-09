package com.anthropic.errors

import com.anthropic.core.http.Headers

class UnexpectedStatusCodeException(
    statusCode: Int,
    headers: Headers,
    body: String,
    error: AnthropicError,
) : AnthropicServiceException(statusCode, headers, body, error)
