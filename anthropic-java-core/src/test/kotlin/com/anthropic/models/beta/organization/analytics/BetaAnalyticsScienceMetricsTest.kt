package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsScienceMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsScienceMetrics =
            BetaAnalyticsScienceMetrics.builder()
                .delegationCount(0L)
                .distinctSessionCount(0L)
                .messageCount(0L)
                .remoteComputeJobCount(0L)
                .skillsUsedCount(0L)
                .build()

        assertThat(betaAnalyticsScienceMetrics.delegationCount()).isEqualTo(0L)
        assertThat(betaAnalyticsScienceMetrics.distinctSessionCount()).contains(0L)
        assertThat(betaAnalyticsScienceMetrics.messageCount()).isEqualTo(0L)
        assertThat(betaAnalyticsScienceMetrics.remoteComputeJobCount()).isEqualTo(0L)
        assertThat(betaAnalyticsScienceMetrics.skillsUsedCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsScienceMetrics =
            BetaAnalyticsScienceMetrics.builder()
                .delegationCount(0L)
                .distinctSessionCount(0L)
                .messageCount(0L)
                .remoteComputeJobCount(0L)
                .skillsUsedCount(0L)
                .build()

        val roundtrippedBetaAnalyticsScienceMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsScienceMetrics),
                jacksonTypeRef<BetaAnalyticsScienceMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsScienceMetrics).isEqualTo(betaAnalyticsScienceMetrics)
    }
}
