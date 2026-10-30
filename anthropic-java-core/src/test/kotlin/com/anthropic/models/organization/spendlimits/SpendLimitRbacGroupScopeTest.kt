package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitRbacGroupScopeTest {

    @Test
    fun create() {
        val spendLimitRbacGroupScope = SpendLimitRbacGroupScope.of("rbac_group_id")

        assertThat(spendLimitRbacGroupScope.rbacGroupId()).isEqualTo("rbac_group_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitRbacGroupScope = SpendLimitRbacGroupScope.of("rbac_group_id")

        val roundtrippedSpendLimitRbacGroupScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitRbacGroupScope),
                jacksonTypeRef<SpendLimitRbacGroupScope>(),
            )

        assertThat(roundtrippedSpendLimitRbacGroupScope).isEqualTo(spendLimitRbacGroupScope)
    }
}
