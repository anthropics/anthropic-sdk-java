package com.anthropic.models.beta.messages

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

internal class BetaRawContentBlockDeltaTest {

    @Test
    fun ofText() {
        val text = BetaTextDelta.of("text")

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofText(text)

        assertThat(presentVariants(betaRawContentBlockDelta))
            .isEqualTo(mapOf("text" to Optional.of(text)))
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofText(BetaTextDelta.of("text"))

        val roundtrippedBetaRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDelta),
                jacksonTypeRef<BetaRawContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDelta).isEqualTo(betaRawContentBlockDelta)
    }

    @Test
    fun ofInputJson() {
        val inputJson = BetaInputJsonDelta.of("partial_json")

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofInputJson(inputJson)

        assertThat(presentVariants(betaRawContentBlockDelta))
            .isEqualTo(mapOf("inputJson" to Optional.of(inputJson)))
    }

    @Test
    fun ofInputJsonRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofInputJson(BetaInputJsonDelta.of("partial_json"))

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
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("My Document")
                        .endCharIndex(0L)
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofCitations(citations)

        assertThat(presentVariants(betaRawContentBlockDelta))
            .isEqualTo(mapOf("citations" to Optional.of(citations)))
    }

    @Test
    fun ofCitationsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofCitations(
                BetaCitationsDelta.builder()
                    .citation(
                        BetaCitationCharLocation.builder()
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

        val roundtrippedBetaRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDelta),
                jacksonTypeRef<BetaRawContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDelta).isEqualTo(betaRawContentBlockDelta)
    }

    @Test
    fun ofThinking() {
        val thinking = BetaThinkingDelta.builder().estimatedTokens(0L).thinking("thinking").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofThinking(thinking)

        assertThat(presentVariants(betaRawContentBlockDelta))
            .isEqualTo(mapOf("thinking" to Optional.of(thinking)))
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofThinking(
                BetaThinkingDelta.builder().estimatedTokens(0L).thinking("thinking").build()
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
        val signature = BetaSignatureDelta.of("signature")

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofSignature(signature)

        assertThat(presentVariants(betaRawContentBlockDelta))
            .isEqualTo(mapOf("signature" to Optional.of(signature)))
    }

    @Test
    fun ofSignatureRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofSignature(BetaSignatureDelta.of("signature"))

        val roundtrippedBetaRawContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDelta),
                jacksonTypeRef<BetaRawContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDelta).isEqualTo(betaRawContentBlockDelta)
    }

    @Test
    fun ofCompaction() {
        val compaction =
            BetaCompactionContentBlockDelta.builder()
                .content("content")
                .encryptedContent("encrypted_content")
                .build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofCompaction(compaction)

        assertThat(presentVariants(betaRawContentBlockDelta))
            .isEqualTo(mapOf("compaction" to Optional.of(compaction)))
    }

    @Test
    fun ofCompactionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDelta =
            BetaRawContentBlockDelta.ofCompaction(
                BetaCompactionContentBlockDelta.builder()
                    .content("content")
                    .encryptedContent("encrypted_content")
                    .build()
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

    private fun presentVariants(
        betaRawContentBlockDelta: BetaRawContentBlockDelta
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "text" to betaRawContentBlockDelta.text(),
                "inputJson" to betaRawContentBlockDelta.inputJson(),
                "citations" to betaRawContentBlockDelta.citations(),
                "thinking" to betaRawContentBlockDelta.thinking(),
                "signature" to betaRawContentBlockDelta.signature(),
                "compaction" to betaRawContentBlockDelta.compaction(),
            )
            .filterValues { it.isPresent }
}
