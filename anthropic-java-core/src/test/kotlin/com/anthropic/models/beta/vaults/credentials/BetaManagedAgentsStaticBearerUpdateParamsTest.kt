// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsStaticBearerUpdateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsStaticBearerUpdateParams =
            BetaManagedAgentsStaticBearerUpdateParams.builder()
                .type(BetaManagedAgentsStaticBearerUpdateParams.Type.STATIC_BEARER)
                .token("x")
                .build()

        assertThat(betaManagedAgentsStaticBearerUpdateParams.type())
            .isEqualTo(BetaManagedAgentsStaticBearerUpdateParams.Type.STATIC_BEARER)
        assertThat(betaManagedAgentsStaticBearerUpdateParams.token()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStaticBearerUpdateParams =
            BetaManagedAgentsStaticBearerUpdateParams.builder()
                .type(BetaManagedAgentsStaticBearerUpdateParams.Type.STATIC_BEARER)
                .token("x")
                .build()

        val roundtrippedBetaManagedAgentsStaticBearerUpdateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStaticBearerUpdateParams),
                jacksonTypeRef<BetaManagedAgentsStaticBearerUpdateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStaticBearerUpdateParams)
            .isEqualTo(betaManagedAgentsStaticBearerUpdateParams)
    }
}
