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
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder().build()
                )
                .secretName("B")
                .secretValue("secret_value")
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
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder().build()
                )
            )
        assertThat(betaManagedAgentsEnvironmentVariableCreateParams.secretName()).isEqualTo("B")
        assertThat(betaManagedAgentsEnvironmentVariableCreateParams.secretValue())
            .isEqualTo("secret_value")
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
                    BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder().build()
                )
                .secretName("B")
                .secretValue("secret_value")
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
