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

@JsonDeserialize(builder = BetaRawContentBlockDeltaEvent.Builder::class)
@NoAutoDetect
class BetaRawContentBlockDeltaEvent
private constructor(
    private val type: JsonField<Type>,
    private val index: JsonField<Long>,
    private val delta: JsonField<Delta>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun type(): Type = type.getRequired("type")

    fun index(): Long = index.getRequired("index")

    fun delta(): Delta = delta.getRequired("delta")

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("index") @ExcludeMissing fun _index() = index

    @JsonProperty("delta") @ExcludeMissing fun _delta() = delta

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaRawContentBlockDeltaEvent = apply {
        if (!validated) {
            type()
            index()
            delta()
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
        private var delta: JsonField<Delta> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRawContentBlockDeltaEvent: BetaRawContentBlockDeltaEvent) = apply {
            this.type = betaRawContentBlockDeltaEvent.type
            this.index = betaRawContentBlockDeltaEvent.index
            this.delta = betaRawContentBlockDeltaEvent.delta
            additionalProperties(betaRawContentBlockDeltaEvent.additionalProperties)
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun index(index: Long) = index(JsonField.of(index))

        @JsonProperty("index")
        @ExcludeMissing
        fun index(index: JsonField<Long>) = apply { this.index = index }

        fun delta(delta: Delta) = delta(JsonField.of(delta))

        @JsonProperty("delta")
        @ExcludeMissing
        fun delta(delta: JsonField<Delta>) = apply { this.delta = delta }

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

        fun build(): BetaRawContentBlockDeltaEvent =
            BetaRawContentBlockDeltaEvent(
                type,
                index,
                delta,
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Delta.Deserializer::class)
    @JsonSerialize(using = Delta.Serializer::class)
    class Delta
    private constructor(
        private val betaTextDelta: BetaTextDelta? = null,
        private val betaInputJsonDelta: BetaInputJsonDelta? = null,
        private val _json: JsonValue? = null,
    ) {

        private var validated: Boolean = false

        fun betaTextDelta(): Optional<BetaTextDelta> = Optional.ofNullable(betaTextDelta)

        fun betaInputJsonDelta(): Optional<BetaInputJsonDelta> =
            Optional.ofNullable(betaInputJsonDelta)

        fun isBetaTextDelta(): Boolean = betaTextDelta != null

        fun isBetaInputJsonDelta(): Boolean = betaInputJsonDelta != null

        fun asBetaTextDelta(): BetaTextDelta = betaTextDelta.getOrThrow("betaTextDelta")

        fun asBetaInputJsonDelta(): BetaInputJsonDelta =
            betaInputJsonDelta.getOrThrow("betaInputJsonDelta")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaTextDelta != null -> visitor.visitBetaTextDelta(betaTextDelta)
                betaInputJsonDelta != null -> visitor.visitBetaInputJsonDelta(betaInputJsonDelta)
                else -> visitor.unknown(_json)
            }
        }

        fun validate(): Delta = apply {
            if (!validated) {
                if (betaTextDelta == null && betaInputJsonDelta == null) {
                    throw AnthropicInvalidDataException("Unknown Delta: $_json")
                }
                betaTextDelta?.validate()
                betaInputJsonDelta?.validate()
                validated = true
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Delta && betaTextDelta == other.betaTextDelta && betaInputJsonDelta == other.betaInputJsonDelta /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaTextDelta, betaInputJsonDelta) /* spotless:on */

        override fun toString(): String =
            when {
                betaTextDelta != null -> "Delta{betaTextDelta=$betaTextDelta}"
                betaInputJsonDelta != null -> "Delta{betaInputJsonDelta=$betaInputJsonDelta}"
                _json != null -> "Delta{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Delta")
            }

        companion object {

            @JvmStatic
            fun ofBetaTextDelta(betaTextDelta: BetaTextDelta) = Delta(betaTextDelta = betaTextDelta)

            @JvmStatic
            fun ofBetaInputJsonDelta(betaInputJsonDelta: BetaInputJsonDelta) =
                Delta(betaInputJsonDelta = betaInputJsonDelta)
        }

        interface Visitor<out T> {

            fun visitBetaTextDelta(betaTextDelta: BetaTextDelta): T

            fun visitBetaInputJsonDelta(betaInputJsonDelta: BetaInputJsonDelta): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Delta: $json")
            }
        }

        class Deserializer : BaseDeserializer<Delta>(Delta::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Delta {
                val json = JsonValue.fromJsonNode(node)

                tryDeserialize(node, jacksonTypeRef<BetaTextDelta>()) { it.validate() }
                    ?.let {
                        return Delta(betaTextDelta = it, _json = json)
                    }
                tryDeserialize(node, jacksonTypeRef<BetaInputJsonDelta>()) { it.validate() }
                    ?.let {
                        return Delta(betaInputJsonDelta = it, _json = json)
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
                    value.betaTextDelta != null -> generator.writeObject(value.betaTextDelta)
                    value.betaInputJsonDelta != null ->
                        generator.writeObject(value.betaInputJsonDelta)
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

        return /* spotless:off */ other is BetaRawContentBlockDeltaEvent && type == other.type && index == other.index && delta == other.delta && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, index, delta, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRawContentBlockDeltaEvent{type=$type, index=$index, delta=$delta, additionalProperties=$additionalProperties}"
}
