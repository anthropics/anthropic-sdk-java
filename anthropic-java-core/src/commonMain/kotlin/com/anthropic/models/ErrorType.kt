// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import kotlinx.kmp.util.core.Enum
import kotlinx.kmp.util.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator

class ErrorType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        val INVALID_REQUEST_ERROR = of("invalid_request_error")

        val AUTHENTICATION_ERROR = of("authentication_error")

        val PERMISSION_ERROR = of("permission_error")

        val NOT_FOUND_ERROR = of("not_found_error")

        val RATE_LIMIT_ERROR = of("rate_limit_error")

        val TIMEOUT_ERROR = of("timeout_error")

        val OVERLOADED_ERROR = of("overloaded_error")

        val API_ERROR = of("api_error")

        val BILLING_ERROR = of("billing_error")

        @JvmStatic fun of(value: String) = ErrorType(JsonField.of(value))
    }

    /** An enum containing [ErrorType]'s known values. */
    enum class Known {
        INVALID_REQUEST_ERROR,
        AUTHENTICATION_ERROR,
        PERMISSION_ERROR,
        NOT_FOUND_ERROR,
        RATE_LIMIT_ERROR,
        TIMEOUT_ERROR,
        OVERLOADED_ERROR,
        API_ERROR,
        BILLING_ERROR,
    }

    /**
     * An enum containing [ErrorType]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [ErrorType] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        INVALID_REQUEST_ERROR,
        AUTHENTICATION_ERROR,
        PERMISSION_ERROR,
        NOT_FOUND_ERROR,
        RATE_LIMIT_ERROR,
        TIMEOUT_ERROR,
        OVERLOADED_ERROR,
        API_ERROR,
        BILLING_ERROR,
        /** An enum member indicating that [ErrorType] was instantiated with an unknown value. */
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
            INVALID_REQUEST_ERROR -> Value.INVALID_REQUEST_ERROR
            AUTHENTICATION_ERROR -> Value.AUTHENTICATION_ERROR
            PERMISSION_ERROR -> Value.PERMISSION_ERROR
            NOT_FOUND_ERROR -> Value.NOT_FOUND_ERROR
            RATE_LIMIT_ERROR -> Value.RATE_LIMIT_ERROR
            TIMEOUT_ERROR -> Value.TIMEOUT_ERROR
            OVERLOADED_ERROR -> Value.OVERLOADED_ERROR
            API_ERROR -> Value.API_ERROR
            BILLING_ERROR -> Value.BILLING_ERROR
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
            INVALID_REQUEST_ERROR -> Known.INVALID_REQUEST_ERROR
            AUTHENTICATION_ERROR -> Known.AUTHENTICATION_ERROR
            PERMISSION_ERROR -> Known.PERMISSION_ERROR
            NOT_FOUND_ERROR -> Known.NOT_FOUND_ERROR
            RATE_LIMIT_ERROR -> Known.RATE_LIMIT_ERROR
            TIMEOUT_ERROR -> Known.TIMEOUT_ERROR
            OVERLOADED_ERROR -> Known.OVERLOADED_ERROR
            API_ERROR -> Known.API_ERROR
            BILLING_ERROR -> Known.BILLING_ERROR
            else -> throw AnthropicInvalidDataException("Unknown ErrorType: $value")
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
        _value().asString() ?: throw AnthropicInvalidDataException("Value is not a String")

    private var validated: Boolean = false

    fun validate(): ErrorType = apply {
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

        return other is ErrorType && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
