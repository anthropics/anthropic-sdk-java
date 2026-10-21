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
                .build()

        assertThat(betaManagedAgentsLimitedCredentialNetworkingParams.allowedHosts())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsLimitedCredentialNetworkingParams =
            BetaManagedAgentsLimitedCredentialNetworkingParams.builder()
                .addAllowedHost("string")
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
