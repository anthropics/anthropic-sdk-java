package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsCoworkMetricsTest {

    @Test
    fun create() {
        val analyticsCoworkMetrics =
            AnalyticsCoworkMetrics.builder()
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

        assertThat(analyticsCoworkMetrics.actionCount()).isEqualTo(0L)
        assertThat(analyticsCoworkMetrics.artifactsCreatedCount()).isEqualTo(0L)
        assertThat(analyticsCoworkMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(analyticsCoworkMetrics.dispatchTurnCount()).isEqualTo(0L)
        assertThat(analyticsCoworkMetrics.distinctConnectorsUsedCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.distinctPluginsUsedCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.distinctSessionCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.editToolCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.fileEditCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.messageCount()).isEqualTo(0L)
        assertThat(analyticsCoworkMetrics.multiEditToolCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.notebookEditToolCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.pluginsUsedCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.sessionsWithFileEditsCount()).contains(0L)
        assertThat(analyticsCoworkMetrics.skillsUsedCount()).isEqualTo(0L)
        assertThat(analyticsCoworkMetrics.writeToolCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsCoworkMetrics =
            AnalyticsCoworkMetrics.builder()
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

        val roundtrippedAnalyticsCoworkMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsCoworkMetrics),
                jacksonTypeRef<AnalyticsCoworkMetrics>(),
            )

        assertThat(roundtrippedAnalyticsCoworkMetrics).isEqualTo(analyticsCoworkMetrics)
    }
}
