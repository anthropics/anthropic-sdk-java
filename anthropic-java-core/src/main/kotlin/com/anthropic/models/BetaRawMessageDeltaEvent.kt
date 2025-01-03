// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects
import java.util.Optional

@NoAutoDetect
class BetaRawMessageDeltaEvent
@JsonCreator
private constructor(
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonProperty("delta") @ExcludeMissing private val delta: JsonField<Delta> = JsonMissing.of(),
    @JsonProperty("usage")
    @ExcludeMissing
    private val usage: JsonField<BetaMessageDeltaUsage> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun type(): Type = type.getRequired("type")

    fun delta(): Delta = delta.getRequired("delta")

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
    fun usage(): BetaMessageDeltaUsage = usage.getRequired("usage")

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("delta") @ExcludeMissing fun _delta() = delta

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

    private var validated: Boolean = false

    fun validate(): BetaRawMessageDeltaEvent = apply {
        if (!validated) {
            type()
            delta().validate()
            usage().validate()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var delta: JsonField<Delta> = JsonMissing.of()
        private var usage: JsonField<BetaMessageDeltaUsage> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRawMessageDeltaEvent: BetaRawMessageDeltaEvent) = apply {
            type = betaRawMessageDeltaEvent.type
            delta = betaRawMessageDeltaEvent.delta
            usage = betaRawMessageDeltaEvent.usage
            additionalProperties = betaRawMessageDeltaEvent.additionalProperties.toMutableMap()
        }

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun delta(delta: Delta) = delta(JsonField.of(delta))

        fun delta(delta: JsonField<Delta>) = apply { this.delta = delta }

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
        fun usage(usage: BetaMessageDeltaUsage) = usage(JsonField.of(usage))

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
        fun usage(usage: JsonField<BetaMessageDeltaUsage>) = apply { this.usage = usage }

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

        fun build(): BetaRawMessageDeltaEvent =
            BetaRawMessageDeltaEvent(
                type,
                delta,
                usage,
                additionalProperties.toImmutable(),
            )
    }

    @NoAutoDetect
    class Delta
    @JsonCreator
    private constructor(
        @JsonProperty("stop_reason")
        @ExcludeMissing
        private val stopReason: JsonField<StopReason> = JsonMissing.of(),
        @JsonProperty("stop_sequence")
        @ExcludeMissing
        private val stopSequence: JsonField<String> = JsonMissing.of(),
        @JsonAnySetter
        private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
    ) {

        fun stopReason(): Optional<StopReason> =
            Optional.ofNullable(stopReason.getNullable("stop_reason"))

        fun stopSequence(): Optional<String> =
            Optional.ofNullable(stopSequence.getNullable("stop_sequence"))

        @JsonProperty("stop_reason") @ExcludeMissing fun _stopReason() = stopReason

        @JsonProperty("stop_sequence") @ExcludeMissing fun _stopSequence() = stopSequence

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        private var validated: Boolean = false

        fun validate(): Delta = apply {
            if (!validated) {
                stopReason()
                stopSequence()
                validated = true
            }
        }

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var stopReason: JsonField<StopReason> = JsonMissing.of()
            private var stopSequence: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(delta: Delta) = apply {
                stopReason = delta.stopReason
                stopSequence = delta.stopSequence
                additionalProperties = delta.additionalProperties.toMutableMap()
            }

            fun stopReason(stopReason: StopReason) = stopReason(JsonField.of(stopReason))

            fun stopReason(stopReason: JsonField<StopReason>) = apply {
                this.stopReason = stopReason
            }

            fun stopSequence(stopSequence: String) = stopSequence(JsonField.of(stopSequence))

            fun stopSequence(stopSequence: JsonField<String>) = apply {
                this.stopSequence = stopSequence
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

            fun build(): Delta =
                Delta(
                    stopReason,
                    stopSequence,
                    additionalProperties.toImmutable(),
                )
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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Delta && stopReason == other.stopReason && stopSequence == other.stopSequence && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(stopReason, stopSequence, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Delta{stopReason=$stopReason, stopSequence=$stopSequence, additionalProperties=$additionalProperties}"
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MESSAGE_DELTA = of("message_delta")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            MESSAGE_DELTA,
        }

        enum class Value {
            MESSAGE_DELTA,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                MESSAGE_DELTA -> Value.MESSAGE_DELTA
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                MESSAGE_DELTA -> Known.MESSAGE_DELTA
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

        return /* spotless:off */ other is BetaRawMessageDeltaEvent && type == other.type && delta == other.delta && usage == other.usage && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, delta, usage, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRawMessageDeltaEvent{type=$type, delta=$delta, usage=$usage, additionalProperties=$additionalProperties}"
}
