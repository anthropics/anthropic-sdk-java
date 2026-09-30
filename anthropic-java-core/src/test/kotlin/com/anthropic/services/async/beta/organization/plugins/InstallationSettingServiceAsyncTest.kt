package com.anthropic.services.async.beta.organization.plugins

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingRemoveParams
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingSetParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class InstallationSettingServiceAsyncTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val installationSettingServiceAsync =
            client.beta().organization().plugins().installationSettings()

        val pageFuture = installationSettingServiceAsync.list("plugin_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val installationSettingServiceAsync =
            client.beta().organization().plugins().installationSettings()

        val betaDeletedPluginInstallationSettingFuture =
            installationSettingServiceAsync.remove(
                InstallationSettingRemoveParams.builder()
                    .pluginId("plugin_id")
                    .target("target")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaDeletedPluginInstallationSetting = betaDeletedPluginInstallationSettingFuture.get()
        betaDeletedPluginInstallationSetting.validate()
    }

    @Test
    fun set() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val installationSettingServiceAsync =
            client.beta().organization().plugins().installationSettings()

        val betaPluginInstallationSettingFuture =
            installationSettingServiceAsync.set(
                InstallationSettingSetParams.builder()
                    .pluginId("plugin_id")
                    .target("target")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .installationPreference(
                        InstallationSettingSetParams.InstallationPreference.REQUIRED
                    )
                    .build()
            )

        val betaPluginInstallationSetting = betaPluginInstallationSettingFuture.get()
        betaPluginInstallationSetting.validate()
    }
}
