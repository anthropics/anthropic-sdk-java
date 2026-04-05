// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.kmp.util.core.contentHash

/** Effort (reasoning_effort) capability details. */
class BetaEffortCapability
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val high: JsonField<BetaCapabilitySupport>,
    private val low: JsonField<BetaCapabilitySupport>,
    private val max: JsonField<BetaCapabilitySupport>,
    private val medium: JsonField<BetaCapabilitySupport>,
    private val supported: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("high")
        @ExcludeMissing
        high: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("low")
        @ExcludeMissing
        low: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("max")
        @ExcludeMissing
        max: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("medium")
        @ExcludeMissing
        medium: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("supported") @ExcludeMissing supported: JsonField<Boolean> = JsonMissing.of(),
    ) : this(high, low, max, medium, supported, mutableMapOf())

    /**
     * Whether the model supports high effort level.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun high(): BetaCapabilitySupport = high.getRequired("high")

    /**
     * Whether the model supports low effort level.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun low(): BetaCapabilitySupport = low.getRequired("low")

    /**
     * Whether the model supports max effort level.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun max(): BetaCapabilitySupport = max.getRequired("max")

    /**
     * Whether the model supports medium effort level.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun medium(): BetaCapabilitySupport = medium.getRequired("medium")

    /**
     * Whether this capability is supported by the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun supported(): Boolean = supported.getRequired("supported")

    /**
     * Returns the raw JSON value of [high].
     *
     * Unlike [high], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("high") @ExcludeMissing fun _high(): JsonField<BetaCapabilitySupport> = high

    /**
     * Returns the raw JSON value of [low].
     *
     * Unlike [low], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("low") @ExcludeMissing fun _low(): JsonField<BetaCapabilitySupport> = low

    /**
     * Returns the raw JSON value of [max].
     *
     * Unlike [max], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max") @ExcludeMissing fun _max(): JsonField<BetaCapabilitySupport> = max

    /**
     * Returns the raw JSON value of [medium].
     *
     * Unlike [medium], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("medium") @ExcludeMissing fun _medium(): JsonField<BetaCapabilitySupport> = medium

    /**
     * Returns the raw JSON value of [supported].
     *
     * Unlike [supported], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("supported") @ExcludeMissing fun _supported(): JsonField<Boolean> = supported

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        additionalProperties.toMap()

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaEffortCapability].
         *
         * The following fields are required:
         * ```java
         * .high()
         * .low()
         * .max()
         * .medium()
         * .supported()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaEffortCapability]. */
    class Builder internal constructor() {

        private var high: JsonField<BetaCapabilitySupport>? = null
        private var low: JsonField<BetaCapabilitySupport>? = null
        private var max: JsonField<BetaCapabilitySupport>? = null
        private var medium: JsonField<BetaCapabilitySupport>? = null
        private var supported: JsonField<Boolean>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(betaEffortCapability: BetaEffortCapability) = apply {
            high = betaEffortCapability.high
            low = betaEffortCapability.low
            max = betaEffortCapability.max
            medium = betaEffortCapability.medium
            supported = betaEffortCapability.supported
            additionalProperties = betaEffortCapability.additionalProperties.toMutableMap()
        }

        /** Whether the model supports high effort level. */
        fun high(high: BetaCapabilitySupport) = high(JsonField.of(high))

        /**
         * Sets [Builder.high] to an arbitrary JSON value.
         *
         * You should usually call [Builder.high] with a well-typed [BetaCapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun high(high: JsonField<BetaCapabilitySupport>) = apply { this.high = high }

        /** Whether the model supports low effort level. */
        fun low(low: BetaCapabilitySupport) = low(JsonField.of(low))

        /**
         * Sets [Builder.low] to an arbitrary JSON value.
         *
         * You should usually call [Builder.low] with a well-typed [BetaCapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun low(low: JsonField<BetaCapabilitySupport>) = apply { this.low = low }

        /** Whether the model supports max effort level. */
        fun max(max: BetaCapabilitySupport) = max(JsonField.of(max))

        /**
         * Sets [Builder.max] to an arbitrary JSON value.
         *
         * You should usually call [Builder.max] with a well-typed [BetaCapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun max(max: JsonField<BetaCapabilitySupport>) = apply { this.max = max }

        /** Whether the model supports medium effort level. */
        fun medium(medium: BetaCapabilitySupport) = medium(JsonField.of(medium))

        /**
         * Sets [Builder.medium] to an arbitrary JSON value.
         *
         * You should usually call [Builder.medium] with a well-typed [BetaCapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun medium(medium: JsonField<BetaCapabilitySupport>) = apply { this.medium = medium }

        /** Whether this capability is supported by the model. */
        fun supported(supported: Boolean) = supported(JsonField.of(supported))

        /**
         * Sets [Builder.supported] to an arbitrary JSON value.
         *
         * You should usually call [Builder.supported] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun supported(supported: JsonField<Boolean>) = apply { this.supported = supported }

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
         * Returns an immutable instance of [BetaEffortCapability].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .high()
         * .low()
         * .max()
         * .medium()
         * .supported()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaEffortCapability =
            BetaEffortCapability(
                checkRequired("high", high),
                checkRequired("low", low),
                checkRequired("max", max),
                checkRequired("medium", medium),
                checkRequired("supported", supported),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaEffortCapability = apply {
        if (validated) {
            return@apply
        }

        high().validate()
        low().validate()
        max().validate()
        medium().validate()
        supported()
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
    internal fun validity(): Int =
        (high.asKnown()?.validity() ?: 0) +
            (low.asKnown()?.validity() ?: 0) +
            (max.asKnown()?.validity() ?: 0) +
            (medium.asKnown()?.validity() ?: 0) +
            (if (supported.asKnown() != null) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaEffortCapability &&
            high == other.high &&
            low == other.low &&
            max == other.max &&
            medium == other.medium &&
            supported == other.supported &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        contentHash(high, low, max, medium, supported, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaEffortCapability{high=$high, low=$low, max=$max, medium=$medium, supported=$supported, additionalProperties=$additionalProperties}"
}
