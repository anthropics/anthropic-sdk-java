package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillChatCoworkUnifiedChatMetricsTest {

    @Test
    fun create() {
        val analyticsSkillChatCoworkUnifiedChatMetrics =
            AnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L)

        assertThat(analyticsSkillChatCoworkUnifiedChatMetrics.distinctConversationSkillUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillChatCoworkUnifiedChatMetrics =
            AnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L)

        val roundtrippedAnalyticsSkillChatCoworkUnifiedChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillChatCoworkUnifiedChatMetrics),
                jacksonTypeRef<AnalyticsSkillChatCoworkUnifiedChatMetrics>(),
            )

        assertThat(roundtrippedAnalyticsSkillChatCoworkUnifiedChatMetrics)
            .isEqualTo(analyticsSkillChatCoworkUnifiedChatMetrics)
    }
}
