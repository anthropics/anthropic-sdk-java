package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorChatMetricsTest {

    @Test
    fun create() {
        val analyticsConnectorChatMetrics = AnalyticsConnectorChatMetrics.of(0L)

        assertThat(analyticsConnectorChatMetrics.distinctConversationConnectorUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorChatMetrics = AnalyticsConnectorChatMetrics.of(0L)

        val roundtrippedAnalyticsConnectorChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorChatMetrics),
                jacksonTypeRef<AnalyticsConnectorChatMetrics>(),
            )

        assertThat(roundtrippedAnalyticsConnectorChatMetrics)
            .isEqualTo(analyticsConnectorChatMetrics)
    }
}
