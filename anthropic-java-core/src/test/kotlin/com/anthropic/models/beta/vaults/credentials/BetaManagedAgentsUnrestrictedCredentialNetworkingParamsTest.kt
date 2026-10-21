package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnrestrictedCredentialNetworkingParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUnrestrictedCredentialNetworkingParams =
            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnrestrictedCredentialNetworkingParams =
            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder().build()

        val roundtrippedBetaManagedAgentsUnrestrictedCredentialNetworkingParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsUnrestrictedCredentialNetworkingParams
                ),
                jacksonTypeRef<BetaManagedAgentsUnrestrictedCredentialNetworkingParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUnrestrictedCredentialNetworkingParams)
            .isEqualTo(betaManagedAgentsUnrestrictedCredentialNetworkingParams)
    }
}
