// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolUseEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolUseEvent =
            BetaManagedAgentsAgentToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentToolUseEvent.Type.AGENT_TOOL_USE)
                .evaluatedPermission(BetaManagedAgentsAgentToolUseEvent.EvaluatedPermission.ALLOW)
                .sessionThreadId("session_thread_id")
                .build()

        assertThat(betaManagedAgentsAgentToolUseEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentToolUseEvent.input())
            .isEqualTo(
                BetaManagedAgentsAgentToolUseEvent.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaManagedAgentsAgentToolUseEvent.name()).isEqualTo("name")
        assertThat(betaManagedAgentsAgentToolUseEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsAgentToolUseEvent.type())
            .isEqualTo(BetaManagedAgentsAgentToolUseEvent.Type.AGENT_TOOL_USE)
        assertThat(betaManagedAgentsAgentToolUseEvent.evaluatedPermission())
            .contains(BetaManagedAgentsAgentToolUseEvent.EvaluatedPermission.ALLOW)
        assertThat(betaManagedAgentsAgentToolUseEvent.sessionThreadId())
            .contains("session_thread_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolUseEvent =
            BetaManagedAgentsAgentToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentToolUseEvent.Type.AGENT_TOOL_USE)
                .evaluatedPermission(BetaManagedAgentsAgentToolUseEvent.EvaluatedPermission.ALLOW)
                .sessionThreadId("session_thread_id")
                .build()

        val roundtrippedBetaManagedAgentsAgentToolUseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolUseEvent),
                jacksonTypeRef<BetaManagedAgentsAgentToolUseEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolUseEvent)
            .isEqualTo(betaManagedAgentsAgentToolUseEvent)
    }
}
