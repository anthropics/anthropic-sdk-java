// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchExpiredResultTest {

    @Test
    fun create() {
        val messageBatchExpiredResult = MessageBatchExpiredResult.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageBatchExpiredResult = MessageBatchExpiredResult.builder().build()

        val roundtrippedMessageBatchExpiredResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageBatchExpiredResult),
                jacksonTypeRef<MessageBatchExpiredResult>(),
            )

        assertThat(roundtrippedMessageBatchExpiredResult).isEqualTo(messageBatchExpiredResult)
    }
}
