package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillClaudeCodeMetricsTest {

    @Test
    fun create() {
        val analyticsSkillClaudeCodeMetrics = AnalyticsSkillClaudeCodeMetrics.of(0L)

        assertThat(analyticsSkillClaudeCodeMetrics.distinctSessionSkillUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillClaudeCodeMetrics = AnalyticsSkillClaudeCodeMetrics.of(0L)

        val roundtrippedAnalyticsSkillClaudeCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillClaudeCodeMetrics),
                jacksonTypeRef<AnalyticsSkillClaudeCodeMetrics>(),
            )

        assertThat(roundtrippedAnalyticsSkillClaudeCodeMetrics)
            .isEqualTo(analyticsSkillClaudeCodeMetrics)
    }
}
