// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentVariableUpdateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentVariableUpdateParams =
            BetaManagedAgentsEnvironmentVariableUpdateParams.builder()
                .type(BetaManagedAgentsEnvironmentVariableUpdateParams.Type.ENVIRONMENT_VARIABLE)
                .injectionLocation(
                    BetaManagedAgentsInjectionLocationUpdateParams.builder()
                        .body(true)
                        .header(true)
                        .build()
                )
                .networking(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
                .secretValue("x")
                .build()

        assertThat(betaManagedAgentsEnvironmentVariableUpdateParams.type())
            .isEqualTo(BetaManagedAgentsEnvironmentVariableUpdateParams.Type.ENVIRONMENT_VARIABLE)
        assertThat(betaManagedAgentsEnvironmentVariableUpdateParams.injectionLocation())
            .contains(
                BetaManagedAgentsInjectionLocationUpdateParams.builder()
                    .body(true)
                    .header(true)
                    .build()
            )
        assertThat(betaManagedAgentsEnvironmentVariableUpdateParams.networking())
            .contains(
                BetaManagedAgentsCredentialNetworkingParams.ofUnrestricted(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
            )
        assertThat(betaManagedAgentsEnvironmentVariableUpdateParams.secretValue()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentVariableUpdateParams =
            BetaManagedAgentsEnvironmentVariableUpdateParams.builder()
                .type(BetaManagedAgentsEnvironmentVariableUpdateParams.Type.ENVIRONMENT_VARIABLE)
                .injectionLocation(
                    BetaManagedAgentsInjectionLocationUpdateParams.builder()
                        .body(true)
                        .header(true)
                        .build()
                )
                .networking(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
                .secretValue("x")
                .build()

        val roundtrippedBetaManagedAgentsEnvironmentVariableUpdateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEnvironmentVariableUpdateParams),
                jacksonTypeRef<BetaManagedAgentsEnvironmentVariableUpdateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentVariableUpdateParams)
            .isEqualTo(betaManagedAgentsEnvironmentVariableUpdateParams)
    }
}
