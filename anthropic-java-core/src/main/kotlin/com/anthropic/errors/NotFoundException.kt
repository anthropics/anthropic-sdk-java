package com.anthropic.errors

import com.anthropic.core.http.Headers

class NotFoundException(headers: Headers, body: String, error: AnthropicError) :
    AnthropicServiceException(404, headers, body, error)
