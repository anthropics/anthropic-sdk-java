// File generated from our OpenAPI spec by Stainless.

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
                    BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
                .secretName("secret_name")
                .type(BetaManagedAgentsEnvironmentVariableAuthResponse.Type.ENVIRONMENT_VARIABLE)
                .build()

        assertThat(betaManagedAgentsEnvironmentVariableAuthResponse.injectionLocation())
            .isEqualTo(
                BetaManagedAgentsInjectionLocationResponse.builder().body(true).header(true).build()
            )
        assertThat(betaManagedAgentsEnvironmentVariableAuthResponse.networking())
            .isEqualTo(
                BetaManagedAgentsEnvironmentVariableAuthResponse.Networking.ofUnrestricted(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
            )
        assertThat(betaManagedAgentsEnvironmentVariableAuthResponse.secretName())
            .isEqualTo("secret_name")
        assertThat(betaManagedAgentsEnvironmentVariableAuthResponse.type())
            .isEqualTo(BetaManagedAgentsEnvironmentVariableAuthResponse.Type.ENVIRONMENT_VARIABLE)
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
                    BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingResponse.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
                .secretName("secret_name")
                .type(BetaManagedAgentsEnvironmentVariableAuthResponse.Type.ENVIRONMENT_VARIABLE)
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
