// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.anthropic.services.async.beta.promptCaching

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.models.BetaPromptCachingMessageCreateParams
import com.anthropic.models.PromptCachingBetaMessage
import com.anthropic.models.RawPromptCachingBetaMessageStreamEvent
import java.util.concurrent.CompletableFuture

interface MessageServiceAsync {

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     */
    @JvmOverloads
    fun create(
        params: BetaPromptCachingMessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<PromptCachingBetaMessage>

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     */
    @JvmOverloads
    fun createStreaming(
        params: BetaPromptCachingMessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): AsyncStreamResponse<RawPromptCachingBetaMessageStreamEvent>
}
