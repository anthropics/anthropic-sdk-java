// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaJwksDiscoveryTest {

    @Test
    fun create() {
        val betaJwksDiscovery =
            BetaJwksDiscovery.builder()
                .caCertPem("ca_cert_pem")
                .discoveryBase("discovery_base")
                .build()

        assertThat(betaJwksDiscovery.caCertPem()).contains("ca_cert_pem")
        assertThat(betaJwksDiscovery.discoveryBase()).contains("discovery_base")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaJwksDiscovery =
            BetaJwksDiscovery.builder()
                .caCertPem("ca_cert_pem")
                .discoveryBase("discovery_base")
                .build()

        val roundtrippedBetaJwksDiscovery =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaJwksDiscovery),
                jacksonTypeRef<BetaJwksDiscovery>(),
            )

        assertThat(roundtrippedBetaJwksDiscovery).isEqualTo(betaJwksDiscovery)
    }
}
