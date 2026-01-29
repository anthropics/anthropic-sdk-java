package com.anthropic.models.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.outputFormatFromClass
import java.util.Objects

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
        private var paramsBuilder = MessageCreateParams.builder()
        private var outputConfigSet: Boolean = false

        @JvmSynthetic
        internal fun wrap(
            outputType: Class<T>,
            paramsBuilder: MessageCreateParams.Builder,
            localValidation: JsonSchemaLocalValidation,
        ) = apply {
            this.outputType = outputType
            this.paramsBuilder = paramsBuilder
            // Convert the class to a JSON schema and apply it to the delegate `Builder`.
            outputConfig(outputType, localValidation)
        }

        /** Injects a given `MessageCreateParams.Builder`. For use only when testing. */
        @JvmSynthetic
        internal fun inject(paramsBuilder: MessageCreateParams.Builder) = apply {
            this.paramsBuilder = paramsBuilder
        }

        /** @see MessageCreateParams.Builder.body */
        fun body(body: MessageCreateParams.Body) = apply { paramsBuilder.body(body) }

        /** @see MessageCreateParams.Builder.maxTokens */
        fun maxTokens(maxTokens: Long) = apply { paramsBuilder.maxTokens(maxTokens) }

        /** @see MessageCreateParams.Builder.maxTokens */
        fun maxTokens(maxTokens: JsonField<Long>) = apply { paramsBuilder.maxTokens(maxTokens) }

        /** @see MessageCreateParams.Builder.messages */
        fun messages(messages: List<MessageParam>) = apply { paramsBuilder.messages(messages) }

        /** @see MessageCreateParams.Builder.messages */
        fun messages(messages: JsonField<List<MessageParam>>) = apply {
            paramsBuilder.messages(messages)
        }

        /** @see MessageCreateParams.Builder.addMessage */
        fun addMessage(message: MessageParam) = apply { paramsBuilder.addMessage(message) }

        /** @see MessageCreateParams.Builder.addMessage */
        fun addMessage(message: Message) = apply { paramsBuilder.addMessage(message) }

        /** @see MessageCreateParams.Builder.addUserMessage */
        fun addUserMessage(content: MessageParam.Content) = apply {
            paramsBuilder.addUserMessage(content)
        }

        /** @see MessageCreateParams.Builder.addUserMessage */
        fun addUserMessage(string: String) = apply { paramsBuilder.addUserMessage(string) }

        /** @see MessageCreateParams.Builder.addUserMessageOfBlockParams */
        fun addUserMessageOfBlockParams(blockParams: List<ContentBlockParam>) = apply {
            paramsBuilder.addUserMessageOfBlockParams(blockParams)
        }

        /** @see MessageCreateParams.Builder.addAssistantMessage */
        fun addAssistantMessage(content: MessageParam.Content) = apply {
            paramsBuilder.addAssistantMessage(content)
        }

        /** @see MessageCreateParams.Builder.addAssistantMessage */
        fun addAssistantMessage(string: String) = apply {
            paramsBuilder.addAssistantMessage(string)
        }

        /** @see MessageCreateParams.Builder.addAssistantMessageOfBlockParams */
        fun addAssistantMessageOfBlockParams(blockParams: List<ContentBlockParam>) = apply {
            paramsBuilder.addAssistantMessageOfBlockParams(blockParams)
        }

        /** @see MessageCreateParams.Builder.model */
        fun model(model: Model) = apply { paramsBuilder.model(model) }

        /** @see MessageCreateParams.Builder.model */
        fun model(model: JsonField<Model>) = apply { paramsBuilder.model(model) }

        /** @see MessageCreateParams.Builder.model */
        fun model(value: String) = apply { paramsBuilder.model(value) }

        /** @see MessageCreateParams.Builder.metadata */
        fun metadata(metadata: Metadata) = apply { paramsBuilder.metadata(metadata) }

        /** @see MessageCreateParams.Builder.metadata */
        fun metadata(metadata: JsonField<Metadata>) = apply { paramsBuilder.metadata(metadata) }

        /** @see MessageCreateParams.Builder.outputConfig */
        fun outputConfig(outputConfig: OutputConfig) = apply {
            paramsBuilder.outputConfig(outputConfig)
        }

        /** @see MessageCreateParams.Builder.outputConfig */
        fun outputConfig(outputConfig: JsonField<OutputConfig>) = apply {
            paramsBuilder.outputConfig(outputConfig)
        }

        /**
         * Sets the output configuration with a JSON schema format derived from the structure of the
         * given class. This is the recommended way to specify structured outputs.
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
            if (outputConfigSet) {
                throw IllegalArgumentException(
                    "outputConfig was called multiple times. Please use only one outputConfig call."
                )
            }
            this.outputType = outputType
            val format = outputFormatFromClass(outputType, localValidation)
            paramsBuilder.outputConfig(OutputConfig.builder().format(format).build())
            // GA version: NO beta header injection
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

        /** @see MessageCreateParams.Builder.systemOfTextBlockParams */
        fun systemOfTextBlockParams(textBlockParams: List<TextBlockParam>) = apply {
            paramsBuilder.systemOfTextBlockParams(textBlockParams)
        }

        /** @see MessageCreateParams.Builder.temperature */
        fun temperature(temperature: Double) = apply { paramsBuilder.temperature(temperature) }

        /** @see MessageCreateParams.Builder.temperature */
        fun temperature(temperature: JsonField<Double>) = apply {
            paramsBuilder.temperature(temperature)
        }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(thinking: ThinkingConfigParam) = apply { paramsBuilder.thinking(thinking) }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(thinking: JsonField<ThinkingConfigParam>) = apply {
            paramsBuilder.thinking(thinking)
        }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(enabled: ThinkingConfigEnabled) = apply { paramsBuilder.thinking(enabled) }

        /** @see MessageCreateParams.Builder.enabledThinking */
        fun enabledThinking(budgetTokens: Long) = apply {
            paramsBuilder.enabledThinking(budgetTokens)
        }

        /** @see MessageCreateParams.Builder.thinking */
        fun thinking(disabled: ThinkingConfigDisabled) = apply { paramsBuilder.thinking(disabled) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(toolChoice: ToolChoice) = apply { paramsBuilder.toolChoice(toolChoice) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(toolChoice: JsonField<ToolChoice>) = apply {
            paramsBuilder.toolChoice(toolChoice)
        }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(auto: ToolChoiceAuto) = apply { paramsBuilder.toolChoice(auto) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(any: ToolChoiceAny) = apply { paramsBuilder.toolChoice(any) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(tool: ToolChoiceTool) = apply { paramsBuilder.toolChoice(tool) }

        /** @see MessageCreateParams.Builder.toolToolChoice */
        fun toolToolChoice(name: String) = apply { paramsBuilder.toolToolChoice(name) }

        /** @see MessageCreateParams.Builder.toolChoice */
        fun toolChoice(none: ToolChoiceNone) = apply { paramsBuilder.toolChoice(none) }

        /** @see MessageCreateParams.Builder.tools */
        fun tools(tools: List<ToolUnion>) = apply { paramsBuilder.tools(tools) }

        /** @see MessageCreateParams.Builder.tools */
        fun tools(tools: JsonField<List<ToolUnion>>) = apply { paramsBuilder.tools(tools) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(tool: ToolUnion) = apply { paramsBuilder.addTool(tool) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(tool: Tool) = apply { paramsBuilder.addTool(tool) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(bash20250124: ToolBash20250124) = apply { paramsBuilder.addTool(bash20250124) }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(textEditor20250124: ToolTextEditor20250124) = apply {
            paramsBuilder.addTool(textEditor20250124)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(textEditor20250429: ToolTextEditor20250429) = apply {
            paramsBuilder.addTool(textEditor20250429)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(textEditor20250728: ToolTextEditor20250728) = apply {
            paramsBuilder.addTool(textEditor20250728)
        }

        /** @see MessageCreateParams.Builder.addTool */
        fun addTool(webSearchTool20250305: WebSearchTool20250305) = apply {
            paramsBuilder.addTool(webSearchTool20250305)
        }

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
