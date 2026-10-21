package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentVariableAuthResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentVariableAuthResponse =
            BetaManagedAgentsEnvironmentVariableAuthResponse.builder()
                .injectionLocation(
                    BetaManagedAgentsInjectionLocationResponse.builder()
                        .body(true)
                        .header(true)
                        .build()
                )
                .networking(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder().build()
                )
                .secretName("secret_name")
                .build()

        assertThat(betaManagedAgentsEnvironmentVariableAuthResponse.injectionLocation())
            .isEqualTo(
                BetaManagedAgentsInjectionLocationResponse.builder().body(true).header(true).build()
            )
        assertThat(betaManagedAgentsEnvironmentVariableAuthResponse.networking())
            .isEqualTo(
                BetaManagedAgentsEnvironmentVariableAuthResponse.Networking.ofUnrestricted(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder().build()
                )
            )
        assertThat(betaManagedAgentsEnvironmentVariableAuthResponse.secretName())
            .isEqualTo("secret_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentVariableAuthResponse =
            BetaManagedAgentsEnvironmentVariableAuthResponse.builder()
                .injectionLocation(
                    BetaManagedAgentsInjectionLocationResponse.builder()
                        .body(true)
                        .header(true)
                        .build()
                )
                .networking(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder().build()
                )
                .secretName("secret_name")
                .build()

        val roundtrippedBetaManagedAgentsEnvironmentVariableAuthResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEnvironmentVariableAuthResponse),
                jacksonTypeRef<BetaManagedAgentsEnvironmentVariableAuthResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentVariableAuthResponse)
            .isEqualTo(betaManagedAgentsEnvironmentVariableAuthResponse)
    }
}
