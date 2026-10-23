package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

class BetaAnalyticsServiceTier private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val BATCH = BetaAnalyticsServiceTier(JsonField.of("batch"))

        @JvmField val FLEX = BetaAnalyticsServiceTier(JsonField.of("flex"))

        @JvmField val FLEX_DISCOUNT = BetaAnalyticsServiceTier(JsonField.of("flex_discount"))

        @JvmField val PRIORITY = BetaAnalyticsServiceTier(JsonField.of("priority"))

        @JvmField
        val PRIORITY_ON_DEMAND = BetaAnalyticsServiceTier(JsonField.of("priority_on_demand"))

        @JvmField val STANDARD = BetaAnalyticsServiceTier(JsonField.of("standard"))

        @JvmStatic
        fun of(value: String): BetaAnalyticsServiceTier =
            // Intern known values so `==` works
            when (value) {
                "batch" -> BATCH
                "flex" -> FLEX
                "flex_discount" -> FLEX_DISCOUNT
                "priority" -> PRIORITY
                "priority_on_demand" -> PRIORITY_ON_DEMAND
                "standard" -> STANDARD
                else -> BetaAnalyticsServiceTier(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaAnalyticsServiceTier =
            value.asString().getOrNull()?.let { of(it) } ?: BetaAnalyticsServiceTier(value)
    }

    /** An enum containing [BetaAnalyticsServiceTier]'s known values. */
    enum class Known {
        BATCH,
        FLEX,
        FLEX_DISCOUNT,
        PRIORITY,
        PRIORITY_ON_DEMAND,
        STANDARD,
    }

    /**
     * An enum containing [BetaAnalyticsServiceTier]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [BetaAnalyticsServiceTier] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        BATCH,
        FLEX,
        FLEX_DISCOUNT,
        PRIORITY,
        PRIORITY_ON_DEMAND,
        STANDARD,
        /**
         * An enum member indicating that [BetaAnalyticsServiceTier] was instantiated with an
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
            BATCH -> Value.BATCH
            FLEX -> Value.FLEX
            FLEX_DISCOUNT -> Value.FLEX_DISCOUNT
            PRIORITY -> Value.PRIORITY
            PRIORITY_ON_DEMAND -> Value.PRIORITY_ON_DEMAND
            STANDARD -> Value.STANDARD
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
            BATCH -> Known.BATCH
            FLEX -> Known.FLEX
            FLEX_DISCOUNT -> Known.FLEX_DISCOUNT
            PRIORITY -> Known.PRIORITY
            PRIORITY_ON_DEMAND -> Known.PRIORITY_ON_DEMAND
            STANDARD -> Known.STANDARD
            else -> throw AnthropicInvalidDataException("Unknown BetaAnalyticsServiceTier: $value")
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
    fun validate(): BetaAnalyticsServiceTier = apply {
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

        return other is BetaAnalyticsServiceTier && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
