// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawMessageStartEventTest {

    @Test
    fun create() {
        val betaRawMessageStartEvent =
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
                        .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    BetaServerToolUsage.builder()
                                        .webFetchRequests(2L)
                                        .webSearchRequests(0L)
                                        .build()
                                )
                                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaRawMessageStartEvent.message())
            .isEqualTo(
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
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
                            .inputTokens(2095L)
                            .outputTokens(503L)
                            .serverToolUse(
                                BetaServerToolUsage.builder()
                                    .webFetchRequests(2L)
                                    .webSearchRequests(0L)
                                    .build()
                            )
                            .serviceTier(BetaUsage.ServiceTier.STANDARD)
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStartEvent =
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
                        .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    BetaServerToolUsage.builder()
                                        .webFetchRequests(2L)
                                        .webSearchRequests(0L)
                                        .build()
                                )
                                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaRawMessageStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageStartEvent),
                jacksonTypeRef<BetaRawMessageStartEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageStartEvent).isEqualTo(betaRawMessageStartEvent)
    }
}
