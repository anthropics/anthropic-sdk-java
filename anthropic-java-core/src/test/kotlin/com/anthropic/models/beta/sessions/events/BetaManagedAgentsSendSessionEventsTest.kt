// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSendSessionEventsTest {

    @Test
    fun create() {
        val betaManagedAgentsSendSessionEvents =
            BetaManagedAgentsSendSessionEvents.builder()
                .addData(
                    BetaManagedAgentsUserMessageEvent.builder()
                        .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                        .addTextContent("Where is my order #1234?")
                        .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                        .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsSendSessionEvents.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsSendSessionEvents.Data.ofUserMessage(
                    BetaManagedAgentsUserMessageEvent.builder()
                        .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                        .addTextContent("Where is my order #1234?")
                        .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                        .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSendSessionEvents =
            BetaManagedAgentsSendSessionEvents.builder()
                .addData(
                    BetaManagedAgentsUserMessageEvent.builder()
                        .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                        .addTextContent("Where is my order #1234?")
                        .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                        .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsSendSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSendSessionEvents),
                jacksonTypeRef<BetaManagedAgentsSendSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSendSessionEvents)
            .isEqualTo(betaManagedAgentsSendSessionEvents)
    }
}
