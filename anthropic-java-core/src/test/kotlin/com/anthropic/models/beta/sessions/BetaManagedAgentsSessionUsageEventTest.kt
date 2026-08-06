// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.BetaCurrency
import com.anthropic.models.beta.BetaMonetaryAmount
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionUsageSnapshot
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionUsageEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionUsageEvent =
            BetaManagedAgentsSessionUsageEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionUsageEvent.Type.SESSION_USAGE)
                .usage(
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
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                        .outputTokens(0)
                        .serverToolUse(
                            BetaManagedAgentsServerToolUsage.builder()
                                .webFetchRequests(0)
                                .webSearchRequests(3)
                                .build()
                        )
                        .build()
                )
                .budget(
                    BetaManagedAgentsBudgetLimit.builder()
                        .maxListCost(
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                        .type(BetaManagedAgentsBudgetLimit.Type.LIMIT)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsSessionUsageEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionUsageEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionUsageEvent.type())
            .isEqualTo(BetaManagedAgentsSessionUsageEvent.Type.SESSION_USAGE)
        assertThat(betaManagedAgentsSessionUsageEvent.usage())
            .isEqualTo(
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
                        BetaMonetaryAmount.builder()
                            .amount("2500")
                            .currency(BetaCurrency.USD)
                            .build()
                    )
                    .outputTokens(0)
                    .serverToolUse(
                        BetaManagedAgentsServerToolUsage.builder()
                            .webFetchRequests(0)
                            .webSearchRequests(3)
                            .build()
                    )
                    .build()
            )
        assertThat(betaManagedAgentsSessionUsageEvent.budget())
            .contains(
                BetaManagedAgentsBudgetLimit.builder()
                    .maxListCost(
                        BetaMonetaryAmount.builder()
                            .amount("2500")
                            .currency(BetaCurrency.USD)
                            .build()
                    )
                    .type(BetaManagedAgentsBudgetLimit.Type.LIMIT)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionUsageEvent =
            BetaManagedAgentsSessionUsageEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionUsageEvent.Type.SESSION_USAGE)
                .usage(
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
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                        .outputTokens(0)
                        .serverToolUse(
                            BetaManagedAgentsServerToolUsage.builder()
                                .webFetchRequests(0)
                                .webSearchRequests(3)
                                .build()
                        )
                        .build()
                )
                .budget(
                    BetaManagedAgentsBudgetLimit.builder()
                        .maxListCost(
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                        .type(BetaManagedAgentsBudgetLimit.Type.LIMIT)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionUsageEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionUsageEvent),
                jacksonTypeRef<BetaManagedAgentsSessionUsageEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionUsageEvent)
            .isEqualTo(betaManagedAgentsSessionUsageEvent)
    }
}
