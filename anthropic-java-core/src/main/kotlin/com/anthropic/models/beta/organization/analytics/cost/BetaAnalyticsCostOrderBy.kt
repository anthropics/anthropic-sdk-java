package com.anthropic.models.beta.organization.analytics.cost

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

class BetaAnalyticsCostOrderBy private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val AMOUNT = BetaAnalyticsCostOrderBy(JsonField.of("amount"))

        @JvmField val LIST_AMOUNT = BetaAnalyticsCostOrderBy(JsonField.of("list_amount"))

        @JvmField val TIME = BetaAnalyticsCostOrderBy(JsonField.of("time"))

        @JvmStatic
        fun of(value: String): BetaAnalyticsCostOrderBy =
            // Intern known values so `==` works
            when (value) {
                "amount" -> AMOUNT
                "list_amount" -> LIST_AMOUNT
                "time" -> TIME
                else -> BetaAnalyticsCostOrderBy(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaAnalyticsCostOrderBy =
            value.asString().getOrNull()?.let { of(it) } ?: BetaAnalyticsCostOrderBy(value)
    }

    /** An enum containing [BetaAnalyticsCostOrderBy]'s known values. */
    enum class Known {
        AMOUNT,
        LIST_AMOUNT,
        TIME,
    }

    /**
     * An enum containing [BetaAnalyticsCostOrderBy]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [BetaAnalyticsCostOrderBy] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        AMOUNT,
        LIST_AMOUNT,
        TIME,
        /**
         * An enum member indicating that [BetaAnalyticsCostOrderBy] was instantiated with an
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
            AMOUNT -> Value.AMOUNT
            LIST_AMOUNT -> Value.LIST_AMOUNT
            TIME -> Value.TIME
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
            AMOUNT -> Known.AMOUNT
            LIST_AMOUNT -> Known.LIST_AMOUNT
            TIME -> Known.TIME
            else -> throw AnthropicInvalidDataException("Unknown BetaAnalyticsCostOrderBy: $value")
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
    fun validate(): BetaAnalyticsCostOrderBy = apply {
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

        return other is BetaAnalyticsCostOrderBy && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
