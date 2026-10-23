package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TargetDeleteParamsTest {

    @Test
    fun create() {
        TargetDeleteParams.builder()
            .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
            .targetId("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            TargetDeleteParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .targetId("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("capp_011CZkZjLP1cWxj5EtMmdgbq")
        assertThat(params._pathParam(1))
            .isEqualTo("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            TargetDeleteParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .targetId("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "experimental-claude-tag-2026-10-21")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            TargetDeleteParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .targetId("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
