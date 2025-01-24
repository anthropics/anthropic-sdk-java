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
import com.anthropic.core.checkRequired
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
import kotlin.jvm.optionals.getOrNull

@NoAutoDetect
class BetaRawContentBlockDeltaEvent
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

    @JsonProperty("delta") @ExcludeMissing fun _delta(): JsonField<Delta> = delta

    @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Long> = index

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaRawContentBlockDeltaEvent = apply {
        if (validated) {
            return@apply
        }

        delta().validate()
        index()
        type()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var delta: JsonField<Delta>? = null
        private var index: JsonField<Long>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRawContentBlockDeltaEvent: BetaRawContentBlockDeltaEvent) = apply {
            delta = betaRawContentBlockDeltaEvent.delta
            index = betaRawContentBlockDeltaEvent.index
            type = betaRawContentBlockDeltaEvent.type
            additionalProperties = betaRawContentBlockDeltaEvent.additionalProperties.toMutableMap()
        }

        fun delta(delta: Delta) = delta(JsonField.of(delta))

        fun delta(delta: JsonField<Delta>) = apply { this.delta = delta }

        fun delta(betaText: BetaTextDelta) = delta(Delta.ofBetaText(betaText))

        fun delta(betaInputJson: BetaInputJsonDelta) = delta(Delta.ofBetaInputJson(betaInputJson))

        fun delta(betaCitations: BetaCitationsDelta) = delta(Delta.ofBetaCitations(betaCitations))

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

        fun build(): BetaRawContentBlockDeltaEvent =
            BetaRawContentBlockDeltaEvent(
                checkRequired("delta", delta),
                checkRequired("index", index),
                checkRequired("type", type),
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Delta.Deserializer::class)
    @JsonSerialize(using = Delta.Serializer::class)
    class Delta
    private constructor(
        private val betaText: BetaTextDelta? = null,
        private val betaInputJson: BetaInputJsonDelta? = null,
        private val betaCitations: BetaCitationsDelta? = null,
        private val _json: JsonValue? = null,
    ) {

        fun betaText(): Optional<BetaTextDelta> = Optional.ofNullable(betaText)

        fun betaInputJson(): Optional<BetaInputJsonDelta> = Optional.ofNullable(betaInputJson)

        fun betaCitations(): Optional<BetaCitationsDelta> = Optional.ofNullable(betaCitations)

        fun isBetaText(): Boolean = betaText != null

        fun isBetaInputJson(): Boolean = betaInputJson != null

        fun isBetaCitations(): Boolean = betaCitations != null

        fun asBetaText(): BetaTextDelta = betaText.getOrThrow("betaText")

        fun asBetaInputJson(): BetaInputJsonDelta = betaInputJson.getOrThrow("betaInputJson")

        fun asBetaCitations(): BetaCitationsDelta = betaCitations.getOrThrow("betaCitations")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaText != null -> visitor.visitBetaText(betaText)
                betaInputJson != null -> visitor.visitBetaInputJson(betaInputJson)
                betaCitations != null -> visitor.visitBetaCitations(betaCitations)
                else -> visitor.unknown(_json)
            }
        }

        private var validated: Boolean = false

        fun validate(): Delta = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBetaText(betaText: BetaTextDelta) {
                        betaText.validate()
                    }

                    override fun visitBetaInputJson(betaInputJson: BetaInputJsonDelta) {
                        betaInputJson.validate()
                    }

                    override fun visitBetaCitations(betaCitations: BetaCitationsDelta) {
                        betaCitations.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Delta && betaText == other.betaText && betaInputJson == other.betaInputJson && betaCitations == other.betaCitations /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaText, betaInputJson, betaCitations) /* spotless:on */

        override fun toString(): String =
            when {
                betaText != null -> "Delta{betaText=$betaText}"
                betaInputJson != null -> "Delta{betaInputJson=$betaInputJson}"
                betaCitations != null -> "Delta{betaCitations=$betaCitations}"
                _json != null -> "Delta{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Delta")
            }

        companion object {

            @JvmStatic fun ofBetaText(betaText: BetaTextDelta) = Delta(betaText = betaText)

            @JvmStatic
            fun ofBetaInputJson(betaInputJson: BetaInputJsonDelta) =
                Delta(betaInputJson = betaInputJson)

            @JvmStatic
            fun ofBetaCitations(betaCitations: BetaCitationsDelta) =
                Delta(betaCitations = betaCitations)
        }

        interface Visitor<out T> {

            fun visitBetaText(betaText: BetaTextDelta): T

            fun visitBetaInputJson(betaInputJson: BetaInputJsonDelta): T

            fun visitBetaCitations(betaCitations: BetaCitationsDelta): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Delta: $json")
            }
        }

        class Deserializer : BaseDeserializer<Delta>(Delta::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Delta {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "text_delta" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaTextDelta>()) { it.validate() }
                            ?.let {
                                return Delta(betaText = it, _json = json)
                            }
                    }
                    "input_json_delta" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaInputJsonDelta>()) { it.validate() }
                            ?.let {
                                return Delta(betaInputJson = it, _json = json)
                            }
                    }
                    "citations_delta" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaCitationsDelta>()) { it.validate() }
                            ?.let {
                                return Delta(betaCitations = it, _json = json)
                            }
                    }
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
                    value.betaText != null -> generator.writeObject(value.betaText)
                    value.betaInputJson != null -> generator.writeObject(value.betaInputJson)
                    value.betaCitations != null -> generator.writeObject(value.betaCitations)
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

        return /* spotless:off */ other is BetaRawContentBlockDeltaEvent && delta == other.delta && index == other.index && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(delta, index, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRawContentBlockDeltaEvent{delta=$delta, index=$index, type=$type, additionalProperties=$additionalProperties}"
}
