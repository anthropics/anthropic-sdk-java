// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryTest {

    @Test
    fun create() {
        val betaManagedAgentsMemory =
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

        assertThat(betaManagedAgentsMemory.id()).isEqualTo("id")
        assertThat(betaManagedAgentsMemory.contentSha256()).isEqualTo("content_sha256")
        assertThat(betaManagedAgentsMemory.contentSizeBytes()).isEqualTo(0)
        assertThat(betaManagedAgentsMemory.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMemory.memoryStoreId()).isEqualTo("memory_store_id")
        assertThat(betaManagedAgentsMemory.memoryVersionId()).isEqualTo("memory_version_id")
        assertThat(betaManagedAgentsMemory.path()).isEqualTo("path")
        assertThat(betaManagedAgentsMemory.type()).isEqualTo(BetaManagedAgentsMemory.Type.MEMORY)
        assertThat(betaManagedAgentsMemory.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMemory.content()).contains("content")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemory =
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

        val roundtrippedBetaManagedAgentsMemory =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemory),
                jacksonTypeRef<BetaManagedAgentsMemory>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemory).isEqualTo(betaManagedAgentsMemory)
    }
}
