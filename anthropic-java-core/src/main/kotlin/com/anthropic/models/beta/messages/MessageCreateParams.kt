// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
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
 * Send a structured list of input messages with text and/or image content, and the model will
 * generate the next message in the conversation.
 *
 * The Messages API can be used for either single queries or stateless multi-turn conversations.
 *
 * Learn more about the Messages API in our
 * [user guide](https://docs.claude.com/en/docs/initial-setup)
 */
class MessageCreateParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * The maximum number of tokens to generate before stopping.
     *
     * Note that our models may stop _before_ reaching this maximum. This parameter only specifies
     * the absolute maximum number of tokens to generate.
     *
     * Different models have different maximum values for this parameter. See
     * [models](https://docs.claude.com/en/docs/models-overview) for details.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun maxTokens(): Long = body.maxTokens()

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
     * Container identifier for reuse across requests.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun container(): Optional<Container> = body.container()

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
     * Specifies the geographic region for inference processing. If not specified, the workspace's
     * `default_inference_geo` is used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inferenceGeo(): Optional<String> = body.inferenceGeo()

    /**
     * MCP servers to be utilized in this request
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mcpServers(): Optional<List<BetaRequestMcpServerUrlDefinition>> = body.mcpServers()

    /**
     * An object describing metadata about the request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun metadata(): Optional<BetaMetadata> = body.metadata()

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
     * Determines whether to use priority capacity (if available) or standard capacity for this
     * request.
     *
     * Anthropic offers different levels of service for your API requests. See
     * [service-tiers](https://docs.claude.com/en/api/service-tiers) for details.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun serviceTier(): Optional<ServiceTier> = body.serviceTier()

    /**
     * Custom text sequences that will cause the model to stop generating.
     *
     * Our models will normally stop when they have naturally completed their turn, which will
     * result in a response `stop_reason` of `"end_turn"`.
     *
     * If you want the model to stop generating when it encounters custom strings of text, you can
     * use the `stop_sequences` parameter. If the model encounters one of the custom sequences, the
     * response `stop_reason` value will be `"stop_sequence"` and the response `stop_sequence` value
     * will contain the matched stop sequence.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun stopSequences(): Optional<List<String>> = body.stopSequences()

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
     * Amount of randomness injected into the response.
     *
     * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
     * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
     *
     * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun temperature(): Optional<Double> = body.temperature()

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
    fun tools(): Optional<List<BetaToolUnion>> = body.tools()

    /**
     * Only sample from the top K options for each subsequent token.
     *
     * Used to remove "long tail" low probability responses.
     * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun topK(): Optional<Long> = body.topK()

    /**
     * Use nucleus sampling.
     *
     * In nucleus sampling, we compute the cumulative distribution over all the options for each
     * subsequent token in decreasing probability order and cut it off once it reaches a particular
     * probability specified by `top_p`. You should either alter `temperature` or `top_p`, but not
     * both.
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun topP(): Optional<Double> = body.topP()

    /**
     * Returns the raw JSON value of [maxTokens].
     *
     * Unlike [maxTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _maxTokens(): JsonField<Long> = body._maxTokens()

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
     * Returns the raw JSON value of [container].
     *
     * Unlike [container], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _container(): JsonField<Container> = body._container()

    /**
     * Returns the raw JSON value of [contextManagement].
     *
     * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _contextManagement(): JsonField<BetaContextManagementConfig> = body._contextManagement()

    /**
     * Returns the raw JSON value of [inferenceGeo].
     *
     * Unlike [inferenceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _inferenceGeo(): JsonField<String> = body._inferenceGeo()

    /**
     * Returns the raw JSON value of [mcpServers].
     *
     * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _mcpServers(): JsonField<List<BetaRequestMcpServerUrlDefinition>> = body._mcpServers()

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _metadata(): JsonField<BetaMetadata> = body._metadata()

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
     * Returns the raw JSON value of [serviceTier].
     *
     * Unlike [serviceTier], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _serviceTier(): JsonField<ServiceTier> = body._serviceTier()

    /**
     * Returns the raw JSON value of [stopSequences].
     *
     * Unlike [stopSequences], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _stopSequences(): JsonField<List<String>> = body._stopSequences()

    /**
     * Returns the raw JSON value of [system].
     *
     * Unlike [system], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _system(): JsonField<System> = body._system()

    /**
     * Returns the raw JSON value of [temperature].
     *
     * Unlike [temperature], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _temperature(): JsonField<Double> = body._temperature()

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
    fun _tools(): JsonField<List<BetaToolUnion>> = body._tools()

    /**
     * Returns the raw JSON value of [topK].
     *
     * Unlike [topK], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _topK(): JsonField<Long> = body._topK()

    /**
     * Returns the raw JSON value of [topP].
     *
     * Unlike [topP], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _topP(): JsonField<Double> = body._topP()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MessageCreateParams].
         *
         * The following fields are required:
         * ```java
         * .maxTokens()
         * .messages()
         * .model()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MessageCreateParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(messageCreateParams: MessageCreateParams) = apply {
            betas = messageCreateParams.betas?.toMutableList()
            body = messageCreateParams.body.toBuilder()
            additionalHeaders = messageCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = messageCreateParams.additionalQueryParams.toBuilder()
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
         * - [maxTokens]
         * - [messages]
         * - [model]
         * - [container]
         * - [contextManagement]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         *
         * Different models have different maximum values for this parameter. See
         * [models](https://docs.claude.com/en/docs/models-overview) for details.
         */
        fun maxTokens(maxTokens: Long) = apply { body.maxTokens(maxTokens) }

        /**
         * Sets [Builder.maxTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxTokens] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun maxTokens(maxTokens: JsonField<Long>) = apply { body.maxTokens(maxTokens) }

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

        /** Container identifier for reuse across requests. */
        fun container(container: Container?) = apply { body.container(container) }

        /** Alias for calling [Builder.container] with `container.orElse(null)`. */
        fun container(container: Optional<Container>) = container(container.getOrNull())

        /**
         * Sets [Builder.container] to an arbitrary JSON value.
         *
         * You should usually call [Builder.container] with a well-typed [Container] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun container(container: JsonField<Container>) = apply { body.container(container) }

        /**
         * Alias for calling [container] with
         * `Container.ofBetaContainerParams(betaContainerParams)`.
         */
        fun container(betaContainerParams: BetaContainerParams) = apply {
            body.container(betaContainerParams)
        }

        /** Alias for calling [container] with `Container.ofString(string)`. */
        fun container(string: String) = apply { body.container(string) }

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

        /**
         * Specifies the geographic region for inference processing. If not specified, the
         * workspace's `default_inference_geo` is used.
         */
        fun inferenceGeo(inferenceGeo: String?) = apply { body.inferenceGeo(inferenceGeo) }

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
            body.inferenceGeo(inferenceGeo)
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

        /** An object describing metadata about the request. */
        fun metadata(metadata: BetaMetadata) = apply { body.metadata(metadata) }

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [BetaMetadata] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun metadata(metadata: JsonField<BetaMetadata>) = apply { body.metadata(metadata) }

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
         * Determines whether to use priority capacity (if available) or standard capacity for this
         * request.
         *
         * Anthropic offers different levels of service for your API requests. See
         * [service-tiers](https://docs.claude.com/en/api/service-tiers) for details.
         */
        fun serviceTier(serviceTier: ServiceTier) = apply { body.serviceTier(serviceTier) }

        /**
         * Sets [Builder.serviceTier] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serviceTier] with a well-typed [ServiceTier] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun serviceTier(serviceTier: JsonField<ServiceTier>) = apply {
            body.serviceTier(serviceTier)
        }

        /**
         * Custom text sequences that will cause the model to stop generating.
         *
         * Our models will normally stop when they have naturally completed their turn, which will
         * result in a response `stop_reason` of `"end_turn"`.
         *
         * If you want the model to stop generating when it encounters custom strings of text, you
         * can use the `stop_sequences` parameter. If the model encounters one of the custom
         * sequences, the response `stop_reason` value will be `"stop_sequence"` and the response
         * `stop_sequence` value will contain the matched stop sequence.
         */
        fun stopSequences(stopSequences: List<String>) = apply { body.stopSequences(stopSequences) }

        /**
         * Sets [Builder.stopSequences] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopSequences] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
            body.stopSequences(stopSequences)
        }

        /**
         * Adds a single [String] to [stopSequences].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addStopSequence(stopSequence: String) = apply { body.addStopSequence(stopSequence) }

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
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        fun temperature(temperature: Double) = apply { body.temperature(temperature) }

        /**
         * Sets [Builder.temperature] to an arbitrary JSON value.
         *
         * You should usually call [Builder.temperature] with a well-typed [Double] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun temperature(temperature: JsonField<Double>) = apply { body.temperature(temperature) }

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
        fun tools(tools: List<BetaToolUnion>) = apply { body.tools(tools) }

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed `List<BetaToolUnion>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun tools(tools: JsonField<List<BetaToolUnion>>) = apply { body.tools(tools) }

        /**
         * Adds a single [BetaToolUnion] to [tools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTool(tool: BetaToolUnion) = apply { body.addTool(tool) }

        /** Alias for calling [addTool] with `BetaToolUnion.ofBetaTool(betaTool)`. */
        fun addTool(betaTool: BetaTool) = apply { body.addTool(betaTool) }

        /** Alias for calling [addTool] with `BetaToolUnion.ofBash20241022(bash20241022)`. */
        fun addTool(bash20241022: BetaToolBash20241022) = apply { body.addTool(bash20241022) }

        /** Alias for calling [addTool] with `BetaToolUnion.ofBash20250124(bash20250124)`. */
        fun addTool(bash20250124: BetaToolBash20250124) = apply { body.addTool(bash20250124) }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)`.
         */
        fun addTool(codeExecutionTool20250522: BetaCodeExecutionTool20250522) = apply {
            body.addTool(codeExecutionTool20250522)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)`.
         */
        fun addTool(codeExecutionTool20250825: BetaCodeExecutionTool20250825) = apply {
            body.addTool(codeExecutionTool20250825)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofComputerUse20241022(computerUse20241022)`.
         */
        fun addTool(computerUse20241022: BetaToolComputerUse20241022) = apply {
            body.addTool(computerUse20241022)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofMemoryTool20250818(memoryTool20250818)`.
         */
        fun addTool(memoryTool20250818: BetaMemoryTool20250818) = apply {
            body.addTool(memoryTool20250818)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofComputerUse20250124(computerUse20250124)`.
         */
        fun addTool(computerUse20250124: BetaToolComputerUse20250124) = apply {
            body.addTool(computerUse20250124)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofTextEditor20241022(textEditor20241022)`.
         */
        fun addTool(textEditor20241022: BetaToolTextEditor20241022) = apply {
            body.addTool(textEditor20241022)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofComputerUse20251124(computerUse20251124)`.
         */
        fun addTool(computerUse20251124: BetaToolComputerUse20251124) = apply {
            body.addTool(computerUse20251124)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofTextEditor20250124(textEditor20250124)`.
         */
        fun addTool(textEditor20250124: BetaToolTextEditor20250124) = apply {
            body.addTool(textEditor20250124)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofTextEditor20250429(textEditor20250429)`.
         */
        fun addTool(textEditor20250429: BetaToolTextEditor20250429) = apply {
            body.addTool(textEditor20250429)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofTextEditor20250728(textEditor20250728)`.
         */
        fun addTool(textEditor20250728: BetaToolTextEditor20250728) = apply {
            body.addTool(textEditor20250728)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofWebSearchTool20250305(webSearchTool20250305)`.
         */
        fun addTool(webSearchTool20250305: BetaWebSearchTool20250305) = apply {
            body.addTool(webSearchTool20250305)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofWebFetchTool20250910(webFetchTool20250910)`.
         */
        fun addTool(webFetchTool20250910: BetaWebFetchTool20250910) = apply {
            body.addTool(webFetchTool20250910)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofSearchToolBm25_20251119(searchToolBm25_20251119)`.
         */
        fun addTool(searchToolBm25_20251119: BetaToolSearchToolBm25_20251119) = apply {
            body.addTool(searchToolBm25_20251119)
        }

        /**
         * Alias for calling [addTool] with
         * `BetaToolUnion.ofSearchToolRegex20251119(searchToolRegex20251119)`.
         */
        fun addTool(searchToolRegex20251119: BetaToolSearchToolRegex20251119) = apply {
            body.addTool(searchToolRegex20251119)
        }

        /** Alias for calling [addTool] with `BetaToolUnion.ofMcpToolset(mcpToolset)`. */
        fun addTool(mcpToolset: BetaMcpToolset) = apply { body.addTool(mcpToolset) }

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topK(topK: Long) = apply { body.topK(topK) }

        /**
         * Sets [Builder.topK] to an arbitrary JSON value.
         *
         * You should usually call [Builder.topK] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun topK(topK: JsonField<Long>) = apply { body.topK(topK) }

        /**
         * Use nucleus sampling.
         *
         * In nucleus sampling, we compute the cumulative distribution over all the options for each
         * subsequent token in decreasing probability order and cut it off once it reaches a
         * particular probability specified by `top_p`. You should either alter `temperature` or
         * `top_p`, but not both.
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topP(topP: Double) = apply { body.topP(topP) }

        /**
         * Sets [Builder.topP] to an arbitrary JSON value.
         *
         * You should usually call [Builder.topP] with a well-typed [Double] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun topP(topP: JsonField<Double>) = apply { body.topP(topP) }

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
         * Returns an immutable instance of [MessageCreateParams].
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
        fun build(): MessageCreateParams =
            MessageCreateParams(
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
        private val maxTokens: JsonField<Long>,
        private val messages: JsonField<List<BetaMessageParam>>,
        private val model: JsonField<Model>,
        private val container: JsonField<Container>,
        private val contextManagement: JsonField<BetaContextManagementConfig>,
        private val inferenceGeo: JsonField<String>,
        private val mcpServers: JsonField<List<BetaRequestMcpServerUrlDefinition>>,
        private val metadata: JsonField<BetaMetadata>,
        private val outputConfig: JsonField<BetaOutputConfig>,
        private val outputFormat: JsonField<BetaJsonOutputFormat>,
        private val serviceTier: JsonField<ServiceTier>,
        private val stopSequences: JsonField<List<String>>,
        private val system: JsonField<System>,
        private val temperature: JsonField<Double>,
        private val thinking: JsonField<BetaThinkingConfigParam>,
        private val toolChoice: JsonField<BetaToolChoice>,
        private val tools: JsonField<List<BetaToolUnion>>,
        private val topK: JsonField<Long>,
        private val topP: JsonField<Double>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("max_tokens")
            @ExcludeMissing
            maxTokens: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("messages")
            @ExcludeMissing
            messages: JsonField<List<BetaMessageParam>> = JsonMissing.of(),
            @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
            @JsonProperty("container")
            @ExcludeMissing
            container: JsonField<Container> = JsonMissing.of(),
            @JsonProperty("context_management")
            @ExcludeMissing
            contextManagement: JsonField<BetaContextManagementConfig> = JsonMissing.of(),
            @JsonProperty("inference_geo")
            @ExcludeMissing
            inferenceGeo: JsonField<String> = JsonMissing.of(),
            @JsonProperty("mcp_servers")
            @ExcludeMissing
            mcpServers: JsonField<List<BetaRequestMcpServerUrlDefinition>> = JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<BetaMetadata> = JsonMissing.of(),
            @JsonProperty("output_config")
            @ExcludeMissing
            outputConfig: JsonField<BetaOutputConfig> = JsonMissing.of(),
            @JsonProperty("output_format")
            @ExcludeMissing
            outputFormat: JsonField<BetaJsonOutputFormat> = JsonMissing.of(),
            @JsonProperty("service_tier")
            @ExcludeMissing
            serviceTier: JsonField<ServiceTier> = JsonMissing.of(),
            @JsonProperty("stop_sequences")
            @ExcludeMissing
            stopSequences: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("system") @ExcludeMissing system: JsonField<System> = JsonMissing.of(),
            @JsonProperty("temperature")
            @ExcludeMissing
            temperature: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("thinking")
            @ExcludeMissing
            thinking: JsonField<BetaThinkingConfigParam> = JsonMissing.of(),
            @JsonProperty("tool_choice")
            @ExcludeMissing
            toolChoice: JsonField<BetaToolChoice> = JsonMissing.of(),
            @JsonProperty("tools")
            @ExcludeMissing
            tools: JsonField<List<BetaToolUnion>> = JsonMissing.of(),
            @JsonProperty("top_k") @ExcludeMissing topK: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("top_p") @ExcludeMissing topP: JsonField<Double> = JsonMissing.of(),
        ) : this(
            maxTokens,
            messages,
            model,
            container,
            contextManagement,
            inferenceGeo,
            mcpServers,
            metadata,
            outputConfig,
            outputFormat,
            serviceTier,
            stopSequences,
            system,
            temperature,
            thinking,
            toolChoice,
            tools,
            topK,
            topP,
            mutableMapOf(),
        )

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         *
         * Different models have different maximum values for this parameter. See
         * [models](https://docs.claude.com/en/docs/models-overview) for details.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun maxTokens(): Long = maxTokens.getRequired("max_tokens")

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
         * Container identifier for reuse across requests.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun container(): Optional<Container> = container.getOptional("container")

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
         * Specifies the geographic region for inference processing. If not specified, the
         * workspace's `default_inference_geo` is used.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun inferenceGeo(): Optional<String> = inferenceGeo.getOptional("inference_geo")

        /**
         * MCP servers to be utilized in this request
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun mcpServers(): Optional<List<BetaRequestMcpServerUrlDefinition>> =
            mcpServers.getOptional("mcp_servers")

        /**
         * An object describing metadata about the request.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun metadata(): Optional<BetaMetadata> = metadata.getOptional("metadata")

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
         * Determines whether to use priority capacity (if available) or standard capacity for this
         * request.
         *
         * Anthropic offers different levels of service for your API requests. See
         * [service-tiers](https://docs.claude.com/en/api/service-tiers) for details.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun serviceTier(): Optional<ServiceTier> = serviceTier.getOptional("service_tier")

        /**
         * Custom text sequences that will cause the model to stop generating.
         *
         * Our models will normally stop when they have naturally completed their turn, which will
         * result in a response `stop_reason` of `"end_turn"`.
         *
         * If you want the model to stop generating when it encounters custom strings of text, you
         * can use the `stop_sequences` parameter. If the model encounters one of the custom
         * sequences, the response `stop_reason` value will be `"stop_sequence"` and the response
         * `stop_sequence` value will contain the matched stop sequence.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun stopSequences(): Optional<List<String>> = stopSequences.getOptional("stop_sequences")

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
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun temperature(): Optional<Double> = temperature.getOptional("temperature")

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
        fun tools(): Optional<List<BetaToolUnion>> = tools.getOptional("tools")

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun topK(): Optional<Long> = topK.getOptional("top_k")

        /**
         * Use nucleus sampling.
         *
         * In nucleus sampling, we compute the cumulative distribution over all the options for each
         * subsequent token in decreasing probability order and cut it off once it reaches a
         * particular probability specified by `top_p`. You should either alter `temperature` or
         * `top_p`, but not both.
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun topP(): Optional<Double> = topP.getOptional("top_p")

        /**
         * Returns the raw JSON value of [maxTokens].
         *
         * Unlike [maxTokens], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("max_tokens") @ExcludeMissing fun _maxTokens(): JsonField<Long> = maxTokens

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
         * Returns the raw JSON value of [container].
         *
         * Unlike [container], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("container")
        @ExcludeMissing
        fun _container(): JsonField<Container> = container

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
         * Returns the raw JSON value of [inferenceGeo].
         *
         * Unlike [inferenceGeo], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("inference_geo")
        @ExcludeMissing
        fun _inferenceGeo(): JsonField<String> = inferenceGeo

        /**
         * Returns the raw JSON value of [mcpServers].
         *
         * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("mcp_servers")
        @ExcludeMissing
        fun _mcpServers(): JsonField<List<BetaRequestMcpServerUrlDefinition>> = mcpServers

        /**
         * Returns the raw JSON value of [metadata].
         *
         * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("metadata")
        @ExcludeMissing
        fun _metadata(): JsonField<BetaMetadata> = metadata

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
         * Returns the raw JSON value of [serviceTier].
         *
         * Unlike [serviceTier], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("service_tier")
        @ExcludeMissing
        fun _serviceTier(): JsonField<ServiceTier> = serviceTier

        /**
         * Returns the raw JSON value of [stopSequences].
         *
         * Unlike [stopSequences], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("stop_sequences")
        @ExcludeMissing
        fun _stopSequences(): JsonField<List<String>> = stopSequences

        /**
         * Returns the raw JSON value of [system].
         *
         * Unlike [system], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("system") @ExcludeMissing fun _system(): JsonField<System> = system

        /**
         * Returns the raw JSON value of [temperature].
         *
         * Unlike [temperature], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("temperature")
        @ExcludeMissing
        fun _temperature(): JsonField<Double> = temperature

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
        @JsonProperty("tools") @ExcludeMissing fun _tools(): JsonField<List<BetaToolUnion>> = tools

        /**
         * Returns the raw JSON value of [topK].
         *
         * Unlike [topK], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("top_k") @ExcludeMissing fun _topK(): JsonField<Long> = topK

        /**
         * Returns the raw JSON value of [topP].
         *
         * Unlike [topP], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("top_p") @ExcludeMissing fun _topP(): JsonField<Double> = topP

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
             * .maxTokens()
             * .messages()
             * .model()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var maxTokens: JsonField<Long>? = null
            private var messages: JsonField<MutableList<BetaMessageParam>>? = null
            private var model: JsonField<Model>? = null
            private var container: JsonField<Container> = JsonMissing.of()
            private var contextManagement: JsonField<BetaContextManagementConfig> = JsonMissing.of()
            private var inferenceGeo: JsonField<String> = JsonMissing.of()
            private var mcpServers: JsonField<MutableList<BetaRequestMcpServerUrlDefinition>>? =
                null
            private var metadata: JsonField<BetaMetadata> = JsonMissing.of()
            private var outputConfig: JsonField<BetaOutputConfig> = JsonMissing.of()
            private var outputFormat: JsonField<BetaJsonOutputFormat> = JsonMissing.of()
            private var serviceTier: JsonField<ServiceTier> = JsonMissing.of()
            private var stopSequences: JsonField<MutableList<String>>? = null
            private var system: JsonField<System> = JsonMissing.of()
            private var temperature: JsonField<Double> = JsonMissing.of()
            private var thinking: JsonField<BetaThinkingConfigParam> = JsonMissing.of()
            private var toolChoice: JsonField<BetaToolChoice> = JsonMissing.of()
            private var tools: JsonField<MutableList<BetaToolUnion>>? = null
            private var topK: JsonField<Long> = JsonMissing.of()
            private var topP: JsonField<Double> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                maxTokens = body.maxTokens
                messages = body.messages.map { it.toMutableList() }
                model = body.model
                container = body.container
                contextManagement = body.contextManagement
                inferenceGeo = body.inferenceGeo
                mcpServers = body.mcpServers.map { it.toMutableList() }
                metadata = body.metadata
                outputConfig = body.outputConfig
                outputFormat = body.outputFormat
                serviceTier = body.serviceTier
                stopSequences = body.stopSequences.map { it.toMutableList() }
                system = body.system
                temperature = body.temperature
                thinking = body.thinking
                toolChoice = body.toolChoice
                tools = body.tools.map { it.toMutableList() }
                topK = body.topK
                topP = body.topP
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * The maximum number of tokens to generate before stopping.
             *
             * Note that our models may stop _before_ reaching this maximum. This parameter only
             * specifies the absolute maximum number of tokens to generate.
             *
             * Different models have different maximum values for this parameter. See
             * [models](https://docs.claude.com/en/docs/models-overview) for details.
             */
            fun maxTokens(maxTokens: Long) = maxTokens(JsonField.of(maxTokens))

            /**
             * Sets [Builder.maxTokens] to an arbitrary JSON value.
             *
             * You should usually call [Builder.maxTokens] with a well-typed [Long] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun maxTokens(maxTokens: JsonField<Long>) = apply { this.maxTokens = maxTokens }

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

            /** Container identifier for reuse across requests. */
            fun container(container: Container?) = container(JsonField.ofNullable(container))

            /** Alias for calling [Builder.container] with `container.orElse(null)`. */
            fun container(container: Optional<Container>) = container(container.getOrNull())

            /**
             * Sets [Builder.container] to an arbitrary JSON value.
             *
             * You should usually call [Builder.container] with a well-typed [Container] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun container(container: JsonField<Container>) = apply { this.container = container }

            /**
             * Alias for calling [container] with
             * `Container.ofBetaContainerParams(betaContainerParams)`.
             */
            fun container(betaContainerParams: BetaContainerParams) =
                container(Container.ofBetaContainerParams(betaContainerParams))

            /** Alias for calling [container] with `Container.ofString(string)`. */
            fun container(string: String) = container(Container.ofString(string))

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

            /**
             * Specifies the geographic region for inference processing. If not specified, the
             * workspace's `default_inference_geo` is used.
             */
            fun inferenceGeo(inferenceGeo: String?) =
                inferenceGeo(JsonField.ofNullable(inferenceGeo))

            /** Alias for calling [Builder.inferenceGeo] with `inferenceGeo.orElse(null)`. */
            fun inferenceGeo(inferenceGeo: Optional<String>) =
                inferenceGeo(inferenceGeo.getOrNull())

            /**
             * Sets [Builder.inferenceGeo] to an arbitrary JSON value.
             *
             * You should usually call [Builder.inferenceGeo] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun inferenceGeo(inferenceGeo: JsonField<String>) = apply {
                this.inferenceGeo = inferenceGeo
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

            /** An object describing metadata about the request. */
            fun metadata(metadata: BetaMetadata) = metadata(JsonField.of(metadata))

            /**
             * Sets [Builder.metadata] to an arbitrary JSON value.
             *
             * You should usually call [Builder.metadata] with a well-typed [BetaMetadata] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun metadata(metadata: JsonField<BetaMetadata>) = apply { this.metadata = metadata }

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
             * Determines whether to use priority capacity (if available) or standard capacity for
             * this request.
             *
             * Anthropic offers different levels of service for your API requests. See
             * [service-tiers](https://docs.claude.com/en/api/service-tiers) for details.
             */
            fun serviceTier(serviceTier: ServiceTier) = serviceTier(JsonField.of(serviceTier))

            /**
             * Sets [Builder.serviceTier] to an arbitrary JSON value.
             *
             * You should usually call [Builder.serviceTier] with a well-typed [ServiceTier] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun serviceTier(serviceTier: JsonField<ServiceTier>) = apply {
                this.serviceTier = serviceTier
            }

            /**
             * Custom text sequences that will cause the model to stop generating.
             *
             * Our models will normally stop when they have naturally completed their turn, which
             * will result in a response `stop_reason` of `"end_turn"`.
             *
             * If you want the model to stop generating when it encounters custom strings of text,
             * you can use the `stop_sequences` parameter. If the model encounters one of the custom
             * sequences, the response `stop_reason` value will be `"stop_sequence"` and the
             * response `stop_sequence` value will contain the matched stop sequence.
             */
            fun stopSequences(stopSequences: List<String>) =
                stopSequences(JsonField.of(stopSequences))

            /**
             * Sets [Builder.stopSequences] to an arbitrary JSON value.
             *
             * You should usually call [Builder.stopSequences] with a well-typed `List<String>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
                this.stopSequences = stopSequences.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [stopSequences].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addStopSequence(stopSequence: String) = apply {
                stopSequences =
                    (stopSequences ?: JsonField.of(mutableListOf())).also {
                        checkKnown("stopSequences", it).add(stopSequence)
                    }
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
             * Amount of randomness injected into the response.
             *
             * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
             * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
             *
             * Note that even with `temperature` of `0.0`, the results will not be fully
             * deterministic.
             */
            fun temperature(temperature: Double) = temperature(JsonField.of(temperature))

            /**
             * Sets [Builder.temperature] to an arbitrary JSON value.
             *
             * You should usually call [Builder.temperature] with a well-typed [Double] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun temperature(temperature: JsonField<Double>) = apply {
                this.temperature = temperature
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
            fun tools(tools: List<BetaToolUnion>) = tools(JsonField.of(tools))

            /**
             * Sets [Builder.tools] to an arbitrary JSON value.
             *
             * You should usually call [Builder.tools] with a well-typed `List<BetaToolUnion>` value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun tools(tools: JsonField<List<BetaToolUnion>>) = apply {
                this.tools = tools.map { it.toMutableList() }
            }

            /**
             * Adds a single [BetaToolUnion] to [tools].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addTool(tool: BetaToolUnion) = apply {
                tools =
                    (tools ?: JsonField.of(mutableListOf())).also {
                        checkKnown("tools", it).add(tool)
                    }
            }

            /** Alias for calling [addTool] with `BetaToolUnion.ofBetaTool(betaTool)`. */
            fun addTool(betaTool: BetaTool) = addTool(BetaToolUnion.ofBetaTool(betaTool))

            /** Alias for calling [addTool] with `BetaToolUnion.ofBash20241022(bash20241022)`. */
            fun addTool(bash20241022: BetaToolBash20241022) =
                addTool(BetaToolUnion.ofBash20241022(bash20241022))

            /** Alias for calling [addTool] with `BetaToolUnion.ofBash20250124(bash20250124)`. */
            fun addTool(bash20250124: BetaToolBash20250124) =
                addTool(BetaToolUnion.ofBash20250124(bash20250124))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)`.
             */
            fun addTool(codeExecutionTool20250522: BetaCodeExecutionTool20250522) =
                addTool(BetaToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)`.
             */
            fun addTool(codeExecutionTool20250825: BetaCodeExecutionTool20250825) =
                addTool(BetaToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofComputerUse20241022(computerUse20241022)`.
             */
            fun addTool(computerUse20241022: BetaToolComputerUse20241022) =
                addTool(BetaToolUnion.ofComputerUse20241022(computerUse20241022))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofMemoryTool20250818(memoryTool20250818)`.
             */
            fun addTool(memoryTool20250818: BetaMemoryTool20250818) =
                addTool(BetaToolUnion.ofMemoryTool20250818(memoryTool20250818))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofComputerUse20250124(computerUse20250124)`.
             */
            fun addTool(computerUse20250124: BetaToolComputerUse20250124) =
                addTool(BetaToolUnion.ofComputerUse20250124(computerUse20250124))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofTextEditor20241022(textEditor20241022)`.
             */
            fun addTool(textEditor20241022: BetaToolTextEditor20241022) =
                addTool(BetaToolUnion.ofTextEditor20241022(textEditor20241022))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofComputerUse20251124(computerUse20251124)`.
             */
            fun addTool(computerUse20251124: BetaToolComputerUse20251124) =
                addTool(BetaToolUnion.ofComputerUse20251124(computerUse20251124))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofTextEditor20250124(textEditor20250124)`.
             */
            fun addTool(textEditor20250124: BetaToolTextEditor20250124) =
                addTool(BetaToolUnion.ofTextEditor20250124(textEditor20250124))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofTextEditor20250429(textEditor20250429)`.
             */
            fun addTool(textEditor20250429: BetaToolTextEditor20250429) =
                addTool(BetaToolUnion.ofTextEditor20250429(textEditor20250429))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofTextEditor20250728(textEditor20250728)`.
             */
            fun addTool(textEditor20250728: BetaToolTextEditor20250728) =
                addTool(BetaToolUnion.ofTextEditor20250728(textEditor20250728))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofWebSearchTool20250305(webSearchTool20250305)`.
             */
            fun addTool(webSearchTool20250305: BetaWebSearchTool20250305) =
                addTool(BetaToolUnion.ofWebSearchTool20250305(webSearchTool20250305))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofWebFetchTool20250910(webFetchTool20250910)`.
             */
            fun addTool(webFetchTool20250910: BetaWebFetchTool20250910) =
                addTool(BetaToolUnion.ofWebFetchTool20250910(webFetchTool20250910))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofSearchToolBm25_20251119(searchToolBm25_20251119)`.
             */
            fun addTool(searchToolBm25_20251119: BetaToolSearchToolBm25_20251119) =
                addTool(BetaToolUnion.ofSearchToolBm25_20251119(searchToolBm25_20251119))

            /**
             * Alias for calling [addTool] with
             * `BetaToolUnion.ofSearchToolRegex20251119(searchToolRegex20251119)`.
             */
            fun addTool(searchToolRegex20251119: BetaToolSearchToolRegex20251119) =
                addTool(BetaToolUnion.ofSearchToolRegex20251119(searchToolRegex20251119))

            /** Alias for calling [addTool] with `BetaToolUnion.ofMcpToolset(mcpToolset)`. */
            fun addTool(mcpToolset: BetaMcpToolset) =
                addTool(BetaToolUnion.ofMcpToolset(mcpToolset))

            /**
             * Only sample from the top K options for each subsequent token.
             *
             * Used to remove "long tail" low probability responses.
             * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            fun topK(topK: Long) = topK(JsonField.of(topK))

            /**
             * Sets [Builder.topK] to an arbitrary JSON value.
             *
             * You should usually call [Builder.topK] with a well-typed [Long] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun topK(topK: JsonField<Long>) = apply { this.topK = topK }

            /**
             * Use nucleus sampling.
             *
             * In nucleus sampling, we compute the cumulative distribution over all the options for
             * each subsequent token in decreasing probability order and cut it off once it reaches
             * a particular probability specified by `top_p`. You should either alter `temperature`
             * or `top_p`, but not both.
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            fun topP(topP: Double) = topP(JsonField.of(topP))

            /**
             * Sets [Builder.topP] to an arbitrary JSON value.
             *
             * You should usually call [Builder.topP] with a well-typed [Double] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun topP(topP: JsonField<Double>) = apply { this.topP = topP }

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
             * .maxTokens()
             * .messages()
             * .model()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("maxTokens", maxTokens),
                    checkRequired("messages", messages).map { it.toImmutable() },
                    checkRequired("model", model),
                    container,
                    contextManagement,
                    inferenceGeo,
                    (mcpServers ?: JsonMissing.of()).map { it.toImmutable() },
                    metadata,
                    outputConfig,
                    outputFormat,
                    serviceTier,
                    (stopSequences ?: JsonMissing.of()).map { it.toImmutable() },
                    system,
                    temperature,
                    thinking,
                    toolChoice,
                    (tools ?: JsonMissing.of()).map { it.toImmutable() },
                    topK,
                    topP,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            maxTokens()
            messages().forEach { it.validate() }
            model()
            container().ifPresent { it.validate() }
            contextManagement().ifPresent { it.validate() }
            inferenceGeo()
            mcpServers().ifPresent { it.forEach { it.validate() } }
            metadata().ifPresent { it.validate() }
            outputConfig().ifPresent { it.validate() }
            outputFormat().ifPresent { it.validate() }
            serviceTier().ifPresent { it.validate() }
            stopSequences()
            system().ifPresent { it.validate() }
            temperature()
            thinking().ifPresent { it.validate() }
            toolChoice().ifPresent { it.validate() }
            tools().ifPresent { it.forEach { it.validate() } }
            topK()
            topP()
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
            (if (maxTokens.asKnown().isPresent) 1 else 0) +
                (messages.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (if (model.asKnown().isPresent) 1 else 0) +
                (container.asKnown().getOrNull()?.validity() ?: 0) +
                (contextManagement.asKnown().getOrNull()?.validity() ?: 0) +
                (if (inferenceGeo.asKnown().isPresent) 1 else 0) +
                (mcpServers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (metadata.asKnown().getOrNull()?.validity() ?: 0) +
                (outputConfig.asKnown().getOrNull()?.validity() ?: 0) +
                (outputFormat.asKnown().getOrNull()?.validity() ?: 0) +
                (serviceTier.asKnown().getOrNull()?.validity() ?: 0) +
                (stopSequences.asKnown().getOrNull()?.size ?: 0) +
                (system.asKnown().getOrNull()?.validity() ?: 0) +
                (if (temperature.asKnown().isPresent) 1 else 0) +
                (thinking.asKnown().getOrNull()?.validity() ?: 0) +
                (toolChoice.asKnown().getOrNull()?.validity() ?: 0) +
                (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (if (topK.asKnown().isPresent) 1 else 0) +
                (if (topP.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                maxTokens == other.maxTokens &&
                messages == other.messages &&
                model == other.model &&
                container == other.container &&
                contextManagement == other.contextManagement &&
                inferenceGeo == other.inferenceGeo &&
                mcpServers == other.mcpServers &&
                metadata == other.metadata &&
                outputConfig == other.outputConfig &&
                outputFormat == other.outputFormat &&
                serviceTier == other.serviceTier &&
                stopSequences == other.stopSequences &&
                system == other.system &&
                temperature == other.temperature &&
                thinking == other.thinking &&
                toolChoice == other.toolChoice &&
                tools == other.tools &&
                topK == other.topK &&
                topP == other.topP &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                maxTokens,
                messages,
                model,
                container,
                contextManagement,
                inferenceGeo,
                mcpServers,
                metadata,
                outputConfig,
                outputFormat,
                serviceTier,
                stopSequences,
                system,
                temperature,
                thinking,
                toolChoice,
                tools,
                topK,
                topP,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{maxTokens=$maxTokens, messages=$messages, model=$model, container=$container, contextManagement=$contextManagement, inferenceGeo=$inferenceGeo, mcpServers=$mcpServers, metadata=$metadata, outputConfig=$outputConfig, outputFormat=$outputFormat, serviceTier=$serviceTier, stopSequences=$stopSequences, system=$system, temperature=$temperature, thinking=$thinking, toolChoice=$toolChoice, tools=$tools, topK=$topK, topP=$topP, additionalProperties=$additionalProperties}"
    }

    /** Container identifier for reuse across requests. */
    @JsonDeserialize(using = Container.Deserializer::class)
    @JsonSerialize(using = Container.Serializer::class)
    class Container
    private constructor(
        private val betaContainerParams: BetaContainerParams? = null,
        private val string: String? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Container parameters with skills to be loaded. */
        fun betaContainerParams(): Optional<BetaContainerParams> =
            Optional.ofNullable(betaContainerParams)

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun isBetaContainerParams(): Boolean = betaContainerParams != null

        fun isString(): Boolean = string != null

        /** Container parameters with skills to be loaded. */
        fun asBetaContainerParams(): BetaContainerParams =
            betaContainerParams.getOrThrow("betaContainerParams")

        fun asString(): String = string.getOrThrow("string")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                betaContainerParams != null -> visitor.visitBetaContainerParams(betaContainerParams)
                string != null -> visitor.visitString(string)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Container = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBetaContainerParams(
                        betaContainerParams: BetaContainerParams
                    ) {
                        betaContainerParams.validate()
                    }

                    override fun visitString(string: String) {}
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
                    override fun visitBetaContainerParams(
                        betaContainerParams: BetaContainerParams
                    ) = betaContainerParams.validity()

                    override fun visitString(string: String) = 1

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Container &&
                betaContainerParams == other.betaContainerParams &&
                string == other.string
        }

        override fun hashCode(): Int = Objects.hash(betaContainerParams, string)

        override fun toString(): String =
            when {
                betaContainerParams != null -> "Container{betaContainerParams=$betaContainerParams}"
                string != null -> "Container{string=$string}"
                _json != null -> "Container{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Container")
            }

        companion object {

            /** Container parameters with skills to be loaded. */
            @JvmStatic
            fun ofBetaContainerParams(betaContainerParams: BetaContainerParams) =
                Container(betaContainerParams = betaContainerParams)

            @JvmStatic fun ofString(string: String) = Container(string = string)
        }

        /**
         * An interface that defines how to map each variant of [Container] to a value of type [T].
         */
        interface Visitor<out T> {

            /** Container parameters with skills to be loaded. */
            fun visitBetaContainerParams(betaContainerParams: BetaContainerParams): T

            fun visitString(string: String): T

            /**
             * Maps an unknown variant of [Container] to a value of type [T].
             *
             * An instance of [Container] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Container: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Container>(Container::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Container {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<BetaContainerParams>())?.let {
                                Container(betaContainerParams = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Container(string = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Container(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Container>(Container::class) {

            override fun serialize(
                value: Container,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.betaContainerParams != null ->
                        generator.writeObject(value.betaContainerParams)
                    value.string != null -> generator.writeObject(value.string)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Container")
                }
            }
        }
    }

    /**
     * Determines whether to use priority capacity (if available) or standard capacity for this
     * request.
     *
     * Anthropic offers different levels of service for your API requests. See
     * [service-tiers](https://docs.claude.com/en/api/service-tiers) for details.
     */
    class ServiceTier @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

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

            @JvmField val AUTO = of("auto")

            @JvmField val STANDARD_ONLY = of("standard_only")

            @JvmStatic fun of(value: String) = ServiceTier(JsonField.of(value))
        }

        /** An enum containing [ServiceTier]'s known values. */
        enum class Known {
            AUTO,
            STANDARD_ONLY,
        }

        /**
         * An enum containing [ServiceTier]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ServiceTier] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            AUTO,
            STANDARD_ONLY,
            /**
             * An enum member indicating that [ServiceTier] was instantiated with an unknown value.
             */
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
                AUTO -> Value.AUTO
                STANDARD_ONLY -> Value.STANDARD_ONLY
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
                AUTO -> Known.AUTO
                STANDARD_ONLY -> Known.STANDARD_ONLY
                else -> throw AnthropicInvalidDataException("Unknown ServiceTier: $value")
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

        fun validate(): ServiceTier = apply {
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

            return other is ServiceTier && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MessageCreateParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "MessageCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
