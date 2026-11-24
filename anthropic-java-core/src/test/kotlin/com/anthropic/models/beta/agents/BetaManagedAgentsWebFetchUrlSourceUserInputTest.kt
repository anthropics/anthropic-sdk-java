package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsWebFetchUrlSourceUserInputTest {

    @Test
    fun ofAll() {
        val all = BetaManagedAgentsWebFetchUrlSourceAll.builder().build()

        val betaManagedAgentsWebFetchUrlSourceUserInput =
            BetaManagedAgentsWebFetchUrlSourceUserInput.ofAll(all)

        assertThat(presentVariants(betaManagedAgentsWebFetchUrlSourceUserInput))
            .isEqualTo(mapOf("all" to Optional.of(all)))
    }

    @Test
    fun ofAllRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceUserInput =
            BetaManagedAgentsWebFetchUrlSourceUserInput.ofAll(
                BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
            )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceUserInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceUserInput),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceUserInput>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceUserInput)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceUserInput)
    }

    @Test
    fun ofNone() {
        val none = BetaManagedAgentsWebFetchUrlSourceNone.builder().build()

        val betaManagedAgentsWebFetchUrlSourceUserInput =
            BetaManagedAgentsWebFetchUrlSourceUserInput.ofNone(none)

        assertThat(presentVariants(betaManagedAgentsWebFetchUrlSourceUserInput))
            .isEqualTo(mapOf("none" to Optional.of(none)))
    }

    @Test
    fun ofNoneRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceUserInput =
            BetaManagedAgentsWebFetchUrlSourceUserInput.ofNone(
                BetaManagedAgentsWebFetchUrlSourceNone.builder().build()
            )

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceUserInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceUserInput),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceUserInput>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceUserInput)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceUserInput)
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
        val betaManagedAgentsWebFetchUrlSourceUserInput =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceUserInput>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsWebFetchUrlSourceUserInput.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaManagedAgentsWebFetchUrlSourceUserInput: BetaManagedAgentsWebFetchUrlSourceUserInput
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "all" to betaManagedAgentsWebFetchUrlSourceUserInput.all(),
                "none" to betaManagedAgentsWebFetchUrlSourceUserInput.none(),
            )
            .filterValues { it.isPresent }
}
