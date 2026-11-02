package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsPluginCoworkMetricsTest {

    @Test
    fun create() {
        val analyticsPluginCoworkMetrics = AnalyticsPluginCoworkMetrics.of(0L)

        assertThat(analyticsPluginCoworkMetrics.distinctSessionPluginUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsPluginCoworkMetrics = AnalyticsPluginCoworkMetrics.of(0L)

        val roundtrippedAnalyticsPluginCoworkMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsPluginCoworkMetrics),
                jacksonTypeRef<AnalyticsPluginCoworkMetrics>(),
            )

        assertThat(roundtrippedAnalyticsPluginCoworkMetrics).isEqualTo(analyticsPluginCoworkMetrics)
    }
}
