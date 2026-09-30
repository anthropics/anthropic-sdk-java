package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsOfficeMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsOfficeMetrics =
            BetaAnalyticsOfficeMetrics.builder()
                .excel(
                    BetaAnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .outlook(
                    BetaAnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .powerpoint(
                    BetaAnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .word(
                    BetaAnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .build()

        assertThat(betaAnalyticsOfficeMetrics.excel())
            .isEqualTo(
                BetaAnalyticsOfficeProductMetrics.builder()
                    .connectorsUsedCount(0L)
                    .distinctConnectorsUsedCount(0L)
                    .distinctSessionCount(0L)
                    .distinctSkillsUsedCount(0L)
                    .messageCount(0L)
                    .skillsUsedCount(0L)
                    .build()
            )
        assertThat(betaAnalyticsOfficeMetrics.outlook())
            .isEqualTo(
                BetaAnalyticsOfficeProductMetrics.builder()
                    .connectorsUsedCount(0L)
                    .distinctConnectorsUsedCount(0L)
                    .distinctSessionCount(0L)
                    .distinctSkillsUsedCount(0L)
                    .messageCount(0L)
                    .skillsUsedCount(0L)
                    .build()
            )
        assertThat(betaAnalyticsOfficeMetrics.powerpoint())
            .isEqualTo(
                BetaAnalyticsOfficeProductMetrics.builder()
                    .connectorsUsedCount(0L)
                    .distinctConnectorsUsedCount(0L)
                    .distinctSessionCount(0L)
                    .distinctSkillsUsedCount(0L)
                    .messageCount(0L)
                    .skillsUsedCount(0L)
                    .build()
            )
        assertThat(betaAnalyticsOfficeMetrics.word())
            .isEqualTo(
                BetaAnalyticsOfficeProductMetrics.builder()
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
        val betaAnalyticsOfficeMetrics =
            BetaAnalyticsOfficeMetrics.builder()
                .excel(
                    BetaAnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .outlook(
                    BetaAnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .powerpoint(
                    BetaAnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .word(
                    BetaAnalyticsOfficeProductMetrics.builder()
                        .connectorsUsedCount(0L)
                        .distinctConnectorsUsedCount(0L)
                        .distinctSessionCount(0L)
                        .distinctSkillsUsedCount(0L)
                        .messageCount(0L)
                        .skillsUsedCount(0L)
                        .build()
                )
                .build()

        val roundtrippedBetaAnalyticsOfficeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsOfficeMetrics),
                jacksonTypeRef<BetaAnalyticsOfficeMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsOfficeMetrics).isEqualTo(betaAnalyticsOfficeMetrics)
    }
}
