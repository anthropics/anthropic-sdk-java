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

@JsonDeserialize(using = BetaContentBlockParam.Deserializer::class)
@JsonSerialize(using = BetaContentBlockParam.Serializer::class)
class BetaContentBlockParam
private constructor(
    private val text: BetaTextBlockParam? = null,
    private val image: BetaImageBlockParam? = null,
    private val toolUse: BetaToolUseBlockParam? = null,
    private val toolResult: BetaToolResultBlockParam? = null,
    private val base64PdfBlock: BetaBase64PdfBlock? = null,
    private val thinking: BetaThinkingBlockParam? = null,
    private val redactedThinking: BetaRedactedThinkingBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun text(): Optional<BetaTextBlockParam> = Optional.ofNullable(text)

    fun image(): Optional<BetaImageBlockParam> = Optional.ofNullable(image)

    fun toolUse(): Optional<BetaToolUseBlockParam> = Optional.ofNullable(toolUse)

    fun toolResult(): Optional<BetaToolResultBlockParam> = Optional.ofNullable(toolResult)

    fun base64PdfBlock(): Optional<BetaBase64PdfBlock> = Optional.ofNullable(base64PdfBlock)

    fun thinking(): Optional<BetaThinkingBlockParam> = Optional.ofNullable(thinking)

    fun redactedThinking(): Optional<BetaRedactedThinkingBlockParam> =
        Optional.ofNullable(redactedThinking)

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun isToolUse(): Boolean = toolUse != null

    fun isToolResult(): Boolean = toolResult != null

    fun isBase64PdfBlock(): Boolean = base64PdfBlock != null

    fun isThinking(): Boolean = thinking != null

    fun isRedactedThinking(): Boolean = redactedThinking != null

    fun asText(): BetaTextBlockParam = text.getOrThrow("text")

    fun asImage(): BetaImageBlockParam = image.getOrThrow("image")

    fun asToolUse(): BetaToolUseBlockParam = toolUse.getOrThrow("toolUse")

    fun asToolResult(): BetaToolResultBlockParam = toolResult.getOrThrow("toolResult")

    fun asBase64PdfBlock(): BetaBase64PdfBlock = base64PdfBlock.getOrThrow("base64PdfBlock")

    fun asThinking(): BetaThinkingBlockParam = thinking.getOrThrow("thinking")

    fun asRedactedThinking(): BetaRedactedThinkingBlockParam =
        redactedThinking.getOrThrow("redactedThinking")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            text != null -> visitor.visitText(text)
            image != null -> visitor.visitImage(image)
            toolUse != null -> visitor.visitToolUse(toolUse)
            toolResult != null -> visitor.visitToolResult(toolResult)
            base64PdfBlock != null -> visitor.visitBase64PdfBlock(base64PdfBlock)
            thinking != null -> visitor.visitThinking(thinking)
            redactedThinking != null -> visitor.visitRedactedThinking(redactedThinking)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaContentBlockParam = apply {
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

                override fun visitToolUse(toolUse: BetaToolUseBlockParam) {
                    toolUse.validate()
                }

                override fun visitToolResult(toolResult: BetaToolResultBlockParam) {
                    toolResult.validate()
                }

                override fun visitBase64PdfBlock(base64PdfBlock: BetaBase64PdfBlock) {
                    base64PdfBlock.validate()
                }

                override fun visitThinking(thinking: BetaThinkingBlockParam) {
                    thinking.validate()
                }

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ) {
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

        return /* spotless:off */ other is BetaContentBlockParam && text == other.text && image == other.image && toolUse == other.toolUse && toolResult == other.toolResult && base64PdfBlock == other.base64PdfBlock && thinking == other.thinking && redactedThinking == other.redactedThinking /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, image, toolUse, toolResult, base64PdfBlock, thinking, redactedThinking) /* spotless:on */

    override fun toString(): String =
        when {
            text != null -> "BetaContentBlockParam{text=$text}"
            image != null -> "BetaContentBlockParam{image=$image}"
            toolUse != null -> "BetaContentBlockParam{toolUse=$toolUse}"
            toolResult != null -> "BetaContentBlockParam{toolResult=$toolResult}"
            base64PdfBlock != null -> "BetaContentBlockParam{base64PdfBlock=$base64PdfBlock}"
            thinking != null -> "BetaContentBlockParam{thinking=$thinking}"
            redactedThinking != null -> "BetaContentBlockParam{redactedThinking=$redactedThinking}"
            _json != null -> "BetaContentBlockParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlockParam")
        }

    companion object {

        @JvmStatic fun ofText(text: BetaTextBlockParam) = BetaContentBlockParam(text = text)

        @JvmStatic fun ofImage(image: BetaImageBlockParam) = BetaContentBlockParam(image = image)

        @JvmStatic
        fun ofToolUse(toolUse: BetaToolUseBlockParam) = BetaContentBlockParam(toolUse = toolUse)

        @JvmStatic
        fun ofToolResult(toolResult: BetaToolResultBlockParam) =
            BetaContentBlockParam(toolResult = toolResult)

        @JvmStatic
        fun ofBase64PdfBlock(base64PdfBlock: BetaBase64PdfBlock) =
            BetaContentBlockParam(base64PdfBlock = base64PdfBlock)

        @JvmStatic
        fun ofThinking(thinking: BetaThinkingBlockParam) =
            BetaContentBlockParam(thinking = thinking)

        @JvmStatic
        fun ofRedactedThinking(redactedThinking: BetaRedactedThinkingBlockParam) =
            BetaContentBlockParam(redactedThinking = redactedThinking)
    }

    /**
     * An interface that defines how to map each variant of [BetaContentBlockParam] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: BetaTextBlockParam): T

        fun visitImage(image: BetaImageBlockParam): T

        fun visitToolUse(toolUse: BetaToolUseBlockParam): T

        fun visitToolResult(toolResult: BetaToolResultBlockParam): T

        fun visitBase64PdfBlock(base64PdfBlock: BetaBase64PdfBlock): T

        fun visitThinking(thinking: BetaThinkingBlockParam): T

        fun visitRedactedThinking(redactedThinking: BetaRedactedThinkingBlockParam): T

        /**
         * Maps an unknown variant of [BetaContentBlockParam] to a value of type [T].
         *
         * An instance of [BetaContentBlockParam] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlockParam: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaContentBlockParam>(BetaContentBlockParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaContentBlockParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(text = it, _json = json)
                        }
                }
                "image" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaImageBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(image = it, _json = json)
                        }
                }
                "tool_use" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolUseBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(toolUse = it, _json = json)
                        }
                }
                "tool_result" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolResultBlockParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaContentBlockParam(toolResult = it, _json = json)
                        }
                }
                "document" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaBase64PdfBlock>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(base64PdfBlock = it, _json = json)
                        }
                }
                "thinking" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaThinkingBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(thinking = it, _json = json)
                        }
                }
                "redacted_thinking" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRedactedThinkingBlockParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaContentBlockParam(redactedThinking = it, _json = json)
                        }
                }
            }

            return BetaContentBlockParam(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaContentBlockParam>(BetaContentBlockParam::class) {

        override fun serialize(
            value: BetaContentBlockParam,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.image != null -> generator.writeObject(value.image)
                value.toolUse != null -> generator.writeObject(value.toolUse)
                value.toolResult != null -> generator.writeObject(value.toolResult)
                value.base64PdfBlock != null -> generator.writeObject(value.base64PdfBlock)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.redactedThinking != null -> generator.writeObject(value.redactedThinking)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockParam")
            }
        }
    }
}
