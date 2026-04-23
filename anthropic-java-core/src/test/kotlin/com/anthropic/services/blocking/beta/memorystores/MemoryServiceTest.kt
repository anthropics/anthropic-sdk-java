// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.memorystores

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
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
internal class MemoryServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryService = client.beta().memoryStores().memories()

        val betaManagedAgentsMemory =
            memoryService.create(
                MemoryCreateParams.builder()
                    .memoryStoreId("memory_store_id")
                    .view(BetaManagedAgentsMemoryView.BASIC)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .content("content")
                    .path("xx")
                    .build()
            )

        betaManagedAgentsMemory.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryService = client.beta().memoryStores().memories()

        val betaManagedAgentsMemory =
            memoryService.retrieve(
                MemoryRetrieveParams.builder()
                    .memoryStoreId("memory_store_id")
                    .memoryId("memory_id")
                    .view(BetaManagedAgentsMemoryView.BASIC)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsMemory.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryService = client.beta().memoryStores().memories()

        val betaManagedAgentsMemory =
            memoryService.update(
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

        betaManagedAgentsMemory.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryService = client.beta().memoryStores().memories()

        val page = memoryService.list("memory_store_id")

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryService = client.beta().memoryStores().memories()

        val betaManagedAgentsDeletedMemory =
            memoryService.delete(
                MemoryDeleteParams.builder()
                    .memoryStoreId("memory_store_id")
                    .memoryId("memory_id")
                    .expectedContentSha256("expected_content_sha256")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsDeletedMemory.validate()
    }
}
