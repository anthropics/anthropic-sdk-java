package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationRateLimitTokenCountGroupTest {

    @Test
    fun create() {
        val betaOrganizationRateLimitTokenCountGroup =
            BetaOrganizationRateLimitTokenCountGroup.of("id")

        assertThat(betaOrganizationRateLimitTokenCountGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationRateLimitTokenCountGroup =
            BetaOrganizationRateLimitTokenCountGroup.of("id")

        val roundtrippedBetaOrganizationRateLimitTokenCountGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationRateLimitTokenCountGroup),
                jacksonTypeRef<BetaOrganizationRateLimitTokenCountGroup>(),
            )

        assertThat(roundtrippedBetaOrganizationRateLimitTokenCountGroup)
            .isEqualTo(betaOrganizationRateLimitTokenCountGroup)
    }
}
