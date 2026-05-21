// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaThinkingDelta
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val estimatedTokens: JsonField<Long>,
    private val thinking: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("estimated_tokens")
        @ExcludeMissing
        estimatedTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("thinking") @ExcludeMissing thinking: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(estimatedTokens, thinking, type, mutableMapOf())

    /**
     * Per-frame increment of a coarse, running estimate of the tokens this thinking block has
     * produced so far. Present whenever the `thinking-token-count-2026-05-13` beta is set; `null`
     * unless `thinking.display` resolves to `"omitted"` and a count is due this frame. Sum the
     * increments across `thinking_delta` frames on this block for a progress indicator. Each
     * increment is a non-negative multiple of a fixed quantum and the cadence is rate-limited, so
     * this is a deliberately lossy display hint, not a billable count; `usage.output_tokens`
     * remains authoritative.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun estimatedTokens(): Optional<Long> = estimatedTokens.getOptional("estimated_tokens")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun thinking(): String = thinking.getRequired("thinking")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("thinking_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [estimatedTokens].
     *
     * Unlike [estimatedTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("estimated_tokens")
    @ExcludeMissing
    fun _estimatedTokens(): JsonField<Long> = estimatedTokens

    /**
     * Returns the raw JSON value of [thinking].
     *
     * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("thinking") @ExcludeMissing fun _thinking(): JsonField<String> = thinking

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
         * Returns a mutable builder for constructing an instance of [BetaThinkingDelta].
         *
         * The following fields are required:
         * ```java
         * .estimatedTokens()
         * .thinking()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaThinkingDelta]. */
    class Builder internal constructor() {

        private var estimatedTokens: JsonField<Long>? = null
        private var thinking: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("thinking_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaThinkingDelta: BetaThinkingDelta) = apply {
            estimatedTokens = betaThinkingDelta.estimatedTokens
            thinking = betaThinkingDelta.thinking
            type = betaThinkingDelta.type
            additionalProperties = betaThinkingDelta.additionalProperties.toMutableMap()
        }

        /**
         * Per-frame increment of a coarse, running estimate of the tokens this thinking block has
         * produced so far. Present whenever the `thinking-token-count-2026-05-13` beta is set;
         * `null` unless `thinking.display` resolves to `"omitted"` and a count is due this frame.
         * Sum the increments across `thinking_delta` frames on this block for a progress indicator.
         * Each increment is a non-negative multiple of a fixed quantum and the cadence is
         * rate-limited, so this is a deliberately lossy display hint, not a billable count;
         * `usage.output_tokens` remains authoritative.
         */
        fun estimatedTokens(estimatedTokens: Long?) =
            estimatedTokens(JsonField.ofNullable(estimatedTokens))

        /**
         * Alias for [Builder.estimatedTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun estimatedTokens(estimatedTokens: Long) = estimatedTokens(estimatedTokens as Long?)

        /** Alias for calling [Builder.estimatedTokens] with `estimatedTokens.orElse(null)`. */
        fun estimatedTokens(estimatedTokens: Optional<Long>) =
            estimatedTokens(estimatedTokens.getOrNull())

        /**
         * Sets [Builder.estimatedTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.estimatedTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun estimatedTokens(estimatedTokens: JsonField<Long>) = apply {
            this.estimatedTokens = estimatedTokens
        }

        fun thinking(thinking: String) = thinking(JsonField.of(thinking))

        /**
         * Sets [Builder.thinking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinking] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun thinking(thinking: JsonField<String>) = apply { this.thinking = thinking }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("thinking_delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaThinkingDelta].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .estimatedTokens()
         * .thinking()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaThinkingDelta =
            BetaThinkingDelta(
                checkRequired("estimatedTokens", estimatedTokens),
                checkRequired("thinking", thinking),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaThinkingDelta = apply {
        if (validated) {
            return@apply
        }

        estimatedTokens()
        thinking()
        _type().let {
            if (it != JsonValue.from("thinking_delta")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (estimatedTokens.asKnown().isPresent) 1 else 0) +
            (if (thinking.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("thinking_delta")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaThinkingDelta &&
            estimatedTokens == other.estimatedTokens &&
            thinking == other.thinking &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(estimatedTokens, thinking, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaThinkingDelta{estimatedTokens=$estimatedTokens, thinking=$thinking, type=$type, additionalProperties=$additionalProperties}"
}
