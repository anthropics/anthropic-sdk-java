package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeltaEventTest {

    @Test
    fun create() {
        val betaManagedAgentsDeltaEvent =
            BetaManagedAgentsDeltaEvent.builder()
                .delta(
                    BetaManagedAgentsDeltaContent.builder()
                        .content(BetaManagedAgentsTextBlock.of("Where is my order #1234?"))
                        .index(0L)
                        .build()
                )
                .eventId("event_id")
                .build()

        assertThat(betaManagedAgentsDeltaEvent.delta())
            .isEqualTo(
                BetaManagedAgentsDeltaContent.builder()
                    .content(BetaManagedAgentsTextBlock.of("Where is my order #1234?"))
                    .index(0L)
                    .build()
            )
        assertThat(betaManagedAgentsDeltaEvent.eventId()).isEqualTo("event_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeltaEvent =
            BetaManagedAgentsDeltaEvent.builder()
                .delta(
                    BetaManagedAgentsDeltaContent.builder()
                        .content(BetaManagedAgentsTextBlock.of("Where is my order #1234?"))
                        .index(0L)
                        .build()
                )
                .eventId("event_id")
                .build()

        val roundtrippedBetaManagedAgentsDeltaEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeltaEvent),
                jacksonTypeRef<BetaManagedAgentsDeltaEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeltaEvent).isEqualTo(betaManagedAgentsDeltaEvent)
    }
}
