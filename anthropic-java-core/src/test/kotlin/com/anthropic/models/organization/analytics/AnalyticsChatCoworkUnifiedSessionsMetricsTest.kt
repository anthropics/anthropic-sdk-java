package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsChatCoworkUnifiedSessionsMetricsTest {

    @Test
    fun create() {
        val analyticsChatCoworkUnifiedSessionsMetrics =
            AnalyticsChatCoworkUnifiedSessionsMetrics.builder()
                .actionCount(0L)
                .artifactsCreatedCount(0L)
                .connectorsUsedCount(0L)
                .dispatchTurnCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctPluginsUsedCount(0L)
                .distinctSessionCount(0L)
                .distinctSkillsUsedCount(0L)
                .editToolCount(0L)
                .fileEditCount(0L)
                .messageCount(0L)
                .multiEditToolCount(0L)
                .notebookEditToolCount(0L)
                .pluginsUsedCount(0L)
                .sessionsWithFileEditsCount(0L)
                .skillsUsedCount(0L)
                .writeToolCount(0L)
                .build()

        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.actionCount()).isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.artifactsCreatedCount()).isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.dispatchTurnCount()).isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.distinctConnectorsUsedCount())
            .contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.distinctPluginsUsedCount())
            .contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.distinctSessionCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.editToolCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.fileEditCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.messageCount()).isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.multiEditToolCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.notebookEditToolCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.pluginsUsedCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.sessionsWithFileEditsCount())
            .contains(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.skillsUsedCount()).isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedSessionsMetrics.writeToolCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsChatCoworkUnifiedSessionsMetrics =
            AnalyticsChatCoworkUnifiedSessionsMetrics.builder()
                .actionCount(0L)
                .artifactsCreatedCount(0L)
                .connectorsUsedCount(0L)
                .dispatchTurnCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctPluginsUsedCount(0L)
                .distinctSessionCount(0L)
                .distinctSkillsUsedCount(0L)
                .editToolCount(0L)
                .fileEditCount(0L)
                .messageCount(0L)
                .multiEditToolCount(0L)
                .notebookEditToolCount(0L)
                .pluginsUsedCount(0L)
                .sessionsWithFileEditsCount(0L)
                .skillsUsedCount(0L)
                .writeToolCount(0L)
                .build()

        val roundtrippedAnalyticsChatCoworkUnifiedSessionsMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsChatCoworkUnifiedSessionsMetrics),
                jacksonTypeRef<AnalyticsChatCoworkUnifiedSessionsMetrics>(),
            )

        assertThat(roundtrippedAnalyticsChatCoworkUnifiedSessionsMetrics)
            .isEqualTo(analyticsChatCoworkUnifiedSessionsMetrics)
    }
}
