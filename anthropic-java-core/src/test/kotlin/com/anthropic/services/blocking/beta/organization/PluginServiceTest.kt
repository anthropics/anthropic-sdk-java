package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.plugins.PluginCreateParams
import com.anthropic.models.beta.organization.plugins.PluginDeleteParams
import com.anthropic.models.beta.organization.plugins.PluginRetrieveParams
import com.anthropic.models.beta.organization.plugins.PluginUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class PluginServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginService = client.beta().organization().plugins()

        val betaPlugin =
            pluginService.create(
                PluginCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addFile("Example data".byteInputStream())
                    .marketplaceId("marketplace_id")
                    .releaseNotes("release_notes")
                    .build()
            )

        betaPlugin.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginService = client.beta().organization().plugins()

        val betaPlugin =
            pluginService.retrieve(
                PluginRetrieveParams.builder()
                    .pluginId("plugin_id")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaPlugin.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginService = client.beta().organization().plugins()

        val betaPlugin =
            pluginService.update(
                PluginUpdateParams.builder()
                    .pluginId("plugin_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                    .build()
            )

        betaPlugin.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginService = client.beta().organization().plugins()

        val page = pluginService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginService = client.beta().organization().plugins()

        val betaDeletedPlugin =
            pluginService.delete(
                PluginDeleteParams.builder()
                    .pluginId("plugin_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaDeletedPlugin.validate()
    }
}
