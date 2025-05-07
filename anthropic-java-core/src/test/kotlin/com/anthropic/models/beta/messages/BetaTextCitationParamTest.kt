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
    fun ofCitationCharLocation() {
        val citationCharLocation =
            BetaCitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationCharLocation(citationCharLocation)

        assertThat(betaTextCitationParam.citationCharLocation()).contains(citationCharLocation)
        assertThat(betaTextCitationParam.citationPageLocation()).isEmpty
        assertThat(betaTextCitationParam.citationContentBlockLocation()).isEmpty
        assertThat(betaTextCitationParam.citationWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationCharLocation(
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
    fun ofCitationPageLocation() {
        val citationPageLocation =
            BetaCitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationPageLocation(citationPageLocation)

        assertThat(betaTextCitationParam.citationCharLocation()).isEmpty
        assertThat(betaTextCitationParam.citationPageLocation()).contains(citationPageLocation)
        assertThat(betaTextCitationParam.citationContentBlockLocation()).isEmpty
        assertThat(betaTextCitationParam.citationWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationPageLocation(
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
    fun ofCitationContentBlockLocation() {
        val citationContentBlockLocation =
            BetaCitationContentBlockLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationContentBlockLocation(citationContentBlockLocation)

        assertThat(betaTextCitationParam.citationCharLocation()).isEmpty
        assertThat(betaTextCitationParam.citationPageLocation()).isEmpty
        assertThat(betaTextCitationParam.citationContentBlockLocation())
            .contains(citationContentBlockLocation)
        assertThat(betaTextCitationParam.citationWebSearchResultLocation()).isEmpty
    }

    @Test
    fun ofCitationContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationContentBlockLocation(
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
    fun ofCitationWebSearchResultLocation() {
        val citationWebSearchResultLocation =
            BetaCitationWebSearchResultLocationParam.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationWebSearchResultLocation(citationWebSearchResultLocation)

        assertThat(betaTextCitationParam.citationCharLocation()).isEmpty
        assertThat(betaTextCitationParam.citationPageLocation()).isEmpty
        assertThat(betaTextCitationParam.citationContentBlockLocation()).isEmpty
        assertThat(betaTextCitationParam.citationWebSearchResultLocation())
            .contains(citationWebSearchResultLocation)
    }

    @Test
    fun ofCitationWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationWebSearchResultLocation(
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
