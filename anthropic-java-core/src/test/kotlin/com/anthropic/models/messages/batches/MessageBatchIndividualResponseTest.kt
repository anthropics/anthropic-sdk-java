// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.CacheCreation
import com.anthropic.models.messages.CitationCharLocation
import com.anthropic.models.messages.Container
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.ServerToolUsage
import com.anthropic.models.messages.StopReason
import com.anthropic.models.messages.TextBlock
import com.anthropic.models.messages.Usage
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchIndividualResponseTest {

    @Test
    fun create() {
        val messageBatchIndividualResponse =
            MessageBatchIndividualResponse.builder()
                .customId("my-custom-id-1")
                .succeededResult(
                    Message.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .container(
                            Container.builder()
                                .id("id")
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .build()
                        )
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
                        .model(Model.CLAUDE_OPUS_4_6)
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
                                .inferenceGeo("inference_geo")
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    ServerToolUsage.builder()
                                        .webFetchRequests(2L)
                                        .webSearchRequests(0L)
                                        .build()
                                )
                                .serviceTier(Usage.ServiceTier.STANDARD)
                                .speed(Usage.Speed.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(messageBatchIndividualResponse.customId()).isEqualTo("my-custom-id-1")
        assertThat(messageBatchIndividualResponse.result())
            .isEqualTo(
                MessageBatchResult.ofSucceeded(
                    MessageBatchSucceededResult.builder()
                        .message(
                            Message.builder()
                                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                                .container(
                                    Container.builder()
                                        .id("id")
                                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                        .build()
                                )
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
                                .model(Model.CLAUDE_OPUS_4_6)
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
                                        .inferenceGeo("inference_geo")
                                        .inputTokens(2095L)
                                        .outputTokens(503L)
                                        .serverToolUse(
                                            ServerToolUsage.builder()
                                                .webFetchRequests(2L)
                                                .webSearchRequests(0L)
                                                .build()
                                        )
                                        .serviceTier(Usage.ServiceTier.STANDARD)
                                        .speed(Usage.Speed.STANDARD)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchIndividualResponse =
            MessageBatchIndividualResponse.builder()
                .customId("my-custom-id-1")
                .succeededResult(
                    Message.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .container(
                            Container.builder()
                                .id("id")
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .build()
                        )
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
                        .model(Model.CLAUDE_OPUS_4_6)
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
                                .inferenceGeo("inference_geo")
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    ServerToolUsage.builder()
                                        .webFetchRequests(2L)
                                        .webSearchRequests(0L)
                                        .build()
                                )
                                .serviceTier(Usage.ServiceTier.STANDARD)
                                .speed(Usage.Speed.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedMessageBatchIndividualResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchIndividualResponse),
                jacksonTypeRef<MessageBatchIndividualResponse>(),
            )

        assertThat(roundtrippedMessageBatchIndividualResponse)
            .isEqualTo(messageBatchIndividualResponse)
    }
}
