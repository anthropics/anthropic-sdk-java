package com.anthropic.models.userprofiles

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * A user profile's status in a program. Each program's `status` field says what the values mean
 * there.
 */
class UserProfileProgramStatus private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        /** The profile is in the program. */
        @JvmField val ACTIVE = UserProfileProgramStatus(JsonField.of("active"))

        /** The profile is not in the program yet, and something is still awaited. */
        @JvmField val PENDING = UserProfileProgramStatus(JsonField.of("pending"))

        /**
         * The profile is not in the program and nothing is awaited: Anthropic has declined, or has
         * withdrawn an earlier approval.
         */
        @JvmField val REJECTED = UserProfileProgramStatus(JsonField.of("rejected"))

        @JvmStatic
        fun of(value: String): UserProfileProgramStatus =
            // Intern known values so `==` works
            when (value) {
                "active" -> ACTIVE
                "pending" -> PENDING
                "rejected" -> REJECTED
                else -> UserProfileProgramStatus(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): UserProfileProgramStatus =
            value.asString().getOrNull()?.let { of(it) } ?: UserProfileProgramStatus(value)
    }

    /** An enum containing [UserProfileProgramStatus]'s known values. */
    enum class Known {
        /** The profile is in the program. */
        ACTIVE,
        /** The profile is not in the program yet, and something is still awaited. */
        PENDING,
        /**
         * The profile is not in the program and nothing is awaited: Anthropic has declined, or has
         * withdrawn an earlier approval.
         */
        REJECTED,
    }

    /**
     * An enum containing [UserProfileProgramStatus]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [UserProfileProgramStatus] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /** The profile is in the program. */
        ACTIVE,
        /** The profile is not in the program yet, and something is still awaited. */
        PENDING,
        /**
         * The profile is not in the program and nothing is awaited: Anthropic has declined, or has
         * withdrawn an earlier approval.
         */
        REJECTED,
        /**
         * An enum member indicating that [UserProfileProgramStatus] was instantiated with an
         * unknown value.
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
            ACTIVE -> Value.ACTIVE
            PENDING -> Value.PENDING
            REJECTED -> Value.REJECTED
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
            ACTIVE -> Known.ACTIVE
            PENDING -> Known.PENDING
            REJECTED -> Known.REJECTED
            else -> throw AnthropicInvalidDataException("Unknown UserProfileProgramStatus: $value")
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
    fun validate(): UserProfileProgramStatus = apply {
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

        return other is UserProfileProgramStatus && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
