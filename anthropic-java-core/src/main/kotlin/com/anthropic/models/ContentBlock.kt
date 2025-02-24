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
    private val text: TextBlock? = null,
    private val toolUse: ToolUseBlock? = null,
    private val thinking: ThinkingBlock? = null,
    private val redactedThinking: RedactedThinkingBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): ContentBlockParam =
        accept(
            object : Visitor<ContentBlockParam> {
                override fun visitText(text: TextBlock): ContentBlockParam =
                    ContentBlockParam.ofText(text.toParam())

                override fun visitToolUse(toolUse: ToolUseBlock): ContentBlockParam =
                    ContentBlockParam.ofToolUse(toolUse.toParam())

                override fun visitThinking(thinking: ThinkingBlock): ContentBlockParam =
                    ContentBlockParam.ofThinking(thinking.toParam())

                override fun visitRedactedThinking(
                    redactedThinking: RedactedThinkingBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofRedactedThinking(redactedThinking.toParam())
            }
        )

    fun text(): Optional<TextBlock> = Optional.ofNullable(text)

    fun toolUse(): Optional<ToolUseBlock> = Optional.ofNullable(toolUse)

    fun thinking(): Optional<ThinkingBlock> = Optional.ofNullable(thinking)

    fun redactedThinking(): Optional<RedactedThinkingBlock> = Optional.ofNullable(redactedThinking)

    fun isText(): Boolean = text != null

    fun isToolUse(): Boolean = toolUse != null

    fun isThinking(): Boolean = thinking != null

    fun isRedactedThinking(): Boolean = redactedThinking != null

    fun asText(): TextBlock = text.getOrThrow("text")

    fun asToolUse(): ToolUseBlock = toolUse.getOrThrow("toolUse")

    fun asThinking(): ThinkingBlock = thinking.getOrThrow("thinking")

    fun asRedactedThinking(): RedactedThinkingBlock =
        redactedThinking.getOrThrow("redactedThinking")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            text != null -> visitor.visitText(text)
            toolUse != null -> visitor.visitToolUse(toolUse)
            thinking != null -> visitor.visitThinking(thinking)
            redactedThinking != null -> visitor.visitRedactedThinking(redactedThinking)
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
                override fun visitText(text: TextBlock) {
                    text.validate()
                }

                override fun visitToolUse(toolUse: ToolUseBlock) {
                    toolUse.validate()
                }

                override fun visitThinking(thinking: ThinkingBlock) {
                    thinking.validate()
                }

                override fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock) {
                    redactedThinking.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ContentBlock && text == other.text && toolUse == other.toolUse && thinking == other.thinking && redactedThinking == other.redactedThinking /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, toolUse, thinking, redactedThinking) /* spotless:on */

    override fun toString(): String =
        when {
            text != null -> "ContentBlock{text=$text}"
            toolUse != null -> "ContentBlock{toolUse=$toolUse}"
            thinking != null -> "ContentBlock{thinking=$thinking}"
            redactedThinking != null -> "ContentBlock{redactedThinking=$redactedThinking}"
            _json != null -> "ContentBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ContentBlock")
        }

    companion object {

        @JvmStatic fun ofText(text: TextBlock) = ContentBlock(text = text)

        @JvmStatic fun ofToolUse(toolUse: ToolUseBlock) = ContentBlock(toolUse = toolUse)

        @JvmStatic fun ofThinking(thinking: ThinkingBlock) = ContentBlock(thinking = thinking)

        @JvmStatic
        fun ofRedactedThinking(redactedThinking: RedactedThinkingBlock) =
            ContentBlock(redactedThinking = redactedThinking)
    }

    /**
     * An interface that defines how to map each variant of [ContentBlock] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: TextBlock): T

        fun visitToolUse(toolUse: ToolUseBlock): T

        fun visitThinking(thinking: ThinkingBlock): T

        fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock): T

        /**
         * Maps an unknown variant of [ContentBlock] to a value of type [T].
         *
         * An instance of [ContentBlock] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ContentBlock: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ContentBlock>(ContentBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ContentBlock {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<TextBlock>()) { it.validate() }
                        ?.let {
                            return ContentBlock(text = it, _json = json)
                        }
                }
                "tool_use" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolUseBlock>()) { it.validate() }
                        ?.let {
                            return ContentBlock(toolUse = it, _json = json)
                        }
                }
                "thinking" -> {
                    tryDeserialize(node, jacksonTypeRef<ThinkingBlock>()) { it.validate() }
                        ?.let {
                            return ContentBlock(thinking = it, _json = json)
                        }
                }
                "redacted_thinking" -> {
                    tryDeserialize(node, jacksonTypeRef<RedactedThinkingBlock>()) { it.validate() }
                        ?.let {
                            return ContentBlock(redactedThinking = it, _json = json)
                        }
                }
            }

            return ContentBlock(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<ContentBlock>(ContentBlock::class) {

        override fun serialize(
            value: ContentBlock,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.toolUse != null -> generator.writeObject(value.toolUse)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.redactedThinking != null -> generator.writeObject(value.redactedThinking)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ContentBlock")
            }
        }
    }
}
