// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.memorystores

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemoryView
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRedactParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRetrieveParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemoryVersionServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryVersionService = client.beta().memoryStores().memoryVersions()

        val betaManagedAgentsMemoryVersion =
            memoryVersionService.retrieve(
                MemoryVersionRetrieveParams.builder()
                    .memoryStoreId("memory_store_id")
                    .memoryVersionId("memory_version_id")
                    .view(BetaManagedAgentsMemoryView.BASIC)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsMemoryVersion.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryVersionService = client.beta().memoryStores().memoryVersions()

        val page = memoryVersionService.list("memory_store_id")

        page.response().validate()
    }

    @Test
    fun redact() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memoryVersionService = client.beta().memoryStores().memoryVersions()

        val betaManagedAgentsMemoryVersion =
            memoryVersionService.redact(
                MemoryVersionRedactParams.builder()
                    .memoryStoreId("memory_store_id")
                    .memoryVersionId("memory_version_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsMemoryVersion.validate()
    }
}
