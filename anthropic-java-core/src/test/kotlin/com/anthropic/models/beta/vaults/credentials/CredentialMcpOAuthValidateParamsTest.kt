// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CredentialMcpOAuthValidateParamsTest {

    @Test
    fun create() {
        CredentialMcpOAuthValidateParams.builder()
            .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
            .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            CredentialMcpOAuthValidateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        assertThat(params._pathParam(1)).isEqualTo("vcrd_011CZkZEMt8gZan2iYOQfSkw")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            CredentialMcpOAuthValidateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
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
        val params =
            CredentialMcpOAuthValidateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
