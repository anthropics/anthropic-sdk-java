// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentThreadMessageSentEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentThreadMessageSentEvent =
            BetaManagedAgentsAgentThreadMessageSentEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .toSessionThreadId("to_session_thread_id")
                .type(BetaManagedAgentsAgentThreadMessageSentEvent.Type.AGENT_THREAD_MESSAGE_SENT)
                .toAgentName("to_agent_name")
                .build()

        assertThat(betaManagedAgentsAgentThreadMessageSentEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentThreadMessageSentEvent.content())
            .containsExactly(
                BetaManagedAgentsAgentThreadMessageSentEvent.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentThreadMessageSentEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsAgentThreadMessageSentEvent.toSessionThreadId())
            .isEqualTo("to_session_thread_id")
        assertThat(betaManagedAgentsAgentThreadMessageSentEvent.type())
            .isEqualTo(BetaManagedAgentsAgentThreadMessageSentEvent.Type.AGENT_THREAD_MESSAGE_SENT)
        assertThat(betaManagedAgentsAgentThreadMessageSentEvent.toAgentName())
            .contains("to_agent_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentThreadMessageSentEvent =
            BetaManagedAgentsAgentThreadMessageSentEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .toSessionThreadId("to_session_thread_id")
                .type(BetaManagedAgentsAgentThreadMessageSentEvent.Type.AGENT_THREAD_MESSAGE_SENT)
                .toAgentName("to_agent_name")
                .build()

        val roundtrippedBetaManagedAgentsAgentThreadMessageSentEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentThreadMessageSentEvent),
                jacksonTypeRef<BetaManagedAgentsAgentThreadMessageSentEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentThreadMessageSentEvent)
            .isEqualTo(betaManagedAgentsAgentThreadMessageSentEvent)
    }
}
