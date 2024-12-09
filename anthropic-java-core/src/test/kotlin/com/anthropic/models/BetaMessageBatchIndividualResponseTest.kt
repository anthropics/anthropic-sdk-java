// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchIndividualResponseTest {

    @Test
    fun createBetaMessageBatchIndividualResponse() {
        val betaMessageBatchIndividualResponse =
            BetaMessageBatchIndividualResponse.builder()
                .customId("my-custom-id-1")
                .result(
                    BetaMessageBatchResult.ofBetaMessageBatchSucceededResult(
                        BetaMessageBatchSucceededResult.builder()
                            .message(
                                BetaMessage.builder()
                                    .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                                    .content(
                                        listOf(
                                            BetaContentBlock.ofBetaTextBlock(
                                                BetaTextBlock.builder()
                                                    .text("text")
                                                    .type(BetaTextBlock.Type.TEXT)
                                                    .build()
                                            )
                                        )
                                    )
                                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                    .role(BetaMessage.Role.ASSISTANT)
                                    .stopReason(BetaMessage.StopReason.END_TURN)
                                    .stopSequence("stop_sequence")
                                    .type(BetaMessage.Type.MESSAGE)
                                    .usage(
                                        BetaUsage.builder()
                                            .cacheCreationInputTokens(2051L)
                                            .cacheReadInputTokens(2051L)
                                            .inputTokens(2095L)
                                            .outputTokens(503L)
                                            .build()
                                    )
                                    .build()
                            )
                            .type(BetaMessageBatchSucceededResult.Type.SUCCEEDED)
                            .build()
                    )
                )
                .build()
        assertThat(betaMessageBatchIndividualResponse).isNotNull
        assertThat(betaMessageBatchIndividualResponse.customId()).isEqualTo("my-custom-id-1")
        assertThat(betaMessageBatchIndividualResponse.result())
            .isEqualTo(
                BetaMessageBatchResult.ofBetaMessageBatchSucceededResult(
                    BetaMessageBatchSucceededResult.builder()
                        .message(
                            BetaMessage.builder()
                                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                                .content(
                                    listOf(
                                        BetaContentBlock.ofBetaTextBlock(
                                            BetaTextBlock.builder()
                                                .text("text")
                                                .type(BetaTextBlock.Type.TEXT)
                                                .build()
                                        )
                                    )
                                )
                                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                .role(BetaMessage.Role.ASSISTANT)
                                .stopReason(BetaMessage.StopReason.END_TURN)
                                .stopSequence("stop_sequence")
                                .type(BetaMessage.Type.MESSAGE)
                                .usage(
                                    BetaUsage.builder()
                                        .cacheCreationInputTokens(2051L)
                                        .cacheReadInputTokens(2051L)
                                        .inputTokens(2095L)
                                        .outputTokens(503L)
                                        .build()
                                )
                                .build()
                        )
                        .type(BetaMessageBatchSucceededResult.Type.SUCCEEDED)
                        .build()
                )
            )
    }
}
