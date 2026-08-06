// File generated from our OpenAPI spec by Stainless.

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

        assertThat(betaManagedAgentsSessionUsage.activeSeconds()).contains(0.0)
        assertThat(betaManagedAgentsSessionUsage.cacheCreation())
            .contains(
                BetaManagedAgentsCacheCreationUsage.builder()
                    .ephemeral1hInputTokens(0)
                    .ephemeral5mInputTokens(0)
                    .build()
            )
        assertThat(betaManagedAgentsSessionUsage.cacheReadInputTokens()).contains(0)
        assertThat(betaManagedAgentsSessionUsage.inputTokens()).contains(0)
        assertThat(betaManagedAgentsSessionUsage.listCost())
            .contains(
                BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
            )
        assertThat(betaManagedAgentsSessionUsage.outputTokens()).contains(0)
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

        val roundtrippedBetaManagedAgentsSessionUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionUsage),
                jacksonTypeRef<BetaManagedAgentsSessionUsage>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionUsage)
            .isEqualTo(betaManagedAgentsSessionUsage)
    }
}
