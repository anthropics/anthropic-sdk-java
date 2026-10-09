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
import com.fasterxml.jackson.annotation.JsonCreator
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

/**
 * Why a workflow run did not finish, or was not created. More types may be added. On
 * `workflow_run.status_ended`, for a `type` you do not recognize, rely on the event's
 * `result.type`.
 */
@JsonDeserialize(using = BetaManagedAgentsWorkflowRunError.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsWorkflowRunError.Serializer::class)
class BetaManagedAgentsWorkflowRunError
private constructor(
    private val timeout: BetaManagedAgentsTimeoutWorkflowRunError? = null,
    private val program: BetaManagedAgentsProgramWorkflowRunError? = null,
    private val unknown: BetaManagedAgentsUnknownWorkflowRunError? = null,
    private val threadLimit: BetaManagedAgentsThreadLimitWorkflowRunError? = null,
    private val maxWorkflowRuns: BetaManagedAgentsMaxWorkflowRunsWorkflowRunError? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            timeout != null -> Type.TIMEOUT_ERROR
            program != null -> Type.PROGRAM_ERROR
            unknown != null -> Type.UNKNOWN_ERROR
            threadLimit != null -> Type.THREAD_LIMIT_ERROR
            maxWorkflowRuns != null -> Type.MAX_WORKFLOW_RUNS_ERROR
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun message(): String =
        when {
            timeout != null -> timeout.message()
            program != null -> program.message()
            unknown != null -> unknown.message()
            threadLimit != null -> threadLimit.message()
            maxWorkflowRuns != null -> maxWorkflowRuns.message()
            else -> _json.getProperty<String>("message").getRequired("message")
        }

    /** The run reached its time limit. */
    fun timeout(): Optional<BetaManagedAgentsTimeoutWorkflowRunError> = Optional.ofNullable(timeout)

    /** The plan, a program that the agent wrote, failed, or the server refused it. */
    fun program(): Optional<BetaManagedAgentsProgramWorkflowRunError> = Optional.ofNullable(program)

    /** A failure that has no type of its own. */
    fun unknown(): Optional<BetaManagedAgentsUnknownWorkflowRunError> = Optional.ofNullable(unknown)

    /** The run exceeded the limit on the number of threads that a run can create. */
    fun threadLimit(): Optional<BetaManagedAgentsThreadLimitWorkflowRunError> =
        Optional.ofNullable(threadLimit)

    /**
     * No run was created, because the session was at its limit of open workflow runs, which are
     * runs that have not ended. Only `workflow_run.error` carries this type.
     */
    fun maxWorkflowRuns(): Optional<BetaManagedAgentsMaxWorkflowRunsWorkflowRunError> =
        Optional.ofNullable(maxWorkflowRuns)

    fun isTimeout(): Boolean = timeout != null

    fun isProgram(): Boolean = program != null

    fun isUnknown(): Boolean = unknown != null

    fun isThreadLimit(): Boolean = threadLimit != null

    fun isMaxWorkflowRuns(): Boolean = maxWorkflowRuns != null

    /** The run reached its time limit. */
    fun asTimeout(): BetaManagedAgentsTimeoutWorkflowRunError = timeout.getOrThrow("timeout")

    /** The plan, a program that the agent wrote, failed, or the server refused it. */
    fun asProgram(): BetaManagedAgentsProgramWorkflowRunError = program.getOrThrow("program")

    /** A failure that has no type of its own. */
    fun asUnknown(): BetaManagedAgentsUnknownWorkflowRunError = unknown.getOrThrow("unknown")

    /** The run exceeded the limit on the number of threads that a run can create. */
    fun asThreadLimit(): BetaManagedAgentsThreadLimitWorkflowRunError =
        threadLimit.getOrThrow("threadLimit")

    /**
     * No run was created, because the session was at its limit of open workflow runs, which are
     * runs that have not ended. Only `workflow_run.error` carries this type.
     */
    fun asMaxWorkflowRuns(): BetaManagedAgentsMaxWorkflowRunsWorkflowRunError =
        maxWorkflowRuns.getOrThrow("maxWorkflowRuns")

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
     * Optional<String> result = betaManagedAgentsWorkflowRunError.accept(new BetaManagedAgentsWorkflowRunError.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitTimeout(BetaManagedAgentsTimeoutWorkflowRunError timeout) {
     *         return Optional.of(timeout.toString());
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
            timeout != null -> visitor.visitTimeout(timeout)
            program != null -> visitor.visitProgram(program)
            unknown != null -> visitor.visitUnknown(unknown)
            threadLimit != null -> visitor.visitThreadLimit(threadLimit)
            maxWorkflowRuns != null -> visitor.visitMaxWorkflowRuns(maxWorkflowRuns)
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
    fun validate(): BetaManagedAgentsWorkflowRunError = apply {
        if (validated) {
            return@apply
        }

        when {
            timeout != null -> timeout.validate()
            program != null -> program.validate()
            unknown != null -> unknown.validate()
            threadLimit != null -> threadLimit.validate()
            maxWorkflowRuns != null -> maxWorkflowRuns.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsWorkflowRunError: $_json"
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
            timeout != null -> timeout.validity()
            program != null -> program.validity()
            unknown != null -> unknown.validity()
            threadLimit != null -> threadLimit.validity()
            maxWorkflowRuns != null -> maxWorkflowRuns.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWorkflowRunError &&
            timeout == other.timeout &&
            program == other.program &&
            unknown == other.unknown &&
            threadLimit == other.threadLimit &&
            maxWorkflowRuns == other.maxWorkflowRuns
    }

    override fun hashCode(): Int =
        Objects.hash(timeout, program, unknown, threadLimit, maxWorkflowRuns)

    override fun toString(): String =
        when {
            timeout != null -> "BetaManagedAgentsWorkflowRunError{timeout=$timeout}"
            program != null -> "BetaManagedAgentsWorkflowRunError{program=$program}"
            unknown != null -> "BetaManagedAgentsWorkflowRunError{unknown=$unknown}"
            threadLimit != null -> "BetaManagedAgentsWorkflowRunError{threadLimit=$threadLimit}"
            maxWorkflowRuns != null ->
                "BetaManagedAgentsWorkflowRunError{maxWorkflowRuns=$maxWorkflowRuns}"
            _json != null -> "BetaManagedAgentsWorkflowRunError{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsWorkflowRunError")
        }

    companion object {

        /** The run reached its time limit. */
        @JvmStatic
        fun ofTimeout(timeout: BetaManagedAgentsTimeoutWorkflowRunError) =
            BetaManagedAgentsWorkflowRunError(timeout = timeout)

        /**
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunError] whose [ofTimeout]
         * variant is built from the given required [message].
         */
        @JvmStatic
        fun ofTimeout(message: String) =
            ofTimeout(BetaManagedAgentsTimeoutWorkflowRunError.of(message))

        /** The plan, a program that the agent wrote, failed, or the server refused it. */
        @JvmStatic
        fun ofProgram(program: BetaManagedAgentsProgramWorkflowRunError) =
            BetaManagedAgentsWorkflowRunError(program = program)

        /**
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunError] whose [ofProgram]
         * variant is built from the given required [message].
         */
        @JvmStatic
        fun ofProgram(message: String) =
            ofProgram(BetaManagedAgentsProgramWorkflowRunError.of(message))

        /** A failure that has no type of its own. */
        @JvmStatic
        fun ofUnknown(unknown: BetaManagedAgentsUnknownWorkflowRunError) =
            BetaManagedAgentsWorkflowRunError(unknown = unknown)

        /**
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunError] whose [ofUnknown]
         * variant is built from the given required [message].
         */
        @JvmStatic
        fun ofUnknown(message: String) =
            ofUnknown(BetaManagedAgentsUnknownWorkflowRunError.of(message))

        /** The run exceeded the limit on the number of threads that a run can create. */
        @JvmStatic
        fun ofThreadLimit(threadLimit: BetaManagedAgentsThreadLimitWorkflowRunError) =
            BetaManagedAgentsWorkflowRunError(threadLimit = threadLimit)

        /**
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunError] whose
         * [ofThreadLimit] variant is built from the given required [message].
         */
        @JvmStatic
        fun ofThreadLimit(message: String) =
            ofThreadLimit(BetaManagedAgentsThreadLimitWorkflowRunError.of(message))

        /**
         * No run was created, because the session was at its limit of open workflow runs, which are
         * runs that have not ended. Only `workflow_run.error` carries this type.
         */
        @JvmStatic
        fun ofMaxWorkflowRuns(maxWorkflowRuns: BetaManagedAgentsMaxWorkflowRunsWorkflowRunError) =
            BetaManagedAgentsWorkflowRunError(maxWorkflowRuns = maxWorkflowRuns)

        /**
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunError] whose
         * [ofMaxWorkflowRuns] variant is built from the given required [message].
         */
        @JvmStatic
        fun ofMaxWorkflowRuns(message: String) =
            ofMaxWorkflowRuns(BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.of(message))
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsWorkflowRunError] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** The run reached its time limit. */
        fun visitTimeout(timeout: BetaManagedAgentsTimeoutWorkflowRunError): T

        /** The plan, a program that the agent wrote, failed, or the server refused it. */
        fun visitProgram(program: BetaManagedAgentsProgramWorkflowRunError): T

        /** A failure that has no type of its own. */
        fun visitUnknown(unknown: BetaManagedAgentsUnknownWorkflowRunError): T

        /** The run exceeded the limit on the number of threads that a run can create. */
        fun visitThreadLimit(threadLimit: BetaManagedAgentsThreadLimitWorkflowRunError): T

        /**
         * No run was created, because the session was at its limit of open workflow runs, which are
         * runs that have not ended. Only `workflow_run.error` carries this type.
         */
        fun visitMaxWorkflowRuns(
            maxWorkflowRuns: BetaManagedAgentsMaxWorkflowRunsWorkflowRunError
        ): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsWorkflowRunError] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsWorkflowRunError] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsWorkflowRunError: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsWorkflowRunError>(
            BetaManagedAgentsWorkflowRunError::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsWorkflowRunError {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "timeout_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsTimeoutWorkflowRunError>(),
                        )
                        ?.let { BetaManagedAgentsWorkflowRunError(timeout = it, _json = json) }
                        ?: BetaManagedAgentsWorkflowRunError(_json = json)
                }
                "program_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsProgramWorkflowRunError>(),
                        )
                        ?.let { BetaManagedAgentsWorkflowRunError(program = it, _json = json) }
                        ?: BetaManagedAgentsWorkflowRunError(_json = json)
                }
                "unknown_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUnknownWorkflowRunError>(),
                        )
                        ?.let { BetaManagedAgentsWorkflowRunError(unknown = it, _json = json) }
                        ?: BetaManagedAgentsWorkflowRunError(_json = json)
                }
                "thread_limit_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsThreadLimitWorkflowRunError>(),
                        )
                        ?.let { BetaManagedAgentsWorkflowRunError(threadLimit = it, _json = json) }
                        ?: BetaManagedAgentsWorkflowRunError(_json = json)
                }
                "max_workflow_runs_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMaxWorkflowRunsWorkflowRunError>(),
                        )
                        ?.let {
                            BetaManagedAgentsWorkflowRunError(maxWorkflowRuns = it, _json = json)
                        } ?: BetaManagedAgentsWorkflowRunError(_json = json)
                }
            }

            return BetaManagedAgentsWorkflowRunError(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsWorkflowRunError>(
            BetaManagedAgentsWorkflowRunError::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsWorkflowRunError,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.timeout != null -> generator.writeObject(value.timeout)
                value.program != null -> generator.writeObject(value.program)
                value.unknown != null -> generator.writeObject(value.unknown)
                value.threadLimit != null -> generator.writeObject(value.threadLimit)
                value.maxWorkflowRuns != null -> generator.writeObject(value.maxWorkflowRuns)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsWorkflowRunError")
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

            @JvmField val TIMEOUT_ERROR = Type(JsonField.of("timeout_error"))

            @JvmField val PROGRAM_ERROR = Type(JsonField.of("program_error"))

            @JvmField val UNKNOWN_ERROR = Type(JsonField.of("unknown_error"))

            @JvmField val THREAD_LIMIT_ERROR = Type(JsonField.of("thread_limit_error"))

            @JvmField val MAX_WORKFLOW_RUNS_ERROR = Type(JsonField.of("max_workflow_runs_error"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "timeout_error" -> TIMEOUT_ERROR
                    "program_error" -> PROGRAM_ERROR
                    "unknown_error" -> UNKNOWN_ERROR
                    "thread_limit_error" -> THREAD_LIMIT_ERROR
                    "max_workflow_runs_error" -> MAX_WORKFLOW_RUNS_ERROR
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            TIMEOUT_ERROR,
            PROGRAM_ERROR,
            UNKNOWN_ERROR,
            THREAD_LIMIT_ERROR,
            MAX_WORKFLOW_RUNS_ERROR,
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
            TIMEOUT_ERROR,
            PROGRAM_ERROR,
            UNKNOWN_ERROR,
            THREAD_LIMIT_ERROR,
            MAX_WORKFLOW_RUNS_ERROR,
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
                TIMEOUT_ERROR -> Value.TIMEOUT_ERROR
                PROGRAM_ERROR -> Value.PROGRAM_ERROR
                UNKNOWN_ERROR -> Value.UNKNOWN_ERROR
                THREAD_LIMIT_ERROR -> Value.THREAD_LIMIT_ERROR
                MAX_WORKFLOW_RUNS_ERROR -> Value.MAX_WORKFLOW_RUNS_ERROR
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
                TIMEOUT_ERROR -> Known.TIMEOUT_ERROR
                PROGRAM_ERROR -> Known.PROGRAM_ERROR
                UNKNOWN_ERROR -> Known.UNKNOWN_ERROR
                THREAD_LIMIT_ERROR -> Known.THREAD_LIMIT_ERROR
                MAX_WORKFLOW_RUNS_ERROR -> Known.MAX_WORKFLOW_RUNS_ERROR
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
