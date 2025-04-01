// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.BetaErrorResponse
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageBatchErroredResultTest {

    @Test
    fun create() {
        val betaMessageBatchErroredResult =
            BetaMessageBatchErroredResult.builder()
                .error(BetaErrorResponse.builder().invalidRequestError("message").build())
                .build()

        assertThat(betaMessageBatchErroredResult.error())
            .isEqualTo(BetaErrorResponse.builder().invalidRequestError("message").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageBatchErroredResult =
            BetaMessageBatchErroredResult.builder()
                .error(BetaErrorResponse.builder().invalidRequestError("message").build())
                .build()

        val roundtrippedBetaMessageBatchErroredResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchErroredResult),
                jacksonTypeRef<BetaMessageBatchErroredResult>(),
            )

        assertThat(roundtrippedBetaMessageBatchErroredResult)
            .isEqualTo(betaMessageBatchErroredResult)
    }
}
