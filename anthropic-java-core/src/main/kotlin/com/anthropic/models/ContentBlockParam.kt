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
    private val text: TextBlockParam? = null,
    private val image: ImageBlockParam? = null,
    private val toolUse: ToolUseBlockParam? = null,
    private val toolResult: ToolResultBlockParam? = null,
    private val document: DocumentBlockParam? = null,
    private val thinking: ThinkingBlockParam? = null,
    private val redactedThinking: RedactedThinkingBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun text(): Optional<TextBlockParam> = Optional.ofNullable(text)

    fun image(): Optional<ImageBlockParam> = Optional.ofNullable(image)

    fun toolUse(): Optional<ToolUseBlockParam> = Optional.ofNullable(toolUse)

    fun toolResult(): Optional<ToolResultBlockParam> = Optional.ofNullable(toolResult)

    fun document(): Optional<DocumentBlockParam> = Optional.ofNullable(document)

    fun thinking(): Optional<ThinkingBlockParam> = Optional.ofNullable(thinking)

    fun redactedThinking(): Optional<RedactedThinkingBlockParam> =
        Optional.ofNullable(redactedThinking)

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun isToolUse(): Boolean = toolUse != null

    fun isToolResult(): Boolean = toolResult != null

    fun isDocument(): Boolean = document != null

    fun isThinking(): Boolean = thinking != null

    fun isRedactedThinking(): Boolean = redactedThinking != null

    fun asText(): TextBlockParam = text.getOrThrow("text")

    fun asImage(): ImageBlockParam = image.getOrThrow("image")

    fun asToolUse(): ToolUseBlockParam = toolUse.getOrThrow("toolUse")

    fun asToolResult(): ToolResultBlockParam = toolResult.getOrThrow("toolResult")

    fun asDocument(): DocumentBlockParam = document.getOrThrow("document")

    fun asThinking(): ThinkingBlockParam = thinking.getOrThrow("thinking")

    fun asRedactedThinking(): RedactedThinkingBlockParam =
        redactedThinking.getOrThrow("redactedThinking")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            text != null -> visitor.visitText(text)
            image != null -> visitor.visitImage(image)
            toolUse != null -> visitor.visitToolUse(toolUse)
            toolResult != null -> visitor.visitToolResult(toolResult)
            document != null -> visitor.visitDocument(document)
            thinking != null -> visitor.visitThinking(thinking)
            redactedThinking != null -> visitor.visitRedactedThinking(redactedThinking)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): ContentBlockParam = apply {
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

                override fun visitToolUse(toolUse: ToolUseBlockParam) {
                    toolUse.validate()
                }

                override fun visitToolResult(toolResult: ToolResultBlockParam) {
                    toolResult.validate()
                }

                override fun visitDocument(document: DocumentBlockParam) {
                    document.validate()
                }

                override fun visitThinking(thinking: ThinkingBlockParam) {
                    thinking.validate()
                }

                override fun visitRedactedThinking(redactedThinking: RedactedThinkingBlockParam) {
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

        return /* spotless:off */ other is ContentBlockParam && text == other.text && image == other.image && toolUse == other.toolUse && toolResult == other.toolResult && document == other.document && thinking == other.thinking && redactedThinking == other.redactedThinking /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, image, toolUse, toolResult, document, thinking, redactedThinking) /* spotless:on */

    override fun toString(): String =
        when {
            text != null -> "ContentBlockParam{text=$text}"
            image != null -> "ContentBlockParam{image=$image}"
            toolUse != null -> "ContentBlockParam{toolUse=$toolUse}"
            toolResult != null -> "ContentBlockParam{toolResult=$toolResult}"
            document != null -> "ContentBlockParam{document=$document}"
            thinking != null -> "ContentBlockParam{thinking=$thinking}"
            redactedThinking != null -> "ContentBlockParam{redactedThinking=$redactedThinking}"
            _json != null -> "ContentBlockParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ContentBlockParam")
        }

    companion object {

        @JvmStatic fun ofText(text: TextBlockParam) = ContentBlockParam(text = text)

        @JvmStatic fun ofImage(image: ImageBlockParam) = ContentBlockParam(image = image)

        @JvmStatic fun ofToolUse(toolUse: ToolUseBlockParam) = ContentBlockParam(toolUse = toolUse)

        @JvmStatic
        fun ofToolResult(toolResult: ToolResultBlockParam) =
            ContentBlockParam(toolResult = toolResult)

        @JvmStatic
        fun ofDocument(document: DocumentBlockParam) = ContentBlockParam(document = document)

        @JvmStatic
        fun ofThinking(thinking: ThinkingBlockParam) = ContentBlockParam(thinking = thinking)

        @JvmStatic
        fun ofRedactedThinking(redactedThinking: RedactedThinkingBlockParam) =
            ContentBlockParam(redactedThinking = redactedThinking)
    }

    /**
     * An interface that defines how to map each variant of [ContentBlockParam] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitText(text: TextBlockParam): T

        fun visitImage(image: ImageBlockParam): T

        fun visitToolUse(toolUse: ToolUseBlockParam): T

        fun visitToolResult(toolResult: ToolResultBlockParam): T

        fun visitDocument(document: DocumentBlockParam): T

        fun visitThinking(thinking: ThinkingBlockParam): T

        fun visitRedactedThinking(redactedThinking: RedactedThinkingBlockParam): T

        /**
         * Maps an unknown variant of [ContentBlockParam] to a value of type [T].
         *
         * An instance of [ContentBlockParam] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ContentBlockParam: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ContentBlockParam>(ContentBlockParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ContentBlockParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<TextBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(text = it, _json = json)
                        }
                }
                "image" -> {
                    tryDeserialize(node, jacksonTypeRef<ImageBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(image = it, _json = json)
                        }
                }
                "tool_use" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolUseBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(toolUse = it, _json = json)
                        }
                }
                "tool_result" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolResultBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(toolResult = it, _json = json)
                        }
                }
                "document" -> {
                    tryDeserialize(node, jacksonTypeRef<DocumentBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(document = it, _json = json)
                        }
                }
                "thinking" -> {
                    tryDeserialize(node, jacksonTypeRef<ThinkingBlockParam>()) { it.validate() }
                        ?.let {
                            return ContentBlockParam(thinking = it, _json = json)
                        }
                }
                "redacted_thinking" -> {
                    tryDeserialize(node, jacksonTypeRef<RedactedThinkingBlockParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return ContentBlockParam(redactedThinking = it, _json = json)
                        }
                }
            }

            return ContentBlockParam(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<ContentBlockParam>(ContentBlockParam::class) {

        override fun serialize(
            value: ContentBlockParam,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.image != null -> generator.writeObject(value.image)
                value.toolUse != null -> generator.writeObject(value.toolUse)
                value.toolResult != null -> generator.writeObject(value.toolResult)
                value.document != null -> generator.writeObject(value.document)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.redactedThinking != null -> generator.writeObject(value.redactedThinking)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ContentBlockParam")
            }
        }
    }
}
