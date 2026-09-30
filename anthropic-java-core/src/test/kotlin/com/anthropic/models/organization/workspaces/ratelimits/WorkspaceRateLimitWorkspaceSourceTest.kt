package com.anthropic.models.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRateLimitWorkspaceSourceTest {

    @Test
    fun create() {
        val workspaceRateLimitWorkspaceSource = WorkspaceRateLimitWorkspaceSource.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceRateLimitWorkspaceSource = WorkspaceRateLimitWorkspaceSource.builder().build()

        val roundtrippedWorkspaceRateLimitWorkspaceSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceRateLimitWorkspaceSource),
                jacksonTypeRef<WorkspaceRateLimitWorkspaceSource>(),
            )

        assertThat(roundtrippedWorkspaceRateLimitWorkspaceSource)
            .isEqualTo(workspaceRateLimitWorkspaceSource)
    }
}
