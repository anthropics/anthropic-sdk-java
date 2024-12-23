// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator

class Model
@JsonCreator
private constructor(
    private val value: JsonField<String>,
) : Enum {

    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val CLAUDE_3_5_HAIKU_LATEST = of("claude-3-5-haiku-latest")

        @JvmField val CLAUDE_3_5_HAIKU_20241022 = of("claude-3-5-haiku-20241022")

        @JvmField val CLAUDE_3_5_SONNET_LATEST = of("claude-3-5-sonnet-latest")

        @JvmField val CLAUDE_3_5_SONNET_20241022 = of("claude-3-5-sonnet-20241022")

        @JvmField val CLAUDE_3_5_SONNET_20240620 = of("claude-3-5-sonnet-20240620")

        @JvmField val CLAUDE_3_OPUS_LATEST = of("claude-3-opus-latest")

        @JvmField val CLAUDE_3_OPUS_20240229 = of("claude-3-opus-20240229")

        @JvmField val CLAUDE_3_SONNET_20240229 = of("claude-3-sonnet-20240229")

        @JvmField val CLAUDE_3_HAIKU_20240307 = of("claude-3-haiku-20240307")

        @JvmField val CLAUDE_2_1 = of("claude-2.1")

        @JvmField val CLAUDE_2_0 = of("claude-2.0")

        @JvmStatic fun of(value: String) = Model(JsonField.of(value))
    }

    enum class Known {
        CLAUDE_3_5_HAIKU_LATEST,
        CLAUDE_3_5_HAIKU_20241022,
        CLAUDE_3_5_SONNET_LATEST,
        CLAUDE_3_5_SONNET_20241022,
        CLAUDE_3_5_SONNET_20240620,
        CLAUDE_3_OPUS_LATEST,
        CLAUDE_3_OPUS_20240229,
        CLAUDE_3_SONNET_20240229,
        CLAUDE_3_HAIKU_20240307,
        CLAUDE_2_1,
        CLAUDE_2_0,
    }

    enum class Value {
        CLAUDE_3_5_HAIKU_LATEST,
        CLAUDE_3_5_HAIKU_20241022,
        CLAUDE_3_5_SONNET_LATEST,
        CLAUDE_3_5_SONNET_20241022,
        CLAUDE_3_5_SONNET_20240620,
        CLAUDE_3_OPUS_LATEST,
        CLAUDE_3_OPUS_20240229,
        CLAUDE_3_SONNET_20240229,
        CLAUDE_3_HAIKU_20240307,
        CLAUDE_2_1,
        CLAUDE_2_0,
        _UNKNOWN,
    }

    fun value(): Value =
        when (this) {
            CLAUDE_3_5_HAIKU_LATEST -> Value.CLAUDE_3_5_HAIKU_LATEST
            CLAUDE_3_5_HAIKU_20241022 -> Value.CLAUDE_3_5_HAIKU_20241022
            CLAUDE_3_5_SONNET_LATEST -> Value.CLAUDE_3_5_SONNET_LATEST
            CLAUDE_3_5_SONNET_20241022 -> Value.CLAUDE_3_5_SONNET_20241022
            CLAUDE_3_5_SONNET_20240620 -> Value.CLAUDE_3_5_SONNET_20240620
            CLAUDE_3_OPUS_LATEST -> Value.CLAUDE_3_OPUS_LATEST
            CLAUDE_3_OPUS_20240229 -> Value.CLAUDE_3_OPUS_20240229
            CLAUDE_3_SONNET_20240229 -> Value.CLAUDE_3_SONNET_20240229
            CLAUDE_3_HAIKU_20240307 -> Value.CLAUDE_3_HAIKU_20240307
            CLAUDE_2_1 -> Value.CLAUDE_2_1
            CLAUDE_2_0 -> Value.CLAUDE_2_0
            else -> Value._UNKNOWN
        }

    fun known(): Known =
        when (this) {
            CLAUDE_3_5_HAIKU_LATEST -> Known.CLAUDE_3_5_HAIKU_LATEST
            CLAUDE_3_5_HAIKU_20241022 -> Known.CLAUDE_3_5_HAIKU_20241022
            CLAUDE_3_5_SONNET_LATEST -> Known.CLAUDE_3_5_SONNET_LATEST
            CLAUDE_3_5_SONNET_20241022 -> Known.CLAUDE_3_5_SONNET_20241022
            CLAUDE_3_5_SONNET_20240620 -> Known.CLAUDE_3_5_SONNET_20240620
            CLAUDE_3_OPUS_LATEST -> Known.CLAUDE_3_OPUS_LATEST
            CLAUDE_3_OPUS_20240229 -> Known.CLAUDE_3_OPUS_20240229
            CLAUDE_3_SONNET_20240229 -> Known.CLAUDE_3_SONNET_20240229
            CLAUDE_3_HAIKU_20240307 -> Known.CLAUDE_3_HAIKU_20240307
            CLAUDE_2_1 -> Known.CLAUDE_2_1
            CLAUDE_2_0 -> Known.CLAUDE_2_0
            else -> throw AnthropicInvalidDataException("Unknown Model: $value")
        }

    fun asString(): String = _value().asStringOrThrow()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is Model && value == other.value /* spotless:on */
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
