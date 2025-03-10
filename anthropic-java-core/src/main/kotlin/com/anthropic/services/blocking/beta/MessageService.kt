// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.anthropic.services.blocking.beta

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.BetaMessage
import com.anthropic.models.BetaMessageCountTokensParams
import com.anthropic.models.BetaMessageCreateParams
import com.anthropic.models.BetaMessageTokensCount
import com.anthropic.models.BetaRawMessageStreamEvent
import com.anthropic.services.blocking.beta.messages.BatchService
import com.google.errorprone.annotations.MustBeClosed

interface MessageService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    fun batches(): BatchService

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    @JvmOverloads
    fun create(
        params: BetaMessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessage

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    @JvmOverloads
    @MustBeClosed
    fun createStreaming(
        params: BetaMessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaRawMessageStreamEvent>

    /**
     * Count the number of tokens in a Message.
     *
     * The Token Count API can be used to count the number of tokens in a Message, including tools,
     * images, and documents, without creating it.
     *
     * Learn more about token counting in our
     * [user guide](/en/docs/build-with-claude/token-counting)
     */
    @JvmOverloads
    fun countTokens(
        params: BetaMessageCountTokensParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageTokensCount

    /** A view of [MessageService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        fun batches(): BatchService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/messages?beta=true`, but is otherwise the same
         * as [MessageService.create].
         */
        @JvmOverloads
        @MustBeClosed
        fun create(
            params: BetaMessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessage>

        /**
         * Returns a raw HTTP response for `post /v1/messages?beta=true`, but is otherwise the same
         * as [MessageService.createStreaming].
         */
        @JvmOverloads
        @MustBeClosed
        fun createStreaming(
            params: BetaMessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>>

        /**
         * Returns a raw HTTP response for `post /v1/messages/count_tokens?beta=true`, but is
         * otherwise the same as [MessageService.countTokens].
         */
        @JvmOverloads
        @MustBeClosed
        fun countTokens(
            params: BetaMessageCountTokensParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageTokensCount>
    }
}
