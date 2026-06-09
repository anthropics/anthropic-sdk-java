// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsLimitedCredentialNetworkingParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsLimitedCredentialNetworkingParams =
            BetaManagedAgentsLimitedCredentialNetworkingParams.builder()
                .addAllowedHost("string")
                .type(BetaManagedAgentsLimitedCredentialNetworkingParams.Type.LIMITED)
                .build()

        assertThat(betaManagedAgentsLimitedCredentialNetworkingParams.allowedHosts())
            .containsExactly("string")
        assertThat(betaManagedAgentsLimitedCredentialNetworkingParams.type())
            .isEqualTo(BetaManagedAgentsLimitedCredentialNetworkingParams.Type.LIMITED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsLimitedCredentialNetworkingParams =
            BetaManagedAgentsLimitedCredentialNetworkingParams.builder()
                .addAllowedHost("string")
                .type(BetaManagedAgentsLimitedCredentialNetworkingParams.Type.LIMITED)
                .build()

        val roundtrippedBetaManagedAgentsLimitedCredentialNetworkingParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsLimitedCredentialNetworkingParams),
                jacksonTypeRef<BetaManagedAgentsLimitedCredentialNetworkingParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsLimitedCredentialNetworkingParams)
            .isEqualTo(betaManagedAgentsLimitedCredentialNetworkingParams)
    }
}
