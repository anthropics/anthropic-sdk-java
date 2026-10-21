package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthPostResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthPostResponse =
            BetaManagedAgentsTokenEndpointAuthPostResponse.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthPostResponse =
            BetaManagedAgentsTokenEndpointAuthPostResponse.builder().build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthPostResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthPostResponse),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthPostResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthPostResponse)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthPostResponse)
    }
}
