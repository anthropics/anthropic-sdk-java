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

internal class ContentBlockSourceContentTest {

    @Test
    fun ofTextBlockParam() {
        val textBlockParam =
            TextBlockParam.builder()
                .text("x")
                .cacheControl(CacheControlEphemeral.builder().build())
                .addCitation(
                    CitationCharLocationParam.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val contentBlockSourceContent = ContentBlockSourceContent.ofTextBlockParam(textBlockParam)

        assertThat(contentBlockSourceContent.textBlockParam()).contains(textBlockParam)
        assertThat(contentBlockSourceContent.imageBlockParam()).isEmpty
    }

    @Test
    fun ofTextBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockSourceContent =
            ContentBlockSourceContent.ofTextBlockParam(
                TextBlockParam.builder()
                    .text("x")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .addCitation(
                        CitationCharLocationParam.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("x")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )

        val roundtrippedContentBlockSourceContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockSourceContent),
                jacksonTypeRef<ContentBlockSourceContent>(),
            )

        assertThat(roundtrippedContentBlockSourceContent).isEqualTo(contentBlockSourceContent)
    }

    @Test
    fun ofImageBlockParam() {
        val imageBlockParam =
            ImageBlockParam.builder()
                .source(
                    Base64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()

        val contentBlockSourceContent = ContentBlockSourceContent.ofImageBlockParam(imageBlockParam)

        assertThat(contentBlockSourceContent.textBlockParam()).isEmpty
        assertThat(contentBlockSourceContent.imageBlockParam()).contains(imageBlockParam)
    }

    @Test
    fun ofImageBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockSourceContent =
            ContentBlockSourceContent.ofImageBlockParam(
                ImageBlockParam.builder()
                    .source(
                        Base64ImageSource.builder()
                            .data("U3RhaW5sZXNzIHJvY2tz")
                            .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                            .build()
                    )
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedContentBlockSourceContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockSourceContent),
                jacksonTypeRef<ContentBlockSourceContent>(),
            )

        assertThat(roundtrippedContentBlockSourceContent).isEqualTo(contentBlockSourceContent)
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
        val contentBlockSourceContent =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ContentBlockSourceContent>())

        val e = assertThrows<AnthropicInvalidDataException> { contentBlockSourceContent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
