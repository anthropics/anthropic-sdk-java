// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaImageBlockParamTest {

    @Test
    fun create() {
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

        assertThat(betaImageBlockParam.source())
            .isEqualTo(
                BetaImageBlockParam.Source.ofBase64(
                    BetaBase64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
            )
        assertThat(betaImageBlockParam.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
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

        val roundtrippedBetaImageBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaImageBlockParam),
                jacksonTypeRef<BetaImageBlockParam>(),
            )

        assertThat(roundtrippedBetaImageBlockParam).isEqualTo(betaImageBlockParam)
    }
}
