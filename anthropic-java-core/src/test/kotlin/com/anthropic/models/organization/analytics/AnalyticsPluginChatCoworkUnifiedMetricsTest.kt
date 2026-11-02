package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsPluginChatCoworkUnifiedMetricsTest {

    @Test
    fun create() {
        val analyticsPluginChatCoworkUnifiedMetrics = AnalyticsPluginChatCoworkUnifiedMetrics.of(0L)

        assertThat(analyticsPluginChatCoworkUnifiedMetrics.distinctSessionPluginUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsPluginChatCoworkUnifiedMetrics = AnalyticsPluginChatCoworkUnifiedMetrics.of(0L)

        val roundtrippedAnalyticsPluginChatCoworkUnifiedMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsPluginChatCoworkUnifiedMetrics),
                jacksonTypeRef<AnalyticsPluginChatCoworkUnifiedMetrics>(),
            )

        assertThat(roundtrippedAnalyticsPluginChatCoworkUnifiedMetrics)
            .isEqualTo(analyticsPluginChatCoworkUnifiedMetrics)
    }
}
