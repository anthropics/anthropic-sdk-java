// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentMcpToolResultEventTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentMcpToolResultEvent =
            BetaManagedAgentsAgentMcpToolResultEvent.builder()
                .id("id")
                .mcpToolUseId("mcp_tool_use_id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentMcpToolResultEvent.Type.AGENT_MCP_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        assertThat(betaManagedAgentsAgentMcpToolResultEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentMcpToolResultEvent.mcpToolUseId())
            .isEqualTo("mcp_tool_use_id")
        assertThat(betaManagedAgentsAgentMcpToolResultEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsAgentMcpToolResultEvent.type())
            .isEqualTo(BetaManagedAgentsAgentMcpToolResultEvent.Type.AGENT_MCP_TOOL_RESULT)
        assertThat(betaManagedAgentsAgentMcpToolResultEvent.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgentMcpToolResultEvent.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentMcpToolResultEvent.isError()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentMcpToolResultEvent =
            BetaManagedAgentsAgentMcpToolResultEvent.builder()
                .id("id")
                .mcpToolUseId("mcp_tool_use_id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentMcpToolResultEvent.Type.AGENT_MCP_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val roundtrippedBetaManagedAgentsAgentMcpToolResultEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentMcpToolResultEvent),
                jacksonTypeRef<BetaManagedAgentsAgentMcpToolResultEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentMcpToolResultEvent)
            .isEqualTo(betaManagedAgentsAgentMcpToolResultEvent)
    }
}
