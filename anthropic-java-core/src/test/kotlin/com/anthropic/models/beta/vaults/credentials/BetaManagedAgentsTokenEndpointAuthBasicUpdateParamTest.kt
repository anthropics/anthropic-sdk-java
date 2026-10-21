package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthBasicUpdateParamTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthBasicUpdateParam =
            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder().clientSecret("x").build()

        assertThat(betaManagedAgentsTokenEndpointAuthBasicUpdateParam.clientSecret()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthBasicUpdateParam =
            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder().clientSecret("x").build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthBasicUpdateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthBasicUpdateParam),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthBasicUpdateParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthBasicUpdateParam)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthBasicUpdateParam)
    }
}
