package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationRateLimitTest {

    @Test
    fun create() {
        val betaOrganizationRateLimit =
            BetaOrganizationRateLimit.builder()
                .id("id")
                .group(
                    BetaOrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
                .groupType(BetaOrganizationRateLimit.GroupType.BATCH)
                .addLimit(BetaOrganizationRateLimitValue.builder().type("type").value(0L).build())
                .addModel("string")
                .build()

        assertThat(betaOrganizationRateLimit.id()).isEqualTo("id")
        assertThat(betaOrganizationRateLimit.group())
            .isEqualTo(
                BetaOrganizationRateLimit.Group.ofModel(
                    BetaOrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
            )
        assertThat(betaOrganizationRateLimit.groupType())
            .isEqualTo(BetaOrganizationRateLimit.GroupType.BATCH)
        assertThat(betaOrganizationRateLimit.limits())
            .containsExactly(
                BetaOrganizationRateLimitValue.builder().type("type").value(0L).build()
            )
        assertThat(betaOrganizationRateLimit.models().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationRateLimit =
            BetaOrganizationRateLimit.builder()
                .id("id")
                .group(
                    BetaOrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
                .groupType(BetaOrganizationRateLimit.GroupType.BATCH)
                .addLimit(BetaOrganizationRateLimitValue.builder().type("type").value(0L).build())
                .addModel("string")
                .build()

        val roundtrippedBetaOrganizationRateLimit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationRateLimit),
                jacksonTypeRef<BetaOrganizationRateLimit>(),
            )

        assertThat(roundtrippedBetaOrganizationRateLimit).isEqualTo(betaOrganizationRateLimit)
    }
}
