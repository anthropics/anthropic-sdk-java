package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorChatCoworkUnifiedMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorChatCoworkUnifiedMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedMetrics.builder()
                .chat(BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
                .sessions(BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
                .build()

        assertThat(betaAnalyticsConnectorChatCoworkUnifiedMetrics.chat())
            .isEqualTo(BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
        assertThat(betaAnalyticsConnectorChatCoworkUnifiedMetrics.sessions())
            .isEqualTo(BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorChatCoworkUnifiedMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedMetrics.builder()
                .chat(BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
                .sessions(BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
                .build()

        val roundtrippedBetaAnalyticsConnectorChatCoworkUnifiedMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsConnectorChatCoworkUnifiedMetrics),
                jacksonTypeRef<BetaAnalyticsConnectorChatCoworkUnifiedMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorChatCoworkUnifiedMetrics)
            .isEqualTo(betaAnalyticsConnectorChatCoworkUnifiedMetrics)
    }
}
