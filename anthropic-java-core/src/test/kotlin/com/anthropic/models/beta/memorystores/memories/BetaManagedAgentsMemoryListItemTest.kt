package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Optional
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
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .content("content")
                .build()

        val betaManagedAgentsMemoryListItem = BetaManagedAgentsMemoryListItem.ofMemory(memory)

        assertThat(presentVariants(betaManagedAgentsMemoryListItem))
            .isEqualTo(mapOf("memory" to Optional.of(memory)))
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
        val memoryPrefix = BetaManagedAgentsMemoryPrefix.of("path")

        val betaManagedAgentsMemoryListItem =
            BetaManagedAgentsMemoryListItem.ofMemoryPrefix(memoryPrefix)

        assertThat(presentVariants(betaManagedAgentsMemoryListItem))
            .isEqualTo(mapOf("memoryPrefix" to Optional.of(memoryPrefix)))
    }

    @Test
    fun ofMemoryPrefixRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryListItem =
            BetaManagedAgentsMemoryListItem.ofMemoryPrefix(BetaManagedAgentsMemoryPrefix.of("path"))

        val roundtrippedBetaManagedAgentsMemoryListItem =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryListItem),
                jacksonTypeRef<BetaManagedAgentsMemoryListItem>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryListItem)
            .isEqualTo(betaManagedAgentsMemoryListItem)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsMemoryListItem =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "path" to "path")),
                    jacksonTypeRef<BetaManagedAgentsMemoryListItem>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMemoryListItem.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsMemoryListItem.path()).isEqualTo("path")

        val mismatchedBetaManagedAgentsMemoryListItem =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "path" to listOf("invalid"))),
                    jacksonTypeRef<BetaManagedAgentsMemoryListItem>(),
                )

        assertThrows<AnthropicInvalidDataException> {
            mismatchedBetaManagedAgentsMemoryListItem.path()
        }
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

        assertThrows<AnthropicInvalidDataException> { betaManagedAgentsMemoryListItem.path() }
    }

    private fun presentVariants(
        betaManagedAgentsMemoryListItem: BetaManagedAgentsMemoryListItem
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "memory" to betaManagedAgentsMemoryListItem.memory(),
                "memoryPrefix" to betaManagedAgentsMemoryListItem.memoryPrefix(),
            )
            .filterValues { it.isPresent }
}
