// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionThreadStatusRescheduledEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionThreadStatusRescheduledEvent =
            BetaManagedAgentsSessionThreadStatusRescheduledEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusRescheduledEvent.Type
                        .SESSION_THREAD_STATUS_RESCHEDULED
                )
                .build()

        assertThat(betaManagedAgentsSessionThreadStatusRescheduledEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionThreadStatusRescheduledEvent.agentName())
            .isEqualTo("agent_name")
        assertThat(betaManagedAgentsSessionThreadStatusRescheduledEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionThreadStatusRescheduledEvent.sessionThreadId())
            .isEqualTo("session_thread_id")
        assertThat(betaManagedAgentsSessionThreadStatusRescheduledEvent.type())
            .isEqualTo(
                BetaManagedAgentsSessionThreadStatusRescheduledEvent.Type
                    .SESSION_THREAD_STATUS_RESCHEDULED
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionThreadStatusRescheduledEvent =
            BetaManagedAgentsSessionThreadStatusRescheduledEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusRescheduledEvent.Type
                        .SESSION_THREAD_STATUS_RESCHEDULED
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionThreadStatusRescheduledEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionThreadStatusRescheduledEvent),
                jacksonTypeRef<BetaManagedAgentsSessionThreadStatusRescheduledEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionThreadStatusRescheduledEvent)
            .isEqualTo(betaManagedAgentsSessionThreadStatusRescheduledEvent)
    }
}
