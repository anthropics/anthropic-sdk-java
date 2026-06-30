// File generated from our OpenAPI spec by Stainless.

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
                        .content(
                            BetaManagedAgentsTextBlock.builder()
                                .text("Where is my order #1234?")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
                        )
                        .type(BetaManagedAgentsDeltaContent.Type.CONTENT_DELTA)
                        .index(0L)
                        .build()
                )
                .eventId("event_id")
                .type(BetaManagedAgentsDeltaEvent.Type.EVENT_DELTA)
                .build()

        assertThat(betaManagedAgentsDeltaEvent.delta())
            .isEqualTo(
                BetaManagedAgentsDeltaContent.builder()
                    .content(
                        BetaManagedAgentsTextBlock.builder()
                            .text("Where is my order #1234?")
                            .type(BetaManagedAgentsTextBlock.Type.TEXT)
                            .build()
                    )
                    .type(BetaManagedAgentsDeltaContent.Type.CONTENT_DELTA)
                    .index(0L)
                    .build()
            )
        assertThat(betaManagedAgentsDeltaEvent.eventId()).isEqualTo("event_id")
        assertThat(betaManagedAgentsDeltaEvent.type())
            .isEqualTo(BetaManagedAgentsDeltaEvent.Type.EVENT_DELTA)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeltaEvent =
            BetaManagedAgentsDeltaEvent.builder()
                .delta(
                    BetaManagedAgentsDeltaContent.builder()
                        .content(
                            BetaManagedAgentsTextBlock.builder()
                                .text("Where is my order #1234?")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
                        )
                        .type(BetaManagedAgentsDeltaContent.Type.CONTENT_DELTA)
                        .index(0L)
                        .build()
                )
                .eventId("event_id")
                .type(BetaManagedAgentsDeltaEvent.Type.EVENT_DELTA)
                .build()

        val roundtrippedBetaManagedAgentsDeltaEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeltaEvent),
                jacksonTypeRef<BetaManagedAgentsDeltaEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeltaEvent).isEqualTo(betaManagedAgentsDeltaEvent)
    }
}
