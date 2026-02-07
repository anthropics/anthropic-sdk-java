// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaRawMessageStreamEventTest {

    @Test
    fun ofMessageStart() {
        val messageStart =
            BetaRawMessageStartEvent.builder()
                .message(
                    BetaMessage.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .container(
                            BetaContainer.builder()
                                .id("id")
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .addSkill(
                                    BetaSkill.builder()
                                        .skillId("x")
                                        .type(BetaSkill.Type.ANTHROPIC)
                                        .version("x")
                                        .build()
                                )
                                .build()
                        )
                        .addContent(
                            BetaTextBlock.builder()
                                .addCitation(
                                    BetaCitationCharLocation.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("document_title")
                                        .endCharIndex(0L)
                                        .fileId("file_id")
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .text("Hi! My name is Claude.")
                                .build()
                        )
                        .contextManagement(
                            BetaContextManagementResponse.builder()
                                .addAppliedEdit(
                                    BetaClearToolUses20250919EditResponse.builder()
                                        .clearedInputTokens(0L)
                                        .clearedToolUses(0L)
                                        .build()
                                )
                                .build()
                        )
                        .model(Model.CLAUDE_OPUS_4_6)
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence(null)
                        .usage(
                            BetaUsage.builder()
                                .cacheCreation(
                                    BetaCacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inferenceGeo("inference_geo")
                                .inputTokens(2095L)
                                .addIteration(
                                    BetaMessageIterationUsage.builder()
                                        .cacheCreation(
                                            BetaCacheCreation.builder()
                                                .ephemeral1hInputTokens(0L)
                                                .ephemeral5mInputTokens(0L)
                                                .build()
                                        )
                                        .cacheCreationInputTokens(0L)
                                        .cacheReadInputTokens(0L)
                                        .inputTokens(0L)
                                        .outputTokens(0L)
                                        .build()
                                )
                                .outputTokens(503L)
                                .serverToolUse(
                                    BetaServerToolUsage.builder()
                                        .webFetchRequests(2L)
                                        .webSearchRequests(0L)
                                        .build()
                                )
                                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                .speed(BetaUsage.Speed.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofMessageStart(messageStart)

        assertThat(betaRawMessageStreamEvent.messageStart()).contains(messageStart)
        assertThat(betaRawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageStop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofMessageStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofMessageStart(
                BetaRawMessageStartEvent.builder()
                    .message(
                        BetaMessage.builder()
                            .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                            .container(
                                BetaContainer.builder()
                                    .id("id")
                                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                    .addSkill(
                                        BetaSkill.builder()
                                            .skillId("x")
                                            .type(BetaSkill.Type.ANTHROPIC)
                                            .version("x")
                                            .build()
                                    )
                                    .build()
                            )
                            .addContent(
                                BetaTextBlock.builder()
                                    .addCitation(
                                        BetaCitationCharLocation.builder()
                                            .citedText("cited_text")
                                            .documentIndex(0L)
                                            .documentTitle("document_title")
                                            .endCharIndex(0L)
                                            .fileId("file_id")
                                            .startCharIndex(0L)
                                            .build()
                                    )
                                    .text("Hi! My name is Claude.")
                                    .build()
                            )
                            .contextManagement(
                                BetaContextManagementResponse.builder()
                                    .addAppliedEdit(
                                        BetaClearToolUses20250919EditResponse.builder()
                                            .clearedInputTokens(0L)
                                            .clearedToolUses(0L)
                                            .build()
                                    )
                                    .build()
                            )
                            .model(Model.CLAUDE_OPUS_4_6)
                            .stopReason(BetaStopReason.END_TURN)
                            .stopSequence(null)
                            .usage(
                                BetaUsage.builder()
                                    .cacheCreation(
                                        BetaCacheCreation.builder()
                                            .ephemeral1hInputTokens(0L)
                                            .ephemeral5mInputTokens(0L)
                                            .build()
                                    )
                                    .cacheCreationInputTokens(2051L)
                                    .cacheReadInputTokens(2051L)
                                    .inferenceGeo("inference_geo")
                                    .inputTokens(2095L)
                                    .addIteration(
                                        BetaMessageIterationUsage.builder()
                                            .cacheCreation(
                                                BetaCacheCreation.builder()
                                                    .ephemeral1hInputTokens(0L)
                                                    .ephemeral5mInputTokens(0L)
                                                    .build()
                                            )
                                            .cacheCreationInputTokens(0L)
                                            .cacheReadInputTokens(0L)
                                            .inputTokens(0L)
                                            .outputTokens(0L)
                                            .build()
                                    )
                                    .outputTokens(503L)
                                    .serverToolUse(
                                        BetaServerToolUsage.builder()
                                            .webFetchRequests(2L)
                                            .webSearchRequests(0L)
                                            .build()
                                    )
                                    .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                    .speed(BetaUsage.Speed.STANDARD)
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
    fun ofMessageDelta() {
        val messageDelta =
            BetaRawMessageDeltaEvent.builder()
                .contextManagement(
                    BetaContextManagementResponse.builder()
                        .addAppliedEdit(
                            BetaClearToolUses20250919EditResponse.builder()
                                .clearedInputTokens(0L)
                                .clearedToolUses(0L)
                                .build()
                        )
                        .build()
                )
                .delta(
                    BetaRawMessageDeltaEvent.Delta.builder()
                        .container(
                            BetaContainer.builder()
                                .id("id")
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .addSkill(
                                    BetaSkill.builder()
                                        .skillId("x")
                                        .type(BetaSkill.Type.ANTHROPIC)
                                        .version("x")
                                        .build()
                                )
                                .build()
                        )
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(
                    BetaMessageDeltaUsage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .addIteration(
                            BetaMessageIterationUsage.builder()
                                .cacheCreation(
                                    BetaCacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheCreationInputTokens(0L)
                                .cacheReadInputTokens(0L)
                                .inputTokens(0L)
                                .outputTokens(0L)
                                .build()
                        )
                        .outputTokens(503L)
                        .serverToolUse(
                            BetaServerToolUsage.builder()
                                .webFetchRequests(2L)
                                .webSearchRequests(0L)
                                .build()
                        )
                        .build()
                )
                .build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofMessageDelta(messageDelta)

        assertThat(betaRawMessageStreamEvent.messageStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageDelta()).contains(messageDelta)
        assertThat(betaRawMessageStreamEvent.messageStop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofMessageDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofMessageDelta(
                BetaRawMessageDeltaEvent.builder()
                    .contextManagement(
                        BetaContextManagementResponse.builder()
                            .addAppliedEdit(
                                BetaClearToolUses20250919EditResponse.builder()
                                    .clearedInputTokens(0L)
                                    .clearedToolUses(0L)
                                    .build()
                            )
                            .build()
                    )
                    .delta(
                        BetaRawMessageDeltaEvent.Delta.builder()
                            .container(
                                BetaContainer.builder()
                                    .id("id")
                                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                    .addSkill(
                                        BetaSkill.builder()
                                            .skillId("x")
                                            .type(BetaSkill.Type.ANTHROPIC)
                                            .version("x")
                                            .build()
                                    )
                                    .build()
                            )
                            .stopReason(BetaStopReason.END_TURN)
                            .stopSequence("stop_sequence")
                            .build()
                    )
                    .usage(
                        BetaMessageDeltaUsage.builder()
                            .cacheCreationInputTokens(2051L)
                            .cacheReadInputTokens(2051L)
                            .inputTokens(2095L)
                            .addIteration(
                                BetaMessageIterationUsage.builder()
                                    .cacheCreation(
                                        BetaCacheCreation.builder()
                                            .ephemeral1hInputTokens(0L)
                                            .ephemeral5mInputTokens(0L)
                                            .build()
                                    )
                                    .cacheCreationInputTokens(0L)
                                    .cacheReadInputTokens(0L)
                                    .inputTokens(0L)
                                    .outputTokens(0L)
                                    .build()
                            )
                            .outputTokens(503L)
                            .serverToolUse(
                                BetaServerToolUsage.builder()
                                    .webFetchRequests(2L)
                                    .webSearchRequests(0L)
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
    fun ofMessageStop() {
        val messageStop = BetaRawMessageStopEvent.builder().build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofMessageStop(messageStop)

        assertThat(betaRawMessageStreamEvent.messageStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageStop()).contains(messageStop)
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofMessageStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofMessageStop(BetaRawMessageStopEvent.builder().build())

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
                                .fileId("file_id")
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

        assertThat(betaRawMessageStreamEvent.messageStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageStop()).isEmpty
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
                                    .fileId("file_id")
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

        assertThat(betaRawMessageStreamEvent.messageStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageStop()).isEmpty
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

        assertThat(betaRawMessageStreamEvent.messageStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.messageStop()).isEmpty
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
