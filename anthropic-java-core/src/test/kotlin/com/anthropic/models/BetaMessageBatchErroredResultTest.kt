// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchErroredResultTest {

    @Test
    fun createBetaMessageBatchErroredResult() {
        val betaMessageBatchErroredResult =
            BetaMessageBatchErroredResult.builder()
                .error(
                    BetaErrorResponse.builder()
                        .invalidRequestError("message")
                        .type(BetaErrorResponse.Type.ERROR)
                        .build()
                )
                .type(BetaMessageBatchErroredResult.Type.ERRORED)
                .build()
        assertThat(betaMessageBatchErroredResult).isNotNull
        assertThat(betaMessageBatchErroredResult.error())
            .isEqualTo(
                BetaErrorResponse.builder()
                    .invalidRequestError("message")
                    .type(BetaErrorResponse.Type.ERROR)
                    .build()
            )
        assertThat(betaMessageBatchErroredResult.type())
            .isEqualTo(BetaMessageBatchErroredResult.Type.ERRORED)
    }
}
