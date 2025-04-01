// File generated from our OpenAPI spec by Stainless.

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

internal class ToolChoiceTest {

    @Test
    fun ofAuto() {
        val auto = ToolChoiceAuto.builder().disableParallelToolUse(true).build()

        val toolChoice = ToolChoice.ofAuto(auto)

        assertThat(toolChoice.auto()).contains(auto)
        assertThat(toolChoice.any()).isEmpty
        assertThat(toolChoice.tool()).isEmpty
        assertThat(toolChoice.none()).isEmpty
    }

    @Test
    fun ofAutoRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolChoice =
            ToolChoice.ofAuto(ToolChoiceAuto.builder().disableParallelToolUse(true).build())

        val roundtrippedToolChoice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolChoice),
                jacksonTypeRef<ToolChoice>(),
            )

        assertThat(roundtrippedToolChoice).isEqualTo(toolChoice)
    }

    @Test
    fun ofAny() {
        val any = ToolChoiceAny.builder().disableParallelToolUse(true).build()

        val toolChoice = ToolChoice.ofAny(any)

        assertThat(toolChoice.auto()).isEmpty
        assertThat(toolChoice.any()).contains(any)
        assertThat(toolChoice.tool()).isEmpty
        assertThat(toolChoice.none()).isEmpty
    }

    @Test
    fun ofAnyRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolChoice =
            ToolChoice.ofAny(ToolChoiceAny.builder().disableParallelToolUse(true).build())

        val roundtrippedToolChoice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolChoice),
                jacksonTypeRef<ToolChoice>(),
            )

        assertThat(roundtrippedToolChoice).isEqualTo(toolChoice)
    }

    @Test
    fun ofTool() {
        val tool = ToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()

        val toolChoice = ToolChoice.ofTool(tool)

        assertThat(toolChoice.auto()).isEmpty
        assertThat(toolChoice.any()).isEmpty
        assertThat(toolChoice.tool()).contains(tool)
        assertThat(toolChoice.none()).isEmpty
    }

    @Test
    fun ofToolRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolChoice =
            ToolChoice.ofTool(
                ToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()
            )

        val roundtrippedToolChoice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolChoice),
                jacksonTypeRef<ToolChoice>(),
            )

        assertThat(roundtrippedToolChoice).isEqualTo(toolChoice)
    }

    @Test
    fun ofNone() {
        val none = ToolChoiceNone.builder().build()

        val toolChoice = ToolChoice.ofNone(none)

        assertThat(toolChoice.auto()).isEmpty
        assertThat(toolChoice.any()).isEmpty
        assertThat(toolChoice.tool()).isEmpty
        assertThat(toolChoice.none()).contains(none)
    }

    @Test
    fun ofNoneRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolChoice = ToolChoice.ofNone(ToolChoiceNone.builder().build())

        val roundtrippedToolChoice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolChoice),
                jacksonTypeRef<ToolChoice>(),
            )

        assertThat(roundtrippedToolChoice).isEqualTo(toolChoice)
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
        val toolChoice = jsonMapper().convertValue(testCase.value, jacksonTypeRef<ToolChoice>())

        val e = assertThrows<AnthropicInvalidDataException> { toolChoice.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
