package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsCoworkMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsCoworkMetrics =
            BetaAnalyticsCoworkMetrics.builder()
                .actionCount(0L)
                .artifactsCreatedCount(0L)
                .connectorsUsedCount(0L)
                .dispatchTurnCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctSessionCount(0L)
                .distinctSkillsUsedCount(0L)
                .messageCount(0L)
                .skillsUsedCount(0L)
                .distinctPluginsUsedCount(0L)
                .editToolCount(0L)
                .fileEditCount(0L)
                .multiEditToolCount(0L)
                .notebookEditToolCount(0L)
                .pluginsUsedCount(0L)
                .sessionsWithFileEditsCount(0L)
                .writeToolCount(0L)
                .build()

        assertThat(betaAnalyticsCoworkMetrics.actionCount()).isEqualTo(0L)
        assertThat(betaAnalyticsCoworkMetrics.artifactsCreatedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsCoworkMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsCoworkMetrics.dispatchTurnCount()).isEqualTo(0L)
        assertThat(betaAnalyticsCoworkMetrics.distinctConnectorsUsedCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.distinctSessionCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.messageCount()).isEqualTo(0L)
        assertThat(betaAnalyticsCoworkMetrics.skillsUsedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsCoworkMetrics.distinctPluginsUsedCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.editToolCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.fileEditCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.multiEditToolCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.notebookEditToolCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.pluginsUsedCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.sessionsWithFileEditsCount()).contains(0L)
        assertThat(betaAnalyticsCoworkMetrics.writeToolCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsCoworkMetrics =
            BetaAnalyticsCoworkMetrics.builder()
                .actionCount(0L)
                .artifactsCreatedCount(0L)
                .connectorsUsedCount(0L)
                .dispatchTurnCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctSessionCount(0L)
                .distinctSkillsUsedCount(0L)
                .messageCount(0L)
                .skillsUsedCount(0L)
                .distinctPluginsUsedCount(0L)
                .editToolCount(0L)
                .fileEditCount(0L)
                .multiEditToolCount(0L)
                .notebookEditToolCount(0L)
                .pluginsUsedCount(0L)
                .sessionsWithFileEditsCount(0L)
                .writeToolCount(0L)
                .build()

        val roundtrippedBetaAnalyticsCoworkMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsCoworkMetrics),
                jacksonTypeRef<BetaAnalyticsCoworkMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsCoworkMetrics).isEqualTo(betaAnalyticsCoworkMetrics)
    }
}
