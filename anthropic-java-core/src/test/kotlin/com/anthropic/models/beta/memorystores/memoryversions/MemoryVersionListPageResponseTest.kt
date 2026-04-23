// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryVersionListPageResponseTest {

    @Test
    fun create() {
        val memoryVersionListPageResponse =
            MemoryVersionListPageResponse.builder()
                .addData(
                    BetaManagedAgentsMemoryVersion.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .memoryId("memory_id")
                        .memoryStoreId("memory_store_id")
                        .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                        .type(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
                        .content("content")
                        .contentSha256("content_sha256")
                        .contentSizeBytes(0)
                        .sessionCreatedBy("x")
                        .path("path")
                        .redactedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .sessionRedactedBy("x")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(memoryVersionListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryVersion.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .memoryId("memory_id")
                    .memoryStoreId("memory_store_id")
                    .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                    .type(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
                    .content("content")
                    .contentSha256("content_sha256")
                    .contentSizeBytes(0)
                    .sessionCreatedBy("x")
                    .path("path")
                    .redactedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionRedactedBy("x")
                    .build()
            )
        assertThat(memoryVersionListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memoryVersionListPageResponse =
            MemoryVersionListPageResponse.builder()
                .addData(
                    BetaManagedAgentsMemoryVersion.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .memoryId("memory_id")
                        .memoryStoreId("memory_store_id")
                        .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                        .type(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
                        .content("content")
                        .contentSha256("content_sha256")
                        .contentSizeBytes(0)
                        .sessionCreatedBy("x")
                        .path("path")
                        .redactedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .sessionRedactedBy("x")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedMemoryVersionListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memoryVersionListPageResponse),
                jacksonTypeRef<MemoryVersionListPageResponse>(),
            )

        assertThat(roundtrippedMemoryVersionListPageResponse)
            .isEqualTo(memoryVersionListPageResponse)
    }
}
