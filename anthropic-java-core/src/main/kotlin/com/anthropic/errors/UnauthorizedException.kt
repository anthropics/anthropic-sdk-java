package com.anthropic.errors

import com.anthropic.core.http.Headers

class UnauthorizedException(headers: Headers, body: String, error: AnthropicError) :
    AnthropicServiceException(401, headers, body, error)
