package com.anthropic.models.beta.organization.claudetag.bundles

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BundleRetrieveParamsTest {

    @Test
    fun create() {
        BundleRetrieveParams.builder()
            .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            BundleRetrieveParams.builder().bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq").build()

        assertThat(params._pathParam(0)).isEqualTo("capp_011CZkZjLP1cWxj5EtMmdgbq")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            BundleRetrieveParams.builder()
                .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
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
            BundleRetrieveParams.builder().bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
