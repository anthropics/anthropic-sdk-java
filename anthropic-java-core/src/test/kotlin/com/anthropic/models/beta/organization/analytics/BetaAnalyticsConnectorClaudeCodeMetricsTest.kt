package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorClaudeCodeMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorClaudeCodeMetrics = BetaAnalyticsConnectorClaudeCodeMetrics.of(0L)

        assertThat(betaAnalyticsConnectorClaudeCodeMetrics.distinctSessionConnectorUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorClaudeCodeMetrics = BetaAnalyticsConnectorClaudeCodeMetrics.of(0L)

        val roundtrippedBetaAnalyticsConnectorClaudeCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsConnectorClaudeCodeMetrics),
                jacksonTypeRef<BetaAnalyticsConnectorClaudeCodeMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorClaudeCodeMetrics)
            .isEqualTo(betaAnalyticsConnectorClaudeCodeMetrics)
    }
}
