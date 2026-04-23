// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryStoreUpdateParamsTest {

    @Test
    fun create() {
        MemoryStoreUpdateParams.builder()
            .memoryStoreId("memory_store_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .description("description")
            .metadata(
                MemoryStoreUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
            .name("x")
            .build()
    }

    @Test
    fun pathParams() {
        val params = MemoryStoreUpdateParams.builder().memoryStoreId("memory_store_id").build()

        assertThat(params._pathParam(0)).isEqualTo("memory_store_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            MemoryStoreUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .description("description")
                .metadata(
                    MemoryStoreUpdateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .name("x")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = MemoryStoreUpdateParams.builder().memoryStoreId("memory_store_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            MemoryStoreUpdateParams.builder()
                .memoryStoreId("memory_store_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .description("description")
                .metadata(
                    MemoryStoreUpdateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .name("x")
                .build()

        val body = params._body()

        assertThat(body.description()).contains("description")
        assertThat(body.metadata())
            .contains(
                MemoryStoreUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.name()).contains("x")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = MemoryStoreUpdateParams.builder().memoryStoreId("memory_store_id").build()

        val body = params._body()
    }
}
