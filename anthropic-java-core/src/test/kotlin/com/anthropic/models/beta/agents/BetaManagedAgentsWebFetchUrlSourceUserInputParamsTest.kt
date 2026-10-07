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

internal class BetaManagedAgentsWebFetchUrlSourceUserInputParamsTest {

    @Test
    fun ofShorthand() {
        val shorthand = BetaManagedAgentsWebFetchUrlSourceShorthand.ALL

        val betaManagedAgentsWebFetchUrlSourceUserInputParams =
            BetaManagedAgentsWebFetchUrlSourceUserInputParams.ofShorthand(shorthand)

        assertThat(betaManagedAgentsWebFetchUrlSourceUserInputParams.shorthand())
            .contains(shorthand)
        assertThat(
                betaManagedAgentsWebFetchUrlSourceUserInputParams
                    .betaManagedAgentsWebFetchUrlSourceUserInput()
            )
            .isEmpty
    }

    @Test
    fun ofShorthandRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceUserInputParams =
            BetaManagedAgentsWebFetchUrlSourceUserInputParams.ofShorthand(
                BetaManagedAgentsWebFetchUrlSourceShorthand.ALL
            )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceUserInputParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceUserInputParams),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceUserInputParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceUserInputParams)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceUserInputParams)
    }

    @Test
    fun ofBetaManagedAgentsWebFetchUrlSourceUserInput() {
        val betaManagedAgentsWebFetchUrlSourceUserInput =
            BetaManagedAgentsWebFetchUrlSourceUserInput.ofAll(
                BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
            )

        val betaManagedAgentsWebFetchUrlSourceUserInputParams =
            BetaManagedAgentsWebFetchUrlSourceUserInputParams
                .ofBetaManagedAgentsWebFetchUrlSourceUserInput(
                    betaManagedAgentsWebFetchUrlSourceUserInput
                )

        assertThat(betaManagedAgentsWebFetchUrlSourceUserInputParams.shorthand()).isEmpty
        assertThat(
                betaManagedAgentsWebFetchUrlSourceUserInputParams
                    .betaManagedAgentsWebFetchUrlSourceUserInput()
            )
            .contains(betaManagedAgentsWebFetchUrlSourceUserInput)
    }

    @Test
    fun ofBetaManagedAgentsWebFetchUrlSourceUserInputRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceUserInputParams =
            BetaManagedAgentsWebFetchUrlSourceUserInputParams
                .ofBetaManagedAgentsWebFetchUrlSourceUserInput(
                    BetaManagedAgentsWebFetchUrlSourceUserInput.ofAll(
                        BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
                    )
                )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceUserInputParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceUserInputParams),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceUserInputParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceUserInputParams)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceUserInputParams)
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
        val betaManagedAgentsWebFetchUrlSourceUserInputParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceUserInputParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsWebFetchUrlSourceUserInputParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
