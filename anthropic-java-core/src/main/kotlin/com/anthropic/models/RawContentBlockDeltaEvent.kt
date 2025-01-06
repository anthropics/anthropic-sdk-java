// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.getOrThrow
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional

@NoAutoDetect
class RawContentBlockDeltaEvent
@JsonCreator
private constructor(
    @JsonProperty("delta") @ExcludeMissing private val delta: JsonField<Delta> = JsonMissing.of(),
    @JsonProperty("index") @ExcludeMissing private val index: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun delta(): Delta = delta.getRequired("delta")

    fun index(): Long = index.getRequired("index")

    fun type(): Type = type.getRequired("type")

    @JsonProperty("delta") @ExcludeMissing fun _delta() = delta

    @JsonProperty("index") @ExcludeMissing fun _index() = index

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): RawContentBlockDeltaEvent = apply {
        if (!validated) {
            delta()
            index()
            type()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var delta: JsonField<Delta> = JsonMissing.of()
        private var index: JsonField<Long> = JsonMissing.of()
        private var type: JsonField<Type> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(rawContentBlockDeltaEvent: RawContentBlockDeltaEvent) = apply {
            delta = rawContentBlockDeltaEvent.delta
            index = rawContentBlockDeltaEvent.index
            type = rawContentBlockDeltaEvent.type
            additionalProperties = rawContentBlockDeltaEvent.additionalProperties.toMutableMap()
        }

        fun delta(delta: Delta) = delta(JsonField.of(delta))

        fun delta(delta: JsonField<Delta>) = apply { this.delta = delta }

        fun index(index: Long) = index(JsonField.of(index))

        fun index(index: JsonField<Long>) = apply { this.index = index }

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

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

        fun build(): RawContentBlockDeltaEvent =
            RawContentBlockDeltaEvent(
                delta,
                index,
                type,
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Delta.Deserializer::class)
    @JsonSerialize(using = Delta.Serializer::class)
    class Delta
    private constructor(
        private val textDelta: TextDelta? = null,
        private val inputJsonDelta: InputJsonDelta? = null,
        private val _json: JsonValue? = null,
    ) {

        private var validated: Boolean = false

        fun textDelta(): Optional<TextDelta> = Optional.ofNullable(textDelta)

        fun inputJsonDelta(): Optional<InputJsonDelta> = Optional.ofNullable(inputJsonDelta)

        fun isTextDelta(): Boolean = textDelta != null

        fun isInputJsonDelta(): Boolean = inputJsonDelta != null

        fun asTextDelta(): TextDelta = textDelta.getOrThrow("textDelta")

        fun asInputJsonDelta(): InputJsonDelta = inputJsonDelta.getOrThrow("inputJsonDelta")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                textDelta != null -> visitor.visitTextDelta(textDelta)
                inputJsonDelta != null -> visitor.visitInputJsonDelta(inputJsonDelta)
                else -> visitor.unknown(_json)
            }
        }

        fun validate(): Delta = apply {
            if (!validated) {
                if (textDelta == null && inputJsonDelta == null) {
                    throw AnthropicInvalidDataException("Unknown Delta: $_json")
                }
                textDelta?.validate()
                inputJsonDelta?.validate()
                validated = true
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Delta && textDelta == other.textDelta && inputJsonDelta == other.inputJsonDelta /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(textDelta, inputJsonDelta) /* spotless:on */

        override fun toString(): String =
            when {
                textDelta != null -> "Delta{textDelta=$textDelta}"
                inputJsonDelta != null -> "Delta{inputJsonDelta=$inputJsonDelta}"
                _json != null -> "Delta{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Delta")
            }

        companion object {

            @JvmStatic fun ofTextDelta(textDelta: TextDelta) = Delta(textDelta = textDelta)

            @JvmStatic
            fun ofInputJsonDelta(inputJsonDelta: InputJsonDelta) =
                Delta(inputJsonDelta = inputJsonDelta)
        }

        interface Visitor<out T> {

            fun visitTextDelta(textDelta: TextDelta): T

            fun visitInputJsonDelta(inputJsonDelta: InputJsonDelta): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Delta: $json")
            }
        }

        class Deserializer : BaseDeserializer<Delta>(Delta::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Delta {
                val json = JsonValue.fromJsonNode(node)

                tryDeserialize(node, jacksonTypeRef<TextDelta>()) { it.validate() }
                    ?.let {
                        return Delta(textDelta = it, _json = json)
                    }
                tryDeserialize(node, jacksonTypeRef<InputJsonDelta>()) { it.validate() }
                    ?.let {
                        return Delta(inputJsonDelta = it, _json = json)
                    }

                return Delta(_json = json)
            }
        }

        class Serializer : BaseSerializer<Delta>(Delta::class) {

            override fun serialize(
                value: Delta,
                generator: JsonGenerator,
                provider: SerializerProvider
            ) {
                when {
                    value.textDelta != null -> generator.writeObject(value.textDelta)
                    value.inputJsonDelta != null -> generator.writeObject(value.inputJsonDelta)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Delta")
                }
            }
        }
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val CONTENT_BLOCK_DELTA = of("content_block_delta")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            CONTENT_BLOCK_DELTA,
        }

        enum class Value {
            CONTENT_BLOCK_DELTA,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                CONTENT_BLOCK_DELTA -> Value.CONTENT_BLOCK_DELTA
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                CONTENT_BLOCK_DELTA -> Known.CONTENT_BLOCK_DELTA
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

        return /* spotless:off */ other is RawContentBlockDeltaEvent && delta == other.delta && index == other.index && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(delta, index, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RawContentBlockDeltaEvent{delta=$delta, index=$index, type=$type, additionalProperties=$additionalProperties}"
}
