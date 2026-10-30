package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsDesignMetricsTest {

    @Test
    fun create() {
        val analyticsDesignMetrics =
            AnalyticsDesignMetrics.builder()
                .distinctProjectsCreatedCount(0L)
                .distinctProjectsUsedCount(0L)
                .distinctSessionCount(0L)
                .messageCount(0L)
                .build()

        assertThat(analyticsDesignMetrics.distinctProjectsCreatedCount()).isEqualTo(0L)
        assertThat(analyticsDesignMetrics.distinctProjectsUsedCount()).contains(0L)
        assertThat(analyticsDesignMetrics.distinctSessionCount()).contains(0L)
        assertThat(analyticsDesignMetrics.messageCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsDesignMetrics =
            AnalyticsDesignMetrics.builder()
                .distinctProjectsCreatedCount(0L)
                .distinctProjectsUsedCount(0L)
                .distinctSessionCount(0L)
                .messageCount(0L)
                .build()

        val roundtrippedAnalyticsDesignMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsDesignMetrics),
                jacksonTypeRef<AnalyticsDesignMetrics>(),
            )

        assertThat(roundtrippedAnalyticsDesignMetrics).isEqualTo(analyticsDesignMetrics)
    }
}
