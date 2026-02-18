package com.anthropic.models.beta.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.betaOutputFormatFromClass
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.messages.Model
import java.util.Objects
import java.util.Optional

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
    /**
     * The raw, underlying message create parameters wrapped by this structured instance of the
     * parameters.
     */
    @get:JvmName("rawParams") val rawParams: MessageCreateParams,
) {
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
        private var paramsBuilder = MessageCreateParams.Companion.builder()
        private var outputConfigSet: Boolean = false

        @JvmSynthetic
        @Deprecated("Use the wrap overload that accepts effort parameter")
        internal fun wrap(
            outputType: Class<T>,
            paramsBuilder: MessageCreateParams.Builder,
            localValidation: JsonSchemaLocalValidation,
        ) = apply {
            this.outputType = outputType
            this.paramsBuilder = paramsBuilder
            // Convert the class to a JSON schema and apply it to the delegate `Builder`.
            @Suppress("DEPRECATION") outputFormat(outputType, localValidation)
        }

        @JvmSynthetic
        internal fun wrap(
            outputType: Class<T>,
            paramsBuilder: MessageCreateParams.Builder,
            effort: BetaOutputConfig.Effort?,
            localValidation: JsonSchemaLocalValidation,
        ) = apply {
            this.outputType = outputType
            this.paramsBuilder = paramsBuilder
            // Convert the class to a JSON schema and apply it to the delegate `Builder`.
            outputConfig(outputType, effort, localValidation)
        }

        /** Injects a given `MessageCreateParams.Builder`. For use only when testing. */
        @JvmSynthetic
        internal fun inject(paramsBuilder: MessageCreateParams.Builder) = apply {
            this.paramsBuilder = paramsBuilder
        }

        /** @see MessageCreateParams.Builder.betas */
        fun betas(betas: List<AnthropicBeta>?) = apply { paramsBuilder.betas(betas) }

        /** @see MessageCreateParams.Builder.betas */
        fun betas(betas: Optional<List<AnthropicBeta>>) = apply { paramsBuilder.betas(betas) }

        /** @see MessageCreateParams.Builder.addBeta */
        fun addBeta(beta: AnthropicBeta) = apply { paramsBuilder.addBeta(beta) }

        /** @see MessageCreateParams.Builder.addBeta */
        fun addBeta(value: String) = apply { paramsBuilder.addBeta(value) }

        /** @see MessageCreateParams.Builder.body */
        fun body(body: MessageCreateParams.Body) = apply { paramsBuilder.body(body) }

        /** @see MessageCreateParams.Builder.maxTokens */
        fun maxTokens(maxTokens: Long) = apply { paramsBuilder.maxTokens(maxTokens) }

        /** @see MessageCreateParams.Builder.maxTokens */
        fun maxTokens(maxTokens: JsonField<Long>) = apply { paramsBuilder.maxTokens(maxTokens) }

        /** @see MessageCreateParams.Builder.messages */
        fun messages(messages: List<BetaMessageParam>) = apply { paramsBuilder.messages(messages) }

        /** @see MessageCreateParams.Builder.messages */
        fun messages(messages: JsonField<List<BetaMessageParam>>) = apply {
            paramsBuilder.messages(messages)
        }

        /** @see MessageCreateParams.Builder.addMessage */
        fun addMessage(message: BetaMessageParam) = apply { paramsBuilder.addMessage(message) }

        /** @see MessageCreateParams.Builder.addMessage */
        fun addMessage(message: BetaMessage) = apply { paramsBuilder.addMessage(message) }

        /** @see MessageCreateParams.Builder.addUserMessage */
        fun addUserMessage(content: BetaMessageParam.Content) = apply {
            paramsBuilder.addUserMessage(content)
        }

        /** @see MessageCreateParams.Builder.addUserMessage */
        fun addUserMessage(string: String) = apply { paramsBuilder.addUserMessage(string) }

        /** @see MessageCreateParams.Builder.addUserMessageOfBetaContentBlockParams */
        fun addUserMessageOfBetaContentBlockParams(
            betaContentBlockParams: List<BetaContentBlockParam>
        ) = apply { paramsBuilder.addUserMessageOfBetaContentBlockParams(betaContentBlockParams) }

        /** @see MessageCreateParams.Builder.addAssistantMessage */
        fun addAssistantMessage(content: BetaMessageParam.Content) = apply {
            paramsBuilder.addAssistantMessage(content)
        }

        /** @see MessageCreateParams.Builder.addAssistantMessage */
        fun addAssistantMessage(string: String) = apply {
            paramsBuilder.addAssistantMessage(string)
        }

        /** @see MessageCreateParams.Builder.addAssistantMessageOfBetaContentBlockParams */
        fun addAssistantMessageOfBetaContentBlockParams(
            betaContentBlockParams: List<BetaContentBlockParam>
        ) = apply {
            paramsBuilder.addAssistantMessageOfBetaContentBlockParams(betaContentBlockParams)
        }

        /** @see MessageCreateParams.Builder.model */
        fun model(model: Model) = apply { paramsBuilder.model(model) }

        /** @see MessageCreateParams.Builder.model */
        fun model(model: JsonField<Model>) = apply { paramsBuilder.model(model) }

        /** @see MessageCreateParams.Builder.model */
        fun model(value: String) = apply { paramsBuilder.model(value) }

        /** @see MessageCreateParams.Builder.container */
        fun container(container: MessageCreateParams.Container?) = apply {
            paramsBuilder.container(container)
        }

        /** @see MessageCreateParams.Builder.container */
        fun container(container: Optional<MessageCreateParams.Container>) = apply {
            paramsBuilder.container(container)
        }

        /** @see MessageCreateParams.Builder.container */
        fun container(container: JsonField<MessageCreateParams.Container>) = apply {
            paramsBuilder.container(container)
        }

        /** @see MessageCreateParams.Builder.container */
        fun container(betaContainerParams: BetaContainerParams) = apply {
            paramsBuilder.container(betaContainerParams)
        }

        /** @see MessageCreateParams.Builder.container */
        fun container(string: String) = apply { paramsBuilder.container(string) }

        /** @see MessageCreateParams.Builder.contextManagement */
        fun contextManagement(contextManagement: BetaContextManagementConfig?) = apply {
            paramsBuilder.contextManagement(contextManagement)
        }

        /** @see MessageCreateParams.Builder.contextManagement */
        fun contextManagement(contextManagement: Optional<BetaContextManagementConfig>) = apply {
            paramsBuilder.contextManagement(contextManagement)
        }

        /** @see MessageCreateParams.Builder.contextManagement */
        fun contextManagement(contextManagement: JsonField<BetaContextManagementConfig>) = apply {
            paramsBuilder.contextManagement(contextManagement)
        }

        /** @see MessageCreateParams.Builder.mcpServers */
        fun mcpServers(mcpServers: List<BetaRequestMcpServerUrlDefinition>) = apply {
            paramsBuilder.mcpServers(mcpServers)
        }

        /** @see MessageCreateParams.Builder.mcpServers */
        fun mcpServers(mcpServers: JsonField<List<BetaRequestMcpServerUrlDefinition>>) = apply {
            paramsBuilder.mcpServers(mcpServers)
        }

        /** @see MessageCreateParams.Builder.addMcpServer */
        fun addMcpServer(mcpServer: BetaRequestMcpServerUrlDefinition) = apply {
            paramsBuilder.addMcpServer(mcpServer)
        }

        /** @see MessageCreateParams.Builder.inferenceGeo */
        fun inferenceGeo(inferenceGeo: String?) = apply { paramsBuilder.inferenceGeo(inferenceGeo) }

        /** @see MessageCreateParams.Builder.inferenceGeo */
        fun inferenceGeo(inferenceGeo: Optional<String>) = apply {
            paramsBuilder.inferenceGeo(inferenceGeo)
        }

        /** @see MessageCreateParams.Builder.inferenceGeo */
        fun inferenceGeo(inferenceGeo: JsonField<String>) = apply {
            paramsBuilder.inferenceGeo(inferenceGeo)
        }

        /** @see MessageCreateParams.Builder.metadata */
        fun metadata(metadata: BetaMetadata) = apply { paramsBuilder.metadata(metadata) }

        /** @see MessageCreateParams.Builder.metadata */
        fun metadata(metadata: JsonField<BetaMetadata>) = apply { paramsBuilder.metadata(metadata) }

        /** @see MessageCreateParams.Builder.outputConfig */
        fun outputConfig(outputConfig: BetaOutputConfig) = apply {
            paramsBuilder.outputConfig(outputConfig)
        }

        /** @see MessageCreateParams.Builder.outputConfig */
        fun outputConfig(outputConfig: JsonField<BetaOutputConfig>) = apply {
            paramsBuilder.outputConfig(outputConfig)
        }

        /**
         * Sets the output format to a JSON schema derived from the structure of the given class.
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
        ) = apply {
            if (outputConfigSet) {
                throw IllegalArgumentException(
                    "Both outputFormat and outputConfig were called. " +
                        "Please use only outputConfig (outputFormat is deprecated)."
                )
            }
            this.outputType = outputType
            val format = betaOutputFormatFromClass(outputType, localValidation)
            // Use output_config.format instead of deprecated output_format
            paramsBuilder.outputConfig(BetaOutputConfig.builder().format(format).build())
            // Auto-inject beta header
            paramsBuilder.addBeta(AnthropicBeta.of("structured-outputs-2025-12-15"))
            outputConfigSet = true
        }

        /**
         * Sets the output configuration with a JSON schema format derived from the structure of the
         * given class. This is the recommended way to specify structured outputs.
         *
         * @see MessageCreateParams.Builder.outputConfig
         */
        @JvmOverloads
        fun outputConfig(
            outputType: Class<T>,
            effort: BetaOutputConfig.Effort? = null,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = apply {
            if (outputConfigSet) {
                throw IllegalArgumentException(
                    "Both outputFormat and outputConfig were called. " +
                        "Please use only outputConfig (outputFormat is deprecated)."
                )
            }
            this.outputType = outputType
            val format = betaOutputFormatFromClass(outputType, localValidation)
            val builder = BetaOutputConfig.builder().format(format)
            effort?.let { builder.effort(it) }
            paramsBuilder.outputConfig(builder.build())
            // Auto-inject beta header
            paramsBuilder.addBeta(AnthropicBeta.of("structured-outputs-2025-12-15"))
            outputConfigSet = true
        }

        /** @see MessageCreateParams.Builder.serviceTier */
        fun serviceTier(serviceTier: MessageCreateParams.ServiceTier) = apply {
            paramsBuilder.serviceTier(serviceTier)
        }

        /** @see MessageCreateParams.Builder.serviceTier */
        fun serviceTier(serviceTier: JsonField<MessageCreateParams.ServiceTier>) = apply {
            paramsBuilder.serviceTier(serviceTier)
        }

        /** @see MessageCreateParams.Builder.speed */
        fun speed(speed: MessageCreateParams.Speed?) = apply { paramsBuilder.speed(speed) }

        /** @see MessageCreateParams.Builder.speed */
        fun speed(speed: Optional<MessageCreateParams.Speed>) = apply { paramsBuilder.speed(speed) }

        /** @see MessageCreateParams.Builder.speed */
        fun speed(speed: JsonField<MessageCreateParams.Speed>) = apply {
            paramsBuilder.speed(speed)
        }

        /** @see MessageCreateParams.Builder.stopSequences */
        fun stopSequences(stopSequences: List<String>) = apply {
            paramsBuilder.stopSequences(stopSequences)
        }

        /** @see MessageCreateParams.Builder.stopSequences */
        fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
            paramsBuilder.stopSequences(stopSequences)
        }

        /** @see MessageCreateParams.Builder.addStopSequence */
        fun addStopSequence(stopSequence: String) = apply {
            paramsBuilder.addStopSequence(stopSequence)
        }

        /** @see MessageCreateParams.Builder.system */
        fun system(system: MessageCreateParams.System) = apply { paramsBuilder.system(system) }

        /** @see MessageCreateParams.Builder.system */
        fun system(system: JsonField<MessageCreateParams.System>) = apply {
            paramsBuilder.system(system)
        }

        /** @see MessageCreateParams.Builder.system */
        fun system(string: String) = apply { paramsBuilder.system(string) }

        /** @see MessageCreateParams.Builder.systemOfBetaTextBlockParams */
        fun systemOfBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>) = apply {
            paramsBuilder.systemOfBetaTextBlockParams(betaTextBlockParams)
        }

        /** @see MessageCreateParams.Builder.temperature */
        fun temperature(temperature: Double) = apply { paramsBuilder.temperature(temperature) }

        /** @see MessageCreateParams.Builder.temperature */
        fun temperature(temperature: JsonField<Double>) = apply {
            paramsBuilder.temperature(temperature)
        }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(thinking: BetaThinkingConfigParam) = apply { paramsBuilder.thinking(thinking) }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(thinking: JsonField<BetaThinkingConfigParam>) = apply {
            paramsBuilder.thinking(thinking)
        }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(enabled: BetaThinkingConfigEnabled) = apply { paramsBuilder.thinking(enabled) }

        /** @see MessageCreateParams.Builder.enabledThinking */
        fun enabledThinking(budgetTokens: Long) = apply {
            paramsBuilder.enabledThinking(budgetTokens)
        }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(disabled: BetaThinkingConfigDisabled) = apply {
            paramsBuilder.thinking(disabled)
        }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(adaptive: BetaThinkingConfigAdaptive) = apply {
            paramsBuilder.thinking(adaptive)
        }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(toolChoice: BetaToolChoice) = apply { paramsBuilder.toolChoice(toolChoice) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(toolChoice: JsonField<BetaToolChoice>) = apply {
            paramsBuilder.toolChoice(toolChoice)
        }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(auto: BetaToolChoiceAuto) = apply { paramsBuilder.toolChoice(auto) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(any: BetaToolChoiceAny) = apply { paramsBuilder.toolChoice(any) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(tool: BetaToolChoiceTool) = apply { paramsBuilder.toolChoice(tool) }

        /** @see MessageCreateParams.Builder.toolToolChoice */
        fun toolToolChoice(name: String) = apply { paramsBuilder.toolToolChoice(name) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(none: BetaToolChoiceNone) = apply { paramsBuilder.toolChoice(none) }

        /** @see MessageCreateParams.Builder.tools */
        fun tools(tools: List<BetaToolUnion>) = apply { paramsBuilder.tools(tools) }

        /** @see MessageCreateParams.Builder.tools */
        fun tools(tools: JsonField<List<BetaToolUnion>>) = apply { paramsBuilder.tools(tools) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(tool: BetaToolUnion) = apply { paramsBuilder.addTool(tool) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(betaTool: BetaTool) = apply { paramsBuilder.addTool(betaTool) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(bash20241022: BetaToolBash20241022) = apply {
            paramsBuilder.addTool(bash20241022)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(bash20250124: BetaToolBash20250124) = apply {
            paramsBuilder.addTool(bash20250124)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(codeExecutionTool20250522: BetaCodeExecutionTool20250522) = apply {
            paramsBuilder.addTool(codeExecutionTool20250522)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(codeExecutionTool20250825: BetaCodeExecutionTool20250825) = apply {
            paramsBuilder.addTool(codeExecutionTool20250825)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(computerUse20241022: BetaToolComputerUse20241022) = apply {
            paramsBuilder.addTool(computerUse20241022)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(memoryTool20250818: BetaMemoryTool20250818) = apply {
            paramsBuilder.addTool(memoryTool20250818)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(computerUse20250124: BetaToolComputerUse20250124) = apply {
            paramsBuilder.addTool(computerUse20250124)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(computerUse20251124: BetaToolComputerUse20251124) = apply {
            paramsBuilder.addTool(computerUse20251124)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(textEditor20241022: BetaToolTextEditor20241022) = apply {
            paramsBuilder.addTool(textEditor20241022)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(textEditor20250124: BetaToolTextEditor20250124) = apply {
            paramsBuilder.addTool(textEditor20250124)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(textEditor20250429: BetaToolTextEditor20250429) = apply {
            paramsBuilder.addTool(textEditor20250429)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(textEditor20250728: BetaToolTextEditor20250728) = apply {
            paramsBuilder.addTool(textEditor20250728)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(webSearchTool20250305: BetaWebSearchTool20250305) = apply {
            paramsBuilder.addTool(webSearchTool20250305)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(webFetchTool20250910: BetaWebFetchTool20250910) = apply {
            paramsBuilder.addTool(webFetchTool20250910)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(codeExecutionTool20260120: BetaCodeExecutionTool20260120) = apply {
            paramsBuilder.addTool(codeExecutionTool20260120)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(webSearchTool20260209: BetaWebSearchTool20260209) = apply {
            paramsBuilder.addTool(webSearchTool20260209)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(webFetchTool20260209: BetaWebFetchTool20260209) = apply {
            paramsBuilder.addTool(webFetchTool20260209)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(searchToolBm25_20251119: BetaToolSearchToolBm25_20251119) = apply {
            paramsBuilder.addTool(searchToolBm25_20251119)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(searchToolRegex20251119: BetaToolSearchToolRegex20251119) = apply {
            paramsBuilder.addTool(searchToolRegex20251119)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(mcpToolset: BetaMcpToolset) = apply { paramsBuilder.addTool(mcpToolset) }

        /** @see MessageCreateParams.Builder.addTool */
        @JvmOverloads
        fun addTool(
            toolParametersType: Class<*>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = apply { paramsBuilder.addTool(toolParametersType, localValidation) }

        /** @see MessageCreateParams.Builder.topK */
        fun topK(topK: Long) = apply { paramsBuilder.topK(topK) }

        /** @see MessageCreateParams.Builder.topK */
        fun topK(topK: JsonField<Long>) = apply { paramsBuilder.topK(topK) }

        /** @see MessageCreateParams.Builder.topP */
        fun topP(topP: Double) = apply { paramsBuilder.topP(topP) }

        /** @see MessageCreateParams.Builder.topP */
        fun topP(topP: JsonField<Double>) = apply { paramsBuilder.topP(topP) }

        /** @see MessageCreateParams.Builder.additionalBodyProperties */
        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            paramsBuilder.additionalBodyProperties(additionalBodyProperties)
        }

        /** @see MessageCreateParams.Builder.putAdditionalBodyProperty */
        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            paramsBuilder.putAdditionalBodyProperty(key, value)
        }

        /** @see MessageCreateParams.Builder.putAllAdditionalBodyProperties */
        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                paramsBuilder.putAllAdditionalBodyProperties(additionalBodyProperties)
            }

        /** @see MessageCreateParams.Builder.removeAdditionalBodyProperty */
        fun removeAdditionalBodyProperty(key: String) = apply {
            paramsBuilder.removeAdditionalBodyProperty(key)
        }

        /** @see MessageCreateParams.Builder.removeAllAdditionalBodyProperties */
        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            paramsBuilder.removeAllAdditionalBodyProperties(keys)
        }

        /** @see MessageCreateParams.Builder.additionalHeaders */
        fun additionalHeaders(additionalHeaders: Headers) = apply {
            paramsBuilder.additionalHeaders(additionalHeaders)
        }

        /** @see MessageCreateParams.Builder.additionalHeaders */
        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            paramsBuilder.additionalHeaders(additionalHeaders)
        }

        /** @see MessageCreateParams.Builder.putAdditionalHeader */
        fun putAdditionalHeader(name: String, value: String) = apply {
            paramsBuilder.putAdditionalHeader(name, value)
        }

        /** @see MessageCreateParams.Builder.putAdditionalHeaders */
        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            paramsBuilder.putAdditionalHeaders(name, values)
        }

        /** @see MessageCreateParams.Builder.putAllAdditionalHeaders */
        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            paramsBuilder.putAllAdditionalHeaders(additionalHeaders)
        }

        /** @see MessageCreateParams.Builder.putAllAdditionalHeaders */
        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            paramsBuilder.putAllAdditionalHeaders(additionalHeaders)
        }

        /** @see MessageCreateParams.Builder.replaceAdditionalHeaders */
        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            paramsBuilder.replaceAdditionalHeaders(name, value)
        }

        /** @see MessageCreateParams.Builder.replaceAdditionalHeaders */
        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            paramsBuilder.replaceAdditionalHeaders(name, values)
        }

        /** @see MessageCreateParams.Builder.replaceAllAdditionalHeaders */
        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            paramsBuilder.replaceAllAdditionalHeaders(additionalHeaders)
        }

        /** @see MessageCreateParams.Builder.replaceAllAdditionalHeaders */
        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            paramsBuilder.replaceAllAdditionalHeaders(additionalHeaders)
        }

        /** @see MessageCreateParams.Builder.removeAdditionalHeaders */
        fun removeAdditionalHeaders(name: String) = apply {
            paramsBuilder.removeAdditionalHeaders(name)
        }

        /** @see MessageCreateParams.Builder.removeAllAdditionalHeaders */
        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            paramsBuilder.removeAllAdditionalHeaders(names)
        }

        /** @see MessageCreateParams.Builder.additionalQueryParams */
        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            paramsBuilder.additionalQueryParams(additionalQueryParams)
        }

        /** @see MessageCreateParams.Builder.additionalQueryParams */
        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            paramsBuilder.additionalQueryParams(additionalQueryParams)
        }

        /** @see MessageCreateParams.Builder.putAdditionalQueryParam */
        fun putAdditionalQueryParam(key: String, value: String) = apply {
            paramsBuilder.putAdditionalQueryParam(key, value)
        }

        /** @see MessageCreateParams.Builder.putAdditionalQueryParams */
        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            paramsBuilder.putAdditionalQueryParams(key, values)
        }

        /** @see MessageCreateParams.Builder.putAllAdditionalQueryParams */
        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            paramsBuilder.putAllAdditionalQueryParams(additionalQueryParams)
        }

        /** @see MessageCreateParams.Builder.putAllAdditionalQueryParams */
        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                paramsBuilder.putAllAdditionalQueryParams(additionalQueryParams)
            }

        /** @see MessageCreateParams.Builder.replaceAdditionalQueryParams */
        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            paramsBuilder.replaceAdditionalQueryParams(key, value)
        }

        /** @see MessageCreateParams.Builder.replaceAdditionalQueryParams */
        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            paramsBuilder.replaceAdditionalQueryParams(key, values)
        }

        /** @see MessageCreateParams.Builder.replaceAllAdditionalQueryParams */
        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            paramsBuilder.replaceAllAdditionalQueryParams(additionalQueryParams)
        }

        /** @see MessageCreateParams.Builder.replaceAllAdditionalQueryParams */
        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                paramsBuilder.replaceAllAdditionalQueryParams(additionalQueryParams)
            }

        /** @see MessageCreateParams.Builder.removeAdditionalQueryParams */
        fun removeAdditionalQueryParams(key: String) = apply {
            paramsBuilder.removeAdditionalQueryParams(key)
        }

        /** @see MessageCreateParams.Builder.removeAllAdditionalQueryParams */
        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            paramsBuilder.removeAllAdditionalQueryParams(keys)
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
            StructuredMessageCreateParams(
                checkRequired("outputType", outputType),
                paramsBuilder.build(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StructuredMessageCreateParams<*> &&
            outputType == other.outputType &&
            rawParams == other.rawParams
    }

    override fun hashCode(): Int = Objects.hash(outputType, rawParams)

    override fun toString() =
        "${javaClass.simpleName}{outputType=$outputType, rawParams=$rawParams}"
}
