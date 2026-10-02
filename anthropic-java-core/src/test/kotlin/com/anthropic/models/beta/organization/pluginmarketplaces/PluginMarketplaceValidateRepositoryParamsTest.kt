package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginMarketplaceValidateRepositoryParamsTest {

    @Test
    fun create() {
        PluginMarketplaceValidateRepositoryParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .repositoryUrl("https://github.com/example-org/example-marketplace")
            .ref("main")
            .build()
    }

    @Test
    fun headers() {
        val params =
            PluginMarketplaceValidateRepositoryParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .repositoryUrl("https://github.com/example-org/example-marketplace")
                .ref("main")
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
            PluginMarketplaceValidateRepositoryParams.builder()
                .repositoryUrl("https://github.com/example-org/example-marketplace")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            PluginMarketplaceValidateRepositoryParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .repositoryUrl("https://github.com/example-org/example-marketplace")
                .ref("main")
                .build()

        val body = params._body()

        assertThat(body.repositoryUrl())
            .isEqualTo("https://github.com/example-org/example-marketplace")
        assertThat(body.ref()).contains("main")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            PluginMarketplaceValidateRepositoryParams.builder()
                .repositoryUrl("https://github.com/example-org/example-marketplace")
                .build()

        val body = params._body()

        assertThat(body.repositoryUrl())
            .isEqualTo("https://github.com/example-org/example-marketplace")
    }
}
