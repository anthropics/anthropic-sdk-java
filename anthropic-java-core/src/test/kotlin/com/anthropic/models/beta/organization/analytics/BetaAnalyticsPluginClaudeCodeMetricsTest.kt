package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsPluginClaudeCodeMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsPluginClaudeCodeMetrics = BetaAnalyticsPluginClaudeCodeMetrics.of(0L)

        assertThat(betaAnalyticsPluginClaudeCodeMetrics.distinctSessionPluginUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsPluginClaudeCodeMetrics = BetaAnalyticsPluginClaudeCodeMetrics.of(0L)

        val roundtrippedBetaAnalyticsPluginClaudeCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsPluginClaudeCodeMetrics),
                jacksonTypeRef<BetaAnalyticsPluginClaudeCodeMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsPluginClaudeCodeMetrics)
            .isEqualTo(betaAnalyticsPluginClaudeCodeMetrics)
    }
}
