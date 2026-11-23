package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IssuerCreateParamsTest {

    @Test
    fun create() {
        IssuerCreateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .issuerUrl("https://token.actions.githubusercontent.com")
            .name("github-actions")
            .checkJti(true)
            .jwks(
                BetaJwksDiscovery.builder()
                    .caCertPem("ca_cert_pem")
                    .discoveryBase("discovery_base")
                    .build()
            )
            .maxJwtLifetimeSeconds(1L)
            .build()
    }

    @Test
    fun headers() {
        val params =
            IssuerCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .issuerUrl("https://token.actions.githubusercontent.com")
                .name("github-actions")
                .checkJti(true)
                .jwks(
                    BetaJwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
                .maxJwtLifetimeSeconds(1L)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            IssuerCreateParams.builder()
                .issuerUrl("https://token.actions.githubusercontent.com")
                .name("github-actions")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            IssuerCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .issuerUrl("https://token.actions.githubusercontent.com")
                .name("github-actions")
                .checkJti(true)
                .jwks(
                    BetaJwksDiscovery.builder()
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
                    BetaJwksDiscovery.builder()
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
