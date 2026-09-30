package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitOrganizationServiceScopeTest {

    @Test
    fun create() {
        val betaSpendLimitOrganizationServiceScope =
            BetaSpendLimitOrganizationServiceScope.of("service")

        assertThat(betaSpendLimitOrganizationServiceScope.service()).isEqualTo("service")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitOrganizationServiceScope =
            BetaSpendLimitOrganizationServiceScope.of("service")

        val roundtrippedBetaSpendLimitOrganizationServiceScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitOrganizationServiceScope),
                jacksonTypeRef<BetaSpendLimitOrganizationServiceScope>(),
            )

        assertThat(roundtrippedBetaSpendLimitOrganizationServiceScope)
            .isEqualTo(betaSpendLimitOrganizationServiceScope)
    }
}
