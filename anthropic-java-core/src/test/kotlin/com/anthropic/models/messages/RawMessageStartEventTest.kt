// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawMessageStartEventTest {

    @Test
    fun create() {
        val rawMessageStartEvent =
            RawMessageStartEvent.builder()
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
                                    ServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .serviceTier(Usage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(rawMessageStartEvent.message())
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
        val rawMessageStartEvent =
            RawMessageStartEvent.builder()
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
                                    ServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .serviceTier(Usage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedRawMessageStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStartEvent),
                jacksonTypeRef<RawMessageStartEvent>(),
            )

        assertThat(roundtrippedRawMessageStartEvent).isEqualTo(rawMessageStartEvent)
    }
}
