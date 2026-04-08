// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
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
                .addData(
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
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
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
                ),
                BetaManagedAgentsSessionEvent.ofAgentMessage(
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
                ),
            )
        assertThat(eventListPageResponse.nextPage()).contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
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
                .addData(
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
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedEventListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(eventListPageResponse),
                jacksonTypeRef<EventListPageResponse>(),
            )

        assertThat(roundtrippedEventListPageResponse).isEqualTo(eventListPageResponse)
    }
}
