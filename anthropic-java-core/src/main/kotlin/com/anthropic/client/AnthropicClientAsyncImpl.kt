// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.core.ClientOptions
import com.anthropic.core.getPackageVersion
import com.anthropic.services.async.BetaServiceAsync
import com.anthropic.services.async.BetaServiceAsyncImpl
import com.anthropic.services.async.CompletionServiceAsync
import com.anthropic.services.async.CompletionServiceAsyncImpl
import com.anthropic.services.async.MessageServiceAsync
import com.anthropic.services.async.MessageServiceAsyncImpl
import com.anthropic.services.async.ModelServiceAsync
import com.anthropic.services.async.ModelServiceAsyncImpl
import java.util.function.Consumer

class AnthropicClientAsyncImpl(private val clientOptions: ClientOptions) : AnthropicClientAsync {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "${javaClass.simpleName}/Java ${getPackageVersion()}")
                .build()

    // Pass the original clientOptions so that this client sets its own User-Agent.
    private val sync: AnthropicClient by lazy { AnthropicClientImpl(clientOptions) }

    private val withRawResponse: AnthropicClientAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val completions: CompletionServiceAsync by lazy {
        CompletionServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val messages: MessageServiceAsync by lazy {
        MessageServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val models: ModelServiceAsync by lazy {
        ModelServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val beta: BetaServiceAsync by lazy { BetaServiceAsyncImpl(clientOptionsWithUserAgent) }

    override fun sync(): AnthropicClient = sync

    override fun withRawResponse(): AnthropicClientAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): AnthropicClientAsync =
        AnthropicClientAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun completions(): CompletionServiceAsync = completions

    override fun messages(): MessageServiceAsync = messages

    override fun models(): ModelServiceAsync = models

    override fun beta(): BetaServiceAsync = beta

    override fun close() = clientOptions.close()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AnthropicClientAsync.WithRawResponse {

        private val completions: CompletionServiceAsync.WithRawResponse by lazy {
            CompletionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val messages: MessageServiceAsync.WithRawResponse by lazy {
            MessageServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val models: ModelServiceAsync.WithRawResponse by lazy {
            ModelServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val beta: BetaServiceAsync.WithRawResponse by lazy {
            BetaServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): AnthropicClientAsync.WithRawResponse =
            AnthropicClientAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun completions(): CompletionServiceAsync.WithRawResponse = completions

        override fun messages(): MessageServiceAsync.WithRawResponse = messages

        override fun models(): ModelServiceAsync.WithRawResponse = models

        override fun beta(): BetaServiceAsync.WithRawResponse = beta
    }
}
