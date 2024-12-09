// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.services.blocking.beta.promptCaching.MessageService

interface PromptCachingService {

    fun messages(): MessageService
}
