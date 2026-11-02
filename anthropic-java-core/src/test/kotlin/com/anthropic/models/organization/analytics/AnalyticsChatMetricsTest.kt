package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsChatMetricsTest {

    @Test
    fun create() {
        val analyticsChatMetrics =
            AnalyticsChatMetrics.builder()
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

        assertThat(analyticsChatMetrics.connectorsUsedCount()).isEqualTo(0L)
        assertThat(analyticsChatMetrics.distinctArtifactsCreatedCount()).isEqualTo(0L)
        assertThat(analyticsChatMetrics.distinctConnectorsUsedCount()).contains(0L)
        assertThat(analyticsChatMetrics.distinctConversationCount()).contains(0L)
        assertThat(analyticsChatMetrics.distinctFilesUploadedCount()).contains(0L)
        assertThat(analyticsChatMetrics.distinctProjectsCreatedCount()).isEqualTo(0L)
        assertThat(analyticsChatMetrics.distinctProjectsUsedCount()).contains(0L)
        assertThat(analyticsChatMetrics.distinctSharedArtifactsViewedCount()).contains(0L)
        assertThat(analyticsChatMetrics.distinctSkillsUsedCount()).contains(0L)
        assertThat(analyticsChatMetrics.messageCount()).isEqualTo(0L)
        assertThat(analyticsChatMetrics.sharedConversationsViewedCount()).isEqualTo(0L)
        assertThat(analyticsChatMetrics.thinkingMessageCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsChatMetrics =
            AnalyticsChatMetrics.builder()
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

        val roundtrippedAnalyticsChatMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsChatMetrics),
                jacksonTypeRef<AnalyticsChatMetrics>(),
            )

        assertThat(roundtrippedAnalyticsChatMetrics).isEqualTo(analyticsChatMetrics)
    }
}
