// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryUpdateParamsTest {

    @Test
    fun create() {
        MemoryUpdateParams.builder()
            .memoryStoreId("memory_store_id")
            .memoryId("memory_id")
            .view(BetaManagedAgentsMemoryView.BASIC)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .content("content")
            .path("xx")
            .precondition(
                BetaManagedAgentsPrecondition.builder()
                    .type(BetaManagedAgentsPrecondition.Type.CONTENT_SHA256)
                    .contentSha256("content_sha256")
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            MemoryUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryId("memory_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("memory_store_id")
        assertThat(params._pathParam(1)).isEqualTo("memory_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            MemoryUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryId("memory_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .content("content")
                .path("xx")
                .precondition(
                    BetaManagedAgentsPrecondition.builder()
                        .type(BetaManagedAgentsPrecondition.Type.CONTENT_SHA256)
                        .contentSha256("content_sha256")
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
        val params =
            MemoryUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryId("memory_id")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            MemoryUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryId("memory_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .content("content")
                .path("xx")
                .precondition(
                    BetaManagedAgentsPrecondition.builder()
                        .type(BetaManagedAgentsPrecondition.Type.CONTENT_SHA256)
                        .contentSha256("content_sha256")
                        .build()
                )
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().put("view", "basic").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            MemoryUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryId("memory_id")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }

    @Test
    fun body() {
        val params =
            MemoryUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryId("memory_id")
                .view(BetaManagedAgentsMemoryView.BASIC)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .content("content")
                .path("xx")
                .precondition(
                    BetaManagedAgentsPrecondition.builder()
                        .type(BetaManagedAgentsPrecondition.Type.CONTENT_SHA256)
                        .contentSha256("content_sha256")
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.content()).contains("content")
        assertThat(body.path()).contains("xx")
        assertThat(body.precondition())
            .contains(
                BetaManagedAgentsPrecondition.builder()
                    .type(BetaManagedAgentsPrecondition.Type.CONTENT_SHA256)
                    .contentSha256("content_sha256")
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            MemoryUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .memoryId("memory_id")
                .build()

        val body = params._body()
    }
}
