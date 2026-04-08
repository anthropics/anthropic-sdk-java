// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsModelConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsModelConfig =
            BetaManagedAgentsModelConfig.builder()
                .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
                .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                .build()

        assertThat(betaManagedAgentsModelConfig.id())
            .isEqualTo(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
        assertThat(betaManagedAgentsModelConfig.speed())
            .contains(BetaManagedAgentsModelConfig.Speed.STANDARD)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsModelConfig =
            BetaManagedAgentsModelConfig.builder()
                .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
                .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                .build()

        val roundtrippedBetaManagedAgentsModelConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsModelConfig),
                jacksonTypeRef<BetaManagedAgentsModelConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsModelConfig).isEqualTo(betaManagedAgentsModelConfig)
    }
}
