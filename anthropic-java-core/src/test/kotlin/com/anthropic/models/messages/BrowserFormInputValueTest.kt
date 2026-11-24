package com.anthropic.models.messages

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

internal class BrowserFormInputValueTest {

    @Test
    fun ofString() {
        val string = "string"

        val browserFormInputValue = BrowserFormInputValue.ofString(string)

        assertThat(presentVariants(browserFormInputValue))
            .isEqualTo(mapOf("string" to Optional.of(string)))
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

        assertThat(presentVariants(browserFormInputValue))
            .isEqualTo(mapOf("number" to Optional.of(number)))
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

        assertThat(presentVariants(browserFormInputValue))
            .isEqualTo(mapOf("bool" to Optional.of(bool)))
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

    private fun presentVariants(
        browserFormInputValue: BrowserFormInputValue
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "string" to browserFormInputValue.string(),
                "number" to browserFormInputValue.number(),
                "bool" to browserFormInputValue.bool(),
            )
            .filterValues { it.isPresent }
}
