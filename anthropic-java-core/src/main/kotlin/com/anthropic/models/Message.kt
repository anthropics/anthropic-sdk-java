// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects
import java.util.Optional

@JsonDeserialize(builder = Message.Builder::class)
@NoAutoDetect
class Message
private constructor(
    private val id: JsonField<String>,
    private val type: JsonField<Type>,
    private val role: JsonField<Role>,
    private val content: JsonField<List<ContentBlock>>,
    private val model: JsonField<Model>,
    private val stopReason: JsonField<StopReason>,
    private val stopSequence: JsonField<String>,
    private val usage: JsonField<Usage>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    fun toParam(): MessageParam =
        MessageParam.builder()
            .content(MessageParam.Content.ofContentBlockParams(content().map { it.toParam() }))
            .role(MessageParam.Role.of(role().toString()))
            .build()

    private var validated: Boolean = false

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     */
    fun id(): String = id.getRequired("id")

    /**
     * Object type.
     *
     * For Messages, this is always `"message"`.
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Conversational role of the generated message.
     *
     * This will always be `"assistant"`.
     */
    fun role(): Role = role.getRequired("role")

    /**
     * Content generated by the model.
     *
     * This is an array of content blocks, each of which has a `type` that determines its shape.
     *
     * Example:
     * ```json
     * [{ "type": "text", "text": "Hi, I'm Claude." }]
     * ```
     *
     * If the request input `messages` ended with an `assistant` turn, then the response `content`
     * will continue directly from that last turn. You can use this to constrain the model's output.
     *
     * For example, if the input `messages` were:
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
     * Then the response `content` might be:
     * ```json
     * [{ "type": "text", "text": "B)" }]
     * ```
     */
    fun content(): List<ContentBlock> = content.getRequired("content")

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     */
    fun model(): Model = model.getRequired("model")

    /**
     * The reason that we stopped.
     *
     * This may be one the following values:
     * - `"end_turn"`: the model reached a natural stopping point
     * - `"max_tokens"`: we exceeded the requested `max_tokens` or the model's maximum
     * - `"stop_sequence"`: one of your provided custom `stop_sequences` was generated
     * - `"tool_use"`: the model invoked one or more tools
     *
     * In non-streaming mode this value is always non-null. In streaming mode, it is null in the
     * `message_start` event and non-null otherwise.
     */
    fun stopReason(): Optional<StopReason> =
        Optional.ofNullable(stopReason.getNullable("stop_reason"))

    /**
     * Which custom stop sequence was generated, if any.
     *
     * This value will be a non-null string if one of your custom stop sequences was generated.
     */
    fun stopSequence(): Optional<String> =
        Optional.ofNullable(stopSequence.getNullable("stop_sequence"))

    /**
     * Billing and rate-limit usage.
     *
     * Anthropic's API bills and rate-limits by token counts, as tokens represent the underlying
     * cost to our systems.
     *
     * Under the hood, the API transforms requests into a format suitable for the model. The model's
     * output then goes through a parsing stage before becoming an API response. As a result, the
     * token counts in `usage` will not match one-to-one with the exact visible content of an API
     * request or response.
     *
     * For example, `output_tokens` will be non-zero, even for an empty string response from Claude.
     */
    fun usage(): Usage = usage.getRequired("usage")

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     */
    @JsonProperty("id") @ExcludeMissing fun _id() = id

    /**
     * Object type.
     *
     * For Messages, this is always `"message"`.
     */
    @JsonProperty("type") @ExcludeMissing fun _type() = type

    /**
     * Conversational role of the generated message.
     *
     * This will always be `"assistant"`.
     */
    @JsonProperty("role") @ExcludeMissing fun _role() = role

    /**
     * Content generated by the model.
     *
     * This is an array of content blocks, each of which has a `type` that determines its shape.
     *
     * Example:
     * ```json
     * [{ "type": "text", "text": "Hi, I'm Claude." }]
     * ```
     *
     * If the request input `messages` ended with an `assistant` turn, then the response `content`
     * will continue directly from that last turn. You can use this to constrain the model's output.
     *
     * For example, if the input `messages` were:
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
     * Then the response `content` might be:
     * ```json
     * [{ "type": "text", "text": "B)" }]
     * ```
     */
    @JsonProperty("content") @ExcludeMissing fun _content() = content

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     */
    @JsonProperty("model") @ExcludeMissing fun _model() = model

    /**
     * The reason that we stopped.
     *
     * This may be one the following values:
     * - `"end_turn"`: the model reached a natural stopping point
     * - `"max_tokens"`: we exceeded the requested `max_tokens` or the model's maximum
     * - `"stop_sequence"`: one of your provided custom `stop_sequences` was generated
     * - `"tool_use"`: the model invoked one or more tools
     *
     * In non-streaming mode this value is always non-null. In streaming mode, it is null in the
     * `message_start` event and non-null otherwise.
     */
    @JsonProperty("stop_reason") @ExcludeMissing fun _stopReason() = stopReason

    /**
     * Which custom stop sequence was generated, if any.
     *
     * This value will be a non-null string if one of your custom stop sequences was generated.
     */
    @JsonProperty("stop_sequence") @ExcludeMissing fun _stopSequence() = stopSequence

    /**
     * Billing and rate-limit usage.
     *
     * Anthropic's API bills and rate-limits by token counts, as tokens represent the underlying
     * cost to our systems.
     *
     * Under the hood, the API transforms requests into a format suitable for the model. The model's
     * output then goes through a parsing stage before becoming an API response. As a result, the
     * token counts in `usage` will not match one-to-one with the exact visible content of an API
     * request or response.
     *
     * For example, `output_tokens` will be non-zero, even for an empty string response from Claude.
     */
    @JsonProperty("usage") @ExcludeMissing fun _usage() = usage

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): Message = apply {
        if (!validated) {
            id()
            type()
            role()
            content()
            model()
            stopReason()
            stopSequence()
            usage().validate()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var id: JsonField<String> = JsonMissing.of()
        private var type: JsonField<Type> = JsonMissing.of()
        private var role: JsonField<Role> = JsonMissing.of()
        private var content: JsonField<List<ContentBlock>> = JsonMissing.of()
        private var model: JsonField<Model> = JsonMissing.of()
        private var stopReason: JsonField<StopReason> = JsonMissing.of()
        private var stopSequence: JsonField<String> = JsonMissing.of()
        private var usage: JsonField<Usage> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(message: Message) = apply {
            this.id = message.id
            this.type = message.type
            this.role = message.role
            this.content = message.content
            this.model = message.model
            this.stopReason = message.stopReason
            this.stopSequence = message.stopSequence
            this.usage = message.usage
            additionalProperties(message.additionalProperties)
        }

        /**
         * Unique object identifier.
         *
         * The format and length of IDs may change over time.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Unique object identifier.
         *
         * The format and length of IDs may change over time.
         */
        @JsonProperty("id") @ExcludeMissing fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Object type.
         *
         * For Messages, this is always `"message"`.
         */
        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Object type.
         *
         * For Messages, this is always `"message"`.
         */
        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /**
         * Conversational role of the generated message.
         *
         * This will always be `"assistant"`.
         */
        fun role(role: Role) = role(JsonField.of(role))

        /**
         * Conversational role of the generated message.
         *
         * This will always be `"assistant"`.
         */
        @JsonProperty("role")
        @ExcludeMissing
        fun role(role: JsonField<Role>) = apply { this.role = role }

        /**
         * Content generated by the model.
         *
         * This is an array of content blocks, each of which has a `type` that determines its shape.
         *
         * Example:
         * ```json
         * [{ "type": "text", "text": "Hi, I'm Claude." }]
         * ```
         *
         * If the request input `messages` ended with an `assistant` turn, then the response
         * `content` will continue directly from that last turn. You can use this to constrain the
         * model's output.
         *
         * For example, if the input `messages` were:
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
         * Then the response `content` might be:
         * ```json
         * [{ "type": "text", "text": "B)" }]
         * ```
         */
        fun content(content: List<ContentBlock>) = content(JsonField.of(content))

        /**
         * Content generated by the model.
         *
         * This is an array of content blocks, each of which has a `type` that determines its shape.
         *
         * Example:
         * ```json
         * [{ "type": "text", "text": "Hi, I'm Claude." }]
         * ```
         *
         * If the request input `messages` ended with an `assistant` turn, then the response
         * `content` will continue directly from that last turn. You can use this to constrain the
         * model's output.
         *
         * For example, if the input `messages` were:
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
         * Then the response `content` might be:
         * ```json
         * [{ "type": "text", "text": "B)" }]
         * ```
         */
        @JsonProperty("content")
        @ExcludeMissing
        fun content(content: JsonField<List<ContentBlock>>) = apply { this.content = content }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(model: Model) = model(JsonField.of(model))

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        @JsonProperty("model")
        @ExcludeMissing
        fun model(model: JsonField<Model>) = apply { this.model = model }

        /**
         * The reason that we stopped.
         *
         * This may be one the following values:
         * - `"end_turn"`: the model reached a natural stopping point
         * - `"max_tokens"`: we exceeded the requested `max_tokens` or the model's maximum
         * - `"stop_sequence"`: one of your provided custom `stop_sequences` was generated
         * - `"tool_use"`: the model invoked one or more tools
         *
         * In non-streaming mode this value is always non-null. In streaming mode, it is null in the
         * `message_start` event and non-null otherwise.
         */
        fun stopReason(stopReason: StopReason) = stopReason(JsonField.of(stopReason))

        /**
         * The reason that we stopped.
         *
         * This may be one the following values:
         * - `"end_turn"`: the model reached a natural stopping point
         * - `"max_tokens"`: we exceeded the requested `max_tokens` or the model's maximum
         * - `"stop_sequence"`: one of your provided custom `stop_sequences` was generated
         * - `"tool_use"`: the model invoked one or more tools
         *
         * In non-streaming mode this value is always non-null. In streaming mode, it is null in the
         * `message_start` event and non-null otherwise.
         */
        @JsonProperty("stop_reason")
        @ExcludeMissing
        fun stopReason(stopReason: JsonField<StopReason>) = apply { this.stopReason = stopReason }

        /**
         * Which custom stop sequence was generated, if any.
         *
         * This value will be a non-null string if one of your custom stop sequences was generated.
         */
        fun stopSequence(stopSequence: String) = stopSequence(JsonField.of(stopSequence))

        /**
         * Which custom stop sequence was generated, if any.
         *
         * This value will be a non-null string if one of your custom stop sequences was generated.
         */
        @JsonProperty("stop_sequence")
        @ExcludeMissing
        fun stopSequence(stopSequence: JsonField<String>) = apply {
            this.stopSequence = stopSequence
        }

        /**
         * Billing and rate-limit usage.
         *
         * Anthropic's API bills and rate-limits by token counts, as tokens represent the underlying
         * cost to our systems.
         *
         * Under the hood, the API transforms requests into a format suitable for the model. The
         * model's output then goes through a parsing stage before becoming an API response. As a
         * result, the token counts in `usage` will not match one-to-one with the exact visible
         * content of an API request or response.
         *
         * For example, `output_tokens` will be non-zero, even for an empty string response from
         * Claude.
         */
        fun usage(usage: Usage) = usage(JsonField.of(usage))

        /**
         * Billing and rate-limit usage.
         *
         * Anthropic's API bills and rate-limits by token counts, as tokens represent the underlying
         * cost to our systems.
         *
         * Under the hood, the API transforms requests into a format suitable for the model. The
         * model's output then goes through a parsing stage before becoming an API response. As a
         * result, the token counts in `usage` will not match one-to-one with the exact visible
         * content of an API request or response.
         *
         * For example, `output_tokens` will be non-zero, even for an empty string response from
         * Claude.
         */
        @JsonProperty("usage")
        @ExcludeMissing
        fun usage(usage: JsonField<Usage>) = apply { this.usage = usage }

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

        fun build(): Message =
            Message(
                id,
                type,
                role,
                content.map { it.toImmutable() },
                model,
                stopReason,
                stopSequence,
                usage,
                additionalProperties.toImmutable(),
            )
    }

    class Role
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val ASSISTANT = of("assistant")

            @JvmStatic fun of(value: String) = Role(JsonField.of(value))
        }

        enum class Known {
            ASSISTANT,
        }

        enum class Value {
            ASSISTANT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                ASSISTANT -> Value.ASSISTANT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                ASSISTANT -> Known.ASSISTANT
                else -> throw AnthropicInvalidDataException("Unknown Role: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Role && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class StopReason
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val END_TURN = of("end_turn")

            @JvmField val MAX_TOKENS = of("max_tokens")

            @JvmField val STOP_SEQUENCE = of("stop_sequence")

            @JvmField val TOOL_USE = of("tool_use")

            @JvmStatic fun of(value: String) = StopReason(JsonField.of(value))
        }

        enum class Known {
            END_TURN,
            MAX_TOKENS,
            STOP_SEQUENCE,
            TOOL_USE,
        }

        enum class Value {
            END_TURN,
            MAX_TOKENS,
            STOP_SEQUENCE,
            TOOL_USE,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                END_TURN -> Value.END_TURN
                MAX_TOKENS -> Value.MAX_TOKENS
                STOP_SEQUENCE -> Value.STOP_SEQUENCE
                TOOL_USE -> Value.TOOL_USE
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                END_TURN -> Known.END_TURN
                MAX_TOKENS -> Known.MAX_TOKENS
                STOP_SEQUENCE -> Known.STOP_SEQUENCE
                TOOL_USE -> Known.TOOL_USE
                else -> throw AnthropicInvalidDataException("Unknown StopReason: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is StopReason && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MESSAGE = of("message")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            MESSAGE,
        }

        enum class Value {
            MESSAGE,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                MESSAGE -> Value.MESSAGE
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                MESSAGE -> Known.MESSAGE
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is Message && id == other.id && type == other.type && role == other.role && content == other.content && model == other.model && stopReason == other.stopReason && stopSequence == other.stopSequence && usage == other.usage && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, type, role, content, model, stopReason, stopSequence, usage, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Message{id=$id, type=$type, role=$role, content=$content, model=$model, stopReason=$stopReason, stopSequence=$stopSequence, usage=$usage, additionalProperties=$additionalProperties}"
}
