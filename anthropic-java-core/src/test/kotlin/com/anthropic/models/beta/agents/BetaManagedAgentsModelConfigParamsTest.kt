// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsModelConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsModelConfigParams =
            BetaManagedAgentsModelConfigParams.builder()
                .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
                .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                .build()

        assertThat(betaManagedAgentsModelConfigParams.id())
            .isEqualTo(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
        assertThat(betaManagedAgentsModelConfigParams.speed())
            .contains(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsModelConfigParams =
            BetaManagedAgentsModelConfigParams.builder()
                .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
                .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                .build()

        val roundtrippedBetaManagedAgentsModelConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsModelConfigParams),
                jacksonTypeRef<BetaManagedAgentsModelConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsModelConfigParams)
            .isEqualTo(betaManagedAgentsModelConfigParams)
    }
}
