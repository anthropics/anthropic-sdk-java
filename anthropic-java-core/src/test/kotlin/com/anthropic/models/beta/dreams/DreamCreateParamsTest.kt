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
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .addMemoryStoreInput("x")
            .model("string")
            .instructions("x")
            .outputBehavior(BetaOutputBehaviorCreateNew.builder().build())
            .build()
    }

    @Test
    fun headers() {
        val params =
            DreamCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .addMemoryStoreInput("x")
                .model("string")
                .instructions("x")
                .outputBehavior(BetaOutputBehaviorCreateNew.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "dreaming-2026-04-21")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
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
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .addMemoryStoreInput("x")
                .model("string")
                .instructions("x")
                .outputBehavior(BetaOutputBehaviorCreateNew.builder().build())
                .build()

        val body = params._body()

        assertThat(body.inputs()).containsExactly(BetaDreamInput.ofMemoryStore("x"))
        assertThat(body.model()).isEqualTo(DreamCreateParams.Model.ofString("string"))
        assertThat(body.instructions()).contains("x")
        assertThat(body.outputBehavior())
            .contains(BetaOutputBehavior.ofCreateNew(BetaOutputBehaviorCreateNew.builder().build()))
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = DreamCreateParams.builder().addMemoryStoreInput("x").model("string").build()

        val body = params._body()

        assertThat(body.inputs()).containsExactly(BetaDreamInput.ofMemoryStore("x"))
        assertThat(body.model()).isEqualTo(DreamCreateParams.Model.ofString("string"))
    }
}
