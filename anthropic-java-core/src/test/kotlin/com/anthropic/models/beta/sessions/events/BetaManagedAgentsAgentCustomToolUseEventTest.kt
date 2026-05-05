// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentCustomToolUseEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentCustomToolUseEvent =
            BetaManagedAgentsAgentCustomToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentCustomToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentCustomToolUseEvent.Type.AGENT_CUSTOM_TOOL_USE)
                .sessionThreadId("session_thread_id")
                .build()

        assertThat(betaManagedAgentsAgentCustomToolUseEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentCustomToolUseEvent.input())
            .isEqualTo(
                BetaManagedAgentsAgentCustomToolUseEvent.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaManagedAgentsAgentCustomToolUseEvent.name()).isEqualTo("name")
        assertThat(betaManagedAgentsAgentCustomToolUseEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsAgentCustomToolUseEvent.type())
            .isEqualTo(BetaManagedAgentsAgentCustomToolUseEvent.Type.AGENT_CUSTOM_TOOL_USE)
        assertThat(betaManagedAgentsAgentCustomToolUseEvent.sessionThreadId())
            .contains("session_thread_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentCustomToolUseEvent =
            BetaManagedAgentsAgentCustomToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentCustomToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentCustomToolUseEvent.Type.AGENT_CUSTOM_TOOL_USE)
                .sessionThreadId("session_thread_id")
                .build()

        val roundtrippedBetaManagedAgentsAgentCustomToolUseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentCustomToolUseEvent),
                jacksonTypeRef<BetaManagedAgentsAgentCustomToolUseEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentCustomToolUseEvent)
            .isEqualTo(betaManagedAgentsAgentCustomToolUseEvent)
    }
}
