// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnrestrictedCredentialNetworkingParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUnrestrictedCredentialNetworkingParams =
            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.of(
                BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type.UNRESTRICTED
            )

        assertThat(betaManagedAgentsUnrestrictedCredentialNetworkingParams.type())
            .isEqualTo(BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type.UNRESTRICTED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnrestrictedCredentialNetworkingParams =
            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.of(
                BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type.UNRESTRICTED
            )

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
