// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.services.blocking.beta.MessageService
import com.anthropic.services.blocking.beta.PromptCachingService

interface BetaService {

    fun messages(): MessageService

    fun promptCaching(): PromptCachingService
}
