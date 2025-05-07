// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaRawMessageStreamEventTest {

    @Test
    fun ofStart() {
        val start =
            BetaRawMessageStartEvent.builder()
                .message(
                    BetaMessage.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .addContent(
                            BetaTextBlock.builder()
                                .addCitation(
                                    BetaCitationCharLocation.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("document_title")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .text("Hi! My name is Claude.")
                                .build()
                        )
                        .model(Model.CLAUDE_3_7_SONNET_LATEST)
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence(null)
                        .usage(
                            BetaUsage.builder()
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    BetaServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofStart(start)

        assertThat(betaRawMessageStreamEvent.start()).contains(start)
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofStart(
                BetaRawMessageStartEvent.builder()
                    .message(
                        BetaMessage.builder()
                            .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                            .addContent(
                                BetaTextBlock.builder()
                                    .addCitation(
                                        BetaCitationCharLocation.builder()
                                            .citedText("cited_text")
                                            .documentIndex(0L)
                                            .documentTitle("document_title")
                                            .endCharIndex(0L)
                                            .startCharIndex(0L)
                                            .build()
                                    )
                                    .text("Hi! My name is Claude.")
                                    .build()
                            )
                            .model(Model.CLAUDE_3_7_SONNET_LATEST)
                            .stopReason(BetaStopReason.END_TURN)
                            .stopSequence(null)
                            .usage(
                                BetaUsage.builder()
                                    .cacheCreationInputTokens(2051L)
                                    .cacheReadInputTokens(2051L)
                                    .inputTokens(2095L)
                                    .outputTokens(503L)
                                    .serverToolUse(
                                        BetaServerToolUsage.builder().webSearchRequests(0L).build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageStreamEvent),
                jacksonTypeRef<BetaRawMessageStreamEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageStreamEvent).isEqualTo(betaRawMessageStreamEvent)
    }

    @Test
    fun ofDelta() {
        val delta =
            BetaRawMessageDeltaEvent.builder()
                .delta(
                    BetaRawMessageDeltaEvent.Delta.builder()
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(
                    BetaMessageDeltaUsage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .serverToolUse(BetaServerToolUsage.builder().webSearchRequests(0L).build())
                        .build()
                )
                .build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofDelta(delta)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).contains(delta)
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofDelta(
                BetaRawMessageDeltaEvent.builder()
                    .delta(
                        BetaRawMessageDeltaEvent.Delta.builder()
                            .stopReason(BetaStopReason.END_TURN)
                            .stopSequence("stop_sequence")
                            .build()
                    )
                    .usage(
                        BetaMessageDeltaUsage.builder()
                            .cacheCreationInputTokens(2051L)
                            .cacheReadInputTokens(2051L)
                            .inputTokens(2095L)
                            .outputTokens(503L)
                            .serverToolUse(
                                BetaServerToolUsage.builder().webSearchRequests(0L).build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageStreamEvent),
                jacksonTypeRef<BetaRawMessageStreamEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageStreamEvent).isEqualTo(betaRawMessageStreamEvent)
    }

    @Test
    fun ofStop() {
        val stop = BetaRawMessageStopEvent.builder().build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofStop(stop)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).contains(stop)
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofStop(BetaRawMessageStopEvent.builder().build())

        val roundtrippedBetaRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageStreamEvent),
                jacksonTypeRef<BetaRawMessageStreamEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageStreamEvent).isEqualTo(betaRawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockStart() {
        val contentBlockStart =
            BetaRawContentBlockStartEvent.builder()
                .contentBlock(
                    BetaTextBlock.builder()
                        .addCitation(
                            BetaCitationCharLocation.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("document_title")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("text")
                        .build()
                )
                .index(0L)
                .build()

        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockStart(contentBlockStart)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).contains(contentBlockStart)
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofContentBlockStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockStart(
                BetaRawContentBlockStartEvent.builder()
                    .contentBlock(
                        BetaTextBlock.builder()
                            .addCitation(
                                BetaCitationCharLocation.builder()
                                    .citedText("cited_text")
                                    .documentIndex(0L)
                                    .documentTitle("document_title")
                                    .endCharIndex(0L)
                                    .startCharIndex(0L)
                                    .build()
                            )
                            .text("text")
                            .build()
                    )
                    .index(0L)
                    .build()
            )

        val roundtrippedBetaRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageStreamEvent),
                jacksonTypeRef<BetaRawMessageStreamEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageStreamEvent).isEqualTo(betaRawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockDelta() {
        val contentBlockDelta =
            BetaRawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()

        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockDelta(contentBlockDelta)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).contains(contentBlockDelta)
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofContentBlockDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockDelta(
                BetaRawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()
            )

        val roundtrippedBetaRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageStreamEvent),
                jacksonTypeRef<BetaRawMessageStreamEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageStreamEvent).isEqualTo(betaRawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockStop() {
        val contentBlockStop = BetaRawContentBlockStopEvent.builder().index(0L).build()

        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockStop(contentBlockStop)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).contains(contentBlockStop)
    }

    @Test
    fun ofContentBlockStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockStop(
                BetaRawContentBlockStopEvent.builder().index(0L).build()
            )

        val roundtrippedBetaRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageStreamEvent),
                jacksonTypeRef<BetaRawMessageStreamEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageStreamEvent).isEqualTo(betaRawMessageStreamEvent)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaRawMessageStreamEvent =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaRawMessageStreamEvent>())

        val e = assertThrows<AnthropicInvalidDataException> { betaRawMessageStreamEvent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
