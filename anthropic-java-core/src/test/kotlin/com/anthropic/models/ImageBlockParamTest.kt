// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ImageBlockParamTest {

    @Test
    fun createImageBlockParam() {
        val imageBlockParam =
            ImageBlockParam.builder()
                .source(
                    ImageBlockParam.Source.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(ImageBlockParam.Source.MediaType.IMAGE_JPEG)
                        .type(ImageBlockParam.Source.Type.BASE64)
                        .build()
                )
                .type(ImageBlockParam.Type.IMAGE)
                .cacheControl(
                    CacheControlEphemeral.builder()
                        .type(CacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(imageBlockParam).isNotNull
        assertThat(imageBlockParam.source())
            .isEqualTo(
                ImageBlockParam.Source.builder()
                    .data("U3RhaW5sZXNzIHJvY2tz")
                    .mediaType(ImageBlockParam.Source.MediaType.IMAGE_JPEG)
                    .type(ImageBlockParam.Source.Type.BASE64)
                    .build()
            )
        assertThat(imageBlockParam.type()).isEqualTo(ImageBlockParam.Type.IMAGE)
        assertThat(imageBlockParam.cacheControl())
            .contains(
                CacheControlEphemeral.builder().type(CacheControlEphemeral.Type.EPHEMERAL).build()
            )
    }
}
