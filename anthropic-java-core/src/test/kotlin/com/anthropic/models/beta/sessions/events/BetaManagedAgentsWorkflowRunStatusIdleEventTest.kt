package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunStatusIdleEventTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunStatusIdleEvent =
            BetaManagedAgentsWorkflowRunStatusIdleEvent.builder()
                .id("sevt_01JQ8Z9A4C6E8G1J2L4N6Q8S")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:03:20.118Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        assertThat(betaManagedAgentsWorkflowRunStatusIdleEvent.id())
            .isEqualTo("sevt_01JQ8Z9A4C6E8G1J2L4N6Q8S")
        assertThat(betaManagedAgentsWorkflowRunStatusIdleEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-01T18:03:20.118Z"))
        assertThat(betaManagedAgentsWorkflowRunStatusIdleEvent.workflowRunId())
            .isEqualTo("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunStatusIdleEvent =
            BetaManagedAgentsWorkflowRunStatusIdleEvent.builder()
                .id("sevt_01JQ8Z9A4C6E8G1J2L4N6Q8S")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:03:20.118Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunStatusIdleEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunStatusIdleEvent),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunStatusIdleEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunStatusIdleEvent)
            .isEqualTo(betaManagedAgentsWorkflowRunStatusIdleEvent)
    }
}
