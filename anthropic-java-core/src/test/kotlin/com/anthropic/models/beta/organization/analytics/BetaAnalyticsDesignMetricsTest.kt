package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsDesignMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsDesignMetrics =
            BetaAnalyticsDesignMetrics.builder()
                .distinctProjectsCreatedCount(0L)
                .distinctProjectsUsedCount(0L)
                .distinctSessionCount(0L)
                .messageCount(0L)
                .build()

        assertThat(betaAnalyticsDesignMetrics.distinctProjectsCreatedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsDesignMetrics.distinctProjectsUsedCount()).contains(0L)
        assertThat(betaAnalyticsDesignMetrics.distinctSessionCount()).contains(0L)
        assertThat(betaAnalyticsDesignMetrics.messageCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsDesignMetrics =
            BetaAnalyticsDesignMetrics.builder()
                .distinctProjectsCreatedCount(0L)
                .distinctProjectsUsedCount(0L)
                .distinctSessionCount(0L)
                .messageCount(0L)
                .build()

        val roundtrippedBetaAnalyticsDesignMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsDesignMetrics),
                jacksonTypeRef<BetaAnalyticsDesignMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsDesignMetrics).isEqualTo(betaAnalyticsDesignMetrics)
    }
}
