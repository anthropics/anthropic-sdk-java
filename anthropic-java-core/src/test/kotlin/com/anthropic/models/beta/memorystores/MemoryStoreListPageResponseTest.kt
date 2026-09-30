package com.anthropic.models.beta.memorystores

import com.anthropic.core.JsonValue
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
                        .id("id")
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .description("description")
                        .metadata(
                            BetaManagedAgentsMemoryStore.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .name("name")
                        .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(memoryStoreListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryStore.builder()
                    .id("id")
                    .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .description("description")
                    .metadata(
                        BetaManagedAgentsMemoryStore.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .name("name")
                    .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(memoryStoreListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseMemoryStoreListPageResponse = MemoryStoreListPageResponse.builder().build()

        val memoryStoreListPageResponse =
            baseMemoryStoreListPageResponse
                .toBuilder()
                .addData(
                    BetaManagedAgentsMemoryStore.builder()
                        .id("id")
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .description("description")
                        .metadata(
                            BetaManagedAgentsMemoryStore.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .name("name")
                        .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .build()

        assertThat(memoryStoreListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsMemoryStore.builder()
                    .id("id")
                    .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .description("description")
                    .metadata(
                        BetaManagedAgentsMemoryStore.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .name("name")
                    .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
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
                        .id("id")
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .description("description")
                        .metadata(
                            BetaManagedAgentsMemoryStore.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .name("name")
                        .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedMemoryStoreListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memoryStoreListPageResponse),
                jacksonTypeRef<MemoryStoreListPageResponse>(),
            )

        assertThat(roundtrippedMemoryStoreListPageResponse).isEqualTo(memoryStoreListPageResponse)
    }
}
