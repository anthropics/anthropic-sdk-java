package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillOfficeProductMetricsTest {

    @Test
    fun create() {
        val analyticsSkillOfficeProductMetrics = AnalyticsSkillOfficeProductMetrics.of(0L)

        assertThat(analyticsSkillOfficeProductMetrics.distinctSessionSkillUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillOfficeProductMetrics = AnalyticsSkillOfficeProductMetrics.of(0L)

        val roundtrippedAnalyticsSkillOfficeProductMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillOfficeProductMetrics),
                jacksonTypeRef<AnalyticsSkillOfficeProductMetrics>(),
            )

        assertThat(roundtrippedAnalyticsSkillOfficeProductMetrics)
            .isEqualTo(analyticsSkillOfficeProductMetrics)
    }
}
