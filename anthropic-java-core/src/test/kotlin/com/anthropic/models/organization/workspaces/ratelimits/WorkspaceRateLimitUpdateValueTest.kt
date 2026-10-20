package com.anthropic.models.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRateLimitUpdateValueTest {

    @Test
    fun create() {
        val workspaceRateLimitUpdateValue =
            WorkspaceRateLimitUpdateValue.builder()
                .type(WorkspaceRateLimitType.FAST_ITPMCA)
                .value(0L)
                .build()

        assertThat(workspaceRateLimitUpdateValue.type())
            .isEqualTo(WorkspaceRateLimitType.FAST_ITPMCA)
        assertThat(workspaceRateLimitUpdateValue.value()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceRateLimitUpdateValue =
            WorkspaceRateLimitUpdateValue.builder()
                .type(WorkspaceRateLimitType.FAST_ITPMCA)
                .value(0L)
                .build()

        val roundtrippedWorkspaceRateLimitUpdateValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceRateLimitUpdateValue),
                jacksonTypeRef<WorkspaceRateLimitUpdateValue>(),
            )

        assertThat(roundtrippedWorkspaceRateLimitUpdateValue)
            .isEqualTo(workspaceRateLimitUpdateValue)
    }
}
