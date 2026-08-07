// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.jsonMapper
import com.anthropic.models.ErrorResponse
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchErroredResultTest {

    @Test
    fun create() {
        val messageBatchErroredResult =
            MessageBatchErroredResult.of(
                ErrorResponse.builder()
                    .invalidRequestErrorError("message")
                    .requestId("request_id")
                    .build()
            )

        assertThat(messageBatchErroredResult.error())
            .isEqualTo(
                ErrorResponse.builder()
                    .invalidRequestErrorError("message")
                    .requestId("request_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchErroredResult =
            MessageBatchErroredResult.of(
                ErrorResponse.builder()
                    .invalidRequestErrorError("message")
                    .requestId("request_id")
                    .build()
            )

        val roundtrippedMessageBatchErroredResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchErroredResult),
                jacksonTypeRef<MessageBatchErroredResult>(),
            )

        assertThat(roundtrippedMessageBatchErroredResult).isEqualTo(messageBatchErroredResult)
    }
}
