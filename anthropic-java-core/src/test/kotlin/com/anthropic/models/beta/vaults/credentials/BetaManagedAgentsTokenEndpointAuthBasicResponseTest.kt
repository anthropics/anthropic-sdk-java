package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthBasicResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthBasicResponse =
            BetaManagedAgentsTokenEndpointAuthBasicResponse.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthBasicResponse =
            BetaManagedAgentsTokenEndpointAuthBasicResponse.builder().build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthBasicResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthBasicResponse),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthBasicResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthBasicResponse)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthBasicResponse)
    }
}
