// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaMessageTest {

    @Test
    fun createPromptCachingBetaMessage() {
        val promptCachingBetaMessage =
            PromptCachingBetaMessage.builder()
                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                .content(
                    listOf(
                        ContentBlock.ofTextBlock(
                            TextBlock.builder().text("text").type(TextBlock.Type.TEXT).build()
                        )
                    )
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .role(PromptCachingBetaMessage.Role.ASSISTANT)
                .stopReason(PromptCachingBetaMessage.StopReason.END_TURN)
                .stopSequence("stop_sequence")
                .type(PromptCachingBetaMessage.Type.MESSAGE)
                .usage(
                    PromptCachingBetaUsage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .build()
                )
                .build()
        assertThat(promptCachingBetaMessage).isNotNull
        assertThat(promptCachingBetaMessage.id()).isEqualTo("msg_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(promptCachingBetaMessage.content())
            .containsExactly(
                ContentBlock.ofTextBlock(
                    TextBlock.builder().text("text").type(TextBlock.Type.TEXT).build()
                )
            )
        assertThat(promptCachingBetaMessage.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(promptCachingBetaMessage.role())
            .isEqualTo(PromptCachingBetaMessage.Role.ASSISTANT)
        assertThat(promptCachingBetaMessage.stopReason())
            .contains(PromptCachingBetaMessage.StopReason.END_TURN)
        assertThat(promptCachingBetaMessage.stopSequence()).contains("stop_sequence")
        assertThat(promptCachingBetaMessage.type()).isEqualTo(PromptCachingBetaMessage.Type.MESSAGE)
        assertThat(promptCachingBetaMessage.usage())
            .isEqualTo(
                PromptCachingBetaUsage.builder()
                    .cacheCreationInputTokens(2051L)
                    .cacheReadInputTokens(2051L)
                    .inputTokens(2095L)
                    .outputTokens(503L)
                    .build()
            )
    }
}
