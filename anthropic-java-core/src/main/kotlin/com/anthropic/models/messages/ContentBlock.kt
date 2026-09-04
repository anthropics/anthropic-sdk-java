// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
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

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitText(text: TextBlock): Type = Type.TEXT

                override fun visitThinking(thinking: ThinkingBlock): Type = Type.THINKING

                override fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock): Type =
                    Type.REDACTED_THINKING

                override fun visitToolUse(toolUse: ToolUseBlock): Type = Type.TOOL_USE

                override fun visitServerToolUse(serverToolUse: ServerToolUseBlock): Type =
                    Type.SERVER_TOOL_USE

                override fun visitWebSearchToolResult(
                    webSearchToolResult: WebSearchToolResultBlock
                ): Type = Type.WEB_SEARCH_TOOL_RESULT

                override fun visitWebFetchToolResult(
                    webFetchToolResult: WebFetchToolResultBlock
                ): Type = Type.WEB_FETCH_TOOL_RESULT

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: CodeExecutionToolResultBlock
                ): Type = Type.CODE_EXECUTION_TOOL_RESULT

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
                ): Type = Type.BASH_CODE_EXECUTION_TOOL_RESULT

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
                ): Type = Type.TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: ToolSearchToolResultBlock
                ): Type = Type.TOOL_SEARCH_TOOL_RESULT

                override fun visitContainerUpload(containerUpload: ContainerUploadBlock): Type =
                    Type.CONTAINER_UPLOAD

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun id(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitText(text: TextBlock): Optional<String> = Optional.empty()

                override fun visitThinking(thinking: ThinkingBlock): Optional<String> =
                    Optional.empty()

                override fun visitRedactedThinking(
                    redactedThinking: RedactedThinkingBlock
                ): Optional<String> = Optional.empty()

                override fun visitToolUse(toolUse: ToolUseBlock): Optional<String> =
                    Optional.of(toolUse.id())

                override fun visitServerToolUse(
                    serverToolUse: ServerToolUseBlock
                ): Optional<String> = Optional.of(serverToolUse.id())

                override fun visitWebSearchToolResult(
                    webSearchToolResult: WebSearchToolResultBlock
                ): Optional<String> = Optional.empty()

                override fun visitWebFetchToolResult(
                    webFetchToolResult: WebFetchToolResultBlock
                ): Optional<String> = Optional.empty()

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: CodeExecutionToolResultBlock
                ): Optional<String> = Optional.empty()

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
                ): Optional<String> = Optional.empty()

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
                ): Optional<String> = Optional.empty()

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: ToolSearchToolResultBlock
                ): Optional<String> = Optional.empty()

                override fun visitContainerUpload(
                    containerUpload: ContainerUploadBlock
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("id").asKnown()
            }
        )

    fun toolUseId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitText(text: TextBlock): Optional<String> = Optional.empty()

                override fun visitThinking(thinking: ThinkingBlock): Optional<String> =
                    Optional.empty()

                override fun visitRedactedThinking(
                    redactedThinking: RedactedThinkingBlock
                ): Optional<String> = Optional.empty()

                override fun visitToolUse(toolUse: ToolUseBlock): Optional<String> =
                    Optional.empty()

                override fun visitServerToolUse(
                    serverToolUse: ServerToolUseBlock
                ): Optional<String> = Optional.empty()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: WebSearchToolResultBlock
                ): Optional<String> = Optional.of(webSearchToolResult.toolUseId())

                override fun visitWebFetchToolResult(
                    webFetchToolResult: WebFetchToolResultBlock
                ): Optional<String> = Optional.of(webFetchToolResult.toolUseId())

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: CodeExecutionToolResultBlock
                ): Optional<String> = Optional.of(codeExecutionToolResult.toolUseId())

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
                ): Optional<String> = Optional.of(bashCodeExecutionToolResult.toolUseId())

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
                ): Optional<String> = Optional.of(textEditorCodeExecutionToolResult.toolUseId())

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: ToolSearchToolResultBlock
                ): Optional<String> = Optional.of(toolSearchToolResult.toolUseId())

                override fun visitContainerUpload(
                    containerUpload: ContainerUploadBlock
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("tool_use_id").asKnown()
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

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = contentBlock.accept(new ContentBlock.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitText(TextBlock text) {
     *         return Optional.of(text.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
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

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
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

        /**
         * Returns an immutable instance of [ContentBlock] whose [ofRedactedThinking] variant is
         * built from the given required [data].
         */
        @JvmStatic
        fun ofRedactedThinking(data: String) = ofRedactedThinking(RedactedThinkingBlock.of(data))

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

        /**
         * Returns an immutable instance of [ContentBlock] whose [ofContainerUpload] variant is
         * built from the given required [fileId].
         */
        @JvmStatic
        fun ofContainerUpload(fileId: String) = ofContainerUpload(ContainerUploadBlock.of(fileId))
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

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val TEXT = of("text")

            @JvmField val THINKING = of("thinking")

            @JvmField val REDACTED_THINKING = of("redacted_thinking")

            @JvmField val TOOL_USE = of("tool_use")

            @JvmField val SERVER_TOOL_USE = of("server_tool_use")

            @JvmField val WEB_SEARCH_TOOL_RESULT = of("web_search_tool_result")

            @JvmField val WEB_FETCH_TOOL_RESULT = of("web_fetch_tool_result")

            @JvmField val CODE_EXECUTION_TOOL_RESULT = of("code_execution_tool_result")

            @JvmField val BASH_CODE_EXECUTION_TOOL_RESULT = of("bash_code_execution_tool_result")

            @JvmField
            val TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT =
                of("text_editor_code_execution_tool_result")

            @JvmField val TOOL_SEARCH_TOOL_RESULT = of("tool_search_tool_result")

            @JvmField val CONTAINER_UPLOAD = of("container_upload")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            TEXT,
            THINKING,
            REDACTED_THINKING,
            TOOL_USE,
            SERVER_TOOL_USE,
            WEB_SEARCH_TOOL_RESULT,
            WEB_FETCH_TOOL_RESULT,
            CODE_EXECUTION_TOOL_RESULT,
            BASH_CODE_EXECUTION_TOOL_RESULT,
            TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT,
            TOOL_SEARCH_TOOL_RESULT,
            CONTAINER_UPLOAD,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            TEXT,
            THINKING,
            REDACTED_THINKING,
            TOOL_USE,
            SERVER_TOOL_USE,
            WEB_SEARCH_TOOL_RESULT,
            WEB_FETCH_TOOL_RESULT,
            CODE_EXECUTION_TOOL_RESULT,
            BASH_CODE_EXECUTION_TOOL_RESULT,
            TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT,
            TOOL_SEARCH_TOOL_RESULT,
            CONTAINER_UPLOAD,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                TEXT -> Value.TEXT
                THINKING -> Value.THINKING
                REDACTED_THINKING -> Value.REDACTED_THINKING
                TOOL_USE -> Value.TOOL_USE
                SERVER_TOOL_USE -> Value.SERVER_TOOL_USE
                WEB_SEARCH_TOOL_RESULT -> Value.WEB_SEARCH_TOOL_RESULT
                WEB_FETCH_TOOL_RESULT -> Value.WEB_FETCH_TOOL_RESULT
                CODE_EXECUTION_TOOL_RESULT -> Value.CODE_EXECUTION_TOOL_RESULT
                BASH_CODE_EXECUTION_TOOL_RESULT -> Value.BASH_CODE_EXECUTION_TOOL_RESULT
                TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT ->
                    Value.TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT
                TOOL_SEARCH_TOOL_RESULT -> Value.TOOL_SEARCH_TOOL_RESULT
                CONTAINER_UPLOAD -> Value.CONTAINER_UPLOAD
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                TEXT -> Known.TEXT
                THINKING -> Known.THINKING
                REDACTED_THINKING -> Known.REDACTED_THINKING
                TOOL_USE -> Known.TOOL_USE
                SERVER_TOOL_USE -> Known.SERVER_TOOL_USE
                WEB_SEARCH_TOOL_RESULT -> Known.WEB_SEARCH_TOOL_RESULT
                WEB_FETCH_TOOL_RESULT -> Known.WEB_FETCH_TOOL_RESULT
                CODE_EXECUTION_TOOL_RESULT -> Known.CODE_EXECUTION_TOOL_RESULT
                BASH_CODE_EXECUTION_TOOL_RESULT -> Known.BASH_CODE_EXECUTION_TOOL_RESULT
                TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT ->
                    Known.TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT
                TOOL_SEARCH_TOOL_RESULT -> Known.TOOL_SEARCH_TOOL_RESULT
                CONTAINER_UPLOAD -> Known.CONTAINER_UPLOAD
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
