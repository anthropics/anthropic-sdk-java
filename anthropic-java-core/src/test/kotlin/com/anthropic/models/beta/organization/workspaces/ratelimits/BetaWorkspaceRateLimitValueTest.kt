// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkspaceRateLimitValueTest {

    @Test
    fun create() {
        val betaWorkspaceRateLimitValue =
            BetaWorkspaceRateLimitValue.builder().orgLimit(0L).type("type").value(0L).build()

        assertThat(betaWorkspaceRateLimitValue.orgLimit()).contains(0L)
        assertThat(betaWorkspaceRateLimitValue.type()).isEqualTo("type")
        assertThat(betaWorkspaceRateLimitValue.value()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkspaceRateLimitValue =
            BetaWorkspaceRateLimitValue.builder().orgLimit(0L).type("type").value(0L).build()

        val roundtrippedBetaWorkspaceRateLimitValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkspaceRateLimitValue),
                jacksonTypeRef<BetaWorkspaceRateLimitValue>(),
            )

        assertThat(roundtrippedBetaWorkspaceRateLimitValue).isEqualTo(betaWorkspaceRateLimitValue)
    }
}
