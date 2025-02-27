// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaImageBlockParamTest {

    @Test
    fun createBetaImageBlockParam() {
        val betaImageBlockParam =
            BetaImageBlockParam.builder()
                .source(
                    BetaBase64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()
        assertThat(betaImageBlockParam).isNotNull
        assertThat(betaImageBlockParam.source())
            .isEqualTo(
                BetaImageBlockParam.Source.ofBetaBase64Image(
                    BetaBase64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
            )
        assertThat(betaImageBlockParam.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }
}
