// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageTest {

    @Test
    fun create() {
        val message =
            Message.builder()
                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                .addContent(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
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
                .stopReason(StopReason.END_TURN)
                .stopSequence(null)
                .usage(
                    Usage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .serverToolUse(ServerToolUsage.builder().webSearchRequests(0L).build())
                        .serviceTier(Usage.ServiceTier.STANDARD)
                        .build()
                )
                .build()

        assertThat(message.id()).isEqualTo("msg_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(message.content())
            .containsExactly(
                ContentBlock.ofText(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
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
        assertThat(message.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
        assertThat(message.stopReason()).contains(StopReason.END_TURN)
        assertThat(message.stopSequence()).isEmpty
        assertThat(message.usage())
            .isEqualTo(
                Usage.builder()
                    .cacheCreationInputTokens(2051L)
                    .cacheReadInputTokens(2051L)
                    .inputTokens(2095L)
                    .outputTokens(503L)
                    .serverToolUse(ServerToolUsage.builder().webSearchRequests(0L).build())
                    .serviceTier(Usage.ServiceTier.STANDARD)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val message =
            Message.builder()
                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                .addContent(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
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
                .stopReason(StopReason.END_TURN)
                .stopSequence(null)
                .usage(
                    Usage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .serverToolUse(ServerToolUsage.builder().webSearchRequests(0L).build())
                        .serviceTier(Usage.ServiceTier.STANDARD)
                        .build()
                )
                .build()

        val roundtrippedMessage =
            jsonMapper.readValue(jsonMapper.writeValueAsString(message), jacksonTypeRef<Message>())

        assertThat(roundtrippedMessage).isEqualTo(message)
    }
}
