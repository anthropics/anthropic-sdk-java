// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VaultCreateParamsTest {

    @Test
    fun create() {
        VaultCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .displayName("Example vault")
            .metadata(
                VaultCreateParams.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
            .build()
    }

    @Test
    fun headers() {
        val params =
            VaultCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .displayName("Example vault")
                .metadata(
                    VaultCreateParams.Metadata.builder()
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
        val params = VaultCreateParams.builder().displayName("Example vault").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            VaultCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .displayName("Example vault")
                .metadata(
                    VaultCreateParams.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.displayName()).isEqualTo("Example vault")
        assertThat(body.metadata())
            .contains(
                VaultCreateParams.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = VaultCreateParams.builder().displayName("Example vault").build()

        val body = params._body()

        assertThat(body.displayName()).isEqualTo("Example vault")
    }
}
