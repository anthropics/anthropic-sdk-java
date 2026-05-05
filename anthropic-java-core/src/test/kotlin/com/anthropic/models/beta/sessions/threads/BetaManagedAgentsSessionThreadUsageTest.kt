// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.BetaManagedAgentsCacheCreationUsage
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionThreadUsageTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionThreadUsage =
            BetaManagedAgentsSessionThreadUsage.builder()
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

        assertThat(betaManagedAgentsSessionThreadUsage.cacheCreation())
            .contains(
                BetaManagedAgentsCacheCreationUsage.builder()
                    .ephemeral1hInputTokens(0)
                    .ephemeral5mInputTokens(0)
                    .build()
            )
        assertThat(betaManagedAgentsSessionThreadUsage.cacheReadInputTokens()).contains(0)
        assertThat(betaManagedAgentsSessionThreadUsage.inputTokens()).contains(0)
        assertThat(betaManagedAgentsSessionThreadUsage.outputTokens()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionThreadUsage =
            BetaManagedAgentsSessionThreadUsage.builder()
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

        val roundtrippedBetaManagedAgentsSessionThreadUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionThreadUsage),
                jacksonTypeRef<BetaManagedAgentsSessionThreadUsage>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionThreadUsage)
            .isEqualTo(betaManagedAgentsSessionThreadUsage)
    }
}
