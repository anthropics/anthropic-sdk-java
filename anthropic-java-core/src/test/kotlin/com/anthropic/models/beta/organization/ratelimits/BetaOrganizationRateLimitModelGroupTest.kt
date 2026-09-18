package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationRateLimitModelGroupTest {

    @Test
    fun create() {
        val betaOrganizationRateLimitModelGroup =
            BetaOrganizationRateLimitModelGroup.builder()
                .id("id")
                .displayName("display_name")
                .build()

        assertThat(betaOrganizationRateLimitModelGroup.id()).isEqualTo("id")
        assertThat(betaOrganizationRateLimitModelGroup.displayName()).isEqualTo("display_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationRateLimitModelGroup =
            BetaOrganizationRateLimitModelGroup.builder()
                .id("id")
                .displayName("display_name")
                .build()

        val roundtrippedBetaOrganizationRateLimitModelGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationRateLimitModelGroup),
                jacksonTypeRef<BetaOrganizationRateLimitModelGroup>(),
            )

        assertThat(roundtrippedBetaOrganizationRateLimitModelGroup)
            .isEqualTo(betaOrganizationRateLimitModelGroup)
    }
}
