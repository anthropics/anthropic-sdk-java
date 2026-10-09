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
    private val webFetchToolResult: WebFetchToolResultBlockParam? = null,
    private val codeExecutionToolResult: CodeExecutionToolResultBlockParam? = null,
    private val bashCodeExecutionToolResult: BashCodeExecutionToolResultBlockParam? = null,
    private val textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlockParam? =
        null,
    private val toolSearchToolResult: ToolSearchToolResultBlockParam? = null,
    private val containerUpload: ContainerUploadBlockParam? = null,
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
            codeExecutionToolResult != null -> Type.CODE_EXECUTION_TOOL_RESULT
            bashCodeExecutionToolResult != null -> Type.BASH_CODE_EXECUTION_TOOL_RESULT
            textEditorCodeExecutionToolResult != null -> Type.TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT
            toolSearchToolResult != null -> Type.TOOL_SEARCH_TOOL_RESULT
            containerUpload != null -> Type.CONTAINER_UPLOAD
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun cacheControl(): Optional<CacheControlEphemeral> =
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
            codeExecutionToolResult != null -> codeExecutionToolResult.cacheControl()
            bashCodeExecutionToolResult != null -> bashCodeExecutionToolResult.cacheControl()
            textEditorCodeExecutionToolResult != null ->
                textEditorCodeExecutionToolResult.cacheControl()
            toolSearchToolResult != null -> toolSearchToolResult.cacheControl()
            containerUpload != null -> containerUpload.cacheControl()
            else -> _json.getProperty<CacheControlEphemeral>("cache_control").asKnown()
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
            codeExecutionToolResult != null -> Optional.empty()
            bashCodeExecutionToolResult != null -> Optional.empty()
            textEditorCodeExecutionToolResult != null -> Optional.empty()
            toolSearchToolResult != null -> Optional.empty()
            containerUpload != null -> Optional.empty()
            else -> _json.getProperty<String>("title").asKnown()
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
            codeExecutionToolResult != null -> Optional.empty()
            bashCodeExecutionToolResult != null -> Optional.empty()
            textEditorCodeExecutionToolResult != null -> Optional.empty()
            toolSearchToolResult != null -> Optional.empty()
            containerUpload != null -> Optional.empty()
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
            codeExecutionToolResult != null -> Optional.empty()
            bashCodeExecutionToolResult != null -> Optional.empty()
            textEditorCodeExecutionToolResult != null -> Optional.empty()
            toolSearchToolResult != null -> Optional.empty()
            containerUpload != null -> Optional.empty()
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
            codeExecutionToolResult != null -> Optional.of(codeExecutionToolResult.toolUseId())
            bashCodeExecutionToolResult != null ->
                Optional.of(bashCodeExecutionToolResult.toolUseId())
            textEditorCodeExecutionToolResult != null ->
                Optional.of(textEditorCodeExecutionToolResult.toolUseId())
            toolSearchToolResult != null -> Optional.of(toolSearchToolResult.toolUseId())
            containerUpload != null -> Optional.empty()
            else -> _json.getProperty<String>("tool_use_id").asKnown()
        }

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

    fun webFetchToolResult(): Optional<WebFetchToolResultBlockParam> =
        Optional.ofNullable(webFetchToolResult)

    fun codeExecutionToolResult(): Optional<CodeExecutionToolResultBlockParam> =
        Optional.ofNullable(codeExecutionToolResult)

    fun bashCodeExecutionToolResult(): Optional<BashCodeExecutionToolResultBlockParam> =
        Optional.ofNullable(bashCodeExecutionToolResult)

    fun textEditorCodeExecutionToolResult(): Optional<TextEditorCodeExecutionToolResultBlockParam> =
        Optional.ofNullable(textEditorCodeExecutionToolResult)

    fun toolSearchToolResult(): Optional<ToolSearchToolResultBlockParam> =
        Optional.ofNullable(toolSearchToolResult)

    /**
     * A content block that represents a file to be uploaded to the container Files uploaded via
     * this block will be available in the container's input directory.
     */
    fun containerUpload(): Optional<ContainerUploadBlockParam> =
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

    fun isWebFetchToolResult(): Boolean = webFetchToolResult != null

    fun isCodeExecutionToolResult(): Boolean = codeExecutionToolResult != null

    fun isBashCodeExecutionToolResult(): Boolean = bashCodeExecutionToolResult != null

    fun isTextEditorCodeExecutionToolResult(): Boolean = textEditorCodeExecutionToolResult != null

    fun isToolSearchToolResult(): Boolean = toolSearchToolResult != null

    fun isContainerUpload(): Boolean = containerUpload != null

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

    fun asWebFetchToolResult(): WebFetchToolResultBlockParam =
        webFetchToolResult.getOrThrow("webFetchToolResult")

    fun asCodeExecutionToolResult(): CodeExecutionToolResultBlockParam =
        codeExecutionToolResult.getOrThrow("codeExecutionToolResult")

    fun asBashCodeExecutionToolResult(): BashCodeExecutionToolResultBlockParam =
        bashCodeExecutionToolResult.getOrThrow("bashCodeExecutionToolResult")

    fun asTextEditorCodeExecutionToolResult(): TextEditorCodeExecutionToolResultBlockParam =
        textEditorCodeExecutionToolResult.getOrThrow("textEditorCodeExecutionToolResult")

    fun asToolSearchToolResult(): ToolSearchToolResultBlockParam =
        toolSearchToolResult.getOrThrow("toolSearchToolResult")

    /**
     * A content block that represents a file to be uploaded to the container Files uploaded via
     * this block will be available in the container's input directory.
     */
    fun asContainerUpload(): ContainerUploadBlockParam =
        containerUpload.getOrThrow("containerUpload")

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
     * Optional<String> result = contentBlockParam.accept(new ContentBlockParam.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitText(TextBlockParam text) {
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
    fun validate(): ContentBlockParam = apply {
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
            codeExecutionToolResult != null -> codeExecutionToolResult.validate()
            bashCodeExecutionToolResult != null -> bashCodeExecutionToolResult.validate()
            textEditorCodeExecutionToolResult != null ->
                textEditorCodeExecutionToolResult.validate()
            toolSearchToolResult != null -> toolSearchToolResult.validate()
            containerUpload != null -> containerUpload.validate()
            else -> throw AnthropicInvalidDataException("Unknown ContentBlockParam: $_json")
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
            codeExecutionToolResult != null -> codeExecutionToolResult.validity()
            bashCodeExecutionToolResult != null -> bashCodeExecutionToolResult.validity()
            textEditorCodeExecutionToolResult != null ->
                textEditorCodeExecutionToolResult.validity()
            toolSearchToolResult != null -> toolSearchToolResult.validity()
            containerUpload != null -> containerUpload.validity()
            else -> 0
        }

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
            codeExecutionToolResult,
            bashCodeExecutionToolResult,
            textEditorCodeExecutionToolResult,
            toolSearchToolResult,
            containerUpload,
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
            webFetchToolResult != null ->
                "ContentBlockParam{webFetchToolResult=$webFetchToolResult}"
            codeExecutionToolResult != null ->
                "ContentBlockParam{codeExecutionToolResult=$codeExecutionToolResult}"
            bashCodeExecutionToolResult != null ->
                "ContentBlockParam{bashCodeExecutionToolResult=$bashCodeExecutionToolResult}"
            textEditorCodeExecutionToolResult != null ->
                "ContentBlockParam{textEditorCodeExecutionToolResult=$textEditorCodeExecutionToolResult}"
            toolSearchToolResult != null ->
                "ContentBlockParam{toolSearchToolResult=$toolSearchToolResult}"
            containerUpload != null -> "ContentBlockParam{containerUpload=$containerUpload}"
            _json != null -> "ContentBlockParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ContentBlockParam")
        }

    companion object {

        /** Regular text content. */
        @JvmStatic fun ofText(text: TextBlockParam) = ContentBlockParam(text = text)

        /**
         * Returns an immutable instance of [ContentBlockParam] whose [ofText] variant is built from
         * the given required [text].
         */
        @JvmStatic fun ofText(text: String) = ofText(TextBlockParam.of(text))

        /** Image content specified directly as base64 data or as a reference via a URL. */
        @JvmStatic fun ofImage(image: ImageBlockParam) = ContentBlockParam(image = image)

        /**
         * Returns an immutable instance of [ContentBlockParam] whose [ofImage] variant is built
         * from the given required [source].
         */
        @JvmStatic fun ofImage(source: ImageBlockParam.Source) = ofImage(ImageBlockParam.of(source))

        /** Alias for calling [ofImage] with `ImageBlockParam.Source.ofBase64(base64)`. */
        @JvmStatic
        fun ofImage(base64: Base64ImageSource) = ofImage(ImageBlockParam.Source.ofBase64(base64))

        /** Alias for calling [ofImage] with `ImageBlockParam.Source.ofUrl(url)`. */
        @JvmStatic fun ofImage(url: UrlImageSource) = ofImage(ImageBlockParam.Source.ofUrl(url))

        /** Alias for calling [ofImage] with `ImageBlockParam.Source.ofFile(file)`. */
        @JvmStatic fun ofImage(file: FileImageSource) = ofImage(ImageBlockParam.Source.ofFile(file))

        /**
         * Document content, either specified directly as base64 data, as text, or as a reference
         * via a URL.
         */
        @JvmStatic
        fun ofDocument(document: DocumentBlockParam) = ContentBlockParam(document = document)

        /**
         * Returns an immutable instance of [ContentBlockParam] whose [ofDocument] variant is built
         * from the given required [source].
         */
        @JvmStatic
        fun ofDocument(source: DocumentBlockParam.Source) =
            ofDocument(DocumentBlockParam.of(source))

        /** Alias for calling [ofDocument] with `DocumentBlockParam.Source.ofBase64(base64)`. */
        @JvmStatic
        fun ofDocument(base64: Base64PdfSource) =
            ofDocument(DocumentBlockParam.Source.ofBase64(base64))

        /** Alias for calling [ofDocument] with `DocumentBlockParam.Source.ofText(text)`. */
        @JvmStatic
        fun ofDocument(text: PlainTextSource) = ofDocument(DocumentBlockParam.Source.ofText(text))

        /** Alias for calling [ofDocument] with `DocumentBlockParam.Source.ofContent(content)`. */
        @JvmStatic
        fun ofDocument(content: ContentBlockSource) =
            ofDocument(DocumentBlockParam.Source.ofContent(content))

        /** Alias for calling [ofDocument] with `DocumentBlockParam.Source.ofUrl(url)`. */
        @JvmStatic
        fun ofDocument(url: UrlPdfSource) = ofDocument(DocumentBlockParam.Source.ofUrl(url))

        /** Alias for calling [ofDocument] with `DocumentBlockParam.Source.ofFile(file)`. */
        @JvmStatic
        fun ofDocument(file: FileDocumentSource) =
            ofDocument(DocumentBlockParam.Source.ofFile(file))

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

        /**
         * Returns an immutable instance of [ContentBlockParam] whose [ofRedactedThinking] variant
         * is built from the given required [data].
         */
        @JvmStatic
        fun ofRedactedThinking(data: String) =
            ofRedactedThinking(RedactedThinkingBlockParam.of(data))

        /** A block indicating a tool use by the model. */
        @JvmStatic fun ofToolUse(toolUse: ToolUseBlockParam) = ContentBlockParam(toolUse = toolUse)

        /** A block specifying the results of a tool use by the model. */
        @JvmStatic
        fun ofToolResult(toolResult: ToolResultBlockParam) =
            ContentBlockParam(toolResult = toolResult)

        /**
         * Returns an immutable instance of [ContentBlockParam] whose [ofToolResult] variant is
         * built from the given required [toolUseId].
         */
        @JvmStatic
        fun ofToolResult(toolUseId: String) = ofToolResult(ToolResultBlockParam.of(toolUseId))

        @JvmStatic
        fun ofServerToolUse(serverToolUse: ServerToolUseBlockParam) =
            ContentBlockParam(serverToolUse = serverToolUse)

        @JvmStatic
        fun ofWebSearchToolResult(webSearchToolResult: WebSearchToolResultBlockParam) =
            ContentBlockParam(webSearchToolResult = webSearchToolResult)

        @JvmStatic
        fun ofWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlockParam) =
            ContentBlockParam(webFetchToolResult = webFetchToolResult)

        @JvmStatic
        fun ofCodeExecutionToolResult(codeExecutionToolResult: CodeExecutionToolResultBlockParam) =
            ContentBlockParam(codeExecutionToolResult = codeExecutionToolResult)

        @JvmStatic
        fun ofBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BashCodeExecutionToolResultBlockParam
        ) = ContentBlockParam(bashCodeExecutionToolResult = bashCodeExecutionToolResult)

        @JvmStatic
        fun ofTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlockParam
        ) = ContentBlockParam(textEditorCodeExecutionToolResult = textEditorCodeExecutionToolResult)

        @JvmStatic
        fun ofToolSearchToolResult(toolSearchToolResult: ToolSearchToolResultBlockParam) =
            ContentBlockParam(toolSearchToolResult = toolSearchToolResult)

        /**
         * A content block that represents a file to be uploaded to the container Files uploaded via
         * this block will be available in the container's input directory.
         */
        @JvmStatic
        fun ofContainerUpload(containerUpload: ContainerUploadBlockParam) =
            ContentBlockParam(containerUpload = containerUpload)

        /**
         * Returns an immutable instance of [ContentBlockParam] whose [ofContainerUpload] variant is
         * built from the given required [fileId].
         */
        @JvmStatic
        fun ofContainerUpload(fileId: String) =
            ofContainerUpload(ContainerUploadBlockParam.of(fileId))
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

        fun visitWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlockParam): T

        fun visitCodeExecutionToolResult(
            codeExecutionToolResult: CodeExecutionToolResultBlockParam
        ): T

        fun visitBashCodeExecutionToolResult(
            bashCodeExecutionToolResult: BashCodeExecutionToolResultBlockParam
        ): T

        fun visitTextEditorCodeExecutionToolResult(
            textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlockParam
        ): T

        fun visitToolSearchToolResult(toolSearchToolResult: ToolSearchToolResultBlockParam): T

        /**
         * A content block that represents a file to be uploaded to the container Files uploaded via
         * this block will be available in the container's input directory.
         */
        fun visitContainerUpload(containerUpload: ContainerUploadBlockParam): T

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
                "web_fetch_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<WebFetchToolResultBlockParam>())
                        ?.let { ContentBlockParam(webFetchToolResult = it, _json = json) }
                        ?: ContentBlockParam(_json = json)
                }
                "code_execution_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<CodeExecutionToolResultBlockParam>())
                        ?.let { ContentBlockParam(codeExecutionToolResult = it, _json = json) }
                        ?: ContentBlockParam(_json = json)
                }
                "bash_code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BashCodeExecutionToolResultBlockParam>(),
                        )
                        ?.let { ContentBlockParam(bashCodeExecutionToolResult = it, _json = json) }
                        ?: ContentBlockParam(_json = json)
                }
                "text_editor_code_execution_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<TextEditorCodeExecutionToolResultBlockParam>(),
                        )
                        ?.let {
                            ContentBlockParam(textEditorCodeExecutionToolResult = it, _json = json)
                        } ?: ContentBlockParam(_json = json)
                }
                "tool_search_tool_result" -> {
                    return tryDeserialize(node, jacksonTypeRef<ToolSearchToolResultBlockParam>())
                        ?.let { ContentBlockParam(toolSearchToolResult = it, _json = json) }
                        ?: ContentBlockParam(_json = json)
                }
                "container_upload" -> {
                    return tryDeserialize(node, jacksonTypeRef<ContainerUploadBlockParam>())?.let {
                        ContentBlockParam(containerUpload = it, _json = json)
                    } ?: ContentBlockParam(_json = json)
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
                else -> throw IllegalStateException("Invalid ContentBlockParam")
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

            @JvmField
            val CODE_EXECUTION_TOOL_RESULT = Type(JsonField.of("code_execution_tool_result"))

            @JvmField
            val BASH_CODE_EXECUTION_TOOL_RESULT =
                Type(JsonField.of("bash_code_execution_tool_result"))

            @JvmField
            val TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT =
                Type(JsonField.of("text_editor_code_execution_tool_result"))

            @JvmField val TOOL_SEARCH_TOOL_RESULT = Type(JsonField.of("tool_search_tool_result"))

            @JvmField val CONTAINER_UPLOAD = Type(JsonField.of("container_upload"))

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
                    "code_execution_tool_result" -> CODE_EXECUTION_TOOL_RESULT
                    "bash_code_execution_tool_result" -> BASH_CODE_EXECUTION_TOOL_RESULT
                    "text_editor_code_execution_tool_result" ->
                        TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT
                    "tool_search_tool_result" -> TOOL_SEARCH_TOOL_RESULT
                    "container_upload" -> CONTAINER_UPLOAD
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
