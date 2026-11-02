package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitOrganizationScopeTest {

    @Test
    fun create() {
        val spendLimitOrganizationScope = SpendLimitOrganizationScope.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitOrganizationScope = SpendLimitOrganizationScope.builder().build()

        val roundtrippedSpendLimitOrganizationScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitOrganizationScope),
                jacksonTypeRef<SpendLimitOrganizationScope>(),
            )

        assertThat(roundtrippedSpendLimitOrganizationScope).isEqualTo(spendLimitOrganizationScope)
    }
}
