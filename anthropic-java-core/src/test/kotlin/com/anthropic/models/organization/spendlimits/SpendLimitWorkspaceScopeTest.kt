package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitWorkspaceScopeTest {

    @Test
    fun create() {
        val spendLimitWorkspaceScope =
            SpendLimitWorkspaceScope.of("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")

        assertThat(spendLimitWorkspaceScope.workspaceId())
            .isEqualTo("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitWorkspaceScope =
            SpendLimitWorkspaceScope.of("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")

        val roundtrippedSpendLimitWorkspaceScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitWorkspaceScope),
                jacksonTypeRef<SpendLimitWorkspaceScope>(),
            )

        assertThat(roundtrippedSpendLimitWorkspaceScope).isEqualTo(spendLimitWorkspaceScope)
    }
}
