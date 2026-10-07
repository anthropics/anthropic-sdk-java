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

internal class ToolsetToolUseBlockTest {

    @Test
    fun ofBrowser() {
        val browser =
            BrowserToolUseBlock.ofNavigate(
                BrowserNavigateToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                    .build()
            )

        val toolsetToolUseBlock = ToolsetToolUseBlock.ofBrowser(browser)

        assertThat(toolsetToolUseBlock.browser()).contains(browser)
        assertThat(toolsetToolUseBlock.computer()).isEmpty
        assertThat(toolsetToolUseBlock.toolUseBlock()).isEmpty
    }

    @Test
    fun ofBrowserRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolsetToolUseBlock =
            ToolsetToolUseBlock.ofBrowser(
                BrowserToolUseBlock.ofNavigate(
                    BrowserNavigateToolUseBlock.builder()
                        .id("id")
                        .caller(DirectCaller.builder().build())
                        .input(BrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                        .build()
                )
            )

        val roundtrippedToolsetToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolsetToolUseBlock),
                jacksonTypeRef<ToolsetToolUseBlock>(),
            )

        assertThat(roundtrippedToolsetToolUseBlock).isEqualTo(toolsetToolUseBlock)
    }

    @Test
    fun ofComputer() {
        val computer =
            ComputerToolUseBlock.ofKey(
                ComputerKeyToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerKeyInput.builder().text("text").repeat(1L).build())
                    .build()
            )

        val toolsetToolUseBlock = ToolsetToolUseBlock.ofComputer(computer)

        assertThat(toolsetToolUseBlock.browser()).isEmpty
        assertThat(toolsetToolUseBlock.computer()).contains(computer)
        assertThat(toolsetToolUseBlock.toolUseBlock()).isEmpty
    }

    @Test
    fun ofComputerRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolsetToolUseBlock =
            ToolsetToolUseBlock.ofComputer(
                ComputerToolUseBlock.ofKey(
                    ComputerKeyToolUseBlock.builder()
                        .id("id")
                        .caller(DirectCaller.builder().build())
                        .input(ComputerKeyInput.builder().text("text").repeat(1L).build())
                        .build()
                )
            )

        val roundtrippedToolsetToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolsetToolUseBlock),
                jacksonTypeRef<ToolsetToolUseBlock>(),
            )

        assertThat(roundtrippedToolsetToolUseBlock).isEqualTo(toolsetToolUseBlock)
    }

    @Test
    fun ofToolUseBlock() {
        val toolUseBlock =
            ToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(JsonValue.from(mapOf("foo" to "bar")))
                .name("x")
                .toolsetName("toolset_name")
                .build()

        val toolsetToolUseBlock = ToolsetToolUseBlock.ofToolUseBlock(toolUseBlock)

        assertThat(toolsetToolUseBlock.browser()).isEmpty
        assertThat(toolsetToolUseBlock.computer()).isEmpty
        assertThat(toolsetToolUseBlock.toolUseBlock()).contains(toolUseBlock)
    }

    @Test
    fun ofToolUseBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolsetToolUseBlock =
            ToolsetToolUseBlock.ofToolUseBlock(
                ToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(JsonValue.from(mapOf("foo" to "bar")))
                    .name("x")
                    .toolsetName("toolset_name")
                    .build()
            )

        val roundtrippedToolsetToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolsetToolUseBlock),
                jacksonTypeRef<ToolsetToolUseBlock>(),
            )

        assertThat(roundtrippedToolsetToolUseBlock).isEqualTo(toolsetToolUseBlock)
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
        val toolsetToolUseBlock =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ToolsetToolUseBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { toolsetToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
