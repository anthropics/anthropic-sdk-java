// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.models.*
import com.anthropic.services.blocking.*

interface AnthropicClient {

    fun async(): AnthropicClientAsync

    fun completions(): CompletionService

    fun messages(): MessageService

    fun beta(): BetaService
}
