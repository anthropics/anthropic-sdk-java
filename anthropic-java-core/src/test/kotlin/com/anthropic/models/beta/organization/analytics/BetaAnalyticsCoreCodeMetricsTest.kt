package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsCoreCodeMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsCoreCodeMetrics =
            BetaAnalyticsCoreCodeMetrics.builder()
                .artifactsCreatedCount(0L)
                .commitCount(0L)
                .distinctSessionCount(0L)
                .linesOfCode(
                    BetaAnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()
                )
                .pullRequestCount(0L)
                .build()

        assertThat(betaAnalyticsCoreCodeMetrics.artifactsCreatedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsCoreCodeMetrics.commitCount()).isEqualTo(0L)
        assertThat(betaAnalyticsCoreCodeMetrics.distinctSessionCount()).contains(0L)
        assertThat(betaAnalyticsCoreCodeMetrics.linesOfCode())
            .isEqualTo(BetaAnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build())
        assertThat(betaAnalyticsCoreCodeMetrics.pullRequestCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsCoreCodeMetrics =
            BetaAnalyticsCoreCodeMetrics.builder()
                .artifactsCreatedCount(0L)
                .commitCount(0L)
                .distinctSessionCount(0L)
                .linesOfCode(
                    BetaAnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()
                )
                .pullRequestCount(0L)
                .build()

        val roundtrippedBetaAnalyticsCoreCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsCoreCodeMetrics),
                jacksonTypeRef<BetaAnalyticsCoreCodeMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsCoreCodeMetrics).isEqualTo(betaAnalyticsCoreCodeMetrics)
    }
}
