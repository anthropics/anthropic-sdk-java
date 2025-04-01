// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedMessageBatchTest {

    @Test
    fun create() {
        val betaDeletedMessageBatch =
            BetaDeletedMessageBatch.builder().id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF").build()

        assertThat(betaDeletedMessageBatch.id()).isEqualTo("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedMessageBatch =
            BetaDeletedMessageBatch.builder().id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF").build()

        val roundtrippedBetaDeletedMessageBatch =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedMessageBatch),
                jacksonTypeRef<BetaDeletedMessageBatch>(),
            )

        assertThat(roundtrippedBetaDeletedMessageBatch).isEqualTo(betaDeletedMessageBatch)
    }
}
