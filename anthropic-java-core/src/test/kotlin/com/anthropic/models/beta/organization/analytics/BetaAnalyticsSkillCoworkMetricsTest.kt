package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillCoworkMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsSkillCoworkMetrics = BetaAnalyticsSkillCoworkMetrics.of(0L)

        assertThat(betaAnalyticsSkillCoworkMetrics.distinctSessionSkillUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillCoworkMetrics = BetaAnalyticsSkillCoworkMetrics.of(0L)

        val roundtrippedBetaAnalyticsSkillCoworkMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillCoworkMetrics),
                jacksonTypeRef<BetaAnalyticsSkillCoworkMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillCoworkMetrics)
            .isEqualTo(betaAnalyticsSkillCoworkMetrics)
    }
}
