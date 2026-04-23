// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryVersionTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryVersion =
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

        assertThat(betaManagedAgentsMemoryVersion.id()).isEqualTo("id")
        assertThat(betaManagedAgentsMemoryVersion.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMemoryVersion.memoryId()).isEqualTo("memory_id")
        assertThat(betaManagedAgentsMemoryVersion.memoryStoreId()).isEqualTo("memory_store_id")
        assertThat(betaManagedAgentsMemoryVersion.operation())
            .isEqualTo(BetaManagedAgentsMemoryVersionOperation.CREATED)
        assertThat(betaManagedAgentsMemoryVersion.type())
            .isEqualTo(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
        assertThat(betaManagedAgentsMemoryVersion.content()).contains("content")
        assertThat(betaManagedAgentsMemoryVersion.contentSha256()).contains("content_sha256")
        assertThat(betaManagedAgentsMemoryVersion.contentSizeBytes()).contains(0)
        assertThat(betaManagedAgentsMemoryVersion.createdBy())
            .contains(
                BetaManagedAgentsActor.ofSession(
                    BetaManagedAgentsSessionActor.builder()
                        .sessionId("x")
                        .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
                        .build()
                )
            )
        assertThat(betaManagedAgentsMemoryVersion.path()).contains("path")
        assertThat(betaManagedAgentsMemoryVersion.redactedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMemoryVersion.redactedBy())
            .contains(
                BetaManagedAgentsActor.ofSession(
                    BetaManagedAgentsSessionActor.builder()
                        .sessionId("x")
                        .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryVersion =
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

        val roundtrippedBetaManagedAgentsMemoryVersion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryVersion),
                jacksonTypeRef<BetaManagedAgentsMemoryVersion>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryVersion)
            .isEqualTo(betaManagedAgentsMemoryVersion)
    }
}
