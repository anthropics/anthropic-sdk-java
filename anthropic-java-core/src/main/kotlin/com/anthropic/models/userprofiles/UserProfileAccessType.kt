package com.anthropic.models.userprofiles

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * How the platform uses the API on behalf of the entity this profile represents. `application`: the
 * platform sells a product that uses the API behind the scenes, and the profile represents an
 * individual end-user of that product. `passthrough`: the platform resells raw inference, and the
 * profile identifies the resold-to company.
 */
class UserProfileAccessType private constructor(private val value: JsonField<String>) : Enum {

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
         * The user profile represents an individual end-user of a product that the platform builds
         * on the API. New profiles get this value by default.
         */
        @JvmField val APPLICATION = UserProfileAccessType(JsonField.of("application"))

        /** The user profile represents a company that the platform resells Claude access to. */
        @JvmField val PASSTHROUGH = UserProfileAccessType(JsonField.of("passthrough"))

        @JvmStatic
        fun of(value: String): UserProfileAccessType =
            // Intern known values so `==` works
            when (value) {
                "application" -> APPLICATION
                "passthrough" -> PASSTHROUGH
                else -> UserProfileAccessType(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): UserProfileAccessType =
            value.asString().getOrNull()?.let { of(it) } ?: UserProfileAccessType(value)
    }

    /** An enum containing [UserProfileAccessType]'s known values. */
    enum class Known {
        /**
         * The user profile represents an individual end-user of a product that the platform builds
         * on the API. New profiles get this value by default.
         */
        APPLICATION,
        /** The user profile represents a company that the platform resells Claude access to. */
        PASSTHROUGH,
    }

    /**
     * An enum containing [UserProfileAccessType]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [UserProfileAccessType] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /**
         * The user profile represents an individual end-user of a product that the platform builds
         * on the API. New profiles get this value by default.
         */
        APPLICATION,
        /** The user profile represents a company that the platform resells Claude access to. */
        PASSTHROUGH,
        /**
         * An enum member indicating that [UserProfileAccessType] was instantiated with an unknown
         * value.
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
            APPLICATION -> Value.APPLICATION
            PASSTHROUGH -> Value.PASSTHROUGH
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
            APPLICATION -> Known.APPLICATION
            PASSTHROUGH -> Known.PASSTHROUGH
            else -> throw AnthropicInvalidDataException("Unknown UserProfileAccessType: $value")
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
    fun validate(): UserProfileAccessType = apply {
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

        return other is UserProfileAccessType && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
