package com.anthropic.services.async.beta.memorystores

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemoryView
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRedactParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemoryVersionServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val memoryVersionServiceAsync = client.beta().memoryStores().memoryVersions()

        val betaManagedAgentsMemoryVersionFuture =
            memoryVersionServiceAsync.retrieve(
                MemoryVersionRetrieveParams.builder()
                    .memoryStoreId("memory_store_id")
                    .memoryVersionId("memory_version_id")
                    .view(BetaManagedAgentsMemoryView.BASIC)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaManagedAgentsMemoryVersion = betaManagedAgentsMemoryVersionFuture.get()
        betaManagedAgentsMemoryVersion.validate()
    }

    @Test
    fun list() {
        val memoryVersionServiceAsync = client.beta().memoryStores().memoryVersions()

        val pageFuture = memoryVersionServiceAsync.list("memory_store_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun redact() {
        val memoryVersionServiceAsync = client.beta().memoryStores().memoryVersions()

        val betaManagedAgentsMemoryVersionFuture =
            memoryVersionServiceAsync.redact(
                MemoryVersionRedactParams.builder()
                    .memoryStoreId("memory_store_id")
                    .memoryVersionId("memory_version_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaManagedAgentsMemoryVersion = betaManagedAgentsMemoryVersionFuture.get()
        betaManagedAgentsMemoryVersion.validate()
    }
}
