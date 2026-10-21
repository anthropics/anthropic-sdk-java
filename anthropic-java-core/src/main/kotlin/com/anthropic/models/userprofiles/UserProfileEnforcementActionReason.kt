package com.anthropic.models.userprofiles

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * Why Anthropic placed this enforcement action: `unsupported_region`, `fraud_or_abuse`,
 * `high_harm`, `usage_policy_violation`, `account_compromise` or `unspecified`. More values may be
 * added; read a value you do not recognize as `unspecified`.
 */
class UserProfileEnforcementActionReason private constructor(private val value: JsonField<String>) :
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

        @JvmField
        val UNSUPPORTED_REGION =
            UserProfileEnforcementActionReason(JsonField.of("unsupported_region"))

        @JvmField
        val FRAUD_OR_ABUSE = UserProfileEnforcementActionReason(JsonField.of("fraud_or_abuse"))

        @JvmField val HIGH_HARM = UserProfileEnforcementActionReason(JsonField.of("high_harm"))

        @JvmField
        val USAGE_POLICY_VIOLATION =
            UserProfileEnforcementActionReason(JsonField.of("usage_policy_violation"))

        @JvmField
        val ACCOUNT_COMPROMISE =
            UserProfileEnforcementActionReason(JsonField.of("account_compromise"))

        @JvmField val UNSPECIFIED = UserProfileEnforcementActionReason(JsonField.of("unspecified"))

        @JvmStatic
        fun of(value: String): UserProfileEnforcementActionReason =
            // Intern known values so `==` works
            when (value) {
                "unsupported_region" -> UNSUPPORTED_REGION
                "fraud_or_abuse" -> FRAUD_OR_ABUSE
                "high_harm" -> HIGH_HARM
                "usage_policy_violation" -> USAGE_POLICY_VIOLATION
                "account_compromise" -> ACCOUNT_COMPROMISE
                "unspecified" -> UNSPECIFIED
                else -> UserProfileEnforcementActionReason(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): UserProfileEnforcementActionReason =
            value.asString().getOrNull()?.let { of(it) }
                ?: UserProfileEnforcementActionReason(value)
    }

    /** An enum containing [UserProfileEnforcementActionReason]'s known values. */
    enum class Known {
        UNSUPPORTED_REGION,
        FRAUD_OR_ABUSE,
        HIGH_HARM,
        USAGE_POLICY_VIOLATION,
        ACCOUNT_COMPROMISE,
        UNSPECIFIED,
    }

    /**
     * An enum containing [UserProfileEnforcementActionReason]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [UserProfileEnforcementActionReason] can contain an unknown value in a couple
     * of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        UNSUPPORTED_REGION,
        FRAUD_OR_ABUSE,
        HIGH_HARM,
        USAGE_POLICY_VIOLATION,
        ACCOUNT_COMPROMISE,
        UNSPECIFIED,
        /**
         * An enum member indicating that [UserProfileEnforcementActionReason] was instantiated with
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
            UNSUPPORTED_REGION -> Value.UNSUPPORTED_REGION
            FRAUD_OR_ABUSE -> Value.FRAUD_OR_ABUSE
            HIGH_HARM -> Value.HIGH_HARM
            USAGE_POLICY_VIOLATION -> Value.USAGE_POLICY_VIOLATION
            ACCOUNT_COMPROMISE -> Value.ACCOUNT_COMPROMISE
            UNSPECIFIED -> Value.UNSPECIFIED
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
            UNSUPPORTED_REGION -> Known.UNSUPPORTED_REGION
            FRAUD_OR_ABUSE -> Known.FRAUD_OR_ABUSE
            HIGH_HARM -> Known.HIGH_HARM
            USAGE_POLICY_VIOLATION -> Known.USAGE_POLICY_VIOLATION
            ACCOUNT_COMPROMISE -> Known.ACCOUNT_COMPROMISE
            UNSPECIFIED -> Known.UNSPECIFIED
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown UserProfileEnforcementActionReason: $value"
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
    fun validate(): UserProfileEnforcementActionReason = apply {
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

        return other is UserProfileEnforcementActionReason && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
