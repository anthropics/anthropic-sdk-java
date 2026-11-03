package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitsAdminApiWritesNotEnabledErrorDetailsTest {

    @Test
    fun create() {
        val betaSpendLimitsAdminApiWritesNotEnabledErrorDetails =
            BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitsAdminApiWritesNotEnabledErrorDetails =
            BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails.builder().build()

        val roundtrippedBetaSpendLimitsAdminApiWritesNotEnabledErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitsAdminApiWritesNotEnabledErrorDetails),
                jacksonTypeRef<BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails>(),
            )

        assertThat(roundtrippedBetaSpendLimitsAdminApiWritesNotEnabledErrorDetails)
            .isEqualTo(betaSpendLimitsAdminApiWritesNotEnabledErrorDetails)
    }
}
