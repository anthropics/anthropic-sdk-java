package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemoryView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryVersionRetrieveParamsTest {

    @Test
    fun create() {
        MemoryVersionRetrieveParams.builder()
            .memoryStoreId("memory_store_id")
            .memoryVersionId("memory_version_id")
            .view(BetaManagedAgentsMemoryView.BASIC)
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            MemoryVersionRetrieveParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryVersionId("memory_version_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("memory_store_id")
        assertThat(params._pathParam(1)).isEqualTo("memory_version_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            MemoryVersionRetrieveParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryVersionId("memory_version_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
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
        val params =
            MemoryVersionRetrieveParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryVersionId("memory_version_id")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            MemoryVersionRetrieveParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryVersionId("memory_version_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().put("view", "basic").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            MemoryVersionRetrieveParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryVersionId("memory_version_id")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
