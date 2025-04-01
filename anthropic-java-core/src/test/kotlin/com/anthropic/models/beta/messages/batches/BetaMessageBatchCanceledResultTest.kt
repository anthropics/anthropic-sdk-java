// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageBatchCanceledResultTest {

    @Test
    fun create() {
        val betaMessageBatchCanceledResult = BetaMessageBatchCanceledResult.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageBatchCanceledResult = BetaMessageBatchCanceledResult.builder().build()

        val roundtrippedBetaMessageBatchCanceledResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchCanceledResult),
                jacksonTypeRef<BetaMessageBatchCanceledResult>(),
            )

        assertThat(roundtrippedBetaMessageBatchCanceledResult)
            .isEqualTo(betaMessageBatchCanceledResult)
    }
}
