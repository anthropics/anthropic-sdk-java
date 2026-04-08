// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentThinkingEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentThinkingEvent =
            BetaManagedAgentsAgentThinkingEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
                .build()

        assertThat(betaManagedAgentsAgentThinkingEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentThinkingEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsAgentThinkingEvent.type())
            .isEqualTo(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentThinkingEvent =
            BetaManagedAgentsAgentThinkingEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
                .build()

        val roundtrippedBetaManagedAgentsAgentThinkingEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentThinkingEvent),
                jacksonTypeRef<BetaManagedAgentsAgentThinkingEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentThinkingEvent)
            .isEqualTo(betaManagedAgentsAgentThinkingEvent)
    }
}
