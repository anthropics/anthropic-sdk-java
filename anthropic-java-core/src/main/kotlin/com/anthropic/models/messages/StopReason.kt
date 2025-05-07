// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator

class StopReason @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val END_TURN = of("end_turn")

        @JvmField val MAX_TOKENS = of("max_tokens")

        @JvmField val STOP_SEQUENCE = of("stop_sequence")

        @JvmField val TOOL_USE = of("tool_use")

        @JvmField val PAUSE_TURN = of("pause_turn")

        @JvmField val REFUSAL = of("refusal")

        @JvmStatic fun of(value: String) = StopReason(JsonField.of(value))
    }

    /** An enum containing [StopReason]'s known values. */
    enum class Known {
        END_TURN,
        MAX_TOKENS,
        STOP_SEQUENCE,
        TOOL_USE,
        PAUSE_TURN,
        REFUSAL,
    }

    /**
     * An enum containing [StopReason]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [StopReason] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        END_TURN,
        MAX_TOKENS,
        STOP_SEQUENCE,
        TOOL_USE,
        PAUSE_TURN,
        REFUSAL,
        /** An enum member indicating that [StopReason] was instantiated with an unknown value. */
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
            END_TURN -> Value.END_TURN
            MAX_TOKENS -> Value.MAX_TOKENS
            STOP_SEQUENCE -> Value.STOP_SEQUENCE
            TOOL_USE -> Value.TOOL_USE
            PAUSE_TURN -> Value.PAUSE_TURN
            REFUSAL -> Value.REFUSAL
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
            END_TURN -> Known.END_TURN
            MAX_TOKENS -> Known.MAX_TOKENS
            STOP_SEQUENCE -> Known.STOP_SEQUENCE
            TOOL_USE -> Known.TOOL_USE
            PAUSE_TURN -> Known.PAUSE_TURN
            REFUSAL -> Known.REFUSAL
            else -> throw AnthropicInvalidDataException("Unknown StopReason: $value")
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

    fun validate(): StopReason = apply {
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

        return /* spotless:off */ other is StopReason && value == other.value /* spotless:on */
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
