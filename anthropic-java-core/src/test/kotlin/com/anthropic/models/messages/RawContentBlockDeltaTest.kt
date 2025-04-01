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

internal class RawContentBlockDeltaTest {

    @Test
    fun ofText() {
        val text = TextDelta.builder().text("text").build()

        val rawContentBlockDelta = RawContentBlockDelta.ofText(text)

        assertThat(rawContentBlockDelta.text()).contains(text)
        assertThat(rawContentBlockDelta.inputJson()).isEmpty
        assertThat(rawContentBlockDelta.citations()).isEmpty
        assertThat(rawContentBlockDelta.thinking()).isEmpty
        assertThat(rawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta =
            RawContentBlockDelta.ofText(TextDelta.builder().text("text").build())

        val roundtrippedRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockDelta),
                jacksonTypeRef<RawContentBlockDelta>(),
            )

        assertThat(roundtrippedRawContentBlockDelta).isEqualTo(rawContentBlockDelta)
    }

    @Test
    fun ofInputJson() {
        val inputJson = InputJsonDelta.builder().partialJson("partial_json").build()

        val rawContentBlockDelta = RawContentBlockDelta.ofInputJson(inputJson)

        assertThat(rawContentBlockDelta.text()).isEmpty
        assertThat(rawContentBlockDelta.inputJson()).contains(inputJson)
        assertThat(rawContentBlockDelta.citations()).isEmpty
        assertThat(rawContentBlockDelta.thinking()).isEmpty
        assertThat(rawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofInputJsonRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta =
            RawContentBlockDelta.ofInputJson(
                InputJsonDelta.builder().partialJson("partial_json").build()
            )

        val roundtrippedRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockDelta),
                jacksonTypeRef<RawContentBlockDelta>(),
            )

        assertThat(roundtrippedRawContentBlockDelta).isEqualTo(rawContentBlockDelta)
    }

    @Test
    fun ofCitations() {
        val citations =
            CitationsDelta.builder()
                .citation(
                    CitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val rawContentBlockDelta = RawContentBlockDelta.ofCitations(citations)

        assertThat(rawContentBlockDelta.text()).isEmpty
        assertThat(rawContentBlockDelta.inputJson()).isEmpty
        assertThat(rawContentBlockDelta.citations()).contains(citations)
        assertThat(rawContentBlockDelta.thinking()).isEmpty
        assertThat(rawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofCitationsRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta =
            RawContentBlockDelta.ofCitations(
                CitationsDelta.builder()
                    .citation(
                        CitationCharLocation.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("document_title")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )

        val roundtrippedRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockDelta),
                jacksonTypeRef<RawContentBlockDelta>(),
            )

        assertThat(roundtrippedRawContentBlockDelta).isEqualTo(rawContentBlockDelta)
    }

    @Test
    fun ofThinking() {
        val thinking = ThinkingDelta.builder().thinking("thinking").build()

        val rawContentBlockDelta = RawContentBlockDelta.ofThinking(thinking)

        assertThat(rawContentBlockDelta.text()).isEmpty
        assertThat(rawContentBlockDelta.inputJson()).isEmpty
        assertThat(rawContentBlockDelta.citations()).isEmpty
        assertThat(rawContentBlockDelta.thinking()).contains(thinking)
        assertThat(rawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta =
            RawContentBlockDelta.ofThinking(ThinkingDelta.builder().thinking("thinking").build())

        val roundtrippedRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockDelta),
                jacksonTypeRef<RawContentBlockDelta>(),
            )

        assertThat(roundtrippedRawContentBlockDelta).isEqualTo(rawContentBlockDelta)
    }

    @Test
    fun ofSignature() {
        val signature = SignatureDelta.builder().signature("signature").build()

        val rawContentBlockDelta = RawContentBlockDelta.ofSignature(signature)

        assertThat(rawContentBlockDelta.text()).isEmpty
        assertThat(rawContentBlockDelta.inputJson()).isEmpty
        assertThat(rawContentBlockDelta.citations()).isEmpty
        assertThat(rawContentBlockDelta.thinking()).isEmpty
        assertThat(rawContentBlockDelta.signature()).contains(signature)
    }

    @Test
    fun ofSignatureRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta =
            RawContentBlockDelta.ofSignature(
                SignatureDelta.builder().signature("signature").build()
            )

        val roundtrippedRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockDelta),
                jacksonTypeRef<RawContentBlockDelta>(),
            )

        assertThat(roundtrippedRawContentBlockDelta).isEqualTo(rawContentBlockDelta)
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
        val rawContentBlockDelta =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<RawContentBlockDelta>())

        val e = assertThrows<AnthropicInvalidDataException> { rawContentBlockDelta.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
