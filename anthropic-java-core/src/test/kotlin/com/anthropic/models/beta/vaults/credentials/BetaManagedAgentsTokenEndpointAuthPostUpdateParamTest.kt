// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthPostUpdateParamTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthPostUpdateParam =
            BetaManagedAgentsTokenEndpointAuthPostUpdateParam.builder()
                .type(BetaManagedAgentsTokenEndpointAuthPostUpdateParam.Type.CLIENT_SECRET_POST)
                .clientSecret("x")
                .build()

        assertThat(betaManagedAgentsTokenEndpointAuthPostUpdateParam.type())
            .isEqualTo(BetaManagedAgentsTokenEndpointAuthPostUpdateParam.Type.CLIENT_SECRET_POST)
        assertThat(betaManagedAgentsTokenEndpointAuthPostUpdateParam.clientSecret()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthPostUpdateParam =
            BetaManagedAgentsTokenEndpointAuthPostUpdateParam.builder()
                .type(BetaManagedAgentsTokenEndpointAuthPostUpdateParam.Type.CLIENT_SECRET_POST)
                .clientSecret("x")
                .build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthPostUpdateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthPostUpdateParam),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthPostUpdateParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthPostUpdateParam)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthPostUpdateParam)
    }
}
