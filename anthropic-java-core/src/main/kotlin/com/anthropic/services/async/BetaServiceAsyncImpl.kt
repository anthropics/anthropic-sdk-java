// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.MessageServiceAsync
import com.anthropic.services.async.beta.MessageServiceAsyncImpl
import com.anthropic.services.async.beta.PromptCachingServiceAsync
import com.anthropic.services.async.beta.PromptCachingServiceAsyncImpl

class BetaServiceAsyncImpl
constructor(
    private val clientOptions: ClientOptions,
) : BetaServiceAsync {

    private val messages: MessageServiceAsync by lazy { MessageServiceAsyncImpl(clientOptions) }

    private val promptCaching: PromptCachingServiceAsync by lazy {
        PromptCachingServiceAsyncImpl(clientOptions)
    }

    override fun messages(): MessageServiceAsync = messages

    override fun promptCaching(): PromptCachingServiceAsync = promptCaching
}
