package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationRateLimitModelGroupTest {

    @Test
    fun create() {
        val organizationRateLimitModelGroup =
            OrganizationRateLimitModelGroup.builder().id("id").displayName("display_name").build()

        assertThat(organizationRateLimitModelGroup.id()).isEqualTo("id")
        assertThat(organizationRateLimitModelGroup.displayName()).isEqualTo("display_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationRateLimitModelGroup =
            OrganizationRateLimitModelGroup.builder().id("id").displayName("display_name").build()

        val roundtrippedOrganizationRateLimitModelGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationRateLimitModelGroup),
                jacksonTypeRef<OrganizationRateLimitModelGroup>(),
            )

        assertThat(roundtrippedOrganizationRateLimitModelGroup)
            .isEqualTo(organizationRateLimitModelGroup)
    }
}
