// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class RawMessageDeltaEvent
private constructor(
    private val delta: JsonField<Delta>,
    private val type: JsonValue,
    private val usage: JsonField<MessageDeltaUsage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("delta") @ExcludeMissing delta: JsonField<Delta> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("usage")
        @ExcludeMissing
        usage: JsonField<MessageDeltaUsage> = JsonMissing.of(),
    ) : this(delta, type, usage, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun delta(): Delta = delta.getRequired("delta")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("message_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
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
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun usage(): MessageDeltaUsage = usage.getRequired("usage")

    /**
     * Returns the raw JSON value of [delta].
     *
     * Unlike [delta], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delta") @ExcludeMissing fun _delta(): JsonField<Delta> = delta

    /**
     * Returns the raw JSON value of [usage].
     *
     * Unlike [usage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("usage") @ExcludeMissing fun _usage(): JsonField<MessageDeltaUsage> = usage

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
         * Returns a mutable builder for constructing an instance of [RawMessageDeltaEvent].
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .usage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RawMessageDeltaEvent]. */
    class Builder internal constructor() {

        private var delta: JsonField<Delta>? = null
        private var type: JsonValue = JsonValue.from("message_delta")
        private var usage: JsonField<MessageDeltaUsage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(rawMessageDeltaEvent: RawMessageDeltaEvent) = apply {
            delta = rawMessageDeltaEvent.delta
            type = rawMessageDeltaEvent.type
            usage = rawMessageDeltaEvent.usage
            additionalProperties = rawMessageDeltaEvent.additionalProperties.toMutableMap()
        }

        fun delta(delta: Delta) = delta(JsonField.of(delta))

        /**
         * Sets [Builder.delta] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delta] with a well-typed [Delta] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun delta(delta: JsonField<Delta>) = apply { this.delta = delta }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("message_delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
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
        fun usage(usage: MessageDeltaUsage) = usage(JsonField.of(usage))

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed [MessageDeltaUsage] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun usage(usage: JsonField<MessageDeltaUsage>) = apply { this.usage = usage }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [RawMessageDeltaEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .usage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): RawMessageDeltaEvent =
            RawMessageDeltaEvent(
                checkRequired("delta", delta),
                type,
                checkRequired("usage", usage),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): RawMessageDeltaEvent = apply {
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

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (delta.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("message_delta")) 1 else 0 } +
            (usage.asKnown().getOrNull()?.validity() ?: 0)

    class Delta
    private constructor(
        private val stopReason: JsonField<StopReason>,
        private val stopSequence: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("stop_reason")
            @ExcludeMissing
            stopReason: JsonField<StopReason> = JsonMissing.of(),
            @JsonProperty("stop_sequence")
            @ExcludeMissing
            stopSequence: JsonField<String> = JsonMissing.of(),
        ) : this(stopReason, stopSequence, mutableMapOf())

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun stopReason(): Optional<StopReason> = stopReason.getOptional("stop_reason")

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun stopSequence(): Optional<String> = stopSequence.getOptional("stop_sequence")

        /**
         * Returns the raw JSON value of [stopReason].
         *
         * Unlike [stopReason], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("stop_reason")
        @ExcludeMissing
        fun _stopReason(): JsonField<StopReason> = stopReason

        /**
         * Returns the raw JSON value of [stopSequence].
         *
         * Unlike [stopSequence], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("stop_sequence")
        @ExcludeMissing
        fun _stopSequence(): JsonField<String> = stopSequence

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
             * Returns a mutable builder for constructing an instance of [Delta].
             *
             * The following fields are required:
             * ```java
             * .stopReason()
             * .stopSequence()
             * ```
             */
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

            /** Alias for calling [Builder.stopReason] with `stopReason.orElse(null)`. */
            fun stopReason(stopReason: Optional<StopReason>) = stopReason(stopReason.getOrNull())

            /**
             * Sets [Builder.stopReason] to an arbitrary JSON value.
             *
             * You should usually call [Builder.stopReason] with a well-typed [StopReason] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun stopReason(stopReason: JsonField<StopReason>) = apply {
                this.stopReason = stopReason
            }

            fun stopSequence(stopSequence: String?) =
                stopSequence(JsonField.ofNullable(stopSequence))

            /** Alias for calling [Builder.stopSequence] with `stopSequence.orElse(null)`. */
            fun stopSequence(stopSequence: Optional<String>) =
                stopSequence(stopSequence.getOrNull())

            /**
             * Sets [Builder.stopSequence] to an arbitrary JSON value.
             *
             * You should usually call [Builder.stopSequence] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun stopSequence(stopSequence: JsonField<String>) = apply {
                this.stopSequence = stopSequence
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAlladditional_properties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAlladditional_properties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Delta].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .stopReason()
             * .stopSequence()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Delta =
                Delta(
                    checkRequired("stopReason", stopReason),
                    checkRequired("stopSequence", stopSequence),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Delta = apply {
            if (validated) {
                return@apply
            }

            stopReason().ifPresent { it.validate() }
            stopSequence()
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
            (stopReason.asKnown().getOrNull()?.validity() ?: 0) +
                (if (stopSequence.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Delta &&
                stopReason == other.stopReason &&
                stopSequence == other.stopSequence &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(stopReason, stopSequence, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Delta{stopReason=$stopReason, stopSequence=$stopSequence, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RawMessageDeltaEvent &&
            delta == other.delta &&
            type == other.type &&
            usage == other.usage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(delta, type, usage, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RawMessageDeltaEvent{delta=$delta, type=$type, usage=$usage, additionalProperties=$additionalProperties}"
}
