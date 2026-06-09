// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnrestrictedCredentialNetworkingResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsUnrestrictedCredentialNetworkingResponse =
            BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder()
                .type(BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.Type.UNRESTRICTED)
                .build()

        assertThat(betaManagedAgentsUnrestrictedCredentialNetworkingResponse.type())
            .isEqualTo(BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.Type.UNRESTRICTED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnrestrictedCredentialNetworkingResponse =
            BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder()
                .type(BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.Type.UNRESTRICTED)
                .build()

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
