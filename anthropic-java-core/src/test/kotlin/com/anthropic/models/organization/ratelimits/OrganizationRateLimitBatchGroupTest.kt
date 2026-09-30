package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationRateLimitBatchGroupTest {

    @Test
    fun create() {
        val organizationRateLimitBatchGroup = OrganizationRateLimitBatchGroup.of("id")

        assertThat(organizationRateLimitBatchGroup.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationRateLimitBatchGroup = OrganizationRateLimitBatchGroup.of("id")

        val roundtrippedOrganizationRateLimitBatchGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationRateLimitBatchGroup),
                jacksonTypeRef<OrganizationRateLimitBatchGroup>(),
            )

        assertThat(roundtrippedOrganizationRateLimitBatchGroup)
            .isEqualTo(organizationRateLimitBatchGroup)
    }
}
