// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.errors.ApiInvalidDataException
import kotlinx.kmp.util.core.json.JsonAnyGetter
import kotlinx.kmp.util.core.json.JsonAnySetter
import kotlinx.kmp.util.core.json.JsonCreator
import kotlinx.kmp.util.core.json.JsonCreatorMode
import kotlinx.kmp.util.core.json.JsonProperty
import kotlinx.kmp.util.core.contentHash

/** Effort (reasoning_effort) capability details. */
class EffortCapability
@JsonCreator(mode = JsonCreatorMode.DISABLED)
private constructor(
    private val high: JsonField<CapabilitySupport>,
    private val low: JsonField<CapabilitySupport>,
    private val max: JsonField<CapabilitySupport>,
    private val medium: JsonField<CapabilitySupport>,
    private val supported: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("high") @ExcludeMissing high: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("low") @ExcludeMissing low: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("max") @ExcludeMissing max: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("medium")
        @ExcludeMissing
        medium: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("supported") @ExcludeMissing supported: JsonField<Boolean> = JsonMissing.of(),
    ) : this(high, low, max, medium, supported, mutableMapOf())

    /**
     * Whether the model supports high effort level.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun high(): CapabilitySupport = high.getRequired("high")

    /**
     * Whether the model supports low effort level.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun low(): CapabilitySupport = low.getRequired("low")

    /**
     * Whether the model supports max effort level.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun max(): CapabilitySupport = max.getRequired("max")

    /**
     * Whether the model supports medium effort level.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun medium(): CapabilitySupport = medium.getRequired("medium")

    /**
     * Whether this capability is supported by the model.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun supported(): Boolean = supported.getRequired("supported")

    /**
     * Returns the raw JSON value of [high].
     *
     * Unlike [high], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("high") @ExcludeMissing fun _high(): JsonField<CapabilitySupport> = high

    /**
     * Returns the raw JSON value of [low].
     *
     * Unlike [low], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("low") @ExcludeMissing fun _low(): JsonField<CapabilitySupport> = low

    /**
     * Returns the raw JSON value of [max].
     *
     * Unlike [max], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max") @ExcludeMissing fun _max(): JsonField<CapabilitySupport> = max

    /**
     * Returns the raw JSON value of [medium].
     *
     * Unlike [medium], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("medium") @ExcludeMissing fun _medium(): JsonField<CapabilitySupport> = medium

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
         * Returns a mutable builder for constructing an instance of [EffortCapability].
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

    /** A builder for [EffortCapability]. */
    class Builder internal constructor() {

        private var high: JsonField<CapabilitySupport>? = null
        private var low: JsonField<CapabilitySupport>? = null
        private var max: JsonField<CapabilitySupport>? = null
        private var medium: JsonField<CapabilitySupport>? = null
        private var supported: JsonField<Boolean>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(effortCapability: EffortCapability) = apply {
            high = effortCapability.high
            low = effortCapability.low
            max = effortCapability.max
            medium = effortCapability.medium
            supported = effortCapability.supported
            additionalProperties = effortCapability.additionalProperties.toMutableMap()
        }

        /** Whether the model supports high effort level. */
        fun high(high: CapabilitySupport) = high(JsonField.of(high))

        /**
         * Sets [Builder.high] to an arbitrary JSON value.
         *
         * You should usually call [Builder.high] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun high(high: JsonField<CapabilitySupport>) = apply { this.high = high }

        /** Whether the model supports low effort level. */
        fun low(low: CapabilitySupport) = low(JsonField.of(low))

        /**
         * Sets [Builder.low] to an arbitrary JSON value.
         *
         * You should usually call [Builder.low] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun low(low: JsonField<CapabilitySupport>) = apply { this.low = low }

        /** Whether the model supports max effort level. */
        fun max(max: CapabilitySupport) = max(JsonField.of(max))

        /**
         * Sets [Builder.max] to an arbitrary JSON value.
         *
         * You should usually call [Builder.max] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun max(max: JsonField<CapabilitySupport>) = apply { this.max = max }

        /** Whether the model supports medium effort level. */
        fun medium(medium: CapabilitySupport) = medium(JsonField.of(medium))

        /**
         * Sets [Builder.medium] to an arbitrary JSON value.
         *
         * You should usually call [Builder.medium] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun medium(medium: JsonField<CapabilitySupport>) = apply { this.medium = medium }

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
         * Returns an immutable instance of [EffortCapability].
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
        fun build(): EffortCapability =
            EffortCapability(
                checkRequired("high", high),
                checkRequired("low", low),
                checkRequired("max", max),
                checkRequired("medium", medium),
                checkRequired("supported", supported),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): EffortCapability = apply {
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
        } catch (e: ApiInvalidDataException) {
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

        return other is EffortCapability &&
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
        "EffortCapability{high=$high, low=$low, max=$max, medium=$medium, supported=$supported, additionalProperties=$additionalProperties}"
}
