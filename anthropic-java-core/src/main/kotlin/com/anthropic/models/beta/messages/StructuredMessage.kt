package com.anthropic.models.beta.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A wrapper for [BetaMessage] that provides type-safe access to the [content] when using the
 * _Structured Outputs_ feature to deserialize a JSON response to an instance of an arbitrary class.
 * See the SDK documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class to which the JSON data in the response will be deserialized.
 */
class StructuredMessage<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    private val delegate: BetaMessage,
) {

    @get:JvmName("rawMessage")
    val rawMessage: BetaMessage
        get() = delegate

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see BetaMessage.id
     */
    fun id(): String = delegate.id()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaMessage.container
     */
    fun container(): Optional<BetaContainer> = delegate.container()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see BetaMessage.content
     */
    fun content(): List<StructuredContentBlock<T>> = content.getRequired("content")

    private val content by lazy {
        delegate._content().map { contentBlocks ->
            contentBlocks.map { StructuredContentBlock(outputType, it) }
        }
    }

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaMessage.contextManagement
     */
    fun contextManagement(): Optional<BetaContextManagementResponse> = delegate.contextManagement()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaMessage.diagnostics
     */
    fun diagnostics(): Optional<BetaDiagnostics> = delegate.diagnostics()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see BetaMessage.model
     */
    fun model(): Model = delegate.model()

    /** @see BetaMessage._role */
    fun _role(): JsonValue = delegate._role()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaMessage.stopDetails
     */
    fun stopDetails(): Optional<BetaRefusalStopDetails> = delegate.stopDetails()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaMessage.stopReason
     */
    fun stopReason(): Optional<BetaStopReason> = delegate.stopReason()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaMessage.stopSequence
     */
    fun stopSequence(): Optional<String> = delegate.stopSequence()

    /** @see BetaMessage._type */
    fun _type(): JsonValue = delegate._type()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see BetaMessage.usage
     */
    fun usage(): BetaUsage = delegate.usage()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaMessage.inputTransformations
     */
    fun inputTransformations(): Optional<List<BetaInputTransformation>> =
        delegate.inputTransformations()

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _id(): JsonField<String> = delegate._id()

    /**
     * Returns the raw JSON value of [container].
     *
     * Unlike [container], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _container(): JsonField<BetaContainer> = delegate._container()

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _content(): JsonField<List<StructuredContentBlock<T>>> = content

    /**
     * Returns the raw JSON value of [contextManagement].
     *
     * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _contextManagement(): JsonField<BetaContextManagementResponse> =
        delegate._contextManagement()

    /**
     * Returns the raw JSON value of [diagnostics].
     *
     * Unlike [diagnostics], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _diagnostics(): JsonField<BetaDiagnostics> = delegate._diagnostics()

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _model(): JsonField<Model> = delegate._model()

    /**
     * Returns the raw JSON value of [stopDetails].
     *
     * Unlike [stopDetails], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _stopDetails(): JsonField<BetaRefusalStopDetails> = delegate._stopDetails()

    /**
     * Returns the raw JSON value of [stopReason].
     *
     * Unlike [stopReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _stopReason(): JsonField<BetaStopReason> = delegate._stopReason()

    /**
     * Returns the raw JSON value of [stopSequence].
     *
     * Unlike [stopSequence], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _stopSequence(): JsonField<String> = delegate._stopSequence()

    /**
     * Returns the raw JSON value of [usage].
     *
     * Unlike [usage], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _usage(): JsonField<BetaUsage> = delegate._usage()

    /**
     * Returns the raw JSON value of [inputTransformations].
     *
     * Unlike [inputTransformations], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _inputTransformations(): JsonField<List<BetaInputTransformation>> =
        delegate._inputTransformations()

    /** @see BetaMessage._additionalProperties */
    fun _additionalProperties(): Map<String, JsonValue> = delegate._additionalProperties()

    /** @see BetaMessage.validate */
    fun validate(): StructuredMessage<T> = apply {
        content().forEach { it.validate() }
        delegate.validate()
    }

    /** @see BetaMessage.isValid */
    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    fun toBuilder() = Builder(outputType).from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [StructuredMessage].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .container()
         * .content()
         * .contextManagement()
         * .diagnostics()
         * .model()
         * .stopDetails()
         * .stopReason()
         * .stopSequence()
         * .usage()
         * ```
         */
        @JvmStatic fun <T : Any> builder(outputType: Class<T>) = Builder(outputType)
    }

    /** A builder for [StructuredMessage]. */
    class Builder<T : Any> internal constructor(private val outputType: Class<T>) {

        private var delegate: BetaMessage.Builder = BetaMessage.builder()

        @JvmSynthetic
        internal fun from(structuredMessage: StructuredMessage<T>) = apply {
            delegate = structuredMessage.delegate.toBuilder()
        }

        /** @see BetaMessage.Builder.id */
        fun id(id: String) = apply { delegate.id(id) }

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { delegate.id(id) }

        /** @see BetaMessage.Builder.container */
        fun container(container: BetaContainer?) = apply { delegate.container(container) }

        /** Alias for calling [Builder.container] with `container.orElse(null)`. */
        fun container(container: Optional<BetaContainer>) = container(container.getOrNull())

        /**
         * Sets [Builder.container] to an arbitrary JSON value.
         *
         * You should usually call [Builder.container] with a well-typed [BetaContainer] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun container(container: JsonField<BetaContainer>) = apply { delegate.container(container) }

        /** @see BetaMessage.Builder.content */
        fun content(content: List<BetaContentBlock>) = apply { delegate.content(content) }

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed `List<BetaContentBlock>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun content(content: JsonField<List<BetaContentBlock>>) = apply {
            delegate.content(content)
        }

        /**
         * Adds a single [BetaContentBlock] to [Builder.content].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addContent(content: BetaContentBlock) = apply { delegate.addContent(content) }

        /** Alias for calling [addContent] with `BetaContentBlock.ofText(text)`. */
        fun addContent(text: BetaTextBlock) = apply { delegate.addContent(text) }

        /** Alias for calling [addContent] with `BetaContentBlock.ofThinking(thinking)`. */
        fun addContent(thinking: BetaThinkingBlock) = apply { delegate.addContent(thinking) }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofRedactedThinking(redactedThinking)`.
         */
        fun addContent(redactedThinking: BetaRedactedThinkingBlock) = apply {
            delegate.addContent(redactedThinking)
        }

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * BetaRedactedThinkingBlock.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun addRedactedThinkingContent(data: String) = apply {
            delegate.addRedactedThinkingContent(data)
        }

        /** Alias for calling [addContent] with `BetaContentBlock.ofToolUse(toolUse)`. */
        fun addContent(toolUse: BetaToolUseBlock) = apply { delegate.addContent(toolUse) }

        /**
         * Alias for calling [addContent] with `BetaContentBlock.ofServerToolUse(serverToolUse)`.
         */
        fun addContent(serverToolUse: BetaServerToolUseBlock) = apply {
            delegate.addContent(serverToolUse)
        }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofWebSearchToolResult(webSearchToolResult)`.
         */
        fun addContent(webSearchToolResult: BetaWebSearchToolResultBlock) = apply {
            delegate.addContent(webSearchToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofWebFetchToolResult(webFetchToolResult)`.
         */
        fun addContent(webFetchToolResult: BetaWebFetchToolResultBlock) = apply {
            delegate.addContent(webFetchToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofAdvisorToolResult(advisorToolResult)`.
         */
        fun addContent(advisorToolResult: BetaAdvisorToolResultBlock) = apply {
            delegate.addContent(advisorToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult)`.
         */
        fun addContent(codeExecutionToolResult: BetaCodeExecutionToolResultBlock) = apply {
            delegate.addContent(codeExecutionToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)`.
         */
        fun addContent(bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlock) = apply {
            delegate.addContent(bashCodeExecutionToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)`.
         */
        fun addContent(
            textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlock
        ) = apply { delegate.addContent(textEditorCodeExecutionToolResult) }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofToolSearchToolResult(toolSearchToolResult)`.
         */
        fun addContent(toolSearchToolResult: BetaToolSearchToolResultBlock) = apply {
            delegate.addContent(toolSearchToolResult)
        }

        /** Alias for calling [addContent] with `BetaContentBlock.ofMcpToolUse(mcpToolUse)`. */
        fun addContent(mcpToolUse: BetaMcpToolUseBlock) = apply { delegate.addContent(mcpToolUse) }

        /**
         * Alias for calling [addContent] with `BetaContentBlock.ofMcpToolResult(mcpToolResult)`.
         */
        fun addContent(mcpToolResult: BetaMcpToolResultBlock) = apply {
            delegate.addContent(mcpToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `BetaContentBlock.ofContainerUpload(containerUpload)`.
         */
        fun addContent(containerUpload: BetaContainerUploadBlock) = apply {
            delegate.addContent(containerUpload)
        }

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * BetaContainerUploadBlock.builder()
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun addContainerUploadContent(fileId: String) = apply {
            delegate.addContainerUploadContent(fileId)
        }

        /** Alias for calling [addContent] with `BetaContentBlock.ofCompaction(compaction)`. */
        fun addContent(compaction: BetaCompactionBlock) = apply { delegate.addContent(compaction) }

        /** Alias for calling [addContent] with `BetaContentBlock.ofFallback(fallback)`. */
        fun addContent(fallback: BetaFallbackBlock) = apply { delegate.addContent(fallback) }

        /**
         * Alias for calling [addContent] with `BetaContentBlock.ofMcpToolListing(mcpToolListing)`.
         */
        fun addContent(mcpToolListing: BetaMcpToolListingBlock) = apply {
            delegate.addContent(mcpToolListing)
        }

        /** @see BetaMessage.Builder.contextManagement */
        fun contextManagement(contextManagement: BetaContextManagementResponse?) = apply {
            delegate.contextManagement(contextManagement)
        }

        /** Alias for calling [Builder.contextManagement] with `contextManagement.orElse(null)`. */
        fun contextManagement(contextManagement: Optional<BetaContextManagementResponse>) =
            contextManagement(contextManagement.getOrNull())

        /**
         * Sets [Builder.contextManagement] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contextManagement] with a well-typed
         * [BetaContextManagementResponse] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun contextManagement(contextManagement: JsonField<BetaContextManagementResponse>) = apply {
            delegate.contextManagement(contextManagement)
        }

        /** @see BetaMessage.Builder.diagnostics */
        fun diagnostics(diagnostics: BetaDiagnostics?) = apply { delegate.diagnostics(diagnostics) }

        /** Alias for calling [Builder.diagnostics] with `diagnostics.orElse(null)`. */
        fun diagnostics(diagnostics: Optional<BetaDiagnostics>) =
            diagnostics(diagnostics.getOrNull())

        /**
         * Sets [Builder.diagnostics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.diagnostics] with a well-typed [BetaDiagnostics] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun diagnostics(diagnostics: JsonField<BetaDiagnostics>) = apply {
            delegate.diagnostics(diagnostics)
        }

        /** @see BetaMessage.Builder.model */
        fun model(model: Model) = apply { delegate.model(model) }

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [Model] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<Model>) = apply { delegate.model(model) }

        /**
         * Sets [model] to an arbitrary [String].
         *
         * You should usually call [model] with a well-typed [Model] constant instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(value: String) = apply { delegate.model(value) }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("assistant")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun role(role: JsonValue) = apply { delegate.role(role) }

        /** @see BetaMessage.Builder.stopDetails */
        fun stopDetails(stopDetails: BetaRefusalStopDetails?) = apply {
            delegate.stopDetails(stopDetails)
        }

        /** Alias for calling [Builder.stopDetails] with `stopDetails.orElse(null)`. */
        fun stopDetails(stopDetails: Optional<BetaRefusalStopDetails>) =
            stopDetails(stopDetails.getOrNull())

        /**
         * Sets [Builder.stopDetails] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopDetails] with a well-typed [BetaRefusalStopDetails]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun stopDetails(stopDetails: JsonField<BetaRefusalStopDetails>) = apply {
            delegate.stopDetails(stopDetails)
        }

        /** @see BetaMessage.Builder.stopReason */
        fun stopReason(stopReason: BetaStopReason?) = apply { delegate.stopReason(stopReason) }

        /** Alias for calling [Builder.stopReason] with `stopReason.orElse(null)`. */
        fun stopReason(stopReason: Optional<BetaStopReason>) = stopReason(stopReason.getOrNull())

        /**
         * Sets [Builder.stopReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopReason] with a well-typed [BetaStopReason] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun stopReason(stopReason: JsonField<BetaStopReason>) = apply {
            delegate.stopReason(stopReason)
        }

        /** @see BetaMessage.Builder.stopSequence */
        fun stopSequence(stopSequence: String?) = apply { delegate.stopSequence(stopSequence) }

        /** Alias for calling [Builder.stopSequence] with `stopSequence.orElse(null)`. */
        fun stopSequence(stopSequence: Optional<String>) = stopSequence(stopSequence.getOrNull())

        /**
         * Sets [Builder.stopSequence] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopSequence] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun stopSequence(stopSequence: JsonField<String>) = apply {
            delegate.stopSequence(stopSequence)
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("message")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { delegate.type(type) }

        /** @see BetaMessage.Builder.usage */
        fun usage(usage: BetaUsage) = apply { delegate.usage(usage) }

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed [BetaUsage] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun usage(usage: JsonField<BetaUsage>) = apply { delegate.usage(usage) }

        /** @see BetaMessage.Builder.inputTransformations */
        fun inputTransformations(inputTransformations: List<BetaInputTransformation>?) = apply {
            delegate.inputTransformations(inputTransformations)
        }

        /**
         * Alias for calling [Builder.inputTransformations] with
         * `inputTransformations.orElse(null)`.
         */
        fun inputTransformations(inputTransformations: Optional<List<BetaInputTransformation>>) =
            inputTransformations(inputTransformations.getOrNull())

        /**
         * Sets [Builder.inputTransformations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputTransformations] with a well-typed
         * `List<BetaInputTransformation>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun inputTransformations(inputTransformations: JsonField<List<BetaInputTransformation>>) =
            apply {
                delegate.inputTransformations(inputTransformations)
            }

        /**
         * Adds a single [BetaInputTransformation] to [inputTransformations].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInputTransformation(inputTransformation: BetaInputTransformation) = apply {
            delegate.addInputTransformation(inputTransformation)
        }

        /**
         * Alias for calling [addInputTransformation] with
         * `BetaInputTransformation.ofThinkingDropped(thinkingDropped)`.
         */
        fun addInputTransformation(thinkingDropped: BetaThinkingDroppedInputTransformation) =
            apply {
                delegate.addInputTransformation(thinkingDropped)
            }

        /**
         * Alias for calling [addInputTransformation] with
         * `BetaInputTransformation.ofThinkingMismatchAllowed(thinkingMismatchAllowed)`.
         */
        fun addInputTransformation(
            thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
        ) = apply { delegate.addInputTransformation(thinkingMismatchAllowed) }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            delegate.additionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            delegate.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            delegate.putAllAdditionalProperties(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { delegate.removeAdditionalProperty(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            delegate.removeAllAdditionalProperties(keys)
        }

        /**
         * Returns an immutable instance of [StructuredMessage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .container()
         * .content()
         * .contextManagement()
         * .diagnostics()
         * .model()
         * .stopDetails()
         * .stopReason()
         * .stopSequence()
         * .usage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): StructuredMessage<T> = StructuredMessage(outputType, delegate.build())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StructuredMessage<*> &&
            outputType == other.outputType &&
            delegate == other.delegate
    }

    override fun hashCode(): Int = Objects.hash(outputType, delegate)

    override fun toString() = "StructuredMessage{outputType=$outputType, rawMessage=$delegate}"
}
