// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.MessageService
import com.anthropic.services.blocking.beta.MessageServiceImpl
import com.anthropic.services.blocking.beta.PromptCachingService
import com.anthropic.services.blocking.beta.PromptCachingServiceImpl

class BetaServiceImpl
constructor(
    private val clientOptions: ClientOptions,
) : BetaService {

    private val messages: MessageService by lazy { MessageServiceImpl(clientOptions) }

    private val promptCaching: PromptCachingService by lazy {
        PromptCachingServiceImpl(clientOptions)
    }

    override fun messages(): MessageService = messages

    override fun promptCaching(): PromptCachingService = promptCaching
}
