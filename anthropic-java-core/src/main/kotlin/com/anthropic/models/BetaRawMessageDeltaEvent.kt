// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
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
    @JsonProperty("delta") @ExcludeMissing private val delta: JsonField<Delta> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonProperty("usage")
    @ExcludeMissing
    private val usage: JsonField<BetaMessageDeltaUsage> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun delta(): Delta = delta.getRequired("delta")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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
     *
     * Total input tokens in a request is the summation of `input_tokens`,
     * `cache_creation_input_tokens`, and `cache_read_input_tokens`.
     */
    fun usage(): BetaMessageDeltaUsage = usage.getRequired("usage")

    @JsonProperty("delta") @ExcludeMissing fun _delta(): JsonField<Delta> = delta

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
     *
     * Total input tokens in a request is the summation of `input_tokens`,
     * `cache_creation_input_tokens`, and `cache_read_input_tokens`.
     */
    @JsonProperty("usage") @ExcludeMissing fun _usage(): JsonField<BetaMessageDeltaUsage> = usage

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaRawMessageDeltaEvent = apply {
        if (validated) {
            return@apply
        }

        delta().validate()
        _type().let {
            if (it != JsonValue.from("message_delta")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        usage().validate()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRawMessageDeltaEvent]. */
    class Builder internal constructor() {

        private var delta: JsonField<Delta>? = null
        private var type: JsonValue = JsonValue.from("message_delta")
        private var usage: JsonField<BetaMessageDeltaUsage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRawMessageDeltaEvent: BetaRawMessageDeltaEvent) = apply {
            delta = betaRawMessageDeltaEvent.delta
            type = betaRawMessageDeltaEvent.type
            usage = betaRawMessageDeltaEvent.usage
            additionalProperties = betaRawMessageDeltaEvent.additionalProperties.toMutableMap()
        }

        fun delta(delta: Delta) = delta(JsonField.of(delta))

        fun delta(delta: JsonField<Delta>) = apply { this.delta = delta }

        fun type(type: JsonValue) = apply { this.type = type }

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
         *
         * Total input tokens in a request is the summation of `input_tokens`,
         * `cache_creation_input_tokens`, and `cache_read_input_tokens`.
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
         *
         * Total input tokens in a request is the summation of `input_tokens`,
         * `cache_creation_input_tokens`, and `cache_read_input_tokens`.
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
                checkRequired("delta", delta),
                type,
                checkRequired("usage", usage),
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

        @JsonProperty("stop_reason")
        @ExcludeMissing
        fun _stopReason(): JsonField<StopReason> = stopReason

        @JsonProperty("stop_sequence")
        @ExcludeMissing
        fun _stopSequence(): JsonField<String> = stopSequence

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        private var validated: Boolean = false

        fun validate(): Delta = apply {
            if (validated) {
                return@apply
            }

            stopReason()
            stopSequence()
            validated = true
        }

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Delta]. */
        class Builder internal constructor() {

            private var stopReason: JsonField<StopReason>? = null
            private var stopSequence: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(delta: Delta) = apply {
                stopReason = delta.stopReason
                stopSequence = delta.stopSequence
                additionalProperties = delta.additionalProperties.toMutableMap()
            }

            fun stopReason(stopReason: StopReason?) = stopReason(JsonField.ofNullable(stopReason))

            fun stopReason(stopReason: Optional<StopReason>) = stopReason(stopReason.orElse(null))

            fun stopReason(stopReason: JsonField<StopReason>) = apply {
                this.stopReason = stopReason
            }

            fun stopSequence(stopSequence: String?) =
                stopSequence(JsonField.ofNullable(stopSequence))

            fun stopSequence(stopSequence: Optional<String>) =
                stopSequence(stopSequence.orElse(null))

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
                    checkRequired("stopReason", stopReason),
                    checkRequired("stopSequence", stopSequence),
                    additionalProperties.toImmutable(),
                )
        }

        class StopReason @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val END_TURN = of("end_turn")

                @JvmField val MAX_TOKENS = of("max_tokens")

                @JvmField val STOP_SEQUENCE = of("stop_sequence")

                @JvmField val TOOL_USE = of("tool_use")

                @JvmStatic fun of(value: String) = StopReason(JsonField.of(value))
            }

            /** An enum containing [StopReason]'s known values. */
            enum class Known {
                END_TURN,
                MAX_TOKENS,
                STOP_SEQUENCE,
                TOOL_USE,
            }

            /**
             * An enum containing [StopReason]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [StopReason] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                END_TURN,
                MAX_TOKENS,
                STOP_SEQUENCE,
                TOOL_USE,
                /**
                 * An enum member indicating that [StopReason] was instantiated with an unknown
                 * value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    END_TURN -> Value.END_TURN
                    MAX_TOKENS -> Value.MAX_TOKENS
                    STOP_SEQUENCE -> Value.STOP_SEQUENCE
                    TOOL_USE -> Value.TOOL_USE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    END_TURN -> Known.END_TURN
                    MAX_TOKENS -> Known.MAX_TOKENS
                    STOP_SEQUENCE -> Known.STOP_SEQUENCE
                    TOOL_USE -> Known.TOOL_USE
                    else -> throw AnthropicInvalidDataException("Unknown StopReason: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaRawMessageDeltaEvent && delta == other.delta && type == other.type && usage == other.usage && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(delta, type, usage, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRawMessageDeltaEvent{delta=$delta, type=$type, usage=$usage, additionalProperties=$additionalProperties}"
}
