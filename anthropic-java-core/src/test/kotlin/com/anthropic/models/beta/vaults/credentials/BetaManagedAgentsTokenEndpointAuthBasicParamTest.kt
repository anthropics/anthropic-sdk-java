package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthBasicParamTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthBasicParam =
            BetaManagedAgentsTokenEndpointAuthBasicParam.of("x")

        assertThat(betaManagedAgentsTokenEndpointAuthBasicParam.clientSecret()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthBasicParam =
            BetaManagedAgentsTokenEndpointAuthBasicParam.of("x")

        val roundtrippedBetaManagedAgentsTokenEndpointAuthBasicParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthBasicParam),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthBasicParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthBasicParam)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthBasicParam)
    }
}
