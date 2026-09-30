package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationRateLimitFilesGroupTest {

    @Test
    fun create() {
        val organizationRateLimitFilesGroup = OrganizationRateLimitFilesGroup.of("id")

        assertThat(organizationRateLimitFilesGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationRateLimitFilesGroup = OrganizationRateLimitFilesGroup.of("id")

        val roundtrippedOrganizationRateLimitFilesGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationRateLimitFilesGroup),
                jacksonTypeRef<OrganizationRateLimitFilesGroup>(),
            )

        assertThat(roundtrippedOrganizationRateLimitFilesGroup)
            .isEqualTo(organizationRateLimitFilesGroup)
    }
}
