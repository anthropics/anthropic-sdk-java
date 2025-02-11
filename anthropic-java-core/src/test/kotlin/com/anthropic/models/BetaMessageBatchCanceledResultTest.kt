// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchCanceledResultTest {

    @Test
    fun createBetaMessageBatchCanceledResult() {
        val betaMessageBatchCanceledResult = BetaMessageBatchCanceledResult.builder().build()
        assertThat(betaMessageBatchCanceledResult).isNotNull
    }
}
