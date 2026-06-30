// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentVariableCreateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentVariableCreateParams =
            BetaManagedAgentsEnvironmentVariableCreateParams.builder()
                .networking(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
                .secretName("x")
                .secretValue("x")
                .type(BetaManagedAgentsEnvironmentVariableCreateParams.Type.ENVIRONMENT_VARIABLE)
                .injectionLocation(
                    BetaManagedAgentsInjectionLocationParams.builder()
                        .body(true)
                        .header(true)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsEnvironmentVariableCreateParams.networking())
            .isEqualTo(
                BetaManagedAgentsCredentialNetworkingParams.ofUnrestricted(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
            )
        assertThat(betaManagedAgentsEnvironmentVariableCreateParams.secretName()).isEqualTo("x")
        assertThat(betaManagedAgentsEnvironmentVariableCreateParams.secretValue()).isEqualTo("x")
        assertThat(betaManagedAgentsEnvironmentVariableCreateParams.type())
            .isEqualTo(BetaManagedAgentsEnvironmentVariableCreateParams.Type.ENVIRONMENT_VARIABLE)
        assertThat(betaManagedAgentsEnvironmentVariableCreateParams.injectionLocation())
            .contains(
                BetaManagedAgentsInjectionLocationParams.builder().body(true).header(true).build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentVariableCreateParams =
            BetaManagedAgentsEnvironmentVariableCreateParams.builder()
                .networking(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder()
                        .type(
                            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type
                                .UNRESTRICTED
                        )
                        .build()
                )
                .secretName("x")
                .secretValue("x")
                .type(BetaManagedAgentsEnvironmentVariableCreateParams.Type.ENVIRONMENT_VARIABLE)
                .injectionLocation(
                    BetaManagedAgentsInjectionLocationParams.builder()
                        .body(true)
                        .header(true)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsEnvironmentVariableCreateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEnvironmentVariableCreateParams),
                jacksonTypeRef<BetaManagedAgentsEnvironmentVariableCreateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentVariableCreateParams)
            .isEqualTo(betaManagedAgentsEnvironmentVariableCreateParams)
    }
}
