package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationRateLimitTokenCountGroupTest {

    @Test
    fun create() {
        val organizationRateLimitTokenCountGroup = OrganizationRateLimitTokenCountGroup.of("id")

        assertThat(organizationRateLimitTokenCountGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationRateLimitTokenCountGroup = OrganizationRateLimitTokenCountGroup.of("id")

        val roundtrippedOrganizationRateLimitTokenCountGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationRateLimitTokenCountGroup),
                jacksonTypeRef<OrganizationRateLimitTokenCountGroup>(),
            )

        assertThat(roundtrippedOrganizationRateLimitTokenCountGroup)
            .isEqualTo(organizationRateLimitTokenCountGroup)
    }
}
