package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorChatCoworkUnifiedChatMetricsTest {

    @Test
    fun create() {
        val analyticsConnectorChatCoworkUnifiedChatMetrics =
            AnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L)

        assertThat(
                analyticsConnectorChatCoworkUnifiedChatMetrics
                    .distinctConversationConnectorUsedCount()
            )
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorChatCoworkUnifiedChatMetrics =
            AnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L)

        val roundtrippedAnalyticsConnectorChatCoworkUnifiedChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorChatCoworkUnifiedChatMetrics),
                jacksonTypeRef<AnalyticsConnectorChatCoworkUnifiedChatMetrics>(),
            )

        assertThat(roundtrippedAnalyticsConnectorChatCoworkUnifiedChatMetrics)
            .isEqualTo(analyticsConnectorChatCoworkUnifiedChatMetrics)
    }
}
