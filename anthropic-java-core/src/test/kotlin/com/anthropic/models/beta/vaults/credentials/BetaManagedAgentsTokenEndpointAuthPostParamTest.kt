// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthPostParamTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthPostParam =
            BetaManagedAgentsTokenEndpointAuthPostParam.builder()
                .clientSecret("x")
                .type(BetaManagedAgentsTokenEndpointAuthPostParam.Type.CLIENT_SECRET_POST)
                .build()

        assertThat(betaManagedAgentsTokenEndpointAuthPostParam.clientSecret()).isEqualTo("x")
        assertThat(betaManagedAgentsTokenEndpointAuthPostParam.type())
            .isEqualTo(BetaManagedAgentsTokenEndpointAuthPostParam.Type.CLIENT_SECRET_POST)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthPostParam =
            BetaManagedAgentsTokenEndpointAuthPostParam.builder()
                .clientSecret("x")
                .type(BetaManagedAgentsTokenEndpointAuthPostParam.Type.CLIENT_SECRET_POST)
                .build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthPostParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthPostParam),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthPostParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthPostParam)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthPostParam)
    }
}
