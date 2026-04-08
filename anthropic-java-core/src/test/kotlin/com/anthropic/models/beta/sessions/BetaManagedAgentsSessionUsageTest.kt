// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionUsageTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionUsage =
            BetaManagedAgentsSessionUsage.builder()
                .cacheCreation(
                    BetaManagedAgentsCacheCreationUsage.builder()
                        .ephemeral1hInputTokens(0)
                        .ephemeral5mInputTokens(0)
                        .build()
                )
                .cacheReadInputTokens(0)
                .inputTokens(0)
                .outputTokens(0)
                .build()

        assertThat(betaManagedAgentsSessionUsage.cacheCreation())
            .contains(
                BetaManagedAgentsCacheCreationUsage.builder()
                    .ephemeral1hInputTokens(0)
                    .ephemeral5mInputTokens(0)
                    .build()
            )
        assertThat(betaManagedAgentsSessionUsage.cacheReadInputTokens()).contains(0)
        assertThat(betaManagedAgentsSessionUsage.inputTokens()).contains(0)
        assertThat(betaManagedAgentsSessionUsage.outputTokens()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionUsage =
            BetaManagedAgentsSessionUsage.builder()
                .cacheCreation(
                    BetaManagedAgentsCacheCreationUsage.builder()
                        .ephemeral1hInputTokens(0)
                        .ephemeral5mInputTokens(0)
                        .build()
                )
                .cacheReadInputTokens(0)
                .inputTokens(0)
                .outputTokens(0)
                .build()

        val roundtrippedBetaManagedAgentsSessionUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionUsage),
                jacksonTypeRef<BetaManagedAgentsSessionUsage>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionUsage)
            .isEqualTo(betaManagedAgentsSessionUsage)
    }
}
