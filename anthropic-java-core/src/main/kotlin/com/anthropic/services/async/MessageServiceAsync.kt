// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.MessageTokensCount
import com.anthropic.models.messages.RawMessageStreamEvent
import com.anthropic.services.async.messages.BatchServiceAsync
import com.google.errorprone.annotations.MustBeClosed
import java.util.concurrent.CompletableFuture

interface MessageServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    fun batches(): BatchServiceAsync

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    fun create(params: MessageCreateParams): CompletableFuture<Message> =
        create(params, RequestOptions.none())

    /** @see [create] */
    fun create(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Message>

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    fun createStreaming(params: MessageCreateParams): AsyncStreamResponse<RawMessageStreamEvent> =
        createStreaming(params, RequestOptions.none())

    /** @see [createStreaming] */
    fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<RawMessageStreamEvent>

    /**
     * Count the number of tokens in a Message.
     *
     * The Token Count API can be used to count the number of tokens in a Message, including tools,
     * images, and documents, without creating it.
     *
     * Learn more about token counting in our
     * [user guide](/en/docs/build-with-claude/token-counting)
     */
    fun countTokens(params: MessageCountTokensParams): CompletableFuture<MessageTokensCount> =
        countTokens(params, RequestOptions.none())

    /** @see [countTokens] */
    fun countTokens(
        params: MessageCountTokensParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MessageTokensCount>

    /**
     * A view of [MessageServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        fun batches(): BatchServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/messages`, but is otherwise the same as
         * [MessageServiceAsync.create].
         */
        @MustBeClosed
        fun create(params: MessageCreateParams): CompletableFuture<HttpResponseFor<Message>> =
            create(params, RequestOptions.none())

        /** @see [create] */
        @MustBeClosed
        fun create(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Message>>

        /**
         * Returns a raw HTTP response for `post /v1/messages`, but is otherwise the same as
         * [MessageServiceAsync.createStreaming].
         */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<RawMessageStreamEvent>>> =
            createStreaming(params, RequestOptions.none())

        /** @see [createStreaming] */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<RawMessageStreamEvent>>>

        /**
         * Returns a raw HTTP response for `post /v1/messages/count_tokens`, but is otherwise the
         * same as [MessageServiceAsync.countTokens].
         */
        @MustBeClosed
        fun countTokens(
            params: MessageCountTokensParams
        ): CompletableFuture<HttpResponseFor<MessageTokensCount>> =
            countTokens(params, RequestOptions.none())

        /** @see [countTokens] */
        @MustBeClosed
        fun countTokens(
            params: MessageCountTokensParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageTokensCount>>
    }
}
