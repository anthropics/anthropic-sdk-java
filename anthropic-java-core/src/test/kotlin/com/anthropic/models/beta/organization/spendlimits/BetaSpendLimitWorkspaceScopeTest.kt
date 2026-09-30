package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitWorkspaceScopeTest {

    @Test
    fun create() {
        val betaSpendLimitWorkspaceScope =
            BetaSpendLimitWorkspaceScope.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        assertThat(betaSpendLimitWorkspaceScope.workspaceId())
            .isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitWorkspaceScope =
            BetaSpendLimitWorkspaceScope.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        val roundtrippedBetaSpendLimitWorkspaceScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitWorkspaceScope),
                jacksonTypeRef<BetaSpendLimitWorkspaceScope>(),
            )

        assertThat(roundtrippedBetaSpendLimitWorkspaceScope).isEqualTo(betaSpendLimitWorkspaceScope)
    }
}
