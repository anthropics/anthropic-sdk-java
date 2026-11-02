package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorOfficeProductMetricsTest {

    @Test
    fun create() {
        val analyticsConnectorOfficeProductMetrics = AnalyticsConnectorOfficeProductMetrics.of(0L)

        assertThat(analyticsConnectorOfficeProductMetrics.distinctSessionConnectorUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorOfficeProductMetrics = AnalyticsConnectorOfficeProductMetrics.of(0L)

        val roundtrippedAnalyticsConnectorOfficeProductMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorOfficeProductMetrics),
                jacksonTypeRef<AnalyticsConnectorOfficeProductMetrics>(),
            )

        assertThat(roundtrippedAnalyticsConnectorOfficeProductMetrics)
            .isEqualTo(analyticsConnectorOfficeProductMetrics)
    }
}
