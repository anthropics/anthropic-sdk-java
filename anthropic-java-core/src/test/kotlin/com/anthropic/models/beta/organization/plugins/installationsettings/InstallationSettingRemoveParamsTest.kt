package com.anthropic.models.beta.organization.plugins.installationsettings

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InstallationSettingRemoveParamsTest {

    @Test
    fun create() {
        InstallationSettingRemoveParams.builder()
            .pluginId("plugin_id")
            .target("target")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            InstallationSettingRemoveParams.builder().pluginId("plugin_id").target("target").build()

        assertThat(params._pathParam(0)).isEqualTo("plugin_id")
        assertThat(params._pathParam(1)).isEqualTo("target")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            InstallationSettingRemoveParams.builder()
                .pluginId("plugin_id")
                .target("target")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "ce-plugins-2026-09-01")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            InstallationSettingRemoveParams.builder().pluginId("plugin_id").target("target").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
