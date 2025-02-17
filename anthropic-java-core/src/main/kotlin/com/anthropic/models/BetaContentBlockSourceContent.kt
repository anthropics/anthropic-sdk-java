// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

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

@JsonDeserialize(using = BetaContentBlockSourceContent.Deserializer::class)
@JsonSerialize(using = BetaContentBlockSourceContent.Serializer::class)
class BetaContentBlockSourceContent
private constructor(
    private val textBlockParam: BetaTextBlockParam? = null,
    private val imageBlockParam: BetaImageBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun textBlockParam(): Optional<BetaTextBlockParam> = Optional.ofNullable(textBlockParam)

    fun imageBlockParam(): Optional<BetaImageBlockParam> = Optional.ofNullable(imageBlockParam)

    fun isTextBlockParam(): Boolean = textBlockParam != null

    fun isImageBlockParam(): Boolean = imageBlockParam != null

    fun asTextBlockParam(): BetaTextBlockParam = textBlockParam.getOrThrow("textBlockParam")

    fun asImageBlockParam(): BetaImageBlockParam = imageBlockParam.getOrThrow("imageBlockParam")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            textBlockParam != null -> visitor.visitTextBlockParam(textBlockParam)
            imageBlockParam != null -> visitor.visitImageBlockParam(imageBlockParam)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaContentBlockSourceContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitTextBlockParam(textBlockParam: BetaTextBlockParam) {
                    textBlockParam.validate()
                }

                override fun visitImageBlockParam(imageBlockParam: BetaImageBlockParam) {
                    imageBlockParam.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaContentBlockSourceContent && textBlockParam == other.textBlockParam && imageBlockParam == other.imageBlockParam /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(textBlockParam, imageBlockParam) /* spotless:on */

    override fun toString(): String =
        when {
            textBlockParam != null ->
                "BetaContentBlockSourceContent{textBlockParam=$textBlockParam}"
            imageBlockParam != null ->
                "BetaContentBlockSourceContent{imageBlockParam=$imageBlockParam}"
            _json != null -> "BetaContentBlockSourceContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlockSourceContent")
        }

    companion object {

        @JvmStatic
        fun ofTextBlockParam(textBlockParam: BetaTextBlockParam) =
            BetaContentBlockSourceContent(textBlockParam = textBlockParam)

        @JvmStatic
        fun ofImageBlockParam(imageBlockParam: BetaImageBlockParam) =
            BetaContentBlockSourceContent(imageBlockParam = imageBlockParam)
    }

    /**
     * An interface that defines how to map each variant of [BetaContentBlockSourceContent] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        fun visitTextBlockParam(textBlockParam: BetaTextBlockParam): T

        fun visitImageBlockParam(imageBlockParam: BetaImageBlockParam): T

        /**
         * Maps an unknown variant of [BetaContentBlockSourceContent] to a value of type [T].
         *
         * An instance of [BetaContentBlockSourceContent] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlockSourceContent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaContentBlockSourceContent>(BetaContentBlockSourceContent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaContentBlockSourceContent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockSourceContent(textBlockParam = it, _json = json)
                        }
                }
                "image" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaImageBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockSourceContent(imageBlockParam = it, _json = json)
                        }
                }
            }

            return BetaContentBlockSourceContent(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaContentBlockSourceContent>(BetaContentBlockSourceContent::class) {

        override fun serialize(
            value: BetaContentBlockSourceContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.textBlockParam != null -> generator.writeObject(value.textBlockParam)
                value.imageBlockParam != null -> generator.writeObject(value.imageBlockParam)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockSourceContent")
            }
        }
    }
}
