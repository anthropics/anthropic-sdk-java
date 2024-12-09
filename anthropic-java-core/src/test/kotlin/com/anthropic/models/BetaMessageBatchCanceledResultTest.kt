// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchCanceledResultTest {

    @Test
    fun createBetaMessageBatchCanceledResult() {
        val betaMessageBatchCanceledResult =
            BetaMessageBatchCanceledResult.builder()
                .type(BetaMessageBatchCanceledResult.Type.CANCELED)
                .build()
        assertThat(betaMessageBatchCanceledResult).isNotNull
        assertThat(betaMessageBatchCanceledResult.type())
            .isEqualTo(BetaMessageBatchCanceledResult.Type.CANCELED)
    }
}
