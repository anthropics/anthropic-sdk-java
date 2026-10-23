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
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
            BundleRetrieveParams.builder().bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
