// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
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
    private val agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent? =
        null,
    private val sessionError: BetaManagedAgentsSessionErrorEvent? = null,
    private val sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent? = null,
    private val sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent? = null,
    private val sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent? = null,
    private val sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent? = null,
    private val spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent? = null,
    private val spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent? = null,
    private val sessionDeleted: BetaManagedAgentsSessionDeletedEvent? = null,
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

    /** Emitted when a model request is initiated by the agent. */
    fun spanModelRequestStart(): Optional<BetaManagedAgentsSpanModelRequestStartEvent> =
        Optional.ofNullable(spanModelRequestStart)

    /** Emitted when a model request completes. */
    fun spanModelRequestEnd(): Optional<BetaManagedAgentsSpanModelRequestEndEvent> =
        Optional.ofNullable(spanModelRequestEnd)

    /**
     * Emitted when a session has been deleted. Terminates any active event stream — no further
     * events will be emitted for this session.
     */
    fun sessionDeleted(): Optional<BetaManagedAgentsSessionDeletedEvent> =
        Optional.ofNullable(sessionDeleted)

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

    fun isAgentThreadContextCompacted(): Boolean = agentThreadContextCompacted != null

    fun isSessionError(): Boolean = sessionError != null

    fun isSessionStatusRescheduled(): Boolean = sessionStatusRescheduled != null

    fun isSessionStatusRunning(): Boolean = sessionStatusRunning != null

    fun isSessionStatusIdle(): Boolean = sessionStatusIdle != null

    fun isSessionStatusTerminated(): Boolean = sessionStatusTerminated != null

    fun isSpanModelRequestStart(): Boolean = spanModelRequestStart != null

    fun isSpanModelRequestEnd(): Boolean = spanModelRequestEnd != null

    fun isSessionDeleted(): Boolean = sessionDeleted != null

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

    /** Emitted when a model request is initiated by the agent. */
    fun asSpanModelRequestStart(): BetaManagedAgentsSpanModelRequestStartEvent =
        spanModelRequestStart.getOrThrow("spanModelRequestStart")

    /** Emitted when a model request completes. */
    fun asSpanModelRequestEnd(): BetaManagedAgentsSpanModelRequestEndEvent =
        spanModelRequestEnd.getOrThrow("spanModelRequestEnd")

    /**
     * Emitted when a session has been deleted. Terminates any active event stream — no further
     * events will be emitted for this session.
     */
    fun asSessionDeleted(): BetaManagedAgentsSessionDeletedEvent =
        sessionDeleted.getOrThrow("sessionDeleted")

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
            agentThreadContextCompacted != null ->
                visitor.visitAgentThreadContextCompacted(agentThreadContextCompacted)
            sessionError != null -> visitor.visitSessionError(sessionError)
            sessionStatusRescheduled != null ->
                visitor.visitSessionStatusRescheduled(sessionStatusRescheduled)
            sessionStatusRunning != null -> visitor.visitSessionStatusRunning(sessionStatusRunning)
            sessionStatusIdle != null -> visitor.visitSessionStatusIdle(sessionStatusIdle)
            sessionStatusTerminated != null ->
                visitor.visitSessionStatusTerminated(sessionStatusTerminated)
            spanModelRequestStart != null ->
                visitor.visitSpanModelRequestStart(spanModelRequestStart)
            spanModelRequestEnd != null -> visitor.visitSpanModelRequestEnd(spanModelRequestEnd)
            sessionDeleted != null -> visitor.visitSessionDeleted(sessionDeleted)
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

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ) {
                    sessionDeleted.validate()
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

                override fun visitSpanModelRequestStart(
                    spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
                ) = spanModelRequestStart.validity()

                override fun visitSpanModelRequestEnd(
                    spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
                ) = spanModelRequestEnd.validity()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaManagedAgentsSessionDeletedEvent
                ) = sessionDeleted.validity()

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
            agentThreadContextCompacted == other.agentThreadContextCompacted &&
            sessionError == other.sessionError &&
            sessionStatusRescheduled == other.sessionStatusRescheduled &&
            sessionStatusRunning == other.sessionStatusRunning &&
            sessionStatusIdle == other.sessionStatusIdle &&
            sessionStatusTerminated == other.sessionStatusTerminated &&
            spanModelRequestStart == other.spanModelRequestStart &&
            spanModelRequestEnd == other.spanModelRequestEnd &&
            sessionDeleted == other.sessionDeleted
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
            agentThreadContextCompacted,
            sessionError,
            sessionStatusRescheduled,
            sessionStatusRunning,
            sessionStatusIdle,
            sessionStatusTerminated,
            spanModelRequestStart,
            spanModelRequestEnd,
            sessionDeleted,
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
            spanModelRequestStart != null ->
                "BetaManagedAgentsStreamSessionEvents{spanModelRequestStart=$spanModelRequestStart}"
            spanModelRequestEnd != null ->
                "BetaManagedAgentsStreamSessionEvents{spanModelRequestEnd=$spanModelRequestEnd}"
            sessionDeleted != null ->
                "BetaManagedAgentsStreamSessionEvents{sessionDeleted=$sessionDeleted}"
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
         * Emitted when a session has been deleted. Terminates any active event stream — no further
         * events will be emitted for this session.
         */
        @JvmStatic
        fun ofSessionDeleted(sessionDeleted: BetaManagedAgentsSessionDeletedEvent) =
            BetaManagedAgentsStreamSessionEvents(sessionDeleted = sessionDeleted)
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

        /** Emitted when a model request is initiated by the agent. */
        fun visitSpanModelRequestStart(
            spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent
        ): T

        /** Emitted when a model request completes. */
        fun visitSpanModelRequestEnd(
            spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent
        ): T

        /**
         * Emitted when a session has been deleted. Terminates any active event stream — no further
         * events will be emitted for this session.
         */
        fun visitSessionDeleted(sessionDeleted: BetaManagedAgentsSessionDeletedEvent): T

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
                "session.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSessionDeletedEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsStreamSessionEvents(sessionDeleted = it, _json = json)
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
                value.spanModelRequestStart != null ->
                    generator.writeObject(value.spanModelRequestStart)
                value.spanModelRequestEnd != null ->
                    generator.writeObject(value.spanModelRequestEnd)
                value.sessionDeleted != null -> generator.writeObject(value.sessionDeleted)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsStreamSessionEvents")
            }
        }
    }
}
