package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionStatusRunningEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionStatusRunningEvent =
            BetaManagedAgentsSessionStatusRunningEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsSessionStatusRunningEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionStatusRunningEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionStatusRunningEvent =
            BetaManagedAgentsSessionStatusRunningEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaManagedAgentsSessionStatusRunningEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionStatusRunningEvent),
                jacksonTypeRef<BetaManagedAgentsSessionStatusRunningEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionStatusRunningEvent)
            .isEqualTo(betaManagedAgentsSessionStatusRunningEvent)
    }
}
