// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RawPromptCachingBetaMessageStartEventTest {

    @Test
    fun createRawPromptCachingBetaMessageStartEvent() {
        val rawPromptCachingBetaMessageStartEvent =
            RawPromptCachingBetaMessageStartEvent.builder()
                .message(
                    PromptCachingBetaMessage.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .content(
                            listOf(
                                ContentBlock.ofTextBlock(
                                    TextBlock.builder()
                                        .text("text")
                                        .type(TextBlock.Type.TEXT)
                                        .build()
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
                )
                .type(RawPromptCachingBetaMessageStartEvent.Type.MESSAGE_START)
                .build()
        assertThat(rawPromptCachingBetaMessageStartEvent).isNotNull
        assertThat(rawPromptCachingBetaMessageStartEvent.message())
            .isEqualTo(
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
            )
        assertThat(rawPromptCachingBetaMessageStartEvent.type())
            .isEqualTo(RawPromptCachingBetaMessageStartEvent.Type.MESSAGE_START)
    }
}
