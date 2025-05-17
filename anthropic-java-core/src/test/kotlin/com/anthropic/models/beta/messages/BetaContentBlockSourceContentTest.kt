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
    fun ofText() {
        val text =
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

        val betaContentBlockSourceContent = BetaContentBlockSourceContent.ofText(text)

        assertThat(betaContentBlockSourceContent.text()).contains(text)
        assertThat(betaContentBlockSourceContent.image()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockSourceContent =
            BetaContentBlockSourceContent.ofText(
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
    fun ofImage() {
        val image =
            BetaImageBlockParam.builder()
                .source(
                    BetaBase64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val betaContentBlockSourceContent = BetaContentBlockSourceContent.ofImage(image)

        assertThat(betaContentBlockSourceContent.text()).isEmpty
        assertThat(betaContentBlockSourceContent.image()).contains(image)
    }

    @Test
    fun ofImageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockSourceContent =
            BetaContentBlockSourceContent.ofImage(
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
