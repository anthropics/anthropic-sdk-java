package com.anthropic.services.async.beta.organization

import com.anthropic.CompletionExecutorAssertingInterceptor
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

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val pluginServiceAsync =
            client.beta().organization().plugins().withOptions { it.putQueryParam("beta", "true") }

        val betaPluginFuture =
            pluginServiceAsync.create(
                PluginCreateParams.builder()
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
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
        val pluginServiceAsync =
            client.beta().organization().plugins().withOptions { it.putQueryParam("beta", "true") }

        val betaPluginFuture =
            pluginServiceAsync.retrieve(
                PluginRetrieveParams.builder()
                    .pluginId("plugin_id")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaPlugin = betaPluginFuture.get()
        betaPlugin.validate()
    }

    @Test
    fun update() {
        val pluginServiceAsync =
            client.beta().organization().plugins().withOptions { it.putQueryParam("beta", "true") }

        val betaPluginFuture =
            pluginServiceAsync.update(
                PluginUpdateParams.builder()
                    .pluginId("plugin_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .servedVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                    .build()
            )

        val betaPlugin = betaPluginFuture.get()
        betaPlugin.validate()
    }

    @Test
    fun list() {
        val pluginServiceAsync =
            client.beta().organization().plugins().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = pluginServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val pluginServiceAsync =
            client.beta().organization().plugins().withOptions { it.putQueryParam("beta", "true") }

        val betaDeletedPluginFuture =
            pluginServiceAsync.delete(
                PluginDeleteParams.builder()
                    .pluginId("plugin_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaDeletedPlugin = betaDeletedPluginFuture.get()
        betaDeletedPlugin.validate()
    }
}
