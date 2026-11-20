package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.BetaCurrency
import com.anthropic.models.beta.BetaMonetaryAmount
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionUsageTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionUsage =
            BetaManagedAgentsSessionUsage.builder()
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

        assertThat(betaManagedAgentsSessionUsage.activeSeconds()).contains(0.0)
        assertThat(betaManagedAgentsSessionUsage.cacheCreation())
            .contains(
                BetaManagedAgentsCacheCreationUsage.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaManagedAgentsSessionUsage.cacheReadInputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionUsage.inputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionUsage.listCost())
            .contains(
                BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
            )
        assertThat(betaManagedAgentsSessionUsage.outputTokens()).contains(0L)
        assertThat(betaManagedAgentsSessionUsage.serverToolUse())
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
        val betaManagedAgentsSessionUsage =
            BetaManagedAgentsSessionUsage.builder()
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

        val roundtrippedBetaManagedAgentsSessionUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionUsage),
                jacksonTypeRef<BetaManagedAgentsSessionUsage>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionUsage)
            .isEqualTo(betaManagedAgentsSessionUsage)
    }
}
