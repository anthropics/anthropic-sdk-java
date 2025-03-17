// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.models.beta.BetaErrorResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageBatchErroredResultTest {

    @Test
    fun createBetaMessageBatchErroredResult() {
        val betaMessageBatchErroredResult =
            BetaMessageBatchErroredResult.builder()
                .error(BetaErrorResponse.builder().invalidRequestError("message").build())
                .build()
        assertThat(betaMessageBatchErroredResult).isNotNull
        assertThat(betaMessageBatchErroredResult.error())
            .isEqualTo(BetaErrorResponse.builder().invalidRequestError("message").build())
    }
}
