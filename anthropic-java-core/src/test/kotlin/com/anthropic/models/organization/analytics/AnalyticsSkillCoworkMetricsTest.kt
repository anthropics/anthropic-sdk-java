package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillCoworkMetricsTest {

    @Test
    fun create() {
        val analyticsSkillCoworkMetrics = AnalyticsSkillCoworkMetrics.of(0L)

        assertThat(analyticsSkillCoworkMetrics.distinctSessionSkillUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillCoworkMetrics = AnalyticsSkillCoworkMetrics.of(0L)

        val roundtrippedAnalyticsSkillCoworkMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillCoworkMetrics),
                jacksonTypeRef<AnalyticsSkillCoworkMetrics>(),
            )

        assertThat(roundtrippedAnalyticsSkillCoworkMetrics).isEqualTo(analyticsSkillCoworkMetrics)
    }
}
