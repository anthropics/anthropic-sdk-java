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

class MessageBatchCreateParams
constructor(
    private val requests: List<Request>,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
    private val additionalBodyProperties: Map<String, JsonValue>,
) {

    fun requests(): List<Request> = requests

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun _additionalBodyProperties(): Map<String, JsonValue> = additionalBodyProperties

    @JvmSynthetic
    internal fun getBody(): MessageBatchCreateBody {
        return MessageBatchCreateBody(requests, additionalBodyProperties)
    }

    @JvmSynthetic internal fun getHeaders(): Headers = additionalHeaders

    @JvmSynthetic internal fun getQueryParams(): QueryParams = additionalQueryParams

    @JsonDeserialize(builder = MessageBatchCreateBody.Builder::class)
    @NoAutoDetect
    class MessageBatchCreateBody
    internal constructor(
        private val requests: List<Request>?,
        private val additionalProperties: Map<String, JsonValue>,
    ) {

        /**
         * List of requests for prompt completion. Each is an individual request to create a
         * Message.
         */
        @JsonProperty("requests") fun requests(): List<Request>? = requests

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var requests: List<Request>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(messageBatchCreateBody: MessageBatchCreateBody) = apply {
                this.requests = messageBatchCreateBody.requests
                additionalProperties(messageBatchCreateBody.additionalProperties)
            }

            /**
             * List of requests for prompt completion. Each is an individual request to create a
             * Message.
             */
            @JsonProperty("requests")
            fun requests(requests: List<Request>) = apply { this.requests = requests }

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

            fun build(): MessageBatchCreateBody =
                MessageBatchCreateBody(
                    checkNotNull(requests) { "`requests` is required but was not set" }
                        .toImmutable(),
                    additionalProperties.toImmutable()
                )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is MessageBatchCreateBody && requests == other.requests && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(requests, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "MessageBatchCreateBody{requests=$requests, additionalProperties=$additionalProperties}"
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    @NoAutoDetect
    class Builder {

        private var requests: MutableList<Request> = mutableListOf()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()
        private var additionalBodyProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(messageBatchCreateParams: MessageBatchCreateParams) = apply {
            requests = messageBatchCreateParams.requests.toMutableList()
            additionalHeaders = messageBatchCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = messageBatchCreateParams.additionalQueryParams.toBuilder()
            additionalBodyProperties =
                messageBatchCreateParams.additionalBodyProperties.toMutableMap()
        }

        /**
         * List of requests for prompt completion. Each is an individual request to create a
         * Message.
         */
        fun requests(requests: List<Request>) = apply {
            this.requests.clear()
            this.requests.addAll(requests)
        }

        /**
         * List of requests for prompt completion. Each is an individual request to create a
         * Message.
         */
        fun addRequest(request: Request) = apply { this.requests.add(request) }

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

        fun build(): MessageBatchCreateParams =
            MessageBatchCreateParams(
                requests.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
                additionalBodyProperties.toImmutable(),
            )
    }

    @JsonDeserialize(builder = Request.Builder::class)
    @NoAutoDetect
    class Request
    private constructor(
        private val customId: String?,
        private val params: Params?,
        private val additionalProperties: Map<String, JsonValue>,
    ) {

        /**
         * Developer-provided ID created for each request in a Message Batch. Useful for matching
         * results to requests, as results may be given out of request order.
         *
         * Must be unique for each request within the Message Batch.
         */
        @JsonProperty("custom_id") fun customId(): String? = customId

        /**
         * Messages API creation parameters for the individual request.
         *
         * See the [Messages API reference](/en/api/messages) for full documentation on available
         * parameters.
         */
        @JsonProperty("params") fun params(): Params? = params

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var customId: String? = null
            private var params: Params? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(request: Request) = apply {
                this.customId = request.customId
                this.params = request.params
                additionalProperties(request.additionalProperties)
            }

            /**
             * Developer-provided ID created for each request in a Message Batch. Useful for
             * matching results to requests, as results may be given out of request order.
             *
             * Must be unique for each request within the Message Batch.
             */
            @JsonProperty("custom_id")
            fun customId(customId: String) = apply { this.customId = customId }

            /**
             * Messages API creation parameters for the individual request.
             *
             * See the [Messages API reference](/en/api/messages) for full documentation on
             * available parameters.
             */
            @JsonProperty("params") fun params(params: Params) = apply { this.params = params }

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

            fun build(): Request =
                Request(
                    checkNotNull(customId) { "`customId` is required but was not set" },
                    checkNotNull(params) { "`params` is required but was not set" },
                    additionalProperties.toImmutable(),
                )
        }

        /**
         * Messages API creation parameters for the individual request.
         *
         * See the [Messages API reference](/en/api/messages) for full documentation on available
         * parameters.
         */
        @JsonDeserialize(builder = Params.Builder::class)
        @NoAutoDetect
        class Params
        private constructor(
            private val model: Model?,
            private val messages: List<MessageParam>?,
            private val maxTokens: Long?,
            private val metadata: Metadata?,
            private val stopSequences: List<String>?,
            private val stream: Boolean?,
            private val system: System?,
            private val temperature: Double?,
            private val toolChoice: ToolChoice?,
            private val tools: List<Tool>?,
            private val topK: Long?,
            private val topP: Double?,
            private val additionalProperties: Map<String, JsonValue>,
        ) {

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            @JsonProperty("model") fun model(): Model? = model

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
            @JsonProperty("messages") fun messages(): List<MessageParam>? = messages

            /**
             * The maximum number of tokens to generate before stopping.
             *
             * Note that our models may stop _before_ reaching this maximum. This parameter only
             * specifies the absolute maximum number of tokens to generate.
             *
             * Different models have different maximum values for this parameter. See
             * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
             */
            @JsonProperty("max_tokens") fun maxTokens(): Long? = maxTokens

            /** An object describing metadata about the request. */
            @JsonProperty("metadata") fun metadata(): Metadata? = metadata

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
            @JsonProperty("stop_sequences") fun stopSequences(): List<String>? = stopSequences

            /**
             * Whether to incrementally stream the response using server-sent events.
             *
             * See [streaming](https://docs.anthropic.com/en/api/messages-streaming) for details.
             */
            @JsonProperty("stream") fun stream(): Boolean? = stream

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
             */
            @JsonProperty("system") fun system(): System? = system

            /**
             * Amount of randomness injected into the response.
             *
             * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
             * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
             *
             * Note that even with `temperature` of `0.0`, the results will not be fully
             * deterministic.
             */
            @JsonProperty("temperature") fun temperature(): Double? = temperature

            /**
             * How the model should use the provided tools. The model can use a specific tool, any
             * available tool, or decide by itself.
             */
            @JsonProperty("tool_choice") fun toolChoice(): ToolChoice? = toolChoice

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
            @JsonProperty("tools") fun tools(): List<Tool>? = tools

            /**
             * Only sample from the top K options for each subsequent token.
             *
             * Used to remove "long tail" low probability responses.
             * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            @JsonProperty("top_k") fun topK(): Long? = topK

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
            @JsonProperty("top_p") fun topP(): Double? = topP

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                @JvmStatic fun builder() = Builder()
            }

            class Builder {

                private var model: Model? = null
                private var messages: List<MessageParam>? = null
                private var maxTokens: Long? = null
                private var metadata: Metadata? = null
                private var stopSequences: List<String>? = null
                private var stream: Boolean? = null
                private var system: System? = null
                private var temperature: Double? = null
                private var toolChoice: ToolChoice? = null
                private var tools: List<Tool>? = null
                private var topK: Long? = null
                private var topP: Double? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(params: Params) = apply {
                    this.model = params.model
                    this.messages = params.messages
                    this.maxTokens = params.maxTokens
                    this.metadata = params.metadata
                    this.stopSequences = params.stopSequences
                    this.stream = params.stream
                    this.system = params.system
                    this.temperature = params.temperature
                    this.toolChoice = params.toolChoice
                    this.tools = params.tools
                    this.topK = params.topK
                    this.topP = params.topP
                    additionalProperties(params.additionalProperties)
                }

                /**
                 * The model that will complete your prompt.\n\nSee
                 * [models](https://docs.anthropic.com/en/docs/models-overview) for additional
                 * details and options.
                 */
                @JsonProperty("model") fun model(model: Model) = apply { this.model = model }

                /**
                 * Input messages.
                 *
                 * Our models are trained to operate on alternating `user` and `assistant`
                 * conversational turns. When creating a new `Message`, you specify the prior
                 * conversational turns with the `messages` parameter, and the model then generates
                 * the next `Message` in the conversation. Consecutive `user` or `assistant` turns
                 * in your request will be combined into a single turn.
                 *
                 * Each input message must be an object with a `role` and `content`. You can specify
                 * a single `user`-role message, or you can include multiple `user` and `assistant`
                 * messages.
                 *
                 * If the final message uses the `assistant` role, the response content will
                 * continue immediately from the content in that message. This can be used to
                 * constrain part of the model's response.
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
                 * Each input message `content` may be either a single `string` or an array of
                 * content blocks, where each block has a specific `type`. Using a `string` for
                 * `content` is shorthand for an array of one content block of type `"text"`. The
                 * following input messages are equivalent:
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
                 * See [examples](https://docs.anthropic.com/en/api/messages-examples#vision) for
                 * more input examples.
                 *
                 * Note that if you want to include a
                 * [system prompt](https://docs.anthropic.com/en/docs/system-prompts), you can use
                 * the top-level `system` parameter — there is no `"system"` role for input messages
                 * in the Messages API.
                 */
                @JsonProperty("messages")
                fun messages(messages: List<MessageParam>) = apply { this.messages = messages }

                /**
                 * The maximum number of tokens to generate before stopping.
                 *
                 * Note that our models may stop _before_ reaching this maximum. This parameter only
                 * specifies the absolute maximum number of tokens to generate.
                 *
                 * Different models have different maximum values for this parameter. See
                 * [models](https://docs.anthropic.com/en/docs/models-overview) for details.
                 */
                @JsonProperty("max_tokens")
                fun maxTokens(maxTokens: Long) = apply { this.maxTokens = maxTokens }

                /** An object describing metadata about the request. */
                @JsonProperty("metadata")
                fun metadata(metadata: Metadata) = apply { this.metadata = metadata }

                /**
                 * Custom text sequences that will cause the model to stop generating.
                 *
                 * Our models will normally stop when they have naturally completed their turn,
                 * which will result in a response `stop_reason` of `"end_turn"`.
                 *
                 * If you want the model to stop generating when it encounters custom strings of
                 * text, you can use the `stop_sequences` parameter. If the model encounters one of
                 * the custom sequences, the response `stop_reason` value will be `"stop_sequence"`
                 * and the response `stop_sequence` value will contain the matched stop sequence.
                 */
                @JsonProperty("stop_sequences")
                fun stopSequences(stopSequences: List<String>) = apply {
                    this.stopSequences = stopSequences
                }

                /**
                 * Whether to incrementally stream the response using server-sent events.
                 *
                 * See [streaming](https://docs.anthropic.com/en/api/messages-streaming) for
                 * details.
                 */
                @JsonProperty("stream") fun stream(stream: Boolean) = apply { this.stream = stream }

                /**
                 * System prompt.
                 *
                 * A system prompt is a way of providing context and instructions to Claude, such as
                 * specifying a particular goal or role. See our
                 * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
                 */
                @JsonProperty("system") fun system(system: System) = apply { this.system = system }

                /**
                 * Amount of randomness injected into the response.
                 *
                 * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0`
                 * for analytical / multiple choice, and closer to `1.0` for creative and generative
                 * tasks.
                 *
                 * Note that even with `temperature` of `0.0`, the results will not be fully
                 * deterministic.
                 */
                @JsonProperty("temperature")
                fun temperature(temperature: Double) = apply { this.temperature = temperature }

                /**
                 * How the model should use the provided tools. The model can use a specific tool,
                 * any available tool, or decide by itself.
                 */
                @JsonProperty("tool_choice")
                fun toolChoice(toolChoice: ToolChoice) = apply { this.toolChoice = toolChoice }

                /**
                 * Definitions of tools that the model may use.
                 *
                 * If you include `tools` in your API request, the model may return `tool_use`
                 * content blocks that represent the model's use of those tools. You can then run
                 * those tools using the tool input generated by the model and then optionally
                 * return results back to the model using `tool_result` content blocks.
                 *
                 * Each tool definition includes:
                 * - `name`: Name of the tool.
                 * - `description`: Optional, but strongly-recommended description of the tool.
                 * - `input_schema`: [JSON schema](https://json-schema.org/) for the tool `input`
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
                 * Tools can be used for workflows that include running client-side tools and
                 * functions, or more generally whenever you want the model to produce a particular
                 * JSON structure of output.
                 *
                 * See our [guide](https://docs.anthropic.com/en/docs/tool-use) for more details.
                 */
                @JsonProperty("tools") fun tools(tools: List<Tool>) = apply { this.tools = tools }

                /**
                 * Only sample from the top K options for each subsequent token.
                 *
                 * Used to remove "long tail" low probability responses.
                 * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
                 *
                 * Recommended for advanced use cases only. You usually only need to use
                 * `temperature`.
                 */
                @JsonProperty("top_k") fun topK(topK: Long) = apply { this.topK = topK }

                /**
                 * Use nucleus sampling.
                 *
                 * In nucleus sampling, we compute the cumulative distribution over all the options
                 * for each subsequent token in decreasing probability order and cut it off once it
                 * reaches a particular probability specified by `top_p`. You should either alter
                 * `temperature` or `top_p`, but not both.
                 *
                 * Recommended for advanced use cases only. You usually only need to use
                 * `temperature`.
                 */
                @JsonProperty("top_p") fun topP(topP: Double) = apply { this.topP = topP }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    this.additionalProperties.putAll(additionalProperties)
                }

                @JsonAnySetter
                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    this.additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun build(): Params =
                    Params(
                        checkNotNull(model) { "`model` is required but was not set" },
                        checkNotNull(messages) { "`messages` is required but was not set" }
                            .toImmutable(),
                        checkNotNull(maxTokens) { "`maxTokens` is required but was not set" },
                        metadata,
                        stopSequences?.toImmutable(),
                        stream,
                        system,
                        temperature,
                        toolChoice,
                        tools?.toImmutable(),
                        topK,
                        topP,
                        additionalProperties.toImmutable(),
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

                fun textBlockParams(): Optional<List<TextBlockParam>> =
                    Optional.ofNullable(textBlockParams)

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
                            value.textBlockParams != null ->
                                generator.writeObject(value.textBlockParams)
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

                return /* spotless:off */ other is Params && model == other.model && messages == other.messages && maxTokens == other.maxTokens && metadata == other.metadata && stopSequences == other.stopSequences && stream == other.stream && system == other.system && temperature == other.temperature && toolChoice == other.toolChoice && tools == other.tools && topK == other.topK && topP == other.topP && additionalProperties == other.additionalProperties /* spotless:on */
            }

            /* spotless:off */
            private val hashCode: Int by lazy { Objects.hash(model, messages, maxTokens, metadata, stopSequences, stream, system, temperature, toolChoice, tools, topK, topP, additionalProperties) }
            /* spotless:on */

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Params{model=$model, messages=$messages, maxTokens=$maxTokens, metadata=$metadata, stopSequences=$stopSequences, stream=$stream, system=$system, temperature=$temperature, toolChoice=$toolChoice, tools=$tools, topK=$topK, topP=$topP, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Request && customId == other.customId && params == other.params && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(customId, params, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Request{customId=$customId, params=$params, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is MessageBatchCreateParams && requests == other.requests && additionalHeaders == other.additionalHeaders && additionalQueryParams == other.additionalQueryParams && additionalBodyProperties == other.additionalBodyProperties /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(requests, additionalHeaders, additionalQueryParams, additionalBodyProperties) /* spotless:on */

    override fun toString() =
        "MessageBatchCreateParams{requests=$requests, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams, additionalBodyProperties=$additionalBodyProperties}"
}
