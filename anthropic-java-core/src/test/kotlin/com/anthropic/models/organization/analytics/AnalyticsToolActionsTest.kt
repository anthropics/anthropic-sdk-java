package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsToolActionsTest {

    @Test
    fun create() {
        val analyticsToolActions =
            AnalyticsToolActions.builder()
                .editTool(
                    AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
                )
                .multiEditTool(
                    AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
                )
                .notebookEditTool(
                    AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
                )
                .writeTool(
                    AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
                )
                .build()

        assertThat(analyticsToolActions.editTool())
            .isEqualTo(
                AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
            )
        assertThat(analyticsToolActions.multiEditTool())
            .isEqualTo(
                AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
            )
        assertThat(analyticsToolActions.notebookEditTool())
            .isEqualTo(
                AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
            )
        assertThat(analyticsToolActions.writeTool())
            .isEqualTo(
                AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsToolActions =
            AnalyticsToolActions.builder()
                .editTool(
                    AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
                )
                .multiEditTool(
                    AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
                )
                .notebookEditTool(
                    AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
                )
                .writeTool(
                    AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()
                )
                .build()

        val roundtrippedAnalyticsToolActions =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsToolActions),
                jacksonTypeRef<AnalyticsToolActions>(),
            )

        assertThat(roundtrippedAnalyticsToolActions).isEqualTo(analyticsToolActions)
    }
}
