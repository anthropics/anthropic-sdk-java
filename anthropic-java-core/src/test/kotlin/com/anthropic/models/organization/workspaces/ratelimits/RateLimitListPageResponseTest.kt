package com.anthropic.models.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.ratelimits.OrganizationRateLimitModelGroup
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateLimitListPageResponseTest {

    @Test
    fun create() {
        val rateLimitListPageResponse =
            RateLimitListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(rateLimitListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(rateLimitListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rateLimitListPageResponse =
            RateLimitListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedRateLimitListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rateLimitListPageResponse),
                jacksonTypeRef<RateLimitListPageResponse>(),
            )

        assertThat(roundtrippedRateLimitListPageResponse).isEqualTo(rateLimitListPageResponse)
    }
}
