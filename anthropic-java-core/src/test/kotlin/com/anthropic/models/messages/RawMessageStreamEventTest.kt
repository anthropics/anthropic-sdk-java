// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class RawMessageStreamEventTest {

    @Test
    fun ofStart() {
        val start =
            RawMessageStartEvent.builder()
                .message(
                    Message.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .addContent(
                            TextBlock.builder()
                                .addCitation(
                                    CitationCharLocation.builder()
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
                        .stopReason(StopReason.END_TURN)
                        .stopSequence(null)
                        .usage(
                            Usage.builder()
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    ServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofStart(start)

        assertThat(rawMessageStreamEvent.start()).contains(start)
        assertThat(rawMessageStreamEvent.delta()).isEmpty
        assertThat(rawMessageStreamEvent.stop()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofStart(
                RawMessageStartEvent.builder()
                    .message(
                        Message.builder()
                            .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                            .addContent(
                                TextBlock.builder()
                                    .addCitation(
                                        CitationCharLocation.builder()
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
                            .stopReason(StopReason.END_TURN)
                            .stopSequence(null)
                            .usage(
                                Usage.builder()
                                    .cacheCreationInputTokens(2051L)
                                    .cacheReadInputTokens(2051L)
                                    .inputTokens(2095L)
                                    .outputTokens(503L)
                                    .serverToolUse(
                                        ServerToolUsage.builder().webSearchRequests(0L).build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofDelta() {
        val delta =
            RawMessageDeltaEvent.builder()
                .delta(
                    RawMessageDeltaEvent.Delta.builder()
                        .stopReason(StopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(
                    MessageDeltaUsage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .serverToolUse(ServerToolUsage.builder().webSearchRequests(0L).build())
                        .build()
                )
                .build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofDelta(delta)

        assertThat(rawMessageStreamEvent.start()).isEmpty
        assertThat(rawMessageStreamEvent.delta()).contains(delta)
        assertThat(rawMessageStreamEvent.stop()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofDelta(
                RawMessageDeltaEvent.builder()
                    .delta(
                        RawMessageDeltaEvent.Delta.builder()
                            .stopReason(StopReason.END_TURN)
                            .stopSequence("stop_sequence")
                            .build()
                    )
                    .usage(
                        MessageDeltaUsage.builder()
                            .cacheCreationInputTokens(2051L)
                            .cacheReadInputTokens(2051L)
                            .inputTokens(2095L)
                            .outputTokens(503L)
                            .serverToolUse(ServerToolUsage.builder().webSearchRequests(0L).build())
                            .build()
                    )
                    .build()
            )

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofStop() {
        val stop = RawMessageStopEvent.builder().build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofStop(stop)

        assertThat(rawMessageStreamEvent.start()).isEmpty
        assertThat(rawMessageStreamEvent.delta()).isEmpty
        assertThat(rawMessageStreamEvent.stop()).contains(stop)
        assertThat(rawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofStop(RawMessageStopEvent.builder().build())

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockStart() {
        val contentBlockStart =
            RawContentBlockStartEvent.builder()
                .contentBlock(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
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

        val rawMessageStreamEvent = RawMessageStreamEvent.ofContentBlockStart(contentBlockStart)

        assertThat(rawMessageStreamEvent.start()).isEmpty
        assertThat(rawMessageStreamEvent.delta()).isEmpty
        assertThat(rawMessageStreamEvent.stop()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStart()).contains(contentBlockStart)
        assertThat(rawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofContentBlockStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofContentBlockStart(
                RawContentBlockStartEvent.builder()
                    .contentBlock(
                        TextBlock.builder()
                            .addCitation(
                                CitationCharLocation.builder()
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

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockDelta() {
        val contentBlockDelta =
            RawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofContentBlockDelta(contentBlockDelta)

        assertThat(rawMessageStreamEvent.start()).isEmpty
        assertThat(rawMessageStreamEvent.delta()).isEmpty
        assertThat(rawMessageStreamEvent.stop()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockDelta()).contains(contentBlockDelta)
        assertThat(rawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofContentBlockDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofContentBlockDelta(
                RawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()
            )

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockStop() {
        val contentBlockStop = RawContentBlockStopEvent.builder().index(0L).build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofContentBlockStop(contentBlockStop)

        assertThat(rawMessageStreamEvent.start()).isEmpty
        assertThat(rawMessageStreamEvent.delta()).isEmpty
        assertThat(rawMessageStreamEvent.stop()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStop()).contains(contentBlockStop)
    }

    @Test
    fun ofContentBlockStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofContentBlockStop(
                RawContentBlockStopEvent.builder().index(0L).build()
            )

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
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
        val rawMessageStreamEvent =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<RawMessageStreamEvent>())

        val e = assertThrows<AnthropicInvalidDataException> { rawMessageStreamEvent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
