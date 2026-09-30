package com.anthropic.models.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRateLimitValueTest {

    @Test
    fun create() {
        val workspaceRateLimitValue =
            WorkspaceRateLimitValue.builder()
                .orgLimit(0L)
                .source(WorkspaceRateLimitWorkspaceSource.builder().build())
                .type("type")
                .value(0L)
                .build()

        assertThat(workspaceRateLimitValue.orgLimit()).contains(0L)
        assertThat(workspaceRateLimitValue.source())
            .isEqualTo(
                WorkspaceRateLimitValue.Source.ofWorkspace(
                    WorkspaceRateLimitWorkspaceSource.builder().build()
                )
            )
        assertThat(workspaceRateLimitValue.type()).isEqualTo("type")
        assertThat(workspaceRateLimitValue.value()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceRateLimitValue =
            WorkspaceRateLimitValue.builder()
                .orgLimit(0L)
                .source(WorkspaceRateLimitWorkspaceSource.builder().build())
                .type("type")
                .value(0L)
                .build()

        val roundtrippedWorkspaceRateLimitValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceRateLimitValue),
                jacksonTypeRef<WorkspaceRateLimitValue>(),
            )

        assertThat(roundtrippedWorkspaceRateLimitValue).isEqualTo(workspaceRateLimitValue)
    }
}
