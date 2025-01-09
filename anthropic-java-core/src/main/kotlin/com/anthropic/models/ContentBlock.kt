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

@JsonDeserialize(using = ContentBlock.Deserializer::class)
@JsonSerialize(using = ContentBlock.Serializer::class)
class ContentBlock
private constructor(
    private val textBlock: TextBlock? = null,
    private val toolUseBlock: ToolUseBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): ContentBlockParam =
        accept(
            object : Visitor<ContentBlockParam> {
                override fun visitTextBlock(textBlock: TextBlock): ContentBlockParam =
                    ContentBlockParam.ofTextBlockParam(textBlock.toParam())

                override fun visitToolUseBlock(toolUseBlock: ToolUseBlock): ContentBlockParam =
                    ContentBlockParam.ofToolUseBlockParam(toolUseBlock.toParam())
            }
        )

    fun textBlock(): Optional<TextBlock> = Optional.ofNullable(textBlock)

    fun toolUseBlock(): Optional<ToolUseBlock> = Optional.ofNullable(toolUseBlock)

    fun isTextBlock(): Boolean = textBlock != null

    fun isToolUseBlock(): Boolean = toolUseBlock != null

    fun asTextBlock(): TextBlock = textBlock.getOrThrow("textBlock")

    fun asToolUseBlock(): ToolUseBlock = toolUseBlock.getOrThrow("toolUseBlock")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            textBlock != null -> visitor.visitTextBlock(textBlock)
            toolUseBlock != null -> visitor.visitToolUseBlock(toolUseBlock)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): ContentBlock = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitTextBlock(textBlock: TextBlock) {
                    textBlock.validate()
                }

                override fun visitToolUseBlock(toolUseBlock: ToolUseBlock) {
                    toolUseBlock.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ContentBlock && textBlock == other.textBlock && toolUseBlock == other.toolUseBlock /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(textBlock, toolUseBlock) /* spotless:on */

    override fun toString(): String =
        when {
            textBlock != null -> "ContentBlock{textBlock=$textBlock}"
            toolUseBlock != null -> "ContentBlock{toolUseBlock=$toolUseBlock}"
            _json != null -> "ContentBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ContentBlock")
        }

    companion object {

        @JvmStatic fun ofTextBlock(textBlock: TextBlock) = ContentBlock(textBlock = textBlock)

        @JvmStatic
        fun ofToolUseBlock(toolUseBlock: ToolUseBlock) = ContentBlock(toolUseBlock = toolUseBlock)
    }

    interface Visitor<out T> {

        fun visitTextBlock(textBlock: TextBlock): T

        fun visitToolUseBlock(toolUseBlock: ToolUseBlock): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ContentBlock: $json")
        }
    }

    class Deserializer : BaseDeserializer<ContentBlock>(ContentBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ContentBlock {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<TextBlock>()) { it.validate() }
                        ?.let {
                            return ContentBlock(textBlock = it, _json = json)
                        }
                }
                "tool_use" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolUseBlock>()) { it.validate() }
                        ?.let {
                            return ContentBlock(toolUseBlock = it, _json = json)
                        }
                }
            }

            return ContentBlock(_json = json)
        }
    }

    class Serializer : BaseSerializer<ContentBlock>(ContentBlock::class) {

        override fun serialize(
            value: ContentBlock,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.textBlock != null -> generator.writeObject(value.textBlock)
                value.toolUseBlock != null -> generator.writeObject(value.toolUseBlock)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ContentBlock")
            }
        }
    }
}
