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

internal class BetaManagedAgentsSessionEventTest {

    @Test
    fun ofUserMessage() {
        val userMessage =
            BetaManagedAgentsUserMessageEvent.builder()
                .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val betaManagedAgentsSessionEvent = BetaManagedAgentsSessionEvent.ofUserMessage(userMessage)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).contains(userMessage)
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserMessage(
                BetaManagedAgentsUserMessageEvent.builder()
                    .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                    .addTextContent("Where is my order #1234?")
                    .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofUserInterrupt() {
        val userInterrupt =
            BetaManagedAgentsUserInterruptEvent.builder()
                .id("id")
                .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserInterrupt(userInterrupt)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).contains(userInterrupt)
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofUserInterruptRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserInterrupt(
                BetaManagedAgentsUserInterruptEvent.builder()
                    .id("id")
                    .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserToolConfirmation(userToolConfirmation)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation())
            .contains(userToolConfirmation)
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofUserToolConfirmationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserToolConfirmation(
                BetaManagedAgentsUserToolConfirmationEvent.builder()
                    .id("id")
                    .result(BetaManagedAgentsUserToolConfirmationEvent.Result.ALLOW)
                    .toolUseId("tool_use_id")
                    .type(BetaManagedAgentsUserToolConfirmationEvent.Type.USER_TOOL_CONFIRMATION)
                    .denyMessage("deny_message")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserCustomToolResult(userCustomToolResult)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult())
            .contains(userCustomToolResult)
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofUserCustomToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserCustomToolResult(
                BetaManagedAgentsUserCustomToolResultEvent.builder()
                    .id("id")
                    .customToolUseId("custom_tool_use_id")
                    .type(BetaManagedAgentsUserCustomToolResultEvent.Type.USER_CUSTOM_TOOL_RESULT)
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentCustomToolUse(agentCustomToolUse)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).contains(agentCustomToolUse)
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentCustomToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentCustomToolUse(
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

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMessage(agentMessage)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).contains(agentMessage)
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
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
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentThinking() {
        val agentThinking =
            BetaManagedAgentsAgentThinkingEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThinking(agentThinking)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).contains(agentThinking)
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThinking(
                BetaManagedAgentsAgentThinkingEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMcpToolUse(agentMcpToolUse)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).contains(agentMcpToolUse)
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentMcpToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMcpToolUse(
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

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMcpToolResult(agentMcpToolResult)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).contains(agentMcpToolResult)
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentMcpToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMcpToolResult(
                BetaManagedAgentsAgentMcpToolResultEvent.builder()
                    .id("id")
                    .mcpToolUseId("mcp_tool_use_id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsAgentMcpToolResultEvent.Type.AGENT_MCP_TOOL_RESULT)
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentToolUse(agentToolUse)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).contains(agentToolUse)
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentToolUse(
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

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentToolResult(agentToolResult)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).contains(agentToolResult)
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentToolResult(
                BetaManagedAgentsAgentToolResultEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .toolUseId("tool_use_id")
                    .type(BetaManagedAgentsAgentToolResultEvent.Type.AGENT_TOOL_RESULT)
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadContextCompacted(agentThreadContextCompacted)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted())
            .contains(agentThreadContextCompacted)
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofAgentThreadContextCompactedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadContextCompacted(
                BetaManagedAgentsAgentThreadContextCompactedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsAgentThreadContextCompactedEvent.Type
                            .AGENT_THREAD_CONTEXT_COMPACTED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionError(sessionError)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).contains(sessionError)
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionError(
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

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusRescheduled(sessionStatusRescheduled)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled())
            .contains(sessionStatusRescheduled)
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusRescheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusRescheduled(
                BetaManagedAgentsSessionStatusRescheduledEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsSessionStatusRescheduledEvent.Type
                            .SESSION_STATUS_RESCHEDULED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionStatusRunning() {
        val sessionStatusRunning =
            BetaManagedAgentsSessionStatusRunningEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionStatusRunningEvent.Type.SESSION_STATUS_RUNNING)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusRunning(sessionStatusRunning)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning())
            .contains(sessionStatusRunning)
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusRunning(
                BetaManagedAgentsSessionStatusRunningEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSessionStatusRunningEvent.Type.SESSION_STATUS_RUNNING)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusIdle(sessionStatusIdle)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).contains(sessionStatusIdle)
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusIdleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusIdle(
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

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionStatusTerminated() {
        val sessionStatusTerminated =
            BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusTerminated(sessionStatusTerminated)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated())
            .contains(sessionStatusTerminated)
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusTerminated(
                BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSpanModelRequestStart() {
        val spanModelRequestStart =
            BetaManagedAgentsSpanModelRequestStartEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanModelRequestStart(spanModelRequestStart)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart())
            .contains(spanModelRequestStart)
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSpanModelRequestStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanModelRequestStart(
                BetaManagedAgentsSpanModelRequestStartEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanModelRequestEnd(spanModelRequestEnd)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd())
            .contains(spanModelRequestEnd)
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
    }

    @Test
    fun ofSpanModelRequestEndRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanModelRequestEnd(
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

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionDeleted() {
        val sessionDeleted =
            BetaManagedAgentsSessionDeletedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionDeleted(sessionDeleted)

        assertThat(betaManagedAgentsSessionEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMessage()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThinking()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).contains(sessionDeleted)
    }

    @Test
    fun ofSessionDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionDeleted(
                BetaManagedAgentsSessionDeletedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
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
        val betaManagedAgentsSessionEvent =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsSessionEvent>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaManagedAgentsSessionEvent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
