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
class RawContentBlockDeltaEvent
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

    fun validate(): RawContentBlockDeltaEvent = apply {
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

    /** A builder for [RawContentBlockDeltaEvent]. */
    class Builder internal constructor() {

        private var delta: JsonField<Delta>? = null
        private var index: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("content_block_delta")
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

        fun delta(text: TextDelta) = delta(Delta.ofText(text))

        fun textDelta(text: String) = delta(TextDelta.builder().text(text).build())

        fun delta(inputJson: InputJsonDelta) = delta(Delta.ofInputJson(inputJson))

        fun inputJsonDelta(partialJson: String) =
            delta(InputJsonDelta.builder().partialJson(partialJson).build())

        fun delta(citations: CitationsDelta) = delta(Delta.ofCitations(citations))

        fun citationsDelta(citation: CitationsDelta.Citation) =
            delta(CitationsDelta.builder().citation(citation).build())

        fun citationsDelta(charLocation: CitationCharLocation) =
            citationsDelta(CitationsDelta.Citation.ofCharLocation(charLocation))

        fun citationsDelta(pageLocation: CitationPageLocation) =
            citationsDelta(CitationsDelta.Citation.ofPageLocation(pageLocation))

        fun citationsDelta(contentBlockLocation: CitationContentBlockLocation) =
            citationsDelta(CitationsDelta.Citation.ofContentBlockLocation(contentBlockLocation))

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

        fun build(): RawContentBlockDeltaEvent =
            RawContentBlockDeltaEvent(
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
        private val text: TextDelta? = null,
        private val inputJson: InputJsonDelta? = null,
        private val citations: CitationsDelta? = null,
        private val _json: JsonValue? = null,
    ) {

        fun text(): Optional<TextDelta> = Optional.ofNullable(text)

        fun inputJson(): Optional<InputJsonDelta> = Optional.ofNullable(inputJson)

        fun citations(): Optional<CitationsDelta> = Optional.ofNullable(citations)

        fun isText(): Boolean = text != null

        fun isInputJson(): Boolean = inputJson != null

        fun isCitations(): Boolean = citations != null

        fun asText(): TextDelta = text.getOrThrow("text")

        fun asInputJson(): InputJsonDelta = inputJson.getOrThrow("inputJson")

        fun asCitations(): CitationsDelta = citations.getOrThrow("citations")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                text != null -> visitor.visitText(text)
                inputJson != null -> visitor.visitInputJson(inputJson)
                citations != null -> visitor.visitCitations(citations)
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
                    override fun visitText(text: TextDelta) {
                        text.validate()
                    }

                    override fun visitInputJson(inputJson: InputJsonDelta) {
                        inputJson.validate()
                    }

                    override fun visitCitations(citations: CitationsDelta) {
                        citations.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Delta && text == other.text && inputJson == other.inputJson && citations == other.citations /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, inputJson, citations) /* spotless:on */

        override fun toString(): String =
            when {
                text != null -> "Delta{text=$text}"
                inputJson != null -> "Delta{inputJson=$inputJson}"
                citations != null -> "Delta{citations=$citations}"
                _json != null -> "Delta{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Delta")
            }

        companion object {

            @JvmStatic fun ofText(text: TextDelta) = Delta(text = text)

            @JvmStatic fun ofInputJson(inputJson: InputJsonDelta) = Delta(inputJson = inputJson)

            @JvmStatic fun ofCitations(citations: CitationsDelta) = Delta(citations = citations)
        }

        /** An interface that defines how to map each variant of [Delta] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitText(text: TextDelta): T

            fun visitInputJson(inputJson: InputJsonDelta): T

            fun visitCitations(citations: CitationsDelta): T

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
                        tryDeserialize(node, jacksonTypeRef<TextDelta>()) { it.validate() }
                            ?.let {
                                return Delta(text = it, _json = json)
                            }
                    }
                    "input_json_delta" -> {
                        tryDeserialize(node, jacksonTypeRef<InputJsonDelta>()) { it.validate() }
                            ?.let {
                                return Delta(inputJson = it, _json = json)
                            }
                    }
                    "citations_delta" -> {
                        tryDeserialize(node, jacksonTypeRef<CitationsDelta>()) { it.validate() }
                            ?.let {
                                return Delta(citations = it, _json = json)
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
                    value.text != null -> generator.writeObject(value.text)
                    value.inputJson != null -> generator.writeObject(value.inputJson)
                    value.citations != null -> generator.writeObject(value.citations)
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

        return /* spotless:off */ other is RawContentBlockDeltaEvent && delta == other.delta && index == other.index && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(delta, index, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RawContentBlockDeltaEvent{delta=$delta, index=$index, type=$type, additionalProperties=$additionalProperties}"
}
