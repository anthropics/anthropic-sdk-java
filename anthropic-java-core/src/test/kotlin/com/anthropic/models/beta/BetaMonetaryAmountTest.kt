// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMonetaryAmountTest {

    @Test
    fun create() {
        val betaMonetaryAmount =
            BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()

        assertThat(betaMonetaryAmount.amount()).isEqualTo("2500")
        assertThat(betaMonetaryAmount.currency()).isEqualTo(BetaCurrency.USD)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMonetaryAmount =
            BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()

        val roundtrippedBetaMonetaryAmount =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMonetaryAmount),
                jacksonTypeRef<BetaMonetaryAmount>(),
            )

        assertThat(roundtrippedBetaMonetaryAmount).isEqualTo(betaMonetaryAmount)
    }
}
