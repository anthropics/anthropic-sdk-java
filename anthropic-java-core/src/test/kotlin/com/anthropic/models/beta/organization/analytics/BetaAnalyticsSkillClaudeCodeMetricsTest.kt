package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillClaudeCodeMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsSkillClaudeCodeMetrics = BetaAnalyticsSkillClaudeCodeMetrics.of(0L)

        assertThat(betaAnalyticsSkillClaudeCodeMetrics.distinctSessionSkillUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillClaudeCodeMetrics = BetaAnalyticsSkillClaudeCodeMetrics.of(0L)

        val roundtrippedBetaAnalyticsSkillClaudeCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillClaudeCodeMetrics),
                jacksonTypeRef<BetaAnalyticsSkillClaudeCodeMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillClaudeCodeMetrics)
            .isEqualTo(betaAnalyticsSkillClaudeCodeMetrics)
    }
}
