// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class BetaRawContentBlockStopEvent
@JsonCreator
private constructor(
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonProperty("index") @ExcludeMissing private val index: JsonField<Long> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun type(): Type = type.getRequired("type")

    fun index(): Long = index.getRequired("index")

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("index") @ExcludeMissing fun _index() = index

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaRawContentBlockStopEvent = apply {
        if (!validated) {
            type()
            index()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var index: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRawContentBlockStopEvent: BetaRawContentBlockStopEvent) = apply {
            type = betaRawContentBlockStopEvent.type
            index = betaRawContentBlockStopEvent.index
            additionalProperties = betaRawContentBlockStopEvent.additionalProperties.toMutableMap()
        }

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun index(index: Long) = index(JsonField.of(index))

        fun index(index: JsonField<Long>) = apply { this.index = index }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        fun build(): BetaRawContentBlockStopEvent =
            BetaRawContentBlockStopEvent(
                type,
                index,
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

            @JvmField val CONTENT_BLOCK_STOP = of("content_block_stop")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            CONTENT_BLOCK_STOP,
        }

        enum class Value {
            CONTENT_BLOCK_STOP,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                CONTENT_BLOCK_STOP -> Value.CONTENT_BLOCK_STOP
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                CONTENT_BLOCK_STOP -> Known.CONTENT_BLOCK_STOP
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

        return /* spotless:off */ other is BetaRawContentBlockStopEvent && type == other.type && index == other.index && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, index, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRawContentBlockStopEvent{type=$type, index=$index, additionalProperties=$additionalProperties}"
}
