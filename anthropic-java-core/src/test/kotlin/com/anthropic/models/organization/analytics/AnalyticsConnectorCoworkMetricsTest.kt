package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorCoworkMetricsTest {

    @Test
    fun create() {
        val analyticsConnectorCoworkMetrics = AnalyticsConnectorCoworkMetrics.of(0L)

        assertThat(analyticsConnectorCoworkMetrics.distinctSessionConnectorUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorCoworkMetrics = AnalyticsConnectorCoworkMetrics.of(0L)

        val roundtrippedAnalyticsConnectorCoworkMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorCoworkMetrics),
                jacksonTypeRef<AnalyticsConnectorCoworkMetrics>(),
            )

        assertThat(roundtrippedAnalyticsConnectorCoworkMetrics)
            .isEqualTo(analyticsConnectorCoworkMetrics)
    }
}
