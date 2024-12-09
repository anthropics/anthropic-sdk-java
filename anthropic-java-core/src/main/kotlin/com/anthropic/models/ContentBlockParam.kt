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

@JsonDeserialize(using = ContentBlockParam.Deserializer::class)
@JsonSerialize(using = ContentBlockParam.Serializer::class)
class ContentBlockParam
private constructor(
    private val textBlockParam: TextBlockParam? = null,
    private val imageBlockParam: ImageBlockParam? = null,
    private val toolUseBlockParam: ToolUseBlockParam? = null,
    private val toolResultBlockParam: ToolResultBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    fun textBlockParam(): Optional<TextBlockParam> = Optional.ofNullable(textBlockParam)

    fun imageBlockParam(): Optional<ImageBlockParam> = Optional.ofNullable(imageBlockParam)

    fun toolUseBlockParam(): Optional<ToolUseBlockParam> = Optional.ofNullable(toolUseBlockParam)

    fun toolResultBlockParam(): Optional<ToolResultBlockParam> =
        Optional.ofNullable(toolResultBlockParam)

    fun isTextBlockParam(): Boolean = textBlockParam != null

    fun isImageBlockParam(): Boolean = imageBlockParam != null

    fun isToolUseBlockParam(): Boolean = toolUseBlockParam != null

    fun isToolResultBlockParam(): Boolean = toolResultBlockParam != null

    fun asTextBlockParam(): TextBlockParam = textBlockParam.getOrThrow("textBlockParam")

    fun asImageBlockParam(): ImageBlockParam = imageBlockParam.getOrThrow("imageBlockParam")

    fun asToolUseBlockParam(): ToolUseBlockParam = toolUseBlockParam.getOrThrow("toolUseBlockParam")

    fun asToolResultBlockParam(): ToolResultBlockParam =
        toolResultBlockParam.getOrThrow("toolResultBlockParam")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            textBlockParam != null -> visitor.visitTextBlockParam(textBlockParam)
            imageBlockParam != null -> visitor.visitImageBlockParam(imageBlockParam)
            toolUseBlockParam != null -> visitor.visitToolUseBlockParam(toolUseBlockParam)
            toolResultBlockParam != null -> visitor.visitToolResultBlockParam(toolResultBlockParam)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): ContentBlockParam = apply {
        if (!validated) {
            if (
                textBlockParam == null &&
                    imageBlockParam == null &&
                    toolUseBlockParam == null &&
                    toolResultBlockParam == null
            ) {
                throw AnthropicInvalidDataException("Unknown ContentBlockParam: $_json")
            }
            textBlockParam?.validate()
            imageBlockParam?.validate()
            toolUseBlockParam?.validate()
            toolResultBlockParam?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ContentBlockParam && textBlockParam == other.textBlockParam && imageBlockParam == other.imageBlockParam && toolUseBlockParam == other.toolUseBlockParam && toolResultBlockParam == other.toolResultBlockParam /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(textBlockParam, imageBlockParam, toolUseBlockParam, toolResultBlockParam) /* spotless:on */

    override fun toString(): String =
        when {
            textBlockParam != null -> "ContentBlockParam{textBlockParam=$textBlockParam}"
            imageBlockParam != null -> "ContentBlockParam{imageBlockParam=$imageBlockParam}"
            toolUseBlockParam != null -> "ContentBlockParam{toolUseBlockParam=$toolUseBlockParam}"
            toolResultBlockParam != null ->
                "ContentBlockParam{toolResultBlockParam=$toolResultBlockParam}"
            _json != null -> "ContentBlockParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ContentBlockParam")
        }

    companion object {

        @JvmStatic
        fun ofTextBlockParam(textBlockParam: TextBlockParam) =
            ContentBlockParam(textBlockParam = textBlockParam)

        @JvmStatic
        fun ofImageBlockParam(imageBlockParam: ImageBlockParam) =
            ContentBlockParam(imageBlockParam = imageBlockParam)

        @JvmStatic
        fun ofToolUseBlockParam(toolUseBlockParam: ToolUseBlockParam) =
            ContentBlockParam(toolUseBlockParam = toolUseBlockParam)

        @JvmStatic
        fun ofToolResultBlockParam(toolResultBlockParam: ToolResultBlockParam) =
            ContentBlockParam(toolResultBlockParam = toolResultBlockParam)
    }

    interface Visitor<out T> {

        fun visitTextBlockParam(textBlockParam: TextBlockParam): T

        fun visitImageBlockParam(imageBlockParam: ImageBlockParam): T

        fun visitToolUseBlockParam(toolUseBlockParam: ToolUseBlockParam): T

        fun visitToolResultBlockParam(toolResultBlockParam: ToolResultBlockParam): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ContentBlockParam: $json")
        }
    }

    class Deserializer : BaseDeserializer<ContentBlockParam>(ContentBlockParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ContentBlockParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<TextBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(textBlockParam = it, _json = json)
                        }
                }
                "image" -> {
                    tryDeserialize(node, jacksonTypeRef<ImageBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(imageBlockParam = it, _json = json)
                        }
                }
                "tool_use" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolUseBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(toolUseBlockParam = it, _json = json)
                        }
                }
                "tool_result" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolResultBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(toolResultBlockParam = it, _json = json)
                        }
                }
            }

            return ContentBlockParam(_json = json)
        }
    }

    class Serializer : BaseSerializer<ContentBlockParam>(ContentBlockParam::class) {

        override fun serialize(
            value: ContentBlockParam,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.textBlockParam != null -> generator.writeObject(value.textBlockParam)
                value.imageBlockParam != null -> generator.writeObject(value.imageBlockParam)
                value.toolUseBlockParam != null -> generator.writeObject(value.toolUseBlockParam)
                value.toolResultBlockParam != null ->
                    generator.writeObject(value.toolResultBlockParam)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ContentBlockParam")
            }
        }
    }
}
