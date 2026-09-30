package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsClaudeCodeMetricsTest {

    @Test
    fun create() {
        val betaAnalyticsClaudeCodeMetrics =
            BetaAnalyticsClaudeCodeMetrics.builder()
                .coreMetrics(
                    BetaAnalyticsCoreCodeMetrics.builder()
                        .artifactsCreatedCount(0L)
                        .commitCount(0L)
                        .distinctSessionCount(0L)
                        .linesOfCode(
                            BetaAnalyticsLinesOfCode.builder()
                                .addedCount(0L)
                                .removedCount(0L)
                                .build()
                        )
                        .pullRequestCount(0L)
                        .build()
                )
                .toolActions(
                    BetaAnalyticsToolActions.builder()
                        .editTool(
                            BetaAnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .multiEditTool(
                            BetaAnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .notebookEditTool(
                            BetaAnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .writeTool(
                            BetaAnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaAnalyticsClaudeCodeMetrics.coreMetrics())
            .isEqualTo(
                BetaAnalyticsCoreCodeMetrics.builder()
                    .artifactsCreatedCount(0L)
                    .commitCount(0L)
                    .distinctSessionCount(0L)
                    .linesOfCode(
                        BetaAnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()
                    )
                    .pullRequestCount(0L)
                    .build()
            )
        assertThat(betaAnalyticsClaudeCodeMetrics.toolActions())
            .isEqualTo(
                BetaAnalyticsToolActions.builder()
                    .editTool(
                        BetaAnalyticsToolActionCounts.builder()
                            .acceptedCount(0L)
                            .rejectedCount(0L)
                            .build()
                    )
                    .multiEditTool(
                        BetaAnalyticsToolActionCounts.builder()
                            .acceptedCount(0L)
                            .rejectedCount(0L)
                            .build()
                    )
                    .notebookEditTool(
                        BetaAnalyticsToolActionCounts.builder()
                            .acceptedCount(0L)
                            .rejectedCount(0L)
                            .build()
                    )
                    .writeTool(
                        BetaAnalyticsToolActionCounts.builder()
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
        val betaAnalyticsClaudeCodeMetrics =
            BetaAnalyticsClaudeCodeMetrics.builder()
                .coreMetrics(
                    BetaAnalyticsCoreCodeMetrics.builder()
                        .artifactsCreatedCount(0L)
                        .commitCount(0L)
                        .distinctSessionCount(0L)
                        .linesOfCode(
                            BetaAnalyticsLinesOfCode.builder()
                                .addedCount(0L)
                                .removedCount(0L)
                                .build()
                        )
                        .pullRequestCount(0L)
                        .build()
                )
                .toolActions(
                    BetaAnalyticsToolActions.builder()
                        .editTool(
                            BetaAnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .multiEditTool(
                            BetaAnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .notebookEditTool(
                            BetaAnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .writeTool(
                            BetaAnalyticsToolActionCounts.builder()
                                .acceptedCount(0L)
                                .rejectedCount(0L)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaAnalyticsClaudeCodeMetrics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsClaudeCodeMetrics),
                jacksonTypeRef<BetaAnalyticsClaudeCodeMetrics>(),
            )

        assertThat(roundtrippedBetaAnalyticsClaudeCodeMetrics)
            .isEqualTo(betaAnalyticsClaudeCodeMetrics)
    }
}
