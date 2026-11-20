package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ImageDimensionsTooLargeErrorDetailsTest {

    @Test
    fun create() {
        val imageDimensionsTooLargeErrorDetails =
            ImageDimensionsTooLargeErrorDetails.builder()
                .maxSideLengthPixels(0L)
                .addPath("string")
                .build()

        assertThat(imageDimensionsTooLargeErrorDetails.maxSideLengthPixels()).isEqualTo(0L)
        assertThat(imageDimensionsTooLargeErrorDetails.paths()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val imageDimensionsTooLargeErrorDetails =
            ImageDimensionsTooLargeErrorDetails.builder()
                .maxSideLengthPixels(0L)
                .addPath("string")
                .build()

        val roundtrippedImageDimensionsTooLargeErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(imageDimensionsTooLargeErrorDetails),
                jacksonTypeRef<ImageDimensionsTooLargeErrorDetails>(),
            )

        assertThat(roundtrippedImageDimensionsTooLargeErrorDetails)
            .isEqualTo(imageDimensionsTooLargeErrorDetails)
    }
}
