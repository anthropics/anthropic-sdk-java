package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillChatMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsSkillChatMetrics = BetaAnalyticsSkillChatMetrics.of(0L)

        assertThat(betaAnalyticsSkillChatMetrics.distinctConversationSkillUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillChatMetrics = BetaAnalyticsSkillChatMetrics.of(0L)

        val roundtrippedBetaAnalyticsSkillChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillChatMetrics),
                jacksonTypeRef<BetaAnalyticsSkillChatMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillChatMetrics)
            .isEqualTo(betaAnalyticsSkillChatMetrics)
    }
}
