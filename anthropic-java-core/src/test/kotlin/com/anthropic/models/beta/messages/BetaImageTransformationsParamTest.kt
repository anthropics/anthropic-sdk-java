// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaImageTransformationsParamTest {

    @Test
    fun create() {
        val betaImageTransformationsParam =
            BetaImageTransformationsParam.builder()
                .oversizedImage(BetaImageTransformationsParam.OversizedImage.DOWNSIZE)
                .build()

        assertThat(betaImageTransformationsParam.oversizedImage())
            .contains(BetaImageTransformationsParam.OversizedImage.DOWNSIZE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaImageTransformationsParam =
            BetaImageTransformationsParam.builder()
                .oversizedImage(BetaImageTransformationsParam.OversizedImage.DOWNSIZE)
                .build()

        val roundtrippedBetaImageTransformationsParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaImageTransformationsParam),
                jacksonTypeRef<BetaImageTransformationsParam>(),
            )

        assertThat(roundtrippedBetaImageTransformationsParam)
            .isEqualTo(betaImageTransformationsParam)
    }
}
