// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthBasicUpdateParamTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthBasicUpdateParam =
            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                .type(BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type.CLIENT_SECRET_BASIC)
                .clientSecret("x")
                .build()

        assertThat(betaManagedAgentsTokenEndpointAuthBasicUpdateParam.type())
            .isEqualTo(BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type.CLIENT_SECRET_BASIC)
        assertThat(betaManagedAgentsTokenEndpointAuthBasicUpdateParam.clientSecret()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthBasicUpdateParam =
            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                .type(BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type.CLIENT_SECRET_BASIC)
                .clientSecret("x")
                .build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthBasicUpdateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthBasicUpdateParam),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthBasicUpdateParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthBasicUpdateParam)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthBasicUpdateParam)
    }
}
