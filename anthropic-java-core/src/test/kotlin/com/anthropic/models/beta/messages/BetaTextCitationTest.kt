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

internal class BetaTextCitationTest {

    @Test
    fun ofCharLocation() {
        val charLocation =
            BetaCitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val betaTextCitation = BetaTextCitation.ofCharLocation(charLocation)

        assertThat(betaTextCitation.charLocation()).contains(charLocation)
        assertThat(betaTextCitation.pageLocation()).isEmpty
        assertThat(betaTextCitation.contentBlockLocation()).isEmpty
        assertThat(betaTextCitation.webSearchResultLocation()).isEmpty
        assertThat(betaTextCitation.searchResultLocation()).isEmpty
    }

    @Test
    fun ofCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofCharLocation(
                BetaCitationCharLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endCharIndex(0L)
                    .startCharIndex(0L)
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun ofPageLocation() {
        val pageLocation =
            BetaCitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val betaTextCitation = BetaTextCitation.ofPageLocation(pageLocation)

        assertThat(betaTextCitation.charLocation()).isEmpty
        assertThat(betaTextCitation.pageLocation()).contains(pageLocation)
        assertThat(betaTextCitation.contentBlockLocation()).isEmpty
        assertThat(betaTextCitation.webSearchResultLocation()).isEmpty
        assertThat(betaTextCitation.searchResultLocation()).isEmpty
    }

    @Test
    fun ofPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofPageLocation(
                BetaCitationPageLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endPageNumber(0L)
                    .startPageNumber(1L)
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun ofContentBlockLocation() {
        val contentBlockLocation =
            BetaCitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val betaTextCitation = BetaTextCitation.ofContentBlockLocation(contentBlockLocation)

        assertThat(betaTextCitation.charLocation()).isEmpty
        assertThat(betaTextCitation.pageLocation()).isEmpty
        assertThat(betaTextCitation.contentBlockLocation()).contains(contentBlockLocation)
        assertThat(betaTextCitation.webSearchResultLocation()).isEmpty
        assertThat(betaTextCitation.searchResultLocation()).isEmpty
    }

    @Test
    fun ofContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofContentBlockLocation(
                BetaCitationContentBlockLocation.builder()
                    .citedText("cited_text")
                    .documentIndex(0L)
                    .documentTitle("document_title")
                    .endBlockIndex(0L)
                    .startBlockIndex(0L)
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun ofWebSearchResultLocation() {
        val webSearchResultLocation =
            BetaCitationsWebSearchResultLocation.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        val betaTextCitation = BetaTextCitation.ofWebSearchResultLocation(webSearchResultLocation)

        assertThat(betaTextCitation.charLocation()).isEmpty
        assertThat(betaTextCitation.pageLocation()).isEmpty
        assertThat(betaTextCitation.contentBlockLocation()).isEmpty
        assertThat(betaTextCitation.webSearchResultLocation()).contains(webSearchResultLocation)
        assertThat(betaTextCitation.searchResultLocation()).isEmpty
    }

    @Test
    fun ofWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofWebSearchResultLocation(
                BetaCitationsWebSearchResultLocation.builder()
                    .citedText("cited_text")
                    .encryptedIndex("encrypted_index")
                    .title("title")
                    .url("url")
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun ofSearchResultLocation() {
        val searchResultLocation =
            BetaSearchResultLocationCitation.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val betaTextCitation = BetaTextCitation.ofSearchResultLocation(searchResultLocation)

        assertThat(betaTextCitation.charLocation()).isEmpty
        assertThat(betaTextCitation.pageLocation()).isEmpty
        assertThat(betaTextCitation.contentBlockLocation()).isEmpty
        assertThat(betaTextCitation.webSearchResultLocation()).isEmpty
        assertThat(betaTextCitation.searchResultLocation()).contains(searchResultLocation)
    }

    @Test
    fun ofSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofSearchResultLocation(
                BetaSearchResultLocationCitation.builder()
                    .citedText("cited_text")
                    .endBlockIndex(0L)
                    .searchResultIndex(0L)
                    .source("source")
                    .startBlockIndex(0L)
                    .title("title")
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
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
        val betaTextCitation =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaTextCitation>())

        val e = assertThrows<AnthropicInvalidDataException> { betaTextCitation.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
