package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillOfficeMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsSkillOfficeMetrics =
            BetaAnalyticsSkillOfficeMetrics.builder()
                .excel(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                .outlook(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                .powerpoint(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                .word(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                .build()

        assertThat(betaAnalyticsSkillOfficeMetrics.excel())
            .isEqualTo(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
        assertThat(betaAnalyticsSkillOfficeMetrics.outlook())
            .isEqualTo(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
        assertThat(betaAnalyticsSkillOfficeMetrics.powerpoint())
            .isEqualTo(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
        assertThat(betaAnalyticsSkillOfficeMetrics.word())
            .isEqualTo(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillOfficeMetrics =
            BetaAnalyticsSkillOfficeMetrics.builder()
                .excel(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                .outlook(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                .powerpoint(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                .word(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                .build()

        val roundtrippedBetaAnalyticsSkillOfficeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillOfficeMetrics),
                jacksonTypeRef<BetaAnalyticsSkillOfficeMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillOfficeMetrics)
            .isEqualTo(betaAnalyticsSkillOfficeMetrics)
    }
}
