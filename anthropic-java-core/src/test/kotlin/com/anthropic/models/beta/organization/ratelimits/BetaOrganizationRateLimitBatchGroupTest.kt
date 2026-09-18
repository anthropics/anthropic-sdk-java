package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationRateLimitBatchGroupTest {

    @Test
    fun create() {
        val betaOrganizationRateLimitBatchGroup = BetaOrganizationRateLimitBatchGroup.of("id")

        assertThat(betaOrganizationRateLimitBatchGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationRateLimitBatchGroup = BetaOrganizationRateLimitBatchGroup.of("id")

        val roundtrippedBetaOrganizationRateLimitBatchGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationRateLimitBatchGroup),
                jacksonTypeRef<BetaOrganizationRateLimitBatchGroup>(),
            )

        assertThat(roundtrippedBetaOrganizationRateLimitBatchGroup)
            .isEqualTo(betaOrganizationRateLimitBatchGroup)
    }
}
