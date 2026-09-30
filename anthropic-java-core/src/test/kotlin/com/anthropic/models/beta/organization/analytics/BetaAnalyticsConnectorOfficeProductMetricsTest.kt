package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorOfficeProductMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorOfficeProductMetrics =
            BetaAnalyticsConnectorOfficeProductMetrics.of(0L)

        assertThat(betaAnalyticsConnectorOfficeProductMetrics.distinctSessionConnectorUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorOfficeProductMetrics =
            BetaAnalyticsConnectorOfficeProductMetrics.of(0L)

        val roundtrippedBetaAnalyticsConnectorOfficeProductMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsConnectorOfficeProductMetrics),
                jacksonTypeRef<BetaAnalyticsConnectorOfficeProductMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorOfficeProductMetrics)
            .isEqualTo(betaAnalyticsConnectorOfficeProductMetrics)
    }
}
