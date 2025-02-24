// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
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
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun delta(): Delta = delta.getRequired("delta")

    fun index(): Long = index.getRequired("index")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    @JsonProperty("delta") @ExcludeMissing fun _delta(): JsonField<Delta> = delta

    @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Long> = index

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
        _type().let {
            if (it != JsonValue.from("content_block_delta")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRawContentBlockDeltaEvent]. */
    class Builder internal constructor() {

        private var delta: JsonField<Delta>? = null
        private var index: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("content_block_delta")
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

        fun betaTextDelta(text: String) = delta(BetaTextDelta.builder().text(text).build())

        fun delta(betaInputJson: BetaInputJsonDelta) = delta(Delta.ofBetaInputJson(betaInputJson))

        fun betaInputJsonDelta(partialJson: String) =
            delta(BetaInputJsonDelta.builder().partialJson(partialJson).build())

        fun delta(betaCitations: BetaCitationsDelta) = delta(Delta.ofBetaCitations(betaCitations))

        fun betaCitationsDelta(citation: BetaCitationsDelta.Citation) =
            delta(BetaCitationsDelta.builder().citation(citation).build())

        fun betaCitationsDelta(betaCitationCharLocation: BetaCitationCharLocation) =
            betaCitationsDelta(
                BetaCitationsDelta.Citation.ofBetaCitationCharLocation(betaCitationCharLocation)
            )

        fun betaCitationsDelta(betaCitationPageLocation: BetaCitationPageLocation) =
            betaCitationsDelta(
                BetaCitationsDelta.Citation.ofBetaCitationPageLocation(betaCitationPageLocation)
            )

        fun betaCitationsDelta(betaCitationContentBlockLocation: BetaCitationContentBlockLocation) =
            betaCitationsDelta(
                BetaCitationsDelta.Citation.ofBetaCitationContentBlockLocation(
                    betaCitationContentBlockLocation
                )
            )

        fun delta(betaThinking: BetaThinkingDelta) = delta(Delta.ofBetaThinking(betaThinking))

        fun betaThinkingDelta(thinking: String) =
            delta(BetaThinkingDelta.builder().thinking(thinking).build())

        fun delta(betaSignature: BetaSignatureDelta) = delta(Delta.ofBetaSignature(betaSignature))

        fun betaSignatureDelta(signature: String) =
            delta(BetaSignatureDelta.builder().signature(signature).build())

        fun index(index: Long) = index(JsonField.of(index))

        fun index(index: JsonField<Long>) = apply { this.index = index }

        fun type(type: JsonValue) = apply { this.type = type }

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
                type,
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
        private val betaThinking: BetaThinkingDelta? = null,
        private val betaSignature: BetaSignatureDelta? = null,
        private val _json: JsonValue? = null,
    ) {

        fun betaText(): Optional<BetaTextDelta> = Optional.ofNullable(betaText)

        fun betaInputJson(): Optional<BetaInputJsonDelta> = Optional.ofNullable(betaInputJson)

        fun betaCitations(): Optional<BetaCitationsDelta> = Optional.ofNullable(betaCitations)

        fun betaThinking(): Optional<BetaThinkingDelta> = Optional.ofNullable(betaThinking)

        fun betaSignature(): Optional<BetaSignatureDelta> = Optional.ofNullable(betaSignature)

        fun isBetaText(): Boolean = betaText != null

        fun isBetaInputJson(): Boolean = betaInputJson != null

        fun isBetaCitations(): Boolean = betaCitations != null

        fun isBetaThinking(): Boolean = betaThinking != null

        fun isBetaSignature(): Boolean = betaSignature != null

        fun asBetaText(): BetaTextDelta = betaText.getOrThrow("betaText")

        fun asBetaInputJson(): BetaInputJsonDelta = betaInputJson.getOrThrow("betaInputJson")

        fun asBetaCitations(): BetaCitationsDelta = betaCitations.getOrThrow("betaCitations")

        fun asBetaThinking(): BetaThinkingDelta = betaThinking.getOrThrow("betaThinking")

        fun asBetaSignature(): BetaSignatureDelta = betaSignature.getOrThrow("betaSignature")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaText != null -> visitor.visitBetaText(betaText)
                betaInputJson != null -> visitor.visitBetaInputJson(betaInputJson)
                betaCitations != null -> visitor.visitBetaCitations(betaCitations)
                betaThinking != null -> visitor.visitBetaThinking(betaThinking)
                betaSignature != null -> visitor.visitBetaSignature(betaSignature)
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

                    override fun visitBetaThinking(betaThinking: BetaThinkingDelta) {
                        betaThinking.validate()
                    }

                    override fun visitBetaSignature(betaSignature: BetaSignatureDelta) {
                        betaSignature.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Delta && betaText == other.betaText && betaInputJson == other.betaInputJson && betaCitations == other.betaCitations && betaThinking == other.betaThinking && betaSignature == other.betaSignature /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaText, betaInputJson, betaCitations, betaThinking, betaSignature) /* spotless:on */

        override fun toString(): String =
            when {
                betaText != null -> "Delta{betaText=$betaText}"
                betaInputJson != null -> "Delta{betaInputJson=$betaInputJson}"
                betaCitations != null -> "Delta{betaCitations=$betaCitations}"
                betaThinking != null -> "Delta{betaThinking=$betaThinking}"
                betaSignature != null -> "Delta{betaSignature=$betaSignature}"
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

            @JvmStatic
            fun ofBetaThinking(betaThinking: BetaThinkingDelta) = Delta(betaThinking = betaThinking)

            @JvmStatic
            fun ofBetaSignature(betaSignature: BetaSignatureDelta) =
                Delta(betaSignature = betaSignature)
        }

        /** An interface that defines how to map each variant of [Delta] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBetaText(betaText: BetaTextDelta): T

            fun visitBetaInputJson(betaInputJson: BetaInputJsonDelta): T

            fun visitBetaCitations(betaCitations: BetaCitationsDelta): T

            fun visitBetaThinking(betaThinking: BetaThinkingDelta): T

            fun visitBetaSignature(betaSignature: BetaSignatureDelta): T

            /**
             * Maps an unknown variant of [Delta] to a value of type [T].
             *
             * An instance of [Delta] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Delta: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Delta>(Delta::class) {

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
                    "thinking_delta" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaThinkingDelta>()) { it.validate() }
                            ?.let {
                                return Delta(betaThinking = it, _json = json)
                            }
                    }
                    "signature_delta" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaSignatureDelta>()) { it.validate() }
                            ?.let {
                                return Delta(betaSignature = it, _json = json)
                            }
                    }
                }

                return Delta(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Delta>(Delta::class) {

            override fun serialize(
                value: Delta,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.betaText != null -> generator.writeObject(value.betaText)
                    value.betaInputJson != null -> generator.writeObject(value.betaInputJson)
                    value.betaCitations != null -> generator.writeObject(value.betaCitations)
                    value.betaThinking != null -> generator.writeObject(value.betaThinking)
                    value.betaSignature != null -> generator.writeObject(value.betaSignature)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Delta")
                }
            }
        }
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
