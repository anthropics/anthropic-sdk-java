// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator

class AnthropicBeta
@JsonCreator
private constructor(
    private val value: JsonField<String>,
) : Enum {

    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val MESSAGE_BATCHES_2024_09_24 = of("message-batches-2024-09-24")

        @JvmField val PROMPT_CACHING_2024_07_31 = of("prompt-caching-2024-07-31")

        @JvmField val COMPUTER_USE_2024_10_22 = of("computer-use-2024-10-22")

        @JvmField val PDFS_2024_09_25 = of("pdfs-2024-09-25")

        @JvmField val TOKEN_COUNTING_2024_11_01 = of("token-counting-2024-11-01")

        @JvmStatic fun of(value: String) = AnthropicBeta(JsonField.of(value))
    }

    enum class Known {
        MESSAGE_BATCHES_2024_09_24,
        PROMPT_CACHING_2024_07_31,
        COMPUTER_USE_2024_10_22,
        PDFS_2024_09_25,
        TOKEN_COUNTING_2024_11_01,
    }

    enum class Value {
        MESSAGE_BATCHES_2024_09_24,
        PROMPT_CACHING_2024_07_31,
        COMPUTER_USE_2024_10_22,
        PDFS_2024_09_25,
        TOKEN_COUNTING_2024_11_01,
        _UNKNOWN,
    }

    fun value(): Value =
        when (this) {
            MESSAGE_BATCHES_2024_09_24 -> Value.MESSAGE_BATCHES_2024_09_24
            PROMPT_CACHING_2024_07_31 -> Value.PROMPT_CACHING_2024_07_31
            COMPUTER_USE_2024_10_22 -> Value.COMPUTER_USE_2024_10_22
            PDFS_2024_09_25 -> Value.PDFS_2024_09_25
            TOKEN_COUNTING_2024_11_01 -> Value.TOKEN_COUNTING_2024_11_01
            else -> Value._UNKNOWN
        }

    fun known(): Known =
        when (this) {
            MESSAGE_BATCHES_2024_09_24 -> Known.MESSAGE_BATCHES_2024_09_24
            PROMPT_CACHING_2024_07_31 -> Known.PROMPT_CACHING_2024_07_31
            COMPUTER_USE_2024_10_22 -> Known.COMPUTER_USE_2024_10_22
            PDFS_2024_09_25 -> Known.PDFS_2024_09_25
            TOKEN_COUNTING_2024_11_01 -> Known.TOKEN_COUNTING_2024_11_01
            else -> throw AnthropicInvalidDataException("Unknown AnthropicBeta: $value")
        }

    fun asString(): String = _value().asStringOrThrow()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is AnthropicBeta && value == other.value /* spotless:on */
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
