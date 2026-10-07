package com.anthropic.models.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.outputFormatFromClass
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A wrapper for [MessageCreateParams] that provides a type-safe [Builder] that can record the
 * [outputType] used to derive a JSON schema from an arbitrary class when using the _Structured
 * Outputs_ feature. When a JSON response is received, it is deserialized to an instance of that
 * type. See the SDK documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class that will be used to derive the JSON schema in the request and to
 *   which the JSON response will be deserialized.
 */
class StructuredMessageCreateParams<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    private val delegate: MessageCreateParams,
) {

    /**
     * The raw, underlying message create parameters wrapped by this structured instance of the
     * parameters.
     */
    @get:JvmName("rawParams")
    val rawParams: MessageCreateParams
        get() = delegate

    /** @see MessageCreateParams.userProfileId */
    fun userProfileId(): Optional<String> = delegate.userProfileId()

    /** @see MessageCreateParams.workspaceId */
    fun workspaceId(): Optional<String> = delegate.workspaceId()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see MessageCreateParams.maxTokens
     */
    fun maxTokens(): Long = delegate.maxTokens()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see MessageCreateParams.messages
     */
    fun messages(): List<MessageParam> = delegate.messages()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see MessageCreateParams.model
     */
    fun model(): Model = delegate.model()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.cacheControl
     */
    fun cacheControl(): Optional<CacheControlEphemeral> = delegate.cacheControl()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.container
     */
    fun container(): Optional<MessageCreateParamsContainer> = delegate.container()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.diagnostics
     */
    fun diagnostics(): Optional<DiagnosticsParam> = delegate.diagnostics()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.inferenceGeo
     */
    fun inferenceGeo(): Optional<String> = delegate.inferenceGeo()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.metadata
     */
    fun metadata(): Optional<Metadata> = delegate.metadata()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.outputConfig
     */
    fun outputConfig(): Optional<OutputConfig> = delegate.outputConfig()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.serviceTier
     */
    fun serviceTier(): Optional<MessageCreateParams.ServiceTier> = delegate.serviceTier()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.stopSequences
     */
    fun stopSequences(): Optional<List<String>> = delegate.stopSequences()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.system
     */
    fun system(): Optional<MessageCreateParams.System> = delegate.system()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.temperature
     */
    @Deprecated(
        "Deprecated. Models released after Claude Opus 4.6 do not support setting temperature. A value of 1.0 will be accepted for backwards compatibility, all other values will be rejected with a 400 error."
    )
    fun temperature(): Optional<Double> = delegate.temperature()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.thinking
     */
    fun thinking(): Optional<ThinkingConfigParam> = delegate.thinking()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.toolChoice
     */
    fun toolChoice(): Optional<ToolChoice> = delegate.toolChoice()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.tools
     */
    fun tools(): Optional<List<ToolUnion>> = delegate.tools()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.topK
     */
    @Deprecated(
        "Deprecated. Models released after Claude Opus 4.6 do not accept top_k; any value will be rejected with a 400 error."
    )
    fun topK(): Optional<Long> = delegate.topK()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.topP
     */
    @Deprecated(
        "Deprecated. Models released after Claude Opus 4.6 do not support setting top_p. A value >= 0.99 will be accepted for backwards compatibility, all other values will be rejected with a 400 error."
    )
    fun topP(): Optional<Double> = delegate.topP()

    /**
     * Returns the raw JSON value of [maxTokens].
     *
     * Unlike [maxTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _maxTokens(): JsonField<Long> = delegate._maxTokens()

    /**
     * Returns the raw JSON value of [messages].
     *
     * Unlike [messages], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _messages(): JsonField<List<MessageParam>> = delegate._messages()

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _model(): JsonField<Model> = delegate._model()

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _cacheControl(): JsonField<CacheControlEphemeral> = delegate._cacheControl()

    /**
     * Returns the raw JSON value of [container].
     *
     * Unlike [container], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _container(): JsonField<MessageCreateParamsContainer> = delegate._container()

    /**
     * Returns the raw JSON value of [diagnostics].
     *
     * Unlike [diagnostics], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _diagnostics(): JsonField<DiagnosticsParam> = delegate._diagnostics()

    /**
     * Returns the raw JSON value of [inferenceGeo].
     *
     * Unlike [inferenceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _inferenceGeo(): JsonField<String> = delegate._inferenceGeo()

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _metadata(): JsonField<Metadata> = delegate._metadata()

    /**
     * Returns the raw JSON value of [outputConfig].
     *
     * Unlike [outputConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _outputConfig(): JsonField<OutputConfig> = delegate._outputConfig()

    /**
     * Returns the raw JSON value of [serviceTier].
     *
     * Unlike [serviceTier], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _serviceTier(): JsonField<MessageCreateParams.ServiceTier> = delegate._serviceTier()

    /**
     * Returns the raw JSON value of [stopSequences].
     *
     * Unlike [stopSequences], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _stopSequences(): JsonField<List<String>> = delegate._stopSequences()

    /**
     * Returns the raw JSON value of [system].
     *
     * Unlike [system], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _system(): JsonField<MessageCreateParams.System> = delegate._system()

    /**
     * Returns the raw JSON value of [temperature].
     *
     * Unlike [temperature], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated(
        "Deprecated. Models released after Claude Opus 4.6 do not support setting temperature. A value of 1.0 will be accepted for backwards compatibility, all other values will be rejected with a 400 error."
    )
    fun _temperature(): JsonField<Double> = delegate._temperature()

    /**
     * Returns the raw JSON value of [thinking].
     *
     * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _thinking(): JsonField<ThinkingConfigParam> = delegate._thinking()

    /**
     * Returns the raw JSON value of [toolChoice].
     *
     * Unlike [toolChoice], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _toolChoice(): JsonField<ToolChoice> = delegate._toolChoice()

    /**
     * Returns the raw JSON value of [tools].
     *
     * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _tools(): JsonField<List<ToolUnion>> = delegate._tools()

    /**
     * Returns the raw JSON value of [topK].
     *
     * Unlike [topK], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated(
        "Deprecated. Models released after Claude Opus 4.6 do not accept top_k; any value will be rejected with a 400 error."
    )
    fun _topK(): JsonField<Long> = delegate._topK()

    /**
     * Returns the raw JSON value of [topP].
     *
     * Unlike [topP], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated(
        "Deprecated. Models released after Claude Opus 4.6 do not support setting top_p. A value >= 0.99 will be accepted for backwards compatibility, all other values will be rejected with a 400 error."
    )
    fun _topP(): JsonField<Double> = delegate._topP()

    /** @see MessageCreateParams._additionalBodyProperties */
    fun _additionalBodyProperties(): Map<String, JsonValue> = delegate._additionalBodyProperties()

    /** @see MessageCreateParams._additionalHeaders */
    fun _additionalHeaders(): Headers = delegate._additionalHeaders()

    /** @see MessageCreateParams._additionalQueryParams */
    fun _additionalQueryParams(): QueryParams = delegate._additionalQueryParams()

    fun toBuilder() = Builder<T>().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [StructuredMessageCreateParams].
         *
         * The following fields are required:
         * ```java
         * .maxTokens()
         * .messages()
         * .model()
         * ```
         */
        @JvmStatic fun <T : Any> builder() = Builder<T>()
    }

    /** A builder for [StructuredMessageCreateParams]. */
    class Builder<T : Any> internal constructor() {

        private var outputType: Class<T>? = null
        private var delegate: MessageCreateParams.Builder = MessageCreateParams.builder()

        @JvmSynthetic
        internal fun from(structuredMessageCreateParams: StructuredMessageCreateParams<T>) = apply {
            outputType = structuredMessageCreateParams.outputType
            delegate = structuredMessageCreateParams.delegate.toBuilder()
        }

        @JvmSynthetic
        internal fun wrap(delegate: MessageCreateParams.Builder) = apply {
            this.delegate = delegate
        }

        /** @see MessageCreateParams.Builder.userProfileId */
        fun userProfileId(userProfileId: String?) = apply { delegate.userProfileId(userProfileId) }

        /** Alias for calling [Builder.userProfileId] with `userProfileId.orElse(null)`. */
        fun userProfileId(userProfileId: Optional<String>) =
            userProfileId(userProfileId.getOrNull())

        /** @see MessageCreateParams.Builder.workspaceId */
        fun workspaceId(workspaceId: String?) = apply { delegate.workspaceId(workspaceId) }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /** @see MessageCreateParams.Builder.maxTokens */
        fun maxTokens(maxTokens: Long) = apply { delegate.maxTokens(maxTokens) }

        /**
         * Sets [Builder.maxTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxTokens] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun maxTokens(maxTokens: JsonField<Long>) = apply { delegate.maxTokens(maxTokens) }

        /** @see MessageCreateParams.Builder.messages */
        fun messages(messages: List<MessageParam>) = apply { delegate.messages(messages) }

        /**
         * Sets [Builder.messages] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messages] with a well-typed `List<MessageParam>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun messages(messages: JsonField<List<MessageParam>>) = apply {
            delegate.messages(messages)
        }

        /**
         * Adds a single [MessageParam] to [messages].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMessage(message: MessageParam) = apply { delegate.addMessage(message) }

        /** Alias for calling [addMessage] with `message.toParam()`. */
        fun addMessage(message: Message) = apply { delegate.addMessage(message) }

        /**
         * Alias for calling [addMessage] with the following:
         * ```java
         * MessageParam.builder()
         *     .role(MessageParam.Role.USER)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addUserMessage(content: MessageParam.Content) = apply {
            delegate.addUserMessage(content)
        }

        /** Alias for calling [addUserMessage] with `MessageParam.Content.ofString(string)`. */
        fun addUserMessage(string: String) = apply { delegate.addUserMessage(string) }

        /**
         * Alias for calling [addUserMessage] with
         * `MessageParam.Content.ofBlockParams(blockParams)`.
         */
        fun addUserMessageOfBlockParams(blockParams: List<ContentBlockParam>) = apply {
            delegate.addUserMessageOfBlockParams(blockParams)
        }

        /**
         * Alias for calling [addMessage] with the following:
         * ```java
         * MessageParam.builder()
         *     .role(MessageParam.Role.ASSISTANT)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addAssistantMessage(content: MessageParam.Content) = apply {
            delegate.addAssistantMessage(content)
        }

        /** Alias for calling [addAssistantMessage] with `MessageParam.Content.ofString(string)`. */
        fun addAssistantMessage(string: String) = apply { delegate.addAssistantMessage(string) }

        /**
         * Alias for calling [addAssistantMessage] with
         * `MessageParam.Content.ofBlockParams(blockParams)`.
         */
        fun addAssistantMessageOfBlockParams(blockParams: List<ContentBlockParam>) = apply {
            delegate.addAssistantMessageOfBlockParams(blockParams)
        }

        /**
         * Alias for calling [addMessage] with the following:
         * ```java
         * MessageParam.builder()
         *     .role(MessageParam.Role.SYSTEM)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addSystemMessage(content: MessageParam.Content) = apply {
            delegate.addSystemMessage(content)
        }

        /** Alias for calling [addSystemMessage] with `MessageParam.Content.ofString(string)`. */
        fun addSystemMessage(string: String) = apply { delegate.addSystemMessage(string) }

        /**
         * Alias for calling [addSystemMessage] with
         * `MessageParam.Content.ofBlockParams(blockParams)`.
         */
        fun addSystemMessageOfBlockParams(blockParams: List<ContentBlockParam>) = apply {
            delegate.addSystemMessageOfBlockParams(blockParams)
        }

        /** @see MessageCreateParams.Builder.model */
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

        /** @see MessageCreateParams.Builder.cacheControl */
        fun cacheControl(cacheControl: CacheControlEphemeral?) = apply {
            delegate.cacheControl(cacheControl)
        }

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed [CacheControlEphemeral]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            delegate.cacheControl(cacheControl)
        }

        /** @see MessageCreateParams.Builder.container */
        fun container(container: MessageCreateParamsContainer?) = apply {
            delegate.container(container)
        }

        /** Alias for calling [Builder.container] with `container.orElse(null)`. */
        fun container(container: Optional<MessageCreateParamsContainer>) =
            container(container.getOrNull())

        /**
         * Sets [Builder.container] to an arbitrary JSON value.
         *
         * You should usually call [Builder.container] with a well-typed
         * [MessageCreateParamsContainer] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun container(container: JsonField<MessageCreateParamsContainer>) = apply {
            delegate.container(container)
        }

        /**
         * Alias for calling [container] with
         * `MessageCreateParamsContainer.ofContainerParams(containerParams)`.
         */
        fun container(containerParams: ContainerParams) = apply {
            delegate.container(containerParams)
        }

        /** Alias for calling [container] with `MessageCreateParamsContainer.ofString(string)`. */
        fun container(string: String) = apply { delegate.container(string) }

        /** @see MessageCreateParams.Builder.diagnostics */
        fun diagnostics(diagnostics: DiagnosticsParam?) = apply {
            delegate.diagnostics(diagnostics)
        }

        /** Alias for calling [Builder.diagnostics] with `diagnostics.orElse(null)`. */
        fun diagnostics(diagnostics: Optional<DiagnosticsParam>) =
            diagnostics(diagnostics.getOrNull())

        /**
         * Sets [Builder.diagnostics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.diagnostics] with a well-typed [DiagnosticsParam] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun diagnostics(diagnostics: JsonField<DiagnosticsParam>) = apply {
            delegate.diagnostics(diagnostics)
        }

        /** @see MessageCreateParams.Builder.inferenceGeo */
        fun inferenceGeo(inferenceGeo: String?) = apply { delegate.inferenceGeo(inferenceGeo) }

        /** Alias for calling [Builder.inferenceGeo] with `inferenceGeo.orElse(null)`. */
        fun inferenceGeo(inferenceGeo: Optional<String>) = inferenceGeo(inferenceGeo.getOrNull())

        /**
         * Sets [Builder.inferenceGeo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inferenceGeo] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun inferenceGeo(inferenceGeo: JsonField<String>) = apply {
            delegate.inferenceGeo(inferenceGeo)
        }

        /** @see MessageCreateParams.Builder.metadata */
        fun metadata(metadata: Metadata) = apply { delegate.metadata(metadata) }

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { delegate.metadata(metadata) }

        /**
         * Sets the output configuration, carrying over the output format already set unless the
         * given output configuration sets its own, as the response will still be deserialized to an
         * instance of the output type.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        fun outputConfig(outputConfig: OutputConfig) = apply {
            val currentFormat = delegate.currentOutputConfig()?._format()?.asKnown()?.getOrNull()
            delegate.outputConfig(
                if (currentFormat != null && outputConfig._format().isMissing())
                    outputConfig.toBuilder().format(currentFormat).build()
                else outputConfig
            )
        }

        /**
         * Sets the output configuration, carrying over the output format already set unless the
         * given output configuration is not an [OutputConfig] value or sets its own.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        fun outputConfig(outputConfig: JsonField<OutputConfig>) = apply {
            val knownOutputConfig = outputConfig.asKnown().getOrNull()
            if (knownOutputConfig != null) outputConfig(knownOutputConfig)
            else delegate.outputConfig(outputConfig)
        }

        /**
         * Sets the output configuration, including a JSON schema format derived from the structure
         * of the output type of the given structured output configuration. Use this instead of the
         * output type alone to set other output configuration options alongside the output type.
         *
         * Unlike the beta version, this GA version does NOT auto-inject any beta header.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        fun outputConfig(outputConfig: StructuredOutputConfig<T>) = apply {
            outputType = outputConfig.outputType
            delegate.outputConfig(outputConfig.rawOutputConfig)
            // GA version: NO beta header injection
        }

        /**
         * Sets the output format of the output configuration to a JSON schema derived from the
         * structure of the given class, preserving any other output configuration options already
         * set. This is the recommended way to specify structured outputs.
         *
         * Unlike the beta version, this GA version does NOT auto-inject any beta header.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        @JvmOverloads
        fun outputConfig(
            outputType: Class<T>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = apply {
            this.outputType = outputType
            val builder = delegate.currentOutputConfig()?.toBuilder() ?: OutputConfig.builder()
            delegate.outputConfig(
                builder.format(outputFormatFromClass(outputType, localValidation)).build()
            )
            // GA version: NO beta header injection
        }

        /** @see MessageCreateParams.Builder.serviceTier */
        fun serviceTier(serviceTier: MessageCreateParams.ServiceTier) = apply {
            delegate.serviceTier(serviceTier)
        }

        /**
         * Sets [Builder.serviceTier] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serviceTier] with a well-typed
         * [MessageCreateParams.ServiceTier] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun serviceTier(serviceTier: JsonField<MessageCreateParams.ServiceTier>) = apply {
            delegate.serviceTier(serviceTier)
        }

        /** @see MessageCreateParams.Builder.stopSequences */
        fun stopSequences(stopSequences: List<String>) = apply {
            delegate.stopSequences(stopSequences)
        }

        /**
         * Sets [Builder.stopSequences] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopSequences] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
            delegate.stopSequences(stopSequences)
        }

        /**
         * Adds a single [String] to [stopSequences].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addStopSequence(stopSequence: String) = apply { delegate.addStopSequence(stopSequence) }

        /** @see MessageCreateParams.Builder.system */
        fun system(system: MessageCreateParams.System) = apply { delegate.system(system) }

        /**
         * Sets [Builder.system] to an arbitrary JSON value.
         *
         * You should usually call [Builder.system] with a well-typed [MessageCreateParams.System]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun system(system: JsonField<MessageCreateParams.System>) = apply {
            delegate.system(system)
        }

        /** Alias for calling [system] with `MessageCreateParams.System.ofString(string)`. */
        fun system(string: String) = apply { delegate.system(string) }

        /**
         * Alias for calling [system] with
         * `MessageCreateParams.System.ofTextBlockParams(textBlockParams)`.
         */
        fun systemOfTextBlockParams(textBlockParams: List<TextBlockParam>) = apply {
            delegate.systemOfTextBlockParams(textBlockParams)
        }

        /** @see MessageCreateParams.Builder.temperature */
        @Deprecated(
            "Deprecated. Models released after Claude Opus 4.6 do not support setting temperature. A value of 1.0 will be accepted for backwards compatibility, all other values will be rejected with a 400 error."
        )
        fun temperature(temperature: Double) = apply { delegate.temperature(temperature) }

        /**
         * Sets [Builder.temperature] to an arbitrary JSON value.
         *
         * You should usually call [Builder.temperature] with a well-typed [Double] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        @Deprecated(
            "Deprecated. Models released after Claude Opus 4.6 do not support setting temperature. A value of 1.0 will be accepted for backwards compatibility, all other values will be rejected with a 400 error."
        )
        fun temperature(temperature: JsonField<Double>) = apply {
            delegate.temperature(temperature)
        }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(thinking: ThinkingConfigParam) = apply { delegate.thinking(thinking) }

        /**
         * Sets [Builder.thinking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinking] with a well-typed [ThinkingConfigParam] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun thinking(thinking: JsonField<ThinkingConfigParam>) = apply {
            delegate.thinking(thinking)
        }

        /** Alias for calling [thinking] with `ThinkingConfigParam.ofEnabled(enabled)`. */
        fun thinking(enabled: ThinkingConfigEnabled) = apply { delegate.thinking(enabled) }

        /**
         * Alias for calling [thinking] with the following:
         * ```java
         * ThinkingConfigEnabled.builder()
         *     .budgetTokens(budgetTokens)
         *     .build()
         * ```
         */
        fun enabledThinking(budgetTokens: Long) = apply { delegate.enabledThinking(budgetTokens) }

        /** Alias for calling [thinking] with `ThinkingConfigParam.ofDisabled(disabled)`. */
        fun thinking(disabled: ThinkingConfigDisabled) = apply { delegate.thinking(disabled) }

        /** Alias for calling [thinking] with `ThinkingConfigParam.ofBetweenTools(betweenTools)`. */
        fun thinking(betweenTools: ThinkingConfigBetweenTools) = apply {
            delegate.thinking(betweenTools)
        }

        /** Alias for calling [thinking] with `ThinkingConfigParam.ofAdaptive(adaptive)`. */
        fun thinking(adaptive: ThinkingConfigAdaptive) = apply { delegate.thinking(adaptive) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(toolChoice: ToolChoice) = apply { delegate.toolChoice(toolChoice) }

        /**
         * Sets [Builder.toolChoice] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolChoice] with a well-typed [ToolChoice] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun toolChoice(toolChoice: JsonField<ToolChoice>) = apply {
            delegate.toolChoice(toolChoice)
        }

        /** Alias for calling [toolChoice] with `ToolChoice.ofAuto(auto)`. */
        fun toolChoice(auto: ToolChoiceAuto) = apply { delegate.toolChoice(auto) }

        /** Alias for calling [toolChoice] with `ToolChoice.ofAny(any)`. */
        fun toolChoice(any: ToolChoiceAny) = apply { delegate.toolChoice(any) }

        /** Alias for calling [toolChoice] with `ToolChoice.ofTool(tool)`. */
        fun toolChoice(tool: ToolChoiceTool) = apply { delegate.toolChoice(tool) }

        /**
         * Alias for calling [toolChoice] with the following:
         * ```java
         * ToolChoiceTool.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun toolToolChoice(name: String) = apply { delegate.toolToolChoice(name) }

        /** Alias for calling [toolChoice] with `ToolChoice.ofNone(none)`. */
        fun toolChoice(none: ToolChoiceNone) = apply { delegate.toolChoice(none) }

        /** @see MessageCreateParams.Builder.tools */
        fun tools(tools: List<ToolUnion>) = apply { delegate.tools(tools) }

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed `List<ToolUnion>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun tools(tools: JsonField<List<ToolUnion>>) = apply { delegate.tools(tools) }

        /**
         * Adds a single [ToolUnion] to [tools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTool(tool: ToolUnion) = apply { delegate.addTool(tool) }

        /** Alias for calling [addTool] with `ToolUnion.ofTool(tool)`. */
        fun addTool(tool: Tool) = apply { delegate.addTool(tool) }

        /** Alias for calling [addTool] with `ToolUnion.ofBash20250124(bash20250124)`. */
        fun addTool(bash20250124: ToolBash20250124) = apply { delegate.addTool(bash20250124) }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)`.
         */
        fun addTool(codeExecutionTool20250522: CodeExecutionTool20250522) = apply {
            delegate.addTool(codeExecutionTool20250522)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)`.
         */
        fun addTool(codeExecutionTool20250825: CodeExecutionTool20250825) = apply {
            delegate.addTool(codeExecutionTool20250825)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)`.
         */
        fun addTool(codeExecutionTool20260120: CodeExecutionTool20260120) = apply {
            delegate.addTool(codeExecutionTool20260120)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)`.
         */
        fun addTool(codeExecutionTool20260521: CodeExecutionTool20260521) = apply {
            delegate.addTool(codeExecutionTool20260521)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofBrowserToolset20260801(browserToolset20260801)`.
         */
        fun addTool(browserToolset20260801: BrowserToolset20260801) = apply {
            delegate.addTool(browserToolset20260801)
        }

        /**
         * Alias for calling [addTool] with `ToolUnion.ofMemoryTool20250818(memoryTool20250818)`.
         */
        fun addTool(memoryTool20250818: MemoryTool20250818) = apply {
            delegate.addTool(memoryTool20250818)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofComputerToolset20260801(computerToolset20260801)`.
         */
        fun addTool(computerToolset20260801: ComputerToolset20260801) = apply {
            delegate.addTool(computerToolset20260801)
        }

        /**
         * Alias for calling [addTool] with `ToolUnion.ofTextEditor20250124(textEditor20250124)`.
         */
        fun addTool(textEditor20250124: ToolTextEditor20250124) = apply {
            delegate.addTool(textEditor20250124)
        }

        /**
         * Alias for calling [addTool] with `ToolUnion.ofTextEditor20250429(textEditor20250429)`.
         */
        fun addTool(textEditor20250429: ToolTextEditor20250429) = apply {
            delegate.addTool(textEditor20250429)
        }

        /**
         * Alias for calling [addTool] with `ToolUnion.ofTextEditor20250728(textEditor20250728)`.
         */
        fun addTool(textEditor20250728: ToolTextEditor20250728) = apply {
            delegate.addTool(textEditor20250728)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofWebSearchTool20250305(webSearchTool20250305)`.
         */
        fun addTool(webSearchTool20250305: WebSearchTool20250305) = apply {
            delegate.addTool(webSearchTool20250305)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofWebFetchTool20250910(webFetchTool20250910)`.
         */
        fun addTool(webFetchTool20250910: WebFetchTool20250910) = apply {
            delegate.addTool(webFetchTool20250910)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofWebSearchTool20260209(webSearchTool20260209)`.
         */
        fun addTool(webSearchTool20260209: WebSearchTool20260209) = apply {
            delegate.addTool(webSearchTool20260209)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofWebFetchTool20260209(webFetchTool20260209)`.
         */
        fun addTool(webFetchTool20260209: WebFetchTool20260209) = apply {
            delegate.addTool(webFetchTool20260209)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofWebFetchTool20260309(webFetchTool20260309)`.
         */
        fun addTool(webFetchTool20260309: WebFetchTool20260309) = apply {
            delegate.addTool(webFetchTool20260309)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofWebSearchTool20260318(webSearchTool20260318)`.
         */
        fun addTool(webSearchTool20260318: WebSearchTool20260318) = apply {
            delegate.addTool(webSearchTool20260318)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofWebFetchTool20260318(webFetchTool20260318)`.
         */
        fun addTool(webFetchTool20260318: WebFetchTool20260318) = apply {
            delegate.addTool(webFetchTool20260318)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofSearchToolBm25_20251119(searchToolBm25_20251119)`.
         */
        fun addTool(searchToolBm25_20251119: ToolSearchToolBm25_20251119) = apply {
            delegate.addTool(searchToolBm25_20251119)
        }

        /**
         * Alias for calling [addTool] with
         * `ToolUnion.ofSearchToolRegex20251119(searchToolRegex20251119)`.
         */
        fun addTool(searchToolRegex20251119: ToolSearchToolRegex20251119) = apply {
            delegate.addTool(searchToolRegex20251119)
        }

        /** @see MessageCreateParams.Builder.topK */
        @Deprecated(
            "Deprecated. Models released after Claude Opus 4.6 do not accept top_k; any value will be rejected with a 400 error."
        )
        fun topK(topK: Long) = apply { delegate.topK(topK) }

        /**
         * Sets [Builder.topK] to an arbitrary JSON value.
         *
         * You should usually call [Builder.topK] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        @Deprecated(
            "Deprecated. Models released after Claude Opus 4.6 do not accept top_k; any value will be rejected with a 400 error."
        )
        fun topK(topK: JsonField<Long>) = apply { delegate.topK(topK) }

        /** @see MessageCreateParams.Builder.topP */
        @Deprecated(
            "Deprecated. Models released after Claude Opus 4.6 do not support setting top_p. A value >= 0.99 will be accepted for backwards compatibility, all other values will be rejected with a 400 error."
        )
        fun topP(topP: Double) = apply { delegate.topP(topP) }

        /**
         * Sets [Builder.topP] to an arbitrary JSON value.
         *
         * You should usually call [Builder.topP] with a well-typed [Double] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        @Deprecated(
            "Deprecated. Models released after Claude Opus 4.6 do not support setting top_p. A value >= 0.99 will be accepted for backwards compatibility, all other values will be rejected with a 400 error."
        )
        fun topP(topP: JsonField<Double>) = apply { delegate.topP(topP) }

        /** @see MessageCreateParams.Builder.body */
        fun body(body: MessageCreateParams.Body) = apply { delegate.body(body) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            delegate.additionalBodyProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            delegate.putAdditionalBodyProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                delegate.putAllAdditionalBodyProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply {
            delegate.removeAdditionalBodyProperty(key)
        }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            delegate.removeAllAdditionalBodyProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            delegate.additionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            delegate.additionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            delegate.putAdditionalHeader(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            delegate.putAdditionalHeaders(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            delegate.putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            delegate.putAllAdditionalHeaders(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            delegate.replaceAdditionalHeaders(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            delegate.replaceAdditionalHeaders(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            delegate.replaceAllAdditionalHeaders(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            delegate.replaceAllAdditionalHeaders(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { delegate.removeAdditionalHeaders(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            delegate.removeAllAdditionalHeaders(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            delegate.additionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            delegate.additionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            delegate.putAdditionalQueryParam(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            delegate.putAdditionalQueryParams(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            delegate.putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                delegate.putAllAdditionalQueryParams(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            delegate.replaceAdditionalQueryParams(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            delegate.replaceAdditionalQueryParams(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            delegate.replaceAllAdditionalQueryParams(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                delegate.replaceAllAdditionalQueryParams(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply {
            delegate.removeAdditionalQueryParams(key)
        }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            delegate.removeAllAdditionalQueryParams(keys)
        }

        /**
         * Returns an immutable instance of [StructuredMessageCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .maxTokens()
         * .messages()
         * .model()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): StructuredMessageCreateParams<T> =
            StructuredMessageCreateParams(checkRequired("outputType", outputType), delegate.build())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StructuredMessageCreateParams<*> &&
            outputType == other.outputType &&
            delegate == other.delegate
    }

    override fun hashCode(): Int = Objects.hash(outputType, delegate)

    override fun toString() =
        "StructuredMessageCreateParams{outputType=$outputType, rawParams=$delegate}"
}
