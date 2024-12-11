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

@JsonDeserialize(builder = Completion.Builder::class)
@NoAutoDetect
class Completion
private constructor(
    private val type: JsonField<Type>,
    private val id: JsonField<String>,
    private val completion: JsonField<String>,
    private val stopReason: JsonField<String>,
    private val model: JsonField<Model>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    /**
     * Object type.
     *
     * For Text Completions, this is always `"completion"`.
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     */
    fun id(): String = id.getRequired("id")

    /** The resulting completion up to and excluding the stop sequences. */
    fun completion(): String = completion.getRequired("completion")

    /**
     * The reason that we stopped.
     *
     * This may be one the following values:
     * - `"stop_sequence"`: we reached a stop sequence — either provided by you via the
     *   `stop_sequences` parameter, or a stop sequence built into the model
     * - `"max_tokens"`: we exceeded `max_tokens_to_sample` or the model's maximum
     */
    fun stopReason(): Optional<String> = Optional.ofNullable(stopReason.getNullable("stop_reason"))

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     */
    fun model(): Model = model.getRequired("model")

    /**
     * Object type.
     *
     * For Text Completions, this is always `"completion"`.
     */
    @JsonProperty("type") @ExcludeMissing fun _type() = type

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     */
    @JsonProperty("id") @ExcludeMissing fun _id() = id

    /** The resulting completion up to and excluding the stop sequences. */
    @JsonProperty("completion") @ExcludeMissing fun _completion() = completion

    /**
     * The reason that we stopped.
     *
     * This may be one the following values:
     * - `"stop_sequence"`: we reached a stop sequence — either provided by you via the
     *   `stop_sequences` parameter, or a stop sequence built into the model
     * - `"max_tokens"`: we exceeded `max_tokens_to_sample` or the model's maximum
     */
    @JsonProperty("stop_reason") @ExcludeMissing fun _stopReason() = stopReason

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     */
    @JsonProperty("model") @ExcludeMissing fun _model() = model

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): Completion = apply {
        if (!validated) {
            type()
            id()
            completion()
            stopReason()
            model()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var id: JsonField<String> = JsonMissing.of()
        private var completion: JsonField<String> = JsonMissing.of()
        private var stopReason: JsonField<String> = JsonMissing.of()
        private var model: JsonField<Model> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(completion: Completion) = apply {
            this.type = completion.type
            this.id = completion.id
            this.completion = completion.completion
            this.stopReason = completion.stopReason
            this.model = completion.model
            additionalProperties(completion.additionalProperties)
        }

        /**
         * Object type.
         *
         * For Text Completions, this is always `"completion"`.
         */
        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Object type.
         *
         * For Text Completions, this is always `"completion"`.
         */
        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

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

        /** The resulting completion up to and excluding the stop sequences. */
        fun completion(completion: String) = completion(JsonField.of(completion))

        /** The resulting completion up to and excluding the stop sequences. */
        @JsonProperty("completion")
        @ExcludeMissing
        fun completion(completion: JsonField<String>) = apply { this.completion = completion }

        /**
         * The reason that we stopped.
         *
         * This may be one the following values:
         * - `"stop_sequence"`: we reached a stop sequence — either provided by you via the
         *   `stop_sequences` parameter, or a stop sequence built into the model
         * - `"max_tokens"`: we exceeded `max_tokens_to_sample` or the model's maximum
         */
        fun stopReason(stopReason: String) = stopReason(JsonField.of(stopReason))

        /**
         * The reason that we stopped.
         *
         * This may be one the following values:
         * - `"stop_sequence"`: we reached a stop sequence — either provided by you via the
         *   `stop_sequences` parameter, or a stop sequence built into the model
         * - `"max_tokens"`: we exceeded `max_tokens_to_sample` or the model's maximum
         */
        @JsonProperty("stop_reason")
        @ExcludeMissing
        fun stopReason(stopReason: JsonField<String>) = apply { this.stopReason = stopReason }

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

        fun build(): Completion =
            Completion(
                type,
                id,
                completion,
                stopReason,
                model,
                additionalProperties.toImmutable(),
            )
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val COMPLETION = of("completion")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            COMPLETION,
        }

        enum class Value {
            COMPLETION,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                COMPLETION -> Value.COMPLETION
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                COMPLETION -> Known.COMPLETION
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

        return /* spotless:off */ other is Completion && type == other.type && id == other.id && completion == other.completion && stopReason == other.stopReason && model == other.model && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, id, completion, stopReason, model, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Completion{type=$type, id=$id, completion=$completion, stopReason=$stopReason, model=$model, additionalProperties=$additionalProperties}"
}
