// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionDeletedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionDeletedEvent =
            BetaManagedAgentsSessionDeletedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
                .build()

        assertThat(betaManagedAgentsSessionDeletedEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionDeletedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionDeletedEvent.type())
            .isEqualTo(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionDeletedEvent =
            BetaManagedAgentsSessionDeletedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
                .build()

        val roundtrippedBetaManagedAgentsSessionDeletedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionDeletedEvent),
                jacksonTypeRef<BetaManagedAgentsSessionDeletedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionDeletedEvent)
            .isEqualTo(betaManagedAgentsSessionDeletedEvent)
    }
}
