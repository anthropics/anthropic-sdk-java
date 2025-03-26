package com.anthropic.helpers

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonNull
import com.anthropic.core.JsonString
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.messages.*
import com.anthropic.models.beta.messages.BetaRawContentBlockStartEvent.ContentBlock
import com.anthropic.models.messages.Model
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class BetaMessageAccumulatorTest {
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
            BetaMessageAccumulator.mergeMessageUsage(
                usage(INPUT_TOKENS),
                BetaMessageDeltaUsage.builder().outputTokens(44L).build(),
            )
        val usage2 =
            BetaMessageAccumulator.mergeMessageUsage(
                usage1,
                BetaMessageDeltaUsage.builder().outputTokens(11L).build(),
            )

        assertThat(usage1.inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(usage1.outputTokens()).isEqualTo(44L)

        assertThat(usage2.inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(usage2.outputTokens()).isEqualTo(44L + 11L)
    }

    @Test
    fun mergeTextDeltaWrongBlockType() {
        assertThatThrownBy {
                BetaMessageAccumulator.mergeTextDelta(
                    com.anthropic.models.beta.messages.BetaContentBlock.ofThinking(thinkingBlock()),
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
            BetaMessageAccumulator.mergeTextDelta(
                com.anthropic.models.beta.messages.BetaContentBlock.ofText(
                    textBlock("hello")
                        .toBuilder()
                        .addCitation(citationPageLocation(123L))
                        .addCitation(citationPageLocation(456L))
                        .build()
                ),
                textDelta(" world"),
            )
        val text2 = BetaMessageAccumulator.mergeTextDelta(text1, textDelta("!!!"))
        val citations2 = text2.asText().citations().get()

        assertThat(text1.isText()).isTrue()
        assertThat(text1.text().get().text()).isEqualTo("hello world")

        assertThat(text2.isText()).isTrue()
        assertThat(text2.text().get().text()).isEqualTo("hello world!!!")

        assertThat(citations2.size).isEqualTo(2)
        assertThat(citations2[0].citationPageLocation().get().startPageNumber()).isEqualTo(123L)
        assertThat(citations2[1].citationPageLocation().get().startPageNumber()).isEqualTo(456L)
    }

    @Test
    fun mergeCitationsDeltaWrongBlockType() {
        assertThatThrownBy {
                BetaMessageAccumulator.mergeCitationsDelta(
                    com.anthropic.models.beta.messages.BetaContentBlock.ofThinking(thinkingBlock()),
                    citationsDelta(123L),
                )
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Content block is not a text block.")
    }

    @Test
    fun mergeCitationsDelta() {
        MessageAccumulator
        // Use all types of citation to exercise the code in [citationsDeltaToTextCitation].
        val text1 =
            BetaMessageAccumulator.mergeCitationsDelta(
                com.anthropic.models.beta.messages.BetaContentBlock.ofText(textBlock("hello")),
                citationsDelta(123L),
            )
        val text2 = BetaMessageAccumulator.mergeCitationsDelta(text1, citationsDelta(456L))
        val text3 =
            BetaMessageAccumulator.mergeCitationsDelta(
                text2,
                BetaCitationsDelta.builder().citation(citationCharLocation(789L)).build(),
            )
        val text4 =
            BetaMessageAccumulator.mergeCitationsDelta(
                text3,
                BetaCitationsDelta.builder().citation(citationContentBlockLocation(890L)).build(),
            )
        val citations4 = text4.asText().citations().get()

        assertThat(text1.isText()).isTrue()
        assertThat(text1.text().get().text()).isEqualTo("hello")

        assertThat(text2.isText()).isTrue()
        assertThat(text2.text().get().text()).isEqualTo("hello")

        assertThat(citations4.size).isEqualTo(4)
        assertThat(citations4[0].citationPageLocation().get().startPageNumber()).isEqualTo(123L)
        assertThat(citations4[1].citationPageLocation().get().startPageNumber()).isEqualTo(456L)
        assertThat(citations4[2].citationCharLocation().get().startCharIndex()).isEqualTo(789L)
        assertThat(citations4[3].citationContentBlockLocation().get().startBlockIndex())
            .isEqualTo(890L)
    }

    @Test
    fun mergeThinkingDeltaWrongBlockType() {
        assertThatThrownBy {
                BetaMessageAccumulator.mergeThinkingDelta(
                    com.anthropic.models.beta.messages.BetaContentBlock.ofText(textBlock("hello")),
                    thinkingDelta("hmm...let me think..."),
                )
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Content block is not a thinking block.")
    }

    @Test
    fun mergeThinkingDelta() {
        val thinking1 =
            BetaMessageAccumulator.mergeThinkingDelta(
                com.anthropic.models.beta.messages.BetaContentBlock.ofThinking(
                    thinkingBlock("Let me see...", "sig-1")
                ),
                thinkingDelta(" Nope."),
            )
        val thinking2 =
            BetaMessageAccumulator.mergeThinkingDelta(thinking1, thinkingDelta(" Not a clue."))

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
                BetaMessageAccumulator.mergeSignatureDelta(
                    com.anthropic.models.beta.messages.BetaContentBlock.ofText(
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
            BetaMessageAccumulator.mergeSignatureDelta(
                com.anthropic.models.beta.messages.BetaContentBlock.ofThinking(
                    thinkingBlock("Hmm...", "sig-1")
                ),
                signatureDelta("sig-2"),
            )
        val thinking2 =
            BetaMessageAccumulator.mergeSignatureDelta(thinking1, signatureDelta("sig-3"))

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
        val accumulator = BetaMessageAccumulator.create()

        assertThatThrownBy { accumulator.message() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("'message_stop' event not yet received.")
    }

    @Test
    fun messageNotStopped() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        assertThatThrownBy { accumulator.message() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("'message_stop' event not yet received.")
    }

    @Test
    fun accumulateStopEventBeforeStarted() {
        val accumulator = BetaMessageAccumulator.create()

        assertThatThrownBy { accumulator.accumulate(messageStopEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_start' event not received.")
    }

    @Test
    fun accumulateDeltaEventBeforeStarted() {
        val accumulator = BetaMessageAccumulator.create()

        assertThatThrownBy { accumulator.accumulate(messageDeltaEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_start' event not received.")
    }

    @Test
    fun accumulateStartEventAfterStart() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())

        assertThatThrownBy { accumulator.accumulate(messageStartEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_start' event already received.")
    }

    @Test
    fun accumulateStopEventAfterStopped() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        assertThatThrownBy { accumulator.accumulate(messageStopEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_stop' event already received.")
    }

    @Test
    fun accumulateStartEventAfterStopped() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        assertThatThrownBy { accumulator.accumulate(messageStartEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_stop' event already received.")
    }

    @Test
    fun accumulateDeltaEventAfterStopped() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        assertThatThrownBy { accumulator.accumulate(messageDeltaEvent()) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("'message_stop' event already received.")
    }

    @Test
    fun messageWithNoDeltasOrContent() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        assertThatNoException().isThrownBy { accumulator.message() }

        val message = accumulator.message()

        assertThat(message.id()).isEqualTo("message-id")
        assertThat(message.model()).isEqualTo(Model.CLAUDE_3_5_SONNET_LATEST)

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
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(
            messageDeltaEvent(JsonField.of(BetaStopReason.END_TURN), outputTokens = 96L)
        )
        accumulator.accumulate(messageStopEvent())

        assertThatNoException().isThrownBy { accumulator.message() }

        val message = accumulator.message()

        assertThat(message.id()).isEqualTo("message-id")
        assertThat(message.model()).isEqualTo(Model.CLAUDE_3_5_SONNET_LATEST)

        assertThat(message.content()).isEmpty()

        assertThat(message.stopReason()).hasValue(BetaStopReason.END_TURN)
        assertThat(message.stopSequence()).isNotPresent()

        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().cacheCreationInputTokens()).hasValue(0L)
        assertThat(message.usage().cacheReadInputTokens()).hasValue(0L)
        assertThat(message.usage().outputTokens()).isEqualTo(96L)
    }

    @Test
    fun messageBetaStopReasonDefaultsToMissing() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageStopEvent())

        // Check both the non-public "raw" JSON value and the public `Optional` value (which will
        // be `null` if the "raw" value is missing).
        assertThat(accumulator.message()._stopReason().isMissing()).isEqualTo(true)
        assertThat(accumulator.message().stopReason()).isEmpty()
    }

    @Test
    fun messageDeltaWithNullBetaStopReason() {
        // The default stop reason is `JsonMissing`. See if an explicit `null` works instead and
        // that an explicit `JsonMissing` does not override the `null`.
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageDeltaEvent(stopReason = SET_TO_NULL))
        accumulator.accumulate(messageDeltaEvent(stopReason = NOT_SET)) // Should be ignored.
        accumulator.accumulate(messageStopEvent())

        assertThat(accumulator.message()._stopReason().isMissing()).isEqualTo(false)
        assertThat(accumulator.message()._stopReason().isNull()).isEqualTo(true)
        assertThat(accumulator.message().stopReason()).isEmpty()
    }

    @Test
    fun messageDeltaWithNonNullBetaStopReason() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        // The last non-missing value should "win".
        accumulator.accumulate(messageDeltaEvent(stopReason = SET_TO_NULL))
        accumulator.accumulate(messageDeltaEvent(stopReason = NOT_SET)) // Should be ignored.
        accumulator.accumulate(
            messageDeltaEvent(stopReason = JsonField.of(BetaStopReason.END_TURN))
        )
        accumulator.accumulate(messageStopEvent())

        assertThat(accumulator.message()._stopReason().isMissing()).isEqualTo(false)
        assertThat(accumulator.message()._stopReason().isNull()).isEqualTo(false)
        assertThat(accumulator.message().stopReason()).hasValue(BetaStopReason.END_TURN)
    }

    @Test
    fun messageStopSequenceDefaultsToMissing() {
        val accumulator = BetaMessageAccumulator.create()

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
        val accumulator = BetaMessageAccumulator.create()

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
        val accumulator = BetaMessageAccumulator.create()

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
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(messageDeltaEvent(outputTokens = 11L))
        accumulator.accumulate(messageDeltaEvent(outputTokens = 12L))
        accumulator.accumulate(messageDeltaEvent(outputTokens = 13L))
        accumulator.accumulate(messageStopEvent())

        assertThat(accumulator.message().usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(accumulator.message().usage().outputTokens()).isEqualTo(11L + 12L + 13L)
    }

    @Test
    fun accumulateContentBlocksWithDuplicateIndexes() {
        val accumulator = BetaMessageAccumulator.create()

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
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(textContentBlockStartEvent(0L, "0-ONE."))

        assertThatThrownBy { accumulator.accumulate(contentBlockStopEvent(1L)) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("Content block not started for index 1.")
    }

    @Test
    fun accumulateContentBlocksDeltaWithoutStart() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(textContentBlockStartEvent(0L, "0-ONE."))

        assertThatThrownBy { accumulator.accumulate(textContentBlockDeltaEvent(1L, "1-TWO.")) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("Content block not started for index 1.")
    }

    @Test
    fun accumulateTextContentBlockWithTextDeltasAndCitationsDeltas() {
        val accumulator = BetaMessageAccumulator.create()

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
            messageDeltaEvent(
                stopReason = JsonField.of(BetaStopReason.END_TURN),
                outputTokens = 99L,
            )
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(BetaStopReason.END_TURN)
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
    fun accumulateToolUseContentBlockWithoutAnyDelta() {
        val accumulator = BetaMessageAccumulator.create()

        accumulator.accumulate(messageStartEvent())
        accumulator.accumulate(toolUseContentBlockStartEvent(1L, "1-TOOL."))

        // There must be at least one `content_block_delta` event before the end of the block to
        // define the input JSON string.
        assertThatThrownBy { accumulator.accumulate(contentBlockStopEvent(1L)) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessage("Missing input JSON for index 1.")
    }

    @Test
    fun accumulateTextAndToolUseContentBlocks() {
        val accumulator = BetaMessageAccumulator.create()

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
            messageDeltaEvent(
                stopReason = JsonField.of(BetaStopReason.TOOL_USE),
                outputTokens = 88L,
            )
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(BetaStopReason.TOOL_USE)
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
    fun accumulateThinkingAndTextContentBlocks() {
        val accumulator = BetaMessageAccumulator.create()

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
            messageDeltaEvent(
                stopReason = JsonField.of(BetaStopReason.END_TURN),
                outputTokens = 88L,
            )
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(BetaStopReason.END_TURN)
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
        val accumulator = BetaMessageAccumulator.create()

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
            messageDeltaEvent(
                stopReason = JsonField.of(BetaStopReason.END_TURN),
                outputTokens = 88L,
            )
        )
        accumulator.accumulate(messageStopEvent())

        val message = accumulator.message()
        val content = message.content()

        assertThat(message.stopSequence()).isEmpty()
        assertThat(message.stopReason()).hasValue(BetaStopReason.END_TURN)
        assertThat(message.usage().inputTokens()).isEqualTo(INPUT_TOKENS)
        assertThat(message.usage().outputTokens()).isEqualTo(88L)

        assertThat(content.size).isEqualTo(2)
        assertThat(content[0].asRedactedThinking().data()).isEqualTo("1-ONE.")

        assertThat(content[1].asText().text()).isEqualTo("3-ONE.3-TWO.3-THREE.")
        assertThat(content[1].asText().citations()).hasValue(listOf(textCitation(234L)))
    }

    @Test
    fun fixtureCreation() {
        // A quick smoke test to ensure that all the test fixture factory functions work without
        // throwing any exceptions unless expected to do so.

        // MESSAGE EVENTS
        assertThatNoException().isThrownBy { messageStartEvent() }

        assertThatNoException().isThrownBy {
            messageDeltaEvent(stopReason = JsonField.of(BetaStopReason.END_TURN))
        }
        assertThatNoException().isThrownBy { messageDeltaEvent(outputTokens = 99) }
        assertThatNoException().isThrownBy {
            messageDeltaEvent(
                JsonField.of(BetaStopReason.STOP_SEQUENCE),
                JsonField.of("stop sequence"),
            )
        }
        // Expect no enforcement of requiring `stop_sequence` if `stop_reason` is `"stop_sequence"`.
        assertThatNoException().isThrownBy {
            messageDeltaEvent(
                JsonField.of(BetaStopReason.STOP_SEQUENCE),
                stopSequence = SET_TO_NULL,
            )
        }
        // Expect no enforcement of requiring `stop_reason` is `"stop_sequence"` when
        // `stop_sequence` is set.
        assertThatNoException().isThrownBy {
            messageDeltaEvent(stopReason = SET_TO_NULL, JsonField.of("stop sequence"))
        }
        assertThatNoException().isThrownBy {
            messageDeltaEvent(JsonField.of(BetaStopReason.END_TURN), JsonField.of("stop sequence"))
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

    private fun messageStartEvent() =
        BetaRawMessageStreamEvent.ofStart(
            BetaRawMessageStartEvent.builder()
                .message(
                    BetaMessage.builder()
                        .id("message-id")
                        .model(Model.CLAUDE_3_5_SONNET_LATEST)
                        .content(listOf())
                        .usage(usage(INPUT_TOKENS))
                        // `stopReason()` and `stopSequence()` must be set explicitly or an error
                        // will occur, but there is no appropriate value other than an explicit "not
                        // set" for a `message_start` event. The final values will be notified in
                        // one or more later `message_delta` events.
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
        stopReason: JsonField<BetaStopReason> = NOT_SET,
        stopSequence: JsonField<String> = NOT_SET,
        outputTokens: Long = 0L,
    ) =
        BetaRawMessageStreamEvent.ofDelta(
            BetaRawMessageDeltaEvent.builder()
                .delta(
                    BetaRawMessageDeltaEvent.Delta.builder()
                        .stopReason(stopReason)
                        .stopSequence(stopSequence)
                        .build()
                )
                .usage(BetaMessageDeltaUsage.builder().outputTokens(outputTokens).build())
                .build()
        )

    private fun messageStopEvent() =
        BetaRawMessageStreamEvent.ofStop(BetaRawMessageStopEvent.builder().build())

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
            ContentBlock.ofBetaText(
                BetaTextBlock.builder()
                    .text(text)
                    .apply {
                        // `citations` cannot remain `null` when `build()` is called, so set the "no
                        // citations" state explicitly with an empty list if needed.
                        citationPageNumber?.let {
                            addCitation(
                                BetaTextCitation.ofCitationPageLocation(
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
            ContentBlock.ofBetaToolUse(
                BetaToolUseBlock.builder().id("tool-id").name(name).input(SET_TO_NULL).build()
            ),
        )

    private fun toolUseContentBlockDeltaEvent(index: Long, partialJson: String) =
        contentBlockDeltaEvent(index, BetaInputJsonDelta.builder().partialJson(partialJson).build())

    private fun thinkingContentBlockStartEvent(index: Long, thinking: String = "[thought-1") =
        contentBlockStartEvent(
            index,
            ContentBlock.ofBetaThinking(
                BetaThinkingBlock.builder()
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
            ContentBlock.ofBetaRedactedThinking(
                BetaRedactedThinkingBlock.builder().data(JsonField.ofNullable(data)).build()
            ),
        )

    /**
     * Creates a signature content block delta event. This can be used to add a signature to a
     * `tool_use` content block and should be used between [toolUseContentBlockStartEvent] and its
     * corresponding [contentBlockStopEvent].
     */
    private fun signatureContentBlockDeltaEvent(index: Long) =
        contentBlockDeltaEvent(index, signatureDelta("a-signature"))

    private fun contentBlockStartEvent(index: Long, contentBlock: ContentBlock) =
        BetaRawMessageStreamEvent.ofContentBlockStart(
            BetaRawContentBlockStartEvent.builder().index(index).contentBlock(contentBlock).build()
        )

    private fun contentBlockDeltaEvent(index: Long, delta: Any) =
        BetaRawMessageStreamEvent.ofContentBlockDelta(
            BetaRawContentBlockDeltaEvent.builder()
                .index(index)
                .apply {
                    when (delta) {
                        is BetaTextDelta -> delta(delta)
                        is BetaCitationsDelta -> delta(delta)
                        is BetaInputJsonDelta -> delta(delta)
                        is BetaThinkingDelta -> delta(delta)
                        is BetaSignatureDelta -> delta(delta)
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
        BetaRawMessageStreamEvent.ofContentBlockStop(
            BetaRawContentBlockStopEvent.builder().index(index).build()
        )

    private fun usage(inputTokens: Long) =
        BetaUsage.builder()
            .inputTokens(inputTokens)
            .cacheCreationInputTokens(0L)
            .cacheReadInputTokens(0L)
            .outputTokens(0L)
            .build()

    private fun textDelta(text: String) = BetaTextDelta.builder().text(text).build()

    private fun citationsDelta(pageNumber: Long) =
        BetaCitationsDelta.builder().citation(citationPageLocation(pageNumber)).build()

    private fun citationPageLocation(pageNumber: Long) =
        BetaCitationPageLocation.builder()
            .documentTitle("Document Title")
            .documentIndex(11L)
            .citedText("cited text")
            .startPageNumber(pageNumber)
            .endPageNumber(pageNumber)
            .build()

    private fun citationContentBlockLocation(blockIndex: Long) =
        BetaCitationContentBlockLocation.builder()
            .documentTitle("Document Title")
            .documentIndex(11L)
            .citedText("cited text")
            .startBlockIndex(blockIndex)
            .endBlockIndex(blockIndex)
            .build()

    private fun citationCharLocation(charIndex: Long) =
        BetaCitationCharLocation.builder()
            .documentTitle("Document Title")
            .documentIndex(11L)
            .citedText("cited text")
            .startCharIndex(charIndex)
            .endCharIndex(charIndex)
            .build()

    private fun textCitation(pageNumber: Long) =
        BetaTextCitation.ofCitationPageLocation(citationPageLocation(pageNumber))

    private fun thinkingDelta(thinking: String) =
        BetaThinkingDelta.builder().thinking(thinking).build()

    private fun signatureDelta(signature: String) =
        BetaSignatureDelta.builder().signature(signature).build()

    private fun textBlock(text: String) =
        BetaTextBlock.builder().text(text).citations(listOf()).build()

    private fun thinkingBlock(thinking: String = "[thinking]", signature: String = "[signature]") =
        BetaThinkingBlock.builder().thinking(thinking).signature(signature).build()
}
