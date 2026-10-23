package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.errors.AnthropicException
import com.anthropic.helpers.BetaMessageThread
import com.anthropic.helpers.BetaToolRunner
import com.anthropic.helpers.StructuredBetaMessageThread
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
     * [user guide](https://platform.claude.com/docs/en/get-started)
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
     * [user guide](https://platform.claude.com/docs/en/get-started)
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
     * [user guide](https://platform.claude.com/docs/en/build-with-claude/token-counting)
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

    /**
     * Creates a message thread: a conversation that the API keeps, so that each turn sends only its
     * new messages instead of the whole conversation.
     *
     * Nothing is sent by this call. The messages of [params], if any, are sent with the first turn.
     * See [BetaMessageThread] for how turns are sent.
     *
     * @throws AnthropicException if the service that this client sends to takes no message threads,
     *   as on Amazon Bedrock and Vertex AI.
     */
    fun thread(params: ThreadCreateParams): BetaMessageThread =
        thread(params, RequestOptions.none())

    /** @see thread */
    fun thread(params: ThreadCreateParams, requestOptions: RequestOptions): BetaMessageThread =
        BetaMessageThread.create(this, params, requestOptions)

    /**
     * Creates a message thread like [thread], whose replies are in the JSON schema derived from the
     * output type of [params] and are returned with their text converted to instances of that
     * class. See [StructuredBetaMessageThread].
     */
    fun <T : Any> thread(params: StructuredThreadCreateParams<T>): StructuredBetaMessageThread<T> =
        thread(params, RequestOptions.none())

    /** @see thread */
    fun <T : Any> thread(
        params: StructuredThreadCreateParams<T>,
        requestOptions: RequestOptions,
    ): StructuredBetaMessageThread<T> =
        StructuredBetaMessageThread(params.outputType, thread(params.rawParams, requestOptions))

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
         * Returns a raw HTTP response for `post /v1/messages`, but is otherwise the same as
         * [MessageService.create].
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
         * Returns a raw HTTP response for `post /v1/messages`, but is otherwise the same as
         * [MessageService.createStreaming].
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
         * Returns a raw HTTP response for `post /v1/messages/count_tokens`, but is otherwise the
         * same as [MessageService.countTokens].
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
