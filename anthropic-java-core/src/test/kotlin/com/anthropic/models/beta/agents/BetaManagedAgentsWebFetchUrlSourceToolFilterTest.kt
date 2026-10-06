package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsWebFetchUrlSourceToolFilterTest {

    @Test
    fun ofAll() {
        val all = BetaManagedAgentsWebFetchUrlSourceAll.builder().build()

        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(all)

        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.all()).contains(all)
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.none()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.only()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.except()).isEmpty
    }

    @Test
    fun ofAllRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(
                BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
            )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilter =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceToolFilter),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilter>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilter)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceToolFilter)
    }

    @Test
    fun ofNone() {
        val none = BetaManagedAgentsWebFetchUrlSourceNone.builder().build()

        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofNone(none)

        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.all()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.none()).contains(none)
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.only()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.except()).isEmpty
    }

    @Test
    fun ofNoneRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofNone(
                BetaManagedAgentsWebFetchUrlSourceNone.builder().build()
            )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilter =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceToolFilter),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilter>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilter)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceToolFilter)
    }

    @Test
    fun ofOnly() {
        val only =
            BetaManagedAgentsWebFetchUrlSourceOnly.builder()
                .addTool(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
                .build()

        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofOnly(only)

        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.all()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.none()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.only()).contains(only)
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.except()).isEmpty
    }

    @Test
    fun ofOnlyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofOnly(
                BetaManagedAgentsWebFetchUrlSourceOnly.builder()
                    .addTool(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilter =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceToolFilter),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilter>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilter)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceToolFilter)
    }

    @Test
    fun ofExcept() {
        val except =
            BetaManagedAgentsWebFetchUrlSourceExcept.builder()
                .addTool(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
                .build()

        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofExcept(except)

        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.all()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.none()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.only()).isEmpty
        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.except()).contains(except)
    }

    @Test
    fun ofExceptRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofExcept(
                BetaManagedAgentsWebFetchUrlSourceExcept.builder()
                    .addTool(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilter =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceToolFilter),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilter>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilter)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceToolFilter)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "tools" to listOf(mapOf("name" to "x", "type" to "tool_reference")),
                        )
                    ),
                    jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilter>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsWebFetchUrlSourceToolFilter.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.tools().getOrNull())
            .containsExactly(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
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
        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilter>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsWebFetchUrlSourceToolFilter.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilter.tools()).isEmpty
    }
}
