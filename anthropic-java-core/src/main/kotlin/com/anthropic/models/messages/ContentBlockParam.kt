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

/** Regular text content. */
@JsonDeserialize(using = ContentBlockParam.Deserializer::class)
@JsonSerialize(using = ContentBlockParam.Serializer::class)
class ContentBlockParam
private constructor(
    private val text: TextBlockParam? = null,
    private val image: ImageBlockParam? = null,
    private val document: DocumentBlockParam? = null,
    private val searchResult: SearchResultBlockParam? = null,
    private val thinking: ThinkingBlockParam? = null,
    private val redactedThinking: RedactedThinkingBlockParam? = null,
    private val toolUse: ToolUseBlockParam? = null,
    private val toolResult: ToolResultBlockParam? = null,
    private val serverToolUse: ServerToolUseBlockParam? = null,
    private val webSearchToolResult: WebSearchToolResultBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    /** Regular text content. */
    fun text(): Optional<TextBlockParam> = Optional.ofNullable(text)

    /** Image content specified directly as base64 data or as a reference via a URL. */
    fun image(): Optional<ImageBlockParam> = Optional.ofNullable(image)

    /**
     * Document content, either specified directly as base64 data, as text, or as a reference via a
     * URL.
     */
    fun document(): Optional<DocumentBlockParam> = Optional.ofNullable(document)

    /** A search result block containing source, title, and content from search operations. */
    fun searchResult(): Optional<SearchResultBlockParam> = Optional.ofNullable(searchResult)

    /** A block specifying internal thinking by the model. */
    fun thinking(): Optional<ThinkingBlockParam> = Optional.ofNullable(thinking)

    /** A block specifying internal, redacted thinking by the model. */
    fun redactedThinking(): Optional<RedactedThinkingBlockParam> =
        Optional.ofNullable(redactedThinking)

    /** A block indicating a tool use by the model. */
    fun toolUse(): Optional<ToolUseBlockParam> = Optional.ofNullable(toolUse)

    /** A block specifying the results of a tool use by the model. */
    fun toolResult(): Optional<ToolResultBlockParam> = Optional.ofNullable(toolResult)

    fun serverToolUse(): Optional<ServerToolUseBlockParam> = Optional.ofNullable(serverToolUse)

    fun webSearchToolResult(): Optional<WebSearchToolResultBlockParam> =
        Optional.ofNullable(webSearchToolResult)

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

    /** Regular text content. */
    fun asText(): TextBlockParam = text.getOrThrow("text")

    /** Image content specified directly as base64 data or as a reference via a URL. */
    fun asImage(): ImageBlockParam = image.getOrThrow("image")

    /**
     * Document content, either specified directly as base64 data, as text, or as a reference via a
     * URL.
     */
    fun asDocument(): DocumentBlockParam = document.getOrThrow("document")

    /** A search result block containing source, title, and content from search operations. */
    fun asSearchResult(): SearchResultBlockParam = searchResult.getOrThrow("searchResult")

    /** A block specifying internal thinking by the model. */
    fun asThinking(): ThinkingBlockParam = thinking.getOrThrow("thinking")

    /** A block specifying internal, redacted thinking by the model. */
    fun asRedactedThinking(): RedactedThinkingBlockParam =
        redactedThinking.getOrThrow("redactedThinking")

    /** A block indicating a tool use by the model. */
    fun asToolUse(): ToolUseBlockParam = toolUse.getOrThrow("toolUse")

    /** A block specifying the results of a tool use by the model. */
    fun asToolResult(): ToolResultBlockParam = toolResult.getOrThrow("toolResult")

    fun asServerToolUse(): ServerToolUseBlockParam = serverToolUse.getOrThrow("serverToolUse")

    fun asWebSearchToolResult(): WebSearchToolResultBlockParam =
        webSearchToolResult.getOrThrow("webSearchToolResult")

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
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): ContentBlockParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: TextBlockParam) {
                    text.validate()
                }

                override fun visitImage(image: ImageBlockParam) {
                    image.validate()
                }

                override fun visitDocument(document: DocumentBlockParam) {
                    document.validate()
                }

                override fun visitSearchResult(searchResult: SearchResultBlockParam) {
                    searchResult.validate()
                }

                override fun visitThinking(thinking: ThinkingBlockParam) {
                    thinking.validate()
                }

                override fun visitRedactedThinking(redactedThinking: RedactedThinkingBlockParam) {
                    redactedThinking.validate()
                }

                override fun visitToolUse(toolUse: ToolUseBlockParam) {
                    toolUse.validate()
                }

                override fun visitToolResult(toolResult: ToolResultBlockParam) {
                    toolResult.validate()
                }

                override fun visitServerToolUse(serverToolUse: ServerToolUseBlockParam) {
                    serverToolUse.validate()
                }

                override fun visitWebSearchToolResult(
                    webSearchToolResult: WebSearchToolResultBlockParam
                ) {
                    webSearchToolResult.validate()
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
                override fun visitText(text: TextBlockParam) = text.validity()

                override fun visitImage(image: ImageBlockParam) = image.validity()

                override fun visitDocument(document: DocumentBlockParam) = document.validity()

                override fun visitSearchResult(searchResult: SearchResultBlockParam) =
                    searchResult.validity()

                override fun visitThinking(thinking: ThinkingBlockParam) = thinking.validity()

                override fun visitRedactedThinking(redactedThinking: RedactedThinkingBlockParam) =
                    redactedThinking.validity()

                override fun visitToolUse(toolUse: ToolUseBlockParam) = toolUse.validity()

                override fun visitToolResult(toolResult: ToolResultBlockParam) =
                    toolResult.validity()

                override fun visitServerToolUse(serverToolUse: ServerToolUseBlockParam) =
                    serverToolUse.validity()

                override fun visitWebSearchToolResult(
                    webSearchToolResult: WebSearchToolResultBlockParam
                ) = webSearchToolResult.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ContentBlockParam &&
            text == other.text &&
            image == other.image &&
            document == other.document &&
            searchResult == other.searchResult &&
            thinking == other.thinking &&
            redactedThinking == other.redactedThinking &&
            toolUse == other.toolUse &&
            toolResult == other.toolResult &&
            serverToolUse == other.serverToolUse &&
            webSearchToolResult == other.webSearchToolResult
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
        )

    override fun toString(): String =
        when {
            text != null -> "ContentBlockParam{text=$text}"
            image != null -> "ContentBlockParam{image=$image}"
            document != null -> "ContentBlockParam{document=$document}"
            searchResult != null -> "ContentBlockParam{searchResult=$searchResult}"
            thinking != null -> "ContentBlockParam{thinking=$thinking}"
            redactedThinking != null -> "ContentBlockParam{redactedThinking=$redactedThinking}"
            toolUse != null -> "ContentBlockParam{toolUse=$toolUse}"
            toolResult != null -> "ContentBlockParam{toolResult=$toolResult}"
            serverToolUse != null -> "ContentBlockParam{serverToolUse=$serverToolUse}"
            webSearchToolResult != null ->
                "ContentBlockParam{webSearchToolResult=$webSearchToolResult}"
            _json != null -> "ContentBlockParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ContentBlockParam")
        }

    companion object {

        /** Regular text content. */
        @JvmStatic fun ofText(text: TextBlockParam) = ContentBlockParam(text = text)

        /** Image content specified directly as base64 data or as a reference via a URL. */
        @JvmStatic fun ofImage(image: ImageBlockParam) = ContentBlockParam(image = image)

        /**
         * Document content, either specified directly as base64 data, as text, or as a reference
         * via a URL.
         */
        @JvmStatic
        fun ofDocument(document: DocumentBlockParam) = ContentBlockParam(document = document)

        /** A search result block containing source, title, and content from search operations. */
        @JvmStatic
        fun ofSearchResult(searchResult: SearchResultBlockParam) =
            ContentBlockParam(searchResult = searchResult)

        /** A block specifying internal thinking by the model. */
        @JvmStatic
        fun ofThinking(thinking: ThinkingBlockParam) = ContentBlockParam(thinking = thinking)

        /** A block specifying internal, redacted thinking by the model. */
        @JvmStatic
        fun ofRedactedThinking(redactedThinking: RedactedThinkingBlockParam) =
            ContentBlockParam(redactedThinking = redactedThinking)

        /** A block indicating a tool use by the model. */
        @JvmStatic fun ofToolUse(toolUse: ToolUseBlockParam) = ContentBlockParam(toolUse = toolUse)

        /** A block specifying the results of a tool use by the model. */
        @JvmStatic
        fun ofToolResult(toolResult: ToolResultBlockParam) =
            ContentBlockParam(toolResult = toolResult)

        @JvmStatic
        fun ofServerToolUse(serverToolUse: ServerToolUseBlockParam) =
            ContentBlockParam(serverToolUse = serverToolUse)

        @JvmStatic
        fun ofWebSearchToolResult(webSearchToolResult: WebSearchToolResultBlockParam) =
            ContentBlockParam(webSearchToolResult = webSearchToolResult)
    }

    /**
     * An interface that defines how to map each variant of [ContentBlockParam] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        /** Regular text content. */
        fun visitText(text: TextBlockParam): T

        /** Image content specified directly as base64 data or as a reference via a URL. */
        fun visitImage(image: ImageBlockParam): T

        /**
         * Document content, either specified directly as base64 data, as text, or as a reference
         * via a URL.
         */
        fun visitDocument(document: DocumentBlockParam): T

        /** A search result block containing source, title, and content from search operations. */
        fun visitSearchResult(searchResult: SearchResultBlockParam): T

        /** A block specifying internal thinking by the model. */
        fun visitThinking(thinking: ThinkingBlockParam): T

        /** A block specifying internal, redacted thinking by the model. */
        fun visitRedactedThinking(redactedThinking: RedactedThinkingBlockParam): T

        /** A block indicating a tool use by the model. */
        fun visitToolUse(toolUse: ToolUseBlockParam): T

        /** A block specifying the results of a tool use by the model. */
        fun visitToolResult(toolResult: ToolResultBlockParam): T

        fun visitServerToolUse(serverToolUse: ServerToolUseBlockParam): T

        fun visitWebSearchToolResult(webSearchToolResult: WebSearchToolResultBlockParam): T

        /**
         * Maps an unknown variant of [ContentBlockParam] to a value of type [T].
         *
         * An instance of [ContentBlockParam] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ContentBlockParam: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ContentBlockParam>(ContentBlockParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ContentBlockParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    return tryDeserialize(node, jacksonTypeRef<TextBlockParam>())?.let {
                        ContentBlockParam(text = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "image" -> {
                    return tryDeserialize(node, jacksonTypeRef<ImageBlockParam>())?.let {
                        ContentBlockParam(image = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "document" -> {
                    return tryDeserialize(node, jacksonTypeRef<DocumentBlockParam>())?.let {
                        ContentBlockParam(document = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "search_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<SearchResultBlockParam>())?.let {
                        ContentBlockParam(searchResult = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "thinking" -> {
                    return tryDeserialize(node, jacksonTypeRef<ThinkingBlockParam>())?.let {
                        ContentBlockParam(thinking = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "redacted_thinking" -> {
                    return tryDeserialize(node, jacksonTypeRef<RedactedThinkingBlockParam>())?.let {
                        ContentBlockParam(redactedThinking = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<ToolUseBlockParam>())?.let {
                        ContentBlockParam(toolUse = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<ToolResultBlockParam>())?.let {
                        ContentBlockParam(toolResult = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "server_tool_use" -> {
                    return tryDeserialize(node, jacksonTypeRef<ServerToolUseBlockParam>())?.let {
                        ContentBlockParam(serverToolUse = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
                }
                "web_search_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<WebSearchToolResultBlockParam>())
                        ?.let { ContentBlockParam(webSearchToolResult = it, _json = json) }
                        ?: ContentBlockParam(_json = json)
                }
            }

            return ContentBlockParam(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<ContentBlockParam>(ContentBlockParam::class) {

        override fun serialize(
            value: ContentBlockParam,
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
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ContentBlockParam")
            }
        }
    }
}
