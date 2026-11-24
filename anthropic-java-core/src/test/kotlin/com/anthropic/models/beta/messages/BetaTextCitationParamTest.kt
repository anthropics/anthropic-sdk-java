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

internal class BetaTextCitationParamTest {

    @Test
    fun ofCharLocation() {
        val charLocation =
            BetaCitationCharLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val betaTextCitationParam = BetaTextCitationParam.ofCharLocation(charLocation)

        assertThat(presentVariants(betaTextCitationParam))
            .isEqualTo(mapOf("charLocation" to Optional.of(charLocation)))
    }

    @Test
    fun ofCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofCharLocation(
                BetaCitationCharLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("x")
                    .endCharIndex(0L)
                    .startCharIndex(0L)
                    .build()
            )

        val roundtrippedBetaTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitationParam),
                jacksonTypeRef<BetaTextCitationParam>(),
            )

        assertThat(roundtrippedBetaTextCitationParam).isEqualTo(betaTextCitationParam)
    }

    @Test
    fun ofPageLocation() {
        val pageLocation =
            BetaCitationPageLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val betaTextCitationParam = BetaTextCitationParam.ofPageLocation(pageLocation)

        assertThat(presentVariants(betaTextCitationParam))
            .isEqualTo(mapOf("pageLocation" to Optional.of(pageLocation)))
    }

    @Test
    fun ofPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofPageLocation(
                BetaCitationPageLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("x")
                    .endPageNumber(0L)
                    .startPageNumber(1L)
                    .build()
            )

        val roundtrippedBetaTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitationParam),
                jacksonTypeRef<BetaTextCitationParam>(),
            )

        assertThat(roundtrippedBetaTextCitationParam).isEqualTo(betaTextCitationParam)
    }

    @Test
    fun ofContentBlockLocation() {
        val contentBlockLocation =
            BetaCitationContentBlockLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofContentBlockLocation(contentBlockLocation)

        assertThat(presentVariants(betaTextCitationParam))
            .isEqualTo(mapOf("contentBlockLocation" to Optional.of(contentBlockLocation)))
    }

    @Test
    fun ofContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofContentBlockLocation(
                BetaCitationContentBlockLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("x")
                    .endBlockIndex(0L)
                    .startBlockIndex(0L)
                    .build()
            )

        val roundtrippedBetaTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitationParam),
                jacksonTypeRef<BetaTextCitationParam>(),
            )

        assertThat(roundtrippedBetaTextCitationParam).isEqualTo(betaTextCitationParam)
    }

    @Test
    fun ofWebSearchResultLocation() {
        val webSearchResultLocation =
            BetaCitationWebSearchResultLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofWebSearchResultLocation(webSearchResultLocation)

        assertThat(presentVariants(betaTextCitationParam))
            .isEqualTo(mapOf("webSearchResultLocation" to Optional.of(webSearchResultLocation)))
    }

    @Test
    fun ofWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofWebSearchResultLocation(
                BetaCitationWebSearchResultLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .encryptedIndex("encrypted_index")
                    .title("x")
                    .url("x")
                    .build()
            )

        val roundtrippedBetaTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitationParam),
                jacksonTypeRef<BetaTextCitationParam>(),
            )

        assertThat(roundtrippedBetaTextCitationParam).isEqualTo(betaTextCitationParam)
    }

    @Test
    fun ofSearchResultLocation() {
        val searchResultLocation =
            BetaCitationSearchResultLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofSearchResultLocation(searchResultLocation)

        assertThat(presentVariants(betaTextCitationParam))
            .isEqualTo(mapOf("searchResultLocation" to Optional.of(searchResultLocation)))
    }

    @Test
    fun ofSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofSearchResultLocation(
                BetaCitationSearchResultLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .endBlockIndex(0L)
                    .searchResultIndex(0L)
                    .source("source")
                    .startBlockIndex(0L)
                    .title("title")
                    .build()
            )

        val roundtrippedBetaTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitationParam),
                jacksonTypeRef<BetaTextCitationParam>(),
            )

        assertThat(roundtrippedBetaTextCitationParam).isEqualTo(betaTextCitationParam)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaTextCitationParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cited_text" to "The grass is green. The sky is blue.",
                            "document_index" to 0,
                            "document_title" to "x",
                            "end_block_index" to 0,
                            "start_block_index" to 0,
                            "title" to "x",
                        )
                    ),
                    jacksonTypeRef<BetaTextCitationParam>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaTextCitationParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaTextCitationParam.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(betaTextCitationParam.documentIndex()).contains(0L)
        assertThat(betaTextCitationParam.documentTitle()).contains("x")
        assertThat(betaTextCitationParam.endBlockIndex()).contains(0L)
        assertThat(betaTextCitationParam.startBlockIndex()).contains(0L)
        assertThat(betaTextCitationParam.title()).contains("x")

        val mismatchedBetaTextCitationParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cited_text" to listOf("invalid"),
                            "document_index" to listOf("invalid"),
                            "document_title" to listOf("invalid"),
                            "end_block_index" to listOf("invalid"),
                            "start_block_index" to listOf("invalid"),
                            "title" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaTextCitationParam>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBetaTextCitationParam.citedText() }
        assertThat(mismatchedBetaTextCitationParam.documentIndex()).isEmpty
        assertThat(mismatchedBetaTextCitationParam.documentTitle()).isEmpty
        assertThat(mismatchedBetaTextCitationParam.endBlockIndex()).isEmpty
        assertThat(mismatchedBetaTextCitationParam.startBlockIndex()).isEmpty
        assertThat(mismatchedBetaTextCitationParam.title()).isEmpty
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
        val betaTextCitationParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaTextCitationParam>())

        val e = assertThrows<AnthropicInvalidDataException> { betaTextCitationParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaTextCitationParam.citedText() }
        assertThat(betaTextCitationParam.documentIndex()).isEmpty
        assertThat(betaTextCitationParam.documentTitle()).isEmpty
        assertThat(betaTextCitationParam.endBlockIndex()).isEmpty
        assertThat(betaTextCitationParam.startBlockIndex()).isEmpty
        assertThat(betaTextCitationParam.title()).isEmpty
    }

    private fun presentVariants(
        betaTextCitationParam: BetaTextCitationParam
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "charLocation" to betaTextCitationParam.charLocation(),
                "pageLocation" to betaTextCitationParam.pageLocation(),
                "contentBlockLocation" to betaTextCitationParam.contentBlockLocation(),
                "webSearchResultLocation" to betaTextCitationParam.webSearchResultLocation(),
                "searchResultLocation" to betaTextCitationParam.searchResultLocation(),
            )
            .filterValues { it.isPresent }
}
