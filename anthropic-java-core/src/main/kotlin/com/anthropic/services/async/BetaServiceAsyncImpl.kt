// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.MessageServiceAsync
import com.anthropic.services.async.beta.MessageServiceAsyncImpl
import com.anthropic.services.async.beta.ModelServiceAsync
import com.anthropic.services.async.beta.ModelServiceAsyncImpl

class BetaServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    BetaServiceAsync {

    private val models: ModelServiceAsync by lazy { ModelServiceAsyncImpl(clientOptions) }

    private val messages: MessageServiceAsync by lazy { MessageServiceAsyncImpl(clientOptions) }

    override fun models(): ModelServiceAsync = models

    override fun messages(): MessageServiceAsync = messages
}
