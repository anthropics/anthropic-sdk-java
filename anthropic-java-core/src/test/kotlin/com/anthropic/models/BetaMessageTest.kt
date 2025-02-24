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
                    BetaTextBlock.builder()
                        .addCitation(
                            BetaCitationCharLocation.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("document_title")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("Hi! My name is Claude.")
                        .build()
                )
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .stopReason(BetaMessage.StopReason.END_TURN)
                .stopSequence(null)
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
                BetaContentBlock.ofText(
                    BetaTextBlock.builder()
                        .addCitation(
                            BetaCitationCharLocation.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("document_title")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("Hi! My name is Claude.")
                        .build()
                )
            )
        assertThat(betaMessage.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
        assertThat(betaMessage.stopReason()).contains(BetaMessage.StopReason.END_TURN)
        assertThat(betaMessage.stopSequence()).isEmpty
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
