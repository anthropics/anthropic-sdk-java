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
    private val codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam? = null,
    private val mcpToolUse: BetaMcpToolUseBlockParam? = null,
    private val mcpToolResult: BetaRequestMcpToolResultBlockParam? = null,
    private val containerUpload: BetaContainerUploadBlockParam? = null,
    private val _json: JsonValue? = null,
) {

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

    fun codeExecutionToolResult(): Optional<BetaCodeExecutionToolResultBlockParam> =
        Optional.ofNullable(codeExecutionToolResult)

    fun mcpToolUse(): Optional<BetaMcpToolUseBlockParam> = Optional.ofNullable(mcpToolUse)

    fun mcpToolResult(): Optional<BetaRequestMcpToolResultBlockParam> =
        Optional.ofNullable(mcpToolResult)

    /**
     * A content block that represents a file to be uploaded to the container Files uploaded via
     * this block will be available in the container's input directory.
     */
    fun containerUpload(): Optional<BetaContainerUploadBlockParam> =
        Optional.ofNullable(containerUpload)

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

    fun isCodeExecutionToolResult(): Boolean = codeExecutionToolResult != null

    fun isMcpToolUse(): Boolean = mcpToolUse != null

    fun isMcpToolResult(): Boolean = mcpToolResult != null

    fun isContainerUpload(): Boolean = containerUpload != null

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

    fun asCodeExecutionToolResult(): BetaCodeExecutionToolResultBlockParam =
        codeExecutionToolResult.getOrThrow("codeExecutionToolResult")

    fun asMcpToolUse(): BetaMcpToolUseBlockParam = mcpToolUse.getOrThrow("mcpToolUse")

    fun asMcpToolResult(): BetaRequestMcpToolResultBlockParam =
        mcpToolResult.getOrThrow("mcpToolResult")

    /**
     * A content block that represents a file to be uploaded to the container Files uploaded via
     * this block will be available in the container's input directory.
     */
    fun asContainerUpload(): BetaContainerUploadBlockParam =
        containerUpload.getOrThrow("containerUpload")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

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
            codeExecutionToolResult != null ->
                visitor.visitCodeExecutionToolResult(codeExecutionToolResult)
            mcpToolUse != null -> visitor.visitMcpToolUse(mcpToolUse)
            mcpToolResult != null -> visitor.visitMcpToolResult(mcpToolResult)
            containerUpload != null -> visitor.visitContainerUpload(containerUpload)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

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

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ) {
                    codeExecutionToolResult.validate()
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

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ) = codeExecutionToolResult.validity()

                override fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam) =
                    mcpToolUse.validity()

                override fun visitMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam) =
                    mcpToolResult.validity()

                override fun visitContainerUpload(containerUpload: BetaContainerUploadBlockParam) =
                    containerUpload.validity()

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
            codeExecutionToolResult == other.codeExecutionToolResult &&
            mcpToolUse == other.mcpToolUse &&
            mcpToolResult == other.mcpToolResult &&
            containerUpload == other.containerUpload
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
            codeExecutionToolResult,
            mcpToolUse,
            mcpToolResult,
            containerUpload,
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
            codeExecutionToolResult != null ->
                "BetaContentBlockParam{codeExecutionToolResult=$codeExecutionToolResult}"
            mcpToolUse != null -> "BetaContentBlockParam{mcpToolUse=$mcpToolUse}"
            mcpToolResult != null -> "BetaContentBlockParam{mcpToolResult=$mcpToolResult}"
            containerUpload != null -> "BetaContentBlockParam{containerUpload=$containerUpload}"
            _json != null -> "BetaContentBlockParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlockParam")
        }

    companion object {

        /** Regular text content. */
        @JvmStatic fun ofText(text: BetaTextBlockParam) = BetaContentBlockParam(text = text)

        /** Image content specified directly as base64 data or as a reference via a URL. */
        @JvmStatic fun ofImage(image: BetaImageBlockParam) = BetaContentBlockParam(image = image)

        /**
         * Document content, either specified directly as base64 data, as text, or as a reference
         * via a URL.
         */
        @JvmStatic
        fun ofDocument(document: BetaRequestDocumentBlock) =
            BetaContentBlockParam(document = document)

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

        /** A block indicating a tool use by the model. */
        @JvmStatic
        fun ofToolUse(toolUse: BetaToolUseBlockParam) = BetaContentBlockParam(toolUse = toolUse)

        /** A block specifying the results of a tool use by the model. */
        @JvmStatic
        fun ofToolResult(toolResult: BetaToolResultBlockParam) =
            BetaContentBlockParam(toolResult = toolResult)

        @JvmStatic
        fun ofServerToolUse(serverToolUse: BetaServerToolUseBlockParam) =
            BetaContentBlockParam(serverToolUse = serverToolUse)

        @JvmStatic
        fun ofWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlockParam) =
            BetaContentBlockParam(webSearchToolResult = webSearchToolResult)

        @JvmStatic
        fun ofCodeExecutionToolResult(
            codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
        ) = BetaContentBlockParam(codeExecutionToolResult = codeExecutionToolResult)

        @JvmStatic
        fun ofMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam) =
            BetaContentBlockParam(mcpToolUse = mcpToolUse)

        @JvmStatic
        fun ofMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam) =
            BetaContentBlockParam(mcpToolResult = mcpToolResult)

        /**
         * A content block that represents a file to be uploaded to the container Files uploaded via
         * this block will be available in the container's input directory.
         */
        @JvmStatic
        fun ofContainerUpload(containerUpload: BetaContainerUploadBlockParam) =
            BetaContentBlockParam(containerUpload = containerUpload)
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

        fun visitCodeExecutionToolResult(
            codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
        ): T

        fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam): T

        fun visitMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam): T

        /**
         * A content block that represents a file to be uploaded to the container Files uploaded via
         * this block will be available in the container's input directory.
         */
        fun visitContainerUpload(containerUpload: BetaContainerUploadBlockParam): T

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
                "code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCodeExecutionToolResultBlockParam>(),
                        )
                        ?.let { BetaContentBlockParam(codeExecutionToolResult = it, _json = json) }
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
                value.codeExecutionToolResult != null ->
                    generator.writeObject(value.codeExecutionToolResult)
                value.mcpToolUse != null -> generator.writeObject(value.mcpToolUse)
                value.mcpToolResult != null -> generator.writeObject(value.mcpToolResult)
                value.containerUpload != null -> generator.writeObject(value.containerUpload)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockParam")
            }
        }
    }
}
