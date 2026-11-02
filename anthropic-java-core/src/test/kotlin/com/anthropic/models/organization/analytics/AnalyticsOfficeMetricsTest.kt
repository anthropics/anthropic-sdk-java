package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsOfficeMetricsTest {

    @Test
    fun create() {
        val analyticsOfficeMetrics =
            AnalyticsOfficeMetrics.builder()
                .excel(
                    AnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .outlook(
                    AnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .powerpoint(
                    AnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .word(
                    AnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .build()

        assertThat(analyticsOfficeMetrics.excel())
            .isEqualTo(
                AnalyticsOfficeProductMetrics.builder()
                    .connectorsUsedCount(0L)
                    .distinctConnectorsUsedCount(0L)
                    .distinctSessionCount(0L)
                    .distinctSkillsUsedCount(0L)
                    .messageCount(0L)
                    .skillsUsedCount(0L)
                    .build()
            )
        assertThat(analyticsOfficeMetrics.outlook())
            .isEqualTo(
                AnalyticsOfficeProductMetrics.builder()
                    .connectorsUsedCount(0L)
                    .distinctConnectorsUsedCount(0L)
                    .distinctSessionCount(0L)
                    .distinctSkillsUsedCount(0L)
                    .messageCount(0L)
                    .skillsUsedCount(0L)
                    .build()
            )
        assertThat(analyticsOfficeMetrics.powerpoint())
            .isEqualTo(
                AnalyticsOfficeProductMetrics.builder()
                    .connectorsUsedCount(0L)
                    .distinctConnectorsUsedCount(0L)
                    .distinctSessionCount(0L)
                    .distinctSkillsUsedCount(0L)
                    .messageCount(0L)
                    .skillsUsedCount(0L)
                    .build()
            )
        assertThat(analyticsOfficeMetrics.word())
            .isEqualTo(
                AnalyticsOfficeProductMetrics.builder()
                    .connectorsUsedCount(0L)
                    .distinctConnectorsUsedCount(0L)
                    .distinctSessionCount(0L)
                    .distinctSkillsUsedCount(0L)
                    .messageCount(0L)
                    .skillsUsedCount(0L)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsOfficeMetrics =
            AnalyticsOfficeMetrics.builder()
                .excel(
                    AnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .outlook(
                    AnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .powerpoint(
                    AnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .word(
                    AnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .build()

        val roundtrippedAnalyticsOfficeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsOfficeMetrics),
                jacksonTypeRef<AnalyticsOfficeMetrics>(),
            )

        assertThat(roundtrippedAnalyticsOfficeMetrics).isEqualTo(analyticsOfficeMetrics)
    }
}
