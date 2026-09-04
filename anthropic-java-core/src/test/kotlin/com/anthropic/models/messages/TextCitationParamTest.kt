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

internal class TextCitationParamTest {

    @Test
    fun ofCharLocation() {
        val charLocation =
            CitationCharLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val textCitationParam = TextCitationParam.ofCharLocation(charLocation)

        assertThat(textCitationParam.charLocation()).contains(charLocation)
        assertThat(textCitationParam.pageLocation()).isEmpty
        assertThat(textCitationParam.contentBlockLocation()).isEmpty
        assertThat(textCitationParam.webSearchResultLocation()).isEmpty
        assertThat(textCitationParam.searchResultLocation()).isEmpty
    }

    @Test
    fun ofCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofCharLocation(
                CitationCharLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("x")
                    .endCharIndex(0L)
                    .startCharIndex(0L)
                    .build()
            )

        val roundtrippedTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitationParam),
                jacksonTypeRef<TextCitationParam>(),
            )

        assertThat(roundtrippedTextCitationParam).isEqualTo(textCitationParam)
    }

    @Test
    fun ofPageLocation() {
        val pageLocation =
            CitationPageLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val textCitationParam = TextCitationParam.ofPageLocation(pageLocation)

        assertThat(textCitationParam.charLocation()).isEmpty
        assertThat(textCitationParam.pageLocation()).contains(pageLocation)
        assertThat(textCitationParam.contentBlockLocation()).isEmpty
        assertThat(textCitationParam.webSearchResultLocation()).isEmpty
        assertThat(textCitationParam.searchResultLocation()).isEmpty
    }

    @Test
    fun ofPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofPageLocation(
                CitationPageLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("x")
                    .endPageNumber(0L)
                    .startPageNumber(1L)
                    .build()
            )

        val roundtrippedTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitationParam),
                jacksonTypeRef<TextCitationParam>(),
            )

        assertThat(roundtrippedTextCitationParam).isEqualTo(textCitationParam)
    }

    @Test
    fun ofContentBlockLocation() {
        val contentBlockLocation =
            CitationContentBlockLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val textCitationParam = TextCitationParam.ofContentBlockLocation(contentBlockLocation)

        assertThat(textCitationParam.charLocation()).isEmpty
        assertThat(textCitationParam.pageLocation()).isEmpty
        assertThat(textCitationParam.contentBlockLocation()).contains(contentBlockLocation)
        assertThat(textCitationParam.webSearchResultLocation()).isEmpty
        assertThat(textCitationParam.searchResultLocation()).isEmpty
    }

    @Test
    fun ofContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofContentBlockLocation(
                CitationContentBlockLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("x")
                    .endBlockIndex(0L)
                    .startBlockIndex(0L)
                    .build()
            )

        val roundtrippedTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitationParam),
                jacksonTypeRef<TextCitationParam>(),
            )

        assertThat(roundtrippedTextCitationParam).isEqualTo(textCitationParam)
    }

    @Test
    fun ofWebSearchResultLocation() {
        val webSearchResultLocation =
            CitationWebSearchResultLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        val textCitationParam = TextCitationParam.ofWebSearchResultLocation(webSearchResultLocation)

        assertThat(textCitationParam.charLocation()).isEmpty
        assertThat(textCitationParam.pageLocation()).isEmpty
        assertThat(textCitationParam.contentBlockLocation()).isEmpty
        assertThat(textCitationParam.webSearchResultLocation()).contains(webSearchResultLocation)
        assertThat(textCitationParam.searchResultLocation()).isEmpty
    }

    @Test
    fun ofWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofWebSearchResultLocation(
                CitationWebSearchResultLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .encryptedIndex("encrypted_index")
                    .title("x")
                    .url("x")
                    .build()
            )

        val roundtrippedTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitationParam),
                jacksonTypeRef<TextCitationParam>(),
            )

        assertThat(roundtrippedTextCitationParam).isEqualTo(textCitationParam)
    }

    @Test
    fun ofSearchResultLocation() {
        val searchResultLocation =
            CitationSearchResultLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val textCitationParam = TextCitationParam.ofSearchResultLocation(searchResultLocation)

        assertThat(textCitationParam.charLocation()).isEmpty
        assertThat(textCitationParam.pageLocation()).isEmpty
        assertThat(textCitationParam.contentBlockLocation()).isEmpty
        assertThat(textCitationParam.webSearchResultLocation()).isEmpty
        assertThat(textCitationParam.searchResultLocation()).contains(searchResultLocation)
    }

    @Test
    fun ofSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofSearchResultLocation(
                CitationSearchResultLocationParam.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .endBlockIndex(0L)
                    .searchResultIndex(0L)
                    .source("source")
                    .startBlockIndex(0L)
                    .title("title")
                    .build()
            )

        val roundtrippedTextCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitationParam),
                jacksonTypeRef<TextCitationParam>(),
            )

        assertThat(roundtrippedTextCitationParam).isEqualTo(textCitationParam)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val textCitationParam =
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
                    jacksonTypeRef<TextCitationParam>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { textCitationParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(textCitationParam.citedText()).isEqualTo("The grass is green. The sky is blue.")
        assertThat(textCitationParam.documentIndex()).contains(0L)
        assertThat(textCitationParam.documentTitle()).contains("x")
        assertThat(textCitationParam.endBlockIndex()).contains(0L)
        assertThat(textCitationParam.startBlockIndex()).contains(0L)
        assertThat(textCitationParam.title()).contains("x")

        val mismatchedTextCitationParam =
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
                    jacksonTypeRef<TextCitationParam>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedTextCitationParam.citedText() }
        assertThat(mismatchedTextCitationParam.documentIndex()).isEmpty
        assertThat(mismatchedTextCitationParam.documentTitle()).isEmpty
        assertThat(mismatchedTextCitationParam.endBlockIndex()).isEmpty
        assertThat(mismatchedTextCitationParam.startBlockIndex()).isEmpty
        assertThat(mismatchedTextCitationParam.title()).isEmpty
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
        val textCitationParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<TextCitationParam>())

        val e = assertThrows<AnthropicInvalidDataException> { textCitationParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { textCitationParam.citedText() }
        assertThat(textCitationParam.documentIndex()).isEmpty
        assertThat(textCitationParam.documentTitle()).isEmpty
        assertThat(textCitationParam.endBlockIndex()).isEmpty
        assertThat(textCitationParam.startBlockIndex()).isEmpty
        assertThat(textCitationParam.title()).isEmpty
    }
}
