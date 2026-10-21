package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentThreadContextCompactedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentThreadContextCompactedEvent =
            BetaManagedAgentsAgentThreadContextCompactedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsAgentThreadContextCompactedEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentThreadContextCompactedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentThreadContextCompactedEvent =
            BetaManagedAgentsAgentThreadContextCompactedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaManagedAgentsAgentThreadContextCompactedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentThreadContextCompactedEvent),
                jacksonTypeRef<BetaManagedAgentsAgentThreadContextCompactedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentThreadContextCompactedEvent)
            .isEqualTo(betaManagedAgentsAgentThreadContextCompactedEvent)
    }
}
