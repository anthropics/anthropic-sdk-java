package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitOrganizationScopeTest {

    @Test
    fun create() {
        val betaSpendLimitOrganizationScope = BetaSpendLimitOrganizationScope.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitOrganizationScope = BetaSpendLimitOrganizationScope.builder().build()

        val roundtrippedBetaSpendLimitOrganizationScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitOrganizationScope),
                jacksonTypeRef<BetaSpendLimitOrganizationScope>(),
            )

        assertThat(roundtrippedBetaSpendLimitOrganizationScope)
            .isEqualTo(betaSpendLimitOrganizationScope)
    }
}
