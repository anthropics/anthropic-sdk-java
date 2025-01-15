// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageBatchSucceededResultTest {

    @Test
    fun createMessageBatchSucceededResult() {
        val messageBatchSucceededResult =
            MessageBatchSucceededResult.builder()
                .message(
                    Message.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .addContent(
                            ContentBlock.ofTextBlock(
                                TextBlock.builder()
                                    .text("Hi! My name is Claude.")
                                    .type(TextBlock.Type.TEXT)
                                    .build()
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
                .type(MessageBatchSucceededResult.Type.SUCCEEDED)
                .build()
        assertThat(messageBatchSucceededResult).isNotNull
        assertThat(messageBatchSucceededResult.message())
            .isEqualTo(
                Message.builder()
                    .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                    .addContent(
                        ContentBlock.ofTextBlock(
                            TextBlock.builder()
                                .text("Hi! My name is Claude.")
                                .type(TextBlock.Type.TEXT)
                                .build()
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
        assertThat(messageBatchSucceededResult.type())
            .isEqualTo(MessageBatchSucceededResult.Type.SUCCEEDED)
    }
}
