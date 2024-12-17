// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.services.blocking.BetaService
import com.anthropic.services.blocking.CompletionService
import com.anthropic.services.blocking.MessageService
import com.anthropic.services.blocking.ModelService

interface AnthropicClient {

    fun async(): AnthropicClientAsync

    fun completions(): CompletionService

    fun messages(): MessageService

    fun models(): ModelService

    fun beta(): BetaService
}
