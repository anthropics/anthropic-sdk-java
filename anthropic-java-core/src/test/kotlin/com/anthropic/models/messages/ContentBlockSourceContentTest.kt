// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContentBlockSourceContentTest {

    @Test
    fun ofTextBlockParam() {
        val textBlockParam = TextBlockParam.builder().text("x").build()

        val contentBlockSourceContent = ContentBlockSourceContent.ofTextBlockParam(textBlockParam)

        assertThat(contentBlockSourceContent.textBlockParam()).contains(textBlockParam)
        assertThat(contentBlockSourceContent.imageBlockParam()).isEmpty
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
                .build()

        val contentBlockSourceContent = ContentBlockSourceContent.ofImageBlockParam(imageBlockParam)

        assertThat(contentBlockSourceContent.textBlockParam()).isEmpty
        assertThat(contentBlockSourceContent.imageBlockParam()).contains(imageBlockParam)
    }
}
