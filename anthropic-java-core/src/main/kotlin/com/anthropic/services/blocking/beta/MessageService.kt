// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.helpers.BetaToolRunner
import com.anthropic.models.beta.messages.*
import com.anthropic.services.blocking.beta.messages.BatchService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface MessageService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MessageService

    fun batches(): BatchService

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our
     * [user guide](https://docs.claude.com/en/docs/initial-setup)
     */
    fun create(params: MessageCreateParams): BetaMessage = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessage

    /**
     * Creates a model response for the given message parameters. The model's structured output in
     * JSON form will be deserialized automatically into an instance of the class `T`. See the SDK
     * documentation for more details.
     *
     * @see create
     */
    fun <T : Any> create(params: StructuredMessageCreateParams<T>): StructuredMessage<T> =
        create(params, RequestOptions.none())

    /**
     * Creates a model response for the given message parameters. The model's structured output in
     * JSON form will be deserialized automatically into an instance of the class `T`. See the SDK
     * documentation for more details.
     *
     * @see create
     */
    fun <T : Any> create(
        params: StructuredMessageCreateParams<T>,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StructuredMessage<T> =
        StructuredMessage<T>(params.outputType, create(params.rawParams, requestOptions))

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our
     * [user guide](https://docs.claude.com/en/docs/initial-setup)
     */
    @MustBeClosed
    fun createStreaming(params: MessageCreateParams): StreamResponse<BetaRawMessageStreamEvent> =
        createStreaming(params, RequestOptions.none())

    /** @see createStreaming */
    @MustBeClosed
    fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaRawMessageStreamEvent>

    /**
     * Creates a streaming model response for the given message. The message parameters can define a
     * JSON schema derived automatically from an arbitrary class to request a structured output in
     * JSON form. However, that structured output is split over multiple streamed events, so it will
     * not be deserialized automatically into an instance of that class. To deserialize the output,
     * first use a helper class to accumulate the stream of events into a single output value. See
     * the
     * [SDK documentation](https://github.com/anthropics/anthropic-sdk-java/#usage-with-streaming)
     * for full details.
     */
    @MustBeClosed
    fun createStreaming(
        params: StructuredMessageCreateParams<*>
    ): StreamResponse<BetaRawMessageStreamEvent> = createStreaming(params, RequestOptions.none())

    /** @see [createStreaming] */
    @MustBeClosed
    fun createStreaming(
        params: StructuredMessageCreateParams<*>,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaRawMessageStreamEvent> = createStreaming(params.rawParams, requestOptions)

    /**
     * Count the number of tokens in a Message.
     *
     * The Token Count API can be used to count the number of tokens in a Message, including tools,
     * images, and documents, without creating it.
     *
     * Learn more about token counting in our
     * [user guide](https://docs.claude.com/en/docs/build-with-claude/token-counting)
     */
    fun countTokens(params: MessageCountTokensParams): BetaMessageTokensCount =
        countTokens(params, RequestOptions.none())

    /** @see countTokens */
    fun countTokens(
        params: MessageCountTokensParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageTokensCount

    fun toolRunner(params: MessageCreateParams): BetaToolRunner =
        toolRunner(ToolRunnerCreateParams.builder().initialMessageParams(params).build())

    /** @see toolRunner */
    fun toolRunner(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaToolRunner =
        toolRunner(
            ToolRunnerCreateParams.builder().initialMessageParams(params).build(),
            requestOptions,
        )

    /** @see toolRunner */
    fun toolRunner(params: ToolRunnerCreateParams): BetaToolRunner =
        toolRunner(params, RequestOptions.none())

    /** @see toolRunner */
    fun toolRunner(
        params: ToolRunnerCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaToolRunner

    /** A view of [MessageService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): MessageService.WithRawResponse

        fun batches(): BatchService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/messages?beta=true`, but is otherwise the same
         * as [MessageService.create].
         */
        @MustBeClosed
        fun create(params: MessageCreateParams): HttpResponseFor<BetaMessage> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessage>

        /**
         * Returns a raw HTTP response for `post /v1/messages?beta=true`, but is otherwise the same
         * as [MessageService.createStreaming].
         */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams
        ): HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>> =
            createStreaming(params, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>>

        /**
         * Returns a raw HTTP response for `post /v1/messages/count_tokens?beta=true`, but is
         * otherwise the same as [MessageService.countTokens].
         */
        @MustBeClosed
        fun countTokens(params: MessageCountTokensParams): HttpResponseFor<BetaMessageTokensCount> =
            countTokens(params, RequestOptions.none())

        /** @see countTokens */
        @MustBeClosed
        fun countTokens(
            params: MessageCountTokensParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageTokensCount>
    }
}
