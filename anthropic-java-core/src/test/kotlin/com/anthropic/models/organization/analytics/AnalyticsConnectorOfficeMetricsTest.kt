package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorOfficeMetricsTest {

    @Test
    fun create() {
        val analyticsConnectorOfficeMetrics =
            AnalyticsConnectorOfficeMetrics.builder()
                .excel(AnalyticsConnectorOfficeProductMetrics.of(0L))
                .outlook(AnalyticsConnectorOfficeProductMetrics.of(0L))
                .powerpoint(AnalyticsConnectorOfficeProductMetrics.of(0L))
                .word(AnalyticsConnectorOfficeProductMetrics.of(0L))
                .build()

        assertThat(analyticsConnectorOfficeMetrics.excel())
            .isEqualTo(AnalyticsConnectorOfficeProductMetrics.of(0L))
        assertThat(analyticsConnectorOfficeMetrics.outlook())
            .isEqualTo(AnalyticsConnectorOfficeProductMetrics.of(0L))
        assertThat(analyticsConnectorOfficeMetrics.powerpoint())
            .isEqualTo(AnalyticsConnectorOfficeProductMetrics.of(0L))
        assertThat(analyticsConnectorOfficeMetrics.word())
            .isEqualTo(AnalyticsConnectorOfficeProductMetrics.of(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorOfficeMetrics =
            AnalyticsConnectorOfficeMetrics.builder()
                .excel(AnalyticsConnectorOfficeProductMetrics.of(0L))
                .outlook(AnalyticsConnectorOfficeProductMetrics.of(0L))
                .powerpoint(AnalyticsConnectorOfficeProductMetrics.of(0L))
                .word(AnalyticsConnectorOfficeProductMetrics.of(0L))
                .build()

        val roundtrippedAnalyticsConnectorOfficeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorOfficeMetrics),
                jacksonTypeRef<AnalyticsConnectorOfficeMetrics>(),
            )

        assertThat(roundtrippedAnalyticsConnectorOfficeMetrics)
            .isEqualTo(analyticsConnectorOfficeMetrics)
    }
}
