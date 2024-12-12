// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.services.async.*

interface AnthropicClientAsync {

    fun sync(): AnthropicClient

    fun completions(): CompletionServiceAsync

    fun messages(): MessageServiceAsync

    fun beta(): BetaServiceAsync
}
