package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceRetrieveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceUpdateParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateArchiveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateRepositoryParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class PluginMarketplaceServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginMarketplaceService = client.beta().organization().pluginMarketplaces()

        val betaPluginMarketplace =
            pluginMarketplaceService.retrieve(
                PluginMarketplaceRetrieveParams.builder()
                    .marketplaceId("marketplace_id")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaPluginMarketplace.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginMarketplaceService = client.beta().organization().pluginMarketplaces()

        val betaPluginMarketplace =
            pluginMarketplaceService.update(
                PluginMarketplaceUpdateParams.builder()
                    .marketplaceId("marketplace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .defaultInstallationPreference(
                        PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE
                    )
                    .build()
            )

        betaPluginMarketplace.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginMarketplaceService = client.beta().organization().pluginMarketplaces()

        val page = pluginMarketplaceService.list()

        page.response().validate()
    }

    @Test
    fun validateArchive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginMarketplaceService = client.beta().organization().pluginMarketplaces()

        val betaPluginMarketplaceValidationReport =
            pluginMarketplaceService.validateArchive(
                PluginMarketplaceValidateArchiveParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .archive("Example data".byteInputStream())
                    .build()
            )

        betaPluginMarketplaceValidationReport.validate()
    }

    @Test
    fun validateRepository() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val pluginMarketplaceService = client.beta().organization().pluginMarketplaces()

        val betaPluginMarketplaceValidationReport =
            pluginMarketplaceService.validateRepository(
                PluginMarketplaceValidateRepositoryParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .repositoryUrl("https://github.com/example-org/example-marketplace")
                    .ref("main")
                    .build()
            )

        betaPluginMarketplaceValidationReport.validate()
    }
}
