package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginRetrieveParamsTest {

    @Test
    fun create() {
        PluginRetrieveParams.builder()
            .pluginId("plugin_id")
            .organizationId("organization_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun pathParams() {
        val params = PluginRetrieveParams.builder().pluginId("plugin_id").build()

        assertThat(params._pathParam(0)).isEqualTo("plugin_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            PluginRetrieveParams.builder()
                .pluginId("plugin_id")
                .organizationId("organization_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "ce-plugins-2026-09-01").joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = PluginRetrieveParams.builder().pluginId("plugin_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            PluginRetrieveParams.builder()
                .pluginId("plugin_id")
                .organizationId("organization_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("organization_id", "organization_id").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = PluginRetrieveParams.builder().pluginId("plugin_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
