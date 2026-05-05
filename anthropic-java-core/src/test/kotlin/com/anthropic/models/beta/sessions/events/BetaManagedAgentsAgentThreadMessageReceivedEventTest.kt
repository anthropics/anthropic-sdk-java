// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentThreadMessageReceivedEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentThreadMessageReceivedEvent =
            BetaManagedAgentsAgentThreadMessageReceivedEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .fromSessionThreadId("from_session_thread_id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(
                    BetaManagedAgentsAgentThreadMessageReceivedEvent.Type
                        .AGENT_THREAD_MESSAGE_RECEIVED
                )
                .fromAgentName("from_agent_name")
                .build()

        assertThat(betaManagedAgentsAgentThreadMessageReceivedEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentThreadMessageReceivedEvent.content())
            .containsExactly(
                BetaManagedAgentsAgentThreadMessageReceivedEvent.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentThreadMessageReceivedEvent.fromSessionThreadId())
            .isEqualTo("from_session_thread_id")
        assertThat(betaManagedAgentsAgentThreadMessageReceivedEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsAgentThreadMessageReceivedEvent.type())
            .isEqualTo(
                BetaManagedAgentsAgentThreadMessageReceivedEvent.Type.AGENT_THREAD_MESSAGE_RECEIVED
            )
        assertThat(betaManagedAgentsAgentThreadMessageReceivedEvent.fromAgentName())
            .contains("from_agent_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentThreadMessageReceivedEvent =
            BetaManagedAgentsAgentThreadMessageReceivedEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .fromSessionThreadId("from_session_thread_id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(
                    BetaManagedAgentsAgentThreadMessageReceivedEvent.Type
                        .AGENT_THREAD_MESSAGE_RECEIVED
                )
                .fromAgentName("from_agent_name")
                .build()

        val roundtrippedBetaManagedAgentsAgentThreadMessageReceivedEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentThreadMessageReceivedEvent),
                jacksonTypeRef<BetaManagedAgentsAgentThreadMessageReceivedEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentThreadMessageReceivedEvent)
            .isEqualTo(betaManagedAgentsAgentThreadMessageReceivedEvent)
    }
}
