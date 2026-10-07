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

internal class BetaToolsetToolUseBlockTest {

    @Test
    fun ofBrowser() {
        val browser =
            BetaBrowserToolUseBlock.ofNavigate(
                BetaBrowserNavigateToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val betaToolsetToolUseBlock = BetaToolsetToolUseBlock.ofBrowser(browser)

        assertThat(betaToolsetToolUseBlock.browser()).contains(browser)
        assertThat(betaToolsetToolUseBlock.computer()).isEmpty
        assertThat(betaToolsetToolUseBlock.betaToolUseBlock()).isEmpty
    }

    @Test
    fun ofBrowserRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolsetToolUseBlock =
            BetaToolsetToolUseBlock.ofBrowser(
                BetaBrowserToolUseBlock.ofNavigate(
                    BetaBrowserNavigateToolUseBlock.builder()
                        .id("id")
                        .input(
                            BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build()
                        )
                        .caller(BetaDirectCaller.builder().build())
                        .build()
                )
            )

        val roundtrippedBetaToolsetToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolsetToolUseBlock),
                jacksonTypeRef<BetaToolsetToolUseBlock>(),
            )

        assertThat(roundtrippedBetaToolsetToolUseBlock).isEqualTo(betaToolsetToolUseBlock)
    }

    @Test
    fun ofComputer() {
        val computer =
            BetaComputerToolUseBlock.ofKey(
                BetaComputerKeyToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerKeyInput.builder().text("text").repeat(1L).build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val betaToolsetToolUseBlock = BetaToolsetToolUseBlock.ofComputer(computer)

        assertThat(betaToolsetToolUseBlock.browser()).isEmpty
        assertThat(betaToolsetToolUseBlock.computer()).contains(computer)
        assertThat(betaToolsetToolUseBlock.betaToolUseBlock()).isEmpty
    }

    @Test
    fun ofComputerRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolsetToolUseBlock =
            BetaToolsetToolUseBlock.ofComputer(
                BetaComputerToolUseBlock.ofKey(
                    BetaComputerKeyToolUseBlock.builder()
                        .id("id")
                        .input(BetaComputerKeyInput.builder().text("text").repeat(1L).build())
                        .caller(BetaDirectCaller.builder().build())
                        .build()
                )
            )

        val roundtrippedBetaToolsetToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolsetToolUseBlock),
                jacksonTypeRef<BetaToolsetToolUseBlock>(),
            )

        assertThat(roundtrippedBetaToolsetToolUseBlock).isEqualTo(betaToolsetToolUseBlock)
    }

    @Test
    fun ofBetaToolUseBlock() {
        val betaToolUseBlock =
            BetaToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf("foo" to "bar")))
                .name("x")
                .caller(BetaDirectCaller.builder().build())
                .toolsetName("toolset_name")
                .build()

        val betaToolsetToolUseBlock = BetaToolsetToolUseBlock.ofBetaToolUseBlock(betaToolUseBlock)

        assertThat(betaToolsetToolUseBlock.browser()).isEmpty
        assertThat(betaToolsetToolUseBlock.computer()).isEmpty
        assertThat(betaToolsetToolUseBlock.betaToolUseBlock()).contains(betaToolUseBlock)
    }

    @Test
    fun ofBetaToolUseBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolsetToolUseBlock =
            BetaToolsetToolUseBlock.ofBetaToolUseBlock(
                BetaToolUseBlock.builder()
                    .id("id")
                    .input(JsonValue.from(mapOf("foo" to "bar")))
                    .name("x")
                    .caller(BetaDirectCaller.builder().build())
                    .toolsetName("toolset_name")
                    .build()
            )

        val roundtrippedBetaToolsetToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolsetToolUseBlock),
                jacksonTypeRef<BetaToolsetToolUseBlock>(),
            )

        assertThat(roundtrippedBetaToolsetToolUseBlock).isEqualTo(betaToolsetToolUseBlock)
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
        val betaToolsetToolUseBlock =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaToolsetToolUseBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { betaToolsetToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
