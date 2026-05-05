// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionThreadStatusTerminatedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionThreadStatusTerminatedEvent =
            BetaManagedAgentsSessionThreadStatusTerminatedEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusTerminatedEvent.Type
                        .SESSION_THREAD_STATUS_TERMINATED
                )
                .build()

        assertThat(betaManagedAgentsSessionThreadStatusTerminatedEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionThreadStatusTerminatedEvent.agentName())
            .isEqualTo("agent_name")
        assertThat(betaManagedAgentsSessionThreadStatusTerminatedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionThreadStatusTerminatedEvent.sessionThreadId())
            .isEqualTo("session_thread_id")
        assertThat(betaManagedAgentsSessionThreadStatusTerminatedEvent.type())
            .isEqualTo(
                BetaManagedAgentsSessionThreadStatusTerminatedEvent.Type
                    .SESSION_THREAD_STATUS_TERMINATED
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionThreadStatusTerminatedEvent =
            BetaManagedAgentsSessionThreadStatusTerminatedEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusTerminatedEvent.Type
                        .SESSION_THREAD_STATUS_TERMINATED
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionThreadStatusTerminatedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionThreadStatusTerminatedEvent),
                jacksonTypeRef<BetaManagedAgentsSessionThreadStatusTerminatedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionThreadStatusTerminatedEvent)
            .isEqualTo(betaManagedAgentsSessionThreadStatusTerminatedEvent)
    }
}
