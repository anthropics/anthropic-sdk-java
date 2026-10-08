package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsChatCoworkUnifiedSessionsMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsChatCoworkUnifiedSessionsMetrics.builder()
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

        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.actionCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.artifactsCreatedCount())
            .isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.connectorsUsedCount())
            .isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.dispatchTurnCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.distinctConnectorsUsedCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.distinctPluginsUsedCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.distinctSessionCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.distinctSkillsUsedCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.editToolCount()).contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.fileEditCount()).contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.messageCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.multiEditToolCount()).contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.notebookEditToolCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.pluginsUsedCount()).contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.sessionsWithFileEditsCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.skillsUsedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedSessionsMetrics.writeToolCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsChatCoworkUnifiedSessionsMetrics.builder()
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

        val roundtrippedBetaAnalyticsChatCoworkUnifiedSessionsMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsChatCoworkUnifiedSessionsMetrics),
                jacksonTypeRef<BetaAnalyticsChatCoworkUnifiedSessionsMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsChatCoworkUnifiedSessionsMetrics)
            .isEqualTo(betaAnalyticsChatCoworkUnifiedSessionsMetrics)
    }
}
