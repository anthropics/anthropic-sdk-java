// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ImageBlockParamTest {

    @Test
    fun create() {
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

        assertThat(imageBlockParam.source())
            .isEqualTo(
                ImageBlockParam.Source.ofBase64Image(
                    Base64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
            )
        assertThat(imageBlockParam.cacheControl()).contains(CacheControlEphemeral.builder().build())
    }
}
