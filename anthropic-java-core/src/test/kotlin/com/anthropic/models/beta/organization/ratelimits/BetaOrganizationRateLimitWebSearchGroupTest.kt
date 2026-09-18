package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationRateLimitWebSearchGroupTest {

    @Test
    fun create() {
        val betaOrganizationRateLimitWebSearchGroup =
            BetaOrganizationRateLimitWebSearchGroup.of("id")

        assertThat(betaOrganizationRateLimitWebSearchGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationRateLimitWebSearchGroup =
            BetaOrganizationRateLimitWebSearchGroup.of("id")

        val roundtrippedBetaOrganizationRateLimitWebSearchGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationRateLimitWebSearchGroup),
                jacksonTypeRef<BetaOrganizationRateLimitWebSearchGroup>(),
            )

        assertThat(roundtrippedBetaOrganizationRateLimitWebSearchGroup)
            .isEqualTo(betaOrganizationRateLimitWebSearchGroup)
    }
}
