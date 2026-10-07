package com.anthropic.models.beta.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.betaOutputFormatFromClass
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.helpers.BetaRunnableTool
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.messages.Model
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

    /** @see MessageCreateParams.betas */
    fun betas(): Optional<List<AnthropicBeta>> = delegate.betas()

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
    fun messages(): List<BetaMessageParam> = delegate.messages()

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
    fun cacheControl(): Optional<BetaCacheControlEphemeral> = delegate.cacheControl()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.compaction
     */
    fun compaction(): Optional<BetaCompactionConfig> = delegate.compaction()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.container
     */
    fun container(): Optional<MessageCreateParams.Container> = delegate.container()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.contextManagement
     */
    fun contextManagement(): Optional<BetaContextManagementConfig> = delegate.contextManagement()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.diagnostics
     */
    fun diagnostics(): Optional<BetaDiagnosticsParam> = delegate.diagnostics()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.fallbackCreditToken
     */
    fun fallbackCreditToken(): Optional<MessageCreateParams.FallbackCreditToken> =
        delegate.fallbackCreditToken()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.fallbacks
     */
    fun fallbacks(): Optional<BetaFallbacksParam> = delegate.fallbacks()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.inferenceGeo
     */
    fun inferenceGeo(): Optional<String> = delegate.inferenceGeo()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.mcpServers
     */
    fun mcpServers(): Optional<List<BetaRequestMcpServerUrlDefinition>> = delegate.mcpServers()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.metadata
     */
    fun metadata(): Optional<BetaMetadata> = delegate.metadata()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.outputConfig
     */
    fun outputConfig(): Optional<BetaOutputConfig> = delegate.outputConfig()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.outputFormat
     */
    @Deprecated("deprecated")
    fun outputFormat(): Optional<BetaJsonOutputFormat> = delegate.outputFormat()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.serviceTier
     */
    fun serviceTier(): Optional<MessageCreateParams.ServiceTier> = delegate.serviceTier()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.speed
     */
    fun speed(): Optional<MessageCreateParams.Speed> = delegate.speed()

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
    fun thinking(): Optional<BetaThinkingConfigParam> = delegate.thinking()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.toolChoice
     */
    fun toolChoice(): Optional<BetaToolChoice> = delegate.toolChoice()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see MessageCreateParams.tools
     */
    fun tools(): Optional<List<BetaToolUnion>> = delegate.tools()

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
    fun _messages(): JsonField<List<BetaMessageParam>> = delegate._messages()

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
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = delegate._cacheControl()

    /**
     * Returns the raw JSON value of [compaction].
     *
     * Unlike [compaction], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _compaction(): JsonField<BetaCompactionConfig> = delegate._compaction()

    /**
     * Returns the raw JSON value of [container].
     *
     * Unlike [container], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _container(): JsonField<MessageCreateParams.Container> = delegate._container()

    /**
     * Returns the raw JSON value of [contextManagement].
     *
     * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _contextManagement(): JsonField<BetaContextManagementConfig> = delegate._contextManagement()

    /**
     * Returns the raw JSON value of [diagnostics].
     *
     * Unlike [diagnostics], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _diagnostics(): JsonField<BetaDiagnosticsParam> = delegate._diagnostics()

    /**
     * Returns the raw JSON value of [fallbackCreditToken].
     *
     * Unlike [fallbackCreditToken], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _fallbackCreditToken(): JsonField<MessageCreateParams.FallbackCreditToken> =
        delegate._fallbackCreditToken()

    /**
     * Returns the raw JSON value of [fallbacks].
     *
     * Unlike [fallbacks], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _fallbacks(): JsonField<BetaFallbacksParam> = delegate._fallbacks()

    /**
     * Returns the raw JSON value of [inferenceGeo].
     *
     * Unlike [inferenceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _inferenceGeo(): JsonField<String> = delegate._inferenceGeo()

    /**
     * Returns the raw JSON value of [mcpServers].
     *
     * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _mcpServers(): JsonField<List<BetaRequestMcpServerUrlDefinition>> = delegate._mcpServers()

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _metadata(): JsonField<BetaMetadata> = delegate._metadata()

    /**
     * Returns the raw JSON value of [outputConfig].
     *
     * Unlike [outputConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _outputConfig(): JsonField<BetaOutputConfig> = delegate._outputConfig()

    /**
     * Returns the raw JSON value of [outputFormat].
     *
     * Unlike [outputFormat], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated("deprecated")
    fun _outputFormat(): JsonField<BetaJsonOutputFormat> = delegate._outputFormat()

    /**
     * Returns the raw JSON value of [serviceTier].
     *
     * Unlike [serviceTier], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _serviceTier(): JsonField<MessageCreateParams.ServiceTier> = delegate._serviceTier()

    /**
     * Returns the raw JSON value of [speed].
     *
     * Unlike [speed], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _speed(): JsonField<MessageCreateParams.Speed> = delegate._speed()

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
    fun _thinking(): JsonField<BetaThinkingConfigParam> = delegate._thinking()

    /**
     * Returns the raw JSON value of [toolChoice].
     *
     * Unlike [toolChoice], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _toolChoice(): JsonField<BetaToolChoice> = delegate._toolChoice()

    /**
     * Returns the raw JSON value of [tools].
     *
     * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _tools(): JsonField<List<BetaToolUnion>> = delegate._tools()

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

        /** @see MessageCreateParams.Builder.betas */
        fun betas(betas: List<AnthropicBeta>?) = apply { delegate.betas(betas) }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply { delegate.addBeta(beta) }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = apply { delegate.addBeta(value) }

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
        fun messages(messages: List<BetaMessageParam>) = apply { delegate.messages(messages) }

        /**
         * Sets [Builder.messages] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messages] with a well-typed `List<BetaMessageParam>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun messages(messages: JsonField<List<BetaMessageParam>>) = apply {
            delegate.messages(messages)
        }

        /**
         * Adds a single [BetaMessageParam] to [messages].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMessage(message: BetaMessageParam) = apply { delegate.addMessage(message) }

        /** Alias for calling [addMessage] with `message.toParam()`. */
        fun addMessage(message: BetaMessage) = apply { delegate.addMessage(message) }

        /**
         * Alias for calling [addMessage] with the following:
         * ```java
         * BetaMessageParam.builder()
         *     .role(BetaMessageParam.Role.USER)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addUserMessage(content: BetaMessageParam.Content) = apply {
            delegate.addUserMessage(content)
        }

        /** Alias for calling [addUserMessage] with `BetaMessageParam.Content.ofString(string)`. */
        fun addUserMessage(string: String) = apply { delegate.addUserMessage(string) }

        /**
         * Alias for calling [addUserMessage] with
         * `BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)`.
         */
        fun addUserMessageOfBetaContentBlockParams(
            betaContentBlockParams: List<BetaContentBlockParam>
        ) = apply { delegate.addUserMessageOfBetaContentBlockParams(betaContentBlockParams) }

        /**
         * Alias for calling [addMessage] with the following:
         * ```java
         * BetaMessageParam.builder()
         *     .role(BetaMessageParam.Role.ASSISTANT)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addAssistantMessage(content: BetaMessageParam.Content) = apply {
            delegate.addAssistantMessage(content)
        }

        /**
         * Alias for calling [addAssistantMessage] with `BetaMessageParam.Content.ofString(string)`.
         */
        fun addAssistantMessage(string: String) = apply { delegate.addAssistantMessage(string) }

        /**
         * Alias for calling [addAssistantMessage] with
         * `BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)`.
         */
        fun addAssistantMessageOfBetaContentBlockParams(
            betaContentBlockParams: List<BetaContentBlockParam>
        ) = apply { delegate.addAssistantMessageOfBetaContentBlockParams(betaContentBlockParams) }

        /**
         * Alias for calling [addMessage] with the following:
         * ```java
         * BetaMessageParam.builder()
         *     .role(BetaMessageParam.Role.SYSTEM)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addSystemMessage(content: BetaMessageParam.Content) = apply {
            delegate.addSystemMessage(content)
        }

        /**
         * Alias for calling [addSystemMessage] with `BetaMessageParam.Content.ofString(string)`.
         */
        fun addSystemMessage(string: String) = apply { delegate.addSystemMessage(string) }

        /**
         * Alias for calling [addSystemMessage] with
         * `BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)`.
         */
        fun addSystemMessageOfBetaContentBlockParams(
            betaContentBlockParams: List<BetaContentBlockParam>
        ) = apply { delegate.addSystemMessageOfBetaContentBlockParams(betaContentBlockParams) }

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
        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) = apply {
            delegate.cacheControl(cacheControl)
        }

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            delegate.cacheControl(cacheControl)
        }

        /** @see MessageCreateParams.Builder.compaction */
        fun compaction(compaction: BetaCompactionConfig?) = apply {
            delegate.compaction(compaction)
        }

        /** Alias for calling [Builder.compaction] with `compaction.orElse(null)`. */
        fun compaction(compaction: Optional<BetaCompactionConfig>) =
            compaction(compaction.getOrNull())

        /**
         * Sets [Builder.compaction] to an arbitrary JSON value.
         *
         * You should usually call [Builder.compaction] with a well-typed [BetaCompactionConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun compaction(compaction: JsonField<BetaCompactionConfig>) = apply {
            delegate.compaction(compaction)
        }

        /** @see MessageCreateParams.Builder.container */
        fun container(container: MessageCreateParams.Container?) = apply {
            delegate.container(container)
        }

        /** Alias for calling [Builder.container] with `container.orElse(null)`. */
        fun container(container: Optional<MessageCreateParams.Container>) =
            container(container.getOrNull())

        /**
         * Sets [Builder.container] to an arbitrary JSON value.
         *
         * You should usually call [Builder.container] with a well-typed
         * [MessageCreateParams.Container] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun container(container: JsonField<MessageCreateParams.Container>) = apply {
            delegate.container(container)
        }

        /**
         * Alias for calling [container] with
         * `MessageCreateParams.Container.ofBetaContainerParams(betaContainerParams)`.
         */
        fun container(betaContainerParams: BetaContainerParams) = apply {
            delegate.container(betaContainerParams)
        }

        /** Alias for calling [container] with `MessageCreateParams.Container.ofString(string)`. */
        fun container(string: String) = apply { delegate.container(string) }

        /** @see MessageCreateParams.Builder.contextManagement */
        fun contextManagement(contextManagement: BetaContextManagementConfig?) = apply {
            delegate.contextManagement(contextManagement)
        }

        /** Alias for calling [Builder.contextManagement] with `contextManagement.orElse(null)`. */
        fun contextManagement(contextManagement: Optional<BetaContextManagementConfig>) =
            contextManagement(contextManagement.getOrNull())

        /**
         * Sets [Builder.contextManagement] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contextManagement] with a well-typed
         * [BetaContextManagementConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun contextManagement(contextManagement: JsonField<BetaContextManagementConfig>) = apply {
            delegate.contextManagement(contextManagement)
        }

        /** @see MessageCreateParams.Builder.diagnostics */
        fun diagnostics(diagnostics: BetaDiagnosticsParam?) = apply {
            delegate.diagnostics(diagnostics)
        }

        /** Alias for calling [Builder.diagnostics] with `diagnostics.orElse(null)`. */
        fun diagnostics(diagnostics: Optional<BetaDiagnosticsParam>) =
            diagnostics(diagnostics.getOrNull())

        /**
         * Sets [Builder.diagnostics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.diagnostics] with a well-typed [BetaDiagnosticsParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun diagnostics(diagnostics: JsonField<BetaDiagnosticsParam>) = apply {
            delegate.diagnostics(diagnostics)
        }

        /** @see MessageCreateParams.Builder.fallbackCreditToken */
        fun fallbackCreditToken(fallbackCreditToken: MessageCreateParams.FallbackCreditToken?) =
            apply {
                delegate.fallbackCreditToken(fallbackCreditToken)
            }

        /**
         * Alias for calling [Builder.fallbackCreditToken] with `fallbackCreditToken.orElse(null)`.
         */
        fun fallbackCreditToken(
            fallbackCreditToken: Optional<MessageCreateParams.FallbackCreditToken>
        ) = fallbackCreditToken(fallbackCreditToken.getOrNull())

        /**
         * Sets [Builder.fallbackCreditToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fallbackCreditToken] with a well-typed
         * [MessageCreateParams.FallbackCreditToken] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun fallbackCreditToken(
            fallbackCreditToken: JsonField<MessageCreateParams.FallbackCreditToken>
        ) = apply { delegate.fallbackCreditToken(fallbackCreditToken) }

        /**
         * Alias for calling [fallbackCreditToken] with
         * `MessageCreateParams.FallbackCreditToken.ofString(string)`.
         */
        fun fallbackCreditToken(string: String) = apply { delegate.fallbackCreditToken(string) }

        /**
         * Alias for calling [fallbackCreditToken] with
         * `MessageCreateParams.FallbackCreditToken.ofBetaFallbackCreditTokenParam(betaFallbackCreditTokenParam)`.
         */
        fun fallbackCreditToken(betaFallbackCreditTokenParam: BetaFallbackCreditTokenParam) =
            apply {
                delegate.fallbackCreditToken(betaFallbackCreditTokenParam)
            }

        /** @see MessageCreateParams.Builder.fallbacks */
        fun fallbacks(fallbacks: BetaFallbacksParam?) = apply { delegate.fallbacks(fallbacks) }

        /** Alias for calling [Builder.fallbacks] with `fallbacks.orElse(null)`. */
        fun fallbacks(fallbacks: Optional<BetaFallbacksParam>) = fallbacks(fallbacks.getOrNull())

        /**
         * Sets [Builder.fallbacks] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fallbacks] with a well-typed [BetaFallbacksParam] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun fallbacks(fallbacks: JsonField<BetaFallbacksParam>) = apply {
            delegate.fallbacks(fallbacks)
        }

        /**
         * Alias for calling [fallbacks] with `BetaFallbacksParam.ofFallbackParams(fallbackParams)`.
         */
        fun fallbacksOfFallbackParams(fallbackParams: List<BetaFallbackParam>) = apply {
            delegate.fallbacksOfFallbackParams(fallbackParams)
        }

        /** Alias for calling [fallbacks] with `BetaFallbacksParam.ofDefault()`. */
        fun fallbacksDefault() = apply { delegate.fallbacksDefault() }

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

        /** @see MessageCreateParams.Builder.mcpServers */
        fun mcpServers(mcpServers: List<BetaRequestMcpServerUrlDefinition>) = apply {
            delegate.mcpServers(mcpServers)
        }

        /**
         * Sets [Builder.mcpServers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServers] with a well-typed
         * `List<BetaRequestMcpServerUrlDefinition>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun mcpServers(mcpServers: JsonField<List<BetaRequestMcpServerUrlDefinition>>) = apply {
            delegate.mcpServers(mcpServers)
        }

        /**
         * Adds a single [BetaRequestMcpServerUrlDefinition] to [mcpServers].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMcpServer(mcpServer: BetaRequestMcpServerUrlDefinition) = apply {
            delegate.addMcpServer(mcpServer)
        }

        /** @see MessageCreateParams.Builder.metadata */
        fun metadata(metadata: BetaMetadata) = apply { delegate.metadata(metadata) }

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [BetaMetadata] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun metadata(metadata: JsonField<BetaMetadata>) = apply { delegate.metadata(metadata) }

        /**
         * Sets the output configuration, carrying over the output format already set unless the
         * given output configuration sets its own, as the response will still be deserialized to an
         * instance of the output type.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        fun outputConfig(outputConfig: BetaOutputConfig) = apply {
            val currentFormat = delegate.currentOutputConfig()?._format()?.asKnown()?.getOrNull()
            delegate.outputConfig(
                if (currentFormat != null && outputConfig._format().isMissing())
                    outputConfig.toBuilder().format(currentFormat).build()
                else outputConfig
            )
        }

        /**
         * Sets the output configuration, carrying over the output format already set unless the
         * given output configuration is not a [BetaOutputConfig] value or sets its own.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        fun outputConfig(outputConfig: JsonField<BetaOutputConfig>) = apply {
            val knownOutputConfig = outputConfig.asKnown().getOrNull()
            if (knownOutputConfig != null) outputConfig(knownOutputConfig)
            else delegate.outputConfig(outputConfig)
        }

        /**
         * Sets the output format of the output configuration to a JSON schema derived from the
         * structure of the given class, preserving any other output configuration options already
         * set.
         *
         * **Deprecated:** Use [outputConfig] instead. This method will be removed in a future
         * release.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        @JvmOverloads
        @Deprecated(
            message =
                "output_format is deprecated. Use outputConfig instead which sets output_config.format.",
            replaceWith = ReplaceWith("outputConfig(outputType, localValidation)"),
        )
        fun outputFormat(
            outputType: Class<T>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = outputConfig(outputType, localValidation)

        /**
         * Sets the output configuration, including a JSON schema format derived from the structure
         * of the output type of the given structured output configuration. Use this instead of the
         * output type alone to set other output configuration options alongside the output type.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        fun outputConfig(outputConfig: StructuredOutputConfig<T>) = apply {
            outputType = outputConfig.outputType
            delegate.outputConfig(outputConfig.rawOutputConfig)
            // Auto-inject beta header
            delegate.addBeta(AnthropicBeta.of("structured-outputs-2025-12-15"))
        }

        /**
         * Sets the output format of the output configuration to a JSON schema derived from the
         * structure of the given class, preserving any other output configuration options already
         * set. This is the recommended way to specify structured outputs.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        @JvmOverloads
        fun outputConfig(
            outputType: Class<T>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = mergeOutputFormat(outputType, localValidation, effort = null)

        /**
         * Sets the output format of the output configuration to a JSON schema derived from the
         * structure of the given class and, if given, the effort level, preserving any other output
         * configuration options already set.
         *
         * **Deprecated:** Use [outputConfig] with a [StructuredOutputConfig] instead, which can set
         * the effort level (and other output configuration options) alongside the output type. This
         * method will be removed in a future release.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        @JvmOverloads
        @Deprecated(
            message =
                "Passing the effort positionally is deprecated. Use outputConfig(StructuredOutputConfig) instead and set the effort via StructuredOutputConfig.builder().",
            replaceWith =
                ReplaceWith(
                    "outputConfig(StructuredOutputConfig.builder<T>().format(outputType, localValidation).effort(effort).build())",
                    "com.anthropic.models.beta.messages.StructuredOutputConfig",
                ),
        )
        fun outputConfig(
            outputType: Class<T>,
            effort: BetaOutputConfig.Effort?,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = mergeOutputFormat(outputType, localValidation, effort)

        private fun mergeOutputFormat(
            outputType: Class<T>,
            localValidation: JsonSchemaLocalValidation,
            effort: BetaOutputConfig.Effort?,
        ) = apply {
            this.outputType = outputType
            val builder = delegate.currentOutputConfig()?.toBuilder() ?: BetaOutputConfig.builder()
            builder.format(betaOutputFormatFromClass(outputType, localValidation))
            // A `null` effort must leave the current effort untouched, not send an explicit `null`.
            effort?.let { builder.effort(it) }
            delegate.outputConfig(builder.build())
            // Auto-inject beta header
            delegate.addBeta(AnthropicBeta.of("structured-outputs-2025-12-15"))
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

        /** @see MessageCreateParams.Builder.speed */
        fun speed(speed: MessageCreateParams.Speed?) = apply { delegate.speed(speed) }

        /** Alias for calling [Builder.speed] with `speed.orElse(null)`. */
        fun speed(speed: Optional<MessageCreateParams.Speed>) = speed(speed.getOrNull())

        /**
         * Sets [Builder.speed] to an arbitrary JSON value.
         *
         * You should usually call [Builder.speed] with a well-typed [MessageCreateParams.Speed]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun speed(speed: JsonField<MessageCreateParams.Speed>) = apply { delegate.speed(speed) }

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
         * `MessageCreateParams.System.ofBetaTextBlockParams(betaTextBlockParams)`.
         */
        fun systemOfBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>) = apply {
            delegate.systemOfBetaTextBlockParams(betaTextBlockParams)
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
        fun thinking(thinking: BetaThinkingConfigParam) = apply { delegate.thinking(thinking) }

        /**
         * Sets [Builder.thinking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinking] with a well-typed [BetaThinkingConfigParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun thinking(thinking: JsonField<BetaThinkingConfigParam>) = apply {
            delegate.thinking(thinking)
        }

        /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofEnabled(enabled)`. */
        fun thinking(enabled: BetaThinkingConfigEnabled) = apply { delegate.thinking(enabled) }

        /**
         * Alias for calling [thinking] with the following:
         * ```java
         * BetaThinkingConfigEnabled.builder()
         *     .budgetTokens(budgetTokens)
         *     .build()
         * ```
         */
        fun enabledThinking(budgetTokens: Long) = apply { delegate.enabledThinking(budgetTokens) }

        /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofDisabled(disabled)`. */
        fun thinking(disabled: BetaThinkingConfigDisabled) = apply { delegate.thinking(disabled) }

        /**
         * Alias for calling [thinking] with `BetaThinkingConfigParam.ofBetweenTools(betweenTools)`.
         */
        fun thinking(betweenTools: BetaThinkingConfigBetweenTools) = apply {
            delegate.thinking(betweenTools)
        }

        /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofAdaptive(adaptive)`. */
        fun thinking(adaptive: BetaThinkingConfigAdaptive) = apply { delegate.thinking(adaptive) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(toolChoice: BetaToolChoice) = apply { delegate.toolChoice(toolChoice) }

        /**
         * Sets [Builder.toolChoice] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolChoice] with a well-typed [BetaToolChoice] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun toolChoice(toolChoice: JsonField<BetaToolChoice>) = apply {
            delegate.toolChoice(toolChoice)
        }

        /** Alias for calling [toolChoice] with `BetaToolChoice.ofAuto(auto)`. */
        fun toolChoice(auto: BetaToolChoiceAuto) = apply { delegate.toolChoice(auto) }

        /** Alias for calling [toolChoice] with `BetaToolChoice.ofAny(any)`. */
        fun toolChoice(any: BetaToolChoiceAny) = apply { delegate.toolChoice(any) }

        /** Alias for calling [toolChoice] with `BetaToolChoice.ofTool(tool)`. */
        fun toolChoice(tool: BetaToolChoiceTool) = apply { delegate.toolChoice(tool) }

        /**
         * Alias for calling [toolChoice] with the following:
         * ```java
         * BetaToolChoiceTool.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun toolToolChoice(name: String) = apply { delegate.toolToolChoice(name) }

        /** Alias for calling [toolChoice] with `BetaToolChoice.ofNone(none)`. */
        fun toolChoice(none: BetaToolChoiceNone) = apply { delegate.toolChoice(none) }

        /** @see MessageCreateParams.Builder.tools */
        fun tools(tools: List<BetaToolUnion>) = apply { delegate.tools(tools) }

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed `List<BetaToolUnion>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun tools(tools: JsonField<List<BetaToolUnion>>) = apply { delegate.tools(tools) }

        /**
         * Adds a single [BetaToolUnion] to [tools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTool(tool: BetaToolUnion) = apply { delegate.addTool(tool) }

        /** Alias for calling [addTool] with `tool.toParam()`. */
        fun addTool(tool: BetaResponseToolUnion) = apply { delegate.addTool(tool) }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofBetaResponseTool(betaResponseTool)`.
         */
        fun addTool(betaResponseTool: BetaResponseTool) = apply {
            delegate.addTool(betaResponseTool)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolBash20241022(toolBash20241022)`.
         */
        fun addTool(toolBash20241022: BetaToolBash20241022) = apply {
            delegate.addTool(toolBash20241022)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolBash20250124(toolBash20250124)`.
         */
        fun addTool(toolBash20250124: BetaToolBash20250124) = apply {
            delegate.addTool(toolBash20250124)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)`.
         */
        fun addTool(codeExecutionTool20250522: BetaCodeExecutionTool20250522) = apply {
            delegate.addTool(codeExecutionTool20250522)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)`.
         */
        fun addTool(codeExecutionTool20250825: BetaCodeExecutionTool20250825) = apply {
            delegate.addTool(codeExecutionTool20250825)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)`.
         */
        fun addTool(codeExecutionTool20260120: BetaCodeExecutionTool20260120) = apply {
            delegate.addTool(codeExecutionTool20260120)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)`.
         */
        fun addTool(codeExecutionTool20260521: BetaCodeExecutionTool20260521) = apply {
            delegate.addTool(codeExecutionTool20260521)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofBrowserToolset20260801(browserToolset20260801)`.
         */
        fun addTool(browserToolset20260801: BetaBrowserToolset20260801) = apply {
            delegate.addTool(browserToolset20260801)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolComputerUse20241022(toolComputerUse20241022)`.
         */
        fun addTool(toolComputerUse20241022: BetaToolComputerUse20241022) = apply {
            delegate.addTool(toolComputerUse20241022)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofMemoryTool20250818(memoryTool20250818)`.
         */
        fun addTool(memoryTool20250818: BetaMemoryTool20250818) = apply {
            delegate.addTool(memoryTool20250818)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolComputerUse20250124(toolComputerUse20250124)`.
         */
        fun addTool(toolComputerUse20250124: BetaToolComputerUse20250124) = apply {
            delegate.addTool(toolComputerUse20250124)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolTextEditor20241022(toolTextEditor20241022)`.
         */
        fun addTool(toolTextEditor20241022: BetaToolTextEditor20241022) = apply {
            delegate.addTool(toolTextEditor20241022)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolComputerUse20251124(toolComputerUse20251124)`.
         */
        fun addTool(toolComputerUse20251124: BetaToolComputerUse20251124) = apply {
            delegate.addTool(toolComputerUse20251124)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofComputerToolset20260801(computerToolset20260801)`.
         */
        fun addTool(computerToolset20260801: BetaComputerToolset20260801) = apply {
            delegate.addTool(computerToolset20260801)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolTextEditor20250124(toolTextEditor20250124)`.
         */
        fun addTool(toolTextEditor20250124: BetaToolTextEditor20250124) = apply {
            delegate.addTool(toolTextEditor20250124)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolTextEditor20250429(toolTextEditor20250429)`.
         */
        fun addTool(toolTextEditor20250429: BetaToolTextEditor20250429) = apply {
            delegate.addTool(toolTextEditor20250429)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolTextEditor20250728(toolTextEditor20250728)`.
         */
        fun addTool(toolTextEditor20250728: BetaToolTextEditor20250728) = apply {
            delegate.addTool(toolTextEditor20250728)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofWebSearchTool20250305(webSearchTool20250305)`.
         */
        fun addTool(webSearchTool20250305: BetaWebSearchTool20250305) = apply {
            delegate.addTool(webSearchTool20250305)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofWebFetchTool20250910(webFetchTool20250910)`.
         */
        fun addTool(webFetchTool20250910: BetaWebFetchTool20250910) = apply {
            delegate.addTool(webFetchTool20250910)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofWebSearchTool20260209(webSearchTool20260209)`.
         */
        fun addTool(webSearchTool20260209: BetaWebSearchTool20260209) = apply {
            delegate.addTool(webSearchTool20260209)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofWebFetchTool20260209(webFetchTool20260209)`.
         */
        fun addTool(webFetchTool20260209: BetaWebFetchTool20260209) = apply {
            delegate.addTool(webFetchTool20260209)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofWebFetchTool20260309(webFetchTool20260309)`.
         */
        fun addTool(webFetchTool20260309: BetaWebFetchTool20260309) = apply {
            delegate.addTool(webFetchTool20260309)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofWebSearchTool20260318(webSearchTool20260318)`.
         */
        fun addTool(webSearchTool20260318: BetaWebSearchTool20260318) = apply {
            delegate.addTool(webSearchTool20260318)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofWebFetchTool20260318(webFetchTool20260318)`.
         */
        fun addTool(webFetchTool20260318: BetaWebFetchTool20260318) = apply {
            delegate.addTool(webFetchTool20260318)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofAdvisorTool20260301(advisorTool20260301)`.
         */
        fun addTool(advisorTool20260301: BetaAdvisorTool20260301) = apply {
            delegate.addTool(advisorTool20260301)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)`.
         */
        fun addTool(toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119) = apply {
            delegate.addTool(toolSearchToolBm25_20251119)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaResponseToolUnion.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)`.
         */
        fun addTool(toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119) = apply {
            delegate.addTool(toolSearchToolRegex20251119)
        }

        /** Alias for calling [addTool] with `BetaResponseToolUnion.ofMcpToolset(mcpToolset)`. */
        fun addTool(mcpToolset: BetaMcpToolset) = apply { delegate.addTool(mcpToolset) }

        /** Alias for calling [addTool] with `BetaToolUnion.ofBetaTool(betaTool)`. */
        fun addTool(betaTool: BetaTool) = apply { delegate.addTool(betaTool) }

        /** @see MessageCreateParams.Builder.addTool */
        @JvmOverloads
        fun addTool(
            toolParametersType: Class<*>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = apply { delegate.addTool(toolParametersType, localValidation) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(tool: BetaRunnableTool) = apply { delegate.addTool(tool) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(tool: com.anthropic.helpers.McpBetaTool) = apply { delegate.addTool(tool) }

        /** @see MessageCreateParams.Builder.addTools */
        fun addTools(tools: List<com.anthropic.helpers.McpBetaTool>) = apply {
            delegate.addTools(tools)
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
