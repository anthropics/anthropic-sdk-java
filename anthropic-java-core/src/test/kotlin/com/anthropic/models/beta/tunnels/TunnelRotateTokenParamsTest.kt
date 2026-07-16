// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TunnelRotateTokenParamsTest {

    @Test
    fun create() {
        TunnelRotateTokenParams.builder()
            .tunnelId("tunnel_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .reason("reason")
            .build()
    }

    @Test
    fun pathParams() {
        val params = TunnelRotateTokenParams.builder().tunnelId("tunnel_id").build()

        assertThat(params._pathParam(0)).isEqualTo("tunnel_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            TunnelRotateTokenParams.builder()
                .tunnelId("tunnel_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .reason("reason")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = TunnelRotateTokenParams.builder().tunnelId("tunnel_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            TunnelRotateTokenParams.builder()
                .tunnelId("tunnel_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .reason("reason")
                .build()

        val body = params._body()

        assertThat(body.reason()).contains("reason")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = TunnelRotateTokenParams.builder().tunnelId("tunnel_id").build()

        val body = params._body()
    }
}
