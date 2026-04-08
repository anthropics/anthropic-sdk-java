// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VaultUpdateParamsTest {

    @Test
    fun create() {
        VaultUpdateParams.builder()
            .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .displayName("Example vault")
            .metadata(
                VaultUpdateParams.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params = VaultUpdateParams.builder().vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv").build()

        assertThat(params._pathParam(0)).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            VaultUpdateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .displayName("Example vault")
                .metadata(
                    VaultUpdateParams.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = VaultUpdateParams.builder().vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            VaultUpdateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .displayName("Example vault")
                .metadata(
                    VaultUpdateParams.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.displayName()).contains("Example vault")
        assertThat(body.metadata())
            .contains(
                VaultUpdateParams.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = VaultUpdateParams.builder().vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv").build()

        val body = params._body()
    }
}
