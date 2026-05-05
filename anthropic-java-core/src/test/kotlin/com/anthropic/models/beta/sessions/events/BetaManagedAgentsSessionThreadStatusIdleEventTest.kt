// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionThreadStatusIdleEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionThreadStatusIdleEvent =
            BetaManagedAgentsSessionThreadStatusIdleEvent.builder()
                .id("sevt_011CZkZXYc8qKly2tiZbrpDv")
                .agentName("Researcher")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                .stopReason(
                    BetaManagedAgentsSessionEndTurn.builder()
                        .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                        .build()
                )
                .type(BetaManagedAgentsSessionThreadStatusIdleEvent.Type.SESSION_THREAD_STATUS_IDLE)
                .build()

        assertThat(betaManagedAgentsSessionThreadStatusIdleEvent.id())
            .isEqualTo("sevt_011CZkZXYc8qKly2tiZbrpDv")
        assertThat(betaManagedAgentsSessionThreadStatusIdleEvent.agentName())
            .isEqualTo("Researcher")
        assertThat(betaManagedAgentsSessionThreadStatusIdleEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsSessionThreadStatusIdleEvent.sessionThreadId())
            .isEqualTo("sthr_011CZkZVWa6oIjw0rgXZpnBt")
        assertThat(betaManagedAgentsSessionThreadStatusIdleEvent.stopReason())
            .isEqualTo(
                BetaManagedAgentsSessionThreadStatusIdleEvent.StopReason.ofEndTurn(
                    BetaManagedAgentsSessionEndTurn.builder()
                        .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                        .build()
                )
            )
        assertThat(betaManagedAgentsSessionThreadStatusIdleEvent.type())
            .isEqualTo(
                BetaManagedAgentsSessionThreadStatusIdleEvent.Type.SESSION_THREAD_STATUS_IDLE
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionThreadStatusIdleEvent =
            BetaManagedAgentsSessionThreadStatusIdleEvent.builder()
                .id("sevt_011CZkZXYc8qKly2tiZbrpDv")
                .agentName("Researcher")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                .stopReason(
                    BetaManagedAgentsSessionEndTurn.builder()
                        .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                        .build()
                )
                .type(BetaManagedAgentsSessionThreadStatusIdleEvent.Type.SESSION_THREAD_STATUS_IDLE)
                .build()

        val roundtrippedBetaManagedAgentsSessionThreadStatusIdleEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionThreadStatusIdleEvent),
                jacksonTypeRef<BetaManagedAgentsSessionThreadStatusIdleEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionThreadStatusIdleEvent)
            .isEqualTo(betaManagedAgentsSessionThreadStatusIdleEvent)
    }
}
