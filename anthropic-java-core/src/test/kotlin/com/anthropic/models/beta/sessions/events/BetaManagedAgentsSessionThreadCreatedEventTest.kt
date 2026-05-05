// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionThreadCreatedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionThreadCreatedEvent =
            BetaManagedAgentsSessionThreadCreatedEvent.builder()
                .id("sevt_011CZkZWXb7pJkx1shYaqoCu")
                .agentName("Researcher")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                .type(BetaManagedAgentsSessionThreadCreatedEvent.Type.SESSION_THREAD_CREATED)
                .build()

        assertThat(betaManagedAgentsSessionThreadCreatedEvent.id())
            .isEqualTo("sevt_011CZkZWXb7pJkx1shYaqoCu")
        assertThat(betaManagedAgentsSessionThreadCreatedEvent.agentName()).isEqualTo("Researcher")
        assertThat(betaManagedAgentsSessionThreadCreatedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsSessionThreadCreatedEvent.sessionThreadId())
            .isEqualTo("sthr_011CZkZVWa6oIjw0rgXZpnBt")
        assertThat(betaManagedAgentsSessionThreadCreatedEvent.type())
            .isEqualTo(BetaManagedAgentsSessionThreadCreatedEvent.Type.SESSION_THREAD_CREATED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionThreadCreatedEvent =
            BetaManagedAgentsSessionThreadCreatedEvent.builder()
                .id("sevt_011CZkZWXb7pJkx1shYaqoCu")
                .agentName("Researcher")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                .type(BetaManagedAgentsSessionThreadCreatedEvent.Type.SESSION_THREAD_CREATED)
                .build()

        val roundtrippedBetaManagedAgentsSessionThreadCreatedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionThreadCreatedEvent),
                jacksonTypeRef<BetaManagedAgentsSessionThreadCreatedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionThreadCreatedEvent)
            .isEqualTo(betaManagedAgentsSessionThreadCreatedEvent)
    }
}
