// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBase64ImageSourceTest {

    @Test
    fun create() {
        val betaBase64ImageSource =
            BetaBase64ImageSource.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                .build()

        assertThat(betaBase64ImageSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
        assertThat(betaBase64ImageSource.mediaType())
            .isEqualTo(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBase64ImageSource =
            BetaBase64ImageSource.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                .build()

        val roundtrippedBetaBase64ImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBase64ImageSource),
                jacksonTypeRef<BetaBase64ImageSource>(),
            )

        assertThat(roundtrippedBetaBase64ImageSource).isEqualTo(betaBase64ImageSource)
    }
}
