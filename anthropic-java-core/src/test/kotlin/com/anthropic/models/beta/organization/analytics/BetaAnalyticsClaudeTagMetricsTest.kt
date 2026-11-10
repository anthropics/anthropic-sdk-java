package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsClaudeTagMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsClaudeTagMetrics =
            BetaAnalyticsClaudeTagMetrics.builder()
                .activeDayCount(0L)
                .activeMessageCount(0L)
                .build()

        assertThat(betaAnalyticsClaudeTagMetrics.activeDayCount()).contains(0L)
        assertThat(betaAnalyticsClaudeTagMetrics.activeMessageCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsClaudeTagMetrics =
            BetaAnalyticsClaudeTagMetrics.builder()
                .activeDayCount(0L)
                .activeMessageCount(0L)
                .build()

        val roundtrippedBetaAnalyticsClaudeTagMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsClaudeTagMetrics),
                jacksonTypeRef<BetaAnalyticsClaudeTagMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsClaudeTagMetrics)
            .isEqualTo(betaAnalyticsClaudeTagMetrics)
    }
}
