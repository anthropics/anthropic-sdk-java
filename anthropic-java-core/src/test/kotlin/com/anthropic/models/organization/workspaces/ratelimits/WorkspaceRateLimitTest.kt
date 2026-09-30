package com.anthropic.models.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.ratelimits.OrganizationRateLimitModelGroup
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRateLimitTest {

    @Test
    fun create() {
        val workspaceRateLimit =
            WorkspaceRateLimit.builder()
                .group(
                    OrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
                .addLimit(
                    WorkspaceRateLimitValue.builder()
                        .orgLimit(0L)
                        .source(WorkspaceRateLimitWorkspaceSource.builder().build())
                        .type("type")
                        .value(0L)
                        .build()
                )
                .addModel("string")
                .rateLimitId("rate_limit_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(workspaceRateLimit.group())
            .isEqualTo(
                WorkspaceRateLimit.Group.ofModel(
                    OrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
            )
        assertThat(workspaceRateLimit.limits())
            .containsExactly(
                WorkspaceRateLimitValue.builder()
                    .orgLimit(0L)
                    .source(WorkspaceRateLimitWorkspaceSource.builder().build())
                    .type("type")
                    .value(0L)
                    .build()
            )
        assertThat(workspaceRateLimit.models().getOrNull()).containsExactly("string")
        assertThat(workspaceRateLimit.rateLimitId()).isEqualTo("rate_limit_id")
        assertThat(workspaceRateLimit.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceRateLimit =
            WorkspaceRateLimit.builder()
                .group(
                    OrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
                .addLimit(
                    WorkspaceRateLimitValue.builder()
                        .orgLimit(0L)
                        .source(WorkspaceRateLimitWorkspaceSource.builder().build())
                        .type("type")
                        .value(0L)
                        .build()
                )
                .addModel("string")
                .rateLimitId("rate_limit_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedWorkspaceRateLimit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceRateLimit),
                jacksonTypeRef<WorkspaceRateLimit>(),
            )

        assertThat(roundtrippedWorkspaceRateLimit).isEqualTo(workspaceRateLimit)
    }
}
