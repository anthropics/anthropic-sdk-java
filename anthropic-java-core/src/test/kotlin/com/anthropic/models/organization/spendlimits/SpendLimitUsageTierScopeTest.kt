package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitUsageTierScopeTest {

    @Test
    fun create() {
        val spendLimitUsageTierScope = SpendLimitUsageTierScope.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitUsageTierScope = SpendLimitUsageTierScope.builder().build()

        val roundtrippedSpendLimitUsageTierScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitUsageTierScope),
                jacksonTypeRef<SpendLimitUsageTierScope>(),
            )

        assertThat(roundtrippedSpendLimitUsageTierScope).isEqualTo(spendLimitUsageTierScope)
    }
}
