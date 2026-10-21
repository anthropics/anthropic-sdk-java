package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsStartEventTest {

    @Test
    fun create() {
        val betaManagedAgentsStartEvent =
            BetaManagedAgentsStartEvent.builder().agentMessageEvent("id").build()

        assertThat(betaManagedAgentsStartEvent.event())
            .isEqualTo(BetaManagedAgentsStartEventPreview.ofAgentMessage("id"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStartEvent =
            BetaManagedAgentsStartEvent.builder().agentMessageEvent("id").build()

        val roundtrippedBetaManagedAgentsStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStartEvent),
                jacksonTypeRef<BetaManagedAgentsStartEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStartEvent).isEqualTo(betaManagedAgentsStartEvent)
    }
}
