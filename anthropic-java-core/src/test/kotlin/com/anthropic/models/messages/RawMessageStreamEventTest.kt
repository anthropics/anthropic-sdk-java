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
    fun ofMessageStart() {
        val messageStart =
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
                                .serviceTier(Usage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofMessageStart(messageStart)

        assertThat(rawMessageStreamEvent.messageStart()).contains(messageStart)
        assertThat(rawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(rawMessageStreamEvent.messageStop()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofMessageStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofMessageStart(
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
                                    .serviceTier(Usage.ServiceTier.STANDARD)
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
    fun ofMessageDelta() {
        val messageDelta =
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

        val rawMessageStreamEvent = RawMessageStreamEvent.ofMessageDelta(messageDelta)

        assertThat(rawMessageStreamEvent.messageStart()).isEmpty
        assertThat(rawMessageStreamEvent.messageDelta()).contains(messageDelta)
        assertThat(rawMessageStreamEvent.messageStop()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofMessageDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofMessageDelta(
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
    fun ofMessageStop() {
        val messageStop = RawMessageStopEvent.builder().build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofMessageStop(messageStop)

        assertThat(rawMessageStreamEvent.messageStart()).isEmpty
        assertThat(rawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(rawMessageStreamEvent.messageStop()).contains(messageStop)
        assertThat(rawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(rawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofMessageStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofMessageStop(RawMessageStopEvent.builder().build())

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

        assertThat(rawMessageStreamEvent.messageStart()).isEmpty
        assertThat(rawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(rawMessageStreamEvent.messageStop()).isEmpty
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

        assertThat(rawMessageStreamEvent.messageStart()).isEmpty
        assertThat(rawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(rawMessageStreamEvent.messageStop()).isEmpty
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

        assertThat(rawMessageStreamEvent.messageStart()).isEmpty
        assertThat(rawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(rawMessageStreamEvent.messageStop()).isEmpty
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
