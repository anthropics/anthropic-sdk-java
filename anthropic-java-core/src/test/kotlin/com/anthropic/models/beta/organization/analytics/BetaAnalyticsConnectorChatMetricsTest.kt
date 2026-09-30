package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorChatMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorChatMetrics = BetaAnalyticsConnectorChatMetrics.of(0L)

        assertThat(betaAnalyticsConnectorChatMetrics.distinctConversationConnectorUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorChatMetrics = BetaAnalyticsConnectorChatMetrics.of(0L)

        val roundtrippedBetaAnalyticsConnectorChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsConnectorChatMetrics),
                jacksonTypeRef<BetaAnalyticsConnectorChatMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorChatMetrics)
            .isEqualTo(betaAnalyticsConnectorChatMetrics)
    }
}
