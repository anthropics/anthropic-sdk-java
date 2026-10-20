package com.anthropic.models.userprofiles

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * The status of the entity's account on the platform, as the platform states it: `active`;
 * `suspended`, when the platform has restricted the account and may restore it; or `blocked`, when
 * the platform has barred it. It records the platform's decision only. The statuses that Anthropic
 * itself reports for the profile, such as its status in a program, are Anthropic's decisions and do
 * not change with it.
 */
class UserProfileExternalUserAccountStatus
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

        /**
         * The platform has neither restricted nor barred the account of the entity that the user
         * profile represents.
         */
        @JvmField val ACTIVE = UserProfileExternalUserAccountStatus(JsonField.of("active"))

        /**
         * The platform has restricted the account of the entity that the user profile represents
         * and may restore it.
         */
        @JvmField val SUSPENDED = UserProfileExternalUserAccountStatus(JsonField.of("suspended"))

        /** The platform has barred the account of the entity that the user profile represents. */
        @JvmField val BLOCKED = UserProfileExternalUserAccountStatus(JsonField.of("blocked"))

        @JvmStatic
        fun of(value: String): UserProfileExternalUserAccountStatus =
            // Intern known values so `==` works
            when (value) {
                "active" -> ACTIVE
                "suspended" -> SUSPENDED
                "blocked" -> BLOCKED
                else -> UserProfileExternalUserAccountStatus(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): UserProfileExternalUserAccountStatus =
            value.asString().getOrNull()?.let { of(it) }
                ?: UserProfileExternalUserAccountStatus(value)
    }

    /** An enum containing [UserProfileExternalUserAccountStatus]'s known values. */
    enum class Known {
        /**
         * The platform has neither restricted nor barred the account of the entity that the user
         * profile represents.
         */
        ACTIVE,
        /**
         * The platform has restricted the account of the entity that the user profile represents
         * and may restore it.
         */
        SUSPENDED,
        /** The platform has barred the account of the entity that the user profile represents. */
        BLOCKED,
    }

    /**
     * An enum containing [UserProfileExternalUserAccountStatus]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [UserProfileExternalUserAccountStatus] can contain an unknown value in a
     * couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /**
         * The platform has neither restricted nor barred the account of the entity that the user
         * profile represents.
         */
        ACTIVE,
        /**
         * The platform has restricted the account of the entity that the user profile represents
         * and may restore it.
         */
        SUSPENDED,
        /** The platform has barred the account of the entity that the user profile represents. */
        BLOCKED,
        /**
         * An enum member indicating that [UserProfileExternalUserAccountStatus] was instantiated
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
            ACTIVE -> Value.ACTIVE
            SUSPENDED -> Value.SUSPENDED
            BLOCKED -> Value.BLOCKED
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
            SUSPENDED -> Known.SUSPENDED
            BLOCKED -> Known.BLOCKED
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown UserProfileExternalUserAccountStatus: $value"
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
    fun validate(): UserProfileExternalUserAccountStatus = apply {
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

        return other is UserProfileExternalUserAccountStatus && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
