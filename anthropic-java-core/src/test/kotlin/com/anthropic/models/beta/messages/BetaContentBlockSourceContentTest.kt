// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContentBlockSourceContentTest {

    @Test
    fun ofTextBlockParam() {
        val textBlockParam = BetaTextBlockParam.builder().text("x").build()

        val betaContentBlockSourceContent =
            BetaContentBlockSourceContent.ofTextBlockParam(textBlockParam)

        assertThat(betaContentBlockSourceContent.textBlockParam()).contains(textBlockParam)
        assertThat(betaContentBlockSourceContent.imageBlockParam()).isEmpty
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
                .build()

        val betaContentBlockSourceContent =
            BetaContentBlockSourceContent.ofImageBlockParam(imageBlockParam)

        assertThat(betaContentBlockSourceContent.textBlockParam()).isEmpty
        assertThat(betaContentBlockSourceContent.imageBlockParam()).contains(imageBlockParam)
    }
}
