// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchRequestCountsTest {

    @Test
    fun createBetaMessageBatchRequestCounts() {
        val betaMessageBatchRequestCounts =
            BetaMessageBatchRequestCounts.builder()
                .canceled(10L)
                .errored(30L)
                .expired(10L)
                .processing(100L)
                .succeeded(50L)
                .build()
        assertThat(betaMessageBatchRequestCounts).isNotNull
        assertThat(betaMessageBatchRequestCounts.canceled()).isEqualTo(10L)
        assertThat(betaMessageBatchRequestCounts.errored()).isEqualTo(30L)
        assertThat(betaMessageBatchRequestCounts.expired()).isEqualTo(10L)
        assertThat(betaMessageBatchRequestCounts.processing()).isEqualTo(100L)
        assertThat(betaMessageBatchRequestCounts.succeeded()).isEqualTo(50L)
    }
}
