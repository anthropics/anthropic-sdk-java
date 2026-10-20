package com.anthropic.models.beta.organization.workspaces.ratelimits

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateLimitUpdateParamsTest {

    @Test
    fun create() {
        RateLimitUpdateParams.builder()
            .workspaceId("workspace_id")
            .rateLimitId("rate_limit_id")
            .addLimit(
                BetaWorkspaceRateLimitUpdateValue.builder()
                    .type(BetaWorkspaceRateLimitType.FAST_ITPMCA)
                    .value(0L)
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            RateLimitUpdateParams.builder()
                .workspaceId("workspace_id")
                .rateLimitId("rate_limit_id")
                .addLimit(
                    BetaWorkspaceRateLimitUpdateValue.builder()
                        .type(BetaWorkspaceRateLimitType.FAST_ITPMCA)
                        .value(0L)
                        .build()
                )
                .build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        assertThat(params._pathParam(1)).isEqualTo("rate_limit_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            RateLimitUpdateParams.builder()
                .workspaceId("workspace_id")
                .rateLimitId("rate_limit_id")
                .addLimit(
                    BetaWorkspaceRateLimitUpdateValue.builder()
                        .type(BetaWorkspaceRateLimitType.FAST_ITPMCA)
                        .value(0L)
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.limits())
            .containsExactly(
                BetaWorkspaceRateLimitUpdateValue.builder()
                    .type(BetaWorkspaceRateLimitType.FAST_ITPMCA)
                    .value(0L)
                    .build()
            )
    }
}
