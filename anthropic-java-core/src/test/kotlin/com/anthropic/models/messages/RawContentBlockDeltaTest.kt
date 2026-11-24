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

internal class RawContentBlockDeltaTest {

    @Test
    fun ofText() {
        val text = TextDelta.of("text")

        val rawContentBlockDelta = RawContentBlockDelta.ofText(text)

        assertThat(presentVariants(rawContentBlockDelta))
            .isEqualTo(mapOf("text" to Optional.of(text)))
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta = RawContentBlockDelta.ofText(TextDelta.of("text"))

        val roundtrippedRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockDelta),
                jacksonTypeRef<RawContentBlockDelta>(),
            )

        assertThat(roundtrippedRawContentBlockDelta).isEqualTo(rawContentBlockDelta)
    }

    @Test
    fun ofInputJson() {
        val inputJson = InputJsonDelta.of("partial_json")

        val rawContentBlockDelta = RawContentBlockDelta.ofInputJson(inputJson)

        assertThat(presentVariants(rawContentBlockDelta))
            .isEqualTo(mapOf("inputJson" to Optional.of(inputJson)))
    }

    @Test
    fun ofInputJsonRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta =
            RawContentBlockDelta.ofInputJson(InputJsonDelta.of("partial_json"))

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
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("My Document")
                        .endCharIndex(0L)
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val rawContentBlockDelta = RawContentBlockDelta.ofCitations(citations)

        assertThat(presentVariants(rawContentBlockDelta))
            .isEqualTo(mapOf("citations" to Optional.of(citations)))
    }

    @Test
    fun ofCitationsRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta =
            RawContentBlockDelta.ofCitations(
                CitationsDelta.builder()
                    .citation(
                        CitationCharLocation.builder()
                            .citedText("The grass is green. The sky is blue.")
                            .documentIndex(0L)
                            .documentTitle("My Document")
                            .endCharIndex(0L)
                            .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
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
        val thinking = ThinkingDelta.of("thinking")

        val rawContentBlockDelta = RawContentBlockDelta.ofThinking(thinking)

        assertThat(presentVariants(rawContentBlockDelta))
            .isEqualTo(mapOf("thinking" to Optional.of(thinking)))
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta = RawContentBlockDelta.ofThinking(ThinkingDelta.of("thinking"))

        val roundtrippedRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockDelta),
                jacksonTypeRef<RawContentBlockDelta>(),
            )

        assertThat(roundtrippedRawContentBlockDelta).isEqualTo(rawContentBlockDelta)
    }

    @Test
    fun ofSignature() {
        val signature = SignatureDelta.of("signature")

        val rawContentBlockDelta = RawContentBlockDelta.ofSignature(signature)

        assertThat(presentVariants(rawContentBlockDelta))
            .isEqualTo(mapOf("signature" to Optional.of(signature)))
    }

    @Test
    fun ofSignatureRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockDelta = RawContentBlockDelta.ofSignature(SignatureDelta.of("signature"))

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

    private fun presentVariants(
        rawContentBlockDelta: RawContentBlockDelta
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "text" to rawContentBlockDelta.text(),
                "inputJson" to rawContentBlockDelta.inputJson(),
                "citations" to rawContentBlockDelta.citations(),
                "thinking" to rawContentBlockDelta.thinking(),
                "signature" to rawContentBlockDelta.signature(),
            )
            .filterValues { it.isPresent }
}
