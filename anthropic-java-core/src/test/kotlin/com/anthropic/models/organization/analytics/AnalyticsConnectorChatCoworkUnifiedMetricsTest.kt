package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorChatCoworkUnifiedMetricsTest {

    @Test
    fun create() {
        val analyticsConnectorChatCoworkUnifiedMetrics =
            AnalyticsConnectorChatCoworkUnifiedMetrics.builder()
                .chat(AnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
                .sessions(AnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
                .build()

        assertThat(analyticsConnectorChatCoworkUnifiedMetrics.chat())
            .isEqualTo(AnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
        assertThat(analyticsConnectorChatCoworkUnifiedMetrics.sessions())
            .isEqualTo(AnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorChatCoworkUnifiedMetrics =
            AnalyticsConnectorChatCoworkUnifiedMetrics.builder()
                .chat(AnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
                .sessions(AnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
                .build()

        val roundtrippedAnalyticsConnectorChatCoworkUnifiedMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorChatCoworkUnifiedMetrics),
                jacksonTypeRef<AnalyticsConnectorChatCoworkUnifiedMetrics>(),
            )

        assertThat(roundtrippedAnalyticsConnectorChatCoworkUnifiedMetrics)
            .isEqualTo(analyticsConnectorChatCoworkUnifiedMetrics)
    }
}
