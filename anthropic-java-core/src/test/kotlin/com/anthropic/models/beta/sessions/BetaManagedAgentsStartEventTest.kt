// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsStartEventTest {

    @Test
    fun create() {
        val betaManagedAgentsStartEvent =
            BetaManagedAgentsStartEvent.builder()
                .agentMessageEvent("id")
                .type(BetaManagedAgentsStartEvent.Type.EVENT_START)
                .build()

        assertThat(betaManagedAgentsStartEvent.event())
            .isEqualTo(
                BetaManagedAgentsStartEventPreview.ofAgentMessage(
                    BetaManagedAgentsAgentMessagePreview.builder()
                        .id("id")
                        .type(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
                        .build()
                )
            )
        assertThat(betaManagedAgentsStartEvent.type())
            .isEqualTo(BetaManagedAgentsStartEvent.Type.EVENT_START)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStartEvent =
            BetaManagedAgentsStartEvent.builder()
                .agentMessageEvent("id")
                .type(BetaManagedAgentsStartEvent.Type.EVENT_START)
                .build()

        val roundtrippedBetaManagedAgentsStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStartEvent),
                jacksonTypeRef<BetaManagedAgentsStartEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStartEvent).isEqualTo(betaManagedAgentsStartEvent)
    }
}
