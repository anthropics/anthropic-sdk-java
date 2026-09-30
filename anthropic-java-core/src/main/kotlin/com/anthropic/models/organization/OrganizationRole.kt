package com.anthropic.models.organization

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

class OrganizationRole private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val ADMIN = OrganizationRole(JsonField.of("admin"))

        @JvmField val BILLING = OrganizationRole(JsonField.of("billing"))

        @JvmField val CLAUDE_CODE_USER = OrganizationRole(JsonField.of("claude_code_user"))

        @JvmField val DEVELOPER = OrganizationRole(JsonField.of("developer"))

        @JvmField val MANAGED = OrganizationRole(JsonField.of("managed"))

        @JvmField val MEMBERSHIP_ADMIN = OrganizationRole(JsonField.of("membership_admin"))

        @JvmField val OWNER = OrganizationRole(JsonField.of("owner"))

        @JvmField val PRIMARY_OWNER = OrganizationRole(JsonField.of("primary_owner"))

        @JvmField val USER = OrganizationRole(JsonField.of("user"))

        @JvmStatic
        fun of(value: String): OrganizationRole =
            // Intern known values so `==` works
            when (value) {
                "admin" -> ADMIN
                "billing" -> BILLING
                "claude_code_user" -> CLAUDE_CODE_USER
                "developer" -> DEVELOPER
                "managed" -> MANAGED
                "membership_admin" -> MEMBERSHIP_ADMIN
                "owner" -> OWNER
                "primary_owner" -> PRIMARY_OWNER
                "user" -> USER
                else -> OrganizationRole(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): OrganizationRole =
            value.asString().getOrNull()?.let { of(it) } ?: OrganizationRole(value)
    }

    /** An enum containing [OrganizationRole]'s known values. */
    enum class Known {
        ADMIN,
        BILLING,
        CLAUDE_CODE_USER,
        DEVELOPER,
        MANAGED,
        MEMBERSHIP_ADMIN,
        OWNER,
        PRIMARY_OWNER,
        USER,
    }

    /**
     * An enum containing [OrganizationRole]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [OrganizationRole] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        ADMIN,
        BILLING,
        CLAUDE_CODE_USER,
        DEVELOPER,
        MANAGED,
        MEMBERSHIP_ADMIN,
        OWNER,
        PRIMARY_OWNER,
        USER,
        /**
         * An enum member indicating that [OrganizationRole] was instantiated with an unknown value.
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
            ADMIN -> Value.ADMIN
            BILLING -> Value.BILLING
            CLAUDE_CODE_USER -> Value.CLAUDE_CODE_USER
            DEVELOPER -> Value.DEVELOPER
            MANAGED -> Value.MANAGED
            MEMBERSHIP_ADMIN -> Value.MEMBERSHIP_ADMIN
            OWNER -> Value.OWNER
            PRIMARY_OWNER -> Value.PRIMARY_OWNER
            USER -> Value.USER
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
            ADMIN -> Known.ADMIN
            BILLING -> Known.BILLING
            CLAUDE_CODE_USER -> Known.CLAUDE_CODE_USER
            DEVELOPER -> Known.DEVELOPER
            MANAGED -> Known.MANAGED
            MEMBERSHIP_ADMIN -> Known.MEMBERSHIP_ADMIN
            OWNER -> Known.OWNER
            PRIMARY_OWNER -> Known.PRIMARY_OWNER
            USER -> Known.USER
            else -> throw AnthropicInvalidDataException("Unknown OrganizationRole: $value")
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
    fun validate(): OrganizationRole = apply {
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

        return other is OrganizationRole && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
