// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator

class AnthropicBeta @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val MESSAGE_BATCHES_2024_09_24 = of("message-batches-2024-09-24")

        @JvmField val PROMPT_CACHING_2024_07_31 = of("prompt-caching-2024-07-31")

        @JvmField val COMPUTER_USE_2024_10_22 = of("computer-use-2024-10-22")

        @JvmField val COMPUTER_USE_2025_01_24 = of("computer-use-2025-01-24")

        @JvmField val PDFS_2024_09_25 = of("pdfs-2024-09-25")

        @JvmField val TOKEN_COUNTING_2024_11_01 = of("token-counting-2024-11-01")

        @JvmField val TOKEN_EFFICIENT_TOOLS_2025_02_19 = of("token-efficient-tools-2025-02-19")

        @JvmField val OUTPUT_128K_2025_02_19 = of("output-128k-2025-02-19")

        @JvmStatic fun of(value: String) = AnthropicBeta(JsonField.of(value))
    }

    /** An enum containing [AnthropicBeta]'s known values. */
    enum class Known {
        MESSAGE_BATCHES_2024_09_24,
        PROMPT_CACHING_2024_07_31,
        COMPUTER_USE_2024_10_22,
        COMPUTER_USE_2025_01_24,
        PDFS_2024_09_25,
        TOKEN_COUNTING_2024_11_01,
        TOKEN_EFFICIENT_TOOLS_2025_02_19,
        OUTPUT_128K_2025_02_19,
    }

    /**
     * An enum containing [AnthropicBeta]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [AnthropicBeta] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        MESSAGE_BATCHES_2024_09_24,
        PROMPT_CACHING_2024_07_31,
        COMPUTER_USE_2024_10_22,
        COMPUTER_USE_2025_01_24,
        PDFS_2024_09_25,
        TOKEN_COUNTING_2024_11_01,
        TOKEN_EFFICIENT_TOOLS_2025_02_19,
        OUTPUT_128K_2025_02_19,
        /**
         * An enum member indicating that [AnthropicBeta] was instantiated with an unknown value.
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
            MESSAGE_BATCHES_2024_09_24 -> Value.MESSAGE_BATCHES_2024_09_24
            PROMPT_CACHING_2024_07_31 -> Value.PROMPT_CACHING_2024_07_31
            COMPUTER_USE_2024_10_22 -> Value.COMPUTER_USE_2024_10_22
            COMPUTER_USE_2025_01_24 -> Value.COMPUTER_USE_2025_01_24
            PDFS_2024_09_25 -> Value.PDFS_2024_09_25
            TOKEN_COUNTING_2024_11_01 -> Value.TOKEN_COUNTING_2024_11_01
            TOKEN_EFFICIENT_TOOLS_2025_02_19 -> Value.TOKEN_EFFICIENT_TOOLS_2025_02_19
            OUTPUT_128K_2025_02_19 -> Value.OUTPUT_128K_2025_02_19
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
            MESSAGE_BATCHES_2024_09_24 -> Known.MESSAGE_BATCHES_2024_09_24
            PROMPT_CACHING_2024_07_31 -> Known.PROMPT_CACHING_2024_07_31
            COMPUTER_USE_2024_10_22 -> Known.COMPUTER_USE_2024_10_22
            COMPUTER_USE_2025_01_24 -> Known.COMPUTER_USE_2025_01_24
            PDFS_2024_09_25 -> Known.PDFS_2024_09_25
            TOKEN_COUNTING_2024_11_01 -> Known.TOKEN_COUNTING_2024_11_01
            TOKEN_EFFICIENT_TOOLS_2025_02_19 -> Known.TOKEN_EFFICIENT_TOOLS_2025_02_19
            OUTPUT_128K_2025_02_19 -> Known.OUTPUT_128K_2025_02_19
            else -> throw AnthropicInvalidDataException("Unknown AnthropicBeta: $value")
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is AnthropicBeta && value == other.value /* spotless:on */
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
