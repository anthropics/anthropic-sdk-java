package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationRateLimitTest {

    @Test
    fun create() {
        val organizationRateLimit =
            OrganizationRateLimit.builder()
                .id("id")
                .group(
                    OrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
                .addLimit(OrganizationRateLimitValue.builder().type("type").value(0L).build())
                .addModel("string")
                .build()

        assertThat(organizationRateLimit.id()).isEqualTo("id")
        assertThat(organizationRateLimit.group())
            .isEqualTo(
                OrganizationRateLimit.Group.ofModel(
                    OrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
            )
        assertThat(organizationRateLimit.limits())
            .containsExactly(OrganizationRateLimitValue.builder().type("type").value(0L).build())
        assertThat(organizationRateLimit.models().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationRateLimit =
            OrganizationRateLimit.builder()
                .id("id")
                .group(
                    OrganizationRateLimitModelGroup.builder()
                        .id("id")
                        .displayName("display_name")
                        .build()
                )
                .addLimit(OrganizationRateLimitValue.builder().type("type").value(0L).build())
                .addModel("string")
                .build()

        val roundtrippedOrganizationRateLimit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationRateLimit),
                jacksonTypeRef<OrganizationRateLimit>(),
            )

        assertThat(roundtrippedOrganizationRateLimit).isEqualTo(organizationRateLimit)
    }
}
