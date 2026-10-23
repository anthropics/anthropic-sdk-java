package com.anthropic.models.beta.organization.analytics.cost

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

/** The cost of one row. */
class BetaAnalyticsCostMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val amount: JsonField<String>,
    private val currency: JsonField<String>,
    private val listAmount: JsonField<String>,
    private val requests: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("amount") @ExcludeMissing amount: JsonField<String> = JsonMissing.of(),
        @JsonProperty("currency") @ExcludeMissing currency: JsonField<String> = JsonMissing.of(),
        @JsonProperty("list_amount")
        @ExcludeMissing
        listAmount: JsonField<String> = JsonMissing.of(),
        @JsonProperty("requests") @ExcludeMissing requests: JsonField<Long> = JsonMissing.of(),
    ) : this(amount, currency, listAmount, requests, mutableMapOf())

    /**
     * Amount (post-discount, pre-credit) in fractional cents (minor units).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun amount(): String = amount.getRequired("amount")

    /**
     * Currency code for the cost amount. Currently always `"USD"`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun currency(): String = currency.getRequired("currency")

    /**
     * List-price amount (pre-discount) in fractional cents.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun listAmount(): String = listAmount.getRequired("list_amount")

    /**
     * Number of API requests in this row's scope. Null when `group_by` includes `cost_type` or
     * `token_type` (the count has no per-component attribution; read it from the ungrouped
     * response). For sandbox / code-execution events, this counts execution spans rather than HTTP
     * requests (these rows surface with `product: null`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun requests(): Optional<Long> = requests.getOptional("requests")

    /**
     * Returns the raw JSON value of [amount].
     *
     * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("amount") @ExcludeMissing fun _amount(): JsonField<String> = amount

    /**
     * Returns the raw JSON value of [currency].
     *
     * Unlike [currency], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("currency") @ExcludeMissing fun _currency(): JsonField<String> = currency

    /**
     * Returns the raw JSON value of [listAmount].
     *
     * Unlike [listAmount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("list_amount") @ExcludeMissing fun _listAmount(): JsonField<String> = listAmount

    /**
     * Returns the raw JSON value of [requests].
     *
     * Unlike [requests], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("requests") @ExcludeMissing fun _requests(): JsonField<Long> = requests

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsCostMetrics].
         *
         * The following fields are required:
         * ```java
         * .amount()
         * .currency()
         * .listAmount()
         * .requests()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsCostMetrics]. */
    class Builder internal constructor() {

        private var amount: JsonField<String>? = null
        private var currency: JsonField<String>? = null
        private var listAmount: JsonField<String>? = null
        private var requests: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsCostMetrics: BetaAnalyticsCostMetrics) = apply {
            amount = betaAnalyticsCostMetrics.amount
            currency = betaAnalyticsCostMetrics.currency
            listAmount = betaAnalyticsCostMetrics.listAmount
            requests = betaAnalyticsCostMetrics.requests
            additionalProperties = betaAnalyticsCostMetrics.additionalProperties.toMutableMap()
        }

        /** Amount (post-discount, pre-credit) in fractional cents (minor units). */
        fun amount(amount: String) = amount(JsonField.of(amount))

        /**
         * Sets [Builder.amount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.amount] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun amount(amount: JsonField<String>) = apply { this.amount = amount }

        /** Currency code for the cost amount. Currently always `"USD"`. */
        fun currency(currency: String) = currency(JsonField.of(currency))

        /**
         * Sets [Builder.currency] to an arbitrary JSON value.
         *
         * You should usually call [Builder.currency] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun currency(currency: JsonField<String>) = apply { this.currency = currency }

        /** List-price amount (pre-discount) in fractional cents. */
        fun listAmount(listAmount: String) = listAmount(JsonField.of(listAmount))

        /**
         * Sets [Builder.listAmount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.listAmount] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun listAmount(listAmount: JsonField<String>) = apply { this.listAmount = listAmount }

        /**
         * Number of API requests in this row's scope. Null when `group_by` includes `cost_type` or
         * `token_type` (the count has no per-component attribution; read it from the ungrouped
         * response). For sandbox / code-execution events, this counts execution spans rather than
         * HTTP requests (these rows surface with `product: null`).
         */
        fun requests(requests: Long?) = requests(JsonField.ofNullable(requests))

        /**
         * Alias for [Builder.requests].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun requests(requests: Long) = requests(requests as Long?)

        /** Alias for calling [Builder.requests] with `requests.orElse(null)`. */
        fun requests(requests: Optional<Long>) = requests(requests.getOrNull())

        /**
         * Sets [Builder.requests] to an arbitrary JSON value.
         *
         * You should usually call [Builder.requests] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun requests(requests: JsonField<Long>) = apply { this.requests = requests }

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
         * Returns an immutable instance of [BetaAnalyticsCostMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .amount()
         * .currency()
         * .listAmount()
         * .requests()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsCostMetrics =
            BetaAnalyticsCostMetrics(
                checkRequired("amount", amount),
                checkRequired("currency", currency),
                checkRequired("listAmount", listAmount),
                checkRequired("requests", requests),
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
    fun validate(): BetaAnalyticsCostMetrics = apply {
        if (validated) {
            return@apply
        }

        amount()
        currency()
        listAmount()
        requests()
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
        (if (amount.asKnown().isPresent) 1 else 0) +
            (if (currency.asKnown().isPresent) 1 else 0) +
            (if (listAmount.asKnown().isPresent) 1 else 0) +
            (if (requests.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsCostMetrics &&
            amount == other.amount &&
            currency == other.currency &&
            listAmount == other.listAmount &&
            requests == other.requests &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(amount, currency, listAmount, requests, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsCostMetrics{amount=$amount, currency=$currency, listAmount=$listAmount, requests=$requests, additionalProperties=$additionalProperties}"
}
