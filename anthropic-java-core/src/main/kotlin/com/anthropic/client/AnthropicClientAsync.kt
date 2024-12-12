// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.services.async.BetaServiceAsync
import com.anthropic.services.async.CompletionServiceAsync
import com.anthropic.services.async.MessageServiceAsync

interface AnthropicClientAsync {

    fun sync(): AnthropicClient

    fun completions(): CompletionServiceAsync

    fun messages(): MessageServiceAsync

    fun beta(): BetaServiceAsync
}
