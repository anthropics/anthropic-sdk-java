package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunCreatedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunCreatedEvent =
            BetaManagedAgentsWorkflowRunCreatedEvent.builder()
                .id("sevt_01JQ8Z6X8K2N4V7T9B3C5D1E")
                .description("Reads each vendor's pricing page and tabulates the plans.")
                .name("Compare the vendors")
                .addPhase(
                    BetaManagedAgentsWorkflowRunPhase.builder()
                        .id("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                        .description(null)
                        .name("Collect the sources")
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:11.412Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        assertThat(betaManagedAgentsWorkflowRunCreatedEvent.id())
            .isEqualTo("sevt_01JQ8Z6X8K2N4V7T9B3C5D1E")
        assertThat(betaManagedAgentsWorkflowRunCreatedEvent.description())
            .contains("Reads each vendor's pricing page and tabulates the plans.")
        assertThat(betaManagedAgentsWorkflowRunCreatedEvent.name()).isEqualTo("Compare the vendors")
        assertThat(betaManagedAgentsWorkflowRunCreatedEvent.phases())
            .containsExactly(
                BetaManagedAgentsWorkflowRunPhase.builder()
                    .id("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                    .description(null)
                    .name("Collect the sources")
                    .build()
            )
        assertThat(betaManagedAgentsWorkflowRunCreatedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-01T18:02:11.412Z"))
        assertThat(betaManagedAgentsWorkflowRunCreatedEvent.workflowRunId())
            .isEqualTo("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunCreatedEvent =
            BetaManagedAgentsWorkflowRunCreatedEvent.builder()
                .id("sevt_01JQ8Z6X8K2N4V7T9B3C5D1E")
                .description("Reads each vendor's pricing page and tabulates the plans.")
                .name("Compare the vendors")
                .addPhase(
                    BetaManagedAgentsWorkflowRunPhase.builder()
                        .id("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                        .description(null)
                        .name("Collect the sources")
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:11.412Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunCreatedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunCreatedEvent),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunCreatedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunCreatedEvent)
            .isEqualTo(betaManagedAgentsWorkflowRunCreatedEvent)
    }
}
