package com.anthropic.models.beta.sessions.events

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/** The `type` of a session event. */
class BetaManagedAgentsSessionEventType private constructor(private val value: JsonField<String>) :
    Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val USER_MESSAGE = BetaManagedAgentsSessionEventType(JsonField.of("user.message"))

        @JvmField
        val USER_INTERRUPT = BetaManagedAgentsSessionEventType(JsonField.of("user.interrupt"))

        @JvmField
        val USER_TOOL_CONFIRMATION =
            BetaManagedAgentsSessionEventType(JsonField.of("user.tool_confirmation"))

        @JvmField
        val USER_CUSTOM_TOOL_RESULT =
            BetaManagedAgentsSessionEventType(JsonField.of("user.custom_tool_result"))

        @JvmField
        val AGENT_CUSTOM_TOOL_USE =
            BetaManagedAgentsSessionEventType(JsonField.of("agent.custom_tool_use"))

        @JvmField
        val AGENT_MESSAGE = BetaManagedAgentsSessionEventType(JsonField.of("agent.message"))

        @JvmField
        val AGENT_THINKING = BetaManagedAgentsSessionEventType(JsonField.of("agent.thinking"))

        @JvmField
        val AGENT_MCP_TOOL_USE =
            BetaManagedAgentsSessionEventType(JsonField.of("agent.mcp_tool_use"))

        @JvmField
        val AGENT_MCP_TOOL_RESULT =
            BetaManagedAgentsSessionEventType(JsonField.of("agent.mcp_tool_result"))

        @JvmField
        val AGENT_TOOL_USE = BetaManagedAgentsSessionEventType(JsonField.of("agent.tool_use"))

        @JvmField
        val AGENT_TOOL_RESULT = BetaManagedAgentsSessionEventType(JsonField.of("agent.tool_result"))

        @JvmField
        val AGENT_THREAD_MESSAGE_RECEIVED =
            BetaManagedAgentsSessionEventType(JsonField.of("agent.thread_message_received"))

        @JvmField
        val AGENT_THREAD_MESSAGE_SENT =
            BetaManagedAgentsSessionEventType(JsonField.of("agent.thread_message_sent"))

        @JvmField
        val AGENT_THREAD_CONTEXT_COMPACTED =
            BetaManagedAgentsSessionEventType(JsonField.of("agent.thread_context_compacted"))

        @JvmField
        val SESSION_ERROR = BetaManagedAgentsSessionEventType(JsonField.of("session.error"))

        @JvmField
        val SESSION_STATUS_RESCHEDULED =
            BetaManagedAgentsSessionEventType(JsonField.of("session.status_rescheduled"))

        @JvmField
        val SESSION_STATUS_RUNNING =
            BetaManagedAgentsSessionEventType(JsonField.of("session.status_running"))

        @JvmField
        val SESSION_STATUS_IDLE =
            BetaManagedAgentsSessionEventType(JsonField.of("session.status_idle"))

        @JvmField
        val SESSION_STATUS_TERMINATED =
            BetaManagedAgentsSessionEventType(JsonField.of("session.status_terminated"))

        @JvmField
        val SESSION_THREAD_CREATED =
            BetaManagedAgentsSessionEventType(JsonField.of("session.thread_created"))

        @JvmField
        val SPAN_OUTCOME_EVALUATION_START =
            BetaManagedAgentsSessionEventType(JsonField.of("span.outcome_evaluation_start"))

        @JvmField
        val SPAN_OUTCOME_EVALUATION_END =
            BetaManagedAgentsSessionEventType(JsonField.of("span.outcome_evaluation_end"))

        @JvmField
        val SPAN_MODEL_REQUEST_START =
            BetaManagedAgentsSessionEventType(JsonField.of("span.model_request_start"))

        @JvmField
        val SPAN_MODEL_REQUEST_END =
            BetaManagedAgentsSessionEventType(JsonField.of("span.model_request_end"))

        @JvmField
        val SPAN_OUTCOME_EVALUATION_ONGOING =
            BetaManagedAgentsSessionEventType(JsonField.of("span.outcome_evaluation_ongoing"))

        @JvmField
        val USER_DEFINE_OUTCOME =
            BetaManagedAgentsSessionEventType(JsonField.of("user.define_outcome"))

        @JvmField
        val SESSION_THREAD_STATUS_RUNNING =
            BetaManagedAgentsSessionEventType(JsonField.of("session.thread_status_running"))

        @JvmField
        val SESSION_THREAD_STATUS_IDLE =
            BetaManagedAgentsSessionEventType(JsonField.of("session.thread_status_idle"))

        @JvmField
        val SESSION_THREAD_STATUS_TERMINATED =
            BetaManagedAgentsSessionEventType(JsonField.of("session.thread_status_terminated"))

        @JvmField
        val USER_TOOL_RESULT = BetaManagedAgentsSessionEventType(JsonField.of("user.tool_result"))

        @JvmField
        val SESSION_THREAD_STATUS_RESCHEDULED =
            BetaManagedAgentsSessionEventType(JsonField.of("session.thread_status_rescheduled"))

        @JvmField
        val SESSION_UPDATED = BetaManagedAgentsSessionEventType(JsonField.of("session.updated"))

        @JvmField
        val SYSTEM_MESSAGE = BetaManagedAgentsSessionEventType(JsonField.of("system.message"))

        @JvmField
        val SESSION_USAGE = BetaManagedAgentsSessionEventType(JsonField.of("session.usage"))

        @JvmField
        val WORKFLOW_RUN_CREATED =
            BetaManagedAgentsSessionEventType(JsonField.of("workflow_run.created"))

        @JvmField
        val WORKFLOW_RUN_STATUS_RUNNING =
            BetaManagedAgentsSessionEventType(JsonField.of("workflow_run.status_running"))

        @JvmField
        val WORKFLOW_RUN_STATUS_IDLE =
            BetaManagedAgentsSessionEventType(JsonField.of("workflow_run.status_idle"))

        @JvmField
        val WORKFLOW_RUN_STATUS_ENDED =
            BetaManagedAgentsSessionEventType(JsonField.of("workflow_run.status_ended"))

        @JvmField
        val WORKFLOW_RUN_ERROR =
            BetaManagedAgentsSessionEventType(JsonField.of("workflow_run.error"))

        @JvmField
        val WORKFLOW_RUN_PHASE_STARTED =
            BetaManagedAgentsSessionEventType(JsonField.of("workflow_run.phase_started"))

        @JvmField
        val WORKFLOW_RUN_PHASE_ENDED =
            BetaManagedAgentsSessionEventType(JsonField.of("workflow_run.phase_ended"))

        @JvmStatic
        fun of(value: String): BetaManagedAgentsSessionEventType =
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
                "session.thread_status_running" -> SESSION_THREAD_STATUS_RUNNING
                "session.thread_status_idle" -> SESSION_THREAD_STATUS_IDLE
                "session.thread_status_terminated" -> SESSION_THREAD_STATUS_TERMINATED
                "user.tool_result" -> USER_TOOL_RESULT
                "session.thread_status_rescheduled" -> SESSION_THREAD_STATUS_RESCHEDULED
                "session.updated" -> SESSION_UPDATED
                "system.message" -> SYSTEM_MESSAGE
                "session.usage" -> SESSION_USAGE
                "workflow_run.created" -> WORKFLOW_RUN_CREATED
                "workflow_run.status_running" -> WORKFLOW_RUN_STATUS_RUNNING
                "workflow_run.status_idle" -> WORKFLOW_RUN_STATUS_IDLE
                "workflow_run.status_ended" -> WORKFLOW_RUN_STATUS_ENDED
                "workflow_run.error" -> WORKFLOW_RUN_ERROR
                "workflow_run.phase_started" -> WORKFLOW_RUN_PHASE_STARTED
                "workflow_run.phase_ended" -> WORKFLOW_RUN_PHASE_ENDED
                else -> BetaManagedAgentsSessionEventType(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaManagedAgentsSessionEventType =
            value.asString().getOrNull()?.let { of(it) } ?: BetaManagedAgentsSessionEventType(value)
    }

    /** An enum containing [BetaManagedAgentsSessionEventType]'s known values. */
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
        SESSION_THREAD_STATUS_RUNNING,
        SESSION_THREAD_STATUS_IDLE,
        SESSION_THREAD_STATUS_TERMINATED,
        USER_TOOL_RESULT,
        SESSION_THREAD_STATUS_RESCHEDULED,
        SESSION_UPDATED,
        SYSTEM_MESSAGE,
        SESSION_USAGE,
        WORKFLOW_RUN_CREATED,
        WORKFLOW_RUN_STATUS_RUNNING,
        WORKFLOW_RUN_STATUS_IDLE,
        WORKFLOW_RUN_STATUS_ENDED,
        WORKFLOW_RUN_ERROR,
        WORKFLOW_RUN_PHASE_STARTED,
        WORKFLOW_RUN_PHASE_ENDED,
    }

    /**
     * An enum containing [BetaManagedAgentsSessionEventType]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [BetaManagedAgentsSessionEventType] can contain an unknown value in a couple
     * of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
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
        SESSION_THREAD_STATUS_RUNNING,
        SESSION_THREAD_STATUS_IDLE,
        SESSION_THREAD_STATUS_TERMINATED,
        USER_TOOL_RESULT,
        SESSION_THREAD_STATUS_RESCHEDULED,
        SESSION_UPDATED,
        SYSTEM_MESSAGE,
        SESSION_USAGE,
        WORKFLOW_RUN_CREATED,
        WORKFLOW_RUN_STATUS_RUNNING,
        WORKFLOW_RUN_STATUS_IDLE,
        WORKFLOW_RUN_STATUS_ENDED,
        WORKFLOW_RUN_ERROR,
        WORKFLOW_RUN_PHASE_STARTED,
        WORKFLOW_RUN_PHASE_ENDED,
        /**
         * An enum member indicating that [BetaManagedAgentsSessionEventType] was instantiated with
         * an unknown value.
         */
        _UNKNOWN,
    }

    /**
     * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN] if
     * the class was instantiated with an unknown value.
     *
     * Use the [known] method instead if you're certain the value is always known or if you want to
     * throw for the unknown case.
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
            SESSION_THREAD_STATUS_RUNNING -> Value.SESSION_THREAD_STATUS_RUNNING
            SESSION_THREAD_STATUS_IDLE -> Value.SESSION_THREAD_STATUS_IDLE
            SESSION_THREAD_STATUS_TERMINATED -> Value.SESSION_THREAD_STATUS_TERMINATED
            USER_TOOL_RESULT -> Value.USER_TOOL_RESULT
            SESSION_THREAD_STATUS_RESCHEDULED -> Value.SESSION_THREAD_STATUS_RESCHEDULED
            SESSION_UPDATED -> Value.SESSION_UPDATED
            SYSTEM_MESSAGE -> Value.SYSTEM_MESSAGE
            SESSION_USAGE -> Value.SESSION_USAGE
            WORKFLOW_RUN_CREATED -> Value.WORKFLOW_RUN_CREATED
            WORKFLOW_RUN_STATUS_RUNNING -> Value.WORKFLOW_RUN_STATUS_RUNNING
            WORKFLOW_RUN_STATUS_IDLE -> Value.WORKFLOW_RUN_STATUS_IDLE
            WORKFLOW_RUN_STATUS_ENDED -> Value.WORKFLOW_RUN_STATUS_ENDED
            WORKFLOW_RUN_ERROR -> Value.WORKFLOW_RUN_ERROR
            WORKFLOW_RUN_PHASE_STARTED -> Value.WORKFLOW_RUN_PHASE_STARTED
            WORKFLOW_RUN_PHASE_ENDED -> Value.WORKFLOW_RUN_PHASE_ENDED
            else -> Value._UNKNOWN
        }

    /**
     * Returns an enum member corresponding to this class instance's value.
     *
     * Use the [value] method instead if you're uncertain the value is always known and don't want
     * to throw for the unknown case.
     *
     * @throws AnthropicInvalidDataException if this class instance's value is a not a known member.
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
            SESSION_THREAD_STATUS_RUNNING -> Known.SESSION_THREAD_STATUS_RUNNING
            SESSION_THREAD_STATUS_IDLE -> Known.SESSION_THREAD_STATUS_IDLE
            SESSION_THREAD_STATUS_TERMINATED -> Known.SESSION_THREAD_STATUS_TERMINATED
            USER_TOOL_RESULT -> Known.USER_TOOL_RESULT
            SESSION_THREAD_STATUS_RESCHEDULED -> Known.SESSION_THREAD_STATUS_RESCHEDULED
            SESSION_UPDATED -> Known.SESSION_UPDATED
            SYSTEM_MESSAGE -> Known.SYSTEM_MESSAGE
            SESSION_USAGE -> Known.SESSION_USAGE
            WORKFLOW_RUN_CREATED -> Known.WORKFLOW_RUN_CREATED
            WORKFLOW_RUN_STATUS_RUNNING -> Known.WORKFLOW_RUN_STATUS_RUNNING
            WORKFLOW_RUN_STATUS_IDLE -> Known.WORKFLOW_RUN_STATUS_IDLE
            WORKFLOW_RUN_STATUS_ENDED -> Known.WORKFLOW_RUN_STATUS_ENDED
            WORKFLOW_RUN_ERROR -> Known.WORKFLOW_RUN_ERROR
            WORKFLOW_RUN_PHASE_STARTED -> Known.WORKFLOW_RUN_PHASE_STARTED
            WORKFLOW_RUN_PHASE_ENDED -> Known.WORKFLOW_RUN_PHASE_ENDED
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsSessionEventType: $value"
                )
        }

    /**
     * Returns this class instance's primitive wire representation.
     *
     * This differs from the [toString] method because that method is primarily for debugging and
     * generally doesn't throw.
     *
     * @throws AnthropicInvalidDataException if this class instance's value does not have the
     *   expected primitive type.
     */
    fun asString(): String =
        _value().asString().orElseThrow { AnthropicInvalidDataException("Value is not a String") }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaManagedAgentsSessionEventType = apply {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionEventType && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
