package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationRateLimitFilesGroupTest {

    @Test
    fun create() {
        val betaOrganizationRateLimitFilesGroup = BetaOrganizationRateLimitFilesGroup.of("id")

        assertThat(betaOrganizationRateLimitFilesGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationRateLimitFilesGroup = BetaOrganizationRateLimitFilesGroup.of("id")

        val roundtrippedBetaOrganizationRateLimitFilesGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationRateLimitFilesGroup),
                jacksonTypeRef<BetaOrganizationRateLimitFilesGroup>(),
            )

        assertThat(roundtrippedBetaOrganizationRateLimitFilesGroup)
            .isEqualTo(betaOrganizationRateLimitFilesGroup)
    }
}
