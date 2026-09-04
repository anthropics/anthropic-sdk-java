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
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endCharIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startCharIndex(0L)
                .build()

        val textCitation = TextCitation.ofCharLocation(charLocation)

        assertThat(textCitation.charLocation()).contains(charLocation)
        assertThat(textCitation.pageLocation()).isEmpty
        assertThat(textCitation.contentBlockLocation()).isEmpty
        assertThat(textCitation.webSearchResultLocation()).isEmpty
        assertThat(textCitation.searchResultLocation()).isEmpty
    }

    @Test
    fun ofCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofCharLocation(
                CitationCharLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("My Document")
                    .endCharIndex(0L)
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
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
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endPageNumber(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startPageNumber(1L)
                .build()

        val textCitation = TextCitation.ofPageLocation(pageLocation)

        assertThat(textCitation.charLocation()).isEmpty
        assertThat(textCitation.pageLocation()).contains(pageLocation)
        assertThat(textCitation.contentBlockLocation()).isEmpty
        assertThat(textCitation.webSearchResultLocation()).isEmpty
        assertThat(textCitation.searchResultLocation()).isEmpty
    }

    @Test
    fun ofPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofPageLocation(
                CitationPageLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("My Document")
                    .endPageNumber(0L)
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
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
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endBlockIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startBlockIndex(0L)
                .build()

        val textCitation = TextCitation.ofContentBlockLocation(contentBlockLocation)

        assertThat(textCitation.charLocation()).isEmpty
        assertThat(textCitation.pageLocation()).isEmpty
        assertThat(textCitation.contentBlockLocation()).contains(contentBlockLocation)
        assertThat(textCitation.webSearchResultLocation()).isEmpty
        assertThat(textCitation.searchResultLocation()).isEmpty
    }

    @Test
    fun ofContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofContentBlockLocation(
                CitationContentBlockLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("My Document")
                    .endBlockIndex(0L)
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
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
                .citedText("The grass is green. The sky is blue.")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        val textCitation = TextCitation.ofWebSearchResultLocation(webSearchResultLocation)

        assertThat(textCitation.charLocation()).isEmpty
        assertThat(textCitation.pageLocation()).isEmpty
        assertThat(textCitation.contentBlockLocation()).isEmpty
        assertThat(textCitation.webSearchResultLocation()).contains(webSearchResultLocation)
        assertThat(textCitation.searchResultLocation()).isEmpty
    }

    @Test
    fun ofWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofWebSearchResultLocation(
                CitationsWebSearchResultLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
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

    @Test
    fun ofSearchResultLocation() {
        val searchResultLocation =
            CitationsSearchResultLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val textCitation = TextCitation.ofSearchResultLocation(searchResultLocation)

        assertThat(textCitation.charLocation()).isEmpty
        assertThat(textCitation.pageLocation()).isEmpty
        assertThat(textCitation.contentBlockLocation()).isEmpty
        assertThat(textCitation.webSearchResultLocation()).isEmpty
        assertThat(textCitation.searchResultLocation()).contains(searchResultLocation)
    }

    @Test
    fun ofSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitation =
            TextCitation.ofSearchResultLocation(
                CitationsSearchResultLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .endBlockIndex(0L)
                    .searchResultIndex(0L)
                    .source("source")
                    .startBlockIndex(0L)
                    .title("title")
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
    fun unknownVariantCommonProperties() {
        val textCitation =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cited_text" to "The grass is green. The sky is blue.",
                            "document_index" to 0,
                            "document_title" to "My Document",
                            "file_id" to "file_011CNha8iCJcU1wXNR6q4V8w",
                            "end_block_index" to 0,
                            "start_block_index" to 0,
                            "title" to "title",
                        )
                    ),
                    jacksonTypeRef<TextCitation>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { textCitation.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(textCitation.citedText()).isEqualTo("The grass is green. The sky is blue.")
        assertThat(textCitation.documentIndex()).contains(0L)
        assertThat(textCitation.documentTitle()).contains("My Document")
        assertThat(textCitation.fileId()).contains("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(textCitation.endBlockIndex()).contains(0L)
        assertThat(textCitation.startBlockIndex()).contains(0L)
        assertThat(textCitation.title()).contains("title")

        val mismatchedTextCitation =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cited_text" to listOf("invalid"),
                            "document_index" to listOf("invalid"),
                            "document_title" to listOf("invalid"),
                            "file_id" to listOf("invalid"),
                            "end_block_index" to listOf("invalid"),
                            "start_block_index" to listOf("invalid"),
                            "title" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<TextCitation>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedTextCitation.citedText() }
        assertThat(mismatchedTextCitation.documentIndex()).isEmpty
        assertThat(mismatchedTextCitation.documentTitle()).isEmpty
        assertThat(mismatchedTextCitation.fileId()).isEmpty
        assertThat(mismatchedTextCitation.endBlockIndex()).isEmpty
        assertThat(mismatchedTextCitation.startBlockIndex()).isEmpty
        assertThat(mismatchedTextCitation.title()).isEmpty
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

        assertThrows<AnthropicInvalidDataException> { textCitation.citedText() }
        assertThat(textCitation.documentIndex()).isEmpty
        assertThat(textCitation.documentTitle()).isEmpty
        assertThat(textCitation.fileId()).isEmpty
        assertThat(textCitation.endBlockIndex()).isEmpty
        assertThat(textCitation.startBlockIndex()).isEmpty
        assertThat(textCitation.title()).isEmpty
    }
}
