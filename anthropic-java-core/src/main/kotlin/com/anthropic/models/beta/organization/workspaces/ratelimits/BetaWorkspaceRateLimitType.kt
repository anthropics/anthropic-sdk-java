package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/** Rate limiter type. */
class BetaWorkspaceRateLimitType private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val FAST_ITPMCA = BetaWorkspaceRateLimitType(JsonField.of("fast_itpmca"))

        @JvmField val FAST_OTPM = BetaWorkspaceRateLimitType(JsonField.of("fast_otpm"))

        @JvmField
        val INPUT_TOKENS_PER_MINUTE =
            BetaWorkspaceRateLimitType(JsonField.of("input_tokens_per_minute"))

        @JvmField
        val INPUT_TOKENS_PER_MINUTE_CACHE_AWARE =
            BetaWorkspaceRateLimitType(JsonField.of("input_tokens_per_minute_cache_aware"))

        @JvmField
        val OUTPUT_TOKENS_PER_MINUTE =
            BetaWorkspaceRateLimitType(JsonField.of("output_tokens_per_minute"))

        @JvmField
        val REQUESTS_PER_MINUTE = BetaWorkspaceRateLimitType(JsonField.of("requests_per_minute"))

        @JvmField val TOKENS_PER_DAY = BetaWorkspaceRateLimitType(JsonField.of("tokens_per_day"))

        @JvmField
        val TOKENS_PER_MINUTE = BetaWorkspaceRateLimitType(JsonField.of("tokens_per_minute"))

        @JvmField
        val TOOL_USES_PER_SECOND = BetaWorkspaceRateLimitType(JsonField.of("tool_uses_per_second"))

        @JvmStatic
        fun of(value: String): BetaWorkspaceRateLimitType =
            // Intern known values so `==` works
            when (value) {
                "fast_itpmca" -> FAST_ITPMCA
                "fast_otpm" -> FAST_OTPM
                "input_tokens_per_minute" -> INPUT_TOKENS_PER_MINUTE
                "input_tokens_per_minute_cache_aware" -> INPUT_TOKENS_PER_MINUTE_CACHE_AWARE
                "output_tokens_per_minute" -> OUTPUT_TOKENS_PER_MINUTE
                "requests_per_minute" -> REQUESTS_PER_MINUTE
                "tokens_per_day" -> TOKENS_PER_DAY
                "tokens_per_minute" -> TOKENS_PER_MINUTE
                "tool_uses_per_second" -> TOOL_USES_PER_SECOND
                else -> BetaWorkspaceRateLimitType(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaWorkspaceRateLimitType =
            value.asString().getOrNull()?.let { of(it) } ?: BetaWorkspaceRateLimitType(value)
    }

    /** An enum containing [BetaWorkspaceRateLimitType]'s known values. */
    enum class Known {
        FAST_ITPMCA,
        FAST_OTPM,
        INPUT_TOKENS_PER_MINUTE,
        INPUT_TOKENS_PER_MINUTE_CACHE_AWARE,
        OUTPUT_TOKENS_PER_MINUTE,
        REQUESTS_PER_MINUTE,
        TOKENS_PER_DAY,
        TOKENS_PER_MINUTE,
        TOOL_USES_PER_SECOND,
    }

    /**
     * An enum containing [BetaWorkspaceRateLimitType]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [BetaWorkspaceRateLimitType] can contain an unknown value in a couple of
     * cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        FAST_ITPMCA,
        FAST_OTPM,
        INPUT_TOKENS_PER_MINUTE,
        INPUT_TOKENS_PER_MINUTE_CACHE_AWARE,
        OUTPUT_TOKENS_PER_MINUTE,
        REQUESTS_PER_MINUTE,
        TOKENS_PER_DAY,
        TOKENS_PER_MINUTE,
        TOOL_USES_PER_SECOND,
        /**
         * An enum member indicating that [BetaWorkspaceRateLimitType] was instantiated with an
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
            FAST_ITPMCA -> Value.FAST_ITPMCA
            FAST_OTPM -> Value.FAST_OTPM
            INPUT_TOKENS_PER_MINUTE -> Value.INPUT_TOKENS_PER_MINUTE
            INPUT_TOKENS_PER_MINUTE_CACHE_AWARE -> Value.INPUT_TOKENS_PER_MINUTE_CACHE_AWARE
            OUTPUT_TOKENS_PER_MINUTE -> Value.OUTPUT_TOKENS_PER_MINUTE
            REQUESTS_PER_MINUTE -> Value.REQUESTS_PER_MINUTE
            TOKENS_PER_DAY -> Value.TOKENS_PER_DAY
            TOKENS_PER_MINUTE -> Value.TOKENS_PER_MINUTE
            TOOL_USES_PER_SECOND -> Value.TOOL_USES_PER_SECOND
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
            FAST_ITPMCA -> Known.FAST_ITPMCA
            FAST_OTPM -> Known.FAST_OTPM
            INPUT_TOKENS_PER_MINUTE -> Known.INPUT_TOKENS_PER_MINUTE
            INPUT_TOKENS_PER_MINUTE_CACHE_AWARE -> Known.INPUT_TOKENS_PER_MINUTE_CACHE_AWARE
            OUTPUT_TOKENS_PER_MINUTE -> Known.OUTPUT_TOKENS_PER_MINUTE
            REQUESTS_PER_MINUTE -> Known.REQUESTS_PER_MINUTE
            TOKENS_PER_DAY -> Known.TOKENS_PER_DAY
            TOKENS_PER_MINUTE -> Known.TOKENS_PER_MINUTE
            TOOL_USES_PER_SECOND -> Known.TOOL_USES_PER_SECOND
            else ->
                throw AnthropicInvalidDataException("Unknown BetaWorkspaceRateLimitType: $value")
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
    fun validate(): BetaWorkspaceRateLimitType = apply {
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

        return other is BetaWorkspaceRateLimitType && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
