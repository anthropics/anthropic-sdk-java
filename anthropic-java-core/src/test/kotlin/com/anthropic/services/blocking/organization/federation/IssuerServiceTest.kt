package com.anthropic.services.blocking.organization.federation

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.federation.issuers.IssuerCreateParams
import com.anthropic.models.organization.federation.issuers.IssuerUpdateParams
import com.anthropic.models.organization.federation.issuers.JwksDiscovery
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class IssuerServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerService = client.organization().federation().issuers()

        val federationIssuer =
            issuerService.create(
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

        federationIssuer.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerService = client.organization().federation().issuers()

        val federationIssuer = issuerService.retrieve("federation_issuer_id")

        federationIssuer.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerService = client.organization().federation().issuers()

        val federationIssuer =
            issuerService.update(
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

        federationIssuer.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerService = client.organization().federation().issuers()

        val page = issuerService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val issuerService = client.organization().federation().issuers()

        val federationIssuer = issuerService.archive("federation_issuer_id")

        federationIssuer.validate()
    }
}
