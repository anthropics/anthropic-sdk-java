// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ImageTransformationsParamTest {

    @Test
    fun create() {
        val imageTransformationsParam =
            ImageTransformationsParam.builder()
                .oversizedImage(ImageTransformationsParam.OversizedImage.DOWNSIZE)
                .build()

        assertThat(imageTransformationsParam.oversizedImage())
            .contains(ImageTransformationsParam.OversizedImage.DOWNSIZE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val imageTransformationsParam =
            ImageTransformationsParam.builder()
                .oversizedImage(ImageTransformationsParam.OversizedImage.DOWNSIZE)
                .build()

        val roundtrippedImageTransformationsParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(imageTransformationsParam),
                jacksonTypeRef<ImageTransformationsParam>(),
            )

        assertThat(roundtrippedImageTransformationsParam).isEqualTo(imageTransformationsParam)
    }
}
