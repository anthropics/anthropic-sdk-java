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

internal class BetaRawContentBlockDeltaTest {

    @Test
    fun ofText() {
        val text = BetaTextDelta.builder().text("text").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofText(text)

        assertThat(betaRawContentBlockDelta.text()).contains(text)
        assertThat(betaRawContentBlockDelta.inputJson()).isEmpty
        assertThat(betaRawContentBlockDelta.citations()).isEmpty
        assertThat(betaRawContentBlockDelta.thinking()).isEmpty
        assertThat(betaRawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofText(BetaTextDelta.builder().text("text").build())

        val roundtrippedBetaRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDelta),
                jacksonTypeRef<BetaRawContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDelta).isEqualTo(betaRawContentBlockDelta)
    }

    @Test
    fun ofInputJson() {
        val inputJson = BetaInputJsonDelta.builder().partialJson("partial_json").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofInputJson(inputJson)

        assertThat(betaRawContentBlockDelta.text()).isEmpty
        assertThat(betaRawContentBlockDelta.inputJson()).contains(inputJson)
        assertThat(betaRawContentBlockDelta.citations()).isEmpty
        assertThat(betaRawContentBlockDelta.thinking()).isEmpty
        assertThat(betaRawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofInputJsonRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofInputJson(
                BetaInputJsonDelta.builder().partialJson("partial_json").build()
            )

        val roundtrippedBetaRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDelta),
                jacksonTypeRef<BetaRawContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDelta).isEqualTo(betaRawContentBlockDelta)
    }

    @Test
    fun ofCitations() {
        val citations =
            BetaCitationsDelta.builder()
                .citation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofCitations(citations)

        assertThat(betaRawContentBlockDelta.text()).isEmpty
        assertThat(betaRawContentBlockDelta.inputJson()).isEmpty
        assertThat(betaRawContentBlockDelta.citations()).contains(citations)
        assertThat(betaRawContentBlockDelta.thinking()).isEmpty
        assertThat(betaRawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofCitationsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofCitations(
                BetaCitationsDelta.builder()
                    .citation(
                        BetaCitationCharLocation.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("document_title")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDelta),
                jacksonTypeRef<BetaRawContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDelta).isEqualTo(betaRawContentBlockDelta)
    }

    @Test
    fun ofThinking() {
        val thinking = BetaThinkingDelta.builder().thinking("thinking").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofThinking(thinking)

        assertThat(betaRawContentBlockDelta.text()).isEmpty
        assertThat(betaRawContentBlockDelta.inputJson()).isEmpty
        assertThat(betaRawContentBlockDelta.citations()).isEmpty
        assertThat(betaRawContentBlockDelta.thinking()).contains(thinking)
        assertThat(betaRawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofThinking(
                BetaThinkingDelta.builder().thinking("thinking").build()
            )

        val roundtrippedBetaRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDelta),
                jacksonTypeRef<BetaRawContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDelta).isEqualTo(betaRawContentBlockDelta)
    }

    @Test
    fun ofSignature() {
        val signature = BetaSignatureDelta.builder().signature("signature").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofSignature(signature)

        assertThat(betaRawContentBlockDelta.text()).isEmpty
        assertThat(betaRawContentBlockDelta.inputJson()).isEmpty
        assertThat(betaRawContentBlockDelta.citations()).isEmpty
        assertThat(betaRawContentBlockDelta.thinking()).isEmpty
        assertThat(betaRawContentBlockDelta.signature()).contains(signature)
    }

    @Test
    fun ofSignatureRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofSignature(
                BetaSignatureDelta.builder().signature("signature").build()
            )

        val roundtrippedBetaRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDelta),
                jacksonTypeRef<BetaRawContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDelta).isEqualTo(betaRawContentBlockDelta)
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
        val betaRawContentBlockDelta =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaRawContentBlockDelta>())

        val e = assertThrows<AnthropicInvalidDataException> { betaRawContentBlockDelta.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
