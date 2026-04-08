// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthBasicParamTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthBasicParam =
            BetaManagedAgentsTokenEndpointAuthBasicParam.builder()
                .clientSecret("x")
                .type(BetaManagedAgentsTokenEndpointAuthBasicParam.Type.CLIENT_SECRET_BASIC)
                .build()

        assertThat(betaManagedAgentsTokenEndpointAuthBasicParam.clientSecret()).isEqualTo("x")
        assertThat(betaManagedAgentsTokenEndpointAuthBasicParam.type())
            .isEqualTo(BetaManagedAgentsTokenEndpointAuthBasicParam.Type.CLIENT_SECRET_BASIC)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthBasicParam =
            BetaManagedAgentsTokenEndpointAuthBasicParam.builder()
                .clientSecret("x")
                .type(BetaManagedAgentsTokenEndpointAuthBasicParam.Type.CLIENT_SECRET_BASIC)
                .build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthBasicParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthBasicParam),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthBasicParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthBasicParam)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthBasicParam)
    }
}
