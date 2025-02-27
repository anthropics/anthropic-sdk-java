// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaBase64ImageSourceTest {

    @Test
    fun createBetaBase64ImageSource() {
        val betaBase64ImageSource =
            BetaBase64ImageSource.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                .build()
        assertThat(betaBase64ImageSource).isNotNull
        assertThat(betaBase64ImageSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
        assertThat(betaBase64ImageSource.mediaType())
            .isEqualTo(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
    }
}
