package com.anthropic.models.beta.memorystores

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryStoreDeleteParamsTest {

    @Test
    fun create() {
        MemoryStoreDeleteParams.builder()
            .memoryStoreId("memory_store_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params = MemoryStoreDeleteParams.builder().memoryStoreId("memory_store_id").build()

        assertThat(params._pathParam(0)).isEqualTo("memory_store_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            MemoryStoreDeleteParams.builder()
                .memoryStoreId("memory_store_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "agent-memory-2026-07-22")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = MemoryStoreDeleteParams.builder().memoryStoreId("memory_store_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
