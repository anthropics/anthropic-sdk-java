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

/** Regular text content. */
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
    private val midConvSystem: BetaMidConversationSystemBlockParam? = null,
    private val toolAddition: BetaRequestToolAdditionBlock? = null,
    private val toolRemoval: BetaRequestToolRemovalBlock? = null,
    private val fallback: BetaFallbackBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        accept(
            object : Visitor<Optional<BetaCacheControlEphemeral>> {
                override fun visitText(
                    text: BetaTextBlockParam
                ): Optional<BetaCacheControlEphemeral> = text.cacheControl()

                override fun visitImage(
                    image: BetaImageBlockParam
                ): Optional<BetaCacheControlEphemeral> = image.cacheControl()

                override fun visitDocument(
                    document: BetaRequestDocumentBlock
                ): Optional<BetaCacheControlEphemeral> = document.cacheControl()

                override fun visitSearchResult(
                    searchResult: BetaSearchResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = searchResult.cacheControl()

                override fun visitThinking(
                    thinking: BetaThinkingBlockParam
                ): Optional<BetaCacheControlEphemeral> = Optional.empty()

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ): Optional<BetaCacheControlEphemeral> = Optional.empty()

                override fun visitToolUse(
                    toolUse: BetaToolUseBlockParam
                ): Optional<BetaCacheControlEphemeral> = toolUse.cacheControl()

                override fun visitToolResult(
                    toolResult: BetaToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = toolResult.cacheControl()

                override fun visitServerToolUse(
                    serverToolUse: BetaServerToolUseBlockParam
                ): Optional<BetaCacheControlEphemeral> = serverToolUse.cacheControl()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = webSearchToolResult.cacheControl()

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = webFetchToolResult.cacheControl()

                override fun visitAdvisorToolResult(
                    advisorToolResult: BetaAdvisorToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = advisorToolResult.cacheControl()

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = codeExecutionToolResult.cacheControl()

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = bashCodeExecutionToolResult.cacheControl()

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult:
                        BetaTextEditorCodeExecutionToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> =
                    textEditorCodeExecutionToolResult.cacheControl()

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: BetaToolSearchToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = toolSearchToolResult.cacheControl()

                override fun visitMcpToolUse(
                    mcpToolUse: BetaMcpToolUseBlockParam
                ): Optional<BetaCacheControlEphemeral> = mcpToolUse.cacheControl()

                override fun visitMcpToolResult(
                    mcpToolResult: BetaRequestMcpToolResultBlockParam
                ): Optional<BetaCacheControlEphemeral> = mcpToolResult.cacheControl()

                override fun visitContainerUpload(
                    containerUpload: BetaContainerUploadBlockParam
                ): Optional<BetaCacheControlEphemeral> = containerUpload.cacheControl()

                override fun visitCompaction(
                    compaction: BetaCompactionBlockParam
                ): Optional<BetaCacheControlEphemeral> = compaction.cacheControl()

                override fun visitMidConvSystem(
                    midConvSystem: BetaMidConversationSystemBlockParam
                ): Optional<BetaCacheControlEphemeral> = midConvSystem.cacheControl()

                override fun visitToolAddition(
                    toolAddition: BetaRequestToolAdditionBlock
                ): Optional<BetaCacheControlEphemeral> = toolAddition.cacheControl()

                override fun visitToolRemoval(
                    toolRemoval: BetaRequestToolRemovalBlock
                ): Optional<BetaCacheControlEphemeral> = toolRemoval.cacheControl()

                override fun visitFallback(
                    fallback: BetaFallbackBlockParam
                ): Optional<BetaCacheControlEphemeral> = Optional.empty()
            }
        )

    fun title(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitText(text: BetaTextBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitImage(image: BetaImageBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitDocument(document: BetaRequestDocumentBlock): Optional<String> =
                    document.title()

                override fun visitSearchResult(
                    searchResult: BetaSearchResultBlockParam
                ): Optional<String> = Optional.of(searchResult.title())

                override fun visitThinking(thinking: BetaThinkingBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitToolUse(toolUse: BetaToolUseBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitToolResult(
                    toolResult: BetaToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitServerToolUse(
                    serverToolUse: BetaServerToolUseBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitAdvisorToolResult(
                    advisorToolResult: BetaAdvisorToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult:
                        BetaTextEditorCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: BetaToolSearchToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitMcpToolUse(
                    mcpToolUse: BetaMcpToolUseBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitMcpToolResult(
                    mcpToolResult: BetaRequestMcpToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitContainerUpload(
                    containerUpload: BetaContainerUploadBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitCompaction(
                    compaction: BetaCompactionBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitMidConvSystem(
                    midConvSystem: BetaMidConversationSystemBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitToolAddition(
                    toolAddition: BetaRequestToolAdditionBlock
                ): Optional<String> = Optional.empty()

                override fun visitToolRemoval(
                    toolRemoval: BetaRequestToolRemovalBlock
                ): Optional<String> = Optional.empty()

                override fun visitFallback(fallback: BetaFallbackBlockParam): Optional<String> =
                    Optional.empty()
            }
        )

    fun id(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitText(text: BetaTextBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitImage(image: BetaImageBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitDocument(document: BetaRequestDocumentBlock): Optional<String> =
                    Optional.empty()

                override fun visitSearchResult(
                    searchResult: BetaSearchResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitThinking(thinking: BetaThinkingBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitToolUse(toolUse: BetaToolUseBlockParam): Optional<String> =
                    Optional.of(toolUse.id())

                override fun visitToolResult(
                    toolResult: BetaToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitServerToolUse(
                    serverToolUse: BetaServerToolUseBlockParam
                ): Optional<String> = Optional.of(serverToolUse.id())

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitAdvisorToolResult(
                    advisorToolResult: BetaAdvisorToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult:
                        BetaTextEditorCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: BetaToolSearchToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitMcpToolUse(
                    mcpToolUse: BetaMcpToolUseBlockParam
                ): Optional<String> = Optional.of(mcpToolUse.id())

                override fun visitMcpToolResult(
                    mcpToolResult: BetaRequestMcpToolResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitContainerUpload(
                    containerUpload: BetaContainerUploadBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitCompaction(
                    compaction: BetaCompactionBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitMidConvSystem(
                    midConvSystem: BetaMidConversationSystemBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitToolAddition(
                    toolAddition: BetaRequestToolAdditionBlock
                ): Optional<String> = Optional.empty()

                override fun visitToolRemoval(
                    toolRemoval: BetaRequestToolRemovalBlock
                ): Optional<String> = Optional.empty()

                override fun visitFallback(fallback: BetaFallbackBlockParam): Optional<String> =
                    Optional.empty()
            }
        )

    fun toolUseId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitText(text: BetaTextBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitImage(image: BetaImageBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitDocument(document: BetaRequestDocumentBlock): Optional<String> =
                    Optional.empty()

                override fun visitSearchResult(
                    searchResult: BetaSearchResultBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitThinking(thinking: BetaThinkingBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitToolUse(toolUse: BetaToolUseBlockParam): Optional<String> =
                    Optional.empty()

                override fun visitToolResult(
                    toolResult: BetaToolResultBlockParam
                ): Optional<String> = Optional.of(toolResult.toolUseId())

                override fun visitServerToolUse(
                    serverToolUse: BetaServerToolUseBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ): Optional<String> = Optional.of(webSearchToolResult.toolUseId())

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlockParam
                ): Optional<String> = Optional.of(webFetchToolResult.toolUseId())

                override fun visitAdvisorToolResult(
                    advisorToolResult: BetaAdvisorToolResultBlockParam
                ): Optional<String> = Optional.of(advisorToolResult.toolUseId())

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.of(codeExecutionToolResult.toolUseId())

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.of(bashCodeExecutionToolResult.toolUseId())

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult:
                        BetaTextEditorCodeExecutionToolResultBlockParam
                ): Optional<String> = Optional.of(textEditorCodeExecutionToolResult.toolUseId())

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: BetaToolSearchToolResultBlockParam
                ): Optional<String> = Optional.of(toolSearchToolResult.toolUseId())

                override fun visitMcpToolUse(
                    mcpToolUse: BetaMcpToolUseBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitMcpToolResult(
                    mcpToolResult: BetaRequestMcpToolResultBlockParam
                ): Optional<String> = Optional.of(mcpToolResult.toolUseId())

                override fun visitContainerUpload(
                    containerUpload: BetaContainerUploadBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitCompaction(
                    compaction: BetaCompactionBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitMidConvSystem(
                    midConvSystem: BetaMidConversationSystemBlockParam
                ): Optional<String> = Optional.empty()

                override fun visitToolAddition(
                    toolAddition: BetaRequestToolAdditionBlock
                ): Optional<String> = Optional.empty()

                override fun visitToolRemoval(
                    toolRemoval: BetaRequestToolRemovalBlock
                ): Optional<String> = Optional.empty()

                override fun visitFallback(fallback: BetaFallbackBlockParam): Optional<String> =
                    Optional.empty()
            }
        )

    fun isError(): Optional<Boolean> =
        accept(
            object : Visitor<Optional<Boolean>> {
                override fun visitText(text: BetaTextBlockParam): Optional<Boolean> =
                    Optional.empty()

                override fun visitImage(image: BetaImageBlockParam): Optional<Boolean> =
                    Optional.empty()

                override fun visitDocument(document: BetaRequestDocumentBlock): Optional<Boolean> =
                    Optional.empty()

                override fun visitSearchResult(
                    searchResult: BetaSearchResultBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitThinking(thinking: BetaThinkingBlockParam): Optional<Boolean> =
                    Optional.empty()

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolUse(toolUse: BetaToolUseBlockParam): Optional<Boolean> =
                    Optional.empty()

                override fun visitToolResult(
                    toolResult: BetaToolResultBlockParam
                ): Optional<Boolean> = toolResult.isError()

                override fun visitServerToolUse(
                    serverToolUse: BetaServerToolUseBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitAdvisorToolResult(
                    advisorToolResult: BetaAdvisorToolResultBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult:
                        BetaTextEditorCodeExecutionToolResultBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: BetaToolSearchToolResultBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitMcpToolUse(
                    mcpToolUse: BetaMcpToolUseBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitMcpToolResult(
                    mcpToolResult: BetaRequestMcpToolResultBlockParam
                ): Optional<Boolean> = mcpToolResult.isError()

                override fun visitContainerUpload(
                    containerUpload: BetaContainerUploadBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitCompaction(
                    compaction: BetaCompactionBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitMidConvSystem(
                    midConvSystem: BetaMidConversationSystemBlockParam
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolAddition(
                    toolAddition: BetaRequestToolAdditionBlock
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolRemoval(
                    toolRemoval: BetaRequestToolRemovalBlock
                ): Optional<Boolean> = Optional.empty()

                override fun visitFallback(fallback: BetaFallbackBlockParam): Optional<Boolean> =
                    Optional.empty()
            }
        )

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
     * System instructions that appear mid-conversation.
     *
     * Use this block to provide or update system-level instructions at a specific point in the
     * conversation, rather than only via the top-level `system` parameter.
     */
    fun midConvSystem(): Optional<BetaMidConversationSystemBlockParam> =
        Optional.ofNullable(midConvSystem)

    /**
     * Mid-conversation directive to surface a declared tool.
     *
     * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
     * offered to the model from this point in the conversation onward.
     */
    fun toolAddition(): Optional<BetaRequestToolAdditionBlock> = Optional.ofNullable(toolAddition)

    /**
     * Mid-conversation directive to withdraw a tool.
     *
     * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is no
     * longer offered to the model from this point in the conversation onward.
     */
    fun toolRemoval(): Optional<BetaRequestToolRemovalBlock> = Optional.ofNullable(toolRemoval)

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

    fun isMidConvSystem(): Boolean = midConvSystem != null

    fun isToolAddition(): Boolean = toolAddition != null

    fun isToolRemoval(): Boolean = toolRemoval != null

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
     * System instructions that appear mid-conversation.
     *
     * Use this block to provide or update system-level instructions at a specific point in the
     * conversation, rather than only via the top-level `system` parameter.
     */
    fun asMidConvSystem(): BetaMidConversationSystemBlockParam =
        midConvSystem.getOrThrow("midConvSystem")

    /**
     * Mid-conversation directive to surface a declared tool.
     *
     * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
     * offered to the model from this point in the conversation onward.
     */
    fun asToolAddition(): BetaRequestToolAdditionBlock = toolAddition.getOrThrow("toolAddition")

    /**
     * Mid-conversation directive to withdraw a tool.
     *
     * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is no
     * longer offered to the model from this point in the conversation onward.
     */
    fun asToolRemoval(): BetaRequestToolRemovalBlock = toolRemoval.getOrThrow("toolRemoval")

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
            midConvSystem != null -> visitor.visitMidConvSystem(midConvSystem)
            toolAddition != null -> visitor.visitToolAddition(toolAddition)
            toolRemoval != null -> visitor.visitToolRemoval(toolRemoval)
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

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: BetaTextBlockParam) {
                    text.validate()
                }

                override fun visitImage(image: BetaImageBlockParam) {
                    image.validate()
                }

                override fun visitDocument(document: BetaRequestDocumentBlock) {
                    document.validate()
                }

                override fun visitSearchResult(searchResult: BetaSearchResultBlockParam) {
                    searchResult.validate()
                }

                override fun visitThinking(thinking: BetaThinkingBlockParam) {
                    thinking.validate()
                }

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ) {
                    redactedThinking.validate()
                }

                override fun visitToolUse(toolUse: BetaToolUseBlockParam) {
                    toolUse.validate()
                }

                override fun visitToolResult(toolResult: BetaToolResultBlockParam) {
                    toolResult.validate()
                }

                override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlockParam) {
                    serverToolUse.validate()
                }

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ) {
                    webSearchToolResult.validate()
                }

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlockParam
                ) {
                    webFetchToolResult.validate()
                }

                override fun visitAdvisorToolResult(
                    advisorToolResult: BetaAdvisorToolResultBlockParam
                ) {
                    advisorToolResult.validate()
                }

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ) {
                    codeExecutionToolResult.validate()
                }

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
                ) {
                    bashCodeExecutionToolResult.validate()
                }

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult:
                        BetaTextEditorCodeExecutionToolResultBlockParam
                ) {
                    textEditorCodeExecutionToolResult.validate()
                }

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: BetaToolSearchToolResultBlockParam
                ) {
                    toolSearchToolResult.validate()
                }

                override fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam) {
                    mcpToolUse.validate()
                }

                override fun visitMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam) {
                    mcpToolResult.validate()
                }

                override fun visitContainerUpload(containerUpload: BetaContainerUploadBlockParam) {
                    containerUpload.validate()
                }

                override fun visitCompaction(compaction: BetaCompactionBlockParam) {
                    compaction.validate()
                }

                override fun visitMidConvSystem(
                    midConvSystem: BetaMidConversationSystemBlockParam
                ) {
                    midConvSystem.validate()
                }

                override fun visitToolAddition(toolAddition: BetaRequestToolAdditionBlock) {
                    toolAddition.validate()
                }

                override fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock) {
                    toolRemoval.validate()
                }

                override fun visitFallback(fallback: BetaFallbackBlockParam) {
                    fallback.validate()
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

                override fun visitDocument(document: BetaRequestDocumentBlock) = document.validity()

                override fun visitSearchResult(searchResult: BetaSearchResultBlockParam) =
                    searchResult.validity()

                override fun visitThinking(thinking: BetaThinkingBlockParam) = thinking.validity()

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ) = redactedThinking.validity()

                override fun visitToolUse(toolUse: BetaToolUseBlockParam) = toolUse.validity()

                override fun visitToolResult(toolResult: BetaToolResultBlockParam) =
                    toolResult.validity()

                override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlockParam) =
                    serverToolUse.validity()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ) = webSearchToolResult.validity()

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlockParam
                ) = webFetchToolResult.validity()

                override fun visitAdvisorToolResult(
                    advisorToolResult: BetaAdvisorToolResultBlockParam
                ) = advisorToolResult.validity()

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ) = codeExecutionToolResult.validity()

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
                ) = bashCodeExecutionToolResult.validity()

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult:
                        BetaTextEditorCodeExecutionToolResultBlockParam
                ) = textEditorCodeExecutionToolResult.validity()

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: BetaToolSearchToolResultBlockParam
                ) = toolSearchToolResult.validity()

                override fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam) =
                    mcpToolUse.validity()

                override fun visitMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam) =
                    mcpToolResult.validity()

                override fun visitContainerUpload(containerUpload: BetaContainerUploadBlockParam) =
                    containerUpload.validity()

                override fun visitCompaction(compaction: BetaCompactionBlockParam) =
                    compaction.validity()

                override fun visitMidConvSystem(
                    midConvSystem: BetaMidConversationSystemBlockParam
                ) = midConvSystem.validity()

                override fun visitToolAddition(toolAddition: BetaRequestToolAdditionBlock) =
                    toolAddition.validity()

                override fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock) =
                    toolRemoval.validity()

                override fun visitFallback(fallback: BetaFallbackBlockParam) = fallback.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

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
            midConvSystem == other.midConvSystem &&
            toolAddition == other.toolAddition &&
            toolRemoval == other.toolRemoval &&
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
            midConvSystem,
            toolAddition,
            toolRemoval,
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
            midConvSystem != null -> "BetaContentBlockParam{midConvSystem=$midConvSystem}"
            toolAddition != null -> "BetaContentBlockParam{toolAddition=$toolAddition}"
            toolRemoval != null -> "BetaContentBlockParam{toolRemoval=$toolRemoval}"
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
         * System instructions that appear mid-conversation.
         *
         * Use this block to provide or update system-level instructions at a specific point in the
         * conversation, rather than only via the top-level `system` parameter.
         */
        @JvmStatic
        fun ofMidConvSystem(midConvSystem: BetaMidConversationSystemBlockParam) =
            BetaContentBlockParam(midConvSystem = midConvSystem)

        /**
         * Returns an immutable instance of [BetaContentBlockParam] whose [ofMidConvSystem] variant
         * is built from the given required [content].
         */
        @JvmStatic
        fun ofMidConvSystem(content: List<BetaMidConversationSystemBlockParam.Content>) =
            ofMidConvSystem(BetaMidConversationSystemBlockParam.of(content))

        /**
         * Mid-conversation directive to surface a declared tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
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
         * Mid-conversation directive to withdraw a tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
         * no longer offered to the model from this point in the conversation onward.
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
         * System instructions that appear mid-conversation.
         *
         * Use this block to provide or update system-level instructions at a specific point in the
         * conversation, rather than only via the top-level `system` parameter.
         */
        fun visitMidConvSystem(midConvSystem: BetaMidConversationSystemBlockParam): T

        /**
         * Mid-conversation directive to surface a declared tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
         * offered to the model from this point in the conversation onward.
         */
        fun visitToolAddition(toolAddition: BetaRequestToolAdditionBlock): T

        /**
         * Mid-conversation directive to withdraw a tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
         * no longer offered to the model from this point in the conversation onward.
         */
        fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock): T

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
                "mid_conv_system" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaMidConversationSystemBlockParam>(),
                        )
                        ?.let { BetaContentBlockParam(midConvSystem = it, _json = json) }
                        ?: BetaContentBlockParam(_json = json)
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
                value.midConvSystem != null -> generator.writeObject(value.midConvSystem)
                value.toolAddition != null -> generator.writeObject(value.toolAddition)
                value.toolRemoval != null -> generator.writeObject(value.toolRemoval)
                value.fallback != null -> generator.writeObject(value.fallback)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockParam")
            }
        }
    }
}
