package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginMarketplaceUpdateParamsTest {

    @Test
    fun create() {
        PluginMarketplaceUpdateParams.builder()
            .marketplaceId("marketplace_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .defaultInstallationPreference(
                PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            PluginMarketplaceUpdateParams.builder()
                .marketplaceId("marketplace_id")
                .defaultInstallationPreference(
                    PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE
                )
                .build()

        assertThat(params._pathParam(0)).isEqualTo("marketplace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            PluginMarketplaceUpdateParams.builder()
                .marketplaceId("marketplace_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .defaultInstallationPreference(
                    PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("message-batches-2024-09-24").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            PluginMarketplaceUpdateParams.builder()
                .marketplaceId("marketplace_id")
                .defaultInstallationPreference(
                    PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            PluginMarketplaceUpdateParams.builder()
                .marketplaceId("marketplace_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .defaultInstallationPreference(
                    PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE
                )
                .build()

        val body = params._body()

        assertThat(body.defaultInstallationPreference())
            .isEqualTo(PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            PluginMarketplaceUpdateParams.builder()
                .marketplaceId("marketplace_id")
                .defaultInstallationPreference(
                    PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE
                )
                .build()

        val body = params._body()

        assertThat(body.defaultInstallationPreference())
            .isEqualTo(PluginMarketplaceUpdateParams.DefaultInstallationPreference.AVAILABLE)
    }
}
