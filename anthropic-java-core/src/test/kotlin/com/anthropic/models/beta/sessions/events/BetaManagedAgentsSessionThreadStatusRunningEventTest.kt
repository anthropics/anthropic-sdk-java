// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionThreadStatusRunningEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionThreadStatusRunningEvent =
            BetaManagedAgentsSessionThreadStatusRunningEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusRunningEvent.Type
                        .SESSION_THREAD_STATUS_RUNNING
                )
                .build()

        assertThat(betaManagedAgentsSessionThreadStatusRunningEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionThreadStatusRunningEvent.agentName())
            .isEqualTo("agent_name")
        assertThat(betaManagedAgentsSessionThreadStatusRunningEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionThreadStatusRunningEvent.sessionThreadId())
            .isEqualTo("session_thread_id")
        assertThat(betaManagedAgentsSessionThreadStatusRunningEvent.type())
            .isEqualTo(
                BetaManagedAgentsSessionThreadStatusRunningEvent.Type.SESSION_THREAD_STATUS_RUNNING
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionThreadStatusRunningEvent =
            BetaManagedAgentsSessionThreadStatusRunningEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusRunningEvent.Type
                        .SESSION_THREAD_STATUS_RUNNING
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionThreadStatusRunningEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionThreadStatusRunningEvent),
                jacksonTypeRef<BetaManagedAgentsSessionThreadStatusRunningEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionThreadStatusRunningEvent)
            .isEqualTo(betaManagedAgentsSessionThreadStatusRunningEvent)
    }
}
