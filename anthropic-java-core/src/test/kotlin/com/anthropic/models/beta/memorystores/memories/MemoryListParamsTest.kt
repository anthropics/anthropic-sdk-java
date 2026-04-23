// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryListParamsTest {

    @Test
    fun create() {
        MemoryListParams.builder()
            .memoryStoreId("memory_store_id")
            .depth(0)
            .limit(0)
            .order(MemoryListParams.Order.ASC)
            .orderBy("order_by")
            .page("page")
            .pathPrefix("path_prefix")
            .view(BetaManagedAgentsMemoryView.BASIC)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params = MemoryListParams.builder().memoryStoreId("memory_store_id").build()

        assertThat(params._pathParam(0)).isEqualTo("memory_store_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            MemoryListParams.builder()
                .memoryStoreId("memory_store_id")
                .depth(0)
                .limit(0)
                .order(MemoryListParams.Order.ASC)
                .orderBy("order_by")
                .page("page")
                .pathPrefix("path_prefix")
                .view(BetaManagedAgentsMemoryView.BASIC)
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
        val params = MemoryListParams.builder().memoryStoreId("memory_store_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            MemoryListParams.builder()
                .memoryStoreId("memory_store_id")
                .depth(0)
                .limit(0)
                .order(MemoryListParams.Order.ASC)
                .orderBy("order_by")
                .page("page")
                .pathPrefix("path_prefix")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("depth", "0")
                    .put("limit", "0")
                    .put("order", "asc")
                    .put("order_by", "order_by")
                    .put("page", "page")
                    .put("path_prefix", "path_prefix")
                    .put("view", "basic")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = MemoryListParams.builder().memoryStoreId("memory_store_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
