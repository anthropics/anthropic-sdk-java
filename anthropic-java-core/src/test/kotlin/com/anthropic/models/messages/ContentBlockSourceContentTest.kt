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
    fun ofText() {
        val text =
            TextBlockParam.builder()
                .text("x")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
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

        val contentBlockSourceContent = ContentBlockSourceContent.ofText(text)

        assertThat(contentBlockSourceContent.text()).contains(text)
        assertThat(contentBlockSourceContent.image()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockSourceContent =
            ContentBlockSourceContent.ofText(
                TextBlockParam.builder()
                    .text("x")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
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
    fun ofImage() {
        val image =
            ImageBlockParam.builder()
                .source(
                    Base64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val contentBlockSourceContent = ContentBlockSourceContent.ofImage(image)

        assertThat(contentBlockSourceContent.text()).isEmpty
        assertThat(contentBlockSourceContent.image()).contains(image)
    }

    @Test
    fun ofImageRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockSourceContent =
            ContentBlockSourceContent.ofImage(
                ImageBlockParam.builder()
                    .source(
                        Base64ImageSource.builder()
                            .data("U3RhaW5sZXNzIHJvY2tz")
                            .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                            .build()
                    )
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
