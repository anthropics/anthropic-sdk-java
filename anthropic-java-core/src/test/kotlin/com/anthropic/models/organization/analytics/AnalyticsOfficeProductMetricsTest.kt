package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsOfficeProductMetricsTest {

    @Test
    fun create() {
        val analyticsOfficeProductMetrics =
            AnalyticsOfficeProductMetrics.builder()
                .connectorsUsedCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctSessionCount(0L)
                .distinctSkillsUsedCount(0L)
                .messageCount(0L)
                .skillsUsedCount(0L)
                .build()

        assertThat(analyticsOfficeProductMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(analyticsOfficeProductMetrics.distinctConnectorsUsedCount()).contains(0L)
        assertThat(analyticsOfficeProductMetrics.distinctSessionCount()).contains(0L)
        assertThat(analyticsOfficeProductMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(analyticsOfficeProductMetrics.messageCount()).isEqualTo(0L)
        assertThat(analyticsOfficeProductMetrics.skillsUsedCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsOfficeProductMetrics =
            AnalyticsOfficeProductMetrics.builder()
                .connectorsUsedCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctSessionCount(0L)
                .distinctSkillsUsedCount(0L)
                .messageCount(0L)
                .skillsUsedCount(0L)
                .build()

        val roundtrippedAnalyticsOfficeProductMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsOfficeProductMetrics),
                jacksonTypeRef<AnalyticsOfficeProductMetrics>(),
            )

        assertThat(roundtrippedAnalyticsOfficeProductMetrics)
            .isEqualTo(analyticsOfficeProductMetrics)
    }
}
