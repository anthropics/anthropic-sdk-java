// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator

class BetaWebSearchToolResultErrorCode
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

        @JvmField val MAX_USES_EXCEEDED = of("max_uses_exceeded")

        @JvmField val TOO_MANY_REQUESTS = of("too_many_requests")

        @JvmField val QUERY_TOO_LONG = of("query_too_long")

        @JvmStatic fun of(value: String) = BetaWebSearchToolResultErrorCode(JsonField.of(value))
    }

    /** An enum containing [BetaWebSearchToolResultErrorCode]'s known values. */
    enum class Known {
        INVALID_TOOL_INPUT,
        UNAVAILABLE,
        MAX_USES_EXCEEDED,
        TOO_MANY_REQUESTS,
        QUERY_TOO_LONG,
    }

    /**
     * An enum containing [BetaWebSearchToolResultErrorCode]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [BetaWebSearchToolResultErrorCode] can contain an unknown value in a couple of
     * cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        INVALID_TOOL_INPUT,
        UNAVAILABLE,
        MAX_USES_EXCEEDED,
        TOO_MANY_REQUESTS,
        QUERY_TOO_LONG,
        /**
         * An enum member indicating that [BetaWebSearchToolResultErrorCode] was instantiated with
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
            INVALID_TOOL_INPUT -> Value.INVALID_TOOL_INPUT
            UNAVAILABLE -> Value.UNAVAILABLE
            MAX_USES_EXCEEDED -> Value.MAX_USES_EXCEEDED
            TOO_MANY_REQUESTS -> Value.TOO_MANY_REQUESTS
            QUERY_TOO_LONG -> Value.QUERY_TOO_LONG
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
            MAX_USES_EXCEEDED -> Known.MAX_USES_EXCEEDED
            TOO_MANY_REQUESTS -> Known.TOO_MANY_REQUESTS
            QUERY_TOO_LONG -> Known.QUERY_TOO_LONG
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaWebSearchToolResultErrorCode: $value"
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

    fun validate(): BetaWebSearchToolResultErrorCode = apply {
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

        return /* spotless:off */ other is BetaWebSearchToolResultErrorCode && value == other.value /* spotless:on */
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
