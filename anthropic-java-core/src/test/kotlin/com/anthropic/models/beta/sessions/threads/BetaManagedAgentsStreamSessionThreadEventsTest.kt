// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentCustomToolUseEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentMcpToolResultEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentMcpToolUseEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentMessageEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentThinkingEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentThreadContextCompactedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentThreadMessageReceivedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentThreadMessageSentEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentToolResultEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentToolUseEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsRetryStatusRetrying
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionDeletedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionEndTurn
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionErrorEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionStatusIdleEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionStatusRescheduledEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionStatusRunningEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionStatusTerminatedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionThreadCreatedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionThreadStatusIdleEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionThreadStatusRescheduledEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionThreadStatusRunningEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionThreadStatusTerminatedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanModelRequestEndEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanModelRequestStartEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanModelUsage
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationEndEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationStartEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUnknownError
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserCustomToolResultEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserDefineOutcomeEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserInterruptEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserToolConfirmationEvent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsStreamSessionThreadEventsTest {

    @Test
    fun ofUserMessage() {
        val userMessage =
            BetaManagedAgentsUserMessageEvent.builder()
                .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserMessage(userMessage)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).contains(userMessage)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserMessage(
                BetaManagedAgentsUserMessageEvent.builder()
                    .id("sevt_011CZkZGOp0iBcp4kaQSihUmy")
                    .addTextContent("Where is my order #1234?")
                    .type(BetaManagedAgentsUserMessageEvent.Type.USER_MESSAGE)
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserInterrupt(userInterrupt)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt())
            .contains(userInterrupt)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofUserInterruptRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserInterrupt(
                BetaManagedAgentsUserInterruptEvent.builder()
                    .id("id")
                    .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserToolConfirmation(userToolConfirmation)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation())
            .contains(userToolConfirmation)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofUserToolConfirmationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserToolConfirmation(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserCustomToolResult(userCustomToolResult)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult())
            .contains(userCustomToolResult)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofUserCustomToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserCustomToolResult(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentCustomToolUse(agentCustomToolUse)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse())
            .contains(agentCustomToolUse)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentCustomToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentCustomToolUse(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentMessage(agentMessage)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).contains(agentMessage)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentMessage(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
    }

    @Test
    fun ofAgentThinking() {
        val agentThinking =
            BetaManagedAgentsAgentThinkingEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
                .build()

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentThinking(agentThinking)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking())
            .contains(agentThinking)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentThinking(
                BetaManagedAgentsAgentThinkingEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsAgentThinkingEvent.Type.AGENT_THINKING)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentMcpToolUse(agentMcpToolUse)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse())
            .contains(agentMcpToolUse)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentMcpToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentMcpToolUse(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentMcpToolResult(agentMcpToolResult)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult())
            .contains(agentMcpToolResult)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentMcpToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentMcpToolResult(
                BetaManagedAgentsAgentMcpToolResultEvent.builder()
                    .id("id")
                    .mcpToolUseId("mcp_tool_use_id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsAgentMcpToolResultEvent.Type.AGENT_MCP_TOOL_RESULT)
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentToolUse(agentToolUse)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).contains(agentToolUse)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentToolUse(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentToolResult(agentToolResult)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult())
            .contains(agentToolResult)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentToolResult(
                BetaManagedAgentsAgentToolResultEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .toolUseId("tool_use_id")
                    .type(BetaManagedAgentsAgentToolResultEvent.Type.AGENT_TOOL_RESULT)
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentThreadMessageReceived(
                agentThreadMessageReceived
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived())
            .contains(agentThreadMessageReceived)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentThreadMessageReceivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentThreadMessageReceived(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentThreadMessageSent(
                agentThreadMessageSent
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent())
            .contains(agentThreadMessageSent)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentThreadMessageSentRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentThreadMessageSent(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentThreadContextCompacted(
                agentThreadContextCompacted
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted())
            .contains(agentThreadContextCompacted)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofAgentThreadContextCompactedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofAgentThreadContextCompacted(
                BetaManagedAgentsAgentThreadContextCompactedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsAgentThreadContextCompactedEvent.Type
                            .AGENT_THREAD_CONTEXT_COMPACTED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionError(sessionError)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).contains(sessionError)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionError(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionStatusRescheduled(
                sessionStatusRescheduled
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled())
            .contains(sessionStatusRescheduled)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionStatusRescheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionStatusRescheduled(
                BetaManagedAgentsSessionStatusRescheduledEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsSessionStatusRescheduledEvent.Type
                            .SESSION_STATUS_RESCHEDULED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
    }

    @Test
    fun ofSessionStatusRunning() {
        val sessionStatusRunning =
            BetaManagedAgentsSessionStatusRunningEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionStatusRunningEvent.Type.SESSION_STATUS_RUNNING)
                .build()

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionStatusRunning(sessionStatusRunning)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning())
            .contains(sessionStatusRunning)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionStatusRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionStatusRunning(
                BetaManagedAgentsSessionStatusRunningEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSessionStatusRunningEvent.Type.SESSION_STATUS_RUNNING)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionStatusIdle(sessionStatusIdle)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle())
            .contains(sessionStatusIdle)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionStatusIdleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionStatusIdle(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
    }

    @Test
    fun ofSessionStatusTerminated() {
        val sessionStatusTerminated =
            BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED)
                .build()

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionStatusTerminated(
                sessionStatusTerminated
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated())
            .contains(sessionStatusTerminated)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionStatusTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionStatusTerminated(
                BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(
                        BetaManagedAgentsSessionStatusTerminatedEvent.Type.SESSION_STATUS_TERMINATED
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadCreated(sessionThreadCreated)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated())
            .contains(sessionThreadCreated)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionThreadCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadCreated(
                BetaManagedAgentsSessionThreadCreatedEvent.builder()
                    .id("sevt_011CZkZWXb7pJkx1shYaqoCu")
                    .agentName("Researcher")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .sessionThreadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                    .type(BetaManagedAgentsSessionThreadCreatedEvent.Type.SESSION_THREAD_CREATED)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanOutcomeEvaluationStart(
                spanOutcomeEvaluationStart
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart())
            .contains(spanOutcomeEvaluationStart)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSpanOutcomeEvaluationStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanOutcomeEvaluationStart(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanOutcomeEvaluationEnd(
                spanOutcomeEvaluationEnd
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd())
            .contains(spanOutcomeEvaluationEnd)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSpanOutcomeEvaluationEndRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanOutcomeEvaluationEnd(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
    }

    @Test
    fun ofSpanModelRequestStart() {
        val spanModelRequestStart =
            BetaManagedAgentsSpanModelRequestStartEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
                .build()

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanModelRequestStart(
                spanModelRequestStart
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart())
            .contains(spanModelRequestStart)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSpanModelRequestStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanModelRequestStart(
                BetaManagedAgentsSpanModelRequestStartEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanModelRequestEnd(spanModelRequestEnd)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd())
            .contains(spanModelRequestEnd)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSpanModelRequestEndRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanModelRequestEnd(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanOutcomeEvaluationOngoing(
                spanOutcomeEvaluationOngoing
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .contains(spanOutcomeEvaluationOngoing)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSpanOutcomeEvaluationOngoingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSpanOutcomeEvaluationOngoing(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserDefineOutcome(userDefineOutcome)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome())
            .contains(userDefineOutcome)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofUserDefineOutcomeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofUserDefineOutcome(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
    }

    @Test
    fun ofSessionDeleted() {
        val sessionDeleted =
            BetaManagedAgentsSessionDeletedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
                .build()

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionDeleted(sessionDeleted)

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted())
            .contains(sessionDeleted)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionDeleted(
                BetaManagedAgentsSessionDeletedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(BetaManagedAgentsSessionDeletedEvent.Type.SESSION_DELETED)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadStatusRunning(
                sessionThreadStatusRunning
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning())
            .contains(sessionThreadStatusRunning)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionThreadStatusRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadStatusRunning(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadStatusIdle(
                sessionThreadStatusIdle
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle())
            .contains(sessionThreadStatusIdle)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionThreadStatusIdleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadStatusIdle(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadStatusTerminated(
                sessionThreadStatusTerminated
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .contains(sessionThreadStatusTerminated)
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .isEmpty
    }

    @Test
    fun ofSessionThreadStatusTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadStatusTerminated(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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

        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadStatusRescheduled(
                sessionThreadStatusRescheduled
            )

        assertThat(betaManagedAgentsStreamSessionThreadEvents.userMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userCustomToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentCustomToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThinking()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentMcpToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolUse()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentToolResult()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageReceived()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadMessageSent()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.agentThreadContextCompacted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionError()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRescheduled()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionStatusTerminated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadCreated()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestStart()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanModelRequestEnd()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.spanOutcomeEvaluationOngoing())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionDeleted()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRunning()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusIdle()).isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusTerminated())
            .isEmpty
        assertThat(betaManagedAgentsStreamSessionThreadEvents.sessionThreadStatusRescheduled())
            .contains(sessionThreadStatusRescheduled)
    }

    @Test
    fun ofSessionThreadStatusRescheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStreamSessionThreadEvents =
            BetaManagedAgentsStreamSessionThreadEvents.ofSessionThreadStatusRescheduled(
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

        val roundtrippedBetaManagedAgentsStreamSessionThreadEvents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStreamSessionThreadEvents),
                jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStreamSessionThreadEvents)
            .isEqualTo(betaManagedAgentsStreamSessionThreadEvents)
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
        val betaManagedAgentsStreamSessionThreadEvents =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsStreamSessionThreadEvents>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsStreamSessionThreadEvents.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
