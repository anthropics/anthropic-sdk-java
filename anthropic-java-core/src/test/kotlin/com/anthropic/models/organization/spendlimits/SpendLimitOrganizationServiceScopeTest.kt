package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitOrganizationServiceScopeTest {

    @Test
    fun create() {
        val spendLimitOrganizationServiceScope = SpendLimitOrganizationServiceScope.of("service")

        assertThat(spendLimitOrganizationServiceScope.service()).isEqualTo("service")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitOrganizationServiceScope = SpendLimitOrganizationServiceScope.of("service")

        val roundtrippedSpendLimitOrganizationServiceScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitOrganizationServiceScope),
                jacksonTypeRef<SpendLimitOrganizationServiceScope>(),
            )

        assertThat(roundtrippedSpendLimitOrganizationServiceScope)
            .isEqualTo(spendLimitOrganizationServiceScope)
    }
}
