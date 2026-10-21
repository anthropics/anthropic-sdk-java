package com.anthropic.models.beta.sessions

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaMonetaryAmount
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * A hard spend ceiling. The session stops issuing new model requests once the tracked list cost
 * reaches `max_list_cost`.
 */
class BetaManagedAgentsBudgetLimit
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val maxListCost: JsonField<BetaMonetaryAmount>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("max_list_cost")
        @ExcludeMissing
        maxListCost: JsonField<BetaMonetaryAmount> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(maxListCost, type, mutableMapOf())

    /**
     * Maximum list cost the session may accrue. List price is used regardless of any negotiated
     * discount, so the cap fires at or before the actual charge.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun maxListCost(): BetaMonetaryAmount = maxListCost.getRequired("max_list_cost")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("limit")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [maxListCost].
     *
     * Unlike [maxListCost], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_list_cost")
    @ExcludeMissing
    fun _maxListCost(): JsonField<BetaMonetaryAmount> = maxListCost

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsBudgetLimit].
         *
         * The following fields are required:
         * ```java
         * .maxListCost()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsBudgetLimit] with the required
         * [maxListCost] set to the given value.
         */
        @JvmStatic
        fun of(maxListCost: BetaMonetaryAmount) = builder().maxListCost(maxListCost).build()
    }

    /** A builder for [BetaManagedAgentsBudgetLimit]. */
    class Builder internal constructor() {

        private var maxListCost: JsonField<BetaMonetaryAmount>? = null
        private var type: JsonValue = JsonValue.from("limit")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsBudgetLimit: BetaManagedAgentsBudgetLimit) = apply {
            maxListCost = betaManagedAgentsBudgetLimit.maxListCost
            type = betaManagedAgentsBudgetLimit.type
            additionalProperties = betaManagedAgentsBudgetLimit.additionalProperties.toMutableMap()
        }

        /**
         * Maximum list cost the session may accrue. List price is used regardless of any negotiated
         * discount, so the cap fires at or before the actual charge.
         */
        fun maxListCost(maxListCost: BetaMonetaryAmount) = maxListCost(JsonField.of(maxListCost))

        /**
         * Sets [Builder.maxListCost] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxListCost] with a well-typed [BetaMonetaryAmount]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun maxListCost(maxListCost: JsonField<BetaMonetaryAmount>) = apply {
            this.maxListCost = maxListCost
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("limit")
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
         * Returns an immutable instance of [BetaManagedAgentsBudgetLimit].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .maxListCost()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsBudgetLimit =
            BetaManagedAgentsBudgetLimit(
                checkRequired("maxListCost", maxListCost),
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
    fun validate(): BetaManagedAgentsBudgetLimit = apply {
        if (validated) {
            return@apply
        }

        maxListCost().validate()
        _type().let {
            if (it != JsonValue.from("limit")) {
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
        (maxListCost.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("limit")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsBudgetLimit &&
            maxListCost == other.maxListCost &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(maxListCost, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsBudgetLimit{maxListCost=$maxListCost, type=$type, additionalProperties=$additionalProperties}"
}
