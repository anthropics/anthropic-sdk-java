// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.anthropic.models.beta.messages.BetaCitationCharLocation
import com.anthropic.models.beta.messages.BetaContainer
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaServerToolUsage
import com.anthropic.models.beta.messages.BetaStopReason
import com.anthropic.models.beta.messages.BetaTextBlock
import com.anthropic.models.beta.messages.BetaUsage
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageBatchIndividualResponseTest {

    @Test
    fun create() {
        val betaMessageBatchIndividualResponse =
            BetaMessageBatchIndividualResponse.builder()
                .customId("my-custom-id-1")
                .succeededResult(
                    BetaMessage.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .container(
                            BetaContainer.builder()
                                .id("id")
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .build()
                        )
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
                                .cacheCreation(
                                    BetaCacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    BetaServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaMessageBatchIndividualResponse.customId()).isEqualTo("my-custom-id-1")
        assertThat(betaMessageBatchIndividualResponse.result())
            .isEqualTo(
                BetaMessageBatchResult.ofSucceeded(
                    BetaMessageBatchSucceededResult.builder()
                        .message(
                            BetaMessage.builder()
                                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                                .container(
                                    BetaContainer.builder()
                                        .id("id")
                                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                        .build()
                                )
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
                                        .cacheCreation(
                                            BetaCacheCreation.builder()
                                                .ephemeral1hInputTokens(0L)
                                                .ephemeral5mInputTokens(0L)
                                                .build()
                                        )
                                        .cacheCreationInputTokens(2051L)
                                        .cacheReadInputTokens(2051L)
                                        .inputTokens(2095L)
                                        .outputTokens(503L)
                                        .serverToolUse(
                                            BetaServerToolUsage.builder()
                                                .webSearchRequests(0L)
                                                .build()
                                        )
                                        .serviceTier(BetaUsage.ServiceTier.STANDARD)
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
        val betaMessageBatchIndividualResponse =
            BetaMessageBatchIndividualResponse.builder()
                .customId("my-custom-id-1")
                .succeededResult(
                    BetaMessage.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .container(
                            BetaContainer.builder()
                                .id("id")
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .build()
                        )
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
                                .cacheCreation(
                                    BetaCacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .serverToolUse(
                                    BetaServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaMessageBatchIndividualResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchIndividualResponse),
                jacksonTypeRef<BetaMessageBatchIndividualResponse>(),
            )

        assertThat(roundtrippedBetaMessageBatchIndividualResponse)
            .isEqualTo(betaMessageBatchIndividualResponse)
    }
}
