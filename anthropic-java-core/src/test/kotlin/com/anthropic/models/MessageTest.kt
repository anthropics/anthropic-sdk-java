// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageTest {

    @Test
    fun createMessage() {
        val message =
            Message.builder()
                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                .content(
                    listOf(
                        ContentBlock.ofTextBlock(
                            TextBlock.builder().text("text").type(TextBlock.Type.TEXT).build()
                        )
                    )
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .role(Message.Role.ASSISTANT)
                .stopReason(Message.StopReason.END_TURN)
                .stopSequence("stop_sequence")
                .type(Message.Type.MESSAGE)
                .usage(Usage.builder().inputTokens(2095L).outputTokens(503L).build())
                .build()
        assertThat(message).isNotNull
        assertThat(message.id()).isEqualTo("msg_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(message.content())
            .containsExactly(
                ContentBlock.ofTextBlock(
                    TextBlock.builder().text("text").type(TextBlock.Type.TEXT).build()
                )
            )
        assertThat(message.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(message.role()).isEqualTo(Message.Role.ASSISTANT)
        assertThat(message.stopReason()).contains(Message.StopReason.END_TURN)
        assertThat(message.stopSequence()).contains("stop_sequence")
        assertThat(message.type()).isEqualTo(Message.Type.MESSAGE)
        assertThat(message.usage())
            .isEqualTo(Usage.builder().inputTokens(2095L).outputTokens(503L).build())
    }
}
