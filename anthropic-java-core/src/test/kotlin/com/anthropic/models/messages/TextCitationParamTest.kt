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
    fun ofCitationCharLocation() {
        val citationCharLocation =
            CitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val textCitationParam = TextCitationParam.ofCitationCharLocation(citationCharLocation)

        assertThat(textCitationParam.citationCharLocation()).contains(citationCharLocation)
        assertThat(textCitationParam.citationPageLocation()).isEmpty
        assertThat(textCitationParam.citationContentBlockLocation()).isEmpty
        assertThat(textCitationParam.citationWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofCitationCharLocation(
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
    fun ofCitationPageLocation() {
        val citationPageLocation =
            CitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val textCitationParam = TextCitationParam.ofCitationPageLocation(citationPageLocation)

        assertThat(textCitationParam.citationCharLocation()).isEmpty
        assertThat(textCitationParam.citationPageLocation()).contains(citationPageLocation)
        assertThat(textCitationParam.citationContentBlockLocation()).isEmpty
        assertThat(textCitationParam.citationWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofCitationPageLocation(
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
    fun ofCitationContentBlockLocation() {
        val citationContentBlockLocation =
            CitationContentBlockLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val textCitationParam =
            TextCitationParam.ofCitationContentBlockLocation(citationContentBlockLocation)

        assertThat(textCitationParam.citationCharLocation()).isEmpty
        assertThat(textCitationParam.citationPageLocation()).isEmpty
        assertThat(textCitationParam.citationContentBlockLocation())
            .contains(citationContentBlockLocation)
        assertThat(textCitationParam.citationWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofCitationContentBlockLocation(
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
    fun ofCitationWebSearchResultLocation() {
        val citationWebSearchResultLocation =
            CitationWebSearchResultLocationParam.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        val textCitationParam =
            TextCitationParam.ofCitationWebSearchResultLocation(citationWebSearchResultLocation)

        assertThat(textCitationParam.citationCharLocation()).isEmpty
        assertThat(textCitationParam.citationPageLocation()).isEmpty
        assertThat(textCitationParam.citationContentBlockLocation()).isEmpty
        assertThat(textCitationParam.citationWebSearchResultLocation())
            .contains(citationWebSearchResultLocation)
    }

    @Test
    fun ofCitationWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val textCitationParam =
            TextCitationParam.ofCitationWebSearchResultLocation(
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
