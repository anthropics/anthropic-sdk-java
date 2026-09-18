package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.ratelimits.BetaOrganizationRateLimitModelGroup
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateLimitListPageResponseTest {

    @Test
    fun create() {
        val rateLimitListPageResponse =
            RateLimitListPageResponse.builder()
                .addData(
                    BetaWorkspaceRateLimit.builder()
                        .group(
                            BetaOrganizationRateLimitModelGroup.builder()
                                .id("id")
                                .displayName("display_name")
                                .build()
                        )
                        .groupType(BetaWorkspaceRateLimit.GroupType.BATCH)
                        .addLimit(
                            BetaWorkspaceRateLimitValue.builder()
                                .orgLimit(0L)
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
                BetaWorkspaceRateLimit.builder()
                    .group(
                        BetaOrganizationRateLimitModelGroup.builder()
                            .id("id")
                            .displayName("display_name")
                            .build()
                    )
                    .groupType(BetaWorkspaceRateLimit.GroupType.BATCH)
                    .addLimit(
                        BetaWorkspaceRateLimitValue.builder()
                            .orgLimit(0L)
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
                    BetaWorkspaceRateLimit.builder()
                        .group(
                            BetaOrganizationRateLimitModelGroup.builder()
                                .id("id")
                                .displayName("display_name")
                                .build()
                        )
                        .groupType(BetaWorkspaceRateLimit.GroupType.BATCH)
                        .addLimit(
                            BetaWorkspaceRateLimitValue.builder()
                                .orgLimit(0L)
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
