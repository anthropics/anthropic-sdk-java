package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunPhaseTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunPhase =
            BetaManagedAgentsWorkflowRunPhase.builder()
                .id("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                .description("Finds each vendor's pricing page.")
                .name("Collect the sources")
                .build()

        assertThat(betaManagedAgentsWorkflowRunPhase.id())
            .isEqualTo("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
        assertThat(betaManagedAgentsWorkflowRunPhase.description())
            .contains("Finds each vendor's pricing page.")
        assertThat(betaManagedAgentsWorkflowRunPhase.name()).isEqualTo("Collect the sources")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunPhase =
            BetaManagedAgentsWorkflowRunPhase.builder()
                .id("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                .description("Finds each vendor's pricing page.")
                .name("Collect the sources")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunPhase =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunPhase),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunPhase>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunPhase)
            .isEqualTo(betaManagedAgentsWorkflowRunPhase)
    }
}
