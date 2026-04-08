// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionStatusIdleEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionStatusIdleEvent =
            BetaManagedAgentsSessionStatusIdleEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .stopReason(
                    BetaManagedAgentsSessionEndTurn.builder()
                        .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                        .build()
                )
                .type(BetaManagedAgentsSessionStatusIdleEvent.Type.SESSION_STATUS_IDLE)
                .build()

        assertThat(betaManagedAgentsSessionStatusIdleEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionStatusIdleEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionStatusIdleEvent.stopReason())
            .isEqualTo(
                BetaManagedAgentsSessionStatusIdleEvent.StopReason.ofEndTurn(
                    BetaManagedAgentsSessionEndTurn.builder()
                        .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                        .build()
                )
            )
        assertThat(betaManagedAgentsSessionStatusIdleEvent.type())
            .isEqualTo(BetaManagedAgentsSessionStatusIdleEvent.Type.SESSION_STATUS_IDLE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionStatusIdleEvent =
            BetaManagedAgentsSessionStatusIdleEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .stopReason(
                    BetaManagedAgentsSessionEndTurn.builder()
                        .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                        .build()
                )
                .type(BetaManagedAgentsSessionStatusIdleEvent.Type.SESSION_STATUS_IDLE)
                .build()

        val roundtrippedBetaManagedAgentsSessionStatusIdleEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionStatusIdleEvent),
                jacksonTypeRef<BetaManagedAgentsSessionStatusIdleEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionStatusIdleEvent)
            .isEqualTo(betaManagedAgentsSessionStatusIdleEvent)
    }
}
