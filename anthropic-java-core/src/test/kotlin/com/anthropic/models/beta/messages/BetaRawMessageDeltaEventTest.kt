// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawMessageDeltaEventTest {

    @Test
    fun create() {
        val betaRawMessageDeltaEvent =
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

        assertThat(betaRawMessageDeltaEvent.contextManagement())
            .contains(
                BetaContextManagementResponse.builder()
                    .addAppliedEdit(
                        BetaClearToolUses20250919EditResponse.builder()
                            .clearedInputTokens(0L)
                            .clearedToolUses(0L)
                            .build()
                    )
                    .build()
            )
        assertThat(betaRawMessageDeltaEvent.delta())
            .isEqualTo(
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
        assertThat(betaRawMessageDeltaEvent.usage())
            .isEqualTo(
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageDeltaEvent =
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

        val roundtrippedBetaRawMessageDeltaEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageDeltaEvent),
                jacksonTypeRef<BetaRawMessageDeltaEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageDeltaEvent).isEqualTo(betaRawMessageDeltaEvent)
    }
}
