// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.getOrThrow
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
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

class BetaMessageCountTokensParams
constructor(
    private val messages: List<BetaMessageParam>,
    private val model: Model,
    private val system: System?,
    private val toolChoice: BetaToolChoice?,
    private val tools: List<Tool>?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
    private val additionalBodyProperties: Map<String, JsonValue>,
) {

    fun messages(): List<BetaMessageParam> = messages

    fun model(): Model = model

    fun system(): Optional<System> = Optional.ofNullable(system)

    fun toolChoice(): Optional<BetaToolChoice> = Optional.ofNullable(toolChoice)

    fun tools(): Optional<List<Tool>> = Optional.ofNullable(tools)

    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun _additionalBodyProperties(): Map<String, JsonValue> = additionalBodyProperties

    @JvmSynthetic
    internal fun getBody(): BetaMessageCountTokensBody {
        return BetaMessageCountTokensBody(
            messages,
            model,
            system,
            toolChoice,
            tools,
            additionalBodyProperties,
        )
    }

    @JvmSynthetic
    internal fun getHeaders(): Headers {
        val headers = Headers.builder()
        this.betas?.let { headers.put("anthropic-beta", it.map(Any::toString)) }
        headers.putAll(additionalHeaders)
        return headers.build()
    }

    @JvmSynthetic internal fun getQueryParams(): QueryParams = additionalQueryParams

    @JsonDeserialize(builder = BetaMessageCountTokensBody.Builder::class)
    @NoAutoDetect
    class BetaMessageCountTokensBody
    internal constructor(
        private val messages: List<BetaMessageParam>?,
        private val model: Model?,
        private val system: System?,
        private val toolChoice: BetaToolChoice?,
        private val tools: List<Tool>?,
        private val additionalProperties: Map<String, JsonValue>,
    ) {

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
        @JsonProperty("messages") fun messages(): List<BetaMessageParam>? = messages

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        @JsonProperty("model") fun model(): Model? = model

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        @JsonProperty("system") fun system(): System? = system

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        @JsonProperty("tool_choice") fun toolChoice(): BetaToolChoice? = toolChoice

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
         * - `input_schema`: [JSON schema](https://json-schema.org/) for the tool `input` shape that
         *   the model will produce in `tool_use` output content blocks.
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
        @JsonProperty("tools") fun tools(): List<Tool>? = tools

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var messages: List<BetaMessageParam>? = null
            private var model: Model? = null
            private var system: System? = null
            private var toolChoice: BetaToolChoice? = null
            private var tools: List<Tool>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(betaMessageCountTokensBody: BetaMessageCountTokensBody) = apply {
                this.messages = betaMessageCountTokensBody.messages
                this.model = betaMessageCountTokensBody.model
                this.system = betaMessageCountTokensBody.system
                this.toolChoice = betaMessageCountTokensBody.toolChoice
                this.tools = betaMessageCountTokensBody.tools
                additionalProperties(betaMessageCountTokensBody.additionalProperties)
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
            @JsonProperty("messages")
            fun messages(messages: List<BetaMessageParam>) = apply { this.messages = messages }

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            @JsonProperty("model") fun model(model: Model) = apply { this.model = model }

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
             */
            @JsonProperty("system") fun system(system: System) = apply { this.system = system }

            /**
             * How the model should use the provided tools. The model can use a specific tool, any
             * available tool, or decide by itself.
             */
            @JsonProperty("tool_choice")
            fun toolChoice(toolChoice: BetaToolChoice) = apply { this.toolChoice = toolChoice }

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
             * - `input_schema`: [JSON schema](https://json-schema.org/) for the tool `input` shape
             *   that the model will produce in `tool_use` output content blocks.
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
            @JsonProperty("tools") fun tools(tools: List<Tool>) = apply { this.tools = tools }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                this.additionalProperties.putAll(additionalProperties)
            }

            @JsonAnySetter
            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                this.additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun build(): BetaMessageCountTokensBody =
                BetaMessageCountTokensBody(
                    checkNotNull(messages) { "`messages` is required but was not set" }
                        .toImmutable(),
                    checkNotNull(model) { "`model` is required but was not set" },
                    system,
                    toolChoice,
                    tools?.toImmutable(),
                    additionalProperties.toImmutable(),
                )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is BetaMessageCountTokensBody && messages == other.messages && model == other.model && system == other.system && toolChoice == other.toolChoice && tools == other.tools && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(messages, model, system, toolChoice, tools, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "BetaMessageCountTokensBody{messages=$messages, model=$model, system=$system, toolChoice=$toolChoice, tools=$tools, additionalProperties=$additionalProperties}"
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    @NoAutoDetect
    class Builder {

        private var messages: MutableList<BetaMessageParam> = mutableListOf()
        private var model: Model? = null
        private var system: System? = null
        private var toolChoice: BetaToolChoice? = null
        private var tools: MutableList<Tool> = mutableListOf()
        private var betas: MutableList<AnthropicBeta> = mutableListOf()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()
        private var additionalBodyProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageCountTokensParams: BetaMessageCountTokensParams) = apply {
            messages = betaMessageCountTokensParams.messages.toMutableList()
            model = betaMessageCountTokensParams.model
            system = betaMessageCountTokensParams.system
            toolChoice = betaMessageCountTokensParams.toolChoice
            tools = betaMessageCountTokensParams.tools?.toMutableList() ?: mutableListOf()
            betas = betaMessageCountTokensParams.betas?.toMutableList() ?: mutableListOf()
            additionalHeaders = betaMessageCountTokensParams.additionalHeaders.toBuilder()
            additionalQueryParams = betaMessageCountTokensParams.additionalQueryParams.toBuilder()
            additionalBodyProperties =
                betaMessageCountTokensParams.additionalBodyProperties.toMutableMap()
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
        fun messages(messages: List<BetaMessageParam>) = apply {
            this.messages.clear()
            this.messages.addAll(messages)
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
        fun addMessage(message: BetaMessageParam) = apply { this.messages.add(message) }

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
        fun addMessage(message: BetaMessage) = addMessage(message.toParam())

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(model: Model) = apply { this.model = model }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(value: String) = apply { this.model = Model.of(value) }

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        fun system(system: System) = apply { this.system = system }

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        fun system(string: String) = apply { this.system = System.ofString(string) }

        /**
         * System prompt.
         *
         * A system prompt is a way of providing context and instructions to Claude, such as
         * specifying a particular goal or role. See our
         * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
         */
        fun systemOfBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>) = apply {
            this.system = System.ofBetaTextBlockParams(betaTextBlockParams)
        }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(toolChoice: BetaToolChoice) = apply { this.toolChoice = toolChoice }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(betaToolChoiceAuto: BetaToolChoiceAuto) = apply {
            this.toolChoice = BetaToolChoice.ofBetaToolChoiceAuto(betaToolChoiceAuto)
        }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(betaToolChoiceAny: BetaToolChoiceAny) = apply {
            this.toolChoice = BetaToolChoice.ofBetaToolChoiceAny(betaToolChoiceAny)
        }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(betaToolChoiceTool: BetaToolChoiceTool) = apply {
            this.toolChoice = BetaToolChoice.ofBetaToolChoiceTool(betaToolChoiceTool)
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
         * - `input_schema`: [JSON schema](https://json-schema.org/) for the tool `input` shape that
         *   the model will produce in `tool_use` output content blocks.
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
        fun tools(tools: List<Tool>) = apply {
            this.tools.clear()
            this.tools.addAll(tools)
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
         * - `input_schema`: [JSON schema](https://json-schema.org/) for the tool `input` shape that
         *   the model will produce in `tool_use` output content blocks.
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
        fun addTool(tool: Tool) = apply { this.tools.add(tool) }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>) = apply {
            this.betas.clear()
            this.betas.addAll(betas)
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun addBeta(beta: AnthropicBeta) = apply { this.betas.add(beta) }

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

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            this.additionalBodyProperties.clear()
            putAllAdditionalBodyProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            additionalBodyProperties.put(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                this.additionalBodyProperties.putAll(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply {
            additionalBodyProperties.remove(key)
        }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalBodyProperty)
        }

        fun build(): BetaMessageCountTokensParams =
            BetaMessageCountTokensParams(
                messages.toImmutable(),
                checkNotNull(model) { "`model` is required but was not set" },
                system,
                toolChoice,
                tools.toImmutable().ifEmpty { null },
                betas.toImmutable().ifEmpty { null },
                additionalHeaders.build(),
                additionalQueryParams.build(),
                additionalBodyProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = System.Deserializer::class)
    @JsonSerialize(using = System.Serializer::class)
    class System
    private constructor(
        private val string: String? = null,
        private val betaTextBlockParams: List<BetaTextBlockParam>? = null,
        private val _json: JsonValue? = null,
    ) {

        private var validated: Boolean = false

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

        fun validate(): System = apply {
            if (!validated) {
                if (string == null && betaTextBlockParams == null) {
                    throw AnthropicInvalidDataException("Unknown System: $_json")
                }
                betaTextBlockParams?.forEach { it.validate() }
                validated = true
            }
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

        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown System: $json")
            }
        }

        class Deserializer : BaseDeserializer<System>(System::class) {

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

        class Serializer : BaseSerializer<System>(System::class) {

            override fun serialize(
                value: System,
                generator: JsonGenerator,
                provider: SerializerProvider
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

    @JsonDeserialize(using = Tool.Deserializer::class)
    @JsonSerialize(using = Tool.Serializer::class)
    class Tool
    private constructor(
        private val betaTool: BetaTool? = null,
        private val betaToolComputerUse20241022: BetaToolComputerUse20241022? = null,
        private val betaToolBash20241022: BetaToolBash20241022? = null,
        private val betaToolTextEditor20241022: BetaToolTextEditor20241022? = null,
        private val _json: JsonValue? = null,
    ) {

        private var validated: Boolean = false

        fun betaTool(): Optional<BetaTool> = Optional.ofNullable(betaTool)

        fun betaToolComputerUse20241022(): Optional<BetaToolComputerUse20241022> =
            Optional.ofNullable(betaToolComputerUse20241022)

        fun betaToolBash20241022(): Optional<BetaToolBash20241022> =
            Optional.ofNullable(betaToolBash20241022)

        fun betaToolTextEditor20241022(): Optional<BetaToolTextEditor20241022> =
            Optional.ofNullable(betaToolTextEditor20241022)

        fun isBetaTool(): Boolean = betaTool != null

        fun isBetaToolComputerUse20241022(): Boolean = betaToolComputerUse20241022 != null

        fun isBetaToolBash20241022(): Boolean = betaToolBash20241022 != null

        fun isBetaToolTextEditor20241022(): Boolean = betaToolTextEditor20241022 != null

        fun asBetaTool(): BetaTool = betaTool.getOrThrow("betaTool")

        fun asBetaToolComputerUse20241022(): BetaToolComputerUse20241022 =
            betaToolComputerUse20241022.getOrThrow("betaToolComputerUse20241022")

        fun asBetaToolBash20241022(): BetaToolBash20241022 =
            betaToolBash20241022.getOrThrow("betaToolBash20241022")

        fun asBetaToolTextEditor20241022(): BetaToolTextEditor20241022 =
            betaToolTextEditor20241022.getOrThrow("betaToolTextEditor20241022")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaTool != null -> visitor.visitBetaTool(betaTool)
                betaToolComputerUse20241022 != null ->
                    visitor.visitBetaToolComputerUse20241022(betaToolComputerUse20241022)
                betaToolBash20241022 != null ->
                    visitor.visitBetaToolBash20241022(betaToolBash20241022)
                betaToolTextEditor20241022 != null ->
                    visitor.visitBetaToolTextEditor20241022(betaToolTextEditor20241022)
                else -> visitor.unknown(_json)
            }
        }

        fun validate(): Tool = apply {
            if (!validated) {
                if (
                    betaTool == null &&
                        betaToolComputerUse20241022 == null &&
                        betaToolBash20241022 == null &&
                        betaToolTextEditor20241022 == null
                ) {
                    throw AnthropicInvalidDataException("Unknown Tool: $_json")
                }
                betaTool?.validate()
                betaToolComputerUse20241022?.validate()
                betaToolBash20241022?.validate()
                betaToolTextEditor20241022?.validate()
                validated = true
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Tool && betaTool == other.betaTool && betaToolComputerUse20241022 == other.betaToolComputerUse20241022 && betaToolBash20241022 == other.betaToolBash20241022 && betaToolTextEditor20241022 == other.betaToolTextEditor20241022 /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaTool, betaToolComputerUse20241022, betaToolBash20241022, betaToolTextEditor20241022) /* spotless:on */

        override fun toString(): String =
            when {
                betaTool != null -> "Tool{betaTool=$betaTool}"
                betaToolComputerUse20241022 != null ->
                    "Tool{betaToolComputerUse20241022=$betaToolComputerUse20241022}"
                betaToolBash20241022 != null -> "Tool{betaToolBash20241022=$betaToolBash20241022}"
                betaToolTextEditor20241022 != null ->
                    "Tool{betaToolTextEditor20241022=$betaToolTextEditor20241022}"
                _json != null -> "Tool{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Tool")
            }

        companion object {

            @JvmStatic fun ofBetaTool(betaTool: BetaTool) = Tool(betaTool = betaTool)

            @JvmStatic
            fun ofBetaToolComputerUse20241022(
                betaToolComputerUse20241022: BetaToolComputerUse20241022
            ) = Tool(betaToolComputerUse20241022 = betaToolComputerUse20241022)

            @JvmStatic
            fun ofBetaToolBash20241022(betaToolBash20241022: BetaToolBash20241022) =
                Tool(betaToolBash20241022 = betaToolBash20241022)

            @JvmStatic
            fun ofBetaToolTextEditor20241022(
                betaToolTextEditor20241022: BetaToolTextEditor20241022
            ) = Tool(betaToolTextEditor20241022 = betaToolTextEditor20241022)
        }

        interface Visitor<out T> {

            fun visitBetaTool(betaTool: BetaTool): T

            fun visitBetaToolComputerUse20241022(
                betaToolComputerUse20241022: BetaToolComputerUse20241022
            ): T

            fun visitBetaToolBash20241022(betaToolBash20241022: BetaToolBash20241022): T

            fun visitBetaToolTextEditor20241022(
                betaToolTextEditor20241022: BetaToolTextEditor20241022
            ): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Tool: $json")
            }
        }

        class Deserializer : BaseDeserializer<Tool>(Tool::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Tool {
                val json = JsonValue.fromJsonNode(node)

                tryDeserialize(node, jacksonTypeRef<BetaTool>()) { it.validate() }
                    ?.let {
                        return Tool(betaTool = it, _json = json)
                    }
                tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20241022>()) {
                        it.validate()
                    }
                    ?.let {
                        return Tool(betaToolComputerUse20241022 = it, _json = json)
                    }
                tryDeserialize(node, jacksonTypeRef<BetaToolBash20241022>()) { it.validate() }
                    ?.let {
                        return Tool(betaToolBash20241022 = it, _json = json)
                    }
                tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20241022>()) { it.validate() }
                    ?.let {
                        return Tool(betaToolTextEditor20241022 = it, _json = json)
                    }

                return Tool(_json = json)
            }
        }

        class Serializer : BaseSerializer<Tool>(Tool::class) {

            override fun serialize(
                value: Tool,
                generator: JsonGenerator,
                provider: SerializerProvider
            ) {
                when {
                    value.betaTool != null -> generator.writeObject(value.betaTool)
                    value.betaToolComputerUse20241022 != null ->
                        generator.writeObject(value.betaToolComputerUse20241022)
                    value.betaToolBash20241022 != null ->
                        generator.writeObject(value.betaToolBash20241022)
                    value.betaToolTextEditor20241022 != null ->
                        generator.writeObject(value.betaToolTextEditor20241022)
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

        return /* spotless:off */ other is BetaMessageCountTokensParams && messages == other.messages && model == other.model && system == other.system && toolChoice == other.toolChoice && tools == other.tools && betas == other.betas && additionalHeaders == other.additionalHeaders && additionalQueryParams == other.additionalQueryParams && additionalBodyProperties == other.additionalBodyProperties /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(messages, model, system, toolChoice, tools, betas, additionalHeaders, additionalQueryParams, additionalBodyProperties) /* spotless:on */

    override fun toString() =
        "BetaMessageCountTokensParams{messages=$messages, model=$model, system=$system, toolChoice=$toolChoice, tools=$tools, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams, additionalBodyProperties=$additionalBodyProperties}"
}
