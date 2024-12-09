// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.anthropic.services.blocking.beta.promptCaching

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.BetaPromptCachingMessageCreateParams
import com.anthropic.models.PromptCachingBetaMessage
import com.anthropic.models.RawPromptCachingBetaMessageStreamEvent
import com.google.errorprone.annotations.MustBeClosed

interface MessageService {

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
    ): PromptCachingBetaMessage

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     */
    @JvmOverloads
    @MustBeClosed
    fun createStreaming(
        params: BetaPromptCachingMessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): StreamResponse<RawPromptCachingBetaMessageStreamEvent>
}
