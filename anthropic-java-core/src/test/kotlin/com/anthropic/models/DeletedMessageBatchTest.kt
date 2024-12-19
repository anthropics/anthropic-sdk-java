// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeletedMessageBatchTest {

    @Test
    fun createDeletedMessageBatch() {
        val deletedMessageBatch =
            DeletedMessageBatch.builder()
                .id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
                .type(DeletedMessageBatch.Type.MESSAGE_BATCH_DELETED)
                .build()
        assertThat(deletedMessageBatch).isNotNull
        assertThat(deletedMessageBatch.id()).isEqualTo("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(deletedMessageBatch.type())
            .isEqualTo(DeletedMessageBatch.Type.MESSAGE_BATCH_DELETED)
    }
}