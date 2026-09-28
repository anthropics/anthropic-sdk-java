package com.anthropic.services.blocking.beta.organization.plugins

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingRemoveParams
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingSetParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class InstallationSettingServiceTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val installationSettingService =
            client.beta().organization().plugins().installationSettings()

        val page = installationSettingService.list("plugin_id")

        page.response().validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val installationSettingService =
            client.beta().organization().plugins().installationSettings()

        val betaDeletedPluginInstallationSetting =
            installationSettingService.remove(
                InstallationSettingRemoveParams.builder()
                    .pluginId("plugin_id")
                    .target("target")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaDeletedPluginInstallationSetting.validate()
    }

    @Test
    fun set() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val installationSettingService =
            client.beta().organization().plugins().installationSettings()

        val betaPluginInstallationSetting =
            installationSettingService.set(
                InstallationSettingSetParams.builder()
                    .pluginId("plugin_id")
                    .target("target")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .installationPreference(
                        InstallationSettingSetParams.InstallationPreference.REQUIRED
                    )
                    .build()
            )

        betaPluginInstallationSetting.validate()
    }
}
