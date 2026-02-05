// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.messages.Model
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

/**
 * Count the number of tokens in a Message.
 *
 * The Token Count API can be used to count the number of tokens in a Message, including tools,
 * images, and documents, without creating it.
 *
 * Learn more about token counting in our
 * [user guide](https://docs.claude.com/en/docs/build-with-claude/token-counting)
 */
class MessageCountTokensParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Input messages.
     *
     * Our models are trained to operate on alternating `user` and `assistant` conversational turns.
     * When creating a new `Message`, you specify the prior conversational turns with the `messages`
     * parameter, and the model then generates the next `Message` in the conversation. Consecutive
     * `user` or `assistant` turns in your request will be combined into a single turn.
     *
     * Each input message must be an object with a `role` and `content`. You can specify a single
     * `user`-role message, or you can include multiple `user` and `assistant` messages.
     *
     * If the final message uses the `assistant` role, the response content will continue
     * immediately from the content in that message. This can be used to constrain part of the
     * model's response.
     *
     * Example with a single `user` message:
     * ```json
     * [{"role": "user", "content": "Hello, Claude"}]
     * ```
     *
     * Example with multiple conversational turns:
     * ```json
     * [
     *   {"role": "user", "content": "Hello there."},
     *   {"role": "assistant", "content": "Hi, I'm Claude. How can I help you?"},
     *   {"role": "user", "content": "Can you explain LLMs in plain English?"},
     * ]
     * ```
     *
     * Example with a partially-filled response from Claude:
     * ```json
     * [
     *   {"role": "user", "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"},
     *   {"role": "assistant", "content": "The best answer is ("},
     * ]
     * ```
     *
     * Each input message `content` may be either a single `string` or an array of content blocks,
     * where each block has a specific `type`. Using a `string` for `content` is shorthand for an
     * array of one content block of type `"text"`. The following input messages are equivalent:
     * ```json
     * {"role": "user", "content": "Hello, Claude"}
     * ```
     * ```json
     * {"role": "user", "content": [{"type": "text", "text": "Hello, Claude"}]}
     * ```
     *
     * See [input examples](https://docs.claude.com/en/api/messages-examples).
     *
     * Note that if you want to include a
     * [system prompt](https://docs.claude.com/en/docs/system-prompts), you can use the top-level
     * `system` parameter — there is no `"system"` role for input messages in the Messages API.
     *
     * There is a limit of 100,000 messages in a single request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun messages(): List<BetaMessageParam> = body.messages()

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun model(): Model = body.model()

    /**
     * Context management configuration.
     *
     * This allows you to control how Claude manages context across multiple requests, such as
     * whether to clear function results or not.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contextManagement(): Optional<BetaContextManagementConfig> = body.contextManagement()

    /**
     * MCP servers to be utilized in this request
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mcpServers(): Optional<List<BetaRequestMcpServerUrlDefinition>> = body.mcpServers()

    /**
     * Configuration options for the model's output, such as the output format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun outputConfig(): Optional<BetaOutputConfig> = body.outputConfig()

    /**
     * Deprecated: Use `output_config.format` instead. See
     * [structured outputs](https://platform.claude.com/docs/en/build-with-claude/structured-outputs)
     *
     * A schema to specify Claude's output format in responses. This parameter will be removed in a
     * future release.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    @Deprecated("deprecated")
    fun outputFormat(): Optional<BetaJsonOutputFormat> = body.outputFormat()

    /**
     * System prompt.
     *
     * A system prompt is a way of providing context and instructions to Claude, such as specifying
     * a particular goal or role. See our
     * [guide to system prompts](https://docs.claude.com/en/docs/system-prompts).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun system(): Optional<System> = body.system()

    /**
     * Configuration for enabling Claude's extended thinking.
     *
     * When enabled, responses include `thinking` content blocks showing Claude's thinking process
     * before the final answer. Requires a minimum budget of 1,024 tokens and counts towards your
     * `max_tokens` limit.
     *
     * See [extended thinking](https://docs.claude.com/en/docs/build-with-claude/extended-thinking)
     * for details.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun thinking(): Optional<BetaThinkingConfigParam> = body.thinking()

    /**
     * How the model should use the provided tools. The model can use a specific tool, any available
     * tool, decide by itself, or not use tools at all.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun toolChoice(): Optional<BetaToolChoice> = body.toolChoice()

    /**
     * Definitions of tools that the model may use.
     *
     * If you include `tools` in your API request, the model may return `tool_use` content blocks
     * that represent the model's use of those tools. You can then run those tools using the tool
     * input generated by the model and then optionally return results back to the model using
     * `tool_result` content blocks.
     *
     * There are two types of tools: **client tools** and **server tools**. The behavior described
     * below applies to client tools. For
     * [server tools](https://docs.claude.com/en/docs/agents-and-tools/tool-use/overview\#server-tools),
     * see their individual documentation as each has its own behavior (e.g., the
     * [web search tool](https://docs.claude.com/en/docs/agents-and-tools/tool-use/web-search-tool)).
     *
     * Each tool definition includes:
     * * `name`: Name of the tool.
     * * `description`: Optional, but strongly-recommended description of the tool.
     * * `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool `input`
     *   shape that the model will produce in `tool_use` output content blocks.
     *
     * For example, if you defined `tools` as:
     * ```json
     * [
     *   {
     *     "name": "get_stock_price",
     *     "description": "Get the current stock price for a given ticker symbol.",
     *     "input_schema": {
     *       "type": "object",
     *       "properties": {
     *         "ticker": {
     *           "type": "string",
     *           "description": "The stock ticker symbol, e.g. AAPL for Apple Inc."
     *         }
     *       },
     *       "required": ["ticker"]
     *     }
     *   }
     * ]
     * ```
     *
     * And then asked the model "What's the S&P 500 at today?", the model might produce `tool_use`
     * content blocks in the response like this:
     * ```json
     * [
     *   {
     *     "type": "tool_use",
     *     "id": "toolu_01D7FLrfh4GYq7yT1ULFeyMV",
     *     "name": "get_stock_price",
     *     "input": { "ticker": "^GSPC" }
     *   }
     * ]
     * ```
     *
     * You might then run your `get_stock_price` tool with `{"ticker": "^GSPC"}` as an input, and
     * return the following back to the model in a subsequent `user` message:
     * ```json
     * [
     *   {
     *     "type": "tool_result",
     *     "tool_use_id": "toolu_01D7FLrfh4GYq7yT1ULFeyMV",
     *     "content": "259.75 USD"
     *   }
     * ]
     * ```
     *
     * Tools can be used for workflows that include running client-side tools and functions, or more
     * generally whenever you want the model to produce a particular JSON structure of output.
     *
     * See our [guide](https://docs.claude.com/en/docs/tool-use) for more details.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tools(): Optional<List<Tool>> = body.tools()

    /**
     * Returns the raw JSON value of [messages].
     *
     * Unlike [messages], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _messages(): JsonField<List<BetaMessageParam>> = body._messages()

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _model(): JsonField<Model> = body._model()

    /**
     * Returns the raw JSON value of [contextManagement].
     *
     * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _contextManagement(): JsonField<BetaContextManagementConfig> = body._contextManagement()

    /**
     * Returns the raw JSON value of [mcpServers].
     *
     * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _mcpServers(): JsonField<List<BetaRequestMcpServerUrlDefinition>> = body._mcpServers()

    /**
     * Returns the raw JSON value of [outputConfig].
     *
     * Unlike [outputConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _outputConfig(): JsonField<BetaOutputConfig> = body._outputConfig()

    /**
     * Returns the raw JSON value of [outputFormat].
     *
     * Unlike [outputFormat], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated("deprecated")
    fun _outputFormat(): JsonField<BetaJsonOutputFormat> = body._outputFormat()

    /**
     * Returns the raw JSON value of [system].
     *
     * Unlike [system], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _system(): JsonField<System> = body._system()

    /**
     * Returns the raw JSON value of [thinking].
     *
     * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _thinking(): JsonField<BetaThinkingConfigParam> = body._thinking()

    /**
     * Returns the raw JSON value of [toolChoice].
     *
     * Unlike [toolChoice], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _toolChoice(): JsonField<BetaToolChoice> = body._toolChoice()

    /**
     * Returns the raw JSON value of [tools].
     *
     * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _tools(): JsonField<List<Tool>> = body._tools()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MessageCountTokensParams].
         *
         * The following fields are required:
         * ```java
         * .messages()
         * .model()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MessageCountTokensParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(messageCountTokensParams: MessageCountTokensParams) = apply {
            betas = messageCountTokensParams.betas?.toMutableList()
            body = messageCountTokensParams.body.toBuilder()
            additionalHeaders = messageCountTokensParams.additionalHeaders.toBuilder()
            additionalQueryParams = messageCountTokensParams.additionalQueryParams.toBuilder()
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [messages]
         * - [model]
         * - [contextManagement]
         * - [mcpServers]
         * - [outputConfig]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Input messages.
         *
         * Our models are trained to operate on alternating `user` and `assistant` conversational
         * turns. When creating a new `Message`, you specify the prior conversational turns with the
         * `messages` parameter, and the model then generates the next `Message` in the
         * conversation. Consecutive `user` or `assistant` turns in your request will be combined
         * into a single turn.
         *
         * Each input message must be an object with a `role` and `content`. You can specify a
         * single `user`-role message, or you can include multiple `user` and `assistant` messages.
         *
         * If the final message uses the `assistant` role, the response content will continue
         * immediately from the content in that message. This can be used to constrain part of the
         * model's response.
         *
         * Example with a single `user` message:
         * ```json
         * [{"role": "user", "content": "Hello, Claude"}]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   {"role": "user", "content": "Hello there."},
         *   {"role": "assistant", "content": "Hi, I'm Claude. How can I help you?"},
         *   {"role": "user", "content": "Can you explain LLMs in plain English?"},
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {"role": "user", "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"},
         *   {"role": "assistant", "content": "The best answer is ("},
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * {"role": "user", "content": "Hello, Claude"}
         * ```
         * ```json
         * {"role": "user", "content": [{"type": "text", "text": "Hello, Claude"}]}
         * ```
         *
         * See [input examples](https://docs.claude.com/en/api/messages-examples).
         *
         * Note that if you want to include a
         * [system prompt](https://docs.claude.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         *
         * There is a limit of 100,000 messages in a single request.
         */
        fun messages(messages: List<BetaMessageParam>) = apply { body.messages(messages) }

        /**
         * Sets [Builder.messages] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messages] with a well-typed `List<BetaMessageParam>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun messages(messages: JsonField<List<BetaMessageParam>>) = apply {
            body.messages(messages)
        }

        /**
         * Adds a single [BetaMessageParam] to [messages].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMessage(message: BetaMessageParam) = apply { body.addMessage(message) }

        /** Alias for calling [addMessage] with `message.toParam()`. */
        fun addMessage(message: BetaMessage) = apply { body.addMessage(message) }

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
            body.addUserMessage(content)
        }

        /** Alias for calling [addUserMessage] with `BetaMessageParam.Content.ofString(string)`. */
        fun addUserMessage(string: String) = apply { body.addUserMessage(string) }

        /**
         * Alias for calling [addUserMessage] with
         * `BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)`.
         */
        fun addUserMessageOfBetaContentBlockParams(
            betaContentBlockParams: List<BetaContentBlockParam>
        ) = apply { body.addUserMessageOfBetaContentBlockParams(betaContentBlockParams) }

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
            body.addAssistantMessage(content)
        }

        /**
         * Alias for calling [addAssistantMessage] with `BetaMessageParam.Content.ofString(string)`.
         */
        fun addAssistantMessage(string: String) = apply { body.addAssistantMessage(string) }

        /**
         * Alias for calling [addAssistantMessage] with
         * `BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)`.
         */
        fun addAssistantMessageOfBetaContentBlockParams(
            betaContentBlockParams: List<BetaContentBlockParam>
        ) = apply { body.addAssistantMessageOfBetaContentBlockParams(betaContentBlockParams) }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(model: Model) = apply { body.model(model) }

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [Model] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<Model>) = apply { body.model(model) }

        /**
         * Sets [model] to an arbitrary [String].
         *
         * You should usually call [model] with a well-typed [Model] constant instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(value: String) = apply { body.model(value) }

        /**
         * Context management configuration.
         *
         * This allows you to control how Claude manages context across multiple requests, such as
         * whether to clear function results or not.
         */
        fun contextManagement(contextManagement: BetaContextManagementConfig?) = apply {
            body.contextManagement(contextManagement)
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
            body.contextManagement(contextManagement)
        }

        /** MCP servers to be utilized in this request */
        fun mcpServers(mcpServers: List<BetaRequestMcpServerUrlDefinition>) = apply {
            body.mcpServers(mcpServers)
        }

        /**
         * Sets [Builder.mcpServers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServers] with a well-typed
         * `List<BetaRequestMcpServerUrlDefinition>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun mcpServers(mcpServers: JsonField<List<BetaRequestMcpServerUrlDefinition>>) = apply {
            body.mcpServers(mcpServers)
        }

        /**
         * Adds a single [BetaRequestMcpServerUrlDefinition] to [mcpServers].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMcpServer(mcpServer: BetaRequestMcpServerUrlDefinition) = apply {
            body.addMcpServer(mcpServer)
        }

        /** Configuration options for the model's output, such as the output format. */
        fun outputConfig(outputConfig: BetaOutputConfig) = apply { body.outputConfig(outputConfig) }

        /**
         * Sets [Builder.outputConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputConfig] with a well-typed [BetaOutputConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun outputConfig(outputConfig: JsonField<BetaOutputConfig>) = apply {
            body.outputConfig(outputConfig)
        }

        /**
         * Deprecated: Use `output_config.format` instead. See
         * [structured outputs](https://platform.claude.com/docs/en/build-with-claude/structured-outputs)
         *
         * A schema to specify Claude's output format in responses. This parameter will be removed
         * in a future release.
         */
        @Deprecated("deprecated")
        fun outputFormat(outputFormat: BetaJsonOutputFormat?) = apply {
            body.outputFormat(outputFormat)
        }

        /** Alias for calling [Builder.outputFormat] with `outputFormat.orElse(null)`. */
        @Deprecated("deprecated")
        fun outputFormat(outputFormat: Optional<BetaJsonOutputFormat>) =
            outputFormat(outputFormat.getOrNull())

        /**
         * Sets [Builder.outputFormat] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputFormat] with a well-typed [BetaJsonOutputFormat]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        @Deprecated("deprecated")
        fun outputFormat(outputFormat: JsonField<BetaJsonOutputFormat>) = apply {
            body.outputFormat(outputFormat)
        }

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.claude.com/en/docs/system-prompts).
         */
        fun system(system: System) = apply { body.system(system) }

        /**
         * Sets [Builder.system] to an arbitrary JSON value.
         *
         * You should usually call [Builder.system] with a well-typed [System] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun system(system: JsonField<System>) = apply { body.system(system) }

        /** Alias for calling [system] with `System.ofString(string)`. */
        fun system(string: String) = apply { body.system(string) }

        /** Alias for calling [system] with `System.ofBetaTextBlockParams(betaTextBlockParams)`. */
        fun systemOfBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>) = apply {
            body.systemOfBetaTextBlockParams(betaTextBlockParams)
        }

        /**
         * Configuration for enabling Claude's extended thinking.
         *
         * When enabled, responses include `thinking` content blocks showing Claude's thinking
         * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
         * towards your `max_tokens` limit.
         *
         * See
         * [extended thinking](https://docs.claude.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun thinking(thinking: BetaThinkingConfigParam) = apply { body.thinking(thinking) }

        /**
         * Sets [Builder.thinking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinking] with a well-typed [BetaThinkingConfigParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun thinking(thinking: JsonField<BetaThinkingConfigParam>) = apply {
            body.thinking(thinking)
        }

        /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofEnabled(enabled)`. */
        fun thinking(enabled: BetaThinkingConfigEnabled) = apply { body.thinking(enabled) }

        /**
         * Alias for calling [thinking] with the following:
         * ```java
         * BetaThinkingConfigEnabled.builder()
         *     .budgetTokens(budgetTokens)
         *     .build()
         * ```
         */
        fun enabledThinking(budgetTokens: Long) = apply { body.enabledThinking(budgetTokens) }

        /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofDisabled(disabled)`. */
        fun thinking(disabled: BetaThinkingConfigDisabled) = apply { body.thinking(disabled) }

        /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofAdaptive(adaptive)`. */
        fun thinking(adaptive: BetaThinkingConfigAdaptive) = apply { body.thinking(adaptive) }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, decide by itself, or not use tools at all.
         */
        fun toolChoice(toolChoice: BetaToolChoice) = apply { body.toolChoice(toolChoice) }

        /**
         * Sets [Builder.toolChoice] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolChoice] with a well-typed [BetaToolChoice] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun toolChoice(toolChoice: JsonField<BetaToolChoice>) = apply {
            body.toolChoice(toolChoice)
        }

        /** Alias for calling [toolChoice] with `BetaToolChoice.ofAuto(auto)`. */
        fun toolChoice(auto: BetaToolChoiceAuto) = apply { body.toolChoice(auto) }

        /** Alias for calling [toolChoice] with `BetaToolChoice.ofAny(any)`. */
        fun toolChoice(any: BetaToolChoiceAny) = apply { body.toolChoice(any) }

        /** Alias for calling [toolChoice] with `BetaToolChoice.ofTool(tool)`. */
        fun toolChoice(tool: BetaToolChoiceTool) = apply { body.toolChoice(tool) }

        /**
         * Alias for calling [toolChoice] with the following:
         * ```java
         * BetaToolChoiceTool.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun toolToolChoice(name: String) = apply { body.toolToolChoice(name) }

        /** Alias for calling [toolChoice] with `BetaToolChoice.ofNone(none)`. */
        fun toolChoice(none: BetaToolChoiceNone) = apply { body.toolChoice(none) }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * There are two types of tools: **client tools** and **server tools**. The behavior
         * described below applies to client tools. For
         * [server tools](https://docs.claude.com/en/docs/agents-and-tools/tool-use/overview\#server-tools),
         * see their individual documentation as each has its own behavior (e.g., the
         * [web search tool](https://docs.claude.com/en/docs/agents-and-tools/tool-use/web-search-tool)).
         *
         * Each tool definition includes:
         * * `name`: Name of the tool.
         * * `description`: Optional, but strongly-recommended description of the tool.
         * * `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
         *   `input` shape that the model will produce in `tool_use` output content blocks.
         *
         * For example, if you defined `tools` as:
         * ```json
         * [
         *   {
         *     "name": "get_stock_price",
         *     "description": "Get the current stock price for a given ticker symbol.",
         *     "input_schema": {
         *       "type": "object",
         *       "properties": {
         *         "ticker": {
         *           "type": "string",
         *           "description": "The stock ticker symbol, e.g. AAPL for Apple Inc."
         *         }
         *       },
         *       "required": ["ticker"]
         *     }
         *   }
         * ]
         * ```
         *
         * And then asked the model "What's the S&P 500 at today?", the model might produce
         * `tool_use` content blocks in the response like this:
         * ```json
         * [
         *   {
         *     "type": "tool_use",
         *     "id": "toolu_01D7FLrfh4GYq7yT1ULFeyMV",
         *     "name": "get_stock_price",
         *     "input": { "ticker": "^GSPC" }
         *   }
         * ]
         * ```
         *
         * You might then run your `get_stock_price` tool with `{"ticker": "^GSPC"}` as an input,
         * and return the following back to the model in a subsequent `user` message:
         * ```json
         * [
         *   {
         *     "type": "tool_result",
         *     "tool_use_id": "toolu_01D7FLrfh4GYq7yT1ULFeyMV",
         *     "content": "259.75 USD"
         *   }
         * ]
         * ```
         *
         * Tools can be used for workflows that include running client-side tools and functions, or
         * more generally whenever you want the model to produce a particular JSON structure of
         * output.
         *
         * See our [guide](https://docs.claude.com/en/docs/tool-use) for more details.
         */
        fun tools(tools: List<Tool>) = apply { body.tools(tools) }

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed `List<Tool>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun tools(tools: JsonField<List<Tool>>) = apply { body.tools(tools) }

        /**
         * Adds a single [Tool] to [tools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTool(tool: Tool) = apply { body.addTool(tool) }

        /** Alias for calling [addTool] with `Tool.ofBeta(beta)`. */
        fun addTool(beta: BetaTool) = apply { body.addTool(beta) }

        /** Alias for calling [addTool] with `Tool.ofBetaToolBash20241022(betaToolBash20241022)`. */
        fun addTool(betaToolBash20241022: BetaToolBash20241022) = apply {
            body.addTool(betaToolBash20241022)
        }

        /** Alias for calling [addTool] with `Tool.ofBetaToolBash20250124(betaToolBash20250124)`. */
        fun addTool(betaToolBash20250124: BetaToolBash20250124) = apply {
            body.addTool(betaToolBash20250124)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaCodeExecutionTool20250522(betaCodeExecutionTool20250522)`.
         */
        fun addTool(betaCodeExecutionTool20250522: BetaCodeExecutionTool20250522) = apply {
            body.addTool(betaCodeExecutionTool20250522)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaCodeExecutionTool20250825(betaCodeExecutionTool20250825)`.
         */
        fun addTool(betaCodeExecutionTool20250825: BetaCodeExecutionTool20250825) = apply {
            body.addTool(betaCodeExecutionTool20250825)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolComputerUse20241022(betaToolComputerUse20241022)`.
         */
        fun addTool(betaToolComputerUse20241022: BetaToolComputerUse20241022) = apply {
            body.addTool(betaToolComputerUse20241022)
        }

        /**
         * Alias for calling [addTool] with `Tool.ofBetaMemoryTool20250818(betaMemoryTool20250818)`.
         */
        fun addTool(betaMemoryTool20250818: BetaMemoryTool20250818) = apply {
            body.addTool(betaMemoryTool20250818)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolComputerUse20250124(betaToolComputerUse20250124)`.
         */
        fun addTool(betaToolComputerUse20250124: BetaToolComputerUse20250124) = apply {
            body.addTool(betaToolComputerUse20250124)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolTextEditor20241022(betaToolTextEditor20241022)`.
         */
        fun addTool(betaToolTextEditor20241022: BetaToolTextEditor20241022) = apply {
            body.addTool(betaToolTextEditor20241022)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolComputerUse20251124(betaToolComputerUse20251124)`.
         */
        fun addTool(betaToolComputerUse20251124: BetaToolComputerUse20251124) = apply {
            body.addTool(betaToolComputerUse20251124)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolTextEditor20250124(betaToolTextEditor20250124)`.
         */
        fun addTool(betaToolTextEditor20250124: BetaToolTextEditor20250124) = apply {
            body.addTool(betaToolTextEditor20250124)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolTextEditor20250429(betaToolTextEditor20250429)`.
         */
        fun addTool(betaToolTextEditor20250429: BetaToolTextEditor20250429) = apply {
            body.addTool(betaToolTextEditor20250429)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolTextEditor20250728(betaToolTextEditor20250728)`.
         */
        fun addTool(betaToolTextEditor20250728: BetaToolTextEditor20250728) = apply {
            body.addTool(betaToolTextEditor20250728)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaWebSearchTool20250305(betaWebSearchTool20250305)`.
         */
        fun addTool(betaWebSearchTool20250305: BetaWebSearchTool20250305) = apply {
            body.addTool(betaWebSearchTool20250305)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaWebFetchTool20250910(betaWebFetchTool20250910)`.
         */
        fun addTool(betaWebFetchTool20250910: BetaWebFetchTool20250910) = apply {
            body.addTool(betaWebFetchTool20250910)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolSearchToolBm25_20251119(betaToolSearchToolBm25_20251119)`.
         */
        fun addTool(betaToolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119) = apply {
            body.addTool(betaToolSearchToolBm25_20251119)
        }

        /**
         * Alias for calling [addTool] with
         * `Tool.ofBetaToolSearchToolRegex20251119(betaToolSearchToolRegex20251119)`.
         */
        fun addTool(betaToolSearchToolRegex20251119: BetaToolSearchToolRegex20251119) = apply {
            body.addTool(betaToolSearchToolRegex20251119)
        }

        /** Alias for calling [addTool] with `Tool.ofBetaMcpToolset(betaMcpToolset)`. */
        fun addTool(betaMcpToolset: BetaMcpToolset) = apply { body.addTool(betaMcpToolset) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [MessageCountTokensParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .messages()
         * .model()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): MessageCountTokensParams =
            MessageCountTokensParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val messages: JsonField<List<BetaMessageParam>>,
        private val model: JsonField<Model>,
        private val contextManagement: JsonField<BetaContextManagementConfig>,
        private val mcpServers: JsonField<List<BetaRequestMcpServerUrlDefinition>>,
        private val outputConfig: JsonField<BetaOutputConfig>,
        private val outputFormat: JsonField<BetaJsonOutputFormat>,
        private val system: JsonField<System>,
        private val thinking: JsonField<BetaThinkingConfigParam>,
        private val toolChoice: JsonField<BetaToolChoice>,
        private val tools: JsonField<List<Tool>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("messages")
            @ExcludeMissing
            messages: JsonField<List<BetaMessageParam>> = JsonMissing.of(),
            @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
            @JsonProperty("context_management")
            @ExcludeMissing
            contextManagement: JsonField<BetaContextManagementConfig> = JsonMissing.of(),
            @JsonProperty("mcp_servers")
            @ExcludeMissing
            mcpServers: JsonField<List<BetaRequestMcpServerUrlDefinition>> = JsonMissing.of(),
            @JsonProperty("output_config")
            @ExcludeMissing
            outputConfig: JsonField<BetaOutputConfig> = JsonMissing.of(),
            @JsonProperty("output_format")
            @ExcludeMissing
            outputFormat: JsonField<BetaJsonOutputFormat> = JsonMissing.of(),
            @JsonProperty("system") @ExcludeMissing system: JsonField<System> = JsonMissing.of(),
            @JsonProperty("thinking")
            @ExcludeMissing
            thinking: JsonField<BetaThinkingConfigParam> = JsonMissing.of(),
            @JsonProperty("tool_choice")
            @ExcludeMissing
            toolChoice: JsonField<BetaToolChoice> = JsonMissing.of(),
            @JsonProperty("tools") @ExcludeMissing tools: JsonField<List<Tool>> = JsonMissing.of(),
        ) : this(
            messages,
            model,
            contextManagement,
            mcpServers,
            outputConfig,
            outputFormat,
            system,
            thinking,
            toolChoice,
            tools,
            mutableMapOf(),
        )

        /**
         * Input messages.
         *
         * Our models are trained to operate on alternating `user` and `assistant` conversational
         * turns. When creating a new `Message`, you specify the prior conversational turns with the
         * `messages` parameter, and the model then generates the next `Message` in the
         * conversation. Consecutive `user` or `assistant` turns in your request will be combined
         * into a single turn.
         *
         * Each input message must be an object with a `role` and `content`. You can specify a
         * single `user`-role message, or you can include multiple `user` and `assistant` messages.
         *
         * If the final message uses the `assistant` role, the response content will continue
         * immediately from the content in that message. This can be used to constrain part of the
         * model's response.
         *
         * Example with a single `user` message:
         * ```json
         * [{"role": "user", "content": "Hello, Claude"}]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   {"role": "user", "content": "Hello there."},
         *   {"role": "assistant", "content": "Hi, I'm Claude. How can I help you?"},
         *   {"role": "user", "content": "Can you explain LLMs in plain English?"},
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {"role": "user", "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"},
         *   {"role": "assistant", "content": "The best answer is ("},
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * {"role": "user", "content": "Hello, Claude"}
         * ```
         * ```json
         * {"role": "user", "content": [{"type": "text", "text": "Hello, Claude"}]}
         * ```
         *
         * See [input examples](https://docs.claude.com/en/api/messages-examples).
         *
         * Note that if you want to include a
         * [system prompt](https://docs.claude.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         *
         * There is a limit of 100,000 messages in a single request.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun messages(): List<BetaMessageParam> = messages.getRequired("messages")

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun model(): Model = model.getRequired("model")

        /**
         * Context management configuration.
         *
         * This allows you to control how Claude manages context across multiple requests, such as
         * whether to clear function results or not.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun contextManagement(): Optional<BetaContextManagementConfig> =
            contextManagement.getOptional("context_management")

        /**
         * MCP servers to be utilized in this request
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun mcpServers(): Optional<List<BetaRequestMcpServerUrlDefinition>> =
            mcpServers.getOptional("mcp_servers")

        /**
         * Configuration options for the model's output, such as the output format.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun outputConfig(): Optional<BetaOutputConfig> = outputConfig.getOptional("output_config")

        /**
         * Deprecated: Use `output_config.format` instead. See
         * [structured outputs](https://platform.claude.com/docs/en/build-with-claude/structured-outputs)
         *
         * A schema to specify Claude's output format in responses. This parameter will be removed
         * in a future release.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        @Deprecated("deprecated")
        fun outputFormat(): Optional<BetaJsonOutputFormat> =
            outputFormat.getOptional("output_format")

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.claude.com/en/docs/system-prompts).
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun system(): Optional<System> = system.getOptional("system")

        /**
         * Configuration for enabling Claude's extended thinking.
         *
         * When enabled, responses include `thinking` content blocks showing Claude's thinking
         * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
         * towards your `max_tokens` limit.
         *
         * See
         * [extended thinking](https://docs.claude.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun thinking(): Optional<BetaThinkingConfigParam> = thinking.getOptional("thinking")

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, decide by itself, or not use tools at all.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun toolChoice(): Optional<BetaToolChoice> = toolChoice.getOptional("tool_choice")

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * There are two types of tools: **client tools** and **server tools**. The behavior
         * described below applies to client tools. For
         * [server tools](https://docs.claude.com/en/docs/agents-and-tools/tool-use/overview\#server-tools),
         * see their individual documentation as each has its own behavior (e.g., the
         * [web search tool](https://docs.claude.com/en/docs/agents-and-tools/tool-use/web-search-tool)).
         *
         * Each tool definition includes:
         * * `name`: Name of the tool.
         * * `description`: Optional, but strongly-recommended description of the tool.
         * * `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
         *   `input` shape that the model will produce in `tool_use` output content blocks.
         *
         * For example, if you defined `tools` as:
         * ```json
         * [
         *   {
         *     "name": "get_stock_price",
         *     "description": "Get the current stock price for a given ticker symbol.",
         *     "input_schema": {
         *       "type": "object",
         *       "properties": {
         *         "ticker": {
         *           "type": "string",
         *           "description": "The stock ticker symbol, e.g. AAPL for Apple Inc."
         *         }
         *       },
         *       "required": ["ticker"]
         *     }
         *   }
         * ]
         * ```
         *
         * And then asked the model "What's the S&P 500 at today?", the model might produce
         * `tool_use` content blocks in the response like this:
         * ```json
         * [
         *   {
         *     "type": "tool_use",
         *     "id": "toolu_01D7FLrfh4GYq7yT1ULFeyMV",
         *     "name": "get_stock_price",
         *     "input": { "ticker": "^GSPC" }
         *   }
         * ]
         * ```
         *
         * You might then run your `get_stock_price` tool with `{"ticker": "^GSPC"}` as an input,
         * and return the following back to the model in a subsequent `user` message:
         * ```json
         * [
         *   {
         *     "type": "tool_result",
         *     "tool_use_id": "toolu_01D7FLrfh4GYq7yT1ULFeyMV",
         *     "content": "259.75 USD"
         *   }
         * ]
         * ```
         *
         * Tools can be used for workflows that include running client-side tools and functions, or
         * more generally whenever you want the model to produce a particular JSON structure of
         * output.
         *
         * See our [guide](https://docs.claude.com/en/docs/tool-use) for more details.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun tools(): Optional<List<Tool>> = tools.getOptional("tools")

        /**
         * Returns the raw JSON value of [messages].
         *
         * Unlike [messages], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("messages")
        @ExcludeMissing
        fun _messages(): JsonField<List<BetaMessageParam>> = messages

        /**
         * Returns the raw JSON value of [model].
         *
         * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

        /**
         * Returns the raw JSON value of [contextManagement].
         *
         * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("context_management")
        @ExcludeMissing
        fun _contextManagement(): JsonField<BetaContextManagementConfig> = contextManagement

        /**
         * Returns the raw JSON value of [mcpServers].
         *
         * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("mcp_servers")
        @ExcludeMissing
        fun _mcpServers(): JsonField<List<BetaRequestMcpServerUrlDefinition>> = mcpServers

        /**
         * Returns the raw JSON value of [outputConfig].
         *
         * Unlike [outputConfig], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("output_config")
        @ExcludeMissing
        fun _outputConfig(): JsonField<BetaOutputConfig> = outputConfig

        /**
         * Returns the raw JSON value of [outputFormat].
         *
         * Unlike [outputFormat], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @Deprecated("deprecated")
        @JsonProperty("output_format")
        @ExcludeMissing
        fun _outputFormat(): JsonField<BetaJsonOutputFormat> = outputFormat

        /**
         * Returns the raw JSON value of [system].
         *
         * Unlike [system], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("system") @ExcludeMissing fun _system(): JsonField<System> = system

        /**
         * Returns the raw JSON value of [thinking].
         *
         * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("thinking")
        @ExcludeMissing
        fun _thinking(): JsonField<BetaThinkingConfigParam> = thinking

        /**
         * Returns the raw JSON value of [toolChoice].
         *
         * Unlike [toolChoice], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tool_choice")
        @ExcludeMissing
        fun _toolChoice(): JsonField<BetaToolChoice> = toolChoice

        /**
         * Returns the raw JSON value of [tools].
         *
         * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tools") @ExcludeMissing fun _tools(): JsonField<List<Tool>> = tools

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
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .messages()
             * .model()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var messages: JsonField<MutableList<BetaMessageParam>>? = null
            private var model: JsonField<Model>? = null
            private var contextManagement: JsonField<BetaContextManagementConfig> = JsonMissing.of()
            private var mcpServers: JsonField<MutableList<BetaRequestMcpServerUrlDefinition>>? =
                null
            private var outputConfig: JsonField<BetaOutputConfig> = JsonMissing.of()
            private var outputFormat: JsonField<BetaJsonOutputFormat> = JsonMissing.of()
            private var system: JsonField<System> = JsonMissing.of()
            private var thinking: JsonField<BetaThinkingConfigParam> = JsonMissing.of()
            private var toolChoice: JsonField<BetaToolChoice> = JsonMissing.of()
            private var tools: JsonField<MutableList<Tool>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                messages = body.messages.map { it.toMutableList() }
                model = body.model
                contextManagement = body.contextManagement
                mcpServers = body.mcpServers.map { it.toMutableList() }
                outputConfig = body.outputConfig
                outputFormat = body.outputFormat
                system = body.system
                thinking = body.thinking
                toolChoice = body.toolChoice
                tools = body.tools.map { it.toMutableList() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Input messages.
             *
             * Our models are trained to operate on alternating `user` and `assistant`
             * conversational turns. When creating a new `Message`, you specify the prior
             * conversational turns with the `messages` parameter, and the model then generates the
             * next `Message` in the conversation. Consecutive `user` or `assistant` turns in your
             * request will be combined into a single turn.
             *
             * Each input message must be an object with a `role` and `content`. You can specify a
             * single `user`-role message, or you can include multiple `user` and `assistant`
             * messages.
             *
             * If the final message uses the `assistant` role, the response content will continue
             * immediately from the content in that message. This can be used to constrain part of
             * the model's response.
             *
             * Example with a single `user` message:
             * ```json
             * [{"role": "user", "content": "Hello, Claude"}]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   {"role": "user", "content": "Hello there."},
             *   {"role": "assistant", "content": "Hi, I'm Claude. How can I help you?"},
             *   {"role": "user", "content": "Can you explain LLMs in plain English?"},
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {"role": "user", "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"},
             *   {"role": "assistant", "content": "The best answer is ("},
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * {"role": "user", "content": "Hello, Claude"}
             * ```
             * ```json
             * {"role": "user", "content": [{"type": "text", "text": "Hello, Claude"}]}
             * ```
             *
             * See [input examples](https://docs.claude.com/en/api/messages-examples).
             *
             * Note that if you want to include a
             * [system prompt](https://docs.claude.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             *
             * There is a limit of 100,000 messages in a single request.
             */
            fun messages(messages: List<BetaMessageParam>) = messages(JsonField.of(messages))

            /**
             * Sets [Builder.messages] to an arbitrary JSON value.
             *
             * You should usually call [Builder.messages] with a well-typed `List<BetaMessageParam>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun messages(messages: JsonField<List<BetaMessageParam>>) = apply {
                this.messages = messages.map { it.toMutableList() }
            }

            /**
             * Adds a single [BetaMessageParam] to [messages].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addMessage(message: BetaMessageParam) = apply {
                messages =
                    (messages ?: JsonField.of(mutableListOf())).also {
                        checkKnown("messages", it).add(message)
                    }
            }

            /** Alias for calling [addMessage] with `message.toParam()`. */
            fun addMessage(message: BetaMessage) = addMessage(message.toParam())

            /**
             * Alias for calling [addMessage] with the following:
             * ```java
             * BetaMessageParam.builder()
             *     .role(BetaMessageParam.Role.USER)
             *     .content(content)
             *     .build()
             * ```
             */
            fun addUserMessage(content: BetaMessageParam.Content) =
                addMessage(
                    BetaMessageParam.builder()
                        .role(BetaMessageParam.Role.USER)
                        .content(content)
                        .build()
                )

            /**
             * Alias for calling [addUserMessage] with `BetaMessageParam.Content.ofString(string)`.
             */
            fun addUserMessage(string: String) =
                addUserMessage(BetaMessageParam.Content.ofString(string))

            /**
             * Alias for calling [addUserMessage] with
             * `BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)`.
             */
            fun addUserMessageOfBetaContentBlockParams(
                betaContentBlockParams: List<BetaContentBlockParam>
            ) =
                addUserMessage(
                    BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)
                )

            /**
             * Alias for calling [addMessage] with the following:
             * ```java
             * BetaMessageParam.builder()
             *     .role(BetaMessageParam.Role.ASSISTANT)
             *     .content(content)
             *     .build()
             * ```
             */
            fun addAssistantMessage(content: BetaMessageParam.Content) =
                addMessage(
                    BetaMessageParam.builder()
                        .role(BetaMessageParam.Role.ASSISTANT)
                        .content(content)
                        .build()
                )

            /**
             * Alias for calling [addAssistantMessage] with
             * `BetaMessageParam.Content.ofString(string)`.
             */
            fun addAssistantMessage(string: String) =
                addAssistantMessage(BetaMessageParam.Content.ofString(string))

            /**
             * Alias for calling [addAssistantMessage] with
             * `BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)`.
             */
            fun addAssistantMessageOfBetaContentBlockParams(
                betaContentBlockParams: List<BetaContentBlockParam>
            ) =
                addAssistantMessage(
                    BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)
                )

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            fun model(model: Model) = model(JsonField.of(model))

            /**
             * Sets [Builder.model] to an arbitrary JSON value.
             *
             * You should usually call [Builder.model] with a well-typed [Model] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun model(model: JsonField<Model>) = apply { this.model = model }

            /**
             * Sets [model] to an arbitrary [String].
             *
             * You should usually call [model] with a well-typed [Model] constant instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun model(value: String) = model(Model.of(value))

            /**
             * Context management configuration.
             *
             * This allows you to control how Claude manages context across multiple requests, such
             * as whether to clear function results or not.
             */
            fun contextManagement(contextManagement: BetaContextManagementConfig?) =
                contextManagement(JsonField.ofNullable(contextManagement))

            /**
             * Alias for calling [Builder.contextManagement] with `contextManagement.orElse(null)`.
             */
            fun contextManagement(contextManagement: Optional<BetaContextManagementConfig>) =
                contextManagement(contextManagement.getOrNull())

            /**
             * Sets [Builder.contextManagement] to an arbitrary JSON value.
             *
             * You should usually call [Builder.contextManagement] with a well-typed
             * [BetaContextManagementConfig] value instead. This method is primarily for setting the
             * field to an undocumented or not yet supported value.
             */
            fun contextManagement(contextManagement: JsonField<BetaContextManagementConfig>) =
                apply {
                    this.contextManagement = contextManagement
                }

            /** MCP servers to be utilized in this request */
            fun mcpServers(mcpServers: List<BetaRequestMcpServerUrlDefinition>) =
                mcpServers(JsonField.of(mcpServers))

            /**
             * Sets [Builder.mcpServers] to an arbitrary JSON value.
             *
             * You should usually call [Builder.mcpServers] with a well-typed
             * `List<BetaRequestMcpServerUrlDefinition>` value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun mcpServers(mcpServers: JsonField<List<BetaRequestMcpServerUrlDefinition>>) = apply {
                this.mcpServers = mcpServers.map { it.toMutableList() }
            }

            /**
             * Adds a single [BetaRequestMcpServerUrlDefinition] to [mcpServers].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addMcpServer(mcpServer: BetaRequestMcpServerUrlDefinition) = apply {
                mcpServers =
                    (mcpServers ?: JsonField.of(mutableListOf())).also {
                        checkKnown("mcpServers", it).add(mcpServer)
                    }
            }

            /** Configuration options for the model's output, such as the output format. */
            fun outputConfig(outputConfig: BetaOutputConfig) =
                outputConfig(JsonField.of(outputConfig))

            /**
             * Sets [Builder.outputConfig] to an arbitrary JSON value.
             *
             * You should usually call [Builder.outputConfig] with a well-typed [BetaOutputConfig]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun outputConfig(outputConfig: JsonField<BetaOutputConfig>) = apply {
                this.outputConfig = outputConfig
            }

            /**
             * Deprecated: Use `output_config.format` instead. See
             * [structured outputs](https://platform.claude.com/docs/en/build-with-claude/structured-outputs)
             *
             * A schema to specify Claude's output format in responses. This parameter will be
             * removed in a future release.
             */
            @Deprecated("deprecated")
            fun outputFormat(outputFormat: BetaJsonOutputFormat?) =
                outputFormat(JsonField.ofNullable(outputFormat))

            /** Alias for calling [Builder.outputFormat] with `outputFormat.orElse(null)`. */
            @Deprecated("deprecated")
            fun outputFormat(outputFormat: Optional<BetaJsonOutputFormat>) =
                outputFormat(outputFormat.getOrNull())

            /**
             * Sets [Builder.outputFormat] to an arbitrary JSON value.
             *
             * You should usually call [Builder.outputFormat] with a well-typed
             * [BetaJsonOutputFormat] value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
             */
            @Deprecated("deprecated")
            fun outputFormat(outputFormat: JsonField<BetaJsonOutputFormat>) = apply {
                this.outputFormat = outputFormat
            }

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.claude.com/en/docs/system-prompts).
             */
            fun system(system: System) = system(JsonField.of(system))

            /**
             * Sets [Builder.system] to an arbitrary JSON value.
             *
             * You should usually call [Builder.system] with a well-typed [System] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun system(system: JsonField<System>) = apply { this.system = system }

            /** Alias for calling [system] with `System.ofString(string)`. */
            fun system(string: String) = system(System.ofString(string))

            /**
             * Alias for calling [system] with `System.ofBetaTextBlockParams(betaTextBlockParams)`.
             */
            fun systemOfBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>) =
                system(System.ofBetaTextBlockParams(betaTextBlockParams))

            /**
             * Configuration for enabling Claude's extended thinking.
             *
             * When enabled, responses include `thinking` content blocks showing Claude's thinking
             * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
             * towards your `max_tokens` limit.
             *
             * See
             * [extended thinking](https://docs.claude.com/en/docs/build-with-claude/extended-thinking)
             * for details.
             */
            fun thinking(thinking: BetaThinkingConfigParam) = thinking(JsonField.of(thinking))

            /**
             * Sets [Builder.thinking] to an arbitrary JSON value.
             *
             * You should usually call [Builder.thinking] with a well-typed
             * [BetaThinkingConfigParam] value instead. This method is primarily for setting the
             * field to an undocumented or not yet supported value.
             */
            fun thinking(thinking: JsonField<BetaThinkingConfigParam>) = apply {
                this.thinking = thinking
            }

            /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofEnabled(enabled)`. */
            fun thinking(enabled: BetaThinkingConfigEnabled) =
                thinking(BetaThinkingConfigParam.ofEnabled(enabled))

            /**
             * Alias for calling [thinking] with the following:
             * ```java
             * BetaThinkingConfigEnabled.builder()
             *     .budgetTokens(budgetTokens)
             *     .build()
             * ```
             */
            fun enabledThinking(budgetTokens: Long) =
                thinking(BetaThinkingConfigEnabled.builder().budgetTokens(budgetTokens).build())

            /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofDisabled(disabled)`. */
            fun thinking(disabled: BetaThinkingConfigDisabled) =
                thinking(BetaThinkingConfigParam.ofDisabled(disabled))

            /** Alias for calling [thinking] with `BetaThinkingConfigParam.ofAdaptive(adaptive)`. */
            fun thinking(adaptive: BetaThinkingConfigAdaptive) =
                thinking(BetaThinkingConfigParam.ofAdaptive(adaptive))

            /**
             * How the model should use the provided tools. The model can use a specific tool, any
             * available tool, decide by itself, or not use tools at all.
             */
            fun toolChoice(toolChoice: BetaToolChoice) = toolChoice(JsonField.of(toolChoice))

            /**
             * Sets [Builder.toolChoice] to an arbitrary JSON value.
             *
             * You should usually call [Builder.toolChoice] with a well-typed [BetaToolChoice] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun toolChoice(toolChoice: JsonField<BetaToolChoice>) = apply {
                this.toolChoice = toolChoice
            }

            /** Alias for calling [toolChoice] with `BetaToolChoice.ofAuto(auto)`. */
            fun toolChoice(auto: BetaToolChoiceAuto) = toolChoice(BetaToolChoice.ofAuto(auto))

            /** Alias for calling [toolChoice] with `BetaToolChoice.ofAny(any)`. */
            fun toolChoice(any: BetaToolChoiceAny) = toolChoice(BetaToolChoice.ofAny(any))

            /** Alias for calling [toolChoice] with `BetaToolChoice.ofTool(tool)`. */
            fun toolChoice(tool: BetaToolChoiceTool) = toolChoice(BetaToolChoice.ofTool(tool))

            /**
             * Alias for calling [toolChoice] with the following:
             * ```java
             * BetaToolChoiceTool.builder()
             *     .name(name)
             *     .build()
             * ```
             */
            fun toolToolChoice(name: String) =
                toolChoice(BetaToolChoiceTool.builder().name(name).build())

            /** Alias for calling [toolChoice] with `BetaToolChoice.ofNone(none)`. */
            fun toolChoice(none: BetaToolChoiceNone) = toolChoice(BetaToolChoice.ofNone(none))

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * There are two types of tools: **client tools** and **server tools**. The behavior
             * described below applies to client tools. For
             * [server tools](https://docs.claude.com/en/docs/agents-and-tools/tool-use/overview\#server-tools),
             * see their individual documentation as each has its own behavior (e.g., the
             * [web search tool](https://docs.claude.com/en/docs/agents-and-tools/tool-use/web-search-tool)).
             *
             * Each tool definition includes:
             * * `name`: Name of the tool.
             * * `description`: Optional, but strongly-recommended description of the tool.
             * * `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
             *   `input` shape that the model will produce in `tool_use` output content blocks.
             *
             * For example, if you defined `tools` as:
             * ```json
             * [
             *   {
             *     "name": "get_stock_price",
             *     "description": "Get the current stock price for a given ticker symbol.",
             *     "input_schema": {
             *       "type": "object",
             *       "properties": {
             *         "ticker": {
             *           "type": "string",
             *           "description": "The stock ticker symbol, e.g. AAPL for Apple Inc."
             *         }
             *       },
             *       "required": ["ticker"]
             *     }
             *   }
             * ]
             * ```
             *
             * And then asked the model "What's the S&P 500 at today?", the model might produce
             * `tool_use` content blocks in the response like this:
             * ```json
             * [
             *   {
             *     "type": "tool_use",
             *     "id": "toolu_01D7FLrfh4GYq7yT1ULFeyMV",
             *     "name": "get_stock_price",
             *     "input": { "ticker": "^GSPC" }
             *   }
             * ]
             * ```
             *
             * You might then run your `get_stock_price` tool with `{"ticker": "^GSPC"}` as an
             * input, and return the following back to the model in a subsequent `user` message:
             * ```json
             * [
             *   {
             *     "type": "tool_result",
             *     "tool_use_id": "toolu_01D7FLrfh4GYq7yT1ULFeyMV",
             *     "content": "259.75 USD"
             *   }
             * ]
             * ```
             *
             * Tools can be used for workflows that include running client-side tools and functions,
             * or more generally whenever you want the model to produce a particular JSON structure
             * of output.
             *
             * See our [guide](https://docs.claude.com/en/docs/tool-use) for more details.
             */
            fun tools(tools: List<Tool>) = tools(JsonField.of(tools))

            /**
             * Sets [Builder.tools] to an arbitrary JSON value.
             *
             * You should usually call [Builder.tools] with a well-typed `List<Tool>` value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun tools(tools: JsonField<List<Tool>>) = apply {
                this.tools = tools.map { it.toMutableList() }
            }

            /**
             * Adds a single [Tool] to [tools].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addTool(tool: Tool) = apply {
                tools =
                    (tools ?: JsonField.of(mutableListOf())).also {
                        checkKnown("tools", it).add(tool)
                    }
            }

            /** Alias for calling [addTool] with `Tool.ofBeta(beta)`. */
            fun addTool(beta: BetaTool) = addTool(Tool.ofBeta(beta))

            /**
             * Alias for calling [addTool] with `Tool.ofBetaToolBash20241022(betaToolBash20241022)`.
             */
            fun addTool(betaToolBash20241022: BetaToolBash20241022) =
                addTool(Tool.ofBetaToolBash20241022(betaToolBash20241022))

            /**
             * Alias for calling [addTool] with `Tool.ofBetaToolBash20250124(betaToolBash20250124)`.
             */
            fun addTool(betaToolBash20250124: BetaToolBash20250124) =
                addTool(Tool.ofBetaToolBash20250124(betaToolBash20250124))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaCodeExecutionTool20250522(betaCodeExecutionTool20250522)`.
             */
            fun addTool(betaCodeExecutionTool20250522: BetaCodeExecutionTool20250522) =
                addTool(Tool.ofBetaCodeExecutionTool20250522(betaCodeExecutionTool20250522))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaCodeExecutionTool20250825(betaCodeExecutionTool20250825)`.
             */
            fun addTool(betaCodeExecutionTool20250825: BetaCodeExecutionTool20250825) =
                addTool(Tool.ofBetaCodeExecutionTool20250825(betaCodeExecutionTool20250825))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolComputerUse20241022(betaToolComputerUse20241022)`.
             */
            fun addTool(betaToolComputerUse20241022: BetaToolComputerUse20241022) =
                addTool(Tool.ofBetaToolComputerUse20241022(betaToolComputerUse20241022))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaMemoryTool20250818(betaMemoryTool20250818)`.
             */
            fun addTool(betaMemoryTool20250818: BetaMemoryTool20250818) =
                addTool(Tool.ofBetaMemoryTool20250818(betaMemoryTool20250818))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolComputerUse20250124(betaToolComputerUse20250124)`.
             */
            fun addTool(betaToolComputerUse20250124: BetaToolComputerUse20250124) =
                addTool(Tool.ofBetaToolComputerUse20250124(betaToolComputerUse20250124))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolTextEditor20241022(betaToolTextEditor20241022)`.
             */
            fun addTool(betaToolTextEditor20241022: BetaToolTextEditor20241022) =
                addTool(Tool.ofBetaToolTextEditor20241022(betaToolTextEditor20241022))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolComputerUse20251124(betaToolComputerUse20251124)`.
             */
            fun addTool(betaToolComputerUse20251124: BetaToolComputerUse20251124) =
                addTool(Tool.ofBetaToolComputerUse20251124(betaToolComputerUse20251124))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolTextEditor20250124(betaToolTextEditor20250124)`.
             */
            fun addTool(betaToolTextEditor20250124: BetaToolTextEditor20250124) =
                addTool(Tool.ofBetaToolTextEditor20250124(betaToolTextEditor20250124))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolTextEditor20250429(betaToolTextEditor20250429)`.
             */
            fun addTool(betaToolTextEditor20250429: BetaToolTextEditor20250429) =
                addTool(Tool.ofBetaToolTextEditor20250429(betaToolTextEditor20250429))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolTextEditor20250728(betaToolTextEditor20250728)`.
             */
            fun addTool(betaToolTextEditor20250728: BetaToolTextEditor20250728) =
                addTool(Tool.ofBetaToolTextEditor20250728(betaToolTextEditor20250728))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaWebSearchTool20250305(betaWebSearchTool20250305)`.
             */
            fun addTool(betaWebSearchTool20250305: BetaWebSearchTool20250305) =
                addTool(Tool.ofBetaWebSearchTool20250305(betaWebSearchTool20250305))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaWebFetchTool20250910(betaWebFetchTool20250910)`.
             */
            fun addTool(betaWebFetchTool20250910: BetaWebFetchTool20250910) =
                addTool(Tool.ofBetaWebFetchTool20250910(betaWebFetchTool20250910))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolSearchToolBm25_20251119(betaToolSearchToolBm25_20251119)`.
             */
            fun addTool(betaToolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119) =
                addTool(Tool.ofBetaToolSearchToolBm25_20251119(betaToolSearchToolBm25_20251119))

            /**
             * Alias for calling [addTool] with
             * `Tool.ofBetaToolSearchToolRegex20251119(betaToolSearchToolRegex20251119)`.
             */
            fun addTool(betaToolSearchToolRegex20251119: BetaToolSearchToolRegex20251119) =
                addTool(Tool.ofBetaToolSearchToolRegex20251119(betaToolSearchToolRegex20251119))

            /** Alias for calling [addTool] with `Tool.ofBetaMcpToolset(betaMcpToolset)`. */
            fun addTool(betaMcpToolset: BetaMcpToolset) =
                addTool(Tool.ofBetaMcpToolset(betaMcpToolset))

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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .messages()
             * .model()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("messages", messages).map { it.toImmutable() },
                    checkRequired("model", model),
                    contextManagement,
                    (mcpServers ?: JsonMissing.of()).map { it.toImmutable() },
                    outputConfig,
                    outputFormat,
                    system,
                    thinking,
                    toolChoice,
                    (tools ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            messages().forEach { it.validate() }
            model()
            contextManagement().ifPresent { it.validate() }
            mcpServers().ifPresent { it.forEach { it.validate() } }
            outputConfig().ifPresent { it.validate() }
            outputFormat().ifPresent { it.validate() }
            system().ifPresent { it.validate() }
            thinking().ifPresent { it.validate() }
            toolChoice().ifPresent { it.validate() }
            tools().ifPresent { it.forEach { it.validate() } }
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
            (messages.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (if (model.asKnown().isPresent) 1 else 0) +
                (contextManagement.asKnown().getOrNull()?.validity() ?: 0) +
                (mcpServers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (outputConfig.asKnown().getOrNull()?.validity() ?: 0) +
                (outputFormat.asKnown().getOrNull()?.validity() ?: 0) +
                (system.asKnown().getOrNull()?.validity() ?: 0) +
                (thinking.asKnown().getOrNull()?.validity() ?: 0) +
                (toolChoice.asKnown().getOrNull()?.validity() ?: 0) +
                (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                messages == other.messages &&
                model == other.model &&
                contextManagement == other.contextManagement &&
                mcpServers == other.mcpServers &&
                outputConfig == other.outputConfig &&
                outputFormat == other.outputFormat &&
                system == other.system &&
                thinking == other.thinking &&
                toolChoice == other.toolChoice &&
                tools == other.tools &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                messages,
                model,
                contextManagement,
                mcpServers,
                outputConfig,
                outputFormat,
                system,
                thinking,
                toolChoice,
                tools,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{messages=$messages, model=$model, contextManagement=$contextManagement, mcpServers=$mcpServers, outputConfig=$outputConfig, outputFormat=$outputFormat, system=$system, thinking=$thinking, toolChoice=$toolChoice, tools=$tools, additionalProperties=$additionalProperties}"
    }

    /**
     * System prompt.
     *
     * A system prompt is a way of providing context and instructions to Claude, such as specifying
     * a particular goal or role. See our
     * [guide to system prompts](https://docs.claude.com/en/docs/system-prompts).
     */
    @JsonDeserialize(using = System.Deserializer::class)
    @JsonSerialize(using = System.Serializer::class)
    class System
    private constructor(
        private val string: String? = null,
        private val betaTextBlockParams: List<BetaTextBlockParam>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun betaTextBlockParams(): Optional<List<BetaTextBlockParam>> =
            Optional.ofNullable(betaTextBlockParams)

        fun isString(): Boolean = string != null

        fun isBetaTextBlockParams(): Boolean = betaTextBlockParams != null

        fun asString(): String = string.getOrThrow("string")

        fun asBetaTextBlockParams(): List<BetaTextBlockParam> =
            betaTextBlockParams.getOrThrow("betaTextBlockParams")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                string != null -> visitor.visitString(string)
                betaTextBlockParams != null -> visitor.visitBetaTextBlockParams(betaTextBlockParams)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): System = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitString(string: String) {}

                    override fun visitBetaTextBlockParams(
                        betaTextBlockParams: List<BetaTextBlockParam>
                    ) {
                        betaTextBlockParams.forEach { it.validate() }
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
                    override fun visitString(string: String) = 1

                    override fun visitBetaTextBlockParams(
                        betaTextBlockParams: List<BetaTextBlockParam>
                    ) = betaTextBlockParams.sumOf { it.validity().toInt() }

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is System &&
                string == other.string &&
                betaTextBlockParams == other.betaTextBlockParams
        }

        override fun hashCode(): Int = Objects.hash(string, betaTextBlockParams)

        override fun toString(): String =
            when {
                string != null -> "System{string=$string}"
                betaTextBlockParams != null -> "System{betaTextBlockParams=$betaTextBlockParams}"
                _json != null -> "System{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid System")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = System(string = string)

            @JvmStatic
            fun ofBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>) =
                System(betaTextBlockParams = betaTextBlockParams.toImmutable())
        }

        /** An interface that defines how to map each variant of [System] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>): T

            /**
             * Maps an unknown variant of [System] to a value of type [T].
             *
             * An instance of [System] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown System: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<System>(System::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): System {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                System(string = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<BetaTextBlockParam>>())?.let {
                                System(betaTextBlockParams = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> System(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<System>(System::class) {

            override fun serialize(
                value: System,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.string != null -> generator.writeObject(value.string)
                    value.betaTextBlockParams != null ->
                        generator.writeObject(value.betaTextBlockParams)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid System")
                }
            }
        }
    }

    /**
     * Configuration for a group of tools from an MCP server.
     *
     * Allows configuring enabled status and defer_loading for all tools from an MCP server, with
     * optional per-tool overrides.
     */
    @JsonDeserialize(using = Tool.Deserializer::class)
    @JsonSerialize(using = Tool.Serializer::class)
    class Tool
    private constructor(
        private val beta: BetaTool? = null,
        private val betaToolBash20241022: BetaToolBash20241022? = null,
        private val betaToolBash20250124: BetaToolBash20250124? = null,
        private val betaCodeExecutionTool20250522: BetaCodeExecutionTool20250522? = null,
        private val betaCodeExecutionTool20250825: BetaCodeExecutionTool20250825? = null,
        private val betaToolComputerUse20241022: BetaToolComputerUse20241022? = null,
        private val betaMemoryTool20250818: BetaMemoryTool20250818? = null,
        private val betaToolComputerUse20250124: BetaToolComputerUse20250124? = null,
        private val betaToolTextEditor20241022: BetaToolTextEditor20241022? = null,
        private val betaToolComputerUse20251124: BetaToolComputerUse20251124? = null,
        private val betaToolTextEditor20250124: BetaToolTextEditor20250124? = null,
        private val betaToolTextEditor20250429: BetaToolTextEditor20250429? = null,
        private val betaToolTextEditor20250728: BetaToolTextEditor20250728? = null,
        private val betaWebSearchTool20250305: BetaWebSearchTool20250305? = null,
        private val betaWebFetchTool20250910: BetaWebFetchTool20250910? = null,
        private val betaToolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119? = null,
        private val betaToolSearchToolRegex20251119: BetaToolSearchToolRegex20251119? = null,
        private val betaMcpToolset: BetaMcpToolset? = null,
        private val _json: JsonValue? = null,
    ) {

        fun beta(): Optional<BetaTool> = Optional.ofNullable(beta)

        fun betaToolBash20241022(): Optional<BetaToolBash20241022> =
            Optional.ofNullable(betaToolBash20241022)

        fun betaToolBash20250124(): Optional<BetaToolBash20250124> =
            Optional.ofNullable(betaToolBash20250124)

        fun betaCodeExecutionTool20250522(): Optional<BetaCodeExecutionTool20250522> =
            Optional.ofNullable(betaCodeExecutionTool20250522)

        fun betaCodeExecutionTool20250825(): Optional<BetaCodeExecutionTool20250825> =
            Optional.ofNullable(betaCodeExecutionTool20250825)

        fun betaToolComputerUse20241022(): Optional<BetaToolComputerUse20241022> =
            Optional.ofNullable(betaToolComputerUse20241022)

        fun betaMemoryTool20250818(): Optional<BetaMemoryTool20250818> =
            Optional.ofNullable(betaMemoryTool20250818)

        fun betaToolComputerUse20250124(): Optional<BetaToolComputerUse20250124> =
            Optional.ofNullable(betaToolComputerUse20250124)

        fun betaToolTextEditor20241022(): Optional<BetaToolTextEditor20241022> =
            Optional.ofNullable(betaToolTextEditor20241022)

        fun betaToolComputerUse20251124(): Optional<BetaToolComputerUse20251124> =
            Optional.ofNullable(betaToolComputerUse20251124)

        fun betaToolTextEditor20250124(): Optional<BetaToolTextEditor20250124> =
            Optional.ofNullable(betaToolTextEditor20250124)

        fun betaToolTextEditor20250429(): Optional<BetaToolTextEditor20250429> =
            Optional.ofNullable(betaToolTextEditor20250429)

        fun betaToolTextEditor20250728(): Optional<BetaToolTextEditor20250728> =
            Optional.ofNullable(betaToolTextEditor20250728)

        fun betaWebSearchTool20250305(): Optional<BetaWebSearchTool20250305> =
            Optional.ofNullable(betaWebSearchTool20250305)

        fun betaWebFetchTool20250910(): Optional<BetaWebFetchTool20250910> =
            Optional.ofNullable(betaWebFetchTool20250910)

        fun betaToolSearchToolBm25_20251119(): Optional<BetaToolSearchToolBm25_20251119> =
            Optional.ofNullable(betaToolSearchToolBm25_20251119)

        fun betaToolSearchToolRegex20251119(): Optional<BetaToolSearchToolRegex20251119> =
            Optional.ofNullable(betaToolSearchToolRegex20251119)

        /**
         * Configuration for a group of tools from an MCP server.
         *
         * Allows configuring enabled status and defer_loading for all tools from an MCP server,
         * with optional per-tool overrides.
         */
        fun betaMcpToolset(): Optional<BetaMcpToolset> = Optional.ofNullable(betaMcpToolset)

        fun isBeta(): Boolean = beta != null

        fun isBetaToolBash20241022(): Boolean = betaToolBash20241022 != null

        fun isBetaToolBash20250124(): Boolean = betaToolBash20250124 != null

        fun isBetaCodeExecutionTool20250522(): Boolean = betaCodeExecutionTool20250522 != null

        fun isBetaCodeExecutionTool20250825(): Boolean = betaCodeExecutionTool20250825 != null

        fun isBetaToolComputerUse20241022(): Boolean = betaToolComputerUse20241022 != null

        fun isBetaMemoryTool20250818(): Boolean = betaMemoryTool20250818 != null

        fun isBetaToolComputerUse20250124(): Boolean = betaToolComputerUse20250124 != null

        fun isBetaToolTextEditor20241022(): Boolean = betaToolTextEditor20241022 != null

        fun isBetaToolComputerUse20251124(): Boolean = betaToolComputerUse20251124 != null

        fun isBetaToolTextEditor20250124(): Boolean = betaToolTextEditor20250124 != null

        fun isBetaToolTextEditor20250429(): Boolean = betaToolTextEditor20250429 != null

        fun isBetaToolTextEditor20250728(): Boolean = betaToolTextEditor20250728 != null

        fun isBetaWebSearchTool20250305(): Boolean = betaWebSearchTool20250305 != null

        fun isBetaWebFetchTool20250910(): Boolean = betaWebFetchTool20250910 != null

        fun isBetaToolSearchToolBm25_20251119(): Boolean = betaToolSearchToolBm25_20251119 != null

        fun isBetaToolSearchToolRegex20251119(): Boolean = betaToolSearchToolRegex20251119 != null

        fun isBetaMcpToolset(): Boolean = betaMcpToolset != null

        fun asBeta(): BetaTool = beta.getOrThrow("beta")

        fun asBetaToolBash20241022(): BetaToolBash20241022 =
            betaToolBash20241022.getOrThrow("betaToolBash20241022")

        fun asBetaToolBash20250124(): BetaToolBash20250124 =
            betaToolBash20250124.getOrThrow("betaToolBash20250124")

        fun asBetaCodeExecutionTool20250522(): BetaCodeExecutionTool20250522 =
            betaCodeExecutionTool20250522.getOrThrow("betaCodeExecutionTool20250522")

        fun asBetaCodeExecutionTool20250825(): BetaCodeExecutionTool20250825 =
            betaCodeExecutionTool20250825.getOrThrow("betaCodeExecutionTool20250825")

        fun asBetaToolComputerUse20241022(): BetaToolComputerUse20241022 =
            betaToolComputerUse20241022.getOrThrow("betaToolComputerUse20241022")

        fun asBetaMemoryTool20250818(): BetaMemoryTool20250818 =
            betaMemoryTool20250818.getOrThrow("betaMemoryTool20250818")

        fun asBetaToolComputerUse20250124(): BetaToolComputerUse20250124 =
            betaToolComputerUse20250124.getOrThrow("betaToolComputerUse20250124")

        fun asBetaToolTextEditor20241022(): BetaToolTextEditor20241022 =
            betaToolTextEditor20241022.getOrThrow("betaToolTextEditor20241022")

        fun asBetaToolComputerUse20251124(): BetaToolComputerUse20251124 =
            betaToolComputerUse20251124.getOrThrow("betaToolComputerUse20251124")

        fun asBetaToolTextEditor20250124(): BetaToolTextEditor20250124 =
            betaToolTextEditor20250124.getOrThrow("betaToolTextEditor20250124")

        fun asBetaToolTextEditor20250429(): BetaToolTextEditor20250429 =
            betaToolTextEditor20250429.getOrThrow("betaToolTextEditor20250429")

        fun asBetaToolTextEditor20250728(): BetaToolTextEditor20250728 =
            betaToolTextEditor20250728.getOrThrow("betaToolTextEditor20250728")

        fun asBetaWebSearchTool20250305(): BetaWebSearchTool20250305 =
            betaWebSearchTool20250305.getOrThrow("betaWebSearchTool20250305")

        fun asBetaWebFetchTool20250910(): BetaWebFetchTool20250910 =
            betaWebFetchTool20250910.getOrThrow("betaWebFetchTool20250910")

        fun asBetaToolSearchToolBm25_20251119(): BetaToolSearchToolBm25_20251119 =
            betaToolSearchToolBm25_20251119.getOrThrow("betaToolSearchToolBm25_20251119")

        fun asBetaToolSearchToolRegex20251119(): BetaToolSearchToolRegex20251119 =
            betaToolSearchToolRegex20251119.getOrThrow("betaToolSearchToolRegex20251119")

        /**
         * Configuration for a group of tools from an MCP server.
         *
         * Allows configuring enabled status and defer_loading for all tools from an MCP server,
         * with optional per-tool overrides.
         */
        fun asBetaMcpToolset(): BetaMcpToolset = betaMcpToolset.getOrThrow("betaMcpToolset")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                beta != null -> visitor.visitBeta(beta)
                betaToolBash20241022 != null ->
                    visitor.visitBetaToolBash20241022(betaToolBash20241022)
                betaToolBash20250124 != null ->
                    visitor.visitBetaToolBash20250124(betaToolBash20250124)
                betaCodeExecutionTool20250522 != null ->
                    visitor.visitBetaCodeExecutionTool20250522(betaCodeExecutionTool20250522)
                betaCodeExecutionTool20250825 != null ->
                    visitor.visitBetaCodeExecutionTool20250825(betaCodeExecutionTool20250825)
                betaToolComputerUse20241022 != null ->
                    visitor.visitBetaToolComputerUse20241022(betaToolComputerUse20241022)
                betaMemoryTool20250818 != null ->
                    visitor.visitBetaMemoryTool20250818(betaMemoryTool20250818)
                betaToolComputerUse20250124 != null ->
                    visitor.visitBetaToolComputerUse20250124(betaToolComputerUse20250124)
                betaToolTextEditor20241022 != null ->
                    visitor.visitBetaToolTextEditor20241022(betaToolTextEditor20241022)
                betaToolComputerUse20251124 != null ->
                    visitor.visitBetaToolComputerUse20251124(betaToolComputerUse20251124)
                betaToolTextEditor20250124 != null ->
                    visitor.visitBetaToolTextEditor20250124(betaToolTextEditor20250124)
                betaToolTextEditor20250429 != null ->
                    visitor.visitBetaToolTextEditor20250429(betaToolTextEditor20250429)
                betaToolTextEditor20250728 != null ->
                    visitor.visitBetaToolTextEditor20250728(betaToolTextEditor20250728)
                betaWebSearchTool20250305 != null ->
                    visitor.visitBetaWebSearchTool20250305(betaWebSearchTool20250305)
                betaWebFetchTool20250910 != null ->
                    visitor.visitBetaWebFetchTool20250910(betaWebFetchTool20250910)
                betaToolSearchToolBm25_20251119 != null ->
                    visitor.visitBetaToolSearchToolBm25_20251119(betaToolSearchToolBm25_20251119)
                betaToolSearchToolRegex20251119 != null ->
                    visitor.visitBetaToolSearchToolRegex20251119(betaToolSearchToolRegex20251119)
                betaMcpToolset != null -> visitor.visitBetaMcpToolset(betaMcpToolset)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Tool = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBeta(beta: BetaTool) {
                        beta.validate()
                    }

                    override fun visitBetaToolBash20241022(
                        betaToolBash20241022: BetaToolBash20241022
                    ) {
                        betaToolBash20241022.validate()
                    }

                    override fun visitBetaToolBash20250124(
                        betaToolBash20250124: BetaToolBash20250124
                    ) {
                        betaToolBash20250124.validate()
                    }

                    override fun visitBetaCodeExecutionTool20250522(
                        betaCodeExecutionTool20250522: BetaCodeExecutionTool20250522
                    ) {
                        betaCodeExecutionTool20250522.validate()
                    }

                    override fun visitBetaCodeExecutionTool20250825(
                        betaCodeExecutionTool20250825: BetaCodeExecutionTool20250825
                    ) {
                        betaCodeExecutionTool20250825.validate()
                    }

                    override fun visitBetaToolComputerUse20241022(
                        betaToolComputerUse20241022: BetaToolComputerUse20241022
                    ) {
                        betaToolComputerUse20241022.validate()
                    }

                    override fun visitBetaMemoryTool20250818(
                        betaMemoryTool20250818: BetaMemoryTool20250818
                    ) {
                        betaMemoryTool20250818.validate()
                    }

                    override fun visitBetaToolComputerUse20250124(
                        betaToolComputerUse20250124: BetaToolComputerUse20250124
                    ) {
                        betaToolComputerUse20250124.validate()
                    }

                    override fun visitBetaToolTextEditor20241022(
                        betaToolTextEditor20241022: BetaToolTextEditor20241022
                    ) {
                        betaToolTextEditor20241022.validate()
                    }

                    override fun visitBetaToolComputerUse20251124(
                        betaToolComputerUse20251124: BetaToolComputerUse20251124
                    ) {
                        betaToolComputerUse20251124.validate()
                    }

                    override fun visitBetaToolTextEditor20250124(
                        betaToolTextEditor20250124: BetaToolTextEditor20250124
                    ) {
                        betaToolTextEditor20250124.validate()
                    }

                    override fun visitBetaToolTextEditor20250429(
                        betaToolTextEditor20250429: BetaToolTextEditor20250429
                    ) {
                        betaToolTextEditor20250429.validate()
                    }

                    override fun visitBetaToolTextEditor20250728(
                        betaToolTextEditor20250728: BetaToolTextEditor20250728
                    ) {
                        betaToolTextEditor20250728.validate()
                    }

                    override fun visitBetaWebSearchTool20250305(
                        betaWebSearchTool20250305: BetaWebSearchTool20250305
                    ) {
                        betaWebSearchTool20250305.validate()
                    }

                    override fun visitBetaWebFetchTool20250910(
                        betaWebFetchTool20250910: BetaWebFetchTool20250910
                    ) {
                        betaWebFetchTool20250910.validate()
                    }

                    override fun visitBetaToolSearchToolBm25_20251119(
                        betaToolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                    ) {
                        betaToolSearchToolBm25_20251119.validate()
                    }

                    override fun visitBetaToolSearchToolRegex20251119(
                        betaToolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                    ) {
                        betaToolSearchToolRegex20251119.validate()
                    }

                    override fun visitBetaMcpToolset(betaMcpToolset: BetaMcpToolset) {
                        betaMcpToolset.validate()
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
                    override fun visitBeta(beta: BetaTool) = beta.validity()

                    override fun visitBetaToolBash20241022(
                        betaToolBash20241022: BetaToolBash20241022
                    ) = betaToolBash20241022.validity()

                    override fun visitBetaToolBash20250124(
                        betaToolBash20250124: BetaToolBash20250124
                    ) = betaToolBash20250124.validity()

                    override fun visitBetaCodeExecutionTool20250522(
                        betaCodeExecutionTool20250522: BetaCodeExecutionTool20250522
                    ) = betaCodeExecutionTool20250522.validity()

                    override fun visitBetaCodeExecutionTool20250825(
                        betaCodeExecutionTool20250825: BetaCodeExecutionTool20250825
                    ) = betaCodeExecutionTool20250825.validity()

                    override fun visitBetaToolComputerUse20241022(
                        betaToolComputerUse20241022: BetaToolComputerUse20241022
                    ) = betaToolComputerUse20241022.validity()

                    override fun visitBetaMemoryTool20250818(
                        betaMemoryTool20250818: BetaMemoryTool20250818
                    ) = betaMemoryTool20250818.validity()

                    override fun visitBetaToolComputerUse20250124(
                        betaToolComputerUse20250124: BetaToolComputerUse20250124
                    ) = betaToolComputerUse20250124.validity()

                    override fun visitBetaToolTextEditor20241022(
                        betaToolTextEditor20241022: BetaToolTextEditor20241022
                    ) = betaToolTextEditor20241022.validity()

                    override fun visitBetaToolComputerUse20251124(
                        betaToolComputerUse20251124: BetaToolComputerUse20251124
                    ) = betaToolComputerUse20251124.validity()

                    override fun visitBetaToolTextEditor20250124(
                        betaToolTextEditor20250124: BetaToolTextEditor20250124
                    ) = betaToolTextEditor20250124.validity()

                    override fun visitBetaToolTextEditor20250429(
                        betaToolTextEditor20250429: BetaToolTextEditor20250429
                    ) = betaToolTextEditor20250429.validity()

                    override fun visitBetaToolTextEditor20250728(
                        betaToolTextEditor20250728: BetaToolTextEditor20250728
                    ) = betaToolTextEditor20250728.validity()

                    override fun visitBetaWebSearchTool20250305(
                        betaWebSearchTool20250305: BetaWebSearchTool20250305
                    ) = betaWebSearchTool20250305.validity()

                    override fun visitBetaWebFetchTool20250910(
                        betaWebFetchTool20250910: BetaWebFetchTool20250910
                    ) = betaWebFetchTool20250910.validity()

                    override fun visitBetaToolSearchToolBm25_20251119(
                        betaToolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                    ) = betaToolSearchToolBm25_20251119.validity()

                    override fun visitBetaToolSearchToolRegex20251119(
                        betaToolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                    ) = betaToolSearchToolRegex20251119.validity()

                    override fun visitBetaMcpToolset(betaMcpToolset: BetaMcpToolset) =
                        betaMcpToolset.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tool &&
                beta == other.beta &&
                betaToolBash20241022 == other.betaToolBash20241022 &&
                betaToolBash20250124 == other.betaToolBash20250124 &&
                betaCodeExecutionTool20250522 == other.betaCodeExecutionTool20250522 &&
                betaCodeExecutionTool20250825 == other.betaCodeExecutionTool20250825 &&
                betaToolComputerUse20241022 == other.betaToolComputerUse20241022 &&
                betaMemoryTool20250818 == other.betaMemoryTool20250818 &&
                betaToolComputerUse20250124 == other.betaToolComputerUse20250124 &&
                betaToolTextEditor20241022 == other.betaToolTextEditor20241022 &&
                betaToolComputerUse20251124 == other.betaToolComputerUse20251124 &&
                betaToolTextEditor20250124 == other.betaToolTextEditor20250124 &&
                betaToolTextEditor20250429 == other.betaToolTextEditor20250429 &&
                betaToolTextEditor20250728 == other.betaToolTextEditor20250728 &&
                betaWebSearchTool20250305 == other.betaWebSearchTool20250305 &&
                betaWebFetchTool20250910 == other.betaWebFetchTool20250910 &&
                betaToolSearchToolBm25_20251119 == other.betaToolSearchToolBm25_20251119 &&
                betaToolSearchToolRegex20251119 == other.betaToolSearchToolRegex20251119 &&
                betaMcpToolset == other.betaMcpToolset
        }

        override fun hashCode(): Int =
            Objects.hash(
                beta,
                betaToolBash20241022,
                betaToolBash20250124,
                betaCodeExecutionTool20250522,
                betaCodeExecutionTool20250825,
                betaToolComputerUse20241022,
                betaMemoryTool20250818,
                betaToolComputerUse20250124,
                betaToolTextEditor20241022,
                betaToolComputerUse20251124,
                betaToolTextEditor20250124,
                betaToolTextEditor20250429,
                betaToolTextEditor20250728,
                betaWebSearchTool20250305,
                betaWebFetchTool20250910,
                betaToolSearchToolBm25_20251119,
                betaToolSearchToolRegex20251119,
                betaMcpToolset,
            )

        override fun toString(): String =
            when {
                beta != null -> "Tool{beta=$beta}"
                betaToolBash20241022 != null -> "Tool{betaToolBash20241022=$betaToolBash20241022}"
                betaToolBash20250124 != null -> "Tool{betaToolBash20250124=$betaToolBash20250124}"
                betaCodeExecutionTool20250522 != null ->
                    "Tool{betaCodeExecutionTool20250522=$betaCodeExecutionTool20250522}"
                betaCodeExecutionTool20250825 != null ->
                    "Tool{betaCodeExecutionTool20250825=$betaCodeExecutionTool20250825}"
                betaToolComputerUse20241022 != null ->
                    "Tool{betaToolComputerUse20241022=$betaToolComputerUse20241022}"
                betaMemoryTool20250818 != null ->
                    "Tool{betaMemoryTool20250818=$betaMemoryTool20250818}"
                betaToolComputerUse20250124 != null ->
                    "Tool{betaToolComputerUse20250124=$betaToolComputerUse20250124}"
                betaToolTextEditor20241022 != null ->
                    "Tool{betaToolTextEditor20241022=$betaToolTextEditor20241022}"
                betaToolComputerUse20251124 != null ->
                    "Tool{betaToolComputerUse20251124=$betaToolComputerUse20251124}"
                betaToolTextEditor20250124 != null ->
                    "Tool{betaToolTextEditor20250124=$betaToolTextEditor20250124}"
                betaToolTextEditor20250429 != null ->
                    "Tool{betaToolTextEditor20250429=$betaToolTextEditor20250429}"
                betaToolTextEditor20250728 != null ->
                    "Tool{betaToolTextEditor20250728=$betaToolTextEditor20250728}"
                betaWebSearchTool20250305 != null ->
                    "Tool{betaWebSearchTool20250305=$betaWebSearchTool20250305}"
                betaWebFetchTool20250910 != null ->
                    "Tool{betaWebFetchTool20250910=$betaWebFetchTool20250910}"
                betaToolSearchToolBm25_20251119 != null ->
                    "Tool{betaToolSearchToolBm25_20251119=$betaToolSearchToolBm25_20251119}"
                betaToolSearchToolRegex20251119 != null ->
                    "Tool{betaToolSearchToolRegex20251119=$betaToolSearchToolRegex20251119}"
                betaMcpToolset != null -> "Tool{betaMcpToolset=$betaMcpToolset}"
                _json != null -> "Tool{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Tool")
            }

        companion object {

            @JvmStatic fun ofBeta(beta: BetaTool) = Tool(beta = beta)

            @JvmStatic
            fun ofBetaToolBash20241022(betaToolBash20241022: BetaToolBash20241022) =
                Tool(betaToolBash20241022 = betaToolBash20241022)

            @JvmStatic
            fun ofBetaToolBash20250124(betaToolBash20250124: BetaToolBash20250124) =
                Tool(betaToolBash20250124 = betaToolBash20250124)

            @JvmStatic
            fun ofBetaCodeExecutionTool20250522(
                betaCodeExecutionTool20250522: BetaCodeExecutionTool20250522
            ) = Tool(betaCodeExecutionTool20250522 = betaCodeExecutionTool20250522)

            @JvmStatic
            fun ofBetaCodeExecutionTool20250825(
                betaCodeExecutionTool20250825: BetaCodeExecutionTool20250825
            ) = Tool(betaCodeExecutionTool20250825 = betaCodeExecutionTool20250825)

            @JvmStatic
            fun ofBetaToolComputerUse20241022(
                betaToolComputerUse20241022: BetaToolComputerUse20241022
            ) = Tool(betaToolComputerUse20241022 = betaToolComputerUse20241022)

            @JvmStatic
            fun ofBetaMemoryTool20250818(betaMemoryTool20250818: BetaMemoryTool20250818) =
                Tool(betaMemoryTool20250818 = betaMemoryTool20250818)

            @JvmStatic
            fun ofBetaToolComputerUse20250124(
                betaToolComputerUse20250124: BetaToolComputerUse20250124
            ) = Tool(betaToolComputerUse20250124 = betaToolComputerUse20250124)

            @JvmStatic
            fun ofBetaToolTextEditor20241022(
                betaToolTextEditor20241022: BetaToolTextEditor20241022
            ) = Tool(betaToolTextEditor20241022 = betaToolTextEditor20241022)

            @JvmStatic
            fun ofBetaToolComputerUse20251124(
                betaToolComputerUse20251124: BetaToolComputerUse20251124
            ) = Tool(betaToolComputerUse20251124 = betaToolComputerUse20251124)

            @JvmStatic
            fun ofBetaToolTextEditor20250124(
                betaToolTextEditor20250124: BetaToolTextEditor20250124
            ) = Tool(betaToolTextEditor20250124 = betaToolTextEditor20250124)

            @JvmStatic
            fun ofBetaToolTextEditor20250429(
                betaToolTextEditor20250429: BetaToolTextEditor20250429
            ) = Tool(betaToolTextEditor20250429 = betaToolTextEditor20250429)

            @JvmStatic
            fun ofBetaToolTextEditor20250728(
                betaToolTextEditor20250728: BetaToolTextEditor20250728
            ) = Tool(betaToolTextEditor20250728 = betaToolTextEditor20250728)

            @JvmStatic
            fun ofBetaWebSearchTool20250305(betaWebSearchTool20250305: BetaWebSearchTool20250305) =
                Tool(betaWebSearchTool20250305 = betaWebSearchTool20250305)

            @JvmStatic
            fun ofBetaWebFetchTool20250910(betaWebFetchTool20250910: BetaWebFetchTool20250910) =
                Tool(betaWebFetchTool20250910 = betaWebFetchTool20250910)

            @JvmStatic
            fun ofBetaToolSearchToolBm25_20251119(
                betaToolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
            ) = Tool(betaToolSearchToolBm25_20251119 = betaToolSearchToolBm25_20251119)

            @JvmStatic
            fun ofBetaToolSearchToolRegex20251119(
                betaToolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
            ) = Tool(betaToolSearchToolRegex20251119 = betaToolSearchToolRegex20251119)

            /**
             * Configuration for a group of tools from an MCP server.
             *
             * Allows configuring enabled status and defer_loading for all tools from an MCP server,
             * with optional per-tool overrides.
             */
            @JvmStatic
            fun ofBetaMcpToolset(betaMcpToolset: BetaMcpToolset) =
                Tool(betaMcpToolset = betaMcpToolset)
        }

        /** An interface that defines how to map each variant of [Tool] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBeta(beta: BetaTool): T

            fun visitBetaToolBash20241022(betaToolBash20241022: BetaToolBash20241022): T

            fun visitBetaToolBash20250124(betaToolBash20250124: BetaToolBash20250124): T

            fun visitBetaCodeExecutionTool20250522(
                betaCodeExecutionTool20250522: BetaCodeExecutionTool20250522
            ): T

            fun visitBetaCodeExecutionTool20250825(
                betaCodeExecutionTool20250825: BetaCodeExecutionTool20250825
            ): T

            fun visitBetaToolComputerUse20241022(
                betaToolComputerUse20241022: BetaToolComputerUse20241022
            ): T

            fun visitBetaMemoryTool20250818(betaMemoryTool20250818: BetaMemoryTool20250818): T

            fun visitBetaToolComputerUse20250124(
                betaToolComputerUse20250124: BetaToolComputerUse20250124
            ): T

            fun visitBetaToolTextEditor20241022(
                betaToolTextEditor20241022: BetaToolTextEditor20241022
            ): T

            fun visitBetaToolComputerUse20251124(
                betaToolComputerUse20251124: BetaToolComputerUse20251124
            ): T

            fun visitBetaToolTextEditor20250124(
                betaToolTextEditor20250124: BetaToolTextEditor20250124
            ): T

            fun visitBetaToolTextEditor20250429(
                betaToolTextEditor20250429: BetaToolTextEditor20250429
            ): T

            fun visitBetaToolTextEditor20250728(
                betaToolTextEditor20250728: BetaToolTextEditor20250728
            ): T

            fun visitBetaWebSearchTool20250305(
                betaWebSearchTool20250305: BetaWebSearchTool20250305
            ): T

            fun visitBetaWebFetchTool20250910(betaWebFetchTool20250910: BetaWebFetchTool20250910): T

            fun visitBetaToolSearchToolBm25_20251119(
                betaToolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
            ): T

            fun visitBetaToolSearchToolRegex20251119(
                betaToolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
            ): T

            /**
             * Configuration for a group of tools from an MCP server.
             *
             * Allows configuring enabled status and defer_loading for all tools from an MCP server,
             * with optional per-tool overrides.
             */
            fun visitBetaMcpToolset(betaMcpToolset: BetaMcpToolset): T

            /**
             * Maps an unknown variant of [Tool] to a value of type [T].
             *
             * An instance of [Tool] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Tool: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Tool>(Tool::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Tool {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<BetaTool>())?.let {
                                Tool(beta = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BetaToolBash20241022>())?.let {
                                Tool(betaToolBash20241022 = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BetaToolBash20250124>())?.let {
                                Tool(betaToolBash20250124 = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20250522>())
                                ?.let { Tool(betaCodeExecutionTool20250522 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20250825>())
                                ?.let { Tool(betaCodeExecutionTool20250825 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20241022>())
                                ?.let { Tool(betaToolComputerUse20241022 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaMemoryTool20250818>())?.let {
                                Tool(betaMemoryTool20250818 = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20250124>())
                                ?.let { Tool(betaToolComputerUse20250124 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20241022>())
                                ?.let { Tool(betaToolTextEditor20241022 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20251124>())
                                ?.let { Tool(betaToolComputerUse20251124 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250124>())
                                ?.let { Tool(betaToolTextEditor20250124 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250429>())
                                ?.let { Tool(betaToolTextEditor20250429 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250728>())
                                ?.let { Tool(betaToolTextEditor20250728 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaWebSearchTool20250305>())?.let {
                                Tool(betaWebSearchTool20250305 = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20250910>())?.let {
                                Tool(betaWebFetchTool20250910 = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BetaToolSearchToolBm25_20251119>())
                                ?.let { Tool(betaToolSearchToolBm25_20251119 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaToolSearchToolRegex20251119>())
                                ?.let { Tool(betaToolSearchToolRegex20251119 = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaMcpToolset>())?.let {
                                Tool(betaMcpToolset = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Tool(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Tool>(Tool::class) {

            override fun serialize(
                value: Tool,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.beta != null -> generator.writeObject(value.beta)
                    value.betaToolBash20241022 != null ->
                        generator.writeObject(value.betaToolBash20241022)
                    value.betaToolBash20250124 != null ->
                        generator.writeObject(value.betaToolBash20250124)
                    value.betaCodeExecutionTool20250522 != null ->
                        generator.writeObject(value.betaCodeExecutionTool20250522)
                    value.betaCodeExecutionTool20250825 != null ->
                        generator.writeObject(value.betaCodeExecutionTool20250825)
                    value.betaToolComputerUse20241022 != null ->
                        generator.writeObject(value.betaToolComputerUse20241022)
                    value.betaMemoryTool20250818 != null ->
                        generator.writeObject(value.betaMemoryTool20250818)
                    value.betaToolComputerUse20250124 != null ->
                        generator.writeObject(value.betaToolComputerUse20250124)
                    value.betaToolTextEditor20241022 != null ->
                        generator.writeObject(value.betaToolTextEditor20241022)
                    value.betaToolComputerUse20251124 != null ->
                        generator.writeObject(value.betaToolComputerUse20251124)
                    value.betaToolTextEditor20250124 != null ->
                        generator.writeObject(value.betaToolTextEditor20250124)
                    value.betaToolTextEditor20250429 != null ->
                        generator.writeObject(value.betaToolTextEditor20250429)
                    value.betaToolTextEditor20250728 != null ->
                        generator.writeObject(value.betaToolTextEditor20250728)
                    value.betaWebSearchTool20250305 != null ->
                        generator.writeObject(value.betaWebSearchTool20250305)
                    value.betaWebFetchTool20250910 != null ->
                        generator.writeObject(value.betaWebFetchTool20250910)
                    value.betaToolSearchToolBm25_20251119 != null ->
                        generator.writeObject(value.betaToolSearchToolBm25_20251119)
                    value.betaToolSearchToolRegex20251119 != null ->
                        generator.writeObject(value.betaToolSearchToolRegex20251119)
                    value.betaMcpToolset != null -> generator.writeObject(value.betaMcpToolset)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Tool")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MessageCountTokensParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "MessageCountTokensParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
