package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnrestrictedCredentialNetworkingResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsUnrestrictedCredentialNetworkingResponse =
            BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnrestrictedCredentialNetworkingResponse =
            BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder().build()

        val roundtrippedBetaManagedAgentsUnrestrictedCredentialNetworkingResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsUnrestrictedCredentialNetworkingResponse
                ),
                jacksonTypeRef<BetaManagedAgentsUnrestrictedCredentialNetworkingResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUnrestrictedCredentialNetworkingResponse)
            .isEqualTo(betaManagedAgentsUnrestrictedCredentialNetworkingResponse)
    }
}
