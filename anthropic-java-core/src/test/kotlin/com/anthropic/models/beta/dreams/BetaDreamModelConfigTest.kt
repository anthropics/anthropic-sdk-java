// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamModelConfigTest {

    @Test
    fun create() {
        val betaDreamModelConfig =
            BetaDreamModelConfig.builder()
                .id("x")
                .speed(BetaDreamModelConfig.Speed.STANDARD)
                .build()

        assertThat(betaDreamModelConfig.id()).isEqualTo("x")
        assertThat(betaDreamModelConfig.speed()).contains(BetaDreamModelConfig.Speed.STANDARD)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamModelConfig =
            BetaDreamModelConfig.builder()
                .id("x")
                .speed(BetaDreamModelConfig.Speed.STANDARD)
                .build()

        val roundtrippedBetaDreamModelConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamModelConfig),
                jacksonTypeRef<BetaDreamModelConfig>(),
            )

        assertThat(roundtrippedBetaDreamModelConfig).isEqualTo(betaDreamModelConfig)
    }
}
