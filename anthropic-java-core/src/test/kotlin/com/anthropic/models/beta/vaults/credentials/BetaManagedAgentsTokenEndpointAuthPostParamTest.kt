package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthPostParamTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthPostParam =
            BetaManagedAgentsTokenEndpointAuthPostParam.of("x")

        assertThat(betaManagedAgentsTokenEndpointAuthPostParam.clientSecret()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthPostParam =
            BetaManagedAgentsTokenEndpointAuthPostParam.of("x")

        val roundtrippedBetaManagedAgentsTokenEndpointAuthPostParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthPostParam),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthPostParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthPostParam)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthPostParam)
    }
}
