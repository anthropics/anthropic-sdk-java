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

    /**
     * Closes this client, relinquishing any underlying resources.
     *
     * This is purposefully not inherited from [AutoCloseable] because the client is long-lived and
     * usually should not be synchronously closed via try-with-resources.
     *
     * It's also usually not necessary to call this method at all. the default HTTP client
     * automatically releases threads and connections if they remain idle, but if you are writing an
     * application that needs to aggressively release unused resources, then you may call this
     * method.
     */
    fun close()
}
