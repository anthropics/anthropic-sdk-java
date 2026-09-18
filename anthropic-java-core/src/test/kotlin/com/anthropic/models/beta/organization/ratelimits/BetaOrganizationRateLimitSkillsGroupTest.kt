package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationRateLimitSkillsGroupTest {

    @Test
    fun create() {
        val betaOrganizationRateLimitSkillsGroup = BetaOrganizationRateLimitSkillsGroup.of("id")

        assertThat(betaOrganizationRateLimitSkillsGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationRateLimitSkillsGroup = BetaOrganizationRateLimitSkillsGroup.of("id")

        val roundtrippedBetaOrganizationRateLimitSkillsGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationRateLimitSkillsGroup),
                jacksonTypeRef<BetaOrganizationRateLimitSkillsGroup>(),
            )

        assertThat(roundtrippedBetaOrganizationRateLimitSkillsGroup)
            .isEqualTo(betaOrganizationRateLimitSkillsGroup)
    }
}
