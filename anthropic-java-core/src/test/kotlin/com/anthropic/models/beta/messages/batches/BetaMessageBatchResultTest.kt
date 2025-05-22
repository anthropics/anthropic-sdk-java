// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaErrorResponse
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
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaMessageBatchResultTest {

    @Test
    fun ofSucceeded() {
        val succeeded =
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
                                    BetaServerToolUsage.builder().webSearchRequests(0L).build()
                                )
                                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                .build()
                        )
                        .build()
                )
                .build()

        val betaMessageBatchResult = BetaMessageBatchResult.ofSucceeded(succeeded)

        assertThat(betaMessageBatchResult.succeeded()).contains(succeeded)
        assertThat(betaMessageBatchResult.errored()).isEmpty
        assertThat(betaMessageBatchResult.canceled()).isEmpty
        assertThat(betaMessageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofSucceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageBatchResult =
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
                                        BetaServerToolUsage.builder().webSearchRequests(0L).build()
                                    )
                                    .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaMessageBatchResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchResult),
                jacksonTypeRef<BetaMessageBatchResult>(),
            )

        assertThat(roundtrippedBetaMessageBatchResult).isEqualTo(betaMessageBatchResult)
    }

    @Test
    fun ofErrored() {
        val errored =
            BetaMessageBatchErroredResult.builder()
                .error(BetaErrorResponse.builder().invalidRequestError("message").build())
                .build()

        val betaMessageBatchResult = BetaMessageBatchResult.ofErrored(errored)

        assertThat(betaMessageBatchResult.succeeded()).isEmpty
        assertThat(betaMessageBatchResult.errored()).contains(errored)
        assertThat(betaMessageBatchResult.canceled()).isEmpty
        assertThat(betaMessageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofErroredRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageBatchResult =
            BetaMessageBatchResult.ofErrored(
                BetaMessageBatchErroredResult.builder()
                    .error(BetaErrorResponse.builder().invalidRequestError("message").build())
                    .build()
            )

        val roundtrippedBetaMessageBatchResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchResult),
                jacksonTypeRef<BetaMessageBatchResult>(),
            )

        assertThat(roundtrippedBetaMessageBatchResult).isEqualTo(betaMessageBatchResult)
    }

    @Test
    fun ofCanceled() {
        val canceled = BetaMessageBatchCanceledResult.builder().build()

        val betaMessageBatchResult = BetaMessageBatchResult.ofCanceled(canceled)

        assertThat(betaMessageBatchResult.succeeded()).isEmpty
        assertThat(betaMessageBatchResult.errored()).isEmpty
        assertThat(betaMessageBatchResult.canceled()).contains(canceled)
        assertThat(betaMessageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofCanceledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageBatchResult =
            BetaMessageBatchResult.ofCanceled(BetaMessageBatchCanceledResult.builder().build())

        val roundtrippedBetaMessageBatchResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchResult),
                jacksonTypeRef<BetaMessageBatchResult>(),
            )

        assertThat(roundtrippedBetaMessageBatchResult).isEqualTo(betaMessageBatchResult)
    }

    @Test
    fun ofExpired() {
        val expired = BetaMessageBatchExpiredResult.builder().build()

        val betaMessageBatchResult = BetaMessageBatchResult.ofExpired(expired)

        assertThat(betaMessageBatchResult.succeeded()).isEmpty
        assertThat(betaMessageBatchResult.errored()).isEmpty
        assertThat(betaMessageBatchResult.canceled()).isEmpty
        assertThat(betaMessageBatchResult.expired()).contains(expired)
    }

    @Test
    fun ofExpiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageBatchResult =
            BetaMessageBatchResult.ofExpired(BetaMessageBatchExpiredResult.builder().build())

        val roundtrippedBetaMessageBatchResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchResult),
                jacksonTypeRef<BetaMessageBatchResult>(),
            )

        assertThat(roundtrippedBetaMessageBatchResult).isEqualTo(betaMessageBatchResult)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaMessageBatchResult =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaMessageBatchResult>())

        val e = assertThrows<AnthropicInvalidDataException> { betaMessageBatchResult.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
