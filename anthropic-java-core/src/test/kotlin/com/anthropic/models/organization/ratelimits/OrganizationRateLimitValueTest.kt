package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationRateLimitValueTest {

    @Test
    fun create() {
        val organizationRateLimitValue =
            OrganizationRateLimitValue.builder().type("type").value(0L).build()

        assertThat(organizationRateLimitValue.type()).isEqualTo("type")
        assertThat(organizationRateLimitValue.value()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationRateLimitValue =
            OrganizationRateLimitValue.builder().type("type").value(0L).build()

        val roundtrippedOrganizationRateLimitValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationRateLimitValue),
                jacksonTypeRef<OrganizationRateLimitValue>(),
            )

        assertThat(roundtrippedOrganizationRateLimitValue).isEqualTo(organizationRateLimitValue)
    }
}
