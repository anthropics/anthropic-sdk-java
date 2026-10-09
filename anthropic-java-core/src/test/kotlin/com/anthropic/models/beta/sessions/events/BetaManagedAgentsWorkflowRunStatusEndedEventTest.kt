package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunStatusEndedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunStatusEndedEvent =
            BetaManagedAgentsWorkflowRunStatusEndedEvent.builder()
                .id("sevt_01JQ8ZC1V5B7N9M1K3J5H7GA")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:05:02.337Z"))
                .result(BetaManagedAgentsWorkflowRunResultCompleted.builder().build())
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        assertThat(betaManagedAgentsWorkflowRunStatusEndedEvent.id())
            .isEqualTo("sevt_01JQ8ZC1V5B7N9M1K3J5H7GA")
        assertThat(betaManagedAgentsWorkflowRunStatusEndedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-01T18:05:02.337Z"))
        assertThat(betaManagedAgentsWorkflowRunStatusEndedEvent.result())
            .isEqualTo(
                BetaManagedAgentsWorkflowRunResult.ofCompleted(
                    BetaManagedAgentsWorkflowRunResultCompleted.builder().build()
                )
            )
        assertThat(betaManagedAgentsWorkflowRunStatusEndedEvent.workflowRunId())
            .isEqualTo("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunStatusEndedEvent =
            BetaManagedAgentsWorkflowRunStatusEndedEvent.builder()
                .id("sevt_01JQ8ZC1V5B7N9M1K3J5H7GA")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:05:02.337Z"))
                .result(BetaManagedAgentsWorkflowRunResultCompleted.builder().build())
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunStatusEndedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunStatusEndedEvent),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunStatusEndedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunStatusEndedEvent)
            .isEqualTo(betaManagedAgentsWorkflowRunStatusEndedEvent)
    }
}
