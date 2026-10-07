package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsWebFetchUrlSourceToolFilterParamsTest {

    @Test
    fun ofShorthand() {
        val shorthand = BetaManagedAgentsWebFetchUrlSourceShorthand.ALL

        val betaManagedAgentsWebFetchUrlSourceToolFilterParams =
            BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofShorthand(shorthand)

        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilterParams.shorthand())
            .contains(shorthand)
        assertThat(
                betaManagedAgentsWebFetchUrlSourceToolFilterParams
                    .betaManagedAgentsWebFetchUrlSourceToolFilter()
            )
            .isEmpty
    }

    @Test
    fun ofShorthandRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceToolFilterParams =
            BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofShorthand(
                BetaManagedAgentsWebFetchUrlSourceShorthand.ALL
            )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilterParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceToolFilterParams),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilterParams)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceToolFilterParams)
    }

    @Test
    fun ofBetaManagedAgentsWebFetchUrlSourceToolFilter() {
        val betaManagedAgentsWebFetchUrlSourceToolFilter =
            BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(
                BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
            )

        val betaManagedAgentsWebFetchUrlSourceToolFilterParams =
            BetaManagedAgentsWebFetchUrlSourceToolFilterParams
                .ofBetaManagedAgentsWebFetchUrlSourceToolFilter(
                    betaManagedAgentsWebFetchUrlSourceToolFilter
                )

        assertThat(betaManagedAgentsWebFetchUrlSourceToolFilterParams.shorthand()).isEmpty
        assertThat(
                betaManagedAgentsWebFetchUrlSourceToolFilterParams
                    .betaManagedAgentsWebFetchUrlSourceToolFilter()
            )
            .contains(betaManagedAgentsWebFetchUrlSourceToolFilter)
    }

    @Test
    fun ofBetaManagedAgentsWebFetchUrlSourceToolFilterRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceToolFilterParams =
            BetaManagedAgentsWebFetchUrlSourceToolFilterParams
                .ofBetaManagedAgentsWebFetchUrlSourceToolFilter(
                    BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(
                        BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
                    )
                )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilterParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceToolFilterParams),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceToolFilterParams)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceToolFilterParams)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaManagedAgentsWebFetchUrlSourceToolFilterParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsWebFetchUrlSourceToolFilterParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
