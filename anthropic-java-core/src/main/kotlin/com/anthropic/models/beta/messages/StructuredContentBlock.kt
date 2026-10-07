package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional
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
    private val delegate: BetaContentBlock,
) {

    @get:JvmName("rawContentBlock")
    val rawContentBlock: BetaContentBlock
        get() = delegate

    /** @see BetaContentBlock.type */
    fun type(): BetaContentBlock.Type = delegate.type()

    /** @see BetaContentBlock.signature */
    fun signature(): Optional<String> = delegate.signature()

    /** @see BetaContentBlock.id */
    fun id(): Optional<String> = delegate.id()

    /** @see BetaContentBlock.toolUseId */
    fun toolUseId(): Optional<String> = delegate.toolUseId()

    /** @see BetaContentBlock.text */
    fun text(): Optional<StructuredTextBlock<T>> = text

    private val text by lazy { delegate.text().map { StructuredTextBlock(outputType, it) } }

    /** @see BetaContentBlock.thinking */
    fun thinking(): Optional<BetaThinkingBlock> = delegate.thinking()

    /** @see BetaContentBlock.redactedThinking */
    fun redactedThinking(): Optional<BetaRedactedThinkingBlock> = delegate.redactedThinking()

    /** @see BetaContentBlock.toolUse */
    fun toolUse(): Optional<BetaToolUseBlock> = delegate.toolUse()

    /** @see BetaContentBlock.serverToolUse */
    fun serverToolUse(): Optional<BetaServerToolUseBlock> = delegate.serverToolUse()

    /** @see BetaContentBlock.webSearchToolResult */
    fun webSearchToolResult(): Optional<BetaWebSearchToolResultBlock> =
        delegate.webSearchToolResult()

    /** @see BetaContentBlock.webFetchToolResult */
    fun webFetchToolResult(): Optional<BetaWebFetchToolResultBlock> = delegate.webFetchToolResult()

    /** @see BetaContentBlock.advisorToolResult */
    fun advisorToolResult(): Optional<BetaAdvisorToolResultBlock> = delegate.advisorToolResult()

    /** @see BetaContentBlock.codeExecutionToolResult */
    fun codeExecutionToolResult(): Optional<BetaCodeExecutionToolResultBlock> =
        delegate.codeExecutionToolResult()

    /** @see BetaContentBlock.bashCodeExecutionToolResult */
    fun bashCodeExecutionToolResult(): Optional<BetaBashCodeExecutionToolResultBlock> =
        delegate.bashCodeExecutionToolResult()

    /** @see BetaContentBlock.textEditorCodeExecutionToolResult */
    fun textEditorCodeExecutionToolResult(): Optional<BetaTextEditorCodeExecutionToolResultBlock> =
        delegate.textEditorCodeExecutionToolResult()

    /** @see BetaContentBlock.toolSearchToolResult */
    fun toolSearchToolResult(): Optional<BetaToolSearchToolResultBlock> =
        delegate.toolSearchToolResult()

    /** @see BetaContentBlock.mcpToolUse */
    fun mcpToolUse(): Optional<BetaMcpToolUseBlock> = delegate.mcpToolUse()

    /** @see BetaContentBlock.mcpToolResult */
    fun mcpToolResult(): Optional<BetaMcpToolResultBlock> = delegate.mcpToolResult()

    /** @see BetaContentBlock.containerUpload */
    fun containerUpload(): Optional<BetaContainerUploadBlock> = delegate.containerUpload()

    /** @see BetaContentBlock.compaction */
    fun compaction(): Optional<BetaCompactionBlock> = delegate.compaction()

    /** @see BetaContentBlock.fallback */
    fun fallback(): Optional<BetaFallbackBlock> = delegate.fallback()

    /** @see BetaContentBlock.mcpToolListing */
    fun mcpToolListing(): Optional<BetaMcpToolListingBlock> = delegate.mcpToolListing()

    /** @see BetaContentBlock.isText */
    fun isText(): Boolean = text.isPresent

    /** @see BetaContentBlock.isThinking */
    fun isThinking(): Boolean = delegate.isThinking()

    /** @see BetaContentBlock.isRedactedThinking */
    fun isRedactedThinking(): Boolean = delegate.isRedactedThinking()

    /** @see BetaContentBlock.isToolUse */
    fun isToolUse(): Boolean = delegate.isToolUse()

    /** @see BetaContentBlock.isServerToolUse */
    fun isServerToolUse(): Boolean = delegate.isServerToolUse()

    /** @see BetaContentBlock.isWebSearchToolResult */
    fun isWebSearchToolResult(): Boolean = delegate.isWebSearchToolResult()

    /** @see BetaContentBlock.isWebFetchToolResult */
    fun isWebFetchToolResult(): Boolean = delegate.isWebFetchToolResult()

    /** @see BetaContentBlock.isAdvisorToolResult */
    fun isAdvisorToolResult(): Boolean = delegate.isAdvisorToolResult()

    /** @see BetaContentBlock.isCodeExecutionToolResult */
    fun isCodeExecutionToolResult(): Boolean = delegate.isCodeExecutionToolResult()

    /** @see BetaContentBlock.isBashCodeExecutionToolResult */
    fun isBashCodeExecutionToolResult(): Boolean = delegate.isBashCodeExecutionToolResult()

    /** @see BetaContentBlock.isTextEditorCodeExecutionToolResult */
    fun isTextEditorCodeExecutionToolResult(): Boolean =
        delegate.isTextEditorCodeExecutionToolResult()

    /** @see BetaContentBlock.isToolSearchToolResult */
    fun isToolSearchToolResult(): Boolean = delegate.isToolSearchToolResult()

    /** @see BetaContentBlock.isMcpToolUse */
    fun isMcpToolUse(): Boolean = delegate.isMcpToolUse()

    /** @see BetaContentBlock.isMcpToolResult */
    fun isMcpToolResult(): Boolean = delegate.isMcpToolResult()

    /** @see BetaContentBlock.isContainerUpload */
    fun isContainerUpload(): Boolean = delegate.isContainerUpload()

    /** @see BetaContentBlock.isCompaction */
    fun isCompaction(): Boolean = delegate.isCompaction()

    /** @see BetaContentBlock.isFallback */
    fun isFallback(): Boolean = delegate.isFallback()

    /** @see BetaContentBlock.isMcpToolListing */
    fun isMcpToolListing(): Boolean = delegate.isMcpToolListing()

    /** @see BetaContentBlock.asText */
    fun asText(): StructuredTextBlock<T> = text.getOrNull().getOrThrow("text")

    /** @see BetaContentBlock.asThinking */
    fun asThinking(): BetaThinkingBlock = delegate.asThinking()

    /** @see BetaContentBlock.asRedactedThinking */
    fun asRedactedThinking(): BetaRedactedThinkingBlock = delegate.asRedactedThinking()

    /** @see BetaContentBlock.asToolUse */
    fun asToolUse(): BetaToolUseBlock = delegate.asToolUse()

    /** @see BetaContentBlock.asServerToolUse */
    fun asServerToolUse(): BetaServerToolUseBlock = delegate.asServerToolUse()

    /** @see BetaContentBlock.asWebSearchToolResult */
    fun asWebSearchToolResult(): BetaWebSearchToolResultBlock = delegate.asWebSearchToolResult()

    /** @see BetaContentBlock.asWebFetchToolResult */
    fun asWebFetchToolResult(): BetaWebFetchToolResultBlock = delegate.asWebFetchToolResult()

    /** @see BetaContentBlock.asAdvisorToolResult */
    fun asAdvisorToolResult(): BetaAdvisorToolResultBlock = delegate.asAdvisorToolResult()

    /** @see BetaContentBlock.asCodeExecutionToolResult */
    fun asCodeExecutionToolResult(): BetaCodeExecutionToolResultBlock =
        delegate.asCodeExecutionToolResult()

    /** @see BetaContentBlock.asBashCodeExecutionToolResult */
    fun asBashCodeExecutionToolResult(): BetaBashCodeExecutionToolResultBlock =
        delegate.asBashCodeExecutionToolResult()

    /** @see BetaContentBlock.asTextEditorCodeExecutionToolResult */
    fun asTextEditorCodeExecutionToolResult(): BetaTextEditorCodeExecutionToolResultBlock =
        delegate.asTextEditorCodeExecutionToolResult()

    /** @see BetaContentBlock.asToolSearchToolResult */
    fun asToolSearchToolResult(): BetaToolSearchToolResultBlock = delegate.asToolSearchToolResult()

    /** @see BetaContentBlock.asMcpToolUse */
    fun asMcpToolUse(): BetaMcpToolUseBlock = delegate.asMcpToolUse()

    /** @see BetaContentBlock.asMcpToolResult */
    fun asMcpToolResult(): BetaMcpToolResultBlock = delegate.asMcpToolResult()

    /** @see BetaContentBlock.asContainerUpload */
    fun asContainerUpload(): BetaContainerUploadBlock = delegate.asContainerUpload()

    /** @see BetaContentBlock.asCompaction */
    fun asCompaction(): BetaCompactionBlock = delegate.asCompaction()

    /** @see BetaContentBlock.asFallback */
    fun asFallback(): BetaFallbackBlock = delegate.asFallback()

    /** @see BetaContentBlock.asMcpToolListing */
    fun asMcpToolListing(): BetaMcpToolListingBlock = delegate.asMcpToolListing()

    /** @see BetaContentBlock._json */
    fun _json(): Optional<JsonValue> = delegate._json()

    /** @see BetaContentBlock.accept */
    fun <R> accept(visitor: Visitor<T, R>): R =
        when {
            isText() -> visitor.visitText(asText())
            isThinking() -> visitor.visitThinking(asThinking())
            isRedactedThinking() -> visitor.visitRedactedThinking(asRedactedThinking())
            isToolUse() -> visitor.visitToolUse(asToolUse())
            isServerToolUse() -> visitor.visitServerToolUse(asServerToolUse())
            isWebSearchToolResult() -> visitor.visitWebSearchToolResult(asWebSearchToolResult())
            isWebFetchToolResult() -> visitor.visitWebFetchToolResult(asWebFetchToolResult())
            isAdvisorToolResult() -> visitor.visitAdvisorToolResult(asAdvisorToolResult())
            isCodeExecutionToolResult() ->
                visitor.visitCodeExecutionToolResult(asCodeExecutionToolResult())
            isBashCodeExecutionToolResult() ->
                visitor.visitBashCodeExecutionToolResult(asBashCodeExecutionToolResult())
            isTextEditorCodeExecutionToolResult() ->
                visitor.visitTextEditorCodeExecutionToolResult(
                    asTextEditorCodeExecutionToolResult()
                )
            isToolSearchToolResult() -> visitor.visitToolSearchToolResult(asToolSearchToolResult())
            isMcpToolUse() -> visitor.visitMcpToolUse(asMcpToolUse())
            isMcpToolResult() -> visitor.visitMcpToolResult(asMcpToolResult())
            isContainerUpload() -> visitor.visitContainerUpload(asContainerUpload())
            isCompaction() -> visitor.visitCompaction(asCompaction())
            isFallback() -> visitor.visitFallback(asFallback())
            isMcpToolListing() -> visitor.visitMcpToolListing(asMcpToolListing())
            else -> visitor.unknown(_json().getOrNull())
        }

    /** @see BetaContentBlock.validate */
    fun validate(): StructuredContentBlock<T> = apply {
        if (isText()) asText().validate() else delegate.validate()
    }

    /** @see BetaContentBlock.isValid */
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

        /** Returns an instance of [StructuredContentBlock] that forwards to [betaContentBlock]. */
        @JvmSynthetic
        internal fun <T : Any> of(outputType: Class<T>, betaContentBlock: BetaContentBlock) =
            StructuredContentBlock(outputType, betaContentBlock)
    }

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

        /** @see BetaContentBlock.Visitor.visitAdvisorToolResult */
        fun visitAdvisorToolResult(advisorToolResult: BetaAdvisorToolResultBlock): T

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

        /** @see BetaContentBlock.Visitor.visitToolSearchToolResult */
        fun visitToolSearchToolResult(toolSearchToolResult: BetaToolSearchToolResultBlock): T

        /** @see BetaContentBlock.Visitor.visitMcpToolUse */
        fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlock): T

        /** @see BetaContentBlock.Visitor.visitMcpToolResult */
        fun visitMcpToolResult(mcpToolResult: BetaMcpToolResultBlock): T

        /** @see BetaContentBlock.Visitor.visitContainerUpload */
        fun visitContainerUpload(containerUpload: BetaContainerUploadBlock): T

        /** @see BetaContentBlock.Visitor.visitCompaction */
        fun visitCompaction(compaction: BetaCompactionBlock): T

        /** @see BetaContentBlock.Visitor.visitFallback */
        fun visitFallback(fallback: BetaFallbackBlock): T

        /** @see BetaContentBlock.Visitor.visitMcpToolListing */
        fun visitMcpToolListing(mcpToolListing: BetaMcpToolListingBlock): T

        /** @see BetaContentBlock.Visitor.unknown */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlock: $json")
        }
    }
}
