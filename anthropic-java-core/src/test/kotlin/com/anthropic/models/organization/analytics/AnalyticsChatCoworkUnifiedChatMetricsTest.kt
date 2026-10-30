package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsChatCoworkUnifiedChatMetricsTest {

    @Test
    fun create() {
        val analyticsChatCoworkUnifiedChatMetrics =
            AnalyticsChatCoworkUnifiedChatMetrics.builder()
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

        assertThat(analyticsChatCoworkUnifiedChatMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.distinctArtifactsCreatedCount())
            .isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.distinctConnectorsUsedCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.distinctConversationCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.distinctFilesUploadedCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.distinctProjectsCreatedCount())
            .isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.distinctProjectsUsedCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.distinctSharedArtifactsViewedCount())
            .contains(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.messageCount()).isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.sharedConversationsViewedCount())
            .isEqualTo(0L)
        assertThat(analyticsChatCoworkUnifiedChatMetrics.thinkingMessageCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsChatCoworkUnifiedChatMetrics =
            AnalyticsChatCoworkUnifiedChatMetrics.builder()
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

        val roundtrippedAnalyticsChatCoworkUnifiedChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsChatCoworkUnifiedChatMetrics),
                jacksonTypeRef<AnalyticsChatCoworkUnifiedChatMetrics>(),
            )

        assertThat(roundtrippedAnalyticsChatCoworkUnifiedChatMetrics)
            .isEqualTo(analyticsChatCoworkUnifiedChatMetrics)
    }
}
