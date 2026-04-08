// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsStreamSessionEventsTest {

    @Test
    fun ofUserMessage() {
        val userMessage =
            BetaManagedAgentsUserMessageEvent.builder()
                .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofUserMessage(userMessage)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).contains(userMessage)
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofUserMessage(
                BetaManagedAgentsUserMessageEvent.builder()
                    .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                    .addTextContent("Where is my order #1234?")
                    .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofUserInterrupt() {
        val userInterrupt =
            BetaManagedAgentsUserInterruptEvent.builder()
                .id("id")
                .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofUserInterrupt(userInterrupt)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).contains(userInterrupt)
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofUserInterruptRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofUserInterrupt(
                BetaManagedAgentsUserInterruptEvent.builder()
                    .id("id")
                    .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofUserToolConfirmation() {
        val userToolConfirmation =
            BetaManagedAgentsUserToolConfirmationEvent.builder()
                .id("id")
                .result(BetaManagedAgentsUserToolConfirmationEvent.Result.ALLOW)
                .toolUseId("tool_use_id")
                .type(BetaManagedAgentsUserToolConfirmationEvent.Type.USER_TOOL_CONFIRMATION)
                .denyMessage("deny_message")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofUserToolConfirmation(userToolConfirmation)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation())
            .contains(userToolConfirmation)
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofUserToolConfirmationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofUserToolConfirmation(
                BetaManagedAgentsUserToolConfirmationEvent.builder()
                    .id("id")
                    .result(BetaManagedAgentsUserToolConfirmationEvent.Result.ALLOW)
                    .toolUseId("tool_use_id")
                    .type(BetaManagedAgentsUserToolConfirmationEvent.Type.USER_TOOL_CONFIRMATION)
                    .denyMessage("deny_message")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofUserCustomToolResult() {
        val userCustomToolResult =
            BetaManagedAgentsUserCustomToolResultEvent.builder()
                .id("id")
                .customToolUseId("custom_tool_use_id")
                .type(BetaManagedAgentsUserCustomToolResultEvent.Type.USER_CUSTOM_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofUserCustomToolResult(userCustomToolResult)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult())
            .contains(userCustomToolResult)
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofUserCustomToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofUserCustomToolResult(
                BetaManagedAgentsUserCustomToolResultEvent.builder()
                    .id("id")
                    .customToolUseId("custom_tool_use_id")
                    .type(BetaManagedAgentsUserCustomToolResultEvent.Type.USER_CUSTOM_TOOL_RESULT)
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofAgentCustomToolUse() {
        val agentCustomToolUse =
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
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentCustomToolUse(agentCustomToolUse)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse())
            .contains(agentCustomToolUse)
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentCustomToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentCustomToolUse(
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
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofAgentMessage() {
        val agentMessage =
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

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentMessage(agentMessage)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).contains(agentMessage)
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentMessage(
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

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofAgentThinking() {
        val agentThinking =
            BetaManagedAgentsAgentThinkingEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentThinking(agentThinking)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).contains(agentThinking)
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentThinking(
                BetaManagedAgentsAgentThinkingEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofAgentMcpToolUse() {
        val agentMcpToolUse =
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
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentMcpToolUse(agentMcpToolUse)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).contains(agentMcpToolUse)
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentMcpToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentMcpToolUse(
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
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofAgentMcpToolResult() {
        val agentMcpToolResult =
            BetaManagedAgentsAgentMcpToolResultEvent.builder()
                .id("id")
                .mcpToolUseId("mcp_tool_use_id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentMcpToolResultEvent.Type.AGENT_MCP_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentMcpToolResult(agentMcpToolResult)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult())
            .contains(agentMcpToolResult)
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentMcpToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentMcpToolResult(
                BetaManagedAgentsAgentMcpToolResultEvent.builder()
                    .id("id")
                    .mcpToolUseId("mcp_tool_use_id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsAgentMcpToolResultEvent.Type.AGENT_MCP_TOOL_RESULT)
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofAgentToolUse() {
        val agentToolUse =
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
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentToolUse(agentToolUse)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).contains(agentToolUse)
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentToolUse(
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
                    .evaluatedPermission(
                        BetaManagedAgentsAgentToolUseEvent.EvaluatedPermission.ALLOW
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofAgentToolResult() {
        val agentToolResult =
            BetaManagedAgentsAgentToolResultEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .toolUseId("tool_use_id")
                .type(BetaManagedAgentsAgentToolResultEvent.Type.AGENT_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentToolResult(agentToolResult)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).contains(agentToolResult)
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentToolResult(
                BetaManagedAgentsAgentToolResultEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .toolUseId("tool_use_id")
                    .type(BetaManagedAgentsAgentToolResultEvent.Type.AGENT_TOOL_RESULT)
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofAgentThreadContextCompacted() {
        val agentThreadContextCompacted =
            BetaManagedAgentsAgentThreadContextCompactedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(
                    BetaManagedAgentsAgentThreadContextCompactedEvent.Type
                        .AGENT_THREAD_CONTEXT_COMPACTED
                )
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentThreadContextCompacted(
                agentThreadContextCompacted
            )

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted())
            .contains(agentThreadContextCompacted)
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentThreadContextCompactedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofAgentThreadContextCompacted(
                BetaManagedAgentsAgentThreadContextCompactedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsAgentThreadContextCompactedEvent.Type
                            .AGENT_THREAD_CONTEXT_COMPACTED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofSessionError() {
        val sessionError =
            BetaManagedAgentsSessionErrorEvent.builder()
                .id("id")
                .error(
                    BetaManagedAgentsUnknownError.builder()
                        .message("message")
                        .retryStatus(
                            BetaManagedAgentsRetryStatusRetrying.builder()
                                .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                                .build()
                        )
                        .type(BetaManagedAgentsUnknownError.Type.UNKNOWN_ERROR)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionErrorEvent.Type.SESSION_ERROR)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionError(sessionError)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).contains(sessionError)
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionError(
                BetaManagedAgentsSessionErrorEvent.builder()
                    .id("id")
                    .error(
                        BetaManagedAgentsUnknownError.builder()
                            .message("message")
                            .retryStatus(
                                BetaManagedAgentsRetryStatusRetrying.builder()
                                    .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                                    .build()
                            )
                            .type(BetaManagedAgentsUnknownError.Type.UNKNOWN_ERROR)
                            .build()
                    )
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSessionErrorEvent.Type.SESSION_ERROR)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofSessionStatusRescheduled() {
        val sessionStatusRescheduled =
            BetaManagedAgentsSessionStatusRescheduledEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(
                    BetaManagedAgentsSessionStatusRescheduledEvent.Type.SESSION_STATUS_RESCHEDULED
                )
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionStatusRescheduled(
                sessionStatusRescheduled
            )

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled())
            .contains(sessionStatusRescheduled)
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusRescheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionStatusRescheduled(
                BetaManagedAgentsSessionStatusRescheduledEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsSessionStatusRescheduledEvent.Type
                            .SESSION_STATUS_RESCHEDULED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofSessionStatusRunning() {
        val sessionStatusRunning =
            BetaManagedAgentsSessionStatusRunningEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionStatusRunningEvent.Type.SESSION_STATUS_RUNNING)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionStatusRunning(sessionStatusRunning)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning())
            .contains(sessionStatusRunning)
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionStatusRunning(
                BetaManagedAgentsSessionStatusRunningEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSessionStatusRunningEvent.Type.SESSION_STATUS_RUNNING)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofSessionStatusIdle() {
        val sessionStatusIdle =
            BetaManagedAgentsSessionStatusIdleEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .stopReason(
                    BetaManagedAgentsSessionEndTurn.builder()
                        .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                        .build()
                )
                .type(BetaManagedAgentsSessionStatusIdleEvent.Type.SESSION_STATUS_IDLE)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionStatusIdle(sessionStatusIdle)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle())
            .contains(sessionStatusIdle)
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusIdleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionStatusIdle(
                BetaManagedAgentsSessionStatusIdleEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .stopReason(
                        BetaManagedAgentsSessionEndTurn.builder()
                            .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                            .build()
                    )
                    .type(BetaManagedAgentsSessionStatusIdleEvent.Type.SESSION_STATUS_IDLE)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofSessionStatusTerminated() {
        val sessionStatusTerminated =
            BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionStatusTerminated(sessionStatusTerminated)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated())
            .contains(sessionStatusTerminated)
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionStatusTerminated(
                BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofSpanModelRequestStart() {
        val spanModelRequestStart =
            BetaManagedAgentsSpanModelRequestStartEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSpanModelRequestStart(spanModelRequestStart)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart())
            .contains(spanModelRequestStart)
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSpanModelRequestStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSpanModelRequestStart(
                BetaManagedAgentsSpanModelRequestStartEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofSpanModelRequestEnd() {
        val spanModelRequestEnd =
            BetaManagedAgentsSpanModelRequestEndEvent.builder()
                .id("id")
                .isError(true)
                .modelRequestStartId("model_request_start_id")
                .modelUsage(
                    BetaManagedAgentsSpanModelUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(0)
                        .inputTokens(0)
                        .outputTokens(0)
                        .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSpanModelRequestEndEvent.Type.SPAN_MODEL_REQUEST_END)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSpanModelRequestEnd(spanModelRequestEnd)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd())
            .contains(spanModelRequestEnd)
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSpanModelRequestEndRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSpanModelRequestEnd(
                BetaManagedAgentsSpanModelRequestEndEvent.builder()
                    .id("id")
                    .isError(true)
                    .modelRequestStartId("model_request_start_id")
                    .modelUsage(
                        BetaManagedAgentsSpanModelUsage.builder()
                            .cacheCreationInputTokens(0)
                            .cacheReadInputTokens(0)
                            .inputTokens(0)
                            .outputTokens(0)
                            .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                            .build()
                    )
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSpanModelRequestEndEvent.Type.SPAN_MODEL_REQUEST_END)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    @Test
    fun ofSessionDeleted() {
        val sessionDeleted =
            BetaManagedAgentsSessionDeletedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
                .build()

        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionDeleted(sessionDeleted)

        assertThat(betaManagedAgentsStreamSessionEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionEvents.sessionDeleted()).contains(sessionDeleted)
    }

    @Test
    fun ofSessionDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionEvents =
            BetaManagedAgentsStreamSessionEvents.ofSessionDeleted(
                BetaManagedAgentsSessionDeletedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionEvents)
            .isEqualTo(betaManagedAgentsStreamSessionEvents)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaManagedAgentsStreamSessionEvents =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsStreamSessionEvents.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
