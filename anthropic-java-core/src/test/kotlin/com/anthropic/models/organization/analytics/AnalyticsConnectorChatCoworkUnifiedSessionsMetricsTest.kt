package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorChatCoworkUnifiedSessionsMetricsTest {

    @Test
    fun create() {
        val analyticsConnectorChatCoworkUnifiedSessionsMetrics =
            AnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L)

        assertThat(
                analyticsConnectorChatCoworkUnifiedSessionsMetrics
                    .distinctSessionConnectorUsedCount()
            )
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorChatCoworkUnifiedSessionsMetrics =
            AnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L)

        val roundtrippedAnalyticsConnectorChatCoworkUnifiedSessionsMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorChatCoworkUnifiedSessionsMetrics),
                jacksonTypeRef<AnalyticsConnectorChatCoworkUnifiedSessionsMetrics>(),
            )

        assertThat(roundtrippedAnalyticsConnectorChatCoworkUnifiedSessionsMetrics)
            .isEqualTo(analyticsConnectorChatCoworkUnifiedSessionsMetrics)
    }
}
