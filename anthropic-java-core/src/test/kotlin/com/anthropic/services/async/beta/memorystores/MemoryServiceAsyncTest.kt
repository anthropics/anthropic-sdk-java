// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.memorystores

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemoryView
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsPrecondition
import com.anthropic.models.beta.memorystores.memories.MemoryCreateParams
import com.anthropic.models.beta.memorystores.memories.MemoryDeleteParams
import com.anthropic.models.beta.memorystores.memories.MemoryRetrieveParams
import com.anthropic.models.beta.memorystores.memories.MemoryUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemoryServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryServiceAsync = client.beta().memoryStores().memories()

        val betaManagedAgentsMemoryFuture =
            memoryServiceAsync.create(
                MemoryCreateParams.builder()
                    .memoryStoreId("memory_store_id")
                    .view(BetaManagedAgentsMemoryView.BASIC)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .content("content")
                    .path("xx")
                    .build()
            )

        val betaManagedAgentsMemory = betaManagedAgentsMemoryFuture.get()
        betaManagedAgentsMemory.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryServiceAsync = client.beta().memoryStores().memories()

        val betaManagedAgentsMemoryFuture =
            memoryServiceAsync.retrieve(
                MemoryRetrieveParams.builder()
                    .memoryStoreId("memory_store_id")
                    .memoryId("memory_id")
                    .view(BetaManagedAgentsMemoryView.BASIC)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsMemory = betaManagedAgentsMemoryFuture.get()
        betaManagedAgentsMemory.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryServiceAsync = client.beta().memoryStores().memories()

        val betaManagedAgentsMemoryFuture =
            memoryServiceAsync.update(
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
            )

        val betaManagedAgentsMemory = betaManagedAgentsMemoryFuture.get()
        betaManagedAgentsMemory.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryServiceAsync = client.beta().memoryStores().memories()

        val pageFuture = memoryServiceAsync.list("memory_store_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryServiceAsync = client.beta().memoryStores().memories()

        val betaManagedAgentsDeletedMemoryFuture =
            memoryServiceAsync.delete(
                MemoryDeleteParams.builder()
                    .memoryStoreId("memory_store_id")
                    .memoryId("memory_id")
                    .expectedContentSha256("expected_content_sha256")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeletedMemory = betaManagedAgentsDeletedMemoryFuture.get()
        betaManagedAgentsDeletedMemory.validate()
    }
}
