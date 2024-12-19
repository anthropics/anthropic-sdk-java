// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaDeletedMessageBatchTest {

    @Test
    fun createBetaDeletedMessageBatch() {
        val betaDeletedMessageBatch =
            BetaDeletedMessageBatch.builder()
                .id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
                .type(BetaDeletedMessageBatch.Type.MESSAGE_BATCH_DELETED)
                .build()
        assertThat(betaDeletedMessageBatch).isNotNull
        assertThat(betaDeletedMessageBatch.id()).isEqualTo("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(betaDeletedMessageBatch.type())
            .isEqualTo(BetaDeletedMessageBatch.Type.MESSAGE_BATCH_DELETED)
    }
}
