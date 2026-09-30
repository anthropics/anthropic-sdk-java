package com.anthropic.models.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JwksDiscoveryTest {

    @Test
    fun create() {
        val jwksDiscovery =
            JwksDiscovery.builder().caCertPem("ca_cert_pem").discoveryBase("discovery_base").build()

        assertThat(jwksDiscovery.caCertPem()).contains("ca_cert_pem")
        assertThat(jwksDiscovery.discoveryBase()).contains("discovery_base")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val jwksDiscovery =
            JwksDiscovery.builder().caCertPem("ca_cert_pem").discoveryBase("discovery_base").build()

        val roundtrippedJwksDiscovery =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(jwksDiscovery),
                jacksonTypeRef<JwksDiscovery>(),
            )

        assertThat(roundtrippedJwksDiscovery).isEqualTo(jwksDiscovery)
    }
}
