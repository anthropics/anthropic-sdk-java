// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.tunnels

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.tunnels.certificates.CertificateArchiveParams
import com.anthropic.models.beta.tunnels.certificates.CertificateCreateParams
import com.anthropic.models.beta.tunnels.certificates.CertificateRetrieveParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CertificateServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val certificateServiceAsync = client.beta().tunnels().certificates()

        val betaTunnelCertificateFuture =
            certificateServiceAsync.create(
                CertificateCreateParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .caCertificatePem("ca_certificate_pem")
                    .build()
            )

        val betaTunnelCertificate = betaTunnelCertificateFuture.get()
        betaTunnelCertificate.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val certificateServiceAsync = client.beta().tunnels().certificates()

        val betaTunnelCertificateFuture =
            certificateServiceAsync.retrieve(
                CertificateRetrieveParams.builder()
                    .tunnelId("tunnel_id")
                    .certificateId("certificate_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaTunnelCertificate = betaTunnelCertificateFuture.get()
        betaTunnelCertificate.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val certificateServiceAsync = client.beta().tunnels().certificates()

        val pageFuture = certificateServiceAsync.list("tunnel_id")

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
        val certificateServiceAsync = client.beta().tunnels().certificates()

        val betaTunnelCertificateFuture =
            certificateServiceAsync.archive(
                CertificateArchiveParams.builder()
                    .tunnelId("tunnel_id")
                    .certificateId("certificate_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaTunnelCertificate = betaTunnelCertificateFuture.get()
        betaTunnelCertificate.validate()
    }
}
