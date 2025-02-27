// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
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
import java.util.Objects
import java.util.Optional

/**
 * Send a structured list of input messages with text and/or image content, and the model will
 * generate the next message in the conversation.
 *
 * The Messages API can be used for either single queries or stateless multi-turn conversations.
 *
 * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
 */
class BetaMessageCreateParams
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
     * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
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
     * [{ "role": "user", "content": "Hello, Claude" }]
     * ```
     *
     * Example with multiple conversational turns:
     * ```json
     * [
     *   { "role": "user", "content": "Hello there." },
     *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
     *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
     * ]
     * ```
     *
     * Example with a partially-filled response from Claude:
     * ```json
     * [
     *   {
     *     "role": "user",
     *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
     *   },
     *   { "role": "assistant", "content": "The best answer is (" }
     * ]
     * ```
     *
     * Each input message `content` may be either a single `string` or an array of content blocks,
     * where each block has a specific `type`. Using a `string` for `content` is shorthand for an
     * array of one content block of type `"text"`. The following input messages are equivalent:
     * ```json
     * { "role": "user", "content": "Hello, Claude" }
     * ```
     * ```json
     * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
     * ```
     *
     * Starting with Claude 3 models, you can also send image content blocks:
     * ```json
     * {
     *   "role": "user",
     *   "content": [
     *     {
     *       "type": "image",
     *       "source": {
     *         "type": "base64",
     *         "media_type": "image/jpeg",
     *         "data": "/9j/4AAQSkZJRg..."
     *       }
     *     },
     *     { "type": "text", "text": "What is in this image?" }
     *   ]
     * }
     * ```
     *
     * We currently support the `base64` source type for images, and the `image/jpeg`, `image/png`,
     * `image/gif`, and `image/webp` media types.
     *
     * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
     * examples.
     *
     * Note that if you want to include a
     * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the top-level
     * `system` parameter — there is no `"system"` role for input messages in the Messages API.
     */
    fun messages(): List<BetaMessageParam> = body.messages()

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     */
    fun model(): Model = body.model()

    /** An object describing metadata about the request. */
    fun metadata(): Optional<BetaMetadata> = body.metadata()

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
     */
    fun stopSequences(): Optional<List<String>> = body.stopSequences()

    /**
     * System prompt.
     *
     * A system prompt is a way of providing context and instructions to Claude, such as specifying
     * a particular goal or role. See our
     * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
     */
    fun system(): Optional<System> = body.system()

    /**
     * Amount of randomness injected into the response.
     *
     * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
     * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
     *
     * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
     */
    fun temperature(): Optional<Double> = body.temperature()

    /**
     * Configuration for enabling Claude's extended thinking.
     *
     * When enabled, responses include `thinking` content blocks showing Claude's thinking process
     * before the final answer. Requires a minimum budget of 1,024 tokens and counts towards your
     * `max_tokens` limit.
     *
     * See
     * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
     * for details.
     */
    fun thinking(): Optional<BetaThinkingConfigParam> = body.thinking()

    /**
     * How the model should use the provided tools. The model can use a specific tool, any available
     * tool, or decide by itself.
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
     * Each tool definition includes:
     * - `name`: Name of the tool.
     * - `description`: Optional, but strongly-recommended description of the tool.
     * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool `input`
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
     * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
     */
    fun tools(): Optional<List<BetaToolUnion>> = body.tools()

    /**
     * Only sample from the top K options for each subsequent token.
     *
     * Used to remove "long tail" low probability responses.
     * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
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
     */
    fun topP(): Optional<Double> = body.topP()

    /**
     * The maximum number of tokens to generate before stopping.
     *
     * Note that our models may stop _before_ reaching this maximum. This parameter only specifies
     * the absolute maximum number of tokens to generate.
     *
     * Different models have different maximum values for this parameter. See
     * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
     */
    fun _maxTokens(): JsonField<Long> = body._maxTokens()

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
     * [{ "role": "user", "content": "Hello, Claude" }]
     * ```
     *
     * Example with multiple conversational turns:
     * ```json
     * [
     *   { "role": "user", "content": "Hello there." },
     *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
     *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
     * ]
     * ```
     *
     * Example with a partially-filled response from Claude:
     * ```json
     * [
     *   {
     *     "role": "user",
     *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
     *   },
     *   { "role": "assistant", "content": "The best answer is (" }
     * ]
     * ```
     *
     * Each input message `content` may be either a single `string` or an array of content blocks,
     * where each block has a specific `type`. Using a `string` for `content` is shorthand for an
     * array of one content block of type `"text"`. The following input messages are equivalent:
     * ```json
     * { "role": "user", "content": "Hello, Claude" }
     * ```
     * ```json
     * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
     * ```
     *
     * Starting with Claude 3 models, you can also send image content blocks:
     * ```json
     * {
     *   "role": "user",
     *   "content": [
     *     {
     *       "type": "image",
     *       "source": {
     *         "type": "base64",
     *         "media_type": "image/jpeg",
     *         "data": "/9j/4AAQSkZJRg..."
     *       }
     *     },
     *     { "type": "text", "text": "What is in this image?" }
     *   ]
     * }
     * ```
     *
     * We currently support the `base64` source type for images, and the `image/jpeg`, `image/png`,
     * `image/gif`, and `image/webp` media types.
     *
     * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
     * examples.
     *
     * Note that if you want to include a
     * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the top-level
     * `system` parameter — there is no `"system"` role for input messages in the Messages API.
     */
    fun _messages(): JsonField<List<BetaMessageParam>> = body._messages()

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     */
    fun _model(): JsonField<Model> = body._model()

    /** An object describing metadata about the request. */
    fun _metadata(): JsonField<BetaMetadata> = body._metadata()

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
     */
    fun _stopSequences(): JsonField<List<String>> = body._stopSequences()

    /**
     * System prompt.
     *
     * A system prompt is a way of providing context and instructions to Claude, such as specifying
     * a particular goal or role. See our
     * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
     */
    fun _system(): JsonField<System> = body._system()

    /**
     * Amount of randomness injected into the response.
     *
     * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
     * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
     *
     * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
     */
    fun _temperature(): JsonField<Double> = body._temperature()

    /**
     * Configuration for enabling Claude's extended thinking.
     *
     * When enabled, responses include `thinking` content blocks showing Claude's thinking process
     * before the final answer. Requires a minimum budget of 1,024 tokens and counts towards your
     * `max_tokens` limit.
     *
     * See
     * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
     * for details.
     */
    fun _thinking(): JsonField<BetaThinkingConfigParam> = body._thinking()

    /**
     * How the model should use the provided tools. The model can use a specific tool, any available
     * tool, or decide by itself.
     */
    fun _toolChoice(): JsonField<BetaToolChoice> = body._toolChoice()

    /**
     * Definitions of tools that the model may use.
     *
     * If you include `tools` in your API request, the model may return `tool_use` content blocks
     * that represent the model's use of those tools. You can then run those tools using the tool
     * input generated by the model and then optionally return results back to the model using
     * `tool_result` content blocks.
     *
     * Each tool definition includes:
     * - `name`: Name of the tool.
     * - `description`: Optional, but strongly-recommended description of the tool.
     * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool `input`
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
     * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
     */
    fun _tools(): JsonField<List<BetaToolUnion>> = body._tools()

    /**
     * Only sample from the top K options for each subsequent token.
     *
     * Used to remove "long tail" low probability responses.
     * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
     */
    fun _topK(): JsonField<Long> = body._topK()

    /**
     * Use nucleus sampling.
     *
     * In nucleus sampling, we compute the cumulative distribution over all the options for each
     * subsequent token in decreasing probability order and cut it off once it reaches a particular
     * probability specified by `top_p`. You should either alter `temperature` or `top_p`, but not
     * both.
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
     */
    fun _topP(): JsonField<Double> = body._topP()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    @JvmSynthetic internal fun _body(): Body = body

    override fun _headers(): Headers {
        val headers = Headers.builder()
        this.betas?.let { headers.put("anthropic-beta", it.map(Any::toString)) }
        headers.putAll(additionalHeaders)
        return headers.build()
    }

    override fun _queryParams(): QueryParams = additionalQueryParams

    @NoAutoDetect
    class Body
    @JsonCreator
    private constructor(
        @JsonProperty("max_tokens")
        @ExcludeMissing
        private val maxTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("messages")
        @ExcludeMissing
        private val messages: JsonField<List<BetaMessageParam>> = JsonMissing.of(),
        @JsonProperty("model")
        @ExcludeMissing
        private val model: JsonField<Model> = JsonMissing.of(),
        @JsonProperty("metadata")
        @ExcludeMissing
        private val metadata: JsonField<BetaMetadata> = JsonMissing.of(),
        @JsonProperty("stop_sequences")
        @ExcludeMissing
        private val stopSequences: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("system")
        @ExcludeMissing
        private val system: JsonField<System> = JsonMissing.of(),
        @JsonProperty("temperature")
        @ExcludeMissing
        private val temperature: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("thinking")
        @ExcludeMissing
        private val thinking: JsonField<BetaThinkingConfigParam> = JsonMissing.of(),
        @JsonProperty("tool_choice")
        @ExcludeMissing
        private val toolChoice: JsonField<BetaToolChoice> = JsonMissing.of(),
        @JsonProperty("tools")
        @ExcludeMissing
        private val tools: JsonField<List<BetaToolUnion>> = JsonMissing.of(),
        @JsonProperty("top_k") @ExcludeMissing private val topK: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("top_p")
        @ExcludeMissing
        private val topP: JsonField<Double> = JsonMissing.of(),
        @JsonAnySetter
        private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
    ) {

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         *
         * Different models have different maximum values for this parameter. See
         * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun messages(): List<BetaMessageParam> = messages.getRequired("messages")

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(): Model = model.getRequired("model")

        /** An object describing metadata about the request. */
        fun metadata(): Optional<BetaMetadata> =
            Optional.ofNullable(metadata.getNullable("metadata"))

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
        fun stopSequences(): Optional<List<String>> =
            Optional.ofNullable(stopSequences.getNullable("stop_sequences"))

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        fun system(): Optional<System> = Optional.ofNullable(system.getNullable("system"))

        /**
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        fun temperature(): Optional<Double> =
            Optional.ofNullable(temperature.getNullable("temperature"))

        /**
         * Configuration for enabling Claude's extended thinking.
         *
         * When enabled, responses include `thinking` content blocks showing Claude's thinking
         * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
         * towards your `max_tokens` limit.
         *
         * See
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun thinking(): Optional<BetaThinkingConfigParam> =
            Optional.ofNullable(thinking.getNullable("thinking"))

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(): Optional<BetaToolChoice> =
            Optional.ofNullable(toolChoice.getNullable("tool_choice"))

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun tools(): Optional<List<BetaToolUnion>> = Optional.ofNullable(tools.getNullable("tools"))

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topK(): Optional<Long> = Optional.ofNullable(topK.getNullable("top_k"))

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
        fun topP(): Optional<Double> = Optional.ofNullable(topP.getNullable("top_p"))

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         *
         * Different models have different maximum values for this parameter. See
         * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
         */
        @JsonProperty("max_tokens") @ExcludeMissing fun _maxTokens(): JsonField<Long> = maxTokens

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        @JsonProperty("messages")
        @ExcludeMissing
        fun _messages(): JsonField<List<BetaMessageParam>> = messages

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

        /** An object describing metadata about the request. */
        @JsonProperty("metadata")
        @ExcludeMissing
        fun _metadata(): JsonField<BetaMetadata> = metadata

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
        @JsonProperty("stop_sequences")
        @ExcludeMissing
        fun _stopSequences(): JsonField<List<String>> = stopSequences

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        @JsonProperty("system") @ExcludeMissing fun _system(): JsonField<System> = system

        /**
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        @JsonProperty("temperature")
        @ExcludeMissing
        fun _temperature(): JsonField<Double> = temperature

        /**
         * Configuration for enabling Claude's extended thinking.
         *
         * When enabled, responses include `thinking` content blocks showing Claude's thinking
         * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
         * towards your `max_tokens` limit.
         *
         * See
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        @JsonProperty("thinking")
        @ExcludeMissing
        fun _thinking(): JsonField<BetaThinkingConfigParam> = thinking

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        @JsonProperty("tool_choice")
        @ExcludeMissing
        fun _toolChoice(): JsonField<BetaToolChoice> = toolChoice

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        @JsonProperty("tools") @ExcludeMissing fun _tools(): JsonField<List<BetaToolUnion>> = tools

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        @JsonProperty("top_k") @ExcludeMissing fun _topK(): JsonField<Long> = topK

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
        @JsonProperty("top_p") @ExcludeMissing fun _topP(): JsonField<Double> = topP

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            maxTokens()
            messages().forEach { it.validate() }
            model()
            metadata().ifPresent { it.validate() }
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

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var maxTokens: JsonField<Long>? = null
            private var messages: JsonField<MutableList<BetaMessageParam>>? = null
            private var model: JsonField<Model>? = null
            private var metadata: JsonField<BetaMetadata> = JsonMissing.of()
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
                metadata = body.metadata
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
             * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
             */
            fun maxTokens(maxTokens: Long) = maxTokens(JsonField.of(maxTokens))

            /**
             * The maximum number of tokens to generate before stopping.
             *
             * Note that our models may stop _before_ reaching this maximum. This parameter only
             * specifies the absolute maximum number of tokens to generate.
             *
             * Different models have different maximum values for this parameter. See
             * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun messages(messages: List<BetaMessageParam>) = messages(JsonField.of(messages))

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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun messages(messages: JsonField<List<BetaMessageParam>>) = apply {
                this.messages = messages.map { it.toMutableList() }
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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun addMessage(message: BetaMessageParam) = apply {
                messages =
                    (messages ?: JsonField.of(mutableListOf())).apply {
                        asKnown()
                            .orElseThrow {
                                IllegalStateException(
                                    "Field was set to non-list type: ${javaClass.simpleName}"
                                )
                            }
                            .add(message)
                    }
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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun addMessage(message: BetaMessage) = addMessage(message.toParam())

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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun addUserMessage(content: BetaMessageParam.Content) =
                addMessage(
                    BetaMessageParam.builder()
                        .role(BetaMessageParam.Role.USER)
                        .content(content)
                        .build()
                )

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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun addUserMessage(string: String) =
                addUserMessage(BetaMessageParam.Content.ofString(string))

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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun addUserMessageOfBetaContentBlockParams(
                betaContentBlockParams: List<BetaContentBlockParam>
            ) =
                addUserMessage(
                    BetaMessageParam.Content.ofBetaContentBlockParams(betaContentBlockParams)
                )

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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun addAssistantMessage(content: BetaMessageParam.Content) =
                addMessage(
                    BetaMessageParam.builder()
                        .role(BetaMessageParam.Role.USER)
                        .content(content)
                        .build()
                )

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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
             */
            fun addAssistantMessage(string: String) =
                addAssistantMessage(BetaMessageParam.Content.ofString(string))

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
             * [{ "role": "user", "content": "Hello, Claude" }]
             * ```
             *
             * Example with multiple conversational turns:
             * ```json
             * [
             *   { "role": "user", "content": "Hello there." },
             *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
             *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
             * ]
             * ```
             *
             * Example with a partially-filled response from Claude:
             * ```json
             * [
             *   {
             *     "role": "user",
             *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
             *   },
             *   { "role": "assistant", "content": "The best answer is (" }
             * ]
             * ```
             *
             * Each input message `content` may be either a single `string` or an array of content
             * blocks, where each block has a specific `type`. Using a `string` for `content` is
             * shorthand for an array of one content block of type `"text"`. The following input
             * messages are equivalent:
             * ```json
             * { "role": "user", "content": "Hello, Claude" }
             * ```
             * ```json
             * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
             * ```
             *
             * Starting with Claude 3 models, you can also send image content blocks:
             * ```json
             * {
             *   "role": "user",
             *   "content": [
             *     {
             *       "type": "image",
             *       "source": {
             *         "type": "base64",
             *         "media_type": "image/jpeg",
             *         "data": "/9j/4AAQSkZJRg..."
             *       }
             *     },
             *     { "type": "text", "text": "What is in this image?" }
             *   ]
             * }
             * ```
             *
             * We currently support the `base64` source type for images, and the `image/jpeg`,
             * `image/png`, `image/gif`, and `image/webp` media types.
             *
             * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more
             * input examples.
             *
             * Note that if you want to include a
             * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
             * top-level `system` parameter — there is no `"system"` role for input messages in the
             * Messages API.
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
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            fun model(model: JsonField<Model>) = apply { this.model = model }

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            fun model(value: String) = model(Model.of(value))

            /** An object describing metadata about the request. */
            fun metadata(metadata: BetaMetadata) = metadata(JsonField.of(metadata))

            /** An object describing metadata about the request. */
            fun metadata(metadata: JsonField<BetaMetadata>) = apply { this.metadata = metadata }

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
            fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
                this.stopSequences = stopSequences.map { it.toMutableList() }
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
            fun addStopSequence(stopSequence: String) = apply {
                stopSequences =
                    (stopSequences ?: JsonField.of(mutableListOf())).apply {
                        asKnown()
                            .orElseThrow {
                                IllegalStateException(
                                    "Field was set to non-list type: ${javaClass.simpleName}"
                                )
                            }
                            .add(stopSequence)
                    }
            }

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
             */
            fun system(system: System) = system(JsonField.of(system))

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
             */
            fun system(system: JsonField<System>) = apply { this.system = system }

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
             */
            fun system(string: String) = system(System.ofString(string))

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
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
             * Amount of randomness injected into the response.
             *
             * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
             * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
             *
             * Note that even with `temperature` of `0.0`, the results will not be fully
             * deterministic.
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
             * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
             * for details.
             */
            fun thinking(thinking: BetaThinkingConfigParam) = thinking(JsonField.of(thinking))

            /**
             * Configuration for enabling Claude's extended thinking.
             *
             * When enabled, responses include `thinking` content blocks showing Claude's thinking
             * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
             * towards your `max_tokens` limit.
             *
             * See
             * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
             * for details.
             */
            fun thinking(thinking: JsonField<BetaThinkingConfigParam>) = apply {
                this.thinking = thinking
            }

            /**
             * Configuration for enabling Claude's extended thinking.
             *
             * When enabled, responses include `thinking` content blocks showing Claude's thinking
             * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
             * towards your `max_tokens` limit.
             *
             * See
             * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
             * for details.
             */
            fun thinking(enabled: BetaThinkingConfigEnabled) =
                thinking(BetaThinkingConfigParam.ofEnabled(enabled))

            /**
             * Configuration for enabling Claude's extended thinking.
             *
             * When enabled, responses include `thinking` content blocks showing Claude's thinking
             * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
             * towards your `max_tokens` limit.
             *
             * See
             * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
             * for details.
             */
            fun enabledThinking(budgetTokens: Long) =
                thinking(BetaThinkingConfigEnabled.builder().budgetTokens(budgetTokens).build())

            /**
             * Configuration for enabling Claude's extended thinking.
             *
             * When enabled, responses include `thinking` content blocks showing Claude's thinking
             * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
             * towards your `max_tokens` limit.
             *
             * See
             * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
             * for details.
             */
            fun thinking(disabled: BetaThinkingConfigDisabled) =
                thinking(BetaThinkingConfigParam.ofDisabled(disabled))

            /**
             * How the model should use the provided tools. The model can use a specific tool, any
             * available tool, or decide by itself.
             */
            fun toolChoice(toolChoice: BetaToolChoice) = toolChoice(JsonField.of(toolChoice))

            /**
             * How the model should use the provided tools. The model can use a specific tool, any
             * available tool, or decide by itself.
             */
            fun toolChoice(toolChoice: JsonField<BetaToolChoice>) = apply {
                this.toolChoice = toolChoice
            }

            /** The model will automatically decide whether to use tools. */
            fun toolChoice(auto: BetaToolChoiceAuto) = toolChoice(BetaToolChoice.ofAuto(auto))

            /** The model will use any available tools. */
            fun toolChoice(any: BetaToolChoiceAny) = toolChoice(BetaToolChoice.ofAny(any))

            /** The model will use the specified tool with `tool_choice.name`. */
            fun toolChoice(tool: BetaToolChoiceTool) = toolChoice(BetaToolChoice.ofTool(tool))

            /** The model will use the specified tool with `tool_choice.name`. */
            fun toolToolChoice(name: String) =
                toolChoice(BetaToolChoiceTool.builder().name(name).build())

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun tools(tools: List<BetaToolUnion>) = tools(JsonField.of(tools))

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun tools(tools: JsonField<List<BetaToolUnion>>) = apply {
                this.tools = tools.map { it.toMutableList() }
            }

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun addTool(tool: BetaToolUnion) = apply {
                tools =
                    (tools ?: JsonField.of(mutableListOf())).apply {
                        asKnown()
                            .orElseThrow {
                                IllegalStateException(
                                    "Field was set to non-list type: ${javaClass.simpleName}"
                                )
                            }
                            .add(tool)
                    }
            }

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun addTool(betaTool: BetaTool) = addTool(BetaToolUnion.ofBetaTool(betaTool))

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun addTool(computerUse20241022: BetaToolComputerUse20241022) =
                addTool(BetaToolUnion.ofComputerUse20241022(computerUse20241022))

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun addTool(bash20241022: BetaToolBash20241022) =
                addTool(BetaToolUnion.ofBash20241022(bash20241022))

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun addTool(textEditor20241022: BetaToolTextEditor20241022) =
                addTool(BetaToolUnion.ofTextEditor20241022(textEditor20241022))

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun addTool(computerUse20250124: BetaToolComputerUse20250124) =
                addTool(BetaToolUnion.ofComputerUse20250124(computerUse20250124))

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun addTool(bash20250124: BetaToolBash20250124) =
                addTool(BetaToolUnion.ofBash20250124(bash20250124))

            /**
             * Definitions of tools that the model may use.
             *
             * If you include `tools` in your API request, the model may return `tool_use` content
             * blocks that represent the model's use of those tools. You can then run those tools
             * using the tool input generated by the model and then optionally return results back
             * to the model using `tool_result` content blocks.
             *
             * Each tool definition includes:
             * - `name`: Name of the tool.
             * - `description`: Optional, but strongly-recommended description of the tool.
             * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
             * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
             */
            fun addTool(textEditor20250124: BetaToolTextEditor20250124) =
                addTool(BetaToolUnion.ofTextEditor20250124(textEditor20250124))

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
             * Only sample from the top K options for each subsequent token.
             *
             * Used to remove "long tail" low probability responses.
             * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
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
             * Use nucleus sampling.
             *
             * In nucleus sampling, we compute the cumulative distribution over all the options for
             * each subsequent token in decreasing probability order and cut it off once it reaches
             * a particular probability specified by `top_p`. You should either alter `temperature`
             * or `top_p`, but not both.
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
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

            fun build(): Body =
                Body(
                    checkRequired("maxTokens", maxTokens),
                    checkRequired("messages", messages).map { it.toImmutable() },
                    checkRequired("model", model),
                    metadata,
                    (stopSequences ?: JsonMissing.of()).map { it.toImmutable() },
                    system,
                    temperature,
                    thinking,
                    toolChoice,
                    (tools ?: JsonMissing.of()).map { it.toImmutable() },
                    topK,
                    topP,
                    additionalProperties.toImmutable(),
                )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Body && maxTokens == other.maxTokens && messages == other.messages && model == other.model && metadata == other.metadata && stopSequences == other.stopSequences && system == other.system && temperature == other.temperature && thinking == other.thinking && toolChoice == other.toolChoice && tools == other.tools && topK == other.topK && topP == other.topP && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(maxTokens, messages, model, metadata, stopSequences, system, temperature, thinking, toolChoice, tools, topK, topP, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{maxTokens=$maxTokens, messages=$messages, model=$model, metadata=$metadata, stopSequences=$stopSequences, system=$system, temperature=$temperature, thinking=$thinking, toolChoice=$toolChoice, tools=$tools, topK=$topK, topP=$topP, additionalProperties=$additionalProperties}"
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMessageCreateParams]. */
    @NoAutoDetect
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(betaMessageCreateParams: BetaMessageCreateParams) = apply {
            betas = betaMessageCreateParams.betas?.toMutableList()
            body = betaMessageCreateParams.body.toBuilder()
            additionalHeaders = betaMessageCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = betaMessageCreateParams.additionalQueryParams.toBuilder()
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.orElse(null))

        /** Optional header to specify the beta version(s) you want to use. */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         *
         * Different models have different maximum values for this parameter. See
         * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
         */
        fun maxTokens(maxTokens: Long) = apply { body.maxTokens(maxTokens) }

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         *
         * Different models have different maximum values for this parameter. See
         * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun messages(messages: List<BetaMessageParam>) = apply { body.messages(messages) }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun messages(messages: JsonField<List<BetaMessageParam>>) = apply {
            body.messages(messages)
        }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun addMessage(message: BetaMessageParam) = apply { body.addMessage(message) }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun addMessage(message: BetaMessage) = apply { body.addMessage(message) }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun addUserMessage(content: BetaMessageParam.Content) = apply {
            body.addUserMessage(content)
        }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun addUserMessage(string: String) = apply { body.addUserMessage(string) }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun addUserMessageOfBetaContentBlockParams(
            betaContentBlockParams: List<BetaContentBlockParam>
        ) = apply { body.addUserMessageOfBetaContentBlockParams(betaContentBlockParams) }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun addAssistantMessage(content: BetaMessageParam.Content) = apply {
            body.addAssistantMessage(content)
        }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
         */
        fun addAssistantMessage(string: String) = apply { body.addAssistantMessage(string) }

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
         * [{ "role": "user", "content": "Hello, Claude" }]
         * ```
         *
         * Example with multiple conversational turns:
         * ```json
         * [
         *   { "role": "user", "content": "Hello there." },
         *   { "role": "assistant", "content": "Hi, I'm Claude. How can I help you?" },
         *   { "role": "user", "content": "Can you explain LLMs in plain English?" }
         * ]
         * ```
         *
         * Example with a partially-filled response from Claude:
         * ```json
         * [
         *   {
         *     "role": "user",
         *     "content": "What's the Greek name for Sun? (A) Sol (B) Helios (C) Sun"
         *   },
         *   { "role": "assistant", "content": "The best answer is (" }
         * ]
         * ```
         *
         * Each input message `content` may be either a single `string` or an array of content
         * blocks, where each block has a specific `type`. Using a `string` for `content` is
         * shorthand for an array of one content block of type `"text"`. The following input
         * messages are equivalent:
         * ```json
         * { "role": "user", "content": "Hello, Claude" }
         * ```
         * ```json
         * { "role": "user", "content": [{ "type": "text", "text": "Hello, Claude" }] }
         * ```
         *
         * Starting with Claude 3 models, you can also send image content blocks:
         * ```json
         * {
         *   "role": "user",
         *   "content": [
         *     {
         *       "type": "image",
         *       "source": {
         *         "type": "base64",
         *         "media_type": "image/jpeg",
         *         "data": "/9j/4AAQSkZJRg..."
         *       }
         *     },
         *     { "type": "text", "text": "What is in this image?" }
         *   ]
         * }
         * ```
         *
         * We currently support the `base64` source type for images, and the `image/jpeg`,
         * `image/png`, `image/gif`, and `image/webp` media types.
         *
         * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for more input
         * examples.
         *
         * Note that if you want to include a
         * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use the
         * top-level `system` parameter — there is no `"system"` role for input messages in the
         * Messages API.
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
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(model: JsonField<Model>) = apply { body.model(model) }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(value: String) = apply { body.model(value) }

        /** An object describing metadata about the request. */
        fun metadata(metadata: BetaMetadata) = apply { body.metadata(metadata) }

        /** An object describing metadata about the request. */
        fun metadata(metadata: JsonField<BetaMetadata>) = apply { body.metadata(metadata) }

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
        fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
            body.stopSequences(stopSequences)
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
        fun addStopSequence(stopSequence: String) = apply { body.addStopSequence(stopSequence) }

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        fun system(system: System) = apply { body.system(system) }

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        fun system(system: JsonField<System>) = apply { body.system(system) }

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        fun system(string: String) = apply { body.system(string) }

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
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
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
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
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun thinking(thinking: BetaThinkingConfigParam) = apply { body.thinking(thinking) }

        /**
         * Configuration for enabling Claude's extended thinking.
         *
         * When enabled, responses include `thinking` content blocks showing Claude's thinking
         * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
         * towards your `max_tokens` limit.
         *
         * See
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun thinking(thinking: JsonField<BetaThinkingConfigParam>) = apply {
            body.thinking(thinking)
        }

        /**
         * Configuration for enabling Claude's extended thinking.
         *
         * When enabled, responses include `thinking` content blocks showing Claude's thinking
         * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
         * towards your `max_tokens` limit.
         *
         * See
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun thinking(enabled: BetaThinkingConfigEnabled) = apply { body.thinking(enabled) }

        /**
         * Configuration for enabling Claude's extended thinking.
         *
         * When enabled, responses include `thinking` content blocks showing Claude's thinking
         * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
         * towards your `max_tokens` limit.
         *
         * See
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun enabledThinking(budgetTokens: Long) = apply { body.enabledThinking(budgetTokens) }

        /**
         * Configuration for enabling Claude's extended thinking.
         *
         * When enabled, responses include `thinking` content blocks showing Claude's thinking
         * process before the final answer. Requires a minimum budget of 1,024 tokens and counts
         * towards your `max_tokens` limit.
         *
         * See
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun thinking(disabled: BetaThinkingConfigDisabled) = apply { body.thinking(disabled) }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(toolChoice: BetaToolChoice) = apply { body.toolChoice(toolChoice) }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(toolChoice: JsonField<BetaToolChoice>) = apply {
            body.toolChoice(toolChoice)
        }

        /** The model will automatically decide whether to use tools. */
        fun toolChoice(auto: BetaToolChoiceAuto) = apply { body.toolChoice(auto) }

        /** The model will use any available tools. */
        fun toolChoice(any: BetaToolChoiceAny) = apply { body.toolChoice(any) }

        /** The model will use the specified tool with `tool_choice.name`. */
        fun toolChoice(tool: BetaToolChoiceTool) = apply { body.toolChoice(tool) }

        /** The model will use the specified tool with `tool_choice.name`. */
        fun toolToolChoice(name: String) = apply { body.toolToolChoice(name) }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun tools(tools: List<BetaToolUnion>) = apply { body.tools(tools) }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun tools(tools: JsonField<List<BetaToolUnion>>) = apply { body.tools(tools) }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun addTool(tool: BetaToolUnion) = apply { body.addTool(tool) }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun addTool(betaTool: BetaTool) = apply { body.addTool(betaTool) }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun addTool(computerUse20241022: BetaToolComputerUse20241022) = apply {
            body.addTool(computerUse20241022)
        }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun addTool(bash20241022: BetaToolBash20241022) = apply { body.addTool(bash20241022) }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun addTool(textEditor20241022: BetaToolTextEditor20241022) = apply {
            body.addTool(textEditor20241022)
        }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun addTool(computerUse20250124: BetaToolComputerUse20250124) = apply {
            body.addTool(computerUse20250124)
        }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun addTool(bash20250124: BetaToolBash20250124) = apply { body.addTool(bash20250124) }

        /**
         * Definitions of tools that the model may use.
         *
         * If you include `tools` in your API request, the model may return `tool_use` content
         * blocks that represent the model's use of those tools. You can then run those tools using
         * the tool input generated by the model and then optionally return results back to the
         * model using `tool_result` content blocks.
         *
         * Each tool definition includes:
         * - `name`: Name of the tool.
         * - `description`: Optional, but strongly-recommended description of the tool.
         * - `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the tool
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
         * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
         */
        fun addTool(textEditor20250124: BetaToolTextEditor20250124) = apply {
            body.addTool(textEditor20250124)
        }

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
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
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
         * Use nucleus sampling.
         *
         * In nucleus sampling, we compute the cumulative distribution over all the options for each
         * subsequent token in decreasing probability order and cut it off once it reaches a
         * particular probability specified by `top_p`. You should either alter `temperature` or
         * `top_p`, but not both.
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
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

        fun build(): BetaMessageCreateParams =
            BetaMessageCreateParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    /**
     * System prompt.
     *
     * A system prompt is a way of providing context and instructions to Claude, such as specifying
     * a particular goal or role. See our
     * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
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

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                string != null -> visitor.visitString(string)
                betaTextBlockParams != null -> visitor.visitBetaTextBlockParams(betaTextBlockParams)
                else -> visitor.unknown(_json)
            }
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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is System && string == other.string && betaTextBlockParams == other.betaTextBlockParams /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(string, betaTextBlockParams) /* spotless:on */

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
                System(betaTextBlockParams = betaTextBlockParams)
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

                tryDeserialize(node, jacksonTypeRef<String>())?.let {
                    return System(string = it, _json = json)
                }
                tryDeserialize(node, jacksonTypeRef<List<BetaTextBlockParam>>()) {
                        it.forEach { it.validate() }
                    }
                    ?.let {
                        return System(betaTextBlockParams = it, _json = json)
                    }

                return System(_json = json)
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

        return /* spotless:off */ other is BetaMessageCreateParams && betas == other.betas && body == other.body && additionalHeaders == other.additionalHeaders && additionalQueryParams == other.additionalQueryParams /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betas, body, additionalHeaders, additionalQueryParams) /* spotless:on */

    override fun toString() =
        "BetaMessageCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
