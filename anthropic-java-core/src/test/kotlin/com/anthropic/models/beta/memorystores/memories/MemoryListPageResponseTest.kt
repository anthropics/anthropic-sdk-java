// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryListPageResponseTest {

    @Test
    fun create() {
        val memoryListPageResponse =
            MemoryListPageResponse.builder()
                .addData(
                    BetaManagedAgentsMemory.builder()
                        .id("id")
                        .contentSha256("content_sha256")
                        .contentSizeBytes(0)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .memoryStoreId("memory_store_id")
                        .memoryVersionId("memory_version_id")
                        .path("path")
                        .type(BetaManagedAgentsMemory.Type.MEMORY)
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .content("content")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(memoryListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryListItem.ofMemory(
                    BetaManagedAgentsMemory.builder()
                        .id("id")
                        .contentSha256("content_sha256")
                        .contentSizeBytes(0)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .memoryStoreId("memory_store_id")
                        .memoryVersionId("memory_version_id")
                        .path("path")
                        .type(BetaManagedAgentsMemory.Type.MEMORY)
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .content("content")
                        .build()
                )
            )
        assertThat(memoryListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memoryListPageResponse =
            MemoryListPageResponse.builder()
                .addData(
                    BetaManagedAgentsMemory.builder()
                        .id("id")
                        .contentSha256("content_sha256")
                        .contentSizeBytes(0)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .memoryStoreId("memory_store_id")
                        .memoryVersionId("memory_version_id")
                        .path("path")
                        .type(BetaManagedAgentsMemory.Type.MEMORY)
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .content("content")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedMemoryListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memoryListPageResponse),
                jacksonTypeRef<MemoryListPageResponse>(),
            )

        assertThat(roundtrippedMemoryListPageResponse).isEqualTo(memoryListPageResponse)
    }
}
