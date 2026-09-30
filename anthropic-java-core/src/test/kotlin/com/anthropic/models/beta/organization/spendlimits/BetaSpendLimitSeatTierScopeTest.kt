package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitSeatTierScopeTest {

    @Test
    fun create() {
        val betaSpendLimitSeatTierScope = BetaSpendLimitSeatTierScope.of("seat_tier")

        assertThat(betaSpendLimitSeatTierScope.seatTier()).isEqualTo("seat_tier")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitSeatTierScope = BetaSpendLimitSeatTierScope.of("seat_tier")

        val roundtrippedBetaSpendLimitSeatTierScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitSeatTierScope),
                jacksonTypeRef<BetaSpendLimitSeatTierScope>(),
            )

        assertThat(roundtrippedBetaSpendLimitSeatTierScope).isEqualTo(betaSpendLimitSeatTierScope)
    }
}
