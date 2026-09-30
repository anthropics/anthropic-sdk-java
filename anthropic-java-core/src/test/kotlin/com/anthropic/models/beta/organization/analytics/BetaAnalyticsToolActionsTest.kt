package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsToolActionsTest {

    @Test
    fun create() {
        val betaAnalyticsToolActions =
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

        assertThat(betaAnalyticsToolActions.editTool())
            .isEqualTo(
                BetaAnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
            )
        assertThat(betaAnalyticsToolActions.multiEditTool())
            .isEqualTo(
                BetaAnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
            )
        assertThat(betaAnalyticsToolActions.notebookEditTool())
            .isEqualTo(
                BetaAnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
            )
        assertThat(betaAnalyticsToolActions.writeTool())
            .isEqualTo(
                BetaAnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsToolActions =
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

        val roundtrippedBetaAnalyticsToolActions =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsToolActions),
                jacksonTypeRef<BetaAnalyticsToolActions>(),
            )

        assertThat(roundtrippedBetaAnalyticsToolActions).isEqualTo(betaAnalyticsToolActions)
    }
}
