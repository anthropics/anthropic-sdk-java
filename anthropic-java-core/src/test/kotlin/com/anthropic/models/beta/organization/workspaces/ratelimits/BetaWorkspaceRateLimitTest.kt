// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkspaceRateLimitTest {

    @Test
    fun create() {
        val betaWorkspaceRateLimit =
            BetaWorkspaceRateLimit.builder()
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

        assertThat(betaWorkspaceRateLimit.groupType())
            .isEqualTo(BetaWorkspaceRateLimit.GroupType.BATCH)
        assertThat(betaWorkspaceRateLimit.limits())
            .containsExactly(
                BetaWorkspaceRateLimitValue.builder().orgLimit(0L).type("type").value(0L).build()
            )
        assertThat(betaWorkspaceRateLimit.models().getOrNull()).containsExactly("string")
        assertThat(betaWorkspaceRateLimit.rateLimitId()).isEqualTo("rate_limit_id")
        assertThat(betaWorkspaceRateLimit.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkspaceRateLimit =
            BetaWorkspaceRateLimit.builder()
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

        val roundtrippedBetaWorkspaceRateLimit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkspaceRateLimit),
                jacksonTypeRef<BetaWorkspaceRateLimit>(),
            )

        assertThat(roundtrippedBetaWorkspaceRateLimit).isEqualTo(betaWorkspaceRateLimit)
    }
}
