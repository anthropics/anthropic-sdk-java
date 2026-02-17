// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class RawContentBlockStartEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val contentBlock: JsonField<ContentBlock>,
    private val index: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content_block")
        @ExcludeMissing
        contentBlock: JsonField<ContentBlock> = JsonMissing.of(),
        @JsonProperty("index") @ExcludeMissing index: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(contentBlock, index, type, mutableMapOf())

    /**
     * Response model for a file uploaded to the container.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun contentBlock(): ContentBlock = contentBlock.getRequired("content_block")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun index(): Long = index.getRequired("index")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("content_block_start")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [contentBlock].
     *
     * Unlike [contentBlock], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content_block")
    @ExcludeMissing
    fun _contentBlock(): JsonField<ContentBlock> = contentBlock

    /**
     * Returns the raw JSON value of [index].
     *
     * Unlike [index], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Long> = index

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RawContentBlockStartEvent].
         *
         * The following fields are required:
         * ```java
         * .contentBlock()
         * .index()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RawContentBlockStartEvent]. */
    class Builder internal constructor() {

        private var contentBlock: JsonField<ContentBlock>? = null
        private var index: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("content_block_start")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(rawContentBlockStartEvent: RawContentBlockStartEvent) = apply {
            contentBlock = rawContentBlockStartEvent.contentBlock
            index = rawContentBlockStartEvent.index
            type = rawContentBlockStartEvent.type
            additionalProperties = rawContentBlockStartEvent.additionalProperties.toMutableMap()
        }

        /** Response model for a file uploaded to the container. */
        fun contentBlock(contentBlock: ContentBlock) = contentBlock(JsonField.of(contentBlock))

        /**
         * Sets [Builder.contentBlock] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentBlock] with a well-typed [ContentBlock] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun contentBlock(contentBlock: JsonField<ContentBlock>) = apply {
            this.contentBlock = contentBlock
        }

        /** Alias for calling [contentBlock] with `ContentBlock.ofText(text)`. */
        fun contentBlock(text: TextBlock) = contentBlock(ContentBlock.ofText(text))

        /** Alias for calling [contentBlock] with `ContentBlock.ofThinking(thinking)`. */
        fun contentBlock(thinking: ThinkingBlock) = contentBlock(ContentBlock.ofThinking(thinking))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofRedactedThinking(redactedThinking)`.
         */
        fun contentBlock(redactedThinking: RedactedThinkingBlock) =
            contentBlock(ContentBlock.ofRedactedThinking(redactedThinking))

        /**
         * Alias for calling [contentBlock] with the following:
         * ```java
         * RedactedThinkingBlock.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun redactedThinkingContentBlock(data: String) =
            contentBlock(RedactedThinkingBlock.builder().data(data).build())

        /** Alias for calling [contentBlock] with `ContentBlock.ofToolUse(toolUse)`. */
        fun contentBlock(toolUse: ToolUseBlock) = contentBlock(ContentBlock.ofToolUse(toolUse))

        /** Alias for calling [contentBlock] with `ContentBlock.ofServerToolUse(serverToolUse)`. */
        fun contentBlock(serverToolUse: ServerToolUseBlock) =
            contentBlock(ContentBlock.ofServerToolUse(serverToolUse))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofWebSearchToolResult(webSearchToolResult)`.
         */
        fun contentBlock(webSearchToolResult: WebSearchToolResultBlock) =
            contentBlock(ContentBlock.ofWebSearchToolResult(webSearchToolResult))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofWebFetchToolResult(webFetchToolResult)`.
         */
        fun contentBlock(webFetchToolResult: WebFetchToolResultBlock) =
            contentBlock(ContentBlock.ofWebFetchToolResult(webFetchToolResult))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult)`.
         */
        fun contentBlock(codeExecutionToolResult: CodeExecutionToolResultBlock) =
            contentBlock(ContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)`.
         */
        fun contentBlock(bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock) =
            contentBlock(ContentBlock.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)`.
         */
        fun contentBlock(
            textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
        ) =
            contentBlock(
                ContentBlock.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)
            )

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofToolSearchToolResult(toolSearchToolResult)`.
         */
        fun contentBlock(toolSearchToolResult: ToolSearchToolResultBlock) =
            contentBlock(ContentBlock.ofToolSearchToolResult(toolSearchToolResult))

        /**
         * Alias for calling [contentBlock] with `ContentBlock.ofContainerUpload(containerUpload)`.
         */
        fun contentBlock(containerUpload: ContainerUploadBlock) =
            contentBlock(ContentBlock.ofContainerUpload(containerUpload))

        /**
         * Alias for calling [contentBlock] with the following:
         * ```java
         * ContainerUploadBlock.builder()
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun containerUploadContentBlock(fileId: String) =
            contentBlock(ContainerUploadBlock.builder().fileId(fileId).build())

        fun index(index: Long) = index(JsonField.of(index))

        /**
         * Sets [Builder.index] to an arbitrary JSON value.
         *
         * You should usually call [Builder.index] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun index(index: JsonField<Long>) = apply { this.index = index }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("content_block_start")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [RawContentBlockStartEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .contentBlock()
         * .index()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): RawContentBlockStartEvent =
            RawContentBlockStartEvent(
                checkRequired("contentBlock", contentBlock),
                checkRequired("index", index),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): RawContentBlockStartEvent = apply {
        if (validated) {
            return@apply
        }

        contentBlock().validate()
        index()
        _type().let {
            if (it != JsonValue.from("content_block_start")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
        (contentBlock.asKnown().getOrNull()?.validity() ?: 0) +
            (if (index.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("content_block_start")) 1 else 0 }

    /** Response model for a file uploaded to the container. */
    @JsonDeserialize(using = ContentBlock.Deserializer::class)
    @JsonSerialize(using = ContentBlock.Serializer::class)
    class ContentBlock
    private constructor(
        private val text: TextBlock? = null,
        private val thinking: ThinkingBlock? = null,
        private val redactedThinking: RedactedThinkingBlock? = null,
        private val toolUse: ToolUseBlock? = null,
        private val serverToolUse: ServerToolUseBlock? = null,
        private val webSearchToolResult: WebSearchToolResultBlock? = null,
        private val webFetchToolResult: WebFetchToolResultBlock? = null,
        private val codeExecutionToolResult: CodeExecutionToolResultBlock? = null,
        private val bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock? = null,
        private val textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock? =
            null,
        private val toolSearchToolResult: ToolSearchToolResultBlock? = null,
        private val containerUpload: ContainerUploadBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        fun text(): Optional<TextBlock> = Optional.ofNullable(text)

        fun thinking(): Optional<ThinkingBlock> = Optional.ofNullable(thinking)

        fun redactedThinking(): Optional<RedactedThinkingBlock> =
            Optional.ofNullable(redactedThinking)

        fun toolUse(): Optional<ToolUseBlock> = Optional.ofNullable(toolUse)

        fun serverToolUse(): Optional<ServerToolUseBlock> = Optional.ofNullable(serverToolUse)

        fun webSearchToolResult(): Optional<WebSearchToolResultBlock> =
            Optional.ofNullable(webSearchToolResult)

        fun webFetchToolResult(): Optional<WebFetchToolResultBlock> =
            Optional.ofNullable(webFetchToolResult)

        fun codeExecutionToolResult(): Optional<CodeExecutionToolResultBlock> =
            Optional.ofNullable(codeExecutionToolResult)

        fun bashCodeExecutionToolResult(): Optional<BashCodeExecutionToolResultBlock> =
            Optional.ofNullable(bashCodeExecutionToolResult)

        fun textEditorCodeExecutionToolResult(): Optional<TextEditorCodeExecutionToolResultBlock> =
            Optional.ofNullable(textEditorCodeExecutionToolResult)

        fun toolSearchToolResult(): Optional<ToolSearchToolResultBlock> =
            Optional.ofNullable(toolSearchToolResult)

        /** Response model for a file uploaded to the container. */
        fun containerUpload(): Optional<ContainerUploadBlock> = Optional.ofNullable(containerUpload)

        fun isText(): Boolean = text != null

        fun isThinking(): Boolean = thinking != null

        fun isRedactedThinking(): Boolean = redactedThinking != null

        fun isToolUse(): Boolean = toolUse != null

        fun isServerToolUse(): Boolean = serverToolUse != null

        fun isWebSearchToolResult(): Boolean = webSearchToolResult != null

        fun isWebFetchToolResult(): Boolean = webFetchToolResult != null

        fun isCodeExecutionToolResult(): Boolean = codeExecutionToolResult != null

        fun isBashCodeExecutionToolResult(): Boolean = bashCodeExecutionToolResult != null

        fun isTextEditorCodeExecutionToolResult(): Boolean =
            textEditorCodeExecutionToolResult != null

        fun isToolSearchToolResult(): Boolean = toolSearchToolResult != null

        fun isContainerUpload(): Boolean = containerUpload != null

        fun asText(): TextBlock = text.getOrThrow("text")

        fun asThinking(): ThinkingBlock = thinking.getOrThrow("thinking")

        fun asRedactedThinking(): RedactedThinkingBlock =
            redactedThinking.getOrThrow("redactedThinking")

        fun asToolUse(): ToolUseBlock = toolUse.getOrThrow("toolUse")

        fun asServerToolUse(): ServerToolUseBlock = serverToolUse.getOrThrow("serverToolUse")

        fun asWebSearchToolResult(): WebSearchToolResultBlock =
            webSearchToolResult.getOrThrow("webSearchToolResult")

        fun asWebFetchToolResult(): WebFetchToolResultBlock =
            webFetchToolResult.getOrThrow("webFetchToolResult")

        fun asCodeExecutionToolResult(): CodeExecutionToolResultBlock =
            codeExecutionToolResult.getOrThrow("codeExecutionToolResult")

        fun asBashCodeExecutionToolResult(): BashCodeExecutionToolResultBlock =
            bashCodeExecutionToolResult.getOrThrow("bashCodeExecutionToolResult")

        fun asTextEditorCodeExecutionToolResult(): TextEditorCodeExecutionToolResultBlock =
            textEditorCodeExecutionToolResult.getOrThrow("textEditorCodeExecutionToolResult")

        fun asToolSearchToolResult(): ToolSearchToolResultBlock =
            toolSearchToolResult.getOrThrow("toolSearchToolResult")

        /** Response model for a file uploaded to the container. */
        fun asContainerUpload(): ContainerUploadBlock =
            containerUpload.getOrThrow("containerUpload")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                text != null -> visitor.visitText(text)
                thinking != null -> visitor.visitThinking(thinking)
                redactedThinking != null -> visitor.visitRedactedThinking(redactedThinking)
                toolUse != null -> visitor.visitToolUse(toolUse)
                serverToolUse != null -> visitor.visitServerToolUse(serverToolUse)
                webSearchToolResult != null -> visitor.visitWebSearchToolResult(webSearchToolResult)
                webFetchToolResult != null -> visitor.visitWebFetchToolResult(webFetchToolResult)
                codeExecutionToolResult != null ->
                    visitor.visitCodeExecutionToolResult(codeExecutionToolResult)
                bashCodeExecutionToolResult != null ->
                    visitor.visitBashCodeExecutionToolResult(bashCodeExecutionToolResult)
                textEditorCodeExecutionToolResult != null ->
                    visitor.visitTextEditorCodeExecutionToolResult(
                        textEditorCodeExecutionToolResult
                    )
                toolSearchToolResult != null ->
                    visitor.visitToolSearchToolResult(toolSearchToolResult)
                containerUpload != null -> visitor.visitContainerUpload(containerUpload)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): ContentBlock = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitText(text: TextBlock) {
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

                    override fun visitWebFetchToolResult(
                        webFetchToolResult: WebFetchToolResultBlock
                    ) {
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
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitText(text: TextBlock) = text.validity()

                    override fun visitThinking(thinking: ThinkingBlock) = thinking.validity()

                    override fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock) =
                        redactedThinking.validity()

                    override fun visitToolUse(toolUse: ToolUseBlock) = toolUse.validity()

                    override fun visitServerToolUse(serverToolUse: ServerToolUseBlock) =
                        serverToolUse.validity()

                    override fun visitWebSearchToolResult(
                        webSearchToolResult: WebSearchToolResultBlock
                    ) = webSearchToolResult.validity()

                    override fun visitWebFetchToolResult(
                        webFetchToolResult: WebFetchToolResultBlock
                    ) = webFetchToolResult.validity()

                    override fun visitCodeExecutionToolResult(
                        codeExecutionToolResult: CodeExecutionToolResultBlock
                    ) = codeExecutionToolResult.validity()

                    override fun visitBashCodeExecutionToolResult(
                        bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
                    ) = bashCodeExecutionToolResult.validity()

                    override fun visitTextEditorCodeExecutionToolResult(
                        textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
                    ) = textEditorCodeExecutionToolResult.validity()

                    override fun visitToolSearchToolResult(
                        toolSearchToolResult: ToolSearchToolResultBlock
                    ) = toolSearchToolResult.validity()

                    override fun visitContainerUpload(containerUpload: ContainerUploadBlock) =
                        containerUpload.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ContentBlock &&
                text == other.text &&
                thinking == other.thinking &&
                redactedThinking == other.redactedThinking &&
                toolUse == other.toolUse &&
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
                thinking,
                redactedThinking,
                toolUse,
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
                text != null -> "ContentBlock{text=$text}"
                thinking != null -> "ContentBlock{thinking=$thinking}"
                redactedThinking != null -> "ContentBlock{redactedThinking=$redactedThinking}"
                toolUse != null -> "ContentBlock{toolUse=$toolUse}"
                serverToolUse != null -> "ContentBlock{serverToolUse=$serverToolUse}"
                webSearchToolResult != null ->
                    "ContentBlock{webSearchToolResult=$webSearchToolResult}"
                webFetchToolResult != null -> "ContentBlock{webFetchToolResult=$webFetchToolResult}"
                codeExecutionToolResult != null ->
                    "ContentBlock{codeExecutionToolResult=$codeExecutionToolResult}"
                bashCodeExecutionToolResult != null ->
                    "ContentBlock{bashCodeExecutionToolResult=$bashCodeExecutionToolResult}"
                textEditorCodeExecutionToolResult != null ->
                    "ContentBlock{textEditorCodeExecutionToolResult=$textEditorCodeExecutionToolResult}"
                toolSearchToolResult != null ->
                    "ContentBlock{toolSearchToolResult=$toolSearchToolResult}"
                containerUpload != null -> "ContentBlock{containerUpload=$containerUpload}"
                _json != null -> "ContentBlock{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ContentBlock")
            }

        companion object {

            @JvmStatic fun ofText(text: TextBlock) = ContentBlock(text = text)

            @JvmStatic fun ofThinking(thinking: ThinkingBlock) = ContentBlock(thinking = thinking)

            @JvmStatic
            fun ofRedactedThinking(redactedThinking: RedactedThinkingBlock) =
                ContentBlock(redactedThinking = redactedThinking)

            @JvmStatic fun ofToolUse(toolUse: ToolUseBlock) = ContentBlock(toolUse = toolUse)

            @JvmStatic
            fun ofServerToolUse(serverToolUse: ServerToolUseBlock) =
                ContentBlock(serverToolUse = serverToolUse)

            @JvmStatic
            fun ofWebSearchToolResult(webSearchToolResult: WebSearchToolResultBlock) =
                ContentBlock(webSearchToolResult = webSearchToolResult)

            @JvmStatic
            fun ofWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlock) =
                ContentBlock(webFetchToolResult = webFetchToolResult)

            @JvmStatic
            fun ofCodeExecutionToolResult(codeExecutionToolResult: CodeExecutionToolResultBlock) =
                ContentBlock(codeExecutionToolResult = codeExecutionToolResult)

            @JvmStatic
            fun ofBashCodeExecutionToolResult(
                bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
            ) = ContentBlock(bashCodeExecutionToolResult = bashCodeExecutionToolResult)

            @JvmStatic
            fun ofTextEditorCodeExecutionToolResult(
                textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
            ) = ContentBlock(textEditorCodeExecutionToolResult = textEditorCodeExecutionToolResult)

            @JvmStatic
            fun ofToolSearchToolResult(toolSearchToolResult: ToolSearchToolResultBlock) =
                ContentBlock(toolSearchToolResult = toolSearchToolResult)

            /** Response model for a file uploaded to the container. */
            @JvmStatic
            fun ofContainerUpload(containerUpload: ContainerUploadBlock) =
                ContentBlock(containerUpload = containerUpload)
        }

        /**
         * An interface that defines how to map each variant of [ContentBlock] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            fun visitText(text: TextBlock): T

            fun visitThinking(thinking: ThinkingBlock): T

            fun visitRedactedThinking(redactedThinking: RedactedThinkingBlock): T

            fun visitToolUse(toolUse: ToolUseBlock): T

            fun visitServerToolUse(serverToolUse: ServerToolUseBlock): T

            fun visitWebSearchToolResult(webSearchToolResult: WebSearchToolResultBlock): T

            fun visitWebFetchToolResult(webFetchToolResult: WebFetchToolResultBlock): T

            fun visitCodeExecutionToolResult(
                codeExecutionToolResult: CodeExecutionToolResultBlock
            ): T

            fun visitBashCodeExecutionToolResult(
                bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock
            ): T

            fun visitTextEditorCodeExecutionToolResult(
                textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock
            ): T

            fun visitToolSearchToolResult(toolSearchToolResult: ToolSearchToolResultBlock): T

            /** Response model for a file uploaded to the container. */
            fun visitContainerUpload(containerUpload: ContainerUploadBlock): T

            /**
             * Maps an unknown variant of [ContentBlock] to a value of type [T].
             *
             * An instance of [ContentBlock] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ContentBlock: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<ContentBlock>(ContentBlock::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ContentBlock {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "text" -> {
                        return tryDeserialize(node, jacksonTypeRef<TextBlock>())?.let {
                            ContentBlock(text = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "thinking" -> {
                        return tryDeserialize(node, jacksonTypeRef<ThinkingBlock>())?.let {
                            ContentBlock(thinking = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "redacted_thinking" -> {
                        return tryDeserialize(node, jacksonTypeRef<RedactedThinkingBlock>())?.let {
                            ContentBlock(redactedThinking = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "tool_use" -> {
                        return tryDeserialize(node, jacksonTypeRef<ToolUseBlock>())?.let {
                            ContentBlock(toolUse = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "server_tool_use" -> {
                        return tryDeserialize(node, jacksonTypeRef<ServerToolUseBlock>())?.let {
                            ContentBlock(serverToolUse = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "web_search_tool_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<WebSearchToolResultBlock>())
                            ?.let { ContentBlock(webSearchToolResult = it, _json = json) }
                            ?: ContentBlock(_json = json)
                    }
                    "web_fetch_tool_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<WebFetchToolResultBlock>())
                            ?.let { ContentBlock(webFetchToolResult = it, _json = json) }
                            ?: ContentBlock(_json = json)
                    }
                    "code_execution_tool_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<CodeExecutionToolResultBlock>())
                            ?.let { ContentBlock(codeExecutionToolResult = it, _json = json) }
                            ?: ContentBlock(_json = json)
                    }
                    "bash_code_execution_tool_result" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BashCodeExecutionToolResultBlock>(),
                            )
                            ?.let { ContentBlock(bashCodeExecutionToolResult = it, _json = json) }
                            ?: ContentBlock(_json = json)
                    }
                    "text_editor_code_execution_tool_result" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<TextEditorCodeExecutionToolResultBlock>(),
                            )
                            ?.let {
                                ContentBlock(textEditorCodeExecutionToolResult = it, _json = json)
                            } ?: ContentBlock(_json = json)
                    }
                    "tool_search_tool_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<ToolSearchToolResultBlock>())
                            ?.let { ContentBlock(toolSearchToolResult = it, _json = json) }
                            ?: ContentBlock(_json = json)
                    }
                    "container_upload" -> {
                        return tryDeserialize(node, jacksonTypeRef<ContainerUploadBlock>())?.let {
                            ContentBlock(containerUpload = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                }

                return ContentBlock(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<ContentBlock>(ContentBlock::class) {

            override fun serialize(
                value: ContentBlock,
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
                    value.webFetchToolResult != null ->
                        generator.writeObject(value.webFetchToolResult)
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
                    else -> throw IllegalStateException("Invalid ContentBlock")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RawContentBlockStartEvent &&
            contentBlock == other.contentBlock &&
            index == other.index &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(contentBlock, index, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RawContentBlockStartEvent{contentBlock=$contentBlock, index=$index, type=$type, additionalProperties=$additionalProperties}"
}
