package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationRateLimitSkillsGroupTest {

    @Test
    fun create() {
        val organizationRateLimitSkillsGroup = OrganizationRateLimitSkillsGroup.of("id")

        assertThat(organizationRateLimitSkillsGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationRateLimitSkillsGroup = OrganizationRateLimitSkillsGroup.of("id")

        val roundtrippedOrganizationRateLimitSkillsGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationRateLimitSkillsGroup),
                jacksonTypeRef<OrganizationRateLimitSkillsGroup>(),
            )

        assertThat(roundtrippedOrganizationRateLimitSkillsGroup)
            .isEqualTo(organizationRateLimitSkillsGroup)
    }
}
