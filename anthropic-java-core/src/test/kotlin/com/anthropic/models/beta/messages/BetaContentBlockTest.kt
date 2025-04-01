// File generated from our OpenAPI spec by Stainless.

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

internal class BetaContentBlockTest {

    @Test
    fun ofText() {
        val text =
            BetaTextBlock.builder()
                .addCitation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .text("text")
                .build()

        val betaContentBlock = BetaContentBlock.ofText(text)

        assertThat(betaContentBlock.text()).contains(text)
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofText(
                BetaTextBlock.builder()
                    .addCitation(
                        BetaCitationCharLocation.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("document_title")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .text("text")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            BetaToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .build()

        val betaContentBlock = BetaContentBlock.ofToolUse(toolUse)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.toolUse()).contains(toolUse)
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofToolUse(
                BetaToolUseBlock.builder()
                    .id("id")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name("x")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofThinking() {
        val thinking =
            BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()

        val betaContentBlock = BetaContentBlock.ofThinking(thinking)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.thinking()).contains(thinking)
        assertThat(betaContentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofThinking(
                BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = BetaRedactedThinkingBlock.builder().data("data").build()

        val betaContentBlock = BetaContentBlock.ofRedactedThinking(redactedThinking)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).contains(redactedThinking)
    }

    @Test
    fun ofRedactedThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofRedactedThinking(
                BetaRedactedThinkingBlock.builder().data("data").build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
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
        val betaContentBlock =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaContentBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { betaContentBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
