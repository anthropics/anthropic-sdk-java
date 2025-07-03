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

internal class BetaTextCitationParamTest {

    @Test
    fun ofCharLocation() {
        val charLocation =
            BetaCitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val betaTextCitationParam = BetaTextCitationParam.ofCharLocation(charLocation)

        assertThat(betaTextCitationParam.charLocation()).contains(charLocation)
        assertThat(betaTextCitationParam.pageLocation()).isEmpty
        assertThat(betaTextCitationParam.contentBlockLocation()).isEmpty
        assertThat(betaTextCitationParam.webSearchResultLocation()).isEmpty
        assertThat(betaTextCitationParam.searchResultLocation()).isEmpty
    }

    @Test
    fun ofCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofCharLocation(
                BetaCitationCharLocationParam.builder()
                    .citedText("cited_text")
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
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val betaTextCitationParam = BetaTextCitationParam.ofPageLocation(pageLocation)

        assertThat(betaTextCitationParam.charLocation()).isEmpty
        assertThat(betaTextCitationParam.pageLocation()).contains(pageLocation)
        assertThat(betaTextCitationParam.contentBlockLocation()).isEmpty
        assertThat(betaTextCitationParam.webSearchResultLocation()).isEmpty
        assertThat(betaTextCitationParam.searchResultLocation()).isEmpty
    }

    @Test
    fun ofPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofPageLocation(
                BetaCitationPageLocationParam.builder()
                    .citedText("cited_text")
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
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofContentBlockLocation(contentBlockLocation)

        assertThat(betaTextCitationParam.charLocation()).isEmpty
        assertThat(betaTextCitationParam.pageLocation()).isEmpty
        assertThat(betaTextCitationParam.contentBlockLocation()).contains(contentBlockLocation)
        assertThat(betaTextCitationParam.webSearchResultLocation()).isEmpty
        assertThat(betaTextCitationParam.searchResultLocation()).isEmpty
    }

    @Test
    fun ofContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofContentBlockLocation(
                BetaCitationContentBlockLocationParam.builder()
                    .citedText("cited_text")
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
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofWebSearchResultLocation(webSearchResultLocation)

        assertThat(betaTextCitationParam.charLocation()).isEmpty
        assertThat(betaTextCitationParam.pageLocation()).isEmpty
        assertThat(betaTextCitationParam.contentBlockLocation()).isEmpty
        assertThat(betaTextCitationParam.webSearchResultLocation())
            .contains(webSearchResultLocation)
        assertThat(betaTextCitationParam.searchResultLocation()).isEmpty
    }

    @Test
    fun ofWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofWebSearchResultLocation(
                BetaCitationWebSearchResultLocationParam.builder()
                    .citedText("cited_text")
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
            BetaSearchResultLocationCitationParam.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofSearchResultLocation(searchResultLocation)

        assertThat(betaTextCitationParam.charLocation()).isEmpty
        assertThat(betaTextCitationParam.pageLocation()).isEmpty
        assertThat(betaTextCitationParam.contentBlockLocation()).isEmpty
        assertThat(betaTextCitationParam.webSearchResultLocation()).isEmpty
        assertThat(betaTextCitationParam.searchResultLocation()).contains(searchResultLocation)
    }

    @Test
    fun ofSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofSearchResultLocation(
                BetaSearchResultLocationCitationParam.builder()
                    .citedText("cited_text")
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
    }
}
