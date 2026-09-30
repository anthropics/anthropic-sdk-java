package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillOfficeProductMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsSkillOfficeProductMetrics = BetaAnalyticsSkillOfficeProductMetrics.of(0L)

        assertThat(betaAnalyticsSkillOfficeProductMetrics.distinctSessionSkillUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillOfficeProductMetrics = BetaAnalyticsSkillOfficeProductMetrics.of(0L)

        val roundtrippedBetaAnalyticsSkillOfficeProductMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillOfficeProductMetrics),
                jacksonTypeRef<BetaAnalyticsSkillOfficeProductMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillOfficeProductMetrics)
            .isEqualTo(betaAnalyticsSkillOfficeProductMetrics)
    }
}
