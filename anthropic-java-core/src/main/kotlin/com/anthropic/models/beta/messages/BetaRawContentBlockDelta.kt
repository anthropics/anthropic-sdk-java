// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

@JsonDeserialize(using = BetaRawContentBlockDelta.Deserializer::class)
@JsonSerialize(using = BetaRawContentBlockDelta.Serializer::class)
class BetaRawContentBlockDelta
private constructor(
    private val text: BetaTextDelta? = null,
    private val inputJson: BetaInputJsonDelta? = null,
    private val citations: BetaCitationsDelta? = null,
    private val thinking: BetaThinkingDelta? = null,
    private val signature: BetaSignatureDelta? = null,
    private val _json: JsonValue? = null,
) {

    fun text(): Optional<BetaTextDelta> = Optional.ofNullable(text)

    fun inputJson(): Optional<BetaInputJsonDelta> = Optional.ofNullable(inputJson)

    fun citations(): Optional<BetaCitationsDelta> = Optional.ofNullable(citations)

    fun thinking(): Optional<BetaThinkingDelta> = Optional.ofNullable(thinking)

    fun signature(): Optional<BetaSignatureDelta> = Optional.ofNullable(signature)

    fun isText(): Boolean = text != null

    fun isInputJson(): Boolean = inputJson != null

    fun isCitations(): Boolean = citations != null

    fun isThinking(): Boolean = thinking != null

    fun isSignature(): Boolean = signature != null

    fun asText(): BetaTextDelta = text.getOrThrow("text")

    fun asInputJson(): BetaInputJsonDelta = inputJson.getOrThrow("inputJson")

    fun asCitations(): BetaCitationsDelta = citations.getOrThrow("citations")

    fun asThinking(): BetaThinkingDelta = thinking.getOrThrow("thinking")

    fun asSignature(): BetaSignatureDelta = signature.getOrThrow("signature")

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

    fun validate(): BetaRawContentBlockDelta = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: BetaTextDelta) {
                    text.validate()
                }

                override fun visitInputJson(inputJson: BetaInputJsonDelta) {
                    inputJson.validate()
                }

                override fun visitCitations(citations: BetaCitationsDelta) {
                    citations.validate()
                }

                override fun visitThinking(thinking: BetaThinkingDelta) {
                    thinking.validate()
                }

                override fun visitSignature(signature: BetaSignatureDelta) {
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

        return /* spotless:off */ other is BetaRawContentBlockDelta && text == other.text && inputJson == other.inputJson && citations == other.citations && thinking == other.thinking && signature == other.signature /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, inputJson, citations, thinking, signature) /* spotless:on */

    override fun toString(): String =
        when {
            text != null -> "BetaRawContentBlockDelta{text=$text}"
            inputJson != null -> "BetaRawContentBlockDelta{inputJson=$inputJson}"
            citations != null -> "BetaRawContentBlockDelta{citations=$citations}"
            thinking != null -> "BetaRawContentBlockDelta{thinking=$thinking}"
            signature != null -> "BetaRawContentBlockDelta{signature=$signature}"
            _json != null -> "BetaRawContentBlockDelta{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaRawContentBlockDelta")
        }

    companion object {

        @JvmStatic fun ofText(text: BetaTextDelta) = BetaRawContentBlockDelta(text = text)

        @JvmStatic
        fun ofInputJson(inputJson: BetaInputJsonDelta) =
            BetaRawContentBlockDelta(inputJson = inputJson)

        @JvmStatic
        fun ofCitations(citations: BetaCitationsDelta) =
            BetaRawContentBlockDelta(citations = citations)

        @JvmStatic
        fun ofThinking(thinking: BetaThinkingDelta) = BetaRawContentBlockDelta(thinking = thinking)

        @JvmStatic
        fun ofSignature(signature: BetaSignatureDelta) =
            BetaRawContentBlockDelta(signature = signature)
    }

    /**
     * An interface that defines how to map each variant of [BetaRawContentBlockDelta] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: BetaTextDelta): T

        fun visitInputJson(inputJson: BetaInputJsonDelta): T

        fun visitCitations(citations: BetaCitationsDelta): T

        fun visitThinking(thinking: BetaThinkingDelta): T

        fun visitSignature(signature: BetaSignatureDelta): T

        /**
         * Maps an unknown variant of [BetaRawContentBlockDelta] to a value of type [T].
         *
         * An instance of [BetaRawContentBlockDelta] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaRawContentBlockDelta: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaRawContentBlockDelta>(BetaRawContentBlockDelta::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaRawContentBlockDelta {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text_delta" -> {
                    return BetaRawContentBlockDelta(
                        text = deserialize(node, jacksonTypeRef<BetaTextDelta>()),
                        _json = json,
                    )
                }
                "input_json_delta" -> {
                    return BetaRawContentBlockDelta(
                        inputJson = deserialize(node, jacksonTypeRef<BetaInputJsonDelta>()),
                        _json = json,
                    )
                }
                "citations_delta" -> {
                    return BetaRawContentBlockDelta(
                        citations = deserialize(node, jacksonTypeRef<BetaCitationsDelta>()),
                        _json = json,
                    )
                }
                "thinking_delta" -> {
                    return BetaRawContentBlockDelta(
                        thinking = deserialize(node, jacksonTypeRef<BetaThinkingDelta>()),
                        _json = json,
                    )
                }
                "signature_delta" -> {
                    return BetaRawContentBlockDelta(
                        signature = deserialize(node, jacksonTypeRef<BetaSignatureDelta>()),
                        _json = json,
                    )
                }
            }

            return BetaRawContentBlockDelta(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaRawContentBlockDelta>(BetaRawContentBlockDelta::class) {

        override fun serialize(
            value: BetaRawContentBlockDelta,
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
                else -> throw IllegalStateException("Invalid BetaRawContentBlockDelta")
            }
        }
    }
}
