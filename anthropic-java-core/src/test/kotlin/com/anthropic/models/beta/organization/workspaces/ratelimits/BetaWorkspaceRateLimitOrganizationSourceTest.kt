package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkspaceRateLimitOrganizationSourceTest {

    @Test
    fun create() {
        val betaWorkspaceRateLimitOrganizationSource =
            BetaWorkspaceRateLimitOrganizationSource.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkspaceRateLimitOrganizationSource =
            BetaWorkspaceRateLimitOrganizationSource.builder().build()

        val roundtrippedBetaWorkspaceRateLimitOrganizationSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkspaceRateLimitOrganizationSource),
                jacksonTypeRef<BetaWorkspaceRateLimitOrganizationSource>(),
            )

        assertThat(roundtrippedBetaWorkspaceRateLimitOrganizationSource)
            .isEqualTo(betaWorkspaceRateLimitOrganizationSource)
    }
}
