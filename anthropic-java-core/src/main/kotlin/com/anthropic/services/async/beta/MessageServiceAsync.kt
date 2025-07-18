// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageTokensCount
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.MessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.services.async.beta.messages.BatchServiceAsync
import com.google.errorprone.annotations.MustBeClosed
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface MessageServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MessageServiceAsync

    fun batches(): BatchServiceAsync

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    fun create(params: MessageCreateParams): CompletableFuture<BetaMessage> =
        create(params, RequestOptions.none())

    /** @see [create] */
    fun create(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaMessage>

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    fun createStreaming(
        params: MessageCreateParams
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        createStreaming(params, RequestOptions.none())

    /** @see [createStreaming] */
    fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaRawMessageStreamEvent>

    /**
     * Count the number of tokens in a Message.
     *
     * The Token Count API can be used to count the number of tokens in a Message, including tools,
     * images, and documents, without creating it.
     *
     * Learn more about token counting in our
     * [user guide](/en/docs/build-with-claude/token-counting)
     */
    fun countTokens(params: MessageCountTokensParams): CompletableFuture<BetaMessageTokensCount> =
        countTokens(params, RequestOptions.none())

    /** @see [countTokens] */
    fun countTokens(
        params: MessageCountTokensParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaMessageTokensCount>

    /**
     * A view of [MessageServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MessageServiceAsync.WithRawResponse

        fun batches(): BatchServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/messages?beta=true`, but is otherwise the same
         * as [MessageServiceAsync.create].
         */
        fun create(params: MessageCreateParams): CompletableFuture<HttpResponseFor<BetaMessage>> =
            create(params, RequestOptions.none())

        /** @see [create] */
        fun create(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaMessage>>

        /**
         * Returns a raw HTTP response for `post /v1/messages?beta=true`, but is otherwise the same
         * as [MessageServiceAsync.createStreaming].
         */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>>> =
            createStreaming(params, RequestOptions.none())

        /** @see [createStreaming] */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>>>

        /**
         * Returns a raw HTTP response for `post /v1/messages/count_tokens?beta=true`, but is
         * otherwise the same as [MessageServiceAsync.countTokens].
         */
        fun countTokens(
            params: MessageCountTokensParams
        ): CompletableFuture<HttpResponseFor<BetaMessageTokensCount>> =
            countTokens(params, RequestOptions.none())

        /** @see [countTokens] */
        fun countTokens(
            params: MessageCountTokensParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaMessageTokensCount>>
    }
}
