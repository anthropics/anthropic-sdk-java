// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.core.ClientOptions
import com.anthropic.core.getPackageVersion
import com.anthropic.models.*
import com.anthropic.services.blocking.*

class AnthropicClientImpl
constructor(
    private val clientOptions: ClientOptions,
) : AnthropicClient {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "${javaClass.simpleName}/Java ${getPackageVersion()}")
                .build()

    // Pass the original clientOptions so that this client sets its own User-Agent.
    private val async: AnthropicClientAsync by lazy { AnthropicClientAsyncImpl(clientOptions) }

    private val completions: CompletionService by lazy {
        CompletionServiceImpl(clientOptionsWithUserAgent)
    }

    private val messages: MessageService by lazy { MessageServiceImpl(clientOptionsWithUserAgent) }

    private val beta: BetaService by lazy { BetaServiceImpl(clientOptionsWithUserAgent) }

    override fun async(): AnthropicClientAsync = async

    override fun completions(): CompletionService = completions

    override fun messages(): MessageService = messages

    override fun beta(): BetaService = beta
}
