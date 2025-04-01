// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
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

@JsonDeserialize(using = RawContentBlockDelta.Deserializer::class)
@JsonSerialize(using = RawContentBlockDelta.Serializer::class)
class RawContentBlockDelta
private constructor(
    private val text: TextDelta? = null,
    private val inputJson: InputJsonDelta? = null,
    private val citations: CitationsDelta? = null,
    private val thinking: ThinkingDelta? = null,
    private val signature: SignatureDelta? = null,
    private val _json: JsonValue? = null,
) {

    fun text(): Optional<TextDelta> = Optional.ofNullable(text)

    fun inputJson(): Optional<InputJsonDelta> = Optional.ofNullable(inputJson)

    fun citations(): Optional<CitationsDelta> = Optional.ofNullable(citations)

    fun thinking(): Optional<ThinkingDelta> = Optional.ofNullable(thinking)

    fun signature(): Optional<SignatureDelta> = Optional.ofNullable(signature)

    fun isText(): Boolean = text != null

    fun isInputJson(): Boolean = inputJson != null

    fun isCitations(): Boolean = citations != null

    fun isThinking(): Boolean = thinking != null

    fun isSignature(): Boolean = signature != null

    fun asText(): TextDelta = text.getOrThrow("text")

    fun asInputJson(): InputJsonDelta = inputJson.getOrThrow("inputJson")

    fun asCitations(): CitationsDelta = citations.getOrThrow("citations")

    fun asThinking(): ThinkingDelta = thinking.getOrThrow("thinking")

    fun asSignature(): SignatureDelta = signature.getOrThrow("signature")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            text != null -> visitor.visitText(text)
            inputJson != null -> visitor.visitInputJson(inputJson)
            citations != null -> visitor.visitCitations(citations)
            thinking != null -> visitor.visitThinking(thinking)
            signature != null -> visitor.visitSignature(signature)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): RawContentBlockDelta = apply {
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

                override fun visitThinking(thinking: ThinkingDelta) {
                    thinking.validate()
                }

                override fun visitSignature(signature: SignatureDelta) {
                    signature.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is RawContentBlockDelta && text == other.text && inputJson == other.inputJson && citations == other.citations && thinking == other.thinking && signature == other.signature /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, inputJson, citations, thinking, signature) /* spotless:on */

    override fun toString(): String =
        when {
            text != null -> "RawContentBlockDelta{text=$text}"
            inputJson != null -> "RawContentBlockDelta{inputJson=$inputJson}"
            citations != null -> "RawContentBlockDelta{citations=$citations}"
            thinking != null -> "RawContentBlockDelta{thinking=$thinking}"
            signature != null -> "RawContentBlockDelta{signature=$signature}"
            _json != null -> "RawContentBlockDelta{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid RawContentBlockDelta")
        }

    companion object {

        @JvmStatic fun ofText(text: TextDelta) = RawContentBlockDelta(text = text)

        @JvmStatic
        fun ofInputJson(inputJson: InputJsonDelta) = RawContentBlockDelta(inputJson = inputJson)

        @JvmStatic
        fun ofCitations(citations: CitationsDelta) = RawContentBlockDelta(citations = citations)

        @JvmStatic
        fun ofThinking(thinking: ThinkingDelta) = RawContentBlockDelta(thinking = thinking)

        @JvmStatic
        fun ofSignature(signature: SignatureDelta) = RawContentBlockDelta(signature = signature)
    }

    /**
     * An interface that defines how to map each variant of [RawContentBlockDelta] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: TextDelta): T

        fun visitInputJson(inputJson: InputJsonDelta): T

        fun visitCitations(citations: CitationsDelta): T

        fun visitThinking(thinking: ThinkingDelta): T

        fun visitSignature(signature: SignatureDelta): T

        /**
         * Maps an unknown variant of [RawContentBlockDelta] to a value of type [T].
         *
         * An instance of [RawContentBlockDelta] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown RawContentBlockDelta: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<RawContentBlockDelta>(RawContentBlockDelta::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): RawContentBlockDelta {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text_delta" -> {
                    return RawContentBlockDelta(
                        text = deserialize(node, jacksonTypeRef<TextDelta>()),
                        _json = json,
                    )
                }
                "input_json_delta" -> {
                    return RawContentBlockDelta(
                        inputJson = deserialize(node, jacksonTypeRef<InputJsonDelta>()),
                        _json = json,
                    )
                }
                "citations_delta" -> {
                    return RawContentBlockDelta(
                        citations = deserialize(node, jacksonTypeRef<CitationsDelta>()),
                        _json = json,
                    )
                }
                "thinking_delta" -> {
                    return RawContentBlockDelta(
                        thinking = deserialize(node, jacksonTypeRef<ThinkingDelta>()),
                        _json = json,
                    )
                }
                "signature_delta" -> {
                    return RawContentBlockDelta(
                        signature = deserialize(node, jacksonTypeRef<SignatureDelta>()),
                        _json = json,
                    )
                }
            }

            return RawContentBlockDelta(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<RawContentBlockDelta>(RawContentBlockDelta::class) {

        override fun serialize(
            value: RawContentBlockDelta,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.inputJson != null -> generator.writeObject(value.inputJson)
                value.citations != null -> generator.writeObject(value.citations)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.signature != null -> generator.writeObject(value.signature)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid RawContentBlockDelta")
            }
        }
    }
}
