package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsChatCoworkUnifiedMetricsTest {

    @Test
    fun create() {
        val analyticsChatCoworkUnifiedMetrics =
            AnalyticsChatCoworkUnifiedMetrics.builder()
                .chat(
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
                )
                .sessions(
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
                )
                .build()

        assertThat(analyticsChatCoworkUnifiedMetrics.chat())
            .isEqualTo(
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
            )
        assertThat(analyticsChatCoworkUnifiedMetrics.sessions())
            .isEqualTo(
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
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsChatCoworkUnifiedMetrics =
            AnalyticsChatCoworkUnifiedMetrics.builder()
                .chat(
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
                )
                .sessions(
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
                )
                .build()

        val roundtrippedAnalyticsChatCoworkUnifiedMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsChatCoworkUnifiedMetrics),
                jacksonTypeRef<AnalyticsChatCoworkUnifiedMetrics>(),
            )

        assertThat(roundtrippedAnalyticsChatCoworkUnifiedMetrics)
            .isEqualTo(analyticsChatCoworkUnifiedMetrics)
    }
}
