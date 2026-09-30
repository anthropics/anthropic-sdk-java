package com.anthropic.models.beta.messages

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
    private val advisorToolResult: BetaAdvisorToolResultBlock? = null,
    private val codeExecutionToolResult: BetaCodeExecutionToolResultBlock? = null,
    private val bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock? = null,
    private val textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock? =
        null,
    private val toolSearchToolResult: BetaToolSearchToolResultBlock? = null,
    private val mcpToolUse: BetaMcpToolUseBlock? = null,
    private val mcpToolResult: BetaMcpToolResultBlock? = null,
    private val containerUpload: BetaContainerUploadBlock? = null,
    private val compaction: BetaCompactionBlock? = null,
    private val fallback: BetaFallbackBlock? = null,
    private val mcpToolListing: BetaMcpToolListingBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): BetaContentBlockParam =
        when {
            text != null -> BetaContentBlockParam.ofText(text.toParam())
            thinking != null -> BetaContentBlockParam.ofThinking(thinking.toParam())
            redactedThinking != null ->
                BetaContentBlockParam.ofRedactedThinking(redactedThinking.toParam())
            toolUse != null -> BetaContentBlockParam.ofToolUse(toolUse.toParam())
            serverToolUse != null -> BetaContentBlockParam.ofServerToolUse(serverToolUse.toParam())
            webSearchToolResult != null ->
                BetaContentBlockParam.ofWebSearchToolResult(webSearchToolResult.toParam())
            webFetchToolResult != null ->
                BetaContentBlockParam.ofWebFetchToolResult(webFetchToolResult.toParam())
            advisorToolResult != null ->
                BetaContentBlockParam.ofAdvisorToolResult(advisorToolResult.toParam())
            codeExecutionToolResult != null ->
                BetaContentBlockParam.ofCodeExecutionToolResult(codeExecutionToolResult.toParam())
            bashCodeExecutionToolResult != null ->
                BetaContentBlockParam.ofBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult.toParam()
                )
            textEditorCodeExecutionToolResult != null ->
                BetaContentBlockParam.ofTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult.toParam()
                )
            toolSearchToolResult != null ->
                BetaContentBlockParam.ofToolSearchToolResult(toolSearchToolResult.toParam())
            mcpToolUse != null -> BetaContentBlockParam.ofMcpToolUse(mcpToolUse.toParam())
            mcpToolResult != null -> BetaContentBlockParam.ofMcpToolResult(mcpToolResult.toParam())
            containerUpload != null ->
                BetaContentBlockParam.ofContainerUpload(containerUpload.toParam())
            compaction != null -> BetaContentBlockParam.ofCompaction(compaction.toParam())
            fallback != null -> BetaContentBlockParam.ofFallback(fallback.toParam())
            mcpToolListing != null ->
                BetaContentBlockParam.ofMcpToolListing(mcpToolListing.toParam())
            else -> throw AnthropicInvalidDataException("Unknown BetaContentBlock: $_json")
        }

    fun type(): Type =
        when {
            text != null -> Type.TEXT
            thinking != null -> Type.THINKING
            redactedThinking != null -> Type.REDACTED_THINKING
            toolUse != null -> Type.TOOL_USE
            serverToolUse != null -> Type.SERVER_TOOL_USE
            webSearchToolResult != null -> Type.WEB_SEARCH_TOOL_RESULT
            webFetchToolResult != null -> Type.WEB_FETCH_TOOL_RESULT
            advisorToolResult != null -> Type.ADVISOR_TOOL_RESULT
            codeExecutionToolResult != null -> Type.CODE_EXECUTION_TOOL_RESULT
            bashCodeExecutionToolResult != null -> Type.BASH_CODE_EXECUTION_TOOL_RESULT
            textEditorCodeExecutionToolResult != null -> Type.TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT
            toolSearchToolResult != null -> Type.TOOL_SEARCH_TOOL_RESULT
            mcpToolUse != null -> Type.MCP_TOOL_USE
            mcpToolResult != null -> Type.MCP_TOOL_RESULT
            containerUpload != null -> Type.CONTAINER_UPLOAD
            compaction != null -> Type.COMPACTION
            fallback != null -> Type.FALLBACK
            mcpToolListing != null -> Type.MCP_TOOL_LISTING
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun signature(): Optional<String> =
        when {
            text != null -> Optional.empty()
            thinking != null -> Optional.of(thinking.signature())
            redactedThinking != null -> Optional.empty()
            toolUse != null -> Optional.empty()
            serverToolUse != null -> Optional.empty()
            webSearchToolResult != null -> Optional.empty()
            webFetchToolResult != null -> Optional.empty()
            advisorToolResult != null -> Optional.empty()
            codeExecutionToolResult != null -> Optional.empty()
            bashCodeExecutionToolResult != null -> Optional.empty()
            textEditorCodeExecutionToolResult != null -> Optional.empty()
            toolSearchToolResult != null -> Optional.empty()
            mcpToolUse != null -> Optional.empty()
            mcpToolResult != null -> Optional.empty()
            containerUpload != null -> Optional.empty()
            compaction != null -> compaction.signature()
            fallback != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            else -> _json.getProperty<String>("signature").asKnown()
        }

    fun id(): Optional<String> =
        when {
            text != null -> Optional.empty()
            thinking != null -> Optional.empty()
            redactedThinking != null -> Optional.empty()
            toolUse != null -> Optional.of(toolUse.id())
            serverToolUse != null -> Optional.of(serverToolUse.id())
            webSearchToolResult != null -> Optional.empty()
            webFetchToolResult != null -> Optional.empty()
            advisorToolResult != null -> Optional.empty()
            codeExecutionToolResult != null -> Optional.empty()
            bashCodeExecutionToolResult != null -> Optional.empty()
            textEditorCodeExecutionToolResult != null -> Optional.empty()
            toolSearchToolResult != null -> Optional.empty()
            mcpToolUse != null -> Optional.of(mcpToolUse.id())
            mcpToolResult != null -> Optional.empty()
            containerUpload != null -> Optional.empty()
            compaction != null -> Optional.empty()
            fallback != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            else -> _json.getProperty<String>("id").asKnown()
        }

    fun toolUseId(): Optional<String> =
        when {
            text != null -> Optional.empty()
            thinking != null -> Optional.empty()
            redactedThinking != null -> Optional.empty()
            toolUse != null -> Optional.empty()
            serverToolUse != null -> Optional.empty()
            webSearchToolResult != null -> Optional.of(webSearchToolResult.toolUseId())
            webFetchToolResult != null -> Optional.of(webFetchToolResult.toolUseId())
            advisorToolResult != null -> Optional.of(advisorToolResult.toolUseId())
            codeExecutionToolResult != null -> Optional.of(codeExecutionToolResult.toolUseId())
            bashCodeExecutionToolResult != null ->
                Optional.of(bashCodeExecutionToolResult.toolUseId())
            textEditorCodeExecutionToolResult != null ->
                Optional.of(textEditorCodeExecutionToolResult.toolUseId())
            toolSearchToolResult != null -> Optional.of(toolSearchToolResult.toolUseId())
            mcpToolUse != null -> Optional.empty()
            mcpToolResult != null -> Optional.of(mcpToolResult.toolUseId())
            containerUpload != null -> Optional.empty()
            compaction != null -> Optional.empty()
            fallback != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            else -> _json.getProperty<String>("tool_use_id").asKnown()
        }

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

    fun advisorToolResult(): Optional<BetaAdvisorToolResultBlock> =
        Optional.ofNullable(advisorToolResult)

    fun codeExecutionToolResult(): Optional<BetaCodeExecutionToolResultBlock> =
        Optional.ofNullable(codeExecutionToolResult)

    fun bashCodeExecutionToolResult(): Optional<BetaBashCodeExecutionToolResultBlock> =
        Optional.ofNullable(bashCodeExecutionToolResult)

    fun textEditorCodeExecutionToolResult(): Optional<BetaTextEditorCodeExecutionToolResultBlock> =
        Optional.ofNullable(textEditorCodeExecutionToolResult)

    fun toolSearchToolResult(): Optional<BetaToolSearchToolResultBlock> =
        Optional.ofNullable(toolSearchToolResult)

    fun mcpToolUse(): Optional<BetaMcpToolUseBlock> = Optional.ofNullable(mcpToolUse)

    fun mcpToolResult(): Optional<BetaMcpToolResultBlock> = Optional.ofNullable(mcpToolResult)

    /** Response model for a file uploaded to the container. */
    fun containerUpload(): Optional<BetaContainerUploadBlock> = Optional.ofNullable(containerUpload)

    /**
     * A compaction block returned when autocompact is triggered.
     *
     * When content is None, it indicates the compaction failed to produce a valid summary (e.g.,
     * malformed output from the model). Clients may round-trip compaction blocks with null content;
     * the server treats them as no-ops.
     */
    fun compaction(): Optional<BetaCompactionBlock> = Optional.ofNullable(compaction)

    /**
     * Marks the point in `content` where one model's output gives way to the next.
     *
     * One block appears per hop where a preceding model actually ran this turn and declined. A turn
     * where no preceding model ran and declined has no such boundary and carries no block — the
     * signal for whether a fallback model served the response is the presence of a
     * `fallback_message` entry in `usage.iterations`, not this block.
     *
     * The block is treated like a server-tool content block for streaming: it arrives via the
     * standard `content_block_start` / `content_block_stop` pair and carries no deltas.
     */
    fun fallback(): Optional<BetaFallbackBlock> = Optional.ofNullable(fallback)

    /**
     * The tool listing the server fetched from an MCP server while producing this response. Send
     * the assistant message back unchanged, this block included, so later requests use this listing
     * instead of asking the MCP server again.
     */
    fun mcpToolListing(): Optional<BetaMcpToolListingBlock> = Optional.ofNullable(mcpToolListing)

    fun isText(): Boolean = text != null

    fun isThinking(): Boolean = thinking != null

    fun isRedactedThinking(): Boolean = redactedThinking != null

    fun isToolUse(): Boolean = toolUse != null

    fun isServerToolUse(): Boolean = serverToolUse != null

    fun isWebSearchToolResult(): Boolean = webSearchToolResult != null

    fun isWebFetchToolResult(): Boolean = webFetchToolResult != null

    fun isAdvisorToolResult(): Boolean = advisorToolResult != null

    fun isCodeExecutionToolResult(): Boolean = codeExecutionToolResult != null

    fun isBashCodeExecutionToolResult(): Boolean = bashCodeExecutionToolResult != null

    fun isTextEditorCodeExecutionToolResult(): Boolean = textEditorCodeExecutionToolResult != null

    fun isToolSearchToolResult(): Boolean = toolSearchToolResult != null

    fun isMcpToolUse(): Boolean = mcpToolUse != null

    fun isMcpToolResult(): Boolean = mcpToolResult != null

    fun isContainerUpload(): Boolean = containerUpload != null

    fun isCompaction(): Boolean = compaction != null

    fun isFallback(): Boolean = fallback != null

    fun isMcpToolListing(): Boolean = mcpToolListing != null

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

    fun asAdvisorToolResult(): BetaAdvisorToolResultBlock =
        advisorToolResult.getOrThrow("advisorToolResult")

    fun asCodeExecutionToolResult(): BetaCodeExecutionToolResultBlock =
        codeExecutionToolResult.getOrThrow("codeExecutionToolResult")

    fun asBashCodeExecutionToolResult(): BetaBashCodeExecutionToolResultBlock =
        bashCodeExecutionToolResult.getOrThrow("bashCodeExecutionToolResult")

    fun asTextEditorCodeExecutionToolResult(): BetaTextEditorCodeExecutionToolResultBlock =
        textEditorCodeExecutionToolResult.getOrThrow("textEditorCodeExecutionToolResult")

    fun asToolSearchToolResult(): BetaToolSearchToolResultBlock =
        toolSearchToolResult.getOrThrow("toolSearchToolResult")

    fun asMcpToolUse(): BetaMcpToolUseBlock = mcpToolUse.getOrThrow("mcpToolUse")

    fun asMcpToolResult(): BetaMcpToolResultBlock = mcpToolResult.getOrThrow("mcpToolResult")

    /** Response model for a file uploaded to the container. */
    fun asContainerUpload(): BetaContainerUploadBlock =
        containerUpload.getOrThrow("containerUpload")

    /**
     * A compaction block returned when autocompact is triggered.
     *
     * When content is None, it indicates the compaction failed to produce a valid summary (e.g.,
     * malformed output from the model). Clients may round-trip compaction blocks with null content;
     * the server treats them as no-ops.
     */
    fun asCompaction(): BetaCompactionBlock = compaction.getOrThrow("compaction")

    /**
     * Marks the point in `content` where one model's output gives way to the next.
     *
     * One block appears per hop where a preceding model actually ran this turn and declined. A turn
     * where no preceding model ran and declined has no such boundary and carries no block — the
     * signal for whether a fallback model served the response is the presence of a
     * `fallback_message` entry in `usage.iterations`, not this block.
     *
     * The block is treated like a server-tool content block for streaming: it arrives via the
     * standard `content_block_start` / `content_block_stop` pair and carries no deltas.
     */
    fun asFallback(): BetaFallbackBlock = fallback.getOrThrow("fallback")

    /**
     * The tool listing the server fetched from an MCP server while producing this response. Send
     * the assistant message back unchanged, this block included, so later requests use this listing
     * instead of asking the MCP server again.
     */
    fun asMcpToolListing(): BetaMcpToolListingBlock = mcpToolListing.getOrThrow("mcpToolListing")

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
     * Optional<String> result = betaContentBlock.accept(new BetaContentBlock.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitText(BetaTextBlock text) {
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
            advisorToolResult != null -> visitor.visitAdvisorToolResult(advisorToolResult)
            codeExecutionToolResult != null ->
                visitor.visitCodeExecutionToolResult(codeExecutionToolResult)
            bashCodeExecutionToolResult != null ->
                visitor.visitBashCodeExecutionToolResult(bashCodeExecutionToolResult)
            textEditorCodeExecutionToolResult != null ->
                visitor.visitTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)
            toolSearchToolResult != null -> visitor.visitToolSearchToolResult(toolSearchToolResult)
            mcpToolUse != null -> visitor.visitMcpToolUse(mcpToolUse)
            mcpToolResult != null -> visitor.visitMcpToolResult(mcpToolResult)
            containerUpload != null -> visitor.visitContainerUpload(containerUpload)
            compaction != null -> visitor.visitCompaction(compaction)
            fallback != null -> visitor.visitFallback(fallback)
            mcpToolListing != null -> visitor.visitMcpToolListing(mcpToolListing)
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
    fun validate(): BetaContentBlock = apply {
        if (validated) {
            return@apply
        }

        when {
            text != null -> text.validate()
            thinking != null -> thinking.validate()
            redactedThinking != null -> redactedThinking.validate()
            toolUse != null -> toolUse.validate()
            serverToolUse != null -> serverToolUse.validate()
            webSearchToolResult != null -> webSearchToolResult.validate()
            webFetchToolResult != null -> webFetchToolResult.validate()
            advisorToolResult != null -> advisorToolResult.validate()
            codeExecutionToolResult != null -> codeExecutionToolResult.validate()
            bashCodeExecutionToolResult != null -> bashCodeExecutionToolResult.validate()
            textEditorCodeExecutionToolResult != null ->
                textEditorCodeExecutionToolResult.validate()
            toolSearchToolResult != null -> toolSearchToolResult.validate()
            mcpToolUse != null -> mcpToolUse.validate()
            mcpToolResult != null -> mcpToolResult.validate()
            containerUpload != null -> containerUpload.validate()
            compaction != null -> compaction.validate()
            fallback != null -> fallback.validate()
            mcpToolListing != null -> mcpToolListing.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaContentBlock: $_json")
        }
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
        when {
            text != null -> text.validity()
            thinking != null -> thinking.validity()
            redactedThinking != null -> redactedThinking.validity()
            toolUse != null -> toolUse.validity()
            serverToolUse != null -> serverToolUse.validity()
            webSearchToolResult != null -> webSearchToolResult.validity()
            webFetchToolResult != null -> webFetchToolResult.validity()
            advisorToolResult != null -> advisorToolResult.validity()
            codeExecutionToolResult != null -> codeExecutionToolResult.validity()
            bashCodeExecutionToolResult != null -> bashCodeExecutionToolResult.validity()
            textEditorCodeExecutionToolResult != null ->
                textEditorCodeExecutionToolResult.validity()
            toolSearchToolResult != null -> toolSearchToolResult.validity()
            mcpToolUse != null -> mcpToolUse.validity()
            mcpToolResult != null -> mcpToolResult.validity()
            containerUpload != null -> containerUpload.validity()
            compaction != null -> compaction.validity()
            fallback != null -> fallback.validity()
            mcpToolListing != null -> mcpToolListing.validity()
            else -> 0
        }

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
            advisorToolResult == other.advisorToolResult &&
            codeExecutionToolResult == other.codeExecutionToolResult &&
            bashCodeExecutionToolResult == other.bashCodeExecutionToolResult &&
            textEditorCodeExecutionToolResult == other.textEditorCodeExecutionToolResult &&
            toolSearchToolResult == other.toolSearchToolResult &&
            mcpToolUse == other.mcpToolUse &&
            mcpToolResult == other.mcpToolResult &&
            containerUpload == other.containerUpload &&
            compaction == other.compaction &&
            fallback == other.fallback &&
            mcpToolListing == other.mcpToolListing
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
            advisorToolResult,
            codeExecutionToolResult,
            bashCodeExecutionToolResult,
            textEditorCodeExecutionToolResult,
            toolSearchToolResult,
            mcpToolUse,
            mcpToolResult,
            containerUpload,
            compaction,
            fallback,
            mcpToolListing,
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
            advisorToolResult != null -> "BetaContentBlock{advisorToolResult=$advisorToolResult}"
            codeExecutionToolResult != null ->
                "BetaContentBlock{codeExecutionToolResult=$codeExecutionToolResult}"
            bashCodeExecutionToolResult != null ->
                "BetaContentBlock{bashCodeExecutionToolResult=$bashCodeExecutionToolResult}"
            textEditorCodeExecutionToolResult != null ->
                "BetaContentBlock{textEditorCodeExecutionToolResult=$textEditorCodeExecutionToolResult}"
            toolSearchToolResult != null ->
                "BetaContentBlock{toolSearchToolResult=$toolSearchToolResult}"
            mcpToolUse != null -> "BetaContentBlock{mcpToolUse=$mcpToolUse}"
            mcpToolResult != null -> "BetaContentBlock{mcpToolResult=$mcpToolResult}"
            containerUpload != null -> "BetaContentBlock{containerUpload=$containerUpload}"
            compaction != null -> "BetaContentBlock{compaction=$compaction}"
            fallback != null -> "BetaContentBlock{fallback=$fallback}"
            mcpToolListing != null -> "BetaContentBlock{mcpToolListing=$mcpToolListing}"
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

        /**
         * Returns an immutable instance of [BetaContentBlock] whose [ofRedactedThinking] variant is
         * built from the given required [data].
         */
        @JvmStatic
        fun ofRedactedThinking(data: String) =
            ofRedactedThinking(BetaRedactedThinkingBlock.of(data))

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
        fun ofAdvisorToolResult(advisorToolResult: BetaAdvisorToolResultBlock) =
            BetaContentBlock(advisorToolResult = advisorToolResult)

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
        fun ofToolSearchToolResult(toolSearchToolResult: BetaToolSearchToolResultBlock) =
            BetaContentBlock(toolSearchToolResult = toolSearchToolResult)

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

        /**
         * Returns an immutable instance of [BetaContentBlock] whose [ofContainerUpload] variant is
         * built from the given required [fileId].
         */
        @JvmStatic
        fun ofContainerUpload(fileId: String) =
            ofContainerUpload(BetaContainerUploadBlock.of(fileId))

        /**
         * A compaction block returned when autocompact is triggered.
         *
         * When content is None, it indicates the compaction failed to produce a valid summary
         * (e.g., malformed output from the model). Clients may round-trip compaction blocks with
         * null content; the server treats them as no-ops.
         */
        @JvmStatic
        fun ofCompaction(compaction: BetaCompactionBlock) =
            BetaContentBlock(compaction = compaction)

        /**
         * Marks the point in `content` where one model's output gives way to the next.
         *
         * One block appears per hop where a preceding model actually ran this turn and declined. A
         * turn where no preceding model ran and declined has no such boundary and carries no block
         * — the signal for whether a fallback model served the response is the presence of a
         * `fallback_message` entry in `usage.iterations`, not this block.
         *
         * The block is treated like a server-tool content block for streaming: it arrives via the
         * standard `content_block_start` / `content_block_stop` pair and carries no deltas.
         */
        @JvmStatic
        fun ofFallback(fallback: BetaFallbackBlock) = BetaContentBlock(fallback = fallback)

        /**
         * The tool listing the server fetched from an MCP server while producing this response.
         * Send the assistant message back unchanged, this block included, so later requests use
         * this listing instead of asking the MCP server again.
         */
        @JvmStatic
        fun ofMcpToolListing(mcpToolListing: BetaMcpToolListingBlock) =
            BetaContentBlock(mcpToolListing = mcpToolListing)
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

        fun visitAdvisorToolResult(advisorToolResult: BetaAdvisorToolResultBlock): T

        fun visitCodeExecutionToolResult(
            codeExecutionToolResult: BetaCodeExecutionToolResultBlock
        ): T

        fun visitBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock
        ): T

        fun visitTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock
        ): T

        fun visitToolSearchToolResult(toolSearchToolResult: BetaToolSearchToolResultBlock): T

        fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlock): T

        fun visitMcpToolResult(mcpToolResult: BetaMcpToolResultBlock): T

        /** Response model for a file uploaded to the container. */
        fun visitContainerUpload(containerUpload: BetaContainerUploadBlock): T

        /**
         * A compaction block returned when autocompact is triggered.
         *
         * When content is None, it indicates the compaction failed to produce a valid summary
         * (e.g., malformed output from the model). Clients may round-trip compaction blocks with
         * null content; the server treats them as no-ops.
         */
        fun visitCompaction(compaction: BetaCompactionBlock): T

        /**
         * Marks the point in `content` where one model's output gives way to the next.
         *
         * One block appears per hop where a preceding model actually ran this turn and declined. A
         * turn where no preceding model ran and declined has no such boundary and carries no block
         * — the signal for whether a fallback model served the response is the presence of a
         * `fallback_message` entry in `usage.iterations`, not this block.
         *
         * The block is treated like a server-tool content block for streaming: it arrives via the
         * standard `content_block_start` / `content_block_stop` pair and carries no deltas.
         */
        fun visitFallback(fallback: BetaFallbackBlock): T

        /**
         * The tool listing the server fetched from an MCP server while producing this response.
         * Send the assistant message back unchanged, this block included, so later requests use
         * this listing instead of asking the MCP server again.
         */
        fun visitMcpToolListing(mcpToolListing: BetaMcpToolListingBlock): T

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
                "advisor_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAdvisorToolResultBlock>())?.let {
                        BetaContentBlock(advisorToolResult = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
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
                "tool_search_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolSearchToolResultBlock>())
                        ?.let { BetaContentBlock(toolSearchToolResult = it, _json = json) }
                        ?: BetaContentBlock(_json = json)
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
                "compaction" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCompactionBlock>())?.let {
                        BetaContentBlock(compaction = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "fallback" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaFallbackBlock>())?.let {
                        BetaContentBlock(fallback = it, _json = json)
                    } ?: BetaContentBlock(_json = json)
                }
                "mcp_tool_listing" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaMcpToolListingBlock>())?.let {
                        BetaContentBlock(mcpToolListing = it, _json = json)
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
                value.advisorToolResult != null -> generator.writeObject(value.advisorToolResult)
                value.codeExecutionToolResult != null ->
                    generator.writeObject(value.codeExecutionToolResult)
                value.bashCodeExecutionToolResult != null ->
                    generator.writeObject(value.bashCodeExecutionToolResult)
                value.textEditorCodeExecutionToolResult != null ->
                    generator.writeObject(value.textEditorCodeExecutionToolResult)
                value.toolSearchToolResult != null ->
                    generator.writeObject(value.toolSearchToolResult)
                value.mcpToolUse != null -> generator.writeObject(value.mcpToolUse)
                value.mcpToolResult != null -> generator.writeObject(value.mcpToolResult)
                value.containerUpload != null -> generator.writeObject(value.containerUpload)
                value.compaction != null -> generator.writeObject(value.compaction)
                value.fallback != null -> generator.writeObject(value.fallback)
                value.mcpToolListing != null -> generator.writeObject(value.mcpToolListing)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlock")
            }
        }
    }

    class Type private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val TEXT = Type(JsonField.of("text"))

            @JvmField val THINKING = Type(JsonField.of("thinking"))

            @JvmField val REDACTED_THINKING = Type(JsonField.of("redacted_thinking"))

            @JvmField val TOOL_USE = Type(JsonField.of("tool_use"))

            @JvmField val SERVER_TOOL_USE = Type(JsonField.of("server_tool_use"))

            @JvmField val WEB_SEARCH_TOOL_RESULT = Type(JsonField.of("web_search_tool_result"))

            @JvmField val WEB_FETCH_TOOL_RESULT = Type(JsonField.of("web_fetch_tool_result"))

            @JvmField val ADVISOR_TOOL_RESULT = Type(JsonField.of("advisor_tool_result"))

            @JvmField
            val CODE_EXECUTION_TOOL_RESULT = Type(JsonField.of("code_execution_tool_result"))

            @JvmField
            val BASH_CODE_EXECUTION_TOOL_RESULT =
                Type(JsonField.of("bash_code_execution_tool_result"))

            @JvmField
            val TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT =
                Type(JsonField.of("text_editor_code_execution_tool_result"))

            @JvmField val TOOL_SEARCH_TOOL_RESULT = Type(JsonField.of("tool_search_tool_result"))

            @JvmField val MCP_TOOL_USE = Type(JsonField.of("mcp_tool_use"))

            @JvmField val MCP_TOOL_RESULT = Type(JsonField.of("mcp_tool_result"))

            @JvmField val CONTAINER_UPLOAD = Type(JsonField.of("container_upload"))

            @JvmField val COMPACTION = Type(JsonField.of("compaction"))

            @JvmField val FALLBACK = Type(JsonField.of("fallback"))

            @JvmField val MCP_TOOL_LISTING = Type(JsonField.of("mcp_tool_listing"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "text" -> TEXT
                    "thinking" -> THINKING
                    "redacted_thinking" -> REDACTED_THINKING
                    "tool_use" -> TOOL_USE
                    "server_tool_use" -> SERVER_TOOL_USE
                    "web_search_tool_result" -> WEB_SEARCH_TOOL_RESULT
                    "web_fetch_tool_result" -> WEB_FETCH_TOOL_RESULT
                    "advisor_tool_result" -> ADVISOR_TOOL_RESULT
                    "code_execution_tool_result" -> CODE_EXECUTION_TOOL_RESULT
                    "bash_code_execution_tool_result" -> BASH_CODE_EXECUTION_TOOL_RESULT
                    "text_editor_code_execution_tool_result" ->
                        TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT
                    "tool_search_tool_result" -> TOOL_SEARCH_TOOL_RESULT
                    "mcp_tool_use" -> MCP_TOOL_USE
                    "mcp_tool_result" -> MCP_TOOL_RESULT
                    "container_upload" -> CONTAINER_UPLOAD
                    "compaction" -> COMPACTION
                    "fallback" -> FALLBACK
                    "mcp_tool_listing" -> MCP_TOOL_LISTING
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
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
            ADVISOR_TOOL_RESULT,
            CODE_EXECUTION_TOOL_RESULT,
            BASH_CODE_EXECUTION_TOOL_RESULT,
            TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT,
            TOOL_SEARCH_TOOL_RESULT,
            MCP_TOOL_USE,
            MCP_TOOL_RESULT,
            CONTAINER_UPLOAD,
            COMPACTION,
            FALLBACK,
            MCP_TOOL_LISTING,
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
            ADVISOR_TOOL_RESULT,
            CODE_EXECUTION_TOOL_RESULT,
            BASH_CODE_EXECUTION_TOOL_RESULT,
            TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT,
            TOOL_SEARCH_TOOL_RESULT,
            MCP_TOOL_USE,
            MCP_TOOL_RESULT,
            CONTAINER_UPLOAD,
            COMPACTION,
            FALLBACK,
            MCP_TOOL_LISTING,
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
                ADVISOR_TOOL_RESULT -> Value.ADVISOR_TOOL_RESULT
                CODE_EXECUTION_TOOL_RESULT -> Value.CODE_EXECUTION_TOOL_RESULT
                BASH_CODE_EXECUTION_TOOL_RESULT -> Value.BASH_CODE_EXECUTION_TOOL_RESULT
                TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT ->
                    Value.TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT
                TOOL_SEARCH_TOOL_RESULT -> Value.TOOL_SEARCH_TOOL_RESULT
                MCP_TOOL_USE -> Value.MCP_TOOL_USE
                MCP_TOOL_RESULT -> Value.MCP_TOOL_RESULT
                CONTAINER_UPLOAD -> Value.CONTAINER_UPLOAD
                COMPACTION -> Value.COMPACTION
                FALLBACK -> Value.FALLBACK
                MCP_TOOL_LISTING -> Value.MCP_TOOL_LISTING
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
                ADVISOR_TOOL_RESULT -> Known.ADVISOR_TOOL_RESULT
                CODE_EXECUTION_TOOL_RESULT -> Known.CODE_EXECUTION_TOOL_RESULT
                BASH_CODE_EXECUTION_TOOL_RESULT -> Known.BASH_CODE_EXECUTION_TOOL_RESULT
                TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT ->
                    Known.TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT
                TOOL_SEARCH_TOOL_RESULT -> Known.TOOL_SEARCH_TOOL_RESULT
                MCP_TOOL_USE -> Known.MCP_TOOL_USE
                MCP_TOOL_RESULT -> Known.MCP_TOOL_RESULT
                CONTAINER_UPLOAD -> Known.CONTAINER_UPLOAD
                COMPACTION -> Known.COMPACTION
                FALLBACK -> Known.FALLBACK
                MCP_TOOL_LISTING -> Known.MCP_TOOL_LISTING
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
