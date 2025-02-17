package com.anthropic.errors

import com.anthropic.core.http.Headers

class PermissionDeniedException(headers: Headers, body: String, error: AnthropicError) :
    AnthropicServiceException(403, headers, body, error)
