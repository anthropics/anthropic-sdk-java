// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamModelConfigParamTest {

    @Test
    fun create() {
        val betaDreamModelConfigParam =
            BetaDreamModelConfigParam.builder()
                .id("x")
                .speed(BetaDreamModelConfigParam.Speed.STANDARD)
                .build()

        assertThat(betaDreamModelConfigParam.id()).isEqualTo("x")
        assertThat(betaDreamModelConfigParam.speed())
            .contains(BetaDreamModelConfigParam.Speed.STANDARD)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamModelConfigParam =
            BetaDreamModelConfigParam.builder()
                .id("x")
                .speed(BetaDreamModelConfigParam.Speed.STANDARD)
                .build()

        val roundtrippedBetaDreamModelConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamModelConfigParam),
                jacksonTypeRef<BetaDreamModelConfigParam>(),
            )

        assertThat(roundtrippedBetaDreamModelConfigParam).isEqualTo(betaDreamModelConfigParam)
    }
}
