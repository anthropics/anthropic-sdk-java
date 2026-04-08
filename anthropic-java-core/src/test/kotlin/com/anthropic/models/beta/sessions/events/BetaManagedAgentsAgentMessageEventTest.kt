// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentMessageEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentMessageEvent =
            BetaManagedAgentsAgentMessageEvent.builder()
                .id("sevt_011CZkZHPq1jCdq5lbRTjiVnz")
                .addContent(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Let me look up order #1234 for you.")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .type(BetaManagedAgentsAgentMessageEvent.Type.AGENT_MESSAGE)
                .build()

        assertThat(betaManagedAgentsAgentMessageEvent.id())
            .isEqualTo("sevt_011CZkZHPq1jCdq5lbRTjiVnz")
        assertThat(betaManagedAgentsAgentMessageEvent.content())
            .containsExactly(
                BetaManagedAgentsTextBlock.builder()
                    .text("Let me look up order #1234 for you.")
                    .type(BetaManagedAgentsTextBlock.Type.TEXT)
                    .build()
            )
        assertThat(betaManagedAgentsAgentMessageEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsAgentMessageEvent.type())
            .isEqualTo(BetaManagedAgentsAgentMessageEvent.Type.AGENT_MESSAGE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentMessageEvent =
            BetaManagedAgentsAgentMessageEvent.builder()
                .id("sevt_011CZkZHPq1jCdq5lbRTjiVnz")
                .addContent(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Let me look up order #1234 for you.")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .type(BetaManagedAgentsAgentMessageEvent.Type.AGENT_MESSAGE)
                .build()

        val roundtrippedBetaManagedAgentsAgentMessageEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentMessageEvent),
                jacksonTypeRef<BetaManagedAgentsAgentMessageEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentMessageEvent)
            .isEqualTo(betaManagedAgentsAgentMessageEvent)
    }
}
