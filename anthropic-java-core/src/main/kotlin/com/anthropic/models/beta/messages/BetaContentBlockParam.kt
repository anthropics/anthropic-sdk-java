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

@JsonDeserialize(using = BetaContentBlockParam.Deserializer::class)
@JsonSerialize(using = BetaContentBlockParam.Serializer::class)
class BetaContentBlockParam
private constructor(
    private val text: BetaTextBlockParam? = null,
    private val image: BetaImageBlockParam? = null,
    private val toolUse: BetaToolUseBlockParam? = null,
    private val serverToolUse: BetaServerToolUseBlockParam? = null,
    private val webSearchToolResult: BetaWebSearchToolResultBlockParam? = null,
    private val toolResult: BetaToolResultBlockParam? = null,
    private val document: BetaBase64PdfBlock? = null,
    private val thinking: BetaThinkingBlockParam? = null,
    private val redactedThinking: BetaRedactedThinkingBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun text(): Optional<BetaTextBlockParam> = Optional.ofNullable(text)

    fun image(): Optional<BetaImageBlockParam> = Optional.ofNullable(image)

    fun toolUse(): Optional<BetaToolUseBlockParam> = Optional.ofNullable(toolUse)

    fun serverToolUse(): Optional<BetaServerToolUseBlockParam> = Optional.ofNullable(serverToolUse)

    fun webSearchToolResult(): Optional<BetaWebSearchToolResultBlockParam> =
        Optional.ofNullable(webSearchToolResult)

    fun toolResult(): Optional<BetaToolResultBlockParam> = Optional.ofNullable(toolResult)

    fun document(): Optional<BetaBase64PdfBlock> = Optional.ofNullable(document)

    fun thinking(): Optional<BetaThinkingBlockParam> = Optional.ofNullable(thinking)

    fun redactedThinking(): Optional<BetaRedactedThinkingBlockParam> =
        Optional.ofNullable(redactedThinking)

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun isToolUse(): Boolean = toolUse != null

    fun isServerToolUse(): Boolean = serverToolUse != null

    fun isWebSearchToolResult(): Boolean = webSearchToolResult != null

    fun isToolResult(): Boolean = toolResult != null

    fun isDocument(): Boolean = document != null

    fun isThinking(): Boolean = thinking != null

    fun isRedactedThinking(): Boolean = redactedThinking != null

    fun asText(): BetaTextBlockParam = text.getOrThrow("text")

    fun asImage(): BetaImageBlockParam = image.getOrThrow("image")

    fun asToolUse(): BetaToolUseBlockParam = toolUse.getOrThrow("toolUse")

    fun asServerToolUse(): BetaServerToolUseBlockParam = serverToolUse.getOrThrow("serverToolUse")

    fun asWebSearchToolResult(): BetaWebSearchToolResultBlockParam =
        webSearchToolResult.getOrThrow("webSearchToolResult")

    fun asToolResult(): BetaToolResultBlockParam = toolResult.getOrThrow("toolResult")

    fun asDocument(): BetaBase64PdfBlock = document.getOrThrow("document")

    fun asThinking(): BetaThinkingBlockParam = thinking.getOrThrow("thinking")

    fun asRedactedThinking(): BetaRedactedThinkingBlockParam =
        redactedThinking.getOrThrow("redactedThinking")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            text != null -> visitor.visitText(text)
            image != null -> visitor.visitImage(image)
            toolUse != null -> visitor.visitToolUse(toolUse)
            serverToolUse != null -> visitor.visitServerToolUse(serverToolUse)
            webSearchToolResult != null -> visitor.visitWebSearchToolResult(webSearchToolResult)
            toolResult != null -> visitor.visitToolResult(toolResult)
            document != null -> visitor.visitDocument(document)
            thinking != null -> visitor.visitThinking(thinking)
            redactedThinking != null -> visitor.visitRedactedThinking(redactedThinking)
            else -> visitor.unknown(_json)
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

                override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlockParam) {
                    serverToolUse.validate()
                }

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ) {
                    webSearchToolResult.validate()
                }

                override fun visitToolResult(toolResult: BetaToolResultBlockParam) {
                    toolResult.validate()
                }

                override fun visitDocument(document: BetaBase64PdfBlock) {
                    document.validate()
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

                override fun visitToolUse(toolUse: BetaToolUseBlockParam) = toolUse.validity()

                override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlockParam) =
                    serverToolUse.validity()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ) = webSearchToolResult.validity()

                override fun visitToolResult(toolResult: BetaToolResultBlockParam) =
                    toolResult.validity()

                override fun visitDocument(document: BetaBase64PdfBlock) = document.validity()

                override fun visitThinking(thinking: BetaThinkingBlockParam) = thinking.validity()

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ) = redactedThinking.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaContentBlockParam && text == other.text && image == other.image && toolUse == other.toolUse && serverToolUse == other.serverToolUse && webSearchToolResult == other.webSearchToolResult && toolResult == other.toolResult && document == other.document && thinking == other.thinking && redactedThinking == other.redactedThinking /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, image, toolUse, serverToolUse, webSearchToolResult, toolResult, document, thinking, redactedThinking) /* spotless:on */

    override fun toString(): String =
        when {
            text != null -> "BetaContentBlockParam{text=$text}"
            image != null -> "BetaContentBlockParam{image=$image}"
            toolUse != null -> "BetaContentBlockParam{toolUse=$toolUse}"
            serverToolUse != null -> "BetaContentBlockParam{serverToolUse=$serverToolUse}"
            webSearchToolResult != null ->
                "BetaContentBlockParam{webSearchToolResult=$webSearchToolResult}"
            toolResult != null -> "BetaContentBlockParam{toolResult=$toolResult}"
            document != null -> "BetaContentBlockParam{document=$document}"
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
        fun ofServerToolUse(serverToolUse: BetaServerToolUseBlockParam) =
            BetaContentBlockParam(serverToolUse = serverToolUse)

        @JvmStatic
        fun ofWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlockParam) =
            BetaContentBlockParam(webSearchToolResult = webSearchToolResult)

        @JvmStatic
        fun ofToolResult(toolResult: BetaToolResultBlockParam) =
            BetaContentBlockParam(toolResult = toolResult)

        @JvmStatic
        fun ofDocument(document: BetaBase64PdfBlock) = BetaContentBlockParam(document = document)

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

        fun visitServerToolUse(serverToolUse: BetaServerToolUseBlockParam): T

        fun visitWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlockParam): T

        fun visitToolResult(toolResult: BetaToolResultBlockParam): T

        fun visitDocument(document: BetaBase64PdfBlock): T

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
                    return tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>())?.let {
                        BetaContentBlockParam(text = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "image" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaImageBlockParam>())?.let {
                        BetaContentBlockParam(image = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolUseBlockParam>())?.let {
                        BetaContentBlockParam(toolUse = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "server_tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaServerToolUseBlockParam>())
                        ?.let { BetaContentBlockParam(serverToolUse = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "web_search_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebSearchToolResultBlockParam>())
                        ?.let { BetaContentBlockParam(webSearchToolResult = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolResultBlockParam>())?.let {
                        BetaContentBlockParam(toolResult = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "document" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBase64PdfBlock>())?.let {
                        BetaContentBlockParam(document = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "thinking" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaThinkingBlockParam>())?.let {
                        BetaContentBlockParam(thinking = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "redacted_thinking" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRedactedThinkingBlockParam>())
                        ?.let { BetaContentBlockParam(redactedThinking = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
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
                value.serverToolUse != null -> generator.writeObject(value.serverToolUse)
                value.webSearchToolResult != null ->
                    generator.writeObject(value.webSearchToolResult)
                value.toolResult != null -> generator.writeObject(value.toolResult)
                value.document != null -> generator.writeObject(value.document)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.redactedThinking != null -> generator.writeObject(value.redactedThinking)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockParam")
            }
        }
    }
}
