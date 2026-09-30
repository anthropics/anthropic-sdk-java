package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginUpdateParamsTest {

    @Test
    fun create() {
        PluginUpdateParams.builder()
            .pluginId("plugin_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            PluginUpdateParams.builder()
                .pluginId("plugin_id")
                .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("plugin_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            PluginUpdateParams.builder()
                .pluginId("plugin_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
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
            PluginUpdateParams.builder()
                .pluginId("plugin_id")
                .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            PluginUpdateParams.builder()
                .pluginId("plugin_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                .build()

        val body = params._body()

        assertThat(body.servedVersionId()).isEqualTo("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            PluginUpdateParams.builder()
                .pluginId("plugin_id")
                .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                .build()

        val body = params._body()

        assertThat(body.servedVersionId()).isEqualTo("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
    }
}
