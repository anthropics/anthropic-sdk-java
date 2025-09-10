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

/** Response model for a file uploaded to the container. */
@JsonDeserialize(using = BetaContentBlock.Deserializer::class)
@JsonSerialize(using = BetaContentBlock.Serializer::class)
class BetaContentBlock
private constructor(
    private val text: BetaTextBlock? = null,
    private val thinking: BetaThinkingBlock? = null,
    private val redactedThinking: BetaRedactedThinkingBlock? = null,
    private val toolUse: BetaToolUseBlock? = null,
    private val serverToolUse: BetaServerToolUseBlock? = null,
    private val webSearchToolResult: BetaWebSearchToolResultBlock? = null,
    private val webFetchToolResult: BetaWebFetchToolResultBlock? = null,
    private val codeExecutionToolResult: BetaCodeExecutionToolResultBlock? = null,
    private val bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock? = null,
    private val textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock? =
        null,
    private val mcpToolUse: BetaMcpToolUseBlock? = null,
    private val mcpToolResult: BetaMcpToolResultBlock? = null,
    private val containerUpload: BetaContainerUploadBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): BetaContentBlockParam =
        accept(
            object : Visitor<BetaContentBlockParam> {
                override fun visitText(text: BetaTextBlock): BetaContentBlockParam =
                    BetaContentBlockParam.ofText(text.toParam())

                override fun visitThinking(thinking: BetaThinkingBlock): BetaContentBlockParam =
                    BetaContentBlockParam.ofThinking(thinking.toParam())

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofRedactedThinking(redactedThinking.toParam())

                override fun visitToolUse(toolUse: BetaToolUseBlock): BetaContentBlockParam =
                    BetaContentBlockParam.ofToolUse(toolUse.toParam())

                override fun visitServerToolUse(
                    serverToolUse: BetaServerToolUseBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofServerToolUse(serverToolUse.toParam())

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofWebSearchToolResult(webSearchToolResult.toParam())

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofWebFetchToolResult(webFetchToolResult.toParam())

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofCodeExecutionToolResult(
                        codeExecutionToolResult.toParam()
                    )

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofBashCodeExecutionToolResult(
                        bashCodeExecutionToolResult.toParam()
                    )

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofTextEditorCodeExecutionToolResult(
                        textEditorCodeExecutionToolResult.toParam()
                    )

                override fun visitMcpToolUse(
                    mcpToolUse: BetaMcpToolUseBlock
                ): BetaContentBlockParam = BetaContentBlockParam.ofMcpToolUse(mcpToolUse.toParam())

                override fun visitMcpToolResult(
                    mcpToolResult: BetaMcpToolResultBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofMcpToolResult(mcpToolResult.toParam())

                override fun visitContainerUpload(
                    containerUpload: BetaContainerUploadBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofContainerUpload(containerUpload.toParam())
            }
        )

    fun text(): Optional<BetaTextBlock> = Optional.ofNullable(text)

    fun thinking(): Optional<BetaThinkingBlock> = Optional.ofNullable(thinking)

    fun redactedThinking(): Optional<BetaRedactedThinkingBlock> =
        Optional.ofNullable(redactedThinking)

    fun toolUse(): Optional<BetaToolUseBlock> = Optional.ofNullable(toolUse)

    fun serverToolUse(): Optional<BetaServerToolUseBlock> = Optional.ofNullable(serverToolUse)

    fun webSearchToolResult(): Optional<BetaWebSearchToolResultBlock> =
        Optional.ofNullable(webSearchToolResult)

    fun webFetchToolResult(): Optional<BetaWebFetchToolResultBlock> =
        Optional.ofNullable(webFetchToolResult)

    fun codeExecutionToolResult(): Optional<BetaCodeExecutionToolResultBlock> =
        Optional.ofNullable(codeExecutionToolResult)

    fun bashCodeExecutionToolResult(): Optional<BetaBashCodeExecutionToolResultBlock> =
        Optional.ofNullable(bashCodeExecutionToolResult)

    fun textEditorCodeExecutionToolResult(): Optional<BetaTextEditorCodeExecutionToolResultBlock> =
        Optional.ofNullable(textEditorCodeExecutionToolResult)

    fun mcpToolUse(): Optional<BetaMcpToolUseBlock> = Optional.ofNullable(mcpToolUse)

    fun mcpToolResult(): Optional<BetaMcpToolResultBlock> = Optional.ofNullable(mcpToolResult)

    /** Response model for a file uploaded to the container. */
    fun containerUpload(): Optional<BetaContainerUploadBlock> = Optional.ofNullable(containerUpload)

    fun isText(): Boolean = text != null

    fun isThinking(): Boolean = thinking != null

    fun isRedactedThinking(): Boolean = redactedThinking != null

    fun isToolUse(): Boolean = toolUse != null

    fun isServerToolUse(): Boolean = serverToolUse != null

    fun isWebSearchToolResult(): Boolean = webSearchToolResult != null

    fun isWebFetchToolResult(): Boolean = webFetchToolResult != null

    fun isCodeExecutionToolResult(): Boolean = codeExecutionToolResult != null

    fun isBashCodeExecutionToolResult(): Boolean = bashCodeExecutionToolResult != null

    fun isTextEditorCodeExecutionToolResult(): Boolean = textEditorCodeExecutionToolResult != null

    fun isMcpToolUse(): Boolean = mcpToolUse != null

    fun isMcpToolResult(): Boolean = mcpToolResult != null

    fun isContainerUpload(): Boolean = containerUpload != null

    fun asText(): BetaTextBlock = text.getOrThrow("text")

    fun asThinking(): BetaThinkingBlock = thinking.getOrThrow("thinking")

    fun asRedactedThinking(): BetaRedactedThinkingBlock =
        redactedThinking.getOrThrow("redactedThinking")

    fun asToolUse(): BetaToolUseBlock = toolUse.getOrThrow("toolUse")

    fun asServerToolUse(): BetaServerToolUseBlock = serverToolUse.getOrThrow("serverToolUse")

    fun asWebSearchToolResult(): BetaWebSearchToolResultBlock =
        webSearchToolResult.getOrThrow("webSearchToolResult")

    fun asWebFetchToolResult(): BetaWebFetchToolResultBlock =
        webFetchToolResult.getOrThrow("webFetchToolResult")

    fun asCodeExecutionToolResult(): BetaCodeExecutionToolResultBlock =
        codeExecutionToolResult.getOrThrow("codeExecutionToolResult")

    fun asBashCodeExecutionToolResult(): BetaBashCodeExecutionToolResultBlock =
        bashCodeExecutionToolResult.getOrThrow("bashCodeExecutionToolResult")

    fun asTextEditorCodeExecutionToolResult(): BetaTextEditorCodeExecutionToolResultBlock =
        textEditorCodeExecutionToolResult.getOrThrow("textEditorCodeExecutionToolResult")

    fun asMcpToolUse(): BetaMcpToolUseBlock = mcpToolUse.getOrThrow("mcpToolUse")

    fun asMcpToolResult(): BetaMcpToolResultBlock = mcpToolResult.getOrThrow("mcpToolResult")

    /** Response model for a file uploaded to the container. */
    fun asContainerUpload(): BetaContainerUploadBlock =
        containerUpload.getOrThrow("containerUpload")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            text != null -> visitor.visitText(text)
            thinking != null -> visitor.visitThinking(thinking)
            redactedThinking != null -> visitor.visitRedactedThinking(redactedThinking)
            toolUse != null -> visitor.visitToolUse(toolUse)
            serverToolUse != null -> visitor.visitServerToolUse(serverToolUse)
            webSearchToolResult != null -> visitor.visitWebSearchToolResult(webSearchToolResult)
            webFetchToolResult != null -> visitor.visitWebFetchToolResult(webFetchToolResult)
            codeExecutionToolResult != null ->
                visitor.visitCodeExecutionToolResult(codeExecutionToolResult)
            bashCodeExecutionToolResult != null ->
                visitor.visitBashCodeExecutionToolResult(bashCodeExecutionToolResult)
            textEditorCodeExecutionToolResult != null ->
                visitor.visitTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)
            mcpToolUse != null -> visitor.visitMcpToolUse(mcpToolUse)
            mcpToolResult != null -> visitor.visitMcpToolResult(mcpToolResult)
            containerUpload != null -> visitor.visitContainerUpload(containerUpload)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaContentBlock = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: BetaTextBlock) {
                    text.validate()
                }

                override fun visitThinking(thinking: BetaThinkingBlock) {
                    thinking.validate()
                }

                override fun visitRedactedThinking(redactedThinking: BetaRedactedThinkingBlock) {
                    redactedThinking.validate()
                }

                override fun visitToolUse(toolUse: BetaToolUseBlock) {
                    toolUse.validate()
                }

                override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlock) {
                    serverToolUse.validate()
                }

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlock
                ) {
                    webSearchToolResult.validate()
                }

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlock
                ) {
                    webFetchToolResult.validate()
                }

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlock
                ) {
                    codeExecutionToolResult.validate()
                }

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock
                ) {
                    bashCodeExecutionToolResult.validate()
                }

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock
                ) {
                    textEditorCodeExecutionToolResult.validate()
                }

                override fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlock) {
                    mcpToolUse.validate()
                }

                override fun visitMcpToolResult(mcpToolResult: BetaMcpToolResultBlock) {
                    mcpToolResult.validate()
                }

                override fun visitContainerUpload(containerUpload: BetaContainerUploadBlock) {
                    containerUpload.validate()
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
                override fun visitText(text: BetaTextBlock) = text.validity()

                override fun visitThinking(thinking: BetaThinkingBlock) = thinking.validity()

                override fun visitRedactedThinking(redactedThinking: BetaRedactedThinkingBlock) =
                    redactedThinking.validity()

                override fun visitToolUse(toolUse: BetaToolUseBlock) = toolUse.validity()

                override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlock) =
                    serverToolUse.validity()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlock
                ) = webSearchToolResult.validity()

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlock
                ) = webFetchToolResult.validity()

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlock
                ) = codeExecutionToolResult.validity()

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock
                ) = bashCodeExecutionToolResult.validity()

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock
                ) = textEditorCodeExecutionToolResult.validity()

                override fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlock) =
                    mcpToolUse.validity()

                override fun visitMcpToolResult(mcpToolResult: BetaMcpToolResultBlock) =
                    mcpToolResult.validity()

                override fun visitContainerUpload(containerUpload: BetaContainerUploadBlock) =
                    containerUpload.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContentBlock &&
            text == other.text &&
            thinking == other.thinking &&
            redactedThinking == other.redactedThinking &&
            toolUse == other.toolUse &&
            serverToolUse == other.serverToolUse &&
            webSearchToolResult == other.webSearchToolResult &&
            webFetchToolResult == other.webFetchToolResult &&
            codeExecutionToolResult == other.codeExecutionToolResult &&
            bashCodeExecutionToolResult == other.bashCodeExecutionToolResult &&
            textEditorCodeExecutionToolResult == other.textEditorCodeExecutionToolResult &&
            mcpToolUse == other.mcpToolUse &&
            mcpToolResult == other.mcpToolResult &&
            containerUpload == other.containerUpload
    }

    override fun hashCode(): Int =
        Objects.hash(
            text,
            thinking,
            redactedThinking,
            toolUse,
            serverToolUse,
            webSearchToolResult,
            webFetchToolResult,
            codeExecutionToolResult,
            bashCodeExecutionToolResult,
            textEditorCodeExecutionToolResult,
            mcpToolUse,
            mcpToolResult,
            containerUpload,
        )

    override fun toString(): String =
        when {
            text != null -> "BetaContentBlock{text=$text}"
            thinking != null -> "BetaContentBlock{thinking=$thinking}"
            redactedThinking != null -> "BetaContentBlock{redactedThinking=$redactedThinking}"
            toolUse != null -> "BetaContentBlock{toolUse=$toolUse}"
            serverToolUse != null -> "BetaContentBlock{serverToolUse=$serverToolUse}"
            webSearchToolResult != null ->
                "BetaContentBlock{webSearchToolResult=$webSearchToolResult}"
            webFetchToolResult != null -> "BetaContentBlock{webFetchToolResult=$webFetchToolResult}"
            codeExecutionToolResult != null ->
                "BetaContentBlock{codeExecutionToolResult=$codeExecutionToolResult}"
            bashCodeExecutionToolResult != null ->
                "BetaContentBlock{bashCodeExecutionToolResult=$bashCodeExecutionToolResult}"
            textEditorCodeExecutionToolResult != null ->
                "BetaContentBlock{textEditorCodeExecutionToolResult=$textEditorCodeExecutionToolResult}"
            mcpToolUse != null -> "BetaContentBlock{mcpToolUse=$mcpToolUse}"
            mcpToolResult != null -> "BetaContentBlock{mcpToolResult=$mcpToolResult}"
            containerUpload != null -> "BetaContentBlock{containerUpload=$containerUpload}"
            _json != null -> "BetaContentBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlock")
        }

    companion object {

        @JvmStatic fun ofText(text: BetaTextBlock) = BetaContentBlock(text = text)

        @JvmStatic
        fun ofThinking(thinking: BetaThinkingBlock) = BetaContentBlock(thinking = thinking)

        @JvmStatic
        fun ofRedactedThinking(redactedThinking: BetaRedactedThinkingBlock) =
            BetaContentBlock(redactedThinking = redactedThinking)

        @JvmStatic fun ofToolUse(toolUse: BetaToolUseBlock) = BetaContentBlock(toolUse = toolUse)

        @JvmStatic
        fun ofServerToolUse(serverToolUse: BetaServerToolUseBlock) =
            BetaContentBlock(serverToolUse = serverToolUse)

        @JvmStatic
        fun ofWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlock) =
            BetaContentBlock(webSearchToolResult = webSearchToolResult)

        @JvmStatic
        fun ofWebFetchToolResult(webFetchToolResult: BetaWebFetchToolResultBlock) =
            BetaContentBlock(webFetchToolResult = webFetchToolResult)

        @JvmStatic
        fun ofCodeExecutionToolResult(codeExecutionToolResult: BetaCodeExecutionToolResultBlock) =
            BetaContentBlock(codeExecutionToolResult = codeExecutionToolResult)

        @JvmStatic
        fun ofBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock
        ) = BetaContentBlock(bashCodeExecutionToolResult = bashCodeExecutionToolResult)

        @JvmStatic
        fun ofTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock
        ) = BetaContentBlock(textEditorCodeExecutionToolResult = textEditorCodeExecutionToolResult)

        @JvmStatic
        fun ofMcpToolUse(mcpToolUse: BetaMcpToolUseBlock) =
            BetaContentBlock(mcpToolUse = mcpToolUse)

        @JvmStatic
        fun ofMcpToolResult(mcpToolResult: BetaMcpToolResultBlock) =
            BetaContentBlock(mcpToolResult = mcpToolResult)

        /** Response model for a file uploaded to the container. */
        @JvmStatic
        fun ofContainerUpload(containerUpload: BetaContainerUploadBlock) =
            BetaContentBlock(containerUpload = containerUpload)
    }

    /**
     * An interface that defines how to map each variant of [BetaContentBlock] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitText(text: BetaTextBlock): T

        fun visitThinking(thinking: BetaThinkingBlock): T

        fun visitRedactedThinking(redactedThinking: BetaRedactedThinkingBlock): T

        fun visitToolUse(toolUse: BetaToolUseBlock): T

        fun visitServerToolUse(serverToolUse: BetaServerToolUseBlock): T

        fun visitWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlock): T

        fun visitWebFetchToolResult(webFetchToolResult: BetaWebFetchToolResultBlock): T

        fun visitCodeExecutionToolResult(
            codeExecutionToolResult: BetaCodeExecutionToolResultBlock
        ): T

        fun visitBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock
        ): T

        fun visitTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock
        ): T

        fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlock): T

        fun visitMcpToolResult(mcpToolResult: BetaMcpToolResultBlock): T

        /** Response model for a file uploaded to the container. */
        fun visitContainerUpload(containerUpload: BetaContainerUploadBlock): T

        /**
         * Maps an unknown variant of [BetaContentBlock] to a value of type [T].
         *
         * An instance of [BetaContentBlock] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlock: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaContentBlock>(BetaContentBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaContentBlock {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaTextBlock>())?.let {
                        BetaContentBlock(text = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "thinking" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaThinkingBlock>())?.let {
                        BetaContentBlock(thinking = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "redacted_thinking" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRedactedThinkingBlock>())?.let {
                        BetaContentBlock(redactedThinking = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolUseBlock>())?.let {
                        BetaContentBlock(toolUse = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "server_tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaServerToolUseBlock>())?.let {
                        BetaContentBlock(serverToolUse = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "web_search_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebSearchToolResultBlock>())
                        ?.let { BetaContentBlock(webSearchToolResult = it, _json = json) }
                        ?: BetaContentBlock(_json = json)
                }
                "web_fetch_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebFetchToolResultBlock>())
                        ?.let { BetaContentBlock(webFetchToolResult = it, _json = json) }
                        ?: BetaContentBlock(_json = json)
                }
                "code_execution_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionToolResultBlock>())
                        ?.let { BetaContentBlock(codeExecutionToolResult = it, _json = json) }
                        ?: BetaContentBlock(_json = json)
                }
                "bash_code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBashCodeExecutionToolResultBlock>(),
                        )
                        ?.let { BetaContentBlock(bashCodeExecutionToolResult = it, _json = json) }
                        ?: BetaContentBlock(_json = json)
                }
                "text_editor_code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTextEditorCodeExecutionToolResultBlock>(),
                        )
                        ?.let {
                            BetaContentBlock(textEditorCodeExecutionToolResult = it, _json = json)
                        } ?: BetaContentBlock(_json = json)
                }
                "mcp_tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaMcpToolUseBlock>())?.let {
                        BetaContentBlock(mcpToolUse = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "mcp_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaMcpToolResultBlock>())?.let {
                        BetaContentBlock(mcpToolResult = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "container_upload" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaContainerUploadBlock>())?.let {
                        BetaContentBlock(containerUpload = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
            }

            return BetaContentBlock(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaContentBlock>(BetaContentBlock::class) {

        override fun serialize(
            value: BetaContentBlock,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.redactedThinking != null -> generator.writeObject(value.redactedThinking)
                value.toolUse != null -> generator.writeObject(value.toolUse)
                value.serverToolUse != null -> generator.writeObject(value.serverToolUse)
                value.webSearchToolResult != null ->
                    generator.writeObject(value.webSearchToolResult)
                value.webFetchToolResult != null -> generator.writeObject(value.webFetchToolResult)
                value.codeExecutionToolResult != null ->
                    generator.writeObject(value.codeExecutionToolResult)
                value.bashCodeExecutionToolResult != null ->
                    generator.writeObject(value.bashCodeExecutionToolResult)
                value.textEditorCodeExecutionToolResult != null ->
                    generator.writeObject(value.textEditorCodeExecutionToolResult)
                value.mcpToolUse != null -> generator.writeObject(value.mcpToolUse)
                value.mcpToolResult != null -> generator.writeObject(value.mcpToolResult)
                value.containerUpload != null -> generator.writeObject(value.containerUpload)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlock")
            }
        }
    }
}
