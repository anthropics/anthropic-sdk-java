package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorChatCoworkUnifiedChatMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorChatCoworkUnifiedChatMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L)

        assertThat(
                betaAnalyticsConnectorChatCoworkUnifiedChatMetrics
                    .distinctConversationConnectorUsedCount()
            )
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorChatCoworkUnifiedChatMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L)

        val roundtrippedBetaAnalyticsConnectorChatCoworkUnifiedChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsConnectorChatCoworkUnifiedChatMetrics),
                jacksonTypeRef<BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorChatCoworkUnifiedChatMetrics)
            .isEqualTo(betaAnalyticsConnectorChatCoworkUnifiedChatMetrics)
    }
}
