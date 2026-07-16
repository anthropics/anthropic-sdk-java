// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TunnelCreateParamsTest {

    @Test
    fun create() {
        TunnelCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .displayName("x")
            .build()
    }

    @Test
    fun headers() {
        val params =
            TunnelCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .displayName("x")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = TunnelCreateParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            TunnelCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .displayName("x")
                .build()

        val body = params._body()

        assertThat(body.displayName()).contains("x")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = TunnelCreateParams.builder().build()

        val body = params._body()
    }
}
