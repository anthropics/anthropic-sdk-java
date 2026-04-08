// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSpanModelUsageTest {

    @Test
    fun create() {
        val betaManagedAgentsSpanModelUsage =
            BetaManagedAgentsSpanModelUsage.builder()
                .cacheCreationInputTokens(0)
                .cacheReadInputTokens(0)
                .inputTokens(0)
                .outputTokens(0)
                .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                .build()

        assertThat(betaManagedAgentsSpanModelUsage.cacheCreationInputTokens()).isEqualTo(0)
        assertThat(betaManagedAgentsSpanModelUsage.cacheReadInputTokens()).isEqualTo(0)
        assertThat(betaManagedAgentsSpanModelUsage.inputTokens()).isEqualTo(0)
        assertThat(betaManagedAgentsSpanModelUsage.outputTokens()).isEqualTo(0)
        assertThat(betaManagedAgentsSpanModelUsage.speed())
            .contains(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSpanModelUsage =
            BetaManagedAgentsSpanModelUsage.builder()
                .cacheCreationInputTokens(0)
                .cacheReadInputTokens(0)
                .inputTokens(0)
                .outputTokens(0)
                .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                .build()

        val roundtrippedBetaManagedAgentsSpanModelUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSpanModelUsage),
                jacksonTypeRef<BetaManagedAgentsSpanModelUsage>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSpanModelUsage)
            .isEqualTo(betaManagedAgentsSpanModelUsage)
    }
}
