// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThreadRetrieveParamsTest {

    @Test
    fun create() {
        ThreadRetrieveParams.builder()
            .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
            .threadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ThreadRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .threadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        assertThat(params._pathParam(1)).isEqualTo("sthr_011CZkZVWa6oIjw0rgXZpnBt")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ThreadRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .threadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
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
            ThreadRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .threadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
