// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.core.ClientOptions
import com.anthropic.core.getPackageVersion
import com.anthropic.services.async.*

class AnthropicClientAsyncImpl
constructor(
    private val clientOptions: ClientOptions,
) : AnthropicClientAsync {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "${javaClass.simpleName}/Java ${getPackageVersion()}")
                .build()

    // Pass the original clientOptions so that this client sets its own User-Agent.
    private val sync: AnthropicClient by lazy { AnthropicClientImpl(clientOptions) }

    private val completions: CompletionServiceAsync by lazy {
        CompletionServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val messages: MessageServiceAsync by lazy {
        MessageServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    private val beta: BetaServiceAsync by lazy { BetaServiceAsyncImpl(clientOptionsWithUserAgent) }

    override fun sync(): AnthropicClient = sync

    override fun completions(): CompletionServiceAsync = completions

    override fun messages(): MessageServiceAsync = messages

    override fun beta(): BetaServiceAsync = beta
}