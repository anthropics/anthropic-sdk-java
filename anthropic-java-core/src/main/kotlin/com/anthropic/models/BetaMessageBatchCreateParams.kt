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

class BetaMessageBatchCreateParams
constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: BetaMessageBatchCreateBody,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * List of requests for prompt completion. Each is an individual request to create a Message.
     */
    fun requests(): List<Request> = body.requests()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    @JvmSynthetic internal fun getBody(): BetaMessageBatchCreateBody = body

    @JvmSynthetic
    internal fun getHeaders(): Headers {
        val headers = Headers.builder()
        this.betas?.let { headers.put("anthropic-beta", it.map(Any::toString)) }
        headers.putAll(additionalHeaders)
        return headers.build()
    }

    @JvmSynthetic internal fun getQueryParams(): QueryParams = additionalQueryParams

    @NoAutoDetect
    class BetaMessageBatchCreateBody
    @JsonCreator
    internal constructor(
        @JsonProperty("requests") private val requests: List<Request>,
        @JsonAnySetter
        private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
    ) {

        /**
         * List of requests for prompt completion. Each is an individual request to create a
         * Message.
         */
        @JsonProperty("requests") fun requests(): List<Request> = requests

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var requests: MutableList<Request>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(betaMessageBatchCreateBody: BetaMessageBatchCreateBody) = apply {
                requests = betaMessageBatchCreateBody.requests.toMutableList()
                additionalProperties =
                    betaMessageBatchCreateBody.additionalProperties.toMutableMap()
            }

            /**
             * List of requests for prompt completion. Each is an individual request to create a
             * Message.
             */
            fun requests(requests: List<Request>) = apply {
                this.requests = requests.toMutableList()
            }

            /**
             * List of requests for prompt completion. Each is an individual request to create a
             * Message.
             */
            fun addRequest(request: Request) = apply {
                requests = (requests ?: mutableListOf()).apply { add(request) }
            }

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

            fun build(): BetaMessageBatchCreateBody =
                BetaMessageBatchCreateBody(
                    checkNotNull(requests) { "`requests` is required but was not set" }
                        .toImmutable(),
                    additionalProperties.toImmutable()
                )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is BetaMessageBatchCreateBody && requests == other.requests && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(requests, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "BetaMessageBatchCreateBody{requests=$requests, additionalProperties=$additionalProperties}"
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    @NoAutoDetect
    class Builder {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: BetaMessageBatchCreateBody.Builder = BetaMessageBatchCreateBody.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(betaMessageBatchCreateParams: BetaMessageBatchCreateParams) = apply {
            betas = betaMessageBatchCreateParams.betas?.toMutableList()
            body = betaMessageBatchCreateParams.body.toBuilder()
            additionalHeaders = betaMessageBatchCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = betaMessageBatchCreateParams.additionalQueryParams.toBuilder()
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>) = apply { this.betas = betas.toMutableList() }

        /** Optional header to specify the beta version(s) you want to use. */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * List of requests for prompt completion. Each is an individual request to create a
         * Message.
         */
        fun requests(requests: List<Request>) = apply { body.requests(requests) }

        /**
         * List of requests for prompt completion. Each is an individual request to create a
         * Message.
         */
        fun addRequest(request: Request) = apply { body.addRequest(request) }

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

        fun build(): BetaMessageBatchCreateParams =
            BetaMessageBatchCreateParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    @NoAutoDetect
    class Request
    @JsonCreator
    private constructor(
        @JsonProperty("custom_id") private val customId: String,
        @JsonProperty("params") private val params: Params,
        @JsonAnySetter
        private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
    ) {

        /**
         * Developer-provided ID created for each request in a Message Batch. Useful for matching
         * results to requests, as results may be given out of request order.
         *
         * Must be unique for each request within the Message Batch.
         */
        @JsonProperty("custom_id") fun customId(): String = customId

        /**
         * Messages API creation parameters for the individual request.
         *
         * See the [Messages API reference](/en/api/messages) for full documentation on available
         * parameters.
         */
        @JsonProperty("params") fun params(): Params = params

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
                customId = request.customId
                params = request.params
                additionalProperties = request.additionalProperties.toMutableMap()
            }

            /**
             * Developer-provided ID created for each request in a Message Batch. Useful for
             * matching results to requests, as results may be given out of request order.
             *
             * Must be unique for each request within the Message Batch.
             */
            fun customId(customId: String) = apply { this.customId = customId }

            /**
             * Messages API creation parameters for the individual request.
             *
             * See the [Messages API reference](/en/api/messages) for full documentation on
             * available parameters.
             */
            fun params(params: Params) = apply { this.params = params }

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
        @NoAutoDetect
        class Params
        @JsonCreator
        private constructor(
            @JsonProperty("max_tokens") private val maxTokens: Long,
            @JsonProperty("messages") private val messages: List<BetaMessageParam>,
            @JsonProperty("model") private val model: Model,
            @JsonProperty("metadata") private val metadata: BetaMetadata?,
            @JsonProperty("stop_sequences") private val stopSequences: List<String>?,
            @JsonProperty("stream") private val stream: Boolean?,
            @JsonProperty("system") private val system: System?,
            @JsonProperty("temperature") private val temperature: Double?,
            @JsonProperty("tool_choice") private val toolChoice: BetaToolChoice?,
            @JsonProperty("tools") private val tools: List<BetaToolUnion>?,
            @JsonProperty("top_k") private val topK: Long?,
            @JsonProperty("top_p") private val topP: Double?,
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
            @JsonProperty("max_tokens") fun maxTokens(): Long = maxTokens

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
            @JsonProperty("messages") fun messages(): List<BetaMessageParam> = messages

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            @JsonProperty("model") fun model(): Model = model

            /** An object describing metadata about the request. */
            @JsonProperty("metadata")
            fun metadata(): Optional<BetaMetadata> = Optional.ofNullable(metadata)

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
            @JsonProperty("stop_sequences")
            fun stopSequences(): Optional<List<String>> = Optional.ofNullable(stopSequences)

            /**
             * Whether to incrementally stream the response using server-sent events.
             *
             * See [streaming](https://docs.anthropic.com/en/api/messages-streaming) for details.
             */
            @JsonProperty("stream") fun stream(): Optional<Boolean> = Optional.ofNullable(stream)

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
             */
            @JsonProperty("system") fun system(): Optional<System> = Optional.ofNullable(system)

            /**
             * Amount of randomness injected into the response.
             *
             * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
             * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
             *
             * Note that even with `temperature` of `0.0`, the results will not be fully
             * deterministic.
             */
            @JsonProperty("temperature")
            fun temperature(): Optional<Double> = Optional.ofNullable(temperature)

            /**
             * How the model should use the provided tools. The model can use a specific tool, any
             * available tool, or decide by itself.
             */
            @JsonProperty("tool_choice")
            fun toolChoice(): Optional<BetaToolChoice> = Optional.ofNullable(toolChoice)

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
            @JsonProperty("tools")
            fun tools(): Optional<List<BetaToolUnion>> = Optional.ofNullable(tools)

            /**
             * Only sample from the top K options for each subsequent token.
             *
             * Used to remove "long tail" low probability responses.
             * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            @JsonProperty("top_k") fun topK(): Optional<Long> = Optional.ofNullable(topK)

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
            @JsonProperty("top_p") fun topP(): Optional<Double> = Optional.ofNullable(topP)

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                @JvmStatic fun builder() = Builder()
            }

            class Builder {

                private var maxTokens: Long? = null
                private var messages: MutableList<BetaMessageParam>? = null
                private var model: Model? = null
                private var metadata: BetaMetadata? = null
                private var stopSequences: MutableList<String>? = null
                private var stream: Boolean? = null
                private var system: System? = null
                private var temperature: Double? = null
                private var toolChoice: BetaToolChoice? = null
                private var tools: MutableList<BetaToolUnion>? = null
                private var topK: Long? = null
                private var topP: Double? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(params: Params) = apply {
                    maxTokens = params.maxTokens
                    messages = params.messages.toMutableList()
                    model = params.model
                    metadata = params.metadata
                    stopSequences = params.stopSequences?.toMutableList()
                    stream = params.stream
                    system = params.system
                    temperature = params.temperature
                    toolChoice = params.toolChoice
                    tools = params.tools?.toMutableList()
                    topK = params.topK
                    topP = params.topP
                    additionalProperties = params.additionalProperties.toMutableMap()
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
                fun maxTokens(maxTokens: Long) = apply { this.maxTokens = maxTokens }

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
                fun messages(messages: List<BetaMessageParam>) = apply {
                    this.messages = messages.toMutableList()
                }

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
                fun addMessage(message: BetaMessageParam) = apply {
                    messages = (messages ?: mutableListOf()).apply { add(message) }
                }

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
                fun addMessage(message: BetaMessage) = addMessage(message.toParam())

                /**
                 * The model that will complete your prompt.\n\nSee
                 * [models](https://docs.anthropic.com/en/docs/models-overview) for additional
                 * details and options.
                 */
                fun model(model: Model) = apply { this.model = model }

                /**
                 * The model that will complete your prompt.\n\nSee
                 * [models](https://docs.anthropic.com/en/docs/models-overview) for additional
                 * details and options.
                 */
                fun model(value: String) = apply { model = Model.of(value) }

                /** An object describing metadata about the request. */
                fun metadata(metadata: BetaMetadata) = apply { this.metadata = metadata }

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
                fun stopSequences(stopSequences: List<String>) = apply {
                    this.stopSequences = stopSequences.toMutableList()
                }

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
                fun addStopSequence(stopSequence: String) = apply {
                    stopSequences = (stopSequences ?: mutableListOf()).apply { add(stopSequence) }
                }

                /**
                 * Whether to incrementally stream the response using server-sent events.
                 *
                 * See [streaming](https://docs.anthropic.com/en/api/messages-streaming) for
                 * details.
                 */
                fun stream(stream: Boolean) = apply { this.stream = stream }

                /**
                 * System prompt.
                 *
                 * A system prompt is a way of providing context and instructions to Claude, such as
                 * specifying a particular goal or role. See our
                 * [guide to system prompts](https://docs.anthropic.com/en/docs/system-prompts).
                 */
                fun system(system: System) = apply { this.system = system }

                fun system(string: String) = apply { this.system = System.ofString(string) }

                fun systemOfBetaTextBlockParams(betaTextBlockParams: List<BetaTextBlockParam>) =
                    apply {
                        this.system = System.ofBetaTextBlockParams(betaTextBlockParams)
                    }

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
                fun temperature(temperature: Double) = apply { this.temperature = temperature }

                /**
                 * How the model should use the provided tools. The model can use a specific tool,
                 * any available tool, or decide by itself.
                 */
                fun toolChoice(toolChoice: BetaToolChoice) = apply { this.toolChoice = toolChoice }

                /** The model will automatically decide whether to use tools. */
                fun toolChoice(betaToolChoiceAuto: BetaToolChoiceAuto) = apply {
                    this.toolChoice = BetaToolChoice.ofBetaToolChoiceAuto(betaToolChoiceAuto)
                }

                /** The model will use any available tools. */
                fun toolChoice(betaToolChoiceAny: BetaToolChoiceAny) = apply {
                    this.toolChoice = BetaToolChoice.ofBetaToolChoiceAny(betaToolChoiceAny)
                }

                /** The model will use the specified tool with `tool_choice.name`. */
                fun toolChoice(betaToolChoiceTool: BetaToolChoiceTool) = apply {
                    this.toolChoice = BetaToolChoice.ofBetaToolChoiceTool(betaToolChoiceTool)
                }

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
                fun tools(tools: List<BetaToolUnion>) = apply { this.tools = tools.toMutableList() }

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
                fun addTool(tool: BetaToolUnion) = apply {
                    tools = (tools ?: mutableListOf()).apply { add(tool) }
                }

                /**
                 * Only sample from the top K options for each subsequent token.
                 *
                 * Used to remove "long tail" low probability responses.
                 * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
                 *
                 * Recommended for advanced use cases only. You usually only need to use
                 * `temperature`.
                 */
                fun topK(topK: Long) = apply { this.topK = topK }

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
                fun topP(topP: Double) = apply { this.topP = topP }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                fun build(): Params =
                    Params(
                        checkNotNull(maxTokens) { "`maxTokens` is required but was not set" },
                        checkNotNull(messages) { "`messages` is required but was not set" }
                            .toImmutable(),
                        checkNotNull(model) { "`model` is required but was not set" },
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

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
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
                        betaTextBlockParams != null ->
                            visitor.visitBetaTextBlockParams(betaTextBlockParams)
                        else -> visitor.unknown(_json)
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
                        betaTextBlockParams != null ->
                            "System{betaTextBlockParams=$betaTextBlockParams}"
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
                        tryDeserialize(node, jacksonTypeRef<List<BetaTextBlockParam>>())?.let {
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

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return /* spotless:off */ other is Params && maxTokens == other.maxTokens && messages == other.messages && model == other.model && metadata == other.metadata && stopSequences == other.stopSequences && stream == other.stream && system == other.system && temperature == other.temperature && toolChoice == other.toolChoice && tools == other.tools && topK == other.topK && topP == other.topP && additionalProperties == other.additionalProperties /* spotless:on */
            }

            /* spotless:off */
            private val hashCode: Int by lazy { Objects.hash(maxTokens, messages, model, metadata, stopSequences, stream, system, temperature, toolChoice, tools, topK, topP, additionalProperties) }
            /* spotless:on */

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Params{maxTokens=$maxTokens, messages=$messages, model=$model, metadata=$metadata, stopSequences=$stopSequences, stream=$stream, system=$system, temperature=$temperature, toolChoice=$toolChoice, tools=$tools, topK=$topK, topP=$topP, additionalProperties=$additionalProperties}"
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

        return /* spotless:off */ other is BetaMessageBatchCreateParams && betas == other.betas && body == other.body && additionalHeaders == other.additionalHeaders && additionalQueryParams == other.additionalQueryParams /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betas, body, additionalHeaders, additionalQueryParams) /* spotless:on */

    override fun toString() =
        "BetaMessageBatchCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
