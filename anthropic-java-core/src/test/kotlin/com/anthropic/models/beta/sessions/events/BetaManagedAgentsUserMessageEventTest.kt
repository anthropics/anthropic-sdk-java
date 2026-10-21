package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserMessageEventTest {

    @Test
    fun create() {
        val betaManagedAgentsUserMessageEvent =
            BetaManagedAgentsUserMessageEvent.builder()
                .id("sevt_011CZkZGPp1iBcp4kaQSihUm")
                .addTextContent("Where is my order #1234?")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        assertThat(betaManagedAgentsUserMessageEvent.id())
            .isEqualTo("sevt_011CZkZGPp1iBcp4kaQSihUm")
        assertThat(betaManagedAgentsUserMessageEvent.content())
            .containsExactly(
                BetaManagedAgentsUserMessageEvent.Content.ofText("Where is my order #1234?")
            )
        assertThat(betaManagedAgentsUserMessageEvent.processedAt())
            .contains(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserMessageEvent =
            BetaManagedAgentsUserMessageEvent.builder()
                .id("sevt_011CZkZGPp1iBcp4kaQSihUm")
                .addTextContent("Where is my order #1234?")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val roundtrippedBetaManagedAgentsUserMessageEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserMessageEvent),
                jacksonTypeRef<BetaManagedAgentsUserMessageEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserMessageEvent)
            .isEqualTo(betaManagedAgentsUserMessageEvent)
    }
}
