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

class MessageCountTokensParams
constructor(
    private val messages: List<MessageParam>,
    private val model: Model,
    private val system: System?,
    private val toolChoice: ToolChoice?,
    private val tools: List<Tool>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
    private val additionalBodyProperties: Map<String, JsonValue>,
) {

    fun messages(): List<MessageParam> = messages

    fun model(): Model = model

    fun system(): Optional<System> = Optional.ofNullable(system)

    fun toolChoice(): Optional<ToolChoice> = Optional.ofNullable(toolChoice)

    fun tools(): Optional<List<Tool>> = Optional.ofNullable(tools)

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun _additionalBodyProperties(): Map<String, JsonValue> = additionalBodyProperties

    @JvmSynthetic
    internal fun getBody(): MessageCountTokensBody {
        return MessageCountTokensBody(
            messages,
            model,
            system,
            toolChoice,
            tools,
            additionalBodyProperties,
        )
    }

    @JvmSynthetic internal fun getHeaders(): Headers = additionalHeaders

    @JvmSynthetic internal fun getQueryParams(): QueryParams = additionalQueryParams

    @JsonDeserialize(builder = MessageCountTokensBody.Builder::class)
    @NoAutoDetect
    class MessageCountTokensBody
    internal constructor(
        private val messages: List<MessageParam>?,
        private val model: Model?,
        private val system: System?,
        private val toolChoice: ToolChoice?,
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
        @JsonProperty("messages") fun messages(): List<MessageParam>? = messages

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
        @JsonProperty("tool_choice") fun toolChoice(): ToolChoice? = toolChoice

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

            private var messages: List<MessageParam>? = null
            private var model: Model? = null
            private var system: System? = null
            private var toolChoice: ToolChoice? = null
            private var tools: List<Tool>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(messageCountTokensBody: MessageCountTokensBody) = apply {
                this.messages = messageCountTokensBody.messages
                this.model = messageCountTokensBody.model
                this.system = messageCountTokensBody.system
                this.toolChoice = messageCountTokensBody.toolChoice
                this.tools = messageCountTokensBody.tools
                additionalProperties(messageCountTokensBody.additionalProperties)
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
            fun messages(messages: List<MessageParam>) = apply { this.messages = messages }

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
            fun toolChoice(toolChoice: ToolChoice) = apply { this.toolChoice = toolChoice }

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

            fun build(): MessageCountTokensBody =
                MessageCountTokensBody(
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

            return /* spotless:off */ other is MessageCountTokensBody && messages == other.messages && model == other.model && system == other.system && toolChoice == other.toolChoice && tools == other.tools && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(messages, model, system, toolChoice, tools, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "MessageCountTokensBody{messages=$messages, model=$model, system=$system, toolChoice=$toolChoice, tools=$tools, additionalProperties=$additionalProperties}"
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    @NoAutoDetect
    class Builder {

        private var messages: MutableList<MessageParam> = mutableListOf()
        private var model: Model? = null
        private var system: System? = null
        private var toolChoice: ToolChoice? = null
        private var tools: MutableList<Tool> = mutableListOf()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()
        private var additionalBodyProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(messageCountTokensParams: MessageCountTokensParams) = apply {
            messages = messageCountTokensParams.messages.toMutableList()
            model = messageCountTokensParams.model
            system = messageCountTokensParams.system
            toolChoice = messageCountTokensParams.toolChoice
            tools = messageCountTokensParams.tools?.toMutableList() ?: mutableListOf()
            additionalHeaders = messageCountTokensParams.additionalHeaders.toBuilder()
            additionalQueryParams = messageCountTokensParams.additionalQueryParams.toBuilder()
            additionalBodyProperties =
                messageCountTokensParams.additionalBodyProperties.toMutableMap()
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
        fun messages(messages: List<MessageParam>) = apply {
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
        fun addMessage(message: MessageParam) = apply { this.messages.add(message) }

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
        fun addMessage(message: Message) = addMessage(message.toParam())

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
        fun systemOfTextBlockParams(textBlockParams: List<TextBlockParam>) = apply {
            this.system = System.ofTextBlockParams(textBlockParams)
        }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(toolChoice: ToolChoice) = apply { this.toolChoice = toolChoice }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(toolChoiceAuto: ToolChoiceAuto) = apply {
            this.toolChoice = ToolChoice.ofToolChoiceAuto(toolChoiceAuto)
        }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(toolChoiceAny: ToolChoiceAny) = apply {
            this.toolChoice = ToolChoice.ofToolChoiceAny(toolChoiceAny)
        }

        /**
         * How the model should use the provided tools. The model can use a specific tool, any
         * available tool, or decide by itself.
         */
        fun toolChoice(toolChoiceTool: ToolChoiceTool) = apply {
            this.toolChoice = ToolChoice.ofToolChoiceTool(toolChoiceTool)
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

        fun build(): MessageCountTokensParams =
            MessageCountTokensParams(
                messages.toImmutable(),
                checkNotNull(model) { "`model` is required but was not set" },
                system,
                toolChoice,
                tools.toImmutable().ifEmpty { null },
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
        private val textBlockParams: List<TextBlockParam>? = null,
        private val _json: JsonValue? = null,
    ) {

        private var validated: Boolean = false

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun textBlockParams(): Optional<List<TextBlockParam>> = Optional.ofNullable(textBlockParams)

        fun isString(): Boolean = string != null

        fun isTextBlockParams(): Boolean = textBlockParams != null

        fun asString(): String = string.getOrThrow("string")

        fun asTextBlockParams(): List<TextBlockParam> =
            textBlockParams.getOrThrow("textBlockParams")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                string != null -> visitor.visitString(string)
                textBlockParams != null -> visitor.visitTextBlockParams(textBlockParams)
                else -> visitor.unknown(_json)
            }
        }

        fun validate(): System = apply {
            if (!validated) {
                if (string == null && textBlockParams == null) {
                    throw AnthropicInvalidDataException("Unknown System: $_json")
                }
                textBlockParams?.forEach { it.validate() }
                validated = true
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is System && string == other.string && textBlockParams == other.textBlockParams /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(string, textBlockParams) /* spotless:on */

        override fun toString(): String =
            when {
                string != null -> "System{string=$string}"
                textBlockParams != null -> "System{textBlockParams=$textBlockParams}"
                _json != null -> "System{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid System")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = System(string = string)

            @JvmStatic
            fun ofTextBlockParams(textBlockParams: List<TextBlockParam>) =
                System(textBlockParams = textBlockParams)
        }

        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitTextBlockParams(textBlockParams: List<TextBlockParam>): T

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
                tryDeserialize(node, jacksonTypeRef<List<TextBlockParam>>()) {
                        it.forEach { it.validate() }
                    }
                    ?.let {
                        return System(textBlockParams = it, _json = json)
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
                    value.textBlockParams != null -> generator.writeObject(value.textBlockParams)
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

        return /* spotless:off */ other is MessageCountTokensParams && messages == other.messages && model == other.model && system == other.system && toolChoice == other.toolChoice && tools == other.tools && additionalHeaders == other.additionalHeaders && additionalQueryParams == other.additionalQueryParams && additionalBodyProperties == other.additionalBodyProperties /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(messages, model, system, toolChoice, tools, additionalHeaders, additionalQueryParams, additionalBodyProperties) /* spotless:on */

    override fun toString() =
        "MessageCountTokensParams{messages=$messages, model=$model, system=$system, toolChoice=$toolChoice, tools=$tools, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams, additionalBodyProperties=$additionalBodyProperties}"
}
