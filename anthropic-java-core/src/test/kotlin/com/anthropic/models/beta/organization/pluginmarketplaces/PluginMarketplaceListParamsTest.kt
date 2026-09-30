package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginMarketplaceListParamsTest {

    @Test
    fun create() {
        PluginMarketplaceListParams.builder()
            .limit(1L)
            .organizationId("organization_id")
            .ownerType(PluginMarketplaceListParams.OwnerType.ORGANIZATION)
            .page("page")
            .source(PluginMarketplaceListParams.Source.DIRECTORY)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun headers() {
        val params =
            PluginMarketplaceListParams.builder()
                .limit(1L)
                .organizationId("organization_id")
                .ownerType(PluginMarketplaceListParams.OwnerType.ORGANIZATION)
                .page("page")
                .source(PluginMarketplaceListParams.Source.DIRECTORY)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = PluginMarketplaceListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            PluginMarketplaceListParams.builder()
                .limit(1L)
                .organizationId("organization_id")
                .ownerType(PluginMarketplaceListParams.OwnerType.ORGANIZATION)
                .page("page")
                .source(PluginMarketplaceListParams.Source.DIRECTORY)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("limit", "1")
                    .put("organization_id", "organization_id")
                    .put("owner_type", "organization")
                    .put("page", "page")
                    .put("source", "directory")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = PluginMarketplaceListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
