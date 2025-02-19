package com.anthropic.errors

import com.anthropic.core.http.Headers

class UnprocessableEntityException(headers: Headers, body: String, error: AnthropicError) :
    AnthropicServiceException(422, headers, body, error)
