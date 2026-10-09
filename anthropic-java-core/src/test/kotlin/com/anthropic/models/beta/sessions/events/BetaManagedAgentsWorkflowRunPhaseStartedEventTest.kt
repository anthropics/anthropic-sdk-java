package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunPhaseStartedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunPhaseStartedEvent =
            BetaManagedAgentsWorkflowRunPhaseStartedEvent.builder()
                .id("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:14.020Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .workflowRunPhaseId("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                .build()

        assertThat(betaManagedAgentsWorkflowRunPhaseStartedEvent.id())
            .isEqualTo("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
        assertThat(betaManagedAgentsWorkflowRunPhaseStartedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-01T18:02:14.020Z"))
        assertThat(betaManagedAgentsWorkflowRunPhaseStartedEvent.workflowRunId())
            .isEqualTo("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
        assertThat(betaManagedAgentsWorkflowRunPhaseStartedEvent.workflowRunPhaseId())
            .isEqualTo("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunPhaseStartedEvent =
            BetaManagedAgentsWorkflowRunPhaseStartedEvent.builder()
                .id("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:14.020Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .workflowRunPhaseId("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunPhaseStartedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunPhaseStartedEvent),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunPhaseStartedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunPhaseStartedEvent)
            .isEqualTo(betaManagedAgentsWorkflowRunPhaseStartedEvent)
    }
}
