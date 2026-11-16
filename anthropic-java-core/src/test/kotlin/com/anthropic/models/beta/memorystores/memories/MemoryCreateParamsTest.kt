package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryCreateParamsTest {

    @Test
    fun create() {
        MemoryCreateParams.builder()
            .memoryStoreId("memory_store_id")
            .view(BetaManagedAgentsMemoryView.BASIC)
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .content("content")
            .path("/P")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            MemoryCreateParams.builder()
                .memoryStoreId("memory_store_id")
                .content("content")
                .path("/P")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("memory_store_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            MemoryCreateParams.builder()
                .memoryStoreId("memory_store_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .content("content")
                .path("/P")
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
        val params =
            MemoryCreateParams.builder()
                .memoryStoreId("memory_store_id")
                .content("content")
                .path("/P")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            MemoryCreateParams.builder()
                .memoryStoreId("memory_store_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .content("content")
                .path("/P")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().put("view", "basic").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            MemoryCreateParams.builder()
                .memoryStoreId("memory_store_id")
                .content("content")
                .path("/P")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }

    @Test
    fun body() {
        val params =
            MemoryCreateParams.builder()
                .memoryStoreId("memory_store_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .content("content")
                .path("/P")
                .build()

        val body = params._body()

        assertThat(body.content()).contains("content")
        assertThat(body.path()).isEqualTo("/P")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            MemoryCreateParams.builder()
                .memoryStoreId("memory_store_id")
                .content("content")
                .path("/P")
                .build()

        val body = params._body()

        assertThat(body.content()).contains("content")
        assertThat(body.path()).isEqualTo("/P")
    }
}
