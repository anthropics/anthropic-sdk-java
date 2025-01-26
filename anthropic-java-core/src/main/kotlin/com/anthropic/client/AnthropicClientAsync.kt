// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client

import com.anthropic.services.async.BetaServiceAsync
import com.anthropic.services.async.CompletionServiceAsync
import com.anthropic.services.async.MessageServiceAsync
import com.anthropic.services.async.ModelServiceAsync

interface AnthropicClientAsync {

    fun sync(): AnthropicClient

    fun completions(): CompletionServiceAsync

    fun messages(): MessageServiceAsync

    fun models(): ModelServiceAsync

    fun beta(): BetaServiceAsync

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
