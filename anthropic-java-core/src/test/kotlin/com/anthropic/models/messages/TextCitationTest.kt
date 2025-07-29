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

internal class TextCitationTest {

    @Test
    fun ofCharLocation() {
        val charLocation =
            CitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .fileId("file_id")
                .startCharIndex(0L)
                .build()

        val textCitation = TextCitation.ofCharLocation(charLocation)

        assertThat(textCitation.charLocation()).contains(charLocation)
        assertThat(textCitation.pageLocation()).isEmpty
        assertThat(textCitation.contentBlockLocation()).isEmpty
        assertThat(textCitation.webSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofCharLocation(
                CitationCharLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endCharIndex(0L)
                    .fileId("file_id")
                    .startCharIndex(0L)
                    .build()
            )

        val roundtrippedTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitation),
                jacksonTypeRef<TextCitation>(),
            )

        assertThat(roundtrippedTextCitation).isEqualTo(textCitation)
    }

    @Test
    fun ofPageLocation() {
        val pageLocation =
            CitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .fileId("file_id")
                .startPageNumber(1L)
                .build()

        val textCitation = TextCitation.ofPageLocation(pageLocation)

        assertThat(textCitation.charLocation()).isEmpty
        assertThat(textCitation.pageLocation()).contains(pageLocation)
        assertThat(textCitation.contentBlockLocation()).isEmpty
        assertThat(textCitation.webSearchResultLocation()).isEmpty
    }

    @Test
    fun ofPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofPageLocation(
                CitationPageLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endPageNumber(0L)
                    .fileId("file_id")
                    .startPageNumber(1L)
                    .build()
            )

        val roundtrippedTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitation),
                jacksonTypeRef<TextCitation>(),
            )

        assertThat(roundtrippedTextCitation).isEqualTo(textCitation)
    }

    @Test
    fun ofContentBlockLocation() {
        val contentBlockLocation =
            CitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .fileId("file_id")
                .startBlockIndex(0L)
                .build()

        val textCitation = TextCitation.ofContentBlockLocation(contentBlockLocation)

        assertThat(textCitation.charLocation()).isEmpty
        assertThat(textCitation.pageLocation()).isEmpty
        assertThat(textCitation.contentBlockLocation()).contains(contentBlockLocation)
        assertThat(textCitation.webSearchResultLocation()).isEmpty
    }

    @Test
    fun ofContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofContentBlockLocation(
                CitationContentBlockLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endBlockIndex(0L)
                    .fileId("file_id")
                    .startBlockIndex(0L)
                    .build()
            )

        val roundtrippedTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitation),
                jacksonTypeRef<TextCitation>(),
            )

        assertThat(roundtrippedTextCitation).isEqualTo(textCitation)
    }

    @Test
    fun ofWebSearchResultLocation() {
        val webSearchResultLocation =
            CitationsWebSearchResultLocation.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        val textCitation = TextCitation.ofWebSearchResultLocation(webSearchResultLocation)

        assertThat(textCitation.charLocation()).isEmpty
        assertThat(textCitation.pageLocation()).isEmpty
        assertThat(textCitation.contentBlockLocation()).isEmpty
        assertThat(textCitation.webSearchResultLocation()).contains(webSearchResultLocation)
    }

    @Test
    fun ofWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofWebSearchResultLocation(
                CitationsWebSearchResultLocation.builder()
                    .citedText("cited_text")
                    .encryptedIndex("encrypted_index")
                    .title("title")
                    .url("url")
                    .build()
            )

        val roundtrippedTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textCitation),
                jacksonTypeRef<TextCitation>(),
            )

        assertThat(roundtrippedTextCitation).isEqualTo(textCitation)
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
        val textCitation = jsonMapper().convertValue(testCase.value, jacksonTypeRef<TextCitation>())

        val e = assertThrows<AnthropicInvalidDataException> { textCitation.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
