// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageTest {

    @Test
    fun create() {
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
                .stopReason(BetaStopReason.END_TURN)
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
        assertThat(betaMessage.stopReason()).contains(BetaStopReason.END_TURN)
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

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
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
                .stopReason(BetaStopReason.END_TURN)
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

        val roundtrippedBetaMessage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessage),
                jacksonTypeRef<BetaMessage>(),
            )

        assertThat(roundtrippedBetaMessage).isEqualTo(betaMessage)
    }
}
