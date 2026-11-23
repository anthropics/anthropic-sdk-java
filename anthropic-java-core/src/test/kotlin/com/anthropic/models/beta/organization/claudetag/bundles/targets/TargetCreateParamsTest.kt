package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TargetCreateParamsTest {

    @Test
    fun create() {
        TargetCreateParams.builder()
            .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .slackChannelTarget("slch_01SvpyRQK3nN6eH9uhRA7n1q")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            TargetCreateParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .slackChannelTarget("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("capp_011CZkZjLP1cWxj5EtMmdgbq")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            TargetCreateParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .slackChannelTarget("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "experimental-claude-tag-2026-10-21")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            TargetCreateParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .slackChannelTarget("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            TargetCreateParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .slackChannelTarget("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .build()

        val body = params._body()

        assertThat(body)
            .isEqualTo(
                BetaClaudeTagBundleTargetCreate.ofSlackChannel("slch_01SvpyRQK3nN6eH9uhRA7n1q")
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            TargetCreateParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .slackChannelTarget("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .build()

        val body = params._body()

        assertThat(body)
            .isEqualTo(
                BetaClaudeTagBundleTargetCreate.ofSlackChannel("slch_01SvpyRQK3nN6eH9uhRA7n1q")
            )
    }
}
