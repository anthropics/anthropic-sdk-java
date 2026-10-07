package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaBrowserFormInputValueTest {

    @Test
    fun ofString() {
        val string = "string"

        val betaBrowserFormInputValue = BetaBrowserFormInputValue.ofString(string)

        assertThat(betaBrowserFormInputValue.string()).contains(string)
        assertThat(betaBrowserFormInputValue.number()).isEmpty
        assertThat(betaBrowserFormInputValue.bool()).isEmpty
    }

    @Test
    fun ofStringRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFormInputValue = BetaBrowserFormInputValue.ofString("string")

        val roundtrippedBetaBrowserFormInputValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFormInputValue),
                jacksonTypeRef<BetaBrowserFormInputValue>(),
            )

        assertThat(roundtrippedBetaBrowserFormInputValue).isEqualTo(betaBrowserFormInputValue)
    }

    @Test
    fun ofNumber() {
        val number = 0.0

        val betaBrowserFormInputValue = BetaBrowserFormInputValue.ofNumber(number)

        assertThat(betaBrowserFormInputValue.string()).isEmpty
        assertThat(betaBrowserFormInputValue.number()).contains(number)
        assertThat(betaBrowserFormInputValue.bool()).isEmpty
    }

    @Test
    fun ofNumberRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFormInputValue = BetaBrowserFormInputValue.ofNumber(0.0)

        val roundtrippedBetaBrowserFormInputValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFormInputValue),
                jacksonTypeRef<BetaBrowserFormInputValue>(),
            )

        assertThat(roundtrippedBetaBrowserFormInputValue).isEqualTo(betaBrowserFormInputValue)
    }

    @Test
    fun ofBool() {
        val bool = true

        val betaBrowserFormInputValue = BetaBrowserFormInputValue.ofBool(bool)

        assertThat(betaBrowserFormInputValue.string()).isEmpty
        assertThat(betaBrowserFormInputValue.number()).isEmpty
        assertThat(betaBrowserFormInputValue.bool()).contains(bool)
    }

    @Test
    fun ofBoolRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFormInputValue = BetaBrowserFormInputValue.ofBool(true)

        val roundtrippedBetaBrowserFormInputValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFormInputValue),
                jacksonTypeRef<BetaBrowserFormInputValue>(),
            )

        assertThat(roundtrippedBetaBrowserFormInputValue).isEqualTo(betaBrowserFormInputValue)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        OBJECT(JsonValue.from(mapOf("invalid" to "object"))),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaBrowserFormInputValue =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaBrowserFormInputValue>())

        val e = assertThrows<AnthropicInvalidDataException> { betaBrowserFormInputValue.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
