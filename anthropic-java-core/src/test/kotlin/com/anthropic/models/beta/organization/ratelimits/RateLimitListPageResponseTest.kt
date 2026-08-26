// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.ratelimits

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
                    BetaOrganizationRateLimit.builder()
                        .id("id")
                        .groupType(BetaOrganizationRateLimit.GroupType.BATCH)
                        .addLimit(
                            BetaOrganizationRateLimitValue.builder().type("type").value(0L).build()
                        )
                        .addModel("string")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(rateLimitListPageResponse.data())
            .containsExactly(
                BetaOrganizationRateLimit.builder()
                    .id("id")
                    .groupType(BetaOrganizationRateLimit.GroupType.BATCH)
                    .addLimit(
                        BetaOrganizationRateLimitValue.builder().type("type").value(0L).build()
                    )
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
                    BetaOrganizationRateLimit.builder()
                        .id("id")
                        .groupType(BetaOrganizationRateLimit.GroupType.BATCH)
                        .addLimit(
                            BetaOrganizationRateLimitValue.builder().type("type").value(0L).build()
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
