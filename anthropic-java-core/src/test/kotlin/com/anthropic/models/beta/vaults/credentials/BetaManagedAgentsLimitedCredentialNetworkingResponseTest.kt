package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsLimitedCredentialNetworkingResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsLimitedCredentialNetworkingResponse =
            BetaManagedAgentsLimitedCredentialNetworkingResponse.builder()
                .addAllowedHost("string")
                .build()

        assertThat(betaManagedAgentsLimitedCredentialNetworkingResponse.allowedHosts())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsLimitedCredentialNetworkingResponse =
            BetaManagedAgentsLimitedCredentialNetworkingResponse.builder()
                .addAllowedHost("string")
                .build()

        val roundtrippedBetaManagedAgentsLimitedCredentialNetworkingResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsLimitedCredentialNetworkingResponse),
                jacksonTypeRef<BetaManagedAgentsLimitedCredentialNetworkingResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsLimitedCredentialNetworkingResponse)
            .isEqualTo(betaManagedAgentsLimitedCredentialNetworkingResponse)
    }
}
