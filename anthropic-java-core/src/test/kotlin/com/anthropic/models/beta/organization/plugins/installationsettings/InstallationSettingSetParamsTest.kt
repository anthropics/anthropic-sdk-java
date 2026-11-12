package com.anthropic.models.beta.organization.plugins.installationsettings

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InstallationSettingSetParamsTest {

    @Test
    fun create() {
        InstallationSettingSetParams.builder()
            .pluginId("plugin_id")
            .target("target")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .installationPreference(InstallationSettingSetParams.InstallationPreference.REQUIRED)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            InstallationSettingSetParams.builder()
                .pluginId("plugin_id")
                .target("target")
                .installationPreference(
                    InstallationSettingSetParams.InstallationPreference.REQUIRED
                )
                .build()

        assertThat(params._pathParam(0)).isEqualTo("plugin_id")
        assertThat(params._pathParam(1)).isEqualTo("target")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            InstallationSettingSetParams.builder()
                .pluginId("plugin_id")
                .target("target")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .installationPreference(
                    InstallationSettingSetParams.InstallationPreference.REQUIRED
                )
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
        val params =
            InstallationSettingSetParams.builder()
                .pluginId("plugin_id")
                .target("target")
                .installationPreference(
                    InstallationSettingSetParams.InstallationPreference.REQUIRED
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            InstallationSettingSetParams.builder()
                .pluginId("plugin_id")
                .target("target")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .installationPreference(
                    InstallationSettingSetParams.InstallationPreference.REQUIRED
                )
                .build()

        val body = params._body()

        assertThat(body.installationPreference())
            .isEqualTo(InstallationSettingSetParams.InstallationPreference.REQUIRED)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            InstallationSettingSetParams.builder()
                .pluginId("plugin_id")
                .target("target")
                .installationPreference(
                    InstallationSettingSetParams.InstallationPreference.REQUIRED
                )
                .build()

        val body = params._body()

        assertThat(body.installationPreference())
            .isEqualTo(InstallationSettingSetParams.InstallationPreference.REQUIRED)
    }
}
