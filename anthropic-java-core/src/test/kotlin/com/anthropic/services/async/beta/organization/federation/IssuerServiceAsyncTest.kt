// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.federation

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.federation.issuers.BetaJwksDiscovery
import com.anthropic.models.beta.organization.federation.issuers.IssuerArchiveParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerCreateParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerRetrieveParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class IssuerServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerServiceAsync = client.beta().organization().federation().issuers()

        val betaFederationIssuerFuture =
            issuerServiceAsync.create(
                IssuerCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .issuerUrl("x")
                    .name("x")
                    .checkJti(true)
                    .jwks(
                        BetaJwksDiscovery.builder()
                            .caCertPem("ca_cert_pem")
                            .discoveryBase("discovery_base")
                            .build()
                    )
                    .maxJwtLifetimeSeconds(1L)
                    .build()
            )

        val betaFederationIssuer = betaFederationIssuerFuture.get()
        betaFederationIssuer.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerServiceAsync = client.beta().organization().federation().issuers()

        val betaFederationIssuerFuture =
            issuerServiceAsync.retrieve(
                IssuerRetrieveParams.builder()
                    .federationIssuerId("federation_issuer_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaFederationIssuer = betaFederationIssuerFuture.get()
        betaFederationIssuer.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerServiceAsync = client.beta().organization().federation().issuers()

        val betaFederationIssuerFuture =
            issuerServiceAsync.update(
                IssuerUpdateParams.builder()
                    .federationIssuerId("federation_issuer_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .checkJti(true)
                    .issuerUrl("x")
                    .jwks(
                        BetaJwksDiscovery.builder()
                            .caCertPem("ca_cert_pem")
                            .discoveryBase("discovery_base")
                            .build()
                    )
                    .jwksPollingDisabled(true)
                    .maxJwtLifetimeSeconds(1L)
                    .name("x")
                    .build()
            )

        val betaFederationIssuer = betaFederationIssuerFuture.get()
        betaFederationIssuer.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerServiceAsync = client.beta().organization().federation().issuers()

        val pageFuture = issuerServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerServiceAsync = client.beta().organization().federation().issuers()

        val betaFederationIssuerFuture =
            issuerServiceAsync.archive(
                IssuerArchiveParams.builder()
                    .federationIssuerId("federation_issuer_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaFederationIssuer = betaFederationIssuerFuture.get()
        betaFederationIssuer.validate()
    }
}
