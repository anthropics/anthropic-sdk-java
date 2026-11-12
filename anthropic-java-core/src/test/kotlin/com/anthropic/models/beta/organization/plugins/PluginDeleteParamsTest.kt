package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginDeleteParamsTest {

    @Test
    fun create() {
        PluginDeleteParams.builder()
            .pluginId("plugin_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun pathParams() {
        val params = PluginDeleteParams.builder().pluginId("plugin_id").build()

        assertThat(params._pathParam(0)).isEqualTo("plugin_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            PluginDeleteParams.builder()
                .pluginId("plugin_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "ce-plugins-2026-09-01").joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = PluginDeleteParams.builder().pluginId("plugin_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
