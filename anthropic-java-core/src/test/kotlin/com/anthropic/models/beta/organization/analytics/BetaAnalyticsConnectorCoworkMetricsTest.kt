package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorCoworkMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorCoworkMetrics = BetaAnalyticsConnectorCoworkMetrics.of(0L)

        assertThat(betaAnalyticsConnectorCoworkMetrics.distinctSessionConnectorUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorCoworkMetrics = BetaAnalyticsConnectorCoworkMetrics.of(0L)

        val roundtrippedBetaAnalyticsConnectorCoworkMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsConnectorCoworkMetrics),
                jacksonTypeRef<BetaAnalyticsConnectorCoworkMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorCoworkMetrics)
            .isEqualTo(betaAnalyticsConnectorCoworkMetrics)
    }
}
