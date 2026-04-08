// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolResultEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolResultEvent =
            BetaManagedAgentsAgentToolResultEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .toolUseId("tool_use_id")
                .type(BetaManagedAgentsAgentToolResultEvent.Type.AGENT_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        assertThat(betaManagedAgentsAgentToolResultEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentToolResultEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsAgentToolResultEvent.toolUseId()).isEqualTo("tool_use_id")
        assertThat(betaManagedAgentsAgentToolResultEvent.type())
            .isEqualTo(BetaManagedAgentsAgentToolResultEvent.Type.AGENT_TOOL_RESULT)
        assertThat(betaManagedAgentsAgentToolResultEvent.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgentToolResultEvent.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentToolResultEvent.isError()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolResultEvent =
            BetaManagedAgentsAgentToolResultEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .toolUseId("tool_use_id")
                .type(BetaManagedAgentsAgentToolResultEvent.Type.AGENT_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val roundtrippedBetaManagedAgentsAgentToolResultEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolResultEvent),
                jacksonTypeRef<BetaManagedAgentsAgentToolResultEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolResultEvent)
            .isEqualTo(betaManagedAgentsAgentToolResultEvent)
    }
}
