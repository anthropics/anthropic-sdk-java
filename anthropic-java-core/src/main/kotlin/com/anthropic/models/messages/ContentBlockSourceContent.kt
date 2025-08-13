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

@JsonDeserialize(using = ContentBlockSourceContent.Deserializer::class)
@JsonSerialize(using = ContentBlockSourceContent.Serializer::class)
class ContentBlockSourceContent
private constructor(
    private val text: TextBlockParam? = null,
    private val image: ImageBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun text(): Optional<TextBlockParam> = Optional.ofNullable(text)

    fun image(): Optional<ImageBlockParam> = Optional.ofNullable(image)

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun asText(): TextBlockParam = text.getOrThrow("text")

    fun asImage(): ImageBlockParam = image.getOrThrow("image")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            text != null -> visitor.visitText(text)
            image != null -> visitor.visitImage(image)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): ContentBlockSourceContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: TextBlockParam) {
                    text.validate()
                }

                override fun visitImage(image: ImageBlockParam) {
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
                override fun visitText(text: TextBlockParam) = text.validity()

                override fun visitImage(image: ImageBlockParam) = image.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ContentBlockSourceContent && text == other.text && image == other.image
    }

    override fun hashCode(): Int = Objects.hash(text, image)

    override fun toString(): String =
        when {
            text != null -> "ContentBlockSourceContent{text=$text}"
            image != null -> "ContentBlockSourceContent{image=$image}"
            _json != null -> "ContentBlockSourceContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ContentBlockSourceContent")
        }

    companion object {

        @JvmStatic fun ofText(text: TextBlockParam) = ContentBlockSourceContent(text = text)

        @JvmStatic fun ofImage(image: ImageBlockParam) = ContentBlockSourceContent(image = image)
    }

    /**
     * An interface that defines how to map each variant of [ContentBlockSourceContent] to a value
     * of type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: TextBlockParam): T

        fun visitImage(image: ImageBlockParam): T

        /**
         * Maps an unknown variant of [ContentBlockSourceContent] to a value of type [T].
         *
         * An instance of [ContentBlockSourceContent] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ContentBlockSourceContent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<ContentBlockSourceContent>(ContentBlockSourceContent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ContentBlockSourceContent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    return tryDeserialize(node, jacksonTypeRef<TextBlockParam>())?.let {
                        ContentBlockSourceContent(text = it, _json = json)
                    } ?: ContentBlockSourceContent(_json = json)
                }
                "image" -> {
                    return tryDeserialize(node, jacksonTypeRef<ImageBlockParam>())?.let {
                        ContentBlockSourceContent(image = it, _json = json)
                    } ?: ContentBlockSourceContent(_json = json)
                }
            }

            return ContentBlockSourceContent(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<ContentBlockSourceContent>(ContentBlockSourceContent::class) {

        override fun serialize(
            value: ContentBlockSourceContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.image != null -> generator.writeObject(value.image)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ContentBlockSourceContent")
            }
        }
    }
}
