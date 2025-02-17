package com.anthropic.errors

import com.anthropic.core.http.Headers

class BadRequestException(headers: Headers, body: String, error: AnthropicError) :
    AnthropicServiceException(400, headers, body, error)
