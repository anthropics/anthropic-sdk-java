// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaLimitedNetworkParamsTest {

    @Test
    fun create() {
        val betaLimitedNetworkParams =
            BetaLimitedNetworkParams.builder()
                .allowMcpServers(true)
                .allowPackageManagers(true)
                .addAllowedHost("string")
                .build()

        assertThat(betaLimitedNetworkParams.allowMcpServers()).contains(true)
        assertThat(betaLimitedNetworkParams.allowPackageManagers()).contains(true)
        assertThat(betaLimitedNetworkParams.allowedHosts().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaLimitedNetworkParams =
            BetaLimitedNetworkParams.builder()
                .allowMcpServers(true)
                .allowPackageManagers(true)
                .addAllowedHost("string")
                .build()

        val roundtrippedBetaLimitedNetworkParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaLimitedNetworkParams),
                jacksonTypeRef<BetaLimitedNetworkParams>(),
            )

        assertThat(roundtrippedBetaLimitedNetworkParams).isEqualTo(betaLimitedNetworkParams)
    }
}
