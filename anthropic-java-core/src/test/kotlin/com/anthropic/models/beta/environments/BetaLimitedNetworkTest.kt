// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaLimitedNetworkTest {

    @Test
    fun create() {
        val betaLimitedNetwork =
            BetaLimitedNetwork.builder()
                .allowMcpServers(true)
                .allowPackageManagers(true)
                .addAllowedHost("string")
                .build()

        assertThat(betaLimitedNetwork.allowMcpServers()).isEqualTo(true)
        assertThat(betaLimitedNetwork.allowPackageManagers()).isEqualTo(true)
        assertThat(betaLimitedNetwork.allowedHosts()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaLimitedNetwork =
            BetaLimitedNetwork.builder()
                .allowMcpServers(true)
                .allowPackageManagers(true)
                .addAllowedHost("string")
                .build()

        val roundtrippedBetaLimitedNetwork =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaLimitedNetwork),
                jacksonTypeRef<BetaLimitedNetwork>(),
            )

        assertThat(roundtrippedBetaLimitedNetwork).isEqualTo(betaLimitedNetwork)
    }
}
