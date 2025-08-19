// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.CacheCreation
import com.anthropic.models.messages.CitationCharLocation
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.ServerToolUsage
import com.anthropic.models.messages.StopReason
import com.anthropic.models.messages.TextBlock
import com.anthropic.models.messages.Usage
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchSucceededResultTest {

    @Test
    fun create() {
        val messageBatchSucceededResult =
            MessageBatchSucceededResult.builder()
                .message(
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
                                        .fileId("file_id")
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
                                .cacheCreation(
                                    CacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    ServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .serviceTier(Usage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(messageBatchSucceededResult.message())
            .isEqualTo(
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
                                    .fileId("file_id")
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
                            .cacheCreation(
                                CacheCreation.builder()
                                    .ephemeral1hInputTokens(0L)
                                    .ephemeral5mInputTokens(0L)
                                    .build()
                            )
                            .cacheCreationInputTokens(2051L)
                            .cacheReadInputTokens(2051L)
                            .inputTokens(2095L)
                            .outputTokens(503L)
                            .serverToolUse(ServerToolUsage.builder().webSearchRequests(0L).build())
                            .serviceTier(Usage.ServiceTier.STANDARD)
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchSucceededResult =
            MessageBatchSucceededResult.builder()
                .message(
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
                                        .fileId("file_id")
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
                                .cacheCreation(
                                    CacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    ServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .serviceTier(Usage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedMessageBatchSucceededResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchSucceededResult),
                jacksonTypeRef<MessageBatchSucceededResult>(),
            )

        assertThat(roundtrippedMessageBatchSucceededResult).isEqualTo(messageBatchSucceededResult)
    }
}
