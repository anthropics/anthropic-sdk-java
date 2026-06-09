// File generated from our OpenAPI spec by Stainless.

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
                .type(BetaManagedAgentsLimitedCredentialNetworkingResponse.Type.LIMITED)
                .build()

        assertThat(betaManagedAgentsLimitedCredentialNetworkingResponse.allowedHosts())
            .containsExactly("string")
        assertThat(betaManagedAgentsLimitedCredentialNetworkingResponse.type())
            .isEqualTo(BetaManagedAgentsLimitedCredentialNetworkingResponse.Type.LIMITED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsLimitedCredentialNetworkingResponse =
            BetaManagedAgentsLimitedCredentialNetworkingResponse.builder()
                .addAllowedHost("string")
                .type(BetaManagedAgentsLimitedCredentialNetworkingResponse.Type.LIMITED)
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
