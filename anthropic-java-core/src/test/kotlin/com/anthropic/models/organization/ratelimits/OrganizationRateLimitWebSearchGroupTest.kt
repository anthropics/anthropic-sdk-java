package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationRateLimitWebSearchGroupTest {

    @Test
    fun create() {
        val organizationRateLimitWebSearchGroup = OrganizationRateLimitWebSearchGroup.of("id")

        assertThat(organizationRateLimitWebSearchGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationRateLimitWebSearchGroup = OrganizationRateLimitWebSearchGroup.of("id")

        val roundtrippedOrganizationRateLimitWebSearchGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationRateLimitWebSearchGroup),
                jacksonTypeRef<OrganizationRateLimitWebSearchGroup>(),
            )

        assertThat(roundtrippedOrganizationRateLimitWebSearchGroup)
            .isEqualTo(organizationRateLimitWebSearchGroup)
    }
}
