// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchCanceledResultTest {

    @Test
    fun create() {
        val messageBatchCanceledResult = MessageBatchCanceledResult.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchCanceledResult = MessageBatchCanceledResult.builder().build()

        val roundtrippedMessageBatchCanceledResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchCanceledResult),
                jacksonTypeRef<MessageBatchCanceledResult>(),
            )

        assertThat(roundtrippedMessageBatchCanceledResult).isEqualTo(messageBatchCanceledResult)
    }
}
