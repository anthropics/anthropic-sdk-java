package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillOfficeMetricsTest {

    @Test
    fun create() {
        val analyticsSkillOfficeMetrics =
            AnalyticsSkillOfficeMetrics.builder()
                .excel(AnalyticsSkillOfficeProductMetrics.of(0L))
                .outlook(AnalyticsSkillOfficeProductMetrics.of(0L))
                .powerpoint(AnalyticsSkillOfficeProductMetrics.of(0L))
                .word(AnalyticsSkillOfficeProductMetrics.of(0L))
                .build()

        assertThat(analyticsSkillOfficeMetrics.excel())
            .isEqualTo(AnalyticsSkillOfficeProductMetrics.of(0L))
        assertThat(analyticsSkillOfficeMetrics.outlook())
            .isEqualTo(AnalyticsSkillOfficeProductMetrics.of(0L))
        assertThat(analyticsSkillOfficeMetrics.powerpoint())
            .isEqualTo(AnalyticsSkillOfficeProductMetrics.of(0L))
        assertThat(analyticsSkillOfficeMetrics.word())
            .isEqualTo(AnalyticsSkillOfficeProductMetrics.of(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillOfficeMetrics =
            AnalyticsSkillOfficeMetrics.builder()
                .excel(AnalyticsSkillOfficeProductMetrics.of(0L))
                .outlook(AnalyticsSkillOfficeProductMetrics.of(0L))
                .powerpoint(AnalyticsSkillOfficeProductMetrics.of(0L))
                .word(AnalyticsSkillOfficeProductMetrics.of(0L))
                .build()

        val roundtrippedAnalyticsSkillOfficeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillOfficeMetrics),
                jacksonTypeRef<AnalyticsSkillOfficeMetrics>(),
            )

        assertThat(roundtrippedAnalyticsSkillOfficeMetrics).isEqualTo(analyticsSkillOfficeMetrics)
    }
}
