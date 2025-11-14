package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

/**
 * A wrapper for [BetaContentBlock] that provides type-safe access to the [text] when using the
 * _Structured Outputs_ feature to deserialize a JSON response to an instance of an arbitrary class.
 * See the SDK documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class to which the JSON data in the content will be deserialized from
 *   the [StructuredTextBlock] returned when [text] is called.
 */
class StructuredContentBlock<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    @get:JvmName("rawContentBlock") val rawContentBlock: BetaContentBlock,
) {
    private val text by lazy { rawContentBlock.text().map { StructuredTextBlock(outputType, it) } }

    /** @see BetaContentBlock.text */
    fun text(): Optional<StructuredTextBlock<T>> = text

    /** @see BetaContentBlock.thinking */
    fun thinking(): Optional<BetaThinkingBlock> = rawContentBlock.thinking()

    /** @see BetaContentBlock.redactedThinking */
    fun redactedThinking(): Optional<BetaRedactedThinkingBlock> = rawContentBlock.redactedThinking()

    /** @see BetaContentBlock.toolUse */
    fun toolUse(): Optional<BetaToolUseBlock> = rawContentBlock.toolUse()

    /** @see BetaContentBlock.serverToolUse */
    fun serverToolUse(): Optional<BetaServerToolUseBlock> = rawContentBlock.serverToolUse()

    /** @see BetaContentBlock.webSearchToolResult */
    fun webSearchToolResult(): Optional<BetaWebSearchToolResultBlock> =
        rawContentBlock.webSearchToolResult()

    /** @see BetaContentBlock.webFetchToolResult */
    fun webFetchToolResult(): Optional<BetaWebFetchToolResultBlock> =
        rawContentBlock.webFetchToolResult()

    /** @see BetaContentBlock.codeExecutionToolResult */
    fun codeExecutionToolResult(): Optional<BetaCodeExecutionToolResultBlock> =
        rawContentBlock.codeExecutionToolResult()

    /** @see BetaContentBlock.bashCodeExecutionToolResult */
    fun bashCodeExecutionToolResult(): Optional<BetaBashCodeExecutionToolResultBlock> =
        rawContentBlock.bashCodeExecutionToolResult()

    /** @see BetaContentBlock.textEditorCodeExecutionToolResult */
    fun textEditorCodeExecutionToolResult(): Optional<BetaTextEditorCodeExecutionToolResultBlock> =
        rawContentBlock.textEditorCodeExecutionToolResult()

    /** @see BetaContentBlock.mcpToolUse */
    fun mcpToolUse(): Optional<BetaMcpToolUseBlock> = rawContentBlock.mcpToolUse()

    /** @see BetaContentBlock.mcpToolResult */
    fun mcpToolResult(): Optional<BetaMcpToolResultBlock> = rawContentBlock.mcpToolResult()

    /** @see BetaContentBlock.containerUpload */
    fun containerUpload(): Optional<BetaContainerUploadBlock> = rawContentBlock.containerUpload()

    /** @see BetaContentBlock.isText */
    fun isText(): Boolean = text().isPresent

    /** @see BetaContentBlock.isThinking */
    fun isThinking(): Boolean = rawContentBlock.isThinking()

    /** @see BetaContentBlock.isRedactedThinking */
    fun isRedactedThinking(): Boolean = rawContentBlock.isRedactedThinking()

    /** @see BetaContentBlock.isToolUse */
    fun isToolUse(): Boolean = rawContentBlock.isToolUse()

    /** @see BetaContentBlock.isServerToolUse */
    fun isServerToolUse(): Boolean = rawContentBlock.isServerToolUse()

    /** @see BetaContentBlock.isWebSearchToolResult */
    fun isWebSearchToolResult(): Boolean = rawContentBlock.isWebSearchToolResult()

    /** @see BetaContentBlock.isWebFetchToolResult */
    fun isWebFetchToolResult(): Boolean = rawContentBlock.isWebFetchToolResult()

    /** @see BetaContentBlock.isCodeExecutionToolResult */
    fun isCodeExecutionToolResult(): Boolean = rawContentBlock.isCodeExecutionToolResult()

    /** @see BetaContentBlock.isBashCodeExecutionToolResult */
    fun isBashCodeExecutionToolResult(): Boolean = rawContentBlock.isBashCodeExecutionToolResult()

    /** @see BetaContentBlock.isTextEditorCodeExecutionToolResult */
    fun isTextEditorCodeExecutionToolResult(): Boolean =
        rawContentBlock.isTextEditorCodeExecutionToolResult()

    /** @see BetaContentBlock.isMcpToolUse */
    fun isMcpToolUse(): Boolean = rawContentBlock.isMcpToolUse()

    /** @see BetaContentBlock.isMcpToolResult */
    fun isMcpToolResult(): Boolean = rawContentBlock.isMcpToolResult()

    /** @see BetaContentBlock.isContainerUpload */
    fun isContainerUpload(): Boolean = rawContentBlock.isContainerUpload()

    /** @see BetaContentBlock.asText */
    fun asText(): StructuredTextBlock<T> =
        text.getOrElse {
            // Same behavior as `com.anthropic.core.getOrThrow` used by the delegate class.
            throw AnthropicInvalidDataException("`text` is not present")
        }

    /** @see BetaContentBlock.asThinking */
    fun asThinking(): BetaThinkingBlock = rawContentBlock.asThinking()

    /** @see BetaContentBlock.asRedactedThinking */
    fun asRedactedThinking(): BetaRedactedThinkingBlock = rawContentBlock.asRedactedThinking()

    /** @see BetaContentBlock.asToolUse */
    fun asToolUse(): BetaToolUseBlock = rawContentBlock.asToolUse()

    /** @see BetaContentBlock.asServerToolUse */
    fun asServerToolUse(): BetaServerToolUseBlock = rawContentBlock.asServerToolUse()

    /** @see BetaContentBlock.asWebSearchToolResult */
    fun asWebSearchToolResult(): BetaWebSearchToolResultBlock =
        rawContentBlock.asWebSearchToolResult()

    /** @see BetaContentBlock.asWebFetchToolResult */
    fun asWebFetchToolResult(): BetaWebFetchToolResultBlock = rawContentBlock.asWebFetchToolResult()

    /** @see BetaContentBlock.asCodeExecutionToolResult */
    fun asCodeExecutionToolResult(): BetaCodeExecutionToolResultBlock =
        rawContentBlock.asCodeExecutionToolResult()

    /** @see BetaContentBlock.asBashCodeExecutionToolResult */
    fun asBashCodeExecutionToolResult(): BetaBashCodeExecutionToolResultBlock =
        rawContentBlock.asBashCodeExecutionToolResult()

    /** @see BetaContentBlock.asTextEditorCodeExecutionToolResult */
    fun asTextEditorCodeExecutionToolResult(): BetaTextEditorCodeExecutionToolResultBlock =
        rawContentBlock.asTextEditorCodeExecutionToolResult()

    /** @see BetaContentBlock.asMcpToolUse */
    fun asMcpToolUse(): BetaMcpToolUseBlock = rawContentBlock.asMcpToolUse()

    /** @see BetaContentBlock.asMcpToolResult */
    fun asMcpToolResult(): BetaMcpToolResultBlock = rawContentBlock.asMcpToolResult()

    /** @see BetaContentBlock.asContainerUpload */
    fun asContainerUpload(): BetaContainerUploadBlock = rawContentBlock.asContainerUpload()

    /** @see BetaContentBlock._json */
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
            isMcpToolUse() -> visitor.visitMcpToolUse(asMcpToolUse())
            isMcpToolResult() -> visitor.visitMcpToolResult(asMcpToolResult())
            isContainerUpload() -> visitor.visitContainerUpload(asContainerUpload())
            else -> visitor.unknown(_json().getOrNull())
        }

    private var validated: Boolean = false

    /** @see BetaContentBlock.validate */
    fun validate(): StructuredContentBlock<T> = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<T, Unit> {
                override fun visitText(text: StructuredTextBlock<T>) {
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

    /** @see BetaContentBlock.Visitor */
    // In keeping with the delegate's `Visitor<T>`, `T` is used to refer to the return type of each
    // function. `R` (for "response") is used to refer to the output type in the response, which is
    // otherwise named `T` in the outer class, but confusion here is probably preferable to
    // confusion there.
    interface Visitor<R : Any, out T> {
        /** @see BetaContentBlock.Visitor.visitText */
        fun visitText(text: StructuredTextBlock<R>): T

        /** @see BetaContentBlock.Visitor.visitThinking */
        fun visitThinking(thinking: BetaThinkingBlock): T

        /** @see BetaContentBlock.Visitor.visitRedactedThinking */
        fun visitRedactedThinking(redactedThinking: BetaRedactedThinkingBlock): T

        /** @see BetaContentBlock.Visitor.visitToolUse */
        fun visitToolUse(toolUse: BetaToolUseBlock): T

        /** @see BetaContentBlock.Visitor.visitServerToolUse */
        fun visitServerToolUse(serverToolUse: BetaServerToolUseBlock): T

        /** @see BetaContentBlock.Visitor.visitWebSearchToolResult */
        fun visitWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlock): T

        /** @see BetaContentBlock.Visitor.visitWebFetchToolResult */
        fun visitWebFetchToolResult(webFetchToolResult: BetaWebFetchToolResultBlock): T

        /** @see BetaContentBlock.Visitor.visitCodeExecutionToolResult */
        fun visitCodeExecutionToolResult(
            codeExecutionToolResult: BetaCodeExecutionToolResultBlock
        ): T

        /** @see BetaContentBlock.Visitor.visitBashCodeExecutionToolResult */
        fun visitBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock
        ): T

        /** @see BetaContentBlock.Visitor.visitTextEditorCodeExecutionToolResult */
        fun visitTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock
        ): T

        /** @see BetaContentBlock.Visitor.visitMcpToolUse */
        fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlock): T

        /** @see BetaContentBlock.Visitor.visitMcpToolResult */
        fun visitMcpToolResult(mcpToolResult: BetaMcpToolResultBlock): T

        /** @see BetaContentBlock.Visitor.visitContainerUpload */
        fun visitContainerUpload(containerUpload: BetaContainerUploadBlock): T

        /** @see BetaContentBlock.Visitor.unknown */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlock: $json")
        }
    }
}
