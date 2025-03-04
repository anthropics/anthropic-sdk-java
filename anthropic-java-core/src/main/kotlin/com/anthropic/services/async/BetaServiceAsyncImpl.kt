// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.MessageServiceAsync
import com.anthropic.services.async.beta.MessageServiceAsyncImpl
import com.anthropic.services.async.beta.ModelServiceAsync
import com.anthropic.services.async.beta.ModelServiceAsyncImpl

class BetaServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    BetaServiceAsync {

    private val withRawResponse: BetaServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val models: ModelServiceAsync by lazy { ModelServiceAsyncImpl(clientOptions) }

    private val messages: MessageServiceAsync by lazy { MessageServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): BetaServiceAsync.WithRawResponse = withRawResponse

    override fun models(): ModelServiceAsync = models

    override fun messages(): MessageServiceAsync = messages

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BetaServiceAsync.WithRawResponse {

        private val models: ModelServiceAsync.WithRawResponse by lazy {
            ModelServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val messages: MessageServiceAsync.WithRawResponse by lazy {
            MessageServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun models(): ModelServiceAsync.WithRawResponse = models

        override fun messages(): MessageServiceAsync.WithRawResponse = messages
    }
}
