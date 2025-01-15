// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageTest {

    @Test
    fun createBetaMessage() {
        val betaMessage =
            BetaMessage.builder()
                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                .addContent(
                    BetaContentBlock.ofBetaTextBlock(
                        BetaTextBlock.builder()
                            .text("Hi! My name is Claude.")
                            .type(BetaTextBlock.Type.TEXT)
                            .build()
                    )
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .role(BetaMessage.Role.ASSISTANT)
                .stopReason(BetaMessage.StopReason.END_TURN)
                .stopSequence(null)
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
        assertThat(betaMessage).isNotNull
        assertThat(betaMessage.id()).isEqualTo("msg_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(betaMessage.content())
            .containsExactly(
                BetaContentBlock.ofBetaTextBlock(
                    BetaTextBlock.builder()
                        .text("Hi! My name is Claude.")
                        .type(BetaTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaMessage.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(betaMessage.role()).isEqualTo(BetaMessage.Role.ASSISTANT)
        assertThat(betaMessage.stopReason()).contains(BetaMessage.StopReason.END_TURN)
        assertThat(betaMessage.stopSequence()).isEmpty
        assertThat(betaMessage.type()).isEqualTo(BetaMessage.Type.MESSAGE)
        assertThat(betaMessage.usage())
            .isEqualTo(
                BetaUsage.builder()
                    .cacheCreationInputTokens(2051L)
                    .cacheReadInputTokens(2051L)
                    .inputTokens(2095L)
                    .outputTokens(503L)
                    .build()
            )
    }
}
