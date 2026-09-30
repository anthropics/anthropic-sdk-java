package com.anthropic.models.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateLimitListPageResponseTest {

    @Test
    fun create() {
        val rateLimitListPageResponse =
            RateLimitListPageResponse.builder()
                .addData(
                    OrganizationRateLimit.builder()
                        .id("id")
                        .group(
                            OrganizationRateLimitModelGroup.builder()
                                .id("id")
                                .displayName("display_name")
                                .build()
                        )
                        .addLimit(
                            OrganizationRateLimitValue.builder().type("type").value(0L).build()
                        )
                        .addModel("string")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(rateLimitListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(rateLimitListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rateLimitListPageResponse =
            RateLimitListPageResponse.builder()
                .addData(
                    OrganizationRateLimit.builder()
                        .id("id")
                        .group(
                            OrganizationRateLimitModelGroup.builder()
                                .id("id")
                                .displayName("display_name")
                                .build()
                        )
                        .addLimit(
                            OrganizationRateLimitValue.builder().type("type").value(0L).build()
                        )
                        .addModel("string")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedRateLimitListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rateLimitListPageResponse),
                jacksonTypeRef<RateLimitListPageResponse>(),
            )

        assertThat(roundtrippedRateLimitListPageResponse).isEqualTo(rateLimitListPageResponse)
    }
}
