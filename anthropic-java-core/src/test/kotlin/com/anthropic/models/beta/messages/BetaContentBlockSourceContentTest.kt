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

internal class BetaContentBlockSourceContentTest {

    @Test
    fun ofTextBlockParam() {
        val textBlockParam =
            BetaTextBlockParam.builder()
                .text("x")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .addCitation(
                    BetaCitationCharLocationParam.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val betaContentBlockSourceContent =
            BetaContentBlockSourceContent.ofTextBlockParam(textBlockParam)

        assertThat(betaContentBlockSourceContent.textBlockParam()).contains(textBlockParam)
        assertThat(betaContentBlockSourceContent.imageBlockParam()).isEmpty
    }

    @Test
    fun ofTextBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockSourceContent =
            BetaContentBlockSourceContent.ofTextBlockParam(
                BetaTextBlockParam.builder()
                    .text("x")
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .addCitation(
                        BetaCitationCharLocationParam.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("x")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaContentBlockSourceContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockSourceContent),
                jacksonTypeRef<BetaContentBlockSourceContent>(),
            )

        assertThat(roundtrippedBetaContentBlockSourceContent)
            .isEqualTo(betaContentBlockSourceContent)
    }

    @Test
    fun ofImageBlockParam() {
        val imageBlockParam =
            BetaImageBlockParam.builder()
                .source(
                    BetaBase64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val betaContentBlockSourceContent =
            BetaContentBlockSourceContent.ofImageBlockParam(imageBlockParam)

        assertThat(betaContentBlockSourceContent.textBlockParam()).isEmpty
        assertThat(betaContentBlockSourceContent.imageBlockParam()).contains(imageBlockParam)
    }

    @Test
    fun ofImageBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockSourceContent =
            BetaContentBlockSourceContent.ofImageBlockParam(
                BetaImageBlockParam.builder()
                    .source(
                        BetaBase64ImageSource.builder()
                            .data("U3RhaW5sZXNzIHJvY2tz")
                            .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                            .build()
                    )
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedBetaContentBlockSourceContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockSourceContent),
                jacksonTypeRef<BetaContentBlockSourceContent>(),
            )

        assertThat(roundtrippedBetaContentBlockSourceContent)
            .isEqualTo(betaContentBlockSourceContent)
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
        val betaContentBlockSourceContent =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaContentBlockSourceContent>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaContentBlockSourceContent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
