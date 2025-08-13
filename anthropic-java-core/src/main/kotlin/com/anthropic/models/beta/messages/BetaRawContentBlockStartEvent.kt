// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaRawContentBlockStartEvent
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
         * Returns a mutable builder for constructing an instance of
         * [BetaRawContentBlockStartEvent].
         *
         * The following fields are required:
         * ```java
         * .contentBlock()
         * .index()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRawContentBlockStartEvent]. */
    class Builder internal constructor() {

        private var contentBlock: JsonField<ContentBlock>? = null
        private var index: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("content_block_start")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRawContentBlockStartEvent: BetaRawContentBlockStartEvent) = apply {
            contentBlock = betaRawContentBlockStartEvent.contentBlock
            index = betaRawContentBlockStartEvent.index
            type = betaRawContentBlockStartEvent.type
            additionalProperties = betaRawContentBlockStartEvent.additionalProperties.toMutableMap()
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
        fun contentBlock(text: BetaTextBlock) = contentBlock(ContentBlock.ofText(text))

        /** Alias for calling [contentBlock] with `ContentBlock.ofThinking(thinking)`. */
        fun contentBlock(thinking: BetaThinkingBlock) =
            contentBlock(ContentBlock.ofThinking(thinking))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofRedactedThinking(redactedThinking)`.
         */
        fun contentBlock(redactedThinking: BetaRedactedThinkingBlock) =
            contentBlock(ContentBlock.ofRedactedThinking(redactedThinking))

        /**
         * Alias for calling [contentBlock] with the following:
         * ```java
         * BetaRedactedThinkingBlock.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun redactedThinkingContentBlock(data: String) =
            contentBlock(BetaRedactedThinkingBlock.builder().data(data).build())

        /** Alias for calling [contentBlock] with `ContentBlock.ofToolUse(toolUse)`. */
        fun contentBlock(toolUse: BetaToolUseBlock) = contentBlock(ContentBlock.ofToolUse(toolUse))

        /** Alias for calling [contentBlock] with `ContentBlock.ofServerToolUse(serverToolUse)`. */
        fun contentBlock(serverToolUse: BetaServerToolUseBlock) =
            contentBlock(ContentBlock.ofServerToolUse(serverToolUse))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofWebSearchToolResult(webSearchToolResult)`.
         */
        fun contentBlock(webSearchToolResult: BetaWebSearchToolResultBlock) =
            contentBlock(ContentBlock.ofWebSearchToolResult(webSearchToolResult))

        /**
         * Alias for calling [contentBlock] with
         * `ContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult)`.
         */
        fun contentBlock(codeExecutionToolResult: BetaCodeExecutionToolResultBlock) =
            contentBlock(ContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult))

        /** Alias for calling [contentBlock] with `ContentBlock.ofMcpToolUse(mcpToolUse)`. */
        fun contentBlock(mcpToolUse: BetaMcpToolUseBlock) =
            contentBlock(ContentBlock.ofMcpToolUse(mcpToolUse))

        /** Alias for calling [contentBlock] with `ContentBlock.ofMcpToolResult(mcpToolResult)`. */
        fun contentBlock(mcpToolResult: BetaMcpToolResultBlock) =
            contentBlock(ContentBlock.ofMcpToolResult(mcpToolResult))

        /**
         * Alias for calling [contentBlock] with `ContentBlock.ofContainerUpload(containerUpload)`.
         */
        fun contentBlock(containerUpload: BetaContainerUploadBlock) =
            contentBlock(ContentBlock.ofContainerUpload(containerUpload))

        /**
         * Alias for calling [contentBlock] with the following:
         * ```java
         * BetaContainerUploadBlock.builder()
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun containerUploadContentBlock(fileId: String) =
            contentBlock(BetaContainerUploadBlock.builder().fileId(fileId).build())

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
         * Returns an immutable instance of [BetaRawContentBlockStartEvent].
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
        fun build(): BetaRawContentBlockStartEvent =
            BetaRawContentBlockStartEvent(
                checkRequired("contentBlock", contentBlock),
                checkRequired("index", index),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaRawContentBlockStartEvent = apply {
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
        private val text: BetaTextBlock? = null,
        private val thinking: BetaThinkingBlock? = null,
        private val redactedThinking: BetaRedactedThinkingBlock? = null,
        private val toolUse: BetaToolUseBlock? = null,
        private val serverToolUse: BetaServerToolUseBlock? = null,
        private val webSearchToolResult: BetaWebSearchToolResultBlock? = null,
        private val codeExecutionToolResult: BetaCodeExecutionToolResultBlock? = null,
        private val mcpToolUse: BetaMcpToolUseBlock? = null,
        private val mcpToolResult: BetaMcpToolResultBlock? = null,
        private val containerUpload: BetaContainerUploadBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        fun text(): Optional<BetaTextBlock> = Optional.ofNullable(text)

        fun thinking(): Optional<BetaThinkingBlock> = Optional.ofNullable(thinking)

        fun redactedThinking(): Optional<BetaRedactedThinkingBlock> =
            Optional.ofNullable(redactedThinking)

        fun toolUse(): Optional<BetaToolUseBlock> = Optional.ofNullable(toolUse)

        fun serverToolUse(): Optional<BetaServerToolUseBlock> = Optional.ofNullable(serverToolUse)

        fun webSearchToolResult(): Optional<BetaWebSearchToolResultBlock> =
            Optional.ofNullable(webSearchToolResult)

        fun codeExecutionToolResult(): Optional<BetaCodeExecutionToolResultBlock> =
            Optional.ofNullable(codeExecutionToolResult)

        fun mcpToolUse(): Optional<BetaMcpToolUseBlock> = Optional.ofNullable(mcpToolUse)

        fun mcpToolResult(): Optional<BetaMcpToolResultBlock> = Optional.ofNullable(mcpToolResult)

        /** Response model for a file uploaded to the container. */
        fun containerUpload(): Optional<BetaContainerUploadBlock> =
            Optional.ofNullable(containerUpload)

        fun isText(): Boolean = text != null

        fun isThinking(): Boolean = thinking != null

        fun isRedactedThinking(): Boolean = redactedThinking != null

        fun isToolUse(): Boolean = toolUse != null

        fun isServerToolUse(): Boolean = serverToolUse != null

        fun isWebSearchToolResult(): Boolean = webSearchToolResult != null

        fun isCodeExecutionToolResult(): Boolean = codeExecutionToolResult != null

        fun isMcpToolUse(): Boolean = mcpToolUse != null

        fun isMcpToolResult(): Boolean = mcpToolResult != null

        fun isContainerUpload(): Boolean = containerUpload != null

        fun asText(): BetaTextBlock = text.getOrThrow("text")

        fun asThinking(): BetaThinkingBlock = thinking.getOrThrow("thinking")

        fun asRedactedThinking(): BetaRedactedThinkingBlock =
            redactedThinking.getOrThrow("redactedThinking")

        fun asToolUse(): BetaToolUseBlock = toolUse.getOrThrow("toolUse")

        fun asServerToolUse(): BetaServerToolUseBlock = serverToolUse.getOrThrow("serverToolUse")

        fun asWebSearchToolResult(): BetaWebSearchToolResultBlock =
            webSearchToolResult.getOrThrow("webSearchToolResult")

        fun asCodeExecutionToolResult(): BetaCodeExecutionToolResultBlock =
            codeExecutionToolResult.getOrThrow("codeExecutionToolResult")

        fun asMcpToolUse(): BetaMcpToolUseBlock = mcpToolUse.getOrThrow("mcpToolUse")

        fun asMcpToolResult(): BetaMcpToolResultBlock = mcpToolResult.getOrThrow("mcpToolResult")

        /** Response model for a file uploaded to the container. */
        fun asContainerUpload(): BetaContainerUploadBlock =
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
                codeExecutionToolResult != null ->
                    visitor.visitCodeExecutionToolResult(codeExecutionToolResult)
                mcpToolUse != null -> visitor.visitMcpToolUse(mcpToolUse)
                mcpToolResult != null -> visitor.visitMcpToolResult(mcpToolResult)
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
                    override fun visitText(text: BetaTextBlock) {
                        text.validate()
                    }

                    override fun visitThinking(thinking: BetaThinkingBlock) {
                        thinking.validate()
                    }

                    override fun visitRedactedThinking(
                        redactedThinking: BetaRedactedThinkingBlock
                    ) {
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

                    override fun visitCodeExecutionToolResult(
                        codeExecutionToolResult: BetaCodeExecutionToolResultBlock
                    ) {
                        codeExecutionToolResult.validate()
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
                    override fun visitText(text: BetaTextBlock) = text.validity()

                    override fun visitThinking(thinking: BetaThinkingBlock) = thinking.validity()

                    override fun visitRedactedThinking(
                        redactedThinking: BetaRedactedThinkingBlock
                    ) = redactedThinking.validity()

                    override fun visitToolUse(toolUse: BetaToolUseBlock) = toolUse.validity()

                    override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlock) =
                        serverToolUse.validity()

                    override fun visitWebSearchToolResult(
                        webSearchToolResult: BetaWebSearchToolResultBlock
                    ) = webSearchToolResult.validity()

                    override fun visitCodeExecutionToolResult(
                        codeExecutionToolResult: BetaCodeExecutionToolResultBlock
                    ) = codeExecutionToolResult.validity()

                    override fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlock) =
                        mcpToolUse.validity()

                    override fun visitMcpToolResult(mcpToolResult: BetaMcpToolResultBlock) =
                        mcpToolResult.validity()

                    override fun visitContainerUpload(containerUpload: BetaContainerUploadBlock) =
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
                codeExecutionToolResult == other.codeExecutionToolResult &&
                mcpToolUse == other.mcpToolUse &&
                mcpToolResult == other.mcpToolResult &&
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
                codeExecutionToolResult,
                mcpToolUse,
                mcpToolResult,
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
                codeExecutionToolResult != null ->
                    "ContentBlock{codeExecutionToolResult=$codeExecutionToolResult}"
                mcpToolUse != null -> "ContentBlock{mcpToolUse=$mcpToolUse}"
                mcpToolResult != null -> "ContentBlock{mcpToolResult=$mcpToolResult}"
                containerUpload != null -> "ContentBlock{containerUpload=$containerUpload}"
                _json != null -> "ContentBlock{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ContentBlock")
            }

        companion object {

            @JvmStatic fun ofText(text: BetaTextBlock) = ContentBlock(text = text)

            @JvmStatic
            fun ofThinking(thinking: BetaThinkingBlock) = ContentBlock(thinking = thinking)

            @JvmStatic
            fun ofRedactedThinking(redactedThinking: BetaRedactedThinkingBlock) =
                ContentBlock(redactedThinking = redactedThinking)

            @JvmStatic fun ofToolUse(toolUse: BetaToolUseBlock) = ContentBlock(toolUse = toolUse)

            @JvmStatic
            fun ofServerToolUse(serverToolUse: BetaServerToolUseBlock) =
                ContentBlock(serverToolUse = serverToolUse)

            @JvmStatic
            fun ofWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlock) =
                ContentBlock(webSearchToolResult = webSearchToolResult)

            @JvmStatic
            fun ofCodeExecutionToolResult(
                codeExecutionToolResult: BetaCodeExecutionToolResultBlock
            ) = ContentBlock(codeExecutionToolResult = codeExecutionToolResult)

            @JvmStatic
            fun ofMcpToolUse(mcpToolUse: BetaMcpToolUseBlock) =
                ContentBlock(mcpToolUse = mcpToolUse)

            @JvmStatic
            fun ofMcpToolResult(mcpToolResult: BetaMcpToolResultBlock) =
                ContentBlock(mcpToolResult = mcpToolResult)

            /** Response model for a file uploaded to the container. */
            @JvmStatic
            fun ofContainerUpload(containerUpload: BetaContainerUploadBlock) =
                ContentBlock(containerUpload = containerUpload)
        }

        /**
         * An interface that defines how to map each variant of [ContentBlock] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            fun visitText(text: BetaTextBlock): T

            fun visitThinking(thinking: BetaThinkingBlock): T

            fun visitRedactedThinking(redactedThinking: BetaRedactedThinkingBlock): T

            fun visitToolUse(toolUse: BetaToolUseBlock): T

            fun visitServerToolUse(serverToolUse: BetaServerToolUseBlock): T

            fun visitWebSearchToolResult(webSearchToolResult: BetaWebSearchToolResultBlock): T

            fun visitCodeExecutionToolResult(
                codeExecutionToolResult: BetaCodeExecutionToolResultBlock
            ): T

            fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlock): T

            fun visitMcpToolResult(mcpToolResult: BetaMcpToolResultBlock): T

            /** Response model for a file uploaded to the container. */
            fun visitContainerUpload(containerUpload: BetaContainerUploadBlock): T

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
                        return tryDeserialize(node, jacksonTypeRef<BetaTextBlock>())?.let {
                            ContentBlock(text = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "thinking" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaThinkingBlock>())?.let {
                            ContentBlock(thinking = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "redacted_thinking" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaRedactedThinkingBlock>())
                            ?.let { ContentBlock(redactedThinking = it, _json = json) }
                            ?: ContentBlock(_json = json)
                    }
                    "tool_use" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaToolUseBlock>())?.let {
                            ContentBlock(toolUse = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "server_tool_use" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaServerToolUseBlock>())?.let {
                            ContentBlock(serverToolUse = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "web_search_tool_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebSearchToolResultBlock>())
                            ?.let { ContentBlock(webSearchToolResult = it, _json = json) }
                            ?: ContentBlock(_json = json)
                    }
                    "code_execution_tool_result" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaCodeExecutionToolResultBlock>(),
                            )
                            ?.let { ContentBlock(codeExecutionToolResult = it, _json = json) }
                            ?: ContentBlock(_json = json)
                    }
                    "mcp_tool_use" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaMcpToolUseBlock>())?.let {
                            ContentBlock(mcpToolUse = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "mcp_tool_result" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaMcpToolResultBlock>())?.let {
                            ContentBlock(mcpToolResult = it, _json = json)
                        } ?: ContentBlock(_json = json)
                    }
                    "container_upload" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaContainerUploadBlock>())
                            ?.let { ContentBlock(containerUpload = it, _json = json) }
                            ?: ContentBlock(_json = json)
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
                    value.codeExecutionToolResult != null ->
                        generator.writeObject(value.codeExecutionToolResult)
                    value.mcpToolUse != null -> generator.writeObject(value.mcpToolUse)
                    value.mcpToolResult != null -> generator.writeObject(value.mcpToolResult)
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

        return other is BetaRawContentBlockStartEvent &&
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
        "BetaRawContentBlockStartEvent{contentBlock=$contentBlock, index=$index, type=$type, additionalProperties=$additionalProperties}"
}
