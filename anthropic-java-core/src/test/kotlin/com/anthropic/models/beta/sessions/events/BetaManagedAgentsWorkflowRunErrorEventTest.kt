package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunErrorEventTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunErrorEvent =
            BetaManagedAgentsWorkflowRunErrorEvent.builder()
                .id("sevt_01JQ8ZB7T3X5Z7C9E1G3J5L7")
                .programError("The workflow run's plan failed.")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:05:02.301Z"))
                .workflowRunId("wrun_011CZm5tR2nHw6Jc9Ys4PdKf")
                .build()

        assertThat(betaManagedAgentsWorkflowRunErrorEvent.id())
            .isEqualTo("sevt_01JQ8ZB7T3X5Z7C9E1G3J5L7")
        assertThat(betaManagedAgentsWorkflowRunErrorEvent.error())
            .isEqualTo(
                BetaManagedAgentsWorkflowRunError.ofProgram("The workflow run's plan failed.")
            )
        assertThat(betaManagedAgentsWorkflowRunErrorEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-01T18:05:02.301Z"))
        assertThat(betaManagedAgentsWorkflowRunErrorEvent.workflowRunId())
            .contains("wrun_011CZm5tR2nHw6Jc9Ys4PdKf")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunErrorEvent =
            BetaManagedAgentsWorkflowRunErrorEvent.builder()
                .id("sevt_01JQ8ZB7T3X5Z7C9E1G3J5L7")
                .programError("The workflow run's plan failed.")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:05:02.301Z"))
                .workflowRunId("wrun_011CZm5tR2nHw6Jc9Ys4PdKf")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunErrorEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunErrorEvent),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunErrorEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunErrorEvent)
            .isEqualTo(betaManagedAgentsWorkflowRunErrorEvent)
    }
}
