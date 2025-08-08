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
                .citedText("cited_text")
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
                    .citedText("cited_text")
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
                .citedText("cited_text")
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
                    .citedText("cited_text")
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
                .citedText("cited_text")
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
                    .citedText("cited_text")
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
                .citedText("cited_text")
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
                    .citedText("cited_text")
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
                .citedText("cited_text")
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
                    .citedText("cited_text")
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
    }
}
