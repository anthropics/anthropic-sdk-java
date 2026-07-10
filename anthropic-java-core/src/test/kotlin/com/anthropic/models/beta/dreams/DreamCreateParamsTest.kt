// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DreamCreateParamsTest {

    @Test
    fun create() {
        DreamCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .addMemoryStoreInput("x")
            .model("string")
            .instructions("x")
            .build()
    }

    @Test
    fun headers() {
        val params =
            DreamCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addMemoryStoreInput("x")
                .model("string")
                .instructions("x")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = DreamCreateParams.builder().addMemoryStoreInput("x").model("string").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            DreamCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addMemoryStoreInput("x")
                .model("string")
                .instructions("x")
                .build()

        val body = params._body()

        assertThat(body.inputs())
            .containsExactly(
                BetaDreamInput.ofMemoryStore(
                    BetaDreamMemoryStoreInput.builder()
                        .memoryStoreId("x")
                        .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(DreamCreateParams.Model.ofString("string"))
        assertThat(body.instructions()).contains("x")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = DreamCreateParams.builder().addMemoryStoreInput("x").model("string").build()

        val body = params._body()

        assertThat(body.inputs())
            .containsExactly(
                BetaDreamInput.ofMemoryStore(
                    BetaDreamMemoryStoreInput.builder()
                        .memoryStoreId("x")
                        .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(DreamCreateParams.Model.ofString("string"))
    }
}
