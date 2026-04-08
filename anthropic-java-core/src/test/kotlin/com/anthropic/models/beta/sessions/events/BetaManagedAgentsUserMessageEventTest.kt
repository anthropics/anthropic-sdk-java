// File generated from our OpenAPI spec by Stainless.

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
                .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        assertThat(betaManagedAgentsUserMessageEvent.id())
            .isEqualTo("sevt_011CZkZGOp0iBcp4kaQSihUmy")
        assertThat(betaManagedAgentsUserMessageEvent.content())
            .containsExactly(
                BetaManagedAgentsUserMessageEvent.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsUserMessageEvent.type())
            .isEqualTo(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
        assertThat(betaManagedAgentsUserMessageEvent.processedAt())
            .contains(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserMessageEvent =
            BetaManagedAgentsUserMessageEvent.builder()
                .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
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
