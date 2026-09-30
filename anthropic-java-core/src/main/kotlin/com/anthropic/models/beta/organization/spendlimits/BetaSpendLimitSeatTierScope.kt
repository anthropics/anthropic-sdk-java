package com.anthropic.models.beta.organization.spendlimits

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

class BetaSpendLimitSeatTierScope
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val seatTier: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("seat_tier") @ExcludeMissing seatTier: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(seatTier, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun seatTier(): String = seatTier.getRequired("seat_tier")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("seat_tier")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [seatTier].
     *
     * Unlike [seatTier], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("seat_tier") @ExcludeMissing fun _seatTier(): JsonField<String> = seatTier

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
         * Returns a mutable builder for constructing an instance of [BetaSpendLimitSeatTierScope].
         *
         * The following fields are required:
         * ```java
         * .seatTier()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaSpendLimitSeatTierScope] with the required
         * [seatTier] set to the given value.
         */
        @JvmStatic fun of(seatTier: String) = builder().seatTier(seatTier).build()
    }

    /** A builder for [BetaSpendLimitSeatTierScope]. */
    class Builder internal constructor() {

        private var seatTier: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("seat_tier")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSpendLimitSeatTierScope: BetaSpendLimitSeatTierScope) = apply {
            seatTier = betaSpendLimitSeatTierScope.seatTier
            type = betaSpendLimitSeatTierScope.type
            additionalProperties = betaSpendLimitSeatTierScope.additionalProperties.toMutableMap()
        }

        fun seatTier(seatTier: String) = seatTier(JsonField.of(seatTier))

        /**
         * Sets [Builder.seatTier] to an arbitrary JSON value.
         *
         * You should usually call [Builder.seatTier] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun seatTier(seatTier: JsonField<String>) = apply { this.seatTier = seatTier }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("seat_tier")
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
         * Returns an immutable instance of [BetaSpendLimitSeatTierScope].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .seatTier()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSpendLimitSeatTierScope =
            BetaSpendLimitSeatTierScope(
                checkRequired("seatTier", seatTier),
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
    fun validate(): BetaSpendLimitSeatTierScope = apply {
        if (validated) {
            return@apply
        }

        seatTier()
        _type().let {
            if (it != JsonValue.from("seat_tier")) {
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
        (if (seatTier.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("seat_tier")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSpendLimitSeatTierScope &&
            seatTier == other.seatTier &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(seatTier, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSpendLimitSeatTierScope{seatTier=$seatTier, type=$type, additionalProperties=$additionalProperties}"
}
