package com.anthropic.models.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A wrapper for [Message] that provides type-safe access to the [content] when using the
 * _Structured Outputs_ feature to deserialize a JSON response to an instance of an arbitrary class.
 * See the SDK documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class to which the JSON data in the response will be deserialized.
 */
class StructuredMessage<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    private val delegate: Message,
) {

    @get:JvmName("rawMessage")
    val rawMessage: Message
        get() = delegate

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see Message.id
     */
    fun id(): String = delegate.id()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see Message.container
     */
    fun container(): Optional<Container> = delegate.container()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see Message.content
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
     * @see Message.diagnostics
     */
    fun diagnostics(): Optional<Diagnostics> = delegate.diagnostics()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see Message.model
     */
    fun model(): Model = delegate.model()

    /** @see Message._role */
    fun _role(): JsonValue = delegate._role()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see Message.stopDetails
     */
    fun stopDetails(): Optional<RefusalStopDetails> = delegate.stopDetails()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see Message.stopReason
     */
    fun stopReason(): Optional<StopReason> = delegate.stopReason()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see Message.stopSequence
     */
    fun stopSequence(): Optional<String> = delegate.stopSequence()

    /** @see Message._type */
    fun _type(): JsonValue = delegate._type()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see Message.usage
     */
    fun usage(): Usage = delegate.usage()

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
    fun _container(): JsonField<Container> = delegate._container()

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _content(): JsonField<List<StructuredContentBlock<T>>> = content

    /**
     * Returns the raw JSON value of [diagnostics].
     *
     * Unlike [diagnostics], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _diagnostics(): JsonField<Diagnostics> = delegate._diagnostics()

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
    fun _stopDetails(): JsonField<RefusalStopDetails> = delegate._stopDetails()

    /**
     * Returns the raw JSON value of [stopReason].
     *
     * Unlike [stopReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _stopReason(): JsonField<StopReason> = delegate._stopReason()

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
    fun _usage(): JsonField<Usage> = delegate._usage()

    /** @see Message._additionalProperties */
    fun _additionalProperties(): Map<String, JsonValue> = delegate._additionalProperties()

    /** @see Message.validate */
    fun validate(): StructuredMessage<T> = apply {
        content().forEach { it.validate() }
        delegate.validate()
    }

    /** @see Message.isValid */
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

        private var delegate: Message.Builder = Message.builder()

        @JvmSynthetic
        internal fun from(structuredMessage: StructuredMessage<T>) = apply {
            delegate = structuredMessage.delegate.toBuilder()
        }

        /** @see Message.Builder.id */
        fun id(id: String) = apply { delegate.id(id) }

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { delegate.id(id) }

        /** @see Message.Builder.container */
        fun container(container: Container?) = apply { delegate.container(container) }

        /** Alias for calling [Builder.container] with `container.orElse(null)`. */
        fun container(container: Optional<Container>) = container(container.getOrNull())

        /**
         * Sets [Builder.container] to an arbitrary JSON value.
         *
         * You should usually call [Builder.container] with a well-typed [Container] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun container(container: JsonField<Container>) = apply { delegate.container(container) }

        /** @see Message.Builder.content */
        fun content(content: List<ContentBlock>) = apply { delegate.content(content) }

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed `List<ContentBlock>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun content(content: JsonField<List<ContentBlock>>) = apply { delegate.content(content) }

        /**
         * Adds a single [ContentBlock] to [Builder.content].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addContent(content: ContentBlock) = apply { delegate.addContent(content) }

        /** Alias for calling [addContent] with `ContentBlock.ofText(text)`. */
        fun addContent(text: TextBlock) = apply { delegate.addContent(text) }

        /** Alias for calling [addContent] with `ContentBlock.ofThinking(thinking)`. */
        fun addContent(thinking: ThinkingBlock) = apply { delegate.addContent(thinking) }

        /**
         * Alias for calling [addContent] with `ContentBlock.ofRedactedThinking(redactedThinking)`.
         */
        fun addContent(redactedThinking: RedactedThinkingBlock) = apply {
            delegate.addContent(redactedThinking)
        }

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * RedactedThinkingBlock.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun addRedactedThinkingContent(data: String) = apply {
            delegate.addRedactedThinkingContent(data)
        }

        /** Alias for calling [addContent] with `ContentBlock.ofToolUse(toolUse)`. */
        fun addContent(toolUse: ToolUseBlock) = apply { delegate.addContent(toolUse) }

        /** Alias for calling [addContent] with `ContentBlock.ofServerToolUse(serverToolUse)`. */
        fun addContent(serverToolUse: ServerToolUseBlock) = apply {
            delegate.addContent(serverToolUse)
        }

        /**
         * Alias for calling [addContent] with
         * `ContentBlock.ofWebSearchToolResult(webSearchToolResult)`.
         */
        fun addContent(webSearchToolResult: WebSearchToolResultBlock) = apply {
            delegate.addContent(webSearchToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `ContentBlock.ofWebFetchToolResult(webFetchToolResult)`.
         */
        fun addContent(webFetchToolResult: WebFetchToolResultBlock) = apply {
            delegate.addContent(webFetchToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `ContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult)`.
         */
        fun addContent(codeExecutionToolResult: CodeExecutionToolResultBlock) = apply {
            delegate.addContent(codeExecutionToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `ContentBlock.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)`.
         */
        fun addContent(bashCodeExecutionToolResult: BashCodeExecutionToolResultBlock) = apply {
            delegate.addContent(bashCodeExecutionToolResult)
        }

        /**
         * Alias for calling [addContent] with
         * `ContentBlock.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)`.
         */
        fun addContent(textEditorCodeExecutionToolResult: TextEditorCodeExecutionToolResultBlock) =
            apply {
                delegate.addContent(textEditorCodeExecutionToolResult)
            }

        /**
         * Alias for calling [addContent] with
         * `ContentBlock.ofToolSearchToolResult(toolSearchToolResult)`.
         */
        fun addContent(toolSearchToolResult: ToolSearchToolResultBlock) = apply {
            delegate.addContent(toolSearchToolResult)
        }

        /**
         * Alias for calling [addContent] with `ContentBlock.ofContainerUpload(containerUpload)`.
         */
        fun addContent(containerUpload: ContainerUploadBlock) = apply {
            delegate.addContent(containerUpload)
        }

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * ContainerUploadBlock.builder()
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun addContainerUploadContent(fileId: String) = apply {
            delegate.addContainerUploadContent(fileId)
        }

        /** @see Message.Builder.diagnostics */
        fun diagnostics(diagnostics: Diagnostics?) = apply { delegate.diagnostics(diagnostics) }

        /** Alias for calling [Builder.diagnostics] with `diagnostics.orElse(null)`. */
        fun diagnostics(diagnostics: Optional<Diagnostics>) = diagnostics(diagnostics.getOrNull())

        /**
         * Sets [Builder.diagnostics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.diagnostics] with a well-typed [Diagnostics] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun diagnostics(diagnostics: JsonField<Diagnostics>) = apply {
            delegate.diagnostics(diagnostics)
        }

        /** @see Message.Builder.model */
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

        /** @see Message.Builder.stopDetails */
        fun stopDetails(stopDetails: RefusalStopDetails?) = apply {
            delegate.stopDetails(stopDetails)
        }

        /** Alias for calling [Builder.stopDetails] with `stopDetails.orElse(null)`. */
        fun stopDetails(stopDetails: Optional<RefusalStopDetails>) =
            stopDetails(stopDetails.getOrNull())

        /**
         * Sets [Builder.stopDetails] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopDetails] with a well-typed [RefusalStopDetails]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun stopDetails(stopDetails: JsonField<RefusalStopDetails>) = apply {
            delegate.stopDetails(stopDetails)
        }

        /** @see Message.Builder.stopReason */
        fun stopReason(stopReason: StopReason?) = apply { delegate.stopReason(stopReason) }

        /** Alias for calling [Builder.stopReason] with `stopReason.orElse(null)`. */
        fun stopReason(stopReason: Optional<StopReason>) = stopReason(stopReason.getOrNull())

        /**
         * Sets [Builder.stopReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopReason] with a well-typed [StopReason] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun stopReason(stopReason: JsonField<StopReason>) = apply {
            delegate.stopReason(stopReason)
        }

        /** @see Message.Builder.stopSequence */
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

        /** @see Message.Builder.usage */
        fun usage(usage: Usage) = apply { delegate.usage(usage) }

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed [Usage] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun usage(usage: JsonField<Usage>) = apply { delegate.usage(usage) }

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
