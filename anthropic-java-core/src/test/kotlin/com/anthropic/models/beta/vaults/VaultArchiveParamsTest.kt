// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VaultArchiveParamsTest {

    @Test
    fun create() {
        VaultArchiveParams.builder()
            .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params = VaultArchiveParams.builder().vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv").build()

        assertThat(params._pathParam(0)).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            VaultArchiveParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
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
        val params = VaultArchiveParams.builder().vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
