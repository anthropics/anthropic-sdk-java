// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.core.ClientOptions
import com.anthropic.core.getPackageVersion
import com.anthropic.services.blocking.BetaService
import com.anthropic.services.blocking.BetaServiceImpl
import com.anthropic.services.blocking.CompletionService
import com.anthropic.services.blocking.CompletionServiceImpl
import com.anthropic.services.blocking.MessageService
import com.anthropic.services.blocking.MessageServiceImpl
import com.anthropic.services.blocking.ModelService
import com.anthropic.services.blocking.ModelServiceImpl

class AnthropicClientImpl(private val clientOptions: ClientOptions) : AnthropicClient {

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

    private val models: ModelService by lazy { ModelServiceImpl(clientOptionsWithUserAgent) }

    private val beta: BetaService by lazy { BetaServiceImpl(clientOptionsWithUserAgent) }

    override fun async(): AnthropicClientAsync = async

    override fun completions(): CompletionService = completions

    override fun messages(): MessageService = messages

    override fun models(): ModelService = models

    override fun beta(): BetaService = beta

    override fun close() = clientOptions.httpClient.close()
}
