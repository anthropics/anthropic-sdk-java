// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.tunnels

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.tunnels.certificates.CertificateArchiveParams
import com.anthropic.models.beta.tunnels.certificates.CertificateCreateParams
import com.anthropic.models.beta.tunnels.certificates.CertificateRetrieveParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CertificateServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val certificateService = client.beta().tunnels().certificates()

        val betaTunnelCertificate =
            certificateService.create(
                CertificateCreateParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .caCertificatePem("ca_certificate_pem")
                    .build()
            )

        betaTunnelCertificate.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val certificateService = client.beta().tunnels().certificates()

        val betaTunnelCertificate =
            certificateService.retrieve(
                CertificateRetrieveParams.builder()
                    .tunnelId("tunnel_id")
                    .certificateId("certificate_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaTunnelCertificate.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val certificateService = client.beta().tunnels().certificates()

        val page = certificateService.list("tunnel_id")

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val certificateService = client.beta().tunnels().certificates()

        val betaTunnelCertificate =
            certificateService.archive(
                CertificateArchiveParams.builder()
                    .tunnelId("tunnel_id")
                    .certificateId("certificate_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaTunnelCertificate.validate()
    }
}
