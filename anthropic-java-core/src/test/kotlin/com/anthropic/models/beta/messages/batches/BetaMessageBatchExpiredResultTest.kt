// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageBatchExpiredResultTest {

    @Test
    fun create() {
        val betaMessageBatchExpiredResult = BetaMessageBatchExpiredResult.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageBatchExpiredResult = BetaMessageBatchExpiredResult.builder().build()

        val roundtrippedBetaMessageBatchExpiredResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageBatchExpiredResult),
                jacksonTypeRef<BetaMessageBatchExpiredResult>(),
            )

        assertThat(roundtrippedBetaMessageBatchExpiredResult)
            .isEqualTo(betaMessageBatchExpiredResult)
    }
}
