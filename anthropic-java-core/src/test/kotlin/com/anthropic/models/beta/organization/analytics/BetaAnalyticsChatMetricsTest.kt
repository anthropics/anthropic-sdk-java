package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsChatMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsChatMetrics =
            BetaAnalyticsChatMetrics.builder()
                .connectorsUsedCount(0L)
                .distinctArtifactsCreatedCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctConversationCount(0L)
                .distinctFilesUploadedCount(0L)
                .distinctProjectsCreatedCount(0L)
                .distinctProjectsUsedCount(0L)
                .distinctSharedArtifactsViewedCount(0L)
                .distinctSkillsUsedCount(0L)
                .messageCount(0L)
                .sharedConversationsViewedCount(0L)
                .thinkingMessageCount(0L)
                .build()

        assertThat(betaAnalyticsChatMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatMetrics.distinctArtifactsCreatedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatMetrics.distinctConnectorsUsedCount()).contains(0L)
        assertThat(betaAnalyticsChatMetrics.distinctConversationCount()).contains(0L)
        assertThat(betaAnalyticsChatMetrics.distinctFilesUploadedCount()).contains(0L)
        assertThat(betaAnalyticsChatMetrics.distinctProjectsCreatedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatMetrics.distinctProjectsUsedCount()).contains(0L)
        assertThat(betaAnalyticsChatMetrics.distinctSharedArtifactsViewedCount()).contains(0L)
        assertThat(betaAnalyticsChatMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(betaAnalyticsChatMetrics.messageCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatMetrics.sharedConversationsViewedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatMetrics.thinkingMessageCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsChatMetrics =
            BetaAnalyticsChatMetrics.builder()
                .connectorsUsedCount(0L)
                .distinctArtifactsCreatedCount(0L)
                .distinctConnectorsUsedCount(0L)
                .distinctConversationCount(0L)
                .distinctFilesUploadedCount(0L)
                .distinctProjectsCreatedCount(0L)
                .distinctProjectsUsedCount(0L)
                .distinctSharedArtifactsViewedCount(0L)
                .distinctSkillsUsedCount(0L)
                .messageCount(0L)
                .sharedConversationsViewedCount(0L)
                .thinkingMessageCount(0L)
                .build()

        val roundtrippedBetaAnalyticsChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsChatMetrics),
                jacksonTypeRef<BetaAnalyticsChatMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsChatMetrics).isEqualTo(betaAnalyticsChatMetrics)
    }
}
