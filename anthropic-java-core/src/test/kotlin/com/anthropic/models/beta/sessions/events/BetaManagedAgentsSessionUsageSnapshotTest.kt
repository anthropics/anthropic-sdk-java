package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.BetaCurrency
import com.anthropic.models.beta.BetaMonetaryAmount
import com.anthropic.models.beta.sessions.BetaManagedAgentsCacheCreationUsage
import com.anthropic.models.beta.sessions.BetaManagedAgentsServerToolUsage
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionUsageSnapshotTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionUsageSnapshot =
            BetaManagedAgentsSessionUsageSnapshot.builder()
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

        assertThat(betaManagedAgentsSessionUsageSnapshot.activeSeconds()).contains(0.0)
        assertThat(betaManagedAgentsSessionUsageSnapshot.cacheCreation())
            .contains(
                BetaManagedAgentsCacheCreationUsage.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaManagedAgentsSessionUsageSnapshot.cacheReadInputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionUsageSnapshot.inputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionUsageSnapshot.listCost())
            .contains(
                BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
            )
        assertThat(betaManagedAgentsSessionUsageSnapshot.outputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionUsageSnapshot.serverToolUse())
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
        val betaManagedAgentsSessionUsageSnapshot =
            BetaManagedAgentsSessionUsageSnapshot.builder()
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

        val roundtrippedBetaManagedAgentsSessionUsageSnapshot =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionUsageSnapshot),
                jacksonTypeRef<BetaManagedAgentsSessionUsageSnapshot>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionUsageSnapshot)
            .isEqualTo(betaManagedAgentsSessionUsageSnapshot)
    }
}
