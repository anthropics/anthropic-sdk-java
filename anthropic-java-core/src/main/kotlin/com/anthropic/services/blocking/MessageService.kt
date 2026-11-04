package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.MessageTokensCount
import com.anthropic.models.messages.RawMessageStreamEvent
import com.anthropic.models.messages.StructuredMessage
import com.anthropic.models.messages.StructuredMessageCreateParams
import com.anthropic.services.blocking.messages.BatchService
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
    fun create(params: MessageCreateParams): Message = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Message

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
    @MustBeClosed
    fun createStreaming(params: MessageCreateParams): StreamResponse<RawMessageStreamEvent> =
        createStreaming(params, RequestOptions.none())

    /** @see createStreaming */
    @MustBeClosed
    fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<RawMessageStreamEvent>

    /**
     * Creates a streaming model response for the given message. The message parameters can define a
     * JSON schema derived automatically from an arbitrary class to request a structured output in
     * JSON form. However, that structured output is split over multiple streamed events, so it will
     * not be deserialized automatically into an instance of that class. To deserialize the output,
     * first use a helper class to accumulate the stream of events into a single output value. See
     * the
     * [SDK documentation](https://platform.claude.com/docs/en/cli-sdks-libraries/sdks/java#structured-outputs)
     * for full details.
     */
    @MustBeClosed
    fun createStreaming(
        params: StructuredMessageCreateParams<*>
    ): StreamResponse<RawMessageStreamEvent> = createStreaming(params, RequestOptions.none())

    /** @see [createStreaming] */
    @MustBeClosed
    fun createStreaming(
        params: StructuredMessageCreateParams<*>,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<RawMessageStreamEvent> = createStreaming(params.rawParams, requestOptions)

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
    fun countTokens(params: MessageCountTokensParams): MessageTokensCount =
        countTokens(params, RequestOptions.none())

    /** @see countTokens */
    fun countTokens(
        params: MessageCountTokensParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MessageTokensCount

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
        fun create(params: MessageCreateParams): HttpResponseFor<Message> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Message>

        /**
         * Returns a raw HTTP response for `post /v1/messages`, but is otherwise the same as
         * [MessageService.createStreaming].
         */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams
        ): HttpResponseFor<StreamResponse<RawMessageStreamEvent>> =
            createStreaming(params, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            params: MessageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<RawMessageStreamEvent>>

        /**
         * Returns a raw HTTP response for `post /v1/messages/count_tokens`, but is otherwise the
         * same as [MessageService.countTokens].
         */
        @MustBeClosed
        fun countTokens(params: MessageCountTokensParams): HttpResponseFor<MessageTokensCount> =
            countTokens(params, RequestOptions.none())

        /** @see countTokens */
        @MustBeClosed
        fun countTokens(
            params: MessageCountTokensParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MessageTokensCount>
    }
}
