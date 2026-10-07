package com.anthropic.models.beta.memorystores

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryStoreListPageResponseTest {

    @Test
    fun create() {
        val memoryStoreListPageResponse =
            MemoryStoreListPageResponse.builder()
                .addData(
                    BetaManagedAgentsMemoryStore.builder()
                        .id("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .description("Per-user preferences and project context.")
                        .metadata(BetaManagedAgentsMemoryStore.Metadata.builder().build())
                        .name("User Preferences")
                        .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(memoryStoreListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryStore.builder()
                    .id("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                    .archivedAt(null)
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .description("Per-user preferences and project context.")
                    .metadata(BetaManagedAgentsMemoryStore.Metadata.builder().build())
                    .name("User Preferences")
                    .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )
        assertThat(memoryStoreListPageResponse.nextPage())
            .contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseMemoryStoreListPageResponse = MemoryStoreListPageResponse.builder().build()

        val memoryStoreListPageResponse =
            baseMemoryStoreListPageResponse
                .toBuilder()
                .addData(
                    BetaManagedAgentsMemoryStore.builder()
                        .id("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .description("Per-user preferences and project context.")
                        .metadata(BetaManagedAgentsMemoryStore.Metadata.builder().build())
                        .name("User Preferences")
                        .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .build()

        assertThat(memoryStoreListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryStore.builder()
                    .id("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                    .archivedAt(null)
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .description("Per-user preferences and project context.")
                    .metadata(BetaManagedAgentsMemoryStore.Metadata.builder().build())
                    .name("User Preferences")
                    .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memoryStoreListPageResponse =
            MemoryStoreListPageResponse.builder()
                .addData(
                    BetaManagedAgentsMemoryStore.builder()
                        .id("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .description("Per-user preferences and project context.")
                        .metadata(BetaManagedAgentsMemoryStore.Metadata.builder().build())
                        .name("User Preferences")
                        .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedMemoryStoreListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memoryStoreListPageResponse),
                jacksonTypeRef<MemoryStoreListPageResponse>(),
            )

        assertThat(roundtrippedMemoryStoreListPageResponse).isEqualTo(memoryStoreListPageResponse)
    }
}
