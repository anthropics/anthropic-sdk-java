// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.promptCaching.MessageService
import com.anthropic.services.blocking.beta.promptCaching.MessageServiceImpl

class PromptCachingServiceImpl
constructor(
    private val clientOptions: ClientOptions,
) : PromptCachingService {

    private val messages: MessageService by lazy { MessageServiceImpl(clientOptions) }

    override fun messages(): MessageService = messages
}
