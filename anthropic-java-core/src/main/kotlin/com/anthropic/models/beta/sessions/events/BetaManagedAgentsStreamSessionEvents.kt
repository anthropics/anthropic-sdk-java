// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsBudgetLimit
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeltaEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsSessionUpdatedEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsSessionUsageEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsStartEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsStartEventPreview
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemMessageEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsUserToolResultEvent
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Server-sent event in the session stream. */
@JsonDeserialize(using = BetaManagedAgentsStreamSessionEvents.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsStreamSessionEvents.Serializer::class)
class BetaManagedAgentsStreamSessionEvents
private constructor(
    private val userMessage: BetaManagedAgentsUserMessageEvent? = null,
    private val userInterrupt: BetaManagedAgentsUserInterruptEvent? = null,
    private val userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent? = null,
    private val userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent? = null,
    private val agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent? = null,
    private val agentMessage: BetaManagedAgentsAgentMessageEvent? = null,
    private val agentThinking: BetaManagedAgentsAgentThinkingEvent? = null,
    private val agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent? = null,
    private val agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent? = null,
    private val agentToolUse: BetaManagedAgentsAgentToolUseEvent? = null,
    private val agentToolResult: BetaManagedAgentsAgentToolResultEvent? = null,
    private val agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent? =
        null,
    private val agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent? = null,
    private val agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent? =
        null,
    private val sessionError: BetaManagedAgentsSessionErrorEvent? = null,
    private val sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent? = null,
    private val sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent? = null,
    private val sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent? = null,
    private val sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent? = null,
    private val sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent? = null,
    private val spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent? =
        null,
    private val spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent? = null,
    private val spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent? = null,
    private val spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent? = null,
    private val spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent? =
        null,
    private val userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent? = null,
    private val sessionDeleted: BetaManagedAgentsSessionDeletedEvent? = null,
    private val sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent? =
        null,
    private val sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent? = null,
    private val sessionThreadStatusTerminated:
        BetaManagedAgentsSessionThreadStatusTerminatedEvent? =
        null,
    private val userToolResult: BetaManagedAgentsUserToolResultEvent? = null,
    private val sessionThreadStatusRescheduled:
        BetaManagedAgentsSessionThreadStatusRescheduledEvent? =
        null,
    private val sessionUpdated: BetaManagedAgentsSessionUpdatedEvent? = null,
    private val eventStart: BetaManagedAgentsStartEvent? = null,
    private val eventDelta: BetaManagedAgentsDeltaEvent? = null,
    private val systemMessage: BetaManagedAgentsSystemMessageEvent? = null,
    private val sessionUsage: BetaManagedAgentsSessionUsageEvent? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Type = Type.USER_MESSAGE

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Type = Type.USER_INTERRUPT

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Type = Type.USER_TOOL_CONFIRMATION

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Type = Type.USER_CUSTOM_TOOL_RESULT

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Type = Type.AGENT_CUSTOM_TOOL_USE

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Type = Type.AGENT_MESSAGE

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Type = Type.AGENT_THINKING

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Type = Type.AGENT_MCP_TOOL_USE

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Type = Type.AGENT_MCP_TOOL_RESULT

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Type = Type.AGENT_TOOL_USE

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Type = Type.AGENT_TOOL_RESULT

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Type = Type.AGENT_THREAD_MESSAGE_RECEIVED

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Type = Type.AGENT_THREAD_MESSAGE_SENT

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Type = Type.AGENT_THREAD_CONTEXT_COMPACTED

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Type = Type.SESSION_ERROR

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Type = Type.SESSION_STATUS_RESCHEDULED

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Type = Type.SESSION_STATUS_RUNNING

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Type = Type.SESSION_STATUS_IDLE

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Type = Type.SESSION_STATUS_TERMINATED

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Type = Type.SESSION_THREAD_CREATED

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Type = Type.SPAN_OUTCOME_EVALUATION_START

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Type = Type.SPAN_OUTCOME_EVALUATION_END

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Type = Type.SPAN_MODEL_REQUEST_START

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Type = Type.SPAN_MODEL_REQUEST_END

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Type = Type.SPAN_OUTCOME_EVALUATION_ONGOING

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Type = Type.USER_DEFINE_OUTCOME

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Type = Type.SESSION_DELETED

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Type = Type.SESSION_THREAD_STATUS_RUNNING

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Type = Type.SESSION_THREAD_STATUS_IDLE

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Type = Type.SESSION_THREAD_STATUS_TERMINATED

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Type = Type.USER_TOOL_RESULT

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Type = Type.SESSION_THREAD_STATUS_RESCHEDULED

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Type = Type.SESSION_UPDATED

                override fun visitEventStart(eventStart: BetaManagedAgentsStartEvent): Type =
                    Type.EVENT_START

                override fun visitEventDelta(eventDelta: BetaManagedAgentsDeltaEvent): Type =
                    Type.EVENT_DELTA

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Type = Type.SYSTEM_MESSAGE

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Type = Type.SESSION_USAGE

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun id(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<String> = Optional.of(userMessage.id())

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<String> = Optional.of(userInterrupt.id())

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<String> = Optional.of(userToolConfirmation.id())

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<String> = Optional.of(userCustomToolResult.id())

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<String> = Optional.of(agentCustomToolUse.id())

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<String> = Optional.of(agentMessage.id())

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<String> = Optional.of(agentThinking.id())

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<String> = Optional.of(agentMcpToolUse.id())

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<String> = Optional.of(agentMcpToolResult.id())

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<String> = Optional.of(agentToolUse.id())

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<String> = Optional.of(agentToolResult.id())

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<String> = Optional.of(agentThreadMessageReceived.id())

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<String> = Optional.of(agentThreadMessageSent.id())

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<String> = Optional.of(agentThreadContextCompacted.id())

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<String> = Optional.of(sessionError.id())

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<String> = Optional.of(sessionStatusRescheduled.id())

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<String> = Optional.of(sessionStatusRunning.id())

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<String> = Optional.of(sessionStatusIdle.id())

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<String> = Optional.of(sessionStatusTerminated.id())

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<String> = Optional.of(sessionThreadCreated.id())

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<String> = Optional.of(spanOutcomeEvaluationStart.id())

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<String> = Optional.of(spanOutcomeEvaluationEnd.id())

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<String> = Optional.of(spanModelRequestStart.id())

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<String> = Optional.of(spanModelRequestEnd.id())

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<String> = Optional.of(spanOutcomeEvaluationOngoing.id())

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<String> = Optional.of(userDefineOutcome.id())

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<String> = Optional.of(sessionDeleted.id())

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<String> = Optional.of(sessionThreadStatusRunning.id())

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<String> = Optional.of(sessionThreadStatusIdle.id())

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<String> = Optional.of(sessionThreadStatusTerminated.id())

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<String> = Optional.of(userToolResult.id())

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<String> = Optional.of(sessionThreadStatusRescheduled.id())

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<String> = Optional.of(sessionUpdated.id())

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<String> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<String> = Optional.of(systemMessage.id())

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<String> = Optional.of(sessionUsage.id())

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("id").asKnown()
            }
        )

    fun processedAt(): Optional<OffsetDateTime> =
        accept(
            object : Visitor<Optional<OffsetDateTime>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<OffsetDateTime> = userMessage.processedAt()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<OffsetDateTime> = userInterrupt.processedAt()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<OffsetDateTime> = userToolConfirmation.processedAt()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<OffsetDateTime> = userCustomToolResult.processedAt()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<OffsetDateTime> = Optional.of(agentCustomToolUse.processedAt())

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<OffsetDateTime> = Optional.of(agentMessage.processedAt())

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<OffsetDateTime> = Optional.of(agentThinking.processedAt())

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<OffsetDateTime> = Optional.of(agentMcpToolUse.processedAt())

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<OffsetDateTime> = Optional.of(agentMcpToolResult.processedAt())

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<OffsetDateTime> = Optional.of(agentToolUse.processedAt())

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<OffsetDateTime> = Optional.of(agentToolResult.processedAt())

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<OffsetDateTime> = Optional.of(agentThreadMessageReceived.processedAt())

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<OffsetDateTime> = Optional.of(agentThreadMessageSent.processedAt())

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<OffsetDateTime> = Optional.of(agentThreadContextCompacted.processedAt())

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionError.processedAt())

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionStatusRescheduled.processedAt())

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionStatusRunning.processedAt())

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionStatusIdle.processedAt())

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionStatusTerminated.processedAt())

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionThreadCreated.processedAt())

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<OffsetDateTime> = Optional.of(spanOutcomeEvaluationStart.processedAt())

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<OffsetDateTime> = Optional.of(spanOutcomeEvaluationEnd.processedAt())

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<OffsetDateTime> = Optional.of(spanModelRequestStart.processedAt())

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<OffsetDateTime> = Optional.of(spanModelRequestEnd.processedAt())

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<OffsetDateTime> =
                    Optional.of(spanOutcomeEvaluationOngoing.processedAt())

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<OffsetDateTime> = Optional.of(userDefineOutcome.processedAt())

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionDeleted.processedAt())

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionThreadStatusRunning.processedAt())

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionThreadStatusIdle.processedAt())

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<OffsetDateTime> =
                    Optional.of(sessionThreadStatusTerminated.processedAt())

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<OffsetDateTime> = userToolResult.processedAt()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<OffsetDateTime> =
                    Optional.of(sessionThreadStatusRescheduled.processedAt())

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionUpdated.processedAt())

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<OffsetDateTime> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<OffsetDateTime> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<OffsetDateTime> = systemMessage.processedAt()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<OffsetDateTime> = Optional.of(sessionUsage.processedAt())

                override fun unknown(json: JsonValue?): Optional<OffsetDateTime> =
                    json.getProperty<OffsetDateTime>("processed_at").asKnown()
            }
        )

    fun sessionThreadId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<String> = userInterrupt.sessionThreadId()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<String> = userToolConfirmation.sessionThreadId()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<String> = userCustomToolResult.sessionThreadId()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<String> = agentCustomToolUse.sessionThreadId()

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<String> = agentMcpToolUse.sessionThreadId()

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<String> = agentToolUse.sessionThreadId()

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<String> = Optional.of(sessionThreadCreated.sessionThreadId())

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<String> = Optional.of(sessionThreadStatusRunning.sessionThreadId())

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<String> = Optional.of(sessionThreadStatusIdle.sessionThreadId())

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<String> = Optional.of(sessionThreadStatusTerminated.sessionThreadId())

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<String> = userToolResult.sessionThreadId()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<String> = Optional.of(sessionThreadStatusRescheduled.sessionThreadId())

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<String> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("session_thread_id").asKnown()
            }
        )

    fun toolUseId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<String> = Optional.of(userToolConfirmation.toolUseId())

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<String> = Optional.of(agentToolResult.toolUseId())

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<String> = Optional.of(userToolResult.toolUseId())

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<String> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("tool_use_id").asKnown()
            }
        )

    fun isError(): Optional<Boolean> =
        accept(
            object : Visitor<Optional<Boolean>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<Boolean> = userCustomToolResult.isError()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<Boolean> = agentMcpToolResult.isError()

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<Boolean> = agentToolResult.isError()

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<Boolean> = spanModelRequestEnd.isError()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<Boolean> = userToolResult.isError()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<Boolean> = Optional.empty()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<Boolean> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Boolean> =
                    json.getProperty<Boolean>("is_error").asKnown()
            }
        )

    fun name(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<String> = Optional.of(agentCustomToolUse.name())

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<String> = Optional.of(agentMcpToolUse.name())

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<String> = Optional.of(agentToolUse.name())

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<String> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("name").asKnown()
            }
        )

    fun agentName(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<String> = Optional.of(sessionThreadCreated.agentName())

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<String> = Optional.of(sessionThreadStatusRunning.agentName())

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<String> = Optional.of(sessionThreadStatusIdle.agentName())

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<String> = Optional.of(sessionThreadStatusTerminated.agentName())

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<String> = Optional.of(sessionThreadStatusRescheduled.agentName())

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<String> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("agent_name").asKnown()
            }
        )

    fun iteration(): Optional<Int> =
        accept(
            object : Visitor<Optional<Int>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<Int> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<Int> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<Int> = Optional.empty()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<Int> = Optional.empty()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<Int> = Optional.of(spanOutcomeEvaluationStart.iteration())

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<Int> = Optional.of(spanOutcomeEvaluationEnd.iteration())

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<Int> = Optional.of(spanOutcomeEvaluationOngoing.iteration())

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<Int> = Optional.empty()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<Int> = Optional.empty()

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<Int> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<Int> = Optional.empty()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<Int> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Int> =
                    json.getProperty<Int>("iteration").asKnown()
            }
        )

    fun outcomeId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<String> = Optional.empty()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<String> = Optional.of(spanOutcomeEvaluationStart.outcomeId())

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<String> = Optional.of(spanOutcomeEvaluationEnd.outcomeId())

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<String> = Optional.empty()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<String> = Optional.of(spanOutcomeEvaluationOngoing.outcomeId())

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<String> = Optional.of(userDefineOutcome.outcomeId())

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<String> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<String> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<String> = Optional.empty()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("outcome_id").asKnown()
            }
        )

    fun budget(): Optional<BetaManagedAgentsBudgetLimit> =
        accept(
            object : Visitor<Optional<BetaManagedAgentsBudgetLimit>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessageEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentToolUse(
                    agentToolUse: BetaManagedAgentsAgentToolUseEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionError(
                    sessionError: BetaManagedAgentsSessionErrorEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = sessionUpdated.budget()

                override fun visitEventStart(
                    eventStart: BetaManagedAgentsStartEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitEventDelta(
                    eventDelta: BetaManagedAgentsDeltaEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = Optional.empty()

                override fun visitSessionUsage(
                    sessionUsage: BetaManagedAgentsSessionUsageEvent
                ): Optional<BetaManagedAgentsBudgetLimit> = sessionUsage.budget()

                override fun unknown(json: JsonValue?): Optional<BetaManagedAgentsBudgetLimit> =
                    json.getProperty<BetaManagedAgentsBudgetLimit>("budget").asKnown()
            }
        )

    /** A user message event in the session conversation. */
    fun userMessage(): Optional<BetaManagedAgentsUserMessageEvent> =
        Optional.ofNullable(userMessage)

    /** An interrupt event that pauses agent execution and returns control to the user. */
    fun userInterrupt(): Optional<BetaManagedAgentsUserInterruptEvent> =
        Optional.ofNullable(userInterrupt)

    /** A tool confirmation event that approves or denies a pending tool execution. */
    fun userToolConfirmation(): Optional<BetaManagedAgentsUserToolConfirmationEvent> =
        Optional.ofNullable(userToolConfirmation)

    /** Event sent by the client providing the result of a custom tool execution. */
    fun userCustomToolResult(): Optional<BetaManagedAgentsUserCustomToolResultEvent> =
        Optional.ofNullable(userCustomToolResult)

    /**
     * Event emitted when the agent calls a custom tool. The session goes idle until the client
     * sends a `user.custom_tool_result` event with the result.
     */
    fun agentCustomToolUse(): Optional<BetaManagedAgentsAgentCustomToolUseEvent> =
        Optional.ofNullable(agentCustomToolUse)

    /** An agent response event in the session conversation. */
    fun agentMessage(): Optional<BetaManagedAgentsAgentMessageEvent> =
        Optional.ofNullable(agentMessage)

    /**
     * Indicates the agent is making forward progress via extended thinking. A progress signal, not
     * a content carrier.
     */
    fun agentThinking(): Optional<BetaManagedAgentsAgentThinkingEvent> =
        Optional.ofNullable(agentThinking)

    /** Event emitted when the agent invokes a tool provided by an MCP server. */
    fun agentMcpToolUse(): Optional<BetaManagedAgentsAgentMcpToolUseEvent> =
        Optional.ofNullable(agentMcpToolUse)

    /** Event representing the result of an MCP tool execution. */
    fun agentMcpToolResult(): Optional<BetaManagedAgentsAgentMcpToolResultEvent> =
        Optional.ofNullable(agentMcpToolResult)

    /** Event emitted when the agent invokes a built-in agent tool. */
    fun agentToolUse(): Optional<BetaManagedAgentsAgentToolUseEvent> =
        Optional.ofNullable(agentToolUse)

    /** Event representing the result of an agent tool execution. */
    fun agentToolResult(): Optional<BetaManagedAgentsAgentToolResultEvent> =
        Optional.ofNullable(agentToolResult)

    /**
     * Delivery event written to the target thread's input stream when an agent-to-agent message
     * arrives.
     */
    fun agentThreadMessageReceived(): Optional<BetaManagedAgentsAgentThreadMessageReceivedEvent> =
        Optional.ofNullable(agentThreadMessageReceived)

    /**
     * Observability event emitted to the sender's output stream when an agent-to-agent message is
     * sent.
     */
    fun agentThreadMessageSent(): Optional<BetaManagedAgentsAgentThreadMessageSentEvent> =
        Optional.ofNullable(agentThreadMessageSent)

    /** Indicates that context compaction (summarization) occurred during the session. */
    fun agentThreadContextCompacted(): Optional<BetaManagedAgentsAgentThreadContextCompactedEvent> =
        Optional.ofNullable(agentThreadContextCompacted)

    /** An error event indicating a problem occurred during session execution. */
    fun sessionError(): Optional<BetaManagedAgentsSessionErrorEvent> =
        Optional.ofNullable(sessionError)

    /** Indicates the session is recovering from an error state and is rescheduled for execution. */
    fun sessionStatusRescheduled(): Optional<BetaManagedAgentsSessionStatusRescheduledEvent> =
        Optional.ofNullable(sessionStatusRescheduled)

    /** Indicates the session is actively running and the agent is working. */
    fun sessionStatusRunning(): Optional<BetaManagedAgentsSessionStatusRunningEvent> =
        Optional.ofNullable(sessionStatusRunning)

    /** Indicates the agent has paused and is awaiting user input. */
    fun sessionStatusIdle(): Optional<BetaManagedAgentsSessionStatusIdleEvent> =
        Optional.ofNullable(sessionStatusIdle)

    /** Indicates the session has terminated, either due to an error or completion. */
    fun sessionStatusTerminated(): Optional<BetaManagedAgentsSessionStatusTerminatedEvent> =
        Optional.ofNullable(sessionStatusTerminated)

    /**
     * Emitted when a subagent is spawned as a new thread. Written to the parent thread's output
     * stream so clients observing the session see child creation.
     */
    fun sessionThreadCreated(): Optional<BetaManagedAgentsSessionThreadCreatedEvent> =
        Optional.ofNullable(sessionThreadCreated)

    /** Emitted when an outcome evaluation cycle begins. */
    fun spanOutcomeEvaluationStart(): Optional<BetaManagedAgentsSpanOutcomeEvaluationStartEvent> =
        Optional.ofNullable(spanOutcomeEvaluationStart)

    /**
     * Emitted when an outcome evaluation cycle completes. Carries the verdict and aggregate token
     * usage. A verdict of `needs_revision` means another evaluation cycle follows; `satisfied`,
     * `max_iterations_reached`, `failed`, or `interrupted` are terminal — no further evaluation
     * cycles follow.
     */
    fun spanOutcomeEvaluationEnd(): Optional<BetaManagedAgentsSpanOutcomeEvaluationEndEvent> =
        Optional.ofNullable(spanOutcomeEvaluationEnd)

    /** Emitted when a model request is initiated by the agent. */
    fun spanModelRequestStart(): Optional<BetaManagedAgentsSpanModelRequestStartEvent> =
        Optional.ofNullable(spanModelRequestStart)

    /** Emitted when a model request completes. */
    fun spanModelRequestEnd(): Optional<BetaManagedAgentsSpanModelRequestEndEvent> =
        Optional.ofNullable(spanModelRequestEnd)

    /**
     * Periodic heartbeat emitted while an outcome evaluation cycle is in progress. Distinguishes
     * 'evaluation is actively running' from 'evaluation is stuck' between the corresponding
     * `span.outcome_evaluation_start` and `span.outcome_evaluation_end` events.
     */
    fun spanOutcomeEvaluationOngoing():
        Optional<BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent> =
        Optional.ofNullable(spanOutcomeEvaluationOngoing)

    /**
     * Echo of a `user.define_outcome` input event. Carries the server-generated `outcome_id` that
     * subsequent `span.outcome_evaluation_*` events reference.
     */
    fun userDefineOutcome(): Optional<BetaManagedAgentsUserDefineOutcomeEvent> =
        Optional.ofNullable(userDefineOutcome)

    /**
     * Emitted when a session has been deleted. Terminates any active event stream — no further
     * events will be emitted for this session.
     */
    fun sessionDeleted(): Optional<BetaManagedAgentsSessionDeletedEvent> =
        Optional.ofNullable(sessionDeleted)

    /**
     * A session thread has begun executing. Emitted on the thread's own stream and cross-posted to
     * the primary stream for child threads.
     */
    fun sessionThreadStatusRunning(): Optional<BetaManagedAgentsSessionThreadStatusRunningEvent> =
        Optional.ofNullable(sessionThreadStatusRunning)

    /**
     * A session thread has yielded and is awaiting input. Emitted on the thread's own stream and
     * cross-posted to the primary stream for child threads.
     */
    fun sessionThreadStatusIdle(): Optional<BetaManagedAgentsSessionThreadStatusIdleEvent> =
        Optional.ofNullable(sessionThreadStatusIdle)

    /**
     * A session thread has terminated and will accept no further input. Emitted on the thread's own
     * stream and cross-posted to the primary stream for child threads.
     */
    fun sessionThreadStatusTerminated():
        Optional<BetaManagedAgentsSessionThreadStatusTerminatedEvent> =
        Optional.ofNullable(sessionThreadStatusTerminated)

    /**
     * Event sent by the client providing the result of an agent-toolset tool execution. Only valid
     * on `self_hosted` environments, where sandbox-routed tools are executed by the client rather
     * than the server.
     */
    fun userToolResult(): Optional<BetaManagedAgentsUserToolResultEvent> =
        Optional.ofNullable(userToolResult)

    /**
     * A session thread hit a transient error and is retrying automatically. Emitted on the thread's
     * own stream and cross-posted to the primary stream for child threads.
     */
    fun sessionThreadStatusRescheduled():
        Optional<BetaManagedAgentsSessionThreadStatusRescheduledEvent> =
        Optional.ofNullable(sessionThreadStatusRescheduled)

    /**
     * Emitted when an UpdateSession request changed at least one field. Carries only the fields
     * that changed; absent fields were not part of the update. The new configuration applies from
     * the next turn.
     */
    fun sessionUpdated(): Optional<BetaManagedAgentsSessionUpdatedEvent> =
        Optional.ofNullable(sessionUpdated)

    /**
     * Opens a preview of a buffered event. Carries the previewed event's type and id only. Followed
     * by zero or more event_delta events with the same event id, normally concluded by the buffered
     * event carrying that id. If the producing model request ends without that event (an error or
     * interrupt mid-stream), its terminal span.model_request_end closes the preview. Only sent on
     * stream connections that opt in via event_deltas; never appears in event history.
     */
    fun eventStart(): Optional<BetaManagedAgentsStartEvent> = Optional.ofNullable(eventStart)

    /**
     * An incremental update to an event that is still being streamed. Deltas are best-effort and
     * may stop early; when the buffered event with id == event_id is produced it carries the
     * complete content. A model request that ends early (an error or interrupt) produces no
     * buffered event — its terminal span.model_request_end closes the preview. Only sent on stream
     * connections that opt in via event_deltas; never appears in event history.
     */
    fun eventDelta(): Optional<BetaManagedAgentsDeltaEvent> = Optional.ofNullable(eventDelta)

    /**
     * A mid-conversation system message event. Carries system-role content that is appended to the
     * session as a `role: "system"` turn.
     */
    fun systemMessage(): Optional<BetaManagedAgentsSystemMessageEvent> =
        Optional.ofNullable(systemMessage)

    /** Periodic snapshot of the session's cumulative usage and tracked list cost. */
    fun sessionUsage(): Optional<BetaManagedAgentsSessionUsageEvent> =
        Optional.ofNullable(sessionUsage)

    fun isUserMessage(): Boolean = userMessage != null

    fun isUserInterrupt(): Boolean = userInterrupt != null

    fun isUserToolConfirmation(): Boolean = userToolConfirmation != null

    fun isUserCustomToolResult(): Boolean = userCustomToolResult != null

    fun isAgentCustomToolUse(): Boolean = agentCustomToolUse != null

    fun isAgentMessage(): Boolean = agentMessage != null

    fun isAgentThinking(): Boolean = agentThinking != null

    fun isAgentMcpToolUse(): Boolean = agentMcpToolUse != null

    fun isAgentMcpToolResult(): Boolean = agentMcpToolResult != null

    fun isAgentToolUse(): Boolean = agentToolUse != null

    fun isAgentToolResult(): Boolean = agentToolResult != null

    fun isAgentThreadMessageReceived(): Boolean = agentThreadMessageReceived != null

    fun isAgentThreadMessageSent(): Boolean = agentThreadMessageSent != null

    fun isAgentThreadContextCompacted(): Boolean = agentThreadContextCompacted != null

    fun isSessionError(): Boolean = sessionError != null

    fun isSessionStatusRescheduled(): Boolean = sessionStatusRescheduled != null

    fun isSessionStatusRunning(): Boolean = sessionStatusRunning != null

    fun isSessionStatusIdle(): Boolean = sessionStatusIdle != null

    fun isSessionStatusTerminated(): Boolean = sessionStatusTerminated != null

    fun isSessionThreadCreated(): Boolean = sessionThreadCreated != null

    fun isSpanOutcomeEvaluationStart(): Boolean = spanOutcomeEvaluationStart != null

    fun isSpanOutcomeEvaluationEnd(): Boolean = spanOutcomeEvaluationEnd != null

    fun isSpanModelRequestStart(): Boolean = spanModelRequestStart != null

    fun isSpanModelRequestEnd(): Boolean = spanModelRequestEnd != null

    fun isSpanOutcomeEvaluationOngoing(): Boolean = spanOutcomeEvaluationOngoing != null

    fun isUserDefineOutcome(): Boolean = userDefineOutcome != null

    fun isSessionDeleted(): Boolean = sessionDeleted != null

    fun isSessionThreadStatusRunning(): Boolean = sessionThreadStatusRunning != null

    fun isSessionThreadStatusIdle(): Boolean = sessionThreadStatusIdle != null

    fun isSessionThreadStatusTerminated(): Boolean = sessionThreadStatusTerminated != null

    fun isUserToolResult(): Boolean = userToolResult != null

    fun isSessionThreadStatusRescheduled(): Boolean = sessionThreadStatusRescheduled != null

    fun isSessionUpdated(): Boolean = sessionUpdated != null

    fun isEventStart(): Boolean = eventStart != null

    fun isEventDelta(): Boolean = eventDelta != null

    fun isSystemMessage(): Boolean = systemMessage != null

    fun isSessionUsage(): Boolean = sessionUsage != null

    /** A user message event in the session conversation. */
    fun asUserMessage(): BetaManagedAgentsUserMessageEvent = userMessage.getOrThrow("userMessage")

    /** An interrupt event that pauses agent execution and returns control to the user. */
    fun asUserInterrupt(): BetaManagedAgentsUserInterruptEvent =
        userInterrupt.getOrThrow("userInterrupt")

    /** A tool confirmation event that approves or denies a pending tool execution. */
    fun asUserToolConfirmation(): BetaManagedAgentsUserToolConfirmationEvent =
        userToolConfirmation.getOrThrow("userToolConfirmation")

    /** Event sent by the client providing the result of a custom tool execution. */
    fun asUserCustomToolResult(): BetaManagedAgentsUserCustomToolResultEvent =
        userCustomToolResult.getOrThrow("userCustomToolResult")

    /**
     * Event emitted when the agent calls a custom tool. The session goes idle until the client
     * sends a `user.custom_tool_result` event with the result.
     */
    fun asAgentCustomToolUse(): BetaManagedAgentsAgentCustomToolUseEvent =
        agentCustomToolUse.getOrThrow("agentCustomToolUse")

    /** An agent response event in the session conversation. */
    fun asAgentMessage(): BetaManagedAgentsAgentMessageEvent =
        agentMessage.getOrThrow("agentMessage")

    /**
     * Indicates the agent is making forward progress via extended thinking. A progress signal, not
     * a content carrier.
     */
    fun asAgentThinking(): BetaManagedAgentsAgentThinkingEvent =
        agentThinking.getOrThrow("agentThinking")

    /** Event emitted when the agent invokes a tool provided by an MCP server. */
    fun asAgentMcpToolUse(): BetaManagedAgentsAgentMcpToolUseEvent =
        agentMcpToolUse.getOrThrow("agentMcpToolUse")

    /** Event representing the result of an MCP tool execution. */
    fun asAgentMcpToolResult(): BetaManagedAgentsAgentMcpToolResultEvent =
        agentMcpToolResult.getOrThrow("agentMcpToolResult")

    /** Event emitted when the agent invokes a built-in agent tool. */
    fun asAgentToolUse(): BetaManagedAgentsAgentToolUseEvent =
        agentToolUse.getOrThrow("agentToolUse")

    /** Event representing the result of an agent tool execution. */
    fun asAgentToolResult(): BetaManagedAgentsAgentToolResultEvent =
        agentToolResult.getOrThrow("agentToolResult")

    /**
     * Delivery event written to the target thread's input stream when an agent-to-agent message
     * arrives.
     */
    fun asAgentThreadMessageReceived(): BetaManagedAgentsAgentThreadMessageReceivedEvent =
        agentThreadMessageReceived.getOrThrow("agentThreadMessageReceived")

    /**
     * Observability event emitted to the sender's output stream when an agent-to-agent message is
     * sent.
     */
    fun asAgentThreadMessageSent(): BetaManagedAgentsAgentThreadMessageSentEvent =
        agentThreadMessageSent.getOrThrow("agentThreadMessageSent")

    /** Indicates that context compaction (summarization) occurred during the session. */
    fun asAgentThreadContextCompacted(): BetaManagedAgentsAgentThreadContextCompactedEvent =
        agentThreadContextCompacted.getOrThrow("agentThreadContextCompacted")

    /** An error event indicating a problem occurred during session execution. */
    fun asSessionError(): BetaManagedAgentsSessionErrorEvent =
        sessionError.getOrThrow("sessionError")

    /** Indicates the session is recovering from an error state and is rescheduled for execution. */
    fun asSessionStatusRescheduled(): BetaManagedAgentsSessionStatusRescheduledEvent =
        sessionStatusRescheduled.getOrThrow("sessionStatusRescheduled")

    /** Indicates the session is actively running and the agent is working. */
    fun asSessionStatusRunning(): BetaManagedAgentsSessionStatusRunningEvent =
        sessionStatusRunning.getOrThrow("sessionStatusRunning")

    /** Indicates the agent has paused and is awaiting user input. */
    fun asSessionStatusIdle(): BetaManagedAgentsSessionStatusIdleEvent =
        sessionStatusIdle.getOrThrow("sessionStatusIdle")

    /** Indicates the session has terminated, either due to an error or completion. */
    fun asSessionStatusTerminated(): BetaManagedAgentsSessionStatusTerminatedEvent =
        sessionStatusTerminated.getOrThrow("sessionStatusTerminated")

    /**
     * Emitted when a subagent is spawned as a new thread. Written to the parent thread's output
     * stream so clients observing the session see child creation.
     */
    fun asSessionThreadCreated(): BetaManagedAgentsSessionThreadCreatedEvent =
        sessionThreadCreated.getOrThrow("sessionThreadCreated")

    /** Emitted when an outcome evaluation cycle begins. */
    fun asSpanOutcomeEvaluationStart(): BetaManagedAgentsSpanOutcomeEvaluationStartEvent =
        spanOutcomeEvaluationStart.getOrThrow("spanOutcomeEvaluationStart")

    /**
     * Emitted when an outcome evaluation cycle completes. Carries the verdict and aggregate token
     * usage. A verdict of `needs_revision` means another evaluation cycle follows; `satisfied`,
     * `max_iterations_reached`, `failed`, or `interrupted` are terminal — no further evaluation
     * cycles follow.
     */
    fun asSpanOutcomeEvaluationEnd(): BetaManagedAgentsSpanOutcomeEvaluationEndEvent =
        spanOutcomeEvaluationEnd.getOrThrow("spanOutcomeEvaluationEnd")

    /** Emitted when a model request is initiated by the agent. */
    fun asSpanModelRequestStart(): BetaManagedAgentsSpanModelRequestStartEvent =
        spanModelRequestStart.getOrThrow("spanModelRequestStart")

    /** Emitted when a model request completes. */
    fun asSpanModelRequestEnd(): BetaManagedAgentsSpanModelRequestEndEvent =
        spanModelRequestEnd.getOrThrow("spanModelRequestEnd")

    /**
     * Periodic heartbeat emitted while an outcome evaluation cycle is in progress. Distinguishes
     * 'evaluation is actively running' from 'evaluation is stuck' between the corresponding
     * `span.outcome_evaluation_start` and `span.outcome_evaluation_end` events.
     */
    fun asSpanOutcomeEvaluationOngoing(): BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent =
        spanOutcomeEvaluationOngoing.getOrThrow("spanOutcomeEvaluationOngoing")

    /**
     * Echo of a `user.define_outcome` input event. Carries the server-generated `outcome_id` that
     * subsequent `span.outcome_evaluation_*` events reference.
     */
    fun asUserDefineOutcome(): BetaManagedAgentsUserDefineOutcomeEvent =
        userDefineOutcome.getOrThrow("userDefineOutcome")

    /**
     * Emitted when a session has been deleted. Terminates any active event stream — no further
     * events will be emitted for this session.
     */
    fun asSessionDeleted(): BetaManagedAgentsSessionDeletedEvent =
        sessionDeleted.getOrThrow("sessionDeleted")

    /**
     * A session thread has begun executing. Emitted on the thread's own stream and cross-posted to
     * the primary stream for child threads.
     */
    fun asSessionThreadStatusRunning(): BetaManagedAgentsSessionThreadStatusRunningEvent =
        sessionThreadStatusRunning.getOrThrow("sessionThreadStatusRunning")

    /**
     * A session thread has yielded and is awaiting input. Emitted on the thread's own stream and
     * cross-posted to the primary stream for child threads.
     */
    fun asSessionThreadStatusIdle(): BetaManagedAgentsSessionThreadStatusIdleEvent =
        sessionThreadStatusIdle.getOrThrow("sessionThreadStatusIdle")

    /**
     * A session thread has terminated and will accept no further input. Emitted on the thread's own
     * stream and cross-posted to the primary stream for child threads.
     */
    fun asSessionThreadStatusTerminated(): BetaManagedAgentsSessionThreadStatusTerminatedEvent =
        sessionThreadStatusTerminated.getOrThrow("sessionThreadStatusTerminated")

    /**
     * Event sent by the client providing the result of an agent-toolset tool execution. Only valid
     * on `self_hosted` environments, where sandbox-routed tools are executed by the client rather
     * than the server.
     */
    fun asUserToolResult(): BetaManagedAgentsUserToolResultEvent =
        userToolResult.getOrThrow("userToolResult")

    /**
     * A session thread hit a transient error and is retrying automatically. Emitted on the thread's
     * own stream and cross-posted to the primary stream for child threads.
     */
    fun asSessionThreadStatusRescheduled(): BetaManagedAgentsSessionThreadStatusRescheduledEvent =
        sessionThreadStatusRescheduled.getOrThrow("sessionThreadStatusRescheduled")

    /**
     * Emitted when an UpdateSession request changed at least one field. Carries only the fields
     * that changed; absent fields were not part of the update. The new configuration applies from
     * the next turn.
     */
    fun asSessionUpdated(): BetaManagedAgentsSessionUpdatedEvent =
        sessionUpdated.getOrThrow("sessionUpdated")

    /**
     * Opens a preview of a buffered event. Carries the previewed event's type and id only. Followed
     * by zero or more event_delta events with the same event id, normally concluded by the buffered
     * event carrying that id. If the producing model request ends without that event (an error or
     * interrupt mid-stream), its terminal span.model_request_end closes the preview. Only sent on
     * stream connections that opt in via event_deltas; never appears in event history.
     */
    fun asEventStart(): BetaManagedAgentsStartEvent = eventStart.getOrThrow("eventStart")

    /**
     * An incremental update to an event that is still being streamed. Deltas are best-effort and
     * may stop early; when the buffered event with id == event_id is produced it carries the
     * complete content. A model request that ends early (an error or interrupt) produces no
     * buffered event — its terminal span.model_request_end closes the preview. Only sent on stream
     * connections that opt in via event_deltas; never appears in event history.
     */
    fun asEventDelta(): BetaManagedAgentsDeltaEvent = eventDelta.getOrThrow("eventDelta")

    /**
     * A mid-conversation system message event. Carries system-role content that is appended to the
     * session as a `role: "system"` turn.
     */
    fun asSystemMessage(): BetaManagedAgentsSystemMessageEvent =
        systemMessage.getOrThrow("systemMessage")

    /** Periodic snapshot of the session's cumulative usage and tracked list cost. */
    fun asSessionUsage(): BetaManagedAgentsSessionUsageEvent =
        sessionUsage.getOrThrow("sessionUsage")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaManagedAgentsStreamSessionEvents.accept(new BetaManagedAgentsStreamSessionEvents.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitUserMessage(BetaManagedAgentsUserMessageEvent userMessage) {
     *         return Optional.of(userMessage.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            userMessage != null -> visitor.visitUserMessage(userMessage)
            userInterrupt != null -> visitor.visitUserInterrupt(userInterrupt)
            userToolConfirmation != null -> visitor.visitUserToolConfirmation(userToolConfirmation)
            userCustomToolResult != null -> visitor.visitUserCustomToolResult(userCustomToolResult)
            agentCustomToolUse != null -> visitor.visitAgentCustomToolUse(agentCustomToolUse)
            agentMessage != null -> visitor.visitAgentMessage(agentMessage)
            agentThinking != null -> visitor.visitAgentThinking(agentThinking)
            agentMcpToolUse != null -> visitor.visitAgentMcpToolUse(agentMcpToolUse)
            agentMcpToolResult != null -> visitor.visitAgentMcpToolResult(agentMcpToolResult)
            agentToolUse != null -> visitor.visitAgentToolUse(agentToolUse)
            agentToolResult != null -> visitor.visitAgentToolResult(agentToolResult)
            agentThreadMessageReceived != null ->
                visitor.visitAgentThreadMessageReceived(agentThreadMessageReceived)
            agentThreadMessageSent != null ->
                visitor.visitAgentThreadMessageSent(agentThreadMessageSent)
            agentThreadContextCompacted != null ->
                visitor.visitAgentThreadContextCompacted(agentThreadContextCompacted)
            sessionError != null -> visitor.visitSessionError(sessionError)
            sessionStatusRescheduled != null ->
                visitor.visitSessionStatusRescheduled(sessionStatusRescheduled)
            sessionStatusRunning != null -> visitor.visitSessionStatusRunning(sessionStatusRunning)
            sessionStatusIdle != null -> visitor.visitSessionStatusIdle(sessionStatusIdle)
            sessionStatusTerminated != null ->
                visitor.visitSessionStatusTerminated(sessionStatusTerminated)
            sessionThreadCreated != null -> visitor.visitSessionThreadCreated(sessionThreadCreated)
            spanOutcomeEvaluationStart != null ->
                visitor.visitSpanOutcomeEvaluationStart(spanOutcomeEvaluationStart)
            spanOutcomeEvaluationEnd != null ->
                visitor.visitSpanOutcomeEvaluationEnd(spanOutcomeEvaluationEnd)
            spanModelRequestStart != null ->
                visitor.visitSpanModelRequestStart(spanModelRequestStart)
            spanModelRequestEnd != null -> visitor.visitSpanModelRequestEnd(spanModelRequestEnd)
            spanOutcomeEvaluationOngoing != null ->
                visitor.visitSpanOutcomeEvaluationOngoing(spanOutcomeEvaluationOngoing)
            userDefineOutcome != null -> visitor.visitUserDefineOutcome(userDefineOutcome)
            sessionDeleted != null -> visitor.visitSessionDeleted(sessionDeleted)
            sessionThreadStatusRunning != null ->
                visitor.visitSessionThreadStatusRunning(sessionThreadStatusRunning)
            sessionThreadStatusIdle != null ->
                visitor.visitSessionThreadStatusIdle(sessionThreadStatusIdle)
            sessionThreadStatusTerminated != null ->
                visitor.visitSessionThreadStatusTerminated(sessionThreadStatusTerminated)
            userToolResult != null -> visitor.visitUserToolResult(userToolResult)
            sessionThreadStatusRescheduled != null ->
                visitor.visitSessionThreadStatusRescheduled(sessionThreadStatusRescheduled)
            sessionUpdated != null -> visitor.visitSessionUpdated(sessionUpdated)
            eventStart != null -> visitor.visitEventStart(eventStart)
            eventDelta != null -> visitor.visitEventDelta(eventDelta)
            systemMessage != null -> visitor.visitSystemMessage(systemMessage)
            sessionUsage != null -> visitor.visitSessionUsage(sessionUsage)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaManagedAgentsStreamSessionEvents = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitUserMessage(userMessage: BetaManagedAgentsUserMessageEvent) {
                    userMessage.validate()
                }

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ) {
                    userInterrupt.validate()
                }

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ) {
                    userToolConfirmation.validate()
                }

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ) {
                    userCustomToolResult.validate()
                }

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ) {
                    agentCustomToolUse.validate()
                }

                override fun visitAgentMessage(agentMessage: BetaManagedAgentsAgentMessageEvent) {
                    agentMessage.validate()
                }

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ) {
                    agentThinking.validate()
                }

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ) {
                    agentMcpToolUse.validate()
                }

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ) {
                    agentMcpToolResult.validate()
                }

                override fun visitAgentToolUse(agentToolUse: BetaManagedAgentsAgentToolUseEvent) {
                    agentToolUse.validate()
                }

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ) {
                    agentToolResult.validate()
                }

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ) {
                    agentThreadMessageReceived.validate()
                }

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ) {
                    agentThreadMessageSent.validate()
                }

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ) {
                    agentThreadContextCompacted.validate()
                }

                override fun visitSessionError(sessionError: BetaManagedAgentsSessionErrorEvent) {
                    sessionError.validate()
                }

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ) {
                    sessionStatusRescheduled.validate()
                }

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ) {
                    sessionStatusRunning.validate()
                }

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ) {
                    sessionStatusIdle.validate()
                }

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ) {
                    sessionStatusTerminated.validate()
                }

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ) {
                    sessionThreadCreated.validate()
                }

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ) {
                    spanOutcomeEvaluationStart.validate()
                }

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ) {
                    spanOutcomeEvaluationEnd.validate()
                }

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ) {
                    spanModelRequestStart.validate()
                }

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ) {
                    spanModelRequestEnd.validate()
                }

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ) {
                    spanOutcomeEvaluationOngoing.validate()
                }

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ) {
                    userDefineOutcome.validate()
                }

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ) {
                    sessionDeleted.validate()
                }

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ) {
                    sessionThreadStatusRunning.validate()
                }

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ) {
                    sessionThreadStatusIdle.validate()
                }

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ) {
                    sessionThreadStatusTerminated.validate()
                }

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ) {
                    userToolResult.validate()
                }

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ) {
                    sessionThreadStatusRescheduled.validate()
                }

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ) {
                    sessionUpdated.validate()
                }

                override fun visitEventStart(eventStart: BetaManagedAgentsStartEvent) {
                    eventStart.validate()
                }

                override fun visitEventDelta(eventDelta: BetaManagedAgentsDeltaEvent) {
                    eventDelta.validate()
                }

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ) {
                    systemMessage.validate()
                }

                override fun visitSessionUsage(sessionUsage: BetaManagedAgentsSessionUsageEvent) {
                    sessionUsage.validate()
                }
            }
        )
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitUserMessage(userMessage: BetaManagedAgentsUserMessageEvent) =
                    userMessage.validity()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEvent
                ) = userInterrupt.validity()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                ) = userToolConfirmation.validity()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                ) = userCustomToolResult.validity()

                override fun visitAgentCustomToolUse(
                    agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent
                ) = agentCustomToolUse.validity()

                override fun visitAgentMessage(agentMessage: BetaManagedAgentsAgentMessageEvent) =
                    agentMessage.validity()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingEvent
                ) = agentThinking.validity()

                override fun visitAgentMcpToolUse(
                    agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent
                ) = agentMcpToolUse.validity()

                override fun visitAgentMcpToolResult(
                    agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent
                ) = agentMcpToolResult.validity()

                override fun visitAgentToolUse(agentToolUse: BetaManagedAgentsAgentToolUseEvent) =
                    agentToolUse.validity()

                override fun visitAgentToolResult(
                    agentToolResult: BetaManagedAgentsAgentToolResultEvent
                ) = agentToolResult.validity()

                override fun visitAgentThreadMessageReceived(
                    agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
                ) = agentThreadMessageReceived.validity()

                override fun visitAgentThreadMessageSent(
                    agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
                ) = agentThreadMessageSent.validity()

                override fun visitAgentThreadContextCompacted(
                    agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
                ) = agentThreadContextCompacted.validity()

                override fun visitSessionError(sessionError: BetaManagedAgentsSessionErrorEvent) =
                    sessionError.validity()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
                ) = sessionStatusRescheduled.validity()

                override fun visitSessionStatusRunning(
                    sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
                ) = sessionStatusRunning.validity()

                override fun visitSessionStatusIdle(
                    sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent
                ) = sessionStatusIdle.validity()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
                ) = sessionStatusTerminated.validity()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
                ) = sessionThreadCreated.validity()

                override fun visitSpanOutcomeEvaluationStart(
                    spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
                ) = spanOutcomeEvaluationStart.validity()

                override fun visitSpanOutcomeEvaluationEnd(
                    spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
                ) = spanOutcomeEvaluationEnd.validity()

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ) = spanModelRequestStart.validity()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ) = spanModelRequestEnd.validity()

                override fun visitSpanOutcomeEvaluationOngoing(
                    spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
                ) = spanOutcomeEvaluationOngoing.validity()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                ) = userDefineOutcome.validity()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ) = sessionDeleted.validity()

                override fun visitSessionThreadStatusRunning(
                    sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
                ) = sessionThreadStatusRunning.validity()

                override fun visitSessionThreadStatusIdle(
                    sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
                ) = sessionThreadStatusIdle.validity()

                override fun visitSessionThreadStatusTerminated(
                    sessionThreadStatusTerminated:
                        BetaManagedAgentsSessionThreadStatusTerminatedEvent
                ) = sessionThreadStatusTerminated.validity()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEvent
                ) = userToolResult.validity()

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ) = sessionThreadStatusRescheduled.validity()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaManagedAgentsSessionUpdatedEvent
                ) = sessionUpdated.validity()

                override fun visitEventStart(eventStart: BetaManagedAgentsStartEvent) =
                    eventStart.validity()

                override fun visitEventDelta(eventDelta: BetaManagedAgentsDeltaEvent) =
                    eventDelta.validity()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEvent
                ) = systemMessage.validity()

                override fun visitSessionUsage(sessionUsage: BetaManagedAgentsSessionUsageEvent) =
                    sessionUsage.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsStreamSessionEvents &&
            userMessage == other.userMessage &&
            userInterrupt == other.userInterrupt &&
            userToolConfirmation == other.userToolConfirmation &&
            userCustomToolResult == other.userCustomToolResult &&
            agentCustomToolUse == other.agentCustomToolUse &&
            agentMessage == other.agentMessage &&
            agentThinking == other.agentThinking &&
            agentMcpToolUse == other.agentMcpToolUse &&
            agentMcpToolResult == other.agentMcpToolResult &&
            agentToolUse == other.agentToolUse &&
            agentToolResult == other.agentToolResult &&
            agentThreadMessageReceived == other.agentThreadMessageReceived &&
            agentThreadMessageSent == other.agentThreadMessageSent &&
            agentThreadContextCompacted == other.agentThreadContextCompacted &&
            sessionError == other.sessionError &&
            sessionStatusRescheduled == other.sessionStatusRescheduled &&
            sessionStatusRunning == other.sessionStatusRunning &&
            sessionStatusIdle == other.sessionStatusIdle &&
            sessionStatusTerminated == other.sessionStatusTerminated &&
            sessionThreadCreated == other.sessionThreadCreated &&
            spanOutcomeEvaluationStart == other.spanOutcomeEvaluationStart &&
            spanOutcomeEvaluationEnd == other.spanOutcomeEvaluationEnd &&
            spanModelRequestStart == other.spanModelRequestStart &&
            spanModelRequestEnd == other.spanModelRequestEnd &&
            spanOutcomeEvaluationOngoing == other.spanOutcomeEvaluationOngoing &&
            userDefineOutcome == other.userDefineOutcome &&
            sessionDeleted == other.sessionDeleted &&
            sessionThreadStatusRunning == other.sessionThreadStatusRunning &&
            sessionThreadStatusIdle == other.sessionThreadStatusIdle &&
            sessionThreadStatusTerminated == other.sessionThreadStatusTerminated &&
            userToolResult == other.userToolResult &&
            sessionThreadStatusRescheduled == other.sessionThreadStatusRescheduled &&
            sessionUpdated == other.sessionUpdated &&
            eventStart == other.eventStart &&
            eventDelta == other.eventDelta &&
            systemMessage == other.systemMessage &&
            sessionUsage == other.sessionUsage
    }

    override fun hashCode(): Int =
        Objects.hash(
            userMessage,
            userInterrupt,
            userToolConfirmation,
            userCustomToolResult,
            agentCustomToolUse,
            agentMessage,
            agentThinking,
            agentMcpToolUse,
            agentMcpToolResult,
            agentToolUse,
            agentToolResult,
            agentThreadMessageReceived,
            agentThreadMessageSent,
            agentThreadContextCompacted,
            sessionError,
            sessionStatusRescheduled,
            sessionStatusRunning,
            sessionStatusIdle,
            sessionStatusTerminated,
            sessionThreadCreated,
            spanOutcomeEvaluationStart,
            spanOutcomeEvaluationEnd,
            spanModelRequestStart,
            spanModelRequestEnd,
            spanOutcomeEvaluationOngoing,
            userDefineOutcome,
            sessionDeleted,
            sessionThreadStatusRunning,
            sessionThreadStatusIdle,
            sessionThreadStatusTerminated,
            userToolResult,
            sessionThreadStatusRescheduled,
            sessionUpdated,
            eventStart,
            eventDelta,
            systemMessage,
            sessionUsage,
        )

    override fun toString(): String =
        when {
            userMessage != null -> "BetaManagedAgentsStreamSessionEvents{userMessage=$userMessage}"
            userInterrupt != null ->
                "BetaManagedAgentsStreamSessionEvents{userInterrupt=$userInterrupt}"
            userToolConfirmation != null ->
                "BetaManagedAgentsStreamSessionEvents{userToolConfirmation=$userToolConfirmation}"
            userCustomToolResult != null ->
                "BetaManagedAgentsStreamSessionEvents{userCustomToolResult=$userCustomToolResult}"
            agentCustomToolUse != null ->
                "BetaManagedAgentsStreamSessionEvents{agentCustomToolUse=$agentCustomToolUse}"
            agentMessage != null ->
                "BetaManagedAgentsStreamSessionEvents{agentMessage=$agentMessage}"
            agentThinking != null ->
                "BetaManagedAgentsStreamSessionEvents{agentThinking=$agentThinking}"
            agentMcpToolUse != null ->
                "BetaManagedAgentsStreamSessionEvents{agentMcpToolUse=$agentMcpToolUse}"
            agentMcpToolResult != null ->
                "BetaManagedAgentsStreamSessionEvents{agentMcpToolResult=$agentMcpToolResult}"
            agentToolUse != null ->
                "BetaManagedAgentsStreamSessionEvents{agentToolUse=$agentToolUse}"
            agentToolResult != null ->
                "BetaManagedAgentsStreamSessionEvents{agentToolResult=$agentToolResult}"
            agentThreadMessageReceived != null ->
                "BetaManagedAgentsStreamSessionEvents{agentThreadMessageReceived=$agentThreadMessageReceived}"
            agentThreadMessageSent != null ->
                "BetaManagedAgentsStreamSessionEvents{agentThreadMessageSent=$agentThreadMessageSent}"
            agentThreadContextCompacted != null ->
                "BetaManagedAgentsStreamSessionEvents{agentThreadContextCompacted=$agentThreadContextCompacted}"
            sessionError != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionError=$sessionError}"
            sessionStatusRescheduled != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionStatusRescheduled=$sessionStatusRescheduled}"
            sessionStatusRunning != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionStatusRunning=$sessionStatusRunning}"
            sessionStatusIdle != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionStatusIdle=$sessionStatusIdle}"
            sessionStatusTerminated != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionStatusTerminated=$sessionStatusTerminated}"
            sessionThreadCreated != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionThreadCreated=$sessionThreadCreated}"
            spanOutcomeEvaluationStart != null ->
                "BetaManagedAgentsStreamSessionEvents{spanOutcomeEvaluationStart=$spanOutcomeEvaluationStart}"
            spanOutcomeEvaluationEnd != null ->
                "BetaManagedAgentsStreamSessionEvents{spanOutcomeEvaluationEnd=$spanOutcomeEvaluationEnd}"
            spanModelRequestStart != null ->
                "BetaManagedAgentsStreamSessionEvents{spanModelRequestStart=$spanModelRequestStart}"
            spanModelRequestEnd != null ->
                "BetaManagedAgentsStreamSessionEvents{spanModelRequestEnd=$spanModelRequestEnd}"
            spanOutcomeEvaluationOngoing != null ->
                "BetaManagedAgentsStreamSessionEvents{spanOutcomeEvaluationOngoing=$spanOutcomeEvaluationOngoing}"
            userDefineOutcome != null ->
                "BetaManagedAgentsStreamSessionEvents{userDefineOutcome=$userDefineOutcome}"
            sessionDeleted != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionDeleted=$sessionDeleted}"
            sessionThreadStatusRunning != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionThreadStatusRunning=$sessionThreadStatusRunning}"
            sessionThreadStatusIdle != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionThreadStatusIdle=$sessionThreadStatusIdle}"
            sessionThreadStatusTerminated != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionThreadStatusTerminated=$sessionThreadStatusTerminated}"
            userToolResult != null ->
                "BetaManagedAgentsStreamSessionEvents{userToolResult=$userToolResult}"
            sessionThreadStatusRescheduled != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionThreadStatusRescheduled=$sessionThreadStatusRescheduled}"
            sessionUpdated != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionUpdated=$sessionUpdated}"
            eventStart != null -> "BetaManagedAgentsStreamSessionEvents{eventStart=$eventStart}"
            eventDelta != null -> "BetaManagedAgentsStreamSessionEvents{eventDelta=$eventDelta}"
            systemMessage != null ->
                "BetaManagedAgentsStreamSessionEvents{systemMessage=$systemMessage}"
            sessionUsage != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionUsage=$sessionUsage}"
            _json != null -> "BetaManagedAgentsStreamSessionEvents{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsStreamSessionEvents")
        }

    companion object {

        /** A user message event in the session conversation. */
        @JvmStatic
        fun ofUserMessage(userMessage: BetaManagedAgentsUserMessageEvent) =
            BetaManagedAgentsStreamSessionEvents(userMessage = userMessage)

        /** An interrupt event that pauses agent execution and returns control to the user. */
        @JvmStatic
        fun ofUserInterrupt(userInterrupt: BetaManagedAgentsUserInterruptEvent) =
            BetaManagedAgentsStreamSessionEvents(userInterrupt = userInterrupt)

        /**
         * Returns an immutable instance of [BetaManagedAgentsStreamSessionEvents] whose
         * [ofUserInterrupt] variant is built from the given required [id].
         */
        @JvmStatic
        fun ofUserInterrupt(id: String) =
            ofUserInterrupt(
                BetaManagedAgentsUserInterruptEvent.builder()
                    .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                    .id(id)
                    .build()
            )

        /** A tool confirmation event that approves or denies a pending tool execution. */
        @JvmStatic
        fun ofUserToolConfirmation(
            userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
        ) = BetaManagedAgentsStreamSessionEvents(userToolConfirmation = userToolConfirmation)

        /** Event sent by the client providing the result of a custom tool execution. */
        @JvmStatic
        fun ofUserCustomToolResult(
            userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
        ) = BetaManagedAgentsStreamSessionEvents(userCustomToolResult = userCustomToolResult)

        /**
         * Event emitted when the agent calls a custom tool. The session goes idle until the client
         * sends a `user.custom_tool_result` event with the result.
         */
        @JvmStatic
        fun ofAgentCustomToolUse(agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent) =
            BetaManagedAgentsStreamSessionEvents(agentCustomToolUse = agentCustomToolUse)

        /** An agent response event in the session conversation. */
        @JvmStatic
        fun ofAgentMessage(agentMessage: BetaManagedAgentsAgentMessageEvent) =
            BetaManagedAgentsStreamSessionEvents(agentMessage = agentMessage)

        /**
         * Indicates the agent is making forward progress via extended thinking. A progress signal,
         * not a content carrier.
         */
        @JvmStatic
        fun ofAgentThinking(agentThinking: BetaManagedAgentsAgentThinkingEvent) =
            BetaManagedAgentsStreamSessionEvents(agentThinking = agentThinking)

        /** Event emitted when the agent invokes a tool provided by an MCP server. */
        @JvmStatic
        fun ofAgentMcpToolUse(agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent) =
            BetaManagedAgentsStreamSessionEvents(agentMcpToolUse = agentMcpToolUse)

        /** Event representing the result of an MCP tool execution. */
        @JvmStatic
        fun ofAgentMcpToolResult(agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent) =
            BetaManagedAgentsStreamSessionEvents(agentMcpToolResult = agentMcpToolResult)

        /** Event emitted when the agent invokes a built-in agent tool. */
        @JvmStatic
        fun ofAgentToolUse(agentToolUse: BetaManagedAgentsAgentToolUseEvent) =
            BetaManagedAgentsStreamSessionEvents(agentToolUse = agentToolUse)

        /** Event representing the result of an agent tool execution. */
        @JvmStatic
        fun ofAgentToolResult(agentToolResult: BetaManagedAgentsAgentToolResultEvent) =
            BetaManagedAgentsStreamSessionEvents(agentToolResult = agentToolResult)

        /**
         * Delivery event written to the target thread's input stream when an agent-to-agent message
         * arrives.
         */
        @JvmStatic
        fun ofAgentThreadMessageReceived(
            agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                agentThreadMessageReceived = agentThreadMessageReceived
            )

        /**
         * Observability event emitted to the sender's output stream when an agent-to-agent message
         * is sent.
         */
        @JvmStatic
        fun ofAgentThreadMessageSent(
            agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
        ) = BetaManagedAgentsStreamSessionEvents(agentThreadMessageSent = agentThreadMessageSent)

        /** Indicates that context compaction (summarization) occurred during the session. */
        @JvmStatic
        fun ofAgentThreadContextCompacted(
            agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                agentThreadContextCompacted = agentThreadContextCompacted
            )

        /** An error event indicating a problem occurred during session execution. */
        @JvmStatic
        fun ofSessionError(sessionError: BetaManagedAgentsSessionErrorEvent) =
            BetaManagedAgentsStreamSessionEvents(sessionError = sessionError)

        /**
         * Indicates the session is recovering from an error state and is rescheduled for execution.
         */
        @JvmStatic
        fun ofSessionStatusRescheduled(
            sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                sessionStatusRescheduled = sessionStatusRescheduled
            )

        /** Indicates the session is actively running and the agent is working. */
        @JvmStatic
        fun ofSessionStatusRunning(
            sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
        ) = BetaManagedAgentsStreamSessionEvents(sessionStatusRunning = sessionStatusRunning)

        /** Indicates the agent has paused and is awaiting user input. */
        @JvmStatic
        fun ofSessionStatusIdle(sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent) =
            BetaManagedAgentsStreamSessionEvents(sessionStatusIdle = sessionStatusIdle)

        /** Indicates the session has terminated, either due to an error or completion. */
        @JvmStatic
        fun ofSessionStatusTerminated(
            sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
        ) = BetaManagedAgentsStreamSessionEvents(sessionStatusTerminated = sessionStatusTerminated)

        /**
         * Emitted when a subagent is spawned as a new thread. Written to the parent thread's output
         * stream so clients observing the session see child creation.
         */
        @JvmStatic
        fun ofSessionThreadCreated(
            sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
        ) = BetaManagedAgentsStreamSessionEvents(sessionThreadCreated = sessionThreadCreated)

        /** Emitted when an outcome evaluation cycle begins. */
        @JvmStatic
        fun ofSpanOutcomeEvaluationStart(
            spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                spanOutcomeEvaluationStart = spanOutcomeEvaluationStart
            )

        /**
         * Emitted when an outcome evaluation cycle completes. Carries the verdict and aggregate
         * token usage. A verdict of `needs_revision` means another evaluation cycle follows;
         * `satisfied`, `max_iterations_reached`, `failed`, or `interrupted` are terminal — no
         * further evaluation cycles follow.
         */
        @JvmStatic
        fun ofSpanOutcomeEvaluationEnd(
            spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                spanOutcomeEvaluationEnd = spanOutcomeEvaluationEnd
            )

        /** Emitted when a model request is initiated by the agent. */
        @JvmStatic
        fun ofSpanModelRequestStart(
            spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
        ) = BetaManagedAgentsStreamSessionEvents(spanModelRequestStart = spanModelRequestStart)

        /** Emitted when a model request completes. */
        @JvmStatic
        fun ofSpanModelRequestEnd(spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent) =
            BetaManagedAgentsStreamSessionEvents(spanModelRequestEnd = spanModelRequestEnd)

        /**
         * Periodic heartbeat emitted while an outcome evaluation cycle is in progress.
         * Distinguishes 'evaluation is actively running' from 'evaluation is stuck' between the
         * corresponding `span.outcome_evaluation_start` and `span.outcome_evaluation_end` events.
         */
        @JvmStatic
        fun ofSpanOutcomeEvaluationOngoing(
            spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                spanOutcomeEvaluationOngoing = spanOutcomeEvaluationOngoing
            )

        /**
         * Echo of a `user.define_outcome` input event. Carries the server-generated `outcome_id`
         * that subsequent `span.outcome_evaluation_*` events reference.
         */
        @JvmStatic
        fun ofUserDefineOutcome(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent) =
            BetaManagedAgentsStreamSessionEvents(userDefineOutcome = userDefineOutcome)

        /**
         * Emitted when a session has been deleted. Terminates any active event stream — no further
         * events will be emitted for this session.
         */
        @JvmStatic
        fun ofSessionDeleted(sessionDeleted: BetaManagedAgentsSessionDeletedEvent) =
            BetaManagedAgentsStreamSessionEvents(sessionDeleted = sessionDeleted)

        /**
         * A session thread has begun executing. Emitted on the thread's own stream and cross-posted
         * to the primary stream for child threads.
         */
        @JvmStatic
        fun ofSessionThreadStatusRunning(
            sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                sessionThreadStatusRunning = sessionThreadStatusRunning
            )

        /**
         * A session thread has yielded and is awaiting input. Emitted on the thread's own stream
         * and cross-posted to the primary stream for child threads.
         */
        @JvmStatic
        fun ofSessionThreadStatusIdle(
            sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
        ) = BetaManagedAgentsStreamSessionEvents(sessionThreadStatusIdle = sessionThreadStatusIdle)

        /**
         * A session thread has terminated and will accept no further input. Emitted on the thread's
         * own stream and cross-posted to the primary stream for child threads.
         */
        @JvmStatic
        fun ofSessionThreadStatusTerminated(
            sessionThreadStatusTerminated: BetaManagedAgentsSessionThreadStatusTerminatedEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                sessionThreadStatusTerminated = sessionThreadStatusTerminated
            )

        /**
         * Event sent by the client providing the result of an agent-toolset tool execution. Only
         * valid on `self_hosted` environments, where sandbox-routed tools are executed by the
         * client rather than the server.
         */
        @JvmStatic
        fun ofUserToolResult(userToolResult: BetaManagedAgentsUserToolResultEvent) =
            BetaManagedAgentsStreamSessionEvents(userToolResult = userToolResult)

        /**
         * A session thread hit a transient error and is retrying automatically. Emitted on the
         * thread's own stream and cross-posted to the primary stream for child threads.
         */
        @JvmStatic
        fun ofSessionThreadStatusRescheduled(
            sessionThreadStatusRescheduled: BetaManagedAgentsSessionThreadStatusRescheduledEvent
        ) =
            BetaManagedAgentsStreamSessionEvents(
                sessionThreadStatusRescheduled = sessionThreadStatusRescheduled
            )

        /**
         * Emitted when an UpdateSession request changed at least one field. Carries only the fields
         * that changed; absent fields were not part of the update. The new configuration applies
         * from the next turn.
         */
        @JvmStatic
        fun ofSessionUpdated(sessionUpdated: BetaManagedAgentsSessionUpdatedEvent) =
            BetaManagedAgentsStreamSessionEvents(sessionUpdated = sessionUpdated)

        /**
         * Opens a preview of a buffered event. Carries the previewed event's type and id only.
         * Followed by zero or more event_delta events with the same event id, normally concluded by
         * the buffered event carrying that id. If the producing model request ends without that
         * event (an error or interrupt mid-stream), its terminal span.model_request_end closes the
         * preview. Only sent on stream connections that opt in via event_deltas; never appears in
         * event history.
         */
        @JvmStatic
        fun ofEventStart(eventStart: BetaManagedAgentsStartEvent) =
            BetaManagedAgentsStreamSessionEvents(eventStart = eventStart)

        /**
         * Returns an immutable instance of [BetaManagedAgentsStreamSessionEvents] whose
         * [ofEventStart] variant is built from the given required [event].
         */
        @JvmStatic
        fun ofEventStart(event: BetaManagedAgentsStartEventPreview) =
            ofEventStart(
                BetaManagedAgentsStartEvent.builder()
                    .type(BetaManagedAgentsStartEvent.Type.EVENT_START)
                    .event(event)
                    .build()
            )

        /**
         * An incremental update to an event that is still being streamed. Deltas are best-effort
         * and may stop early; when the buffered event with id == event_id is produced it carries
         * the complete content. A model request that ends early (an error or interrupt) produces no
         * buffered event — its terminal span.model_request_end closes the preview. Only sent on
         * stream connections that opt in via event_deltas; never appears in event history.
         */
        @JvmStatic
        fun ofEventDelta(eventDelta: BetaManagedAgentsDeltaEvent) =
            BetaManagedAgentsStreamSessionEvents(eventDelta = eventDelta)

        /**
         * A mid-conversation system message event. Carries system-role content that is appended to
         * the session as a `role: "system"` turn.
         */
        @JvmStatic
        fun ofSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEvent) =
            BetaManagedAgentsStreamSessionEvents(systemMessage = systemMessage)

        /** Periodic snapshot of the session's cumulative usage and tracked list cost. */
        @JvmStatic
        fun ofSessionUsage(sessionUsage: BetaManagedAgentsSessionUsageEvent) =
            BetaManagedAgentsStreamSessionEvents(sessionUsage = sessionUsage)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsStreamSessionEvents]
     * to a value of type [T].
     */
    interface Visitor<out T> {

        /** A user message event in the session conversation. */
        fun visitUserMessage(userMessage: BetaManagedAgentsUserMessageEvent): T

        /** An interrupt event that pauses agent execution and returns control to the user. */
        fun visitUserInterrupt(userInterrupt: BetaManagedAgentsUserInterruptEvent): T

        /** A tool confirmation event that approves or denies a pending tool execution. */
        fun visitUserToolConfirmation(
            userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
        ): T

        /** Event sent by the client providing the result of a custom tool execution. */
        fun visitUserCustomToolResult(
            userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
        ): T

        /**
         * Event emitted when the agent calls a custom tool. The session goes idle until the client
         * sends a `user.custom_tool_result` event with the result.
         */
        fun visitAgentCustomToolUse(agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent): T

        /** An agent response event in the session conversation. */
        fun visitAgentMessage(agentMessage: BetaManagedAgentsAgentMessageEvent): T

        /**
         * Indicates the agent is making forward progress via extended thinking. A progress signal,
         * not a content carrier.
         */
        fun visitAgentThinking(agentThinking: BetaManagedAgentsAgentThinkingEvent): T

        /** Event emitted when the agent invokes a tool provided by an MCP server. */
        fun visitAgentMcpToolUse(agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent): T

        /** Event representing the result of an MCP tool execution. */
        fun visitAgentMcpToolResult(agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent): T

        /** Event emitted when the agent invokes a built-in agent tool. */
        fun visitAgentToolUse(agentToolUse: BetaManagedAgentsAgentToolUseEvent): T

        /** Event representing the result of an agent tool execution. */
        fun visitAgentToolResult(agentToolResult: BetaManagedAgentsAgentToolResultEvent): T

        /**
         * Delivery event written to the target thread's input stream when an agent-to-agent message
         * arrives.
         */
        fun visitAgentThreadMessageReceived(
            agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
        ): T

        /**
         * Observability event emitted to the sender's output stream when an agent-to-agent message
         * is sent.
         */
        fun visitAgentThreadMessageSent(
            agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
        ): T

        /** Indicates that context compaction (summarization) occurred during the session. */
        fun visitAgentThreadContextCompacted(
            agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
        ): T

        /** An error event indicating a problem occurred during session execution. */
        fun visitSessionError(sessionError: BetaManagedAgentsSessionErrorEvent): T

        /**
         * Indicates the session is recovering from an error state and is rescheduled for execution.
         */
        fun visitSessionStatusRescheduled(
            sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
        ): T

        /** Indicates the session is actively running and the agent is working. */
        fun visitSessionStatusRunning(
            sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
        ): T

        /** Indicates the agent has paused and is awaiting user input. */
        fun visitSessionStatusIdle(sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent): T

        /** Indicates the session has terminated, either due to an error or completion. */
        fun visitSessionStatusTerminated(
            sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
        ): T

        /**
         * Emitted when a subagent is spawned as a new thread. Written to the parent thread's output
         * stream so clients observing the session see child creation.
         */
        fun visitSessionThreadCreated(
            sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
        ): T

        /** Emitted when an outcome evaluation cycle begins. */
        fun visitSpanOutcomeEvaluationStart(
            spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
        ): T

        /**
         * Emitted when an outcome evaluation cycle completes. Carries the verdict and aggregate
         * token usage. A verdict of `needs_revision` means another evaluation cycle follows;
         * `satisfied`, `max_iterations_reached`, `failed`, or `interrupted` are terminal — no
         * further evaluation cycles follow.
         */
        fun visitSpanOutcomeEvaluationEnd(
            spanOutcomeEvaluationEnd: BetaManagedAgentsSpanOutcomeEvaluationEndEvent
        ): T

        /** Emitted when a model request is initiated by the agent. */
        fun visitSpanModelRequestStart(
            spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
        ): T

        /** Emitted when a model request completes. */
        fun visitSpanModelRequestEnd(
            spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
        ): T

        /**
         * Periodic heartbeat emitted while an outcome evaluation cycle is in progress.
         * Distinguishes 'evaluation is actively running' from 'evaluation is stuck' between the
         * corresponding `span.outcome_evaluation_start` and `span.outcome_evaluation_end` events.
         */
        fun visitSpanOutcomeEvaluationOngoing(
            spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
        ): T

        /**
         * Echo of a `user.define_outcome` input event. Carries the server-generated `outcome_id`
         * that subsequent `span.outcome_evaluation_*` events reference.
         */
        fun visitUserDefineOutcome(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent): T

        /**
         * Emitted when a session has been deleted. Terminates any active event stream — no further
         * events will be emitted for this session.
         */
        fun visitSessionDeleted(sessionDeleted: BetaManagedAgentsSessionDeletedEvent): T

        /**
         * A session thread has begun executing. Emitted on the thread's own stream and cross-posted
         * to the primary stream for child threads.
         */
        fun visitSessionThreadStatusRunning(
            sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
        ): T

        /**
         * A session thread has yielded and is awaiting input. Emitted on the thread's own stream
         * and cross-posted to the primary stream for child threads.
         */
        fun visitSessionThreadStatusIdle(
            sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
        ): T

        /**
         * A session thread has terminated and will accept no further input. Emitted on the thread's
         * own stream and cross-posted to the primary stream for child threads.
         */
        fun visitSessionThreadStatusTerminated(
            sessionThreadStatusTerminated: BetaManagedAgentsSessionThreadStatusTerminatedEvent
        ): T

        /**
         * Event sent by the client providing the result of an agent-toolset tool execution. Only
         * valid on `self_hosted` environments, where sandbox-routed tools are executed by the
         * client rather than the server.
         */
        fun visitUserToolResult(userToolResult: BetaManagedAgentsUserToolResultEvent): T

        /**
         * A session thread hit a transient error and is retrying automatically. Emitted on the
         * thread's own stream and cross-posted to the primary stream for child threads.
         */
        fun visitSessionThreadStatusRescheduled(
            sessionThreadStatusRescheduled: BetaManagedAgentsSessionThreadStatusRescheduledEvent
        ): T

        /**
         * Emitted when an UpdateSession request changed at least one field. Carries only the fields
         * that changed; absent fields were not part of the update. The new configuration applies
         * from the next turn.
         */
        fun visitSessionUpdated(sessionUpdated: BetaManagedAgentsSessionUpdatedEvent): T

        /**
         * Opens a preview of a buffered event. Carries the previewed event's type and id only.
         * Followed by zero or more event_delta events with the same event id, normally concluded by
         * the buffered event carrying that id. If the producing model request ends without that
         * event (an error or interrupt mid-stream), its terminal span.model_request_end closes the
         * preview. Only sent on stream connections that opt in via event_deltas; never appears in
         * event history.
         */
        fun visitEventStart(eventStart: BetaManagedAgentsStartEvent): T

        /**
         * An incremental update to an event that is still being streamed. Deltas are best-effort
         * and may stop early; when the buffered event with id == event_id is produced it carries
         * the complete content. A model request that ends early (an error or interrupt) produces no
         * buffered event — its terminal span.model_request_end closes the preview. Only sent on
         * stream connections that opt in via event_deltas; never appears in event history.
         */
        fun visitEventDelta(eventDelta: BetaManagedAgentsDeltaEvent): T

        /**
         * A mid-conversation system message event. Carries system-role content that is appended to
         * the session as a `role: "system"` turn.
         */
        fun visitSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEvent): T

        /** Periodic snapshot of the session's cumulative usage and tracked list cost. */
        fun visitSessionUsage(sessionUsage: BetaManagedAgentsSessionUsageEvent): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsStreamSessionEvents] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsStreamSessionEvents] can contain an unknown variant if
         * it was deserialized from data that doesn't match any known variant. For example, if the
         * SDK is on an older version than the API, then the API may respond with new variants that
         * the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsStreamSessionEvents: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsStreamSessionEvents>(
            BetaManagedAgentsStreamSessionEvents::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsStreamSessionEvents {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "user.message" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsUserMessageEvent>())
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(userMessage = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "user.interrupt" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserInterruptEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(userInterrupt = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "user.tool_confirmation" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserToolConfirmationEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                userToolConfirmation = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "user.custom_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserCustomToolResultEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                userCustomToolResult = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.custom_tool_use" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentCustomToolUseEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                agentCustomToolUse = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentMessageEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(agentMessage = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.thinking" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThinkingEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(agentThinking = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.mcp_tool_use" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentMcpToolUseEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(agentMcpToolUse = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.mcp_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentMcpToolResultEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                agentMcpToolResult = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.tool_use" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentToolUseEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(agentToolUse = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentToolResultEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(agentToolResult = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.thread_message_received" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThreadMessageReceivedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                agentThreadMessageReceived = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.thread_message_sent" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThreadMessageSentEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                agentThreadMessageSent = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "agent.thread_context_compacted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThreadContextCompactedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                agentThreadContextCompacted = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionErrorEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(sessionError = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.status_rescheduled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionStatusRescheduledEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionStatusRescheduled = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.status_running" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionStatusRunningEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionStatusRunning = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.status_idle" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionStatusIdleEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionStatusIdle = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.status_terminated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionStatusTerminatedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionStatusTerminated = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.thread_created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadCreatedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionThreadCreated = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "span.outcome_evaluation_start" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationStartEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                spanOutcomeEvaluationStart = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "span.outcome_evaluation_end" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationEndEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                spanOutcomeEvaluationEnd = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "span.model_request_start" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanModelRequestStartEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                spanModelRequestStart = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "span.model_request_end" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanModelRequestEndEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                spanModelRequestEnd = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "span.outcome_evaluation_ongoing" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                spanOutcomeEvaluationOngoing = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "user.define_outcome" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserDefineOutcomeEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                userDefineOutcome = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionDeletedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(sessionDeleted = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.thread_status_running" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadStatusRunningEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionThreadStatusRunning = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.thread_status_idle" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadStatusIdleEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionThreadStatusIdle = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.thread_status_terminated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadStatusTerminatedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionThreadStatusTerminated = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "user.tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserToolResultEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(userToolResult = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.thread_status_rescheduled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadStatusRescheduledEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(
                                sessionThreadStatusRescheduled = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.updated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionUpdatedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(sessionUpdated = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "event_start" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsStartEvent>())
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(eventStart = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "event_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsDeltaEvent>())
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(eventDelta = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "system.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSystemMessageEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(systemMessage = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
                "session.usage" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionUsageEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(sessionUsage = it, _json = json)
                        } ?: BetaManagedAgentsStreamSessionEvents(_json = json)
                }
            }

            return BetaManagedAgentsStreamSessionEvents(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsStreamSessionEvents>(
            BetaManagedAgentsStreamSessionEvents::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsStreamSessionEvents,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.userMessage != null -> generator.writeObject(value.userMessage)
                value.userInterrupt != null -> generator.writeObject(value.userInterrupt)
                value.userToolConfirmation != null ->
                    generator.writeObject(value.userToolConfirmation)
                value.userCustomToolResult != null ->
                    generator.writeObject(value.userCustomToolResult)
                value.agentCustomToolUse != null -> generator.writeObject(value.agentCustomToolUse)
                value.agentMessage != null -> generator.writeObject(value.agentMessage)
                value.agentThinking != null -> generator.writeObject(value.agentThinking)
                value.agentMcpToolUse != null -> generator.writeObject(value.agentMcpToolUse)
                value.agentMcpToolResult != null -> generator.writeObject(value.agentMcpToolResult)
                value.agentToolUse != null -> generator.writeObject(value.agentToolUse)
                value.agentToolResult != null -> generator.writeObject(value.agentToolResult)
                value.agentThreadMessageReceived != null ->
                    generator.writeObject(value.agentThreadMessageReceived)
                value.agentThreadMessageSent != null ->
                    generator.writeObject(value.agentThreadMessageSent)
                value.agentThreadContextCompacted != null ->
                    generator.writeObject(value.agentThreadContextCompacted)
                value.sessionError != null -> generator.writeObject(value.sessionError)
                value.sessionStatusRescheduled != null ->
                    generator.writeObject(value.sessionStatusRescheduled)
                value.sessionStatusRunning != null ->
                    generator.writeObject(value.sessionStatusRunning)
                value.sessionStatusIdle != null -> generator.writeObject(value.sessionStatusIdle)
                value.sessionStatusTerminated != null ->
                    generator.writeObject(value.sessionStatusTerminated)
                value.sessionThreadCreated != null ->
                    generator.writeObject(value.sessionThreadCreated)
                value.spanOutcomeEvaluationStart != null ->
                    generator.writeObject(value.spanOutcomeEvaluationStart)
                value.spanOutcomeEvaluationEnd != null ->
                    generator.writeObject(value.spanOutcomeEvaluationEnd)
                value.spanModelRequestStart != null ->
                    generator.writeObject(value.spanModelRequestStart)
                value.spanModelRequestEnd != null ->
                    generator.writeObject(value.spanModelRequestEnd)
                value.spanOutcomeEvaluationOngoing != null ->
                    generator.writeObject(value.spanOutcomeEvaluationOngoing)
                value.userDefineOutcome != null -> generator.writeObject(value.userDefineOutcome)
                value.sessionDeleted != null -> generator.writeObject(value.sessionDeleted)
                value.sessionThreadStatusRunning != null ->
                    generator.writeObject(value.sessionThreadStatusRunning)
                value.sessionThreadStatusIdle != null ->
                    generator.writeObject(value.sessionThreadStatusIdle)
                value.sessionThreadStatusTerminated != null ->
                    generator.writeObject(value.sessionThreadStatusTerminated)
                value.userToolResult != null -> generator.writeObject(value.userToolResult)
                value.sessionThreadStatusRescheduled != null ->
                    generator.writeObject(value.sessionThreadStatusRescheduled)
                value.sessionUpdated != null -> generator.writeObject(value.sessionUpdated)
                value.eventStart != null -> generator.writeObject(value.eventStart)
                value.eventDelta != null -> generator.writeObject(value.eventDelta)
                value.systemMessage != null -> generator.writeObject(value.systemMessage)
                value.sessionUsage != null -> generator.writeObject(value.sessionUsage)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsStreamSessionEvents")
            }
        }
    }

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val USER_MESSAGE = of("user.message")

            @JvmField val USER_INTERRUPT = of("user.interrupt")

            @JvmField val USER_TOOL_CONFIRMATION = of("user.tool_confirmation")

            @JvmField val USER_CUSTOM_TOOL_RESULT = of("user.custom_tool_result")

            @JvmField val AGENT_CUSTOM_TOOL_USE = of("agent.custom_tool_use")

            @JvmField val AGENT_MESSAGE = of("agent.message")

            @JvmField val AGENT_THINKING = of("agent.thinking")

            @JvmField val AGENT_MCP_TOOL_USE = of("agent.mcp_tool_use")

            @JvmField val AGENT_MCP_TOOL_RESULT = of("agent.mcp_tool_result")

            @JvmField val AGENT_TOOL_USE = of("agent.tool_use")

            @JvmField val AGENT_TOOL_RESULT = of("agent.tool_result")

            @JvmField val AGENT_THREAD_MESSAGE_RECEIVED = of("agent.thread_message_received")

            @JvmField val AGENT_THREAD_MESSAGE_SENT = of("agent.thread_message_sent")

            @JvmField val AGENT_THREAD_CONTEXT_COMPACTED = of("agent.thread_context_compacted")

            @JvmField val SESSION_ERROR = of("session.error")

            @JvmField val SESSION_STATUS_RESCHEDULED = of("session.status_rescheduled")

            @JvmField val SESSION_STATUS_RUNNING = of("session.status_running")

            @JvmField val SESSION_STATUS_IDLE = of("session.status_idle")

            @JvmField val SESSION_STATUS_TERMINATED = of("session.status_terminated")

            @JvmField val SESSION_THREAD_CREATED = of("session.thread_created")

            @JvmField val SPAN_OUTCOME_EVALUATION_START = of("span.outcome_evaluation_start")

            @JvmField val SPAN_OUTCOME_EVALUATION_END = of("span.outcome_evaluation_end")

            @JvmField val SPAN_MODEL_REQUEST_START = of("span.model_request_start")

            @JvmField val SPAN_MODEL_REQUEST_END = of("span.model_request_end")

            @JvmField val SPAN_OUTCOME_EVALUATION_ONGOING = of("span.outcome_evaluation_ongoing")

            @JvmField val USER_DEFINE_OUTCOME = of("user.define_outcome")

            @JvmField val SESSION_DELETED = of("session.deleted")

            @JvmField val SESSION_THREAD_STATUS_RUNNING = of("session.thread_status_running")

            @JvmField val SESSION_THREAD_STATUS_IDLE = of("session.thread_status_idle")

            @JvmField val SESSION_THREAD_STATUS_TERMINATED = of("session.thread_status_terminated")

            @JvmField val USER_TOOL_RESULT = of("user.tool_result")

            @JvmField
            val SESSION_THREAD_STATUS_RESCHEDULED = of("session.thread_status_rescheduled")

            @JvmField val SESSION_UPDATED = of("session.updated")

            @JvmField val EVENT_START = of("event_start")

            @JvmField val EVENT_DELTA = of("event_delta")

            @JvmField val SYSTEM_MESSAGE = of("system.message")

            @JvmField val SESSION_USAGE = of("session.usage")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            USER_MESSAGE,
            USER_INTERRUPT,
            USER_TOOL_CONFIRMATION,
            USER_CUSTOM_TOOL_RESULT,
            AGENT_CUSTOM_TOOL_USE,
            AGENT_MESSAGE,
            AGENT_THINKING,
            AGENT_MCP_TOOL_USE,
            AGENT_MCP_TOOL_RESULT,
            AGENT_TOOL_USE,
            AGENT_TOOL_RESULT,
            AGENT_THREAD_MESSAGE_RECEIVED,
            AGENT_THREAD_MESSAGE_SENT,
            AGENT_THREAD_CONTEXT_COMPACTED,
            SESSION_ERROR,
            SESSION_STATUS_RESCHEDULED,
            SESSION_STATUS_RUNNING,
            SESSION_STATUS_IDLE,
            SESSION_STATUS_TERMINATED,
            SESSION_THREAD_CREATED,
            SPAN_OUTCOME_EVALUATION_START,
            SPAN_OUTCOME_EVALUATION_END,
            SPAN_MODEL_REQUEST_START,
            SPAN_MODEL_REQUEST_END,
            SPAN_OUTCOME_EVALUATION_ONGOING,
            USER_DEFINE_OUTCOME,
            SESSION_DELETED,
            SESSION_THREAD_STATUS_RUNNING,
            SESSION_THREAD_STATUS_IDLE,
            SESSION_THREAD_STATUS_TERMINATED,
            USER_TOOL_RESULT,
            SESSION_THREAD_STATUS_RESCHEDULED,
            SESSION_UPDATED,
            EVENT_START,
            EVENT_DELTA,
            SYSTEM_MESSAGE,
            SESSION_USAGE,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            USER_MESSAGE,
            USER_INTERRUPT,
            USER_TOOL_CONFIRMATION,
            USER_CUSTOM_TOOL_RESULT,
            AGENT_CUSTOM_TOOL_USE,
            AGENT_MESSAGE,
            AGENT_THINKING,
            AGENT_MCP_TOOL_USE,
            AGENT_MCP_TOOL_RESULT,
            AGENT_TOOL_USE,
            AGENT_TOOL_RESULT,
            AGENT_THREAD_MESSAGE_RECEIVED,
            AGENT_THREAD_MESSAGE_SENT,
            AGENT_THREAD_CONTEXT_COMPACTED,
            SESSION_ERROR,
            SESSION_STATUS_RESCHEDULED,
            SESSION_STATUS_RUNNING,
            SESSION_STATUS_IDLE,
            SESSION_STATUS_TERMINATED,
            SESSION_THREAD_CREATED,
            SPAN_OUTCOME_EVALUATION_START,
            SPAN_OUTCOME_EVALUATION_END,
            SPAN_MODEL_REQUEST_START,
            SPAN_MODEL_REQUEST_END,
            SPAN_OUTCOME_EVALUATION_ONGOING,
            USER_DEFINE_OUTCOME,
            SESSION_DELETED,
            SESSION_THREAD_STATUS_RUNNING,
            SESSION_THREAD_STATUS_IDLE,
            SESSION_THREAD_STATUS_TERMINATED,
            USER_TOOL_RESULT,
            SESSION_THREAD_STATUS_RESCHEDULED,
            SESSION_UPDATED,
            EVENT_START,
            EVENT_DELTA,
            SYSTEM_MESSAGE,
            SESSION_USAGE,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                USER_MESSAGE -> Value.USER_MESSAGE
                USER_INTERRUPT -> Value.USER_INTERRUPT
                USER_TOOL_CONFIRMATION -> Value.USER_TOOL_CONFIRMATION
                USER_CUSTOM_TOOL_RESULT -> Value.USER_CUSTOM_TOOL_RESULT
                AGENT_CUSTOM_TOOL_USE -> Value.AGENT_CUSTOM_TOOL_USE
                AGENT_MESSAGE -> Value.AGENT_MESSAGE
                AGENT_THINKING -> Value.AGENT_THINKING
                AGENT_MCP_TOOL_USE -> Value.AGENT_MCP_TOOL_USE
                AGENT_MCP_TOOL_RESULT -> Value.AGENT_MCP_TOOL_RESULT
                AGENT_TOOL_USE -> Value.AGENT_TOOL_USE
                AGENT_TOOL_RESULT -> Value.AGENT_TOOL_RESULT
                AGENT_THREAD_MESSAGE_RECEIVED -> Value.AGENT_THREAD_MESSAGE_RECEIVED
                AGENT_THREAD_MESSAGE_SENT -> Value.AGENT_THREAD_MESSAGE_SENT
                AGENT_THREAD_CONTEXT_COMPACTED -> Value.AGENT_THREAD_CONTEXT_COMPACTED
                SESSION_ERROR -> Value.SESSION_ERROR
                SESSION_STATUS_RESCHEDULED -> Value.SESSION_STATUS_RESCHEDULED
                SESSION_STATUS_RUNNING -> Value.SESSION_STATUS_RUNNING
                SESSION_STATUS_IDLE -> Value.SESSION_STATUS_IDLE
                SESSION_STATUS_TERMINATED -> Value.SESSION_STATUS_TERMINATED
                SESSION_THREAD_CREATED -> Value.SESSION_THREAD_CREATED
                SPAN_OUTCOME_EVALUATION_START -> Value.SPAN_OUTCOME_EVALUATION_START
                SPAN_OUTCOME_EVALUATION_END -> Value.SPAN_OUTCOME_EVALUATION_END
                SPAN_MODEL_REQUEST_START -> Value.SPAN_MODEL_REQUEST_START
                SPAN_MODEL_REQUEST_END -> Value.SPAN_MODEL_REQUEST_END
                SPAN_OUTCOME_EVALUATION_ONGOING -> Value.SPAN_OUTCOME_EVALUATION_ONGOING
                USER_DEFINE_OUTCOME -> Value.USER_DEFINE_OUTCOME
                SESSION_DELETED -> Value.SESSION_DELETED
                SESSION_THREAD_STATUS_RUNNING -> Value.SESSION_THREAD_STATUS_RUNNING
                SESSION_THREAD_STATUS_IDLE -> Value.SESSION_THREAD_STATUS_IDLE
                SESSION_THREAD_STATUS_TERMINATED -> Value.SESSION_THREAD_STATUS_TERMINATED
                USER_TOOL_RESULT -> Value.USER_TOOL_RESULT
                SESSION_THREAD_STATUS_RESCHEDULED -> Value.SESSION_THREAD_STATUS_RESCHEDULED
                SESSION_UPDATED -> Value.SESSION_UPDATED
                EVENT_START -> Value.EVENT_START
                EVENT_DELTA -> Value.EVENT_DELTA
                SYSTEM_MESSAGE -> Value.SYSTEM_MESSAGE
                SESSION_USAGE -> Value.SESSION_USAGE
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                USER_MESSAGE -> Known.USER_MESSAGE
                USER_INTERRUPT -> Known.USER_INTERRUPT
                USER_TOOL_CONFIRMATION -> Known.USER_TOOL_CONFIRMATION
                USER_CUSTOM_TOOL_RESULT -> Known.USER_CUSTOM_TOOL_RESULT
                AGENT_CUSTOM_TOOL_USE -> Known.AGENT_CUSTOM_TOOL_USE
                AGENT_MESSAGE -> Known.AGENT_MESSAGE
                AGENT_THINKING -> Known.AGENT_THINKING
                AGENT_MCP_TOOL_USE -> Known.AGENT_MCP_TOOL_USE
                AGENT_MCP_TOOL_RESULT -> Known.AGENT_MCP_TOOL_RESULT
                AGENT_TOOL_USE -> Known.AGENT_TOOL_USE
                AGENT_TOOL_RESULT -> Known.AGENT_TOOL_RESULT
                AGENT_THREAD_MESSAGE_RECEIVED -> Known.AGENT_THREAD_MESSAGE_RECEIVED
                AGENT_THREAD_MESSAGE_SENT -> Known.AGENT_THREAD_MESSAGE_SENT
                AGENT_THREAD_CONTEXT_COMPACTED -> Known.AGENT_THREAD_CONTEXT_COMPACTED
                SESSION_ERROR -> Known.SESSION_ERROR
                SESSION_STATUS_RESCHEDULED -> Known.SESSION_STATUS_RESCHEDULED
                SESSION_STATUS_RUNNING -> Known.SESSION_STATUS_RUNNING
                SESSION_STATUS_IDLE -> Known.SESSION_STATUS_IDLE
                SESSION_STATUS_TERMINATED -> Known.SESSION_STATUS_TERMINATED
                SESSION_THREAD_CREATED -> Known.SESSION_THREAD_CREATED
                SPAN_OUTCOME_EVALUATION_START -> Known.SPAN_OUTCOME_EVALUATION_START
                SPAN_OUTCOME_EVALUATION_END -> Known.SPAN_OUTCOME_EVALUATION_END
                SPAN_MODEL_REQUEST_START -> Known.SPAN_MODEL_REQUEST_START
                SPAN_MODEL_REQUEST_END -> Known.SPAN_MODEL_REQUEST_END
                SPAN_OUTCOME_EVALUATION_ONGOING -> Known.SPAN_OUTCOME_EVALUATION_ONGOING
                USER_DEFINE_OUTCOME -> Known.USER_DEFINE_OUTCOME
                SESSION_DELETED -> Known.SESSION_DELETED
                SESSION_THREAD_STATUS_RUNNING -> Known.SESSION_THREAD_STATUS_RUNNING
                SESSION_THREAD_STATUS_IDLE -> Known.SESSION_THREAD_STATUS_IDLE
                SESSION_THREAD_STATUS_TERMINATED -> Known.SESSION_THREAD_STATUS_TERMINATED
                USER_TOOL_RESULT -> Known.USER_TOOL_RESULT
                SESSION_THREAD_STATUS_RESCHEDULED -> Known.SESSION_THREAD_STATUS_RESCHEDULED
                SESSION_UPDATED -> Known.SESSION_UPDATED
                EVENT_START -> Known.EVENT_START
                EVENT_DELTA -> Known.EVENT_DELTA
                SYSTEM_MESSAGE -> Known.SYSTEM_MESSAGE
                SESSION_USAGE -> Known.SESSION_USAGE
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
