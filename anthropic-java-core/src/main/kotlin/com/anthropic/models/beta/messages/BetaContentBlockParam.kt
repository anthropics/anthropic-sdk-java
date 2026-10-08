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

@JsonDeserialize(using = BetaContentBlockParam.Deserializer::class)
@JsonSerialize(using = BetaContentBlockParam.Serializer::class)
class BetaContentBlockParam
private constructor(
    private val text: BetaTextBlockParam? = null,
    private val image: BetaImageBlockParam? = null,
    private val document: BetaRequestDocumentBlock? = null,
    private val searchResult: BetaSearchResultBlockParam? = null,
    private val thinking: BetaThinkingBlockParam? = null,
    private val redactedThinking: BetaRedactedThinkingBlockParam? = null,
    private val toolUse: BetaToolUseBlockParam? = null,
    private val toolResult: BetaToolResultBlockParam? = null,
    private val serverToolUse: BetaServerToolUseBlockParam? = null,
    private val webSearchToolResult: BetaWebSearchToolResultBlockParam? = null,
    private val webFetchToolResult: BetaWebFetchToolResultBlockParam? = null,
    private val advisorToolResult: BetaAdvisorToolResultBlockParam? = null,
    private val codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam? = null,
    private val bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam? = null,
    private val textEditorCodeExecutionToolResult:
        BetaTextEditorCodeExecutionToolResultBlockParam? =
        null,
    private val toolSearchToolResult: BetaToolSearchToolResultBlockParam? = null,
    private val mcpToolUse: BetaMcpToolUseBlockParam? = null,
    private val mcpToolResult: BetaRequestMcpToolResultBlockParam? = null,
    private val containerUpload: BetaContainerUploadBlockParam? = null,
    private val compaction: BetaCompactionBlockParam? = null,
    private val toolAddition: BetaRequestToolAdditionBlock? = null,
    private val toolRemoval: BetaRequestToolRemovalBlock? = null,
    private val mcpToolListing: BetaMcpToolListingBlockParam? = null,
    private val fallback: BetaFallbackBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            text != null -> Type.TEXT
            image != null -> Type.IMAGE
            document != null -> Type.DOCUMENT
            searchResult != null -> Type.SEARCH_RESULT
            thinking != null -> Type.THINKING
            redactedThinking != null -> Type.REDACTED_THINKING
            toolUse != null -> Type.TOOL_USE
            toolResult != null -> Type.TOOL_RESULT
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
            toolAddition != null -> Type.TOOL_ADDITION
            toolRemoval != null -> Type.TOOL_REMOVAL
            mcpToolListing != null -> Type.MCP_TOOL_LISTING
            fallback != null -> Type.FALLBACK
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        when {
            text != null -> text.cacheControl()
            image != null -> image.cacheControl()
            document != null -> document.cacheControl()
            searchResult != null -> searchResult.cacheControl()
            thinking != null -> Optional.empty()
            redactedThinking != null -> Optional.empty()
            toolUse != null -> toolUse.cacheControl()
            toolResult != null -> toolResult.cacheControl()
            serverToolUse != null -> serverToolUse.cacheControl()
            webSearchToolResult != null -> webSearchToolResult.cacheControl()
            webFetchToolResult != null -> webFetchToolResult.cacheControl()
            advisorToolResult != null -> advisorToolResult.cacheControl()
            codeExecutionToolResult != null -> codeExecutionToolResult.cacheControl()
            bashCodeExecutionToolResult != null -> bashCodeExecutionToolResult.cacheControl()
            textEditorCodeExecutionToolResult != null ->
                textEditorCodeExecutionToolResult.cacheControl()
            toolSearchToolResult != null -> toolSearchToolResult.cacheControl()
            mcpToolUse != null -> mcpToolUse.cacheControl()
            mcpToolResult != null -> mcpToolResult.cacheControl()
            containerUpload != null -> containerUpload.cacheControl()
            compaction != null -> compaction.cacheControl()
            toolAddition != null -> toolAddition.cacheControl()
            toolRemoval != null -> toolRemoval.cacheControl()
            mcpToolListing != null -> Optional.empty()
            fallback != null -> Optional.empty()
            else -> _json.getProperty<BetaCacheControlEphemeral>("cache_control").asKnown()
        }

    fun title(): Optional<String> =
        when {
            text != null -> Optional.empty()
            image != null -> Optional.empty()
            document != null -> document.title()
            searchResult != null -> Optional.of(searchResult.title())
            thinking != null -> Optional.empty()
            redactedThinking != null -> Optional.empty()
            toolUse != null -> Optional.empty()
            toolResult != null -> Optional.empty()
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
            compaction != null -> Optional.empty()
            toolAddition != null -> Optional.empty()
            toolRemoval != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            fallback != null -> Optional.empty()
            else -> _json.getProperty<String>("title").asKnown()
        }

    fun signature(): Optional<String> =
        when {
            text != null -> Optional.empty()
            image != null -> Optional.empty()
            document != null -> Optional.empty()
            searchResult != null -> Optional.empty()
            thinking != null -> Optional.of(thinking.signature())
            redactedThinking != null -> Optional.empty()
            toolUse != null -> Optional.empty()
            toolResult != null -> Optional.empty()
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
            toolAddition != null -> Optional.empty()
            toolRemoval != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            fallback != null -> Optional.empty()
            else -> _json.getProperty<String>("signature").asKnown()
        }

    fun id(): Optional<String> =
        when {
            text != null -> Optional.empty()
            image != null -> Optional.empty()
            document != null -> Optional.empty()
            searchResult != null -> Optional.empty()
            thinking != null -> Optional.empty()
            redactedThinking != null -> Optional.empty()
            toolUse != null -> Optional.of(toolUse.id())
            toolResult != null -> Optional.empty()
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
            toolAddition != null -> Optional.empty()
            toolRemoval != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            fallback != null -> Optional.empty()
            else -> _json.getProperty<String>("id").asKnown()
        }

    fun toolsetName(): Optional<String> =
        when {
            text != null -> Optional.empty()
            image != null -> Optional.empty()
            document != null -> Optional.empty()
            searchResult != null -> Optional.empty()
            thinking != null -> Optional.empty()
            redactedThinking != null -> Optional.empty()
            toolUse != null -> toolUse.toolsetName()
            toolResult != null -> toolResult.toolsetName()
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
            compaction != null -> Optional.empty()
            toolAddition != null -> Optional.empty()
            toolRemoval != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            fallback != null -> Optional.empty()
            else -> _json.getProperty<String>("toolset_name").asKnown()
        }

    fun toolUseId(): Optional<String> =
        when {
            text != null -> Optional.empty()
            image != null -> Optional.empty()
            document != null -> Optional.empty()
            searchResult != null -> Optional.empty()
            thinking != null -> Optional.empty()
            redactedThinking != null -> Optional.empty()
            toolUse != null -> Optional.empty()
            toolResult != null -> Optional.of(toolResult.toolUseId())
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
            toolAddition != null -> Optional.empty()
            toolRemoval != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            fallback != null -> Optional.empty()
            else -> _json.getProperty<String>("tool_use_id").asKnown()
        }

    fun isError(): Optional<Boolean> =
        when {
            text != null -> Optional.empty()
            image != null -> Optional.empty()
            document != null -> Optional.empty()
            searchResult != null -> Optional.empty()
            thinking != null -> Optional.empty()
            redactedThinking != null -> Optional.empty()
            toolUse != null -> Optional.empty()
            toolResult != null -> toolResult.isError()
            serverToolUse != null -> Optional.empty()
            webSearchToolResult != null -> Optional.empty()
            webFetchToolResult != null -> Optional.empty()
            advisorToolResult != null -> Optional.empty()
            codeExecutionToolResult != null -> Optional.empty()
            bashCodeExecutionToolResult != null -> Optional.empty()
            textEditorCodeExecutionToolResult != null -> Optional.empty()
            toolSearchToolResult != null -> Optional.empty()
            mcpToolUse != null -> Optional.empty()
            mcpToolResult != null -> mcpToolResult.isError()
            containerUpload != null -> Optional.empty()
            compaction != null -> Optional.empty()
            toolAddition != null -> Optional.empty()
            toolRemoval != null -> Optional.empty()
            mcpToolListing != null -> Optional.empty()
            fallback != null -> Optional.empty()
            else -> _json.getProperty<Boolean>("is_error").asKnown()
        }

    /** Regular text content. */
    fun text(): Optional<BetaTextBlockParam> = Optional.ofNullable(text)

    /** Image content specified directly as base64 data or as a reference via a URL. */
    fun image(): Optional<BetaImageBlockParam> = Optional.ofNullable(image)

    /**
     * Document content, either specified directly as base64 data, as text, or as a reference via a
     * URL.
     */
    fun document(): Optional<BetaRequestDocumentBlock> = Optional.ofNullable(document)

    /** A search result block containing source, title, and content from search operations. */
    fun searchResult(): Optional<BetaSearchResultBlockParam> = Optional.ofNullable(searchResult)

    /** A block specifying internal thinking by the model. */
    fun thinking(): Optional<BetaThinkingBlockParam> = Optional.ofNullable(thinking)

    /** A block specifying internal, redacted thinking by the model. */
    fun redactedThinking(): Optional<BetaRedactedThinkingBlockParam> =
        Optional.ofNullable(redactedThinking)

    /** A block indicating a tool use by the model. */
    fun toolUse(): Optional<BetaToolUseBlockParam> = Optional.ofNullable(toolUse)

    /** A block specifying the results of a tool use by the model. */
    fun toolResult(): Optional<BetaToolResultBlockParam> = Optional.ofNullable(toolResult)

    fun serverToolUse(): Optional<BetaServerToolUseBlockParam> = Optional.ofNullable(serverToolUse)

    fun webSearchToolResult(): Optional<BetaWebSearchToolResultBlockParam> =
        Optional.ofNullable(webSearchToolResult)

    fun webFetchToolResult(): Optional<BetaWebFetchToolResultBlockParam> =
        Optional.ofNullable(webFetchToolResult)

    fun advisorToolResult(): Optional<BetaAdvisorToolResultBlockParam> =
        Optional.ofNullable(advisorToolResult)

    fun codeExecutionToolResult(): Optional<BetaCodeExecutionToolResultBlockParam> =
        Optional.ofNullable(codeExecutionToolResult)

    fun bashCodeExecutionToolResult(): Optional<BetaBashCodeExecutionToolResultBlockParam> =
        Optional.ofNullable(bashCodeExecutionToolResult)

    fun textEditorCodeExecutionToolResult():
        Optional<BetaTextEditorCodeExecutionToolResultBlockParam> =
        Optional.ofNullable(textEditorCodeExecutionToolResult)

    fun toolSearchToolResult(): Optional<BetaToolSearchToolResultBlockParam> =
        Optional.ofNullable(toolSearchToolResult)

    fun mcpToolUse(): Optional<BetaMcpToolUseBlockParam> = Optional.ofNullable(mcpToolUse)

    fun mcpToolResult(): Optional<BetaRequestMcpToolResultBlockParam> =
        Optional.ofNullable(mcpToolResult)

    /**
     * A content block that represents a file to be uploaded to the container Files uploaded via
     * this block will be available in the container's input directory.
     */
    fun containerUpload(): Optional<BetaContainerUploadBlockParam> =
        Optional.ofNullable(containerUpload)

    /**
     * A compaction block containing summary of previous context.
     *
     * Users should round-trip these blocks from responses to subsequent requests to maintain
     * context across compaction boundaries.
     *
     * When content is None, the block represents a failed compaction. The server treats these as
     * no-ops. Empty string content is not allowed.
     */
    fun compaction(): Optional<BetaCompactionBlockParam> = Optional.ofNullable(compaction)

    /**
     * Mid-conversation directive to make a tool available.
     *
     * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's ``tools``. Under
     * the ``inline-tools-2026-09-15`` beta it may instead be a reference to a tool defined earlier
     * in ``messages``, or a ``tool_definition`` object that carries an inline tool definition in
     * ``definition`` (the same object a ``tools`` entry holds). An ``mcp_toolset`` definition also
     * requires the ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point
     * in the conversation onward.
     */
    fun toolAddition(): Optional<BetaRequestToolAdditionBlock> = Optional.ofNullable(toolAddition)

    /**
     * Mid-conversation directive to withdraw a tool.
     *
     * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's ``tools``
     * or defined earlier in ``messages``. It is no longer offered to the model from this point in
     * the conversation onward.
     */
    fun toolRemoval(): Optional<BetaRequestToolRemovalBlock> = Optional.ofNullable(toolRemoval)

    /**
     * The tool listing an MCP server returned while an earlier response was produced, as that
     * response carried it. Send the assistant message back unchanged, this block included, and the
     * server uses this listing for the matching `mcp_toolset` instead of asking the MCP server
     * again.
     */
    fun mcpToolListing(): Optional<BetaMcpToolListingBlockParam> =
        Optional.ofNullable(mcpToolListing)

    /**
     * A `fallback` block echoed back from a prior response.
     *
     * Accepted in `messages[].content` and not rendered into the prompt; not validated against the
     * request's `fallbacks` chain or top-level `model`.
     *
     * Echo the assistant turn back verbatim, including this block in its original position. The
     * block marks the boundary between content produced before and after a fallback hop, and the
     * server relies on that boundary to validate the turn: when thinking runs flank the boundary,
     * omitting the block merges them into one span the server cannot validate (the request is
     * rejected), and moving it into the middle of a single run is likewise rejected; between
     * non-thinking blocks the block's placement has no validation effect.
     */
    fun fallback(): Optional<BetaFallbackBlockParam> = Optional.ofNullable(fallback)

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun isDocument(): Boolean = document != null

    fun isSearchResult(): Boolean = searchResult != null

    fun isThinking(): Boolean = thinking != null

    fun isRedactedThinking(): Boolean = redactedThinking != null

    fun isToolUse(): Boolean = toolUse != null

    fun isToolResult(): Boolean = toolResult != null

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

    fun isToolAddition(): Boolean = toolAddition != null

    fun isToolRemoval(): Boolean = toolRemoval != null

    fun isMcpToolListing(): Boolean = mcpToolListing != null

    fun isFallback(): Boolean = fallback != null

    /** Regular text content. */
    fun asText(): BetaTextBlockParam = text.getOrThrow("text")

    /** Image content specified directly as base64 data or as a reference via a URL. */
    fun asImage(): BetaImageBlockParam = image.getOrThrow("image")

    /**
     * Document content, either specified directly as base64 data, as text, or as a reference via a
     * URL.
     */
    fun asDocument(): BetaRequestDocumentBlock = document.getOrThrow("document")

    /** A search result block containing source, title, and content from search operations. */
    fun asSearchResult(): BetaSearchResultBlockParam = searchResult.getOrThrow("searchResult")

    /** A block specifying internal thinking by the model. */
    fun asThinking(): BetaThinkingBlockParam = thinking.getOrThrow("thinking")

    /** A block specifying internal, redacted thinking by the model. */
    fun asRedactedThinking(): BetaRedactedThinkingBlockParam =
        redactedThinking.getOrThrow("redactedThinking")

    /** A block indicating a tool use by the model. */
    fun asToolUse(): BetaToolUseBlockParam = toolUse.getOrThrow("toolUse")

    /** A block specifying the results of a tool use by the model. */
    fun asToolResult(): BetaToolResultBlockParam = toolResult.getOrThrow("toolResult")

    fun asServerToolUse(): BetaServerToolUseBlockParam = serverToolUse.getOrThrow("serverToolUse")

    fun asWebSearchToolResult(): BetaWebSearchToolResultBlockParam =
        webSearchToolResult.getOrThrow("webSearchToolResult")

    fun asWebFetchToolResult(): BetaWebFetchToolResultBlockParam =
        webFetchToolResult.getOrThrow("webFetchToolResult")

    fun asAdvisorToolResult(): BetaAdvisorToolResultBlockParam =
        advisorToolResult.getOrThrow("advisorToolResult")

    fun asCodeExecutionToolResult(): BetaCodeExecutionToolResultBlockParam =
        codeExecutionToolResult.getOrThrow("codeExecutionToolResult")

    fun asBashCodeExecutionToolResult(): BetaBashCodeExecutionToolResultBlockParam =
        bashCodeExecutionToolResult.getOrThrow("bashCodeExecutionToolResult")

    fun asTextEditorCodeExecutionToolResult(): BetaTextEditorCodeExecutionToolResultBlockParam =
        textEditorCodeExecutionToolResult.getOrThrow("textEditorCodeExecutionToolResult")

    fun asToolSearchToolResult(): BetaToolSearchToolResultBlockParam =
        toolSearchToolResult.getOrThrow("toolSearchToolResult")

    fun asMcpToolUse(): BetaMcpToolUseBlockParam = mcpToolUse.getOrThrow("mcpToolUse")

    fun asMcpToolResult(): BetaRequestMcpToolResultBlockParam =
        mcpToolResult.getOrThrow("mcpToolResult")

    /**
     * A content block that represents a file to be uploaded to the container Files uploaded via
     * this block will be available in the container's input directory.
     */
    fun asContainerUpload(): BetaContainerUploadBlockParam =
        containerUpload.getOrThrow("containerUpload")

    /**
     * A compaction block containing summary of previous context.
     *
     * Users should round-trip these blocks from responses to subsequent requests to maintain
     * context across compaction boundaries.
     *
     * When content is None, the block represents a failed compaction. The server treats these as
     * no-ops. Empty string content is not allowed.
     */
    fun asCompaction(): BetaCompactionBlockParam = compaction.getOrThrow("compaction")

    /**
     * Mid-conversation directive to make a tool available.
     *
     * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's ``tools``. Under
     * the ``inline-tools-2026-09-15`` beta it may instead be a reference to a tool defined earlier
     * in ``messages``, or a ``tool_definition`` object that carries an inline tool definition in
     * ``definition`` (the same object a ``tools`` entry holds). An ``mcp_toolset`` definition also
     * requires the ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point
     * in the conversation onward.
     */
    fun asToolAddition(): BetaRequestToolAdditionBlock = toolAddition.getOrThrow("toolAddition")

    /**
     * Mid-conversation directive to withdraw a tool.
     *
     * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's ``tools``
     * or defined earlier in ``messages``. It is no longer offered to the model from this point in
     * the conversation onward.
     */
    fun asToolRemoval(): BetaRequestToolRemovalBlock = toolRemoval.getOrThrow("toolRemoval")

    /**
     * The tool listing an MCP server returned while an earlier response was produced, as that
     * response carried it. Send the assistant message back unchanged, this block included, and the
     * server uses this listing for the matching `mcp_toolset` instead of asking the MCP server
     * again.
     */
    fun asMcpToolListing(): BetaMcpToolListingBlockParam =
        mcpToolListing.getOrThrow("mcpToolListing")

    /**
     * A `fallback` block echoed back from a prior response.
     *
     * Accepted in `messages[].content` and not rendered into the prompt; not validated against the
     * request's `fallbacks` chain or top-level `model`.
     *
     * Echo the assistant turn back verbatim, including this block in its original position. The
     * block marks the boundary between content produced before and after a fallback hop, and the
     * server relies on that boundary to validate the turn: when thinking runs flank the boundary,
     * omitting the block merges them into one span the server cannot validate (the request is
     * rejected), and moving it into the middle of a single run is likewise rejected; between
     * non-thinking blocks the block's placement has no validation effect.
     */
    fun asFallback(): BetaFallbackBlockParam = fallback.getOrThrow("fallback")

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
     * Optional<String> result = betaContentBlockParam.accept(new BetaContentBlockParam.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitText(BetaTextBlockParam text) {
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
            image != null -> visitor.visitImage(image)
            document != null -> visitor.visitDocument(document)
            searchResult != null -> visitor.visitSearchResult(searchResult)
            thinking != null -> visitor.visitThinking(thinking)
            redactedThinking != null -> visitor.visitRedactedThinking(redactedThinking)
            toolUse != null -> visitor.visitToolUse(toolUse)
            toolResult != null -> visitor.visitToolResult(toolResult)
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
            toolAddition != null -> visitor.visitToolAddition(toolAddition)
            toolRemoval != null -> visitor.visitToolRemoval(toolRemoval)
            mcpToolListing != null -> visitor.visitMcpToolListing(mcpToolListing)
            fallback != null -> visitor.visitFallback(fallback)
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
    fun validate(): BetaContentBlockParam = apply {
        if (validated) {
            return@apply
        }

        when {
            text != null -> text.validate()
            image != null -> image.validate()
            document != null -> document.validate()
            searchResult != null -> searchResult.validate()
            thinking != null -> thinking.validate()
            redactedThinking != null -> redactedThinking.validate()
            toolUse != null -> toolUse.validate()
            toolResult != null -> toolResult.validate()
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
            toolAddition != null -> toolAddition.validate()
            toolRemoval != null -> toolRemoval.validate()
            mcpToolListing != null -> mcpToolListing.validate()
            fallback != null -> fallback.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaContentBlockParam: $_json")
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
            image != null -> image.validity()
            document != null -> document.validity()
            searchResult != null -> searchResult.validity()
            thinking != null -> thinking.validity()
            redactedThinking != null -> redactedThinking.validity()
            toolUse != null -> toolUse.validity()
            toolResult != null -> toolResult.validity()
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
            toolAddition != null -> toolAddition.validity()
            toolRemoval != null -> toolRemoval.validity()
            mcpToolListing != null -> mcpToolListing.validity()
            fallback != null -> fallback.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContentBlockParam &&
            text == other.text &&
            image == other.image &&
            document == other.document &&
            searchResult == other.searchResult &&
            thinking == other.thinking &&
            redactedThinking == other.redactedThinking &&
            toolUse == other.toolUse &&
            toolResult == other.toolResult &&
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
            toolAddition == other.toolAddition &&
            toolRemoval == other.toolRemoval &&
            mcpToolListing == other.mcpToolListing &&
            fallback == other.fallback
    }

    override fun hashCode(): Int =
        Objects.hash(
            text,
            image,
            document,
            searchResult,
            thinking,
            redactedThinking,
            toolUse,
            toolResult,
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
            toolAddition,
            toolRemoval,
            mcpToolListing,
            fallback,
        )

    override fun toString(): String =
        when {
            text != null -> "BetaContentBlockParam{text=$text}"
            image != null -> "BetaContentBlockParam{image=$image}"
            document != null -> "BetaContentBlockParam{document=$document}"
            searchResult != null -> "BetaContentBlockParam{searchResult=$searchResult}"
            thinking != null -> "BetaContentBlockParam{thinking=$thinking}"
            redactedThinking != null -> "BetaContentBlockParam{redactedThinking=$redactedThinking}"
            toolUse != null -> "BetaContentBlockParam{toolUse=$toolUse}"
            toolResult != null -> "BetaContentBlockParam{toolResult=$toolResult}"
            serverToolUse != null -> "BetaContentBlockParam{serverToolUse=$serverToolUse}"
            webSearchToolResult != null ->
                "BetaContentBlockParam{webSearchToolResult=$webSearchToolResult}"
            webFetchToolResult != null ->
                "BetaContentBlockParam{webFetchToolResult=$webFetchToolResult}"
            advisorToolResult != null ->
                "BetaContentBlockParam{advisorToolResult=$advisorToolResult}"
            codeExecutionToolResult != null ->
                "BetaContentBlockParam{codeExecutionToolResult=$codeExecutionToolResult}"
            bashCodeExecutionToolResult != null ->
                "BetaContentBlockParam{bashCodeExecutionToolResult=$bashCodeExecutionToolResult}"
            textEditorCodeExecutionToolResult != null ->
                "BetaContentBlockParam{textEditorCodeExecutionToolResult=$textEditorCodeExecutionToolResult}"
            toolSearchToolResult != null ->
                "BetaContentBlockParam{toolSearchToolResult=$toolSearchToolResult}"
            mcpToolUse != null -> "BetaContentBlockParam{mcpToolUse=$mcpToolUse}"
            mcpToolResult != null -> "BetaContentBlockParam{mcpToolResult=$mcpToolResult}"
            containerUpload != null -> "BetaContentBlockParam{containerUpload=$containerUpload}"
            compaction != null -> "BetaContentBlockParam{compaction=$compaction}"
            toolAddition != null -> "BetaContentBlockParam{toolAddition=$toolAddition}"
            toolRemoval != null -> "BetaContentBlockParam{toolRemoval=$toolRemoval}"
            mcpToolListing != null -> "BetaContentBlockParam{mcpToolListing=$mcpToolListing}"
            fallback != null -> "BetaContentBlockParam{fallback=$fallback}"
            _json != null -> "BetaContentBlockParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlockParam")
        }

    companion object {

        /** Regular text content. */
        @JvmStatic fun ofText(text: BetaTextBlockParam) = BetaContentBlockParam(text = text)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofText] variant is built
         * from the given required [text].
         */
        @JvmStatic fun ofText(text: String) = ofText(BetaTextBlockParam.of(text))

        /** Image content specified directly as base64 data or as a reference via a URL. */
        @JvmStatic fun ofImage(image: BetaImageBlockParam) = BetaContentBlockParam(image = image)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofImage] variant is built
         * from the given required [source].
         */
        @JvmStatic
        fun ofImage(source: BetaImageBlockParam.Source) = ofImage(BetaImageBlockParam.of(source))

        /** Alias for calling [ofImage] with `BetaImageBlockParam.Source.ofBase64(base64)`. */
        @JvmStatic
        fun ofImage(base64: BetaBase64ImageSource) =
            ofImage(BetaImageBlockParam.Source.ofBase64(base64))

        /** Alias for calling [ofImage] with `BetaImageBlockParam.Source.ofUrl(url)`. */
        @JvmStatic
        fun ofImage(url: BetaUrlImageSource) = ofImage(BetaImageBlockParam.Source.ofUrl(url))

        /** Alias for calling [ofImage] with `BetaImageBlockParam.Source.ofFile(file)`. */
        @JvmStatic
        fun ofImage(file: BetaFileImageSource) = ofImage(BetaImageBlockParam.Source.ofFile(file))

        /**
         * Document content, either specified directly as base64 data, as text, or as a reference
         * via a URL.
         */
        @JvmStatic
        fun ofDocument(document: BetaRequestDocumentBlock) =
            BetaContentBlockParam(document = document)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofDocument] variant is
         * built from the given required [source].
         */
        @JvmStatic
        fun ofDocument(source: BetaRequestDocumentBlock.Source) =
            ofDocument(BetaRequestDocumentBlock.of(source))

        /**
         * Alias for calling [ofDocument] with `BetaRequestDocumentBlock.Source.ofBase64(base64)`.
         */
        @JvmStatic
        fun ofDocument(base64: BetaBase64PdfSource) =
            ofDocument(BetaRequestDocumentBlock.Source.ofBase64(base64))

        /** Alias for calling [ofDocument] with `BetaRequestDocumentBlock.Source.ofText(text)`. */
        @JvmStatic
        fun ofDocument(text: BetaPlainTextSource) =
            ofDocument(BetaRequestDocumentBlock.Source.ofText(text))

        /**
         * Alias for calling [ofDocument] with `BetaRequestDocumentBlock.Source.ofContent(content)`.
         */
        @JvmStatic
        fun ofDocument(content: BetaContentBlockSource) =
            ofDocument(BetaRequestDocumentBlock.Source.ofContent(content))

        /** Alias for calling [ofDocument] with `BetaRequestDocumentBlock.Source.ofUrl(url)`. */
        @JvmStatic
        fun ofDocument(url: BetaUrlPdfSource) =
            ofDocument(BetaRequestDocumentBlock.Source.ofUrl(url))

        /** Alias for calling [ofDocument] with `BetaRequestDocumentBlock.Source.ofFile(file)`. */
        @JvmStatic
        fun ofDocument(file: BetaFileDocumentSource) =
            ofDocument(BetaRequestDocumentBlock.Source.ofFile(file))

        /** A search result block containing source, title, and content from search operations. */
        @JvmStatic
        fun ofSearchResult(searchResult: BetaSearchResultBlockParam) =
            BetaContentBlockParam(searchResult = searchResult)

        /** A block specifying internal thinking by the model. */
        @JvmStatic
        fun ofThinking(thinking: BetaThinkingBlockParam) =
            BetaContentBlockParam(thinking = thinking)

        /** A block specifying internal, redacted thinking by the model. */
        @JvmStatic
        fun ofRedactedThinking(redactedThinking: BetaRedactedThinkingBlockParam) =
            BetaContentBlockParam(redactedThinking = redactedThinking)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofRedactedThinking]
         * variant is built from the given required [data].
         */
        @JvmStatic
        fun ofRedactedThinking(data: String) =
            ofRedactedThinking(BetaRedactedThinkingBlockParam.of(data))

        /** A block indicating a tool use by the model. */
        @JvmStatic
        fun ofToolUse(toolUse: BetaToolUseBlockParam) = BetaContentBlockParam(toolUse = toolUse)

        /** A block specifying the results of a tool use by the model. */
        @JvmStatic
        fun ofToolResult(toolResult: BetaToolResultBlockParam) =
            BetaContentBlockParam(toolResult = toolResult)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofToolResult] variant is
         * built from the given required [toolUseId].
         */
        @JvmStatic
        fun ofToolResult(toolUseId: String) = ofToolResult(BetaToolResultBlockParam.of(toolUseId))

        @JvmStatic
        fun ofServerToolUse(serverToolUse: BetaServerToolUseBlockParam) =
            BetaContentBlockParam(serverToolUse = serverToolUse)

        @JvmStatic
        fun ofWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlockParam) =
            BetaContentBlockParam(webSearchToolResult = webSearchToolResult)

        @JvmStatic
        fun ofWebFetchToolResult(webFetchToolResult: BetaWebFetchToolResultBlockParam) =
            BetaContentBlockParam(webFetchToolResult = webFetchToolResult)

        @JvmStatic
        fun ofAdvisorToolResult(advisorToolResult: BetaAdvisorToolResultBlockParam) =
            BetaContentBlockParam(advisorToolResult = advisorToolResult)

        @JvmStatic
        fun ofCodeExecutionToolResult(
            codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
        ) = BetaContentBlockParam(codeExecutionToolResult = codeExecutionToolResult)

        @JvmStatic
        fun ofBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
        ) = BetaContentBlockParam(bashCodeExecutionToolResult = bashCodeExecutionToolResult)

        @JvmStatic
        fun ofTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlockParam
        ) =
            BetaContentBlockParam(
                textEditorCodeExecutionToolResult = textEditorCodeExecutionToolResult
            )

        @JvmStatic
        fun ofToolSearchToolResult(toolSearchToolResult: BetaToolSearchToolResultBlockParam) =
            BetaContentBlockParam(toolSearchToolResult = toolSearchToolResult)

        @JvmStatic
        fun ofMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam) =
            BetaContentBlockParam(mcpToolUse = mcpToolUse)

        @JvmStatic
        fun ofMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam) =
            BetaContentBlockParam(mcpToolResult = mcpToolResult)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofMcpToolResult] variant
         * is built from the given required [toolUseId].
         */
        @JvmStatic
        fun ofMcpToolResult(toolUseId: String) =
            ofMcpToolResult(BetaRequestMcpToolResultBlockParam.of(toolUseId))

        /**
         * A content block that represents a file to be uploaded to the container Files uploaded via
         * this block will be available in the container's input directory.
         */
        @JvmStatic
        fun ofContainerUpload(containerUpload: BetaContainerUploadBlockParam) =
            BetaContentBlockParam(containerUpload = containerUpload)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofContainerUpload]
         * variant is built from the given required [fileId].
         */
        @JvmStatic
        fun ofContainerUpload(fileId: String) =
            ofContainerUpload(BetaContainerUploadBlockParam.of(fileId))

        /**
         * A compaction block containing summary of previous context.
         *
         * Users should round-trip these blocks from responses to subsequent requests to maintain
         * context across compaction boundaries.
         *
         * When content is None, the block represents a failed compaction. The server treats these
         * as no-ops. Empty string content is not allowed.
         */
        @JvmStatic
        fun ofCompaction(compaction: BetaCompactionBlockParam) =
            BetaContentBlockParam(compaction = compaction)

        /**
         * Mid-conversation directive to make a tool available.
         *
         * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's ``tools``.
         * Under the ``inline-tools-2026-09-15`` beta it may instead be a reference to a tool
         * defined earlier in ``messages``, or a ``tool_definition`` object that carries an inline
         * tool definition in ``definition`` (the same object a ``tools`` entry holds). An
         * ``mcp_toolset`` definition also requires the ``mcp-client-2026-09-15`` beta. The tool is
         * offered to the model from this point in the conversation onward.
         */
        @JvmStatic
        fun ofToolAddition(toolAddition: BetaRequestToolAdditionBlock) =
            BetaContentBlockParam(toolAddition = toolAddition)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofToolAddition] variant
         * is built from the given required [tool].
         */
        @JvmStatic
        fun ofToolAddition(tool: BetaRequestToolAdditionBlock.Tool) =
            ofToolAddition(BetaRequestToolAdditionBlock.of(tool))

        /**
         * Alias for calling [ofToolAddition] with
         * `BetaRequestToolAdditionBlock.Tool.ofReference(reference)`.
         */
        @JvmStatic
        fun ofToolAddition(reference: BetaToolChangeToolReference) =
            ofToolAddition(BetaRequestToolAdditionBlock.Tool.ofReference(reference))

        /** Alias for calling [ofToolAddition] with `reference.toParam()`. */
        @JvmStatic
        fun ofToolAddition(reference: BetaResponseToolChangeToolReference) =
            ofToolAddition(reference.toParam())

        /**
         * Alias for calling [ofToolAddition] with
         * `BetaRequestToolAdditionBlock.Tool.ofMcpToolReference(mcpToolReference)`.
         */
        @JvmStatic
        fun ofToolAddition(mcpToolReference: BetaToolChangeMcpToolReference) =
            ofToolAddition(BetaRequestToolAdditionBlock.Tool.ofMcpToolReference(mcpToolReference))

        /** Alias for calling [ofToolAddition] with `mcpToolReference.toParam()`. */
        @JvmStatic
        fun ofToolAddition(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
            ofToolAddition(mcpToolReference.toParam())

        /**
         * Alias for calling [ofToolAddition] with
         * `BetaRequestToolAdditionBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)`.
         */
        @JvmStatic
        fun ofToolAddition(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            ofToolAddition(
                BetaRequestToolAdditionBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)
            )

        /** Alias for calling [ofToolAddition] with `mcpToolsetReference.toParam()`. */
        @JvmStatic
        fun ofToolAddition(mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference) =
            ofToolAddition(mcpToolsetReference.toParam())

        /**
         * Alias for calling [ofToolAddition] with
         * `BetaRequestToolAdditionBlock.Tool.ofDefinition(definition)`.
         */
        @JvmStatic
        fun ofToolAddition(definition: BetaToolChangeToolDefinitionParam) =
            ofToolAddition(BetaRequestToolAdditionBlock.Tool.ofDefinition(definition))

        /** Alias for calling [ofToolAddition] with `definition.toParam()`. */
        @JvmStatic
        fun ofToolAddition(definition: BetaToolChangeToolDefinition) =
            ofToolAddition(definition.toParam())

        /**
         * Mid-conversation directive to withdraw a tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's
         * ``tools`` or defined earlier in ``messages``. It is no longer offered to the model from
         * this point in the conversation onward.
         */
        @JvmStatic
        fun ofToolRemoval(toolRemoval: BetaRequestToolRemovalBlock) =
            BetaContentBlockParam(toolRemoval = toolRemoval)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofToolRemoval] variant is
         * built from the given required [tool].
         */
        @JvmStatic
        fun ofToolRemoval(tool: BetaRequestToolRemovalBlock.Tool) =
            ofToolRemoval(BetaRequestToolRemovalBlock.of(tool))

        /**
         * Alias for calling [ofToolRemoval] with
         * `BetaRequestToolRemovalBlock.Tool.ofReference(reference)`.
         */
        @JvmStatic
        fun ofToolRemoval(reference: BetaToolChangeToolReference) =
            ofToolRemoval(BetaRequestToolRemovalBlock.Tool.ofReference(reference))

        /** Alias for calling [ofToolRemoval] with `reference.toParam()`. */
        @JvmStatic
        fun ofToolRemoval(reference: BetaResponseToolChangeToolReference) =
            ofToolRemoval(reference.toParam())

        /**
         * Alias for calling [ofToolRemoval] with
         * `BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(mcpToolReference)`.
         */
        @JvmStatic
        fun ofToolRemoval(mcpToolReference: BetaToolChangeMcpToolReference) =
            ofToolRemoval(BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(mcpToolReference))

        /** Alias for calling [ofToolRemoval] with `mcpToolReference.toParam()`. */
        @JvmStatic
        fun ofToolRemoval(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
            ofToolRemoval(mcpToolReference.toParam())

        /**
         * Alias for calling [ofToolRemoval] with
         * `BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)`.
         */
        @JvmStatic
        fun ofToolRemoval(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            ofToolRemoval(
                BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)
            )

        /** Alias for calling [ofToolRemoval] with `mcpToolsetReference.toParam()`. */
        @JvmStatic
        fun ofToolRemoval(mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference) =
            ofToolRemoval(mcpToolsetReference.toParam())

        /**
         * The tool listing an MCP server returned while an earlier response was produced, as that
         * response carried it. Send the assistant message back unchanged, this block included, and
         * the server uses this listing for the matching `mcp_toolset` instead of asking the MCP
         * server again.
         */
        @JvmStatic
        fun ofMcpToolListing(mcpToolListing: BetaMcpToolListingBlockParam) =
            BetaContentBlockParam(mcpToolListing = mcpToolListing)

        /**
         * A `fallback` block echoed back from a prior response.
         *
         * Accepted in `messages[].content` and not rendered into the prompt; not validated against
         * the request's `fallbacks` chain or top-level `model`.
         *
         * Echo the assistant turn back verbatim, including this block in its original position. The
         * block marks the boundary between content produced before and after a fallback hop, and
         * the server relies on that boundary to validate the turn: when thinking runs flank the
         * boundary, omitting the block merges them into one span the server cannot validate (the
         * request is rejected), and moving it into the middle of a single run is likewise rejected;
         * between non-thinking blocks the block's placement has no validation effect.
         */
        @JvmStatic
        fun ofFallback(fallback: BetaFallbackBlockParam) =
            BetaContentBlockParam(fallback = fallback)
    }

    /**
     * An interface that defines how to map each variant of [BetaContentBlockParam] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /** Regular text content. */
        fun visitText(text: BetaTextBlockParam): T

        /** Image content specified directly as base64 data or as a reference via a URL. */
        fun visitImage(image: BetaImageBlockParam): T

        /**
         * Document content, either specified directly as base64 data, as text, or as a reference
         * via a URL.
         */
        fun visitDocument(document: BetaRequestDocumentBlock): T

        /** A search result block containing source, title, and content from search operations. */
        fun visitSearchResult(searchResult: BetaSearchResultBlockParam): T

        /** A block specifying internal thinking by the model. */
        fun visitThinking(thinking: BetaThinkingBlockParam): T

        /** A block specifying internal, redacted thinking by the model. */
        fun visitRedactedThinking(redactedThinking: BetaRedactedThinkingBlockParam): T

        /** A block indicating a tool use by the model. */
        fun visitToolUse(toolUse: BetaToolUseBlockParam): T

        /** A block specifying the results of a tool use by the model. */
        fun visitToolResult(toolResult: BetaToolResultBlockParam): T

        fun visitServerToolUse(serverToolUse: BetaServerToolUseBlockParam): T

        fun visitWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlockParam): T

        fun visitWebFetchToolResult(webFetchToolResult: BetaWebFetchToolResultBlockParam): T

        fun visitAdvisorToolResult(advisorToolResult: BetaAdvisorToolResultBlockParam): T

        fun visitCodeExecutionToolResult(
            codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
        ): T

        fun visitBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
        ): T

        fun visitTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlockParam
        ): T

        fun visitToolSearchToolResult(toolSearchToolResult: BetaToolSearchToolResultBlockParam): T

        fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam): T

        fun visitMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam): T

        /**
         * A content block that represents a file to be uploaded to the container Files uploaded via
         * this block will be available in the container's input directory.
         */
        fun visitContainerUpload(containerUpload: BetaContainerUploadBlockParam): T

        /**
         * A compaction block containing summary of previous context.
         *
         * Users should round-trip these blocks from responses to subsequent requests to maintain
         * context across compaction boundaries.
         *
         * When content is None, the block represents a failed compaction. The server treats these
         * as no-ops. Empty string content is not allowed.
         */
        fun visitCompaction(compaction: BetaCompactionBlockParam): T

        /**
         * Mid-conversation directive to make a tool available.
         *
         * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's ``tools``.
         * Under the ``inline-tools-2026-09-15`` beta it may instead be a reference to a tool
         * defined earlier in ``messages``, or a ``tool_definition`` object that carries an inline
         * tool definition in ``definition`` (the same object a ``tools`` entry holds). An
         * ``mcp_toolset`` definition also requires the ``mcp-client-2026-09-15`` beta. The tool is
         * offered to the model from this point in the conversation onward.
         */
        fun visitToolAddition(toolAddition: BetaRequestToolAdditionBlock): T

        /**
         * Mid-conversation directive to withdraw a tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's
         * ``tools`` or defined earlier in ``messages``. It is no longer offered to the model from
         * this point in the conversation onward.
         */
        fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock): T

        /**
         * The tool listing an MCP server returned while an earlier response was produced, as that
         * response carried it. Send the assistant message back unchanged, this block included, and
         * the server uses this listing for the matching `mcp_toolset` instead of asking the MCP
         * server again.
         */
        fun visitMcpToolListing(mcpToolListing: BetaMcpToolListingBlockParam): T

        /**
         * A `fallback` block echoed back from a prior response.
         *
         * Accepted in `messages[].content` and not rendered into the prompt; not validated against
         * the request's `fallbacks` chain or top-level `model`.
         *
         * Echo the assistant turn back verbatim, including this block in its original position. The
         * block marks the boundary between content produced before and after a fallback hop, and
         * the server relies on that boundary to validate the turn: when thinking runs flank the
         * boundary, omitting the block merges them into one span the server cannot validate (the
         * request is rejected), and moving it into the middle of a single run is likewise rejected;
         * between non-thinking blocks the block's placement has no validation effect.
         */
        fun visitFallback(fallback: BetaFallbackBlockParam): T

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
                "document" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRequestDocumentBlock>())?.let {
                        BetaContentBlockParam(document = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "search_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaSearchResultBlockParam>())?.let {
                        BetaContentBlockParam(searchResult = it, _json = json)
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
                "tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolUseBlockParam>())?.let {
                        BetaContentBlockParam(toolUse = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolResultBlockParam>())?.let {
                        BetaContentBlockParam(toolResult = it, _json = json)
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
                "web_fetch_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebFetchToolResultBlockParam>())
                        ?.let { BetaContentBlockParam(webFetchToolResult = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "advisor_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAdvisorToolResultBlockParam>())
                        ?.let { BetaContentBlockParam(advisorToolResult = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCodeExecutionToolResultBlockParam>(),
                        )
                        ?.let { BetaContentBlockParam(codeExecutionToolResult = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "bash_code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBashCodeExecutionToolResultBlockParam>(),
                        )
                        ?.let {
                            BetaContentBlockParam(bashCodeExecutionToolResult = it, _json = json)
                        } ?: BetaContentBlockParam(_json = json)
                }
                "text_editor_code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTextEditorCodeExecutionToolResultBlockParam>(),
                        )
                        ?.let {
                            BetaContentBlockParam(
                                textEditorCodeExecutionToolResult = it,
                                _json = json,
                            )
                        } ?: BetaContentBlockParam(_json = json)
                }
                "tool_search_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaToolSearchToolResultBlockParam>(),
                        )
                        ?.let { BetaContentBlockParam(toolSearchToolResult = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "mcp_tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaMcpToolUseBlockParam>())?.let {
                        BetaContentBlockParam(mcpToolUse = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "mcp_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaRequestMcpToolResultBlockParam>(),
                        )
                        ?.let { BetaContentBlockParam(mcpToolResult = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "container_upload" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaContainerUploadBlockParam>())
                        ?.let { BetaContentBlockParam(containerUpload = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "compaction" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCompactionBlockParam>())?.let {
                        BetaContentBlockParam(compaction = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
                }
                "tool_addition" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRequestToolAdditionBlock>())
                        ?.let { BetaContentBlockParam(toolAddition = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "tool_removal" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRequestToolRemovalBlock>())
                        ?.let { BetaContentBlockParam(toolRemoval = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "mcp_tool_listing" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaMcpToolListingBlockParam>())
                        ?.let { BetaContentBlockParam(mcpToolListing = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
                }
                "fallback" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaFallbackBlockParam>())?.let {
                        BetaContentBlockParam(fallback = it, _json = json)
                    } ?: BetaContentBlockParam(_json = json)
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
                value.document != null -> generator.writeObject(value.document)
                value.searchResult != null -> generator.writeObject(value.searchResult)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.redactedThinking != null -> generator.writeObject(value.redactedThinking)
                value.toolUse != null -> generator.writeObject(value.toolUse)
                value.toolResult != null -> generator.writeObject(value.toolResult)
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
                value.toolAddition != null -> generator.writeObject(value.toolAddition)
                value.toolRemoval != null -> generator.writeObject(value.toolRemoval)
                value.mcpToolListing != null -> generator.writeObject(value.mcpToolListing)
                value.fallback != null -> generator.writeObject(value.fallback)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockParam")
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

            @JvmField val IMAGE = Type(JsonField.of("image"))

            @JvmField val DOCUMENT = Type(JsonField.of("document"))

            @JvmField val SEARCH_RESULT = Type(JsonField.of("search_result"))

            @JvmField val THINKING = Type(JsonField.of("thinking"))

            @JvmField val REDACTED_THINKING = Type(JsonField.of("redacted_thinking"))

            @JvmField val TOOL_USE = Type(JsonField.of("tool_use"))

            @JvmField val TOOL_RESULT = Type(JsonField.of("tool_result"))

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

            @JvmField val TOOL_ADDITION = Type(JsonField.of("tool_addition"))

            @JvmField val TOOL_REMOVAL = Type(JsonField.of("tool_removal"))

            @JvmField val MCP_TOOL_LISTING = Type(JsonField.of("mcp_tool_listing"))

            @JvmField val FALLBACK = Type(JsonField.of("fallback"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "text" -> TEXT
                    "image" -> IMAGE
                    "document" -> DOCUMENT
                    "search_result" -> SEARCH_RESULT
                    "thinking" -> THINKING
                    "redacted_thinking" -> REDACTED_THINKING
                    "tool_use" -> TOOL_USE
                    "tool_result" -> TOOL_RESULT
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
                    "tool_addition" -> TOOL_ADDITION
                    "tool_removal" -> TOOL_REMOVAL
                    "mcp_tool_listing" -> MCP_TOOL_LISTING
                    "fallback" -> FALLBACK
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
            IMAGE,
            DOCUMENT,
            SEARCH_RESULT,
            THINKING,
            REDACTED_THINKING,
            TOOL_USE,
            TOOL_RESULT,
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
            TOOL_ADDITION,
            TOOL_REMOVAL,
            MCP_TOOL_LISTING,
            FALLBACK,
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
            IMAGE,
            DOCUMENT,
            SEARCH_RESULT,
            THINKING,
            REDACTED_THINKING,
            TOOL_USE,
            TOOL_RESULT,
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
            TOOL_ADDITION,
            TOOL_REMOVAL,
            MCP_TOOL_LISTING,
            FALLBACK,
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
                IMAGE -> Value.IMAGE
                DOCUMENT -> Value.DOCUMENT
                SEARCH_RESULT -> Value.SEARCH_RESULT
                THINKING -> Value.THINKING
                REDACTED_THINKING -> Value.REDACTED_THINKING
                TOOL_USE -> Value.TOOL_USE
                TOOL_RESULT -> Value.TOOL_RESULT
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
                TOOL_ADDITION -> Value.TOOL_ADDITION
                TOOL_REMOVAL -> Value.TOOL_REMOVAL
                MCP_TOOL_LISTING -> Value.MCP_TOOL_LISTING
                FALLBACK -> Value.FALLBACK
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
                IMAGE -> Known.IMAGE
                DOCUMENT -> Known.DOCUMENT
                SEARCH_RESULT -> Known.SEARCH_RESULT
                THINKING -> Known.THINKING
                REDACTED_THINKING -> Known.REDACTED_THINKING
                TOOL_USE -> Known.TOOL_USE
                TOOL_RESULT -> Known.TOOL_RESULT
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
                TOOL_ADDITION -> Known.TOOL_ADDITION
                TOOL_REMOVAL -> Known.TOOL_REMOVAL
                MCP_TOOL_LISTING -> Known.MCP_TOOL_LISTING
                FALLBACK -> Known.FALLBACK
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
