package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsCoreCodeMetricsTest {

    @Test
    fun create() {
        val analyticsCoreCodeMetrics =
            AnalyticsCoreCodeMetrics.builder()
                .artifactsCreatedCount(0L)
                .commitCount(0L)
                .distinctSessionCount(0L)
                .linesOfCode(AnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build())
                .pullRequestCount(0L)
                .build()

        assertThat(analyticsCoreCodeMetrics.artifactsCreatedCount()).isEqualTo(0L)
        assertThat(analyticsCoreCodeMetrics.commitCount()).isEqualTo(0L)
        assertThat(analyticsCoreCodeMetrics.distinctSessionCount()).contains(0L)
        assertThat(analyticsCoreCodeMetrics.linesOfCode())
            .isEqualTo(AnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build())
        assertThat(analyticsCoreCodeMetrics.pullRequestCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsCoreCodeMetrics =
            AnalyticsCoreCodeMetrics.builder()
                .artifactsCreatedCount(0L)
                .commitCount(0L)
                .distinctSessionCount(0L)
                .linesOfCode(AnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build())
                .pullRequestCount(0L)
                .build()

        val roundtrippedAnalyticsCoreCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsCoreCodeMetrics),
                jacksonTypeRef<AnalyticsCoreCodeMetrics>(),
            )

        assertThat(roundtrippedAnalyticsCoreCodeMetrics).isEqualTo(analyticsCoreCodeMetrics)
    }
}
