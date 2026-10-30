package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsScienceMetricsTest {

    @Test
    fun create() {
        val analyticsScienceMetrics =
            AnalyticsScienceMetrics.builder()
                .delegationCount(0L)
                .distinctSessionCount(0L)
                .messageCount(0L)
                .remoteComputeJobCount(0L)
                .skillsUsedCount(0L)
                .build()

        assertThat(analyticsScienceMetrics.delegationCount()).isEqualTo(0L)
        assertThat(analyticsScienceMetrics.distinctSessionCount()).contains(0L)
        assertThat(analyticsScienceMetrics.messageCount()).isEqualTo(0L)
        assertThat(analyticsScienceMetrics.remoteComputeJobCount()).isEqualTo(0L)
        assertThat(analyticsScienceMetrics.skillsUsedCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsScienceMetrics =
            AnalyticsScienceMetrics.builder()
                .delegationCount(0L)
                .distinctSessionCount(0L)
                .messageCount(0L)
                .remoteComputeJobCount(0L)
                .skillsUsedCount(0L)
                .build()

        val roundtrippedAnalyticsScienceMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsScienceMetrics),
                jacksonTypeRef<AnalyticsScienceMetrics>(),
            )

        assertThat(roundtrippedAnalyticsScienceMetrics).isEqualTo(analyticsScienceMetrics)
    }
}
