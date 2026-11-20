package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.BetaCurrency
import com.anthropic.models.beta.BetaMonetaryAmount
import com.anthropic.models.beta.sessions.BetaManagedAgentsCacheCreationUsage
import com.anthropic.models.beta.sessions.BetaManagedAgentsServerToolUsage
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionThreadUsageTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionThreadUsage =
            BetaManagedAgentsSessionThreadUsage.builder()
                .activeSeconds(0.0)
                .cacheCreation(
                    BetaManagedAgentsCacheCreationUsage.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheReadInputTokens(0L)
                .inputTokens(0L)
                .listCost(
                    BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
                )
                .outputTokens(0L)
                .serverToolUse(
                    BetaManagedAgentsServerToolUsage.builder()
                        .webFetchRequests(0)
                        .webSearchRequests(3)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsSessionThreadUsage.activeSeconds()).contains(0.0)
        assertThat(betaManagedAgentsSessionThreadUsage.cacheCreation())
            .contains(
                BetaManagedAgentsCacheCreationUsage.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaManagedAgentsSessionThreadUsage.cacheReadInputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionThreadUsage.inputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionThreadUsage.listCost())
            .contains(
                BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
            )
        assertThat(betaManagedAgentsSessionThreadUsage.outputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionThreadUsage.serverToolUse())
            .contains(
                BetaManagedAgentsServerToolUsage.builder()
                    .webFetchRequests(0)
                    .webSearchRequests(3)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionThreadUsage =
            BetaManagedAgentsSessionThreadUsage.builder()
                .activeSeconds(0.0)
                .cacheCreation(
                    BetaManagedAgentsCacheCreationUsage.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheReadInputTokens(0L)
                .inputTokens(0L)
                .listCost(
                    BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
                )
                .outputTokens(0L)
                .serverToolUse(
                    BetaManagedAgentsServerToolUsage.builder()
                        .webFetchRequests(0)
                        .webSearchRequests(3)
                        .build()
                )
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
