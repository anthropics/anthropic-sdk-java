package com.anthropic.models.beta.organization.plugins.versions

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionDownloadParamsTest {

    @Test
    fun create() {
        VersionDownloadParams.builder()
            .pluginId("plugin_id")
            .version("version")
            .organizationId("organization_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            VersionDownloadParams.builder().pluginId("plugin_id").version("version").build()

        assertThat(params._pathParam(0)).isEqualTo("plugin_id")
        assertThat(params._pathParam(1)).isEqualTo("version")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            VersionDownloadParams.builder()
                .pluginId("plugin_id")
                .version("version")
                .organizationId("organization_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "ce-plugins-2026-09-01")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            VersionDownloadParams.builder().pluginId("plugin_id").version("version").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            VersionDownloadParams.builder()
                .pluginId("plugin_id")
                .version("version")
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
            VersionDownloadParams.builder().pluginId("plugin_id").version("version").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
