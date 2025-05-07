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
    fun ofCitationCharLocation() {
        val citationCharLocation =
            BetaCitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val betaTextCitation = BetaTextCitation.ofCitationCharLocation(citationCharLocation)

        assertThat(betaTextCitation.citationCharLocation()).contains(citationCharLocation)
        assertThat(betaTextCitation.citationPageLocation()).isEmpty
        assertThat(betaTextCitation.citationContentBlockLocation()).isEmpty
        assertThat(betaTextCitation.citationsWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofCitationCharLocation(
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
    fun ofCitationPageLocation() {
        val citationPageLocation =
            BetaCitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val betaTextCitation = BetaTextCitation.ofCitationPageLocation(citationPageLocation)

        assertThat(betaTextCitation.citationCharLocation()).isEmpty
        assertThat(betaTextCitation.citationPageLocation()).contains(citationPageLocation)
        assertThat(betaTextCitation.citationContentBlockLocation()).isEmpty
        assertThat(betaTextCitation.citationsWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofCitationPageLocation(
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
    fun ofCitationContentBlockLocation() {
        val citationContentBlockLocation =
            BetaCitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val betaTextCitation =
            BetaTextCitation.ofCitationContentBlockLocation(citationContentBlockLocation)

        assertThat(betaTextCitation.citationCharLocation()).isEmpty
        assertThat(betaTextCitation.citationPageLocation()).isEmpty
        assertThat(betaTextCitation.citationContentBlockLocation())
            .contains(citationContentBlockLocation)
        assertThat(betaTextCitation.citationsWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofCitationContentBlockLocation(
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
    fun ofCitationsWebSearchResultLocation() {
        val citationsWebSearchResultLocation =
            BetaCitationsWebSearchResultLocation.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        val betaTextCitation =
            BetaTextCitation.ofCitationsWebSearchResultLocation(citationsWebSearchResultLocation)

        assertThat(betaTextCitation.citationCharLocation()).isEmpty
        assertThat(betaTextCitation.citationPageLocation()).isEmpty
        assertThat(betaTextCitation.citationContentBlockLocation()).isEmpty
        assertThat(betaTextCitation.citationsWebSearchResultLocation())
            .contains(citationsWebSearchResultLocation)
    }

    @Test
    fun ofCitationsWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofCitationsWebSearchResultLocation(
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
