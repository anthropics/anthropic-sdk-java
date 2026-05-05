// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads.events

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEvent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EventListPageResponseTest {

    @Test
    fun create() {
        val eventListPageResponse =
            EventListPageResponse.builder()
                .addData(
                    BetaManagedAgentsUserMessageEvent.builder()
                        .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                        .addTextContent("Where is my order #1234?")
                        .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                        .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(eventListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsSessionEvent.ofUserMessage(
                    BetaManagedAgentsUserMessageEvent.builder()
                        .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                        .addTextContent("Where is my order #1234?")
                        .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                        .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
            )
        assertThat(eventListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val eventListPageResponse =
            EventListPageResponse.builder()
                .addData(
                    BetaManagedAgentsUserMessageEvent.builder()
                        .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                        .addTextContent("Where is my order #1234?")
                        .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                        .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedEventListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(eventListPageResponse),
                jacksonTypeRef<EventListPageResponse>(),
            )

        assertThat(roundtrippedEventListPageResponse).isEqualTo(eventListPageResponse)
    }
}
