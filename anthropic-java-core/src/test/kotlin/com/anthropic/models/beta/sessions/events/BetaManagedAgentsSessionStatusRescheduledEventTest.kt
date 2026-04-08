// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionStatusRescheduledEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionStatusRescheduledEvent =
            BetaManagedAgentsSessionStatusRescheduledEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(
                    BetaManagedAgentsSessionStatusRescheduledEvent.Type.SESSION_STATUS_RESCHEDULED
                )
                .build()

        assertThat(betaManagedAgentsSessionStatusRescheduledEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionStatusRescheduledEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionStatusRescheduledEvent.type())
            .isEqualTo(
                BetaManagedAgentsSessionStatusRescheduledEvent.Type.SESSION_STATUS_RESCHEDULED
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionStatusRescheduledEvent =
            BetaManagedAgentsSessionStatusRescheduledEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(
                    BetaManagedAgentsSessionStatusRescheduledEvent.Type.SESSION_STATUS_RESCHEDULED
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionStatusRescheduledEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionStatusRescheduledEvent),
                jacksonTypeRef<BetaManagedAgentsSessionStatusRescheduledEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionStatusRescheduledEvent)
            .isEqualTo(betaManagedAgentsSessionStatusRescheduledEvent)
    }
}
