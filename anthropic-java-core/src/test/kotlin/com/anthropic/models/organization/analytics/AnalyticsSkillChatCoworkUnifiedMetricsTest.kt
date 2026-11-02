package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillChatCoworkUnifiedMetricsTest {

    @Test
    fun create() {
        val analyticsSkillChatCoworkUnifiedMetrics =
            AnalyticsSkillChatCoworkUnifiedMetrics.builder()
                .chat(AnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
                .sessions(AnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
                .build()

        assertThat(analyticsSkillChatCoworkUnifiedMetrics.chat())
            .isEqualTo(AnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
        assertThat(analyticsSkillChatCoworkUnifiedMetrics.sessions())
            .isEqualTo(AnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillChatCoworkUnifiedMetrics =
            AnalyticsSkillChatCoworkUnifiedMetrics.builder()
                .chat(AnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
                .sessions(AnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
                .build()

        val roundtrippedAnalyticsSkillChatCoworkUnifiedMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillChatCoworkUnifiedMetrics),
                jacksonTypeRef<AnalyticsSkillChatCoworkUnifiedMetrics>(),
            )

        assertThat(roundtrippedAnalyticsSkillChatCoworkUnifiedMetrics)
            .isEqualTo(analyticsSkillChatCoworkUnifiedMetrics)
    }
}
