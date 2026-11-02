package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitSeatTierScopeTest {

    @Test
    fun create() {
        val spendLimitSeatTierScope = SpendLimitSeatTierScope.of("seat_tier")

        assertThat(spendLimitSeatTierScope.seatTier()).isEqualTo("seat_tier")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitSeatTierScope = SpendLimitSeatTierScope.of("seat_tier")

        val roundtrippedSpendLimitSeatTierScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitSeatTierScope),
                jacksonTypeRef<SpendLimitSeatTierScope>(),
            )

        assertThat(roundtrippedSpendLimitSeatTierScope).isEqualTo(spendLimitSeatTierScope)
    }
}
