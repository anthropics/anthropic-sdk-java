package com.anthropic.errors

import com.anthropic.core.http.Headers

class RateLimitException(headers: Headers, body: String, error: AnthropicError) :
    AnthropicServiceException(429, headers, body, error)
