// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentMcpToolUseEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentMcpToolUseEvent =
            BetaManagedAgentsAgentMcpToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentMcpToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .mcpServerName("mcp_server_name")
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentMcpToolUseEvent.Type.AGENT_MCP_TOOL_USE)
                .evaluatedPermission(
                    BetaManagedAgentsAgentMcpToolUseEvent.EvaluatedPermission.ALLOW
                )
                .sessionThreadId("session_thread_id")
                .build()

        assertThat(betaManagedAgentsAgentMcpToolUseEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentMcpToolUseEvent.input())
            .isEqualTo(
                BetaManagedAgentsAgentMcpToolUseEvent.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaManagedAgentsAgentMcpToolUseEvent.mcpServerName())
            .isEqualTo("mcp_server_name")
        assertThat(betaManagedAgentsAgentMcpToolUseEvent.name()).isEqualTo("name")
        assertThat(betaManagedAgentsAgentMcpToolUseEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsAgentMcpToolUseEvent.type())
            .isEqualTo(BetaManagedAgentsAgentMcpToolUseEvent.Type.AGENT_MCP_TOOL_USE)
        assertThat(betaManagedAgentsAgentMcpToolUseEvent.evaluatedPermission())
            .contains(BetaManagedAgentsAgentMcpToolUseEvent.EvaluatedPermission.ALLOW)
        assertThat(betaManagedAgentsAgentMcpToolUseEvent.sessionThreadId())
            .contains("session_thread_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentMcpToolUseEvent =
            BetaManagedAgentsAgentMcpToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentMcpToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .mcpServerName("mcp_server_name")
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentMcpToolUseEvent.Type.AGENT_MCP_TOOL_USE)
                .evaluatedPermission(
                    BetaManagedAgentsAgentMcpToolUseEvent.EvaluatedPermission.ALLOW
                )
                .sessionThreadId("session_thread_id")
                .build()

        val roundtrippedBetaManagedAgentsAgentMcpToolUseEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentMcpToolUseEvent),
                jacksonTypeRef<BetaManagedAgentsAgentMcpToolUseEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentMcpToolUseEvent)
            .isEqualTo(betaManagedAgentsAgentMcpToolUseEvent)
    }
}
