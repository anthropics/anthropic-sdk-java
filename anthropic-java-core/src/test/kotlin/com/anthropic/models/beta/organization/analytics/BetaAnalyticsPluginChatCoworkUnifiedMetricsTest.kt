package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsPluginChatCoworkUnifiedMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsPluginChatCoworkUnifiedMetrics =
            BetaAnalyticsPluginChatCoworkUnifiedMetrics.of(0L)

        assertThat(betaAnalyticsPluginChatCoworkUnifiedMetrics.distinctSessionPluginUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsPluginChatCoworkUnifiedMetrics =
            BetaAnalyticsPluginChatCoworkUnifiedMetrics.of(0L)

        val roundtrippedBetaAnalyticsPluginChatCoworkUnifiedMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsPluginChatCoworkUnifiedMetrics),
                jacksonTypeRef<BetaAnalyticsPluginChatCoworkUnifiedMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsPluginChatCoworkUnifiedMetrics)
            .isEqualTo(betaAnalyticsPluginChatCoworkUnifiedMetrics)
    }
}
