// File generated from our OpenAPI spec by Stainless.

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
                        .ephemeral1hInputTokens(0)
                        .ephemeral5mInputTokens(0)
                        .build()
                )
                .cacheReadInputTokens(0)
                .inputTokens(0)
                .listCost(
                    BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
                )
                .outputTokens(0)
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
                    .ephemeral1hInputTokens(0)
                    .ephemeral5mInputTokens(0)
                    .build()
            )
        assertThat(betaManagedAgentsSessionUsageSnapshot.cacheReadInputTokens()).contains(0)
        assertThat(betaManagedAgentsSessionUsageSnapshot.inputTokens()).contains(0)
        assertThat(betaManagedAgentsSessionUsageSnapshot.listCost())
            .contains(
                BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
            )
        assertThat(betaManagedAgentsSessionUsageSnapshot.outputTokens()).contains(0)
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
                        .ephemeral1hInputTokens(0)
                        .ephemeral5mInputTokens(0)
                        .build()
                )
                .cacheReadInputTokens(0)
                .inputTokens(0)
                .listCost(
                    BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
                )
                .outputTokens(0)
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
