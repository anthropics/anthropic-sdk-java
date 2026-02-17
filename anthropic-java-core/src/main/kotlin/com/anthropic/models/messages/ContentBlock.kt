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

/** Response model for a file uploaded to the container. */
@JsonDeserialize(using = ContentBlock.Deserializer::class)
@JsonSerialize(using = ContentBlock.Serializer::class)
class ContentBlock
private constructor(
    private val text: TextBlock? = null,
    private val thinking: ThinkingBlock? = null,
    private val redactedThinking: RedactedThinkingBlock? = null,
    private val toolUse: ToolUseBlock? = null,
    private val serverToolUse: ServerToolUseBlock? = null,
    private val webSearchToolResult: WebSearchToolResultBlock? = null,
    private val webFetchToolResult: WebFetchToolResultBlock? = null,
    private val codeExecutionToolResult: CodeExecutionToolResultBlock? = null,
    private val bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock? = null,
    private val textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock? = null,
    private val toolSearchToolResult: ToolSearchToolResultBlock? = null,
    private val containerUpload: ContainerUploadBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): ContentBlockParam =
        accept(
            object : Visitor<ContentBlockParam> {
                override fun visitText(text: TextBlock): ContentBlockParam =
                    ContentBlockParam.ofText(text.toParam())

                override fun visitThinking(thinking: ThinkingBlock): ContentBlockParam =
                    ContentBlockParam.ofThinking(thinking.toParam())

                override fun visitRedactedThinking(
                    redactedThinking: RedactedThinkingBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofRedactedThinking(redactedThinking.toParam())

                override fun visitToolUse(toolUse: ToolUseBlock): ContentBlockParam =
                    ContentBlockParam.ofToolUse(toolUse.toParam())

                override fun visitServerToolUse(
                    serverToolUse: ServerToolUseBlock
                ): ContentBlockParam = ContentBlockParam.ofServerToolUse(serverToolUse.toParam())

                override fun visitWebSearchToolResult(
                    webSearchToolResult: WebSearchToolResultBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofWebSearchToolResult(webSearchToolResult.toParam())

                override fun visitWebFetchToolResult(
                    webFetchToolResult: WebFetchToolResultBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofWebFetchToolResult(webFetchToolResult.toParam())

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: CodeExecutionToolResultBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofCodeExecutionToolResult(codeExecutionToolResult.toParam())

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofBashCodeExecutionToolResult(
                        bashCodeExecutionToolResult.toParam()
                    )

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofTextEditorCodeExecutionToolResult(
                        textEditorCodeExecutionToolResult.toParam()
                    )

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: ToolSearchToolResultBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofToolSearchToolResult(toolSearchToolResult.toParam())

                override fun visitContainerUpload(
                    containerUpload: ContainerUploadBlock
                ): ContentBlockParam =
                    ContentBlockParam.ofContainerUpload(containerUpload.toParam())
            }
        )

    fun text(): Optional<TextBlock> = Optional.ofNullable(text)

    fun thinking(): Optional<ThinkingBlock> = Optional.ofNullable(thinking)

    fun redactedThinking(): Optional<RedactedThinkingBlock> = Optional.ofNullable(redactedThinking)

    fun toolUse(): Optional<ToolUseBlock> = Optional.ofNullable(toolUse)

    fun serverToolUse(): Optional<ServerToolUseBlock> = Optional.ofNullable(serverToolUse)

    fun webSearchToolResult(): Optional<WebSearchToolResultBlock> =
        Optional.ofNullable(webSearchToolResult)

    fun webFetchToolResult(): Optional<WebFetchToolResultBlock> =
        Optional.ofNullable(webFetchToolResult)

    fun codeExecutionToolResult(): Optional<CodeExecutionToolResultBlock> =
        Optional.ofNullable(codeExecutionToolResult)

    fun bashCodeExecutionToolResult(): Optional<BashCodeExecutionToolResultBlock> =
        Optional.ofNullable(bashCodeExecutionToolResult)

    fun textEditorCodeExecutionToolResult(): Optional<TextEditorCodeExecutionToolResultBlock> =
        Optional.ofNullable(textEditorCodeExecutionToolResult)

    fun toolSearchToolResult(): Optional<ToolSearchToolResultBlock> =
        Optional.ofNullable(toolSearchToolResult)

    /** Response model for a file uploaded to the container. */
    fun containerUpload(): Optional<ContainerUploadBlock> = Optional.ofNullable(containerUpload)

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

    fun isToolSearchToolResult(): Boolean = toolSearchToolResult != null

    fun isContainerUpload(): Boolean = containerUpload != null

    fun asText(): TextBlock = text.getOrThrow("text")

    fun asThinking(): ThinkingBlock = thinking.getOrThrow("thinking")

    fun asRedactedThinking(): RedactedThinkingBlock =
        redactedThinking.getOrThrow("redactedThinking")

    fun asToolUse(): ToolUseBlock = toolUse.getOrThrow("toolUse")

    fun asServerToolUse(): ServerToolUseBlock = serverToolUse.getOrThrow("serverToolUse")

    fun asWebSearchToolResult(): WebSearchToolResultBlock =
        webSearchToolResult.getOrThrow("webSearchToolResult")

    fun asWebFetchToolResult(): WebFetchToolResultBlock =
        webFetchToolResult.getOrThrow("webFetchToolResult")

    fun asCodeExecutionToolResult(): CodeExecutionToolResultBlock =
        codeExecutionToolResult.getOrThrow("codeExecutionToolResult")

    fun asBashCodeExecutionToolResult(): BashCodeExecutionToolResultBlock =
        bashCodeExecutionToolResult.getOrThrow("bashCodeExecutionToolResult")

    fun asTextEditorCodeExecutionToolResult(): TextEditorCodeExecutionToolResultBlock =
        textEditorCodeExecutionToolResult.getOrThrow("textEditorCodeExecutionToolResult")

    fun asToolSearchToolResult(): ToolSearchToolResultBlock =
        toolSearchToolResult.getOrThrow("toolSearchToolResult")

    /** Response model for a file uploaded to the container. */
    fun asContainerUpload(): ContainerUploadBlock = containerUpload.getOrThrow("containerUpload")

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
            toolSearchToolResult != null -> visitor.visitToolSearchToolResult(toolSearchToolResult)
            containerUpload != null -> visitor.visitContainerUpload(containerUpload)
            else -> visitor.unknown(_json)
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

                override fun visitThinking(thinking: ThinkingBlock) {
                    thinking.validate()
                }

                override fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock) {
                    redactedThinking.validate()
                }

                override fun visitToolUse(toolUse: ToolUseBlock) {
                    toolUse.validate()
                }

                override fun visitServerToolUse(serverToolUse: ServerToolUseBlock) {
                    serverToolUse.validate()
                }

                override fun visitWebSearchToolResult(
                    webSearchToolResult: WebSearchToolResultBlock
                ) {
                    webSearchToolResult.validate()
                }

                override fun visitWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlock) {
                    webFetchToolResult.validate()
                }

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: CodeExecutionToolResultBlock
                ) {
                    codeExecutionToolResult.validate()
                }

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
                ) {
                    bashCodeExecutionToolResult.validate()
                }

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
                ) {
                    textEditorCodeExecutionToolResult.validate()
                }

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: ToolSearchToolResultBlock
                ) {
                    toolSearchToolResult.validate()
                }

                override fun visitContainerUpload(containerUpload: ContainerUploadBlock) {
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
                override fun visitText(text: TextBlock) = text.validity()

                override fun visitThinking(thinking: ThinkingBlock) = thinking.validity()

                override fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock) =
                    redactedThinking.validity()

                override fun visitToolUse(toolUse: ToolUseBlock) = toolUse.validity()

                override fun visitServerToolUse(serverToolUse: ServerToolUseBlock) =
                    serverToolUse.validity()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: WebSearchToolResultBlock
                ) = webSearchToolResult.validity()

                override fun visitWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlock) =
                    webFetchToolResult.validity()

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: CodeExecutionToolResultBlock
                ) = codeExecutionToolResult.validity()

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
                ) = bashCodeExecutionToolResult.validity()

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
                ) = textEditorCodeExecutionToolResult.validity()

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: ToolSearchToolResultBlock
                ) = toolSearchToolResult.validity()

                override fun visitContainerUpload(containerUpload: ContainerUploadBlock) =
                    containerUpload.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ContentBlock &&
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
            toolSearchToolResult == other.toolSearchToolResult &&
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
            toolSearchToolResult,
            containerUpload,
        )

    override fun toString(): String =
        when {
            text != null -> "ContentBlock{text=$text}"
            thinking != null -> "ContentBlock{thinking=$thinking}"
            redactedThinking != null -> "ContentBlock{redactedThinking=$redactedThinking}"
            toolUse != null -> "ContentBlock{toolUse=$toolUse}"
            serverToolUse != null -> "ContentBlock{serverToolUse=$serverToolUse}"
            webSearchToolResult != null -> "ContentBlock{webSearchToolResult=$webSearchToolResult}"
            webFetchToolResult != null -> "ContentBlock{webFetchToolResult=$webFetchToolResult}"
            codeExecutionToolResult != null ->
                "ContentBlock{codeExecutionToolResult=$codeExecutionToolResult}"
            bashCodeExecutionToolResult != null ->
                "ContentBlock{bashCodeExecutionToolResult=$bashCodeExecutionToolResult}"
            textEditorCodeExecutionToolResult != null ->
                "ContentBlock{textEditorCodeExecutionToolResult=$textEditorCodeExecutionToolResult}"
            toolSearchToolResult != null ->
                "ContentBlock{toolSearchToolResult=$toolSearchToolResult}"
            containerUpload != null -> "ContentBlock{containerUpload=$containerUpload}"
            _json != null -> "ContentBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ContentBlock")
        }

    companion object {

        @JvmStatic fun ofText(text: TextBlock) = ContentBlock(text = text)

        @JvmStatic fun ofThinking(thinking: ThinkingBlock) = ContentBlock(thinking = thinking)

        @JvmStatic
        fun ofRedactedThinking(redactedThinking: RedactedThinkingBlock) =
            ContentBlock(redactedThinking = redactedThinking)

        @JvmStatic fun ofToolUse(toolUse: ToolUseBlock) = ContentBlock(toolUse = toolUse)

        @JvmStatic
        fun ofServerToolUse(serverToolUse: ServerToolUseBlock) =
            ContentBlock(serverToolUse = serverToolUse)

        @JvmStatic
        fun ofWebSearchToolResult(webSearchToolResult: WebSearchToolResultBlock) =
            ContentBlock(webSearchToolResult = webSearchToolResult)

        @JvmStatic
        fun ofWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlock) =
            ContentBlock(webFetchToolResult = webFetchToolResult)

        @JvmStatic
        fun ofCodeExecutionToolResult(codeExecutionToolResult: CodeExecutionToolResultBlock) =
            ContentBlock(codeExecutionToolResult = codeExecutionToolResult)

        @JvmStatic
        fun ofBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
        ) = ContentBlock(bashCodeExecutionToolResult = bashCodeExecutionToolResult)

        @JvmStatic
        fun ofTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
        ) = ContentBlock(textEditorCodeExecutionToolResult = textEditorCodeExecutionToolResult)

        @JvmStatic
        fun ofToolSearchToolResult(toolSearchToolResult: ToolSearchToolResultBlock) =
            ContentBlock(toolSearchToolResult = toolSearchToolResult)

        /** Response model for a file uploaded to the container. */
        @JvmStatic
        fun ofContainerUpload(containerUpload: ContainerUploadBlock) =
            ContentBlock(containerUpload = containerUpload)
    }

    /**
     * An interface that defines how to map each variant of [ContentBlock] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: TextBlock): T

        fun visitThinking(thinking: ThinkingBlock): T

        fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock): T

        fun visitToolUse(toolUse: ToolUseBlock): T

        fun visitServerToolUse(serverToolUse: ServerToolUseBlock): T

        fun visitWebSearchToolResult(webSearchToolResult: WebSearchToolResultBlock): T

        fun visitWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlock): T

        fun visitCodeExecutionToolResult(codeExecutionToolResult: CodeExecutionToolResultBlock): T

        fun visitBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
        ): T

        fun visitTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
        ): T

        fun visitToolSearchToolResult(toolSearchToolResult: ToolSearchToolResultBlock): T

        /** Response model for a file uploaded to the container. */
        fun visitContainerUpload(containerUpload: ContainerUploadBlock): T

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
                    return tryDeserialize(node, jacksonTypeRef<TextBlock>())?.let {
                        ContentBlock(text = it, _json = json)
                    } ?: ContentBlock(_json = json)
                }
                "thinking" -> {
                    return tryDeserialize(node, jacksonTypeRef<ThinkingBlock>())?.let {
                        ContentBlock(thinking = it, _json = json)
                    } ?: ContentBlock(_json = json)
                }
                "redacted_thinking" -> {
                    return tryDeserialize(node, jacksonTypeRef<RedactedThinkingBlock>())?.let {
                        ContentBlock(redactedThinking = it, _json = json)
                    } ?: ContentBlock(_json = json)
                }
                "tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<ToolUseBlock>())?.let {
                        ContentBlock(toolUse = it, _json = json)
                    } ?: ContentBlock(_json = json)
                }
                "server_tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<ServerToolUseBlock>())?.let {
                        ContentBlock(serverToolUse = it, _json = json)
                    } ?: ContentBlock(_json = json)
                }
                "web_search_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<WebSearchToolResultBlock>())?.let {
                        ContentBlock(webSearchToolResult = it, _json = json)
                    } ?: ContentBlock(_json = json)
                }
                "web_fetch_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<WebFetchToolResultBlock>())?.let {
                        ContentBlock(webFetchToolResult = it, _json = json)
                    } ?: ContentBlock(_json = json)
                }
                "code_execution_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<CodeExecutionToolResultBlock>())
                        ?.let { ContentBlock(codeExecutionToolResult = it, _json = json) }
                        ?: ContentBlock(_json = json)
                }
                "bash_code_execution_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BashCodeExecutionToolResultBlock>())
                        ?.let { ContentBlock(bashCodeExecutionToolResult = it, _json = json) }
                        ?: ContentBlock(_json = json)
                }
                "text_editor_code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<TextEditorCodeExecutionToolResultBlock>(),
                        )
                        ?.let { ContentBlock(textEditorCodeExecutionToolResult = it, _json = json) }
                        ?: ContentBlock(_json = json)
                }
                "tool_search_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<ToolSearchToolResultBlock>())?.let {
                        ContentBlock(toolSearchToolResult = it, _json = json)
                    } ?: ContentBlock(_json = json)
                }
                "container_upload" -> {
                    return tryDeserialize(node, jacksonTypeRef<ContainerUploadBlock>())?.let {
                        ContentBlock(containerUpload = it, _json = json)
                    } ?: ContentBlock(_json = json)
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
                value.toolSearchToolResult != null ->
                    generator.writeObject(value.toolSearchToolResult)
                value.containerUpload != null -> generator.writeObject(value.containerUpload)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ContentBlock")
            }
        }
    }
}
