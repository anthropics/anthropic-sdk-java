package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillChatCoworkUnifiedMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsSkillChatCoworkUnifiedMetrics =
            BetaAnalyticsSkillChatCoworkUnifiedMetrics.builder()
                .chat(BetaAnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
                .sessions(BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
                .build()

        assertThat(betaAnalyticsSkillChatCoworkUnifiedMetrics.chat())
            .isEqualTo(BetaAnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
        assertThat(betaAnalyticsSkillChatCoworkUnifiedMetrics.sessions())
            .isEqualTo(BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillChatCoworkUnifiedMetrics =
            BetaAnalyticsSkillChatCoworkUnifiedMetrics.builder()
                .chat(BetaAnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
                .sessions(BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
                .build()

        val roundtrippedBetaAnalyticsSkillChatCoworkUnifiedMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillChatCoworkUnifiedMetrics),
                jacksonTypeRef<BetaAnalyticsSkillChatCoworkUnifiedMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillChatCoworkUnifiedMetrics)
            .isEqualTo(betaAnalyticsSkillChatCoworkUnifiedMetrics)
    }
}
