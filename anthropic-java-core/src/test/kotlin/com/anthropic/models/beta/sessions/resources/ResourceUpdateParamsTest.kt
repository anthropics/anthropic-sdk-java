// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ResourceUpdateParamsTest {

    @Test
    fun create() {
        ResourceUpdateParams.builder()
            .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
            .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .authorizationToken("ghp_exampletoken")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ResourceUpdateParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .authorizationToken("ghp_exampletoken")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        assertThat(params._pathParam(1)).isEqualTo("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ResourceUpdateParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .authorizationToken("ghp_exampletoken")
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
            ResourceUpdateParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .authorizationToken("ghp_exampletoken")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ResourceUpdateParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .authorizationToken("ghp_exampletoken")
                .build()

        val body = params._body()

        assertThat(body.authorizationToken()).isEqualTo("ghp_exampletoken")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ResourceUpdateParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .authorizationToken("ghp_exampletoken")
                .build()

        val body = params._body()

        assertThat(body.authorizationToken()).isEqualTo("ghp_exampletoken")
    }
}
