package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L)

        assertThat(
                betaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics
                    .distinctSessionConnectorUsedCount()
            )
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L)

        val roundtrippedBetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics
                ),
                jacksonTypeRef<BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics)
            .isEqualTo(betaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics)
    }
}
