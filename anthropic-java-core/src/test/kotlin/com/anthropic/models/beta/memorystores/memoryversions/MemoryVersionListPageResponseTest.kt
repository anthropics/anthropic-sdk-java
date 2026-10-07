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
                        .id("memver_011CZkZBJq5dWxk9fVLNcPht")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .memoryId("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                        .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                        .type(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
                        .content(null)
                        .contentSha256(
                            "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                        )
                        .contentSizeBytes(28)
                        .sessionCreatedBy("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .path("/preferences/formatting.md")
                        .redactedAt(null)
                        .sessionRedactedBy("x")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(memoryVersionListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryVersion.builder()
                    .id("memver_011CZkZBJq5dWxk9fVLNcPht")
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .memoryId("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                    .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                    .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                    .type(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
                    .content(null)
                    .contentSha256(
                        "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                    )
                    .contentSizeBytes(28)
                    .sessionCreatedBy("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .path("/preferences/formatting.md")
                    .redactedAt(null)
                    .sessionRedactedBy("x")
                    .build()
            )
        assertThat(memoryVersionListPageResponse.nextPage())
            .contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseMemoryVersionListPageResponse = MemoryVersionListPageResponse.builder().build()

        val memoryVersionListPageResponse =
            baseMemoryVersionListPageResponse
                .toBuilder()
                .addData(
                    BetaManagedAgentsMemoryVersion.builder()
                        .id("memver_011CZkZBJq5dWxk9fVLNcPht")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .memoryId("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                        .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                        .type(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
                        .content(null)
                        .contentSha256(
                            "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                        )
                        .contentSizeBytes(28)
                        .sessionCreatedBy("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .path("/preferences/formatting.md")
                        .redactedAt(null)
                        .sessionRedactedBy("x")
                        .build()
                )
                .build()

        assertThat(memoryVersionListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryVersion.builder()
                    .id("memver_011CZkZBJq5dWxk9fVLNcPht")
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .memoryId("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                    .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                    .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                    .type(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
                    .content(null)
                    .contentSha256(
                        "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                    )
                    .contentSizeBytes(28)
                    .sessionCreatedBy("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .path("/preferences/formatting.md")
                    .redactedAt(null)
                    .sessionRedactedBy("x")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memoryVersionListPageResponse =
            MemoryVersionListPageResponse.builder()
                .addData(
                    BetaManagedAgentsMemoryVersion.builder()
                        .id("memver_011CZkZBJq5dWxk9fVLNcPht")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .memoryId("mem_011CZkZ9X2dpNyB6YbtxvB6e")
                        .memoryStoreId("memstore_01Wf3kQ8tZxB2mVr7HcJ4aNd")
                        .operation(BetaManagedAgentsMemoryVersionOperation.CREATED)
                        .type(BetaManagedAgentsMemoryVersion.Type.MEMORY_VERSION)
                        .content(null)
                        .contentSha256(
                            "ba7936d94c84d948a2232088f78228f175df6a8353b2d5bc9228eee5794a0024"
                        )
                        .contentSizeBytes(28)
                        .sessionCreatedBy("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .path("/preferences/formatting.md")
                        .redactedAt(null)
                        .sessionRedactedBy("x")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
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
