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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                    .sessionThreadId("session_thread_id")
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
                .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                    .sessionThreadId("session_thread_id")
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
                .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                    .sessionThreadId("session_thread_id")
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
                .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                    .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                    .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
                    .sessionThreadId("session_thread_id")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
    fun ofAgentThreadMessageReceived() {
        val agentThreadMessageReceived =
            BetaManagedAgentsAgentThreadMessageReceivedEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .fromSessionThreadId("from_session_thread_id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(
                    BetaManagedAgentsAgentThreadMessageReceivedEvent.Type
                        .AGENT_THREAD_MESSAGE_RECEIVED
                )
                .fromAgentName("from_agent_name")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadMessageReceived(agentThreadMessageReceived)

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived())
            .contains(agentThreadMessageReceived)
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofAgentThreadMessageReceivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadMessageReceived(
                BetaManagedAgentsAgentThreadMessageReceivedEvent.builder()
                    .id("id")
                    .addTextContent("Where is my order #1234?")
                    .fromSessionThreadId("from_session_thread_id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsAgentThreadMessageReceivedEvent.Type
                            .AGENT_THREAD_MESSAGE_RECEIVED
                    )
                    .fromAgentName("from_agent_name")
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
    fun ofAgentThreadMessageSent() {
        val agentThreadMessageSent =
            BetaManagedAgentsAgentThreadMessageSentEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .toSessionThreadId("to_session_thread_id")
                .type(BetaManagedAgentsAgentThreadMessageSentEvent.Type.AGENT_THREAD_MESSAGE_SENT)
                .toAgentName("to_agent_name")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadMessageSent(agentThreadMessageSent)

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent())
            .contains(agentThreadMessageSent)
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofAgentThreadMessageSentRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadMessageSent(
                BetaManagedAgentsAgentThreadMessageSentEvent.builder()
                    .id("id")
                    .addTextContent("Where is my order #1234?")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .toSessionThreadId("to_session_thread_id")
                    .type(
                        BetaManagedAgentsAgentThreadMessageSentEvent.Type.AGENT_THREAD_MESSAGE_SENT
                    )
                    .toAgentName("to_agent_name")
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted())
            .contains(agentThreadContextCompacted)
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).contains(sessionError)
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled())
            .contains(sessionStatusRescheduled)
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning())
            .contains(sessionStatusRunning)
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).contains(sessionStatusIdle)
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated())
            .contains(sessionStatusTerminated)
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
    fun ofSessionThreadCreated() {
        val sessionThreadCreated =
            BetaManagedAgentsSessionThreadCreatedEvent.builder()
                .id("sevt_011CZkZWXb7pJkx1shYaqoCu")
                .agentName("Researcher")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                .type(BetaManagedAgentsSessionThreadCreatedEvent.Type.SESSION_THREAD_CREATED)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadCreated(sessionThreadCreated)

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated())
            .contains(sessionThreadCreated)
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofSessionThreadCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadCreated(
                BetaManagedAgentsSessionThreadCreatedEvent.builder()
                    .id("sevt_011CZkZWXb7pJkx1shYaqoCu")
                    .agentName("Researcher")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                    .type(BetaManagedAgentsSessionThreadCreatedEvent.Type.SESSION_THREAD_CREATED)
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
    fun ofSpanOutcomeEvaluationStart() {
        val spanOutcomeEvaluationStart =
            BetaManagedAgentsSpanOutcomeEvaluationStartEvent.builder()
                .id("sevt_011CZkZTUy4mGhu8peVXnlzr")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationStartEvent.Type
                        .SPAN_OUTCOME_EVALUATION_START
                )
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationStart(spanOutcomeEvaluationStart)

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart())
            .contains(spanOutcomeEvaluationStart)
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofSpanOutcomeEvaluationStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationStart(
                BetaManagedAgentsSpanOutcomeEvaluationStartEvent.builder()
                    .id("sevt_011CZkZTUy4mGhu8peVXnlzr")
                    .iteration(0)
                    .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                    .type(
                        BetaManagedAgentsSpanOutcomeEvaluationStartEvent.Type
                            .SPAN_OUTCOME_EVALUATION_START
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
    fun ofSpanOutcomeEvaluationEnd() {
        val spanOutcomeEvaluationEnd =
            BetaManagedAgentsSpanOutcomeEvaluationEndEvent.builder()
                .id("sevt_011CZkZUVz5nHiv9qfWYomas")
                .explanation("All five sections present with inline citations.")
                .iteration(0)
                .outcomeEvaluationStartId("sevt_011CZkZTUy4mGhu8peVXnlzr")
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
                .result("satisfied")
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationEndEvent.Type.SPAN_OUTCOME_EVALUATION_END
                )
                .usage(
                    BetaManagedAgentsSpanModelUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(1536)
                        .inputTokens(1842)
                        .outputTokens(213)
                        .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                        .build()
                )
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationEnd(spanOutcomeEvaluationEnd)

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd())
            .contains(spanOutcomeEvaluationEnd)
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofSpanOutcomeEvaluationEndRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationEnd(
                BetaManagedAgentsSpanOutcomeEvaluationEndEvent.builder()
                    .id("sevt_011CZkZUVz5nHiv9qfWYomas")
                    .explanation("All five sections present with inline citations.")
                    .iteration(0)
                    .outcomeEvaluationStartId("sevt_011CZkZTUy4mGhu8peVXnlzr")
                    .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
                    .result("satisfied")
                    .type(
                        BetaManagedAgentsSpanOutcomeEvaluationEndEvent.Type
                            .SPAN_OUTCOME_EVALUATION_END
                    )
                    .usage(
                        BetaManagedAgentsSpanModelUsage.builder()
                            .cacheCreationInputTokens(0)
                            .cacheReadInputTokens(1536)
                            .inputTokens(1842)
                            .outputTokens(213)
                            .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                            .build()
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart())
            .contains(spanModelRequestStart)
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd())
            .contains(spanModelRequestEnd)
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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
    fun ofSpanOutcomeEvaluationOngoing() {
        val spanOutcomeEvaluationOngoing =
            BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.builder()
                .id("sevt_011CZkZbCG2uOpc6xmDfvTzh")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.Type
                        .SPAN_OUTCOME_EVALUATION_ONGOING
                )
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationOngoing(
                spanOutcomeEvaluationOngoing
            )

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing())
            .contains(spanOutcomeEvaluationOngoing)
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofSpanOutcomeEvaluationOngoingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationOngoing(
                BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.builder()
                    .id("sevt_011CZkZbCG2uOpc6xmDfvTzh")
                    .iteration(0)
                    .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                    .type(
                        BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.Type
                            .SPAN_OUTCOME_EVALUATION_ONGOING
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
    fun ofUserDefineOutcome() {
        val userDefineOutcome =
            BetaManagedAgentsUserDefineOutcomeEvent.builder()
                .id("sevt_011CZkZSTx3lFgt7odUWmkyq")
                .description("Produce a 2-page summary as summary.md")
                .maxIterations(3)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .textRubric("Must cover all five sections; cite sources inline.")
                .type(BetaManagedAgentsUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserDefineOutcome(userDefineOutcome)

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).contains(userDefineOutcome)
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofUserDefineOutcomeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserDefineOutcome(
                BetaManagedAgentsUserDefineOutcomeEvent.builder()
                    .id("sevt_011CZkZSTx3lFgt7odUWmkyq")
                    .description("Produce a 2-page summary as summary.md")
                    .maxIterations(3)
                    .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                    .textRubric("Must cover all five sections; cite sources inline.")
                    .type(BetaManagedAgentsUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).contains(sessionDeleted)
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
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

    @Test
    fun ofSessionThreadStatusRunning() {
        val sessionThreadStatusRunning =
            BetaManagedAgentsSessionThreadStatusRunningEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusRunningEvent.Type
                        .SESSION_THREAD_STATUS_RUNNING
                )
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusRunning(sessionThreadStatusRunning)

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning())
            .contains(sessionThreadStatusRunning)
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofSessionThreadStatusRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusRunning(
                BetaManagedAgentsSessionThreadStatusRunningEvent.builder()
                    .id("id")
                    .agentName("agent_name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .type(
                        BetaManagedAgentsSessionThreadStatusRunningEvent.Type
                            .SESSION_THREAD_STATUS_RUNNING
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
    fun ofSessionThreadStatusIdle() {
        val sessionThreadStatusIdle =
            BetaManagedAgentsSessionThreadStatusIdleEvent.builder()
                .id("sevt_011CZkZXYc8qKly2tiZbrpDv")
                .agentName("Researcher")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                .stopReason(
                    BetaManagedAgentsSessionEndTurn.builder()
                        .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                        .build()
                )
                .type(BetaManagedAgentsSessionThreadStatusIdleEvent.Type.SESSION_THREAD_STATUS_IDLE)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusIdle(sessionThreadStatusIdle)

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle())
            .contains(sessionThreadStatusIdle)
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofSessionThreadStatusIdleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusIdle(
                BetaManagedAgentsSessionThreadStatusIdleEvent.builder()
                    .id("sevt_011CZkZXYc8qKly2tiZbrpDv")
                    .agentName("Researcher")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                    .stopReason(
                        BetaManagedAgentsSessionEndTurn.builder()
                            .type(BetaManagedAgentsSessionEndTurn.Type.END_TURN)
                            .build()
                    )
                    .type(
                        BetaManagedAgentsSessionThreadStatusIdleEvent.Type
                            .SESSION_THREAD_STATUS_IDLE
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
    fun ofSessionThreadStatusTerminated() {
        val sessionThreadStatusTerminated =
            BetaManagedAgentsSessionThreadStatusTerminatedEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusTerminatedEvent.Type
                        .SESSION_THREAD_STATUS_TERMINATED
                )
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusTerminated(
                sessionThreadStatusTerminated
            )

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated())
            .contains(sessionThreadStatusTerminated)
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled()).isEmpty
    }

    @Test
    fun ofSessionThreadStatusTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusTerminated(
                BetaManagedAgentsSessionThreadStatusTerminatedEvent.builder()
                    .id("id")
                    .agentName("agent_name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .type(
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent.Type
                            .SESSION_THREAD_STATUS_TERMINATED
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
    fun ofSessionThreadStatusRescheduled() {
        val sessionThreadStatusRescheduled =
            BetaManagedAgentsSessionThreadStatusRescheduledEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .type(
                    BetaManagedAgentsSessionThreadStatusRescheduledEvent.Type
                        .SESSION_THREAD_STATUS_RESCHEDULED
                )
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusRescheduled(
                sessionThreadStatusRescheduled
            )

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
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled())
            .contains(sessionThreadStatusRescheduled)
    }

    @Test
    fun ofSessionThreadStatusRescheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusRescheduled(
                BetaManagedAgentsSessionThreadStatusRescheduledEvent.builder()
                    .id("id")
                    .agentName("agent_name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .type(
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent.Type
                            .SESSION_THREAD_STATUS_RESCHEDULED
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
