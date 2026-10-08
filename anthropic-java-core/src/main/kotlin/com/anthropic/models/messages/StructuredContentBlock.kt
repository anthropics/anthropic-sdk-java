package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional
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
    private val delegate: ContentBlock,
) {

    @get:JvmName("rawContentBlock")
    val rawContentBlock: ContentBlock
        get() = delegate

    /** @see ContentBlock.type */
    fun type(): ContentBlock.Type = delegate.type()

    /** @see ContentBlock.id */
    fun id(): Optional<String> = delegate.id()

    /** @see ContentBlock.toolUseId */
    fun toolUseId(): Optional<String> = delegate.toolUseId()

    /** @see ContentBlock.text */
    fun text(): Optional<StructuredTextBlock<T>> = text

    private val text by lazy { delegate.text().map { StructuredTextBlock(outputType, it) } }

    /** @see ContentBlock.thinking */
    fun thinking(): Optional<ThinkingBlock> = delegate.thinking()

    /** @see ContentBlock.redactedThinking */
    fun redactedThinking(): Optional<RedactedThinkingBlock> = delegate.redactedThinking()

    /** @see ContentBlock.toolUse */
    fun toolUse(): Optional<ToolUseBlock> = delegate.toolUse()

    /** @see ContentBlock.serverToolUse */
    fun serverToolUse(): Optional<ServerToolUseBlock> = delegate.serverToolUse()

    /** @see ContentBlock.webSearchToolResult */
    fun webSearchToolResult(): Optional<WebSearchToolResultBlock> = delegate.webSearchToolResult()

    /** @see ContentBlock.webFetchToolResult */
    fun webFetchToolResult(): Optional<WebFetchToolResultBlock> = delegate.webFetchToolResult()

    /** @see ContentBlock.codeExecutionToolResult */
    fun codeExecutionToolResult(): Optional<CodeExecutionToolResultBlock> =
        delegate.codeExecutionToolResult()

    /** @see ContentBlock.bashCodeExecutionToolResult */
    fun bashCodeExecutionToolResult(): Optional<BashCodeExecutionToolResultBlock> =
        delegate.bashCodeExecutionToolResult()

    /** @see ContentBlock.textEditorCodeExecutionToolResult */
    fun textEditorCodeExecutionToolResult(): Optional<TextEditorCodeExecutionToolResultBlock> =
        delegate.textEditorCodeExecutionToolResult()

    /** @see ContentBlock.toolSearchToolResult */
    fun toolSearchToolResult(): Optional<ToolSearchToolResultBlock> =
        delegate.toolSearchToolResult()

    /** @see ContentBlock.containerUpload */
    fun containerUpload(): Optional<ContainerUploadBlock> = delegate.containerUpload()

    /** @see ContentBlock.isText */
    fun isText(): Boolean = text.isPresent

    /** @see ContentBlock.isThinking */
    fun isThinking(): Boolean = delegate.isThinking()

    /** @see ContentBlock.isRedactedThinking */
    fun isRedactedThinking(): Boolean = delegate.isRedactedThinking()

    /** @see ContentBlock.isToolUse */
    fun isToolUse(): Boolean = delegate.isToolUse()

    /** @see ContentBlock.isServerToolUse */
    fun isServerToolUse(): Boolean = delegate.isServerToolUse()

    /** @see ContentBlock.isWebSearchToolResult */
    fun isWebSearchToolResult(): Boolean = delegate.isWebSearchToolResult()

    /** @see ContentBlock.isWebFetchToolResult */
    fun isWebFetchToolResult(): Boolean = delegate.isWebFetchToolResult()

    /** @see ContentBlock.isCodeExecutionToolResult */
    fun isCodeExecutionToolResult(): Boolean = delegate.isCodeExecutionToolResult()

    /** @see ContentBlock.isBashCodeExecutionToolResult */
    fun isBashCodeExecutionToolResult(): Boolean = delegate.isBashCodeExecutionToolResult()

    /** @see ContentBlock.isTextEditorCodeExecutionToolResult */
    fun isTextEditorCodeExecutionToolResult(): Boolean =
        delegate.isTextEditorCodeExecutionToolResult()

    /** @see ContentBlock.isToolSearchToolResult */
    fun isToolSearchToolResult(): Boolean = delegate.isToolSearchToolResult()

    /** @see ContentBlock.isContainerUpload */
    fun isContainerUpload(): Boolean = delegate.isContainerUpload()

    /** @see ContentBlock.asText */
    fun asText(): StructuredTextBlock<T> = text.getOrNull().getOrThrow("text")

    /** @see ContentBlock.asThinking */
    fun asThinking(): ThinkingBlock = delegate.asThinking()

    /** @see ContentBlock.asRedactedThinking */
    fun asRedactedThinking(): RedactedThinkingBlock = delegate.asRedactedThinking()

    /** @see ContentBlock.asToolUse */
    fun asToolUse(): ToolUseBlock = delegate.asToolUse()

    /** @see ContentBlock.asServerToolUse */
    fun asServerToolUse(): ServerToolUseBlock = delegate.asServerToolUse()

    /** @see ContentBlock.asWebSearchToolResult */
    fun asWebSearchToolResult(): WebSearchToolResultBlock = delegate.asWebSearchToolResult()

    /** @see ContentBlock.asWebFetchToolResult */
    fun asWebFetchToolResult(): WebFetchToolResultBlock = delegate.asWebFetchToolResult()

    /** @see ContentBlock.asCodeExecutionToolResult */
    fun asCodeExecutionToolResult(): CodeExecutionToolResultBlock =
        delegate.asCodeExecutionToolResult()

    /** @see ContentBlock.asBashCodeExecutionToolResult */
    fun asBashCodeExecutionToolResult(): BashCodeExecutionToolResultBlock =
        delegate.asBashCodeExecutionToolResult()

    /** @see ContentBlock.asTextEditorCodeExecutionToolResult */
    fun asTextEditorCodeExecutionToolResult(): TextEditorCodeExecutionToolResultBlock =
        delegate.asTextEditorCodeExecutionToolResult()

    /** @see ContentBlock.asToolSearchToolResult */
    fun asToolSearchToolResult(): ToolSearchToolResultBlock = delegate.asToolSearchToolResult()

    /** @see ContentBlock.asContainerUpload */
    fun asContainerUpload(): ContainerUploadBlock = delegate.asContainerUpload()

    /** @see ContentBlock._json */
    fun _json(): Optional<JsonValue> = delegate._json()

    /** @see ContentBlock.accept */
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

    /** @see ContentBlock.validate */
    fun validate(): StructuredContentBlock<T> = apply {
        if (isText()) asText().validate() else delegate.validate()
    }

    /** @see ContentBlock.isValid */
    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StructuredContentBlock<*> &&
            outputType == other.outputType &&
            delegate == other.delegate
    }

    override fun hashCode(): Int = Objects.hash(outputType, delegate)

    override fun toString() =
        "StructuredContentBlock{outputType=$outputType, rawContentBlock=$delegate}"

    companion object {

        /** Returns an instance of [StructuredContentBlock] that forwards to [contentBlock]. */
        @JvmSynthetic
        internal fun <T : Any> of(outputType: Class<T>, contentBlock: ContentBlock) =
            StructuredContentBlock(outputType, contentBlock)
    }

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
