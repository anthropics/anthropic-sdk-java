package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunStatusRunningEventTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunStatusRunningEvent =
            BetaManagedAgentsWorkflowRunStatusRunningEvent.builder()
                .id("sevt_01JQ8Z6Y1M3P5R7T9V1X3Z5B")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:11.430Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        assertThat(betaManagedAgentsWorkflowRunStatusRunningEvent.id())
            .isEqualTo("sevt_01JQ8Z6Y1M3P5R7T9V1X3Z5B")
        assertThat(betaManagedAgentsWorkflowRunStatusRunningEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-01T18:02:11.430Z"))
        assertThat(betaManagedAgentsWorkflowRunStatusRunningEvent.workflowRunId())
            .isEqualTo("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunStatusRunningEvent =
            BetaManagedAgentsWorkflowRunStatusRunningEvent.builder()
                .id("sevt_01JQ8Z6Y1M3P5R7T9V1X3Z5B")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:11.430Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunStatusRunningEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunStatusRunningEvent),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunStatusRunningEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunStatusRunningEvent)
            .isEqualTo(betaManagedAgentsWorkflowRunStatusRunningEvent)
    }
}
