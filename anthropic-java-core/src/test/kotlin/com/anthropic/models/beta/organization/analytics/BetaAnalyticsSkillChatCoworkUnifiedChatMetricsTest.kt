package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillChatCoworkUnifiedChatMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsSkillChatCoworkUnifiedChatMetrics =
            BetaAnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L)

        assertThat(
                betaAnalyticsSkillChatCoworkUnifiedChatMetrics.distinctConversationSkillUsedCount()
            )
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillChatCoworkUnifiedChatMetrics =
            BetaAnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L)

        val roundtrippedBetaAnalyticsSkillChatCoworkUnifiedChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillChatCoworkUnifiedChatMetrics),
                jacksonTypeRef<BetaAnalyticsSkillChatCoworkUnifiedChatMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillChatCoworkUnifiedChatMetrics)
            .isEqualTo(betaAnalyticsSkillChatCoworkUnifiedChatMetrics)
    }
}
