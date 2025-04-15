package com.anthropic.helpers

import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonObject
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.CitationCharLocation
import com.anthropic.models.messages.CitationContentBlockLocation
import com.anthropic.models.messages.CitationPageLocation
import com.anthropic.models.messages.CitationsDelta
import com.anthropic.models.messages.ContentBlock
import com.anthropic.models.messages.InputJsonDelta
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageDeltaUsage
import com.anthropic.models.messages.RawContentBlockDelta
import com.anthropic.models.messages.RawContentBlockDeltaEvent
import com.anthropic.models.messages.RawContentBlockStartEvent
import com.anthropic.models.messages.RawContentBlockStopEvent
import com.anthropic.models.messages.RawMessageDeltaEvent
import com.anthropic.models.messages.RawMessageStartEvent
import com.anthropic.models.messages.RawMessageStopEvent
import com.anthropic.models.messages.RawMessageStreamEvent
import com.anthropic.models.messages.RedactedThinkingBlock
import com.anthropic.models.messages.SignatureDelta
import com.anthropic.models.messages.TextBlock
import com.anthropic.models.messages.TextCitation
import com.anthropic.models.messages.TextDelta
import com.anthropic.models.messages.ThinkingBlock
import com.anthropic.models.messages.ThinkingDelta
import com.anthropic.models.messages.ToolUseBlock
import com.anthropic.models.messages.Usage

/**
 * An accumulator that constructs a [Message] from a sequence of streamed events. Pass all events
 * from the `message_start` event to the `message_stop` event to [accumulate] and then call
 * [message] to get the final accumulated message. The final [Message] will be similar to what would
 * have been received had the non-streaming API been used.
 *
 * A [MessageAccumulator] may only be used to accumulate _one_ message. To accumulate another
 * message, create another instance of [MessageAccumulator].
 */
class MessageAccumulator private constructor() {
    /**
     * The final accumulated message. Created from the [messageBuilder] when the `message_stop`
     * event is notified.
     */
    private var message: Message? = null

    /**
     * The message builder used to accumulate the message details. Created when the `message_start`
     * event is notified.
     */
    private var messageBuilder: Message.Builder? = null

    /**
     * The message usage that accumulates the details of input and output tokens used when creating
     * the message. Created when the `message_start` event is notified.
     */
    private var messageUsage: Usage? = null

    /**
     * The indexed collection of content blocks accumulated so far. As `content_block_delta` events
     * are received, immutable elements here may be replaced with updated instances that hold the
     * latest accumulation. The keys correspond to the `index` identified in each of the
     * `content_block_delta` events.
     */
    private val messageContent: MutableMap<Long, ContentBlock> = mutableMapOf()

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

        @JvmStatic fun create() = MessageAccumulator()

        @JvmSynthetic
        internal fun mergeMessageUsage(usage: Usage, deltaUsage: MessageDeltaUsage) =
            usage.toBuilder().outputTokens(usage.outputTokens() + deltaUsage.outputTokens()).build()

        @JvmSynthetic
        internal fun mergeTextDelta(
            contentBlock: ContentBlock,
            textDelta: TextDelta,
        ): ContentBlock {
            require(contentBlock.isText()) { "Content block is not a text block." }
            val oldTextBlock = contentBlock.asText()
            val newTextBlock =
                oldTextBlock.toBuilder().text(oldTextBlock.text() + textDelta.text()).build()

            return ContentBlock.ofText(newTextBlock)
        }

        @JvmSynthetic
        internal fun mergeCitationsDelta(
            contentBlock: ContentBlock,
            citationsDelta: CitationsDelta,
        ): ContentBlock {
            require(contentBlock.isText()) { "Content block is not a text block." }
            val oldTextBlock = contentBlock.asText()
            val newTextBlock =
                oldTextBlock
                    .toBuilder()
                    .addCitation(citationsDeltaToTextCitation(citationsDelta))
                    .build()

            return ContentBlock.ofText(newTextBlock)
        }

        @JvmSynthetic
        internal fun mergeThinkingDelta(
            contentBlock: ContentBlock,
            thinkingDelta: ThinkingDelta,
        ): ContentBlock {
            require(contentBlock.isThinking()) { "Content block is not a thinking block." }
            val oldThinkingBlock = contentBlock.asThinking()
            val newThinkingBlock =
                oldThinkingBlock
                    .toBuilder()
                    .thinking(oldThinkingBlock.thinking() + thinkingDelta.thinking())
                    .build()

            return ContentBlock.ofThinking(newThinkingBlock)
        }

        @JvmSynthetic
        internal fun mergeSignatureDelta(
            contentBlock: ContentBlock,
            signatureDelta: SignatureDelta,
        ): ContentBlock {
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

            return ContentBlock.ofThinking(newThinkingBlock)
        }

        @JvmSynthetic
        internal fun citationsDeltaToTextCitation(citationsDelta: CitationsDelta) =
            // A `CitationsDelta` only holds _one_ citation.
            citationsDelta
                .citation()
                .accept(
                    object : CitationsDelta.Citation.Visitor<TextCitation> {
                        override fun visitCharLocation(charLocation: CitationCharLocation) =
                            TextCitation.ofCitationCharLocation(charLocation)

                        override fun visitPageLocation(pageLocation: CitationPageLocation) =
                            TextCitation.ofCitationPageLocation(pageLocation)

                        override fun visitContentBlockLocation(
                            contentBlockLocation: CitationContentBlockLocation
                        ) = TextCitation.ofCitationContentBlockLocation(contentBlockLocation)
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
     * Accumulates a streamed event and uses it to construct a [Message]. When all events, including
     * the `message_stop` event, have been accumulated, the message can be retrieved by calling
     * [message].
     *
     * @return The given [event] for convenience, such as when chaining method calls.
     * @throws AnthropicInvalidDataException If [accumulate] is called again after the final
     *   `message_stop` event has been accumulated. A [MessageAccumulator] can only be used to
     *   accumulate a single [Message].
     */
    fun accumulate(event: RawMessageStreamEvent): RawMessageStreamEvent {
        if (message != null) {
            throw AnthropicInvalidDataException("'message_stop' event already received.")
        }

        event.accept(
            object : RawMessageStreamEvent.Visitor<Unit> {
                override fun visitStart(start: RawMessageStartEvent) {
                    if (messageBuilder != null) {
                        throw AnthropicInvalidDataException(
                            "'message_start' event already received."
                        )
                    }
                    messageBuilder = start.message().toBuilder()
                    messageUsage = start.message().usage()
                }

                override fun visitDelta(deltaEvent: RawMessageDeltaEvent) {
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

                    messageUsage = mergeMessageUsage(requireMessageUsage(), deltaEvent.usage())
                }

                override fun visitStop(stop: RawMessageStopEvent) {
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

                override fun visitContentBlockStart(contentBlockStart: RawContentBlockStartEvent) {
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
                                    RawContentBlockStartEvent.ContentBlock.Visitor<ContentBlock> {
                                    override fun visitText(text: TextBlock) =
                                        ContentBlock.ofText(text)

                                    override fun visitToolUse(toolUse: ToolUseBlock) =
                                        ContentBlock.ofToolUse(toolUse)

                                    override fun visitThinking(thinking: ThinkingBlock) =
                                        ContentBlock.ofThinking(thinking)

                                    // Anthropic Extended Thinking API specification:
                                    // "`redacted_thinking` blocks will not have any deltas
                                    // associated and will be sent as a single event."
                                    override fun visitRedactedThinking(
                                        redactedThinking: RedactedThinkingBlock
                                    ) = ContentBlock.ofRedactedThinking(redactedThinking)
                                }
                            )
                }

                override fun visitContentBlockDelta(contentBlockDelta: RawContentBlockDeltaEvent) {
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
                                object : RawContentBlockDelta.Visitor<ContentBlock> {
                                    override fun visitText(text: TextDelta) =
                                        mergeTextDelta(oldContentBlock, text)

                                    override fun visitInputJson(inputJson: InputJsonDelta) = run {
                                        val oldInputJson = messageContentInputJson[index]

                                        messageContentInputJson[index] =
                                            (oldInputJson ?: "") + inputJson.partialJson()

                                        oldContentBlock // Unchanged until stop event.
                                    }

                                    override fun visitCitations(citations: CitationsDelta) =
                                        mergeCitationsDelta(oldContentBlock, citations)

                                    override fun visitThinking(thinking: ThinkingDelta) =
                                        mergeThinkingDelta(oldContentBlock, thinking)

                                    override fun visitSignature(signature: SignatureDelta) =
                                        mergeSignatureDelta(oldContentBlock, signature)
                                }
                            )
                }

                override fun visitContentBlockStop(contentBlockStop: RawContentBlockStopEvent) {
                    val index = contentBlockStop.index()

                    // Check only that there was a corresponding `content_block_start` event with
                    // the same index as this `content_block_stop` event. There are no "subtypes" of
                    // a `RawContentBlockStopEvent` as there are for the corresponding start and
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

                    if (oldContentBlock.isToolUse()) {
                        // Check that there was at least one delta, so a potentially-valid `input`
                        // JSON string was accumulated.
                        inputJson
                            ?: throw AnthropicInvalidDataException(
                                "Missing input JSON for index $index."
                            )

                        messageContent[index] =
                            ContentBlock.ofToolUse(
                                oldContentBlock
                                    .asToolUse()
                                    .toBuilder()
                                    // Anthropic Streaming Messages API: "the final `tool_use.input`
                                    // is always an _object_." However, if a tool function has no
                                    // arguments, the concatenated `inputJson` can be an empty
                                    // string. In that case, interpret it as a missing field.
                                    .input(
                                        if (inputJson.trim() == "") JsonMissing.of()
                                        else
                                            JSON_MAPPER.readValue(inputJson, JsonObject::class.java)
                                    )
                                    .build()
                            )
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
