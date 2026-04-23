// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
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
internal class MemoryStoreServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreServiceAsync = client.beta().memoryStores()

        val betaManagedAgentsMemoryStoreFuture =
            memoryStoreServiceAsync.create(
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

        val betaManagedAgentsMemoryStore = betaManagedAgentsMemoryStoreFuture.get()
        betaManagedAgentsMemoryStore.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreServiceAsync = client.beta().memoryStores()

        val betaManagedAgentsMemoryStoreFuture =
            memoryStoreServiceAsync.retrieve(
                MemoryStoreRetrieveParams.builder()
                    .memoryStoreId("memory_store_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsMemoryStore = betaManagedAgentsMemoryStoreFuture.get()
        betaManagedAgentsMemoryStore.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreServiceAsync = client.beta().memoryStores()

        val betaManagedAgentsMemoryStoreFuture =
            memoryStoreServiceAsync.update(
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

        val betaManagedAgentsMemoryStore = betaManagedAgentsMemoryStoreFuture.get()
        betaManagedAgentsMemoryStore.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreServiceAsync = client.beta().memoryStores()

        val pageFuture = memoryStoreServiceAsync.list()

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
        val memoryStoreServiceAsync = client.beta().memoryStores()

        val betaManagedAgentsDeletedMemoryStoreFuture =
            memoryStoreServiceAsync.delete(
                MemoryStoreDeleteParams.builder()
                    .memoryStoreId("memory_store_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeletedMemoryStore = betaManagedAgentsDeletedMemoryStoreFuture.get()
        betaManagedAgentsDeletedMemoryStore.validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryStoreServiceAsync = client.beta().memoryStores()

        val betaManagedAgentsMemoryStoreFuture =
            memoryStoreServiceAsync.archive(
                MemoryStoreArchiveParams.builder()
                    .memoryStoreId("memory_store_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsMemoryStore = betaManagedAgentsMemoryStoreFuture.get()
        betaManagedAgentsMemoryStore.validate()
    }
}
