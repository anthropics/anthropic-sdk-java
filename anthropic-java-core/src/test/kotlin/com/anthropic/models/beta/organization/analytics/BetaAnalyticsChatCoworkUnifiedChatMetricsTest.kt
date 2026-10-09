package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsChatCoworkUnifiedChatMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsChatCoworkUnifiedChatMetrics =
            BetaAnalyticsChatCoworkUnifiedChatMetrics.builder()
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

        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.distinctArtifactsCreatedCount())
            .isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.distinctConnectorsUsedCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.distinctConversationCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.distinctFilesUploadedCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.distinctProjectsCreatedCount())
            .isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.distinctProjectsUsedCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.distinctSharedArtifactsViewedCount())
            .contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.messageCount()).isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.sharedConversationsViewedCount())
            .isEqualTo(0L)
        assertThat(betaAnalyticsChatCoworkUnifiedChatMetrics.thinkingMessageCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsChatCoworkUnifiedChatMetrics =
            BetaAnalyticsChatCoworkUnifiedChatMetrics.builder()
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

        val roundtrippedBetaAnalyticsChatCoworkUnifiedChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsChatCoworkUnifiedChatMetrics),
                jacksonTypeRef<BetaAnalyticsChatCoworkUnifiedChatMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsChatCoworkUnifiedChatMetrics)
            .isEqualTo(betaAnalyticsChatCoworkUnifiedChatMetrics)
    }
}
