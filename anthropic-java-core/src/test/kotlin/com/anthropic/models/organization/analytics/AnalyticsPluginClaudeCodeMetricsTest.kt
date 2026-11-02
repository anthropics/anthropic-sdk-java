package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsPluginClaudeCodeMetricsTest {

    @Test
    fun create() {
        val analyticsPluginClaudeCodeMetrics = AnalyticsPluginClaudeCodeMetrics.of(0L)

        assertThat(analyticsPluginClaudeCodeMetrics.distinctSessionPluginUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsPluginClaudeCodeMetrics = AnalyticsPluginClaudeCodeMetrics.of(0L)

        val roundtrippedAnalyticsPluginClaudeCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsPluginClaudeCodeMetrics),
                jacksonTypeRef<AnalyticsPluginClaudeCodeMetrics>(),
            )

        assertThat(roundtrippedAnalyticsPluginClaudeCodeMetrics)
            .isEqualTo(analyticsPluginClaudeCodeMetrics)
    }
}
