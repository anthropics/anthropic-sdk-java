// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemoryView
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryVersionListParamsTest {

    @Test
    fun create() {
        MemoryVersionListParams.builder()
            .memoryStoreId("memory_store_id")
            .apiKeyId("api_key_id")
            .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .limit(0)
            .memoryId("memory_id")
            .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
            .page("page")
            .sessionId("session_id")
            .view(BetaManagedAgentsMemoryView.BASIC)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params = MemoryVersionListParams.builder().memoryStoreId("memory_store_id").build()

        assertThat(params._pathParam(0)).isEqualTo("memory_store_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            MemoryVersionListParams.builder()
                .memoryStoreId("memory_store_id")
                .apiKeyId("api_key_id")
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .limit(0)
                .memoryId("memory_id")
                .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                .page("page")
                .sessionId("session_id")
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
        val params = MemoryVersionListParams.builder().memoryStoreId("memory_store_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            MemoryVersionListParams.builder()
                .memoryStoreId("memory_store_id")
                .apiKeyId("api_key_id")
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .limit(0)
                .memoryId("memory_id")
                .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                .page("page")
                .sessionId("session_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("api_key_id", "api_key_id")
                    .put("created_at[gte]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lte]", "2019-12-27T18:11:19.117Z")
                    .put("limit", "0")
                    .put("memory_id", "memory_id")
                    .put("operation", "created")
                    .put("page", "page")
                    .put("session_id", "session_id")
                    .put("view", "basic")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = MemoryVersionListParams.builder().memoryStoreId("memory_store_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
