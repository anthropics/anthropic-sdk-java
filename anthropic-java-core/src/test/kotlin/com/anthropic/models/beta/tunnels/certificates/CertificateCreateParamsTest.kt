// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels.certificates

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CertificateCreateParamsTest {

    @Test
    fun create() {
        CertificateCreateParams.builder()
            .tunnelId("tunnel_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .caCertificatePem("ca_certificate_pem")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            CertificateCreateParams.builder()
                .tunnelId("tunnel_id")
                .caCertificatePem("ca_certificate_pem")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("tunnel_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            CertificateCreateParams.builder()
                .tunnelId("tunnel_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .caCertificatePem("ca_certificate_pem")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            CertificateCreateParams.builder()
                .tunnelId("tunnel_id")
                .caCertificatePem("ca_certificate_pem")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            CertificateCreateParams.builder()
                .tunnelId("tunnel_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .caCertificatePem("ca_certificate_pem")
                .build()

        val body = params._body()

        assertThat(body.caCertificatePem()).isEqualTo("ca_certificate_pem")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            CertificateCreateParams.builder()
                .tunnelId("tunnel_id")
                .caCertificatePem("ca_certificate_pem")
                .build()

        val body = params._body()

        assertThat(body.caCertificatePem()).isEqualTo("ca_certificate_pem")
    }
}
