// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator

/**
 * The model that will complete your prompt.\n\nSee
 * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and options.
 */
class Model @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        /** Most intelligent model for building agents and coding */
        @JvmField val CLAUDE_OPUS_4_6 = of("claude-opus-4-6")

        /** Best combination of speed and intelligence */
        @JvmField val CLAUDE_SONNET_4_6 = of("claude-sonnet-4-6")

        /** Fastest model with near-frontier intelligence */
        @JvmField val CLAUDE_HAIKU_4_5 = of("claude-haiku-4-5")

        /** Fastest model with near-frontier intelligence */
        @JvmField val CLAUDE_HAIKU_4_5_20251001 = of("claude-haiku-4-5-20251001")

        /** Premium model combining maximum intelligence with practical performance */
        @JvmField val CLAUDE_OPUS_4_5 = of("claude-opus-4-5")

        /** Premium model combining maximum intelligence with practical performance */
        @JvmField val CLAUDE_OPUS_4_5_20251101 = of("claude-opus-4-5-20251101")

        /** High-performance model for agents and coding */
        @JvmField val CLAUDE_SONNET_4_5 = of("claude-sonnet-4-5")

        /** High-performance model for agents and coding */
        @JvmField val CLAUDE_SONNET_4_5_20250929 = of("claude-sonnet-4-5-20250929")

        /** Exceptional model for specialized complex tasks */
        @JvmField val CLAUDE_OPUS_4_1 = of("claude-opus-4-1")

        /** Exceptional model for specialized complex tasks */
        @JvmField val CLAUDE_OPUS_4_1_20250805 = of("claude-opus-4-1-20250805")

        /** Powerful model for complex tasks */
        @JvmField val CLAUDE_OPUS_4_0 = of("claude-opus-4-0")

        /** Powerful model for complex tasks */
        @JvmField val CLAUDE_OPUS_4_20250514 = of("claude-opus-4-20250514")

        /** High-performance model with extended thinking */
        @JvmField val CLAUDE_SONNET_4_0 = of("claude-sonnet-4-0")

        /** High-performance model with extended thinking */
        @JvmField val CLAUDE_SONNET_4_20250514 = of("claude-sonnet-4-20250514")

        /** Fast and cost-effective model */
        @Deprecated(
            "Will reach end-of-life on April 20th, 2026. Please migrate to claude-haiku-4-5. Visit https://docs.anthropic.com/en/docs/resources/model-deprecations for more information."
        )
        @JvmField
        val CLAUDE_3_HAIKU_20240307 = of("claude-3-haiku-20240307")

        @JvmStatic fun of(value: String) = Model(JsonField.of(value))
    }

    /** An enum containing [Model]'s known values. */
    enum class Known {
        /** Most intelligent model for building agents and coding */
        CLAUDE_OPUS_4_6,
        /** Best combination of speed and intelligence */
        CLAUDE_SONNET_4_6,
        /** Fastest model with near-frontier intelligence */
        CLAUDE_HAIKU_4_5,
        /** Fastest model with near-frontier intelligence */
        CLAUDE_HAIKU_4_5_20251001,
        /** Premium model combining maximum intelligence with practical performance */
        CLAUDE_OPUS_4_5,
        /** Premium model combining maximum intelligence with practical performance */
        CLAUDE_OPUS_4_5_20251101,
        /** High-performance model for agents and coding */
        CLAUDE_SONNET_4_5,
        /** High-performance model for agents and coding */
        CLAUDE_SONNET_4_5_20250929,
        /** Exceptional model for specialized complex tasks */
        CLAUDE_OPUS_4_1,
        /** Exceptional model for specialized complex tasks */
        CLAUDE_OPUS_4_1_20250805,
        /** Powerful model for complex tasks */
        CLAUDE_OPUS_4_0,
        /** Powerful model for complex tasks */
        CLAUDE_OPUS_4_20250514,
        /** High-performance model with extended thinking */
        CLAUDE_SONNET_4_0,
        /** High-performance model with extended thinking */
        CLAUDE_SONNET_4_20250514,
        @Deprecated(
            "Will reach end-of-life on April 20th, 2026. Please migrate to claude-haiku-4-5. Visit https://docs.anthropic.com/en/docs/resources/model-deprecations for more information."
        )
        /** Fast and cost-effective model */
        CLAUDE_3_HAIKU_20240307,
    }

    /**
     * An enum containing [Model]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [Model] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /** Most intelligent model for building agents and coding */
        CLAUDE_OPUS_4_6,
        /** Best combination of speed and intelligence */
        CLAUDE_SONNET_4_6,
        /** Fastest model with near-frontier intelligence */
        CLAUDE_HAIKU_4_5,
        /** Fastest model with near-frontier intelligence */
        CLAUDE_HAIKU_4_5_20251001,
        /** Premium model combining maximum intelligence with practical performance */
        CLAUDE_OPUS_4_5,
        /** Premium model combining maximum intelligence with practical performance */
        CLAUDE_OPUS_4_5_20251101,
        /** High-performance model for agents and coding */
        CLAUDE_SONNET_4_5,
        /** High-performance model for agents and coding */
        CLAUDE_SONNET_4_5_20250929,
        /** Exceptional model for specialized complex tasks */
        CLAUDE_OPUS_4_1,
        /** Exceptional model for specialized complex tasks */
        CLAUDE_OPUS_4_1_20250805,
        /** Powerful model for complex tasks */
        CLAUDE_OPUS_4_0,
        /** Powerful model for complex tasks */
        CLAUDE_OPUS_4_20250514,
        /** High-performance model with extended thinking */
        CLAUDE_SONNET_4_0,
        /** High-performance model with extended thinking */
        CLAUDE_SONNET_4_20250514,
        @Deprecated(
            "Will reach end-of-life on April 20th, 2026. Please migrate to claude-haiku-4-5. Visit https://docs.anthropic.com/en/docs/resources/model-deprecations for more information."
        )
        /** Fast and cost-effective model */
        CLAUDE_3_HAIKU_20240307,
        /** An enum member indicating that [Model] was instantiated with an unknown value. */
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
            CLAUDE_OPUS_4_6 -> Value.CLAUDE_OPUS_4_6
            CLAUDE_SONNET_4_6 -> Value.CLAUDE_SONNET_4_6
            CLAUDE_HAIKU_4_5 -> Value.CLAUDE_HAIKU_4_5
            CLAUDE_HAIKU_4_5_20251001 -> Value.CLAUDE_HAIKU_4_5_20251001
            CLAUDE_OPUS_4_5 -> Value.CLAUDE_OPUS_4_5
            CLAUDE_OPUS_4_5_20251101 -> Value.CLAUDE_OPUS_4_5_20251101
            CLAUDE_SONNET_4_5 -> Value.CLAUDE_SONNET_4_5
            CLAUDE_SONNET_4_5_20250929 -> Value.CLAUDE_SONNET_4_5_20250929
            CLAUDE_OPUS_4_1 -> Value.CLAUDE_OPUS_4_1
            CLAUDE_OPUS_4_1_20250805 -> Value.CLAUDE_OPUS_4_1_20250805
            CLAUDE_OPUS_4_0 -> Value.CLAUDE_OPUS_4_0
            CLAUDE_OPUS_4_20250514 -> Value.CLAUDE_OPUS_4_20250514
            CLAUDE_SONNET_4_0 -> Value.CLAUDE_SONNET_4_0
            CLAUDE_SONNET_4_20250514 -> Value.CLAUDE_SONNET_4_20250514
            CLAUDE_3_HAIKU_20240307 -> Value.CLAUDE_3_HAIKU_20240307
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
            CLAUDE_OPUS_4_6 -> Known.CLAUDE_OPUS_4_6
            CLAUDE_SONNET_4_6 -> Known.CLAUDE_SONNET_4_6
            CLAUDE_HAIKU_4_5 -> Known.CLAUDE_HAIKU_4_5
            CLAUDE_HAIKU_4_5_20251001 -> Known.CLAUDE_HAIKU_4_5_20251001
            CLAUDE_OPUS_4_5 -> Known.CLAUDE_OPUS_4_5
            CLAUDE_OPUS_4_5_20251101 -> Known.CLAUDE_OPUS_4_5_20251101
            CLAUDE_SONNET_4_5 -> Known.CLAUDE_SONNET_4_5
            CLAUDE_SONNET_4_5_20250929 -> Known.CLAUDE_SONNET_4_5_20250929
            CLAUDE_OPUS_4_1 -> Known.CLAUDE_OPUS_4_1
            CLAUDE_OPUS_4_1_20250805 -> Known.CLAUDE_OPUS_4_1_20250805
            CLAUDE_OPUS_4_0 -> Known.CLAUDE_OPUS_4_0
            CLAUDE_OPUS_4_20250514 -> Known.CLAUDE_OPUS_4_20250514
            CLAUDE_SONNET_4_0 -> Known.CLAUDE_SONNET_4_0
            CLAUDE_SONNET_4_20250514 -> Known.CLAUDE_SONNET_4_20250514
            CLAUDE_3_HAIKU_20240307 -> Known.CLAUDE_3_HAIKU_20240307
            else -> throw AnthropicInvalidDataException("Unknown Model: $value")
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

    fun validate(): Model = apply {
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

        return other is Model && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
