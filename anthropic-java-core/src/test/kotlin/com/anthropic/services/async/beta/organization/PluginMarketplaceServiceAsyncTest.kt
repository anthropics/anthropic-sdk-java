package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceRetrieveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceUpdateParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateArchiveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateRepositoryParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class PluginMarketplaceServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val pluginMarketplaceServiceAsync =
            client.beta().organization().pluginMarketplaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaPluginMarketplaceFuture =
            pluginMarketplaceServiceAsync.retrieve(
                PluginMarketplaceRetrieveParams.builder()
                    .marketplaceId("marketplace_id")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaPluginMarketplace = betaPluginMarketplaceFuture.get()
        betaPluginMarketplace.validate()
    }

    @Test
    fun update() {
        val pluginMarketplaceServiceAsync =
            client.beta().organization().pluginMarketplaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaPluginMarketplaceFuture =
            pluginMarketplaceServiceAsync.update(
                PluginMarketplaceUpdateParams.builder()
                    .marketplaceId("marketplace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .defaultInstallationPreference(
                        PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE
                    )
                    .build()
            )

        val betaPluginMarketplace = betaPluginMarketplaceFuture.get()
        betaPluginMarketplace.validate()
    }

    @Test
    fun list() {
        val pluginMarketplaceServiceAsync =
            client.beta().organization().pluginMarketplaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = pluginMarketplaceServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun validateArchive() {
        val pluginMarketplaceServiceAsync =
            client.beta().organization().pluginMarketplaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaPluginMarketplaceValidationReportFuture =
            pluginMarketplaceServiceAsync.validateArchive(
                PluginMarketplaceValidateArchiveParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .archive("Example data".byteInputStream())
                    .build()
            )

        val betaPluginMarketplaceValidationReport =
            betaPluginMarketplaceValidationReportFuture.get()
        betaPluginMarketplaceValidationReport.validate()
    }

    @Test
    fun validateRepository() {
        val pluginMarketplaceServiceAsync =
            client.beta().organization().pluginMarketplaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaPluginMarketplaceValidationReportFuture =
            pluginMarketplaceServiceAsync.validateRepository(
                PluginMarketplaceValidateRepositoryParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .repositoryUrl("https://github.com/example-org/example-marketplace")
                    .ref("main")
                    .build()
            )

        val betaPluginMarketplaceValidationReport =
            betaPluginMarketplaceValidationReportFuture.get()
        betaPluginMarketplaceValidationReport.validate()
    }
}
