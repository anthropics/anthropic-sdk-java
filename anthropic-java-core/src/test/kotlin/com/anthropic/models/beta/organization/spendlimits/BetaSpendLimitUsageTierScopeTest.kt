package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitUsageTierScopeTest {

    @Test
    fun create() {
        val betaSpendLimitUsageTierScope = BetaSpendLimitUsageTierScope.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitUsageTierScope = BetaSpendLimitUsageTierScope.builder().build()

        val roundtrippedBetaSpendLimitUsageTierScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitUsageTierScope),
                jacksonTypeRef<BetaSpendLimitUsageTierScope>(),
            )

        assertThat(roundtrippedBetaSpendLimitUsageTierScope).isEqualTo(betaSpendLimitUsageTierScope)
    }
}
