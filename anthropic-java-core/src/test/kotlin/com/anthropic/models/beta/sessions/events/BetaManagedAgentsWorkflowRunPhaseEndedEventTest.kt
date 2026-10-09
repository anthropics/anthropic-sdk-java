package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunPhaseEndedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunPhaseEndedEvent =
            BetaManagedAgentsWorkflowRunPhaseEndedEvent.builder()
                .id("sevt_01JQ8ZB9W2Y4A6C8E1G2J4L6")
                .phaseStartedId("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:04:47.905Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .workflowRunPhaseId("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                .build()

        assertThat(betaManagedAgentsWorkflowRunPhaseEndedEvent.id())
            .isEqualTo("sevt_01JQ8ZB9W2Y4A6C8E1G2J4L6")
        assertThat(betaManagedAgentsWorkflowRunPhaseEndedEvent.phaseStartedId())
            .isEqualTo("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
        assertThat(betaManagedAgentsWorkflowRunPhaseEndedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-01T18:04:47.905Z"))
        assertThat(betaManagedAgentsWorkflowRunPhaseEndedEvent.workflowRunId())
            .isEqualTo("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
        assertThat(betaManagedAgentsWorkflowRunPhaseEndedEvent.workflowRunPhaseId())
            .isEqualTo("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunPhaseEndedEvent =
            BetaManagedAgentsWorkflowRunPhaseEndedEvent.builder()
                .id("sevt_01JQ8ZB9W2Y4A6C8E1G2J4L6")
                .phaseStartedId("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:04:47.905Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .workflowRunPhaseId("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunPhaseEndedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunPhaseEndedEvent),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunPhaseEndedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunPhaseEndedEvent)
            .isEqualTo(betaManagedAgentsWorkflowRunPhaseEndedEvent)
    }
}
