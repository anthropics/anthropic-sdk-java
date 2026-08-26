// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationRateLimitValueTest {

    @Test
    fun create() {
        val betaOrganizationRateLimitValue =
            BetaOrganizationRateLimitValue.builder().type("type").value(0L).build()

        assertThat(betaOrganizationRateLimitValue.type()).isEqualTo("type")
        assertThat(betaOrganizationRateLimitValue.value()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationRateLimitValue =
            BetaOrganizationRateLimitValue.builder().type("type").value(0L).build()

        val roundtrippedBetaOrganizationRateLimitValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationRateLimitValue),
                jacksonTypeRef<BetaOrganizationRateLimitValue>(),
            )

        assertThat(roundtrippedBetaOrganizationRateLimitValue)
            .isEqualTo(betaOrganizationRateLimitValue)
    }
}
