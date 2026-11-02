package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillChatCoworkUnifiedSessionsMetricsTest {

    @Test
    fun create() {
        val analyticsSkillChatCoworkUnifiedSessionsMetrics =
            AnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L)

        assertThat(analyticsSkillChatCoworkUnifiedSessionsMetrics.distinctSessionSkillUsedCount())
            .contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillChatCoworkUnifiedSessionsMetrics =
            AnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L)

        val roundtrippedAnalyticsSkillChatCoworkUnifiedSessionsMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillChatCoworkUnifiedSessionsMetrics),
                jacksonTypeRef<AnalyticsSkillChatCoworkUnifiedSessionsMetrics>(),
            )

        assertThat(roundtrippedAnalyticsSkillChatCoworkUnifiedSessionsMetrics)
            .isEqualTo(analyticsSkillChatCoworkUnifiedSessionsMetrics)
    }
}
