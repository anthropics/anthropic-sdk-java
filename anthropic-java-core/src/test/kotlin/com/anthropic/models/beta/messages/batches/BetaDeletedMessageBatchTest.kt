// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedMessageBatchTest {

    @Test
    fun create() {
        val betaDeletedMessageBatch =
            BetaDeletedMessageBatch.builder().id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF").build()

        assertThat(betaDeletedMessageBatch.id()).isEqualTo("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
    }
}
