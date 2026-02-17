// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator

class BashCodeExecutionToolResultErrorCode
@JsonCreator
private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val INVALID_TOOL_INPUT = of("invalid_tool_input")

        @JvmField val UNAVAILABLE = of("unavailable")

        @JvmField val TOO_MANY_REQUESTS = of("too_many_requests")

        @JvmField val EXECUTION_TIME_EXCEEDED = of("execution_time_exceeded")

        @JvmField val OUTPUT_FILE_TOO_LARGE = of("output_file_too_large")

        @JvmStatic fun of(value: String) = BashCodeExecutionToolResultErrorCode(JsonField.of(value))
    }

    /** An enum containing [BashCodeExecutionToolResultErrorCode]'s known values. */
    enum class Known {
        INVALID_TOOL_INPUT,
        UNAVAILABLE,
        TOO_MANY_REQUESTS,
        EXECUTION_TIME_EXCEEDED,
        OUTPUT_FILE_TOO_LARGE,
    }

    /**
     * An enum containing [BashCodeExecutionToolResultErrorCode]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [BashCodeExecutionToolResultErrorCode] can contain an unknown value in a
     * couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        INVALID_TOOL_INPUT,
        UNAVAILABLE,
        TOO_MANY_REQUESTS,
        EXECUTION_TIME_EXCEEDED,
        OUTPUT_FILE_TOO_LARGE,
        /**
         * An enum member indicating that [BashCodeExecutionToolResultErrorCode] was instantiated
         * with an unknown value.
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
            INVALID_TOOL_INPUT -> Value.INVALID_TOOL_INPUT
            UNAVAILABLE -> Value.UNAVAILABLE
            TOO_MANY_REQUESTS -> Value.TOO_MANY_REQUESTS
            EXECUTION_TIME_EXCEEDED -> Value.EXECUTION_TIME_EXCEEDED
            OUTPUT_FILE_TOO_LARGE -> Value.OUTPUT_FILE_TOO_LARGE
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
            INVALID_TOOL_INPUT -> Known.INVALID_TOOL_INPUT
            UNAVAILABLE -> Known.UNAVAILABLE
            TOO_MANY_REQUESTS -> Known.TOO_MANY_REQUESTS
            EXECUTION_TIME_EXCEEDED -> Known.EXECUTION_TIME_EXCEEDED
            OUTPUT_FILE_TOO_LARGE -> Known.OUTPUT_FILE_TOO_LARGE
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BashCodeExecutionToolResultErrorCode: $value"
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

    fun validate(): BashCodeExecutionToolResultErrorCode = apply {
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

        return other is BashCodeExecutionToolResultErrorCode && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
