package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillChatCoworkUnifiedSessionsMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsSkillChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L)

        assertThat(
                betaAnalyticsSkillChatCoworkUnifiedSessionsMetrics.distinctSessionSkillUsedCount()
            )
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L)

        val roundtrippedBetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillChatCoworkUnifiedSessionsMetrics),
                jacksonTypeRef<BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics)
            .isEqualTo(betaAnalyticsSkillChatCoworkUnifiedSessionsMetrics)
    }
}
