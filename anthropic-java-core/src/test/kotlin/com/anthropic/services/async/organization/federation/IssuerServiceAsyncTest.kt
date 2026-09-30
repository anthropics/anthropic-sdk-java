package com.anthropic.services.async.organization.federation

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.federation.issuers.IssuerCreateParams
import com.anthropic.models.organization.federation.issuers.IssuerUpdateParams
import com.anthropic.models.organization.federation.issuers.JwksDiscovery
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
        val issuerServiceAsync = client.organization().federation().issuers()

        val federationIssuerFuture =
            issuerServiceAsync.create(
                IssuerCreateParams.builder()
                    .issuerUrl("x")
                    .name("x")
                    .checkJti(true)
                    .jwks(
                        JwksDiscovery.builder()
                            .caCertPem("ca_cert_pem")
                            .discoveryBase("discovery_base")
                            .build()
                    )
                    .maxJwtLifetimeSeconds(1L)
                    .build()
            )

        val federationIssuer = federationIssuerFuture.get()
        federationIssuer.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerServiceAsync = client.organization().federation().issuers()

        val federationIssuerFuture = issuerServiceAsync.retrieve("federation_issuer_id")

        val federationIssuer = federationIssuerFuture.get()
        federationIssuer.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerServiceAsync = client.organization().federation().issuers()

        val federationIssuerFuture =
            issuerServiceAsync.update(
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
            )

        val federationIssuer = federationIssuerFuture.get()
        federationIssuer.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerServiceAsync = client.organization().federation().issuers()

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
        val issuerServiceAsync = client.organization().federation().issuers()

        val federationIssuerFuture = issuerServiceAsync.archive("federation_issuer_id")

        val federationIssuer = federationIssuerFuture.get()
        federationIssuer.validate()
    }
}
