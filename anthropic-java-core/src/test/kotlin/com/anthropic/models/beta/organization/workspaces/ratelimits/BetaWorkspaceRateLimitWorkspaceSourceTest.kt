package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkspaceRateLimitWorkspaceSourceTest {

    @Test
    fun create() {
        val betaWorkspaceRateLimitWorkspaceSource =
            BetaWorkspaceRateLimitWorkspaceSource.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkspaceRateLimitWorkspaceSource =
            BetaWorkspaceRateLimitWorkspaceSource.builder().build()

        val roundtrippedBetaWorkspaceRateLimitWorkspaceSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkspaceRateLimitWorkspaceSource),
                jacksonTypeRef<BetaWorkspaceRateLimitWorkspaceSource>(),
            )

        assertThat(roundtrippedBetaWorkspaceRateLimitWorkspaceSource)
            .isEqualTo(betaWorkspaceRateLimitWorkspaceSource)
    }
}
