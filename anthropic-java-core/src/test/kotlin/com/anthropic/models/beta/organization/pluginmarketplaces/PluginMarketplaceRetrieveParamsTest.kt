package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginMarketplaceRetrieveParamsTest {

    @Test
    fun create() {
        PluginMarketplaceRetrieveParams.builder()
            .marketplaceId("marketplace_id")
            .organizationId("organization_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            PluginMarketplaceRetrieveParams.builder().marketplaceId("marketplace_id").build()

        assertThat(params._pathParam(0)).isEqualTo("marketplace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            PluginMarketplaceRetrieveParams.builder()
                .marketplaceId("marketplace_id")
                .organizationId("organization_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
            PluginMarketplaceRetrieveParams.builder().marketplaceId("marketplace_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            PluginMarketplaceRetrieveParams.builder()
                .marketplaceId("marketplace_id")
                .organizationId("organization_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("organization_id", "organization_id").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            PluginMarketplaceRetrieveParams.builder().marketplaceId("marketplace_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
