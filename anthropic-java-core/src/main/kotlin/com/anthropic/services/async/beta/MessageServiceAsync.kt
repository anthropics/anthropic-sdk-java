package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.errors.AnthropicException
import com.anthropic.helpers.BetaMessageThreadAsync
import com.anthropic.helpers.StructuredBetaMessageThreadAsync
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageTokensCount
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.MessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.beta.messages.StructuredMessage
import com.anthropic.models.beta.messages.StructuredMessageCreateParams
import com.anthropic.models.beta.messages.StructuredThreadCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams
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
     * Learn more about the Messages API in our
     * [user guide](https://platform.claude.com/docs/en/get-started)
     *
     * @throws com.anthropic.models.messages.AvailableToolsLimitExceededException The tools
     *   available after a message exceed a limit on their number or their size.
     * @throws com.anthropic.models.ClaudeCodeKeyCreatorNotMemberException The API key belongs to
     *   the Claude Code workspace, and the user who created it is no longer a member of the
     *   organization or of that workspace.
     * @throws com.anthropic.models.ClaudeCodeVersionTooOldException The request comes from a Claude
     *   Code version older than the minimum that the requested model or the organization requires.
     *   Update Claude Code, then retry.
     * @throws com.anthropic.models.CmekKeyDisabledException The workspace's customer-managed
     *   encryption key is disabled or revoked. Re-enable the key, then retry.
     * @throws com.anthropic.models.CmekKeyNetworkBlockedException The key management service's
     *   network access controls blocked the request to the workspace's customer-managed encryption
     *   key. The key itself may be healthy.
     * @throws com.anthropic.models.messages.CompactionBlockAmbiguousException `messages` holds more
     *   than one `compaction` block with content. Send only the block the conversation returned
     *   last.
     * @throws com.anthropic.models.messages.CompactionBlockMisplacedException The `compaction`
     *   block is not the first block of the conversation, or is in a `system` message.
     * @throws com.anthropic.models.messages.CompactionContentMismatchException The `compaction`
     *   block's `content` differs from the text its `signature` recorded.
     * @throws com.anthropic.models.messages.CompactionIncompleteTurnException The compaction
     *   request ends on an `assistant` turn that is not finished.
     * @throws com.anthropic.models.messages.CompactionNothingToSummarizeException `messages` holds
     *   no `user` or `assistant` content to summarize or adopt.
     * @throws com.anthropic.models.messages.CompactionSignatureInvalidException The `compaction`
     *   block's `signature` does not verify.
     * @throws com.anthropic.models.messages.CompactionTooManyToolReferencesException The
     *   conversation references more distinct tools than a `compaction` block can record.
     * @throws com.anthropic.models.messages.CompactionToolChangesMismatchException The `compaction`
     *   block's `tool_changes` differs from what was returned with the block.
     * @throws com.anthropic.models.OrganizationOnHoldException Anthropic has disabled the
     *   organization for trust and safety reasons. An organization disabled for any other reason,
     *   such as billing, does not get this code. `appeal_url`, when present, is where the
     *   organization can appeal.
     * @throws com.anthropic.models.RequestBodyEncodingInvalidException The request body is not
     *   valid for its `Content-Encoding`.
     * @throws com.anthropic.models.RequestBodyEncodingUnsupportedException The request's
     *   `Content-Encoding` is not supported.
     * @throws com.anthropic.models.messages.ThreadAlreadyContinuedException The response that
     *   `previous_message_id` names has already been continued, and each response can be continued
     *   only once. Continue from the Thread's most recent response `id`.
     * @throws com.anthropic.models.messages.ThreadContinueInFlightException Another request is
     *   still continuing from the same `previous_message_id`. Wait for it to complete and continue
     *   from its response `id`, or replay the full conversation with `thread: {"type": "create"}`.
     * @throws com.anthropic.models.messages.ThreadFingerprintMismatchException A field that must
     *   stay the same across a Thread's turns differs from the value stored with the Thread; the
     *   message names each such field. Send the stored values, or replay the full conversation with
     *   `thread: {"type": "create"}` to change them.
     * @throws com.anthropic.models.messages.ThreadUnsupportedRequestException The request combines
     *   `thread` with a request field or an organization setting that Threads do not support;
     *   `unsupported`, when present, names it. Send the full conversation without `thread`, or
     *   change that field.
     * @throws com.anthropic.models.messages.ToolNameConflictException A tool made available in
     *   `messages` takes a name that an available tool of another kind, or of another MCP server,
     *   already holds.
     * @throws com.anthropic.models.messages.ToolReferenceUnresolvedException A tool change or MCP
     *   tool listing references a tool or MCP server that is not defined at that position in the
     *   conversation.
     * @throws com.anthropic.models.messages.ThreadNotFoundException No usable Thread state was
     *   found for `previous_message_id`. Replay the full conversation with `thread: {"type":
     *   "create"}` to start a new Thread.
     * @throws com.anthropic.models.skills.SkillReadBlockedUndecryptableException A custom skill the
     *   request names is stored under an encryption key state the workspace cannot currently
     *   decrypt.
     * @throws com.anthropic.models.RequestBodyTooLargeAfterDecodingException The compressed request
     *   body exceeds the request size limit once decoded.
     * @throws com.anthropic.models.EnforcedSpendLimitReachedException The organization has crossed
     *   the monthly API usage threshold of its API tier.
     * @throws com.anthropic.models.CmekKeyUnreachableException The workspace's customer-managed
     *   encryption key could not be reached. Retry the request; if this continues, check that the
     *   key is available in your key management service, or contact Anthropic support.
     * @throws com.anthropic.models.messages.CompactionUnavailableException The compaction could not
     *   be completed for a transient server reason. Retry the request.
     */
    fun create(params: MessageCreateParams): CompletableFuture<BetaMessage> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaMessage>

    /**
     * Creates a model response for the given message. The model's structured output in JSON form
     * will be deserialized automatically into an instance of the class `T`. See the SDK
     * documentation for more details.
     *
     * @see create
     */
    fun <T : Any> create(
        params: StructuredMessageCreateParams<T>
    ): CompletableFuture<StructuredMessage<T>> = create(params, RequestOptions.none())

    /**
     * Creates a model response for the given message. The model's structured output in JSON form
     * will be deserialized automatically into an instance of the class `T`. See the SDK
     * documentation for more details.
     *
     * @see create
     */
    fun <T : Any> create(
        params: StructuredMessageCreateParams<T>,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<StructuredMessage<T>> =
        create(params.rawParams, requestOptions).thenApply {
            StructuredMessage(params.outputType, it)
        }

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our
     * [user guide](https://platform.claude.com/docs/en/get-started)
     *
     * @throws com.anthropic.models.messages.AvailableToolsLimitExceededException The tools
     *   available after a message exceed a limit on their number or their size.
     * @throws com.anthropic.models.ClaudeCodeKeyCreatorNotMemberException The API key belongs to
     *   the Claude Code workspace, and the user who created it is no longer a member of the
     *   organization or of that workspace.
     * @throws com.anthropic.models.ClaudeCodeVersionTooOldException The request comes from a Claude
     *   Code version older than the minimum that the requested model or the organization requires.
     *   Update Claude Code, then retry.
     * @throws com.anthropic.models.CmekKeyDisabledException The workspace's customer-managed
     *   encryption key is disabled or revoked. Re-enable the key, then retry.
     * @throws com.anthropic.models.CmekKeyNetworkBlockedException The key management service's
     *   network access controls blocked the request to the workspace's customer-managed encryption
     *   key. The key itself may be healthy.
     * @throws com.anthropic.models.messages.CompactionBlockAmbiguousException `messages` holds more
     *   than one `compaction` block with content. Send only the block the conversation returned
     *   last.
     * @throws com.anthropic.models.messages.CompactionBlockMisplacedException The `compaction`
     *   block is not the first block of the conversation, or is in a `system` message.
     * @throws com.anthropic.models.messages.CompactionContentMismatchException The `compaction`
     *   block's `content` differs from the text its `signature` recorded.
     * @throws com.anthropic.models.messages.CompactionIncompleteTurnException The compaction
     *   request ends on an `assistant` turn that is not finished.
     * @throws com.anthropic.models.messages.CompactionNothingToSummarizeException `messages` holds
     *   no `user` or `assistant` content to summarize or adopt.
     * @throws com.anthropic.models.messages.CompactionSignatureInvalidException The `compaction`
     *   block's `signature` does not verify.
     * @throws com.anthropic.models.messages.CompactionTooManyToolReferencesException The
     *   conversation references more distinct tools than a `compaction` block can record.
     * @throws com.anthropic.models.messages.CompactionToolChangesMismatchException The `compaction`
     *   block's `tool_changes` differs from what was returned with the block.
     * @throws com.anthropic.models.OrganizationOnHoldException Anthropic has disabled the
     *   organization for trust and safety reasons. An organization disabled for any other reason,
     *   such as billing, does not get this code. `appeal_url`, when present, is where the
     *   organization can appeal.
     * @throws com.anthropic.models.RequestBodyEncodingInvalidException The request body is not
     *   valid for its `Content-Encoding`.
     * @throws com.anthropic.models.RequestBodyEncodingUnsupportedException The request's
     *   `Content-Encoding` is not supported.
     * @throws com.anthropic.models.messages.ThreadAlreadyContinuedException The response that
     *   `previous_message_id` names has already been continued, and each response can be continued
     *   only once. Continue from the Thread's most recent response `id`.
     * @throws com.anthropic.models.messages.ThreadContinueInFlightException Another request is
     *   still continuing from the same `previous_message_id`. Wait for it to complete and continue
     *   from its response `id`, or replay the full conversation with `thread: {"type": "create"}`.
     * @throws com.anthropic.models.messages.ThreadFingerprintMismatchException A field that must
     *   stay the same across a Thread's turns differs from the value stored with the Thread; the
     *   message names each such field. Send the stored values, or replay the full conversation with
     *   `thread: {"type": "create"}` to change them.
     * @throws com.anthropic.models.messages.ThreadUnsupportedRequestException The request combines
     *   `thread` with a request field or an organization setting that Threads do not support;
     *   `unsupported`, when present, names it. Send the full conversation without `thread`, or
     *   change that field.
     * @throws com.anthropic.models.messages.ToolNameConflictException A tool made available in
     *   `messages` takes a name that an available tool of another kind, or of another MCP server,
     *   already holds.
     * @throws com.anthropic.models.messages.ToolReferenceUnresolvedException A tool change or MCP
     *   tool listing references a tool or MCP server that is not defined at that position in the
     *   conversation.
     * @throws com.anthropic.models.messages.ThreadNotFoundException No usable Thread state was
     *   found for `previous_message_id`. Replay the full conversation with `thread: {"type":
     *   "create"}` to start a new Thread.
     * @throws com.anthropic.models.skills.SkillReadBlockedUndecryptableException A custom skill the
     *   request names is stored under an encryption key state the workspace cannot currently
     *   decrypt.
     * @throws com.anthropic.models.RequestBodyTooLargeAfterDecodingException The compressed request
     *   body exceeds the request size limit once decoded.
     * @throws com.anthropic.models.EnforcedSpendLimitReachedException The organization has crossed
     *   the monthly API usage threshold of its API tier.
     * @throws com.anthropic.models.CmekKeyUnreachableException The workspace's customer-managed
     *   encryption key could not be reached. Retry the request; if this continues, check that the
     *   key is available in your key management service, or contact Anthropic support.
     * @throws com.anthropic.models.messages.CompactionUnavailableException The compaction could not
     *   be completed for a transient server reason. Retry the request.
     */
    fun createStreaming(
        params: MessageCreateParams
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        createStreaming(params, RequestOptions.none())

    /** @see createStreaming */
    fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaRawMessageStreamEvent>

    /**
     * Creates a streaming model response for the given message. The input parameters can define a
     * JSON schema derived automatically from an arbitrary class to request a structured output in
     * JSON form. However, that structured output is split over multiple streamed events, so it will
     * not be deserialized automatically into an instance of that class. To deserialize the output,
     * first use a helper class to accumulate the stream of events into a single output value. See
     * the
     * [SDK documentation](https://platform.claude.com/docs/en/cli-sdks-libraries/sdks/java#structured-outputs)
     * for full details.
     */
    fun createStreaming(
        params: StructuredMessageCreateParams<*>
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        createStreaming(params, RequestOptions.none())

    /** @see [createStreaming] */
    fun createStreaming(
        params: StructuredMessageCreateParams<*>,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        createStreaming(params.rawParams, requestOptions)

    /**
     * Creates a message thread: a conversation that the API keeps, so that each turn sends only its
     * new messages instead of the whole conversation.
     *
     * Nothing is sent by this call. The messages of [params], if any, are sent with the first turn.
     * See [BetaMessageThreadAsync] for how turns are sent.
     *
     * @throws AnthropicException if the service that this client sends to takes no message threads,
     *   as on Amazon Bedrock and Vertex AI.
     */
    fun thread(params: ThreadCreateParams): BetaMessageThreadAsync =
        thread(params, RequestOptions.none())

    /** @see thread */
    fun thread(params: ThreadCreateParams, requestOptions: RequestOptions): BetaMessageThreadAsync =
        BetaMessageThreadAsync.create(this, params, requestOptions)

    /**
     * Creates a message thread like [thread], whose replies are in the JSON schema derived from the
     * output type of [params] and are returned with their text converted to instances of that
     * class. See [StructuredBetaMessageThreadAsync].
     */
    fun <T : Any> thread(
        params: StructuredThreadCreateParams<T>
    ): StructuredBetaMessageThreadAsync<T> = thread(params, RequestOptions.none())

    /** @see thread */
    fun <T : Any> thread(
        params: StructuredThreadCreateParams<T>,
        requestOptions: RequestOptions,
    ): StructuredBetaMessageThreadAsync<T> =
        StructuredBetaMessageThreadAsync(
            params.outputType,
            thread(params.rawParams, requestOptions),
        )

    /**
     * Count the number of tokens in a Message.
     *
     * The Token Count API can be used to count the number of tokens in a Message, including tools,
     * images, and documents, without creating it.
     *
     * Learn more about token counting in our
     * [user guide](https://platform.claude.com/docs/en/build-with-claude/token-counting)
     *
     * @throws com.anthropic.models.messages.AvailableToolsLimitExceededException The tools
     *   available after a message exceed a limit on their number or their size.
     * @throws com.anthropic.models.ClaudeCodeKeyCreatorNotMemberException The API key belongs to
     *   the Claude Code workspace, and the user who created it is no longer a member of the
     *   organization or of that workspace.
     * @throws com.anthropic.models.messages.CompactionBlockAmbiguousException `messages` holds more
     *   than one `compaction` block with content. Send only the block the conversation returned
     *   last.
     * @throws com.anthropic.models.messages.CompactionBlockMisplacedException The `compaction`
     *   block is not the first block of the conversation, or is in a `system` message.
     * @throws com.anthropic.models.messages.CompactionContentMismatchException The `compaction`
     *   block's `content` differs from the text its `signature` recorded.
     * @throws com.anthropic.models.messages.CompactionNothingToSummarizeException `messages` holds
     *   no `user` or `assistant` content to summarize or adopt.
     * @throws com.anthropic.models.messages.CompactionSignatureInvalidException The `compaction`
     *   block's `signature` does not verify.
     * @throws com.anthropic.models.messages.CompactionToolChangesMismatchException The `compaction`
     *   block's `tool_changes` differs from what was returned with the block.
     * @throws com.anthropic.models.OrganizationOnHoldException Anthropic has disabled the
     *   organization for trust and safety reasons. An organization disabled for any other reason,
     *   such as billing, does not get this code. `appeal_url`, when present, is where the
     *   organization can appeal.
     * @throws com.anthropic.models.RequestBodyEncodingInvalidException The request body is not
     *   valid for its `Content-Encoding`.
     * @throws com.anthropic.models.RequestBodyEncodingUnsupportedException The request's
     *   `Content-Encoding` is not supported.
     * @throws com.anthropic.models.messages.ToolNameConflictException A tool made available in
     *   `messages` takes a name that an available tool of another kind, or of another MCP server,
     *   already holds.
     * @throws com.anthropic.models.messages.ToolReferenceUnresolvedException A tool change or MCP
     *   tool listing references a tool or MCP server that is not defined at that position in the
     *   conversation.
     * @throws com.anthropic.models.RequestBodyTooLargeAfterDecodingException The compressed request
     *   body exceeds the request size limit once decoded.
     * @throws com.anthropic.models.EnforcedSpendLimitReachedException The organization has crossed
     *   the monthly API usage threshold of its API tier.
     * @throws com.anthropic.models.messages.CompactionUnavailableException The compaction could not
     *   be completed for a transient server reason. Retry the request.
     */
    fun countTokens(params: MessageCountTokensParams): CompletableFuture<BetaMessageTokensCount> =
        countTokens(params, RequestOptions.none())

    /** @see countTokens */
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
         * Returns a raw HTTP response for `post /v1/messages`, but is otherwise the same as
         * [MessageServiceAsync.create].
         */
        fun create(params: MessageCreateParams): CompletableFuture<HttpResponseFor<BetaMessage>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaMessage>>

        /**
         * Returns a raw HTTP response for `post /v1/messages`, but is otherwise the same as
         * [MessageServiceAsync.createStreaming].
         */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>>> =
            createStreaming(params, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>>>

        /**
         * Returns a raw HTTP response for `post /v1/messages/count_tokens`, but is otherwise the
         * same as [MessageServiceAsync.countTokens].
         */
        fun countTokens(
            params: MessageCountTokensParams
        ): CompletableFuture<HttpResponseFor<BetaMessageTokensCount>> =
            countTokens(params, RequestOptions.none())

        /** @see countTokens */
        fun countTokens(
            params: MessageCountTokensParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaMessageTokensCount>>
    }
}
