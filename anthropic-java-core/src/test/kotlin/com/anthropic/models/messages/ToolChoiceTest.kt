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

internal class ToolChoiceTest {

    @Test
    fun ofAuto() {
        val auto = ToolChoiceAuto.builder().disableParallelToolUse(true).build()

        val toolChoice = ToolChoice.ofAuto(auto)

        assertThat(presentVariants(toolChoice)).isEqualTo(mapOf("auto" to Optional.of(auto)))
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

        assertThat(presentVariants(toolChoice)).isEqualTo(mapOf("any" to Optional.of(any)))
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

        assertThat(presentVariants(toolChoice)).isEqualTo(mapOf("tool" to Optional.of(tool)))
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

        assertThat(presentVariants(toolChoice)).isEqualTo(mapOf("none" to Optional.of(none)))
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

    @Test
    fun unknownVariantCommonProperties() {
        val toolChoice =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "disable_parallel_tool_use" to true)
                    ),
                    jacksonTypeRef<ToolChoice>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { toolChoice.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(toolChoice.disableParallelToolUse()).contains(true)

        val mismatchedToolChoice =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "disable_parallel_tool_use" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<ToolChoice>(),
                )

        assertThat(mismatchedToolChoice.disableParallelToolUse()).isEmpty
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

        assertThat(toolChoice.disableParallelToolUse()).isEmpty
    }

    private fun presentVariants(toolChoice: ToolChoice): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "auto" to toolChoice.auto(),
                "any" to toolChoice.any(),
                "tool" to toolChoice.tool(),
                "none" to toolChoice.none(),
            )
            .filterValues { it.isPresent }
}
