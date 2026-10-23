package com.anthropic.models.beta.organization.analytics.usage

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

class BetaAnalyticsUsageOrderBy private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val OUTPUT_TOKENS = BetaAnalyticsUsageOrderBy(JsonField.of("output_tokens"))

        @JvmField val REQUESTS = BetaAnalyticsUsageOrderBy(JsonField.of("requests"))

        @JvmField val TIME = BetaAnalyticsUsageOrderBy(JsonField.of("time"))

        @JvmField val TOTAL_TOKENS = BetaAnalyticsUsageOrderBy(JsonField.of("total_tokens"))

        @JvmField
        val UNCACHED_INPUT_TOKENS = BetaAnalyticsUsageOrderBy(JsonField.of("uncached_input_tokens"))

        @JvmStatic
        fun of(value: String): BetaAnalyticsUsageOrderBy =
            // Intern known values so `==` works
            when (value) {
                "output_tokens" -> OUTPUT_TOKENS
                "requests" -> REQUESTS
                "time" -> TIME
                "total_tokens" -> TOTAL_TOKENS
                "uncached_input_tokens" -> UNCACHED_INPUT_TOKENS
                else -> BetaAnalyticsUsageOrderBy(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaAnalyticsUsageOrderBy =
            value.asString().getOrNull()?.let { of(it) } ?: BetaAnalyticsUsageOrderBy(value)
    }

    /** An enum containing [BetaAnalyticsUsageOrderBy]'s known values. */
    enum class Known {
        OUTPUT_TOKENS,
        REQUESTS,
        TIME,
        TOTAL_TOKENS,
        UNCACHED_INPUT_TOKENS,
    }

    /**
     * An enum containing [BetaAnalyticsUsageOrderBy]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [BetaAnalyticsUsageOrderBy] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        OUTPUT_TOKENS,
        REQUESTS,
        TIME,
        TOTAL_TOKENS,
        UNCACHED_INPUT_TOKENS,
        /**
         * An enum member indicating that [BetaAnalyticsUsageOrderBy] was instantiated with an
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
            OUTPUT_TOKENS -> Value.OUTPUT_TOKENS
            REQUESTS -> Value.REQUESTS
            TIME -> Value.TIME
            TOTAL_TOKENS -> Value.TOTAL_TOKENS
            UNCACHED_INPUT_TOKENS -> Value.UNCACHED_INPUT_TOKENS
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
            OUTPUT_TOKENS -> Known.OUTPUT_TOKENS
            REQUESTS -> Known.REQUESTS
            TIME -> Known.TIME
            TOTAL_TOKENS -> Known.TOTAL_TOKENS
            UNCACHED_INPUT_TOKENS -> Known.UNCACHED_INPUT_TOKENS
            else -> throw AnthropicInvalidDataException("Unknown BetaAnalyticsUsageOrderBy: $value")
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
    fun validate(): BetaAnalyticsUsageOrderBy = apply {
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

        return other is BetaAnalyticsUsageOrderBy && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
