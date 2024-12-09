// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.promptCaching.MessageServiceAsync
import com.anthropic.services.async.beta.promptCaching.MessageServiceAsyncImpl

class PromptCachingServiceAsyncImpl
constructor(
    private val clientOptions: ClientOptions,
) : PromptCachingServiceAsync {

    private val messages: MessageServiceAsync by lazy { MessageServiceAsyncImpl(clientOptions) }

    override fun messages(): MessageServiceAsync = messages
}
