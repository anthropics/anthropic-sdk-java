package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorOfficeMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorOfficeMetrics =
            BetaAnalyticsConnectorOfficeMetrics.builder()
                .excel(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                .outlook(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                .powerpoint(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                .word(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                .build()

        assertThat(betaAnalyticsConnectorOfficeMetrics.excel())
            .isEqualTo(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
        assertThat(betaAnalyticsConnectorOfficeMetrics.outlook())
            .isEqualTo(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
        assertThat(betaAnalyticsConnectorOfficeMetrics.powerpoint())
            .isEqualTo(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
        assertThat(betaAnalyticsConnectorOfficeMetrics.word())
            .isEqualTo(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorOfficeMetrics =
            BetaAnalyticsConnectorOfficeMetrics.builder()
                .excel(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                .outlook(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                .powerpoint(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                .word(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                .build()

        val roundtrippedBetaAnalyticsConnectorOfficeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsConnectorOfficeMetrics),
                jacksonTypeRef<BetaAnalyticsConnectorOfficeMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorOfficeMetrics)
            .isEqualTo(betaAnalyticsConnectorOfficeMetrics)
    }
}
