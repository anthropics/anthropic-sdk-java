package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsClaudeTagMetricsTest {

    @Test
    fun create() {
        val analyticsClaudeTagMetrics =
            AnalyticsClaudeTagMetrics.builder().activeDayCount(0L).activeMessageCount(0L).build()

        assertThat(analyticsClaudeTagMetrics.activeDayCount()).contains(0L)
        assertThat(analyticsClaudeTagMetrics.activeMessageCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsClaudeTagMetrics =
            AnalyticsClaudeTagMetrics.builder().activeDayCount(0L).activeMessageCount(0L).build()

        val roundtrippedAnalyticsClaudeTagMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsClaudeTagMetrics),
                jacksonTypeRef<AnalyticsClaudeTagMetrics>(),
            )

        assertThat(roundtrippedAnalyticsClaudeTagMetrics).isEqualTo(analyticsClaudeTagMetrics)
    }
}
