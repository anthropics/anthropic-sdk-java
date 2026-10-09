package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
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

/** How a workflow run ended. */
@JsonDeserialize(using = BetaManagedAgentsWorkflowRunResult.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsWorkflowRunResult.Serializer::class)
class BetaManagedAgentsWorkflowRunResult
private constructor(
    private val completed: BetaManagedAgentsWorkflowRunResultCompleted? = null,
    private val error: BetaManagedAgentsWorkflowRunResultError? = null,
    private val stopped: BetaManagedAgentsWorkflowRunResultStopped? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            completed != null -> Type.COMPLETED
            error != null -> Type.ERROR
            stopped != null -> Type.STOPPED
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /**
     * The run's plan, a program that the agent wrote, finished. This does not say whether the work
     * succeeded.
     */
    fun completed(): Optional<BetaManagedAgentsWorkflowRunResultCompleted> =
        Optional.ofNullable(completed)

    /** The run failed or reached its time limit. */
    fun error(): Optional<BetaManagedAgentsWorkflowRunResultError> = Optional.ofNullable(error)

    /** The agent stopped the run. */
    fun stopped(): Optional<BetaManagedAgentsWorkflowRunResultStopped> =
        Optional.ofNullable(stopped)

    fun isCompleted(): Boolean = completed != null

    fun isError(): Boolean = error != null

    fun isStopped(): Boolean = stopped != null

    /**
     * The run's plan, a program that the agent wrote, finished. This does not say whether the work
     * succeeded.
     */
    fun asCompleted(): BetaManagedAgentsWorkflowRunResultCompleted =
        completed.getOrThrow("completed")

    /** The run failed or reached its time limit. */
    fun asError(): BetaManagedAgentsWorkflowRunResultError = error.getOrThrow("error")

    /** The agent stopped the run. */
    fun asStopped(): BetaManagedAgentsWorkflowRunResultStopped = stopped.getOrThrow("stopped")

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
     * Optional<String> result = betaManagedAgentsWorkflowRunResult.accept(new BetaManagedAgentsWorkflowRunResult.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCompleted(BetaManagedAgentsWorkflowRunResultCompleted completed) {
     *         return Optional.of(completed.toString());
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
            completed != null -> visitor.visitCompleted(completed)
            error != null -> visitor.visitError(error)
            stopped != null -> visitor.visitStopped(stopped)
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
    fun validate(): BetaManagedAgentsWorkflowRunResult = apply {
        if (validated) {
            return@apply
        }

        when {
            completed != null -> completed.validate()
            error != null -> error.validate()
            stopped != null -> stopped.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsWorkflowRunResult: $_json"
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
            completed != null -> completed.validity()
            error != null -> error.validity()
            stopped != null -> stopped.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWorkflowRunResult &&
            completed == other.completed &&
            error == other.error &&
            stopped == other.stopped
    }

    override fun hashCode(): Int = Objects.hash(completed, error, stopped)

    override fun toString(): String =
        when {
            completed != null -> "BetaManagedAgentsWorkflowRunResult{completed=$completed}"
            error != null -> "BetaManagedAgentsWorkflowRunResult{error=$error}"
            stopped != null -> "BetaManagedAgentsWorkflowRunResult{stopped=$stopped}"
            _json != null -> "BetaManagedAgentsWorkflowRunResult{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsWorkflowRunResult")
        }

    companion object {

        /**
         * The run's plan, a program that the agent wrote, finished. This does not say whether the
         * work succeeded.
         */
        @JvmStatic
        fun ofCompleted(completed: BetaManagedAgentsWorkflowRunResultCompleted) =
            BetaManagedAgentsWorkflowRunResult(completed = completed)

        /** The run failed or reached its time limit. */
        @JvmStatic
        fun ofError(error: BetaManagedAgentsWorkflowRunResultError) =
            BetaManagedAgentsWorkflowRunResult(error = error)

        /**
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunResult] whose [ofError]
         * variant is built from the given required [error].
         */
        @JvmStatic
        fun ofError(error: BetaManagedAgentsWorkflowRunError) =
            ofError(BetaManagedAgentsWorkflowRunResultError.of(error))

        /**
         * Alias for calling [ofError] with `BetaManagedAgentsWorkflowRunError.ofTimeout(timeout)`.
         */
        @JvmStatic
        fun ofError(timeout: BetaManagedAgentsTimeoutWorkflowRunError) =
            ofError(BetaManagedAgentsWorkflowRunError.ofTimeout(timeout))

        /**
         * Alias for calling [ofError] with `BetaManagedAgentsWorkflowRunError.ofProgram(program)`.
         */
        @JvmStatic
        fun ofError(program: BetaManagedAgentsProgramWorkflowRunError) =
            ofError(BetaManagedAgentsWorkflowRunError.ofProgram(program))

        /**
         * Alias for calling [ofError] with `BetaManagedAgentsWorkflowRunError.ofUnknown(unknown)`.
         */
        @JvmStatic
        fun ofError(unknown: BetaManagedAgentsUnknownWorkflowRunError) =
            ofError(BetaManagedAgentsWorkflowRunError.ofUnknown(unknown))

        /**
         * Alias for calling [ofError] with
         * `BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit)`.
         */
        @JvmStatic
        fun ofError(threadLimit: BetaManagedAgentsThreadLimitWorkflowRunError) =
            ofError(BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit))

        /**
         * Alias for calling [ofError] with
         * `BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns)`.
         */
        @JvmStatic
        fun ofError(maxWorkflowRuns: BetaManagedAgentsMaxWorkflowRunsWorkflowRunError) =
            ofError(BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns))

        /** The agent stopped the run. */
        @JvmStatic
        fun ofStopped(stopped: BetaManagedAgentsWorkflowRunResultStopped) =
            BetaManagedAgentsWorkflowRunResult(stopped = stopped)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsWorkflowRunResult] to
     * a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * The run's plan, a program that the agent wrote, finished. This does not say whether the
         * work succeeded.
         */
        fun visitCompleted(completed: BetaManagedAgentsWorkflowRunResultCompleted): T

        /** The run failed or reached its time limit. */
        fun visitError(error: BetaManagedAgentsWorkflowRunResultError): T

        /** The agent stopped the run. */
        fun visitStopped(stopped: BetaManagedAgentsWorkflowRunResultStopped): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsWorkflowRunResult] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsWorkflowRunResult] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsWorkflowRunResult: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsWorkflowRunResult>(
            BetaManagedAgentsWorkflowRunResult::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsWorkflowRunResult {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "completed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWorkflowRunResultCompleted>(),
                        )
                        ?.let { BetaManagedAgentsWorkflowRunResult(completed = it, _json = json) }
                        ?: BetaManagedAgentsWorkflowRunResult(_json = json)
                }
                "error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWorkflowRunResultError>(),
                        )
                        ?.let { BetaManagedAgentsWorkflowRunResult(error = it, _json = json) }
                        ?: BetaManagedAgentsWorkflowRunResult(_json = json)
                }
                "stopped" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWorkflowRunResultStopped>(),
                        )
                        ?.let { BetaManagedAgentsWorkflowRunResult(stopped = it, _json = json) }
                        ?: BetaManagedAgentsWorkflowRunResult(_json = json)
                }
            }

            return BetaManagedAgentsWorkflowRunResult(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsWorkflowRunResult>(
            BetaManagedAgentsWorkflowRunResult::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsWorkflowRunResult,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.completed != null -> generator.writeObject(value.completed)
                value.error != null -> generator.writeObject(value.error)
                value.stopped != null -> generator.writeObject(value.stopped)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsWorkflowRunResult")
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

            @JvmField val COMPLETED = Type(JsonField.of("completed"))

            @JvmField val ERROR = Type(JsonField.of("error"))

            @JvmField val STOPPED = Type(JsonField.of("stopped"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "completed" -> COMPLETED
                    "error" -> ERROR
                    "stopped" -> STOPPED
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            COMPLETED,
            ERROR,
            STOPPED,
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
            COMPLETED,
            ERROR,
            STOPPED,
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
                COMPLETED -> Value.COMPLETED
                ERROR -> Value.ERROR
                STOPPED -> Value.STOPPED
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
                COMPLETED -> Known.COMPLETED
                ERROR -> Known.ERROR
                STOPPED -> Known.STOPPED
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
