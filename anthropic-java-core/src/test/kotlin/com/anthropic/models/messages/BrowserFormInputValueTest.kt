package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BrowserFormInputValueTest {

    @Test
    fun ofString() {
        val string = "string"

        val browserFormInputValue = BrowserFormInputValue.ofString(string)

        assertThat(browserFormInputValue.string()).contains(string)
        assertThat(browserFormInputValue.number()).isEmpty
        assertThat(browserFormInputValue.bool()).isEmpty
    }

    @Test
    fun ofStringRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserFormInputValue = BrowserFormInputValue.ofString("string")

        val roundtrippedBrowserFormInputValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFormInputValue),
                jacksonTypeRef<BrowserFormInputValue>(),
            )

        assertThat(roundtrippedBrowserFormInputValue).isEqualTo(browserFormInputValue)
    }

    @Test
    fun ofNumber() {
        val number = 0.0

        val browserFormInputValue = BrowserFormInputValue.ofNumber(number)

        assertThat(browserFormInputValue.string()).isEmpty
        assertThat(browserFormInputValue.number()).contains(number)
        assertThat(browserFormInputValue.bool()).isEmpty
    }

    @Test
    fun ofNumberRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserFormInputValue = BrowserFormInputValue.ofNumber(0.0)

        val roundtrippedBrowserFormInputValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFormInputValue),
                jacksonTypeRef<BrowserFormInputValue>(),
            )

        assertThat(roundtrippedBrowserFormInputValue).isEqualTo(browserFormInputValue)
    }

    @Test
    fun ofBool() {
        val bool = true

        val browserFormInputValue = BrowserFormInputValue.ofBool(bool)

        assertThat(browserFormInputValue.string()).isEmpty
        assertThat(browserFormInputValue.number()).isEmpty
        assertThat(browserFormInputValue.bool()).contains(bool)
    }

    @Test
    fun ofBoolRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserFormInputValue = BrowserFormInputValue.ofBool(true)

        val roundtrippedBrowserFormInputValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFormInputValue),
                jacksonTypeRef<BrowserFormInputValue>(),
            )

        assertThat(roundtrippedBrowserFormInputValue).isEqualTo(browserFormInputValue)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        OBJECT(JsonValue.from(mapOf("invalid" to "object"))),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val browserFormInputValue =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BrowserFormInputValue>())

        val e = assertThrows<AnthropicInvalidDataException> { browserFormInputValue.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
