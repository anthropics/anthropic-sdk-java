// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsMemoryListItemTest {

    @Test
    fun ofMemory() {
        val memory =
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

        val betaManagedAgentsMemoryListItem = BetaManagedAgentsMemoryListItem.ofMemory(memory)

        assertThat(betaManagedAgentsMemoryListItem.memory()).contains(memory)
        assertThat(betaManagedAgentsMemoryListItem.memoryPrefix()).isEmpty
    }

    @Test
    fun ofMemoryRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryListItem =
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

        val roundtrippedBetaManagedAgentsMemoryListItem =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryListItem),
                jacksonTypeRef<BetaManagedAgentsMemoryListItem>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryListItem)
            .isEqualTo(betaManagedAgentsMemoryListItem)
    }

    @Test
    fun ofMemoryPrefix() {
        val memoryPrefix =
            BetaManagedAgentsMemoryPrefix.builder()
                .path("path")
                .type(BetaManagedAgentsMemoryPrefix.Type.MEMORY_PREFIX)
                .build()

        val betaManagedAgentsMemoryListItem =
            BetaManagedAgentsMemoryListItem.ofMemoryPrefix(memoryPrefix)

        assertThat(betaManagedAgentsMemoryListItem.memory()).isEmpty
        assertThat(betaManagedAgentsMemoryListItem.memoryPrefix()).contains(memoryPrefix)
    }

    @Test
    fun ofMemoryPrefixRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryListItem =
            BetaManagedAgentsMemoryListItem.ofMemoryPrefix(
                BetaManagedAgentsMemoryPrefix.builder()
                    .path("path")
                    .type(BetaManagedAgentsMemoryPrefix.Type.MEMORY_PREFIX)
                    .build()
            )

        val roundtrippedBetaManagedAgentsMemoryListItem =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryListItem),
                jacksonTypeRef<BetaManagedAgentsMemoryListItem>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryListItem)
            .isEqualTo(betaManagedAgentsMemoryListItem)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaManagedAgentsMemoryListItem =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsMemoryListItem>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMemoryListItem.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
