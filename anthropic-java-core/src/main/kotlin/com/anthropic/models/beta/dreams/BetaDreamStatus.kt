package com.anthropic.models.beta.dreams

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * Where a dream is in its lifecycle.
 *
 * `completed`, `failed`, and `canceled` are final: once a dream has one of these statuses, its
 * status doesn't change again.
 *
 * See the [Dreams guide](https://platform.claude.com/docs/en/managed-agents/dreams#lifecycle) for
 * what each status means.
 */
class BetaDreamStatus @JsonCreator private constructor(private val value: JsonField<String>) :
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

        /**
         * The dream is waiting to start and hasn't read its inputs yet.
         *
         * `outputs` is empty and every `usage` count is zero.
         */
        @JvmField val PENDING = of("pending")

        /**
         * The dream is reading its inputs and writing its result.
         *
         * `usage` updates while the dream has this status.
         */
        @JvmField val RUNNING = of("running")

        /** The dream finished and its output memory store holds the complete result. */
        @JvmField val COMPLETED = of("completed")

        /**
         * The dream stopped with an error, which `error` describes.
         *
         * If `outputs` references a memory store, that memory store keeps what the dream wrote
         * before it stopped.
         */
        @JvmField val FAILED = of("failed")

        /**
         * A cancel request stopped the dream before it reached `completed` or `failed`.
         *
         * If `outputs` references a memory store, that memory store keeps what the dream wrote.
         * `usage` can keep changing after the cancel.
         */
        @JvmField val CANCELED = of("canceled")

        @JvmStatic fun of(value: String) = BetaDreamStatus(JsonField.of(value))

        @JvmSynthetic
        internal fun of(value: JsonField<String>): BetaDreamStatus =
            value.asString().getOrNull()?.let { of(it) } ?: BetaDreamStatus(value)
    }

    /** An enum containing [BetaDreamStatus]'s known values. */
    enum class Known {
        /**
         * The dream is waiting to start and hasn't read its inputs yet.
         *
         * `outputs` is empty and every `usage` count is zero.
         */
        PENDING,
        /**
         * The dream is reading its inputs and writing its result.
         *
         * `usage` updates while the dream has this status.
         */
        RUNNING,
        /** The dream finished and its output memory store holds the complete result. */
        COMPLETED,
        /**
         * The dream stopped with an error, which `error` describes.
         *
         * If `outputs` references a memory store, that memory store keeps what the dream wrote
         * before it stopped.
         */
        FAILED,
        /**
         * A cancel request stopped the dream before it reached `completed` or `failed`.
         *
         * If `outputs` references a memory store, that memory store keeps what the dream wrote.
         * `usage` can keep changing after the cancel.
         */
        CANCELED,
    }

    /**
     * An enum containing [BetaDreamStatus]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [BetaDreamStatus] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /**
         * The dream is waiting to start and hasn't read its inputs yet.
         *
         * `outputs` is empty and every `usage` count is zero.
         */
        PENDING,
        /**
         * The dream is reading its inputs and writing its result.
         *
         * `usage` updates while the dream has this status.
         */
        RUNNING,
        /** The dream finished and its output memory store holds the complete result. */
        COMPLETED,
        /**
         * The dream stopped with an error, which `error` describes.
         *
         * If `outputs` references a memory store, that memory store keeps what the dream wrote
         * before it stopped.
         */
        FAILED,
        /**
         * A cancel request stopped the dream before it reached `completed` or `failed`.
         *
         * If `outputs` references a memory store, that memory store keeps what the dream wrote.
         * `usage` can keep changing after the cancel.
         */
        CANCELED,
        /**
         * An enum member indicating that [BetaDreamStatus] was instantiated with an unknown value.
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
            PENDING -> Value.PENDING
            RUNNING -> Value.RUNNING
            COMPLETED -> Value.COMPLETED
            FAILED -> Value.FAILED
            CANCELED -> Value.CANCELED
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
            PENDING -> Known.PENDING
            RUNNING -> Known.RUNNING
            COMPLETED -> Known.COMPLETED
            FAILED -> Known.FAILED
            CANCELED -> Known.CANCELED
            else -> throw AnthropicInvalidDataException("Unknown BetaDreamStatus: $value")
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
    fun validate(): BetaDreamStatus = apply {
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

        return other is BetaDreamStatus && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
