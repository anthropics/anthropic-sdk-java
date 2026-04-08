// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ResourceRetrieveParamsTest {

    @Test
    fun create() {
        ResourceRetrieveParams.builder()
            .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
            .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ResourceRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        assertThat(params._pathParam(1)).isEqualTo("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ResourceRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
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
            ResourceRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
