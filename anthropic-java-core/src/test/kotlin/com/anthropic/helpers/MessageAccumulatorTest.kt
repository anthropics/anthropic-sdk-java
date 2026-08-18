package com.anthropic.helpers

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonNull
import com.anthropic.core.JsonString
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.*
import com.anthropic.models.messages.RawContentBlockStartEvent.ContentBlock
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class MessageAccumulatorTest {
    companion object {
        private const val INPUT_TOKENS: Long = 42L

        /** A value that is "not set" has no effect on any existing value when accumulated. */
        private val NOT_SET = JsonMissing.of()

        /** A value that is "set to null" resets any existing value to `null` when accumulated. */
        private val SET_TO_NULL = JsonNull.of()
    }

    @Test
    fun mergeMessageUsage() {
        val usage1 =
            MessageAccumulator.mergeMessageUsage(
                usage(INPUT_TOKENS),
                MessageDeltaUsage.builder()
                    .outputTokens(44L)
                    .outputTokensDetails(OutputTokensDetails.builder().thinkingTokens(0L).build())
                    .cacheCreationInputTokens(0L)
                    .cacheReadInputTokens(0L)
                    .inputTokens(INPUT_TOKENS) // Use test constant
                    .serverToolUse(
                        ServerToolUsage.builder().webFetchRequests(0L).webSearchRequests(0L).build()
                    )
                    .build(),
            )
        val usage2 =
            MessageAccumulator.mergeMessageUsage(
                usage1,
                MessageDeltaUsage.builder()
                    .outputTokens(11L)
                    .outputTokensDetails(OutputTokensDetails.builder().thinkingTokens(0L).build())
                    .cacheCreationInputTokens(0L)
                    .cacheReadInputTokens(0L)
                    .inputTokens(INPUT_TOKENS) // Use test constant
                    .serverToolUse(
                        ServerToolUsage.builder().webFetchRequests(0L).webSearchRequests(0L).build()
                    )
                    .build(),
            )

        assertThat(usage1.inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(usage1.outputTokens()).isEqualTo(44L)

        assertThat(usage2.inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(usage2.outputTokens()).isEqualTo(11L)
    }

    @Test
    fun mergeMessageUsageWithNullInputTokensKeepsMessageStartValue() {
        val merged =
            MessageAccumulator.mergeMessageUsage(
                usage(INPUT_TOKENS),
                jsonMapper()
                    .readValue(
                        """{"output_tokens":5,"input_tokens":null}""",
                        MessageDeltaUsage::class.java,
                    ),
            )

        assertThat(merged.inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(merged.outputTokens()).isEqualTo(5L)
    }

    @Test
    fun mergeMessageUsageWithNullCountersKeepsMessageStartValues() {
        val startUsage =
            usage(INPUT_TOKENS)
                .toBuilder()
                .cacheCreationInputTokens(10L)
                .cacheReadInputTokens(5L)
                .serverToolUse(
                    ServerToolUsage.builder().webFetchRequests(1L).webSearchRequests(2L).build()
                )
                .outputTokensDetails(OutputTokensDetails.builder().thinkingTokens(3L).build())
                .build()

        val merged =
            MessageAccumulator.mergeMessageUsage(
                startUsage,
                jsonMapper()
                    .readValue(
                        """{"output_tokens":5,"cache_creation_input_tokens":null,"cache_read_input_tokens":null,"server_tool_use":null,"output_tokens_details":null}""",
                        MessageDeltaUsage::class.java,
                    ),
            )

        assertThat(merged.outputTokens()).isEqualTo(5L)
        assertThat(merged.cacheCreationInputTokens()).hasValue(10L)
        assertThat(merged.cacheReadInputTokens()).hasValue(5L)
        assertThat(merged.serverToolUse()).isEqualTo(startUsage.serverToolUse())
        assertThat(merged.outputTokensDetails()).isEqualTo(startUsage.outputTokensDetails())
    }

    @Test
    fun mergeTextDeltaWrongBlockType() {
        assertThatThrownBy {
                MessageAccumulator.mergeTextDelta(
                    com.anthropic.models.messages.ContentBlock.ofThinking(thinkingBlock()),
                    textDelta("hello"),
                )
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Content block is not a text block.")
    }

    @Test
    fun mergeTextDelta() {
        // Add citations and later check they are preserved by the "merge" operation. This proves
        // (enough) that "toBuilder()" is being called to copy everything, not just the text.
        val text1 =
            MessageAccumulator.mergeTextDelta(
                com.anthropic.models.messages.ContentBlock.ofText(
                    textBlock("hello")
                        .toBuilder()
                        .addCitation(citationPageLocation(123L))
                        .addCitation(citationPageLocation(456L))
                        .build()
                ),
                textDelta(" world"),
            )
        val text2 = MessageAccumulator.mergeTextDelta(text1, textDelta("!!!"))
        val citations2 = text2.asText().citations().get()

        assertThat(text1.isText()).isTrue()
        assertThat(text1.text().get().text()).isEqualTo("hello world")

        assertThat(text2.isText()).isTrue()
        assertThat(text2.text().get().text()).isEqualTo("hello world!!!")

        assertThat(citations2.size).isEqualTo(2)
        assertThat(citations2[0].pageLocation().get().startPageNumber()).isEqualTo(123L)
        assertThat(citations2[1].pageLocation().get().startPageNumber()).isEqualTo(456L)
    }

    @Test
    fun mergeCitationsDeltaWrongBlockType() {
        assertThatThrownBy {
                MessageAccumulator.mergeCitationsDelta(
                    com.anthropic.models.messages.ContentBlock.ofThinking(thinkingBlock()),
                    citationsDelta(123L),
                )
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Content block is not a text block.")
    }

    @Test
    fun mergeCitationsDelta() {
        // Use all types of citation to exercise the code in [citationsDeltaToTextCitation].
        val text1 =
            MessageAccumulator.mergeCitationsDelta(
                com.anthropic.models.messages.ContentBlock.ofText(textBlock("hello")),
                citationsDelta(123L),
            )
        val text2 = MessageAccumulator.mergeCitationsDelta(text1, citationsDelta(456L))
        val text3 =
            MessageAccumulator.mergeCitationsDelta(
                text2,
                CitationsDelta.builder().citation(citationCharLocation(789L)).build(),
            )
        val text4 =
            MessageAccumulator.mergeCitationsDelta(
                text3,
                CitationsDelta.builder().citation(citationContentBlockLocation(890L)).build(),
            )
        val citations4 = text4.asText().citations().get()

        assertThat(text1.isText()).isTrue()
        assertThat(text1.text().get().text()).isEqualTo("hello")

        assertThat(text2.isText()).isTrue()
        assertThat(text2.text().get().text()).isEqualTo("hello")

        assertThat(citations4.size).isEqualTo(4)
        assertThat(citations4[0].pageLocation().get().startPageNumber()).isEqualTo(123L)
        assertThat(citations4[1].pageLocation().get().startPageNumber()).isEqualTo(456L)
        assertThat(citations4[2].charLocation().get().startCharIndex()).isEqualTo(789L)
        assertThat(citations4[3].contentBlockLocation().get().startBlockIndex()).isEqualTo(890L)
    }

    @Test
    fun mergeThinkingDeltaWrongBlockType() {
        assertThatThrownBy {
                MessageAccumulator.mergeThinkingDelta(
                    com.anthropic.models.messages.ContentBlock.ofText(textBlock("hello")),
                    thinkingDelta("hmm...let me think..."),
                )
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Content block is not a thinking block.")
    }

    @Test
    fun mergeThinkingDelta() {
        val thinking1 =
            MessageAccumulator.mergeThinkingDelta(
                com.anthropic.models.messages.ContentBlock.ofThinking(
                    thinkingBlock("Let me see...", "sig-1")
                ),
                thinkingDelta(" Nope."),
            )
        val thinking2 =
            MessageAccumulator.mergeThinkingDelta(thinking1, thinkingDelta(" Not a clue."))

        assertThat(thinking1.isThinking()).isTrue()
        assertThat(thinking1.thinking().get().thinking()).isEqualTo("Let me see... Nope.")

        assertThat(thinking2.isThinking()).isTrue()
        assertThat(thinking2.thinking().get().thinking())
            .isEqualTo("Let me see... Nope. Not a clue.")

        assertThat(thinking2.thinking().get().signature()).isEqualTo("sig-1")
    }

    @Test
    fun mergeSignatureDeltaWrongBlockType() {
        assertThatThrownBy {
                MessageAccumulator.mergeSignatureDelta(
                    com.anthropic.models.messages.ContentBlock.ofText(
                        textBlock("Yours sincerely,")
                    ),
                    signatureDelta("John Hancock"),
                )
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Content block is not a thinking block.")
    }

    @Test
    fun mergeSignatureDelta() {
        val thinking1 =
            MessageAccumulator.mergeSignatureDelta(
                com.anthropic.models.messages.ContentBlock.ofThinking(
                    thinkingBlock("Hmm...", "sig-1")
                ),
                signatureDelta("sig-2"),
            )
        val thinking2 = MessageAccumulator.mergeSignatureDelta(thinking1, signatureDelta("sig-3"))

        assertThat(thinking1.isThinking()).isTrue()
        assertThat(thinking1.thinking().get().thinking()).isEqualTo("Hmm...")
        // Signatures are not concatenated. The last signature delta is used as the only signature.
        assertThat(thinking1.thinking().get().signature()).isEqualTo("sig-2")

        assertThat(thinking2.isThinking()).isTrue()
        assertThat(thinking2.thinking().get().thinking()).isEqualTo("Hmm...")
        assertThat(thinking2.thinking().get().signature()).isEqualTo("sig-3")
    }

    @Test
    fun messageNotStarted() {
        val accumulator = MessageAccumulator.create()

        assertThatThrownBy { accumulator.message() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("'message_stop' event not yet received.")
    }

    @Test
    fun messageNotStopped() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        assertThatThrownBy { accumulator.message() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("'message_stop' event not yet received.")
    }

    @Test
    fun accumulateStopEventBeforeStarted() {
        val accumulator = MessageAccumulator.create()

        assertThatThrownBy { accumulator.accumulate(messageStopEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_start' event not received.")
    }

    @Test
    fun accumulateDeltaEventBeforeStarted() {
        val accumulator = MessageAccumulator.create()

        assertThatThrownBy { accumulator.accumulate(messageDeltaEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_start' event not received.")
    }

    @Test
    fun accumulateStartEventAfterStart() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        assertThatThrownBy { accumulator.accumulate(messageStartEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_start' event already received.")
    }

    @Test
    fun accumulateStopEventAfterStopped() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        assertThatThrownBy { accumulator.accumulate(messageStopEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_stop' event already received.")
    }

    @Test
    fun accumulateStartEventAfterStopped() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        assertThatThrownBy { accumulator.accumulate(messageStartEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_stop' event already received.")
    }

    @Test
    fun accumulateDeltaEventAfterStopped() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        assertThatThrownBy { accumulator.accumulate(messageDeltaEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_stop' event already received.")
    }

    @Test
    fun messageWithNoDeltasOrContent() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        assertThatNoException().isThrownBy { accumulator.message() }

        val message = accumulator.message()

        assertThat(message.id()).isEqualTo("message-id")
        assertThat(message.model()).isEqualTo(Model.CLAUDE_SONNET_4_5)

        assertThat(message.content()).isEmpty()

        assertThat(message.stopReason()).isNotPresent()
        assertThat(message.stopSequence()).isNotPresent()

        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().cacheCreationInputTokens()).hasValue(0L)
        assertThat(message.usage().cacheReadInputTokens()).hasValue(0L)
        assertThat(message.usage().outputTokens()).isEqualTo(0L)
    }

    @Test
    fun messageWithDeltasAndNoContent() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(
            messageDeltaEvent(JsonField.of(StopReason.END_TURN), outputTokens = 96L)
        )
        accumulator.accumulate(messageStopEvent())

        assertThatNoException().isThrownBy { accumulator.message() }

        val message = accumulator.message()

        assertThat(message.id()).isEqualTo("message-id")
        assertThat(message.model()).isEqualTo(Model.CLAUDE_SONNET_4_5)

        assertThat(message.content()).isEmpty()

        assertThat(message.stopReason()).hasValue(StopReason.END_TURN)
        assertThat(message.stopSequence()).isNotPresent()

        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().cacheCreationInputTokens()).hasValue(0L)
        assertThat(message.usage().cacheReadInputTokens()).hasValue(0L)
        assertThat(message.usage().outputTokens()).isEqualTo(96L)
    }

    @Test
    fun messageStopReasonDefaultsToMissing() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        // Check both the non-public "raw" JSON value and the public `Optional` value (which will
        // be `null` if the "raw" value is missing).
        assertThat(accumulator.message()._stopReason().isMissing()).isEqualTo(true)
        assertThat(accumulator.message().stopReason()).isEmpty()
    }

    @Test
    fun messageDeltaWithNullStopReason() {
        // The default stop reason is `JsonMissing`. See if an explicit `null` works instead and
        // that an explicit `JsonMissing` does not override the `null`.
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageDeltaEvent(stopReason = SET_TO_NULL))
        accumulator.accumulate(messageDeltaEvent(stopReason = NOT_SET)) // Should be ignored.
        accumulator.accumulate(messageStopEvent())

        assertThat(accumulator.message()._stopReason().isMissing()).isEqualTo(false)
        assertThat(accumulator.message()._stopReason().isNull()).isEqualTo(true)
        assertThat(accumulator.message().stopReason()).isEmpty()
    }

    @Test
    fun messageDeltaWithNonNullStopReason() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        // The last non-missing value should "win".
        accumulator.accumulate(messageDeltaEvent(stopReason = SET_TO_NULL))
        accumulator.accumulate(messageDeltaEvent(stopReason = NOT_SET)) // Should be ignored.
        accumulator.accumulate(messageDeltaEvent(stopReason = JsonField.of(StopReason.END_TURN)))
        accumulator.accumulate(messageStopEvent())

        assertThat(accumulator.message()._stopReason().isMissing()).isEqualTo(false)
        assertThat(accumulator.message()._stopReason().isNull()).isEqualTo(false)
        assertThat(accumulator.message().stopReason()).hasValue(StopReason.END_TURN)
    }

    @Test
    fun messageStopSequenceDefaultsToMissing() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        // Check both the non-public "raw" JSON value and the public `Optional` value (which will
        // be `null` if the "raw" value is missing).
        assertThat(accumulator.message()._stopSequence().isMissing()).isEqualTo(true)
        assertThat(accumulator.message().stopSequence()).isEmpty()
    }

    @Test
    fun messageDeltaWithNullStopSequence() {
        // The default stop sequence is `JsonMissing`. See if an explicit `null` works instead and
        // that an explicit `JsonMissing` does not override the `null`.
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageDeltaEvent(stopSequence = SET_TO_NULL))
        accumulator.accumulate(messageDeltaEvent(stopSequence = NOT_SET)) // Should be ignored.
        accumulator.accumulate(messageStopEvent())

        assertThat(accumulator.message()._stopSequence().isMissing()).isEqualTo(false)
        assertThat(accumulator.message()._stopSequence().isNull()).isEqualTo(true)
        assertThat(accumulator.message().stopSequence()).isEmpty()
    }

    @Test
    fun messageDeltaWithNonNullStopSequence() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        // The last non-missing value should "win".
        accumulator.accumulate(messageDeltaEvent(stopSequence = SET_TO_NULL))
        accumulator.accumulate(messageDeltaEvent(stopSequence = NOT_SET)) // Should be ignored.
        accumulator.accumulate(messageDeltaEvent(stopSequence = JsonField.of("hello world")))
        accumulator.accumulate(messageStopEvent())

        assertThat(accumulator.message()._stopSequence().isMissing()).isEqualTo(false)
        assertThat(accumulator.message()._stopSequence().isNull()).isEqualTo(false)
        assertThat(accumulator.message().stopSequence()).hasValue("hello world")
    }

    @Test
    fun messageDeltaWithUsage() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageDeltaEvent(outputTokens = 11L))
        accumulator.accumulate(messageDeltaEvent(outputTokens = 12L))
        accumulator.accumulate(messageDeltaEvent(outputTokens = 13L))
        accumulator.accumulate(messageStopEvent())

        assertThat(accumulator.message().usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(accumulator.message().usage().outputTokens()).isEqualTo(13L)
    }

    @Test
    fun messageDeltaAppliesContainerAndAllUsageCounters() {
        // Everything the terminal `message_delta` carries must land on the accumulated message:
        // `container` and `output_tokens_details` are never sent on `message_start`, so this event
        // is their only source.
        val accumulator = MessageAccumulator.create()
        val container =
            Container.builder()
                .id("container-id")
                .expiresAt(OffsetDateTime.parse("2050-01-01T00:00:00Z"))
                .build()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(
            RawMessageStreamEvent.ofMessageDelta(
                RawMessageDeltaEvent.builder()
                    .delta(
                        RawMessageDeltaEvent.Delta.builder()
                            .container(container)
                            .stopDetails(NOT_SET)
                            .stopReason(JsonField.of(StopReason.END_TURN))
                            .stopSequence(NOT_SET)
                            .build()
                    )
                    .usage(
                        MessageDeltaUsage.builder()
                            .outputTokens(96L)
                            .outputTokensDetails(
                                OutputTokensDetails.builder().thinkingTokens(64L).build()
                            )
                            .cacheCreationInputTokens(7L)
                            .cacheReadInputTokens(9L)
                            .inputTokens(101L)
                            .serverToolUse(
                                ServerToolUsage.builder()
                                    .webFetchRequests(1L)
                                    .webSearchRequests(2L)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()

        assertThat(message.container()).hasValue(container)
        assertThat(message.usage().outputTokens()).isEqualTo(96L)
        assertThat(message.usage().inputTokens()).isEqualTo(101L)
        assertThat(message.usage().cacheCreationInputTokens()).hasValue(7L)
        assertThat(message.usage().cacheReadInputTokens()).hasValue(9L)
        assertThat(message.usage().outputTokensDetails().get().thinkingTokens()).isEqualTo(64L)
        assertThat(message.usage().serverToolUse().get().webFetchRequests()).isEqualTo(1L)
        assertThat(message.usage().serverToolUse().get().webSearchRequests()).isEqualTo(2L)
    }

    @Test
    fun messageDeltaWithoutOptionalUsageKeysKeepsMessageStartValues() {
        // A `message_delta` omits every usage key that does not apply to the response, and never
        // re-sends `service_tier`, `cache_creation` or `inference_geo`, so all of those must
        // survive from `message_start`.
        val accumulator = MessageAccumulator.create()
        val startUsage =
            usage(INPUT_TOKENS)
                .toBuilder()
                .cacheCreationInputTokens(11L)
                .cacheReadInputTokens(22L)
                .outputTokensDetails(OutputTokensDetails.builder().thinkingTokens(33L).build())
                .serverToolUse(
                    ServerToolUsage.builder().webFetchRequests(3L).webSearchRequests(4L).build()
                )
                .build()

        accumulator.accumulate(messageStartEvent(startUsage))
        accumulator.accumulate(
            RawMessageStreamEvent.ofMessageDelta(
                RawMessageDeltaEvent.builder()
                    .delta(
                        RawMessageDeltaEvent.Delta.builder()
                            .stopDetails(NOT_SET)
                            .stopReason(JsonField.of(StopReason.END_TURN))
                            .stopSequence(NOT_SET)
                            .build()
                    )
                    // Only `output_tokens` is non-optional on the wire.
                    .usage(
                        jsonMapper()
                            .readValue("""{"output_tokens":77}""", MessageDeltaUsage::class.java)
                    )
                    .build()
            )
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()

        assertThat(message.usage().outputTokens()).isEqualTo(77L)
        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().cacheCreationInputTokens()).hasValue(11L)
        assertThat(message.usage().cacheReadInputTokens()).hasValue(22L)
        assertThat(message.usage().outputTokensDetails().get().thinkingTokens()).isEqualTo(33L)
        assertThat(message.usage().serverToolUse().get().webFetchRequests()).isEqualTo(3L)
        assertThat(message.usage().serviceTier()).hasValue(Usage.ServiceTier.STANDARD)
        assertThat(message.usage().cacheCreation().get().ephemeral5mInputTokens()).isEqualTo(0L)
        assertThat(message.usage().inferenceGeo()).hasValue("inference_geo")
        assertThat(message.container()).isEmpty()
    }

    @Test
    fun accumulateContentBlocksWithDuplicateIndexes() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(textContentBlockStartEvent(0L, "0-ONE."))
        accumulator.accumulate(textContentBlockStartEvent(1L, "1-ONE."))
        accumulator.accumulate(textContentBlockDeltaEvent(0L, "0-TWO."))

        assertThatThrownBy { accumulator.accumulate(textContentBlockStartEvent(0L, "0-ONE.")) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("Content block already started for index 0.")
    }

    @Test
    fun accumulateContentBlocksStopWithoutStart() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(textContentBlockStartEvent(0L, "0-ONE."))

        assertThatThrownBy { accumulator.accumulate(contentBlockStopEvent(1L)) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("Content block not started for index 1.")
    }

    @Test
    fun accumulateContentBlocksDeltaWithoutStart() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(textContentBlockStartEvent(0L, "0-ONE."))

        assertThatThrownBy { accumulator.accumulate(textContentBlockDeltaEvent(1L, "1-TWO.")) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("Content block not started for index 1.")
    }

    @Test
    fun accumulateTextContentBlockWithTextDeltasAndCitationsDeltas() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        // Deliberately use non-zero-based and non-consecutive indexes and start the blocks out of
        // order. In the final message there should be two blocks in the indexed order, but using
        // indexes 0 and 1. While this might not be realistic, it should demonstrate the tolerance
        // of the implementation for indexes arriving out-of-order and having gaps.
        accumulator.accumulate(textContentBlockStartEvent(3L, "3-ONE."))
        accumulator.accumulate(textContentBlockStartEvent(1L, "1-ONE.", 987L))

        accumulator.accumulate(textContentBlockDeltaEvent(1L, "1-TWO."))
        accumulator.accumulate(textContentBlockDeltaEvent(3L, "3-TWO."))

        accumulator.accumulate(citationContentBlockDeltaEvent(3L, 234L))
        accumulator.accumulate(textContentBlockDeltaEvent(3L, "3-THREE."))
        accumulator.accumulate(citationContentBlockDeltaEvent(3L, 123L))

        accumulator.accumulate(textContentBlockDeltaEvent(1L, "1-THREE."))
        accumulator.accumulate(citationContentBlockDeltaEvent(1L, 654L))

        accumulator.accumulate(contentBlockStopEvent(3L))
        accumulator.accumulate(contentBlockStopEvent(1L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.END_TURN), outputTokens = 99L)
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(StopReason.END_TURN)
        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().outputTokens()).isEqualTo(99L)

        assertThat(content.size).isEqualTo(2)
        assertThat(content[0].asText().text()).isEqualTo("1-ONE.1-TWO.1-THREE.")
        assertThat(content[0].asText().citations())
            .hasValue(listOf(textCitation(987L), textCitation(654L)))

        assertThat(content[1].asText().text()).isEqualTo("3-ONE.3-TWO.3-THREE.")
        assertThat(content[1].asText().citations())
            .hasValue(listOf(textCitation(234L), textCitation(123L)))
    }

    @Test
    fun accumulateTextContentBlockWithCitationsDeltasWithoutInitialCitations() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        // A real streamed `content_block_start` payload omits the `citations` field entirely, so
        // the text block starts with `citations` missing rather than set to an empty list.
        accumulator.accumulate(
            contentBlockStartEvent(
                0L,
                ContentBlock.ofText(
                    TextBlock.builder().text("").citations(JsonMissing.of()).build()
                ),
            )
        )
        accumulator.accumulate(textContentBlockDeltaEvent(0L, "The grass is green."))
        accumulator.accumulate(citationContentBlockDeltaEvent(0L, 123L))
        accumulator.accumulate(citationContentBlockDeltaEvent(0L, 456L))
        accumulator.accumulate(contentBlockStopEvent(0L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.END_TURN), outputTokens = 9L)
        )
        accumulator.accumulate(messageStopEvent())

        val content = accumulator.message().content()

        assertThat(content.size).isEqualTo(1)
        assertThat(content[0].asText().text()).isEqualTo("The grass is green.")
        assertThat(content[0].asText().citations())
            .hasValue(listOf(textCitation(123L), textCitation(456L)))
    }

    @Test
    fun accumulateToolUseContentBlockWithoutAnyDelta() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(toolUseContentBlockStartEvent(1L, "1-TOOL."))

        // There must be at least one `content_block_delta` event before the end of the block to
        // define the input JSON string.
        assertThatThrownBy { accumulator.accumulate(contentBlockStopEvent(1L)) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("Missing input JSON for index 1.")
    }

    @Test
    fun accumulateToolUseContentBlockWithEmptyInput() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        accumulator.accumulate(toolUseContentBlockStartEvent(1L, "1-TOOL."))

        // Tool use content block deltas will build up the JSON string: {}
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, "{"))
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, "}"))

        accumulator.accumulate(contentBlockStopEvent(1L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.TOOL_USE), outputTokens = 88L)
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(StopReason.TOOL_USE)
        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().outputTokens()).isEqualTo(88L)

        assertThat(content.size).isEqualTo(1)
        assertThat(content[0].asToolUse().name()).isEqualTo("1-TOOL.")
        assertThat(content[0].asToolUse()._input().asObject().get()).isEmpty()
    }

    @Test
    fun accumulateToolUseContentBlockWithNoInputCanBeSentBackToTheApi() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(toolUseContentBlockStartEvent(1L, "1-TOOL."))
        // A tool that declares no parameters streams one empty `input_json_delta`.
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, ""))
        accumulator.accumulate(contentBlockStopEvent(1L))
        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.TOOL_USE), outputTokens = 88L)
        )
        accumulator.accumulate(messageStopEvent())

        // Appending the assistant turn back into the conversation is the normal
        // shape of a tool-use loop. `tool_use.input` is required, so a param
        // whose `input` is missing is rejected with a 400:
        //   messages.N.content.M.tool_use.input: Field required
        val toolUse = accumulator.message().toParam().content().blockParams().get()[0].asToolUse()

        assertThat(toolUse._input().asObject().get()).isEmpty()
    }

    @Test
    fun accumulateToolUseContentBlockWithNoInput() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        accumulator.accumulate(toolUseContentBlockStartEvent(1L, "1-TOOL."))

        // Tool use content block deltas will build up an empty JSON string. This behavior was
        // observed in issue #249. A tool that declares no parameters has an
        // input of `{}` — the only object it can be. Whitespace should be ignored.
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, ""))
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, " "))

        accumulator.accumulate(contentBlockStopEvent(1L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.TOOL_USE), outputTokens = 88L)
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(StopReason.TOOL_USE)
        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().outputTokens()).isEqualTo(88L)

        assertThat(content.size).isEqualTo(1)
        assertThat(content[0].asToolUse().name()).isEqualTo("1-TOOL.")
        assertThat(content[0].asToolUse()._input().asObject().get()).isEmpty()
    }

    @Test
    fun accumulateTextAndToolUseContentBlocks() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        accumulator.accumulate(textContentBlockStartEvent(3L, "3-ONE."))
        accumulator.accumulate(toolUseContentBlockStartEvent(1L, "1-TOOL."))

        // Tool use content block deltas will build up the JSON string: {"hello":"world"}
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, "{\"hel"))
        accumulator.accumulate(textContentBlockDeltaEvent(3L, "3-TWO."))

        accumulator.accumulate(citationContentBlockDeltaEvent(3L, 234L))
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, "lo\":\"wo"))
        accumulator.accumulate(textContentBlockDeltaEvent(3L, "3-THREE."))

        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, "rld\"}"))

        accumulator.accumulate(contentBlockStopEvent(3L))
        accumulator.accumulate(contentBlockStopEvent(1L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.TOOL_USE), outputTokens = 88L)
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(StopReason.TOOL_USE)
        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().outputTokens()).isEqualTo(88L)

        assertThat(content.size).isEqualTo(2)
        assertThat(content[0].asToolUse().name()).isEqualTo("1-TOOL.")
        assertThat(content[0].asToolUse()._input().asObject().get()["hello"])
            .isEqualTo(JsonString.of("world"))

        assertThat(content[1].asText().text()).isEqualTo("3-ONE.3-TWO.3-THREE.")
        assertThat(content[1].asText().citations()).hasValue(listOf(textCitation(234L)))
    }

    @Test
    fun accumulateToolUseContentBlockTruncatedByMaxTokens() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(toolUseContentBlockStartEvent(0L, "get_weather"))

        // The stream hits `max_tokens` mid tool call, so the block stops on a fragment that can
        // never become valid JSON. This is a legal stream and must still yield a final message.
        accumulator.accumulate(toolUseContentBlockDeltaEvent(0L, "{\"location\": \"San Fr"))
        assertThatNoException().isThrownBy { accumulator.accumulate(contentBlockStopEvent(0L)) }

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.MAX_TOKENS), outputTokens = 9L)
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopReason()).hasValue(StopReason.MAX_TOKENS)
        assertThat(message.usage().outputTokens()).isEqualTo(9L)

        assertThat(content.size).isEqualTo(1)
        assertThat(content[0].asToolUse().id()).isEqualTo("tool-id")
        assertThat(content[0].asToolUse().name()).isEqualTo("get_weather")
        // The unparseable fragment is dropped; `input` stays the empty object the block started as.
        assertThat(content[0].asToolUse()._input().asObject().get()).isEmpty()
    }

    @Test
    fun accumulateToolUseContentBlockWithInvalidJson() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(toolUseContentBlockStartEvent(1L, "test-tool"))

        // Build up invalid JSON string: {invalid"json}. At `content_block_stop` this is
        // indistinguishable from a fragment truncated by `max_tokens`, so it is tolerated the
        // same way rather than thrown.
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, "{invalid\""))
        accumulator.accumulate(toolUseContentBlockDeltaEvent(1L, "json}"))
        accumulator.accumulate(contentBlockStopEvent(1L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.TOOL_USE), outputTokens = 88L)
        )
        accumulator.accumulate(messageStopEvent())

        val toolUse = accumulator.message().content()[0].asToolUse()

        assertThat(toolUse.name()).isEqualTo("test-tool")
        assertThat(toolUse._input().asObject().get()).isEmpty()
    }

    @Test
    fun accumulateThinkingAndTextContentBlocks() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        accumulator.accumulate(textContentBlockStartEvent(3L, "3-ONE."))
        accumulator.accumulate(thinkingContentBlockStartEvent(1L, "1-ONE."))

        accumulator.accumulate(textContentBlockDeltaEvent(3L, "3-TWO."))
        accumulator.accumulate(thinkingContentBlockDeltaEvent(1L, "1-TWO."))

        accumulator.accumulate(citationContentBlockDeltaEvent(3L, 234L))
        accumulator.accumulate(textContentBlockDeltaEvent(3L, "3-THREE."))

        accumulator.accumulate(signatureContentBlockDeltaEvent(1L))

        accumulator.accumulate(contentBlockStopEvent(3L))
        accumulator.accumulate(contentBlockStopEvent(1L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.END_TURN), outputTokens = 88L)
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(StopReason.END_TURN)
        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().outputTokens()).isEqualTo(88L)

        assertThat(content.size).isEqualTo(2)
        assertThat(content[0].asThinking().thinking()).isEqualTo("1-ONE.1-TWO.")
        assertThat(content[0].asThinking().signature()).isEqualTo("a-signature")

        assertThat(content[1].asText().text()).isEqualTo("3-ONE.3-TWO.3-THREE.")
        assertThat(content[1].asText().citations()).hasValue(listOf(textCitation(234L)))
    }

    @Test
    fun accumulateRedactedThinkingAndTextContentBlocks() {
        val accumulator = MessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        accumulator.accumulate(textContentBlockStartEvent(3L, "3-ONE."))
        accumulator.accumulate(redactedThinkingContentBlockStartEvent(1L, "1-ONE."))

        // `redacting_thinking` content blocks never have any delta events.
        accumulator.accumulate(textContentBlockDeltaEvent(3L, "3-TWO."))

        accumulator.accumulate(citationContentBlockDeltaEvent(3L, 234L))
        accumulator.accumulate(textContentBlockDeltaEvent(3L, "3-THREE."))

        accumulator.accumulate(contentBlockStopEvent(3L))
        accumulator.accumulate(contentBlockStopEvent(1L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.END_TURN), outputTokens = 88L)
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(StopReason.END_TURN)
        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().outputTokens()).isEqualTo(88L)

        assertThat(content.size).isEqualTo(2)
        assertThat(content[0].asRedactedThinking().data()).isEqualTo("1-ONE.")

        assertThat(content[1].asText().text()).isEqualTo("3-ONE.3-TWO.3-THREE.")
        assertThat(content[1].asText().citations()).hasValue(listOf(textCitation(234L)))
    }

    @Test
    fun accumulateUnknownContentBlockAndDeltaTypes() {
        val accumulator = MessageAccumulator.create()
        val unknownBlockJson =
            """{"type":"zz_future_block","id":"zzblk_1","payload":{"level":2,"tags":["a","b"]}}"""

        accumulator.accumulate(messageStartEvent())

        accumulator.accumulate(
            contentBlockStartEvent(
                0L,
                jsonMapper().readValue(unknownBlockJson, ContentBlock::class.java),
            )
        )
        accumulator.accumulate(unknownContentBlockDeltaEvent(0L))
        accumulator.accumulate(contentBlockStopEvent(0L))

        accumulator.accumulate(textContentBlockStartEvent(1L, "ONE."))
        accumulator.accumulate(unknownContentBlockDeltaEvent(1L))
        accumulator.accumulate(textContentBlockDeltaEvent(1L, "TWO."))
        accumulator.accumulate(contentBlockStopEvent(1L))

        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(StopReason.END_TURN), outputTokens = 9L)
        )
        accumulator.accumulate(messageStopEvent())

        val content = accumulator.message().content()

        assertThat(content.size).isEqualTo(2)
        assertThat(jsonMapper().writeValueAsString(content[0])).isEqualTo(unknownBlockJson)
        assertThat(content[1].asText().text()).isEqualTo("ONE.TWO.")
    }

    @Test
    fun fixtureCreation() {
        // A quick smoke test to ensure that all the test fixture factory functions work without
        // throwing any exceptions unless expected to do so.

        // MESSAGE EVENTS
        assertThatNoException().isThrownBy { messageStartEvent() }

        assertThatNoException().isThrownBy {
            messageDeltaEvent(stopReason = JsonField.of(StopReason.END_TURN))
        }
        assertThatNoException().isThrownBy { messageDeltaEvent(outputTokens = 99) }
        assertThatNoException().isThrownBy {
            messageDeltaEvent(JsonField.of(StopReason.STOP_SEQUENCE), JsonField.of("stop sequence"))
        }
        // Expect no enforcement of requiring `stop_sequence` if `stop_reason` is `"stop_sequence"`.
        assertThatNoException().isThrownBy {
            messageDeltaEvent(JsonField.of(StopReason.STOP_SEQUENCE), stopSequence = SET_TO_NULL)
        }
        // Expect no enforcement of requiring `stop_reason` is `"stop_sequence"` when
        // `stop_sequence` is set.
        assertThatNoException().isThrownBy {
            messageDeltaEvent(stopReason = SET_TO_NULL, JsonField.of("stop sequence"))
        }
        assertThatNoException().isThrownBy {
            messageDeltaEvent(JsonField.of(StopReason.END_TURN), JsonField.of("stop sequence"))
        }

        assertThatNoException().isThrownBy { messageStopEvent() }

        // TEXT CONTENT BLOCK EVENTS (INCLUDING CITATIONS)
        assertThatNoException().isThrownBy { textContentBlockStartEvent(1L, "text") }
        assertThatNoException().isThrownBy { textContentBlockStartEvent(1L, "text", 101L) }
        assertThatNoException().isThrownBy { textContentBlockDeltaEvent(1L, "more text") }
        assertThatNoException().isThrownBy { citationContentBlockDeltaEvent(1L, 102L) }

        // TOOL USE CONTENT BLOCK EVENTS
        assertThatNoException().isThrownBy { toolUseContentBlockStartEvent(1L) }
        assertThatNoException().isThrownBy { toolUseContentBlockDeltaEvent(1L, "{") }

        // THINKING CONTENT BLOCK EVENTS (INCLUDING SIGNATURES)
        assertThatNoException().isThrownBy { thinkingContentBlockStartEvent(1L) }
        assertThatNoException().isThrownBy { thinkingContentBlockDeltaEvent(1L, "[thought-2]") }
        assertThatNoException().isThrownBy { signatureContentBlockDeltaEvent(1L) }

        // REDACTED THINKING CONTENT BLOCK EVENTS
        assertThatNoException().isThrownBy { redactedThinkingContentBlockStartEvent(1L, null) }
        assertThatNoException().isThrownBy { redactedThinkingContentBlockStartEvent(1L, "data") }

        // COMMON CONTENT BLOCK STOP EVENT
        assertThatNoException().isThrownBy { contentBlockStopEvent(1L) }
    }

    // --------------------------------------------------------------------------------------------
    // In all the following test fixture factory functions, the `type` property (where relevant) is
    // not set explicitly, as it always has an appropriate non-null default value.

    private fun messageStartEvent(usage: Usage = usage(INPUT_TOKENS)) =
        RawMessageStreamEvent.ofMessageStart(
            RawMessageStartEvent.builder()
                .message(
                    Message.builder()
                        .id("message-id")
                        .model(Model.CLAUDE_SONNET_4_5)
                        .content(listOf())
                        .usage(usage)
                        // `stopReason()` and `stopSequence()` must be set explicitly or an error
                        // will occur, but there is no appropriate value other than an explicit "not
                        // set" for a `message_start` event. The final values will be notified in
                        // one or more later `message_delta` events.
                        .stopDetails(NOT_SET)
                        .stopReason(NOT_SET)
                        .stopSequence(NOT_SET)
                        // The default non-null value for `role` suffices.
                        .build()
                )
                .build()
        )

    /**
     * Creates a `message_delta` event that can set the `stop_reason` and the `stop_sequence`, and
     * increment the count of `output_tokens`.
     *
     * When all `message_delta` events have been accumulated, the `stop_reason` should be set to a
     * non-`null` value to emulate what happens in real-world scenarios. The `stop_sequence` should
     * be set to a non-`null` value if the `stop_reason` is `"stop_sequence"`. None of these
     * expectations are enforced either here or in the Message API.
     *
     * @param stopReason The default value is `NOT_SET` (`JsonMissing`), which is interpreted as the
     *   field not being set, not even to `null`; when the event is accumulated with the message,
     *   any existing value of the stop reason will remain unchanged. Use `SET_TO_NULL` to
     *   explicitly set the field to have a `null` (really `JsonNull`) value, when the event is
     *   accumulated with the message, any existing value of the stop reason will be overwritten
     *   with `null`. Otherwise, set the value to the required stop reason; when the event is
     *   accumulated with the message, any existing value of the stop reason will be replaced with
     *   the new value.
     * @param stopSequence The behavior follows the pattern of [stopReason].
     * @param outputTokens The number of output tokens to _add_ to the current count of output
     *   tokens already accumulated by the message.
     */
    private fun messageDeltaEvent(
        stopReason: JsonField<StopReason> = NOT_SET,
        stopSequence: JsonField<String> = NOT_SET,
        outputTokens: Long = 0L,
        inputTokens: Long = INPUT_TOKENS,
        cacheCreationInputTokens: Long = 0L,
        cacheReadInputTokens: Long = 0L,
        webSearchRequests: Long = 0L,
    ) =
        RawMessageStreamEvent.ofMessageDelta(
            RawMessageDeltaEvent.builder()
                .delta(
                    RawMessageDeltaEvent.Delta.builder()
                        .stopDetails(NOT_SET)
                        .stopReason(stopReason)
                        .stopSequence(stopSequence)
                        .build()
                )
                .usage(
                    MessageDeltaUsage.builder()
                        .outputTokens(outputTokens)
                        .outputTokensDetails(
                            OutputTokensDetails.builder().thinkingTokens(0L).build()
                        )
                        .cacheCreationInputTokens(cacheCreationInputTokens)
                        .cacheReadInputTokens(cacheReadInputTokens)
                        .inputTokens(inputTokens)
                        .serverToolUse(
                            ServerToolUsage.builder()
                                .webFetchRequests(0L)
                                .webSearchRequests(webSearchRequests)
                                .build()
                        )
                        .build()
                )
                .build()
        )

    private fun messageStopEvent() =
        RawMessageStreamEvent.ofMessageStop(RawMessageStopEvent.builder().build())

    /**
     * @param citationPageNumber Omit (or use `null`) to create a text content block without any
     *   citations.
     */
    private fun textContentBlockStartEvent(
        index: Long,
        text: String,
        citationPageNumber: Long? = null,
    ) =
        contentBlockStartEvent(
            index,
            ContentBlock.ofText(
                TextBlock.builder()
                    .text(text)
                    .apply {
                        // `citations` cannot remain `null` when `build()` is called, so set the "no
                        // citations" state explicitly with an empty list if needed.
                        citationPageNumber?.let {
                            addCitation(
                                TextCitation.ofPageLocation(
                                    citationPageLocation(citationPageNumber)
                                )
                            )
                        } ?: citations(listOf())
                    }
                    .build()
            ),
        )

    private fun textContentBlockDeltaEvent(index: Long, text: String) =
        contentBlockDeltaEvent(index, textDelta(text))

    /**
     * Creates a citation content block delta event. This can be used to add a citation to a text
     * content block and should be used between [textContentBlockStartEvent] and its corresponding
     * [contentBlockStopEvent].
     */
    private fun citationContentBlockDeltaEvent(index: Long, pageNumber: Long) =
        contentBlockDeltaEvent(index, citationsDelta(pageNumber))

    private fun toolUseContentBlockStartEvent(index: Long, name: String = "tool-name") =
        contentBlockStartEvent(
            index,
            ContentBlock.ofToolUse(
                ToolUseBlock.builder()
                    .id("tool-id")
                    .caller(DirectCaller.builder().build())
                    .name(name)
                    .input(SET_TO_NULL)
                    .build()
            ),
        )

    private fun toolUseContentBlockDeltaEvent(index: Long, partialJson: String) =
        contentBlockDeltaEvent(index, InputJsonDelta.builder().partialJson(partialJson).build())

    private fun thinkingContentBlockStartEvent(index: Long, thinking: String = "[thought-1") =
        contentBlockStartEvent(
            index,
            ContentBlock.ofThinking(
                ThinkingBlock.builder()
                    .thinking(thinking)
                    // No signature is set for the `content_block_start` event. A single
                    // `content_block_delta` event, just before the `content_block_stop` event will
                    // provide the final signature.
                    .signature(SET_TO_NULL)
                    .build()
            ),
        )

    private fun thinkingContentBlockDeltaEvent(index: Long, thinking: String) =
        contentBlockDeltaEvent(index, thinkingDelta(thinking))

    /**
     * Creates a `redacted_thinking` content block start event. This type of content block will
     * never have any associated content block delta events.
     */
    private fun redactedThinkingContentBlockStartEvent(index: Long, data: String?) =
        contentBlockStartEvent(
            index,
            ContentBlock.ofRedactedThinking(
                RedactedThinkingBlock.builder().data(JsonField.ofNullable(data)).build()
            ),
        )

    /**
     * Creates a signature content block delta event. This can be used to add a signature to a
     * `tool_use` content block and should be used between [toolUseContentBlockStartEvent] and its
     * corresponding [contentBlockStopEvent].
     */
    private fun signatureContentBlockDeltaEvent(index: Long) =
        contentBlockDeltaEvent(index, signatureDelta("a-signature"))

    private fun unknownContentBlockDeltaEvent(index: Long) =
        contentBlockDeltaEvent(
            index,
            jsonMapper()
                .readValue(
                    """{"type":"zz_future_delta","fragment":"x"}""",
                    RawContentBlockDelta::class.java,
                ),
        )

    private fun contentBlockStartEvent(index: Long, contentBlock: ContentBlock) =
        RawMessageStreamEvent.ofContentBlockStart(
            RawContentBlockStartEvent.builder().index(index).contentBlock(contentBlock).build()
        )

    private fun contentBlockDeltaEvent(index: Long, delta: Any) =
        RawMessageStreamEvent.ofContentBlockDelta(
            RawContentBlockDeltaEvent.builder()
                .index(index)
                .apply {
                    when (delta) {
                        is RawContentBlockDelta -> delta(delta)
                        is TextDelta -> delta(delta)
                        is CitationsDelta -> delta(delta)
                        is InputJsonDelta -> delta(delta)
                        is ThinkingDelta -> delta(delta)
                        is SignatureDelta -> delta(delta)
                        // There are no delta events for `redacted_thinking` content blocks.
                        else ->
                            throw IllegalArgumentException(
                                "Unknown delta type ${delta::class.java}"
                            )
                    }
                }
                .build()
        )

    // One type of `content_block_stop` event that is common to all types of content blocks.
    private fun contentBlockStopEvent(index: Long) =
        RawMessageStreamEvent.ofContentBlockStop(
            RawContentBlockStopEvent.builder().index(index).build()
        )

    private fun usage(inputTokens: Long) =
        Usage.builder()
            .inputTokens(inputTokens)
            .cacheCreation(
                CacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
            .cacheCreationInputTokens(0L)
            .cacheReadInputTokens(0L)
            .inferenceGeo("inference_geo")
            .outputTokens(0L)
            .outputTokensDetails(OutputTokensDetails.builder().thinkingTokens(0L).build())
            .serverToolUse(
                ServerToolUsage.builder().webFetchRequests(0L).webSearchRequests(0L).build()
            )
            .serviceTier(Usage.ServiceTier.STANDARD)
            .build()

    private fun textDelta(text: String) = TextDelta.builder().text(text).build()

    private fun citationsDelta(pageNumber: Long) =
        CitationsDelta.builder().citation(citationPageLocation(pageNumber)).build()

    private fun citationPageLocation(pageNumber: Long) =
        CitationPageLocation.builder()
            .fileId("fileId")
            .documentTitle("Document Title")
            .documentIndex(11L)
            .citedText("cited text")
            .startPageNumber(pageNumber)
            .endPageNumber(pageNumber)
            .build()

    private fun citationContentBlockLocation(blockIndex: Long) =
        CitationContentBlockLocation.builder()
            .fileId("fileId")
            .documentTitle("Document Title")
            .documentIndex(11L)
            .citedText("cited text")
            .startBlockIndex(blockIndex)
            .endBlockIndex(blockIndex)
            .build()

    private fun citationCharLocation(charIndex: Long) =
        CitationCharLocation.builder()
            .fileId("fileId")
            .documentTitle("Document Title")
            .documentIndex(11L)
            .citedText("cited text")
            .startCharIndex(charIndex)
            .endCharIndex(charIndex)
            .build()

    private fun textCitation(pageNumber: Long) =
        TextCitation.ofPageLocation(citationPageLocation(pageNumber))

    private fun thinkingDelta(thinking: String) = ThinkingDelta.builder().thinking(thinking).build()

    private fun signatureDelta(signature: String) =
        SignatureDelta.builder().signature(signature).build()

    private fun textBlock(text: String) = TextBlock.builder().text(text).citations(listOf()).build()

    private fun thinkingBlock(thinking: String = "[thinking]", signature: String = "[signature]") =
        ThinkingBlock.builder().thinking(thinking).signature(signature).build()
}
