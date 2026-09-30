package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsOfficeProductMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsOfficeProductMetrics =
            BetaAnalyticsOfficeProductMetrics.builder()
                .connectorsUsedCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctSessionCount(0L)
                .distinctSkillsUsedCount(0L)
                .messageCount(0L)
                .skillsUsedCount(0L)
                .build()

        assertThat(betaAnalyticsOfficeProductMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsOfficeProductMetrics.distinctConnectorsUsedCount()).contains(0L)
        assertThat(betaAnalyticsOfficeProductMetrics.distinctSessionCount()).contains(0L)
        assertThat(betaAnalyticsOfficeProductMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(betaAnalyticsOfficeProductMetrics.messageCount()).isEqualTo(0L)
        assertThat(betaAnalyticsOfficeProductMetrics.skillsUsedCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsOfficeProductMetrics =
            BetaAnalyticsOfficeProductMetrics.builder()
                .connectorsUsedCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctSessionCount(0L)
                .distinctSkillsUsedCount(0L)
                .messageCount(0L)
                .skillsUsedCount(0L)
                .build()

        val roundtrippedBetaAnalyticsOfficeProductMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsOfficeProductMetrics),
                jacksonTypeRef<BetaAnalyticsOfficeProductMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsOfficeProductMetrics)
            .isEqualTo(betaAnalyticsOfficeProductMetrics)
    }
}
