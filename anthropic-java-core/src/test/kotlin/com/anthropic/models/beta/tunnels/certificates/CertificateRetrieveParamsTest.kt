// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels.certificates

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CertificateRetrieveParamsTest {

    @Test
    fun create() {
        CertificateRetrieveParams.builder()
            .tunnelId("tunnel_id")
            .certificateId("certificate_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            CertificateRetrieveParams.builder()
                .tunnelId("tunnel_id")
                .certificateId("certificate_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("tunnel_id")
        assertThat(params._pathParam(1)).isEqualTo("certificate_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            CertificateRetrieveParams.builder()
                .tunnelId("tunnel_id")
                .certificateId("certificate_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
            CertificateRetrieveParams.builder()
                .tunnelId("tunnel_id")
                .certificateId("certificate_id")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
