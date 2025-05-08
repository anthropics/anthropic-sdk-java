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

@JsonDeserialize(using = BetaContentBlockSourceContent.Deserializer::class)
@JsonSerialize(using = BetaContentBlockSourceContent.Serializer::class)
class BetaContentBlockSourceContent
private constructor(
    private val text: BetaTextBlockParam? = null,
    private val image: BetaImageBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun text(): Optional<BetaTextBlockParam> = Optional.ofNullable(text)

    fun image(): Optional<BetaImageBlockParam> = Optional.ofNullable(image)

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun asText(): BetaTextBlockParam = text.getOrThrow("text")

    fun asImage(): BetaImageBlockParam = image.getOrThrow("image")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            text != null -> visitor.visitText(text)
            image != null -> visitor.visitImage(image)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaContentBlockSourceContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: BetaTextBlockParam) {
                    text.validate()
                }

                override fun visitImage(image: BetaImageBlockParam) {
                    image.validate()
                }
            }
        )
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitText(text: BetaTextBlockParam) = text.validity()

                override fun visitImage(image: BetaImageBlockParam) = image.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaContentBlockSourceContent && text == other.text && image == other.image /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, image) /* spotless:on */

    override fun toString(): String =
        when {
            text != null -> "BetaContentBlockSourceContent{text=$text}"
            image != null -> "BetaContentBlockSourceContent{image=$image}"
            _json != null -> "BetaContentBlockSourceContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlockSourceContent")
        }

    companion object {

        @JvmStatic fun ofText(text: BetaTextBlockParam) = BetaContentBlockSourceContent(text = text)

        @JvmStatic
        fun ofImage(image: BetaImageBlockParam) = BetaContentBlockSourceContent(image = image)
    }

    /**
     * An interface that defines how to map each variant of [BetaContentBlockSourceContent] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: BetaTextBlockParam): T

        fun visitImage(image: BetaImageBlockParam): T

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
                    return tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>())?.let {
                        BetaContentBlockSourceContent(text = it, _json = json)
                    } ?: BetaContentBlockSourceContent(_json = json)
                }
                "image" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaImageBlockParam>())?.let {
                        BetaContentBlockSourceContent(image = it, _json = json)
                    } ?: BetaContentBlockSourceContent(_json = json)
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
                value.text != null -> generator.writeObject(value.text)
                value.image != null -> generator.writeObject(value.image)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockSourceContent")
            }
        }
    }
}
