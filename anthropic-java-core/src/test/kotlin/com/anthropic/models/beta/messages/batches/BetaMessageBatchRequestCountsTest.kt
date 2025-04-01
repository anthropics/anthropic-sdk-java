// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageBatchRequestCountsTest {

    @Test
    fun create() {
        val betaMessageBatchRequestCounts =
            BetaMessageBatchRequestCounts.builder()
                .canceled(10L)
                .errored(30L)
                .expired(10L)
                .processing(100L)
                .succeeded(50L)
                .build()

        assertThat(betaMessageBatchRequestCounts.canceled()).isEqualTo(10L)
        assertThat(betaMessageBatchRequestCounts.errored()).isEqualTo(30L)
        assertThat(betaMessageBatchRequestCounts.expired()).isEqualTo(10L)
        assertThat(betaMessageBatchRequestCounts.processing()).isEqualTo(100L)
        assertThat(betaMessageBatchRequestCounts.succeeded()).isEqualTo(50L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageBatchRequestCounts =
            BetaMessageBatchRequestCounts.builder()
                .canceled(10L)
                .errored(30L)
                .expired(10L)
                .processing(100L)
                .succeeded(50L)
                .build()

        val roundtrippedBetaMessageBatchRequestCounts =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchRequestCounts),
                jacksonTypeRef<BetaMessageBatchRequestCounts>(),
            )

        assertThat(roundtrippedBetaMessageBatchRequestCounts)
            .isEqualTo(betaMessageBatchRequestCounts)
    }
}
