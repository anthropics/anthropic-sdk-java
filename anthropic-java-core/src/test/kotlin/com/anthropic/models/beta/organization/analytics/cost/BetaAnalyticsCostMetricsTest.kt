package com.anthropic.models.beta.organization.analytics.cost

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsCostMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsCostMetrics =
            BetaAnalyticsCostMetrics.builder()
                .amount("41280.000000")
                .currency("USD")
                .listAmount("51600.000000")
                .requests(128L)
                .build()

        assertThat(betaAnalyticsCostMetrics.amount()).isEqualTo("41280.000000")
        assertThat(betaAnalyticsCostMetrics.currency()).isEqualTo("USD")
        assertThat(betaAnalyticsCostMetrics.listAmount()).isEqualTo("51600.000000")
        assertThat(betaAnalyticsCostMetrics.requests()).contains(128L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsCostMetrics =
            BetaAnalyticsCostMetrics.builder()
                .amount("41280.000000")
                .currency("USD")
                .listAmount("51600.000000")
                .requests(128L)
                .build()

        val roundtrippedBetaAnalyticsCostMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsCostMetrics),
                jacksonTypeRef<BetaAnalyticsCostMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsCostMetrics).isEqualTo(betaAnalyticsCostMetrics)
    }
}
