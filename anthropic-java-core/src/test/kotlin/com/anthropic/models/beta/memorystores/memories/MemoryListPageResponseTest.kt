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
                        .id("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                        .contentSha256(
                            "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                        )
                        .contentSizeBytes(28)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .memoryVersionId("memver_011CZkZBJq5dWxk9fVLNcPht")
                        .path("/preferences/formatting.md")
                        .type(BetaManagedAgentsMemory.Type.MEMORY)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .content(null)
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(memoryListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryListItem.ofMemory(
                    BetaManagedAgentsMemory.builder()
                        .id("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                        .contentSha256(
                            "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                        )
                        .contentSizeBytes(28)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .memoryVersionId("memver_011CZkZBJq5dWxk9fVLNcPht")
                        .path("/preferences/formatting.md")
                        .type(BetaManagedAgentsMemory.Type.MEMORY)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .content(null)
                        .build()
                )
            )
        assertThat(memoryListPageResponse.nextPage()).contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseMemoryListPageResponse = MemoryListPageResponse.builder().build()

        val memoryListPageResponse =
            baseMemoryListPageResponse
                .toBuilder()
                .addData(
                    BetaManagedAgentsMemoryListItem.ofMemory(
                        BetaManagedAgentsMemory.builder()
                            .id("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                            .contentSha256(
                                "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                            )
                            .contentSizeBytes(28)
                            .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                            .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                            .memoryVersionId("memver_011CZkZBJq5dWxk9fVLNcPht")
                            .path("/preferences/formatting.md")
                            .type(BetaManagedAgentsMemory.Type.MEMORY)
                            .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                            .content(null)
                            .build()
                    )
                )
                .build()

        assertThat(memoryListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryListItem.ofMemory(
                    BetaManagedAgentsMemory.builder()
                        .id("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                        .contentSha256(
                            "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                        )
                        .contentSizeBytes(28)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .memoryVersionId("memver_011CZkZBJq5dWxk9fVLNcPht")
                        .path("/preferences/formatting.md")
                        .type(BetaManagedAgentsMemory.Type.MEMORY)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .content(null)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memoryListPageResponse =
            MemoryListPageResponse.builder()
                .addData(
                    BetaManagedAgentsMemory.builder()
                        .id("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                        .contentSha256(
                            "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                        )
                        .contentSizeBytes(28)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .memoryVersionId("memver_011CZkZBJq5dWxk9fVLNcPht")
                        .path("/preferences/formatting.md")
                        .type(BetaManagedAgentsMemory.Type.MEMORY)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .content(null)
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedMemoryListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memoryListPageResponse),
                jacksonTypeRef<MemoryListPageResponse>(),
            )

        assertThat(roundtrippedMemoryListPageResponse).isEqualTo(memoryListPageResponse)
    }
}
