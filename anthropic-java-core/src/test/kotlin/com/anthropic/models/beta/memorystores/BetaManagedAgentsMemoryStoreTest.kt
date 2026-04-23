// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryStoreTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryStore =
            BetaManagedAgentsMemoryStore.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .name("name")
                .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .description("description")
                .metadata(
                    BetaManagedAgentsMemoryStore.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMemoryStore.id()).isEqualTo("id")
        assertThat(betaManagedAgentsMemoryStore.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMemoryStore.name()).isEqualTo("name")
        assertThat(betaManagedAgentsMemoryStore.type())
            .isEqualTo(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
        assertThat(betaManagedAgentsMemoryStore.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMemoryStore.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMemoryStore.description()).contains("description")
        assertThat(betaManagedAgentsMemoryStore.metadata())
            .contains(
                BetaManagedAgentsMemoryStore.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryStore =
            BetaManagedAgentsMemoryStore.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .name("name")
                .type(BetaManagedAgentsMemoryStore.Type.MEMORY_STORE)
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .description("description")
                .metadata(
                    BetaManagedAgentsMemoryStore.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMemoryStore =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryStore),
                jacksonTypeRef<BetaManagedAgentsMemoryStore>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryStore).isEqualTo(betaManagedAgentsMemoryStore)
    }
}
