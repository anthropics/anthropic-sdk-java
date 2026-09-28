package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.plugins.PluginCreateParams
import com.anthropic.models.beta.organization.plugins.PluginDeleteParams
import com.anthropic.models.beta.organization.plugins.PluginRetrieveParams
import com.anthropic.models.beta.organization.plugins.PluginUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class PluginServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginServiceAsync = client.beta().organization().plugins()

        val betaPluginFuture =
            pluginServiceAsync.create(
                PluginCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addFile("Example data".byteInputStream())
                    .marketplaceId("marketplace_id")
                    .releaseNotes("release_notes")
                    .build()
            )

        val betaPlugin = betaPluginFuture.get()
        betaPlugin.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginServiceAsync = client.beta().organization().plugins()

        val betaPluginFuture =
            pluginServiceAsync.retrieve(
                PluginRetrieveParams.builder()
                    .pluginId("plugin_id")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaPlugin = betaPluginFuture.get()
        betaPlugin.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginServiceAsync = client.beta().organization().plugins()

        val betaPluginFuture =
            pluginServiceAsync.update(
                PluginUpdateParams.builder()
                    .pluginId("plugin_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                    .build()
            )

        val betaPlugin = betaPluginFuture.get()
        betaPlugin.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginServiceAsync = client.beta().organization().plugins()

        val pageFuture = pluginServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginServiceAsync = client.beta().organization().plugins()

        val betaDeletedPluginFuture =
            pluginServiceAsync.delete(
                PluginDeleteParams.builder()
                    .pluginId("plugin_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaDeletedPlugin = betaDeletedPluginFuture.get()
        betaDeletedPlugin.validate()
    }
}
