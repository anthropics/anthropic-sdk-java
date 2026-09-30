package com.anthropic.models.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRateLimitOrganizationSourceTest {

    @Test
    fun create() {
        val workspaceRateLimitOrganizationSource =
            WorkspaceRateLimitOrganizationSource.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceRateLimitOrganizationSource =
            WorkspaceRateLimitOrganizationSource.builder().build()

        val roundtrippedWorkspaceRateLimitOrganizationSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceRateLimitOrganizationSource),
                jacksonTypeRef<WorkspaceRateLimitOrganizationSource>(),
            )

        assertThat(roundtrippedWorkspaceRateLimitOrganizationSource)
            .isEqualTo(workspaceRateLimitOrganizationSource)
    }
}
