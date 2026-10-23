package com.anthropic.models.beta.organization.analytics.usage

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServerToolUse
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsUsageMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsUsageMetrics =
            BetaAnalyticsUsageMetrics.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheReadInputTokens(3200000L)
                .outputTokens(891000L)
                .requests(128L)
                .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                .totalTokens(5377000L)
                .uncachedInputTokens(1284500L)
                .build()

        assertThat(betaAnalyticsUsageMetrics.cacheCreation())
            .isEqualTo(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaAnalyticsUsageMetrics.cacheReadInputTokens()).isEqualTo(3200000L)
        assertThat(betaAnalyticsUsageMetrics.outputTokens()).isEqualTo(891000L)
        assertThat(betaAnalyticsUsageMetrics.requests()).contains(128L)
        assertThat(betaAnalyticsUsageMetrics.serverToolUse())
            .isEqualTo(BetaAnalyticsServerToolUse.of(10L))
        assertThat(betaAnalyticsUsageMetrics.totalTokens()).isEqualTo(5377000L)
        assertThat(betaAnalyticsUsageMetrics.uncachedInputTokens()).isEqualTo(1284500L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsUsageMetrics =
            BetaAnalyticsUsageMetrics.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheReadInputTokens(3200000L)
                .outputTokens(891000L)
                .requests(128L)
                .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                .totalTokens(5377000L)
                .uncachedInputTokens(1284500L)
                .build()

        val roundtrippedBetaAnalyticsUsageMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsUsageMetrics),
                jacksonTypeRef<BetaAnalyticsUsageMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsUsageMetrics).isEqualTo(betaAnalyticsUsageMetrics)
    }
}
