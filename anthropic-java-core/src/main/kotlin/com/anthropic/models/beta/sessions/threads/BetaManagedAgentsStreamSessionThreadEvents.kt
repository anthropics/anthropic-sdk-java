package com.anthropic.models.beta.sessions.threads

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
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentCustomToolUseEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentEvaluatedPermission
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentMcpToolResultEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentMcpToolUseEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentMessageEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentThinkingEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentThreadContextCompactedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentThreadMessageReceivedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentThreadMessageSentEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentToolEvaluation
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentToolResultEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentToolUseEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionDeletedEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionErrorEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionRefusalStopDetails
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
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationEndEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationStartEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserCustomToolResultEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserDefineOutcomeEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserInterruptEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserToolConfirmationEvent
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

/** Server-sent event in a single thread's stream. */
@JsonDeserialize(using = BetaManagedAgentsStreamSessionThreadEvents.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsStreamSessionThreadEvents.Serializer::class)
class BetaManagedAgentsStreamSessionThreadEvents
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
        when {
            userMessage != null -> Type.USER_MESSAGE
            userInterrupt != null -> Type.USER_INTERRUPT
            userToolConfirmation != null -> Type.USER_TOOL_CONFIRMATION
            userCustomToolResult != null -> Type.USER_CUSTOM_TOOL_RESULT
            agentCustomToolUse != null -> Type.AGENT_CUSTOM_TOOL_USE
            agentMessage != null -> Type.AGENT_MESSAGE
            agentThinking != null -> Type.AGENT_THINKING
            agentMcpToolUse != null -> Type.AGENT_MCP_TOOL_USE
            agentMcpToolResult != null -> Type.AGENT_MCP_TOOL_RESULT
            agentToolUse != null -> Type.AGENT_TOOL_USE
            agentToolResult != null -> Type.AGENT_TOOL_RESULT
            agentThreadMessageReceived != null -> Type.AGENT_THREAD_MESSAGE_RECEIVED
            agentThreadMessageSent != null -> Type.AGENT_THREAD_MESSAGE_SENT
            agentThreadContextCompacted != null -> Type.AGENT_THREAD_CONTEXT_COMPACTED
            sessionError != null -> Type.SESSION_ERROR
            sessionStatusRescheduled != null -> Type.SESSION_STATUS_RESCHEDULED
            sessionStatusRunning != null -> Type.SESSION_STATUS_RUNNING
            sessionStatusIdle != null -> Type.SESSION_STATUS_IDLE
            sessionStatusTerminated != null -> Type.SESSION_STATUS_TERMINATED
            sessionThreadCreated != null -> Type.SESSION_THREAD_CREATED
            spanOutcomeEvaluationStart != null -> Type.SPAN_OUTCOME_EVALUATION_START
            spanOutcomeEvaluationEnd != null -> Type.SPAN_OUTCOME_EVALUATION_END
            spanModelRequestStart != null -> Type.SPAN_MODEL_REQUEST_START
            spanModelRequestEnd != null -> Type.SPAN_MODEL_REQUEST_END
            spanOutcomeEvaluationOngoing != null -> Type.SPAN_OUTCOME_EVALUATION_ONGOING
            userDefineOutcome != null -> Type.USER_DEFINE_OUTCOME
            sessionDeleted != null -> Type.SESSION_DELETED
            sessionThreadStatusRunning != null -> Type.SESSION_THREAD_STATUS_RUNNING
            sessionThreadStatusIdle != null -> Type.SESSION_THREAD_STATUS_IDLE
            sessionThreadStatusTerminated != null -> Type.SESSION_THREAD_STATUS_TERMINATED
            userToolResult != null -> Type.USER_TOOL_RESULT
            sessionThreadStatusRescheduled != null -> Type.SESSION_THREAD_STATUS_RESCHEDULED
            sessionUpdated != null -> Type.SESSION_UPDATED
            eventStart != null -> Type.EVENT_START
            eventDelta != null -> Type.EVENT_DELTA
            systemMessage != null -> Type.SYSTEM_MESSAGE
            sessionUsage != null -> Type.SESSION_USAGE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun id(): Optional<String> =
        when {
            userMessage != null -> Optional.of(userMessage.id())
            userInterrupt != null -> Optional.of(userInterrupt.id())
            userToolConfirmation != null -> Optional.of(userToolConfirmation.id())
            userCustomToolResult != null -> Optional.of(userCustomToolResult.id())
            agentCustomToolUse != null -> Optional.of(agentCustomToolUse.id())
            agentMessage != null -> Optional.of(agentMessage.id())
            agentThinking != null -> Optional.of(agentThinking.id())
            agentMcpToolUse != null -> Optional.of(agentMcpToolUse.id())
            agentMcpToolResult != null -> Optional.of(agentMcpToolResult.id())
            agentToolUse != null -> Optional.of(agentToolUse.id())
            agentToolResult != null -> Optional.of(agentToolResult.id())
            agentThreadMessageReceived != null -> Optional.of(agentThreadMessageReceived.id())
            agentThreadMessageSent != null -> Optional.of(agentThreadMessageSent.id())
            agentThreadContextCompacted != null -> Optional.of(agentThreadContextCompacted.id())
            sessionError != null -> Optional.of(sessionError.id())
            sessionStatusRescheduled != null -> Optional.of(sessionStatusRescheduled.id())
            sessionStatusRunning != null -> Optional.of(sessionStatusRunning.id())
            sessionStatusIdle != null -> Optional.of(sessionStatusIdle.id())
            sessionStatusTerminated != null -> Optional.of(sessionStatusTerminated.id())
            sessionThreadCreated != null -> Optional.of(sessionThreadCreated.id())
            spanOutcomeEvaluationStart != null -> Optional.of(spanOutcomeEvaluationStart.id())
            spanOutcomeEvaluationEnd != null -> Optional.of(spanOutcomeEvaluationEnd.id())
            spanModelRequestStart != null -> Optional.of(spanModelRequestStart.id())
            spanModelRequestEnd != null -> Optional.of(spanModelRequestEnd.id())
            spanOutcomeEvaluationOngoing != null -> Optional.of(spanOutcomeEvaluationOngoing.id())
            userDefineOutcome != null -> Optional.of(userDefineOutcome.id())
            sessionDeleted != null -> Optional.of(sessionDeleted.id())
            sessionThreadStatusRunning != null -> Optional.of(sessionThreadStatusRunning.id())
            sessionThreadStatusIdle != null -> Optional.of(sessionThreadStatusIdle.id())
            sessionThreadStatusTerminated != null -> Optional.of(sessionThreadStatusTerminated.id())
            userToolResult != null -> Optional.of(userToolResult.id())
            sessionThreadStatusRescheduled != null ->
                Optional.of(sessionThreadStatusRescheduled.id())
            sessionUpdated != null -> Optional.of(sessionUpdated.id())
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.of(systemMessage.id())
            sessionUsage != null -> Optional.of(sessionUsage.id())
            else -> _json.getProperty<String>("id").asKnown()
        }

    fun processedAt(): Optional<OffsetDateTime> =
        when {
            userMessage != null -> userMessage.processedAt()
            userInterrupt != null -> userInterrupt.processedAt()
            userToolConfirmation != null -> userToolConfirmation.processedAt()
            userCustomToolResult != null -> userCustomToolResult.processedAt()
            agentCustomToolUse != null -> Optional.of(agentCustomToolUse.processedAt())
            agentMessage != null -> Optional.of(agentMessage.processedAt())
            agentThinking != null -> Optional.of(agentThinking.processedAt())
            agentMcpToolUse != null -> Optional.of(agentMcpToolUse.processedAt())
            agentMcpToolResult != null -> Optional.of(agentMcpToolResult.processedAt())
            agentToolUse != null -> Optional.of(agentToolUse.processedAt())
            agentToolResult != null -> Optional.of(agentToolResult.processedAt())
            agentThreadMessageReceived != null ->
                Optional.of(agentThreadMessageReceived.processedAt())
            agentThreadMessageSent != null -> Optional.of(agentThreadMessageSent.processedAt())
            agentThreadContextCompacted != null ->
                Optional.of(agentThreadContextCompacted.processedAt())
            sessionError != null -> Optional.of(sessionError.processedAt())
            sessionStatusRescheduled != null -> Optional.of(sessionStatusRescheduled.processedAt())
            sessionStatusRunning != null -> Optional.of(sessionStatusRunning.processedAt())
            sessionStatusIdle != null -> Optional.of(sessionStatusIdle.processedAt())
            sessionStatusTerminated != null -> Optional.of(sessionStatusTerminated.processedAt())
            sessionThreadCreated != null -> Optional.of(sessionThreadCreated.processedAt())
            spanOutcomeEvaluationStart != null ->
                Optional.of(spanOutcomeEvaluationStart.processedAt())
            spanOutcomeEvaluationEnd != null -> Optional.of(spanOutcomeEvaluationEnd.processedAt())
            spanModelRequestStart != null -> Optional.of(spanModelRequestStart.processedAt())
            spanModelRequestEnd != null -> Optional.of(spanModelRequestEnd.processedAt())
            spanOutcomeEvaluationOngoing != null ->
                Optional.of(spanOutcomeEvaluationOngoing.processedAt())
            userDefineOutcome != null -> Optional.of(userDefineOutcome.processedAt())
            sessionDeleted != null -> Optional.of(sessionDeleted.processedAt())
            sessionThreadStatusRunning != null ->
                Optional.of(sessionThreadStatusRunning.processedAt())
            sessionThreadStatusIdle != null -> Optional.of(sessionThreadStatusIdle.processedAt())
            sessionThreadStatusTerminated != null ->
                Optional.of(sessionThreadStatusTerminated.processedAt())
            userToolResult != null -> userToolResult.processedAt()
            sessionThreadStatusRescheduled != null ->
                Optional.of(sessionThreadStatusRescheduled.processedAt())
            sessionUpdated != null -> Optional.of(sessionUpdated.processedAt())
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> systemMessage.processedAt()
            sessionUsage != null -> Optional.of(sessionUsage.processedAt())
            else -> _json.getProperty<OffsetDateTime>("processed_at").asKnown()
        }

    fun sessionThreadId(): Optional<String> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> userInterrupt.sessionThreadId()
            userToolConfirmation != null -> userToolConfirmation.sessionThreadId()
            userCustomToolResult != null -> userCustomToolResult.sessionThreadId()
            agentCustomToolUse != null -> agentCustomToolUse.sessionThreadId()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> agentMcpToolUse.sessionThreadId()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> agentToolUse.sessionThreadId()
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.of(sessionThreadCreated.sessionThreadId())
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null ->
                Optional.of(sessionThreadStatusRunning.sessionThreadId())
            sessionThreadStatusIdle != null ->
                Optional.of(sessionThreadStatusIdle.sessionThreadId())
            sessionThreadStatusTerminated != null ->
                Optional.of(sessionThreadStatusTerminated.sessionThreadId())
            userToolResult != null -> userToolResult.sessionThreadId()
            sessionThreadStatusRescheduled != null ->
                Optional.of(sessionThreadStatusRescheduled.sessionThreadId())
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else -> _json.getProperty<String>("session_thread_id").asKnown()
        }

    fun toolUseId(): Optional<String> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.of(userToolConfirmation.toolUseId())
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> Optional.empty()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> Optional.empty()
            agentToolResult != null -> Optional.of(agentToolResult.toolUseId())
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> Optional.empty()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> Optional.of(userToolResult.toolUseId())
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else -> _json.getProperty<String>("tool_use_id").asKnown()
        }

    fun isError(): Optional<Boolean> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> userCustomToolResult.isError()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> Optional.empty()
            agentMcpToolResult != null -> agentMcpToolResult.isError()
            agentToolUse != null -> Optional.empty()
            agentToolResult != null -> agentToolResult.isError()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> spanModelRequestEnd.isError()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> Optional.empty()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> userToolResult.isError()
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else -> _json.getProperty<Boolean>("is_error").asKnown()
        }

    fun name(): Optional<String> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.of(agentCustomToolUse.name())
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> Optional.of(agentMcpToolUse.name())
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> Optional.of(agentToolUse.name())
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> Optional.empty()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> Optional.empty()
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else -> _json.getProperty<String>("name").asKnown()
        }

    fun evaluatedPermission(): Optional<BetaManagedAgentsAgentEvaluatedPermission> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> agentMcpToolUse.evaluatedPermission()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> agentToolUse.evaluatedPermission()
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> Optional.empty()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> Optional.empty()
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else ->
                _json
                    .getProperty<BetaManagedAgentsAgentEvaluatedPermission>("evaluated_permission")
                    .asKnown()
        }

    fun evaluation(): Optional<BetaManagedAgentsAgentToolEvaluation> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> agentMcpToolUse.evaluation()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> agentToolUse.evaluation()
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> Optional.empty()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> Optional.empty()
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else -> _json.getProperty<BetaManagedAgentsAgentToolEvaluation>("evaluation").asKnown()
        }

    fun stopDetails(): Optional<BetaManagedAgentsSessionRefusalStopDetails> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> Optional.empty()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> Optional.empty()
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> sessionStatusIdle.stopDetails()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> sessionThreadStatusIdle.stopDetails()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> Optional.empty()
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else ->
                _json
                    .getProperty<BetaManagedAgentsSessionRefusalStopDetails>("stop_details")
                    .asKnown()
        }

    fun agentName(): Optional<String> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> Optional.empty()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> Optional.empty()
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.of(sessionThreadCreated.agentName())
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null ->
                Optional.of(sessionThreadStatusRunning.agentName())
            sessionThreadStatusIdle != null -> Optional.of(sessionThreadStatusIdle.agentName())
            sessionThreadStatusTerminated != null ->
                Optional.of(sessionThreadStatusTerminated.agentName())
            userToolResult != null -> Optional.empty()
            sessionThreadStatusRescheduled != null ->
                Optional.of(sessionThreadStatusRescheduled.agentName())
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else -> _json.getProperty<String>("agent_name").asKnown()
        }

    fun iteration(): Optional<Int> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> Optional.empty()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> Optional.empty()
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null ->
                Optional.of(spanOutcomeEvaluationStart.iteration())
            spanOutcomeEvaluationEnd != null -> Optional.of(spanOutcomeEvaluationEnd.iteration())
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null ->
                Optional.of(spanOutcomeEvaluationOngoing.iteration())
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> Optional.empty()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> Optional.empty()
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else -> _json.getProperty<Int>("iteration").asKnown()
        }

    fun outcomeId(): Optional<String> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> Optional.empty()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> Optional.empty()
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null ->
                Optional.of(spanOutcomeEvaluationStart.outcomeId())
            spanOutcomeEvaluationEnd != null -> Optional.of(spanOutcomeEvaluationEnd.outcomeId())
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null ->
                Optional.of(spanOutcomeEvaluationOngoing.outcomeId())
            userDefineOutcome != null -> Optional.of(userDefineOutcome.outcomeId())
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> Optional.empty()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> Optional.empty()
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> Optional.empty()
            else -> _json.getProperty<String>("outcome_id").asKnown()
        }

    fun budget(): Optional<BetaManagedAgentsBudgetLimit> =
        when {
            userMessage != null -> Optional.empty()
            userInterrupt != null -> Optional.empty()
            userToolConfirmation != null -> Optional.empty()
            userCustomToolResult != null -> Optional.empty()
            agentCustomToolUse != null -> Optional.empty()
            agentMessage != null -> Optional.empty()
            agentThinking != null -> Optional.empty()
            agentMcpToolUse != null -> Optional.empty()
            agentMcpToolResult != null -> Optional.empty()
            agentToolUse != null -> Optional.empty()
            agentToolResult != null -> Optional.empty()
            agentThreadMessageReceived != null -> Optional.empty()
            agentThreadMessageSent != null -> Optional.empty()
            agentThreadContextCompacted != null -> Optional.empty()
            sessionError != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunning != null -> Optional.empty()
            sessionStatusIdle != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            spanOutcomeEvaluationStart != null -> Optional.empty()
            spanOutcomeEvaluationEnd != null -> Optional.empty()
            spanModelRequestStart != null -> Optional.empty()
            spanModelRequestEnd != null -> Optional.empty()
            spanOutcomeEvaluationOngoing != null -> Optional.empty()
            userDefineOutcome != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionThreadStatusRunning != null -> Optional.empty()
            sessionThreadStatusIdle != null -> Optional.empty()
            sessionThreadStatusTerminated != null -> Optional.empty()
            userToolResult != null -> Optional.empty()
            sessionThreadStatusRescheduled != null -> Optional.empty()
            sessionUpdated != null -> sessionUpdated.budget()
            eventStart != null -> Optional.empty()
            eventDelta != null -> Optional.empty()
            systemMessage != null -> Optional.empty()
            sessionUsage != null -> sessionUsage.budget()
            else -> _json.getProperty<BetaManagedAgentsBudgetLimit>("budget").asKnown()
        }

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
     * Emitted when a child thread is created. Written to the parent thread's output stream so
     * clients observing the session see child creation.
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
     * Emitted when a child thread is created. Written to the parent thread's output stream so
     * clients observing the session see child creation.
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
     * Optional<String> result = betaManagedAgentsStreamSessionThreadEvents.accept(new BetaManagedAgentsStreamSessionThreadEvents.Visitor<Optional<String>>() {
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
    fun validate(): BetaManagedAgentsStreamSessionThreadEvents = apply {
        if (validated) {
            return@apply
        }

        when {
            userMessage != null -> userMessage.validate()
            userInterrupt != null -> userInterrupt.validate()
            userToolConfirmation != null -> userToolConfirmation.validate()
            userCustomToolResult != null -> userCustomToolResult.validate()
            agentCustomToolUse != null -> agentCustomToolUse.validate()
            agentMessage != null -> agentMessage.validate()
            agentThinking != null -> agentThinking.validate()
            agentMcpToolUse != null -> agentMcpToolUse.validate()
            agentMcpToolResult != null -> agentMcpToolResult.validate()
            agentToolUse != null -> agentToolUse.validate()
            agentToolResult != null -> agentToolResult.validate()
            agentThreadMessageReceived != null -> agentThreadMessageReceived.validate()
            agentThreadMessageSent != null -> agentThreadMessageSent.validate()
            agentThreadContextCompacted != null -> agentThreadContextCompacted.validate()
            sessionError != null -> sessionError.validate()
            sessionStatusRescheduled != null -> sessionStatusRescheduled.validate()
            sessionStatusRunning != null -> sessionStatusRunning.validate()
            sessionStatusIdle != null -> sessionStatusIdle.validate()
            sessionStatusTerminated != null -> sessionStatusTerminated.validate()
            sessionThreadCreated != null -> sessionThreadCreated.validate()
            spanOutcomeEvaluationStart != null -> spanOutcomeEvaluationStart.validate()
            spanOutcomeEvaluationEnd != null -> spanOutcomeEvaluationEnd.validate()
            spanModelRequestStart != null -> spanModelRequestStart.validate()
            spanModelRequestEnd != null -> spanModelRequestEnd.validate()
            spanOutcomeEvaluationOngoing != null -> spanOutcomeEvaluationOngoing.validate()
            userDefineOutcome != null -> userDefineOutcome.validate()
            sessionDeleted != null -> sessionDeleted.validate()
            sessionThreadStatusRunning != null -> sessionThreadStatusRunning.validate()
            sessionThreadStatusIdle != null -> sessionThreadStatusIdle.validate()
            sessionThreadStatusTerminated != null -> sessionThreadStatusTerminated.validate()
            userToolResult != null -> userToolResult.validate()
            sessionThreadStatusRescheduled != null -> sessionThreadStatusRescheduled.validate()
            sessionUpdated != null -> sessionUpdated.validate()
            eventStart != null -> eventStart.validate()
            eventDelta != null -> eventDelta.validate()
            systemMessage != null -> systemMessage.validate()
            sessionUsage != null -> sessionUsage.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsStreamSessionThreadEvents: $_json"
                )
        }
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
        when {
            userMessage != null -> userMessage.validity()
            userInterrupt != null -> userInterrupt.validity()
            userToolConfirmation != null -> userToolConfirmation.validity()
            userCustomToolResult != null -> userCustomToolResult.validity()
            agentCustomToolUse != null -> agentCustomToolUse.validity()
            agentMessage != null -> agentMessage.validity()
            agentThinking != null -> agentThinking.validity()
            agentMcpToolUse != null -> agentMcpToolUse.validity()
            agentMcpToolResult != null -> agentMcpToolResult.validity()
            agentToolUse != null -> agentToolUse.validity()
            agentToolResult != null -> agentToolResult.validity()
            agentThreadMessageReceived != null -> agentThreadMessageReceived.validity()
            agentThreadMessageSent != null -> agentThreadMessageSent.validity()
            agentThreadContextCompacted != null -> agentThreadContextCompacted.validity()
            sessionError != null -> sessionError.validity()
            sessionStatusRescheduled != null -> sessionStatusRescheduled.validity()
            sessionStatusRunning != null -> sessionStatusRunning.validity()
            sessionStatusIdle != null -> sessionStatusIdle.validity()
            sessionStatusTerminated != null -> sessionStatusTerminated.validity()
            sessionThreadCreated != null -> sessionThreadCreated.validity()
            spanOutcomeEvaluationStart != null -> spanOutcomeEvaluationStart.validity()
            spanOutcomeEvaluationEnd != null -> spanOutcomeEvaluationEnd.validity()
            spanModelRequestStart != null -> spanModelRequestStart.validity()
            spanModelRequestEnd != null -> spanModelRequestEnd.validity()
            spanOutcomeEvaluationOngoing != null -> spanOutcomeEvaluationOngoing.validity()
            userDefineOutcome != null -> userDefineOutcome.validity()
            sessionDeleted != null -> sessionDeleted.validity()
            sessionThreadStatusRunning != null -> sessionThreadStatusRunning.validity()
            sessionThreadStatusIdle != null -> sessionThreadStatusIdle.validity()
            sessionThreadStatusTerminated != null -> sessionThreadStatusTerminated.validity()
            userToolResult != null -> userToolResult.validity()
            sessionThreadStatusRescheduled != null -> sessionThreadStatusRescheduled.validity()
            sessionUpdated != null -> sessionUpdated.validity()
            eventStart != null -> eventStart.validity()
            eventDelta != null -> eventDelta.validity()
            systemMessage != null -> systemMessage.validity()
            sessionUsage != null -> sessionUsage.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsStreamSessionThreadEvents &&
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
            userMessage != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{userMessage=$userMessage}"
            userInterrupt != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{userInterrupt=$userInterrupt}"
            userToolConfirmation != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{userToolConfirmation=$userToolConfirmation}"
            userCustomToolResult != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{userCustomToolResult=$userCustomToolResult}"
            agentCustomToolUse != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentCustomToolUse=$agentCustomToolUse}"
            agentMessage != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentMessage=$agentMessage}"
            agentThinking != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentThinking=$agentThinking}"
            agentMcpToolUse != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentMcpToolUse=$agentMcpToolUse}"
            agentMcpToolResult != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentMcpToolResult=$agentMcpToolResult}"
            agentToolUse != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentToolUse=$agentToolUse}"
            agentToolResult != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentToolResult=$agentToolResult}"
            agentThreadMessageReceived != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentThreadMessageReceived=$agentThreadMessageReceived}"
            agentThreadMessageSent != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentThreadMessageSent=$agentThreadMessageSent}"
            agentThreadContextCompacted != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{agentThreadContextCompacted=$agentThreadContextCompacted}"
            sessionError != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionError=$sessionError}"
            sessionStatusRescheduled != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionStatusRescheduled=$sessionStatusRescheduled}"
            sessionStatusRunning != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionStatusRunning=$sessionStatusRunning}"
            sessionStatusIdle != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionStatusIdle=$sessionStatusIdle}"
            sessionStatusTerminated != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionStatusTerminated=$sessionStatusTerminated}"
            sessionThreadCreated != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionThreadCreated=$sessionThreadCreated}"
            spanOutcomeEvaluationStart != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{spanOutcomeEvaluationStart=$spanOutcomeEvaluationStart}"
            spanOutcomeEvaluationEnd != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{spanOutcomeEvaluationEnd=$spanOutcomeEvaluationEnd}"
            spanModelRequestStart != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{spanModelRequestStart=$spanModelRequestStart}"
            spanModelRequestEnd != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{spanModelRequestEnd=$spanModelRequestEnd}"
            spanOutcomeEvaluationOngoing != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{spanOutcomeEvaluationOngoing=$spanOutcomeEvaluationOngoing}"
            userDefineOutcome != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{userDefineOutcome=$userDefineOutcome}"
            sessionDeleted != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionDeleted=$sessionDeleted}"
            sessionThreadStatusRunning != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionThreadStatusRunning=$sessionThreadStatusRunning}"
            sessionThreadStatusIdle != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionThreadStatusIdle=$sessionThreadStatusIdle}"
            sessionThreadStatusTerminated != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionThreadStatusTerminated=$sessionThreadStatusTerminated}"
            userToolResult != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{userToolResult=$userToolResult}"
            sessionThreadStatusRescheduled != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionThreadStatusRescheduled=$sessionThreadStatusRescheduled}"
            sessionUpdated != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionUpdated=$sessionUpdated}"
            eventStart != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{eventStart=$eventStart}"
            eventDelta != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{eventDelta=$eventDelta}"
            systemMessage != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{systemMessage=$systemMessage}"
            sessionUsage != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionUsage=$sessionUsage}"
            _json != null -> "BetaManagedAgentsStreamSessionThreadEvents{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaManagedAgentsStreamSessionThreadEvents")
        }

    companion object {

        /** A user message event in the session conversation. */
        @JvmStatic
        fun ofUserMessage(userMessage: BetaManagedAgentsUserMessageEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(userMessage = userMessage)

        /** An interrupt event that pauses agent execution and returns control to the user. */
        @JvmStatic
        fun ofUserInterrupt(userInterrupt: BetaManagedAgentsUserInterruptEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(userInterrupt = userInterrupt)

        /**
         * Returns an immutable instance of [BetaManagedAgentsStreamSessionThreadEvents] whose
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
        ) = BetaManagedAgentsStreamSessionThreadEvents(userToolConfirmation = userToolConfirmation)

        /** Event sent by the client providing the result of a custom tool execution. */
        @JvmStatic
        fun ofUserCustomToolResult(
            userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
        ) = BetaManagedAgentsStreamSessionThreadEvents(userCustomToolResult = userCustomToolResult)

        /**
         * Event emitted when the agent calls a custom tool. The session goes idle until the client
         * sends a `user.custom_tool_result` event with the result.
         */
        @JvmStatic
        fun ofAgentCustomToolUse(agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(agentCustomToolUse = agentCustomToolUse)

        /** An agent response event in the session conversation. */
        @JvmStatic
        fun ofAgentMessage(agentMessage: BetaManagedAgentsAgentMessageEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(agentMessage = agentMessage)

        /**
         * Indicates the agent is making forward progress via extended thinking. A progress signal,
         * not a content carrier.
         */
        @JvmStatic
        fun ofAgentThinking(agentThinking: BetaManagedAgentsAgentThinkingEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(agentThinking = agentThinking)

        /** Event emitted when the agent invokes a tool provided by an MCP server. */
        @JvmStatic
        fun ofAgentMcpToolUse(agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(agentMcpToolUse = agentMcpToolUse)

        /** Event representing the result of an MCP tool execution. */
        @JvmStatic
        fun ofAgentMcpToolResult(agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(agentMcpToolResult = agentMcpToolResult)

        /** Event emitted when the agent invokes a built-in agent tool. */
        @JvmStatic
        fun ofAgentToolUse(agentToolUse: BetaManagedAgentsAgentToolUseEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(agentToolUse = agentToolUse)

        /** Event representing the result of an agent tool execution. */
        @JvmStatic
        fun ofAgentToolResult(agentToolResult: BetaManagedAgentsAgentToolResultEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(agentToolResult = agentToolResult)

        /**
         * Delivery event written to the target thread's input stream when an agent-to-agent message
         * arrives.
         */
        @JvmStatic
        fun ofAgentThreadMessageReceived(
            agentThreadMessageReceived: BetaManagedAgentsAgentThreadMessageReceivedEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                agentThreadMessageReceived = agentThreadMessageReceived
            )

        /**
         * Observability event emitted to the sender's output stream when an agent-to-agent message
         * is sent.
         */
        @JvmStatic
        fun ofAgentThreadMessageSent(
            agentThreadMessageSent: BetaManagedAgentsAgentThreadMessageSentEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                agentThreadMessageSent = agentThreadMessageSent
            )

        /** Indicates that context compaction (summarization) occurred during the session. */
        @JvmStatic
        fun ofAgentThreadContextCompacted(
            agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                agentThreadContextCompacted = agentThreadContextCompacted
            )

        /** An error event indicating a problem occurred during session execution. */
        @JvmStatic
        fun ofSessionError(sessionError: BetaManagedAgentsSessionErrorEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(sessionError = sessionError)

        /**
         * Indicates the session is recovering from an error state and is rescheduled for execution.
         */
        @JvmStatic
        fun ofSessionStatusRescheduled(
            sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                sessionStatusRescheduled = sessionStatusRescheduled
            )

        /** Indicates the session is actively running and the agent is working. */
        @JvmStatic
        fun ofSessionStatusRunning(
            sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent
        ) = BetaManagedAgentsStreamSessionThreadEvents(sessionStatusRunning = sessionStatusRunning)

        /** Indicates the agent has paused and is awaiting user input. */
        @JvmStatic
        fun ofSessionStatusIdle(sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(sessionStatusIdle = sessionStatusIdle)

        /** Indicates the session has terminated, either due to an error or completion. */
        @JvmStatic
        fun ofSessionStatusTerminated(
            sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                sessionStatusTerminated = sessionStatusTerminated
            )

        /**
         * Emitted when a child thread is created. Written to the parent thread's output stream so
         * clients observing the session see child creation.
         */
        @JvmStatic
        fun ofSessionThreadCreated(
            sessionThreadCreated: BetaManagedAgentsSessionThreadCreatedEvent
        ) = BetaManagedAgentsStreamSessionThreadEvents(sessionThreadCreated = sessionThreadCreated)

        /** Emitted when an outcome evaluation cycle begins. */
        @JvmStatic
        fun ofSpanOutcomeEvaluationStart(
            spanOutcomeEvaluationStart: BetaManagedAgentsSpanOutcomeEvaluationStartEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
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
            BetaManagedAgentsStreamSessionThreadEvents(
                spanOutcomeEvaluationEnd = spanOutcomeEvaluationEnd
            )

        /** Emitted when a model request is initiated by the agent. */
        @JvmStatic
        fun ofSpanModelRequestStart(
            spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                spanModelRequestStart = spanModelRequestStart
            )

        /** Emitted when a model request completes. */
        @JvmStatic
        fun ofSpanModelRequestEnd(spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(spanModelRequestEnd = spanModelRequestEnd)

        /**
         * Periodic heartbeat emitted while an outcome evaluation cycle is in progress.
         * Distinguishes 'evaluation is actively running' from 'evaluation is stuck' between the
         * corresponding `span.outcome_evaluation_start` and `span.outcome_evaluation_end` events.
         */
        @JvmStatic
        fun ofSpanOutcomeEvaluationOngoing(
            spanOutcomeEvaluationOngoing: BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                spanOutcomeEvaluationOngoing = spanOutcomeEvaluationOngoing
            )

        /**
         * Echo of a `user.define_outcome` input event. Carries the server-generated `outcome_id`
         * that subsequent `span.outcome_evaluation_*` events reference.
         */
        @JvmStatic
        fun ofUserDefineOutcome(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(userDefineOutcome = userDefineOutcome)

        /**
         * Emitted when a session has been deleted. Terminates any active event stream — no further
         * events will be emitted for this session.
         */
        @JvmStatic
        fun ofSessionDeleted(sessionDeleted: BetaManagedAgentsSessionDeletedEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(sessionDeleted = sessionDeleted)

        /**
         * A session thread has begun executing. Emitted on the thread's own stream and cross-posted
         * to the primary stream for child threads.
         */
        @JvmStatic
        fun ofSessionThreadStatusRunning(
            sessionThreadStatusRunning: BetaManagedAgentsSessionThreadStatusRunningEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                sessionThreadStatusRunning = sessionThreadStatusRunning
            )

        /**
         * A session thread has yielded and is awaiting input. Emitted on the thread's own stream
         * and cross-posted to the primary stream for child threads.
         */
        @JvmStatic
        fun ofSessionThreadStatusIdle(
            sessionThreadStatusIdle: BetaManagedAgentsSessionThreadStatusIdleEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                sessionThreadStatusIdle = sessionThreadStatusIdle
            )

        /**
         * A session thread has terminated and will accept no further input. Emitted on the thread's
         * own stream and cross-posted to the primary stream for child threads.
         */
        @JvmStatic
        fun ofSessionThreadStatusTerminated(
            sessionThreadStatusTerminated: BetaManagedAgentsSessionThreadStatusTerminatedEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                sessionThreadStatusTerminated = sessionThreadStatusTerminated
            )

        /**
         * Event sent by the client providing the result of an agent-toolset tool execution. Only
         * valid on `self_hosted` environments, where sandbox-routed tools are executed by the
         * client rather than the server.
         */
        @JvmStatic
        fun ofUserToolResult(userToolResult: BetaManagedAgentsUserToolResultEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(userToolResult = userToolResult)

        /**
         * A session thread hit a transient error and is retrying automatically. Emitted on the
         * thread's own stream and cross-posted to the primary stream for child threads.
         */
        @JvmStatic
        fun ofSessionThreadStatusRescheduled(
            sessionThreadStatusRescheduled: BetaManagedAgentsSessionThreadStatusRescheduledEvent
        ) =
            BetaManagedAgentsStreamSessionThreadEvents(
                sessionThreadStatusRescheduled = sessionThreadStatusRescheduled
            )

        /**
         * Emitted when an UpdateSession request changed at least one field. Carries only the fields
         * that changed; absent fields were not part of the update. The new configuration applies
         * from the next turn.
         */
        @JvmStatic
        fun ofSessionUpdated(sessionUpdated: BetaManagedAgentsSessionUpdatedEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(sessionUpdated = sessionUpdated)

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
            BetaManagedAgentsStreamSessionThreadEvents(eventStart = eventStart)

        /**
         * Returns an immutable instance of [BetaManagedAgentsStreamSessionThreadEvents] whose
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
            BetaManagedAgentsStreamSessionThreadEvents(eventDelta = eventDelta)

        /**
         * A mid-conversation system message event. Carries system-role content that is appended to
         * the session as a `role: "system"` turn.
         */
        @JvmStatic
        fun ofSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(systemMessage = systemMessage)

        /** Periodic snapshot of the session's cumulative usage and tracked list cost. */
        @JvmStatic
        fun ofSessionUsage(sessionUsage: BetaManagedAgentsSessionUsageEvent) =
            BetaManagedAgentsStreamSessionThreadEvents(sessionUsage = sessionUsage)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsStreamSessionThreadEvents] to a value of type [T].
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
         * Emitted when a child thread is created. Written to the parent thread's output stream so
         * clients observing the session see child creation.
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
         * Maps an unknown variant of [BetaManagedAgentsStreamSessionThreadEvents] to a value of
         * type [T].
         *
         * An instance of [BetaManagedAgentsStreamSessionThreadEvents] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsStreamSessionThreadEvents: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsStreamSessionThreadEvents>(
            BetaManagedAgentsStreamSessionThreadEvents::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsStreamSessionThreadEvents {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "user.message" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsUserMessageEvent>())
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                userMessage = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "user.interrupt" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserInterruptEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                userInterrupt = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "user.tool_confirmation" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserToolConfirmationEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                userToolConfirmation = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "user.custom_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserCustomToolResultEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                userCustomToolResult = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.custom_tool_use" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentCustomToolUseEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentCustomToolUse = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentMessageEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentMessage = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.thinking" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThinkingEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentThinking = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.mcp_tool_use" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentMcpToolUseEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentMcpToolUse = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.mcp_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentMcpToolResultEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentMcpToolResult = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.tool_use" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentToolUseEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentToolUse = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentToolResultEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentToolResult = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.thread_message_received" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThreadMessageReceivedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentThreadMessageReceived = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.thread_message_sent" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThreadMessageSentEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentThreadMessageSent = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "agent.thread_context_compacted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThreadContextCompactedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                agentThreadContextCompacted = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionErrorEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionError = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.status_rescheduled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionStatusRescheduledEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionStatusRescheduled = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.status_running" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionStatusRunningEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionStatusRunning = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.status_idle" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionStatusIdleEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionStatusIdle = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.status_terminated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionStatusTerminatedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionStatusTerminated = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.thread_created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadCreatedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionThreadCreated = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "span.outcome_evaluation_start" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationStartEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                spanOutcomeEvaluationStart = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "span.outcome_evaluation_end" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationEndEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                spanOutcomeEvaluationEnd = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "span.model_request_start" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanModelRequestStartEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                spanModelRequestStart = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "span.model_request_end" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanModelRequestEndEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                spanModelRequestEnd = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "span.outcome_evaluation_ongoing" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                spanOutcomeEvaluationOngoing = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "user.define_outcome" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserDefineOutcomeEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                userDefineOutcome = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionDeletedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionDeleted = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.thread_status_running" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadStatusRunningEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionThreadStatusRunning = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.thread_status_idle" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadStatusIdleEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionThreadStatusIdle = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.thread_status_terminated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadStatusTerminatedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionThreadStatusTerminated = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "user.tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserToolResultEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                userToolResult = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.thread_status_rescheduled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionThreadStatusRescheduledEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionThreadStatusRescheduled = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.updated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionUpdatedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionUpdated = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "event_start" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsStartEvent>())
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                eventStart = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "event_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsDeltaEvent>())
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                eventDelta = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "system.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSystemMessageEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                systemMessage = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
                "session.usage" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionUsageEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionThreadEvents(
                                sessionUsage = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsStreamSessionThreadEvents(_json = json)
                }
            }

            return BetaManagedAgentsStreamSessionThreadEvents(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsStreamSessionThreadEvents>(
            BetaManagedAgentsStreamSessionThreadEvents::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsStreamSessionThreadEvents,
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
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsStreamSessionThreadEvents"
                    )
            }
        }
    }

    class Type private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val USER_MESSAGE = Type(JsonField.of("user.message"))

            @JvmField val USER_INTERRUPT = Type(JsonField.of("user.interrupt"))

            @JvmField val USER_TOOL_CONFIRMATION = Type(JsonField.of("user.tool_confirmation"))

            @JvmField val USER_CUSTOM_TOOL_RESULT = Type(JsonField.of("user.custom_tool_result"))

            @JvmField val AGENT_CUSTOM_TOOL_USE = Type(JsonField.of("agent.custom_tool_use"))

            @JvmField val AGENT_MESSAGE = Type(JsonField.of("agent.message"))

            @JvmField val AGENT_THINKING = Type(JsonField.of("agent.thinking"))

            @JvmField val AGENT_MCP_TOOL_USE = Type(JsonField.of("agent.mcp_tool_use"))

            @JvmField val AGENT_MCP_TOOL_RESULT = Type(JsonField.of("agent.mcp_tool_result"))

            @JvmField val AGENT_TOOL_USE = Type(JsonField.of("agent.tool_use"))

            @JvmField val AGENT_TOOL_RESULT = Type(JsonField.of("agent.tool_result"))

            @JvmField
            val AGENT_THREAD_MESSAGE_RECEIVED = Type(JsonField.of("agent.thread_message_received"))

            @JvmField
            val AGENT_THREAD_MESSAGE_SENT = Type(JsonField.of("agent.thread_message_sent"))

            @JvmField
            val AGENT_THREAD_CONTEXT_COMPACTED =
                Type(JsonField.of("agent.thread_context_compacted"))

            @JvmField val SESSION_ERROR = Type(JsonField.of("session.error"))

            @JvmField
            val SESSION_STATUS_RESCHEDULED = Type(JsonField.of("session.status_rescheduled"))

            @JvmField val SESSION_STATUS_RUNNING = Type(JsonField.of("session.status_running"))

            @JvmField val SESSION_STATUS_IDLE = Type(JsonField.of("session.status_idle"))

            @JvmField
            val SESSION_STATUS_TERMINATED = Type(JsonField.of("session.status_terminated"))

            @JvmField val SESSION_THREAD_CREATED = Type(JsonField.of("session.thread_created"))

            @JvmField
            val SPAN_OUTCOME_EVALUATION_START = Type(JsonField.of("span.outcome_evaluation_start"))

            @JvmField
            val SPAN_OUTCOME_EVALUATION_END = Type(JsonField.of("span.outcome_evaluation_end"))

            @JvmField val SPAN_MODEL_REQUEST_START = Type(JsonField.of("span.model_request_start"))

            @JvmField val SPAN_MODEL_REQUEST_END = Type(JsonField.of("span.model_request_end"))

            @JvmField
            val SPAN_OUTCOME_EVALUATION_ONGOING =
                Type(JsonField.of("span.outcome_evaluation_ongoing"))

            @JvmField val USER_DEFINE_OUTCOME = Type(JsonField.of("user.define_outcome"))

            @JvmField val SESSION_DELETED = Type(JsonField.of("session.deleted"))

            @JvmField
            val SESSION_THREAD_STATUS_RUNNING = Type(JsonField.of("session.thread_status_running"))

            @JvmField
            val SESSION_THREAD_STATUS_IDLE = Type(JsonField.of("session.thread_status_idle"))

            @JvmField
            val SESSION_THREAD_STATUS_TERMINATED =
                Type(JsonField.of("session.thread_status_terminated"))

            @JvmField val USER_TOOL_RESULT = Type(JsonField.of("user.tool_result"))

            @JvmField
            val SESSION_THREAD_STATUS_RESCHEDULED =
                Type(JsonField.of("session.thread_status_rescheduled"))

            @JvmField val SESSION_UPDATED = Type(JsonField.of("session.updated"))

            @JvmField val EVENT_START = Type(JsonField.of("event_start"))

            @JvmField val EVENT_DELTA = Type(JsonField.of("event_delta"))

            @JvmField val SYSTEM_MESSAGE = Type(JsonField.of("system.message"))

            @JvmField val SESSION_USAGE = Type(JsonField.of("session.usage"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "user.message" -> USER_MESSAGE
                    "user.interrupt" -> USER_INTERRUPT
                    "user.tool_confirmation" -> USER_TOOL_CONFIRMATION
                    "user.custom_tool_result" -> USER_CUSTOM_TOOL_RESULT
                    "agent.custom_tool_use" -> AGENT_CUSTOM_TOOL_USE
                    "agent.message" -> AGENT_MESSAGE
                    "agent.thinking" -> AGENT_THINKING
                    "agent.mcp_tool_use" -> AGENT_MCP_TOOL_USE
                    "agent.mcp_tool_result" -> AGENT_MCP_TOOL_RESULT
                    "agent.tool_use" -> AGENT_TOOL_USE
                    "agent.tool_result" -> AGENT_TOOL_RESULT
                    "agent.thread_message_received" -> AGENT_THREAD_MESSAGE_RECEIVED
                    "agent.thread_message_sent" -> AGENT_THREAD_MESSAGE_SENT
                    "agent.thread_context_compacted" -> AGENT_THREAD_CONTEXT_COMPACTED
                    "session.error" -> SESSION_ERROR
                    "session.status_rescheduled" -> SESSION_STATUS_RESCHEDULED
                    "session.status_running" -> SESSION_STATUS_RUNNING
                    "session.status_idle" -> SESSION_STATUS_IDLE
                    "session.status_terminated" -> SESSION_STATUS_TERMINATED
                    "session.thread_created" -> SESSION_THREAD_CREATED
                    "span.outcome_evaluation_start" -> SPAN_OUTCOME_EVALUATION_START
                    "span.outcome_evaluation_end" -> SPAN_OUTCOME_EVALUATION_END
                    "span.model_request_start" -> SPAN_MODEL_REQUEST_START
                    "span.model_request_end" -> SPAN_MODEL_REQUEST_END
                    "span.outcome_evaluation_ongoing" -> SPAN_OUTCOME_EVALUATION_ONGOING
                    "user.define_outcome" -> USER_DEFINE_OUTCOME
                    "session.deleted" -> SESSION_DELETED
                    "session.thread_status_running" -> SESSION_THREAD_STATUS_RUNNING
                    "session.thread_status_idle" -> SESSION_THREAD_STATUS_IDLE
                    "session.thread_status_terminated" -> SESSION_THREAD_STATUS_TERMINATED
                    "user.tool_result" -> USER_TOOL_RESULT
                    "session.thread_status_rescheduled" -> SESSION_THREAD_STATUS_RESCHEDULED
                    "session.updated" -> SESSION_UPDATED
                    "event_start" -> EVENT_START
                    "event_delta" -> EVENT_DELTA
                    "system.message" -> SYSTEM_MESSAGE
                    "session.usage" -> SESSION_USAGE
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
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
