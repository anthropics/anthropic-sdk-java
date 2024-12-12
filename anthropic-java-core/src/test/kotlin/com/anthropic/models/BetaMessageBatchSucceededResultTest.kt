// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchSucceededResultTest {

    @Test
    fun createBetaMessageBatchSucceededResult() {
        val betaMessageBatchSucceededResult =
            BetaMessageBatchSucceededResult.builder()
                .message(
                    BetaMessage.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .content(
                            listOf(
                                BetaContentBlock.ofBetaTextBlock(
                                    BetaTextBlock.builder()
                                        .text("Hi! My name is Claude.")
                                        .type(BetaTextBlock.Type.TEXT)
                                        .build()
                                )
                            )
                        )
                        .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                        .role(BetaMessage.Role.ASSISTANT)
                        .stopReason(BetaMessage.StopReason.END_TURN)
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
        assertThat(betaMessageBatchSucceededResult).isNotNull
        assertThat(betaMessageBatchSucceededResult.message())
            .isEqualTo(
                BetaMessage.builder()
                    .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                    .content(
                        listOf(
                            BetaContentBlock.ofBetaTextBlock(
                                BetaTextBlock.builder()
                                    .text("Hi! My name is Claude.")
                                    .type(BetaTextBlock.Type.TEXT)
                                    .build()
                            )
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .role(BetaMessage.Role.ASSISTANT)
                    .stopReason(BetaMessage.StopReason.END_TURN)
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
        assertThat(betaMessageBatchSucceededResult.type())
            .isEqualTo(BetaMessageBatchSucceededResult.Type.SUCCEEDED)
    }
}