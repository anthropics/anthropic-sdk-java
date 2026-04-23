// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.memorystores.MemoryStoreArchiveParams
import com.anthropic.models.beta.memorystores.MemoryStoreCreateParams
import com.anthropic.models.beta.memorystores.MemoryStoreDeleteParams
import com.anthropic.models.beta.memorystores.MemoryStoreRetrieveParams
import com.anthropic.models.beta.memorystores.MemoryStoreUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemoryStoreServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreService = client.beta().memoryStores()

        val betaManagedAgentsMemoryStore =
            memoryStoreService.create(
                MemoryStoreCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .name("x")
                    .description("description")
                    .metadata(
                        MemoryStoreCreateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .build()
            )

        betaManagedAgentsMemoryStore.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreService = client.beta().memoryStores()

        val betaManagedAgentsMemoryStore =
            memoryStoreService.retrieve(
                MemoryStoreRetrieveParams.builder()
                    .memoryStoreId("memory_store_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsMemoryStore.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreService = client.beta().memoryStores()

        val betaManagedAgentsMemoryStore =
            memoryStoreService.update(
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
            )

        betaManagedAgentsMemoryStore.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreService = client.beta().memoryStores()

        val page = memoryStoreService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreService = client.beta().memoryStores()

        val betaManagedAgentsDeletedMemoryStore =
            memoryStoreService.delete(
                MemoryStoreDeleteParams.builder()
                    .memoryStoreId("memory_store_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsDeletedMemoryStore.validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreService = client.beta().memoryStores()

        val betaManagedAgentsMemoryStore =
            memoryStoreService.archive(
                MemoryStoreArchiveParams.builder()
                    .memoryStoreId("memory_store_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsMemoryStore.validate()
    }
}
