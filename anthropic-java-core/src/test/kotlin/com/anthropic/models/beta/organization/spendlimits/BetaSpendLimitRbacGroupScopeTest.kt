package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitRbacGroupScopeTest {

    @Test
    fun create() {
        val betaSpendLimitRbacGroupScope = BetaSpendLimitRbacGroupScope.of("rbac_group_id")

        assertThat(betaSpendLimitRbacGroupScope.rbacGroupId()).isEqualTo("rbac_group_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitRbacGroupScope = BetaSpendLimitRbacGroupScope.of("rbac_group_id")

        val roundtrippedBetaSpendLimitRbacGroupScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitRbacGroupScope),
                jacksonTypeRef<BetaSpendLimitRbacGroupScope>(),
            )

        assertThat(roundtrippedBetaSpendLimitRbacGroupScope).isEqualTo(betaSpendLimitRbacGroupScope)
    }
}
