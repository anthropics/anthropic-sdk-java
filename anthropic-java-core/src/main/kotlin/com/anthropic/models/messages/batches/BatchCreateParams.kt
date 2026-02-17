// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

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
import com.anthropic.models.messages.CodeExecutionTool20250522
import com.anthropic.models.messages.CodeExecutionTool20250825
import com.anthropic.models.messages.ContentBlockParam
import com.anthropic.models.messages.MemoryTool20250818
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageParam
import com.anthropic.models.messages.Metadata
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.OutputConfig
import com.anthropic.models.messages.TextBlockParam
import com.anthropic.models.messages.ThinkingConfigAdaptive
import com.anthropic.models.messages.ThinkingConfigDisabled
import com.anthropic.models.messages.ThinkingConfigEnabled
import com.anthropic.models.messages.ThinkingConfigParam
import com.anthropic.models.messages.Tool
import com.anthropic.models.messages.ToolBash20250124
import com.anthropic.models.messages.ToolChoice
import com.anthropic.models.messages.ToolChoiceAny
import com.anthropic.models.messages.ToolChoiceAuto
import com.anthropic.models.messages.ToolChoiceNone
import com.anthropic.models.messages.ToolChoiceTool
import com.anthropic.models.messages.ToolSearchToolBm25_20251119
import com.anthropic.models.messages.ToolSearchToolRegex20251119
import com.anthropic.models.messages.ToolTextEditor20250124
import com.anthropic.models.messages.ToolTextEditor20250429
import com.anthropic.models.messages.ToolTextEditor20250728
import com.anthropic.models.messages.ToolUnion
import com.anthropic.models.messages.WebFetchTool20250910
import com.anthropic.models.messages.WebSearchTool20250305
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
 * Send a batch of Message creation requests.
 *
 * The Message Batches API can be used to process multiple Messages API requests at once. Once a
 * Message Batch is created, it begins processing immediately. Batches can take up to 24 hours to
 * complete.
 *
 * Learn more about the Message Batches API in our
 * [user guide](https://docs.claude.com/en/docs/build-with-claude/batch-processing)
 */
class BatchCreateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * List of requests for prompt completion. Each is an individual request to create a Message.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun requests(): List<Request> = body.requests()

    /**
     * Returns the raw JSON value of [requests].
     *
     * Unlike [requests], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _requests(): JsonField<List<Request>> = body._requests()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BatchCreateParams].
         *
         * The following fields are required:
         * ```java
         * .requests()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BatchCreateParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(batchCreateParams: BatchCreateParams) = apply {
            body = batchCreateParams.body.toBuilder()
            additionalHeaders = batchCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = batchCreateParams.additionalQueryParams.toBuilder()
        }

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [requests]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * List of requests for prompt completion. Each is an individual request to create a
         * Message.
         */
        fun requests(requests: List<Request>) = apply { body.requests(requests) }

        /**
         * Sets [Builder.requests] to an arbitrary JSON value.
         *
         * You should usually call [Builder.requests] with a well-typed `List<Request>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun requests(requests: JsonField<List<Request>>) = apply { body.requests(requests) }

        /**
         * Adds a single [Request] to [requests].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRequest(request: Request) = apply { body.addRequest(request) }

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
         * Returns an immutable instance of [BatchCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .requests()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BatchCreateParams =
            BatchCreateParams(
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val requests: JsonField<List<Request>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("requests")
            @ExcludeMissing
            requests: JsonField<List<Request>> = JsonMissing.of()
        ) : this(requests, mutableMapOf())

        /**
         * List of requests for prompt completion. Each is an individual request to create a
         * Message.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun requests(): List<Request> = requests.getRequired("requests")

        /**
         * Returns the raw JSON value of [requests].
         *
         * Unlike [requests], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("requests")
        @ExcludeMissing
        fun _requests(): JsonField<List<Request>> = requests

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
             * .requests()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var requests: JsonField<MutableList<Request>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                requests = body.requests.map { it.toMutableList() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * List of requests for prompt completion. Each is an individual request to create a
             * Message.
             */
            fun requests(requests: List<Request>) = requests(JsonField.of(requests))

            /**
             * Sets [Builder.requests] to an arbitrary JSON value.
             *
             * You should usually call [Builder.requests] with a well-typed `List<Request>` value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun requests(requests: JsonField<List<Request>>) = apply {
                this.requests = requests.map { it.toMutableList() }
            }

            /**
             * Adds a single [Request] to [requests].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addRequest(request: Request) = apply {
                requests =
                    (requests ?: JsonField.of(mutableListOf())).also {
                        checkKnown("requests", it).add(request)
                    }
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

            /**
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .requests()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("requests", requests).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            requests().forEach { it.validate() }
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
            (requests.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                requests == other.requests &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(requests, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{requests=$requests, additionalProperties=$additionalProperties}"
    }

    class Request
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val customId: JsonField<String>,
        private val params: JsonField<Params>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("custom_id")
            @ExcludeMissing
            customId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("params") @ExcludeMissing params: JsonField<Params> = JsonMissing.of(),
        ) : this(customId, params, mutableMapOf())

        /**
         * Developer-provided ID created for each request in a Message Batch. Useful for matching
         * results to requests, as results may be given out of request order.
         *
         * Must be unique for each request within the Message Batch.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun customId(): String = customId.getRequired("custom_id")

        /**
         * Messages API creation parameters for the individual request.
         *
         * See the [Messages API reference](https://docs.claude.com/en/api/messages) for full
         * documentation on available parameters.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun params(): Params = params.getRequired("params")

        /**
         * Returns the raw JSON value of [customId].
         *
         * Unlike [customId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("custom_id") @ExcludeMissing fun _customId(): JsonField<String> = customId

        /**
         * Returns the raw JSON value of [params].
         *
         * Unlike [params], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("params") @ExcludeMissing fun _params(): JsonField<Params> = params

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
             * Returns a mutable builder for constructing an instance of [Request].
             *
             * The following fields are required:
             * ```java
             * .customId()
             * .params()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Request]. */
        class Builder internal constructor() {

            private var customId: JsonField<String>? = null
            private var params: JsonField<Params>? = null
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
            fun customId(customId: String) = customId(JsonField.of(customId))

            /**
             * Sets [Builder.customId] to an arbitrary JSON value.
             *
             * You should usually call [Builder.customId] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun customId(customId: JsonField<String>) = apply { this.customId = customId }

            /**
             * Messages API creation parameters for the individual request.
             *
             * See the [Messages API reference](https://docs.claude.com/en/api/messages) for full
             * documentation on available parameters.
             */
            fun params(params: Params) = params(JsonField.of(params))

            /**
             * Sets [Builder.params] to an arbitrary JSON value.
             *
             * You should usually call [Builder.params] with a well-typed [Params] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun params(params: JsonField<Params>) = apply { this.params = params }

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
             * Returns an immutable instance of [Request].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .customId()
             * .params()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Request =
                Request(
                    checkRequired("customId", customId),
                    checkRequired("params", params),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Request = apply {
            if (validated) {
                return@apply
            }

            customId()
            params().validate()
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
            (if (customId.asKnown().isPresent) 1 else 0) +
                (params.asKnown().getOrNull()?.validity() ?: 0)

        /**
         * Messages API creation parameters for the individual request.
         *
         * See the [Messages API reference](https://docs.claude.com/en/api/messages) for full
         * documentation on available parameters.
         */
        class Params
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val maxTokens: JsonField<Long>,
            private val messages: JsonField<List<MessageParam>>,
            private val model: JsonField<Model>,
            private val container: JsonField<String>,
            private val inferenceGeo: JsonField<String>,
            private val metadata: JsonField<Metadata>,
            private val outputConfig: JsonField<OutputConfig>,
            private val serviceTier: JsonField<ServiceTier>,
            private val speed: JsonField<Speed>,
            private val stopSequences: JsonField<List<String>>,
            private val stream: JsonField<Boolean>,
            private val system: JsonField<System>,
            private val temperature: JsonField<Double>,
            private val thinking: JsonField<ThinkingConfigParam>,
            private val toolChoice: JsonField<ToolChoice>,
            private val tools: JsonField<List<ToolUnion>>,
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
                messages: JsonField<List<MessageParam>> = JsonMissing.of(),
                @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
                @JsonProperty("container")
                @ExcludeMissing
                container: JsonField<String> = JsonMissing.of(),
                @JsonProperty("inference_geo")
                @ExcludeMissing
                inferenceGeo: JsonField<String> = JsonMissing.of(),
                @JsonProperty("metadata")
                @ExcludeMissing
                metadata: JsonField<Metadata> = JsonMissing.of(),
                @JsonProperty("output_config")
                @ExcludeMissing
                outputConfig: JsonField<OutputConfig> = JsonMissing.of(),
                @JsonProperty("service_tier")
                @ExcludeMissing
                serviceTier: JsonField<ServiceTier> = JsonMissing.of(),
                @JsonProperty("speed") @ExcludeMissing speed: JsonField<Speed> = JsonMissing.of(),
                @JsonProperty("stop_sequences")
                @ExcludeMissing
                stopSequences: JsonField<List<String>> = JsonMissing.of(),
                @JsonProperty("stream")
                @ExcludeMissing
                stream: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("system")
                @ExcludeMissing
                system: JsonField<System> = JsonMissing.of(),
                @JsonProperty("temperature")
                @ExcludeMissing
                temperature: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("thinking")
                @ExcludeMissing
                thinking: JsonField<ThinkingConfigParam> = JsonMissing.of(),
                @JsonProperty("tool_choice")
                @ExcludeMissing
                toolChoice: JsonField<ToolChoice> = JsonMissing.of(),
                @JsonProperty("tools")
                @ExcludeMissing
                tools: JsonField<List<ToolUnion>> = JsonMissing.of(),
                @JsonProperty("top_k") @ExcludeMissing topK: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("top_p") @ExcludeMissing topP: JsonField<Double> = JsonMissing.of(),
            ) : this(
                maxTokens,
                messages,
                model,
                container,
                inferenceGeo,
                metadata,
                outputConfig,
                serviceTier,
                speed,
                stopSequences,
                stream,
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
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun maxTokens(): Long = maxTokens.getRequired("max_tokens")

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
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun messages(): List<MessageParam> = messages.getRequired("messages")

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun model(): Model = model.getRequired("model")

            /**
             * Container identifier for reuse across requests.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun container(): Optional<String> = container.getOptional("container")

            /**
             * Specifies the geographic region for inference processing. If not specified, the
             * workspace's `default_inference_geo` is used.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun inferenceGeo(): Optional<String> = inferenceGeo.getOptional("inference_geo")

            /**
             * An object describing metadata about the request.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun metadata(): Optional<Metadata> = metadata.getOptional("metadata")

            /**
             * Configuration options for the model's output, such as the output format.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun outputConfig(): Optional<OutputConfig> = outputConfig.getOptional("output_config")

            /**
             * Determines whether to use priority capacity (if available) or standard capacity for
             * this request.
             *
             * Anthropic offers different levels of service for your API requests. See
             * [service-tiers](https://docs.claude.com/en/api/service-tiers) for details.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun serviceTier(): Optional<ServiceTier> = serviceTier.getOptional("service_tier")

            /**
             * The inference speed mode for this request. `"fast"` enables high
             * output-tokens-per-second inference.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun speed(): Optional<Speed> = speed.getOptional("speed")

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
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun stopSequences(): Optional<List<String>> =
                stopSequences.getOptional("stop_sequences")

            /**
             * Whether to incrementally stream the response using server-sent events.
             *
             * See [streaming](https://docs.claude.com/en/api/messages-streaming) for details.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun stream(): Optional<Boolean> = stream.getOptional("stream")

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.claude.com/en/docs/system-prompts).
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun system(): Optional<System> = system.getOptional("system")

            /**
             * Amount of randomness injected into the response.
             *
             * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
             * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
             *
             * Note that even with `temperature` of `0.0`, the results will not be fully
             * deterministic.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
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
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun thinking(): Optional<ThinkingConfigParam> = thinking.getOptional("thinking")

            /**
             * How the model should use the provided tools. The model can use a specific tool, any
             * available tool, decide by itself, or not use tools at all.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun toolChoice(): Optional<ToolChoice> = toolChoice.getOptional("tool_choice")

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
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun tools(): Optional<List<ToolUnion>> = tools.getOptional("tools")

            /**
             * Only sample from the top K options for each subsequent token.
             *
             * Used to remove "long tail" low probability responses.
             * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun topK(): Optional<Long> = topK.getOptional("top_k")

            /**
             * Use nucleus sampling.
             *
             * In nucleus sampling, we compute the cumulative distribution over all the options for
             * each subsequent token in decreasing probability order and cut it off once it reaches
             * a particular probability specified by `top_p`. You should either alter `temperature`
             * or `top_p`, but not both.
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun topP(): Optional<Double> = topP.getOptional("top_p")

            /**
             * Returns the raw JSON value of [maxTokens].
             *
             * Unlike [maxTokens], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("max_tokens")
            @ExcludeMissing
            fun _maxTokens(): JsonField<Long> = maxTokens

            /**
             * Returns the raw JSON value of [messages].
             *
             * Unlike [messages], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("messages")
            @ExcludeMissing
            fun _messages(): JsonField<List<MessageParam>> = messages

            /**
             * Returns the raw JSON value of [model].
             *
             * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

            /**
             * Returns the raw JSON value of [container].
             *
             * Unlike [container], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("container")
            @ExcludeMissing
            fun _container(): JsonField<String> = container

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
             * Returns the raw JSON value of [metadata].
             *
             * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("metadata")
            @ExcludeMissing
            fun _metadata(): JsonField<Metadata> = metadata

            /**
             * Returns the raw JSON value of [outputConfig].
             *
             * Unlike [outputConfig], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("output_config")
            @ExcludeMissing
            fun _outputConfig(): JsonField<OutputConfig> = outputConfig

            /**
             * Returns the raw JSON value of [serviceTier].
             *
             * Unlike [serviceTier], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("service_tier")
            @ExcludeMissing
            fun _serviceTier(): JsonField<ServiceTier> = serviceTier

            /**
             * Returns the raw JSON value of [speed].
             *
             * Unlike [speed], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("speed") @ExcludeMissing fun _speed(): JsonField<Speed> = speed

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
             * Returns the raw JSON value of [stream].
             *
             * Unlike [stream], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("stream") @ExcludeMissing fun _stream(): JsonField<Boolean> = stream

            /**
             * Returns the raw JSON value of [system].
             *
             * Unlike [system], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("system") @ExcludeMissing fun _system(): JsonField<System> = system

            /**
             * Returns the raw JSON value of [temperature].
             *
             * Unlike [temperature], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("temperature")
            @ExcludeMissing
            fun _temperature(): JsonField<Double> = temperature

            /**
             * Returns the raw JSON value of [thinking].
             *
             * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("thinking")
            @ExcludeMissing
            fun _thinking(): JsonField<ThinkingConfigParam> = thinking

            /**
             * Returns the raw JSON value of [toolChoice].
             *
             * Unlike [toolChoice], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("tool_choice")
            @ExcludeMissing
            fun _toolChoice(): JsonField<ToolChoice> = toolChoice

            /**
             * Returns the raw JSON value of [tools].
             *
             * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("tools") @ExcludeMissing fun _tools(): JsonField<List<ToolUnion>> = tools

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
                 * Returns a mutable builder for constructing an instance of [Params].
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

            /** A builder for [Params]. */
            class Builder internal constructor() {

                private var maxTokens: JsonField<Long>? = null
                private var messages: JsonField<MutableList<MessageParam>>? = null
                private var model: JsonField<Model>? = null
                private var container: JsonField<String> = JsonMissing.of()
                private var inferenceGeo: JsonField<String> = JsonMissing.of()
                private var metadata: JsonField<Metadata> = JsonMissing.of()
                private var outputConfig: JsonField<OutputConfig> = JsonMissing.of()
                private var serviceTier: JsonField<ServiceTier> = JsonMissing.of()
                private var speed: JsonField<Speed> = JsonMissing.of()
                private var stopSequences: JsonField<MutableList<String>>? = null
                private var stream: JsonField<Boolean> = JsonMissing.of()
                private var system: JsonField<System> = JsonMissing.of()
                private var temperature: JsonField<Double> = JsonMissing.of()
                private var thinking: JsonField<ThinkingConfigParam> = JsonMissing.of()
                private var toolChoice: JsonField<ToolChoice> = JsonMissing.of()
                private var tools: JsonField<MutableList<ToolUnion>>? = null
                private var topK: JsonField<Long> = JsonMissing.of()
                private var topP: JsonField<Double> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(params: Params) = apply {
                    maxTokens = params.maxTokens
                    messages = params.messages.map { it.toMutableList() }
                    model = params.model
                    container = params.container
                    inferenceGeo = params.inferenceGeo
                    metadata = params.metadata
                    outputConfig = params.outputConfig
                    serviceTier = params.serviceTier
                    speed = params.speed
                    stopSequences = params.stopSequences.map { it.toMutableList() }
                    stream = params.stream
                    system = params.system
                    temperature = params.temperature
                    thinking = params.thinking
                    toolChoice = params.toolChoice
                    tools = params.tools.map { it.toMutableList() }
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
                 * [models](https://docs.claude.com/en/docs/models-overview) for details.
                 */
                fun maxTokens(maxTokens: Long) = maxTokens(JsonField.of(maxTokens))

                /**
                 * Sets [Builder.maxTokens] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.maxTokens] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun maxTokens(maxTokens: JsonField<Long>) = apply { this.maxTokens = maxTokens }

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
                 * Each input message `content` may be either a single `string` or an array of
                 * content blocks, where each block has a specific `type`. Using a `string` for
                 * `content` is shorthand for an array of one content block of type `"text"`. The
                 * following input messages are equivalent:
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
                 * top-level `system` parameter — there is no `"system"` role for input messages in
                 * the Messages API.
                 *
                 * There is a limit of 100,000 messages in a single request.
                 */
                fun messages(messages: List<MessageParam>) = messages(JsonField.of(messages))

                /**
                 * Sets [Builder.messages] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.messages] with a well-typed `List<MessageParam>`
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun messages(messages: JsonField<List<MessageParam>>) = apply {
                    this.messages = messages.map { it.toMutableList() }
                }

                /**
                 * Adds a single [MessageParam] to [messages].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addMessage(message: MessageParam) = apply {
                    messages =
                        (messages ?: JsonField.of(mutableListOf())).also {
                            checkKnown("messages", it).add(message)
                        }
                }

                /** Alias for calling [addMessage] with `message.toParam()`. */
                fun addMessage(message: Message) = addMessage(message.toParam())

                /**
                 * Alias for calling [addMessage] with the following:
                 * ```java
                 * MessageParam.builder()
                 *     .role(MessageParam.Role.USER)
                 *     .content(content)
                 *     .build()
                 * ```
                 */
                fun addUserMessage(content: MessageParam.Content) =
                    addMessage(
                        MessageParam.builder().role(MessageParam.Role.USER).content(content).build()
                    )

                /**
                 * Alias for calling [addUserMessage] with `MessageParam.Content.ofString(string)`.
                 */
                fun addUserMessage(string: String) =
                    addUserMessage(MessageParam.Content.ofString(string))

                /**
                 * Alias for calling [addUserMessage] with
                 * `MessageParam.Content.ofBlockParams(blockParams)`.
                 */
                fun addUserMessageOfBlockParams(blockParams: List<ContentBlockParam>) =
                    addUserMessage(MessageParam.Content.ofBlockParams(blockParams))

                /**
                 * Alias for calling [addMessage] with the following:
                 * ```java
                 * MessageParam.builder()
                 *     .role(MessageParam.Role.ASSISTANT)
                 *     .content(content)
                 *     .build()
                 * ```
                 */
                fun addAssistantMessage(content: MessageParam.Content) =
                    addMessage(
                        MessageParam.builder()
                            .role(MessageParam.Role.ASSISTANT)
                            .content(content)
                            .build()
                    )

                /**
                 * Alias for calling [addAssistantMessage] with
                 * `MessageParam.Content.ofString(string)`.
                 */
                fun addAssistantMessage(string: String) =
                    addAssistantMessage(MessageParam.Content.ofString(string))

                /**
                 * Alias for calling [addAssistantMessage] with
                 * `MessageParam.Content.ofBlockParams(blockParams)`.
                 */
                fun addAssistantMessageOfBlockParams(blockParams: List<ContentBlockParam>) =
                    addAssistantMessage(MessageParam.Content.ofBlockParams(blockParams))

                /**
                 * The model that will complete your prompt.\n\nSee
                 * [models](https://docs.anthropic.com/en/docs/models-overview) for additional
                 * details and options.
                 */
                fun model(model: Model) = model(JsonField.of(model))

                /**
                 * Sets [Builder.model] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.model] with a well-typed [Model] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
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
                fun container(container: String?) = container(JsonField.ofNullable(container))

                /** Alias for calling [Builder.container] with `container.orElse(null)`. */
                fun container(container: Optional<String>) = container(container.getOrNull())

                /**
                 * Sets [Builder.container] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.container] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun container(container: JsonField<String>) = apply { this.container = container }

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
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun inferenceGeo(inferenceGeo: JsonField<String>) = apply {
                    this.inferenceGeo = inferenceGeo
                }

                /** An object describing metadata about the request. */
                fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

                /**
                 * Sets [Builder.metadata] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.metadata] with a well-typed [Metadata] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

                /** Configuration options for the model's output, such as the output format. */
                fun outputConfig(outputConfig: OutputConfig) =
                    outputConfig(JsonField.of(outputConfig))

                /**
                 * Sets [Builder.outputConfig] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.outputConfig] with a well-typed [OutputConfig]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun outputConfig(outputConfig: JsonField<OutputConfig>) = apply {
                    this.outputConfig = outputConfig
                }

                /**
                 * Determines whether to use priority capacity (if available) or standard capacity
                 * for this request.
                 *
                 * Anthropic offers different levels of service for your API requests. See
                 * [service-tiers](https://docs.claude.com/en/api/service-tiers) for details.
                 */
                fun serviceTier(serviceTier: ServiceTier) = serviceTier(JsonField.of(serviceTier))

                /**
                 * Sets [Builder.serviceTier] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.serviceTier] with a well-typed [ServiceTier]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun serviceTier(serviceTier: JsonField<ServiceTier>) = apply {
                    this.serviceTier = serviceTier
                }

                /**
                 * The inference speed mode for this request. `"fast"` enables high
                 * output-tokens-per-second inference.
                 */
                fun speed(speed: Speed?) = speed(JsonField.ofNullable(speed))

                /** Alias for calling [Builder.speed] with `speed.orElse(null)`. */
                fun speed(speed: Optional<Speed>) = speed(speed.getOrNull())

                /**
                 * Sets [Builder.speed] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.speed] with a well-typed [Speed] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun speed(speed: JsonField<Speed>) = apply { this.speed = speed }

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
                fun stopSequences(stopSequences: List<String>) =
                    stopSequences(JsonField.of(stopSequences))

                /**
                 * Sets [Builder.stopSequences] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.stopSequences] with a well-typed `List<String>`
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
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
                 * Whether to incrementally stream the response using server-sent events.
                 *
                 * See [streaming](https://docs.claude.com/en/api/messages-streaming) for details.
                 */
                fun stream(stream: Boolean) = stream(JsonField.of(stream))

                /**
                 * Sets [Builder.stream] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.stream] with a well-typed [Boolean] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun stream(stream: JsonField<Boolean>) = apply { this.stream = stream }

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
                 * You should usually call [Builder.system] with a well-typed [System] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun system(system: JsonField<System>) = apply { this.system = system }

                /** Alias for calling [system] with `System.ofString(string)`. */
                fun system(string: String) = system(System.ofString(string))

                /** Alias for calling [system] with `System.ofTextBlockParams(textBlockParams)`. */
                fun systemOfTextBlockParams(textBlockParams: List<TextBlockParam>) =
                    system(System.ofTextBlockParams(textBlockParams))

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
                fun temperature(temperature: Double) = temperature(JsonField.of(temperature))

                /**
                 * Sets [Builder.temperature] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.temperature] with a well-typed [Double] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun temperature(temperature: JsonField<Double>) = apply {
                    this.temperature = temperature
                }

                /**
                 * Configuration for enabling Claude's extended thinking.
                 *
                 * When enabled, responses include `thinking` content blocks showing Claude's
                 * thinking process before the final answer. Requires a minimum budget of 1,024
                 * tokens and counts towards your `max_tokens` limit.
                 *
                 * See
                 * [extended thinking](https://docs.claude.com/en/docs/build-with-claude/extended-thinking)
                 * for details.
                 */
                fun thinking(thinking: ThinkingConfigParam) = thinking(JsonField.of(thinking))

                /**
                 * Sets [Builder.thinking] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.thinking] with a well-typed
                 * [ThinkingConfigParam] value instead. This method is primarily for setting the
                 * field to an undocumented or not yet supported value.
                 */
                fun thinking(thinking: JsonField<ThinkingConfigParam>) = apply {
                    this.thinking = thinking
                }

                /** Alias for calling [thinking] with `ThinkingConfigParam.ofEnabled(enabled)`. */
                fun thinking(enabled: ThinkingConfigEnabled) =
                    thinking(ThinkingConfigParam.ofEnabled(enabled))

                /**
                 * Alias for calling [thinking] with the following:
                 * ```java
                 * ThinkingConfigEnabled.builder()
                 *     .budgetTokens(budgetTokens)
                 *     .build()
                 * ```
                 */
                fun enabledThinking(budgetTokens: Long) =
                    thinking(ThinkingConfigEnabled.builder().budgetTokens(budgetTokens).build())

                /** Alias for calling [thinking] with `ThinkingConfigParam.ofDisabled(disabled)`. */
                fun thinking(disabled: ThinkingConfigDisabled) =
                    thinking(ThinkingConfigParam.ofDisabled(disabled))

                /** Alias for calling [thinking] with `ThinkingConfigParam.ofAdaptive(adaptive)`. */
                fun thinking(adaptive: ThinkingConfigAdaptive) =
                    thinking(ThinkingConfigParam.ofAdaptive(adaptive))

                /**
                 * How the model should use the provided tools. The model can use a specific tool,
                 * any available tool, decide by itself, or not use tools at all.
                 */
                fun toolChoice(toolChoice: ToolChoice) = toolChoice(JsonField.of(toolChoice))

                /**
                 * Sets [Builder.toolChoice] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.toolChoice] with a well-typed [ToolChoice] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun toolChoice(toolChoice: JsonField<ToolChoice>) = apply {
                    this.toolChoice = toolChoice
                }

                /** Alias for calling [toolChoice] with `ToolChoice.ofAuto(auto)`. */
                fun toolChoice(auto: ToolChoiceAuto) = toolChoice(ToolChoice.ofAuto(auto))

                /** Alias for calling [toolChoice] with `ToolChoice.ofAny(any)`. */
                fun toolChoice(any: ToolChoiceAny) = toolChoice(ToolChoice.ofAny(any))

                /** Alias for calling [toolChoice] with `ToolChoice.ofTool(tool)`. */
                fun toolChoice(tool: ToolChoiceTool) = toolChoice(ToolChoice.ofTool(tool))

                /**
                 * Alias for calling [toolChoice] with the following:
                 * ```java
                 * ToolChoiceTool.builder()
                 *     .name(name)
                 *     .build()
                 * ```
                 */
                fun toolToolChoice(name: String) =
                    toolChoice(ToolChoiceTool.builder().name(name).build())

                /** Alias for calling [toolChoice] with `ToolChoice.ofNone(none)`. */
                fun toolChoice(none: ToolChoiceNone) = toolChoice(ToolChoice.ofNone(none))

                /**
                 * Definitions of tools that the model may use.
                 *
                 * If you include `tools` in your API request, the model may return `tool_use`
                 * content blocks that represent the model's use of those tools. You can then run
                 * those tools using the tool input generated by the model and then optionally
                 * return results back to the model using `tool_result` content blocks.
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
                 * * `input_schema`: [JSON schema](https://json-schema.org/draft/2020-12) for the
                 *   tool `input` shape that the model will produce in `tool_use` output content
                 *   blocks.
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
                 * See our [guide](https://docs.claude.com/en/docs/tool-use) for more details.
                 */
                fun tools(tools: List<ToolUnion>) = tools(JsonField.of(tools))

                /**
                 * Sets [Builder.tools] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.tools] with a well-typed `List<ToolUnion>` value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun tools(tools: JsonField<List<ToolUnion>>) = apply {
                    this.tools = tools.map { it.toMutableList() }
                }

                /**
                 * Adds a single [ToolUnion] to [tools].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addTool(tool: ToolUnion) = apply {
                    tools =
                        (tools ?: JsonField.of(mutableListOf())).also {
                            checkKnown("tools", it).add(tool)
                        }
                }

                /** Alias for calling [addTool] with `ToolUnion.ofTool(tool)`. */
                fun addTool(tool: Tool) = addTool(ToolUnion.ofTool(tool))

                /** Alias for calling [addTool] with `ToolUnion.ofBash20250124(bash20250124)`. */
                fun addTool(bash20250124: ToolBash20250124) =
                    addTool(ToolUnion.ofBash20250124(bash20250124))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)`.
                 */
                fun addTool(codeExecutionTool20250522: CodeExecutionTool20250522) =
                    addTool(ToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)`.
                 */
                fun addTool(codeExecutionTool20250825: CodeExecutionTool20250825) =
                    addTool(ToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)`.
                 */
                fun addTool(codeExecutionTool20260120: ToolUnion.CodeExecutionTool20260120) =
                    addTool(ToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofMemoryTool20250818(memoryTool20250818)`.
                 */
                fun addTool(memoryTool20250818: MemoryTool20250818) =
                    addTool(ToolUnion.ofMemoryTool20250818(memoryTool20250818))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofTextEditor20250124(textEditor20250124)`.
                 */
                fun addTool(textEditor20250124: ToolTextEditor20250124) =
                    addTool(ToolUnion.ofTextEditor20250124(textEditor20250124))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofTextEditor20250429(textEditor20250429)`.
                 */
                fun addTool(textEditor20250429: ToolTextEditor20250429) =
                    addTool(ToolUnion.ofTextEditor20250429(textEditor20250429))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofTextEditor20250728(textEditor20250728)`.
                 */
                fun addTool(textEditor20250728: ToolTextEditor20250728) =
                    addTool(ToolUnion.ofTextEditor20250728(textEditor20250728))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofWebSearchTool20250305(webSearchTool20250305)`.
                 */
                fun addTool(webSearchTool20250305: WebSearchTool20250305) =
                    addTool(ToolUnion.ofWebSearchTool20250305(webSearchTool20250305))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofWebFetchTool20250910(webFetchTool20250910)`.
                 */
                fun addTool(webFetchTool20250910: WebFetchTool20250910) =
                    addTool(ToolUnion.ofWebFetchTool20250910(webFetchTool20250910))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofWebSearchTool20260209(webSearchTool20260209)`.
                 */
                fun addTool(webSearchTool20260209: ToolUnion.WebSearchTool20260209) =
                    addTool(ToolUnion.ofWebSearchTool20260209(webSearchTool20260209))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofWebFetchTool20260209(webFetchTool20260209)`.
                 */
                fun addTool(webFetchTool20260209: ToolUnion.WebFetchTool20260209) =
                    addTool(ToolUnion.ofWebFetchTool20260209(webFetchTool20260209))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofSearchToolBm25_20251119(searchToolBm25_20251119)`.
                 */
                fun addTool(searchToolBm25_20251119: ToolSearchToolBm25_20251119) =
                    addTool(ToolUnion.ofSearchToolBm25_20251119(searchToolBm25_20251119))

                /**
                 * Alias for calling [addTool] with
                 * `ToolUnion.ofSearchToolRegex20251119(searchToolRegex20251119)`.
                 */
                fun addTool(searchToolRegex20251119: ToolSearchToolRegex20251119) =
                    addTool(ToolUnion.ofSearchToolRegex20251119(searchToolRegex20251119))

                /**
                 * Only sample from the top K options for each subsequent token.
                 *
                 * Used to remove "long tail" low probability responses.
                 * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
                 *
                 * Recommended for advanced use cases only. You usually only need to use
                 * `temperature`.
                 */
                fun topK(topK: Long) = topK(JsonField.of(topK))

                /**
                 * Sets [Builder.topK] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.topK] with a well-typed [Long] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun topK(topK: JsonField<Long>) = apply { this.topK = topK }

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
                fun topP(topP: Double) = topP(JsonField.of(topP))

                /**
                 * Sets [Builder.topP] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.topP] with a well-typed [Double] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun topP(topP: JsonField<Double>) = apply { this.topP = topP }

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

                /**
                 * Returns an immutable instance of [Params].
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
                fun build(): Params =
                    Params(
                        checkRequired("maxTokens", maxTokens),
                        checkRequired("messages", messages).map { it.toImmutable() },
                        checkRequired("model", model),
                        container,
                        inferenceGeo,
                        metadata,
                        outputConfig,
                        serviceTier,
                        speed,
                        (stopSequences ?: JsonMissing.of()).map { it.toImmutable() },
                        stream,
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

            fun validate(): Params = apply {
                if (validated) {
                    return@apply
                }

                maxTokens()
                messages().forEach { it.validate() }
                model()
                container()
                inferenceGeo()
                metadata().ifPresent { it.validate() }
                outputConfig().ifPresent { it.validate() }
                serviceTier().ifPresent { it.validate() }
                speed().ifPresent { it.validate() }
                stopSequences()
                stream()
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
                    (if (container.asKnown().isPresent) 1 else 0) +
                    (if (inferenceGeo.asKnown().isPresent) 1 else 0) +
                    (metadata.asKnown().getOrNull()?.validity() ?: 0) +
                    (outputConfig.asKnown().getOrNull()?.validity() ?: 0) +
                    (serviceTier.asKnown().getOrNull()?.validity() ?: 0) +
                    (speed.asKnown().getOrNull()?.validity() ?: 0) +
                    (stopSequences.asKnown().getOrNull()?.size ?: 0) +
                    (if (stream.asKnown().isPresent) 1 else 0) +
                    (system.asKnown().getOrNull()?.validity() ?: 0) +
                    (if (temperature.asKnown().isPresent) 1 else 0) +
                    (thinking.asKnown().getOrNull()?.validity() ?: 0) +
                    (toolChoice.asKnown().getOrNull()?.validity() ?: 0) +
                    (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                    (if (topK.asKnown().isPresent) 1 else 0) +
                    (if (topP.asKnown().isPresent) 1 else 0)

            /**
             * Determines whether to use priority capacity (if available) or standard capacity for
             * this request.
             *
             * Anthropic offers different levels of service for your API requests. See
             * [service-tiers](https://docs.claude.com/en/api/service-tiers) for details.
             */
            class ServiceTier
            @JsonCreator
            private constructor(private val value: JsonField<String>) : Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
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
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    AUTO,
                    STANDARD_ONLY,
                    /**
                     * An enum member indicating that [ServiceTier] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
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
                 * Use the [value] method instead if you're uncertain the value is always known and
                 * don't want to throw for the unknown case.
                 *
                 * @throws AnthropicInvalidDataException if this class instance's value is a not a
                 *   known member.
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
                 * This differs from the [toString] method because that method is primarily for
                 * debugging and generally doesn't throw.
                 *
                 * @throws AnthropicInvalidDataException if this class instance's value does not
                 *   have the expected primitive type.
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
             * The inference speed mode for this request. `"fast"` enables high
             * output-tokens-per-second inference.
             */
            class Speed @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
                 */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val STANDARD = of("standard")

                    @JvmField val FAST = of("fast")

                    @JvmStatic fun of(value: String) = Speed(JsonField.of(value))
                }

                /** An enum containing [Speed]'s known values. */
                enum class Known {
                    STANDARD,
                    FAST,
                }

                /**
                 * An enum containing [Speed]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [Speed] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    STANDARD,
                    FAST,
                    /**
                     * An enum member indicating that [Speed] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
                 */
                fun value(): Value =
                    when (this) {
                        STANDARD -> Value.STANDARD
                        FAST -> Value.FAST
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * Use the [value] method instead if you're uncertain the value is always known and
                 * don't want to throw for the unknown case.
                 *
                 * @throws AnthropicInvalidDataException if this class instance's value is a not a
                 *   known member.
                 */
                fun known(): Known =
                    when (this) {
                        STANDARD -> Known.STANDARD
                        FAST -> Known.FAST
                        else -> throw AnthropicInvalidDataException("Unknown Speed: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * This differs from the [toString] method because that method is primarily for
                 * debugging and generally doesn't throw.
                 *
                 * @throws AnthropicInvalidDataException if this class instance's value does not
                 *   have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString().orElseThrow {
                        AnthropicInvalidDataException("Value is not a String")
                    }

                private var validated: Boolean = false

                fun validate(): Speed = apply {
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

                    return other is Speed && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            /**
             * System prompt.
             *
             * A system prompt is a way of providing context and instructions to Claude, such as
             * specifying a particular goal or role. See our
             * [guide to system prompts](https://docs.claude.com/en/docs/system-prompts).
             */
            @JsonDeserialize(using = System.Deserializer::class)
            @JsonSerialize(using = System.Serializer::class)
            class System
            private constructor(
                private val string: String? = null,
                private val textBlockParams: List<TextBlockParam>? = null,
                private val _json: JsonValue? = null,
            ) {

                fun string(): Optional<String> = Optional.ofNullable(string)

                fun textBlockParams(): Optional<List<TextBlockParam>> =
                    Optional.ofNullable(textBlockParams)

                fun isString(): Boolean = string != null

                fun isTextBlockParams(): Boolean = textBlockParams != null

                fun asString(): String = string.getOrThrow("string")

                fun asTextBlockParams(): List<TextBlockParam> =
                    textBlockParams.getOrThrow("textBlockParams")

                fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

                fun <T> accept(visitor: Visitor<T>): T =
                    when {
                        string != null -> visitor.visitString(string)
                        textBlockParams != null -> visitor.visitTextBlockParams(textBlockParams)
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

                            override fun visitTextBlockParams(
                                textBlockParams: List<TextBlockParam>
                            ) {
                                textBlockParams.forEach { it.validate() }
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

                            override fun visitTextBlockParams(
                                textBlockParams: List<TextBlockParam>
                            ) = textBlockParams.sumOf { it.validity().toInt() }

                            override fun unknown(json: JsonValue?) = 0
                        }
                    )

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is System &&
                        string == other.string &&
                        textBlockParams == other.textBlockParams
                }

                override fun hashCode(): Int = Objects.hash(string, textBlockParams)

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
                        System(textBlockParams = textBlockParams.toImmutable())
                }

                /**
                 * An interface that defines how to map each variant of [System] to a value of type
                 * [T].
                 */
                interface Visitor<out T> {

                    fun visitString(string: String): T

                    fun visitTextBlockParams(textBlockParams: List<TextBlockParam>): T

                    /**
                     * Maps an unknown variant of [System] to a value of type [T].
                     *
                     * An instance of [System] can contain an unknown variant if it was deserialized
                     * from data that doesn't match any known variant. For example, if the SDK is on
                     * an older version than the API, then the API may respond with new variants
                     * that the SDK is unaware of.
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
                                    tryDeserialize(node, jacksonTypeRef<List<TextBlockParam>>())
                                        ?.let { System(textBlockParams = it, _json = json) },
                                )
                                .filterNotNull()
                                .allMaxBy { it.validity() }
                                .toList()
                        return when (bestMatches.size) {
                            // This can happen if what we're deserializing is completely
                            // incompatible with all the possible variants (e.g. deserializing from
                            // boolean).
                            0 -> System(_json = json)
                            1 -> bestMatches.single()
                            // If there's more than one match with the highest validity, then use
                            // the first completely valid match, or simply the first match if none
                            // are completely valid.
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

                return other is Params &&
                    maxTokens == other.maxTokens &&
                    messages == other.messages &&
                    model == other.model &&
                    container == other.container &&
                    inferenceGeo == other.inferenceGeo &&
                    metadata == other.metadata &&
                    outputConfig == other.outputConfig &&
                    serviceTier == other.serviceTier &&
                    speed == other.speed &&
                    stopSequences == other.stopSequences &&
                    stream == other.stream &&
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
                    inferenceGeo,
                    metadata,
                    outputConfig,
                    serviceTier,
                    speed,
                    stopSequences,
                    stream,
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
                "Params{maxTokens=$maxTokens, messages=$messages, model=$model, container=$container, inferenceGeo=$inferenceGeo, metadata=$metadata, outputConfig=$outputConfig, serviceTier=$serviceTier, speed=$speed, stopSequences=$stopSequences, stream=$stream, system=$system, temperature=$temperature, thinking=$thinking, toolChoice=$toolChoice, tools=$tools, topK=$topK, topP=$topP, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Request &&
                customId == other.customId &&
                params == other.params &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(customId, params, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Request{customId=$customId, params=$params, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BatchCreateParams &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "BatchCreateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
