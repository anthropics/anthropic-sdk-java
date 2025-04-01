// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeletedMessageBatchTest {

    @Test
    fun create() {
        val deletedMessageBatch =
            DeletedMessageBatch.builder().id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF").build()

        assertThat(deletedMessageBatch.id()).isEqualTo("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deletedMessageBatch =
            DeletedMessageBatch.builder().id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF").build()

        val roundtrippedDeletedMessageBatch =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deletedMessageBatch),
                jacksonTypeRef<DeletedMessageBatch>(),
            )

        assertThat(roundtrippedDeletedMessageBatch).isEqualTo(deletedMessageBatch)
    }
}
