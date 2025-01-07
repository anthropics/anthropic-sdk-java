// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RawMessageStartEventTest {

    @Test
    fun createRawMessageStartEvent() {
        val rawMessageStartEvent =
            RawMessageStartEvent.builder()
                .message(
                    Message.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .content(
                            listOf(
                                ContentBlock.ofTextBlock(
                                    TextBlock.builder()
                                        .text("Hi! My name is Claude.")
                                        .type(TextBlock.Type.TEXT)
                                        .build()
                                )
                            )
                        )
                        .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                        .role(Message.Role.ASSISTANT)
                        .stopReason(Message.StopReason.END_TURN)
                        .stopSequence(null)
                        .type(Message.Type.MESSAGE)
                        .usage(
                            Usage.builder()
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .build()
                        )
                        .build()
                )
                .type(RawMessageStartEvent.Type.MESSAGE_START)
                .build()
        assertThat(rawMessageStartEvent).isNotNull
        assertThat(rawMessageStartEvent.message())
            .isEqualTo(
                Message.builder()
                    .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                    .content(
                        listOf(
                            ContentBlock.ofTextBlock(
                                TextBlock.builder()
                                    .text("Hi! My name is Claude.")
                                    .type(TextBlock.Type.TEXT)
                                    .build()
                            )
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .role(Message.Role.ASSISTANT)
                    .stopReason(Message.StopReason.END_TURN)
                    .stopSequence(null)
                    .type(Message.Type.MESSAGE)
                    .usage(
                        Usage.builder()
                            .cacheCreationInputTokens(2051L)
                            .cacheReadInputTokens(2051L)
                            .inputTokens(2095L)
                            .outputTokens(503L)
                            .build()
                    )
                    .build()
            )
        assertThat(rawMessageStartEvent.type()).isEqualTo(RawMessageStartEvent.Type.MESSAGE_START)
    }
}
