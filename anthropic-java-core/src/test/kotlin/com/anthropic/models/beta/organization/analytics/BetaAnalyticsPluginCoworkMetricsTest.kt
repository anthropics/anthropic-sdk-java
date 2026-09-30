package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsPluginCoworkMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsPluginCoworkMetrics = BetaAnalyticsPluginCoworkMetrics.of(0L)

        assertThat(betaAnalyticsPluginCoworkMetrics.distinctSessionPluginUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsPluginCoworkMetrics = BetaAnalyticsPluginCoworkMetrics.of(0L)

        val roundtrippedBetaAnalyticsPluginCoworkMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsPluginCoworkMetrics),
                jacksonTypeRef<BetaAnalyticsPluginCoworkMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsPluginCoworkMetrics)
            .isEqualTo(betaAnalyticsPluginCoworkMetrics)
    }
}
