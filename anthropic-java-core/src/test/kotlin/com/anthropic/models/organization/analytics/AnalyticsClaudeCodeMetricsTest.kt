package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsClaudeCodeMetricsTest {

    @Test
    fun create() {
        val analyticsClaudeCodeMetrics =
            AnalyticsClaudeCodeMetrics.builder()
                .coreMetrics(
                    AnalyticsCoreCodeMetrics.builder()
                        .artifactsCreatedCount(0L)
                        .commitCount(0L)
                        .distinctSessionCount(0L)
                        .linesOfCode(
                            AnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()
                        )
                        .pullRequestCount(0L)
                        .build()
                )
                .toolActions(
                    AnalyticsToolActions.builder()
                        .editTool(
                            AnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .multiEditTool(
                            AnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .notebookEditTool(
                            AnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .writeTool(
                            AnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(analyticsClaudeCodeMetrics.coreMetrics())
            .isEqualTo(
                AnalyticsCoreCodeMetrics.builder()
                    .artifactsCreatedCount(0L)
                    .commitCount(0L)
                    .distinctSessionCount(0L)
                    .linesOfCode(
                        AnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()
                    )
                    .pullRequestCount(0L)
                    .build()
            )
        assertThat(analyticsClaudeCodeMetrics.toolActions())
            .isEqualTo(
                AnalyticsToolActions.builder()
                    .editTool(
                        AnalyticsToolActionCounts.builder()
                            .acceptedCount(0L)
                            .rejectedCount(0L)
                            .build()
                    )
                    .multiEditTool(
                        AnalyticsToolActionCounts.builder()
                            .acceptedCount(0L)
                            .rejectedCount(0L)
                            .build()
                    )
                    .notebookEditTool(
                        AnalyticsToolActionCounts.builder()
                            .acceptedCount(0L)
                            .rejectedCount(0L)
                            .build()
                    )
                    .writeTool(
                        AnalyticsToolActionCounts.builder()
                            .acceptedCount(0L)
                            .rejectedCount(0L)
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsClaudeCodeMetrics =
            AnalyticsClaudeCodeMetrics.builder()
                .coreMetrics(
                    AnalyticsCoreCodeMetrics.builder()
                        .artifactsCreatedCount(0L)
                        .commitCount(0L)
                        .distinctSessionCount(0L)
                        .linesOfCode(
                            AnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()
                        )
                        .pullRequestCount(0L)
                        .build()
                )
                .toolActions(
                    AnalyticsToolActions.builder()
                        .editTool(
                            AnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .multiEditTool(
                            AnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .notebookEditTool(
                            AnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .writeTool(
                            AnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedAnalyticsClaudeCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsClaudeCodeMetrics),
                jacksonTypeRef<AnalyticsClaudeCodeMetrics>(),
            )

        assertThat(roundtrippedAnalyticsClaudeCodeMetrics).isEqualTo(analyticsClaudeCodeMetrics)
    }
}
