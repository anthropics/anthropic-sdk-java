package com.anthropic.helpers

import com.anthropic.core.JsonObject
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.messages.*

/** Checks if a content block is one that tracks tool input via input_json_delta events */
@JvmSynthetic
internal fun BetaContentBlock.tracksToolInput(): Boolean =
    isToolUse() || isServerToolUse() || isMcpToolUse()

/**
 * An accumulator that constructs a [BetaMessage] from a sequence of streamed events. Pass all
 * events from the `message_start` event to the `message_stop` event to [accumulate] and then call
 * [message] to get the final accumulated message. The final [BetaMessage] will be similar to what
 * would have been received had the non-streaming API been used.
 *
 * A [BetaMessageAccumulator] may only be used to accumulate _one_ message. To accumulate another
 * message, create another instance of [BetaMessageAccumulator].
 */
class BetaMessageAccumulator private constructor() {
    /**
     * The final accumulated message. Created from the [messageBuilder] when the `message_stop`
     * event is notified.
     */
    private var message: BetaMessage? = null

    /**
     * The message builder used to accumulate the message details. Created when the `message_start`
     * event is notified.
     */
    private var messageBuilder: BetaMessage.Builder? = null

    /**
     * The message usage that accumulates the details of input and output tokens used when creating
     * the message. Created when the `message_start` event is notified.
     */
    private var messageUsage: BetaUsage? = null

    /**
     * The indexed collection of content blocks accumulated so far. As `content_block_delta` events
     * are received, immutable elements here may be replaced with updated instances that hold the
     * latest accumulation. The keys correspond to the `index` identified in each of the
     * `content_block_delta` events.
     */
    private val messageContent: MutableMap<Long, BetaContentBlock> = mutableMapOf()

    /**
     * Accumulations of partial JSON strings from `tool_use` content block deltas to form complete
     * strings on the `content_block_stop` events, as valid JSON strings are required before each
     * final `tool_use` content block can be created. Only on the `content_block_stop` events are
     * such blocks created (based on the blocks from the `content_block_start` events); there are
     * _no_ incremental updates to the starting content blocks as the `tool_use` delta events are
     * notified. The keys correspond to the `index` identified in each of the `content_block_delta`
     * events.
     */
    private val messageContentInputJson: MutableMap<Long, String> = mutableMapOf()

    companion object {
        private val JSON_MAPPER = jsonMapper()

        @JvmStatic fun create() = BetaMessageAccumulator()

        @JvmSynthetic
        internal fun mergeMessageUsage(
            usage: BetaUsage,
            deltaUsage: BetaMessageDeltaUsage,
        ): BetaUsage {
            val builder = usage.toBuilder()

            if (!deltaUsage._outputTokens().isMissing()) {
                builder.outputTokens(deltaUsage.outputTokens())
            }

            if (!deltaUsage._inputTokens().isMissing()) {
                builder.inputTokens(deltaUsage.inputTokens().orElse(0))
            }

            if (!deltaUsage._cacheCreationInputTokens().isMissing()) {
                builder.cacheCreationInputTokens(deltaUsage.cacheCreationInputTokens())
            }

            if (!deltaUsage._cacheReadInputTokens().isMissing()) {
                builder.cacheReadInputTokens(deltaUsage.cacheReadInputTokens())
            }

            if (!deltaUsage._serverToolUse().isMissing()) {
                builder.serverToolUse(deltaUsage.serverToolUse())
            }

            return builder.build()
        }

        @JvmSynthetic
        internal fun mergeTextDelta(
            contentBlock: BetaContentBlock,
            textDelta: BetaTextDelta,
        ): BetaContentBlock {
            require(contentBlock.isText()) { "Content block is not a text block." }
            val oldTextBlock = contentBlock.asText()
            val newTextBlock =
                oldTextBlock.toBuilder().text(oldTextBlock.text() + textDelta.text()).build()

            return BetaContentBlock.ofText(newTextBlock)
        }

        @JvmSynthetic
        internal fun mergeCitationsDelta(
            contentBlock: BetaContentBlock,
            citationsDelta: BetaCitationsDelta,
        ): BetaContentBlock {
            require(contentBlock.isText()) { "Content block is not a text block." }
            val oldTextBlock = contentBlock.asText()
            val newTextBlock =
                oldTextBlock
                    .toBuilder()
                    .addCitation(citationsDeltaToTextCitation(citationsDelta))
                    .build()

            return BetaContentBlock.ofText(newTextBlock)
        }

        @JvmSynthetic
        internal fun mergeThinkingDelta(
            contentBlock: BetaContentBlock,
            thinkingDelta: BetaThinkingDelta,
        ): BetaContentBlock {
            require(contentBlock.isThinking()) { "Content block is not a thinking block." }
            val oldThinkingBlock = contentBlock.asThinking()
            val newThinkingBlock =
                oldThinkingBlock
                    .toBuilder()
                    .thinking(oldThinkingBlock.thinking() + thinkingDelta.thinking())
                    .build()

            return BetaContentBlock.ofThinking(newThinkingBlock)
        }

        @JvmSynthetic
        internal fun mergeSignatureDelta(
            contentBlock: BetaContentBlock,
            signatureDelta: BetaSignatureDelta,
        ): BetaContentBlock {
            // Anthropic Streaming Messages API: "For thinking content, a special `signature_delta`
            // event is sent just before the `content_block_stop` event. This signature is used to
            // verify the integrity of the thinking block."
            //
            // Therefore, the "merge" here does not concatenate with the existing value of the
            // `signature` on the `oldThinkingBlock`; the signature is simply set from the given
            // `signatureDelta`, as there will be only one such delta for the content block.
            require(contentBlock.isThinking()) { "Content block is not a thinking block." }
            val oldThinkingBlock = contentBlock.asThinking()
            val newThinkingBlock =
                oldThinkingBlock.toBuilder().signature(signatureDelta.signature()).build()

            return BetaContentBlock.ofThinking(newThinkingBlock)
        }

        @JvmSynthetic
        internal fun citationsDeltaToTextCitation(citationsDelta: BetaCitationsDelta) =
            // A `CitationsDelta` only holds _one_ citation.
            citationsDelta
                .citation()
                .accept(
                    object : BetaCitationsDelta.Citation.Visitor<BetaTextCitation> {
                        override fun visitCharLocation(charLocation: BetaCitationCharLocation) =
                            BetaTextCitation.ofCharLocation(charLocation)

                        override fun visitPageLocation(pageLocation: BetaCitationPageLocation) =
                            BetaTextCitation.ofPageLocation(pageLocation)

                        override fun visitContentBlockLocation(
                            contentBlockLocation: BetaCitationContentBlockLocation
                        ) = BetaTextCitation.ofContentBlockLocation(contentBlockLocation)

                        override fun visitWebSearchResultLocation(
                            betaCitationsWebSearchResultLocation:
                                BetaCitationsWebSearchResultLocation
                        ) =
                            BetaTextCitation.ofWebSearchResultLocation(
                                betaCitationsWebSearchResultLocation
                            )

                        override fun visitSearchResultLocation(
                            searchResultLocation: BetaCitationSearchResultLocation
                        ): BetaTextCitation =
                            BetaTextCitation.ofSearchResultLocation(searchResultLocation)
                    }
                )
    }

    /**
     * Gets the final accumulated message. Until the `message_stop` event has been received, a
     * message will not be available. Wait until all events have been handled by [accumulate] before
     * calling this method.
     *
     * @throws IllegalStateException If called before the `message_stop` event has been accumulated.
     */
    fun message() = checkNotNull(message) { "'message_stop' event not yet received." }

    /**
     * Accumulates a streamed event and uses it to construct a [BetaMessage]. When all events,
     * including the `message_stop` event, have been accumulated, the message can be retrieved by
     * calling [message].
     *
     * @return The given [event] for convenience, such as when chaining method calls.
     * @throws AnthropicInvalidDataException If [accumulate] is called again after the final
     *   `message_stop` event has been accumulated. A [BetaMessageAccumulator] can only be used to
     *   accumulate a single [BetaMessage].
     */
    fun accumulate(event: BetaRawMessageStreamEvent): BetaRawMessageStreamEvent {
        if (message != null) {
            throw AnthropicInvalidDataException("'message_stop' event already received.")
        }

        event.accept(
            object : BetaRawMessageStreamEvent.Visitor<Unit> {
                override fun visitMessageStart(start: BetaRawMessageStartEvent) {
                    if (messageBuilder != null) {
                        throw AnthropicInvalidDataException(
                            "'message_start' event already received."
                        )
                    }
                    messageBuilder = start.message().toBuilder()
                    messageUsage = start.message().usage()
                }

                override fun visitMessageDelta(deltaEvent: BetaRawMessageDeltaEvent) {
                    val delta = deltaEvent.delta()

                    // The Anthropic API allows that there may be "one or more `message_delta`
                    // events". Here, the interpretation is that if multiple `message_delta` events
                    // have a `stop_reason`, only the last encountered non-missing `stop_reason`
                    // value will survive, which may be an _explicit_ `null` value.
                    if (delta._stopReason().isNull()) {
                        requireMessageBuilder().stopReason(null)
                    } else if (!delta._stopReason().isMissing()) {
                        requireMessageBuilder().stopReason(delta.stopReason())
                    }

                    // The same applies to the `stop_sequence` string; only the last value will
                    // survive; multiple `stop_sequence` string values from multiple events are
                    // _not_ concatenated.
                    if (delta._stopSequence().isNull()) {
                        requireMessageBuilder().stopSequence(null)
                    } else if (!delta._stopSequence().isMissing()) {
                        requireMessageBuilder().stopSequence(delta.stopSequence().get())
                    }

                    if (delta.container().isPresent()) {
                        requireMessageBuilder().container(delta.container().get())
                    }

                    if (deltaEvent.contextManagement().isPresent()) {
                        requireMessageBuilder()
                            .contextManagement(deltaEvent.contextManagement().get())
                    }

                    messageUsage = mergeMessageUsage(requireMessageUsage(), deltaEvent.usage())
                }

                override fun visitMessageStop(stop: BetaRawMessageStopEvent) {
                    message =
                        requireMessageBuilder()
                            // The indexed content block map is converted to a list with the blocks
                            // in the indexed order. If there are gaps in the indexes, then the
                            // indexes of the final list of content blocks will not correspond to
                            // the indexes of the map entries. However, gaps are not expected and
                            // what the event indexes were does not matter for the content blocks in
                            // the final message; it only matters that the relative order of the
                            // content blocks is preserved.
                            .content(messageContent.entries.sortedBy { it.key }.map { it.value })
                            .usage(requireMessageUsage())
                            .build()
                    messageBuilder = null
                }

                override fun visitContentBlockStart(
                    contentBlockStart: BetaRawContentBlockStartEvent
                ) {
                    val index = contentBlockStart.index()

                    if (messageContent[index] != null) {
                        throw AnthropicInvalidDataException(
                            "Content block already started for index $index."
                        )
                    }

                    messageContent[index] =
                        contentBlockStart
                            .contentBlock()
                            .accept(
                                object :
                                    BetaRawContentBlockStartEvent.ContentBlock.Visitor<
                                        BetaContentBlock
                                    > {
                                    override fun visitText(betaText: BetaTextBlock) =
                                        BetaContentBlock.ofText(betaText)

                                    override fun visitToolUse(betaToolUse: BetaToolUseBlock) =
                                        BetaContentBlock.ofToolUse(betaToolUse)

                                    override fun visitServerToolUse(
                                        betaServerToolUse: BetaServerToolUseBlock
                                    ): BetaContentBlock =
                                        BetaContentBlock.ofServerToolUse(betaServerToolUse)

                                    override fun visitWebFetchToolResult(
                                        webFetchToolResult: BetaWebFetchToolResultBlock
                                    ): BetaContentBlock =
                                        BetaContentBlock.ofWebFetchToolResult(webFetchToolResult)

                                    override fun visitWebSearchToolResult(
                                        betaWebSearchToolResult: BetaWebSearchToolResultBlock
                                    ): BetaContentBlock =
                                        BetaContentBlock.ofWebSearchToolResult(
                                            betaWebSearchToolResult
                                        )

                                    override fun visitCodeExecutionToolResult(
                                        codeExecutionToolResult: BetaCodeExecutionToolResultBlock
                                    ): BetaContentBlock {
                                        return BetaContentBlock.ofCodeExecutionToolResult(
                                            codeExecutionToolResult
                                        )
                                    }

                                    override fun visitBashCodeExecutionToolResult(
                                        bashCodeExecutionToolResult:
                                            BetaBashCodeExecutionToolResultBlock
                                    ): BetaContentBlock {
                                        return BetaContentBlock.ofBashCodeExecutionToolResult(
                                            bashCodeExecutionToolResult
                                        )
                                    }

                                    override fun visitTextEditorCodeExecutionToolResult(
                                        textEditorCodeExecutionToolResult:
                                            BetaTextEditorCodeExecutionToolResultBlock
                                    ): BetaContentBlock {
                                        return BetaContentBlock.ofTextEditorCodeExecutionToolResult(
                                            textEditorCodeExecutionToolResult
                                        )
                                    }

                                    override fun visitMcpToolUse(
                                        mcpToolUse: BetaMcpToolUseBlock
                                    ): BetaContentBlock {
                                        return BetaContentBlock.ofMcpToolUse(mcpToolUse)
                                    }

                                    override fun visitMcpToolResult(
                                        mcpToolResult: BetaMcpToolResultBlock
                                    ): BetaContentBlock {
                                        return BetaContentBlock.ofMcpToolResult(mcpToolResult)
                                    }

                                    override fun visitContainerUpload(
                                        containerUpload: BetaContainerUploadBlock
                                    ): BetaContentBlock {
                                        return BetaContentBlock.ofContainerUpload(containerUpload)
                                    }

                                    override fun visitThinking(betaThinking: BetaThinkingBlock) =
                                        BetaContentBlock.ofThinking(betaThinking)

                                    // Anthropic Extended Thinking API specification:
                                    // "`redacted_thinking` blocks will not have any deltas
                                    // associated and will be sent as a single event."
                                    override fun visitRedactedThinking(
                                        betaRedactedThinking: BetaRedactedThinkingBlock
                                    ) = BetaContentBlock.ofRedactedThinking(betaRedactedThinking)
                                }
                            )
                }

                override fun visitContentBlockDelta(
                    contentBlockDelta: BetaRawContentBlockDeltaEvent
                ) {
                    val index = contentBlockDelta.index()
                    val oldContentBlock =
                        messageContent[index]
                            ?: throw AnthropicInvalidDataException(
                                "Content block not started for index $index."
                            )

                    messageContent[index] =
                        contentBlockDelta
                            .delta()
                            .accept(
                                object : BetaRawContentBlockDelta.Visitor<BetaContentBlock> {
                                    override fun visitText(text: BetaTextDelta) =
                                        mergeTextDelta(oldContentBlock, text)

                                    override fun visitInputJson(inputJson: BetaInputJsonDelta) =
                                        run {
                                            val oldInputJson = messageContentInputJson[index]

                                            messageContentInputJson[index] =
                                                (oldInputJson ?: "") + inputJson.partialJson()

                                            oldContentBlock // Unchanged until stop event.
                                        }

                                    override fun visitCitations(citations: BetaCitationsDelta) =
                                        mergeCitationsDelta(oldContentBlock, citations)

                                    override fun visitThinking(thinking: BetaThinkingDelta) =
                                        mergeThinkingDelta(oldContentBlock, thinking)

                                    override fun visitSignature(signature: BetaSignatureDelta) =
                                        mergeSignatureDelta(oldContentBlock, signature)
                                }
                            )
                }

                override fun visitContentBlockStop(contentBlockStop: BetaRawContentBlockStopEvent) {
                    val index = contentBlockStop.index()

                    // Check only that there was a corresponding `content_block_start` event with
                    // the same index as this `content_block_stop` event. There are no "subtypes" of
                    // a `BetaRawContentBlockStopEvent` as there are for the corresponding start and
                    // delta events. It is not possible to validate that the `type` of this event is
                    // the expected one for the accumulated content with the same `index`, as the
                    // type is always just `content_block_stop`.
                    val oldContentBlock =
                        messageContent[index]
                            ?: throw AnthropicInvalidDataException(
                                "Content block not started for index $index."
                            )

                    // The `content_block_stop` event for most content block types can be ignored,
                    // as it carries no data. Where the `index` corresponds to a `tool_use` content
                    // block, the partial JSON that was concatenated from each delta can now be used
                    // to update the final `tool_use` content block.
                    val inputJson = messageContentInputJson[index]

                    if (oldContentBlock.tracksToolInput()) {
                        // Check that there was at least one delta, so a potentially-valid `input`
                        // JSON string was accumulated.
                        inputJson
                            ?: throw AnthropicInvalidDataException(
                                "Missing input JSON for index $index."
                            )

                        val parsedInput =
                            try {
                                JSON_MAPPER.readValue(inputJson, JsonObject::class.java)
                            } catch (e: Exception) {
                                throw AnthropicInvalidDataException(
                                    "Unable to parse tool parameter JSON from model. Please retry your request or adjust your prompt. Error: ${e}. JSON: $inputJson"
                                )
                            }

                        messageContent[index] =
                            when {
                                oldContentBlock.isToolUse() ->
                                    BetaContentBlock.ofToolUse(
                                        oldContentBlock
                                            .asToolUse()
                                            .toBuilder()
                                            .input(parsedInput)
                                            .build()
                                    )
                                oldContentBlock.isServerToolUse() ->
                                    BetaContentBlock.ofServerToolUse(
                                        oldContentBlock
                                            .asServerToolUse()
                                            .toBuilder()
                                            .input(parsedInput)
                                            .build()
                                    )
                                oldContentBlock.isMcpToolUse() ->
                                    BetaContentBlock.ofMcpToolUse(
                                        oldContentBlock
                                            .asMcpToolUse()
                                            .toBuilder()
                                            .input(parsedInput)
                                            .build()
                                    )
                                else -> oldContentBlock // Should never happen given tracksToolInput
                            // check
                            }
                    }
                }
            }
        )

        return event
    }

    private fun requireMessageBuilder() =
        messageBuilder ?: throw AnthropicInvalidDataException("'message_start' event not received.")

    private fun requireMessageUsage() =
        messageUsage ?: throw AnthropicInvalidDataException("'message_start' event not received.")
}
