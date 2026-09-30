package com.anthropic.models.organization.federation.issuers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IssuerUpdateParamsTest {

    @Test
    fun create() {
        IssuerUpdateParams.builder()
            .federationIssuerId("federation_issuer_id")
            .checkJti(true)
            .issuerUrl("x")
            .jwks(
                JwksDiscovery.builder()
                    .caCertPem("ca_cert_pem")
                    .discoveryBase("discovery_base")
                    .build()
            )
            .jwksPollingDisabled(true)
            .maxJwtLifetimeSeconds(1L)
            .name("x")
            .build()
    }

    @Test
    fun pathParams() {
        val params = IssuerUpdateParams.builder().federationIssuerId("federation_issuer_id").build()

        assertThat(params._pathParam(0)).isEqualTo("federation_issuer_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            IssuerUpdateParams.builder()
                .federationIssuerId("federation_issuer_id")
                .checkJti(true)
                .issuerUrl("x")
                .jwks(
                    JwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
                .jwksPollingDisabled(true)
                .maxJwtLifetimeSeconds(1L)
                .name("x")
                .build()

        val body = params._body()

        assertThat(body.checkJti()).contains(true)
        assertThat(body.issuerUrl()).contains("x")
        assertThat(body.jwks())
            .contains(
                IssuerUpdateParams.Jwks.ofDiscovery(
                    JwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
            )
        assertThat(body.jwksPollingDisabled()).contains(true)
        assertThat(body.maxJwtLifetimeSeconds()).contains(1L)
        assertThat(body.name()).contains("x")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = IssuerUpdateParams.builder().federationIssuerId("federation_issuer_id").build()

        val body = params._body()
    }
}
