package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkspaceRateLimitUpdateValueTest {

    @Test
    fun create() {
        val betaWorkspaceRateLimitUpdateValue =
            BetaWorkspaceRateLimitUpdateValue.builder()
                .type(BetaWorkspaceRateLimitType.FAST_ITPMCA)
                .value(0L)
                .build()

        assertThat(betaWorkspaceRateLimitUpdateValue.type())
            .isEqualTo(BetaWorkspaceRateLimitType.FAST_ITPMCA)
        assertThat(betaWorkspaceRateLimitUpdateValue.value()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkspaceRateLimitUpdateValue =
            BetaWorkspaceRateLimitUpdateValue.builder()
                .type(BetaWorkspaceRateLimitType.FAST_ITPMCA)
                .value(0L)
                .build()

        val roundtrippedBetaWorkspaceRateLimitUpdateValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkspaceRateLimitUpdateValue),
                jacksonTypeRef<BetaWorkspaceRateLimitUpdateValue>(),
            )

        assertThat(roundtrippedBetaWorkspaceRateLimitUpdateValue)
            .isEqualTo(betaWorkspaceRateLimitUpdateValue)
    }
}
