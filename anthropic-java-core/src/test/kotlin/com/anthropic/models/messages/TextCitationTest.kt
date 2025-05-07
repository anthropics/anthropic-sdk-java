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
    fun ofCitationCharLocation() {
        val citationCharLocation =
            CitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val textCitation = TextCitation.ofCitationCharLocation(citationCharLocation)

        assertThat(textCitation.citationCharLocation()).contains(citationCharLocation)
        assertThat(textCitation.citationPageLocation()).isEmpty
        assertThat(textCitation.citationContentBlockLocation()).isEmpty
        assertThat(textCitation.citationsWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofCitationCharLocation(
                CitationCharLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endCharIndex(0L)
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
    fun ofCitationPageLocation() {
        val citationPageLocation =
            CitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val textCitation = TextCitation.ofCitationPageLocation(citationPageLocation)

        assertThat(textCitation.citationCharLocation()).isEmpty
        assertThat(textCitation.citationPageLocation()).contains(citationPageLocation)
        assertThat(textCitation.citationContentBlockLocation()).isEmpty
        assertThat(textCitation.citationsWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofCitationPageLocation(
                CitationPageLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endPageNumber(0L)
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
    fun ofCitationContentBlockLocation() {
        val citationContentBlockLocation =
            CitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val textCitation = TextCitation.ofCitationContentBlockLocation(citationContentBlockLocation)

        assertThat(textCitation.citationCharLocation()).isEmpty
        assertThat(textCitation.citationPageLocation()).isEmpty
        assertThat(textCitation.citationContentBlockLocation())
            .contains(citationContentBlockLocation)
        assertThat(textCitation.citationsWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofCitationContentBlockLocation(
                CitationContentBlockLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endBlockIndex(0L)
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
    fun ofCitationsWebSearchResultLocation() {
        val citationsWebSearchResultLocation =
            CitationsWebSearchResultLocation.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        val textCitation =
            TextCitation.ofCitationsWebSearchResultLocation(citationsWebSearchResultLocation)

        assertThat(textCitation.citationCharLocation()).isEmpty
        assertThat(textCitation.citationPageLocation()).isEmpty
        assertThat(textCitation.citationContentBlockLocation()).isEmpty
        assertThat(textCitation.citationsWebSearchResultLocation())
            .contains(citationsWebSearchResultLocation)
    }

    @Test
    fun ofCitationsWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofCitationsWebSearchResultLocation(
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
