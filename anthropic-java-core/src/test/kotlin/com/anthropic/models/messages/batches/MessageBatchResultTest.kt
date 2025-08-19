// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.ErrorResponse
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
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class MessageBatchResultTest {

    @Test
    fun ofSucceeded() {
        val succeeded =
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

        val messageBatchResult = MessageBatchResult.ofSucceeded(succeeded)

        assertThat(messageBatchResult.succeeded()).contains(succeeded)
        assertThat(messageBatchResult.errored()).isEmpty
        assertThat(messageBatchResult.canceled()).isEmpty
        assertThat(messageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofSucceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchResult =
            MessageBatchResult.ofSucceeded(
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
            )

        val roundtrippedMessageBatchResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchResult),
                jacksonTypeRef<MessageBatchResult>(),
            )

        assertThat(roundtrippedMessageBatchResult).isEqualTo(messageBatchResult)
    }

    @Test
    fun ofErrored() {
        val errored =
            MessageBatchErroredResult.builder()
                .error(ErrorResponse.builder().invalidRequestErrorError("message").build())
                .build()

        val messageBatchResult = MessageBatchResult.ofErrored(errored)

        assertThat(messageBatchResult.succeeded()).isEmpty
        assertThat(messageBatchResult.errored()).contains(errored)
        assertThat(messageBatchResult.canceled()).isEmpty
        assertThat(messageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofErroredRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchResult =
            MessageBatchResult.ofErrored(
                MessageBatchErroredResult.builder()
                    .error(ErrorResponse.builder().invalidRequestErrorError("message").build())
                    .build()
            )

        val roundtrippedMessageBatchResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchResult),
                jacksonTypeRef<MessageBatchResult>(),
            )

        assertThat(roundtrippedMessageBatchResult).isEqualTo(messageBatchResult)
    }

    @Test
    fun ofCanceled() {
        val canceled = MessageBatchCanceledResult.builder().build()

        val messageBatchResult = MessageBatchResult.ofCanceled(canceled)

        assertThat(messageBatchResult.succeeded()).isEmpty
        assertThat(messageBatchResult.errored()).isEmpty
        assertThat(messageBatchResult.canceled()).contains(canceled)
        assertThat(messageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofCanceledRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchResult =
            MessageBatchResult.ofCanceled(MessageBatchCanceledResult.builder().build())

        val roundtrippedMessageBatchResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchResult),
                jacksonTypeRef<MessageBatchResult>(),
            )

        assertThat(roundtrippedMessageBatchResult).isEqualTo(messageBatchResult)
    }

    @Test
    fun ofExpired() {
        val expired = MessageBatchExpiredResult.builder().build()

        val messageBatchResult = MessageBatchResult.ofExpired(expired)

        assertThat(messageBatchResult.succeeded()).isEmpty
        assertThat(messageBatchResult.errored()).isEmpty
        assertThat(messageBatchResult.canceled()).isEmpty
        assertThat(messageBatchResult.expired()).contains(expired)
    }

    @Test
    fun ofExpiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchResult =
            MessageBatchResult.ofExpired(MessageBatchExpiredResult.builder().build())

        val roundtrippedMessageBatchResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchResult),
                jacksonTypeRef<MessageBatchResult>(),
            )

        assertThat(roundtrippedMessageBatchResult).isEqualTo(messageBatchResult)
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
        val messageBatchResult =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<MessageBatchResult>())

        val e = assertThrows<AnthropicInvalidDataException> { messageBatchResult.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
