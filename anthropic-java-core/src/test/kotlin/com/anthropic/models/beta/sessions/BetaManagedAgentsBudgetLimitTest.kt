package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.BetaCurrency
import com.anthropic.models.beta.BetaMonetaryAmount
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsBudgetLimitTest {

    @Test
    fun create() {
        val betaManagedAgentsBudgetLimit =
            BetaManagedAgentsBudgetLimit.of(
                BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
            )

        assertThat(betaManagedAgentsBudgetLimit.maxListCost())
            .isEqualTo(
                BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsBudgetLimit =
            BetaManagedAgentsBudgetLimit.of(
                BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
            )

        val roundtrippedBetaManagedAgentsBudgetLimit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsBudgetLimit),
                jacksonTypeRef<BetaManagedAgentsBudgetLimit>(),
            )

        assertThat(roundtrippedBetaManagedAgentsBudgetLimit).isEqualTo(betaManagedAgentsBudgetLimit)
    }
}
