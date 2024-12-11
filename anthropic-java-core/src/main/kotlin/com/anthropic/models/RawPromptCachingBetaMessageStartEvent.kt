// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects

@JsonDeserialize(builder = RawPromptCachingBetaMessageStartEvent.Builder::class)
@NoAutoDetect
class RawPromptCachingBetaMessageStartEvent
private constructor(
    private val type: JsonField<Type>,
    private val message: JsonField<PromptCachingBetaMessage>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun type(): Type = type.getRequired("type")

    fun message(): PromptCachingBetaMessage = message.getRequired("message")

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("message") @ExcludeMissing fun _message() = message

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): RawPromptCachingBetaMessageStartEvent = apply {
        if (!validated) {
            type()
            message().validate()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var message: JsonField<PromptCachingBetaMessage> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            rawPromptCachingBetaMessageStartEvent: RawPromptCachingBetaMessageStartEvent
        ) = apply {
            this.type = rawPromptCachingBetaMessageStartEvent.type
            this.message = rawPromptCachingBetaMessageStartEvent.message
            additionalProperties(rawPromptCachingBetaMessageStartEvent.additionalProperties)
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun message(message: PromptCachingBetaMessage) = message(JsonField.of(message))

        @JsonProperty("message")
        @ExcludeMissing
        fun message(message: JsonField<PromptCachingBetaMessage>) = apply { this.message = message }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            this.additionalProperties.putAll(additionalProperties)
        }

        @JsonAnySetter
        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            this.additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun build(): RawPromptCachingBetaMessageStartEvent =
            RawPromptCachingBetaMessageStartEvent(
                type,
                message,
                additionalProperties.toImmutable(),
            )
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MESSAGE_START = of("message_start")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            MESSAGE_START,
        }

        enum class Value {
            MESSAGE_START,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                MESSAGE_START -> Value.MESSAGE_START
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                MESSAGE_START -> Known.MESSAGE_START
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is RawPromptCachingBetaMessageStartEvent && type == other.type && message == other.message && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, message, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RawPromptCachingBetaMessageStartEvent{type=$type, message=$message, additionalProperties=$additionalProperties}"
}
