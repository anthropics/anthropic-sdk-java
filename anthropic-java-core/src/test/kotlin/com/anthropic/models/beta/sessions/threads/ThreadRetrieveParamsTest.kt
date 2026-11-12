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
            .threadId("sthr_011CZkZVWa6oJjw1rgXZpnBt")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ThreadRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .threadId("sthr_011CZkZVWa6oJjw1rgXZpnBt")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        assertThat(params._pathParam(1)).isEqualTo("sthr_011CZkZVWa6oJjw1rgXZpnBt")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ThreadRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .threadId("sthr_011CZkZVWa6oJjw1rgXZpnBt")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "managed-agents-2026-04-01")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            ThreadRetrieveParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .threadId("sthr_011CZkZVWa6oJjw1rgXZpnBt")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
