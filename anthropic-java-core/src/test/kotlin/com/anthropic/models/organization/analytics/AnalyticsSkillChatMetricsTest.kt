package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillChatMetricsTest {

    @Test
    fun create() {
        val analyticsSkillChatMetrics = AnalyticsSkillChatMetrics.of(0L)

        assertThat(analyticsSkillChatMetrics.distinctConversationSkillUsedCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillChatMetrics = AnalyticsSkillChatMetrics.of(0L)

        val roundtrippedAnalyticsSkillChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillChatMetrics),
                jacksonTypeRef<AnalyticsSkillChatMetrics>(),
            )

        assertThat(roundtrippedAnalyticsSkillChatMetrics).isEqualTo(analyticsSkillChatMetrics)
    }
}
