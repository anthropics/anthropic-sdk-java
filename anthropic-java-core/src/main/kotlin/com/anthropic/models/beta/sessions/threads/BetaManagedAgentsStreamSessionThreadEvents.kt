// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
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
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionDeletedEvent
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
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationEndEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSpanOutcomeEvaluationStartEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserCustomToolResultEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserDefineOutcomeEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserInterruptEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserToolConfirmationEvent
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
    private val sessionThreadStatusRescheduled:
        BetaManagedAgentsSessionThreadStatusRescheduledEvent? =
        null,
    private val _json: JsonValue? = null,
) {

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
     * A session thread hit a transient error and is retrying automatically. Emitted on the thread's
     * own stream and cross-posted to the primary stream for child threads.
     */
    fun sessionThreadStatusRescheduled():
        Optional<BetaManagedAgentsSessionThreadStatusRescheduledEvent> =
        Optional.ofNullable(sessionThreadStatusRescheduled)

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

    fun isSessionThreadStatusRescheduled(): Boolean = sessionThreadStatusRescheduled != null

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
     * A session thread hit a transient error and is retrying automatically. Emitted on the thread's
     * own stream and cross-posted to the primary stream for child threads.
     */
    fun asSessionThreadStatusRescheduled(): BetaManagedAgentsSessionThreadStatusRescheduledEvent =
        sessionThreadStatusRescheduled.getOrThrow("sessionThreadStatusRescheduled")

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
            sessionThreadStatusRescheduled != null ->
                visitor.visitSessionThreadStatusRescheduled(sessionThreadStatusRescheduled)
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

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ) {
                    sessionThreadStatusRescheduled.validate()
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

                override fun visitSessionThreadStatusRescheduled(
                    sessionThreadStatusRescheduled:
                        BetaManagedAgentsSessionThreadStatusRescheduledEvent
                ) = sessionThreadStatusRescheduled.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

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
            sessionThreadStatusRescheduled == other.sessionThreadStatusRescheduled
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
            sessionThreadStatusRescheduled,
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
            sessionThreadStatusRescheduled != null ->
                "BetaManagedAgentsStreamSessionThreadEvents{sessionThreadStatusRescheduled=$sessionThreadStatusRescheduled}"
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
         * Emitted when a subagent is spawned as a new thread. Written to the parent thread's output
         * stream so clients observing the session see child creation.
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
         * A session thread hit a transient error and is retrying automatically. Emitted on the
         * thread's own stream and cross-posted to the primary stream for child threads.
         */
        fun visitSessionThreadStatusRescheduled(
            sessionThreadStatusRescheduled: BetaManagedAgentsSessionThreadStatusRescheduledEvent
        ): T

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
                value.sessionThreadStatusRescheduled != null ->
                    generator.writeObject(value.sessionThreadStatusRescheduled)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsStreamSessionThreadEvents"
                    )
            }
        }
    }
}
