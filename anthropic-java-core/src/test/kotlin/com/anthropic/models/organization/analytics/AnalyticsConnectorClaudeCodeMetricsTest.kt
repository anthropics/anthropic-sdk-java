package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorClaudeCodeMetricsTest {

    @Test
    fun create() {
        val analyticsConnectorClaudeCodeMetrics = AnalyticsConnectorClaudeCodeMetrics.of(0L)

        assertThat(analyticsConnectorClaudeCodeMetrics.distinctSessionConnectorUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorClaudeCodeMetrics = AnalyticsConnectorClaudeCodeMetrics.of(0L)

        val roundtrippedAnalyticsConnectorClaudeCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorClaudeCodeMetrics),
                jacksonTypeRef<AnalyticsConnectorClaudeCodeMetrics>(),
            )

        assertThat(roundtrippedAnalyticsConnectorClaudeCodeMetrics)
            .isEqualTo(analyticsConnectorClaudeCodeMetrics)
    }
}
