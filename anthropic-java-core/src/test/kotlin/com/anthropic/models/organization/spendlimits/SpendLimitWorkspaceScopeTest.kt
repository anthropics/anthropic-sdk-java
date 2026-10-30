package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitWorkspaceScopeTest {

    @Test
    fun create() {
        val spendLimitWorkspaceScope =
            SpendLimitWorkspaceScope.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        assertThat(spendLimitWorkspaceScope.workspaceId())
            .isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitWorkspaceScope =
            SpendLimitWorkspaceScope.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        val roundtrippedSpendLimitWorkspaceScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitWorkspaceScope),
                jacksonTypeRef<SpendLimitWorkspaceScope>(),
            )

        assertThat(roundtrippedSpendLimitWorkspaceScope).isEqualTo(spendLimitWorkspaceScope)
    }
}
