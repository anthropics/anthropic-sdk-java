// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionStatusTerminatedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionStatusTerminatedEvent =
            BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED)
                .build()

        assertThat(betaManagedAgentsSessionStatusTerminatedEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionStatusTerminatedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionStatusTerminatedEvent.type())
            .isEqualTo(BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionStatusTerminatedEvent =
            BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED)
                .build()

        val roundtrippedBetaManagedAgentsSessionStatusTerminatedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionStatusTerminatedEvent),
                jacksonTypeRef<BetaManagedAgentsSessionStatusTerminatedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionStatusTerminatedEvent)
            .isEqualTo(betaManagedAgentsSessionStatusTerminatedEvent)
    }
}
