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
                .injectionLocation(
                    BetaManagedAgentsInjectionLocationUpdateParams.builder()
                        .body(true)
                        .header(true)
                        .build()
                )
                .networking(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder().build()
                )
                .secretValue("x")
                .build()

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
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder().build()
                )
            )
        assertThat(betaManagedAgentsEnvironmentVariableUpdateParams.secretValue()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentVariableUpdateParams =
            BetaManagedAgentsEnvironmentVariableUpdateParams.builder()
                .injectionLocation(
                    BetaManagedAgentsInjectionLocationUpdateParams.builder()
                        .body(true)
                        .header(true)
                        .build()
                )
                .networking(
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder().build()
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
