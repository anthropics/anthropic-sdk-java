package com.anthropic.models.organization.federation.issuers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IssuerCreateParamsTest {

    @Test
    fun create() {
        IssuerCreateParams.builder()
            .issuerUrl("https://token.actions.githubusercontent.com")
            .name("github-actions")
            .checkJti(true)
            .jwks(
                JwksDiscovery.builder()
                    .caCertPem("ca_cert_pem")
                    .discoveryBase("discovery_base")
                    .build()
            )
            .maxJwtLifetimeSeconds(1L)
            .build()
    }

    @Test
    fun body() {
        val params =
            IssuerCreateParams.builder()
                .issuerUrl("https://token.actions.githubusercontent.com")
                .name("github-actions")
                .checkJti(true)
                .jwks(
                    JwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
                .maxJwtLifetimeSeconds(1L)
                .build()

        val body = params._body()

        assertThat(body.issuerUrl()).isEqualTo("https://token.actions.githubusercontent.com")
        assertThat(body.name()).isEqualTo("github-actions")
        assertThat(body.checkJti()).contains(true)
        assertThat(body.jwks())
            .contains(
                IssuerCreateParams.Jwks.ofDiscovery(
                    JwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
            )
        assertThat(body.maxJwtLifetimeSeconds()).contains(1L)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            IssuerCreateParams.builder()
                .issuerUrl("https://token.actions.githubusercontent.com")
                .name("github-actions")
                .build()

        val body = params._body()

        assertThat(body.issuerUrl()).isEqualTo("https://token.actions.githubusercontent.com")
        assertThat(body.name()).isEqualTo("github-actions")
    }
}
