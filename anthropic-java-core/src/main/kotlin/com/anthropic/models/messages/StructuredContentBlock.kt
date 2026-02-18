package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

/**
 * A wrapper for [ContentBlock] that provides type-safe access to the [text] when using the
 * _Structured Outputs_ feature to deserialize a JSON response to an instance of an arbitrary class.
 * See the SDK documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class to which the JSON data in the content will be deserialized from
 *   the [StructuredTextBlock] returned when [text] is called.
 */
class StructuredContentBlock<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    @get:JvmName("rawContentBlock") val rawContentBlock: ContentBlock,
) {
    private val text by lazy { rawContentBlock.text().map { StructuredTextBlock(outputType, it) } }

    /** @see ContentBlock.text */
    fun text(): Optional<StructuredTextBlock<T>> = text

    /** @see ContentBlock.thinking */
    fun thinking(): Optional<ThinkingBlock> = rawContentBlock.thinking()

    /** @see ContentBlock.redactedThinking */
    fun redactedThinking(): Optional<RedactedThinkingBlock> = rawContentBlock.redactedThinking()

    /** @see ContentBlock.toolUse */
    fun toolUse(): Optional<ToolUseBlock> = rawContentBlock.toolUse()

    /** @see ContentBlock.serverToolUse */
    fun serverToolUse(): Optional<ServerToolUseBlock> = rawContentBlock.serverToolUse()

    /** @see ContentBlock.webSearchToolResult */
    fun webSearchToolResult(): Optional<WebSearchToolResultBlock> =
        rawContentBlock.webSearchToolResult()

    /** @see ContentBlock.webFetchToolResult */
    fun webFetchToolResult(): Optional<WebFetchToolResultBlock> =
        rawContentBlock.webFetchToolResult()

    /** @see ContentBlock.codeExecutionToolResult */
    fun codeExecutionToolResult(): Optional<CodeExecutionToolResultBlock> =
        rawContentBlock.codeExecutionToolResult()

    /** @see ContentBlock.bashCodeExecutionToolResult */
    fun bashCodeExecutionToolResult(): Optional<BashCodeExecutionToolResultBlock> =
        rawContentBlock.bashCodeExecutionToolResult()

    /** @see ContentBlock.textEditorCodeExecutionToolResult */
    fun textEditorCodeExecutionToolResult(): Optional<TextEditorCodeExecutionToolResultBlock> =
        rawContentBlock.textEditorCodeExecutionToolResult()

    /** @see ContentBlock.toolSearchToolResult */
    fun toolSearchToolResult(): Optional<ToolSearchToolResultBlock> =
        rawContentBlock.toolSearchToolResult()

    /** @see ContentBlock.containerUpload */
    fun containerUpload(): Optional<ContainerUploadBlock> = rawContentBlock.containerUpload()

    /** @see ContentBlock.isText */
    fun isText(): Boolean = text().isPresent

    /** @see ContentBlock.isThinking */
    fun isThinking(): Boolean = rawContentBlock.isThinking()

    /** @see ContentBlock.isRedactedThinking */
    fun isRedactedThinking(): Boolean = rawContentBlock.isRedactedThinking()

    /** @see ContentBlock.isToolUse */
    fun isToolUse(): Boolean = rawContentBlock.isToolUse()

    /** @see ContentBlock.isServerToolUse */
    fun isServerToolUse(): Boolean = rawContentBlock.isServerToolUse()

    /** @see ContentBlock.isWebSearchToolResult */
    fun isWebSearchToolResult(): Boolean = rawContentBlock.isWebSearchToolResult()

    /** @see ContentBlock.isWebFetchToolResult */
    fun isWebFetchToolResult(): Boolean = rawContentBlock.isWebFetchToolResult()

    /** @see ContentBlock.isCodeExecutionToolResult */
    fun isCodeExecutionToolResult(): Boolean = rawContentBlock.isCodeExecutionToolResult()

    /** @see ContentBlock.isBashCodeExecutionToolResult */
    fun isBashCodeExecutionToolResult(): Boolean = rawContentBlock.isBashCodeExecutionToolResult()

    /** @see ContentBlock.isTextEditorCodeExecutionToolResult */
    fun isTextEditorCodeExecutionToolResult(): Boolean =
        rawContentBlock.isTextEditorCodeExecutionToolResult()

    /** @see ContentBlock.isToolSearchToolResult */
    fun isToolSearchToolResult(): Boolean = rawContentBlock.isToolSearchToolResult()

    /** @see ContentBlock.isContainerUpload */
    fun isContainerUpload(): Boolean = rawContentBlock.isContainerUpload()

    /** @see ContentBlock.asText */
    fun asText(): StructuredTextBlock<T> =
        text.getOrElse {
            // Same behavior as `com.anthropic.core.getOrThrow` used by the delegate class.
            throw AnthropicInvalidDataException("`text` is not present")
        }

    /** @see ContentBlock.asThinking */
    fun asThinking(): ThinkingBlock = rawContentBlock.asThinking()

    /** @see ContentBlock.asRedactedThinking */
    fun asRedactedThinking(): RedactedThinkingBlock = rawContentBlock.asRedactedThinking()

    /** @see ContentBlock.asToolUse */
    fun asToolUse(): ToolUseBlock = rawContentBlock.asToolUse()

    /** @see ContentBlock.asServerToolUse */
    fun asServerToolUse(): ServerToolUseBlock = rawContentBlock.asServerToolUse()

    /** @see ContentBlock.asWebSearchToolResult */
    fun asWebSearchToolResult(): WebSearchToolResultBlock = rawContentBlock.asWebSearchToolResult()

    /** @see ContentBlock.asWebFetchToolResult */
    fun asWebFetchToolResult(): WebFetchToolResultBlock = rawContentBlock.asWebFetchToolResult()

    /** @see ContentBlock.asCodeExecutionToolResult */
    fun asCodeExecutionToolResult(): CodeExecutionToolResultBlock =
        rawContentBlock.asCodeExecutionToolResult()

    /** @see ContentBlock.asBashCodeExecutionToolResult */
    fun asBashCodeExecutionToolResult(): BashCodeExecutionToolResultBlock =
        rawContentBlock.asBashCodeExecutionToolResult()

    /** @see ContentBlock.asTextEditorCodeExecutionToolResult */
    fun asTextEditorCodeExecutionToolResult(): TextEditorCodeExecutionToolResultBlock =
        rawContentBlock.asTextEditorCodeExecutionToolResult()

    /** @see ContentBlock.asToolSearchToolResult */
    fun asToolSearchToolResult(): ToolSearchToolResultBlock =
        rawContentBlock.asToolSearchToolResult()

    /** @see ContentBlock.asContainerUpload */
    fun asContainerUpload(): ContainerUploadBlock = rawContentBlock.asContainerUpload()

    /** @see ContentBlock._json */
    fun _json(): Optional<JsonValue> = rawContentBlock._json()

    fun <R> accept(visitor: Visitor<T, R>): R =
        when {
            isText() -> visitor.visitText(asText())
            isThinking() -> visitor.visitThinking(asThinking())
            isRedactedThinking() -> visitor.visitRedactedThinking(asRedactedThinking())
            isToolUse() -> visitor.visitToolUse(asToolUse())
            isServerToolUse() -> visitor.visitServerToolUse(asServerToolUse())
            isWebSearchToolResult() -> visitor.visitWebSearchToolResult(asWebSearchToolResult())
            isWebFetchToolResult() -> visitor.visitWebFetchToolResult(asWebFetchToolResult())
            isCodeExecutionToolResult() ->
                visitor.visitCodeExecutionToolResult(asCodeExecutionToolResult())
            isBashCodeExecutionToolResult() ->
                visitor.visitBashCodeExecutionToolResult(asBashCodeExecutionToolResult())
            isTextEditorCodeExecutionToolResult() ->
                visitor.visitTextEditorCodeExecutionToolResult(
                    asTextEditorCodeExecutionToolResult()
                )
            isToolSearchToolResult() -> visitor.visitToolSearchToolResult(asToolSearchToolResult())
            isContainerUpload() -> visitor.visitContainerUpload(asContainerUpload())
            else -> visitor.unknown(_json().getOrNull())
        }

    private var validated: Boolean = false

    /** @see ContentBlock.validate */
    fun validate(): StructuredContentBlock<T> = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<T, Unit> {
                override fun visitText(text: StructuredTextBlock<T>) {
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
        } catch (_: AnthropicInvalidDataException) {
            false
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StructuredContentBlock<*> &&
            outputType == other.outputType &&
            rawContentBlock == other.rawContentBlock
    }

    override fun hashCode(): Int = Objects.hash(outputType, rawContentBlock)

    override fun toString(): String =
        "${javaClass.simpleName}{outputType=$outputType, rawContentBlock=$rawContentBlock}"

    /** @see ContentBlock.Visitor */
    // In keeping with the delegate's `Visitor<T>`, `T` is used to refer to the return type of each
    // function. `R` (for "response") is used to refer to the output type in the response, which is
    // otherwise named `T` in the outer class, but confusion here is probably preferable to
    // confusion there.
    interface Visitor<R : Any, out T> {
        /** @see ContentBlock.Visitor.visitText */
        fun visitText(text: StructuredTextBlock<R>): T

        /** @see ContentBlock.Visitor.visitThinking */
        fun visitThinking(thinking: ThinkingBlock): T

        /** @see ContentBlock.Visitor.visitRedactedThinking */
        fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock): T

        /** @see ContentBlock.Visitor.visitToolUse */
        fun visitToolUse(toolUse: ToolUseBlock): T

        /** @see ContentBlock.Visitor.visitServerToolUse */
        fun visitServerToolUse(serverToolUse: ServerToolUseBlock): T

        /** @see ContentBlock.Visitor.visitWebSearchToolResult */
        fun visitWebSearchToolResult(webSearchToolResult: WebSearchToolResultBlock): T

        /** @see ContentBlock.Visitor.visitWebFetchToolResult */
        fun visitWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlock): T

        /** @see ContentBlock.Visitor.visitCodeExecutionToolResult */
        fun visitCodeExecutionToolResult(codeExecutionToolResult: CodeExecutionToolResultBlock): T

        /** @see ContentBlock.Visitor.visitBashCodeExecutionToolResult */
        fun visitBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
        ): T

        /** @see ContentBlock.Visitor.visitTextEditorCodeExecutionToolResult */
        fun visitTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
        ): T

        /** @see ContentBlock.Visitor.visitToolSearchToolResult */
        fun visitToolSearchToolResult(toolSearchToolResult: ToolSearchToolResultBlock): T

        /** @see ContentBlock.Visitor.visitContainerUpload */
        fun visitContainerUpload(containerUpload: ContainerUploadBlock): T

        /** @see ContentBlock.Visitor.unknown */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ContentBlock: $json")
        }
    }
}
